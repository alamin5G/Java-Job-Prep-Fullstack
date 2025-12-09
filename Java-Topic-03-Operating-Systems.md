# 📌 Topic 3: Operating Systems & Linux

## 🎯 Learning Objectives
Master OS fundamentals and Linux commands - essential for every backend developer. 90% servers run Linux!

---

## 🔷 Part 1: Operating System Fundamentals

### Concepts to Master:

- [ ] **What is an Operating System?**
  ```
  OS = Software that manages hardware and provides services to applications
  
  Main Functions:
  1. Process Management
  2. Memory Management
  3. File System Management
  4. I/O Management
  5. Security & Protection
  ```
  
  **Real-life Example:** 🏢 **Building Manager**
  - OS = Manager
  - Applications = Tenants
  - Hardware = Building resources (electricity, water)
  - Manager allocates resources to tenants

- [ ] **Process vs Thread**
  ```
  Process:
  - Independent program in execution
  - Own memory space
  - Heavy-weight
  - Inter-process communication needed
  
  Thread:
  - Lightweight process
  - Shares memory with parent process
  - Faster creation/switching
  - Direct communication
  ```
  
  **Real-life Example:** 🏭 **Factory**
  - Process = Entire factory
  - Thread = Worker in factory
  - Multiple workers share factory resources
  
  **Interview Question:**
  ```
  Q: Process vs Thread difference?
  A: 
  - Process = Independent execution unit with own memory
  - Thread = Lightweight unit sharing process memory
  - Threads faster to create/switch
  - Example: Browser tabs = processes, JavaScript execution = threads
  ```

- [ ] **Process States**
  ```
  NEW → READY → RUNNING → WAITING → TERMINATED
  
  NEW: Process created
  READY: Waiting for CPU
  RUNNING: Executing on CPU
  WAITING: Waiting for I/O or event
  TERMINATED: Execution completed
  ```
  
  **Real-life Example:** 🍽️ **Restaurant**
  - NEW = Customer enters
  - READY = Waiting for table
  - RUNNING = Eating
  - WAITING = Waiting for food
  - TERMINATED = Customer leaves

- [ ] **CPU Scheduling Algorithms**
  ```
  1. FCFS (First Come First Serve)
     - Simple queue
     - Non-preemptive
     - Convoy effect problem
  
  2. SJF (Shortest Job First)
     - Minimum average waiting time
     - Starvation possible
  
  3. Round Robin
     - Time quantum based
     - Fair allocation
     - Used in time-sharing systems
  
  4. Priority Scheduling
     - Each process has priority
     - Higher priority executes first
     - Aging prevents starvation
  ```
  
  **Real-life Example:** 🏥 **Hospital Queue**
  - FCFS = Normal queue
  - Priority = Emergency patients first
  - Round Robin = Doctor gives 10 mins to each

---

## 🔷 Part 2: Memory Management

### Concepts to Master:

- [ ] **Memory Hierarchy**
  ```
  Fastest & Expensive
  ↓
  Registers (CPU)
  Cache (L1, L2, L3)
  RAM (Main Memory)
  SSD/HDD (Secondary Storage)
  ↓
  Slowest & Cheap
  ```

- [ ] **Virtual Memory**
  ```
  Concept: Use disk as extension of RAM
  
  Benefits:
  - Run programs larger than RAM
  - More processes can run
  - Isolation between processes
  
  Page Fault:
  - Requested page not in RAM
  - Load from disk (slow!)
  - Thrashing: Too many page faults
  ```
  
  **Real-life Example:** 📚 **Library**
  - RAM = Desk (limited space)
  - Disk = Bookshelf
  - Bring books to desk when needed
  - Return when done

- [ ] **Paging vs Segmentation**
  ```
  Paging:
  - Fixed-size blocks (pages)
  - No external fragmentation
  - Internal fragmentation possible
  
  Segmentation:
  - Variable-size blocks (segments)
  - Logical division (code, data, stack)
  - External fragmentation possible
  ```

---

## 🔷 Part 3: Linux Essentials

### Must-Know Linux Commands:

- [ ] **File System Navigation**
  ```bash
  # Print working directory
  pwd
  
  # List files
  ls              # Basic list
  ls -l           # Long format (permissions, size, date)
  ls -la          # Include hidden files
  ls -lh          # Human-readable sizes
  
  # Change directory
  cd /path/to/dir
  cd ..           # Parent directory
  cd ~            # Home directory
  cd -            # Previous directory
  
  # Create directory
  mkdir mydir
  mkdir -p parent/child/grandchild  # Create nested
  
  # Remove directory
  rmdir mydir     # Empty directory only
  rm -r mydir     # Recursive (with contents)
  rm -rf mydir    # Force remove (DANGEROUS!)
  ```
  
  **Real-life Use:** 🗂️ **Project Navigation**
  ```bash
  cd ~/projects/spring-boot-app
  ls -la
  cd src/main/java
  ```

