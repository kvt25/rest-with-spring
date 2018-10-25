# Sample Project with Spring Data JPA, Hibernate association, Rest service, SpringBoot

Some notes:
1. @GetMapping(value = "/employees/{id}", produces = MediaTypes.HAL_JSON_VALUE)
If you get the error:
    ```sh
        .w.s.m.s.DefaultHandlerExceptionResolver : Failed to write HTTP message: org.springframework.http.converter.HttpMessageNotWritableException: Could not marshal [Resources { content: [FooApplication.FooResource(foo=foo)], links: [] }]: null; nested exception is javax.xml.bind.MarshalException
        - with linked exception
    ```
    -> You have to add "produces" in @GetMapping annotation
    
2. How to avoid infinite recursion loop when creating JSON
   ```sh
   Employee model
   @JsonIgnoreProperties("employee")
   private List<Document> documents;
   ```
   ```sh
   Document model
   @JsonIgnoreProperties("documents")
   private Employee employee;
   ```
    -> Add @JsonIgnoreProperties to avoid infinite recursion loop when creating JSON response