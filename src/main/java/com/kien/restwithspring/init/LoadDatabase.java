package com.kien.restwithspring.init;

import com.kien.restwithspring.model.Document;
import com.kien.restwithspring.model.Employee;
import com.kien.restwithspring.repository.EmployeeRepository;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            Employee emp1 = new Employee("Bilbo Baggins", "burglar");
            List<Document> docs1 = Arrays.asList(new Document("doc001_111", emp1), new Document("001_222", emp1));
            emp1.setDocuments(docs1);

            Employee emp2 = new Employee("Harry Potter", "wizard");
            List<Document> docs2 = Arrays.asList(new Document("doc002_111", emp2), new Document("002_222", emp2));
            emp2.setDocuments(docs2);

            log.info("Preloading " + repository.save(emp1));
            log.info("Preloading " + repository.save(emp2));
        };
    }
}
