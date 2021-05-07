package com.guo.gymxmjpa.dao;


import com.guo.gymxmjpa.entity.Adminuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * @Description: 管理员Dao层接口
 */
@Transactional
public interface AdminuserDao extends JpaRepository<Adminuser,Long> {
    Adminuser findByAdminNameAndAdminPassword(String name,String password);


    @Modifying
    @Query(value = "update  adminuser set adminPassword =:adminPassword where adminId =:adminId",nativeQuery = true)
    void updPassword(@Param("adminId") long adminId,@Param("adminPassword") String adminPassword) ;



}
