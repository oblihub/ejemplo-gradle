/*
forma de invocación de método call:
def ejecucion = load 'script.groovy'
ejecucion.call()
*/

def buildMaven(){
  
    sh './mvnw clean install'
}

def nexusDownload(){
    nexusPublisher nexusInstanceId: 'nxs01', nexusRepositoryId: 'devops-usach-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: "${WORKSPACE}/build/DevOpsUsach2020-1.0.0.jar"]], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '1.0.0']]]
}

return this;