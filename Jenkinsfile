pipeline {
    agent any
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-login')
    }
    stages {
        stage('Up Containers') {
            steps {          
                echo 'Building of an image has been started...'
                sh "docker-compose -p kybernetique/web-project up"
                echo 'Building of an image executed successfully!'
            }
        }
        stage('Login Dockerhub') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
        stage('Push Dockerhub') {
            steps{
                sh "docker push kybernetique/web-project:latest"
            }
        }
    }
}
