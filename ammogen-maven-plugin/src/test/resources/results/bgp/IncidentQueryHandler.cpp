// THIS IS GENERATED CODE, COPY THIS FILE ONE LEVEL UP TO BUILD AND/OR EDIT

#include <sqlite3.h>

#include "json/value.h"
#include "json/writer.h"
#include "log.h"
#include "GatewayConnector.h"

#include "IncidentQueryHandler.h"
#include "IncidentConfigManager.h"
#include "DataStoreUtils.h"

MediaQueryParamParser::MediaQueryParamParser (void)
{
}

void
MediaQueryParamParser::parse (const std::string &params)
{
  tokenize (event_id_, params); 
  tokenize (data_, params); 
  tokenize (created_date_min_, params);
  tokenize (created_date_max_, params); 
  tokenize (modified_date_min_, params);
  tokenize (modified_date_max_, params); 

  fini_check (params);
}

MediaQueryStatementBuilder::MediaQueryStatementBuilder (
      const std::string &params,
      sqlite3 *db)
  : QueryStatementBuilder (params, db, "SELECT * FROM ")
{
}

bool
MediaQueryStatementBuilder::build (void)
{
  parser_.parse (params_);

  query_str_ += "media_table WHERE ";

  bool good_adds =
    this->addFilter (parser_.event_id_, "event_id", false)
    && this->addFilter (parser_.data_, "data", false)
    && this->addFilter (parser_.created_date_min_, "created_date", true, true)
    && this->addFilter (parser_.created_date_max_, "created_date", true, false)
    && this->addFilter (parser_.modified_date_min_, "modified_date", true, true)
    && this->addFilter (parser_.modified_date_max_, "modified_date", true, false);

  if (!good_adds)
    {
      return false;
    }

  int status = sqlite3_prepare (db_,
                                query_str_.c_str (),
                                query_str_.length (),
                                &stmt_,
                                0);

  if (status != SQLITE_OK)
    {
      LOG_ERROR ("Preparation of Media query statement failed: "
                 << sqlite3_errmsg (db_));

      return false;
    }

  return this->bind ();
}

bool
MediaQueryStatementBuilder::bind (void)
{
  // SQL insert indexes are 1-based.
  unsigned int index = 1;

  return
    DataStoreUtils::bind_text (db_, stmt_, index, parser_.event_id_, false)
    && DataStoreUtils::bind_text (db_, stmt_, index, parser_.data_, false)
    && DataStoreUtils::bind_int (db_, stmt_, index, parser_.created_date_min_)
    && DataStoreUtils::bind_int (db_, stmt_, index, parser_.created_date_max_)
    && DataStoreUtils::bind_int (db_, stmt_, index, parser_.modified_date_min_)
    && DataStoreUtils::bind_int (db_, stmt_, index, parser_.modified_date_max_);
}

MediaQueryHandler::MediaQueryHandler (
      sqlite3 *db,
      ammo::gateway::GatewayConnector *sender,
      ammo::gateway::PullRequest &pr)
  : db_ (db),
    sender_ (sender),
    pr_ (pr),
    builder_ (pr.query, db)
{
}

