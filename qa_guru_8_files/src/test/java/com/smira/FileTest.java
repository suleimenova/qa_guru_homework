package com.smira;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smira.model.Complex;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;

public class FileTest {
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

}}
