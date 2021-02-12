/*pipeline {
    agent any

    parameters {
        choice(name: 'stageparam', choices: ['build', 'deploy-dev', 'deploy-devAuto', 'deploy-devBA', 'deploy-Uat', 'deploy-prod'], description: 'Select destination environment for deployment')
    }


    stages {
      /*  stage('maven pom') {
            steps {
                 script {
                   pom = getPom(effective: false)
                 }
                 echo "build"
                echo env.BRANCH_NAME
            }
        }

        stage('build') {
            steps {
                 echo "build"
                 echo env.BRANCH_NAME
            }
        }

        stage('Deploy-lower-env') {
           when {
                beforeAgent true
                branch 'feature/jenkins-poc'
           }
            steps {
                script {
                    if (stageparam == "deploy-dev") {
                       echo "deploying"
                    } else if (stageparam == "deploy-devAuto") {
                       echo "deploying"
                    } else if (stageparam == "deploy-devBA") {
                      echo "deploying"
                    }
                }
            }
        }

         stage('Deploy-higher-env') {

            when {
              beforeAgent true
              branch 'master'
            }

            steps {
                script {
                    if (stageparam == "deploy-Uat") {
                       def pass = input  parameters: [password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'A secret password') ]
                       echo "deploying"
                    } else if (stageparam == "deploy-prod") {
                       def pass = input  parameters: [password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'A secret password') ]
                       echo "deploying"
                    }
                }
            }
        }
    }
} */
def loadValuesYaml(x){
    def valuesYaml = readYaml (file: './config.yaml')
    return valuesYaml(x);
}

