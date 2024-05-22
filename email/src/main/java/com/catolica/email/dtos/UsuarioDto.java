package com.catolica.email.dtos;


import jakarta.mail.Address;
import lombok.Data;

import java.util.UUID;

@Data

public class UsuarioDto {

    private UUID id;
    private String nome;
    private Integer idade;
    private String email;
    private String senha;
    private Address endereco;



}
