pipeline {
    agent any
	
	tools{
		gradle 'gradle'
		maven 'maven'
	}
    stages {
        stage('build & test') {
            steps {
                echo 'Source code compilation in progress.....'
                script {
                    if(isUnix()) {
                        echo 'Unix OS'
                        sh './gradlew build'
                    } else {
                        echo 'Windows OS'
                        bat 'gradlew build'
                    }
                }
                echo '.....Source code compilation completed'
            }
        }
		
		stage('sonar') {
            steps {
                echo 'Sonar scan in progress.....'
                withSonarQubeEnv(credentialsId: 'SonarQbToken2', installationName: 'SonarServer') {
                    script {
                        if(isUnix()) {
                            echo 'Unix OS'
                                sh './gradlew sonarqube -Dsonar.projectKey=ejemplo-gradle -Dsonar.projectName=ejemplo-gradle -Dsonar.java.binaries=build'
                        } else {
                            echo 'Windows OS'
                                bat 'gradlew sonarqube -Dsonar.projectKey=ejemplo-gradle Dsonar.projectName=ejemplo-gradle -Dsonar.java.binaries=build'
                        }
                        echo '.....Sonar scan completed'
                    }
                }
            }
        }
    }
 }
