head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.45.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoChangeCancelOrderAcceptUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP訂正取消受付UnitServiceImplForMock(WEB3IfoChangeCancelOrderAcceptUnitServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/02 徐宏偉 (中訊) 新規作成
*/
package webbroker3.ifo.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.ifo.data.HostFotypeOrderAcceptParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 先物OP訂正取消受付UnitServiceImplForMock
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3IfoChangeCancelOrderAcceptUnitServiceImplForMock
    extends WEB3IfoChangeCancelOrderAcceptUnitServiceImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3IfoChangeCancelOrderAcceptUnitServiceImplForMock.class);

    /**
     * (notify訂正取消受付(Mock))<BR>
     * 訂正取消受付処理を行う。<BR>
     * <BR>
     * @@param l_hostFotypeOrderAcceptParams - 注文受付キューParamsオブジェクト
     */
    public void notifyChangeCancelOrderAccept(HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyChangeCancelOrderAccept(HostFotypeOrderAcceptParams) -- ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoChangeCancelOrderAcceptUnitServiceImpl",
            "notifyChangeCancelOrderAccept",
            new Class[] {HostFotypeOrderAcceptParams.class},
            new Object[]{l_hostFotypeOrderAcceptParams});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoChangeCancelOrderAcceptUnitServiceImpl",
            "notifyChangeCancelOrderAccept",
            new Class[] {HostFotypeOrderAcceptParams.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoChangeCancelOrderAcceptUnitServiceImpl",
                "notifyChangeCancelOrderAccept",
                new Class[] {HostFotypeOrderAcceptParams.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoChangeCancelOrderAcceptUnitServiceImpl",
                "notifyChangeCancelOrderAccept",
                new Class[] {HostFotypeOrderAcceptParams.class}).asVoid();

            log.exiting(STR_METHOD_NAME);
            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.notifyChangeCancelOrderAccept(l_hostFotypeOrderAcceptParams);
    }

    /**
     * (notify訂正取消受付時間外(Mock))<BR>
     * 訂正取消受付時間外処理を行う。<BR>
     * @@param l_hostFotypeOrderAcceptParams - (注文受付キューParams)<BR>
     * 注文受付キューParamsオブジェクト
     */
    public void notifyChangeCancelOrderAcceptOvertime(
        HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyChangeCancelOrderAcceptOvertime(HostFotypeOrderAcceptParams) -- ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoChangeCancelOrderAcceptUnitServiceImpl",
            "notifyChangeCancelOrderAcceptOvertime",
            new Class[] {HostFotypeOrderAcceptParams.class},
            new Object[]{l_hostFotypeOrderAcceptParams});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoChangeCancelOrderAcceptUnitServiceImpl",
            "notifyChangeCancelOrderAcceptOvertime",
            new Class[] {HostFotypeOrderAcceptParams.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoChangeCancelOrderAcceptUnitServiceImpl",
                "notifyChangeCancelOrderAcceptOvertime",
                new Class[] {HostFotypeOrderAcceptParams.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoChangeCancelOrderAcceptUnitServiceImpl",
                "notifyChangeCancelOrderAcceptOvertime",
                new Class[] {HostFotypeOrderAcceptParams.class}).asVoid();

            log.exiting(STR_METHOD_NAME);
            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.notifyChangeCancelOrderAcceptOvertime(l_hostFotypeOrderAcceptParams);
    }
}
@
