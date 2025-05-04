package com.nodo.designPattern.creational.factory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BankServiceFactoryTest {

    @Autowired
    BankServiceFactory factory;

    @Test
    void createBankService() {
        BankServiceAbstract a = factory.createBankService("A");
        System.out.printf(a.getBankCode());
    }
}