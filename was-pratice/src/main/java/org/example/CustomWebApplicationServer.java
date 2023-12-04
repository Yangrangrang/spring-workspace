package org.example;

import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomWebApplicationServer {
    private final int port;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);

    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(port)){
            logger.info("CustomWebApplicationServer started {} port", port);

            Socket clientSocket;
            logger.info("CustomWebApplicationServer waiting for client");

            while ((clientSocket = serverSocket.accept()) != null) {
                logger.info("CustomWebApplicationServer client connected");

                /**
                 *  step1 : 사용자 요청을 메인 Thread가 처리 하도록 한다.
                 */

//                try (InputStream in = clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()){
//                    BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
//                    DataOutputStream dos = new DataOutputStream(out);
//
////                    String line;
////                    while ((line = br.readLine()) != "") {
////                        System.out.println(line);
////                    }
//
//                    System.out.println("br = " + br);
//                    HttpRequest httpRequest = new HttpRequest(br);
//
//                    if (httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")) {
//                        QueryStrings queryStrings = httpRequest.getQueryStrings();
//
//                        int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
//                        String operator = queryStrings.getValue("operator");
//                        int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));
//
//                        int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));
//                        byte[] body = String.valueOf(result).getBytes();
//
//                        HttpResponse response = new HttpResponse(dos);
//                        response.response200Header("application/json", body.length);
//                        response.responseBody(body);
//                    }
//                }
                /**
                 * step2
                 * 사용자의 요청이 들어올때마다 thread를 새로 생성해서 사용자의 요청을 클라이언트 소켓에 전달해서 처리
                 * 문제점 : 스레드는 생성될때마다 독립적인 스택메모리 공간을 할당 받는다. (메모리를 할당받는 작업은 비싼 작업)
                 *          사용자의 요청이 있을 때마다 생성을 하게 되면 성능이 떨어지게 된다.
                 *          즉, 요청이 몰리게 되면 스레드를 많이 생성하게 되고 메모리를 할당작업처럼 비싼 작업이 많이 발생
                 *          또한, 동시 접속자 수가 많아질 경우에 많은 스레드가 생성될 텐데 스레드가 많아지게 되면 CPU컨테스트 스위칭 횟수가 증가되고
                 *          CPU와 메모리 사용량이 증가
                 *          최악의 경우 서버의 리소스가 감당하지 못해서 서버가 다운되는 가능성도 있다.
                 *
                 * 해결 : 스레드를 이미 고정된 개수만큼 생성해두고 이를 재활용하는 스레드 풀 개념 적용 (step3)
                 */
//                new Thread(new ClientRequestHandler(clientSocket)).start();

                /**
                 * step3
                 * Thread Pool을 적용해 안정적인 서비스가 가능하도록 한다.
                 */
                executorService.execute(new ClientRequestHandler(clientSocket));
            }
        }
    }
}
