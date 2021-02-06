package com.github.beatacatcode.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private HashSet<Role> roles;
}
