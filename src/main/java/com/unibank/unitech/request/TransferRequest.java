package com.unibank.unitech.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
    long accountFrom;
    long accountTo;
    double amount;
}
