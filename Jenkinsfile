pipeline {
    //agent { node { label 'docker' } }

    parameters {
        choice(name: 'stageparam', choices: ['build', 'deploy-ICP', 'deploy-EKS-AIM-VPC', 'deploy-EKS-ES-VPC-DIT', 'deploy-EKS-ES-VPC-FIT', 'deploy-EKS-ES-VPC-IPE', 'deploy-EKS-ES-VPC-IAT'], description: 'Build and test only or build,test, and deploy in specific environments')
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '20'))
        disableConcurrentBuilds()
        timeout(time: 1, unit: 'HOURS')
        sendSplunkConsoleLog()
    }

    stages {
        stage('Build') {
            steps {
                 cmd: 'clean build'
            }
        }

        stage('Deploy') {
            steps {
                script {
                    if (stageparam == "deploy-ICP") {

                    } else if (stageparam == "deploy-EKS-AIM-VPC") {

                    } else if (stageparam == "deploy-EKS-AIM-VP") {
                    }
                }
            }

        }
    }
}
