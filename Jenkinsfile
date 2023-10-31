pipeline {
    agent "main"
    stages {
        stage('Build') {
            steps {
                echo "Building has been starter..."
                sh gradlew build
                echo "Building executed succesfully!"
            }
        }
        stage('Test') {
            steps {
                echo "Testing has been starter..."
                sh gradlew test
                echo "Testing executed successfully!"
            }
        }
    }
}
