package com.google.android.gms.wallet;

public class PaymentData {
    private String email;
    private String tokenizationData;
    private CardInfo cardInfo;
    private ShippingAddress shippingAddress;

    public String getEmail() {
        return email;
    }

    public String getTokenizationData() {
        return tokenizationData;
    }

    public CardInfo getCardInfo() {
        return cardInfo;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public static class CardInfo {
        private String cardNetwork;
        private String cardDetails;
        private String billingAddress;

        public String getCardNetwork() {
            return cardNetwork;
        }

        public String getCardDetails() {
            return cardDetails;
        }

        public String getBillingAddress() {
            return billingAddress;
        }
    }

    public static class ShippingAddress {
        private String name;
        private String postalCode;
        private String countryCode;
        private String phoneNumber;
        private String address1;
        private String address2;
        private String address3;
        private String locality;
        private String administrativeArea;
        private String sortingCode;

        public String getName() {
            return name;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getAddress1() {
            return address1;
        }

        public String getAddress2() {
            return address2;
        }

        public String getAddress3() {
            return address3;
        }

        public String getLocality() {
            return locality;
        }

        public String getAdministrativeArea() {
            return administrativeArea;
        }

        public String getSortingCode() {
            return sortingCode;
        }
    }
}