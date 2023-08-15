package FinalActivity.data;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class dataProviders extends dataReader{

    public DataFormatter formatter = new DataFormatter();

    public XSSFWorkbook workbook() throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
                "/src/test/java/FinalActivity/data/finalActivityData.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        return wb;
    }

    @DataProvider(name="hashMapData")
    public Object[][] getData() throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("message","Hello");
        map.put("a","5");
        map.put("b","6");

        return new Object[][]{{map}};
    }

    @DataProvider(name="jsonData")
    public Object[][] getJsonData() throws IOException {
        List<HashMap<String,String>> data = getJsonDataToMap();
        return new Object[][]{{data.get(0)},{data.get(1)}};
    }

    @DataProvider(name="inputFormData")
    public Object[][] getInputFormData() throws IOException {
        XSSFSheet sheet = workbook().getSheet("InputForm");
        int rowCount = sheet.getPhysicalNumberOfRows();
        XSSFRow row = sheet.getRow(0);
        int colCount = row.getLastCellNum();
        Object data[][] = new Object[rowCount-1][colCount];

        for (int i=0; i<rowCount-1; i++){
            row = sheet.getRow(i+1);
            for(int j=0; j<colCount; j++){
                XSSFCell cell = row.getCell(j);
                data[i][j] = formatter.formatCellValue(cell);
            }
        }
        return data;
    }

    @DataProvider(name="jQuery")
    public Object[][] getJQueryData() throws IOException {
        XSSFSheet sheet = workbook().getSheet("jQuery");
        int rowCount = sheet.getPhysicalNumberOfRows();
        XSSFRow row = sheet.getRow(0);
        int colCount = row.getLastCellNum();
        Object data[][] = new Object[rowCount-1][colCount];
        XSSFCell cell;

        for (int i=0; i<rowCount-1; i++){
            row = sheet.getRow(i+1);
            for(int j=0; j<colCount; j++){
                cell = row.getCell(j);
                if (j != 1) {
                    data[i][j] = formatter.formatCellValue(cell);
                } else {
                    String[] states = cell.getStringCellValue().split("[\r\n]+");
                    data[i][j] = states;
                }


            }
        }
        return data;
    }

    @DataProvider(name="DualList")
    public Object[][] getDualListData() throws IOException {
        XSSFSheet sheet = workbook().getSheet("DualList");
        int rowCount = sheet.getPhysicalNumberOfRows();
        XSSFRow row = sheet.getRow(0);
        int colCount = row.getLastCellNum();
        Object data[][] = new Object[rowCount-1][colCount];
        XSSFCell cell;

        for (int i=0; i<rowCount-1; i++){
            row = sheet.getRow(i+1);
            for(int j=0; j<colCount; j++){
                cell = row.getCell(j);
                String[] listBox = cell.getStringCellValue().split("[\r\n]+");
                data[i][j] = listBox;
            }
        }
        return data;
    }
}
