#!/bin/bash

PROJECT_HOME=$(realpath "$(dirname -- $0)/..")
PGPASSWORD=secret psql -h localhost -p 5432 demo postgres < $PROJECT_HOME/scripts/fixture.sql
