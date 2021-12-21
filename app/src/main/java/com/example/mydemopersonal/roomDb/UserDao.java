package com.example.mydemopersonal.roomDb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    List<Long> insertUser(User... user);

    @Query("SELECT * FROM user")
    List<User> getAllUser();

    @Query("SELECT * FROM user WHERE uid = :uid")
    User getUser(int uid);

    @Query("DELETE FROM user")
    void deleteAllUser();

    @Delete
    void deleteByUserId(User user);

    @Update
    void updateUser(User user);


}
