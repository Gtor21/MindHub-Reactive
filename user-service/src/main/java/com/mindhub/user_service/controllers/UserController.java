package com.mindhub.user_service.controllers;

import com.mindhub.user_service.exeptions.UserNotFoundException;
import com.mindhub.user_service.dtos.UserDTO;
import com.mindhub.user_service.exeptions.UserNotFoundExceptionAuth;
import com.mindhub.user_service.mappers.UserMapper;
import com.mindhub.user_service.models.UserEntity;
import com.mindhub.user_service.repositories.UserRepository;
import com.mindhub.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.mindhub.user_service.mappers.UserMapper.toUserDTO;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public Mono<UserEntity> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .switchIfEmpty(Mono.error(new UserNotFoundException(id)));
    }

    @GetMapping
    public Flux<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public Mono<UserEntity> createUser(@RequestBody UserDTO user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public Mono<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserDTO user) {
        return userService.updateUser(id, user)
                .switchIfEmpty(Mono.error(new UserNotFoundException(id)));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id)
                .switchIfEmpty(Mono.error(new UserNotFoundException(id)));
    }

    @GetMapping("/current")
    public Mono<UserDTO> getCurrent(ServerWebExchange exchange){
        return userRepository.findByEmail(exchange.getRequest().getHeaders().getFirst("username"))
                .switchIfEmpty(Mono.error(new UserNotFoundExceptionAuth("Usuario no encontrado")))
                .map(UserDTO::new);
    }
}
