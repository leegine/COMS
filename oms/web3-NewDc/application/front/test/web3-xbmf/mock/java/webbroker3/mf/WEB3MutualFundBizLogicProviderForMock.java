head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.23.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundBizLogicProviderForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3MutualFundBizLogicProviderForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/11 徐宏偉 (中訊) 新規作成
*/
package webbroker3.mf;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundBizLogicProviderForMock extends WEB3MutualFundBizLogicProvider
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundBizLogicProviderForMock.class);
    /**
     * (calc概算買付口数)
     * 乗換先銘柄の概算買付口数の計算を行う。<BR>
     * <BR>
     * @@param l_mfProduct - 銘柄
     * @@param l_dblOrderQuantity - 注文数量
     * @@return double
     * @@roseuid 40A3495A00B7
     */
    public double calcEstimatedBuyQty(
        WEB3MutualFundProduct l_mfProduct, 
        double l_dblOrderQuantity)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcEstimatedBuyQty(WEB3MutualFundProduct, double)--ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundBizLogicProvider",
            "calcEstimatedBuyQty",
            new Class[] {WEB3MutualFundProduct.class, double.class},
            new Object[]{l_mfProduct, new Double(l_dblOrderQuantity)});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundBizLogicProvider",
            "calcEstimatedBuyQty",
            new Class[] {WEB3MutualFundProduct.class, double.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundBizLogicProvider",
                "calcEstimatedBuyQty",
                new Class[] {WEB3MutualFundProduct.class, double.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            return (double)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundBizLogicProvider",
                "calcEstimatedBuyQty",
                new Class[] {WEB3MutualFundProduct.class, double.class}).asDouble();
        }

        log.exiting(STR_METHOD_NAME);
        return super.calcEstimatedBuyQty(l_mfProduct,l_dblOrderQuantity);
    }
}
@
