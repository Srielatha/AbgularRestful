pipeline {

    agent any


    triggers {
      githubPush()
    }

    triggers {
            pollSCM('') //Empty quotes tells it to build on a push
    }

    parameters {
        choice(name: 'stageparam', choices: ['build', 'deploy-dev', 'deploy-devAuto', 'deploy-devBA', 'deploy-Uat', 'deploy-prod'], description: 'Select destination environment for deployment')
    }

    stages {
         stage('Checkout'){

                  checkout scm
               }
        stage('build') {
            steps {
                 //properties([pipelineTriggers([[$class: 'GitHubPushTrigger'], pollSCM('H/15 * * * *')])])
                 echo "build"
                 echo env.BRANCH_NAME
            }
        }

        stage('Deploy-lower-env') {
           when {
                beforeAgent true
                branch 'feature/app'
           }
            steps {
                script {
                    //def pass = passwordParameter description: "Enter password"
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
                    //def pass = passwordParameter description: "Enter password"
                    if (stageparam == "deploy-Uat") {
                       echo "deploying"
                    } else if (stageparam == "deploy-prod") {
                       echo "deploying"
                    }
                }
            }
        }
    }
}

