package com.catolica.email.services;

import com.catolica.email.enums.UsuarioStatus;
import com.catolica.email.models.UsuarioModel;
import com.catolica.email.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private JavaMailSender emailSender;

    public UsuarioModel sendEmail(UsuarioModel usuarioModel) {
        usuarioModel.setSendDateEmail(LocalDateTime.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(usuarioModel.getEmailFrom());
            message.setTo(usuarioModel.getEmailTo());
            message.setSubject(usuarioModel.getSubject());
            message.setText(usuarioModel.getText());
            emailSender.send(message);

            usuarioModel.setUsuarioStatus(UsuarioStatus.SENT);
        } catch (MailException e) {
            usuarioModel.setUsuarioStatus(UsuarioStatus.ERROR);
        }

        return usuarioRepository.save(usuarioModel);
    }
}
