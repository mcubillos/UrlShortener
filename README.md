# UrlShortener
Most of us are familiar with seeing URLs like bit.ly or t.co on our Twitter or Facebook feeds. These are examples of shortened URLs, which are a short alias or pointer to a longer page link. For example, I can send you the shortened URL http://bit.ly/SaaYw5 that will forward you to a very long Google URL with search results on how to iron a shirt.

This is a maven project then to run it 
$ mvn spring-boot:run


Endpoints:

For short url 
POST http://localhost:8080/shortUrl with the follow body 
{
  "url":"twitter.com"
}
the response will be http://localhost:8080/{id} 

If you want the original url 
GET http://localhost:8080/{id}
