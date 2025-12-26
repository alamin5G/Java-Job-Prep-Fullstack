# 📌 Topic 13: AWS Cloud Basics - Deployment & Services

## 🎯 Learning Objectives

Master essential AWS services to deploy and scale Java applications in the cloud - a mandatory skill for modern developers that AI cannot replace.

---

## 🔷 Part 1: Understanding Cloud Computing

### Concepts to Master:

- [ ] **What is Cloud Computing?**
  ```
  Traditional (On-Premise):
  - Buy physical servers
  - Maintain data center
  - Pay upfront costs
  - Limited scalability
  - Your responsibility for everything
  
  Cloud Computing:
  - Rent virtual servers
  - No data center needed
  - Pay as you go
  - Unlimited scalability
  - Provider handles infrastructure
  ```
  
  **Real-life Example:** 🏠 **Owning vs Renting**
  - On-Premise = Buy a house
  - Cloud = Rent an apartment
  - Flexibility to move
  - Pay only for what you use
  - No maintenance headaches

- [ ] **Why AWS?**
  ```
  Benefits:
  ✅ Market leader (32% market share)
  ✅ 200+ services
  ✅ Global infrastructure (30+ regions)
  ✅ Pay-as-you-go pricing
  ✅ High availability
  ✅ Security & compliance
  ✅ Free tier for learning
  
  Competitors:
  - Azure (Microsoft)
  - Google Cloud Platform (GCP)
  - But AWS is most popular!
  ```

- [ ] **Cloud Service Models**
  ```
  IaaS (Infrastructure as a Service):
  - EC2 (Virtual Servers)
  - You manage: OS, runtime, app
  - AWS manages: Hardware, networking
  
  PaaS (Platform as a Service):
  - Elastic Beanstalk
  - You manage: App code
  - AWS manages: OS, runtime, scaling
  
  SaaS (Software as a Service):
  - Gmail, Salesforce
  - You manage: Nothing (just use it)
  - Provider manages: Everything
  ```
  
  **Real-life Example:** 🍕 **Pizza Analogy**
  - On-Premise = Make pizza at home (buy everything)
  - IaaS = Buy ingredients, make at home (oven provided)
  - PaaS = Pizza kit delivered (just bake)
  - SaaS = Order delivery (just eat)

---

## 🔷 Part 2: EC2 (Elastic Compute Cloud)

### Concepts to Master:

- [ ] **What is EC2?**
  ```
  EC2 = Virtual servers in the cloud
  
  Features:
  - Choose OS (Linux, Windows)
  - Choose instance type (CPU, RAM)
  - Scale up/down as needed
  - Pay per hour/second
  - Start/stop anytime
  ```
  
  **Real-life Example:** 🚗 **Car Rental**
  - EC2 = Rent a car
  - Choose car type (small, SUV, truck)
  - Pay per day
  - Return when done
  - Upgrade/downgrade anytime

- [ ] **Instance Types**
  ```
  t2.micro (Free Tier):
  - 1 vCPU, 1 GB RAM
  - Good for: Learning, small apps
  
  t2.small:
  - 1 vCPU, 2 GB RAM
  - Good for: Small websites
  
  t2.medium:
  - 2 vCPU, 4 GB RAM
  - Good for: Medium apps
  
  m5.large:
  - 2 vCPU, 8 GB RAM
  - Good for: Production apps
  
  c5.xlarge:
  - 4 vCPU, 8 GB RAM
  - Good for: CPU-intensive apps
  ```

- [ ] **Launching EC2 Instance (Step-by-Step)**
  ```
  1. Login to AWS Console
  2. Go to EC2 Dashboard
  3. Click "Launch Instance"
  4. Choose AMI (Amazon Machine Image):
     - Amazon Linux 2
     - Ubuntu Server 22.04
  5. Choose Instance Type:
     - t2.micro (Free Tier)
  6. Configure Security Group:
     - SSH (Port 22) - Your IP
     - HTTP (Port 80) - Anywhere
     - Custom (Port 8080) - Anywhere (for Spring Boot)
  7. Create/Select Key Pair (.pem file)
  8. Launch!
  ```

