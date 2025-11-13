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
                // Build the image and give it a name
                sh 'docker buildx build --network=host -t my-atm-app --load .'
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