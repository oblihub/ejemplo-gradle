/*
forma de invocación de método call:
def ejecucion = load 'script.groovy'
ejecucion.call()
*/

def buildMaven(){
  
    sh './mvnw clean install'
}

def sonarMaven(){

        sh './mvnw clean verify sonar:sonar -Dsonar.projectKey=ejemplo-maven -Dsonar.java.binaries=build'
}



return this;