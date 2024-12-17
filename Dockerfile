FROM quay.io/wildfly/wildfly:28.0.0.Final-jdk17


COPY target/lab3.war /opt/jboss/wildfly/standalone/deployments/lab3.war
