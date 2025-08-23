package com.alves.vitor.DigitalAccounts.infra.controller.mappers;

import com.alves.vitor.DigitalAccounts.domain.entity.Account;
import com.alves.vitor.DigitalAccounts.domain.entity.Operation;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.AccountDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.OperationDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.request.OperationAccountRequestCreateDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.request.OperationRequestCreateDTO;
import org.springframework.stereotype.Component;

@Component
public class OperationMapper {

    public Operation toDomain(OperationRequestCreateDTO dto) {
        OperationAccountRequestCreateDTO accountDTO = dto.getAccountDTO();

        Account account = new Account(accountDTO.getAgency(), accountDTO.getNumber());

        return new Operation(
                account,
                dto.getOperationType(),
                dto.getQuantia()
        );
    }

    public OperationDTO toDTO(Operation domain) {
        Account account = domain.getAccount();

        return new OperationDTO(
                new AccountDTO(account.getAgency(), account.getNumber(),
                        account.getTotalAmount(), account.getCurrency()),
                domain.getType(),
                domain.getAmount(),
                domain.getTime()
        );
    }
}
