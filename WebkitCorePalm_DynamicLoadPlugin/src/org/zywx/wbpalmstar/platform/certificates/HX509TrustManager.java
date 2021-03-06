/*
 *  Copyright (C) 2014 The AppCan Open Source Project.
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.

 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.zywx.wbpalmstar.platform.certificates;


import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HX509TrustManager implements X509TrustManager {

    private X509TrustManager mTrustManager;

    public HX509TrustManager(KeyStore ksP12) throws Exception {

        TrustManagerFactory tfactory = TrustManagerFactory.getInstance(Http.algorithm);
        tfactory.init(ksP12);
        TrustManager[] trustMgr = tfactory.getTrustManagers();
        if (trustMgr.length == 0) {
            throw new NoSuchAlgorithmException("no trust manager found");
        }
        mTrustManager = (X509TrustManager) trustMgr[0];

    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        mTrustManager.checkClientTrusted(chain, authType);
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) {
        try {
            if ((chain != null) && (chain.length == 1)) {
                chain[0].checkValidity();
            } else {
                mTrustManager.checkServerTrusted(chain, authType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {

        return null;
    }

}
