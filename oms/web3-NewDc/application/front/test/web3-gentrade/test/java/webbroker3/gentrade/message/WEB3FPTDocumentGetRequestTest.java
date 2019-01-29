head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.29.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FPTDocumentGetRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (株)大和総研ビジネス・イノベーション
 File Name           : WEB3FPTDocumentGetRequestTest.java
 Author Name         : Daiwa Institute of Research Business Innovation
 Revision History    : 2010/11/17 劉レイ(北京中訊) 新規作成
 */
package webbroker3.gentrade.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FPTDocumentGetRequestTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FPTDocumentGetRequestTest.class);
    
    WEB3FPTDocumentGetRequest l_request =null;
    
    protected void setUp() throws Exception
    {
        super.setUp();
        
        l_request = new WEB3FPTDocumentGetRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public WEB3FPTDocumentGetRequestTest(String name)
    {
        super(name);
    }

    public void testValidateCase0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(STR_METHOD_NAME);
        
        l_request.deliveryTarget = null;
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals( WEB3ErrorCatalog.BUSINESS_ERROR_03223, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
}
@
