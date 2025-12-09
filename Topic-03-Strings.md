# 📌 TOPIC 3: Strings Mastery

## 🎯 Learning Objectives
Strings হলো interview এর সবচেয়ে common topic। প্রায় 30-40% problems strings নিয়ে।

---

## 🔷 Part 1: String Fundamentals

### Concepts to Master:

- [ ] **String কীভাবে Memory তে Store হয়**
  ```java
  String str = "Hello";
  // Memory: char array ['H','e','l','l','o']
  // Java তে String immutable
  ```
  
  **Real-life Example:** 🏦 **Bank Account Number**
  - একবার create হলে change হয় না
  - নতুন account = নতুন string
  - Security এর জন্য immutable

- [ ] **String vs StringBuilder vs StringBuffer**
  ```java
  // String (Immutable)
  String s = "Hello";
  s = s + " World";  // নতুন object তৈরি হয়
  
  // StringBuilder (Mutable, Not thread-safe)
  StringBuilder sb = new StringBuilder("Hello");
  sb.append(" World");  // Same object modify
  
  // StringBuffer (Mutable, Thread-safe)
  StringBuffer sbf = new StringBuffer("Hello");
  sbf.append(" World");  // Synchronized
  ```
  
  **Real-life Use Cases:**
  - **String:** Configuration values, constants
  - **StringBuilder:** Log file generation, CSV building
  - **StringBuffer:** Multi-threaded chat application

- [ ] **String Pool Concept**
  ```java
  String s1 = "Java";      // String pool
  String s2 = "Java";      // Same reference
  String s3 = new String("Java");  // Heap
  
  System.out.println(s1 == s2);  // true
  System.out.println(s1 == s3);  // false
  System.out.println(s1.equals(s3));  // true
  ```
  
  **Real-life Example:** 🌐 **URL Caching**
  - Same URL বারবার create না করে pool থেকে নেয়
  - Memory save হয়

---

## 🔷 Part 2: String Manipulation Techniques

### 1. Two Pointers on Strings

- [ ] **Palindrome Check**
  ```java
  boolean isPalindrome(String s) {
      int left = 0, right = s.length() - 1;
      while (left < right) {
          if (s.charAt(left) != s.charAt(right)) {
              return false;
          }
          left++;
          right--;
      }
      return true;
  }
  ```
  
  **Real-life Use Case:** 📝 **Username Validation**
  - "racecar" type usernames detect করা
  - Symmetric patterns check

- [ ] **Valid Palindrome (Ignore Non-Alphanumeric)**
  ```java
  boolean isPalindrome(String s) {
      int left = 0, right = s.length() - 1;
      while (left < right) {
          while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
              left++;
          }
          while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
              right--;
          }
          if (Character.toLowerCase(s.charAt(left)) != 
              Character.toLowerCase(s.charAt(right))) {
              return false;
          }
          left++;
          right--;
      }
      return true;
  }
  ```
  
  **Real-life Example:** 💬 **Social Media Post Validation**
  - "A man, a plan, a canal: Panama" → valid palindrome

### 2. Sliding Window on Strings

- [ ] **Longest Substring Without Repeating Characters**
  ```java
  int lengthOfLongestSubstring(String s) {
      HashMap<Character, Integer> map = new HashMap<>();
      int left = 0, maxLen = 0;
      
      for (int right = 0; right < s.length(); right++) {
          char c = s.charAt(right);
          if (map.containsKey(c)) {
              left = Math.max(left, map.get(c) + 1);
          }
          map.put(c, right);
          maxLen = Math.max(maxLen, right - left + 1);
      }
      return maxLen;
  }
  ```
  
  **Real-life Use Case:** 🔐 **Password Strength Checker**
  - Unique characters কতটা consecutive আছে
  - Strong password = longer unique substring

