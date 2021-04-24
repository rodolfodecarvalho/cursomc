package com.rodolfoguerra.cursomc.services.validation;

import com.rodolfoguerra.cursomc.controllers.exceptions.FieldMessage;
import com.rodolfoguerra.cursomc.dto.ClientDTO;
import com.rodolfoguerra.cursomc.model.Client;
import com.rodolfoguerra.cursomc.repositories.ClientRepository;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientUpdateValidation implements ConstraintValidator<ClientUpdate, ClientDTO> {

    private final HttpServletRequest httpServletRequest;

    private final ClientRepository clientRepository;

    public ClientUpdateValidation(HttpServletRequest httpServletRequest, ClientRepository clientRepository) {
        this.httpServletRequest = httpServletRequest;
        this.clientRepository = clientRepository;
    }

    @Override
    public void initialize(ClientUpdate ann) {
    }

    @Override
    public boolean isValid(ClientDTO clientDTO, ConstraintValidatorContext context) {

        Map<String,String> map = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long uriId = Long.valueOf(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Client client = clientRepository.findByEmail(clientDTO.getEmail());
        if (client != null && Boolean.FALSE.equals(client.getId().equals(uriId))) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}