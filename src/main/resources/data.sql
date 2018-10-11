/*
 * End-point to tests if everthing is ok
*/
insert into response_template (port, method, uri, status_code, body)
values (7777, 'GET', '/loki/test', 200, '{"systemInfo":{"description":"Server is running on port 7777","version":"v1.0.0"}}');