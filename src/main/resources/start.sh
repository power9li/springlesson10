#!/bin/bash
#进入docker-machine
#docker-machine ssh machine1

cd /home/docker/power
./build.sh

docker pull tutum/redis

docker run -d -p 6379:6379 -e REDIS_PASS="secret" tutum/redis

docker run -p 8080:8080 -d --name sessiontest1 sessiontest:1.0

docker run -p 8080:8080 -d --name sessiontest2 sessiontest:1.0

docker run -p 80:80 --link sessiontest1:sessiontest1 --link sessiontest2:sessiontest2 --name some-nginx -v /home/docker/nginx/nginx.conf:/etc/nginx/nginx.conf:ro -d nginx
