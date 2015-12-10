// THIS IS GENERATED CODE, COPY THIS FILE ONE LEVEL UP TO BUILD AND/OR EDIT

#include <sqlite3.h>

#include "GatewayConnector.h"
#include "log.h"
#include "json/value.h"

#include "DataStoreUtils.h"

#include "IncidentPushHandler.h"
#include "IncidentConfigManager.h"

IncidentPushHandler::IncidentPushHandler (sqlite3 *db,
                                          const ammo::gateway::PushData &pd)
  : PushHandler (db, pd)
{
}

bool
IncidentPushHandler::handlePush (void)
{
  IncidentConfigManager *cfg_mgr =
    IncidentConfigManager::getInstance ();

  if (pd_.mimeType == cfg_mgr->mediaMimeType ())
    {
      return pushMedia ();
    }

  if (pd_.mimeType == cfg_mgr->eventMimeType ())
    {
      return pushEvent ();
    }

  if (pd_.mimeType == cfg_mgr->statusMimeType ())
    {
      return pushStatus ();
    }

  if (pd_.mimeType == cfg_mgr->categoryMimeType ())
    {
      return pushCategory ();
    }

  LOG_ERROR ("Bad mime type: " << pd_.mimeType);
  return false;
}

bool
IncidentPushHandler::pushMedia (void)
{
  std::string insert_str ("insert into media_table");
  insert_str += " values (?,?,?,?)";

  int status =
	  sqlite3_prepare_v2 (db_, insert_str.c_str (), -1, &stmt_, 0);

  if (status != SQLITE_OK)
    {
      LOG_ERROR ("Media push - "
                 << "prep of sqlite statement failed: "
		             << sqlite3_errmsg (db_));

      return false;
    }

  Json::Value root;

  if (! DataStoreUtils::parseJson (pd_.data, root))
    {
      return false;
    }

  unsigned int index = 1;

  bool good_binds =
    DataStoreUtils::bind_text (db_, stmt_, index, root["eventId"].asString (), true)
    && DataStoreUtils::bind_text (db_, stmt_, index, root["data"].asString (), true)
    && DataStoreUtils::bind_int (db_, stmt_, index, root["createdDate"].asInt ())
    && DataStoreUtils::bind_int (db_, stmt_, index, root["modifiedDate"].asInt ());

	if (good_binds)
	  {
      status = sqlite3_step (stmt_);

      if (status != SQLITE_DONE)
        {
          LOG_ERROR ("Media push - "
                     << "insert operation failed: "
                     << sqlite3_errmsg (db_));

          return false;
        }
    }

  return good_binds;
}

bool
IncidentPushHandler::pushEvent (void)
{
  std::string insert_str ("insert into event_table");
  insert_str += " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

  int status =
	  sqlite3_prepare_v2 (db_, insert_str.c_str (), -1, &stmt_, 0);

  if (status != SQLITE_OK)
    {
      LOG_ERROR ("Event push - "
                 << "prep of sqlite statement failed: "
		             << sqlite3_errmsg (db_));

      return false;
    }

  Json::Value root;

  if (! DataStoreUtils::parseJson (pd_.data, root))
    {
      return false;
    }

  unsigned int index = 1;

  bool good_binds =
    DataStoreUtils::bind_text (db_, stmt_, index, root["uuid"].asString (), true)
    && DataStoreUtils::bind_int (db_, stmt_, index, root["mediaCount"].asInt ())
    && DataStoreUtils::bind_text (db_, stmt_, index, root["originator"].asString (), true)
    && DataStoreUtils::bind_text (db_, stmt_, index, root["displayName"].asString (), true)
    && DataStoreUtils::bind_text (db_, stmt_, index, root["categoryId"].asString (), true)
    && DataStoreUtils::bind_text (db_, stmt_, index, root["title"].asString (), true)
    && DataStoreUtils::bind_text (db_, stmt_, index, root["description"].asString (), true)
    && DataStoreUtils::bind_double (db_, stmt_, index, root["longitude"].asDouble ())
    && DataStoreUtils::bind_double (db_, stmt_, index, root["latitude"].asDouble ())
    && DataStoreUtils::bind_int (db_, stmt_, index, root["createdDate"].asInt ())
    && DataStoreUtils::bind_int (db_, stmt_, index, root["modifiedDate"].asInt ())
    && DataStoreUtils::bind_text (db_, stmt_, index, root["cid"].asString (), true)
    && DataStoreUtils::bind_text (db_, stmt_, index, root["category"].asString (), true)
    && DataStoreUtils::bind_text (db_, stmt_, index, root["unit"].asString (), true)
    && DataStoreUtils::bind_int (db_, stmt_, index, root["size"].asInt ())
    && DataStoreUtils::bind_text (db_, stmt_, index, root["destGroupType"].asString (), true)
    && DataStoreUtils::bind_text (db_, stmt_, index, root["destGroupName"].asString (), true)
    && DataStoreUtils::bind_int (db_, stmt_, index, root["status"].asInt ());

	if (good_binds)
	  {
      status = sqlite3_step (stmt_);

      if (status != SQLITE_DONE)
        {
          LOG_ERROR ("Event push - "
                     << "insert operation failed: "
                     << sqlite3_errmsg (db_));

          return false;
        }
    }

  return good_binds;
}