void
MediaQueryHandler::handleQuery (void)
{
  if (!builder_.build ())
    {
      LOG_ERROR ("MediaQueryHandler::handleQuery - "
                 "construction of query statement failed");
      return;
    }

  // If the arg is 0, we want unlimited results.	
  unsigned int resultLimit =
    (pr_.maxResults == 0 ? ACE_UINT32_MAX : pr_.maxResults);
  unsigned int index = 0;
  unsigned int skip = 0;

  sqlite3_stmt *stmt = builder_.query ();

  std::string my_mime_type =
    IncidentConfigManager::getInstance ()->mediaMimeType ();

  while (sqlite3_step (stmt) == SQLITE_ROW
         && index < resultLimit)
    {
      if (skip++ < pr_.startFromCount)
        {
          continue;
        }

	    // For insertion, column numbers are 1-based, for extraction
	    // they're 0-based. SQLite retrieves text as const unsigned
	    // char*, reinterpret_cast<> is the only way to convert it
	    // to const char* for std::string assignment.
	    std::string uri (
		    reinterpret_cast<const char *> (sqlite3_column_text (stmt, 0)));

      LOG_TRACE ("Sending response to " << pr_.pluginId);
      LOG_TRACE ("  type: " << my_mime_type);

      ammo::gateway::PullResponse response =
        ammo::gateway::PullResponse::createFromPullRequest (pr_);
      response.mimeType = my_mime_type;
      response.uri = uri;
      this->encode_row (stmt, response.data);

      ++index;

      if (sender_ == 0)
        {
          // No response can be sent, but we will still see the trace
          // and debug output up to this point.  
          continue;
        }

      bool good_response = sender_->pullResponse (response);

      if (!good_response)
	      {
	        LOG_ERROR ("MediaQueryHandler::handleQuery - "
                     "sender->pullResponse() failed");
	      }
    }
}

void
MediaQueryHandler::encode_row (sqlite3_stmt *stmt,
                                  std::string &output)
{
  Json::Value json_value;
  unsigned int index = 1;

  // SQLite retrieves text as const unsigned char*. Overloaded operator
  // interprets it as bool - reinterpret_cast<> is the only way to
  // convert it to const char* for recognition as text.

  static const Json::StaticString event_id ("eventId");
  json_value[event_id] =
    reinterpret_cast<const char *> (sqlite3_column_text (stmt, index++)); 

  static const Json::StaticString data ("data");
  json_value[data] =
    reinterpret_cast<const char *> (sqlite3_column_text (stmt, index++)); 

  static const Json::StaticString created_date ("createdDate");
  json_value[created_date] = sqlite3_column_int (stmt, index++); 

  static const Json::StaticString modified_date ("modifiedDate");
  json_value[modified_date] = sqlite3_column_int (stmt, index++); 

//  LOG_TRACE ("matched row: " << value.toStyledString ());

  Json::FastWriter writer;
  output = writer.write (json_value);
}

//==============================================

EventQueryParamParser::EventQueryParamParser (void)
{
}

void
EventQueryParamParser::parse (const std::string &params)
{
  tokenize (uuid_, params); 
  tokenize (media_count_min_, params);
  tokenize (media_count_max_, params); 
  tokenize (originator_, params); 
  tokenize (display_name_, params); 
  tokenize (category_id_, params); 
  tokenize (title_, params); 
  tokenize (description_, params); 
  tokenize (longitude_min_, params);
  tokenize (longitude_max_, params); 
  tokenize (latitude_min_, params);
  tokenize (latitude_max_, params); 
  tokenize (created_date_min_, params);
  tokenize (created_date_max_, params); 
  tokenize (modified_date_min_, params);
  tokenize (modified_date_max_, params); 
  tokenize (cid_, params); 
  tokenize (category_, params); 
  tokenize (unit_, params); 
  tokenize (size_min_, params);
  tokenize (size_max_, params); 
  tokenize (dest_group_type_, params); 
  tokenize (dest_group_name_, params); 
  tokenize (status_min_, params);
  tokenize (status_max_, params); 

  fini_check (params);
}

EventQueryStatementBuilder::EventQueryStatementBuilder (
      const std::string &params,
      sqlite3 *db)
  : QueryStatementBuilder (params, db, "SELECT * FROM ")
{
}

