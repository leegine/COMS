head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.59.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTMLoginRejectIPManagementServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3AdminTMLoginRejectIPManagementServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/26 安陽(中訊) 新規作成
*/
package webbroker3.trademanagement.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTMLoginRejectIPManagementServiceImplForMock extends WEB3AdminTMLoginRejectIPManagementServiceImpl
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMLoginRejectIPManagementServiceImplForMock.class);
    
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute", new Class[]
                {WEB3GenRequest.class}, new Object[]
                {l_request});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                "execute", new Class[]
                {WEB3GenRequest.class}))
        {
            log.debug("webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImplForMock.execute()");
            TestBaseForMock.MOCK_MANAGER
                    .getMethodReturnValue(
                            "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                            "execute", new Class[]
                            {WEB3GenRequest.class}).asWEB3BaseException();
            
            log.debug("webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImplForMock.execute()");
            TestBaseForMock.MOCK_MANAGER
                    .getMethodReturnValue(
                            "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                            "execute", new Class[]
                            {WEB3GenRequest.class}).asWEB3BaseRuntimeException();
            
            return (WEB3GenResponse) TestBaseForMock.MOCK_MANAGER
                    .getMethodReturnValue(
                            "webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl",
                            "execute", new Class[]
                            {WEB3GenRequest.class}).asObject();
        }
        return super.execute(l_request);
    }
}
@
