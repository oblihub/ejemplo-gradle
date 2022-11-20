def mvn_script
def grdl_script

pipeline {
    agent any
	
	tools{
		gradle 'gradle'
		maven 'maven'
	}
    parameters{
        choice(name: 'build_tool', choices: ['maven', 'gradle'], description: 'Permite elegir el tipo de buid')
        booleanParam(name:'SonarCheck',defaultValue: false, description:'Validar codigo en Sonarqube')
        booleanParam(name:'PushToNexus',defaultValue: false, description:'Hacer push a Nexus')
    }

    stages {
        stage('Load Scripts'){
            steps{
                script{
                    mvn_script = load "maven.groovy"
                    grdl_script = load "gradle.groovy"
                }
            }
        }
        stage('build & test (Maven)') {
            when{
                expression{
                    params.build_tool == 'maven'
                }
            }
            steps {
                script {
                        mvn_script.buildMaven()
                }
            }
        }
        stage('build (Gradle)') {
            when{
                expression{
                    params.build_tool == 'gradle'
                }
            }
            steps {
                script {
                        mvn_script.buildGradle()
                }
            }
        }

        stage('sonar') {            
            when{
                expression{
                    params.SonarCheck
                }
            }
            steps {
                echo 'Sonar scan in progress.....'
                withSonarQubeEnv(credentialsId: 'SonarQbToken2', installationName: 'SonarServer') {
                    script {
                        grdl_script.sonarGradle()
                        echo '.....Sonar scan completed'
                    }
                }
            }
        }

        stage('run & test (Gradle)') {
            when{
                expression{
                    params.build_tool == 'gradle'
                }
            }
            steps {
				echo 'Gradle run in progress.....'
                script {
                    grdl_script.runTest()                        
                }
                echo '.....Gradle run completed'
            }
        }

        stage("uploadNexus") {
            when{
                expression{
                    params.PushToNexus
                }
            }
            steps {
                echo 'Uploading to nexus in progress.....'
                script {
					mvn_script.uploadNexus()
				}
			}
        }
    }
 }
