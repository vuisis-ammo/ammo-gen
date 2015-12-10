// THIS IS GENERATED CODE, COPY THIS FILE ONE LEVEL UP TO BUILD AND/OR EDIT

#include <fstream>

#include "ace/OS_NS_sys_stat.h"

#include "log.h"
#include "json/value.h"
#include "json/reader.h"

#include "IncidentConfigManager.h"
#include "IncidentReceiver.h"

const char *CONFIG_DIRECTORY = "ammo-gateway";
const char *CONFIG_FILE = "IncidentPluginConfig.json";

using namespace std;
using namespace ammo::gateway;

IncidentConfigManager *IncidentConfigManager::sharedInstance = 0;

IncidentConfigManager::IncidentConfigManager (
      IncidentReceiver *receiver,
      GatewayConnector *connector)
  : receiver_ (receiver),
    connector_ (connector)
{
  LOG_TRACE ("Parsing config file...");

  string configFilename = findConfigFile ();

  if (configFilename != "")
    {
      ifstream configFile (configFilename.c_str ());

      if (configFile)
        {
          Json::Value root;
          Json::Reader reader;

          bool parsingSuccessful = reader.parse (configFile, root);

          if (parsingSuccessful)
            {
              if (root["DatabasePath"].isString ())
                {
                  db_filepath_ = root["DatabasePath"].asString ();
                }
              else
                {
                  LOG_ERROR ("DatabasePath string is missing "
                             << "or malformed in config file");
                }

              if (root["MediaMimeType"].isString ())
                {
                  string mime_type (root["MediaMimeType"].asString ());
                  LOG_DEBUG ("Registering push and pull interest in " << mime_type);
                  connector_->registerDataInterest (mime_type, receiver_);
                  connector_->registerPullInterest (mime_type, receiver_);
                  mediaMimeType_ = mime_type;
                }
              else
                {
                  LOG_ERROR ("MediaMimeType string is missing "
                             << "or malformed in config file");
                }

              if (root["EventMimeType"].isString ())
                {
                  string mime_type (root["EventMimeType"].asString ());
                  LOG_DEBUG ("Registering push and pull interest in " << mime_type);
                  connector_->registerDataInterest (mime_type, receiver_);
                  connector_->registerPullInterest (mime_type, receiver_);
                  eventMimeType_ = mime_type;
                }
              else
                {
                  LOG_ERROR ("EventMimeType string is missing "
                             << "or malformed in config file");
                }

              if (root["StatusMimeType"].isString ())
                {
                  string mime_type (root["StatusMimeType"].asString ());
                  LOG_DEBUG ("Registering push and pull interest in " << mime_type);
                  connector_->registerDataInterest (mime_type, receiver_);
                  connector_->registerPullInterest (mime_type, receiver_);
                  statusMimeType_ = mime_type;
                }
              else
                {
                  LOG_ERROR ("StatusMimeType string is missing "
                             << "or malformed in config file");
                }

              if (root["CategoryMimeType"].isString ())
                {
                  string mime_type (root["CategoryMimeType"].asString ());
                  LOG_DEBUG ("Registering push and pull interest in " << mime_type);
                  connector_->registerDataInterest (mime_type, receiver_);
                  connector_->registerPullInterest (mime_type, receiver_);
                  categoryMimeType_ = mime_type;
                }
              else
                {
                  LOG_ERROR ("CategoryMimeType string is missing "
                             << "or malformed in config file");
                }

            }
          else
            {
              LOG_ERROR ("JSON parsing error in config file '"
                         << CONFIG_FILE
                         << "'.");
            }

          configFile.close ();
        }
      else
        {
          LOG_WARN ("Could not read from config file '"
                    << CONFIG_FILE
                    << "'.  Using defaults.");
        }
	  }
  else
    {
      LOG_WARN ("Using default configuration.");
    }
}

IncidentConfigManager *
IncidentConfigManager::getInstance (
  IncidentReceiver *receiver,
  GatewayConnector *connector)
{
  if (sharedInstance == 0)
    {
      if (receiver == 0 || connector == 0)
        {
          LOG_ERROR ("First call to getInstance() must pass in"
                     " a receiver and a connector");

          return 0;
        }

	    sharedInstance =
		    new IncidentConfigManager (receiver, connector);
	  }

  return sharedInstance;
}

const std::string &
IncidentConfigManager::db_filepath (void) const
{
  return db_filepath_;
}

const std::string &
IncidentConfigManager::mediaMimeType (void) const
{
  return mediaMimeType_;
}

const std::string &
IncidentConfigManager::eventMimeType (void) const
{
  return eventMimeType_;
}

const std::string &
IncidentConfigManager::statusMimeType (void) const
{
  return statusMimeType_;
}

const std::string &
IncidentConfigManager::categoryMimeType (void) const
{
  return categoryMimeType_;
}

string
IncidentConfigManager::findConfigFile (void)
{
  string filePath;
  ACE_stat statStruct;

  string home, gatewayRoot;

  char *homeC = ACE_OS::getenv("HOME");
  if(homeC == NULL) {
    home = "";
  } else {
    home = homeC;
  }

  char *gatewayRootC = ACE_OS::getenv("GATEWAY_ROOT");
  if(gatewayRootC == NULL) {
    gatewayRoot = "";
  } else {
    gatewayRoot = gatewayRootC;
  }

  filePath = CONFIG_FILE;
  //stat returns 0 if the file exists
  if(ACE_OS::stat(filePath.c_str(), &statStruct)) {
    filePath = home + "/" + "." + CONFIG_DIRECTORY + "/" + CONFIG_FILE;
    if(ACE_OS::stat(filePath.c_str(), &statStruct)) {
      filePath = string("/etc/") + CONFIG_DIRECTORY + "/" + CONFIG_FILE;
      if(ACE_OS::stat(filePath.c_str(), &statStruct)) {
        filePath = gatewayRoot + "/etc/" + CONFIG_FILE;
        if(ACE_OS::stat(filePath.c_str(), &statStruct)) {
          filePath = gatewayRoot + "/build/etc/" + CONFIG_FILE;
          if(ACE_OS::stat(filePath.c_str(), &statStruct)) {
            filePath = string("../etc/") + CONFIG_FILE;
            if(ACE_OS::stat(filePath.c_str(), &statStruct)) {
              LOG_ERROR("No config file found.");
              return "";
            }
          }
        }
      }
    }
  }

  LOG_INFO ("Using config file: " << filePath);
  return filePath;
}