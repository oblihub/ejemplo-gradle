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
            when{
                expression{
                    params.build_tool == 'gradle'
                }
            }
            steps {
                script {
                    grdl_script.buildGradle()
                }

            }
        }

    }
 }
