head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.29.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3DocumentDeliverHistoryRegistRequesTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ExpirationDateListRequestTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/02 陸文靜（中訊）新規作成
*/
package webbroker3.gentrade.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3Exception;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3DocumentDeliverHistoryRegistRequesTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    WEB3DocumentDeliverHistoryRegistRequest l_request =null;

    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3ExpirationDateListRequestTest.class);

    public WEB3DocumentDeliverHistoryRegistRequesTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request =
            new WEB3DocumentDeliverHistoryRegistRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //  銘柄コードがnullの場合
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.debug(TEST_START + STR_METHOD_NAME);
        l_request.productCode = null;
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals( WEB3ErrorCatalog.BUSINESS_ERROR_00079, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(STR_METHOD_NAME);
        l_request.productCode = new String[0] ;
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals( WEB3ErrorCatalog.BUSINESS_ERROR_00079, l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    //  種別コードがnullの場合
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(STR_METHOD_NAME);
        l_request.productCode = new String[]{"aa"};
        l_request.typeCode = null;
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals( WEB3ErrorCatalog.BUSINESS_ERROR_02202, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(STR_METHOD_NAME);
        l_request.productCode = new String[]{"aa"};
        l_request.typeCode = "b";
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
}
@
