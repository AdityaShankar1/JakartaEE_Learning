# 🛰️ Go Infrastructure: The Load Balancer

This folder contains the Go-based infrastructure that manages traffic for the system.

### Engineering Fundamentals:
* **HTTP Reverse Proxying:** Built using Go's native `httputil` to bridge traffic.
* **Round Robin Scheduling:** A stateless algorithm to distribute load across backends.
* **Active Health Monitoring:** A background **Goroutine** (lightweight thread) that pings servers to ensure uptime.
* **Thread Safety:** Uses `sync.RWMutex` to protect the server's health state from race conditions during high traffic.

### How to Run:
1. Start Backend 2: `go run backend2.go`
2. Start Load Balancer: `go run main.go`
