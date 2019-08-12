package com.xi.proj.repository;

import com.xi.proj.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

  //@Query("select s from table_user s where s.username=?1 and s.password=?2")
  UserEntity findByUsernameAndPassword(String username,String password);
  //@Query("select s from table_user s where s.username=?1")
  UserEntity findByUsername(String username);
}