head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.41.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXTransConnectionImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXTransConnectionImplForMock extends WEB3FXTransConnectionImpl
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FXTransConnectionImplForMock.class);
    
    public WEB3FXGftResultNoticeTelegramUnit doTransfer(CompFxConditionParams l_compFxConditionParams, WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit)
    throws WEB3BaseException
{
    TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.aio.WEB3FXTransConnectionImpl",
        "doTransfer", new Class[]
        { CompFxConditionParams.class, WEB3FXGftAskingTelegramUnit.class}, new Object[]
        { l_compFxConditionParams,  l_fXGftAskingTelegramUnit});
    if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.aio.WEB3FXTransConnectionImpl",
        "doTransfer", new Class[]
        { CompFxConditionParams.class, WEB3FXGftAskingTelegramUnit.class }))
    {
        log.debug("webbroker3.aio.WEB3FXTransConnectionImplForMock.doTransfer()");
        
        TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.aio.WEB3FXTransConnectionImpl",
            "doTransfer", new Class[]
            { CompFxConditionParams.class, WEB3FXGftAskingTelegramUnit.class }).asWEB3BaseException();
        
        return (WEB3FXGftResultNoticeTelegramUnit)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.aio.WEB3FXTransConnectionImpl",
            "doTransfer", new Class[]
            {CompFxConditionParams.class, WEB3FXGftAskingTelegramUnit.class }).asObject();
    }
    return super.doTransfer(l_compFxConditionParams, l_fXGftAskingTelegramUnit);
}
}
@
