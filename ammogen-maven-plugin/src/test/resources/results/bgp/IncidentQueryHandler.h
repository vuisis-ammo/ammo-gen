// THIS IS GENERATED CODE, COPY THIS FILE ONE LEVEL UP TO BUILD AND/OR EDIT

#ifndef INCIDENT_QUERY_HANDLER_H
#define INCIDENT_QUERY_HANDLER_H

#include <string>

#include "QueryStatementBuilder.h"
#include "StringParser.h"

class sqlite3_stmt;

namespace ammo
{
  namespace gateway
  {
    class PullRequest;
    class GatewayConnector;
  }
}

class MediaQueryParamParser : public StringParser
{
public:
  MediaQueryParamParser (void);

  void parse (const std::string &params);

  std::string event_id_; 
  std::string data_; 
  std::string created_date_min_;
  std::string created_date_max_; 
  std::string modified_date_min_;
  std::string modified_date_max_; 
};

class MediaQueryStatementBuilder : public QueryStatementBuilder
{
public:
  MediaQueryStatementBuilder (const std::string &params,
                                 sqlite3 *db);

  // Prepares the SQL query statement and binds the param values.
  bool build (void);

private:
  bool bind (void);

private:
  MediaQueryParamParser parser_;
};

class MediaQueryHandler
{
public:
  MediaQueryHandler (sqlite3 *db,
                        ammo::gateway::GatewayConnector *sender,
                        ammo::gateway::PullRequest &pr);

  void handleQuery (void);

private:
  void encode_row (sqlite3_stmt *stmt,
                   std::string &output);

private:
  sqlite3 *db_;
  ammo::gateway::GatewayConnector *sender_;
  ammo::gateway::PullRequest &pr_;
  MediaQueryStatementBuilder builder_;
};

//==============================================

class EventQueryParamParser : public StringParser
{
public:
  EventQueryParamParser (void);

  void parse (const std::string &params);

  std::string uuid_; 
  std::string media_count_min_;
  std::string media_count_max_; 
  std::string originator_; 
  std::string display_name_; 
  std::string category_id_; 
  std::string title_; 
  std::string description_; 
  std::string longitude_min_;
  std::string longitude_max_; 
  std::string latitude_min_;
  std::string latitude_max_; 
  std::string created_date_min_;
  std::string created_date_max_; 
  std::string modified_date_min_;
  std::string modified_date_max_; 
  std::string cid_; 
  std::string category_; 
  std::string unit_; 
  std::string size_min_;
  std::string size_max_; 
  std::string dest_group_type_; 
  std::string dest_group_name_; 
  std::string status_min_;
  std::string status_max_; 
};

class EventQueryStatementBuilder : public QueryStatementBuilder
{
public:
  EventQueryStatementBuilder (const std::string &params,
                                 sqlite3 *db);

  // Prepares the SQL query statement and binds the param values.
  bool build (void);

private:
  bool bind (void);

private:
  EventQueryParamParser parser_;
};

class EventQueryHandler
{
public:
  EventQueryHandler (sqlite3 *db,
                        ammo::gateway::GatewayConnector *sender,
                        ammo::gateway::PullRequest &pr);

  void handleQuery (void);

private:
  void encode_row (sqlite3_stmt *stmt,
                   std::string &output);

private:
  sqlite3 *db_;
  ammo::gateway::GatewayConnector *sender_;
  ammo::gateway::PullRequest &pr_;
  EventQueryStatementBuilder builder_;
};

//==============================================

class StatusQueryParamParser : public StringParser
{
public:
  StatusQueryParamParser (void);

  void parse (const std::string &params);

  std::string event_uuid_; 
  std::string status_; 
  std::string actor_; 
  std::string modified_date_min_;
  std::string modified_date_max_; 
};

class StatusQueryStatementBuilder : public QueryStatementBuilder
{
public:
  StatusQueryStatementBuilder (const std::string &params,
                                 sqlite3 *db);

  // Prepares the SQL query statement and binds the param values.
  bool build (void);

private:
  bool bind (void);

private:
  StatusQueryParamParser parser_;
};

class StatusQueryHandler
{
public:
  StatusQueryHandler (sqlite3 *db,
                        ammo::gateway::GatewayConnector *sender,
                        ammo::gateway::PullRequest &pr);

  void handleQuery (void);

private:
  void encode_row (sqlite3_stmt *stmt,
                   std::string &output);

private:
  sqlite3 *db_;
  ammo::gateway::GatewayConnector *sender_;
  ammo::gateway::PullRequest &pr_;
  StatusQueryStatementBuilder builder_;
};

//==============================================

class CategoryQueryParamParser : public StringParser
{
public:
  CategoryQueryParamParser (void);

  void parse (const std::string &params);

  std::string main_category_; 
  std::string sub_category_; 
  std::string tigr_id_; 
  std::string icon_; 
};

class CategoryQueryStatementBuilder : public QueryStatementBuilder
{
public:
  CategoryQueryStatementBuilder (const std::string &params,
                                 sqlite3 *db);

  // Prepares the SQL query statement and binds the param values.
  bool build (void);

private:
  bool bind (void);

private:
  CategoryQueryParamParser parser_;
};

class CategoryQueryHandler
{
public:
  CategoryQueryHandler (sqlite3 *db,
                        ammo::gateway::GatewayConnector *sender,
                        ammo::gateway::PullRequest &pr);

  void handleQuery (void);

private:
  void encode_row (sqlite3_stmt *stmt,
                   std::string &output);

private:
  sqlite3 *db_;
  ammo::gateway::GatewayConnector *sender_;
  ammo::gateway::PullRequest &pr_;
  CategoryQueryStatementBuilder builder_;
};

#endif // INCIDENT_QUERY_HANDLER_H