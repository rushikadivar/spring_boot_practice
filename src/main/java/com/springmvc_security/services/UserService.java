package com.springmvc_security.services;

import com.springmvc_security.dto.*;
import com.springmvc_security.entity.Profile;
import com.springmvc_security.entity.User;
import com.springmvc_security.exceptionhandling.UserAlreadyExist;
import com.springmvc_security.exceptionhandling.UserNotFoundException;
import com.springmvc_security.repository.UserRepository;
import com.springmvc_security.repository.UserSpecification;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream().map(user -> new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), mapUserToUserProfileResponseDto(user.getProfile()))).toList();
    }

    public UserResponseDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), mapUserToUserProfileResponseDto(user.getProfile())))
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
                .stream().findFirst().map(user -> new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), mapUserToUserProfileResponseDto(user.getProfile())))
                .orElseThrow(() -> new UserNotFoundException("No user exist:"));
    }

    @Transactional
    public UserResponseDto save(UserRequestDto userDto) {
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

        if (Objects.nonNull(userDto.getProfile())) {
            Profile profile = new Profile();
            profile.setPhoto(userDto.getProfile().getPhoto());
            profile.setFullName(userDto.getProfile().getFullName());
            profile.setUser(user);
            user.setProfile(profile);
        }

        User dbUser = userRepository.save(user);
        return new UserResponseDto(dbUser.getId(), dbUser.getUsername(), dbUser.getEmail(), mapUserToUserProfileResponseDto(dbUser.getProfile()));
    }

    private boolean isPasswordStrong(String password) {
//        return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$");
        return password.matches("");
    }

    @Transactional
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("No user exist: Id=" + id);
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public UserResponseDto updateUserById(Long id, UserRequestDto userDto) {
        User dbUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("No user exist: Id=" + id));
        dbUser.setUsername(userDto.getUsername());
        dbUser.setPassword(userDto.getPassword());
        dbUser = userRepository.save(dbUser);
        return new UserResponseDto(dbUser.getId(), dbUser.getUsername(), dbUser.getEmail(), mapUserToUserProfileResponseDto(dbUser.getProfile()));
    }

    @Transactional
    public UserResponseDto updateUserPartialById(Long id, UserPatchRequestDto userPatchDto) {
        User dbUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("No user exist: Id=" + id));
        if (Objects.nonNull(userPatchDto.getUsername()) && !dbUser.getUsername().equals(userPatchDto.getUsername())) {
            dbUser.setUsername(userPatchDto.getUsername());
        }
        if (Objects.nonNull(userPatchDto.getPassword()) && !dbUser.getPassword().equals(userPatchDto.getPassword())) {
            dbUser.setPassword(userPatchDto.getPassword());
        }
        dbUser = userRepository.save(dbUser);
        return new UserResponseDto(dbUser.getId(), dbUser.getUsername(), dbUser.getEmail(), mapUserToUserProfileResponseDto(dbUser.getProfile()));
    }

    private UserProfileResponseDto mapUserToUserProfileResponseDto(Profile profile) {
        if (Objects.nonNull(profile)) {
            return new UserProfileResponseDto(profile.getUserProfileId(), profile.getFullName(), profile.getPhoto(), null);
        }
        return null;
    }
}
