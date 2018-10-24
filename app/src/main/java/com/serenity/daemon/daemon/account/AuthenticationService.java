package com.serenity.daemon.daemon.account;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.NetworkErrorException;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by serenitynanian on 2018/10/24.
 * 这个service通过binder来告知系统，存在这样一个APP账户
 * 这个APP的账号信息在authenticator.xml中配置的
 *
 * 这个服务不需要我们来启动   是由系统自动检测 自动启动的
 */

public class AuthenticationService extends Service {

    private AccountAuthenticator accountAuthenticator;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return accountAuthenticator.getIBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        accountAuthenticator = new AccountAuthenticator(this);
    }

    static class AccountAuthenticator extends AbstractAccountAuthenticator {

        public AccountAuthenticator(Context context) {
            super(context);
        }

        @Override
        public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
            return null;
        }

        @Override
        public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {
            return null;
        }

        @Override
        public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
            return null;
        }

        @Override
        public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
            return null;
        }

        @Override
        public String getAuthTokenLabel(String authTokenType) {
            return null;
        }

        @Override
        public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
            return null;
        }

        @Override
        public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
            return null;
        }
    }
}
