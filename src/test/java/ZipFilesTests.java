import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipFilesTests {

    @Test
    void checkFilesContentInZip() throws Exception {
        try (ZipFile zipFile = new ZipFile(new File("src/test/resources/archive.zip"))) { // через ZipInputStream будет лучше?
            // Get an enumeration of the entries
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            // Iterate through the entries
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                switch (entry.getName()) {
                    case "email.csv": {
                        InputStream is = zipFile.getInputStream(entry);
                        CSVReader csvReader = new CSVReader(new InputStreamReader(is));
                        List<String[]> data = csvReader.readAll();
                        Assertions.assertEquals(2, data.size());
                        Assertions.assertArrayEquals(
                                new String[]{"Login email", "Identifier", "First name", "Last name"},
                                data.get(0)
                        );
                        Assertions.assertArrayEquals(
                                new String[]{"laura@example.com", "2070", "Laura", "Grey"},
                                data.get(1)
                        );
                        break;
                    }
                    case "file_example_XLSX_50.xlsx": {
                        InputStream is = zipFile.getInputStream(entry);
                        XLS xls = new XLS(is);

                        String actualValue = xls.excel.getSheetAt(0).getRow(0).getCell(1).getStringCellValue();
                        Assertions.assertTrue(actualValue.contains("First Name"));
                        break;
                    }
                    case "file-sample_150kB.pdf": {
                        InputStream is = zipFile.getInputStream(entry);
                        PDF pdf = new PDF(is);
                        Assertions.assertEquals(4, pdf.numberOfPages);
                        Assertions.assertEquals("Writer", pdf.creator);
                        Assertions.assertTrue(pdf.text.contains("Lorem ipsum"));

                        break;
                    }
                }
            }

        }

    }
}