bool
EventQueryStatementBuilder::build (void)
{
  parser_.parse (params_);

  query_str_ += "event_table WHERE ";

  bool good_adds =
    this->addFilter (parser_.uuid_, "uuid", false)
    && this->addFilter (parser_.media_count_min_, "media_count", true, true)
    && this->addFilter (parser_.media_count_max_, "media_count", true, false)
    && this->addFilter (parser_.originator_, "originator", false)
    && this->addFilter (parser_.display_name_, "display_name", false)
    && this->addFilter (parser_.category_id_, "category_id", false)
    && this->addFilter (parser_.title_, "title", false)
    && this->addFilter (parser_.description_, "description", false)
    && this->addFilter (parser_.longitude_min_, "longitude", true, true)
    && this->addFilter (parser_.longitude_max_, "longitude", true, false)
    && this->addFilter (parser_.latitude_min_, "latitude", true, true)
    && this->addFilter (parser_.latitude_max_, "latitude", true, false)
    && this->addFilter (parser_.created_date_min_, "created_date", true, true)
    && this->addFilter (parser_.created_date_max_, "created_date", true, false)
    && this->addFilter (parser_.modified_date_min_, "modified_date", true, true)
    && this->addFilter (parser_.modified_date_max_, "modified_date", true, false)
    && this->addFilter (parser_.cid_, "cid", false)
    && this->addFilter (parser_.category_, "category", false)
    && this->addFilter (parser_.unit_, "unit", false)
    && this->addFilter (parser_.size_min_, "size", true, true)
    && this->addFilter (parser_.size_max_, "size", true, false)
    && this->addFilter (parser_.dest_group_type_, "dest_group_type", false)
    && this->addFilter (parser_.dest_group_name_, "dest_group_name", false)
    && this->addFilter (parser_.status_min_, "status", true, true)
    && this->addFilter (parser_.status_max_, "status", true, false);

  if (!good_adds)
    {
      return false;
    }

  int status = sqlite3_prepare (db_,
                                query_str_.c_str (),
                                query_str_.length (),
                                &stmt_,
                                0);

  if (status != SQLITE_OK)
    {
      LOG_ERROR ("Preparation of Event query statement failed: "
                 << sqlite3_errmsg (db_));

      return false;
    }

  return this->bind ();
}

bool
EventQueryStatementBuilder::bind (void)
{
  // SQL insert indexes are 1-based.
  unsigned int index = 1;

  return
    DataStoreUtils::bind_text (db_, stmt_, index, parser_.uuid_, false)
    && DataStoreUtils::bind_int (db_, stmt_, index, parser_.media_count_min_)
    && DataStoreUtils::bind_int (db_, stmt_, index, parser_.media_count_max_)
    && DataStoreUtils::bind_text (db_, stmt_, index, parser_.originator_, false)
    && DataStoreUtils::bind_text (db_, stmt_, index, parser_.display_name_, false)
    && DataStoreUtils::bind_text (db_, stmt_, index, parser_.category_id_, false)
    && DataStoreUtils::bind_text (db_, stmt_, index, parser_.title_, false)
    && DataStoreUtils::bind_text (db_, stmt_, index, parser_.description_, false)
    && DataStoreUtils::bind_double (db_, stmt_, index, parser_.longitude_min_)
    && DataStoreUtils::bind_double (db_, stmt_, index, parser_.longitude_max_)
    && DataStoreUtils::bind_double (db_, stmt_, index, parser_.latitude_min_)
    && DataStoreUtils::bind_double (db_, stmt_, index, parser_.latitude_max_)
    && DataStoreUtils::bind_int (db_, stmt_, index, parser_.created_date_min_)
    && DataStoreUtils::bind_int (db_, stmt_, index, parser_.created_date_max_)
    && DataStoreUtils::bind_int (db_, stmt_, index, parser_.modified_date_min_)
    && DataStoreUtils::bind_int (db_, stmt_, index, parser_.modified_date_max_)
    && DataStoreUtils::bind_text (db_, stmt_, index, parser_.cid_, false)
    && DataStoreUtils::bind_text (db_, stmt_, index, parser_.category_, false)
    && DataStoreUtils::bind_text (db_, stmt_, index, parser_.unit_, false)
    && DataStoreUtils::bind_int (db_, stmt_, index, parser_.size_min_)
    && DataStoreUtils::bind_int (db_, stmt_, index, parser_.size_max_)
    && DataStoreUtils::bind_text (db_, stmt_, index, parser_.dest_group_type_, false)
    && DataStoreUtils::bind_text (db_, stmt_, index, parser_.dest_group_name_, false)
    && DataStoreUtils::bind_int (db_, stmt_, index, parser_.status_min_)
    && DataStoreUtils::bind_int (db_, stmt_, index, parser_.status_max_);
}

