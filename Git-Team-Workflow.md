# 📌 GitHub Team Collaboration Workflow

## 🎯 Learning Objectives
Master Git and GitHub for professional team collaboration - essential skill for any developer job.

---

## 🔷 Part 1: Git Branching Strategies

### Concepts to Master:

- [ ] **Git Flow**
  ```
  Branches:
  - main (production)
  - develop (integration)
  - feature/* (new features)
  - release/* (release preparation)
  - hotfix/* (urgent fixes)
  
  Workflow:
  1. Create feature branch from develop
  2. Work on feature
  3. Merge back to develop
  4. Create release branch
  5. Test and merge to main
  6. Tag release
  ```
  
  **Commands:**
  ```bash
  # Start new feature
  git checkout develop
  git pull origin develop
  git checkout -b feature/user-authentication
  
  # Work on feature
  git add .
  git commit -m "Add login functionality"
  git push origin feature/user-authentication
  
  # Merge to develop
  git checkout develop
  git merge feature/user-authentication
  git push origin develop
  
  # Delete feature branch
  git branch -d feature/user-authentication
  git push origin --delete feature/user-authentication
  ```

- [ ] **GitHub Flow (Simpler)**
  ```
  Branches:
  - main (always deployable)
  - feature/* (short-lived)
  
  Workflow:
  1. Create feature branch from main
  2. Make changes and commit
  3. Open Pull Request
  4. Code review
  5. Merge to main
  6. Deploy
  ```
  
  **Best for:** Small teams, continuous deployment

- [ ] **Trunk-Based Development**
  ```
  - Single main branch
  - Short-lived feature branches (< 1 day)
  - Frequent merges
  - Feature flags for incomplete features
  ```

---

## 🔷 Part 2: Pull Requests & Code Review

### Concepts to Master:

- [ ] **Creating Pull Request**
  ```bash
  # 1. Create and switch to feature branch
  git checkout -b feature/add-payment
  
  # 2. Make changes
  # ... edit files ...
  
  # 3. Commit changes
  git add .
  git commit -m "Add payment gateway integration"
  
  # 4. Push to remote
  git push origin feature/add-payment
  
  # 5. Go to GitHub and create PR
  # - Add title and description
  # - Request reviewers
  # - Link related issues
  ```

- [ ] **Good PR Description Template**
  ```markdown
  ## Description
  Added Stripe payment gateway integration for checkout process.
  
  ## Changes Made
  - Created PaymentService class
  - Added Stripe API integration
  - Implemented payment webhook handler
  - Added unit tests
  
  ## Testing
  - [ ] Unit tests pass
  - [ ] Integration tests pass
  - [ ] Manually tested with test card
  
  ## Screenshots
  [Add screenshots if UI changes]
  
  ## Related Issues
  Closes #123
  
  ## Checklist
  - [ ] Code follows style guidelines
  - [ ] Self-reviewed code
  - [ ] Commented complex code
  - [ ] Updated documentation
  - [ ] No new warnings
  ```

- [ ] **Code Review Best Practices**
  ```
  As Reviewer:
  ✅ Be respectful and constructive
  ✅ Explain why, not just what
  ✅ Suggest improvements
  ✅ Approve if looks good
  ✅ Request changes if issues found
  
  As Author:
  ✅ Respond to all comments
  ✅ Don't take feedback personally
  ✅ Ask questions if unclear
  ✅ Make requested changes
  ✅ Thank reviewers
  
  Review Checklist:
  - Code quality and readability
  - Follows coding standards
  - Tests included
  - No security vulnerabilities
  - Performance considerations
  - Documentation updated
  ```

---

## 🔷 Part 3: Merge Conflicts Resolution

### Concepts to Master:

- [ ] **Understanding Merge Conflicts**
  ```
  Conflict occurs when:
  - Same file modified in both branches
  - Same lines changed differently
  - Git can't auto-merge
  ```

- [ ] **Resolving Conflicts**
  ```bash
  # 1. Try to merge
  git checkout main
  git merge feature/my-feature
  # CONFLICT (content): Merge conflict in file.txt
  
  # 2. Check conflicted files
  git status
  
  # 3. Open conflicted file
  # You'll see:
  <<<<<<< HEAD
  Code from main branch
  =======
  Code from feature branch
  >>>>>>> feature/my-feature
  
  # 4. Edit file to resolve
  # Remove conflict markers
  # Keep desired code
  
  # 5. Mark as resolved
  git add file.txt
  
  # 6. Complete merge
  git commit -m "Merge feature/my-feature, resolved conflicts"
  ```

- [ ] **Preventing Conflicts**
  ```bash
  # Keep feature branch updated
  git checkout feature/my-feature
  git pull origin main  # Or rebase
  
  # Communicate with team
  # - Coordinate on same files
  # - Small, frequent commits
  # - Short-lived branches
  ```

---

## 🔷 Part 4: Advanced Git Commands

### Concepts to Master:

