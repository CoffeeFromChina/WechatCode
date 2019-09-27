package com.sptpc.mappers;

import com.sptpc.pojo.Borrow;
import com.sptpc.pojo.Parameter;
import com.sptpc.pojo.ReaderType;
import org.springframework.stereotype.Repository;

import java.util.List;

//借书接口
@Repository
public interface BorrowMapper {
    public int insertBorrow(Borrow borrow);

    public int updateBookByID(Parameter parameter);

    public Parameter getReaderInfoByID(Parameter parameter);

    public int updateReaderByID(Parameter parameter);

    public ReaderType findReaderTypeByrdID(String rdID);

    public List<Borrow> selectBorrowInfo(Parameter parameter);

    public int getCounts(Parameter parameter);

    public int deleteBorrowByID(int id);

    public int updateReader(Parameter parameter);

    public Borrow getBorrow(Borrow borrow);

    public int updateReturn(Borrow borrow);

    public int updateTB(Borrow borrow);
}
