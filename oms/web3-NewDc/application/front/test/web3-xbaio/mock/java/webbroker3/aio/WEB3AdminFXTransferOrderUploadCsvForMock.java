head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.41.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFXTransferOrderUploadCsvForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFXTransferOrderUploadCsvForMock extends WEB3AdminFXTransferOrderUploadCsv
{
	/**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AioBizLogicProviderForMock.class);

	public String getUserCode(int l_intLineNumber)
    {
		final String STR_METHOD_NAME = "getAvailCode(int)-->ForMock";
        log.entering(STR_METHOD_NAME);

        // 1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.aio.WEB3AdminFXTransferOrderUploadCsv",
            "getUserCode", 
            new Class[] {int.class}, 
            new Object[] { new Integer(l_intLineNumber)});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.aio.WEB3AdminFXTransferOrderUploadCsv",
            "getUserCode",
            new Class[] { int.class}))
        {
            log.debug("webbroker3.aio.WEB3AdminFXTransferOrderUploadCsv.getAvailCode()");

            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.aio.WEB3AdminFXTransferOrderUploadCsv",
                "getUserCode", 
                new Class[] { int.class}).asObject().toString();

        }
        return super.getUserCode(l_intLineNumber);
    }

	public WEB3GentradeMainAccount getMainAccount(int l_intLineNumber) throws WEB3BaseException
    {
		final String STR_METHOD_NAME = "getAvailCode(int)-->ForMock";
        log.entering(STR_METHOD_NAME);

        // 1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.aio.WEB3AdminFXTransferOrderUploadCsv",
            "getMainAccount", 
            new Class[] {int.class}, 
            new Object[] { new Integer(l_intLineNumber)});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.aio.WEB3AdminFXTransferOrderUploadCsv",
            "getMainAccount",
            new Class[] { int.class}))
        {
            log.debug("webbroker3.aio.WEB3AdminFXTransferOrderUploadCsv.getAvailCode()");

            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.aio.WEB3AdminFXTransferOrderUploadCsv",
                "getMainAccount", 
                new Class[] { int.class}).asWEB3BaseException();

            return (WEB3GentradeMainAccount)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.aio.WEB3AdminFXTransferOrderUploadCsv",
                "getMainAccount", 
                new Class[] { int.class}).asObject();
        }
        return super.getMainAccount(l_intLineNumber);
    }
}
@
