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
                //gradleBuild image: 'dtr.cdl.es.ad.adp.com/innerspace/gradle:6.0.1',
                 cmd: 'clean build'
            }
        }

        stage('Deploy') {
            steps {
                script {
                    if (stageparam == "deploy-ICP") {
                        kubeDeploy cluster: 'icp-aws-ue1-dev2-aim.us-east-1.nexgen-nonprod.aws.adp',
                            namespace: 'aes',
                            envName: 'dit',
                            credentialsId: 'aim-devops',
                            helmChart: 'ferris-wheel/web-service',
                            helmChartValues: ['values.yaml'],
                            projectModel: [name: 'adp-enterprise-search-service', version: 'latest']
                    } else if (stageparam == "deploy-EKS-AIM-VPC") {
                        kubeDeploy cloudctlImage: 'dtr.cdl.es.ad.adp.com/innerspace/cloudctl:3.2.1-helm3',
                            cluster: 'es-nonprod-01',
                            region: 'us-east-1',
                            envName: 'dit',
                            role: 'arn:aws:iam::711283292668:role/AWS-PowerUsers',
                            namespace: 'aes-dcc-dit',
                            credentialsId: 'aim-devops',
                            helmChart: 'ferris-wheel/web-service',
                            helmChartValues: ['values1.yaml'],
                            projectModel: [name: 'adp-enterprise-search-service', version: 'latest']
                    }
                }
            }

        }
    }
}
