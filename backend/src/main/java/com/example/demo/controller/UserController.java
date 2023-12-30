package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<Object> getUsers() {
        Map<String, Object> response = new HashMap<>();
        List<User> users = userService.getUsers();
        return generateResponse(true, users);
    }

    @GetMapping("/userAddress")
    public ResponseEntity<Object> getAddressByID(@RequestBody UserDTO userDTO){
        Map<String, Object> response = new HashMap<>();
        String address = userService.getAddressByID(userDTO);
        return (address != null) ? generateResponse(true, address) : generateResponse(false, "user not found");
    }

    @PostMapping("/user")
    public ResponseEntity<Object> saveUser(@RequestBody UserDTO userDTO) {
        Map<String, Object> response = new HashMap<>();
        UserDTO user = userService.saveUser(userDTO);
        return (user != null) ? generateResponse(true, "user successfully saved") : generateResponse(false, "user insert failed");
    }

    @DeleteMapping("/user")
    public ResponseEntity<Object> deleteUser(@RequestBody UserDTO userDTO) {
        Map<String, Object> response = new HashMap<>();
        UserDTO deleteUserDTO = userService.deleteUser(userDTO);
        return (deleteUserDTO != null) ? generateResponse(true, "user deleted successfully") : generateResponse(false, "user delete failed!");
    }

    @PatchMapping("/user")
    public ResponseEntity<Object> updateUser(@RequestBody UserDTO userDTO) {
        Map<String, Object> response = new HashMap<>();
        UserDTO updateUserDTO = userService.updateUser(userDTO);
        return (updateUserDTO != null) ? generateResponse(true, updateUserDTO): generateResponse(false, "user not found!");
    }

    public ResponseEntity<Object> generateResponse(boolean isSuccess, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", isSuccess ? 1 : 0);
        response.put("message", data);
        return isSuccess ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
