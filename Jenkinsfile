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
                sh '''
                    pkill -f 'userservice-0.0.1-SNAPSHOT.jar' || true
                    nohup java -jar target/userservice-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
                '''
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
