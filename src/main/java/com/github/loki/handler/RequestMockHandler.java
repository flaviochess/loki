package com.github.loki.handler;

import com.github.loki.response.GetResponseTemplate;
import com.github.loki.response.ResponseTemplate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 *
 * @author Flavio Andrade
 */
@AllArgsConstructor
public class RequestMockHandler extends AbstractHandler {

    @NonNull
    private Integer port;
    
    @NonNull
    private GetResponseTemplate getResponseTemplate;

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ResponseTemplate responseTemplate = getResponseTemplate.byPortAndMethodAndUri(port, request.getMethod(), request.getRequestURI());
        
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(responseTemplate.getStatusCode());

        PrintWriter out = response.getWriter();
        out.println(responseTemplate.getBody());
        
        baseRequest.setHandled(true);
    }

}
