Cassandra PoC
==

Cassandra 는 아파치 재단에서 관리하는 오픈소스 NoSQL 분산 데이터베이스 관리 시스템이다.
2008년, Google BigTable의 컬럼 기반 데이터 모델과 Amazon Dynamo의 분산 모델을 기반으로 하여 
Facebook의 Avinash Lakshman (Amazon Dynamo 의 저자 중 한 사람)와 Prashant Malik에 
의해 최초 발표된 이후, 2009년 아파치 인큐베이터 프로젝트 선정, 2010년 아파치 top-level 프로젝트로 
선정되는 등 혜성같이 등장하여 SNS계의 데이터베이스 강자로 자리잡아 가고 있다. 이와 같은 빠른 성장의 배경에는 
Masterless Architecture (특정 노드가 Coordinator 역할을 전담하는 기존의 Master-Slave 
방식과 달리, 모든 노드가 Coordinator 역할을 수행할 수 있는 토큰링 구조) 로 인한 고성능&고가용성의 장점이 있으며, 
기존의 RDBMS 와는 달리 Scale out 을 통한 성능 향상이 수월하다는 점도 그 폭발적인 인기에 한 몫을 하고 있다. 

![이미지 이름](http://culturallife.xyz/wp-content/uploads/2017/03/%EC%BA%A1%EC%B2%98.png)


## Reference Site
1. [Getting Started with Time Series Data Modeling](https://academy.datastax.com/resources/getting-started-time-series-data-modeling)
2. [Apache Cassandra NoSQL Performance Benchmarks](https://academy.datastax.com/planet-cassandra/nosql-performance-benchmarks)
3. [Downloading Cassandra & Install](http://cassandra.apache.org/download/)
4. [Learn Cassandra](https://www.tutorialspoint.com/cassandra/index.htm)
6. [DB Engines Ranking](https://db-engines.com/en/ranking)
7. [How to install Cassandra on Ubuntu?](https://github.com/ykorn012/cassandra/blob/master/README.md)
8. [How to run graphical Linux applications on Bash on Ubuntu on Windows 10](https://seanthegeek.net/234/graphical-linux-applications-bash-ubuntu-windows/)
9. [윈도우 10에서 Bash shell 지원](https://blogs.msdn.microsoft.com/eva/?p=7633)
   
## Install & Environments
1. java 설치 
- PPA를 추가
~~~
sudo add-apt-repository ppa:webupd8team/java
~~~
- apt 업데이트
~~~
sudo apt update
~~~
- Java8 을 설치
~~~
sudo apt install oracle-java8-installer
~~~
- 설치 확인
~~~
javac -version
java -version
~~~
- Java 환경변수 자동 설정
~~~
sudo apt install oracle-java8-set-default
~~~

2. Install Java Native Access (JNA)
~~~
sudo apt-get install libjna-java  
~~~ 
3. Reference Site #3을 참조하여 Cassandra 설치

4. Reference Site #4을 참조하여 Eclipse Environment Maven 환경 구성 (Cassandra - Installation - Eclipse Environment)
~~~
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.isd</groupId>
	<artifactId>cassandra</artifactId>
	<version>0.0.1-SNAPSHOT</version>

	<build>
		<plugins>
			<plugin>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.1</version>

				<configuration>
					<source>1.8</source>
					<target>1.8</target>
				</configuration>

			</plugin>
		</plugins>
	</build>

	<dependencies>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>1.7.5</version>
		</dependency>

		<dependency>
			<groupId>com.datastax.cassandra</groupId>
			<artifactId>cassandra-driver-core</artifactId>
			<version>3.3.0</version>
		</dependency>

		<dependency>
			<groupId>com.google.guava</groupId>
			<artifactId>guava</artifactId>
			<version>16.0.1</version>
		</dependency>

		<dependency>
			<groupId>com.codahale.metrics</groupId>
			<artifactId>metrics-core</artifactId>
			<version>3.0.2</version>
		</dependency>

		<dependency>
			<groupId>io.netty</groupId>
			<artifactId>netty</artifactId>
			<version>3.9.0.Final</version>
		</dependency>
	</dependencies>
</project>
~~~

5. Reference Site #4 참조하여 cqlsh를 이용한 Keyspace & TABLE 생성
~~~
cqlsh.> CREATE KEYSPACE tutorialspoint
WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};

cqlsh> DESCRIBE keyspaces;

cqlsh> USE tutorialspoint;

cqlsh:tutorialspoint> CREATE TABLE emp(
   emp_id int PRIMARY KEY,
   emp_name text,
   emp_city text,
   emp_sal varint,
   emp_phone varint
   );
   
cqlsh:tutorialspoint> select * from emp;

 emp_id | emp_city | emp_name | emp_phone | emp_sal
--------+----------+----------+-----------+---------
~~~   

6. Source의 CreateData Java Class 실행 결과
~~~
ykorn012@JUNG01:/mnt/c/Users/Jung01$ cqlsh
Connected to Test Cluster at 127.0.0.1:9042.
[cqlsh 5.0.1 | Cassandra 3.11.1 | CQL spec 3.4.4 | Native protocol v4]
Use HELP for help.
cqlsh> USE tutorialspoint;
cqlsh:tutorialspoint> select * from emp;

 emp_id | emp_city  | emp_name | emp_phone  | emp_sal
--------+-----------+----------+------------+---------
      1 | Hyderabad |      ram | 9848022338 |   50000
      2 | Hyderabad |    robin | 9848022339 |   40000
      3 |   Chennai |   rahman | 9848022330 |   45000

(3 rows)
~~~

## TroubleShootings
1. 인증서 문제 : /etc/ssl/certs/에 다가 cert 복사
~~~
ykorn012@Jung01:/mnt/d/SWTools/cassendra$ curl -v  https://google.com
* Rebuilt URL to: https://google.com/
* Establish HTTP proxy tunnel to google.com:443
> CONNECT google.com:443 HTTP/1.1
> Host: google.com:443
> User-Agent: curl/7.47.0
> Proxy-Connection: Keep-Alive
>
< HTTP/1.1 200 Connection established
<
* Proxy replied OK to CONNECT request
* found 173 certificates in /etc/ssl/certs/ca-certificates.crt
* found 692 certificates in /etc/ssl/certs
~~~  

2. Python 설치
1) https://repo.continuum.io/archive/Anaconda3-4.2.0-Linux-x86_64.sh 다운로드 후 설치
2) You are using pip version 8.1.2, however version 9.0.1 is available. 
~~~
conda update pip
pip install tensorflow
