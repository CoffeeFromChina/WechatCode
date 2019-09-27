package com.sptpc.service;

import com.sptpc.mappers.ReaderTypeMapper;
import com.sptpc.pojo.ReaderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

//读者类别服务层
@Service
public class ReaderTypeService {
    @Autowired
    private ReaderTypeMapper readerTypeMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public int insertReaderType(ReaderType readerType) {
        return this.readerTypeMapper.insertReaderType(readerType);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ReaderType findReaderTypeByID(int id) {
        return this.readerTypeMapper.findReaderTypeByID(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteReaderTypeByID(int id) {
        return this.readerTypeMapper.deleteReaderTypeByID(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int updateReaderType(ReaderType readerType) {
        return this.readerTypeMapper.updateReaderType(readerType);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @SuppressWarnings("rawtypes")
    public List<ReaderType> searchReaderType(Map map) {
        return this.readerTypeMapper.searchReaderType(map);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public int findCountsReaderType(int rdType) {
        return this.readerTypeMapper.findCountsReaderType(rdType);
    }
}
