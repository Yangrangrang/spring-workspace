package org.example;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class WebApplicationServer {

    private static final Logger log = LoggerFactory.getLogger(WebApplicationServer.class);

    // webapplication server code
    public static void main(String[] args) throws Exception {
        // tomcat의 root directory 설정 (webapps)
        String webappDirLocation = "webapps/";
        Tomcat tomcat = new Tomcat();
        // tomcat의 port 8080 실행
        tomcat.setPort(8080);

        // 8080 port로 들어오면 루트디렉토리인 webapps 밑에서 찾는다
        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        log.info("configuring app with basedir: {}", new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();

        /**
         * /code_study/spring-workspace/mvc-practice/webapps/WEB-INF/classes
         */
    }
}