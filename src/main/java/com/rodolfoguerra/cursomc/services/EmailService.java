package com.rodolfoguerra.cursomc.services;

import com.rodolfoguerra.cursomc.model.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void senderOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage msg);
}
