#!/bin/bash
docker stop pg
docker rm pg
docker-compose up -d pg