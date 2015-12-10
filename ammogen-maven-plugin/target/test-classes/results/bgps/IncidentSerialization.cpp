// THIS IS GENERATED CODE, COPY THIS FILE ONE LEVEL UP TO BUILD AND/OR EDIT

#include <sstream>

#include "json/value.h"
#include "json/reader.h"
#include "log.h"

#include "IncidentSerialization.h"
#include "Incident.pb.h"

std::string
incidentMediaData::encodeJson (void)
{
  std::ostringstream sstream;
  sstream << "{"
          << "\"eventId\" : "<< "\"" << eventId_ << "\"" << ","
          << "\"data\" : "<< "\"" << data_ << "\"" << ","
          << "\"createdDate\" : "<< createdDate_ << ","
          << "\"modifiedDate\" : "<< modifiedDate_      
          << "}";

  std::string retval (sstream.str ());
  return retval;
}

bool
incidentMediaData::decodeJson (const std::string &data)
{
  Json::Reader jsonReader;
  Json::Value root;
  bool parseSuccess = jsonReader.parse (data, root);

  if (!parseSuccess)
    {
      LOG_ERROR ("JSON parsing error: "
                 << jsonReader.getFormatedErrorMessages ());

      return false;
    }

  eventId_ = root["eventId"].asString ();
  data_ = root["data"].asString ();
  createdDate_ = root["createdDate"].asInt ();
  modifiedDate_ = root["modifiedDate"].asInt ();

  return true;
}

IncidentMedia *
incidentMediaData::encodeProtobuf (void)
{
  IncidentMedia *newMsg =
    new IncidentMedia;

  newMsg->set_event_id (eventId_);
  newMsg->set_data (data_);
  newMsg->set_created_date (createdDate_);
  newMsg->set_modified_date (modifiedDate_);

  return newMsg;
}

bool
incidentMediaData::decodeProtobuf (
  const IncidentMedia *msg)
{
  eventId_ = msg->event_id ();
  data_ = msg->data ();
  createdDate_ = msg->created_date ();
  modifiedDate_ = msg->modified_date ();

  return true;
}

std::string
incidentEventData::encodeJson (void)
{
  std::ostringstream sstream;
  sstream << "{"
          << "\"uuid\" : "<< "\"" << uuid_ << "\"" << ","
          << "\"mediaCount\" : "<< mediaCount_ << ","
          << "\"originator\" : "<< "\"" << originator_ << "\"" << ","
          << "\"displayName\" : "<< "\"" << displayName_ << "\"" << ","
          << "\"categoryId\" : "<< "\"" << categoryId_ << "\"" << ","
          << "\"title\" : "<< "\"" << title_ << "\"" << ","
          << "\"description\" : "<< "\"" << description_ << "\"" << ","
          << "\"longitude\" : "<< longitude_ << ","
          << "\"latitude\" : "<< latitude_ << ","
          << "\"createdDate\" : "<< createdDate_ << ","
          << "\"modifiedDate\" : "<< modifiedDate_ << ","
          << "\"cid\" : "<< "\"" << cid_ << "\"" << ","
          << "\"category\" : "<< "\"" << category_ << "\"" << ","
          << "\"unit\" : "<< "\"" << unit_ << "\"" << ","
          << "\"size\" : "<< size_ << ","
          << "\"destGroupType\" : "<< "\"" << destGroupType_ << "\"" << ","
          << "\"destGroupName\" : "<< "\"" << destGroupName_ << "\"" << ","
          << "\"status\" : "<< status_      
          << "}";

  std::string retval (sstream.str ());
  return retval;
}

bool
incidentEventData::decodeJson (const std::string &data)
{
  Json::Reader jsonReader;
  Json::Value root;
  bool parseSuccess = jsonReader.parse (data, root);

  if (!parseSuccess)
    {
      LOG_ERROR ("JSON parsing error: "
                 << jsonReader.getFormatedErrorMessages ());

      return false;
    }

  uuid_ = root["uuid"].asString ();
  mediaCount_ = root["mediaCount"].asInt ();
  originator_ = root["originator"].asString ();
  displayName_ = root["displayName"].asString ();
  categoryId_ = root["categoryId"].asString ();
  title_ = root["title"].asString ();
  description_ = root["description"].asString ();
  longitude_ = root["longitude"].asDouble ();
  latitude_ = root["latitude"].asDouble ();
  createdDate_ = root["createdDate"].asInt ();
  modifiedDate_ = root["modifiedDate"].asInt ();
  cid_ = root["cid"].asString ();
  category_ = root["category"].asString ();
  unit_ = root["unit"].asString ();
  size_ = root["size"].asInt ();
  destGroupType_ = root["destGroupType"].asString ();
  destGroupName_ = root["destGroupName"].asString ();
  status_ = root["status"].asInt ();

  return true;
}

