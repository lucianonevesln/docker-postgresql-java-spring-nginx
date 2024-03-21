#!/bin/bash

maven="mvn clean package"
docker_compose="docker compose up"

echo -e "\n MAVEN : comando de build do projeto Spring \n"

$maven

echo -e "\n DOCKER : comando para execução dos conteineres \n"

$docker_compose