pipeline {
    options {
        // persist artifacts and console output for the most recent 3 pipeline runs
        buildDiscarder(logRotator(numToKeepStr: '3'))
        //this wil stop concurrent builds
        disableConcurrentBuilds()
        //lock(label: 'testing', quantity: 1, resource: 'test', skipIfLocked: true, variable: 'echo "locking resource due to insufficient permissions "')
        skipDefaultCheckout true
    }

    agent any

    parameters {
        choice(
            name: 'DEPLOY_ENV',
            description: 'Select destination environment for deployment',
            choices: ['dev', 'dev-auto', 'prod']
        )
    }

    environment {
        // reference maven install location
        MAVEN_HOME = '/opt/apache-maven-3.6.3/'
        // set environment specific properties used by Jenkins/CDK for deployment
          ENV = loadValuesYaml('ENV')
//        SUBENV = params.DEPLOY_ENV.loadValuesYaml('SUBENV')
//        AWS_ACCOUNT = params.DEPLOY_ENV.loadValuesYaml('AWS_ACCOUNT')
//        VPC_ENDPOINT_ID = params.DEPLOY_ENV.loadValuesYaml('VPC_ENDPOINT_ID')
//        JENKINS_ROLE = params.DEPLOY_ENV.loadValuesYaml('JENKINS_ROLE')
        // set job success/failure email receipients
        //JOB_REPORTING_EMAILS = 'eskm-developers@adp.org'
    }

    stages {
        /* stage('Artifactory Configuration') {
             steps {
                 rtServer (
                     id: 'ARTIFACTORY_SERVER',
                     url: 'https://artifactory.adp.org/artifactory',
                     credentialsId: 'jenkins-artifactory'
                 )
 
                 rtMavenDeployer (
                     id: 'MAVEN_DEPLOYER',
                     serverId: 'ARTIFACTORY_SERVER',
                     releaseRepo: 'adp-release',
                     snapshotRepo: 'adp-snapshot'
                 )
 
                 rtMavenResolver (
                     id: 'MAVEN_RESOLVER',
                     serverId: 'ARTIFACTORY_SERVER',
                     releaseRepo: 'libs-release',
                     snapshotRepo: 'libs-snapshot'
                 )
 
                 rtNpmResolver (
                     id: 'NPM_RESOLVER',
                     serverId: 'ARTIFACTORY_SERVER',
                     repo: 'npm-virtual'
                 )
             }
         }
 
         stage('Build Java Artifacts') {
             steps {
                 rtMavenRun (
                     pom: 'pom.xml',
                     // run maven in non-interactive mode (-B), forcing snapshot update (-U)
                     goals: 'clean package -B -U -DskipTests',
                     resolverId: 'MAVEN_RESOLVER'
                     // ELK 2020-11-23 removing deployment of artifacts, currently getting 405 errors
                     //deployerId: "MAVEN_DEPLOYER",
                 )
             }
         }
 
         stage('Java Unit Tests') {
             steps {
                 // ELK 2020-11-18 check if a testng plugin is available in adp jenkins
                 rtMavenRun (
                     pom: 'pom.xml',
                     goals: 'test',
                     resolverId: 'MAVEN_RESOLVER'
                 )
             }
         }
 
         stage('Infrastructure - NPM Dependencies') {
             steps {
                 sh 'npm ci'
             }
         }
 
         stage('Infrastructure - Compile Typescript') {
             steps {
                 sh 'npm run build'
             }
         }
 
         stage('Sonar Scan') {
             steps {
                 // ELK 2020-11-18 investigate how to incorporate typescript code analysis
                 withSonarQubeEnv('sonarqube') {
                     rtMavenRun (
                         pom: 'pom.xml',
                         goals: 'sonar:sonar',
                         resolverId: 'MAVEN_RESOLVER'
                     )
                 }
             }
         } */

        stage('CDK Deploy Stack') {
            steps {
                script {
                    if(DEPLOY_ENV == "dev") {
                        echo "deploying"
                        echo ENV
                        /* withAWS(role:"${JENKINS_ROLE}", roleAccount:"${AWS_ACCOUNT}", duration: 3600, roleSessionName: 'jenkins-eskm-session', region:'us-east-1') {
                            sh 'cdk deploy --require-approval never'
                     }*/
                    }
                }
            }
        }

        stage('CDK Deploy Stack - higher env') {
            when {
                beforeAgent true
                branch 'master'
            }
            steps {
                script {
                    if(DEPLOY_ENV == "prod") {
                        //prompt for password in console log
                        def pass = input  parameters: [password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'A secret password') ]
                        echo "deploying"
                        echo ENV
                        /*withAWS(role:"${JENKINS_ROLE}", roleAccount:"${AWS_ACCOUNT}", duration: 3600, roleSessionName: 'jenkins-eskm-session', region:'us-east-1') {
                            sh 'cdk deploy --require-approval never'
                        }*/
                    }
                }
            }
        }

        /*
        stage('Monitoring Tools - NPM install deps') {
            steps {
                writeFile file: '.tmp/package.json', text: '{"dependencies":{"typescript":"^4.0.3","@adp-cdk/cdk-cw-dashboard": "^0.1.2","aws-cdk": "^1.70.0"}}'
                sh """cat .tmp/package.json"""
                rtNpmInstall (
                    resolverId: 'NPM_RESOLVER',
                    path: '.tmp'
                )
            }
        }

        stage('Monitoring Tools - CDK Diff Check') {
            steps {
                withAWS(role:"${JENKINS_ROLE}", roleAccount:"${AWS_ACCOUNT}", duration: 3600, roleSessionName: 'jenkins-eskm-session', region:'us-east-1') {
                    script {
                        try {
                            dir(".tmp/node_modules/@adp-cdk/cdk-cw-dashboard") {
                                if(env.SUBENV) {
                                    sh """${WORKSPACE}/.tmp/node_modules/aws-cdk/bin/cdk diff --context sub_env=${SUBENV} --context source_stack=adp-${ENV}-iad-eskm-ecs-${SUBENV}-stack"""
                                } else {
                                    sh """${WORKSPACE}/.tmp/node_modules/aws-cdk/bin/cdk diff --context source_stack=adp-${ENV}-iad-eskm-ecs-stack"""
                                }
                            }
                        } catch (e) {
                            echo e.getMessage()
                            echo "Error detected in cdk diff for monitoring stack - but we do not care while it is a pilot programm."
                        }
                    }
                }
            }
        }

        stage('Monitoring Tools - CDK Deploy') {
            steps {
                withAWS(role:"${JENKINS_ROLE}", roleAccount:"${AWS_ACCOUNT}", duration: 3600, roleSessionName: 'jenkins-eskm-session', region:'us-east-1') {
                    script {
                        try {
                            dir(".tmp/node_modules/@adp-cdk/cdk-cw-dashboard") {
                                if(env.SUBENV) {
                                    sh """${WORKSPACE}/.tmp/node_modules/aws-cdk/bin/cdk deploy --context sub_env=${SUBENV} --context source_stack=adp-${ENV}-iad-eskm-ecs-${SUBENV}-stack"""
                                } else {
                                    sh """${WORKSPACE}/.tmp/node_modules/aws-cdk/bin/cdk deploy --context source_stack=adp-${ENV}-iad-eskm-ecs-stack"""
                                }
                            }
                        } catch (e) {
                            echo e.getMessage()
                            echo "Error detected in cdk deploy for monitoring stack - but we do not care while it is a pilot programm."
                        }
                    }
                }
            }
        }
        */
    }

    /*post {
        failure {
            emailext(
                to: """${JOB_REPORTING_EMAILS}""",
                subject: """Build FAILED in Jenkins: ${env.JOB_NAME} - (${params.DEPLOY_ENV} deployment) - #${env.BUILD_NUMBER}""",
                body: """Check console output at ${env.BUILD_URL} to view the results. \n\n ${currentBuild.changeSets}"""
            )
        }
        unstable {
            emailext(
                to: """${JOB_REPORTING_EMAILS}""",
                subject: """UNSTABLE build in Jenkins: ${env.JOB_NAME} - (${params.DEPLOY_ENV} deployment) - #${env.BUILD_NUMBER}""",
                body: """Check console output at ${env.BUILD_URL} to view the results. \n\n ${currentBuild.changeSets}"""
            )
        }
        success {
            emailext(
                to: """${JOB_REPORTING_EMAILS}""",
                subject: """Build SUCCESS in Jenkins: ${env.JOB_NAME} - (${params.DEPLOY_ENV} deployment) - #${env.BUILD_NUMBER}""",
                body: """Check console output at ${env.BUILD_URL} to view the results. \n\n ${currentBuild.changeSets}"""
            )
        }
    }*/
}

