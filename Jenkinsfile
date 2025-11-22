pipeline {
    agent any
    environment {
        // This tells the app: "When running inside Docker, look for the DB at host 'atm_db'"
        SPRING_DATASOURCE_URL = "jdbc:postgresql://atm_db:5432/atm_db"
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build Docker Image') {
            steps {
                // Builds the image and tags it 'my-atm-app:latest'
                // --network=host allows it to download Maven dependencies faster
                sh 'docker buildx build --network=host -t my-atm-app:latest .'
            }
        }
        stage('Cleanup') {
            steps {
                // Removes "dangling" images to save space
                sh 'docker image prune -f'
            }
        }
    }
}