- [ ] **Connect to EC2**
  ```bash
  # Download .pem file
  # Change permissions
  chmod 400 my-key.pem
  
  # Connect via SSH
  ssh -i "my-key.pem" ec2-user@ec2-xx-xx-xx-xx.compute.amazonaws.com
  
  # You're now inside EC2 instance!
  ```

- [ ] **Deploy Spring Boot App on EC2**
  ```bash
  # 1. Install Java
  sudo yum install java-17-amazon-corretto -y
  java -version
  
  # 2. Upload JAR file (from local machine)
  scp -i "my-key.pem" myapp.jar ec2-user@ec2-xx-xx-xx-xx:/home/ec2-user/
  
  # 3. Run Spring Boot app
  java -jar myapp.jar
  
  # 4. Run in background (nohup)
  nohup java -jar myapp.jar > app.log 2>&1 &
  
  # 5. Check if running
  curl http://localhost:8080/api/health
  
  # 6. Access from browser
  http://ec2-xx-xx-xx-xx.compute.amazonaws.com:8080
  ```
  
  **Real-life Example:** 🏢 **Renting Office Space**
  - EC2 = Office building
  - SSH = Your office key
  - Deploy app = Move in your furniture
  - Run app = Start working

---

## 🔷 Part 3: S3 (Simple Storage Service)

### Concepts to Master:

- [ ] **What is S3?**
  ```
  S3 = Object storage service
  
  Features:
  - Store any type of file
  - Unlimited storage
  - 99.999999999% durability (11 nines!)
  - Pay per GB stored
  - Access from anywhere
  
  Use Cases:
  - Static website hosting
  - File uploads (images, videos)
  - Backup and archiving
  - Data lakes
  ```
  
  **Real-life Example:** 📦 **Warehouse Storage**
  - S3 = Warehouse
  - Buckets = Storage rooms
  - Objects = Boxes/items
  - Pay for space used
  - Retrieve anytime

- [ ] **S3 Concepts**
  ```
  Bucket:
  - Container for objects
  - Globally unique name
  - Example: my-app-uploads
  
  Object:
  - File stored in bucket
  - Has key (filename/path)
  - Example: users/123/profile.jpg
  
  URL:
  - https://my-bucket.s3.amazonaws.com/file.jpg
  ```

- [ ] **Create S3 Bucket**
  ```
  1. Go to S3 Console
  2. Click "Create bucket"
  3. Enter bucket name: my-app-files
  4. Choose region: us-east-1
  5. Uncheck "Block all public access" (if needed)
  6. Create bucket
  ```

- [ ] **Upload File via AWS SDK**
  ```xml
  <!-- pom.xml -->
  <dependency>
      <groupId>com.amazonaws</groupId>
      <artifactId>aws-java-sdk-s3</artifactId>
      <version>1.12.529</version>
  </dependency>
  ```
  
  ```java
  @Service
  public class S3Service {
      
      private final AmazonS3 s3Client;
      private final String bucketName = "my-app-files";
      
      public S3Service() {
          this.s3Client = AmazonS3ClientBuilder
              .standard()
              .withRegion("us-east-1")
              .build();
      }
      
      // Upload file
      public String uploadFile(MultipartFile file) {
          String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
          
          try {
              ObjectMetadata metadata = new ObjectMetadata();
              metadata.setContentLength(file.getSize());
              metadata.setContentType(file.getContentType());
              
              s3Client.putObject(
                  bucketName,
                  fileName,
                  file.getInputStream(),
                  metadata
              );
              
              // Return file URL
              return s3Client.getUrl(bucketName, fileName).toString();
              
          } catch (IOException e) {
              throw new RuntimeException("Failed to upload file", e);
          }
      }
      
      // Download file
      public byte[] downloadFile(String fileName) {
          S3Object s3Object = s3Client.getObject(bucketName, fileName);
          try {
              return s3Object.getObjectContent().readAllBytes();
          } catch (IOException e) {
              throw new RuntimeException("Failed to download file", e);
          }
      }
      
      // Delete file
      public void deleteFile(String fileName) {
          s3Client.deleteObject(bucketName, fileName);
      }
  }
  
  // Controller
  @RestController
  @RequestMapping("/api/files")
  public class FileController {
      
      @Autowired
      private S3Service s3Service;
      
      @PostMapping("/upload")
      public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
          String fileUrl = s3Service.uploadFile(file);
          return ResponseEntity.ok(fileUrl);
      }
      
      @GetMapping("/download/{fileName}")
      public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
          byte[] data = s3Service.downloadFile(fileName);
          return ResponseEntity.ok()
              .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
              .body(data);
      }
  }
  ```

