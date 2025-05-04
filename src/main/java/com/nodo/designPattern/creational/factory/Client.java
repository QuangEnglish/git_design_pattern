package com.nodo.designPattern.creational.factory;

public class Client {
    public static void main(String[] args) {
        Bank bank = BankFactory.getBank(BankType.TPBANK);
        System.out.printf(bank.getBankName());
    }
}

interface Bank {
    String getBankName();
}

class BankFactory {

    public static Bank getBank(BankType bankType) {
        return switch (bankType) {
            case TPBANK -> new TPBank();
            case VIETCOMBANK -> new VietcomBank();
            default -> throw new IllegalArgumentException("This bank type is unsupported");
        };
    }
}

enum BankType {
    TPBANK, VIETCOMBANK
}

class TPBank implements Bank {
    @Override
    public String getBankName() {
        return "TPBank";
    }
}

class VietcomBank implements Bank {
    @Override
    public String getBankName() {
        return "VietcomBank";
    }
}



