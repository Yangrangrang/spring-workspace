package org.example;

import java.util.Objects;

/**
 *  HttpRequest
 *      - RequestLine (GET /calculate?operand1=11&opertor=*&operand2=33 HTTP/1.1)
 *          - HttpMethod
 *          - path
 *          - queryString
*       - Header
 *      - Body
 */
public class RequestLine {
    // GET   /calculate?operand1=11&opertor=*&operand2=33   HTTP/1.1

    private final String urlPath;   // GET
    private final String method;    // /calculate
    private String queryString;    // operand1=11&opertor=*&operand2=33

    public RequestLine(String method, String urlPath, String queryString) {
        this.method = method;
        this.urlPath = urlPath;
        this.queryString = queryString;
    }

    public RequestLine(String requestLine) {
        String[] tokens = requestLine.split(" ");
        this.method = tokens[0];
        String[] urlPathTokens = tokens[1].split("\\?");
        this.urlPath = urlPathTokens[0];

        if (urlPathTokens.length == 2) {
            this.queryString = urlPathTokens[1];
        }


    }

    // 객체를 비교 하려면 필요하다
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestLine that = (RequestLine) o;
        return Objects.equals(urlPath, that.urlPath) && Objects.equals(method, that.method) && Objects.equals(queryString, that.queryString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(urlPath, method, queryString);
    }
}
