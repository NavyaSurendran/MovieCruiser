#!/bin/bash

cd movie-cruiser-authentication
source ./env-variables.sh
mvn clean package
cd ..
cd movie-cruiser-backend
source ./env-variables.sh
mvn clean package
cd ..