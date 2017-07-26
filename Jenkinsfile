#!/usr/bin/env groovy

@Library('ZomisJenkins')
import net.zomis.jenkins.Duga

node {
    stage('Checkout') {
        checkout scm
    }
    stage('Build') {
        sh './gradlew dist'
    }
    stage('Reports') {
        junit 'build/test-results/*.xml'
        new Duga().dugaResult('SUCCESS')
    }
}

