FROM maven

VOLUME ["/opt/htdoc/water-article"]

WORKDIR /opt/htdoc/water-article

EXPOSE 8080

CMD mvn install && java -jar ./target/water-article.jar