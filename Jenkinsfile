pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Building has been started...'
                ./gradlew build
                echo 'Building executed succesfully!'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing has been started...'
                ./gradlew test
                echo 'Testing executed successfully!'
            }
        }
    }
}
