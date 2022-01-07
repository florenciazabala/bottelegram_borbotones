# bottelegram_borbotones

Esto va en el main:

final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

GenericUrl url = new GenericUrl("http://localhost:8090/pruebaaaa");
HttpRequest request = HTTP_TRANSPORT.createRequestFactory().buildGetRequest(url);
HttpResponse response = request.execute();
System.out.println(response.getStatusCode());

InputStream is = response.getContent();
int ch;
while ((ch = is.read()) != -1) {
    System.out.print((char) ch);
}
response.disconnect();

<dependency>
    <groupId>com.google.api-client</groupId>
    <artifactId>google-api-client</artifactId>
    <version>1.33.0</version>
</dependency>