---

## 🔷 Part 4: RDS (Relational Database Service)

### Concepts to Master:

- [ ] **What is RDS?**
  ```
  RDS = Managed database service
  
  Supported Databases:
  - MySQL
  - PostgreSQL
  - MariaDB
  - Oracle
  - SQL Server
  
  Benefits:
  ✅ Automatic backups
  ✅ Auto scaling
  ✅ High availability
  ✅ Security patches automatic
  ✅ No server management
  ```
  
  **Real-life Example:** 🏦 **Bank Vault Service**
  - RDS = Professional vault service
  - You = Store valuables
  - Service = Handles security, backups
  - You focus on business, not vault maintenance

- [ ] **Create RDS Instance**
  ```
  1. Go to RDS Console
  2. Click "Create database"
  3. Choose engine: MySQL
  4. Choose template: Free tier
  5. Settings:
     - DB instance identifier: myapp-db
     - Master username: admin
     - Master password: ********
  6. Instance configuration:
     - db.t3.micro (Free tier)
  7. Storage: 20 GB
  8. Connectivity:
     - Public access: Yes (for learning)
     - Security group: Allow MySQL (3306)
  9. Create database
  ```

- [ ] **Connect Spring Boot to RDS**
  ```properties
  # application.properties
  spring.datasource.url=jdbc:mysql://myapp-db.xxxxxx.us-east-1.rds.amazonaws.com:3306/mydb
  spring.datasource.username=admin
  spring.datasource.password=your-password
  spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
  
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.show-sql=true
  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
  ```
  
  **Security Best Practice:**
  ```properties
  # Use environment variables
  spring.datasource.url=${DB_URL}
  spring.datasource.username=${DB_USERNAME}
  spring.datasource.password=${DB_PASSWORD}
  ```

---

## 🔷 Part 5: Elastic Beanstalk (Easy Deployment)

### Concepts to Master:

- [ ] **What is Elastic Beanstalk?**
  ```
  Elastic Beanstalk = PaaS for easy deployment
  
  You provide:
  - JAR file
  
  AWS handles:
  - EC2 instances
  - Load balancer
  - Auto-scaling
  - Monitoring
  - Health checks
  ```
  
  **Real-life Example:** 🍕 **Pizza Delivery Service**
  - You = Make pizza (write code)
  - Beanstalk = Delivery service (handles deployment)
  - Customers get pizza (users access app)
  - You don't worry about delivery logistics

- [ ] **Deploy Spring Boot to Beanstalk**
  ```bash
  # 1. Build JAR file
  mvn clean package
  
  # 2. Go to Elastic Beanstalk Console
  # 3. Create Application
  #    - Application name: my-spring-app
  #    - Platform: Java 17 (Corretto)
  #    - Upload JAR file
  # 4. Configure environment
  #    - Environment name: myapp-prod
  #    - Instance type: t2.micro
  # 5. Create environment
  
  # AWS will:
  # - Create EC2 instance
  # - Install Java
  # - Deploy your JAR
  # - Setup load balancer
  # - Configure auto-scaling
  
  # 6. Access your app
  # http://myapp-prod.us-east-1.elasticbeanstalk.com
  ```

- [ ] **Environment Variables in Beanstalk**
  ```
  1. Go to Configuration
  2. Software → Edit
  3. Environment properties:
     DB_URL=jdbc:mysql://...
     DB_USERNAME=admin
     DB_PASSWORD=secret
     JWT_SECRET=mykey
  4. Apply
  ```

---

## 💻 Practice Project: Deploy Full-Stack App to AWS

