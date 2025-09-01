package com.springmvc_security.services;

import com.springmvc_security.dto.UserProfilePatchDto;
import com.springmvc_security.dto.UserProfileRequestDto;
import com.springmvc_security.dto.UserProfileResponseDto;
import com.springmvc_security.dto.UserResponseDto;
import com.springmvc_security.entity.Profile;
import com.springmvc_security.entity.User;
import com.springmvc_security.exceptionhandling.ProfileNotFoundException;
import com.springmvc_security.exceptionhandling.UserNotFoundException;
import com.springmvc_security.repository.ProfileRepository;
import com.springmvc_security.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public List<UserProfileResponseDto> getUserProfiles(Long userId) {
        return profileRepository.findAllByUserId(userId)
                .stream()
                .map(profile ->
                        new UserProfileResponseDto(profile.getUserProfileId(),
                                profile.getFullName(),
                                profile.getPhoto(),
                                mapToUserResponseDto(profile.getUser())
                        )
                ).toList();
    }

//    public UserProfileResponseDto updateUserProfile(Long userId, UserProfileRequestDto userProfileRequestDto) {
//        // simulate the profile is added first time
//        Profile profileTobeSaved = new Profile();
//        profileTobeSaved.setUser(userRepository.findById(userId)
//                .orElseThrow(() -> new UserNotFoundException("No user exist: Id=" + userId)));
//        profileTobeSaved.setFull_name(userProfileRequestDto.getFull_name());
//        profileTobeSaved.setPhoto(userProfileRequestDto.getPhoto());
//
//        Profile dbUserProfile = profileRepository.save(profileTobeSaved);
//        User dbProfileUser = dbUserProfile.getUser();
//        UserResponseDto userResponseDto = new UserResponseDto(dbProfileUser.getId(), dbProfileUser.getUsername(), dbProfileUser.getPassword());
//        return new UserProfileResponseDto(dbUserProfile.getUser_profile_id(), userResponseDto, dbUserProfile.getFull_name(), dbUserProfile.getPhoto())
//    }

    @Transactional
    public UserProfileResponseDto addUserProfile(Long userId, UserProfileRequestDto userProfileRequestDto) {
        Profile profileTobeSaved = new Profile();
        profileTobeSaved.setUser(userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No user exist: Id=" + userId)));
        profileTobeSaved.setFullName(userProfileRequestDto.getFullName());
        profileTobeSaved.setPhoto(userProfileRequestDto.getPhoto());

        Profile dbUserProfile = profileRepository.save(profileTobeSaved);
        User dbProfileUser = dbUserProfile.getUser();
        UserResponseDto userResponseDto = new UserResponseDto(dbProfileUser.getId(), dbProfileUser.getUsername(), dbProfileUser.getEmail(), mapToUserProfileResponseDto(dbUserProfile));
        return new UserProfileResponseDto(dbUserProfile.getUserProfileId(), dbUserProfile.getFullName(), dbUserProfile.getPhoto(), userResponseDto);
    }

    @Transactional
    public UserProfileResponseDto updateUserProfile(Long userId, UserProfileRequestDto dto) {
            Profile profile = profileRepository.findByUserId(userId)
                .orElseGet(() -> {
                    userRepository.findById(userId)
                            .orElseThrow(() -> new UserNotFoundException("User not found: Id=" + userId));
                    return new Profile();
                });

        profile.setFullName(dto.getFullName().toLowerCase());
        profile.setPhoto(dto.getPhoto());

        Profile savedProfile = profileRepository.save(profile);

        return mapToUserProfileResponseDto(savedProfile);
    }


    @Transactional
    public UserProfileResponseDto patchUserProfile(Long userId, UserProfilePatchDto dto) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found for userId=" + userId));

        if (dto.getFullName() != null) {
            profile.setFullName(dto.getFullName());
        }
        if (dto.getPhoto() != null) {
            profile.setPhoto(dto.getPhoto());
        }

        Profile savedProfile = profileRepository.save(profile);
        return mapToUserProfileResponseDto(savedProfile);
    }


    private UserProfileResponseDto mapToUserProfileResponseDto(Profile profile) {
        User user = profile.getUser();
        UserResponseDto userDto = new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), mapUserToUserProfileResponseDto(user.getProfile()));
        return new UserProfileResponseDto(profile.getUserProfileId(), profile.getFullName(), profile.getPhoto(), userDto);
    }

    private UserProfileResponseDto mapUserToUserProfileResponseDto(Profile profile) {
        if (Objects.nonNull(profile)) {
            return new UserProfileResponseDto(profile.getUserProfileId(), profile.getFullName(), profile.getPhoto(), null);
        }
        return null;
    }

    private UserResponseDto mapToUserResponseDto(User user) {
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), mapUserToUserProfileResponseDto(user.getProfile()));
    }
}

