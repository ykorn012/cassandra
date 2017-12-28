# cassandra
Cassandra PoC

## Reference Site
1. [Downloading Cassandra & Install](http://cassandra.apache.org/download/) 
2. [Learn Cassandra](https://www.tutorialspoint.com/cassandra/cassandra_create_keyspace.htm)
3. [How to install Cassandra on Ubuntu?](https://github.com/ykorn012/cassandra/blob/master/README.md)
4. [How to run graphical Linux applications on Bash on Ubuntu on Windows 10](https://seanthegeek.net/234/graphical-linux-applications-bash-ubuntu-windows/)
5. [윈도우 10에서 Bash shell 지원](https://blogs.msdn.microsoft.com/eva/?p=7633)
   
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
