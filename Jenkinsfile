pipeline {
    agent any
    environment {
        BROWSER = "chrome"
        REPORT_DIR = "reports"
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/yourusername/yourproject.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test -Dbrowser=${BROWSER}'
            }
        }

        stage('Publish Reports') {
            steps {
                // Archive Extent Reports
                archiveArtifacts artifacts: "${REPORT_DIR}/**/*.html", allowEmptyArchive: true

                // Publish TestNG results
                publishHTML(target: [
                    reportName: 'Extent Report',
                    reportDir: "${REPORT_DIR}",
                    reportFiles: '*.html',
                    keepAll: true,
                    alwaysLinkToLastBuild: true,
                    allowMissing: true
                ])
            }
        }

        stage('Notifications') {
            steps {
                // Send email or Slack notification
                // Example: email notification plugin
                mail to: 'team@example.com',
                     subject: "Build #${env.BUILD_NUMBER} - ${currentBuild.currentResult}",
                     body: "Check Jenkins for build details: ${env.BUILD_URL}"
            }
        }
    }
}
