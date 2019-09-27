package com.sptpc.mappers;

import com.sptpc.pojo.SysRP;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRPMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRP record);

    int insertSelective(SysRP record);

    SysRP selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRP record);

    int updateByPrimaryKey(SysRP record);
}
