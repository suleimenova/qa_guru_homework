package com.smira.model;
import java.util.List;

public class Complex {
    public String nameOfApartmentComplex;
    public int maxNumberOfFloor;
    public List<String> types;
    public boolean smartTechnologyHas;
    public Apartments apartments;
    public static class Apartments {
        public int apartmentsID;
        public int square;
        public int floor;
    }

}