- [ ] **Minimum Window Substring**
  ```java
  String minWindow(String s, String t) {
      if (s.length() < t.length()) return "";
      
      HashMap<Character, Integer> need = new HashMap<>();
      HashMap<Character, Integer> window = new HashMap<>();
      
      for (char c : t.toCharArray()) {
          need.put(c, need.getOrDefault(c, 0) + 1);
      }
      
      int left = 0, right = 0;
      int valid = 0;
      int start = 0, minLen = Integer.MAX_VALUE;
      
      while (right < s.length()) {
          char c = s.charAt(right);
          right++;
          
          if (need.containsKey(c)) {
              window.put(c, window.getOrDefault(c, 0) + 1);
              if (window.get(c).equals(need.get(c))) {
                  valid++;
              }
          }
          
          while (valid == need.size()) {
              if (right - left < minLen) {
                  start = left;
                  minLen = right - left;
              }
              
              char d = s.charAt(left);
              left++;
              
              if (need.containsKey(d)) {
                  if (window.get(d).equals(need.get(d))) {
                      valid--;
                  }
                  window.put(d, window.get(d) - 1);
              }
          }
      }
      
      return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
  }
  ```
  
  **Real-life Use Case:** 🔍 **Search Engine Autocomplete**
  - User type করলে minimum matching substring খুঁজে বের করা
  - "goo" → "google" suggest করা

### 3. Character Frequency

- [ ] **Anagram Check**
  ```java
  boolean isAnagram(String s, String t) {
      if (s.length() != t.length()) return false;
      
      int[] count = new int[26];
      for (int i = 0; i < s.length(); i++) {
          count[s.charAt(i) - 'a']++;
          count[t.charAt(i) - 'a']--;
      }
      
      for (int c : count) {
          if (c != 0) return false;
      }
      return true;
  }
  ```
  
  **Real-life Use Case:** 🎮 **Word Game (Scrabble)**
  - "listen" এবং "silent" same letters
  - Valid word formation check

- [ ] **Group Anagrams**
  ```java
  List<List<String>> groupAnagrams(String[] strs) {
      HashMap<String, List<String>> map = new HashMap<>();
      
      for (String s : strs) {
          char[] chars = s.toCharArray();
          Arrays.sort(chars);
          String key = new String(chars);
          
          if (!map.containsKey(key)) {
              map.put(key, new ArrayList<>());
          }
          map.get(key).add(s);
      }
      
      return new ArrayList<>(map.values());
  }
  ```
  
  **Real-life Use Case:** 📧 **Email Spam Detection**
  - Similar pattern এর emails group করা
  - "earn money" vs "money earn" → same group

---

## 🔷 Part 3: Pattern Matching

### 1. Substring Search

- [ ] **Naive Pattern Matching**
  ```java
  int naiveSearch(String text, String pattern) {
      int n = text.length();
      int m = pattern.length();
      
      for (int i = 0; i <= n - m; i++) {
          int j;
          for (j = 0; j < m; j++) {
              if (text.charAt(i + j) != pattern.charAt(j)) {
                  break;
              }
          }
          if (j == m) return i;  // Pattern found
      }
      return -1;
  }
  // Time: O(n*m)
  ```
  
  **Real-life Use Case:** 📄 **Ctrl+F in Text Editor**
  - Document এ word খুঁজে বের করা

- [ ] **KMP Algorithm (Efficient)**
  ```java
  int[] computeLPS(String pattern) {
      int m = pattern.length();
      int[] lps = new int[m];
      int len = 0;
      int i = 1;
      
      while (i < m) {
          if (pattern.charAt(i) == pattern.charAt(len)) {
              len++;
              lps[i] = len;
              i++;
          } else {
              if (len != 0) {
                  len = lps[len - 1];
              } else {
                  lps[i] = 0;
                  i++;
              }
          }
      }
      return lps;
  }
  
  int KMPSearch(String text, String pattern) {
      int n = text.length();
      int m = pattern.length();
      int[] lps = computeLPS(pattern);
      
      int i = 0, j = 0;
      while (i < n) {
          if (text.charAt(i) == pattern.charAt(j)) {
              i++;
              j++;
          }
          
          if (j == m) {
              return i - j;  // Pattern found
          } else if (i < n && text.charAt(i) != pattern.charAt(j)) {
              if (j != 0) {
                  j = lps[j - 1];
              } else {
                  i++;
              }
          }
      }
      return -1;
  }
  // Time: O(n + m)
  ```
  
  **Real-life Use Case:** 🔍 **Google Search**
  - Billions of pages এ efficiently search করা
  - DNA sequence matching

---

## 🔷 Part 4: String Transformation

- [ ] **Reverse Words in String**
  ```java
  String reverseWords(String s) {
      String[] words = s.trim().split("\\s+");
      StringBuilder result = new StringBuilder();
      
      for (int i = words.length - 1; i >= 0; i--) {
          result.append(words[i]);
          if (i > 0) result.append(" ");
      }
      
      return result.toString();
  }
  ```
  
  **Real-life Use Case:** 🌐 **RTL Language Support**
  - Arabic/Hebrew text processing
  - "Hello World" → "World Hello"

