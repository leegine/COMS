head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.44.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionChangeCancelNotifyUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3OptionChangeCancelNotifyUnitServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/08 徐宏偉 (中訊) 新規作成
*/
package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.ifo.WEB3IfoChangeCancelNotifyUpdateInterceptor;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP訂正取消通知UnitServiceImpl(Mock))
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3OptionChangeCancelNotifyUnitServiceImplForMock
    extends WEB3OptionChangeCancelNotifyUnitServiceImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3OptionChangeCancelNotifyUnitServiceImplForMock.class);

    /**
     * (notify訂正(Mock))<BR>
     * @@param l_orderUnit - 注文単位オブジェクト<BR>
     * @@param l_ifoChangeCancelNotifyInterceptor - 先物OP訂正取消通知インタセプタオブジェクト<BR>
     */
    public void notifyChange(
        OrderUnit l_orderUnit,
        WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor)
        throws WEB3BaseException    
    {
        final String STR_METHOD_NAME = "notifyChange()-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeCancelNotifyUnitServiceImpl",
            "notifyChange",
            new Class[] {OrderUnit.class, WEB3IfoChangeCancelNotifyUpdateInterceptor.class},
            new Object[]{l_orderUnit, l_ifoChangeCancelNotifyInterceptor});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeCancelNotifyUnitServiceImpl",
            "notifyChange",
            new Class[] {OrderUnit.class, WEB3IfoChangeCancelNotifyUpdateInterceptor.class}))
        {
            log.exiting(STR_METHOD_NAME);
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeCancelNotifyUnitServiceImpl",
                "notifyChange",
                new Class[] {OrderUnit.class,
                    WEB3IfoChangeCancelNotifyUpdateInterceptor.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeCancelNotifyUnitServiceImpl",
                "notifyChange",
                new Class[] {OrderUnit.class, WEB3IfoChangeCancelNotifyUpdateInterceptor.class}).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.notifyChange(l_orderUnit, l_ifoChangeCancelNotifyInterceptor);
    }

    /**
     * (notify取消(Mock))<BR>
     * @@param l_orderUnit - 注文単位オブジェクト<BR>
     * @@param l_ifoChangeCancelNotifyInterceptor - 先物OP訂正取消通知インタセプタオブジェクト<BR>
     */
    public String notifyCancel(
        OrderUnit l_orderUnit,
        WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyCancel(OrderUnit, WEB3IfoChangeCancelNotifyUpdateInterceptor)-->ForMock";

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeCancelNotifyUnitServiceImpl",
            "notifyCancel",
            new Class[] {OrderUnit.class, WEB3IfoChangeCancelNotifyUpdateInterceptor.class},
            new Object[]{l_orderUnit, l_ifoChangeCancelNotifyInterceptor});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeCancelNotifyUnitServiceImpl",
            "notifyCancel",
            new Class[] {OrderUnit.class, WEB3IfoChangeCancelNotifyUpdateInterceptor.class}))
        {
            log.exiting(STR_METHOD_NAME);
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeCancelNotifyUnitServiceImpl",
                "notifyCancel",
                new Class[] {OrderUnit.class,
                    WEB3IfoChangeCancelNotifyUpdateInterceptor.class}).asWEB3BaseException();

            //3)MockFor --〉 asObject
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeCancelNotifyUnitServiceImpl",
                "notifyCancel",
                new Class[] {OrderUnit.class, WEB3IfoChangeCancelNotifyUpdateInterceptor.class}).asObject();

        }

        log.exiting(STR_METHOD_NAME);
        return super.notifyCancel(l_orderUnit, l_ifoChangeCancelNotifyInterceptor);
    }
}
@
