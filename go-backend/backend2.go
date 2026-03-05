package main

import (
	"fmt"
	"net/http"
)

func main() {
	http.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
		fmt.Fprintf(w, "Hello from the SECOND Backend (Running on Go)!")
	})

	fmt.Println("Second backend (Go) started on :8082")
	http.ListenAndServe(":8082", nil)
}
