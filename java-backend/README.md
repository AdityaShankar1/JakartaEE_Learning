# Jakarta EE Learning: Under the Hood

This project is a deep-dive into Java Web Development without the "magic" of IDE wizards. Built using **Java 25**, **Maven**, and **Tomcat 11** on macOS.

It is:
1. An attempt to understand how Java works "under the hood"
2. A curious attempt at learning fundamentals of Jarkata EE with 10-stage SPA -> basic CRUD

It is NOT:
1. A production-ready application
2. A reason to prefer raw HttpServlet/JSP over modern frameworks like Spring or Quarkus

## 🚀 Achievements So Far
* **Manual Project Setup**: Created a standard WAR structure without archetypes.
* **Servlet Basics**: Implemented `HttpServlet` with `@WebServlet` annotations.
* **Static Content**: Integrated a static blog page featuring a majestic giraffe.
* **Dynamic Features**: 
    * Server-side clock and personalized greeting via URL parameters (`GET`).
    * Functional Calculator using HTML Forms (`POST`).
* **Infrastructure**: Managed standalone Tomcat deployment and handled port conflicts manually.

## 🛠 Tech Stack
* **Language**: Java 25
* **API**: Jakarta Servlet 6.1
* **Server**: Apache Tomcat 11
* **Build Tool**: Maven

## 📖 How to Run
1. Build: `mvn clean package`
2. Deploy: Copy the `.war` to Tomcat's `webapps` folder.
3. Start: `catalina run`

## Screenshots at Different Stages:

### Stage 0: Hello World:

<img width="550" height="224" alt="image" src="https://github.com/user-attachments/assets/9d0c475f-5a75-4d5e-8382-40f76a099704" />

### Stage 1: Giraffe Blog:

<img width="1031" height="693" alt="image" src="https://github.com/user-attachments/assets/b7c2cc44-62b4-4b77-b7fa-622d8be13928" />

#### Stage 1.5: A Quick Corss-Browser Compatibility Check:

<img width="1454" height="768" alt="image" src="https://github.com/user-attachments/assets/66c8550c-efa5-48ea-8beb-32b2e0cda39e" />

✅ Worked on Gecko (FireFox) and Apple WebKit (Safari)

### Stage 2: Handling User Input:

<img width="1031" height="693" alt="image" src="https://github.com/user-attachments/assets/d0851cac-4773-44af-8503-b0dc30972bbc" />

### Step 3: Request Forwarding to Show Result on Same Page:

<img width="1265" height="761" alt="image" src="https://github.com/user-attachments/assets/5a672dbd-87be-4afa-a047-9b08350cfbbd" />

**Note**: Here, I had to rename blog.html to blog.jsp

### Step 4: Adding Toggle for User to Choose:

<img width="1159" height="527" alt="image" src="https://github.com/user-attachments/assets/d9889eee-09b9-40df-aef5-ab9a0374ce96" />
<img width="1127" height="763" alt="image" src="https://github.com/user-attachments/assets/39efb20f-0d75-404c-ad00-23e067e80f92" />

### Step 5: Session Management to Remember User's Selection:

<img width="1038" height="762" alt="image" src="https://github.com/user-attachments/assets/14e62c4d-62ab-4b5f-ad0b-6e7c863a65af" />

### Step 6: Login Page to Secure Information:

<img width="1124" height="473" alt="image" src="https://github.com/user-attachments/assets/ecf3f4a9-0f60-4d2a-8cca-ef2bc11a3df2" />

### Step 7: Improvised Login (to prevent bypassing via Url) and a LogOut option:

<img width="1263" height="745" alt="image" src="https://github.com/user-attachments/assets/3155fe06-b0e5-4f7a-9bce-432de76c207b" />

### Step 8: Create & Delete Animal Blogs:

<img width="1263" height="767" alt="image" src="https://github.com/user-attachments/assets/2c173244-5c85-4aec-86dd-a365fbaa99cb" />

### Step 9: Update Exisiting Animal:

<img width="990" height="764" alt="image" src="https://github.com/user-attachments/assets/0e80859f-7283-4e1f-bc84-118f12012079" />

### Step 10: Make this into an API that returns JSON:

<img width="1265" height="767" alt="image" src="https://github.com/user-attachments/assets/863a295b-fc64-4418-aacd-e28177f74929" />

---

## 📜 License & Disclaimer

**Usage for Learning Only**
This project is provided "as-is" for educational and portfolio purposes. 

1.  **Liability:** The author is not responsible for any security vulnerabilities, data loss, or system instability caused by running this code on your machine or server.
2.  **Legacy Code:** Components (like JSP) are included for historical/educational context and may contain inherent vulnerabilities that are not patched for production use.

---

## 🔑 Test Credentials

Since this is a demonstration of an integrated system, the database is pre-seeded with a test user. Use the following credentials to bypass the login screen and interact with the Java/H2 backend:

| Role | Username | Password |
| :--- | :--- | :--- |
| **Admin/Test User** | `aditya` | `java25` |

### 🔍 How to Verify the Flow:
1.  Navigate to `http://localhost:8080` (The Load Balancer).
2.  Enter the credentials above.
3.  Upon success, the **Go Load Balancer** will have successfully proxied your request, and the **Java Backend** will have authenticated you against the **H2 Database**.

> **⚠️ Production Note:** In a real-world scenario, credentials would never be hardcoded or stored in plaintext. This project uses a pre-seeded H2 in-memory database for ease of demonstration and recruitment review.
