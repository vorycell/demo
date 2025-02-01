package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class demoController {

    private final WebClient webClient;

    public demoController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/products")
    public Mono<ResponseEntity<String>> getProducts() {
        return webClient.get()
                .uri("/api/products")
                .retrieve()
                .bodyToMono(String.class)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/products/{id}")
    public Mono<ResponseEntity<String>> getProductById(@PathVariable Long id) {
        return webClient.get()
                .uri("/api/products/{id}", id)
                .retrieve()
                .bodyToMono(String.class)
                .map(ResponseEntity::ok);
    }

    @PostMapping("/products")
    public Mono<ResponseEntity<String>> createProduct(@RequestBody String product) {
        return webClient.post()
                .uri("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product)
                .retrieve()
                .bodyToMono(String.class)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/products/{id}")
    public Mono<ResponseEntity<String>> updateProduct(@PathVariable Long id, @RequestBody String product) {
        return webClient.put()
                .uri("/api/products/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product)
                .retrieve()
                .bodyToMono(String.class)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/products/{id}")
    public Mono<ResponseEntity<String>> deleteProduct(@PathVariable Long id) {
        return webClient.delete()
                .uri("/api/products/{id}", id)
                .retrieve()
                .bodyToMono(String.class)
                .map(ResponseEntity::ok);
    }
}