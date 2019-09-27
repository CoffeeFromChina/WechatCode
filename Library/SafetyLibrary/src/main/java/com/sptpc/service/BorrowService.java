package com.sptpc.service;

import com.sptpc.mappers.BorrowMapper;
import com.sptpc.pojo.Borrow;
import com.sptpc.pojo.Parameter;
import com.sptpc.pojo.ReaderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BorrowService {
    @Autowired
    private BorrowMapper borrowMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public int insertBorrow(Borrow borrow) {
        return this.borrowMapper.insertBorrow(borrow);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ReaderType findReaderTypeByrdID(String rdID) {
        return this.borrowMapper.findReaderTypeByrdID(rdID);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int updateBookByID(Parameter parameter) {
        return this.borrowMapper.updateBookByID(parameter);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Parameter getReaderInfoByID(Parameter parameter) {
        return this.borrowMapper.getReaderInfoByID(parameter);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int updateReaderByID(Parameter parameter) {
        return this.borrowMapper.updateReaderByID(parameter);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Borrow> selectBorrowInfo(Parameter parameter) {
        return this.borrowMapper.selectBorrowInfo(parameter);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public int getCounts(Parameter parameter) {
        return this.borrowMapper.getCounts(parameter);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteBorrowByID(int id) {
        return this.borrowMapper.deleteBorrowByID(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int updateReader(Parameter parameter) {
        return this.borrowMapper.updateReader(parameter);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Borrow getBorrow(Borrow borrow) {
        return this.borrowMapper.getBorrow(borrow);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int updateReturn(Borrow borrow) {
        return this.borrowMapper.updateReturn(borrow);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int updateTB(Borrow borrow) {
        return this.borrowMapper.updateTB(borrow);
    }

}
