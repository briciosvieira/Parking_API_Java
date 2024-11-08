package com.sousa.demo_parking_api;


import com.sousa.demo_parking_api.web.Dto.UpdatePasswordDto;
import com.sousa.demo_parking_api.web.Dto.UserCreateDto;
import com.sousa.demo_parking_api.web.Dto.responseDto.UserResponseDto;
import com.sousa.demo_parking_api.web.exception.ErrorMessageExc;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/users/users-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/users/users-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserIntregation {

    @Autowired
    WebTestClient testClient;

    @Test
    public void createUsers_withUsernameAndPasswordInvalid_returnCreatedUserAndStatus201(){
        UserResponseDto responseBody = testClient.post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserCreateDto("fabricio@gmail.com", "123456"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(UserResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getUsername()).isEqualTo("fabricio@gmail.com");
        Assertions.assertThat(responseBody.getRole()).isEqualTo("CLIENTE");
    }


    @Test
    public void createUsers_withUsernameInvalid_returnErroMessage422(){
        ErrorMessageExc responseBody = testClient.post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserCreateDto("", "123456"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessageExc.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);


        responseBody = testClient.post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserCreateDto("fabricio@", "123456"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessageExc.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);


        responseBody = testClient.post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserCreateDto("fabricio@gmail.c", "123456"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessageExc.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);


    }

    @Test
    public void infById_User_with_Status_code200(){
        UserResponseDto responseBody = testClient.get()
                .uri("/api/v1/users/100")
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getUsername()).isEqualTo("fabriciotest@gmail.com");
        Assertions.assertThat(responseBody.getRole()).isEqualTo("ADMIN");
    }


  @Test
    public void infById_User_with_ErrorMessage_Status_code404(){
        ErrorMessageExc responseBody = testClient.get()
                .uri("/api/v1/users/109")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorMessageExc.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
    }

@Test
    public void updateUers_WitchReturn_Status204(){
        testClient.put()
                .uri("/api/v1/users/100")
                .bodyValue(new UpdatePasswordDto("123456", "1234567","1234567"))
                .exchange()
                .expectStatus().isNoContent();

    }

@Test
    public void updateUers_WitchReturn_Status404(){
        testClient.put()
                .uri("/api/v1/users/0")
                .bodyValue(new UpdatePasswordDto("123456", "1234567","1234567"))
                .exchange()
                .expectStatus().isNotFound();

        testClient.put()
                .uri("/api/v1/users/100")
                .bodyValue(new UpdatePasswordDto("123456", "123456","1234567"))
                .exchange()
                .expectStatus().isBadRequest();

    }

    @Test
    public void deleteUser_Status204(){
        testClient.delete()
                .uri("/api/v1/users/100")
                .exchange()
                .expectStatus().isNoContent();

    }

    @Test
    public void listUsers() {
        List<UserResponseDto> responseBody = testClient.get()
                .uri("/api/v1/users")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(UserResponseDto.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody).isNotEmpty();

        for (UserResponseDto user : responseBody) {
            Assertions.assertThat(user.getUsername()).isNotNull();
        }


    }

}
