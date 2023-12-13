package org.example.mvc.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

public class JspView implements View{
    private final String name;

    public JspView(String name) {
        this.name = name;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 모델의 값이 세팅이 되어있으면
        /** java식
         * for(Map.Entry<String, ?> entry : model.entrySet()){
         * String key = entry.getKey();
         * Object value = entry.getValue();
         * request.setAttribute(key, value);
        }*/

        /** 람다 방식 (익명람다)
         * model.forEach((key, value) -> request.setAttribute(key,value));
         */

        /**
         * 람다 방식 (메서드 참조)
         * model.forEach(request::setAttribute);
         */

        model.forEach(request::setAttribute);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(name);
        System.out.println("requestDispatcher = " + requestDispatcher);
        requestDispatcher.forward(request, response);
    }
}
