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

# some place we can mount and view lighthouse reports
VOLUME /home/chrome/BDDStack/build/reports
WORKDIR /home/chrome/BDDStack/build/reports

# Expose port 9222
EXPOSE 9222

# Autorun chrome headless with no GPU
ENTRYPOINT [ "google-chrome-stable" ]
CMD [ "--headless", "--disable-gpu", "--remote-debugging-address=0.0.0.0", "--remote-debugging-port=9222" ]

CMD [ "/bin/bash" ]
