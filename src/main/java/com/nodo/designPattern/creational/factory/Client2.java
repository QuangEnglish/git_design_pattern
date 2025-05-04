package com.nodo.designPattern.creational.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Stream;


@Service
@Slf4j
class BankServiceFactory {

    private HashMap<String, BankServiceAbstract> bankServices;


    public BankServiceAbstract createBankService(String partnerCode) {
        BankCodeEnum bankCodeEnum = Stream.of(BankCodeEnum.values())
                .filter(bankCode -> bankCode.name().equals(partnerCode))
                .findFirst().orElse(BankCodeEnum.TPBANK);
        BankServiceAbstract bankServiceAbstract = bankServices.get(bankCodeEnum.name());
        if (bankServiceAbstract == null) {
            throw new IllegalArgumentException("This bank type is unsupported");
        }
        log.debug("// BankServiceFactory getBank {}", bankCodeEnum.name());
        return bankServiceAbstract;
    }


    @Autowired
    BankServiceFactory(Set<BankServiceAbstract> bankServiceAbstracts) {
        buildBankService((bankServiceAbstracts));
    }

    private void buildBankService(Set<BankServiceAbstract> bankServiceAbstracts) {
        bankServices = new HashMap<>();
        bankServiceAbstracts.forEach(tmp -> bankServices.put(tmp.getBankCode(), tmp));
    }

}

abstract class BankServiceAbstract {
    abstract String getBankCode();
}

@Service
class TPBankService extends BankServiceAbstract {
    @Override
    String getBankCode() {
        return BankCodeEnum.TPBANK.name();
    }
}

@Service
class VietComBankService extends BankServiceAbstract {
    @Override
    String getBankCode() {
        return BankCodeEnum.VIETCOMBANK.name();
    }
}

enum BankCodeEnum {
    TPBANK, VIETCOMBANK
}
