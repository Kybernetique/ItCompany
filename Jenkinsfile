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
    }
}