bool
IncidentPushHandler::pushStatus (void)
{
  std::string insert_str ("insert into status_table");
  insert_str += " values (?,?,?,?)";

  int status =
	  sqlite3_prepare_v2 (db_, insert_str.c_str (), -1, &stmt_, 0);

  if (status != SQLITE_OK)
    {
      LOG_ERROR ("Status push - "
                 << "prep of sqlite statement failed: "
		             << sqlite3_errmsg (db_));

      return false;
    }

  Json::Value root;

  if (! DataStoreUtils::parseJson (pd_.data, root))
    {
      return false;
    }

  unsigned int index = 1;

  bool good_binds =
    DataStoreUtils::bind_text (db_, stmt_, index, root["eventUuid"].asString (), true)
    && DataStoreUtils::bind_text (db_, stmt_, index, root["status"].asString (), true)
    && DataStoreUtils::bind_text (db_, stmt_, index, root["actor"].asString (), true)
    && DataStoreUtils::bind_int (db_, stmt_, index, root["modifiedDate"].asInt ());

	if (good_binds)
	  {
      status = sqlite3_step (stmt_);

      if (status != SQLITE_DONE)
        {
          LOG_ERROR ("Status push - "
                     << "insert operation failed: "
                     << sqlite3_errmsg (db_));

          return false;
        }
    }

  return good_binds;
}

bool
IncidentPushHandler::pushCategory (void)
{
  std::string insert_str ("insert into category_table");
  insert_str += " values (?,?,?,?)";

  int status =
	  sqlite3_prepare_v2 (db_, insert_str.c_str (), -1, &stmt_, 0);

  if (status != SQLITE_OK)
    {
      LOG_ERROR ("Category push - "
                 << "prep of sqlite statement failed: "
		             << sqlite3_errmsg (db_));

      return false;
    }

  Json::Value root;

  if (! DataStoreUtils::parseJson (pd_.data, root))
    {
      return false;
    }

  unsigned int index = 1;

  bool good_binds =
    DataStoreUtils::bind_text (db_, stmt_, index, root["mainCategory"].asString (), true)
    && DataStoreUtils::bind_text (db_, stmt_, index, root["subCategory"].asString (), true)
    && DataStoreUtils::bind_text (db_, stmt_, index, root["tigrId"].asString (), true)
    && DataStoreUtils::bind_text (db_, stmt_, index, root["icon"].asString (), true);

	if (good_binds)
	  {
      status = sqlite3_step (stmt_);

      if (status != SQLITE_DONE)
        {
          LOG_ERROR ("Category push - "
                     << "insert operation failed: "
                     << sqlite3_errmsg (db_));

          return false;
        }
    }

  return good_binds;
}