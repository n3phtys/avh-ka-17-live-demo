package main

import (
    "fmt"
    "net/http"
)

func handler(w http.ResponseWriter, r *http.Request) {
	var v = r.URL.Path[1:]
	
	if (len(v) == 0 || v == "favicon.ico") {
		v = "World"
	}

    fmt.Fprintf(w, "Hello %s from Go!", v)
}

func main() {
	fmt.Println("Starting hello world server")
    http.HandleFunc("/", handler)
    http.ListenAndServe(":8080", nil)
}