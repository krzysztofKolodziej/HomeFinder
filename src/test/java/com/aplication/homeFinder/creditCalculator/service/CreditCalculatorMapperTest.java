package com.aplication.homeFinder.creditCalculator.service;

import com.aplication.homeFinder.creditCalculator.model.CreditCalculator;
import com.aplication.homeFinder.creditCalculator.service.dto.CreditCalculatorDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreditCalculatorMapperTest {
    @InjectMocks
    private CreditCalculatorMapper creditCalculatorMapper;

    @Test
    void shouldMapCreditCalculatorToCreditCalculatorDto() {
        //given
        CreditCalculator creditCalculatorTest = getCreditCalculator();
        CreditCalculatorDto creditCalculatorDtoTest = getCreditCalculatorDto();

        //when
        CreditCalculator creditCalculatorMap = creditCalculatorMapper.mapper(creditCalculatorDtoTest);

        //then
        assertEquals(creditCalculatorTest.getPropertyValue(), creditCalculatorMap.getPropertyValue());
        assertEquals(creditCalculatorTest.getDownPayment(), creditCalculatorMap.getDownPayment());
        assertEquals(creditCalculatorTest.getCreditAmount(), creditCalculatorMap.getCreditAmount());
        assertEquals(creditCalculatorTest.getRepaymentPeriod(), creditCalculatorMap.getRepaymentPeriod());
        assertEquals(creditCalculatorTest.getSourceOfIncome(), creditCalculatorMap.getSourceOfIncome());
        assertEquals(creditCalculatorTest.getContractDurationInMonth(), creditCalculatorMap.getContractDurationInMonth());
        assertEquals(creditCalculatorTest.isContinuityOfEmployment(), creditCalculatorMap.isContinuityOfEmployment());
        assertEquals(creditCalculatorTest.getMonthlyNetIncome(), creditCalculatorMap.getMonthlyNetIncome());
        assertEquals(creditCalculatorTest.getMonthlyExpenditures(), creditCalculatorMap.getMonthlyExpenditures());
        assertEquals(creditCalculatorTest.isDelayInLoanRepayment(), creditCalculatorMap.isDelayInLoanRepayment());
        assertEquals(creditCalculatorTest.getNumberOfDependents(), creditCalculatorMap.getNumberOfDependents());
        assertEquals(creditCalculatorTest.getMonthlyAmountOtherLoans(), creditCalculatorMap.getMonthlyAmountOtherLoans());
        assertEquals(creditCalculatorTest.getCreditCardLimit(), creditCalculatorMap.getCreditCardLimit());
        assertEquals(creditCalculatorTest.getDateOfBirth(), creditCalculatorMap.getDateOfBirth());
        assertEquals(creditCalculatorTest.getPhoneNumber(), creditCalculatorMap.getPhoneNumber());
        assertEquals(creditCalculatorTest.getName(), creditCalculatorMap.getName());
    }


    private static CreditCalculatorDto getCreditCalculatorDto() {
        return CreditCalculatorDto.builder()
                .propertyValue(825000)
                .downPayment(200000)
                .creditAmount(625000)
                .repaymentPeriod(30)
                .sourceOfIncome(SourceOfIncome.ORDER_CONTRACT)
                .contractDurationInMonth(34)
                .continuityOfEmployment(true)
                .monthlyNetIncome(12000)
                .monthlyExpenditures(3000)
                .delayInLoanRepayment(false)
                .numberOfDependents(2)
                .monthlyAmountOtherLoans(500)
                .creditCardLimit(10000)
                .dateOfBirth(LocalDate.of(1985, 1, 15))
                .phoneNumber("123456789")
                .name("Jan")
                .build();
    }

    private static CreditCalculator getCreditCalculator() {
        CreditCalculator creditCalculator = new CreditCalculator();
        creditCalculator.setPropertyValue(825000);
        creditCalculator.setDownPayment(200000);
        creditCalculator.setCreditAmount(625000);
        creditCalculator.setRepaymentPeriod(30);
        creditCalculator.setSourceOfIncome(SourceOfIncome.ORDER_CONTRACT);
        creditCalculator.setContractDurationInMonth(34);
        creditCalculator.setContinuityOfEmployment(true);
        creditCalculator.setMonthlyNetIncome(12000);
        creditCalculator.setMonthlyExpenditures(3000);
        creditCalculator.setDelayInLoanRepayment(false);
        creditCalculator.setNumberOfDependents(2);
        creditCalculator.setMonthlyAmountOtherLoans(500);
        creditCalculator.setCreditCardLimit(10000);
        creditCalculator.setDateOfBirth(LocalDate.of(1985, 1, 15));
        creditCalculator.setPhoneNumber("123456789");
        creditCalculator.setName("Jan");
        return creditCalculator;
    }

}