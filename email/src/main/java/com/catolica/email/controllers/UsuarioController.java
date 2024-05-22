package com.catolica.email.controllers;

import com.catolica.email.dtos.UsuarioDto;
import com.catolica.email.models.UsuarioModel;
import com.catolica.email.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService emailService;


    @PostMapping("/sending-email")
    public ResponseEntity<UsuarioModel> sendingEmail(@RequestBody @Valid UsuarioDto usuarioDto){
        UsuarioModel emailModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, emailModel);
        emailService.sendEmail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }

}
