head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.27.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityReceiveCloseOrderUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3EquityReceiveCloseOrderUnitServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/30 徐宏偉 (中訊) 新規作成
*/
package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3EquityReceiveCloseOrderUnitServiceImplForMock 
    extends WEB3EquityReceiveCloseOrderUnitServiceImpl
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityReceiveCloseOrderUnitServiceImplForMock.class);
    /**
     * (exec失効(mock))<BR>
     */
    public void execCloseOrder(
        HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams,
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execCloseOrder(HostEqtypeCloseOrderNotifyParams, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        
        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl",
            "execCloseOrder",
            new Class[] {HostEqtypeCloseOrderNotifyParams.class, EqTypeOrderUnit.class},
            new Object[]{l_hostEqtypeCloseOrderNotifyParams, l_orderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl",
            "execCloseOrder",
            new Class[] {HostEqtypeCloseOrderNotifyParams.class, EqTypeOrderUnit.class}))
        {
            log.exiting(STR_METHOD_NAME);
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl",
                "execCloseOrder",
                new Class[] {HostEqtypeCloseOrderNotifyParams.class, EqTypeOrderUnit.class}).asWEB3BaseException();

            //3)MockFor --〉 asObject
             TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl",
                "execCloseOrder",
                new Class[] {HostEqtypeCloseOrderNotifyParams.class, EqTypeOrderUnit.class}).asObject();
             
             return;
        }

        log.exiting(STR_METHOD_NAME);
        super.execCloseOrder(l_hostEqtypeCloseOrderNotifyParams, l_orderUnit);
    }
    
    public void notifyCloseOrder(HostEqtypeCloseOrderNotifyParams l_params, EqTypeOrderUnit l_orderUnit)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl",
                "notifyCloseOrder", new Class[]
                { HostEqtypeCloseOrderNotifyParams.class, EqTypeOrderUnit.class }, new Object[]
                { l_params, l_orderUnit });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl",
                "notifyCloseOrder", new Class[]
                { HostEqtypeCloseOrderNotifyParams.class, EqTypeOrderUnit.class }))
        {
            log
                    .debug("webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImplForMock.notifyCloseOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl",
                    "notifyCloseOrder", new Class[]
                    { HostEqtypeCloseOrderNotifyParams.class, EqTypeOrderUnit.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl",
                    "notifyCloseOrder", new Class[]
                    { HostEqtypeCloseOrderNotifyParams.class, EqTypeOrderUnit.class }).asVoid();
            return;
        }
        super.notifyCloseOrder(l_params, l_orderUnit);
    }
}
@
