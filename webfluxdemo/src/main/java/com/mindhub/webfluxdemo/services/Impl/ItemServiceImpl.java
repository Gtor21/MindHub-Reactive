package com.mindhub.webfluxdemo.services.Impl;

import com.mindhub.webfluxdemo.handlers.NotFoundException;
import com.mindhub.webfluxdemo.models.Item;
import com.mindhub.webfluxdemo.repositories.ItemRespository;
import com.mindhub.webfluxdemo.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRespository itemRespository;

    @Override
    public Mono<Item> getById(Long id) {
        return itemRespository.findById(id).switchIfEmpty(Mono.error( new NotFoundException("Item no encontrado Numero: " + id)));
    }

    @Override
    public Flux<Item> getAll() {
        return itemRespository.findAll();
    }

    @Override
    public Mono<Item> save(Item item) {
        return itemRespository.save(item);
    }

    @Override
    public Mono<Item> deleteById(Long id) {
        return getById(id).flatMap(item -> {
            itemRespository.delete(item).subscribe();
            return Mono.empty();
        });
    }

    @Override
    public Mono<Item> update(Item item) {
        return getById(item.getId()).flatMap(exitedItem -> {
            exitedItem.setName(item.getName());
            return itemRespository.save(item);
        });
    }
}
