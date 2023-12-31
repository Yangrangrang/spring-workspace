package org.example.mvc.view;

import static org.example.mvc.view.RedirectView.DEFAULT_REDIRECT_PREFIX;

public class JspViewResolver implements ViewResolver{
    @Override
    public View resolveView(String viewName) {
        if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX)){
            System.out.println("1111");
            // viewName이 redirect로 시작할 경우
            return new RedirectView(viewName);
        }
        System.out.println("2222");
        // viewName이 jsp일 경우
        return new JspView(viewName + ".jsp");
    }
}
