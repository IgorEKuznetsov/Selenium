package com.auto.test.lesson6;

import com.auto.main.lesson6.AuthUser;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class DataProvider {
    static Stream<Arguments> menuDataProvider() {
        return Stream.of(
                Arguments.of(("//a[contains(@class, 'item') and @href='/books']"), ("//h1[.='Аудиокниги']")),
                Arguments.of(("//a[contains(@class, 'item') and @href='/services']"), ("//h1[.='Подписки и сервисы']")),
                Arguments.of(("//a[contains(@class, 'item') and @href='/collections']"), ("//h1[.='Все подборки']")),
                Arguments.of(("//a[contains(@class, 'item') and @href='/promocode']"), ("//h1[.='Активация промокода']"))
        );
    }

     static Stream<Arguments> userDataProvider() {
        return Stream.of(
                Arguments.of(new AuthUser("standard_user", "12345"), true),
                Arguments.of(new AuthUser("standard_user", ""), true),
                Arguments.of(new AuthUser("locked_out_user", "locked_out_user"), true),
                Arguments.of(new AuthUser("", "secret_sauce"), true),
                Arguments.of(new AuthUser("", ""), true)
        );
    }

     static Stream<Arguments> accessUserDataProvider() {
        return Stream.of(
                Arguments.of(new AuthUser("standard_user", "secret_sauce"), true),
                Arguments.of(new AuthUser("performance_glitch_user", "secret_sauce"), true),
                Arguments.of(new AuthUser("problem_user", "secret_sauce"), true)
        );
    }
}
