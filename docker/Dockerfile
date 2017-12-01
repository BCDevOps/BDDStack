FROM centos:centos7
LABEL name="BDDStack" \ 
			maintainer="Roland Stens <rstens@stens.ca>" \
			version="0.1" \
			description="Google Chrome Headless in a container"

RUN yum -y update; yum clean all
RUN yum -y install epel-release
RUN yum -y install wget nano zip unzip Xvfb java-1.8.0-openjdk java-1.8.0-openjdk-devel git; yum clean all
RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_x86_64.rpm
RUN yum -y install google-chrome-stable_current_x86_64.rpm
RUN git clone https://github.com/rstens/BDDStack.git
CMD [ "/bin/bash" ]

