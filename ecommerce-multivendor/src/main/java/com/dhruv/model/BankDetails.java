package com.dhruv.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BankDetails {
    private String accountNumber;

    private String accountHolderName;

    private String ifsCode;
}
