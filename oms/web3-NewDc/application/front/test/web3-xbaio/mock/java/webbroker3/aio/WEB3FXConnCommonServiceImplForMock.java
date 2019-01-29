head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.08.07.44.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	85c4d9ebcee7843;
filename	WEB3FXConnCommonServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3FXConnCommonServiceImplForMock.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/18 金傑（中訊）新規作成
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXConnCommonServiceImplForMock extends WEB3FXConnCommonServiceImpl
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FXDataControlServiceImplForMock.class);
    
    public WEB3ExtConnection sendExtConnAskingMessage(CompFxConditionParams l_compFxConditionParams,
            WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit) throws WEB3BaseException
	{
	TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.aio.WEB3FXConnCommonServiceImpl",
	        "sendExtConnAskingMessage", new Class[]
	        { CompFxConditionParams.class, WEB3FXGftAskingTelegramUnit.class }, new Object[]
	        { l_compFxConditionParams, l_fXGftAskingTelegramUnit });
	if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.aio.WEB3FXConnCommonServiceImpl",
	        "sendExtConnAskingMessage", new Class[]
	        {  CompFxConditionParams.class, WEB3FXGftAskingTelegramUnit.class }))
	{
	    TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.aio.WEB3FXConnCommonServiceImpl",
	            "sendExtConnAskingMessage", new Class[]
	            { CompFxConditionParams.class, WEB3FXGftAskingTelegramUnit.class }).asWEB3BaseException();
	    return (WEB3ExtConnection) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
	            "webbroker3.aio.WEB3FXConnCommonServiceImpl", "sendExtConnAskingMessage", new Class[]
	            {  CompFxConditionParams.class, WEB3FXGftAskingTelegramUnit.class}).asObject();
	}
	return super.sendExtConnAskingMessage(l_compFxConditionParams, l_fXGftAskingTelegramUnit);
}
}
@
