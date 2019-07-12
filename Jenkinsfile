pipeline {
  agent {
    label "docker"
  }
  stages {
    stage("Lint") {
      steps {
        sh "echo lint"
      }
    }
    stage("Test") {
      steps {
        sh "echo test"
      }
    }
    stage("Build") {
      steps {
        sh "echo ${env.BUILD_NUMBER}"
      }
    }
    stage("Publish") {
      steps {
        sh "echo public"
      }
    }
    stage("Deploy") {
      steps {
        sh "echo deploy"
      }
    }
  }
  post {
    always {
      sh "echo done!"
    }
  }
}
