package com.sptpc.controller;


import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.sptpc.pojo.Borrow;
import com.sptpc.pojo.Parameter;
import com.sptpc.pojo.ReaderType;
import com.sptpc.service.BorrowService;
import com.sptpc.util.DateExchangeUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

//借书控制器
@Controller
@RequestMapping("/borrow")
@RequiresRoles(value = {"BookManager", "SysManager", "Manager"}, logical = Logical.OR)
public class BorrowContorller {
    @Autowired
    private BorrowService borrowService;

    //来到借书页面
    @RequestMapping("/toBorrow")
    @RequiresPermissions(value = {"borrow:toBorrow", "iterm:all"}, logical = Logical.OR)
    public String comeBorrow() {
        return "WEB-INF/bookJsp/bookBorrow";
    }

    //插入借阅信息,读者信息表的借阅图书数加1，图书表的图书状态改变成借出
    @RequestMapping(value = "/insertBorrow", method = {RequestMethod.POST, RequestMethod.GET})
    @RequiresPermissions(value = {"borrow:insertBorrow", "iterm:all"}, logical = Logical.OR)
    public String InsertBorrow(@Validated Borrow borrow, BindingResult br, Model model,
                               @RequestParam("DateOut") String DateOut) throws Exception {

        if (br.hasErrors()) {
            String Message = null;
            List<ObjectError> errors = br.getAllErrors();
            for (ObjectError objectError : errors) {

                model.addAttribute("message", Message + objectError.getDefaultMessage());
            }
            return "error";
        }
        ReaderType readerType = borrowService.findReaderTypeByrdID(borrow.getRdID());
        borrow.setIdContinueTimes(0);
        borrow.setIdDateOut(DateExchangeUtil.StringToDate(DateOut));
        borrow.setIdDateRetPlan(
                DateExchangeUtil.GetDateFromDay(DateExchangeUtil.StringToDate(DateOut), readerType.getCanLendDay()));
        borrow.setIdDateRetAct(null);
        borrow.setIdOverDay(0);
        borrow.setIdOverMoney(0);
        borrow.setIdPunishMoney(0);
        borrow.setIsHasReturn("未还");
        borrow.setOperatorRet("");
        System.out.println(borrow.getBkID() + borrow.getRdID() + borrow.getOperatorLend());
        // 更新
        Parameter parameter = new Parameter();

        parameter.setBkID(borrow.getBkID());
        parameter.setRdID(borrow.getRdID());
        // 比较证件是否有效
        Parameter parameter1 = borrowService.getReaderInfoByID(parameter);
        if (parameter1.getRdStatus().equals("有效") && (DateExchangeUtil.GetDayFormDate(new Date(),
                parameter1.getRdDateReg()) <= readerType.getDateValid() * 366 || readerType.getDateValid() == 0)) {
            if (parameter1.getRdBorrowQty() <= readerType.getCanLendQty()) {
                if (parameter1.getBkStatus().equals("在馆")) {
                    parameter.setBkStatus("借出");
                    parameter.setRdStatus("有效");
                    parameter.setRdBorrowQty(parameter1.getRdBorrowQty() + 1);
                    borrowService.insertBorrow(borrow);
                    borrowService.updateBookByID(parameter);
                    borrowService.updateReaderByID(parameter);
                } else {
                    throw new Exception("图书已借出");
                }

            } else {
                throw new Exception("已超过规定的借书的数量");
            }

        } else {

            parameter.setRdStatus("注销");
            borrowService.updateReaderByID(parameter1);
            throw new Exception("你的证件已过期");
        }

        return "index";
    }

