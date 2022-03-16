import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelOut {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream
                ("C:\\Users\\cente\\OneDrive\\Рабочий стол\\Java\\Test\\Report.xlsx");

        ZipSecureFile.setMinInflateRatio(-1.0d); //anti «Zip bomb»

        Workbook wb = new XSSFWorkbook(fis);
        List<Participant> participants = new ArrayList<>();
        List<Participant> winners = new ArrayList<>();
        Participant participant;

        for (int i = 1; i < wb.getSheetAt(0).getLastRowNum(); i++) {
            participants.add(new Participant(getPhone(wb, i), getEmail(wb, i), getData(wb,i), getReceiptId(wb,i)));
        }

        for (int i = 0; i < 3; i++) {
           winners.add(participants.get(choosingWinner(participants.size(), sumNumberOfNumbers(participants.size()))));
           participants.remove(choosingWinner(participants.size(), sumNumberOfNumbers(participants.size())));
        }

        fis.close();

        Workbook wb1 = new XSSFWorkbook();
        Sheet sh = wb1.createSheet("Победители");
        Row row;

        for (int i = 0; i < winners.size(); i++) {
            row = sh.createRow(i);
            participant = participants.get(i);
            row.createCell(0).setCellValue(participant.getPhone());
            row.createCell(1).setCellValue(participant.getEmail());
            row.createCell(2).setCellValue(participant.getReceiptDate());
            row.createCell(3).setCellValue(participant.getReceiptId());
        }

        FileOutputStream fos = new FileOutputStream("C:\\Users\\cente\\OneDrive\\Рабочий стол\\Java\\Test\\Победители.xlsx");
        wb1.write(fos);
        fos.close();

    }

    public static String getPhone(Workbook wb, int row) {
        return Long.toString((long) wb.getSheetAt(0).getRow(row).getCell(0).getNumericCellValue());
    }

    public static String getEmail(Workbook wb, int row) {
        return wb.getSheetAt(0).getRow(row).getCell(1).getStringCellValue();
    }

    public static Date getData (Workbook wb, int row) {
        return wb.getSheetAt(0).getRow(row).getCell(2).getDateCellValue();
    }

    public static int getReceiptId (Workbook wb, int row) {
        return (int) wb.getSheetAt(0).getRow(row).getCell(3).getNumericCellValue();
    }

    public static int sumNumberOfNumbers(int num) {

        int z;
        int sum = 0;

        while (num > 0){
            z = num % 10;
            sum += z;
            num /= 10;
        }
        return sum;
    }

    public static int choosingWinner (int countChecks, int num ) {

        int winnerNum = countChecks / num;
        return winnerNum - 1; // Победитель 1 соответствует индексу массива 0
    }

}
