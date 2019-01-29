head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.22.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSLProductCancelConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄登録取消確認リクエスト(WEB3AdminSLProductCancelConfirmRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/25 孫洪江 (中訊) 新規作成
*/

package webbroker3.aio.message;

import java.util.Calendar;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSLProductCancelConfirmRequestTest extends TestBaseForMock
{
    private WEB3AdminSLProductCancelConfirmRequest l_request = null;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
                 WEB3AdminSLProductCancelConfirmRequestTest.class);

    public WEB3AdminSLProductCancelConfirmRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        this.l_request = new WEB3AdminSLProductCancelConfirmRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.l_request = null;
    }

    /**
     * １）　@リクエスト.担保銘柄検索キーがnullの場合、例外をスロー
     * 
     * 抛出異常: BUSINESS_ERROR_02917
     */
    public void testValidate_C0001()
    {
        String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.searchConditions = null;
            this.l_request.validate();
            fail(); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02917, l_ex.getErrorInfo());
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * ２）　@リクエスト.担保銘柄検索キー.適用期間fromがnullの場合、例外をスロー
     * 
     * 抛出異常: BUSINESS_ERROR_01444
     */
    public void testValidate_C0002()
    {
        String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.searchConditions = new WEB3SLProductSearchConditions();
            this.l_request.searchConditions.targetPeriodFrom = null;
            this.l_request.validate();
            fail(); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01444, l_ex.getErrorInfo());
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 正常 
     */
    public void testValidate_C0003()
    {
        String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.searchConditions = new WEB3SLProductSearchConditions();
            this.l_request.searchConditions.targetPeriodFrom = Calendar.getInstance().getTime();
            this.l_request.validate();
            assertTrue(true);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            
            fail(); 
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
