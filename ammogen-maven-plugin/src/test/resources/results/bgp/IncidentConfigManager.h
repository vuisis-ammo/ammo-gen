// THIS IS GENERATED CODE, COPY THIS FILE ONE LEVEL UP TO BUILD AND/OR EDIT

#ifndef INCIDENT_CONFIG_MANAGER_H
#define INCIDENT_CONFIG_MANAGER_H

#include <string>

class IncidentReceiver;

namespace ammo
{
  namespace gateway
  {
    class GatewayConnector;
  }
}

class IncidentConfigManager
{
public:
  static
  IncidentConfigManager *getInstance (
    IncidentReceiver *receiver = 0,
	  ammo::gateway::GatewayConnector *connector = 0);

	const std::string &db_filepath (void) const;

  const std::string &mediaMimeType (void) const;
  const std::string &eventMimeType (void) const;
  const std::string &statusMimeType (void) const;
  const std::string &categoryMimeType (void) const;

private:
  IncidentConfigManager (
    IncidentReceiver *receiver,
    ammo::gateway::GatewayConnector *connector);

  std::string findConfigFile (void);

private:
  static IncidentConfigManager *sharedInstance;

	std::string db_filepath_;
	IncidentReceiver *receiver_;
  ammo::gateway::GatewayConnector *connector_;

  std::string mediaMimeType_;
  std::string eventMimeType_;
  std::string statusMimeType_;
  std::string categoryMimeType_;
};

#endif // INCIDENT_CONFIG_MANAGER_H