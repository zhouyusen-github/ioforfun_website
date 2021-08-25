# ioforfun-website
## Introduction
A website directory of IO games


## Service
* user register and login
* add favorite, delete favorite
* show my favorite
* Game list are displayed in pages
* Game Search

## Tech Stack
use reverse proxy to solve cross origin problem  
development: vue proxy  
production: nginx  

**Backend**  
SpringBoot + MySQL + Redis

**Frontend**  
Vue + Vue-Router + Axios + Element

## Dependency Version
**development environment**  
Windows 10

JDK: 14.0.2 (corretto-1.8.0_282 is also ok)  
Maven: 3.8.1  
Redis: 5.0.9  
Mysql: 5.7.34-log  
IDEA: 20.3.2  

node: v14.16.0  
npm: 6.14.11  
Webstorm: 20.3.3  

other detail in pom.xml and package.json

**production environment**  
Debian 10  

JDK: openjdk 11.0.11 2021-04-20  
redis: 5.0.3  
mariadb:  10.3.29-MariaDB-0+deb10u1  
nginx: 1.14.2  

## Run
1. install Dependent software and run mysql and redis
2. Register an email account and get its smtp setting way
3. git clone project
4. run sql in package demo_build_sql
5. open terminal in package frontend, run frontend 
```sh
npm run serve
```
5. open terminal in package backend, write your setting in application.yml
   1. MySQL connection URL, account
   2. Redis connection
   3. Smtp connection
   4. host ip (activationDomain)
6. run backend
```sh
mvn spring-boot:run
```
7. open "http://localhost:8090" in browser


## Deploy
1. install Dependent software and run mysql and redis
2. Register an email account and get its smtp setting way
3. git clone project
4. run sql in package demo_build_sql
5. open terminal in package frontend, build frontend 
```sh
npm run build
```

7. open terminal in package backend, write your setting in src/resource/application.yml
   1. MySQL connection URL, username and password of account
   2. Redis connection
   3. Smtp connection
   4. host ip (activationDomain)
8. build backend
```sh
mvn package
```
9. run backend jar from mvn package
```
java -jar backend-1.0-SNAPSHOT.jar
```
10. Set nginx 
    1. root to output dist
    2. Reverse proxy of backend

```conf
    server {
        listen    80;
        server_name    your_host_ip;
        charset    utf-8;

        location / {

			root   /home/admin/dist; # vue package output dist position
            index  index.html;
            try_files $uri $uri/ /index.html;
        }

        location /category {
            proxy_pass http://localhost:8080;
        }
        
        location /verificationCode {
            proxy_pass http://localhost:8080;
        }

        location /game {
            proxy_pass http://localhost:8080;
        }

        location /user {
            proxy_pass http://localhost:8080;
        }

        location /favorite {
            proxy_pass http://localhost:8080;
        }
    }
```


11.  open "http://your_host_ip" in browser

## sample
http://34.211.155.251/

