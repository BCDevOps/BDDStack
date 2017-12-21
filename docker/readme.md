This is an example of a Dockerfile that you can use to create a local image.
This is not the OpenShift Dockerfile, that one can be found [here:](https://github.com/BCDevOps/openshift-tools/blob/master/provisioning/jenkins-slaves/bddstack/Dockerfile)

Once the docker image is running:

* cd /home/chrome 
* git clone <https://github.com/rstens/BDDStack.git>
* cd BDDStack
* ./gradlew chromeHeadlessTest or 
* If you want to run ./gradlew firefoxHeadlessTest, first run:

~~~~
Xvfb :1 -screen 0 1024x768x24 &
export DISPLAY=:1
~~~~