/**
 * Helper method to generate the environment name (for the account) based on the deploy
 * environment (deployEnv) selected by the job runner.
 */
String getEnvName(String deployEnv) {
    if(deployEnv.startsWith('dev')) {
        echo "Setting 'ENV=dev'"
        return "dev"
    } else {
        throw new Exception('Unrecognized deployment environment selected when determining ENV property: ' + deployEnv);
    }
}

/**
 * Helper method to generate the subenvironment name (within an account) based on the
 * deploy environment (deployEnv) selected by the job runner.
 */
String getSubEnvName(String deployEnv) {
    if(deployEnv.equals('dev-auto')) {
        echo "Setting 'SUBENV=auto'"
        return 'auto'
    } else if(deployEnv.equals('dev-ba')) {
        echo "Setting 'SUBENV=ba'"
        return 'ba'
    } else {
        echo "Setting 'SUBENV=' (none)"
        return ''
    }
}

/**
 * Helper method to generate the AWS Account ID based on the deploy environment
 * (deployEnv) selected by the job runner.
 */
String geAwsAccountId(String deployEnv) {
    if(deployEnv.startsWith('dev')) {
        echo "Setting 'AWS_ACCOUNT=248847483068'"
        return '248847483068'
    } else {
        throw new Exception('Unrecognized deployment environment selected when determining AWS_ACCOUNT property: ' + deployEnv);
    }
}

/**
 * Helper method to generate the VPC Endpoint ID based on the deploy environment
 * (deployEnv) selected by the job runner.
 */
String getVpcEndpointId(String deployEnv) {
    if(deployEnv.startsWith('dev')) {
        echo "Setting 'VPC_ENDPOINT_ID=vpce-0d2538e8b49acd2c5'"
        return 'vpce-0d2538e8b49acd2c5'
    } else {
        echo "Setting 'VPC_ENDPOINT_ID=' (none)"
        return ''
    }
}

/**
 * Helper method to generate the Jenkins Role based on the deploy environment
 * (deployEnv) selected by the job runner.
 */
String getJenkinsRole(String deployEnv) {
    if(deployEnv.startsWith('dev')) {
        echo "Setting 'JENKINS_ROLE=adp-dev-iad-eskm-jenkins-role'"
    } else if(deployEnv.startsWith('prod')) {
        echo "Setting 'JENKINS_ROLE=adp-dev-iad-eskm-jenkins-role'"
    }else {
        throw new Exception('Unrecognized deployment environment selected when determining JENKINS_ROLE property: ' + deployEnv);
    }
}