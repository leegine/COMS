head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.37.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoCampaignRegistAccListServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminAccInfoCampaignRegistAccListServiceImplForMock.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/08 金傑 (中訊) 新規作成
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccInfoCampaignRegistAccListServiceImplForMock extends WEB3AdminAccInfoCampaignRegistAccListServiceImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminAccInfoCampaignRegistAccListServiceImplForMock.class);
    
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignRegistAccListServiceImpl",
            "execute",
            new Class[] {WEB3GenRequest.class},
            new Object[]{l_request});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignRegistAccListServiceImpl",
            "execute",
            new Class[] {WEB3GenRequest.class})){
            log.debug(
                    "webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignRegistAccListServiceImplTestForMock.execute(WEB3GenRequest)" + 
                    "l_request = " + l_request);
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignRegistAccListServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class}).asWEB3BaseException();
            
            return (WEB3GenResponse)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignRegistAccListServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class}).asObject();
        }
        return super.execute(l_request);
    }

}
@
