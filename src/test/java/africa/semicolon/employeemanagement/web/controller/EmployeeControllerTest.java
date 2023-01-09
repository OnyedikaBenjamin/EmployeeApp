package africa.semicolon.employeemanagement.web.controller;

import africa.semicolon.employeemanagement.data.dto.response.EmployeeResponse;
import africa.semicolon.employeemanagement.data.repository.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts="/db/insert.sql")
class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Autowired
    EmployeeRepository employeeRepository;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Test get employees api")
    void getAllEmployeesRecord() throws Exception {
        mockMvc.perform(get("/api/employee/getRecords")
                        .contentType("application/json"))
                .andExpect(status().is(200))
                .andDo(print());
    }

    @Test
    void createNewEmployee() throws Exception {
        EmployeeResponse employeeDto = new EmployeeResponse();
        employeeDto.setFirstName("Toye");
        employeeDto.setLastName("David");
        employeeDto.setEmail("lalaland@gmail.com");
        employeeDto.setAge(28);

        String requestBody = objectMapper.writeValueAsString(employeeDto);
        mockMvc.perform(post("/api/employee/create")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().is(200))
                .andDo(print());
    }

    @Test
    void updateEmployeeRecord() throws Exception {
        mockMvc.perform(patch("/api/employee/update/precious@gmail.com")
                        .contentType("application/json-patch+json")
                        .content(Files.readAllBytes(Path.of("payload.json"))))
                .andExpect(status().is(200))
                .andDo(print());
    }

    @Test
    void deleteEmployeeRecord() throws Exception {
        mockMvc.perform(delete("/api/employee/delete/2")
                        .contentType("application/json"))
                .andExpect(status().is(200))
                .andDo(print());
    }

    @Test
    void getSingleEmployee() throws Exception {
        mockMvc.perform(get("/api/employee/single/3")
                        .contentType("application/json"))
                .andExpect(status().is(200))
                .andDo(print());
    }
}