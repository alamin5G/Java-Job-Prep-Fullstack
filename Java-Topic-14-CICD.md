# 📌 Topic 14: CI/CD - Continuous Integration & Deployment

## 🎯 Learning Objectives

Master CI/CD pipelines to automate testing and deployment - a critical DevOps skill that separates junior from senior developers and is highly valued by employers.

---

## 🔷 Part 1: Understanding CI/CD

### Concepts to Master:

- [ ] **What is CI/CD?**
  ```
  Without CI/CD (Manual):
  - Developer writes code
  - Manually runs tests
  - Manually builds JAR
  - Manually uploads to server
  - Manually restarts server
  - Repeat for every change!
  - Error-prone, time-consuming
  
  With CI/CD (Automated):
  - Developer pushes code to Git
  - Automatic testing
  - Automatic building
  - Automatic deployment
  - Automatic notifications
  - Fast, reliable, repeatable
  ```
  
  **Real-life Example:** 🏭 **Factory Assembly Line**
  - Manual = Hand-crafting each product
  - CI/CD = Automated assembly line
  - Consistent quality
  - Faster production
  - Less human error

- [ ] **CI vs CD**
  ```
  Continuous Integration (CI):
  - Merge code frequently (daily)
  - Run automated tests
  - Build application
  - Catch bugs early
  
  Continuous Deployment (CD):
  - Automatically deploy to production
  - After tests pass
  - No manual intervention
  - Fast releases
  
  Continuous Delivery (CD):
  - Ready to deploy anytime
  - Manual approval for production
  - More control
  ```
  
  **Real-life Example:** 📦 **Package Delivery**
  - CI = Quality check at warehouse
  - Continuous Delivery = Package ready, awaiting approval
  - Continuous Deployment = Auto-delivery without approval

- [ ] **Benefits of CI/CD**
  ```
  ✅ Faster releases (daily vs monthly)
  ✅ Fewer bugs (automated testing)
  ✅ Consistent deployments
  ✅ Quick rollbacks
  ✅ Developer productivity
  ✅ Better collaboration
  ✅ Reduced risk
  ```

---

## 🔷 Part 2: Jenkins Basics

### Concepts to Master:

- [ ] **What is Jenkins?**
  ```
  Jenkins = Open-source automation server
  
  Features:
  - Build automation
  - Test automation
  - Deployment automation
  - 1000+ plugins
  - Free and popular
  ```
  
  **Real-life Example:** 🤖 **Personal Assistant**
  - Jenkins = Your assistant
  - You = Give instructions (pipeline)
  - Assistant = Executes tasks automatically
  - You focus on coding, not deployment

- [ ] **Install Jenkins**
  ```bash
  # Method 1: Docker (Easiest)
  docker run -p 8080:8080 -p 50000:50000 \
    -v jenkins_home:/var/jenkins_home \
    jenkins/jenkins:lts
  
  # Method 2: Download WAR file
  wget http://mirrors.jenkins.io/war-stable/latest/jenkins.war
  java -jar jenkins.war --httpPort=8080
  
  # Access: http://localhost:8080
  # Initial password: cat /var/jenkins_home/secrets/initialAdminPassword
  ```

- [ ] **Jenkins Setup**
  ```
  1. Access http://localhost:8080
  2. Enter initial admin password
  3. Install suggested plugins
  4. Create admin user
  5. Configure Jenkins URL
  6. Start using Jenkins!
  ```

- [ ] **Install Required Plugins**
  ```
  1. Manage Jenkins → Plugins
  2. Install:
     - Git Plugin
     - Maven Integration
     - Pipeline
     - Docker Pipeline
     - Email Extension
     - Slack Notification
  ```

---

## 🔷 Part 3: Creating Jenkins Pipeline

### Concepts to Master:

- [ ] **Freestyle Project (Simple)**
  ```
  1. New Item → Freestyle Project
  2. Source Code Management:
     - Git
     - Repository URL: https://github.com/user/repo
     - Branch: main
  3. Build Triggers:
     - Poll SCM: H/5 * * * * (every 5 mins)
     - OR GitHub webhook
  4. Build Steps:
     - Execute shell:
       mvn clean package
  5. Post-build Actions:
     - Archive artifacts: target/*.jar
     - Email notification
  6. Save and Build Now
  ```

- [ ] **Pipeline as Code (Jenkinsfile)**
  ```groovy
  // Jenkinsfile (in project root)
  pipeline {
      agent any
      
      tools {
          maven 'Maven 3.8.6'
          jdk 'JDK 17'
      }
      
      stages {
          stage('Checkout') {
              steps {
                  git branch: 'main',
                      url: 'https://github.com/user/repo.git'
              }
          }
          
          stage('Build') {
              steps {
                  sh 'mvn clean compile'
              }
          }
          
          stage('Test') {
              steps {
                  sh 'mvn test'
              }
              post {
                  always {
                      junit 'target/surefire-reports/*.xml'
                  }
              }
          }
          
          stage('Package') {
              steps {
                  sh 'mvn package -DskipTests'
              }
          }
          
          stage('Archive') {
              steps {
                  archiveArtifacts artifacts: 'target/*.jar',
                                   fingerprint: true
              }
          }
      }
      
      post {
          success {
              echo 'Build successful!'
              // Send notification
          }
          failure {
              echo 'Build failed!'
              // Send alert
          }
      }
  }
  ```

