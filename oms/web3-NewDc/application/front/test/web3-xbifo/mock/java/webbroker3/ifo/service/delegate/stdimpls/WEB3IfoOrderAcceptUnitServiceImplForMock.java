head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.43.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoOrderAcceptUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP注文受付UnitServiceImplForMock(WEB3IfoOrderAcceptUnitServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/01 徐宏偉 (中訊) 新規作成
*/
package webbroker3.ifo.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.ifo.data.HostFotypeOrderAcceptParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 先物OP注文受付UnitServiceImplForMock
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3IfoOrderAcceptUnitServiceImplForMock extends WEB3IfoOrderAcceptUnitServiceImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3IfoOrderAcceptUnitServiceImplForMock.class);

    /**
     * (notify注文受付(Mock))<BR>
     * 注文受付処理を行う。<BR>
     * <BR>
     * @@param l_hostFotypeOrderAcceptParams - 注文受付キューParamsオブジェクト
     */
    public void notifyOrderAccept(HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".notifyOrderAccept(HostFotypeOrderAcceptParams)-ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImpl",
            "notifyOrderAccept",
            new Class[] {HostFotypeOrderAcceptParams.class},
            new Object[]{l_hostFotypeOrderAcceptParams});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImpl",
            "notifyOrderAccept", new Class[] {HostFotypeOrderAcceptParams.class}))
        {
            log.debug("webbroker3.ifo.service.delegate.stdimpls"
                + ".WEB3IfoOrderAcceptUnitServiceImpl.notifyOrderAccept(HostFotypeOrderAcceptParams)-ForMock");

            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImpl",
                "notifyOrderAccept",
                new Class[] {HostFotypeOrderAcceptParams.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImpl",
                "notifyOrderAccept",
                new Class[] {HostFotypeOrderAcceptParams.class}).asVoid();

            log.exiting(STR_METHOD_NAME);
            return;
        }
        super.notifyOrderAccept(l_hostFotypeOrderAcceptParams);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify受付時間外)<BR>
     * 受付時間外処理を行う。<BR>
     * <BR>
     * @@param l_hostFotypeOrderAcceptParams - 注文受付キューParamsオブジェクト
     */
    public void notifyOrderAcceptOvertime(
        HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            ".notifyOrderAcceptOvertime(HostFotypeOrderAcceptParams)-ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImpl",
            "notifyOrderAcceptOvertime",
            new Class[] {HostFotypeOrderAcceptParams.class},
            new Object[]{l_hostFotypeOrderAcceptParams});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImpl",
            "notifyOrderAcceptOvertime", new Class[] {HostFotypeOrderAcceptParams.class}))
        {
            log.debug("webbroker3.ifo.service.delegate.stdimpls"
                + ".WEB3IfoOrderAcceptUnitServiceImpl.notifyOrderAcceptOvertime(HostFotypeOrderAcceptParams)-ForMock");

            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImpl",
                "notifyOrderAcceptOvertime",
                new Class[] {HostFotypeOrderAcceptParams.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImpl",
                "notifyOrderAcceptOvertime",
                new Class[] {HostFotypeOrderAcceptParams.class}).asVoid();

            log.exiting(STR_METHOD_NAME);
            return;
        }

        super.notifyOrderAcceptOvertime(l_hostFotypeOrderAcceptParams);
        log.exiting(STR_METHOD_NAME);

    }
}
@
