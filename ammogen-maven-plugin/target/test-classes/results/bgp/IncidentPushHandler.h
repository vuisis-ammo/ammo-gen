// THIS IS GENERATED CODE, COPY THIS FILE ONE LEVEL UP TO BUILD AND/OR EDIT

#ifndef INCIDENT_PUSH_HANDLER_H
#define INCIDENT_PUSH_HANDLER_H

#include "PushHandler.h"

class IncidentPushHandler : public PushHandler
{
public:
  IncidentPushHandler (sqlite3 *db,
                       const ammo::gateway::PushData &pd);

  bool handlePush (void);

private:
  bool pushMedia (void);
  bool pushEvent (void);
  bool pushStatus (void);
  bool pushCategory (void);  
};

#endif // INCIDENT_PUSH_HANDLER_H