EventQueryHandler::EventQueryHandler (
      sqlite3 *db,
      ammo::gateway::GatewayConnector *sender,
      ammo::gateway::PullRequest &pr)
  : db_ (db),
    sender_ (sender),
    pr_ (pr),
    builder_ (pr.query, db)
{
}

void
EventQueryHandler::handleQuery (void)
{
  if (!builder_.build ())
    {
      LOG_ERROR ("EventQueryHandler::handleQuery - "
                 "construction of query statement failed");
      return;
    }

  // If the arg is 0, we want unlimited results.	
  unsigned int resultLimit =
    (pr_.maxResults == 0 ? ACE_UINT32_MAX : pr_.maxResults);
  unsigned int index = 0;
  unsigned int skip = 0;

  sqlite3_stmt *stmt = builder_.query ();

  std::string my_mime_type =
    IncidentConfigManager::getInstance ()->eventMimeType ();

  while (sqlite3_step (stmt) == SQLITE_ROW
         && index < resultLimit)
    {
      if (skip++ < pr_.startFromCount)
        {
          continue;
        }

	    // For insertion, column numbers are 1-based, for extraction
	    // they're 0-based. SQLite retrieves text as const unsigned
	    // char*, reinterpret_cast<> is the only way to convert it
	    // to const char* for std::string assignment.
	    std::string uri (
		    reinterpret_cast<const char *> (sqlite3_column_text (stmt, 0)));

      LOG_TRACE ("Sending response to " << pr_.pluginId);
      LOG_TRACE ("  type: " << my_mime_type);

      ammo::gateway::PullResponse response =
        ammo::gateway::PullResponse::createFromPullRequest (pr_);
      response.mimeType = my_mime_type;
      response.uri = uri;
      this->encode_row (stmt, response.data);

      ++index;

      if (sender_ == 0)
        {
          // No response can be sent, but we will still see the trace
          // and debug output up to this point.  
          continue;
        }

      bool good_response = sender_->pullResponse (response);

      if (!good_response)
	      {
	        LOG_ERROR ("EventQueryHandler::handleQuery - "
                     "sender->pullResponse() failed");
	      }
    }
}

void
EventQueryHandler::encode_row (sqlite3_stmt *stmt,
                                  std::string &output)
{
  Json::Value json_value;
  unsigned int index = 1;

  // SQLite retrieves text as const unsigned char*. Overloaded operator
  // interprets it as bool - reinterpret_cast<> is the only way to
  // convert it to const char* for recognition as text.

  static const Json::StaticString uuid ("uuid");
  json_value[uuid] =
    reinterpret_cast<const char *> (sqlite3_column_text (stmt, index++)); 

  static const Json::StaticString media_count ("mediaCount");
  json_value[media_count] = sqlite3_column_int (stmt, index++); 

  static const Json::StaticString originator ("originator");
  json_value[originator] =
    reinterpret_cast<const char *> (sqlite3_column_text (stmt, index++)); 

  static const Json::StaticString display_name ("displayName");
  json_value[display_name] =
    reinterpret_cast<const char *> (sqlite3_column_text (stmt, index++)); 

  static const Json::StaticString category_id ("categoryId");
  json_value[category_id] =
    reinterpret_cast<const char *> (sqlite3_column_text (stmt, index++)); 

  static const Json::StaticString title ("title");
  json_value[title] =
    reinterpret_cast<const char *> (sqlite3_column_text (stmt, index++)); 

  static const Json::StaticString description ("description");
  json_value[description] =
    reinterpret_cast<const char *> (sqlite3_column_text (stmt, index++)); 

  static const Json::StaticString longitude ("longitude");
  json_value[longitude] = sqlite3_column_double (stmt, index++); 

  static const Json::StaticString latitude ("latitude");
  json_value[latitude] = sqlite3_column_double (stmt, index++); 

  static const Json::StaticString created_date ("createdDate");
  json_value[created_date] = sqlite3_column_int (stmt, index++); 

  static const Json::StaticString modified_date ("modifiedDate");
  json_value[modified_date] = sqlite3_column_int (stmt, index++); 

  static const Json::StaticString cid ("cid");
  json_value[cid] =
    reinterpret_cast<const char *> (sqlite3_column_text (stmt, index++)); 

  static const Json::StaticString category ("category");
  json_value[category] =
    reinterpret_cast<const char *> (sqlite3_column_text (stmt, index++)); 

  static const Json::StaticString unit ("unit");
  json_value[unit] =
    reinterpret_cast<const char *> (sqlite3_column_text (stmt, index++)); 

  static const Json::StaticString size ("size");
  json_value[size] = sqlite3_column_int (stmt, index++); 

  static const Json::StaticString dest_group_type ("destGroupType");
  json_value[dest_group_type] =
    reinterpret_cast<const char *> (sqlite3_column_text (stmt, index++)); 

  static const Json::StaticString dest_group_name ("destGroupName");
  json_value[dest_group_name] =
    reinterpret_cast<const char *> (sqlite3_column_text (stmt, index++)); 

  static const Json::StaticString status ("status");
  json_value[status] = sqlite3_column_int (stmt, index++); 

//  LOG_TRACE ("matched row: " << value.toStyledString ());

  Json::FastWriter writer;
  output = writer.write (json_value);
}

