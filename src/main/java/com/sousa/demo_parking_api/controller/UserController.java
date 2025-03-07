package com.sousa.demo_parking_api.controller;

import com.sousa.demo_parking_api.entity.User;
import com.sousa.demo_parking_api.service.UserService;
import com.sousa.demo_parking_api.web.Dto.userDto.UpdatePasswordDto;
import com.sousa.demo_parking_api.web.Dto.userDto.UserCreateDto;
import com.sousa.demo_parking_api.web.exception.ErrorMessageException;
import com.sousa.demo_parking_api.web.mapper.UserModelMapper;
import com.sousa.demo_parking_api.web.Dto.responseDto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Crud para usuários")
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @Operation(summary = "Criar um novo usuário", description = "Api desenvolvida para estudos",
        responses = {
            @ApiResponse(responseCode = "201", description = "Recursos criado com sucesso",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "409", description = "E-mail já cadastrado",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessageException.class))),
            @ApiResponse(responseCode = "422", description = "Recursos não processado por dados invalidos",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessageException.class)))
        })

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserCreateDto userdto){
        User users = service.save(UserModelMapper.toUser(userdto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserModelMapper.ToDto(users));
    }


    @Operation(summary = "Buscar usuário pelo ID",description = "Api desenvolvida para estudos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessageException.class)))
            })

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<UserResponseDto> findById(@PathVariable Long id ){
        User user = service.findById(id);
        return ResponseEntity.ok(UserModelMapper.ToDto(user));
    }


    @Operation(summary = "Listar todos os usuários",description = "Api desenvolvida para estudos",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema( schema = @Schema(implementation = UserResponseDto.class)))),
                    @ApiResponse(responseCode = "204", description = "Lista vazia",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessageException.class))),
            })

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDto>> getAll(){
        List<User> users = service.getAll();
        return ResponseEntity.ok(UserModelMapper.toListDto(users)) ;
    }

    @Operation(summary = "Arualizar senha do usuário",description = "Api desenvolvida para estudos",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Senha atualizada",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessageException.class))),
                    @ApiResponse(responseCode = "400", description = "Verifique as senhas e tente novamente",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessageException.class)))
            })

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || (hasRole('CLIENTE') AND #id == principal.id)")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id ,@Valid @RequestBody UpdatePasswordDto dto){
        service.patchPassword(id, dto.getPassword(), dto.getNewPassword(), dto.getConfirmNewPassword());
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Deletar usuário",description = "Api desenvolvida para estudos",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Usuario deletado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "usuário não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessageException.class)))
            })

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
          service.findById(id);
          service.delete(id);
          return ResponseEntity.noContent().build();
    }
}
