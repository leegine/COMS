head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.41.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccOpenVoucherRegAcceptUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AccOpenVoucherRegAcceptUnitServiceImplForMock.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/05/29 金傑 (中訊) 新規作成
*/
package webbroker3.accountopen.service.delegate.stdimpls;

import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccOpenVoucherRegAcceptUnitServiceImplForMock extends WEB3AccOpenVoucherRegAcceptUnitServiceImpl
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AccOpenVoucherRegAcceptUnitServiceImplForMock.class);
    
    public String notifyVoucherRegAccept(HostAccOpenAcceptParams l_accOpenAcceptParams) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherRegAcceptUnitServiceImpl",
                "notifyVoucherRegAccept", new Class[]
                { HostAccOpenAcceptParams.class }, new Object[]
                { l_accOpenAcceptParams });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherRegAcceptUnitServiceImpl",
                "notifyVoucherRegAccept", new Class[]
                { HostAccOpenAcceptParams.class }))
        {
            log
                    .debug("webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherRegAcceptUnitServiceImplForMock.notifyVoucherRegAccept()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherRegAcceptUnitServiceImpl",
                    "notifyVoucherRegAccept", new Class[]
                    { HostAccOpenAcceptParams.class }).asWEB3BaseException();
            return (String) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherRegAcceptUnitServiceImpl",
                    "notifyVoucherRegAccept", new Class[]
                    { HostAccOpenAcceptParams.class }).asObject();
        }
        return super.notifyVoucherRegAccept(l_accOpenAcceptParams);
    }
}
@
