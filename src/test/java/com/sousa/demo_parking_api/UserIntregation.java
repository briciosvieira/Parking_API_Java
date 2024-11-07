package com.sousa.demo_parking_api;


import com.sousa.demo_parking_api.web.Dto.UserCreateDto;
import com.sousa.demo_parking_api.web.Dto.responseDto.UserResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/users/users-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/users/users-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserIntregation {

    @Autowired
    WebTestClient testClient;

    @Test
    public void createUsers_withUsernameAnsPasswordInvalid_returnCreatedUserAndStatus201(){
        UserResponseDto responseBody = testClient.post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserCreateDto("fabriciVVo@gmail.com", "123456"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(UserResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getUsername()).isEqualTo("fabriciVVo@gmail.com");
        Assertions.assertThat(responseBody.getRole()).isEqualTo("CLIENTE");
    }
}
