#!/bin/bash
docker-machine create --driver virtualbox machine1
docker-machine scp ~/nginx.conf machine1:/home/docker/nginx/nginx.conf
docker-machine scp ~/target/springlesson10-1.0-SNAPSHOT.jar machine1:/home/docker/power/
docker-machine scp ~/src/main/resource/build.sh machine1:/home/docker/power/
docker-machine scp ~/src/main/resource/Dockerfile machine1:/home/docker/power/
