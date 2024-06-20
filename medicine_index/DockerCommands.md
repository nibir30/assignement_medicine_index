# to build docker image, nibir30 is userID, medicine_index is the image name

docker build -t nibir30/medicine_index .

# find image id using

docker images

# to run docker, 8000 the port i want to expose, 8006 the port my project runs on, then the image name

docker run -p 8000:8006 nibir30/medicine_index

# OR, where the image id is ca9209f45b50

docker run -p 8000:8006 ca9209f45b50

# create docker volume for shared files or databases

docker volume create shared-stuff

# to run by docker compose,

docker-compose up

# create network

docker network create springboot-mysql-net

# Install docker mysql,

docker pull mysql:8.0.34

# Create volume

docker volume create --driver local -o o=bind -o type=none -o device="/root/doclive" doc_storage

# Delete delete all volumes

docker volume rm -f $(docker volume ls )

# mysql run config

docker run --name mysqldb --network springboot-mysql-net -e MYSQL_ROOT_PASSWORD=13579 -e MYSQL_DATABASE=doclive -e
MYSQL_USER=nibir -e MYSQL_PASSWORD=13579 -d mysql:8.0.34

# access docker container in interactive mode(-it), 319 is the first 3 letters of container id

docker exec -it 2dd bash
docker exec -it 2dd /bin/sh

# check logs of docker container

docker logs c883c9fc9a0c

# access to mysql, with user and password

mysql -unibir -p13579
mysql -uroot -p13579

# check database name

show databases;

# use any database

use medicine_index;

# build docker

docker build -t nibir30/medicine_index .

# build and run container

docker run --network springboot-mysql-net --name medicine_index_container -p 8006:8006 nibir30/medicine_index -d
medicine_index

# build and run container with restart

docker run --network springboot-mysql-net --name medicine_index_container -p 8006:8006 nibir30/medicine_index -d
medicine_index --restart unless-stopped

# sleep infinity

docker run --network springboot-mysql-net --name medicine_index_container -p 8006:8006 nibir30/medicine_index -d
medicine_index -c "echo 'Hello Doclive'; sleep infinity"

# deploy docker in server

go to filezilla, upload docker-compose.yml, run 'docker-compose up'
If cache error, run -
docker-compose rm

# view container list

docker ps -a

# start container

docker start 2dd(container id)



