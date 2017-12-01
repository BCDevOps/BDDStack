FROM centos:centos7
LABEL name="BDDStack" \ 
			maintainer="Roland Stens <rstens@stens.ca>" \
			version="0.1" \
			description="Google Chrome Headless in a container"

RUN yum -y update; yum clean all
RUN yum -y install epel-release
RUN yum -y install wget nano zip unzip Xvfb java-1.8.0-openjdk java-1.8.0-openjdk-devel git; yum clean all
RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_x86_64.rpm
RUN yum -y install google-chrome-stable_current_x86_64.rpm; yum clean all
RUN ps -ef |grep Xvfb username 12988 1 0 Oct05 ? 00:00:02 Xvfb :99 -screen 0 1152x900x8
RUN yum -y install maven; yum clean all
RUN useradd autotester
RUN echo pipeline | passwd theUsername --stdin
RUN cd /home/autotest
RUN su - autotest
RUN git clone https://github.com/rstens/BDDStack.git
RUN cd BDDStack
CMD [ "/bin/bash" ]

