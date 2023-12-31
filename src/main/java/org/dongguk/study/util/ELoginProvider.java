package org.dongguk.study.util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ELoginProvider {
    KAKAO("KAKAO"),
    GOOGLE("GOOGLE"),
    APPLE("APPLE");

    private final String provider;

    @Override
    public String toString() {
        return provider;
    }
}