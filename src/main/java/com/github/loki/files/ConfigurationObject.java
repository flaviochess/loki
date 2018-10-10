package com.github.loki.files;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 *  {
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
