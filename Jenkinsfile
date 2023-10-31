pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo "Building has been starter..."
                ./gradlew build
                echo "Building executed succesfully!"
            }
        }
        stage('Test') {
            steps {
                echo "Testing has been starter..."
                ./gradlew test
                echo "Testing executed successfully!"
            }
        }
    }
}
