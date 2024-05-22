package com.aplication.homeFinder.creditCalculator.model;

import com.aplication.homeFinder.creditCalculator.service.SourceOfIncome;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditCalculator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int propertyValue;
    private int downPayment;
    private int creditAmount;
    private int repaymentPeriod;
    @Enumerated(EnumType.STRING)
    private SourceOfIncome sourceOfIncome;
    private int contractDurationInMonth;
    private boolean continuityOfEmployment;
    private int monthlyNetIncome;

    private int monthlyExpenditures;
    private boolean delayInLoanRepayment;
    private int numberOfDependents;
    private int monthlyAmountOtherLoans;
    private int creditCardLimit;

    private LocalDate dateOfBirth;
    private int phoneNumber;
    private String name;

}