### Project Architecture:
```
                    ┌─────────────────┐
                    │   CloudFront    │ (CDN)
                    │   (Frontend)    │
                    └────────┬────────┘
                             │
                    ┌────────▼────────┐
                    │  Load Balancer  │
                    └────────┬────────┘
                             │
              ┌──────────────┼──────────────┐
              │              │              │
       ┌──────▼─────┐ ┌─────▼──────┐ ┌────▼──────┐
       │   EC2-1    │ │   EC2-2    │ │   EC2-3   │
       │ (Backend)  │ │ (Backend)  │ │ (Backend) │
       └────────────┘ └────────────┘ └───────────┘
              │              │              │
              └──────────────┼──────────────┘
                             │
                    ┌────────▼────────┐
                    │   RDS MySQL     │
                    │   (Database)    │
                    └─────────────────┘
                             │
                    ┌────────▼────────┐
                    │   S3 Bucket     │
                    │  (File Storage) │
                    └─────────────────┘
```

### Implementation Steps:

- [ ] **Step 1: Setup Database**
  - Create RDS MySQL instance
  - Configure security group
  - Test connection

- [ ] **Step 2: Setup File Storage**
  - Create S3 bucket
  - Configure CORS
  - Setup IAM permissions

- [ ] **Step 3: Deploy Backend**
  - Build Spring Boot JAR
  - Deploy to Elastic Beanstalk
  - Configure environment variables
  - Test API endpoints

- [ ] **Step 4: Deploy Frontend**
  - Build React app
  - Upload to S3
  - Enable static website hosting
  - Configure CloudFront (CDN)

- [ ] **Step 5: Configure Domain**
  - Buy domain (Route 53)
  - Point to CloudFront
  - Setup SSL certificate

**Duration:** 1 week
**Skills:** AWS, Deployment, DevOps

---

## 🎯 Interview Questions & Answers

### Q1: What is the difference between EC2 and Elastic Beanstalk?

**Answer:**
```
EC2 (IaaS):
- Full control over server
- You manage: OS, updates, scaling
- More flexible
- More responsibility
- Example: Custom server setup

Elastic Beanstalk (PaaS):
- Upload code, AWS handles rest
- AWS manages: Servers, scaling, monitoring
- Less flexible
- Less responsibility
- Example: Quick deployment

When to use:
- EC2: Need full control, custom setup
- Beanstalk: Quick deployment, standard app
```

### Q2: Explain S3 storage classes.

**Answer:**
```
S3 Standard:
- Frequent access
- High availability
- Most expensive
- Use: Active data

S3 Intelligent-Tiering:
- Auto-moves between tiers
- Cost-optimized
- Use: Unknown access patterns

S3 Glacier:
- Archive storage
- Retrieval takes minutes/hours
- Very cheap
- Use: Backups, compliance

Example:
- User uploads → S3 Standard
- After 30 days → Intelligent-Tiering
- After 90 days → Glacier
```

### Q3: How do you secure your AWS resources?

**Answer:**
```
1. IAM (Identity and Access Management):
   - Create users with minimal permissions
   - Use roles instead of access keys
   - Enable MFA

2. Security Groups:
   - Firewall for EC2
   - Allow only necessary ports
   - Restrict by IP

3. VPC (Virtual Private Cloud):
   - Isolate resources
   - Private subnets for databases
   - Public subnets for web servers

4. Encryption:
   - S3: Server-side encryption
   - RDS: Encrypt at rest
   - SSL/TLS for data in transit

5. Secrets Manager:
   - Store passwords, API keys
   - Rotate automatically
   - Never hardcode secrets
```

---

## ✅ Move-On Criteria

- [ ] ✔ **Cloud computing concepts explain করতে পারবেন**
- [ ] ✔ **EC2 instance launch এবং connect করতে পারবেন**
- [ ] ✔ **S3 bucket create এবং file upload করতে পারবেন**
- [ ] ✔ **RDS database setup করতে পারবেন**
- [ ] ✔ **Elastic Beanstalk এ app deploy করতে পারবেন**
- [ ] ✔ **AWS security best practices জানবেন**
- [ ] ✔ **Complete app deploy করতে পারবেন AWS তে**
- [ ] ✔ **Interview questions confidently answer করতে পারবেন**

---

**Real-World Applications:**
- 🛒 E-commerce platforms
- 📱 Mobile app backends
- 🎬 Video streaming services
- 🏦 Banking applications
- 📊 Analytics dashboards

**Next:** [CI/CD with Jenkins →](Java-Topic-14-CICD.md)
