head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.41.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXTelegramProcessServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3FXTelegramProcessServiceImplForMock.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/17 金傑（中訊）新規作成
*/
package webbroker3.aio;

import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3GftMessageOperationDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

public class WEB3FXTelegramProcessServiceImplForMock extends WEB3FXTelegramProcessServiceImpl
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FXTelegramProcessServiceImplForMock.class);
    
    public boolean isGFTTelegramSendAndReceiveValueSame(
            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class }, new Object[]
                { l_fxGftResultNoticeTelegramUnit });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class }))
        {
            log.debug("webbroker3.aio.WEB3FXTelegramProcessServiceImplForMock.isGFTTelegramSendAndReceiveValueSame()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                    "isGFTTelegramSendAndReceiveValueSame", new Class[]
                    { WEB3FXGftResultNoticeTelegramUnit.class }).asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                    "isGFTTelegramSendAndReceiveValueSame", new Class[]
                    { WEB3FXGftResultNoticeTelegramUnit.class }).asBoolean();
        }
        return super.isGFTTelegramSendAndReceiveValueSame(l_fxGftResultNoticeTelegramUnit);
    }

    public boolean isGFTTelegramSet(WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit) 
        throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
            "isGFTTelegramSet", new Class[]
            { WEB3FXGftResultNoticeTelegramUnit.class }, new Object[]
            { l_fxGftResultNoticeTelegramUnit });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSet", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class }))
        {
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSet", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class }).asBoolean();
        }
        return super.isGFTTelegramSet(l_fxGftResultNoticeTelegramUnit);
    }

    public boolean isGFTTelegramLengthPropSame(WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)
        throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
            "isGFTTelegramLengthPropSame", new Class[]
            { WEB3FXGftResultNoticeTelegramUnit.class }, new Object[]
            { l_fxGftResultNoticeTelegramUnit });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramLengthPropSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class }))
        {
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramLengthPropSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class }).asBoolean();
        }
        return super.isGFTTelegramLengthPropSame(l_fxGftResultNoticeTelegramUnit);
    }
}
@
