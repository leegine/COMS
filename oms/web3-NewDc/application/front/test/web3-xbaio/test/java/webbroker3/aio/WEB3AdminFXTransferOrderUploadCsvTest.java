head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.33.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFXTransferOrderUploadCsvTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFXTransferOrderUploadCsvTest extends TestBaseForMock
{
	/**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXTransferOrderUploadCsv.class);

	public WEB3AdminFXTransferOrderUploadCsvTest(String name)
	{
		super(name);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	public void testCreateColumnHeader_C0001()
	{
		final String STR_METHOD_NAME = "testCreateColumnHeader_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFXTransferOrderUploadCsvForTest l_transferOrderUploadCsvTest =
        	new WEB3AdminFXTransferOrderUploadCsvForTest();

        assertEquals(
    		l_transferOrderUploadCsvTest.getColumnHeader()[1].getColumnLabel(),
    		"利用者コード");
        log.exiting(STR_METHOD_NAME);
	}

	public void testValidateDetailsLine_C0001()
	{
		final String STR_METHOD_NAME = "testValidateDetailsLine_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFXTransferOrderUploadCsvForMock l_transferOrderUploadCsvTest =
        	new WEB3AdminFXTransferOrderUploadCsvForMock();

        try
        {
        	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AdminFXTransferOrderUploadCsv",
                "getUserCode", 
                new Class[] { int.class},
                "121２1");

        	l_transferOrderUploadCsvTest.validateDetailsLine(0, null);
        	fail();
        }
        catch (WEB3BaseException l_ex)
        {
        	log.debug(l_ex.getErrorMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02367);
        }
        catch (Exception l_ex)
        {
        	log.debug(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }
        log.exiting(STR_METHOD_NAME);
	}

	public void testValidateDetailsLine_C0002()
	{
		final String STR_METHOD_NAME = "testValidateDetailsLine_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFXTransferOrderUploadCsvForMock l_transferOrderUploadCsvTest =
        	new WEB3AdminFXTransferOrderUploadCsvForMock();

        try
        {
        	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AdminFXTransferOrderUploadCsv",
                "getUserCode", 
                new Class[] { int.class},
                "12121");
        	l_transferOrderUploadCsvTest.validateDetailsLine(0, null);
        	fail();
        }
        catch (WEB3BaseException l_ex)
        {
        	log.debug(l_ex.getErrorMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02368);
        }
        catch (Exception l_ex)
        {
        	log.debug(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }
        log.exiting(STR_METHOD_NAME);
	}

	public void testValidateDetailsLine_C0003()
	{
		final String STR_METHOD_NAME = "testValidateDetailsLine_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFXTransferOrderUploadCsvForMock l_transferOrderUploadCsvTest =
        	new WEB3AdminFXTransferOrderUploadCsvForMock();

        try
        {
        	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AdminFXTransferOrderUploadCsv",
                "getMainAccount", 
                new Class[] { int.class},
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, null));

        	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AdminFXTransferOrderUploadCsv",
                "getUserCode", 
                new Class[] { int.class},
                "12121254");

        	l_transferOrderUploadCsvTest.validateDetailsLine(0, null);
        	fail();
        }
        catch (WEB3BaseException l_ex)
        {
        	log.debug(l_ex.getErrorMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
        	log.debug(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }
        log.exiting(STR_METHOD_NAME);
	}

	class WEB3AdminFXTransferOrderUploadCsvForTest extends WEB3AdminFXTransferOrderUploadCsv
	{
		public WEB3AdminFXTransferOrderUploadCsvForTest()
		{
			super();
		}
		public WEB3GentradeCsvColumnModel[] getColumnHeader()
		{
			return columnHeader;
		}
	}
}
@