//==============================================

StatusQueryParamParser::StatusQueryParamParser (void)
{
}

void
StatusQueryParamParser::parse (const std::string &params)
{
  tokenize (event_uuid_, params); 
  tokenize (status_, params); 
  tokenize (actor_, params); 
  tokenize (modified_date_min_, params);
  tokenize (modified_date_max_, params); 

  fini_check (params);
}

StatusQueryStatementBuilder::StatusQueryStatementBuilder (
      const std::string &params,
      sqlite3 *db)
  : QueryStatementBuilder (params, db, "SELECT * FROM ")
{
}

bool
StatusQueryStatementBuilder::build (void)
{
  parser_.parse (params_);

  query_str_ += "status_table WHERE ";

  bool good_adds =
    this->addFilter (parser_.event_uuid_, "event_uuid", false)
    && this->addFilter (parser_.status_, "status", false)
    && this->addFilter (parser_.actor_, "actor", false)
    && this->addFilter (parser_.modified_date_min_, "modified_date", true, true)
    && this->addFilter (parser_.modified_date_max_, "modified_date", true, false);

  if (!good_adds)
    {
      return false;
    }

  int status = sqlite3_prepare (db_,
                                query_str_.c_str (),
                                query_str_.length (),
                                &stmt_,
                                0);

  if (status != SQLITE_OK)
    {
      LOG_ERROR ("Preparation of Status query statement failed: "
                 << sqlite3_errmsg (db_));

      return false;
    }

  return this->bind ();
}

bool
StatusQueryStatementBuilder::bind (void)
{
  // SQL insert indexes are 1-based.
  unsigned int index = 1;

  return
    DataStoreUtils::bind_text (db_, stmt_, index, parser_.event_uuid_, false)
    && DataStoreUtils::bind_text (db_, stmt_, index, parser_.status_, false)
    && DataStoreUtils::bind_text (db_, stmt_, index, parser_.actor_, false)
    && DataStoreUtils::bind_int (db_, stmt_, index, parser_.modified_date_min_)
    && DataStoreUtils::bind_int (db_, stmt_, index, parser_.modified_date_max_);
}

StatusQueryHandler::StatusQueryHandler (
      sqlite3 *db,
      ammo::gateway::GatewayConnector *sender,
      ammo::gateway::PullRequest &pr)
  : db_ (db),
    sender_ (sender),
    pr_ (pr),
    builder_ (pr.query, db)
{
}

