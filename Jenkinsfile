pipeline {
  agent {
    label "docker"
  }
  environment { 
    JAVA_HOME = "/usr/lib/jvm/default-jvm"
  }
  stages {
    stage("Lint") {
      steps {
        sh "echo lint"
      }
    }
    stage("Test") {
      steps {
        sh "docker-compose -f docker-compose-local.yaml build app"
        sh "docker-compose -f docker-compose-local.yaml run --rm --service-ports test"
      }
    }
    stage("Build & Deliver") {
      steps {
        sh '''
          docker run --rm \
            -v $HOME/.m2:/root/.m2:rw \
            -v /var/lib/docker/volumes/cicd_jenkins_agent/_data/issue-service_${BRANCH_NAME}:/app:rw \
            -w "/app" \
            openjdk:8u191-jdk-alpine3.9 \
            sh -c "./mvnw -Dmaven.test.skip=true clean package"

          IMAGE_NAME=$($PWD/mvnw help:evaluate -Dexpression=project.name -q -DforceStdout)
          IMAGE_VERSION=$($PWD/mvnw help:evaluate -Dexpression=project.version -q -DforceStdout)

          docker build $PWD \
            -t $IMAGE_NAME:$IMAGE_VERSION \
            --build-arg jar_file=$IMAGE_NAME-$IMAGE_VERSION.jar
        
          # can't set the environment variable for later stage use...
          docker tag $IMAGE_NAME:$IMAGE_VERSION $PRIVATE_REGISTRY/$IMAGE_NAME
          docker tag $IMAGE_NAME:$IMAGE_VERSION $PRIVATE_REGISTRY/$IMAGE_NAME:$IMAGE_VERSION.b${BUILD_NUMBER}
          docker push $PRIVATE_REGISTRY/$IMAGE_NAME
          docker push $PRIVATE_REGISTRY/$IMAGE_NAME:$IMAGE_VERSION.b${BUILD_NUMBER}
        '''
      }
    }
    stage("Stage") {
      when {
        branch "master"
      }
      steps {
        sh "docker service update --force --update-parallelism 2 --update-delay 30s demo_issues"
      }
    }
    stage("Deploy") {
      when {
        branch "production"
      }
      steps {
        sh "echo deploy"
      }
    }
  }
  post {
    always {
      sh "docker-compose -f docker-compose-local.yaml down"
    }
  }
}
