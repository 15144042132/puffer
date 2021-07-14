# 模块说明
* puffer（河豚），sting（刺）
* 以sting-？开头的模块不可用，意为等待被拼装的模块.
* 以puffer-？开头的模块，为已经完成的可用模块
* LOG.md  为更新日志
* TODO.md 是尚未完成的功能
  
* 引用：
~~~xml
<servers>
  <server>
    <id>rdc-releases</id>
    <username>60ee303fb9ae39c89abb8dae</username>
    <password>wiH4tVXwOcKb</password>
  </server>
  <server>
    <id>rdc-snapshots</id>
    <username>60ee303fb9ae39c89abb8dae</username>
    <password>wiH4tVXwOcKb</password>
  </server>
</servers>
~~~
~~~xml
<mirrors>
  <mirror>
    <id>mirror</id>
    <mirrorOf>central,jcenter,!rdc-releases,!rdc-snapshots</mirrorOf>
    <name>mirror</name>
    <url>https://maven.aliyun.com/nexus/content/groups/public</url>
  </mirror>
</mirrors>
~~~
~~~xml
 <dependency>
   <groupId>com.puffer</groupId>
   <artifactId>puffer-admin-starter</artifactId>
   <version>{最新版本号}</version>
 </dependency>
~~~
