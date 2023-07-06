package org.dongguk.study.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.study.dto.UserDto;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    public UserDto readUserProfile() {
        return UserDto.builder()
                .id(0L)
                .name("wonjun")
                .introduction("intro").build();
    }
}
