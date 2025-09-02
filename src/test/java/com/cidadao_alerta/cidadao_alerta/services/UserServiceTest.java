package com.cidadao_alerta.cidadao_alerta.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.cidadao_alerta.cidadao_alerta.models.dtos.UserDTORequest;
import com.cidadao_alerta.cidadao_alerta.models.dtos.UserDTOResponse;
import com.cidadao_alerta.cidadao_alerta.models.entities.UserEntity;
import com.cidadao_alerta.cidadao_alerta.models.enums.Role;
import com.cidadao_alerta.cidadao_alerta.models.mappers.UserMapper;
import com.cidadao_alerta.cidadao_alerta.repositories.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private UserMapper userMapper;

  @InjectMocks // injeta o mock das dependencias do userService
  private UserService userService;

  private List<UserEntity> usersDatabaseTest = new ArrayList<>();

  @BeforeEach // esse metodo sera executado antes dos testes para iniciar os mocks
  void setup() {
    this.usersDatabaseTest = List.of(
      createUser("1f97b5d8-b21e-4b68-87a3-2c7a3c3b8c35", "Alice Silva", "alice.silva@example.com", Role.CITIZEN, "senha123"),
      createUser("8c3b7f1e-5a24-4e42-9a3d-9f4a1b2c3d4e", "Bruno Costa", "bruno.costa@example.com", Role.CITIZEN, "senha456"),
      createUser("4a2d8e9c-6b3a-4f8e-bd3a-7e8c9d0a1b2c", "Carla Martins", "carla.martins@example.com", Role.ADMIN, "senha789"),
      createUser("d2c1b0a9-8e7d-4c6b-a1b0-9f8e7d6c5b4a", "Daniel Andrade", "daniel.andrade@example.com", Role.CITIZEN, "senha101"),
      createUser("e5f4b3a2-1c0d-4e8f-b7a6-f5b4c3d2a1e0", "Elisa Ferreira", "elisa.ferreira@example.com", Role.CITIZEN, "senha202")
    );
  }

  @Test
  @DisplayName("Caso de sucesso de criacao de usuario")
  void testCreateNewUserSuccess() {
    Role role = Role.ADMIN;
    String name = "usuario admin";
    String email = "admin@gmail.com";
    String password = "senha123";

    UserDTORequest userDTO = new UserDTORequest(
      role, 
      name, 
      email, 
      password
    );
    
    UserEntity newUserEntity = new UserEntity();
    newUserEntity.setRole(role);
    newUserEntity.setName(name);
    newUserEntity.setEmail(email);
    newUserEntity.setPassword(password);

    UserEntity newUserEntityWithId = this.createUser("9edb1613-c59b-4296-8f45-44175065c949", name, email, role, password);

    // Quando o mapper.toEntity for chamado com o DTO, retorne a entidade sem ID
    when(this.userMapper.toEntity(userDTO)).thenReturn(newUserEntity);

    // diz ao moquito oq fazer quando o user repository for chamado no userService
    when(this.userRepository.save(newUserEntity)).thenReturn(newUserEntityWithId);

    // executar o metodo a ser testado
    UserEntity result = this.userService.createNewUser(userDTO);
    
    // verifica a chamada do usermapper
    verify(this.userMapper, times(1)).toEntity(userDTO);

    // verifica se o repository foi chamado corretamente
    verify(this.userRepository, times(1)).save(any());

    assertThat(result).isNotNull();
    assertThat(result).usingRecursiveComparison().isEqualTo(newUserEntityWithId);
    assertThat(result.getComments()).isEqualTo(Collections.emptySet());
  }

  @Test
  @DisplayName("Caso de erro, email já existente")
  void testCreateNewUserFailedEmailExists() throws ResponseStatusException {
    UserEntity userExists = this.usersDatabaseTest.get(0);
    UserDTORequest userDTO = new UserDTORequest(
      userExists.getRole(),
      userExists.getName(),
      userExists.getEmail(),
      userExists.getPassword()
    );
    UserEntity newUserEntity = new UserEntity();
    newUserEntity.setRole(userExists.getRole());
    newUserEntity.setName(userExists.getName());
    newUserEntity.setEmail(userExists.getEmail());
    newUserEntity.setPassword(userExists.getPassword());

    when(this.userMapper.toEntity(userDTO)).thenReturn(userExists);
    when(
      this.userRepository.findByEmail(userExists.getEmail())
    ).thenReturn(Optional.of(userExists));

    ResponseStatusException exception = Assertions.assertThrows(
      ResponseStatusException.class,
      () -> this.userService.createNewUser(userDTO)
    );

    assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    assertThat(exception.getReason()).isEqualTo("Já existe um usuário com esse email!");

    verify(this.userMapper, times(1)).toEntity(userDTO);
    verify(this.userRepository, times(1)).findByEmail(any(String.class));
    verify(this.userRepository, never()).save(any(UserEntity.class));
  }

  @Test
  @DisplayName("Caso de sucesso, retornar todos os usuarios do banco no formato de response dto.")
  void testGetAllUsersSuccess() {
    // 1 - arrange - preparar, organizar
    List<UserEntity> usuariosDb = new ArrayList<>(this.usersDatabaseTest);
    List<UserDTOResponse> usuariosDto = usuariosDb.stream().map(
      usuario -> this.createDefaultUserDTOResponse(usuario)
    ).toList();

    when(this.userRepository.findAll()).thenReturn(usuariosDb);
    when(this.userMapper.toDTO(any(UserEntity.class))).thenAnswer(
      invocation -> this.createDefaultUserDTOResponse(invocation.getArgument(0))
    );

    // 2 - act - agir, executar
    List<UserDTOResponse> result = this.userService.getAllUsers();

    // 3 - asser - verificar, afirmar
    assertThat(result).as("Resultado do service não deveria ser null").isNotNull();
    assertThat(result).as("O tamanho do array de dto's nao corresponde ao tamanho esperado").hasSize(usuariosDto.size());
    // assertThat(result).as("Campos não correspondem com os passados").isEqualTo(usuariosDto);
    // usingRecursiveComparison compara cada campo em vez da referencia da memoria
    assertThat(result).as("Campos não correspondem com os passados").usingRecursiveComparison().isEqualTo(usuariosDto);

    UserDTOResponse resultUser4 = result.get(3);
    assertThat(resultUser4.getId()).as("Id nao corresponde ao esperado").isEqualTo(UUID.fromString("d2c1b0a9-8e7d-4c6b-a1b0-9f8e7d6c5b4a"));
    assertThat(resultUser4.getEmail()).as("Email nao corresponde ao esperado").isEqualTo("daniel.andrade@example.com");
    assertThat(resultUser4.getName()).as("Nome nao corresponde ao esperado").isEqualTo("Daniel Andrade");
    assertThat(resultUser4.getRole()).as("Role nao corresponde ao esperado").isEqualTo(Role.CITIZEN);
    
    // verificar o comportamento das interacoes
    verify(this.userRepository, times(1)).findAll();
    verify(this.userMapper, times(usuariosDb.size())).toDTO(any(UserEntity.class));
  }

  @Test
  @DisplayName("Realizar um getAllUsers sem ter usuários cadastrados na api.")
  void testGetAllUsersDbEmpty() {
    when(userRepository.findAll()).thenReturn(Collections.emptyList());

    List<UserDTOResponse> result = this.userService.getAllUsers();

    assertThat(result).as("Resultado do service não deveria ser null").isNotNull();
    assertThat(result).as("Resultado do service deve ser um array vazio").isEmpty();
    // assertThat(result).as("O service deve retornar um arraylist vazio.").isEqualTo(usersDTO);

    verify(this.userRepository, times(1)).findAll();
    verify(this.userMapper, never()).toDTO(any(UserEntity.class));
  }

  @Test
  @DisplayName("Caso de sucesso, deletar um usuario")
  void testDeleteSuccess() throws ResponseStatusException {
    List<UserEntity> usuariosDb = new ArrayList<>(this.usersDatabaseTest);
    UserEntity userDelete = usuariosDb.get(0);
    UUID iduserDelete = userDelete.getId();

    when(userRepository.findById(iduserDelete)).thenReturn(Optional.of(userDelete));
    doNothing().when(userRepository).delete(userDelete);

    UserEntity result = this.userService.delete(iduserDelete);

    assertThat(result).as("O resultado não pode ser null").isNotNull();
    assertThat(result).as("Usuario diferente do esperado").usingRecursiveComparison().isEqualTo(userDelete);

    verify(userRepository, times(1)).findById(iduserDelete);
    verify(userRepository, times(1)).delete(userDelete);
  }

  @Test
  @DisplayName("Caso de erro, tentar deletar um usuario que não existe")
  void testDeleteFailed() throws ResponseStatusException {
    List<UserEntity> usuariosDb = new ArrayList<>(this.usersDatabaseTest);
    UserEntity userDelete = usuariosDb.get(0);
    UUID iduserDelete = UUID.fromString("2ea4fa74-e1fc-4ee0-92dd-dd7989aaee72");

    when(userRepository.findById(iduserDelete)).thenReturn(Optional.empty());

    ResponseStatusException exception = Assertions.assertThrows(
      ResponseStatusException.class,
      () -> this.userService.delete(iduserDelete)
    );

    assertThat(exception.getStatusCode()).as("O status code esperado eh not found").isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(exception.getReason()).as("A mensagem esperada não bate com o que foi lançado").isEqualTo("Nenhum usuário encontrado!");

    verify(userRepository, times(1)).findById(iduserDelete);
    verify(userRepository, never()).delete(userDelete);
  }

  @Test
  @DisplayName("Caso de sucesso, usuario buscado com sucesso")
  void testGetUserByIdSuccess() {
    List<UserEntity> usuariosDb = new ArrayList<>(this.usersDatabaseTest);
    UserEntity findUser = usuariosDb.get(0);
    UUID userId = findUser.getId();
    UserDTOResponse userMap = this.createDefaultUserDTOResponse(findUser);

    when(userRepository.findById(userId)).thenReturn(Optional.of(findUser));
    when(this.userMapper.toDTO(findUser)).thenReturn(userMap);

    UserDTOResponse result = this.userService.getUserById(userId);

    assertThat(result).as("O resultado esperado não pode ser null").isNotNull();
    assertThat(result).as("O usuario retornado eh diferente do esperado").isEqualTo(userMap);

    verify(userRepository, times(1)).findById(userId);
    verify(userMapper, times(1)).toDTO(findUser);
  }

  @Test
  @DisplayName("Caso de erro, usuario nao encontrado")
  void testGetUserByIdFailed() {
    UUID userId = UUID.fromString("2ea4fa74-e1fc-4ee0-92dd-dd7989aaee72");

    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    ResponseStatusException exception = assertThrows(
      ResponseStatusException.class,
      () ->  this.userService.getUserById(userId)
    );

    assertThat(exception.getStatusCode()).as("O status code esperado eh not found").isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(exception.getReason()).as("A mensagem esperada não bate com o que foi lançado").isEqualTo("Nenhum usuário encontrado!");

    verify(userRepository, times(1)).findById(userId);
    verify(userMapper, never()).toDTO(any(UserEntity.class));
  }

  private UserDTOResponse createDefaultUserDTOResponse(UserEntity entity) {
    UserDTOResponse userDTO = new UserDTOResponse(
      entity.getId(),
      entity.getName(),
      entity.getEmail(),
      entity.getRole()
    );

    return userDTO;
  }

  private UserEntity createUser(String id, String name, String email, Role role, String password) {
    UserEntity user = new UserEntity();
    user.setId(UUID.fromString(id));
    user.setName(name);
    user.setEmail(email);
    user.setRole(role);
    user.setPassword(password);

    return user;
  }
}
