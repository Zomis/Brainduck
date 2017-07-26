#!/usr/bin/env groovy

@Library('ZomisJenkins')
import net.zomis.jenkins.Duga

node {
    stage('Build') {
        sh './gradlew dist'
        junit 'build/test-results/*.xml'
        new Duga().dugaResult('SUCCESS')
    }
}

