package api.utils;

import lombok.Data;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name="Data")
    public Object[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir") + "//testdata//UserData1.xlsx";
        ExcelUtility util = new ExcelUtility(path);

        int rownum = util.getRowCount("Sheet1");
        int column = util.getLastCellNum("Sheet1", 1);

        Object[][] apidata = new String[rownum][column];

        for(int i= 1; i<=rownum; i++)
        {
            for(int j = 0; j<column; j++)
            {
                apidata[i-1][j] = util.getCellData("Sheet1", i, j);
            }
        }
        return apidata;
    }

    @DataProvider(name= "UserNames")
    public Object[] getUserNames() throws IOException {
        String path = System.getProperty("user.dir") +"//testdata//UserData1.xlsx";

        ExcelUtility util = new ExcelUtility(path);

        int rownum = util.getTotalRows("Sheet1");
        Object[] apidata = new String[rownum];
        for(int i = 1; i<=rownum; i++)
        {
            apidata[i-1] = util.getCellData("Sheet1", i , 1);
        }
        return apidata;
    }

}
