package com.smira;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FileParseTest {
    ClassLoader cl = FileParseTest.class.getClassLoader();
    @Test
    void pdfTest() throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloadedFile = $("a[href*='junit-user-guide-5.9.1.pdf']").download();
        PDF pdf = new PDF(downloadedFile);
        assertThat(pdf.author).contains("Sam Brannen");


    }

    @Test
    void xlsTest() throws Exception {
        InputStream is = cl.getResourceAsStream("test_file.xlsx");
        XLS xls = new XLS(is);
        assertThat(xls.excel.getSheetAt(0)
                        .getRow(2)
                        .getCell(1)
                        .getStringCellValue()
        ).isEqualTo("Mara");
    }

    @Test
    void csvTest() throws Exception{
        try (InputStream is = cl.getResourceAsStream("qa_guru.csv")){
            CSVReader reader = new CSVReader(new InputStreamReader(is));
            List<String[]> content = reader.readAll();
            String[] row = content.get(1);
            assertThat(row[0]).isEqualTo("LastName");
        }

    }

    @Test
    void zipTest() throws Exception {
        InputStream is = cl.getResourceAsStream("qa_guru_files.zip");
        ZipInputStream zis = new ZipInputStream(is);
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            String entryName = entry.getName();
        }

    }
    }


