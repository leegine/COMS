head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.05.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualOrderAcceptUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信注文受付UnitServiceImplForMock(WEB3MutualOrderAcceptUnitServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/08 徐宏偉 (中訊) 新規作成
*/
package webbroker3.mf.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.mf.WEB3MutualFundAcceptConfirmInterceptor;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (投信注文受付UnitServiceImplForMock)
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3MutualOrderAcceptUnitServiceImplForMock
    extends WEB3MutualOrderAcceptUnitServiceImpl
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualOrderAcceptUnitServiceImplForMock.class);

    /**
     * (notifyOrderAcceptComplete(Mock))
     * 投信注文受付完了処理をおこなう。 <BR>
     * @@param l_mutualFundAcceptConfirmInterceptor - 投信受付確定インタセプタ
     */
    public void notifyOrderAcceptComplete(
        MutualFundOrderUnit l_mutualFundOrderUnit, 
        WEB3MutualFundAcceptConfirmInterceptor l_mutualFundAcceptConfirmInterceptor) 
            throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME =
            "notifyOrderAcceptComplete(MutualFundOrderUnit, WEB3MutualFundAcceptConfirmInterceptor)-->ForMock)";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderAcceptUnitServiceImpl",
            "notifyOrderAcceptComplete",
            new Class[] {MutualFundOrderUnit.class,
                WEB3MutualFundAcceptConfirmInterceptor.class},
            new Object[]{l_mutualFundOrderUnit, l_mutualFundAcceptConfirmInterceptor});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderAcceptUnitServiceImpl",
            "notifyOrderAcceptComplete",
            new Class[] {MutualFundOrderUnit.class,
                WEB3MutualFundAcceptConfirmInterceptor.class}))
        {
            //2）MockFor --〉 WEB3BaseException
            log.exiting(STR_METHOD_NAME);
//            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
//                "webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderAcceptUnitServiceImpl",
//                "notifyOrderAcceptComplete",
//                new Class[] {MutualFundOrderUnit.class,
//                    WEB3MutualFundAcceptConfirmInterceptor.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderAcceptUnitServiceImpl",
                "notifyOrderAcceptComplete",
                new Class[] {MutualFundOrderUnit.class, WEB3MutualFundAcceptConfirmInterceptor.class}).asVoid();
             return;
        }

        log.exiting(STR_METHOD_NAME);
        super.notifyOrderAcceptComplete(l_mutualFundOrderUnit, l_mutualFundAcceptConfirmInterceptor);
    }

    /**
     * (notify注文受付失敗(Mock))<BR>
     * @@param l_mutualFundOrderUnit - 投信注文単位
     * @@param l_mutualFundAcceptConfirmInterceptor - 投信受付確定インタセプタ
     * @@throws WEB3BaseException
     */
    public void notifyOrderAcceptFail(
        MutualFundOrderUnit l_mutualFundOrderUnit, 
        WEB3MutualFundAcceptConfirmInterceptor l_mutualFundAcceptConfirmInterceptor) 
            throws WEB3BusinessLayerException, WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "notifyOrderAcceptFail(MutualFundOrderUnit, WEB3MutualFundAcceptConfirmInterceptor)";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderAcceptUnitServiceImpl",
            "notifyOrderAcceptFail",
            new Class[] {MutualFundOrderUnit.class,
                WEB3MutualFundAcceptConfirmInterceptor.class},
            new Object[]{l_mutualFundOrderUnit, l_mutualFundAcceptConfirmInterceptor});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderAcceptUnitServiceImpl",
            "notifyOrderAcceptFail",
            new Class[] {MutualFundOrderUnit.class,
                WEB3MutualFundAcceptConfirmInterceptor.class}))
        {
            //2）MockFor --〉 WEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderAcceptUnitServiceImpl",
                "notifyOrderAcceptFail",
                new Class[] {MutualFundOrderUnit.class,
                    WEB3MutualFundAcceptConfirmInterceptor.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderAcceptUnitServiceImpl",
                "notifyOrderAcceptFail",
                new Class[] {MutualFundOrderUnit.class, WEB3MutualFundAcceptConfirmInterceptor.class}).asVoid();
             return;
        }

        log.exiting(STR_METHOD_NAME);
        super.notifyOrderAcceptComplete(l_mutualFundOrderUnit, l_mutualFundAcceptConfirmInterceptor);
    }
}
@
