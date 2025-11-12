pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // This automatically gets the code from the Gitea webhook
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                // Builds the Dockerfile from this project's folder
                // and names the image 'my-atm-app'
                sh 'docker build -t my-atm-app .'
            }
        }

        stage('Deploy App') {
            steps {
                script {
                    // This stops and removes the *old* container
                    // (It's OK if it fails the first time)
                    sh 'docker stop my-atm-app-run || true'
                    sh 'docker rm my-atm-app-run || true'

                    // This starts your *new* container
                    // (Change 8080 to your app's port if it's a web app)
                    sh 'docker run -d --name my-atm-app-run -p 8000:8080 my-atm-app'
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