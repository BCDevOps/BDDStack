# BuildConfig

## Description

This buildconfig can be used to create a fresh imagestream, which can be used in a Jenkinsfile.

## Usage

Process this file, creating or replacing imagestreams and builds.  Make sure to specify a project.

```
oc process -f openshift/bddstack.bc.yaml | oc [create|replace] -n <PROJECT> -f -
```

Consume this image in a Jenkinsfile.  This example uses a declarative syntax.

```
stage('Functional Tests') {
    steps {
        script {
            openshift.withCluster() {
                openshift.withProject(TOOLS_PROJECT) {
                    echo "Functional Testing"
                    podTemplate(
                        label: "bddstack-${env.CHANGE_ID}",
                        name: "bddstack-${env.CHANGE_ID}",
                        serviceAccount: 'jenkins',
                        cloud: 'openshift',
                        containers: [
                            containerTemplate(
                                name: 'jnlp',
                                image: 'docker-registry.default.svc:5000/<PROJECT>/bddstack:latest',
                                resourceRequestCpu: '800m',
                                resourceLimitCpu: '800m',
                                resourceRequestMemory: '4Gi',
                                resourceLimitMemory: '4Gi',
                                workingDir: '/home/jenkins',
                                command: '',
                                args: '${computer.jnlpmac} ${computer.name}'
                            )
                        ]
                    ) {
                        node("bddstack-${env.CHANGE_ID}") {
                            //the checkout is mandatory, otherwise functional tests would fail
                            echo "checking out source"
                            checkout scm
                            dir('functional-tests') {
                                try {
                                    try {
                                        sh './gradlew chromeHeadlessTest'
                                    } finally {
                                        archiveArtifacts allowEmptyArchive: true, artifacts: 'build/reports/**/*'
                                        archiveArtifacts allowEmptyArchive: true, artifacts: 'build/test-results/**/*'
                                        junit 'build/test-results/**/*.xml'
                                    }
                                } catch (error) {
                                    echo error
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

```
