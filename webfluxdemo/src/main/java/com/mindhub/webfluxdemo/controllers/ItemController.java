package com.mindhub.webfluxdemo.controllers;

import com.mindhub.webfluxdemo.models.Item;
import com.mindhub.webfluxdemo.services.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/items")
@Tag(name = "Controlador Item", description = "API Sprint Reactivo")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Operation(summary = "Obtener Todo", description = "Obtiene todo los items")
    @GetMapping
    public Flux<Item> getAllItems(){
        return itemService.getAll();
    }

    @Operation(summary = "Crear", description = "Añade un nuevo item a la APP")
    @PostMapping
    public Mono<Item> createItem(@RequestBody Item item){
        return itemService.save(item);
    }

    @Operation(summary = "Obtener por ID", description = "Obtiene un item por ID")
    @GetMapping("/{id}")
    public Mono<Item> getItemById(@PathVariable Long id){
        return itemService.getById(id);
    }

    @Operation(summary = "Eliminar por ID", description = "Elimina un item por ID")
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<String>> deleteItemById(@PathVariable Long id){
        itemService.deleteById(id).subscribe();
        return Mono.just(ResponseEntity.noContent().build());
    }

    @Operation(summary = "Actualizar", description = "Actualiza un item existente en la APP")
    @PutMapping("/{id}")
    public Mono<Item> editItem(@PathVariable Long id, @RequestBody Item item){
        item.setId(id);
        return itemService.update(item);
    }
}
