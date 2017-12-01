FROM centos:centos7
LABEL name="BDDStack" \ 
			maintainer="Roland Stens <rstens@stens.ca>" \
			version="0.1" \
			description="Google Chrome Headless in a container"

RUN yum -y update; yum clean all
RUN yum -y install epel-release
RUN yum -y install wget nano Xvfb java-1.8.0-openjdk git; yum clean all
RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_x86_64.rpm
RUN yum -y install google-chrome-stable_current_x86_64.rpm; yum clean all
RUN rm google-chrome-stable_current_x86_64.rpm

# Add Chrome as a user
RUN groupadd -r chrome && useradd -r -g chrome -G audio,video chrome \
    && mkdir -p /home/chrome && chown -R chrome:chrome /home/chrome

# Run Chrome non-privileged
USER chrome

CMD [ "/bin/bash" ]
