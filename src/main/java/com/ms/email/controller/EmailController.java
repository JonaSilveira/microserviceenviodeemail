package com.ms.email.controller;

import com.ms.email.dtos.EmailDto;
import com.ms.email.model.Email;
import com.ms.email.repository.EmailRepository;
import com.ms.email.service.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.beans.Beans;

@RestController
public class EmailController {

    @Autowired
    EmailRepository repository;

    @Autowired
    EmailService emailService;

    @PostMapping("send-email")
    public ResponseEntity<Email> sendEmail(@RequestBody @Valid EmailDto emailDto){
        Email email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        emailService.sendEmail(email);
        return new ResponseEntity<>(email, HttpStatus.OK);

    }


}
