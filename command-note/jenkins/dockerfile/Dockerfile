FROM jenkins/jenkins:lts
USER root
COPY aliyun-sources.list /etc/apt/sources.list
RUN apt-get clean && apt-get update && apt-get install -y git
