// THIS IS GENERATED CODE, COPY THIS FILE ONE LEVEL UP TO BUILD AND/OR EDIT

#ifndef INCIDENT_RECEIVER_H
#define INCIDENT_RECEIVER_H

#include "GatewayConnector.h"

class sqlite3;

class IncidentReceiver : public ammo::gateway::DataPushReceiverListener,
					               public ammo::gateway::GatewayConnectorDelegate,
                         public ammo::gateway::PullRequestReceiverListener
{
public:
  IncidentReceiver (void);
  ~IncidentReceiver (void);

  // GatewayConnectorDelegate methods
  virtual void onConnect (ammo::gateway::GatewayConnector *sender);
  virtual void onDisconnect (ammo::gateway::GatewayConnector *sender);

  // DataPushReceiverListener methods
  virtual void onPushDataReceived (ammo::gateway::GatewayConnector *sender,
							                     ammo::gateway::PushData &pushData);

  // PullRequestReceiverListener methods
  virtual void onPullRequestReceived (ammo::gateway::GatewayConnector *sender,
                                      ammo::gateway::PullRequest &pullReq);

  bool init (void);

private:
  // Creates directories in the database filepath if they don't exist.
  bool check_path (void);

private:
  // Pointer to open database.
  sqlite3 *db_;
};

#endif // INCIDENT_RECEIVER_H