def mvn_script
def grdl_script

pipeline {
    agent any
	
	tools{
		gradle 'gradle'
		maven 'maven'
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
    }
 }
