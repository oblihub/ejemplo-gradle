/*
forma de invocación de método call:
def ejecucion = load 'script.groovy'
ejecucion.call()
*/

def buildMaven(){
  
    sh './mvnw clean install'
}

return this;