- [ ] **Advanced Pipeline with Docker**
  ```groovy
  pipeline {
      agent any
      
      environment {
          DOCKER_IMAGE = 'myapp'
          DOCKER_TAG = "${env.BUILD_NUMBER}"
      }
      
      stages {
          stage('Checkout') {
              steps {
                  checkout scm
              }
          }
          
          stage('Build JAR') {
              steps {
                  sh 'mvn clean package -DskipTests'
              }
          }
          
          stage('Run Tests') {
              steps {
                  sh 'mvn test'
              }
          }
          
          stage('Build Docker Image') {
              steps {
                  script {
                      docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                  }
              }
          }
          
          stage('Push to Registry') {
              steps {
                  script {
                      docker.withRegistry('https://registry.hub.docker.com', 'docker-credentials') {
                          docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").push()
                          docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").push('latest')
                      }
                  }
              }
          }
          
          stage('Deploy to Server') {
              steps {
                  sh '''
                      ssh user@server "docker pull ${DOCKER_IMAGE}:${DOCKER_TAG} && \
                                       docker stop myapp || true && \
                                       docker rm myapp || true && \
                                       docker run -d --name myapp -p 8080:8080 ${DOCKER_IMAGE}:${DOCKER_TAG}"
                  '''
              }
          }
      }
      
      post {
          success {
              slackSend color: 'good',
                        message: "Build #${env.BUILD_NUMBER} succeeded!"
          }
          failure {
              slackSend color: 'danger',
                        message: "Build #${env.BUILD_NUMBER} failed!"
          }
      }
  }
  ```

---

## 🔷 Part 4: GitHub Actions (Alternative to Jenkins)

### Concepts to Master:

- [ ] **What is GitHub Actions?**
  ```
  GitHub Actions = CI/CD built into GitHub
  
  Benefits:
  ✅ No server setup needed
  ✅ Free for public repos
  ✅ Integrated with GitHub
  ✅ Easy to use
  ✅ Marketplace of actions
  
  vs Jenkins:
  - Jenkins: More powerful, self-hosted
  - GitHub Actions: Easier, cloud-based
  ```

- [ ] **Basic Workflow**
  ```yaml
  # .github/workflows/ci.yml
  name: Java CI with Maven
  
  on:
    push:
      branches: [ main ]
    pull_request:
      branches: [ main ]
  
  jobs:
    build:
      runs-on: ubuntu-latest
      
      steps:
      - uses: actions/checkout@v3
      
      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'
      
      - name: Build with Maven
        run: mvn clean package
      
      - name: Run tests
        run: mvn test
      
      - name: Upload JAR
        uses: actions/upload-artifact@v3
        with:
          name: app-jar
          path: target/*.jar
  ```

- [ ] **Deploy to AWS**
  ```yaml
  # .github/workflows/deploy.yml
  name: Deploy to AWS
  
  on:
    push:
      branches: [ main ]
  
  jobs:
    deploy:
      runs-on: ubuntu-latest
      
      steps:
      - uses: actions/checkout@v3
      
      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
      
      - name: Build JAR
        run: mvn clean package -DskipTests
      
      - name: Deploy to Elastic Beanstalk
        uses: einaregilsson/beanstalk-deploy@v21
        with:
          aws_access_key: ${{ secrets.AWS_ACCESS_KEY }}
          aws_secret_key: ${{ secrets.AWS_SECRET_KEY }}
          application_name: my-app
          environment_name: my-app-prod
          version_label: ${{ github.sha }}
          region: us-east-1
          deployment_package: target/myapp.jar
  ```

- [ ] **Docker Build and Push**
  ```yaml
  name: Docker Build and Push
  
  on:
    push:
      branches: [ main ]
  
  jobs:
    build:
      runs-on: ubuntu-latest
      
      steps:
      - uses: actions/checkout@v3
      
      - name: Build JAR
        run: mvn clean package -DskipTests
      
      - name: Login to DockerHub
        uses: docker/login-action@v2
        with:
          username: ${{ secrets.DOCKER_USERNAME }}
          password: ${{ secrets.DOCKER_PASSWORD }}
      
      - name: Build and Push Docker Image
        uses: docker/build-push-action@v4
        with:
          context: .
          push: true
          tags: |
            myusername/myapp:latest
            myusername/myapp:${{ github.sha }}
  ```

---

## 🔷 Part 5: Best Practices

### Concepts to Master:

