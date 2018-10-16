/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.loki.handler;

import com.github.loki.response.GetResponseTemplate;
import com.github.loki.response.ResponseTemplate;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 * The server's resquest handler. Responsible for intercepting requests.
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
