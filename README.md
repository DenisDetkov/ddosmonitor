### How to run the app
##### 1. In file src/main/resources/application.properties change:<br>
    spring.datasource.url - url to your database
    spring.datasource.username - username of database user
    ipinfo.token - IpInfo token. It needs to block access from Russia and to get sites location. You can get your token here https://ipinfo.io/account/home 
    application.security.admin.username - Admin username
    application.security.admin.password - Admin password
##### You can use ddosDump.sql file to fill your database with current database data of original site<br>
##### Main file to start the app is Application located in src/java/com/ddos/java/Application<br>
##### Project using Java 11
##### To enter an admin panel, use path {site}/admin
##### To see banned IPs because of DDoS attack on your site, use path {site}/admin/ips
##### If you will use not a Heroku hosting, you can easily delete Procfile and system.properties and this part at the bottom of pom.xml
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-dependency-plugin</artifactId>
                    <executions>
                        <execution>
                            <phase>package</phase>
                            <goals><goal>copy</goal></goals>
                            <configuration>
                                <artifactItems>
                                    <artifactItem>
                                        <groupId>com.heroku</groupId>
                                        <artifactId>webapp-runner</artifactId>
                                        <version>9.0.52.1</version>
                                        <destFileName>ddos-1.0-SNAPSHOT.jar</destFileName>
                                    </artifactItem>
                                </artifactItems>
                            </configuration>
                        </execution>
                    </executions>
                </plugin>
