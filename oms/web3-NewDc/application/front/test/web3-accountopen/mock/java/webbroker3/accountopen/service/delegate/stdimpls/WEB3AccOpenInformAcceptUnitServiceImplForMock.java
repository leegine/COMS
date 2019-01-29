head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.41.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccOpenInformAcceptUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3AccOpenInformAcceptUnitServiceImplForMock.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/09/22 金傑（中訊）新規作成
 */
package webbroker3.accountopen.service.delegate.stdimpls;

import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccOpenInformAcceptUnitServiceImplForMock extends WEB3AccOpenInformAcceptUnitServiceImpl
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccOpenInformAcceptUnitServiceImplForMock.class);

    public void notifyInformAccept(HostAccOpenAcceptParams l_hostAccOpenAcceptParams,
            VariousInformParams l_variousInformParams, String l_strProcessDiv) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenInformAcceptUnitServiceImpl",
                "notifyInformAccept", new Class[]
                { HostAccOpenAcceptParams.class, VariousInformParams.class, String.class }, new Object[]
                { l_hostAccOpenAcceptParams, l_variousInformParams, l_strProcessDiv });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenInformAcceptUnitServiceImpl",
                "notifyInformAccept", new Class[]
                { HostAccOpenAcceptParams.class, VariousInformParams.class, String.class }))
        {
            log
                    .debug("webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenInformAcceptUnitServiceImplForMock.notifyInformAccept()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenInformAcceptUnitServiceImpl",
                    "notifyInformAccept", new Class[]
                    { HostAccOpenAcceptParams.class, VariousInformParams.class, String.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenInformAcceptUnitServiceImpl",
                    "notifyInformAccept", new Class[]
                    { HostAccOpenAcceptParams.class, VariousInformParams.class, String.class }).asVoid();
            return;
        }
        super.notifyInformAccept(l_hostAccOpenAcceptParams, l_variousInformParams, l_strProcessDiv);
    }
}
@
