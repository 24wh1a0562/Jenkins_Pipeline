pipeline {
    agent any

    tools {
        maven 'maven'
        jdk 'java'
    }

    stages {
        
        stage('Build & Test') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Deploy') {
            steps {
                sh 'sudo systemctl restart userservice'
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Build failed. Check logs above.'
        }
    }
}
