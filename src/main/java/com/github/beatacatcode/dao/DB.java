package com.github.beatacatcode.dao;

import com.github.beatacatcode.domain.Role;
import com.github.beatacatcode.domain.User;

import java.util.*;

public class DB {
    public static Map<String, User> userMap=new HashMap<>();
    static {
        userMap.put("zs",new User(1L,"zs","123456",new HashSet<>(Collections.singletonList(new Role(1L, "admin")))));
        userMap.put("ls",new User(1L,"ls","123456",new HashSet<>(Collections.singletonList(new Role(1L, "user")))));
        System.out.println(userMap);
    }
    public static User getUserByName(String name){
        return userMap.get(name);
    }
}
