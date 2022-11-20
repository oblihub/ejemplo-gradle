plugins {
    id 'groovy'
}

sourceSets {
    main {
        groovy {
            srcDirs = ['src', 'vars']
        }
        resources {
            srcDirs = ['resources']
        }
    }
}

repositories {
    mavenCentral()
    mavenLocal()
    maven {
        url "https://repo.jenkins-ci.org/releases/"
    }
}

dependencies {
    implementation group: 'com.hynnet', name: 'json-lib', version: '2.4'
    implementation group: 'org.apache.commons', name: 'commons-lang3', version: '3.0'
    implementation group: 'org.eclipse.hudson', name: 'hudson-core', version: '3.3.3', { exclude group: 'org.eclipse.hudson', module: 'hudson-remoting' }
    implementation group: 'javax.servlet', name: 'javax.servlet-api', version: '3.0.1'
    implementation group: 'com.cloudbees', name: 'groovy-cps', version: '1.31'
    implementation group: 'org.jenkins-ci.plugins', name: 'job-dsl-core', version: '1.77'
    implementation group: 'org.codehaus.groovy', name: 'groovy-all', version: '3.0.6'

    testImplementation group: 'com.lesfurets', name: 'jenkins-pipeline-unit', version: '1.7'
    testImplementation group: 'org.jenkins-ci.plugins.workflow', name: 'workflow-job', version: '2.40'
    testImplementation group: 'junit', name: 'junit', version: '4.13'
}
