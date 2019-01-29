head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.02.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqSendQueueReferenceRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 
 * @@author ding-zhaokui
 *
 */
public class WEB3AdminFeqSendQueueReferenceRequestTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqSendQueueReferenceRequestTest.class);
    WEB3AdminFeqSendQueueReferenceRequest l_request =
        new WEB3AdminFeqSendQueueReferenceRequest();

    public WEB3AdminFeqSendQueueReferenceRequestTest(String arg0)
    {
        super(arg0);
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
     * Test method for 'webbroker3.feq.message.WEB3AdminFeqSendQueueReferenceRequest.validate()'
     */
    public void testValidate_case1()
    {
        final String STR_METHOD_NAME = "testValidate_case1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.transactionDiv = "2";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testValidate_case2()
    {
        final String STR_METHOD_NAME = "testValidate_case2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.transactionDiv = "3";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01250,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);

    }

}
@
