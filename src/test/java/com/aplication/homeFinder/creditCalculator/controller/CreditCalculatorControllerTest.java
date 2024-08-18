package com.aplication.homeFinder.creditCalculator.controller;

import com.aplication.homeFinder.creditCalculator.service.SourceOfIncome;
import com.aplication.homeFinder.creditCalculator.service.dto.CreditCalculatorDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CreditCalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    void shouldSaveOfferDtoAndReturnOfferDto() throws Exception {
        //given
        CreditCalculatorDto creditCalculatorDto = getCreditCalculatorDto();

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/creditCalculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creditCalculatorDto)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string("601237"));
    }

    @Test
    @Transactional
    void shouldReturnBadRequestWhenCreditCalculatorDtoIsNull() throws Exception {
        //when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/creditCalculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenFieldPropertyValueIsGreaterOrEqualsThanZero() throws Exception {
        //given
        CreditCalculatorDto creditCalculatorDto = getCreditCalculatorDto();
        creditCalculatorDto.setPropertyValue(-1000000);

        //when & then
        mockMvc.perform(post("/creditCalculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creditCalculatorDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .content().string("{\"status\":\"BAD_REQUEST\",\"message\":\"Value must be greater than or equal to zero\"}"));
    }

    @Test
    void shouldReturnBadRequestWhenFieldDownPaymentIsGreaterOrEqualsThanZero() throws Exception {
        //given
        CreditCalculatorDto creditCalculatorDto = getCreditCalculatorDto();
        creditCalculatorDto.setDownPayment(-1000000);

        //when & then
        mockMvc.perform(post("/creditCalculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creditCalculatorDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .content().string("{\"status\":\"BAD_REQUEST\",\"message\":\"Value must be greater than or equal to zero\"}"));
    }

    @Test
    void shouldReturnBadRequestWhenFieldCreditAmountIsGreaterOrEqualsThanZero() throws Exception {
        //given
        CreditCalculatorDto creditCalculatorDto = getCreditCalculatorDto();
        creditCalculatorDto.setCreditAmount(-1000000);

        //when & then
        mockMvc.perform(post("/creditCalculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creditCalculatorDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .content().string("{\"status\":\"BAD_REQUEST\",\"message\":\"Value must be greater than or equal to zero\"}"));
    }

    @Test
    void shouldReturnBadRequestWhenFieldRepaymentPeriodIsGreaterOrEqualsThanZero() throws Exception {
        //given
        CreditCalculatorDto creditCalculatorDto = getCreditCalculatorDto();
        creditCalculatorDto.setRepaymentPeriod(0);

        //when & then
        mockMvc.perform(post("/creditCalculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creditCalculatorDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .content().string("{\"status\":\"BAD_REQUEST\",\"message\":\"Value must be greater than or equal to one\"}"));
    }

    @Test
    void shouldReturnBadRequestWhenSourceOfIncomeIsDifferThanEnum() throws Exception {
        //given
        String invalidJsonWithFieldSourceOfIncome = getInvalidJsonWithFieldSourceOfIncome();

        //when & then
        mockMvc.perform(post("/creditCalculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJsonWithFieldSourceOfIncome))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .content().string("{\"status\":\"BAD_REQUEST\",\"message\":\"Invalid JSON format\"}"));
    }

    @Test
    void shouldReturnBadRequestWhenFieldSourceOfIncomeIsNull() throws Exception {
        //given
        CreditCalculatorDto creditCalculatorDto = getCreditCalculatorDto();
        creditCalculatorDto.setSourceOfIncome(null);

        //when & then
        mockMvc.perform(post("/creditCalculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creditCalculatorDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .content().string("{\"status\":\"BAD_REQUEST\",\"message\":\"Value must not be null\"}"));
    }

    @Test
    void shouldReturnBadRequestWhenFieldContractDurationIsGreaterOrEqualsThanZero() throws Exception {
        //given
        CreditCalculatorDto creditCalculatorDto = getCreditCalculatorDto();
        creditCalculatorDto.setContractDurationInMonth(3);

        //when & then
        mockMvc.perform(post("/creditCalculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creditCalculatorDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .content().string("{\"status\":\"BAD_REQUEST\",\"message\":\"Value must be greater than or equal to thirty-three\"}"));
    }

    @Test
    void shouldReturnBadRequestWhenFieldNumberOfDependentsIsGreaterOrEqualsThanZero() throws Exception {
        //given
        CreditCalculatorDto creditCalculatorDto = getCreditCalculatorDto();
        creditCalculatorDto.setNumberOfDependents(-1);

        //when & then
        mockMvc.perform(post("/creditCalculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creditCalculatorDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .content().string("{\"status\":\"BAD_REQUEST\",\"message\":\"Value must be greater than or equal to zero\"}"));
    }

    @Test
    void shouldReturnBadRequestWhenFieldMonthlyAmountOtherLoansIsGreaterOrEqualsThanZero() throws Exception {
        //given
        CreditCalculatorDto creditCalculatorDto = getCreditCalculatorDto();
        creditCalculatorDto.setMonthlyAmountOtherLoans(-1);

        //when & then
        mockMvc.perform(post("/creditCalculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creditCalculatorDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .content().string("{\"status\":\"BAD_REQUEST\",\"message\":\"Value must be greater than or equal to zero\"}"));
    }

    @Test
    void shouldReturnBadRequestWhenFieldCreditCardLimitIsGreaterOrEqualsThanZero() throws Exception {
        //given
        CreditCalculatorDto creditCalculatorDto = getCreditCalculatorDto();
        creditCalculatorDto.setCreditCardLimit(-1);

        //when & then
        mockMvc.perform(post("/creditCalculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creditCalculatorDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .content().string("{\"status\":\"BAD_REQUEST\",\"message\":\"Value must be greater than or equal to zero\"}"));
    }

    @Test
    void shouldReturnBadRequestWhenDateOfBirthIsNull() throws Exception {
        //given
        CreditCalculatorDto creditCalculatorDto = getCreditCalculatorDto();
        creditCalculatorDto.setDateOfBirth(null);

        //when & then
        mockMvc.perform(post("/creditCalculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creditCalculatorDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .content().string("{\"status\":\"BAD_REQUEST\",\"message\":\"Value must not be null\"}"));
    }

    @Test
    void shouldReturnBadRequestWhenDateOfBirthIsPast() throws Exception {
        //given
        CreditCalculatorDto creditCalculatorDto = getCreditCalculatorDto();
        creditCalculatorDto.setDateOfBirth(LocalDate.of(2029,11,4));

        //when & then
        mockMvc.perform(post("/creditCalculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creditCalculatorDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .content().string("{\"status\":\"BAD_REQUEST\",\"message\":\"Date must be past\"}"));
    }

    @Test
    void shouldReturnBadRequestWhenPhoneNumberIsNull() throws Exception {
        //given
        CreditCalculatorDto creditCalculatorDto = getCreditCalculatorDto();
        creditCalculatorDto.setPhoneNumber(null);

        //when & then
        mockMvc.perform(post("/creditCalculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creditCalculatorDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .content().string("{\"status\":\"BAD_REQUEST\",\"message\":\"Value must not be null\"}"));
    }

    @Test
    void shouldReturnBadRequestWhenPhoneNumberIsEmpty() throws Exception {
        //given
        CreditCalculatorDto creditCalculatorDto = getCreditCalculatorDto();
        creditCalculatorDto.setPhoneNumber("    ");

        //when & then
        mockMvc.perform(post("/creditCalculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creditCalculatorDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .content().string("{\"status\":\"BAD_REQUEST\",\"message\":\"Invalid phone number\"}"));
    }

    @Test
    @Transactional
    void shouldReturnBadRequestWhenPhoneNumberIsInvalid() throws Exception {
        //given
        CreditCalculatorDto creditCalculatorDto = getCreditCalculatorDto();
        creditCalculatorDto.setPhoneNumber("444333abc");

        //when & then
        mockMvc.perform(post("/creditCalculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creditCalculatorDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .content().string("{\"status\":\"BAD_REQUEST\",\"message\":\"Invalid phone number\"}"));
    }

    @Test
    void shouldReturnBadRequestWhenNameIsNull() throws Exception {
        //given
        CreditCalculatorDto creditCalculatorDto = getCreditCalculatorDto();
        creditCalculatorDto.setName(null);

        //when & then
        mockMvc.perform(post("/creditCalculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creditCalculatorDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .content().string("{\"status\":\"BAD_REQUEST\",\"message\":\"Value must not be empty\"}"));
    }

    @Test
    void shouldReturnBadRequestWhenNameIsEmpty() throws Exception {
        //given
        CreditCalculatorDto creditCalculatorDto = getCreditCalculatorDto();
        creditCalculatorDto.setName("    ");

        //when & then
        mockMvc.perform(post("/creditCalculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creditCalculatorDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .content().string("{\"status\":\"BAD_REQUEST\",\"message\":\"Value must not be empty\"}"));
    }

    private static CreditCalculatorDto getCreditCalculatorDto() {
        CreditCalculatorDto creditCalculatorDto = new CreditCalculatorDto();
        creditCalculatorDto.setPropertyValue(825000);
        creditCalculatorDto.setDownPayment(200000);
        creditCalculatorDto.setCreditAmount(625000);
        creditCalculatorDto.setRepaymentPeriod(30);
        creditCalculatorDto.setSourceOfIncome(SourceOfIncome.ORDER_CONTRACT);
        creditCalculatorDto.setContractDurationInMonth(34);
        creditCalculatorDto.setContinuityOfEmployment(true);
        creditCalculatorDto.setMonthlyNetIncome(12000);
        creditCalculatorDto.setMonthlyExpenditures(3000);
        creditCalculatorDto.setDelayInLoanRepayment(false);
        creditCalculatorDto.setNumberOfDependents(2);
        creditCalculatorDto.setMonthlyAmountOtherLoans(500);
        creditCalculatorDto.setCreditCardLimit(10000);
        creditCalculatorDto.setDateOfBirth(LocalDate.of(1985, 1, 15));
        creditCalculatorDto.setPhoneNumber("123456789");
        creditCalculatorDto.setName("Jan");
        return creditCalculatorDto;
    }

    private static String getInvalidJsonWithFieldSourceOfIncome() {
        return """
               {
                   "propertyValue": 825000,
                   "downPayment": 200000,
                   "creditAmount": 625000,
                   "repaymentPeriod": 30,
                   "sourceOfIncome": "invalid source of income",
                   "contractDurationInMonth": 34,
                   "continuityOfEmployment": true,
                   "monthlyNetIncome": 12000,
                   "monthlyExpenditures": 3000,
                   "delayInLoanRepayment": false,
                   "numberOfDependents": 2,
                   "monthlyAmountOtherLoans": 500,
                   "creditCardLimit": 10000,
                   "dateOfBirth": "1985-01-15",
                   "phoneNumber": "123456789",
                   "name": "Jan"
               }""";
    }


}