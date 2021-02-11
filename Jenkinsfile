pipeline {
//agent { node { label 'docker' } }
agent any

triggers {
githubPush()
}


parameters {
    choice(name: 'Deploy-Env', choices: ['build', 'deploy-dev', 'deploy-devAuto', 'deploy-devBA'], description: 'Select destination environment for deployment')
}

stages {
    stage('build') {
        steps {
             echo "build"
             echo env.BRANCH_NAME
        }
    }

    stage('Deploy') {
        when {
          beforeAgent true
          branch 'master'
        }
        steps {
            script {
                //def pass = passwordParameter description: "Enter password"
                if (Deploy-Env == "deploy-dev") {
                   echo "deploying"
                } else if (Deploy-Env == "deploy-devAuto") {
                   echo "deploying"
                } else if (Deploy-Env == "deploy-devBA") {
                  echo "deploying"
                }
            }
        }

    }
}
}
