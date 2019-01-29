head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.50.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginExecuteReferenceRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3MarginExecuteReferenceRequestTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/10/18 金傑（中訊）新規作成
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MarginExecuteReferenceRequestTest extends TestBaseForMock
{

    private WEB3MarginExecuteReferenceRequest l_request = null;

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginExecuteReferenceRequestTest.class);

    public WEB3MarginExecuteReferenceRequestTest(String name)
    {
        super(name);
    }
    
    public void setUp() throws Exception
    {
        super.setUp();
        this.l_request = new WEB3MarginExecuteReferenceRequest();
        
        //referenceType
        this.l_request.referenceType = "1";
        // execType
        this.l_request.execType = "2";
        
        WEB3MarginSortKey[] sortKeys = new WEB3MarginSortKey[1];
        sortKeys[0] = new WEB3MarginSortKey();
        sortKeys[0].ascDesc = "A";
        // productCode
        sortKeys[0].keyItem = "productCode";
        this.l_request.sortKeys = sortKeys;
        
        this.l_request.pageIndex = "1";
        this.l_request.pageSize = "2";
        
        // marketCode
        this.l_request.marketCode = "1";
        
    }
    
    public void tearDown() throws Exception
    {
        this.l_request = null;
        super.tearDown();
    }
    
    /**
     * 発注条件区分の値が存在しないコード値です
     * 
     * スロー:BUSINESS_ERROR_00212のメッセージ
     *
     */
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.orderCondType = "3";
            this.l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00212,l_ex.getErrorInfo());
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * 
     * 正常結束
     *
     */
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.orderCondType = "1";
            this.l_request.validate();

        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }

}
@
