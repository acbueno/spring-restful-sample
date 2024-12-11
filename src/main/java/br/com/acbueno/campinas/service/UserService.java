package br.com.acbueno.campinas.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.acbueno.campinas.dto.UserRequestDTO;
import br.com.acbueno.campinas.dto.UserResponseDTO;
import br.com.acbueno.campinas.model.User;
import br.com.acbueno.campinas.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;

  public Page<UserResponseDTO> findAll(Pageable pageable) {
    //@formatter:off
    return userRepository.findAll(pageable)
        .map(user -> modelMapper.map(user, UserResponseDTO.class));
    //@fotmatter:on
  }
  
  @Transactional
  public UserResponseDTO save(UserRequestDTO request) {
    User user = userRepository.save(modelMapper.map(request, User.class));
    return modelMapper.map(user, UserResponseDTO.class);
  }
  
  public UserResponseDTO findUserById(Long id) {
    return modelMapper.map(userRepository.findById(id), UserResponseDTO.class);
  }
  
  @Transactional
  public void deleteUserById(Long id) {
   userRepository.deleteById(id);  
  }
  
  @Transactional
  public UserResponseDTO updateUserById(Long id, UserRequestDTO request) {
    User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("Not found user id"));
    user.setName(request.getName());
    user.setEmail(user.getEmail());
    User userSaved = userRepository.save(user);
    
    return modelMapper.map(userSaved, UserResponseDTO.class);
  }

}
