import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipFilesTests {

    private final ClassLoader cl = ZipFilesTests.class.getClassLoader();


    @Test
    @DisplayName("Проверка контента csv файла в архиве")
    void checkCsvContentInZip() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("archive.zip"))) { // через ZipInputStream будет лучше?
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("email.csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> data = csvReader.readAll();

                    assertThat(data.size()).isEqualTo(2);
                    assertThat(data.get(0)).isEqualTo(new String[]{"Login email", "Identifier", "First name", "Last name"});
                    assertThat(data.get(1)).isEqualTo(new String[]{"laura@example.com", "2070", "Laura", "Grey"});
                }
            }
        }
    }


    @Test
    @DisplayName("Проверка контента xlsx файла в архиве")
    void checkXlsxContentInZip() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("archive.zip"))) { // через ZipInputStream будет лучше?
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("file_example_XLSX_50.xlsx")) {
                    XLS xls = new XLS(zis);

                    String actualValue = xls.excel.getSheetAt(0).getRow(0).getCell(1).getStringCellValue();
                    assertThat(actualValue).isEqualTo("First Name");
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка контента pdf файла в архиве")
    void checkPdfContentInZip() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("archive.zip"))) { // через ZipInputStream будет лучше?
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("file-sample_150kB.pdf")) {
                    PDF pdf = new PDF(zis);

                    assertThat(pdf.numberOfPages).isEqualTo(4);
                    assertThat(pdf.creator).isEqualTo("Writer");
                    assertThat(pdf.text.contains("Lorem ipsum")).isTrue();
                }
            }
        }
    }
}


