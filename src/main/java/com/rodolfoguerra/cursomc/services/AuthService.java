package com.rodolfoguerra.cursomc.services;

import com.rodolfoguerra.cursomc.model.Client;
import com.rodolfoguerra.cursomc.repositories.ClientRepository;
import com.rodolfoguerra.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {
    private final ClientRepository clientRepository;

    private final BCryptPasswordEncoder pe;

    private final EmailService emailService;

    private final Random rand = new Random();

    public AuthService(ClientRepository clientRepository, BCryptPasswordEncoder pe, EmailService emailService) {
        this.clientRepository = clientRepository;
        this.pe = pe;
        this.emailService = emailService;
    }

    public void sendNewPassword(String email) {

        Client client = clientRepository.findByEmail(email);
        if (client == null) {
            throw new ObjectNotFoundException("Email não encontrado");
        }

        String newPass = newPassword();
        client.setPassword(pe.encode(newPass));

        clientRepository.save(client);
        emailService.sendNewPasswordEmail(client, newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i=0; i<10; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = rand.nextInt(3);
        if (opt == 0) { // gera um digito
            return (char) (rand.nextInt(10) + 48);
        }
        else if (opt == 1) { // gera letra maiuscula
            return (char) (rand.nextInt(26) + 65);
        }
        else { // gera letra minuscula
            return (char) (rand.nextInt(26) + 97);
        }
    }
}