head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.02.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqOrderAndExecutionInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : iWEB3AdminFeqOrderAndExecutionInputRequestTest.javaj
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/05 g (u) VKì¬
*/
package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFeqOrderAndExecutionInputRequestTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderAndExecutionInputRequestTest.class);
    public WEB3AdminFeqOrderAndExecutionInputRequestTest(String arg0)
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

    public void test_validate_c0001()
    {
        final String STR_METHOD_NAME = "test_validate_c0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFeqOrderAndExecutionInputRequest l_request =
                new WEB3AdminFeqOrderAndExecutionInputRequest();
            l_request.managementCode = "1234";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03163);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void test_validate_c0002()
    {
        final String STR_METHOD_NAME = "test_validate_c0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFeqOrderAndExecutionInputRequest l_request =
                new WEB3AdminFeqOrderAndExecutionInputRequest();
            l_request.managementCode = "1234";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03163);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void test_validate_c0003()
    {
        final String STR_METHOD_NAME = "test_validate_c0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFeqOrderAndExecutionInputRequest l_request =
                new WEB3AdminFeqOrderAndExecutionInputRequest();
            l_request.managementCode = "12345";
            l_request.validate();
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
}
@
