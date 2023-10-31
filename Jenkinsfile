pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Building has been started...'
                bash gradlew build
                echo 'Building executed succesfully!'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing has been started...'
                bash gradlew test
                echo 'Testing executed successfully!'
            }
        }
    }
}
