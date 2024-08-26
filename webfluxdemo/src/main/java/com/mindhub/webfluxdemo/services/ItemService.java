package com.mindhub.webfluxdemo.services;

import com.mindhub.webfluxdemo.models.Item;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ItemService {

    Mono<Item> getById(Long id);

    Flux<Item> getAll();

    Mono<Item> save(Item item);

    Mono<Item> deleteById(Long id);

    Mono<Item> update(Item item);

}