- [ ] **File Operations**
  ```bash
  # Create file
  touch file.txt
  
  # Copy file
  cp source.txt destination.txt
  cp -r folder1 folder2  # Copy directory
  
  # Move/Rename
  mv oldname.txt newname.txt
  mv file.txt /path/to/destination/
  
  # Delete file
  rm file.txt
  rm -f file.txt  # Force delete
  
  # View file content
  cat file.txt           # Entire file
  head file.txt          # First 10 lines
  head -n 20 file.txt    # First 20 lines
  tail file.txt          # Last 10 lines
  tail -f log.txt        # Follow (real-time logs)
  less file.txt          # Paginated view
  
  # Edit file
  nano file.txt          # Simple editor
  vim file.txt           # Advanced editor
  ```
  
  **Real-life Use:** 📝 **Log Monitoring**
  ```bash
  tail -f /var/log/application.log
  ```

- [ ] **File Permissions**
  ```bash
  # View permissions
  ls -l file.txt
  # -rw-r--r-- 1 user group 1234 Jan 1 12:00 file.txt
  # |  |  |
  # |  |  └── Others (read)
  # |  └───── Group (read)
  # └──────── Owner (read, write)
  
  # Permission types
  r = read (4)
  w = write (2)
  x = execute (1)
  
  # Change permissions
  chmod 755 script.sh
  # 7 (owner: rwx) = 4+2+1
  # 5 (group: r-x) = 4+0+1
  # 5 (others: r-x) = 4+0+1
  
  chmod +x script.sh     # Add execute permission
  chmod -w file.txt      # Remove write permission
  
  # Change owner
  chown user:group file.txt
  ```
  
  **Real-life Use:** 🔐 **Secure Files**
  ```bash
  chmod 600 ~/.ssh/id_rsa  # Private key (owner read/write only)
  chmod 755 deploy.sh      # Script (owner all, others read/execute)
  ```

- [ ] **Process Management**
  ```bash
  # List processes
  ps                # Current terminal processes
  ps aux            # All processes
  ps aux | grep java  # Find Java processes
  
  # Real-time process monitor
  top               # Interactive
  htop              # Better UI (if installed)
  
  # Kill process
  kill PID          # Graceful termination
  kill -9 PID       # Force kill
  killall java      # Kill all Java processes
  
  # Background processes
  command &         # Run in background
  nohup command &   # Run even after logout
  
  # Job control
  jobs              # List background jobs
  fg %1             # Bring job 1 to foreground
  bg %1             # Resume job 1 in background
  ```
  
  **Real-life Use:** 🔄 **Manage Spring Boot App**
  ```bash
  # Find running app
  ps aux | grep spring-boot
  
  # Kill if stuck
  kill -9 12345
  
  # Run in background
  nohup java -jar app.jar &
  ```

- [ ] **Text Processing**
  ```bash
  # Search in files
  grep "error" log.txt
  grep -r "TODO" src/     # Recursive search
  grep -i "error" log.txt # Case-insensitive
  grep -n "error" log.txt # Show line numbers
  
  # Find files
  find . -name "*.java"
  find . -type f -name "*.log"
  find . -mtime -7        # Modified in last 7 days
  
  # Word count
  wc file.txt             # Lines, words, characters
  wc -l file.txt          # Line count only
  
  # Sort
  sort file.txt
  sort -r file.txt        # Reverse
  sort -n numbers.txt     # Numeric sort
  
  # Unique lines
  uniq file.txt
  sort file.txt | uniq    # Remove duplicates
  ```
  
  **Real-life Use:** 🔍 **Debug Logs**
  ```bash
  # Find all errors in last hour
  grep "ERROR" app.log | tail -100
  
  # Count error types
  grep "ERROR" app.log | cut -d':' -f2 | sort | uniq -c
  ```

- [ ] **Networking Commands**
  ```bash
  # Check connectivity
  ping google.com
  ping -c 4 google.com    # 4 packets only
  
  # DNS lookup
  nslookup google.com
  dig google.com
  
  # Network interfaces
  ifconfig                # IP addresses
  ip addr show            # Modern alternative
  
  # Port listening
  netstat -tuln           # All listening ports
  netstat -tuln | grep 8080  # Check if port 8080 open
  
  # Download files
  wget https://example.com/file.zip
  curl https://api.example.com/data
  curl -X POST -H "Content-Type: application/json" \
       -d '{"key":"value"}' https://api.example.com/endpoint
  ```
  
  **Real-life Use:** 🌐 **Test API**
  ```bash
  # Test Spring Boot API
  curl http://localhost:8080/api/users
  
  # POST request
  curl -X POST http://localhost:8080/api/users \
       -H "Content-Type: application/json" \
       -d '{"name":"John","email":"john@test.com"}'
  ```

