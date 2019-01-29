head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.41.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioBizLogicProviderForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3AioBizLogicProviderForMock.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/04/06 金傑 (中訊) 新規作成
 */
package webbroker3.aio;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AioBizLogicProviderForMock extends WEB3AioBizLogicProvider
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AioBizLogicProviderForMock.class);

    public double calcTransPossibleAmount(long l_accountID, ProductTypeEnum l_productType, long l_productID,
            TaxTypeEnum l_taxType, String l_equityType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(HostPaymentOrderParams, String, boolean, String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        // 1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.aio.WEB3AioBizLogicProvider",
                "calcTransPossibleAmount", 
                new Class[] {long.class, 
                             ProductTypeEnum.class, 
                             long.class,
                             TaxTypeEnum.class, 
                             String.class }, 
              new Object[] { new Long(l_accountID), l_productType,
                        new Long(l_productID), l_taxType, l_equityType });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.aio.WEB3AioBizLogicProvider",
                "calcTransPossibleAmount", 
                new Class[] { long.class, ProductTypeEnum.class, long.class,
                        TaxTypeEnum.class, String.class }))
        {
            log.debug("webbroker3.aio.WEB3AioBizLogicProviderForMock.calcTransPossibleAmount()");
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3AioBizLogicProvider",
                    "calcTransPossibleAmount", 
                    new Class[] { long.class, ProductTypeEnum.class, long.class,
                            TaxTypeEnum.class, String.class }).asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3AioBizLogicProvider",
                    "calcTransPossibleAmount", 
                    new Class[] { long.class, ProductTypeEnum.class, long.class,
                            TaxTypeEnum.class, String.class }).asDouble();
            
        }
        return super.calcTransPossibleAmount(l_accountID, l_productType, l_productID, l_taxType, l_equityType);
    }
}
@
