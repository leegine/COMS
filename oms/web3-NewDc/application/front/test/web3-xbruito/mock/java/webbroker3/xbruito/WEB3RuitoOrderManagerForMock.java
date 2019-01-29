head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.34.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3RuitoOrderManagerForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3RuitoOrderManagerForMock.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/10/29 金傑（中訊）新規作成
 */
package webbroker3.xbruito;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3RuitoOrderManagerForMock extends WEB3RuitoOrderManager
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3RuitoOrderManagerForMock.class);

    public NewOrderValidationResult validateNewOrder(SubAccount l_subAccount, WEB3RuitoProduct l_ruitoProduct,
            double l_dbOrderQuantity, boolean l_blnIsBuy, String l_strPaymentMethod, String l_strDesignateMethod)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.xbruito.WEB3RuitoOrderManager",
                "validateNewOrder", new Class[]
                { SubAccount.class, WEB3RuitoProduct.class, double.class, boolean.class, String.class, String.class },
                new Object[]
                { l_subAccount, l_ruitoProduct, new Double(l_dbOrderQuantity), new Boolean(l_blnIsBuy),
                        l_strPaymentMethod, l_strDesignateMethod });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.xbruito.WEB3RuitoOrderManager", "validateNewOrder",
                new Class[]
                { SubAccount.class, WEB3RuitoProduct.class, double.class, boolean.class, String.class, String.class }))
        {
            log.debug("webbroker3.xbruito.WEB3RuitoOrderManagerForMock.validateNewOrder()");
            TestBaseForMock.MOCK_MANAGER
                    .getMethodReturnValue(
                            "webbroker3.xbruito.WEB3RuitoOrderManager",
                            "validateNewOrder",
                            new Class[]
                            { SubAccount.class, WEB3RuitoProduct.class, double.class, boolean.class, String.class,
                                    String.class }).asWEB3BaseException();
            return (NewOrderValidationResult) TestBaseForMock.MOCK_MANAGER
                    .getMethodReturnValue(
                            "webbroker3.xbruito.WEB3RuitoOrderManager",
                            "validateNewOrder",
                            new Class[]
                            { SubAccount.class, WEB3RuitoProduct.class, double.class, boolean.class, String.class,
                                    String.class }).asObject();
        }
        return super.validateNewOrder(l_subAccount, l_ruitoProduct, l_dbOrderQuantity, l_blnIsBuy, l_strPaymentMethod,
                l_strDesignateMethod);
    }
}
@
