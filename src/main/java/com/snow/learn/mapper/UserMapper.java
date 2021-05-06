package com.snow.learn.mapper;

import com.snow.learn.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User Sel(int id);

}
