// THIS IS GENERATED CODE, COPY THIS FILE ONE LEVEL UP TO BUILD AND/OR EDIT

#include <string>
#include "Incident.pb.h"
#include "Incident_Export.h"

namespace ammo
{
  namespace gateway
  {
    namespace serialization
    {
      class IncidentMedia;
      class IncidentEvent;
      class IncidentStatus;
      class IncidentCategory;
    }
  }
}

using namespace ammo::gateway::serialization;

class Incident_Export incidentMediaData
{
public:
  std::string eventId_;
  std::string data_;
  long createdDate_;
  long modifiedDate_;

  std::string encodeJson (void);
  bool decodeJson (const std::string &data);

  IncidentMedia *encodeProtobuf (void);
  bool decodeProtobuf (const IncidentMedia *msg);
};

class Incident_Export incidentEventData
{
public:
  std::string uuid_;
  long mediaCount_;
  std::string originator_;
  std::string displayName_;
  std::string categoryId_;
  std::string title_;
  std::string description_;
  double longitude_;
  double latitude_;
  long createdDate_;
  long modifiedDate_;
  std::string cid_;
  std::string category_;
  std::string unit_;
  long size_;
  std::string destGroupType_;
  std::string destGroupName_;
  long status_;

  std::string encodeJson (void);
  bool decodeJson (const std::string &data);

  IncidentEvent *encodeProtobuf (void);
  bool decodeProtobuf (const IncidentEvent *msg);
};

class Incident_Export incidentStatusData
{
public:
  std::string eventUuid_;
  std::string status_;
  std::string actor_;
  long modifiedDate_;

  std::string encodeJson (void);
  bool decodeJson (const std::string &data);

  IncidentStatus *encodeProtobuf (void);
  bool decodeProtobuf (const IncidentStatus *msg);
};

class Incident_Export incidentCategoryData
{
public:
  std::string mainCategory_;
  std::string subCategory_;
  std::string tigrId_;
  std::string icon_;

  std::string encodeJson (void);
  bool decodeJson (const std::string &data);

  IncidentCategory *encodeProtobuf (void);
  bool decodeProtobuf (const IncidentCategory *msg);
};