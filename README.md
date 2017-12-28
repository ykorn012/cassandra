# cassandra
Cassandra PoC

## Reference Site
1. [Downloading Cassandra & Install](http://cassandra.apache.org/download/) 
2. [Learn Cassandra](https://www.tutorialspoint.com/cassandra/cassandra_create_keyspace.htm)
   
## Install & Environments
1. Reference Site #1을 참조하여 Cassandra 설치
2. Reference Site #1을 참조하여 Cassandra 설치

## TroubleShootings
1. 인증서 문제 : /etc/ssl/certs/에 다가 cert 복사
~~~    
ykorn012@Jung01:/mnt/d/SWTools/cassendra$ curl -v  https://google.com
* Rebuilt URL to: https://google.com/
*   Trying 70.10.15.10...
* Connected to 70.10.15.10 (70.10.15.10) port 8080 (#0)
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
