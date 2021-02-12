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
//def loadValuesYaml(){
//    def valuesYaml = readYaml (file: './config.yaml')
//    return valuesYaml[];
//}

pipeline {
    options {
        // persist artifacts and console output for the most recent 3 pipeline runs
        buildDiscarder(logRotator(numToKeepStr: '3'))
        //this wil stop concurrent builds
        disableConcurrentBuilds()
        //lock(label: 'testing', quantity: 1, resource: 'test', skipIfLocked: true, variable: 'echo "locking resource due to insufficient permissions "')
        skipDefaultCheckout true
    }

    agent {
            datas = readYaml (file: './config.yaml')
    }

    parameters {
        choice(
            name: 'DEPLOY_ENV',
            description: 'Select destination environment for deployment',
            choices: ['dev', 'dev-auto', 'prod']
        )
    }

    environment {
        def valuesYaml = readYaml (file: './config.yaml')
        // reference maven install location
         MAVEN_HOME = '/opt/apache-maven-3.6.3/'
        // set environment specific properties used by Jenkins/CDK for deployment
          ENV = loadValuesYaml.get(ENV).get(params.DEPLOY_ENV).get('AWS_ACCOUNT')
          //SUBENV = 'subEnv'
//        AWS_ACCOUNT = params.DEPLOY_ENV.loadValuesYaml('AWS_ACCOUNT')
//        VPC_ENDPOINT_ID = params.DEPLOY_ENV.loadValuesYaml('VPC_ENDPOINT_ID')
//        JENKINS_ROLE = params.DEPLOY_ENV.loadValuesYaml('JENKINS_ROLE')
        // set job success/failure email receipients
        //JOB_REPORTING_EMAILS = 'eskm-developers@adp.org'
    }

    stages {
        stage('CDK Deploy Stack') {
            steps {
                script {
                    def valuesYaml = readYaml (file: './config.yaml')
                    if(DEPLOY_ENV == "dev") {
                        echo ENV
                        echo valuesYaml.get(ENV).get(params.DEPLOY_ENV).get('AWS_ACCOUNT')
                        echo "deploying"
                        //echo loadValuesYaml(test.'ENV')
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
                    def valuesYaml = readYaml (file: './config.yaml')
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
    }
}

/**
 * Helper method to generate the environment name (for the account) based on the deploy
 * environment (deployEnv) selected by the job runner.
 */
String getEnvName(String deployEnv) {
    if(deployEnv.equalsIgnoreCase(String )) {
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