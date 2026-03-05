package main

import (
	"fmt"
	"net/http"
	"net/http/httputil"
	"net/url"
	"sync"
	"time"
)

type Backend struct {
	URL   *url.URL
	Alive bool
	mux   sync.RWMutex // Prevents "Race Conditions"
}

var (
	backends = []*Backend{}
	counter  = 0
)

// SetAlive updates the status of the backend safely
func (b *Backend) SetAlive(status bool) {
	b.mux.Lock()
	b.Alive = status
	b.mux.Unlock()
}

// IsAlive checks status safely
func (b *Backend) IsAlive() bool {
	b.mux.RLock()
	status := b.Alive
	b.mux.RUnlock()
	return status
}

func healthCheck() {
	for {
		for _, b := range backends {
			// Try to connect to the server
			resp, err := http.Get(b.URL.String())
			if err != nil || resp.StatusCode != 200 {
				if b.IsAlive() {
					fmt.Printf("❌ Server %s went DOWN!\n", b.URL)
					b.SetAlive(false)
				}
			} else {
				if !b.IsAlive() {
					fmt.Printf("✅ Server %s is back UP!\n", b.URL)
					b.SetAlive(true)
				}
			}
		}
		time.Sleep(5 * time.Second) // Check every 5 seconds
	}
}

func lbHandler(w http.ResponseWriter, r *http.Request) {
	// Pick the next ALIVE server
	for i := 0; i < len(backends); i++ {
		b := backends[counter%len(backends)]
		counter++

		if b.IsAlive() {
			proxy := httputil.NewSingleHostReverseProxy(b.URL)
			proxy.ServeHTTP(w, r)
			return
		}
	}
	http.Error(w, "All backends are down", http.StatusServiceUnavailable)
}

func main() {
	// Initialize our backends
	urls := []string{"http://localhost:8081", "http://localhost:8082"}
	for _, u := range urls {
		parsedUrl, _ := url.Parse(u)
		backends = append(backends, &Backend{URL: parsedUrl, Alive: true})
	}

	// START THE HEARTBEAT in the background
	go healthCheck()

	http.HandleFunc("/", lbHandler)
	fmt.Println("Smart Load Balancer with Health Checks started on :8080")
	http.ListenAndServe(":8080", nil)
}
