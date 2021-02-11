pipeline {
    //agent { node { label 'docker' } }
    agent any

triggers {
    githubPush()
  }


    parameters {
        choice(name: 'stageparam', choices: ['build', 'deploy-ICP', 'deploy-EKS-AIM-VPC', 'deploy-EKS-ES-VPC-DIT', 'deploy-EKS-ES-VPC-FIT', 'deploy-EKS-ES-VPC-IPE', 'deploy-EKS-ES-VPC-IAT'], description: 'Build and test only or build,test, and deploy in specific environments')
    }

    stages {
        stage('Build') {
            steps {
                 //cmd: 'clean build'
                 echo "building"
                 echo env.BRANCH_NAME
            }
        }

        stage('Deploy') {
            when {
              beforeAgent true
              branch 'feature/app'
            }
            steps {
                script {
                    //def pass = passwordParameter description: "Enter password"
                    if (stageparam == "deploy-ICP") {
                                         echo "deploying"

                    } else if (stageparam == "deploy-EKS-AIM-VPC") {

                        pass;
                    } else if (stageparam == "deploy-EKS-AIM-VP") {


                    }
                }
            }

        }
    }
}
