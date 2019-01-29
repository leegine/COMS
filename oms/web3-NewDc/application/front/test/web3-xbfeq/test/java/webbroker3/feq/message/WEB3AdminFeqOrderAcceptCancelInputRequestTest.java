head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.02.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqOrderAcceptCancelInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3AdminFeqOrderAcceptCancelInputRequestTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/05 武波 (中訊) 新規作成
*/
package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFeqOrderAcceptCancelInputRequestTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderAcceptCancelInputRequestTest.class);
    public WEB3AdminFeqOrderAcceptCancelInputRequestTest(String arg0)
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
            WEB3AdminFeqOrderAcceptCancelInputRequest l_request =
                new WEB3AdminFeqOrderAcceptCancelInputRequest();
            l_request.managementCode = "1234亜";
            l_request.branchCode ="381";
            l_request.accountCode="123456";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3ForeignSortKey l_key = new WEB3ForeignSortKey();
            l_key.keyItem = WEB3FeqSortKeyItemNameDef.ORDER_EMP_CODE;
            l_request.sortKeys = new WEB3ForeignSortKey[]{l_key};
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
            WEB3AdminFeqOrderAcceptCancelInputRequest l_request =
                new WEB3AdminFeqOrderAcceptCancelInputRequest();
            l_request.managementCode = "1234";
            l_request.branchCode ="381";
            l_request.accountCode="123456";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3ForeignSortKey l_key = new WEB3ForeignSortKey();
            l_key.keyItem = WEB3FeqSortKeyItemNameDef.ORDER_EMP_CODE;
            l_request.sortKeys = new WEB3ForeignSortKey[]{l_key};
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
            WEB3AdminFeqOrderAcceptCancelInputRequest l_request =
                new WEB3AdminFeqOrderAcceptCancelInputRequest();
            l_request.managementCode = "12345";
            l_request.branchCode ="381";
            l_request.accountCode="123456";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3ForeignSortKey l_key = new WEB3ForeignSortKey();
            l_key.keyItem = WEB3FeqSortKeyItemNameDef.ORDER_EMP_CODE;
            l_key.ascDesc = WEB3AscDescDef.ASC;
            l_request.sortKeys = new WEB3ForeignSortKey[]{l_key};
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
