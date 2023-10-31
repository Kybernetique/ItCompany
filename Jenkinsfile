pipeline {
    agent any
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
        stage('Login dockerhub') {
            steps {
                sh "docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD}"
            }
        }
        stage('Build image') {
            steps {          
                echo 'Deploy has been started...'
                sh "docker build -t kybernetique/web-project ."
                echo 'Deploy executed successfully!'
            }
        }
    }
}
