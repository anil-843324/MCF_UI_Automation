package utilities;
import org.testng.annotations.DataProvider;
import java.util.Hashtable;


public class DataProviderUtil {

    @DataProvider(name = "validData")
    public static Object[][] getValidData() {
        ReusableFunction.openExcelFile(Constants.SUITE1_XL_PATH);
        return getDataFromExcel(Constants.SUITE1_XL_PATH, "Sheet1");
    }

    @DataProvider(name = "invalidData")
    public static Object[][] getInvalidData() {
        return getDataFromExcel(Constants.INVALID_SUITE_XL_PATH, "Sheet1");
    }

    private static Object[][] getDataFromExcel(String excelPath, String sheetName) {
        ExcelReader excel = new ExcelReader(excelPath);
        int rows = excel.getRowCount(sheetName);
        int cols = excel.getColumnCount(sheetName);

        Object[][] data = new Object[rows - 1][1];
        Hashtable<String, String> table;

        for (int rowNum = 2; rowNum <= rows; rowNum++) {
            table = new Hashtable<>();

            for (int colNum = 0; colNum < cols; colNum++) {
                String cellData = excel.getCellData(sheetName, colNum, rowNum);
                String header = excel.getCellData(sheetName, colNum, 1);
                table.put(header, cellData);
            }

            data[rowNum - 2][0] = table;
        }
        return data;
    }


}


    // @DataProvider
    // public Object[][] getData() {

    // openExcelFile();

    // if (excel == null) {
    // excel = new ExcelReader(Constants.SUITE1_XL_PATH);
    // }
    // int rows = excel.getRowCount("Sheet1");
    // int cols = excel.getColumnCount("Sheet1");

    // Hashtable<String, String> table = null;
    // Object[][] data = new Object[rows - 1][1];

    // for (int rowNum = 2; rowNum <= rows; rowNum++) {
    // table = new Hashtable<String, String>();

    // for (int colNum = 0; colNum < cols; colNum++) {
    // String cellData = excel.getCellData("Sheet1", colNum, rowNum);
    // String header = excel.getCellData("Sheet1", colNum, 1);

    // table.put(header, cellData);
    // }

    // data[rowNum - 2][0] = table;

    // }
    // return data;

    // }