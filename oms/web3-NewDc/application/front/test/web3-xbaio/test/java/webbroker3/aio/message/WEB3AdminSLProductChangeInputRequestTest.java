head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.22.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSLProductChangeInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3AdminSLProductChangeInputRequestTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/26 トウ鋒鋼 (中訊) 新規作成
*/
package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSLProductChangeInputRequestTest extends TestBaseForMock
{
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminSLProductChangeInputRequestTest.class);

    WEB3AdminSLProductChangeInputRequest l_request = null;
    
    public WEB3AdminSLProductChangeInputRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3AdminSLProductChangeInputRequest();
    }

    protected void tearDown() throws Exception
    {
        l_request = null;
        super.tearDown();
    }
    
    public void testValidate_case0001()
    {
        final String STR_METHOD_NAME = "testValidate_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02917, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidate_case0002()
    {
        final String STR_METHOD_NAME = "testValidate_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01444, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidate_case0003()
    {
        final String STR_METHOD_NAME = "testValidate_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070926", "yyyyMMdd");
        try
        {
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
