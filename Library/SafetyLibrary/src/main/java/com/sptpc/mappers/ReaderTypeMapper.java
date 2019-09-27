package com.sptpc.mappers;

import com.sptpc.pojo.ReaderType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReaderTypeMapper {
    //读者类别注册
    public int insertReaderType(ReaderType readerType);

    //根据id查找信息
    public ReaderType findReaderTypeByID(int id);

    //根据id删除信息
    public int deleteReaderTypeByID(int id);

    //更新数据
    public int updateReaderType(ReaderType readerType);

    //分页查找
    @SuppressWarnings("rawtypes")
    public List<ReaderType> searchReaderType(Map map);

    //得到总记录的个数
    public int findCountsReaderType(int rdType);
}
