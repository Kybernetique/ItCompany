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
        stage('Build image') {
            steps {          
                echo 'Building of an image has been started...'
                docker.build("kybernetique/web-project .")
                echo 'Building of an image executed successfully!'
            }
        }
    }
}
