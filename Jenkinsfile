
pipeline {

    agent any

    stages {

        stage('Checkout Code') {

            steps {

                git 'https://github.com/96mahesh/swaglabsrepo.git'

            }

        }

         stage('Run Unit Test Cases') {

            steps {

                 bat "run.bat"

            }

         }

          stage('Publish Test Reports') {

            steps {

                         junit '**/target/surefire-reports/TEST-*.xml'

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
