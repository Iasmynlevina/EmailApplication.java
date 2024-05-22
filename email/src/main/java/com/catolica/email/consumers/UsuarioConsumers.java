package com.catolica.email.consumers;

import com.catolica.email.dtos.UsuarioDto;
import com.catolica.email.models.UsuarioModel;
import com.catolica.email.services.UsuarioService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;

@Component
public class UsuarioConsumers {

    @Autowired
    UsuarioService usuarioService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listenRegister(@Payload UsuarioDto usuarioDto) {
        UsuarioModel usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, usuarioModel);
        usuarioService.sendEmail(usuarioModel);
        System.out.println("Status: " + usuarioModel.getUsuarioStatus().toString());
    }
}
