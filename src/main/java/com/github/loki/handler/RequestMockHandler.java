package com.github.loki.handler;

import com.github.loki.response.GetResponse;
import com.github.loki.response.ResponseConfig;
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
    private GetResponse getResponse;

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ResponseConfig resposConfig = getResponse.byPortAndMethodAndUri(port, request.getMethod(), request.getRequestURI());
        
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(resposConfig.getStatusCode());

        PrintWriter out = response.getWriter();
        out.println(resposConfig.getBody());
        
        baseRequest.setHandled(true);
    }

}
