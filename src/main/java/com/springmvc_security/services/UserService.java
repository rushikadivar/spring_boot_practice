package com.springmvc_security.services;

import com.springmvc_security.dto.UserRequestDto;
import com.springmvc_security.dto.UserResponseDto;
import com.springmvc_security.dto.UserSearchRequest;
import com.springmvc_security.entity.User;
import com.springmvc_security.exceptionhandling.UserAlreadyExist;
import com.springmvc_security.exceptionhandling.UserNotFoundException;
import com.springmvc_security.repository.UserRepository;
import com.springmvc_security.repository.UserSpecification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream().map(user -> new UserResponseDto(user.getId(), user.getUsername(), user.getEmail())).toList();
    }

    public UserResponseDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserResponseDto(user.getId(), user.getUsername(), user.getEmail()))
                .orElseThrow(() -> new UserNotFoundException("No user exist: Id=" + id));
    }

    public UserResponseDto getUserBy(UserSearchRequest userSearchRequest) {
//        if (Objects.nonNull(userSearchRequest.getEmail()) && Objects.nonNull(userSearchRequest.getUsername())) {
//            return userRepository.findUserByEmail(userSearchRequest.getEmail())
//                    .map(user -> new UserResponseDto(user.getId(), user.getUsername(), user.getEmail()))
//                    .orElseThrow(() -> new UserNotFoundException("No user exist: Email=" + userSearchRequest.getEmail()));
//        } else if (Objects.nonNull(userSearchRequest.getEmail())) {
//            return userRepository.findUserByEmail(userSearchRequest.getEmail())
//                    .map(user -> new UserResponseDto(user.getId(), user.getUsername(), user.getEmail()))
//                    .orElseThrow(() -> new UserNotFoundException("No user exist: Email=" + userSearchRequest.getEmail()));
//        }
//        return userRepository.findUserByUsername(userSearchRequest.getUsername())
//                .map(user -> new UserResponseDto(user.getId(), user.getUsername(), user.getEmail()))
//                .orElseThrow(() -> new UserNotFoundException("No user exist: username=" + userSearchRequest.getUsername()));

        UserSpecification userSpecification = new UserSpecification(userSearchRequest);
        return userRepository.findUserBy(userSpecification)
                .stream().findFirst().map(user -> new UserResponseDto(user.getId(), user.getUsername(), user.getEmail()))
                .orElseThrow(() -> new UserNotFoundException("No user exist:"));
    }

    public void save(UserRequestDto userDto) {
        if (userRepository.existsUserByEmail(userDto.getEmail()) || userRepository.existsUserByUsername(userDto.getUsername())) {
            throw new UserAlreadyExist("User already exists");
        }
//        if (!isPasswordStrong(userDto.getPassword())) {
//            throw new WeakPasswordException("password doesn't meet strength requirement");
//        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
    }

    private boolean isPasswordStrong(String password) {
//        return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$");
        return password.matches("");
    }

    //    @Transactional
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("No user exist: Id=" + id);
        }
        userRepository.deleteById(id);
    }

    public void updateUserById(Long id, UserRequestDto userDto) {
        User dbUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("No user exist: Id=" + id));
        if (!dbUser.getUsername().equals(userDto.getUsername())) {
            dbUser.setUsername(userDto.getUsername());
        }
        if (!dbUser.getPassword().equals(userDto.getPassword())) {
            dbUser.setPassword(userDto.getPassword());
        }
        userRepository.save(dbUser);
    }
}
