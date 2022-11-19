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
    }
 }
