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
      sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
    } else {
      bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
    }
  }
}
