/* 
 * Temporary file - online for preliminary tests.
 */
insert into response_template (port, method, uri, status_code, body)
values (9898, 'GET', '/myapi/loki/test', 200, '[{"account":{"id":709918,"description":"Brasil Account","personId":28333,"name":"Flavio Andrade","model":"XPTO","country":"BR","enabled":true},"profile":{"id":"ADMIN","name":"ADMIN","standard":true,"order":1}}]');

insert into response_template (port, method, uri, status_code, body)
values (9898, 'GET', '/myapi/loki/v1', 200, '{"systemInfo":{"id":1,"description":"Loki","version":"v0.0.1","author":"Flavio Andrade"}}');

insert into response_template (port, method, uri, status_code, body)
values (9898, 'GET', '/myapi/loki/v2', 404, '{"status": 404, "error": "Not Found", "message": "Not Found"}');

insert into response_template (port, method, uri, status_code, body)
values (9898, 'GET', '/myapi/loki/*', 200, '{"systemInfo":{"id":1,"description":"Wildcard patern test 1","version":"v0.0.1","author":"Flavio Andrade"}}');

insert into response_template (port, method, uri, status_code, body)
values (9898, 'GET', '/myapi/loki/**', 200, '{"systemInfo":{"id":1,"description":"Double wildcard patern test 2","version":"v0.0.1","author":"Flavio Andrade"}}');

insert into response_template (port, method, uri, status_code, body)
values (9898, 'GET', '/myapi/*/wildcard', 200, '{"systemInfo":{"id":1,"description":"Wildcard patern test 3","version":"v0.0.1","author":"Flavio Andrade"}}');

insert into response_template (port, method, uri, status_code, body)
values (9898, 'GET', '/myapi/*/wildcard/**', 200, '{"systemInfo":{"id":1,"description":"Wildcard and double wildcard patern test 4","version":"v0.0.1","author":"Flavio Andrade"}}');

insert into response_template (port, method, uri, status_code, body)
values (9898, 'POST', '/myapi/*/wildc?rd', 200, '{"systemInfo":{"id":1,"description":"Wildcards patern test 5","version":"v0.0.1","author":"Flavio Andrade"}}');