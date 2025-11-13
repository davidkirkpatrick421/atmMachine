pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                // Builds the Dockerfile from this project's folder
                sh 'docker build -t my-atm-app .'
            }
        }

        stage('Run App') {
            steps {
                script {
                    // This cleans up the container from the *last* run
                    sh 'docker rm my-atm-app-run || true'

                    // This runs your new container and waits for it to finish.
                    // The app's output will appear in this build log.
                    sh 'docker run --name my-atm-app-run my-atm-app'
                }
            }
        }
    }

    post {
        // This cleans up old images to save disk space
        always {
            sh 'docker image prune -f'
        }
    }
}