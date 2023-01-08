package com.smira;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.smira.model.Complex;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


import static com.codeborne.pdftest.assertj.Assertions.assertThat;

public class FileTest {
    ClassLoader cl = FileTest.class.getClassLoader();

    @DisplayName("Json check value")
    @Test
    void jsonTest() throws Exception {
        File file = new File("src/test/resources/complex.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Complex complex = objectMapper.readValue(file, Complex.class);

        assertThat(complex.nameOfApartmentComplex).isEqualTo("Sezim qala");
        assertThat(complex.maxNumberOfFloor).isEqualTo(16);
        assertThat(complex.smartTechnologyHas).isEqualTo(true);
        assertThat(complex.apartments.apartmentsID).isEqualTo(123);
        assertThat(complex.apartments.square).isEqualTo(40);
        assertThat(complex.apartments.floor).isEqualTo(2);

    }

    @DisplayName("Parsing zip file")
    @Test
    void zipTest() throws Exception {
        ZipFile zf = new ZipFile(new File("src/test/resources/qa_guru_files.zip"));
        try (ZipInputStream is = new ZipInputStream(cl.getResourceAsStream("qa_guru_files.zip"))){
            ZipEntry entry;
            while ((entry = is.getNextEntry()) != null) {
                if (entry.getName() == "qa_guru_files.csv") {
                    try (InputStream inputStream = zf.getInputStream(entry);
                         CSVReader reader = new CSVReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                        List<String[]> content = reader.readAll();
                        String[] row = content.get(1);
                        assertThat(row[0]).isEqualTo("LastName");
                    }
                } else if (entry.getName() == "test_file.xlsx") {
                    try (InputStream inputStream = zf.getInputStream(entry)) {
                        XLS xls = new XLS(inputStream);
                        assertThat(xls.excel.getSheetAt(0)
                                .getRow(2)
                                .getCell(1)
                                .getStringCellValue()
                        ).isEqualTo("Mara");
                    }
                } else if (entry.getName() == "test.pdf") {
                    try (InputStream inputStream = zf.getInputStream(entry)) {
                        PDF pdf = new PDF(inputStream);
                        assertThat(pdf.author).contains("Sam Brannen");
                    }
                }
            }
        }
    }
}

