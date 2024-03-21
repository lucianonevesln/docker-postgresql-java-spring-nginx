#!/bin/bash

maven="mvn clean package"
docker_compose_build="docker compose up --build"

echo -e "\n MAVEN : comando de build do projeto Spring \n"

$maven

echo -e "\n DOCKER : comando para build do projeto \n"

$docker_compose_build