    //列举所有的信息
    @RequestMapping("/selectBorrowInfo")
    @RequiresPermissions(value = {"borrow:selectBorrowInfo", "iterm:all"}, logical = Logical.OR)
    public String SelectBorrowInfo(Parameter parameter, Model model) {
        int count = borrowService.getCounts(parameter);
        int start = parameter.getStart();
        if (start < 0) start = 0;
        if (start > count) start -= 10;
        int end = start + 10;
        parameter.setStart(start);
        parameter.setEnd(end);
        List<Borrow> borrowList = borrowService.selectBorrowInfo(parameter);
        model.addAttribute("BorrowList", borrowList);
        model.addAttribute("parameter", parameter);
        return "WEB-INF/bookJsp/bookBorrowInfo";
    }

    //删除借阅信息，把一切的信息还原
    @RequestMapping("deleteBorrow")
    @RequiresPermissions(value = {"borrow:deleteBorrow", "iterm:all"}, logical = Logical.OR)
    public String DeleteBorrow(Parameter parameter, RedirectAttributes ra, @RequestParam("id") int id) {
        if (parameter.getIsHasReturn().equals("未还")) {
            Parameter pmt = borrowService.getReaderInfoByID(parameter);
            Parameter temp = new Parameter();
            temp.setBkID(parameter.getBkID());
            temp.setRdID(parameter.getRdID());
            temp.setRdBorrowQty(pmt.getRdBorrowQty() - 1);
            temp.setBkStatus("在馆");
            borrowService.updateReader(temp);
            borrowService.updateBookByID(temp);
        }
        borrowService.deleteBorrowByID(id);
        ra.addAttribute("start", 0);
        ra.addAttribute("isHasReturn", parameter.getIsHasReturn());
        return "redirect:/borrow/selectBorrowInfo";
    }

    //进入归还和续借的操作页面
    @RequestMapping("/comeBorrowEdit")
    @RequiresPermissions(value = {"borrow:comeBorrowEdit", "iterm:all"}, logical = Logical.OR)
    public String comeBorrowEdit() {
        return "WEB-INF/bookJsp/bookBorrowEdit";
    }

    /**
     * @throws ：
     * @Title : BorrowOperator
     * @功能描述: TODO
     * @开发者：陈强
     * @参数： @param borrow
     * @参数： @param br
     * @参数： @param model
     * @参数： @param radion
     * @参数： @param operator
     * @参数： @return
     * @参数： @throws Exception
     * @返回类型：String
     */
    @RequestMapping(value = "/borrowOperator", method = {RequestMethod.POST, RequestMethod.GET})
    @RequiresPermissions(value = {"borrow:borrowOperator", "iterm:all"}, logical = Logical.OR)
    public String BorrowOperator(@Validated Borrow borrow, BindingResult br, Model model, @RequestParam("tool") String radion, @RequestParam("operator") String operator
    ) throws Exception {

        if (br.hasErrors()) {
            String Message = null;
            List<ObjectError> errors = br.getAllErrors();
            for (ObjectError objectError : errors) {

                model.addAttribute("message", Message + objectError.getDefaultMessage());
            }
            return "error";
        }
        Borrow row = borrowService.getBorrow(borrow);
        ReaderType readerType = borrowService.findReaderTypeByrdID(borrow.getRdID());
        if (row.getIsHasReturn().equals("已还")) {
            throw new Exception("该图书已还不需要此操作");
        }
        if (radion.equals("2")) {

            if (row.getIdContinueTimes() < readerType.getCanContinueTimes()) {
                borrow.setIdDateOut(new Date());
                borrow.setIdContinueTimes(row.getIdContinueTimes() + 1);
                borrow.setOperatorLend(operator);
                borrow.setIdDateRetPlan(DateExchangeUtil.GetDateFromDay(new Date(), readerType.getCanLendDay()));
                borrowService.updateTB(borrow);
            } else {
                throw new Exception("你已没有续借的机会");
            }


        }
        if (radion.equals("1")) {
            borrow.setIdDateRetAct(new Date());
            borrow.setIdOverDay((int) DateExchangeUtil.GetDayFormDate(new Date(), row.getIdDateRetPlan()));
            borrow.setIdOverMoney(borrow.getIdOverDay() * readerType.getPunishRate());
            borrow.setIdPunishMoney(borrow.getIdOverMoney());
            borrow.setIsHasReturn("已还");
            borrow.setOperatorRet(operator);
            borrowService.updateReturn(borrow);
            Parameter parameter = new Parameter();
            parameter.setBkID(borrow.getBkID());
            parameter.setRdID(borrow.getRdID());
            Parameter pmt = borrowService.getReaderInfoByID(parameter);
            Parameter temp = new Parameter();
            temp.setBkID(parameter.getBkID());
            temp.setRdID(parameter.getRdID());
            temp.setRdBorrowQty(pmt.getRdBorrowQty() - 1);
            temp.setBkStatus("在馆");
            borrowService.updateReader(temp);
            borrowService.updateBookByID(temp);
        }

        return "index";
    }

