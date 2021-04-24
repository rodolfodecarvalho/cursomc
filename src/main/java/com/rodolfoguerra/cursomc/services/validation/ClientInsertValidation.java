package com.rodolfoguerra.cursomc.services.validation;

import com.rodolfoguerra.cursomc.controllers.exceptions.FieldMessage;
import com.rodolfoguerra.cursomc.dto.ClientNewDTO;
import com.rodolfoguerra.cursomc.model.Client;
import com.rodolfoguerra.cursomc.model.enums.ClientType;
import com.rodolfoguerra.cursomc.repositories.ClientRepository;
import com.rodolfoguerra.cursomc.services.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidation implements ConstraintValidator<ClientInsert, ClientNewDTO> {

    private final ClientRepository clientRepository;

    public ClientInsertValidation(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void initialize(ClientInsert ann) {
    }

    @Override
    public boolean isValid(ClientNewDTO clientNewDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (clientNewDTO.getType().equals(ClientType.PESSOA_FISICA.getCode()) && Boolean.FALSE.equals(BR.isValidCPF(clientNewDTO.getCpfOrCnpj()))) {
            list.add(new FieldMessage("cpfOrCnpj", "CPF inválido"));
        }

        if (clientNewDTO.getType().equals(ClientType.PESSOA_JURIDICA.getCode()) && Boolean.FALSE.equals(BR.isValidCNPJ(clientNewDTO.getCpfOrCnpj()))) {
            list.add(new FieldMessage("cpfOrCnpj", "CNPJ inválido"));
        }

        Client client = clientRepository.findByEmail(clientNewDTO.getEmail());
        if (client != null) {
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