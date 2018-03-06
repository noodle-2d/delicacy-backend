set PGPASSWORD=postgres
psql postgresql://localhost:5432/delicacy_database delicacy_user < drop-schema.sql
