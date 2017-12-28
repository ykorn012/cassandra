# cassandra
Cassandra PoC

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
1. [Getting Started with Time Series Data Modeling]https://academy.datastax.com/resources/getting-started-time-series-data-modeling
2. [Downloading Cassandra & Install](http://cassandra.apache.org/download/) 
3. [Learn Cassandra](https://www.tutorialspoint.com/cassandra/cassandra_create_keyspace.htm)
4. [How to install Cassandra on Ubuntu?](https://github.com/ykorn012/cassandra/blob/master/README.md)
5. [How to run graphical Linux applications on Bash on Ubuntu on Windows 10](https://seanthegeek.net/234/graphical-linux-applications-bash-ubuntu-windows/)
6. [윈도우 10에서 Bash shell 지원](https://blogs.msdn.microsoft.com/eva/?p=7633)
   
## Install & Environments
1. java 설치 
~~~
sudo apt-get install openjdk-8-jdk or sudo apt-get install oracle-java8-set-default
~~~
2. Install Java Native Access (JNA)
~~~
sudo apt-get install libjna-java  
~~~ 
3. Reference Site #1을 참조하여 Cassandra 설치
4. cqlsh를 사용하여 Reference Site #2를 참조하여 사용
5. 아래 Java Source로 Test
~~~
package com.isd.poc.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

class CreateData {
	public static void main(String args[]) throws Exception {
		String query = "Truncate emp;"; // Query
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build(); // Creating Cluster object
		Session session = cluster.connect("tutorialspoint"); // Creating Session object
		session.execute(query); // Executing the query
		System.out.println("Table truncated");
		
		// queries
		String query1 = "INSERT INTO emp (emp_id, emp_name, emp_city, emp_phone,  emp_sal)"
				+ " VALUES(1,'ram', 'Hyderabad', 9848022338, 50000);";
		String query2 = "INSERT INTO emp (emp_id, emp_name, emp_city, emp_phone, emp_sal)"
				+ " VALUES(2,'robin', 'Hyderabad', 9848022339, 40000);";
		String query3 = "INSERT INTO emp (emp_id, emp_name, emp_city, emp_phone, emp_sal)"
				+ " VALUES(3,'rahman', 'Chennai', 9848022330, 45000);";
		
		// Executing the query
		session.execute(query1);
		session.execute(query2);
		session.execute(query3);
		System.out.println("Data created");
	}
}
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
