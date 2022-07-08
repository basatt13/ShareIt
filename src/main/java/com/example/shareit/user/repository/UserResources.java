package com.example.shareit.user.repository;

import com.example.shareit.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserResources {

    public static Map<Long, User> users = new HashMap<>();
    static List<Long> ids = new ArrayList<>();
}
