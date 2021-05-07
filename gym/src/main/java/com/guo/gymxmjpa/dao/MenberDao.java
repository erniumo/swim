package com.guo.gymxmjpa.dao;


import com.guo.gymxmjpa.entity.Adminuser;
import com.guo.gymxmjpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * @Description: 会员信息Dao层接口
 */
@Transactional
public interface MenberDao extends JpaRepository<Member,Long> {

    Member findByUsernameAndPassword(String username,String password);

    Member findByMemberId(long id);

    @Modifying
    @Query(value = "update  member set Password =:Password where MemberId =:MemberId",nativeQuery = true)
    void updPassword(@Param("MemberId") long MemberId, @Param("Password") String Password) ;
}