void
StatusQueryHandler::handleQuery (void)
{
  if (!builder_.build ())
    {
      LOG_ERROR ("StatusQueryHandler::handleQuery - "
                 "construction of query statement failed");
      return;
    }

  // If the arg is 0, we want unlimited results.	
  unsigned int resultLimit =
    (pr_.maxResults == 0 ? ACE_UINT32_MAX : pr_.maxResults);
  unsigned int index = 0;
  unsigned int skip = 0;

  sqlite3_stmt *stmt = builder_.query ();

  std::string my_mime_type =
    IncidentConfigManager::getInstance ()->statusMimeType ();

  while (sqlite3_step (stmt) == SQLITE_ROW
         && index < resultLimit)
    {
      if (skip++ < pr_.startFromCount)
        {
          continue;
        }

	    // For insertion, column numbers are 1-based, for extraction
	    // they're 0-based. SQLite retrieves text as const unsigned
	    // char*, reinterpret_cast<> is the only way to convert it
	    // to const char* for std::string assignment.
	    std::string uri (
		    reinterpret_cast<const char *> (sqlite3_column_text (stmt, 0)));

      LOG_TRACE ("Sending response to " << pr_.pluginId);
      LOG_TRACE ("  type: " << my_mime_type);

      ammo::gateway::PullResponse response =
        ammo::gateway::PullResponse::createFromPullRequest (pr_);
      response.mimeType = my_mime_type;
      response.uri = uri;
      this->encode_row (stmt, response.data);

      ++index;

      if (sender_ == 0)
        {
          // No response can be sent, but we will still see the trace
          // and debug output up to this point.  
          continue;
        }

      bool good_response = sender_->pullResponse (response);

      if (!good_response)
	      {
	        LOG_ERROR ("StatusQueryHandler::handleQuery - "
                     "sender->pullResponse() failed");
	      }
    }
}

void
StatusQueryHandler::encode_row (sqlite3_stmt *stmt,
                                  std::string &output)
{
  Json::Value json_value;
  unsigned int index = 1;

  // SQLite retrieves text as const unsigned char*. Overloaded operator
  // interprets it as bool - reinterpret_cast<> is the only way to
  // convert it to const char* for recognition as text.

  static const Json::StaticString event_uuid ("eventUuid");
  json_value[event_uuid] =
    reinterpret_cast<const char *> (sqlite3_column_text (stmt, index++)); 

  static const Json::StaticString status ("status");
  json_value[status] =
    reinterpret_cast<const char *> (sqlite3_column_text (stmt, index++)); 

  static const Json::StaticString actor ("actor");
  json_value[actor] =
    reinterpret_cast<const char *> (sqlite3_column_text (stmt, index++)); 

  static const Json::StaticString modified_date ("modifiedDate");
  json_value[modified_date] = sqlite3_column_int (stmt, index++); 

//  LOG_TRACE ("matched row: " << value.toStyledString ());

  Json::FastWriter writer;
  output = writer.write (json_value);
}

//==============================================

CategoryQueryParamParser::CategoryQueryParamParser (void)
{
}

void
CategoryQueryParamParser::parse (const std::string &params)
{
  tokenize (main_category_, params); 
  tokenize (sub_category_, params); 
  tokenize (tigr_id_, params); 
  tokenize (icon_, params); 

  fini_check (params);
}

CategoryQueryStatementBuilder::CategoryQueryStatementBuilder (
      const std::string &params,
      sqlite3 *db)
  : QueryStatementBuilder (params, db, "SELECT * FROM ")
{
}

bool
CategoryQueryStatementBuilder::build (void)
{
  parser_.parse (params_);

  query_str_ += "category_table WHERE ";

  bool good_adds =
    this->addFilter (parser_.main_category_, "main_category", false)
    && this->addFilter (parser_.sub_category_, "sub_category", false)
    && this->addFilter (parser_.tigr_id_, "tigr_id", false)
    && this->addFilter (parser_.icon_, "icon", false);

  if (!good_adds)
    {
      return false;
    }

  int status = sqlite3_prepare (db_,
                                query_str_.c_str (),
                                query_str_.length (),
                                &stmt_,
                                0);

  if (status != SQLITE_OK)
    {
      LOG_ERROR ("Preparation of Category query statement failed: "
                 << sqlite3_errmsg (db_));

      return false;
    }

  return this->bind ();
}

