package in.cdac.controller;

import in.cdac.model.UserModel;
import in.cdac.repository.UserModelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequestMapping("/user")
public class UserModelController {

    @Autowired
    private UserModelRepository userModelRepository;

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody UserModel userModel) {

        userModelRepository.save(userModel);
        return new ResponseEntity(userModel, HttpStatus.CREATED);
    }
}
