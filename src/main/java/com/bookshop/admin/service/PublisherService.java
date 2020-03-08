package com.bookshop.admin.service;

import com.bookshop.admin.domain.Publisher;
import com.bookshop.admin.exception.RecordNotFoundException;
import com.bookshop.admin.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    private PublisherRepository publisherRepository;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> getAllPublishers() {
        List<Publisher> publisherList = publisherRepository.findAll();

        if (publisherList.size() > 0) {
            return publisherList;
        } else {
            return new ArrayList<>();
        }
    }

    public Publisher getPublisherById(Long id) throws RecordNotFoundException {
        Optional<Publisher> publisher = publisherRepository.findById(id);

        if (publisher.isPresent()) {
            return publisher.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public Publisher createOrUpdatePublisher(Publisher publisher) {
        Optional<Publisher> publisherEntity = publisherRepository.findById(publisher.getId());

        if (publisherEntity.isPresent()) {
            Publisher updatePublisher = publisherEntity.get();
            updatePublisher.setName(publisher.getName());
            updatePublisher.setAddress(publisher.getAddress());

            publisherRepository.save(updatePublisher);

            return updatePublisher;
        } else {
            publisherRepository.save(publisher);

            return publisher;
        }
    }

    public void deletePublisherById(Long id) throws RecordNotFoundException {
        Optional<Publisher> publisher = publisherRepository.findById(id);

        if (publisher.isPresent()) {
            publisherRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}
