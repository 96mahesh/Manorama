
pipeline {

    agent any

    stages {

        stage('Checkout Code') {

            steps {

                git 'https://github.com/96mahesh/Manorama.git'

            }

        }

         stage('Run Unit Test Cases') {

            steps {

                 bat "run.bat"

            }

         }

          stage('Publish Test Reports') {

            steps {

                        allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
               publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'test-output', reportFiles: 'C:\\Users\\Admin\\Manorama_workSpace\\manorama\\test-output\\emailable-report.html', reportName: 'HTML Report', reportTitles: '', useWrapperFileDirectly: true])
                testNG()
                mail bcc: 'mahirampo@gmail.com', body: '''Hi dear, Good morning

I am sending my email reports through pipeline could you please check it once, If any quations let me know


Thanks
Mahesh
7842358565''', cc: 'rampatrunimaheshbabu@gmail.com', from: '', replyTo: '', subject: 'Sending email reports', to: 'maheshrampatruni@gmail.com'

            }

          }

           stage('Build Code') {

            steps {

                bat "mvn -Dmaven.test.failure.ignore=true clean package"

            }

           }

            stage('Archive Results') {

            steps {

                     archiveArtifacts 'target/*.war'

            }

            }

    }

}
