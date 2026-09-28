package com.tech.ayugram.vision.stub.barcode;

public class Barcode {
    public static final int QR_CODE = 1;
    public static final int DATA_MATRIX = 2;
    public static final int AZTEC = 4;
    public static final int CODE_39 = 8;
    public static final int CODE_93 = 16;
    public static final int CODE_128 = 32;
    public static final int EAN_8 = 64;
    public static final int EAN_13 = 128;
    public static final int ITF = 256;
    public static final int PDF417 = 512;
    public static final int UPC_A = 1024;
    public static final int UPC_E = 2048;
    public static final int DRIVER_LICENSE = 1024;

    public String rawValue;
    public int valueFormat;
    public String displayValue;
    public int format;
    public CornerPoint[] cornerPoints;
    public DriverLicense driverLicense;

    public String getRawValue() { return rawValue; }
    public void setRawValue(String rawValue) { this.rawValue = rawValue; }
    public int getValueFormat() { return valueFormat; }
    public void setValueFormat(int valueFormat) { this.valueFormat = valueFormat; }
    public String getDisplayValue() { return displayValue; }
    public void setDisplayValue(String displayValue) { this.displayValue = displayValue; }
    public int getFormat() { return format; }
    public void setFormat(int format) { this.format = format; }
    public CornerPoint[] getCornerPoints() { return cornerPoints; }
    public void setCornerPoints(CornerPoint[] cornerPoints) { this.cornerPoints = cornerPoints; }
    public DriverLicense getDriverLicense() { return driverLicense; }
    public void setDriverLicense(DriverLicense driverLicense) { this.driverLicense = driverLicense; }

    public static class CornerPoint {
        public float x;
        public float y;
    }

    public static class DriverLicense {
        public String documentType;
        public String issuingCountry;
        public String firstName;
        public String middleName;
        public String lastName;
        public String gender;
        public String licenseNumber;
        public String dateOfBirth;
        public String dateOfExpiry;
        public String dateOfIssue;
        public String addressStreet;
        public String addressCity;
        public String addressState;
        public String addressZip;

        // Aliases for compatibility
        public String birthDate;
        public String expiryDate;
    }
}