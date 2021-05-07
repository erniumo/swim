package com.guo.gymxmjpa.dao;


import com.guo.gymxmjpa.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 设备信息Dao层接口
 * @Author: LiuJian
 * @Date: 2020/4/3
 */
public interface EquipmentDao extends JpaRepository<Equipment,Long> {
}
