package com.xuuuuu.controller;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.xuuuuu.mapper.CatagoryMapper;
import com.xuuuuu.pojo.Catagory;
import com.xuuuuu.service.ExcleService;
import com.xuuuuu.util.ExcelListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExcleController {

    @Autowired
    ExcleService excleService;

    @Autowired
    CatagoryMapper catagoryMapper;

    @RequestMapping("/expor")
    public String exporExcel(HttpServletResponse response) throws IOException {
        System.out.println("导出成功");
        ExcelWriter writer = null;
        OutputStream outputStream = response.getOutputStream();
        try {

            response.setHeader("Content-disposition", "attachment; filename=" + "catagory.xls");
            response.setContentType("application/msexcel;charset=UTF-8");//设置类型
            response.setHeader("Pragma", "No-cache");//设置头
            response.setHeader("Cache-Control", "no-cache");//设置头
            response.setDateHeader("Expires", 0);//设置日期头

            writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLS, true);

            Sheet sheet = new Sheet(1, 0, Catagory.class);
            sheet.setSheetName("目录");

            List<Catagory> catagoryList = excleService.findAll();

            writer.write(catagoryList, sheet);
            writer.finish();

            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.getOutputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "index";
    }

    //使用easyexcel,需要自定义实现AnalysisEventListener接口.
    //easyexcel会帮助我们读取数据.我们再将读取的数据封装到List中,
    //再在控制器中,获取easyexcel帮我们读取到的数据,在对数据进行类型转换和封装,最后插入到数据库即可.
    //这个框架,真的是桑心病况,太特么的简单了.
    @RequestMapping("/import")
    public String importExcel(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("导入成功");

        InputStream inputStream = file.getInputStream();

        //实例化实现了AnalysisEventListener接口的类
        ExcelListener listener = new ExcelListener();
        //传入参数
        ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, listener);
        //读取信息
        excelReader.read(new Sheet(1, 1, Catagory.class));

        //获取数据
        List<Object> list = listener.getDatas();

        List<Catagory> catagoryList = new ArrayList<Catagory>();
        Catagory catagory = new Catagory();

        //转换数据类型,并插入到数据库
        for (int i = 0; i < list.size(); i++) {
            catagory = (Catagory) list.get(i);
            catagoryMapper.insertCategory(catagory);
        }
        return "index";
    }

}
