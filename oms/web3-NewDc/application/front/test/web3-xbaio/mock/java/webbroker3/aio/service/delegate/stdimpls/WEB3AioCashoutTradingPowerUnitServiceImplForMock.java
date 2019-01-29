head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.29.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioCashoutTradingPowerUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金余力チェックUnitServiceImplForMock(WEB3AioCashoutTradingPowerUnitServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/03/09 齊珂 (中訊) 新規作成
*/
package webbroker3.aio.service.delegate.stdimpls;

import webbroker3.aio.data.HostPaymentOrderParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 出金余力チェックUnitServiceImplForMock
 *
 * @@author 齊珂(中訊)
 * @@version 1.0
 */
public class WEB3AioCashoutTradingPowerUnitServiceImplForMock extends
    WEB3AioCashoutTradingPowerUnitServiceImpl
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutTradingPowerUnitServiceImplForMock.class);
    
    /**
     * (Mock)<BR>
     * 注文の余力チェック処理を行う。<BR>
     * @@param l_hostPaymentOrderParams - (出金請求注文キューの行オブジェクト)
     * @@param l_strProcessFlag - (処理フラグ)
     * @@param l_blnTriggerIssueFlag - (トリガ発行フラグ)
     * @@param l_strDbCurrentPriceCheckDiv - (DB時価余力チェック区分)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public void execute(HostPaymentOrderParams l_hostPaymentOrderParams,
        String l_strProcessFlag, boolean l_blnTriggerIssueFlag, String l_strDbCurrentPriceCheckDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(HostPaymentOrderParams, String, boolean, String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.aio.service.delegate.stdimpls.WEB3AioCashoutTradingPowerUnitServiceImpl",
            "execute",
            new Class[] {HostPaymentOrderParams.class, String.class, boolean.class, String.class},
            new Object[]{l_hostPaymentOrderParams,
                l_strProcessFlag, 
                new Boolean(l_blnTriggerIssueFlag), 
                l_strDbCurrentPriceCheckDiv});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.aio.service.delegate.stdimpls.WEB3AioCashoutTradingPowerUnitServiceImpl",
            "execute",  
            new Class[] {
                HostPaymentOrderParams.class, 
                String.class, 
                boolean.class, 
                String.class}))
        {
            //2）MockFor --〉 WEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3AioCashoutTradingPowerUnitServiceImpl",
                "execute",
                new Class[] {
                    HostPaymentOrderParams.class, 
                    String.class, 
                    boolean.class, 
                    String.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3AioCashoutTradingPowerUnitServiceImpl",
                "execute",
                new Class[] {
                    HostPaymentOrderParams.class,
                    String.class, 
                    boolean.class, 
                    String.class}).asVoid();
            return;

        }

        log.exiting(STR_METHOD_NAME);
        super.execute(
            l_hostPaymentOrderParams,
            l_strProcessFlag, 
            l_blnTriggerIssueFlag, 
            l_strDbCurrentPriceCheckDiv);
    }
}
@
