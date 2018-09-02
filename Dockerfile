FROM maven

RUN mkdir -p /opt/app
ADD ./target/water-article.jar /opt/app/
WORKDIR /opt/app

EXPOSE 8080

CMD java -jar ./water-article.jar