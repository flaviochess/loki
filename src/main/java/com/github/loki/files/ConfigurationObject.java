package com.github.loki.files;

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
 *                      "mimeType": "application/json",
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
public class ConfigurationObject {

    private Integer port;

    private String context;

    private List<Entry> entries;

    @Getter
    @Setter
    public class Entry {

        private Request request;

        private Response response;
    }

    @Getter
    @Setter
    public class Request {

        private String method;

        private String uri;
    }

    @Getter
    @Setter
    public class Response {

        private Integer status;

        private Content content;
    }

    @Getter
    @Setter
    public class Content {

        private String text;

    }
}
