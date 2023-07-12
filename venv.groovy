#!groovy

@Library('insights-cpt-ci-configs') _

// Create MARKER variable that will be common for all sub-builds
def MARKER = """${ new Date().format("yyyy-MM-dd'T'HH:mm:ss").toString().replaceAll('[^a-zA-Z0-9-]', '_') }"""
def STATUS_DATA_FILE = """status-data-${MARKER}.json"""


pipeline {
    agent {
        kubernetes {
            inheritFrom "cpt-v2"
            defaultContainer "compute"
            label "cpt-v2"
        }
    }

    environment {
        MARKER = "${MARKER}"
        STATUS_DATA_FILE = "${STATUS_DATA_FILE}"
    }

    stages {
        stage('Setup') {
            steps {
               script {
                        globalHelpers.helperCreateVenv()
                    }
            }
        }
        stage('Pausing') {
            steps {
                sleep(time: 2, unit: 'MINUTES')
            }
        }
    }
}
