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
						sh './mvnw clean install'
                        sh './gradlew build'
                    } else {
                        echo 'Windows OS'
                        bat 'mvn clean install'
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
		
		stage('run & test') {
            steps {
				echo 'Gradle run in progress.....'
                script {
                        if(isUnix()) {
                            echo 'Unix OS'
                                sh './gradlew bootrun &'
								sh 'sleep 5'
								sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'"
                        } else {
							echo 'Windows OS'
								bat 'gradlew bootrun &'
								sh 'sleep 5'
								sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'"
                        }
                        echo '.....Gradle run completed'
                    }
            }
        }
		
		stage("uploadNexus") {
            steps {
                echo 'Uploading to nexus in progress.....'
                script {
						nexusPublisher nexusInstanceId: 'nxs01', nexusRepositoryId: 'devops-usach-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: "${WORKSPACE}/build/DevOpsUsach2020-1.0.0.jar"]], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '1.0.0']]]
				}
			}
        }
    }
 }
