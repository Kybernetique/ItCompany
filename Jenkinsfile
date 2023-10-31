pipeline {
    agent any
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-login')
    }
    stages {
        stage('Build') {
            steps {
                sh "chmod +x gradlew"
                echo 'Building has been started...'
                sh "./gradlew build"
                echo 'Building executed succesfully!'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing has been started...'
                sh "./gradlew test"
                echo 'Testing executed successfully!'
            }
        }
        stage('login to dockerhub') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
        stage('Build image') {
            steps {          
                echo 'Building of an image has been started...'
                sh "docker build -t kybernetique/web-project ."
                sh "docker push kybernetique/web-project:latest"
                echo 'Building of an image executed successfully!'
            }
        }
    }
}