- [ ] **Pipeline Best Practices**
  ```
  1. Keep builds fast:
     - Parallel execution
     - Cache dependencies
     - Skip unnecessary steps
  
  2. Fail fast:
     - Run tests early
     - Stop on first failure
     - Quick feedback
  
  3. Security:
     - Use secrets management
     - Never hardcode credentials
     - Scan for vulnerabilities
  
  4. Notifications:
     - Slack/Email on failure
     - Track build metrics
     - Monitor pipeline health
  
  5. Rollback strategy:
     - Keep previous versions
     - Quick rollback mechanism
     - Test rollback process
  ```

- [ ] **Deployment Strategies**
  ```
  Blue-Green Deployment:
  - Two identical environments
  - Deploy to inactive (green)
  - Switch traffic to green
  - Keep blue as backup
  
  Canary Deployment:
  - Deploy to small subset (5%)
  - Monitor metrics
  - Gradually increase (25%, 50%, 100%)
  - Rollback if issues
  
  Rolling Deployment:
  - Update servers one by one
  - Always some servers running
  - No downtime
  - Slower but safer
  ```

---

## 💻 Practice Project: Complete CI/CD Pipeline

### Project: E-Commerce Backend with Full CI/CD

**Architecture:**
```
GitHub → Jenkins → Build → Test → Docker → AWS
```

### Implementation Steps:

- [ ] **Step 1: Setup Jenkins**
  - Install Jenkins
  - Configure plugins
  - Setup credentials

- [ ] **Step 2: Create Jenkinsfile**
  - Checkout code
  - Build with Maven
  - Run unit tests
  - Build Docker image
  - Push to Docker Hub

- [ ] **Step 3: Setup AWS**
  - Create EC2 instance
  - Install Docker
  - Configure security groups

- [ ] **Step 4: Deploy Pipeline**
  - SSH to EC2
  - Pull Docker image
  - Run container
  - Health check

- [ ] **Step 5: Add Monitoring**
  - Slack notifications
  - Email alerts
  - Build metrics

**Duration:** 1 week
**Skills:** Jenkins, Docker, AWS, DevOps

---

## 🎯 Interview Questions & Answers

### Q1: What is the difference between CI and CD?

**Answer:**
```
Continuous Integration (CI):
- Merge code frequently
- Automated testing
- Automated building
- Catch bugs early
- Example: Every commit triggers build + tests

Continuous Deployment (CD):
- Automated deployment to production
- After tests pass
- No manual approval
- Example: Code → Test → Production (automatic)

Continuous Delivery (CD):
- Ready to deploy anytime
- Manual approval for production
- More control
- Example: Code → Test → Staging → [Approve] → Production

Real Example:
- CI: Code review + automated tests
- Delivery: Ready to release, awaiting approval
- Deployment: Auto-release to production
```

### Q2: Explain a typical CI/CD pipeline.

**Answer:**
```
Pipeline Stages:

1. Source:
   - Developer pushes code to Git
   - Webhook triggers pipeline

2. Build:
   - Checkout code
   - Compile code (mvn compile)
   - Build JAR (mvn package)

3. Test:
   - Unit tests (mvn test)
   - Integration tests
   - Code coverage check

4. Quality:
   - SonarQube analysis
   - Security scan
   - Code style check

5. Package:
   - Build Docker image
   - Push to registry

6. Deploy:
   - Deploy to staging
   - Run smoke tests
   - Deploy to production

7. Monitor:
   - Health checks
   - Performance monitoring
   - Alert on issues

Real Example: E-commerce app
- Push code → Build → Test → Docker → AWS → Monitor
```

### Q3: How do you handle failed deployments?

**Answer:**
```
Strategies:

1. Automated Rollback:
   - Keep previous version
   - Health check fails → Auto-rollback
   - Example: Deploy v2, fails → Revert to v1

2. Blue-Green Deployment:
   - Deploy to green environment
   - Test green
   - Switch traffic to green
   - Keep blue as backup

3. Feature Flags:
   - Deploy code but disable feature
   - Enable gradually
   - Disable if issues

4. Monitoring:
   - Track error rates
   - Monitor performance
   - Alert on anomalies

Real Example:
- Deploy new payment feature
- Error rate increases
- Auto-rollback triggered
- Team investigates issue
- Fix and redeploy
```

---

## ✅ Move-On Criteria

- [ ] ✔ **CI/CD concepts explain করতে পারবেন**
- [ ] ✔ **Jenkins install এবং configure করতে পারবেন**
- [ ] ✔ **Jenkinsfile লিখতে পারবেন**
- [ ] ✔ **GitHub Actions workflow তৈরি করতে পারবেন**
- [ ] ✔ **Docker build এবং push করতে পারবেন**
- [ ] ✔ **AWS তে deploy করতে পারবেন**
- [ ] ✔ **Complete pipeline setup করতে পারবেন**
- [ ] ✔ **Interview questions confidently answer করতে পারবেন**

---

**Real-World Applications:**
- 🚀 Startup rapid releases
- 🏢 Enterprise deployments
- 📱 Mobile app backends
- 🎮 Game servers
- 💳 Payment systems

**Next:** [Monitoring & Logging →](Java-Topic-15-Monitoring.md)
