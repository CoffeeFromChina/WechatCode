package com.sptpc.service;

import com.sptpc.mappers.BookClassMapper;
import com.sptpc.pojo.BookClass;
import com.sptpc.pojo.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//图书编目服务层
@Service
public class BookClassService {
    @Autowired
    private BookClassMapper bookClassMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public int insertBookClass(BookClass bookClass) {
        return this.bookClassMapper.insertBookClass(bookClass);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteBookClassByID(BookClass bookClass) {
        return this.bookClassMapper.deleteBookClassByID(bookClass);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int updateBookClass(BookClass bookClass) {
        return this.bookClassMapper.updateBookClass(bookClass);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<BookClass> selectBookClassByID(Parameter parameter) {
        return this.bookClassMapper.selectBookClassByID(parameter);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public int getCount(Parameter parameter) {
        return this.bookClassMapper.getCount(parameter);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public BookClass findBookClassByID(Parameter parameter) {
        return this.bookClassMapper.findBookClassByID(parameter);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<BookClass> selectAllBkCatalog() {
        return this.bookClassMapper.selectAllBkCatalog();
    }

}
