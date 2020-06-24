package com.challenge.controller;
import com.challenge.model.User;
import com.challenge.service.UserService;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("Insert a user")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Successful Operation")})
    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid User user){
        return new ResponseEntity<User>(userService.save(user,"POST"), HttpStatus.CREATED);
    }

    //Método de teste
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String getUser(Authentication authentication){
        return authentication.getName();
    }

    @ApiOperation("Search all users using predicate")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful Operation")})
    @GetMapping
    public Page<User> getFiltered(@QuerydslPredicate(root = User.class) Predicate predicate, @PageableDefault(size = 5) Pageable pageable) {
        return userService.findAll(predicate, pageable);
    }

    @ApiOperation("Delete a user")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful Operation")})
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return userService.findById(id)
                .map(record -> {
                    userService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation("Change a user")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful Operation")})
    @PutMapping(path = "/{id}", consumes = { "application/json" })
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody User user) {
        return userService.findById(id).map(record -> {
            User updated = userService.save(record,"PUT");
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }
}
