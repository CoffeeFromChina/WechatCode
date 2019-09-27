package com.sptpc.mappers;

import com.sptpc.pojo.SysUR;
import org.springframework.stereotype.Repository;

@Repository
public interface SysURMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUR record);

    int insertSelective(SysUR record);

    SysUR selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUR record);

    int updateByPrimaryKey(SysUR record);
}
