package com.github.loki.handler;

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

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);


        PrintWriter out = response.getWriter();

        System.out.println(request.getPathInfo());

        if (request.getRequestURI().equalsIgnoreCase("/flavio/test")) {
            out.println(getResponse1());
        } else {
            out.println(getResponse2());
        }

        baseRequest.setHandled(true);
    }

    private String getResponse1() {

        Random random = new Random();
        Integer randonInt = random.nextInt();

        String json = "{\"id\":\"" + randonInt + "\",\"value\":\"File\",\"popup\":"
                + "{\"item\":[{\"value\":\"New\",\"onclick\":\"CreateNewDoc()\"},"
                + "{\"value\":\"Open\",\"onclick\":\"OpenDoc()\"},{\"value\":\"Close\",\"onclick\":\"CloseDoc()\"}]}}";

        return json;
    }

    private String getResponse2() {

        Random random = new Random();
        Integer randonInt = random.nextInt();

        String json = "{\"id\":\"" + randonInt + "\",\"name\":\"jonh doe\",\"CPF\":\"044.358.133-94\",\"nascimento\":\"29/08/1990\"}";

        return json;
    }

}
