head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.17.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeBizLogicProviderForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3GentradeBizLogicProviderForMock.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/20  金傑　@(中訊) 新規作成
*/
package webbroker3.gentrade;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3GentradeBizLogicProviderForMock extends WEB3GentradeBizLogicProvider
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3GentradeBizLogicProviderForMock.class);
    
    public void calcCommission(WEB3GentradeCommission l_commission, SubAccount l_subAccount) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.gentrade.WEB3GentradeBizLogicProvider",
                "calcCommission", new Class[]
                { WEB3GentradeCommission.class, SubAccount.class }, new Object[]
                { l_commission, l_subAccount });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.gentrade.WEB3GentradeBizLogicProvider",
                "calcCommission", new Class[]
                { WEB3GentradeCommission.class, SubAccount.class }))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeBizLogicProviderForMock.calcCommission()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.gentrade.WEB3GentradeBizLogicProvider",
                    "calcCommission", new Class[]
                    { WEB3GentradeCommission.class, SubAccount.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.gentrade.WEB3GentradeBizLogicProvider",
                    "calcCommission", new Class[]
                    { WEB3GentradeCommission.class, SubAccount.class }).asVoid();
            return;
        }
        super.calcCommission(l_commission, l_subAccount);
    }
    
    public double calcSalesTax(double l_dblAmount, Timestamp l_tsBaseDate, SubAccount l_subAccount)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.gentrade.WEB3GentradeBizLogicProvider",
                "calcSalesTax", new Class[]
                { double.class, Timestamp.class, SubAccount.class }, new Object[]
                { new Double(l_dblAmount), l_tsBaseDate, l_subAccount });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.gentrade.WEB3GentradeBizLogicProvider", "calcSalesTax",
                new Class[]
                { double.class, Timestamp.class, SubAccount.class }))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeBizLogicProviderForMock.calcSalesTax()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.gentrade.WEB3GentradeBizLogicProvider",
                    "calcSalesTax", new Class[]
                    { double.class, Timestamp.class, SubAccount.class }).asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeBizLogicProvider", "calcSalesTax", new Class[]
                    { double.class, Timestamp.class, SubAccount.class }).asDouble();
        }
        return super.calcSalesTax(l_dblAmount, l_tsBaseDate, l_subAccount);
    }
}
@
