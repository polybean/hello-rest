#!/bin/bash

PROJECT_HOME=$(realpath "$(dirname -- $0)/..")
PGPASSWORD=secret psql -h localhost -p 5432 demo postgres -c "DELETE FROM issues;"
$PROJECT_HOME/mvnw test