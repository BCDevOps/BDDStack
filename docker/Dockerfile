FROM centos:centos7
MAINTAINER Roland Stens <rstens@stens.ca>

RUN yum -y update; yum clean all
RUN yum -y install epel-release
RUN yum -y install wget nano zip unzip Xvfb java-1.8.0-openjdk java-1.8.0-openjdk-devel git; yum clean all
RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_x86_64.rpm
RUN yum -y install google-chrome-stable_current_x86_64.rpm
RUN ps -ef |grep Xvfb username 12988 1 0 Oct05 ? 00:00:02 Xvfb :99 -screen 0 1152x900x8
RUN yum -y install maven

EXPOSE 8000

CMD [ "/bin/bash" ]
