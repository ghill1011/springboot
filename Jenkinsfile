node {
  def mvnHome
  stage("Preparation") { // for display purposes
    // Get some code from a GitHub repository
    git 'https://github.com/ghill1011/springboot.git'
    mvnHome = tool 'Apache Maven'
  }
  stage('Build') {
    // Run the maven build
    if (isUnix()) {
      sh "'${mvnHome}/bin/mvn' clean package"
    } else {
      bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
    }
  }
  stage("Deploy") {
    pushToCloudFoundry cloudSpace: 'development', credentialsId: 'fed76caa-7a01-4242-844c-1196605f2175', organization: 'Gregg', target: 'https://api.run.pivotal.io'
  }
}
