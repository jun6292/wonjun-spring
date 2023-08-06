package org.dongguk.study.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.study.Repository.UserRepository;
import org.dongguk.study.domain.User;
import org.dongguk.study.dto.UserDto;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public UserDto readUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저가 없어요."));

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName()).build();
    }

    @Transactional
    public UserDto updateUserProfile(Long userId, UserDto dto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저가 없어요."));

        user.patch(dto);
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName()).build();
    }

    @Transactional
    public UserDto deleteUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저가 없어요."));

        userRepository.delete(user);
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName()).build();
    }
}
