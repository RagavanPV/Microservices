mkdir -p /certs

openssl req -x509 -newkey rsa:4096 -nodes -keyout /certs/privateKey.key -out /certs/certificate.crt -days 365 -subj "/C=IN/ST=TamilNadu/L=Chennai/O=Ragavan/OU=Microservices/CN=ragavanpv.wordpress.com"
openssl pkcs12 -export -passout pass: -out /certs/cert.pfx -inkey /certs/privateKey.key -in /certs/certificate.crt

java -jar auth-server.jar
