head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.39.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AccInfoElecDeliveryRegisterChangeInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (株)大和総研ビジネス・イノベーション
 File Name           : WEB3AccInfoElecDeliveryRegisterChangeInputRequestTest.java
 Author Name         : Daiwa Institute of Research Business Innovation
 Revision History    : 2010/11/19 劉レイ(北京中訊) 新規作成
 */
package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccInfoElecDeliveryRegisterChangeInputRequestTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoElecDeliveryRegisterChangeInputRequestTest.class);

    WEB3AccInfoElecDeliveryRegisterChangeInputRequest l_request = null;
    
    protected void setUp() throws Exception
    {
        super.setUp();
        
        l_request = new WEB3AccInfoElecDeliveryRegisterChangeInputRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public WEB3AccInfoElecDeliveryRegisterChangeInputRequestTest(String name)
    {
        super(name);
    }

    public void testValidateCase0001()
    {
        final String STR_METHOD_NAME="testValidateCase0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.eleDeliveryFlag = null;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03221, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateCase0002()
    {
        final String STR_METHOD_NAME="testValidateCase0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.eleDeliveryFlag = "";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03221, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateCase0003()
    {
        final String STR_METHOD_NAME="testValidateCase0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.eleDeliveryFlag = "*";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03222, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateCase0004()
    {
        final String STR_METHOD_NAME="testValidateCase0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.eleDeliveryFlag = "0";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
}
@