    //导出数据
    @RequestMapping("/exportBook")
    public String ExportExcel(Parameter parameter, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(parameter.getStart());
        parameter.setEnd(parameter.getStart() + 10);
        ExcelWriter writer = null;
        OutputStream outputStream = response.getOutputStream();
        try {
            response.setHeader("Content-disposition", "attachment; filename=" + "borrow.xls");
            response.setContentType("application/msexcel;charset=UTF-8");//设置类型
            response.setHeader("Pragma", "No-cache");//设置头
            response.setHeader("Cache-Control", "no-cache");//设置头
            response.setDateHeader("Expires", 0);//设置日期头

            writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLS, true);

            Sheet sheet = new Sheet(1, 0, Borrow.class);
            sheet.setSheetName("借阅信息");

            List<Borrow> borrowList = borrowService.selectBorrowInfo(parameter);

            writer.write(borrowList, sheet);
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
//        这些注释内容是使用Apache.poi进行Excel导出的代码。为了纪念，特此注释
//        List<Borrow> borrowList = borrowService.selectBorrowInfo(parameter);
//
//        ExportExcelUtil ex = new ExportExcelUtil();
//        String title = "图书信息表";
//        String[] headers = {"序号", "读者序号", "图书序号", "续借次数", "借书日期", "应还日期", "实际还书日期", "超期天数", "超期金额", "罚款金额", "还书情况", "借书操作员", "还书操作员"};
//        List<String[]> dataset = new ArrayList<String[]>();
//        for (int i = 0; i < borrowList.size(); i++) {
//            Borrow borrow = borrowList.get(i);
//            dataset.add(new String[]{String.valueOf(borrow.getBorrowID()), borrow.getRdID(), borrow.getBkID(), String.valueOf(borrow.getIdContinueTimes()),
//                    DateExchangeUtil.DateToString(borrow.getIdDateOut()), DateExchangeUtil.DateToString(borrow.getIdDateRetPlan()), DateExchangeUtil.DateToString(borrow.getIdDateRetAct()),
//                    String.valueOf(borrow.getIdOverDay()), String.valueOf(borrow.getIdOverMoney()), String.valueOf(borrow.getIdPunishMoney()),
//                    borrow.getIsHasReturn(), borrow.getOperatorLend(), borrow.getOperatorRet()});
//        }
//        OutputStream out = null;//创建一个输出流对象
//        try {
//
//            out = response.getOutputStream();//
//            response.setHeader("Content-disposition", "attachment; filename=" + "borrow.xls");//filename是下载的xls的名，建议最好用英文
//            response.setContentType("application/msexcel;charset=UTF-8");//设置类型
//            response.setHeader("Pragma", "No-cache");//设置头
//            response.setHeader("Cache-Control", "no-cache");//设置头
//            response.setDateHeader("Expires", 0);//设置日期头
//            String rootPath = request.getSession().getServletContext().getRealPath("/");
//            ex.exportExcel(rootPath, title, headers, dataset, out);
//            out.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (out != null) {
//                    out.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return "";
    }
}
