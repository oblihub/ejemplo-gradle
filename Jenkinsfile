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
        booleanParam(name:'PushToNexus',defaultValue: false, description:'True = Hace Push a Nexus, False = No hara push a Nexus')
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

        stage('build & test') {
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

        stage('sonar') {
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

        stage('run & test') {
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
    }
 }
