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
package com.github.loki.files;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Class to represent file settings.
 *
 * The file settings should have a structure similar to: *  {
 *      "port": 9898,
 *      "context": "myapi",
 *      "entries": [
 *          {
 *              "request": {
 *                  "method": "GET",
 *                  "uri": "/loki/test"
 *              },
 *              "response": {
 *                  "status": 200,
 *                  "content": {
 *                      "text": "[{\"account\":{\"id\":709918,\"description\":\"Brasil Account\",\"personId\":28333,\"name\":\"Flavio Andrade\",\"model\":\"XPTO\",\"country\":\"BR\",\"enabled\":true},\"profile\":{\"id\":\"ADMIN\",\"name\":\"ADMIN\",\"standard\":true,\"order\":1}}]"
 *                  }
 *              }
 *          }
 *      ]
 *  }
 * 
 * @author Flavio Andrade
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigurationObject {

    private Integer port;

    private String context;

    private List<Entry> entries = new ArrayList();

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Entry {

        private Request request;

        private Response response;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Request {

        private String method;

        private String uri;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Response {

        private Integer status;

        private Content content;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Content {

        private String text;

    }
}
