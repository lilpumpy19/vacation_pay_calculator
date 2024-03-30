package com.example.vacation_pay_calculator;



import com.example.vacation_pay_calculator.controller.CalculatorController;
import com.example.vacation_pay_calculator.model.VacationPayRequest;
import com.example.vacation_pay_calculator.service.calculator.CalculatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CalculatorController.class)
@AutoConfigureMockMvc
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CalculatorService calculatorService;

    @SuppressWarnings("null")
    @Test
    void calculateVacationPay_WithValidRequest_ShouldReturnOk() throws Exception {
        VacationPayRequest request = new VacationPayRequest(16000.0, 14, null);
        double expectedResult = 16000.0 * 14 / 29.3;

        Mockito.when(calculatorService.calculateVacationPay(Mockito.any(VacationPayRequest.class)))
                .thenReturn(expectedResult);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(expectedResult)));
    }

    @SuppressWarnings("null")
    @Test
    void calculateVacationPay_WithInvalidRequest_ShouldReturnBadRequest() throws Exception {
        VacationPayRequest request = new VacationPayRequest(-500.0, 0, null);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
