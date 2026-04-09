package com.eazybytes.eazystore.controller;

import com.eazybytes.eazystore.scopes.ApplicationScopedBean;
import com.eazybytes.eazystore.scopes.RequestScopedBean;
import com.eazybytes.eazystore.scopes.SessionScopedBean;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/scope")
@RequiredArgsConstructor
public class ScopeController {
    private final RequestScopedBean requestScopedBean;
    private final SessionScopedBean sessionScopedBean;
    private final ApplicationScopedBean applicationScopedBean;

    @GetMapping("/request")
    public ResponseEntity<String> testRequestScope() {
        requestScopedBean.setUserName("John Doe");
        return ResponseEntity.ok().body(requestScopedBean.getUserName());
    }

    @GetMapping("/session")
    public ResponseEntity<String> testSessionScope() {
        sessionScopedBean.setUserName("John Doe");
        return ResponseEntity.ok().body(sessionScopedBean.getUserName());
    }

    @GetMapping("/application")
    public ResponseEntity<Integer> testApplicationScope() {
        applicationScopedBean.incrementVisitorCount();
        return ResponseEntity.ok().body(applicationScopedBean.getVisitorCount());
    }

    @GetMapping("/test")
    public ResponseEntity<String> testScope() {
        // @RequestScope, a bean's lifecycle is bound to a single HTTP request
        // no userName displayed due to a different request
        //return ResponseEntity.ok().body(requestScopedBean.getUserName());
        // userName "John Doe" displayed if in the same session,
        // if open a new incognito window, no userName
        //return ResponseEntity.ok().body(sessionScopedBean.getUserName());
        // the value will be kept until the application is stopped
        return ResponseEntity.ok().body(String.valueOf(applicationScopedBean.getVisitorCount()));
    }
}
