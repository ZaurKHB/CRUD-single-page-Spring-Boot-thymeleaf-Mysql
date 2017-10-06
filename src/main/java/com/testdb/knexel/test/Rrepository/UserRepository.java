package com.testdb.knexel.test.Rrepository;

import com.testdb.knexel.test.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    List<User> findByLastName(String lastname);
    List <User> findUsersByName (String  name);
    List <User> findAllByParentname(String parent);
    List <User> findAllByBirthday(Date birthday);
    List <User> findAllByAddress( String Address);
    List <User> findAllByNumber(String num);



}
