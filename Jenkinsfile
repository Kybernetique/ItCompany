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
        stage('Deploy') {
            steps {
                withCredentials([string(credentialsId: 'e95966e7-3860-4656-a5a4-51313536546b', variable: 'dockerhubpswd')])
                sh "docker login -u kybernetique -p ${dockerhubpswd}"
                echo 'Deploy has been started...'
                sh "docker build -t kybernetique/web-project ."
                sh "docker push kybernetique/web-project:latest"
                echo 'Deploy executed successfully!'
            }
        }
    }
}