- [ ] **String Compression**
  ```java
  String compress(String s) {
      StringBuilder compressed = new StringBuilder();
      int count = 1;
      
      for (int i = 1; i < s.length(); i++) {
          if (s.charAt(i) == s.charAt(i - 1)) {
              count++;
          } else {
              compressed.append(s.charAt(i - 1));
              if (count > 1) compressed.append(count);
              count = 1;
          }
      }
      
      compressed.append(s.charAt(s.length() - 1));
      if (count > 1) compressed.append(count);
      
      return compressed.length() < s.length() ? compressed.toString() : s;
  }
  ```
  
  **Real-life Use Case:** 💾 **File Compression**
  - "aaabbbccc" → "a3b3c3"
  - Image compression (RLE encoding)

---

## 💻 Coding Practice (20 Problems)

### 🟢 Basic (5)

- [ ] **1. Reverse String**
  - Input: "hello" → Output: "olleh"
  - Use case: Display text backwards

- [ ] **2. First Unique Character**
  - Input: "leetcode" → Output: 0 (index of 'l')
  - Use case: Find first non-repeated character in stream

- [ ] **3. Valid Anagram**
  - Input: "anagram", "nagaram" → true
  - Use case: Word games

- [ ] **4. Palindrome Check**
  - Input: "racecar" → true
  - Use case: Username validation

- [ ] **5. Count Vowels**
  - Input: "hello" → Output: 2
  - Use case: Text analysis

### 🔵 Intermediate (7)

- [ ] **6. Longest Common Prefix**
  - Input: ["flower","flow","flight"] → "fl"
  - Use case: Autocomplete suggestions

- [ ] **7. String to Integer (atoi)**
  - Input: "  -42" → -42
  - Use case: Parse user input

- [ ] **8. Longest Palindromic Substring**
  - Input: "babad" → "bab" or "aba"
  - Use case: DNA sequence analysis

- [ ] **9. Group Anagrams**
  - Input: ["eat","tea","tan","ate"] → [["eat","tea","ate"],["tan"]]
  - Use case: Organize similar words

- [ ] **10. Zigzag Conversion**
  - Input: "PAYPALISHIRING", rows=3 → "PAHNAPLSIIGYIR"
  - Use case: Encryption patterns

- [ ] **11. Implement strStr()**
  - Input: haystack="hello", needle="ll" → 2
  - Use case: Substring search

- [ ] **12. Longest Substring Without Repeating**
  - Input: "abcabcbb" → 3 ("abc")
  - Use case: Password strength

### 🟡 Advanced (8)

- [ ] **13. Minimum Window Substring**
  - Input: s="ADOBECODEBANC", t="ABC" → "BANC"
  - Use case: Search optimization

- [ ] **14. Valid Parentheses**
  - Input: "()[]{}" → true
  - Use case: Code syntax validation

- [ ] **15. Generate Parentheses**
  - Input: n=3 → ["((()))","(()())","(())()","()(())","()()()"]
  - Use case: Expression generation

- [ ] **16. Multiply Strings**
  - Input: "123", "456" → "56088"
  - Use case: Big number calculation

- [ ] **17. Wildcard Matching**
  - Input: s="aa", p="*" → true
  - Use case: File pattern matching (*.txt)

- [ ] **18. Regular Expression Matching**
  - Input: s="aa", p="a*" → true
  - Use case: Email/phone validation

- [ ] **19. Edit Distance**
  - Input: "horse", "ros" → 3
  - Use case: Spell checker, DNA alignment

- [ ] **20. Longest Repeating Character Replacement**
  - Input: s="AABABBA", k=1 → 4
  - Use case: Data deduplication

---

## ✅ Move-On Criteria

- [ ] ✔ **String immutability explain করতে পারবেন real example সহ**
- [ ] ✔ **StringBuilder vs String কখন use করবেন বলতে পারবেন**
- [ ] ✔ **Two pointers + sliding window strings এ apply করতে পারবেন**
- [ ] ✔ **Anagram problems instantly solve করতে পারবেন**
- [ ] ✔ **Pattern matching algorithms (KMP) concept বুঝবেন**
- [ ] ✔ **15+ string problems without hints solve করতে পারবেন**

---

**Real-World Applications:**
- 🔐 Password validation
- 📧 Email parsing
- 🌐 URL processing
- 💬 Chat applications
- 🔍 Search engines
- 📝 Text editors
