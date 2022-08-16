# 배포 환경

#### 계정 및 버전 정보

> **시스템환경**
>
>OS : Ubuntu 20.04 LTS (GNU/Linux 5.4.0-1018-aws x86_64)
>
>Cpu 모델 :  Intel(R) Xeon(R) CPU E5-2686 v4 @ 2.30GHz
>
>Total Memory:  16396056 kB

> **Frontend**
>
> `node 14.17.0`, `vue 3.2.37`
>
> **Backend**
>
> `openjdk version "1.8.0_192"`,  `spring-boot 2.4.5`

> **MySQL**
>
> version : 8.0.30
>
> username : goose
>
> password : goose7goose

> **사용 API**
>
>JDOODLE (https://www.jdoodle.com/ ) 코드 컴파일러 
>
>yjs docs (https://docs.yjs.dev/ ) 공유 문서 편집기
>
>OpenVidu (https://openvidu.io/ ) WebRTC 오픈소스


### 기본 설치
```sh
sudo apt-get upgrade
sudo apt-get update
sudo timedatectl set-timezone "Asia/Seoul"
sudo apt-get install nginx
sudo apt-get install nodejs
sudo apt-get install npm
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.38.0/install.sh | bash
source ~/.bashrc
nvm install v14.17.0

#자바설치
udo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 0x219BD9C9
sudo apt-add-repository 'deb http://repos.azulsystems.com/ubuntu stable main'
sudo apt-get update
sudo apt-get install zulu-8
#자바홈 설정
vi /etc/profile
...
export JAVA_HOME=/usr/lib/jvm/zulu-8-amd64
...
java -version
```


##### docker

```sh
# 패키지 설치
sudo apt-get install apt-transport-https ca-certificates curl gnupg-agent software-properties-common
# GPG Key 인증
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
# docker repository 등록
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
# 도커 설치
sudo apt-get update && sudo apt-get install docker-ce docker-ce-cli containerd.io
# 도커 시작
service docker start
# 도커 확인
sudo service docker status
# 도커 재실행
sudo service docker restart
```

#### HTTPS 키 발급
```sh
sudo apt-get install letsencrypt
# nginx를 사용중이면 중지
sudo systemctl stop nginx
# 인증서 발급
sudo letsencrypt certonly --standalone -d 제외한 도메인 이름
# 이메일 작성 Agree
# 뉴스레터 N
# 가 발급경로
 ## ssl_certificate /etc/letsencrypt/live/도메인이름/fullchain.pem; 
 ## ssl_certificate_key /etc/letsencrypt/live/도메인이름/privkey.pem; 
 
 # pem을 PKCS12 형식으로 변경
openssl pkcs12 -export -in fullchain.pem -inkey privkey.pem -out keystore.p12 -name airpageserver -CAfile chain.pem -caname root
```

##### docker compose

```sh
# openvidu 설치
sudo curl https://s3-eu-west-1.amazonaws.com/aws.openvidu.io/install_openvidu_latest.sh | bash

# .env 파일 수정
vi .env

# openvidu 서버 시작
sudo ./openvidu start
# openvidu 서버 종료
sudo ./openvidu stop
# openvidu 서버 재시작
sudo ./openvidu restart
```


### MySQL

##### version : 8.0.30
```sh
$ sudo apt-get install mysql-server
$ sudo mysql -u root -p
> use mysql
> select Host, User, authentication_string from user;
> alter user 'root'@'localhost' identified with mysql_native_password by 'myy7goose';
> FLUSH PRIVILEGES;
> exit
> sudo su
> cd /etc/mysql/mysql.conf.d
> vi mysqld.cnf
# 수정 bind-address = 0.0.0.0
$ service mysql restart
> create user 'goose'@'%' identified by 'goose7goose';
> grant all privileges on *.* to username@'%';
```


### Openvidu

```sh
$ sudo su
$ cd /opt
$ curl https://s3-eu-west-1.amazonaws.com/aws.openvidu.io/install_openvidu_latest.sh | bash
$ cd openvidu
$ vi .env

# DOMAIN_OR_PUBLIC_IP : i7c209.p.ssafy.io:8080
# OPENVIDU_SECRET : MY_GOOSEGOOSE
# CERTIFICATE_TYPE : letsencrypt
# LETSENCRYPT_EMAIL : eamil@email.com
# HTTP_PORT : 80
# HTTPS_PORT : 4443
# openvidu 실행

$ ./openvidu start

# 인증서 발급 확인
$ cd /opt/openvidu/certificates/live/도메인
$ ls

```