- [ ] **Rebase vs Merge**
  ```bash
  # Merge (creates merge commit)
  git checkout main
  git merge feature/my-feature
  
  # Rebase (linear history)
  git checkout feature/my-feature
  git rebase main
  git checkout main
  git merge feature/my-feature  # Fast-forward
  
  # Interactive rebase (clean up commits)
  git rebase -i HEAD~3
  # pick, squash, reword, edit, drop
  ```

- [ ] **Cherry-pick**
  ```bash
  # Apply specific commit to current branch
  git cherry-pick <commit-hash>
  
  # Use case: Apply hotfix to multiple branches
  ```

- [ ] **Stash**
  ```bash
  # Save work temporarily
  git stash
  git stash save "Work in progress"
  
  # List stashes
  git stash list
  
  # Apply stash
  git stash apply
  git stash pop  # Apply and remove
  
  # Apply specific stash
  git stash apply stash@{1}
  
  # Drop stash
  git stash drop stash@{0}
  ```

- [ ] **Reset vs Revert**
  ```bash
  # Reset (rewrite history - use carefully!)
  git reset --soft HEAD~1   # Undo commit, keep changes staged
  git reset --mixed HEAD~1  # Undo commit, unstage changes
  git reset --hard HEAD~1   # Undo commit, discard changes
  
  # Revert (create new commit that undoes)
  git revert <commit-hash>  # Safe for shared branches
  ```

- [ ] **Reflog (Recover lost commits)**
  ```bash
  # View all ref updates
  git reflog
  
  # Recover deleted branch/commit
  git checkout <commit-hash>
  git checkout -b recovered-branch
  ```

---

## 🔷 Part 5: GitHub Features

### Concepts to Master:

- [ ] **Issues**
  ```markdown
  ## Bug Report
  **Description:** Login button not working
  
  **Steps to Reproduce:**
  1. Go to login page
  2. Enter credentials
  3. Click login
  
  **Expected:** Redirect to dashboard
  **Actual:** Nothing happens
  
  **Environment:**
  - Browser: Chrome 120
  - OS: Windows 11
  
  **Labels:** bug, priority-high
  **Assignee:** @developer
  ```

- [ ] **Projects (Kanban Board)**
  ```
  Columns:
  - Backlog
  - To Do
  - In Progress
  - In Review
  - Done
  
  Link issues to cards
  Automate card movement
  ```

- [ ] **GitHub Actions (CI/CD)**
  ```yaml
  # .github/workflows/build.yml
  name: Build and Test
  
  on:
    push:
      branches: [ main, develop ]
    pull_request:
      branches: [ main ]
  
  jobs:
    build:
      runs-on: ubuntu-latest
      
      steps:
      - uses: actions/checkout@v2
      
      - name: Set up JDK 17
        uses: actions/setup-java@v2
        with:
          java-version: '17'
      
      - name: Build with Maven
        run: mvn clean install
      
      - name: Run tests
        run: mvn test
      
      - name: Upload coverage
        uses: codecov/codecov-action@v2
  ```

- [ ] **Branch Protection Rules**
  ```
  Settings → Branches → Add rule
  
  Protections:
  ✅ Require pull request reviews (2 approvals)
  ✅ Require status checks to pass
  ✅ Require branches to be up to date
  ✅ Include administrators
  ✅ Restrict who can push
  ```

---

## 🔷 Part 6: Team Collaboration Best Practices

### Guidelines:

- [ ] **Commit Messages**
  ```
  Good:
  feat: Add user authentication
  fix: Resolve null pointer in payment service
  docs: Update API documentation
  refactor: Simplify user validation logic
  test: Add unit tests for order service
  
  Bad:
  Update
  Fixed bug
  Changes
  asdfgh
  ```
  
  **Format:**
  ```
  <type>: <subject>
  
  <body>
  
  <footer>
  
  Types: feat, fix, docs, style, refactor, test, chore
  ```

- [ ] **Branch Naming**
  ```
  feature/user-authentication
  bugfix/login-error
  hotfix/payment-crash
  release/v1.2.0
  
  Pattern: <type>/<description-in-kebab-case>
  ```

- [ ] **Code Review Etiquette**
  ```
  ✅ Review within 24 hours
  ✅ Be specific in comments
  ✅ Suggest code snippets
  ✅ Acknowledge good code
  ✅ Test the changes locally
  
  ❌ "This is wrong" (explain why)
  ❌ Personal attacks
  ❌ Nitpicking style (use linter)
  ```

---

## ✅ Move-On Criteria

- [ ] ✔ **Git branching strategies বুঝবেন**
- [ ] ✔ **Pull Request create এবং review করতে পারবেন**
- [ ] ✔ **Merge conflicts resolve করতে পারবেন**
- [ ] ✔ **Advanced Git commands use করতে পারবেন**
- [ ] ✔ **GitHub Actions setup করতে পারবেন**
- [ ] ✔ **Team collaboration best practices follow করবেন**

**Real-World Applications:**
- 👥 Team development
- 🔄 Code review process
- 🚀 CI/CD pipelines
- 📊 Project management
- 🔒 Code quality assurance
