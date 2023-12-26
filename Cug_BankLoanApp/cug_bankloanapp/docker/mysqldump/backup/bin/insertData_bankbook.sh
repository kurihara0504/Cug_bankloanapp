#!/bin/bash
mysql -u $MYSQL_ROOT_USER \
          -h $MYSQL_HOST \
          -P $MYSQL_PORT \
          -p$MYSQL_ROOT_PASSWORD \
          mypost < /opt/mysql/backup/bin/sql/digital_mypostbase.sql
