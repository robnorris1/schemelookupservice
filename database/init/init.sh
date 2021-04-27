#!/bin/bash
set -e
export PGPASSWORD=$POSTGRES_PASSWORD;
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
\set vschema_owner $SCHEMA_OWNER
\set vschema_password '''$SCHEMA_OWNER_PASSWORD'''
\set vdb_name $POSTGRES_DB
\set vmaster_user $POSTGRES_USER
\set vapp_user $APP_USER
\set vapp_password '''$APP_PASSWORD'''

CREATE USER :vschema_owner WITH PASSWORD :vschema_password;
GRANT ALL PRIVILEGES ON DATABASE :vdb_name TO :vschema_owner;
GRANT :vschema_owner TO :vmaster_user;
CREATE SCHEMA :vschema_owner AUTHORIZATION :vschema_owner;
ALTER ROLE :vschema_owner SET SEARCH_PATH TO :vschema_owner,public;

CREATE USER :vapp_user WITH PASSWORD :vapp_password;
ALTER ROLE :vapp_user WITH LOGIN;
GRANT ALL PRIVILEGES ON DATABASE :vdb_name TO :vapp_user;
GRANT :vschema_owner TO :vapp_user;
ALTER ROLE :vapp_user SET SEARCH_PATH TO :vschema_owner,public;
EOSQL

