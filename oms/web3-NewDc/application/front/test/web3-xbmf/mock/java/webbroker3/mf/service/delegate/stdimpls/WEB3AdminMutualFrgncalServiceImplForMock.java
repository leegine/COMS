head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.05.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminMutualFrgncalServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminMutualFrgncalServiceImplForMock.java
Author Name      : Daiwa Institute of Research
Revesion History : 2008/07/28 王志葵 (中訊) 新規作成
*/
package webbroker3.mf.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.mf.message.WEB3AdminMutualFrgncalCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualFrgncalInputRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalInputResponse;
import webbroker3.mf.message.WEB3AdminMutualFrgncalReferenceRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalReferenceResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminMutualFrgncalServiceImplForMock extends WEB3AdminMutualFrgncalServiceImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminMutualFrgncalServiceImplForMock.class);

    public WEB3AdminMutualFrgncalInputResponse inputFrgncal(
        WEB3AdminMutualFrgncalInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "inputFrgncal(WEB3AdminMutualFrgncalInputRequest)-->ForMock)";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualFrgncalServiceImpl",
            "inputFrgncal",
            new Class[] {WEB3AdminMutualFrgncalInputRequest.class},
            new Object[]{l_request});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualFrgncalServiceImpl",
            "inputFrgncal",
            new Class[] {WEB3AdminMutualFrgncalInputRequest.class}))
        {         
//            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
//                "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualFrgncalServiceImpl",
//                "inputFrgncal",
//                new Class[] {WEB3AdminMutualFrgncalInputRequest.class}).asWEB3BaseException();
            
            return (WEB3AdminMutualFrgncalInputResponse)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualFrgncalServiceImpl",
                "inputFrgncal",
                new Class[] {WEB3AdminMutualFrgncalInputRequest.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.inputFrgncal(l_request);
    }

    public WEB3AdminMutualFrgncalCompleteResponse submitFrgncal(
        WEB3AdminMutualFrgncalCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitFrgncal(WEB3AdminMutualFrgncalCompleteRequest)-->ForMock)";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualFrgncalServiceImpl",
            "submitFrgncal",
            new Class[] {WEB3AdminMutualFrgncalCompleteRequest.class},
            new Object[]{l_request});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualFrgncalServiceImpl",
            "submitFrgncal",
            new Class[] {WEB3AdminMutualFrgncalCompleteRequest.class}))
        {
            log.debug(
            "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualFrgncalServiceImpl." +
            "submitFrgncal(WEB3AdminMutualFrgncalCompleteRequest)" + 
            "l_request = " + l_request);
        
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualFrgncalServiceImpl",
                "submitFrgncal",
                new Class[] {WEB3AdminMutualFrgncalCompleteRequest.class}).asWEB3BaseException();
        
            return (WEB3AdminMutualFrgncalCompleteResponse)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualFrgncalServiceImpl",
                "submitFrgncal",
                new Class[] {WEB3AdminMutualFrgncalCompleteRequest.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.submitFrgncal(l_request);
    }

    public WEB3AdminMutualFrgncalReferenceResponse searchFrgncal(
            WEB3AdminMutualFrgncalReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "searchFrgncal(WEB3AdminMutualFrgncalReferenceRequest)-->ForMock)";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualFrgncalServiceImpl",
            "searchFrgncal",
            new Class[] {WEB3AdminMutualFrgncalReferenceRequest.class},
            new Object[]{l_request});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualFrgncalServiceImpl",
            "searchFrgncal",
            new Class[] {WEB3AdminMutualFrgncalReferenceRequest.class}))
        {
            log.debug(
            "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualFrgncalServiceImpl." +
            "searchFrgncal(WEB3AdminMutualFrgncalReferenceRequest)" + 
            "l_request = " + l_request);
        
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualFrgncalServiceImpl",
                "searchFrgncal",
                new Class[] {WEB3AdminMutualFrgncalReferenceRequest.class}).asWEB3BaseException();
        
            return (WEB3AdminMutualFrgncalReferenceResponse)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualFrgncalServiceImpl",
                "searchFrgncal",
                new Class[] {WEB3AdminMutualFrgncalReferenceRequest.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.searchFrgncal(l_request);
    }
}
@
