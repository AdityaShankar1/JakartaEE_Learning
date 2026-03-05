# 🏗️ Go-Infra-LoadBalancer

A distributed system project demonstrating a high-performance **Go Load Balancer** sitting in front of a **Java (JSP/Servlet) Backend**.

### 🌌 System Architecture
This project bridges modern systems programming (Go) with enterprise web standards (Java Jakarta EE).

### 📊 Full-Stack Architecture flow

\`\`\`mermaid
graph TD
    User((User/Browser)) -->|Port 8080| LB[Go Load Balancer]
    
    subgraph "Infrastructure Layer (Go)"
        LB -->|Round Robin| RR[Routing Logic]
        RR -.->|Heartbeat| HC{Health Check}
    end

    subgraph "Backend Tier"
        RR -->|Forward| J[Java Backend: Tomcat/Glassfish]
        RR -->|Forward| G[Go Backend: Native]
        
        subgraph "Data Persistence"
            J -->|JDBC| DB[(H2 Database)]
        end
    end

    style LB fill:#00ADD8,stroke:#333,stroke-width:2px,color:#fff
    style J fill:#f89820,stroke:#333,stroke-width:2px,color:#fff
    style DB fill:#4479A1,stroke:#333,stroke-width:1px,color:#fff
    style G fill:#00ADD8,stroke:#333,stroke-width:2px,color:#fff
\`\`\`

* **The Front Door:** Go Load Balancer (Port 8080) - [Go Logic Details](./go-backend/README.md)
* **Backend Instance 1:** Java JSP/Servlet on Tomcat (Port 8081) - [Java Logic Details](./java-backend/README.md)
* **Backend Instance 2:** Go Native Microservice (Port 8082) - Serving as a fallback/secondary node.

### 📂 Explore the Codebase
* [**Java Backend** (Jakarta EE/Tomcat)](./java-backend) — View the core business logic and JSP templates.
* [**Go Infrastructure** (Load Balancer)](./go-backend) — View the networking logic, health checks, and goroutines.

### 🛠️ Key Infra Features
1. **Dynamic Rerouting:** If the Java Tomcat server is killed, the Load Balancer detects the failure within 5 seconds and automatically reroutes all traffic to the Go backend.
2. **Cross-Language Proxying:** Demonstrates the ability to manage traffic between different language runtimes (JVM and Go Binary).
3. **Concurrency Control:** Utilizes Go's concurrency primitives to handle health checks without blocking incoming user requests.
