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
            }
        }

        stage('Deploy') {
            steps {
                script {
                    if (stageparam == "deploy-ICP") {
                          if(env.BRANCH_NAME == "master") {
                                echo "deploying"
                                echo env.BRANCH_NAME
                          }

                    } else if (stageparam == "deploy-EKS-AIM-VPC") {

                         if(env.BRANCH_NAME == "origin/*") {
                                                                  echo "deploying"
                                                                 }
                    } else if (stageparam == "deploy-EKS-AIM-VP") {

                    if(env.BRANCH_NAME == "feature/*") {
                                                             echo "deploying"
                                                            }
                    }
                }
            }

        }
    }
}

/*properties([pipelineTriggers([githubPush()])])

node {
        git url: 'https://github.com/Srielatha/AngularRestful.git',branch: 'master'
        stage ('Compile Stage') {

            echo "compiling"
            echo "compilation completed"
        }

        stage ('Testing Stage') {

            echo "testing completed"
            echo "testing completed"
        }
        stage("Deploy") {

                echo "deployment completed"
                        }
            }
} */