IncidentEvent *
incidentEventData::encodeProtobuf (void)
{
  IncidentEvent *newMsg =
    new IncidentEvent;

  newMsg->set_uuid (uuid_);
  newMsg->set_media_count (mediaCount_);
  newMsg->set_originator (originator_);
  newMsg->set_display_name (displayName_);
  newMsg->set_category_id (categoryId_);
  newMsg->set_title (title_);
  newMsg->set_description (description_);
  newMsg->set_longitude (longitude_);
  newMsg->set_latitude (latitude_);
  newMsg->set_created_date (createdDate_);
  newMsg->set_modified_date (modifiedDate_);
  newMsg->set_cid (cid_);
  newMsg->set_category (category_);
  newMsg->set_unit (unit_);
  newMsg->set_size (size_);
  newMsg->set_dest_group_type (destGroupType_);
  newMsg->set_dest_group_name (destGroupName_);
  newMsg->set_status (status_);

  return newMsg;
}

bool
incidentEventData::decodeProtobuf (
  const IncidentEvent *msg)
{
  uuid_ = msg->uuid ();
  mediaCount_ = msg->media_count ();
  originator_ = msg->originator ();
  displayName_ = msg->display_name ();
  categoryId_ = msg->category_id ();
  title_ = msg->title ();
  description_ = msg->description ();
  longitude_ = msg->longitude ();
  latitude_ = msg->latitude ();
  createdDate_ = msg->created_date ();
  modifiedDate_ = msg->modified_date ();
  cid_ = msg->cid ();
  category_ = msg->category ();
  unit_ = msg->unit ();
  size_ = msg->size ();
  destGroupType_ = msg->dest_group_type ();
  destGroupName_ = msg->dest_group_name ();
  status_ = msg->status ();

  return true;
}

std::string
incidentStatusData::encodeJson (void)
{
  std::ostringstream sstream;
  sstream << "{"
          << "\"eventUuid\" : "<< "\"" << eventUuid_ << "\"" << ","
          << "\"status\" : "<< "\"" << status_ << "\"" << ","
          << "\"actor\" : "<< "\"" << actor_ << "\"" << ","
          << "\"modifiedDate\" : "<< modifiedDate_      
          << "}";

  std::string retval (sstream.str ());
  return retval;
}

bool
incidentStatusData::decodeJson (const std::string &data)
{
  Json::Reader jsonReader;
  Json::Value root;
  bool parseSuccess = jsonReader.parse (data, root);

  if (!parseSuccess)
    {
      LOG_ERROR ("JSON parsing error: "
                 << jsonReader.getFormatedErrorMessages ());

      return false;
    }

  eventUuid_ = root["eventUuid"].asString ();
  status_ = root["status"].asString ();
  actor_ = root["actor"].asString ();
  modifiedDate_ = root["modifiedDate"].asInt ();

  return true;
}

IncidentStatus *
incidentStatusData::encodeProtobuf (void)
{
  IncidentStatus *newMsg =
    new IncidentStatus;

  newMsg->set_event_uuid (eventUuid_);
  newMsg->set_status (status_);
  newMsg->set_actor (actor_);
  newMsg->set_modified_date (modifiedDate_);

  return newMsg;
}

bool
incidentStatusData::decodeProtobuf (
  const IncidentStatus *msg)
{
  eventUuid_ = msg->event_uuid ();
  status_ = msg->status ();
  actor_ = msg->actor ();
  modifiedDate_ = msg->modified_date ();

  return true;
}

std::string
incidentCategoryData::encodeJson (void)
{
  std::ostringstream sstream;
  sstream << "{"
          << "\"mainCategory\" : "<< "\"" << mainCategory_ << "\"" << ","
          << "\"subCategory\" : "<< "\"" << subCategory_ << "\"" << ","
          << "\"tigrId\" : "<< "\"" << tigrId_ << "\"" << ","
          << "\"icon\" : "<< "\"" << icon_ << "\""      
          << "}";

  std::string retval (sstream.str ());
  return retval;
}

bool
incidentCategoryData::decodeJson (const std::string &data)
{
  Json::Reader jsonReader;
  Json::Value root;
  bool parseSuccess = jsonReader.parse (data, root);

  if (!parseSuccess)
    {
      LOG_ERROR ("JSON parsing error: "
                 << jsonReader.getFormatedErrorMessages ());

      return false;
    }

  mainCategory_ = root["mainCategory"].asString ();
  subCategory_ = root["subCategory"].asString ();
  tigrId_ = root["tigrId"].asString ();
  icon_ = root["icon"].asString ();

  return true;
}

IncidentCategory *
incidentCategoryData::encodeProtobuf (void)
{
  IncidentCategory *newMsg =
    new IncidentCategory;

  newMsg->set_main_category (mainCategory_);
  newMsg->set_sub_category (subCategory_);
  newMsg->set_tigr_id (tigrId_);
  newMsg->set_icon (icon_);

  return newMsg;
}

bool
incidentCategoryData::decodeProtobuf (
  const IncidentCategory *msg)
{
  mainCategory_ = msg->main_category ();
  subCategory_ = msg->sub_category ();
  tigrId_ = msg->tigr_id ();
  icon_ = msg->icon ();

  return true;
}