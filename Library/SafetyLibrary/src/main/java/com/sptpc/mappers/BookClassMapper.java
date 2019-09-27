package com.sptpc.mappers;

import com.sptpc.pojo.BookClass;
import com.sptpc.pojo.Parameter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookClassMapper {
    public int insertBookClass(BookClass bookClass);

    public int deleteBookClassByID(BookClass bookClass);

    public int updateBookClass(BookClass bookClass);

    public List<BookClass> selectBookClassByID(Parameter parameter);

    public int getCount(Parameter parameter);

    public BookClass findBookClassByID(Parameter parameter);

    public List<BookClass> selectAllBkCatalog();
}
