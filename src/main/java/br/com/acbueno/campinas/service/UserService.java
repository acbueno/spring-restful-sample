package br.com.acbueno.campinas.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.acbueno.campinas.dto.UserRequestDTO;
import br.com.acbueno.campinas.dto.UserResponseDTO;
import br.com.acbueno.campinas.model.User;
import br.com.acbueno.campinas.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;

  public List<UserResponseDTO> findAll() {
    //@formatter:off
    return userRepository.findAll().stream()
        .map(user -> modelMapper.map(user, UserResponseDTO.class))
        .toList();
    //@fotmatter:on
  }
  
  public UserResponseDTO save(UserRequestDTO request) {
    User user = userRepository.save(modelMapper.map(request, User.class));
    return modelMapper.map(user, UserResponseDTO.class);
  }
  
  public UserResponseDTO findUserById(Long id) {
    return modelMapper.map(userRepository.findById(id), UserResponseDTO.class);
  }
  
  public void deleteUserById(Long id) {
   userRepository.deleteById(id);  
  }
  
  public UserResponseDTO updateUserById(Long id, UserRequestDTO request) {
    User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("Not found user id"));
    user.setName(request.getName());
    user.setEmail(user.getEmail());
    User userSaved = userRepository.save(user);
    
    return modelMapper.map(userSaved, UserResponseDTO.class);
  }

}
