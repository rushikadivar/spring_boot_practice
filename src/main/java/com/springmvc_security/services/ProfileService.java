package com.springmvc_security.services;

import com.springmvc_security.entity.Profile;
import com.springmvc_security.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile updateUserProfile(Long id, Profile profile) {

        return profileRepository.save(profile);
    }
}
