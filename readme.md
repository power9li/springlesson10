## 如何开始

**创建docker-machine** 

docker-machine是运行docker的虚拟机
windows,macos 上均有对应的安装版本。安装后运行下面脚本创建一个docker-machine实例

```
docker-machine create --driver virtualbox machine1
```
**copy对应文件到docker-machine中**

```
docker-machine scp ~/nginx.conf machine1:/home/docker/nginx/nginx.conf
docker-machine scp ~/target/springlesson10-1.0-SNAPSHOT.jar machine1:/home/docker/power/
docker-machine scp ~/src/main/resource/build.sh machine1:/home/docker/power/
docker-machine scp ~/src/main/resource/Dockerfile machine1:/home/docker/power/
```

**进入docker-machine**

```
docker-machine ssh machine1
```

**创建自定义docker容器(testsession1.0)镜像**

```
cd /home/docker/power
./build.sh
```

**创建一个redis容器，两个spring-boot容器和一个nginx容器**

```
docker pull tutum/redis
docker run -d -p 6379:6379 -e REDIS_PASS="secret" tutum/redis
docker run -p 8080:8080 -d --name sessiontest1 sessiontest:1.0
docker run -p 8080:8080 -d --name sessiontest2 sessiontest:1.0
docker run -p 80:80 --link sessiontest1:sessiontest1 --link sessiontest2:sessiontest2 --name some-nginx -v /home/docker/nginx/nginx.conf:/etc/nginx/nginx.conf:ro -d nginx
```
