head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.02.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqExecuteReferenceRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqExecuteReferenceRequestTest extends TestBaseForMock
{

	private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqExecuteReferenceRequestTest.class);

	public WEB3FeqExecuteReferenceRequestTest(String name)
	{
		super(name);
		// TODO Auto-generated constructor stub
	}

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	/*
	 * Test method for 'webbroker3.feq.message.WEB3FeqExecuteReferenceRequest.validate()'
	 */
	public void testValidate_C0001() 
	{
		final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3FeqExecuteReferenceRequest l_request = new WEB3FeqExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.execType = "3";
        l_request.sortKeys = null;

        try
        {
        	l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
        	assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
        	assertEquals("ソートキーが未指定です。", l_ex.getErrorMessage());
        }
        log.exiting(STR_METHOD_NAME);
	}

	public void testValidate_C0002() 
	{
		final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3FeqExecuteReferenceRequest l_request = new WEB3FeqExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.execType = "4";
        l_request.sortKeys = null;

        try
        {
        	l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
        	assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
        	assertEquals("ソートキーが未指定です。", l_ex.getErrorMessage());
        }
        log.exiting(STR_METHOD_NAME);
	}

	public void testValidate_C0003() 
	{
		final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3FeqExecuteReferenceRequest l_request = new WEB3FeqExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.execType = "5";

        try
        {
        	l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
        	assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00626, l_ex.getErrorInfo());
        }
        log.exiting(STR_METHOD_NAME);
	}
    
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FeqExecuteReferenceRequest l_request = new WEB3FeqExecuteReferenceRequest();
        l_request.referenceType = "1";
        l_request.execType = "5";
        
        try
        {
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
            
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00626, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FeqExecuteReferenceRequest l_request = new WEB3FeqExecuteReferenceRequest();
        l_request.referenceType = "1";
        l_request.execType = "4";
        
        try
        {
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
            
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FeqExecuteReferenceRequest l_request = new WEB3FeqExecuteReferenceRequest();
        l_request.referenceType = "1";
        l_request.execType = "3";
        
        try
        {
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
            
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
