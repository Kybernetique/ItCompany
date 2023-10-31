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
        stage('Build Image') {
            steps {          
                echo 'Building of an image has been started...'
                sh "docker build -t kybernetique/web-project ."
                echo 'Building of an image executed successfully!'
            }
        }
        stage('Login Dockerhub') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
        stage('Push to Dockerhub') {
            steps{
                sh "docker push kybernetique/web-project:latest"
            }
        }
    }
}