bool
CategoryQueryStatementBuilder::bind (void)
{
  // SQL insert indexes are 1-based.
  unsigned int index = 1;

  return
    DataStoreUtils::bind_text (db_, stmt_, index, parser_.main_category_, false)
    && DataStoreUtils::bind_text (db_, stmt_, index, parser_.sub_category_, false)
    && DataStoreUtils::bind_text (db_, stmt_, index, parser_.tigr_id_, false)
    && DataStoreUtils::bind_text (db_, stmt_, index, parser_.icon_, false);
}

CategoryQueryHandler::CategoryQueryHandler (
      sqlite3 *db,
      ammo::gateway::GatewayConnector *sender,
      ammo::gateway::PullRequest &pr)
  : db_ (db),
    sender_ (sender),
    pr_ (pr),
    builder_ (pr.query, db)
{
}

void
CategoryQueryHandler::handleQuery (void)
{
  if (!builder_.build ())
    {
      LOG_ERROR ("CategoryQueryHandler::handleQuery - "
                 "construction of query statement failed");
      return;
    }

  // If the arg is 0, we want unlimited results.	
  unsigned int resultLimit =
    (pr_.maxResults == 0 ? ACE_UINT32_MAX : pr_.maxResults);
  unsigned int index = 0;
  unsigned int skip = 0;

  sqlite3_stmt *stmt = builder_.query ();

  std::string my_mime_type =
    IncidentConfigManager::getInstance ()->categoryMimeType ();

  while (sqlite3_step (stmt) == SQLITE_ROW
         && index < resultLimit)
    {
      if (skip++ < pr_.startFromCount)
        {
          continue;
        }

	    // For insertion, column numbers are 1-based, for extraction
	    // they're 0-based. SQLite retrieves text as const unsigned
	    // char*, reinterpret_cast<> is the only way to convert it
	    // to const char* for std::string assignment.
	    std::string uri (
		    reinterpret_cast<const char *> (sqlite3_column_text (stmt, 0)));

      LOG_TRACE ("Sending response to " << pr_.pluginId);
      LOG_TRACE ("  type: " << my_mime_type);

      ammo::gateway::PullResponse response =
        ammo::gateway::PullResponse::createFromPullRequest (pr_);
      response.mimeType = my_mime_type;
      response.uri = uri;
      this->encode_row (stmt, response.data);

      ++index;

      if (sender_ == 0)
        {
          // No response can be sent, but we will still see the trace
          // and debug output up to this point.  
          continue;
        }

      bool good_response = sender_->pullResponse (response);

      if (!good_response)
	      {
	        LOG_ERROR ("CategoryQueryHandler::handleQuery - "
                     "sender->pullResponse() failed");
	      }
    }
}

void
CategoryQueryHandler::encode_row (sqlite3_stmt *stmt,
                                  std::string &output)
{
  Json::Value json_value;
  unsigned int index = 1;

  // SQLite retrieves text as const unsigned char*. Overloaded operator
  // interprets it as bool - reinterpret_cast<> is the only way to
  // convert it to const char* for recognition as text.

  static const Json::StaticString main_category ("mainCategory");
  json_value[main_category] =
    reinterpret_cast<const char *> (sqlite3_column_text (stmt, index++)); 

  static const Json::StaticString sub_category ("subCategory");
  json_value[sub_category] =
    reinterpret_cast<const char *> (sqlite3_column_text (stmt, index++)); 

  static const Json::StaticString tigr_id ("tigrId");
  json_value[tigr_id] =
    reinterpret_cast<const char *> (sqlite3_column_text (stmt, index++)); 

  static const Json::StaticString icon ("icon");
  json_value[icon] =
    reinterpret_cast<const char *> (sqlite3_column_text (stmt, index++)); 

//  LOG_TRACE ("matched row: " << value.toStyledString ());

  Json::FastWriter writer;
  output = writer.write (json_value);
}