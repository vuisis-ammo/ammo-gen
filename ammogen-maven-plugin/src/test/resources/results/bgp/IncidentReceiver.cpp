// THIS IS GENERATED CODE, COPY THIS FILE ONE LEVEL UP TO BUILD AND/OR EDIT

#include <sqlite3.h>

#include "ace/OS_NS_unistd.h"
#include "ace/OS_NS_sys_stat.h"

#include "log.h"

#include "IncidentReceiver.h"
#include "IncidentPushHandler.h"
#include "IncidentQueryHandler.h"
#include "IncidentConfigManager.h"

using namespace ammo::gateway;

IncidentReceiver::IncidentReceiver (void)
  : db_ (0)
{
}

IncidentReceiver::~IncidentReceiver (void)
{
  LOG_DEBUG ("Closing Incident database...");

  sqlite3_close (db_);
}

void
IncidentReceiver::onConnect (GatewayConnector * /* sender */)
{
}

void
IncidentReceiver::onDisconnect (GatewayConnector * /* sender */)
{
}

void
IncidentReceiver::onPushDataReceived (GatewayConnector * /* sender */,
                                      PushData &pushData)
{
  IncidentPushHandler push_handler (db_, pushData);

  // Error message is output elsewhere if handler fails.
  if (push_handler.handlePush ())
    {
      LOG_TRACE ("Incident data store successful");
    }
}

void
IncidentReceiver::onPullRequestReceived (GatewayConnector *sender,
                                         PullRequest &pullRequest)
{
  if (sender == 0)
    {
      LOG_WARN ("Sender is null, no responses will be sent");
    }

  IncidentConfigManager *cfg_mgr =
    IncidentConfigManager::getInstance ();

  if (pullRequest.mimeType == cfg_mgr->mediaMimeType ())
    {
      MediaQueryHandler query_handler (db_, sender, pullRequest);

      // Error message is output elsewhere if handler fails.
      query_handler.handleQuery ();

      return;
    }

  if (pullRequest.mimeType == cfg_mgr->eventMimeType ())
    {
      EventQueryHandler query_handler (db_, sender, pullRequest);

      // Error message is output elsewhere if handler fails.
      query_handler.handleQuery ();

      return;
    }

  if (pullRequest.mimeType == cfg_mgr->statusMimeType ())
    {
      StatusQueryHandler query_handler (db_, sender, pullRequest);

      // Error message is output elsewhere if handler fails.
      query_handler.handleQuery ();

      return;
    }

  if (pullRequest.mimeType == cfg_mgr->categoryMimeType ())
    {
      CategoryQueryHandler query_handler (db_, sender, pullRequest);

      // Error message is output elsewhere if handler fails.
      query_handler.handleQuery ();

      return;
    }  
}

bool
IncidentReceiver::init (void)
{
  if (!check_path ())
    {
      // check_path() will also output error info.
      LOG_ERROR ("IncidentReceiver::init() failed");
      return false;
    }

  std::string fullpath (
    IncidentConfigManager::getInstance ()->db_filepath ());
  fullpath += "Incident_db.sql3";

  int status = sqlite3_open (fullpath.c_str (), &db_);

  if (status != 0)
    {
      LOG_ERROR ("Incident plugin - " << sqlite3_errmsg (db_));
      return false;
    }

  const char *data_tbl_str = 0;
  char *db_err = 0;

  data_tbl_str =
    "CREATE TABLE IF NOT EXISTS media_table ("
  	  "event_id TEXT,"
  	  "data TEXT,"
  	  "created_date INTEGER,"
  	  "modified_date INTEGER)";

  sqlite3_exec (db_, data_tbl_str, 0, 0, &db_err);

  if (db_err != 0)
    {
  	    LOG_ERROR ("Incident media_table - " << db_err);
  			return false;    
    }

  data_tbl_str =
    "CREATE TABLE IF NOT EXISTS event_table ("
  	  "uuid TEXT,"
  	  "media_count INTEGER,"
  	  "originator TEXT,"
  	  "display_name TEXT,"
  	  "category_id TEXT,"
  	  "title TEXT,"
  	  "description TEXT,"
  	  "longitude REAL,"
  	  "latitude REAL,"
  	  "created_date INTEGER,"
  	  "modified_date INTEGER,"
  	  "cid TEXT,"
  	  "category TEXT,"
  	  "unit TEXT,"
  	  "size INTEGER,"
  	  "dest_group_type TEXT,"
  	  "dest_group_name TEXT,"
  	  "status INTEGER)";

  sqlite3_exec (db_, data_tbl_str, 0, 0, &db_err);

  if (db_err != 0)
    {
  	    LOG_ERROR ("Incident event_table - " << db_err);
  			return false;    
    }

  data_tbl_str =
    "CREATE TABLE IF NOT EXISTS status_table ("
  	  "event_uuid TEXT,"
  	  "status TEXT,"
  	  "actor TEXT,"
  	  "modified_date INTEGER)";

  sqlite3_exec (db_, data_tbl_str, 0, 0, &db_err);

  if (db_err != 0)
    {
  	    LOG_ERROR ("Incident status_table - " << db_err);
  			return false;    
    }

  data_tbl_str =
    "CREATE TABLE IF NOT EXISTS category_table ("
  	  "main_category TEXT,"
  	  "sub_category TEXT,"
  	  "tigr_id TEXT,"
  	  "icon TEXT)";

  sqlite3_exec (db_, data_tbl_str, 0, 0, &db_err);

  if (db_err != 0)
    {
  	    LOG_ERROR ("Incident category_table - " << db_err);
  			return false;    
    }  

  return true;
}

bool
IncidentReceiver::check_path (void)
{
  char delimiter = '/';
  std::string db_filepath (
    IncidentConfigManager::getInstance ()->db_filepath ());

  std::string::size_type lastPos = db_filepath.find_first_not_of (delimiter, 0);
  std::string::size_type pos = db_filepath.find_first_of (delimiter, lastPos);

  std::string seg = db_filepath.substr (lastPos, pos - lastPos);
  bool top_level = true;

  while (std::string::npos != pos || std::string::npos != lastPos)
    {
      //LOG_DEBUG ("segment: " << seg);
      int result = 0;

      switch (db_filepath[0])
        {
          case '/':
            result =
              ACE_OS::chdir (top_level
                             ? (std::string ("/") + seg).c_str ()
                             : seg.c_str ());
            break;
          case '$':
            result =
              ACE_OS::chdir (top_level
                             ? ACE_OS::getenv (seg.c_str () + 1)
                             : seg.c_str ());
            break;
          default:
            result = ACE_OS::chdir (seg.c_str ());
            break;
        }

      if (result == -1)
        {
          LOG_ERROR ("check_path(); error changing current directory to "
                     << seg);
          return false;
        }

      lastPos = db_filepath.find_first_not_of (delimiter, pos);
      pos = db_filepath.find_first_of (delimiter, lastPos);

      // This would get caught in the next iteration but
      // we need to check and possibly exit here.
      if (std::string::npos == lastPos)
        {
          return true;
        }

      seg = db_filepath.substr (lastPos, pos - lastPos);

      top_level = false;
      result = ACE_OS::mkdir (seg.c_str (), S_IRWXU | S_IRWXG | S_IRWXO);

      // EEXIST is ok - directory already existed, ignore the return value.
      if (result != 0 && errno != EEXIST)
        {
          LOG_ERROR ("check_path(): error creating db filepath directory "
                     << seg);
          return false;
        }
    }

  return true;
}