- [ ] **System Information**
  ```bash
  # Disk usage
  df -h               # Disk space
  du -sh folder/      # Folder size
  du -h --max-depth=1 # Size of subdirectories
  
  # Memory usage
  free -h
  
  # CPU info
  lscpu
  cat /proc/cpuinfo
  
  # System uptime
  uptime
  
  # Who is logged in
  who
  w
  ```

- [ ] **Package Management (Ubuntu/Debian)**
  ```bash
  # Update package list
  sudo apt update
  
  # Upgrade packages
  sudo apt upgrade
  
  # Install package
  sudo apt install package-name
  sudo apt install openjdk-17-jdk
  sudo apt install mysql-server
  
  # Remove package
  sudo apt remove package-name
  
  # Search package
  apt search keyword
  ```

---

## 🔷 Part 4: Shell Scripting Basics

### Concepts to Master:

- [ ] **Basic Shell Script**
  ```bash
  #!/bin/bash
  # This is a comment
  
  # Variables
  NAME="John"
  AGE=25
  
  echo "Hello, $NAME"
  echo "You are $AGE years old"
  
  # User input
  read -p "Enter your name: " USERNAME
  echo "Welcome, $USERNAME"
  
  # Arithmetic
  NUM1=10
  NUM2=5
  SUM=$((NUM1 + NUM2))
  echo "Sum: $SUM"
  ```

- [ ] **Conditionals**
  ```bash
  #!/bin/bash
  
  # If-else
  AGE=20
  
  if [ $AGE -ge 18 ]; then
      echo "Adult"
  else
      echo "Minor"
  fi
  
  # File checks
  FILE="test.txt"
  
  if [ -f $FILE ]; then
      echo "File exists"
  else
      echo "File does not exist"
  fi
  
  # String comparison
  NAME="John"
  
  if [ "$NAME" == "John" ]; then
      echo "Hello John"
  fi
  ```

- [ ] **Loops**
  ```bash
  #!/bin/bash
  
  # For loop
  for i in 1 2 3 4 5; do
      echo "Number: $i"
  done
  
  # Range
  for i in {1..10}; do
      echo $i
  done
  
  # While loop
  COUNT=1
  while [ $COUNT -le 5 ]; do
      echo "Count: $COUNT"
      COUNT=$((COUNT + 1))
  done
  
  # Loop through files
  for file in *.txt; do
      echo "Processing: $file"
  done
  ```

- [ ] **Practical Script: Deploy Spring Boot**
  ```bash
  #!/bin/bash
  
  # Deploy Spring Boot application
  
  APP_NAME="my-spring-app"
  JAR_FILE="target/$APP_NAME.jar"
  PID_FILE="/var/run/$APP_NAME.pid"
  
  # Stop existing process
  if [ -f $PID_FILE ]; then
      PID=$(cat $PID_FILE)
      echo "Stopping process $PID..."
      kill $PID
      rm $PID_FILE
  fi
  
  # Build application
  echo "Building application..."
  mvn clean package -DskipTests
  
  # Start application
  echo "Starting application..."
  nohup java -jar $JAR_FILE > app.log 2>&1 &
  echo $! > $PID_FILE
  
  echo "Application started with PID: $(cat $PID_FILE)"
  ```

---

## 💻 Practice Tasks

- [ ] **Task 1: File Management**
  - Create project structure using mkdir
  - Copy files between directories
  - Set proper permissions
  - **Skills:** File operations, permissions

- [ ] **Task 2: Log Analysis**
  - Use grep to find errors
  - Count occurrences
  - Extract specific patterns
  - **Skills:** Text processing

- [ ] **Task 3: Process Monitoring**
  - Find Java processes
  - Monitor resource usage
  - Kill stuck processes
  - **Skills:** Process management

- [ ] **Task 4: Deployment Script**
  - Write script to deploy Spring Boot app
  - Include build, stop, start steps
  - Add error handling
  - **Skills:** Shell scripting

---

## ✅ Move-On Criteria

- [ ] ✔ **OS concepts (process, thread, memory) explain করতে পারবেন**
- [ ] ✔ **Linux commands confidently use করতে পারবেন**
- [ ] ✔ **File permissions বুঝবেন এবং set করতে পারবেন**
- [ ] ✔ **Basic shell script লিখতে পারবেন**
- [ ] ✔ **Server এ navigate করতে পারবেন**

---

**Real-World Applications:**
- 🖥️ Server management
- 🚀 Application deployment
- 📊 Log analysis
- 🔧 Troubleshooting
- ⚙️ Automation scripts

**Next:** [System Design Fundamentals →](Java-Topic-06-System-Design.md)
