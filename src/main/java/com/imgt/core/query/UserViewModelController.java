package com.imgt.core.query;

import com.imgt.core.query.user.UserViewModelDto;
import com.imgt.core.query.user.UserViewModelService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/query/users")
public class UserViewModelController {

    private final UserViewModelService userViewModelService;

    public UserViewModelController(UserViewModelService userViewModelService) {
        this.userViewModelService = userViewModelService;
    }

    @GetMapping("/{id}")
    public UserViewModelDto getUserById(@PathVariable UUID id) {
        return userViewModelService.getUser(id);
    }

    @GetMapping
    public UserViewModelDto getUserById(@RequestBody String nome) {
        return userViewModelService.getUserByNome(nome);
    }
}
