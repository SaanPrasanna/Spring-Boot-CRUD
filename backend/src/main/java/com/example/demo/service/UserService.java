package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    public UserDTO saveUser(UserDTO userDTO) {
        return !userExists(userDTO.getId()) ? userRepo.save(modelMapper.map(userDTO, User.class)) != null ? userDTO : null : null;
    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public String getAddressByID(UserDTO userDTO){
        return userRepo.findAddressByID(userDTO.getId());
    }

    public UserDTO updateUser(UserDTO userDTO) {

        return userExists(userDTO.getId()) ? userRepo.save(modelMapper.map(userDTO, User.class)) != null ? userDTO : null : null;
    }

    public UserDTO deleteUser (UserDTO userDTO){
        if(userExists(userDTO.getId())){
            userRepo.delete(modelMapper.map(userDTO, User.class));
            return userDTO;
        }else{
            return null;
        }
    }

    private Boolean userExists(int userID) {
        Optional<User> user = userRepo.findById(userID);
        return user.isPresent();
    }
}
