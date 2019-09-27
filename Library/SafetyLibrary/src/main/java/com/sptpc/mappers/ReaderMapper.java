package com.sptpc.mappers;

import com.sptpc.pojo.Parameter;
import com.sptpc.pojo.Reader;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReaderMapper {
    public int insertReader(Reader reader);

    public Reader findReaderByrdID(String rdID);

    public int deleteReaderByrdID(String rdID);

    public int updateReaderByrdID(Reader reader);

    public int getCounts(Parameter parameter);

    public List<Reader> getReaderInfo(Parameter parameter);

    public String findStatusByrdID(String rdID);

    public int updateStatusByrdID(Reader reader);
}
