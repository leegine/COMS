head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.39.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AccInfoEquityCommissionCourseChangeCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3AccInfoEquityCommissionCourseChangeCompleteRequestTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/08/21 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccInfoEquityCommissionCourseChangeCompleteRequestTest extends TestBaseForMock
{
    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccInfoEquityCommissionCourseChangeCompleteRequestTest.class);
    WEB3AccInfoEquityCommissionCourseChangeCompleteRequest l_request = null;
    public WEB3AccInfoEquityCommissionCourseChangeCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3AccInfoEquityCommissionCourseChangeCompleteRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //手数料コースのチェック
    //未入力の場合、例外をスローする。
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = ".testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
 
        try 
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex) 
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01095, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //手数料コースのチェック
    //不正なコード値の場合、例外をスローする。
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = ".testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
 
        try 
        {
            l_request.commissionCourse = "9";
            l_request.validate();
        }
        catch (WEB3BaseException l_ex) 
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01096, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //normalcase
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = ".testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
 
        try 
        {
            l_request.commissionCourse = "99";
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

}
@
