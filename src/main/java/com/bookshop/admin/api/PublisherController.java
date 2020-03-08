package com.bookshop.admin.api;

import com.bookshop.admin.domain.Publisher;
import com.bookshop.admin.exception.RecordNotFoundException;
import com.bookshop.admin.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public ResponseEntity<List<Publisher>> getAllPublisher() {
        List<Publisher> publisherList = publisherService.getAllPublishers();

        return new ResponseEntity<>(publisherList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Publisher publisher = publisherService.getPublisherById(id);

        return new ResponseEntity<>(publisher, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Publisher> createOrUpdatePublisher(Publisher publisher) {
        Publisher publisherEntity = publisherService.createOrUpdatePublisher(publisher);

        return new ResponseEntity<>(publisherEntity, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping
    public HttpStatus deletePublisherById(@PathVariable("id") Long id) throws RecordNotFoundException {
        publisherService.deletePublisherById(id);

        return HttpStatus.FORBIDDEN;
    }
}
