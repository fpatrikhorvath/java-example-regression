package com.regression.framework.selenium.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class ContextAccount {
    private long id;
    private AccountType accountType;
    private double balance;
    private double available;

    public ContextAccount() {
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(final AccountType accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(final double balance) {
        this.balance = balance;
    }

    public double getAvailable() {
        return available;
    }

    public void setAvailable(final double available) {
        this.available = available;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContextAccount{");
        sb.append("id=").append(id);
        sb.append(", accountType=").append(accountType);
        sb.append(", balance=").append(balance);
        sb.append(", available=").append(available);
        sb.append('}');
        return sb.toString();
    }

    public enum AccountType {
        CHECKING("checking"),

        SAVINGS("savings");

        private String type;

        AccountType(final String type) {
            this.type = type;
        }

        @JsonCreator
        public static ContextAccount.AccountType fromValue(final String type) {
            for (ContextAccount.AccountType b : ContextAccount.AccountType.values()) {
                if (b.type.equals(type)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + type + "'");
        }

        @JsonValue
        public String getType() {
            return type;
        }

        @Override
        public String toString() {
            return String.valueOf(type);
        }
    }
}