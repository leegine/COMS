head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.38.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AccInfoElecDeliveryRegisterChangeCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (株)大和総研ビジネス・イノベーション
 File Name           : Webbroker3-helloworld プラグインクラス(WEB3HelloWorldPlugin.java)
 Author Name         : Daiwa Institute of Research Business Innovation
 Revision History    : 2010/11/19 劉レイ(北京中訊) 新規作成
 */
package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccInfoElecDeliveryRegisterChangeCompleteRequestTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoElecDeliveryRegisterChangeCompleteRequestTest.class);

    WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request = null;
    
    public WEB3AccInfoElecDeliveryRegisterChangeCompleteRequestTest(String name)
    {
        super(name);
        
        l_request = new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testValidateCase0001()
    {
        final String STR_METHOD_NAME="testValidateCase0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.tradingReportDiv = "";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03211, l_ex.getErrorInfo());
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
            l_request.positionReportDiv = "";
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
            l_request.opeReportDiv = "";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03213, l_ex.getErrorInfo());
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
            l_request.ordRulReportDiv = "";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03214, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateCase0005()
    {
        final String STR_METHOD_NAME="testValidateCase0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.report_div1 = "";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03215, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateCase0006()
    {
        final String STR_METHOD_NAME="testValidateCase0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.report_div2 = "";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03216, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateCase0007()
    {
        final String STR_METHOD_NAME="testValidateCase0007()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.report_div3 = "";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03217, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateCase0008()
    {
        final String STR_METHOD_NAME="testValidateCase0008()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.report_div4 = "";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03218, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateCase0009()
    {
        final String STR_METHOD_NAME="testValidateCase0009()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.report_div5 = "";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03219, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateCase0010()
    {
        final String STR_METHOD_NAME="testValidateCase0010()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.opeReportDiv = null;
            l_request.ordRulReportDiv = null;
            l_request.positionReportDiv = null;
            l_request.report_div1 = null;
            l_request.report_div2 = null;
            l_request.report_div3 = null;
            l_request.report_div4 = null;
            l_request.report_div5 = null;
            l_request.tradingReportDiv = null;
            
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03220, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateCase0011()
    {
        final String STR_METHOD_NAME="testValidateCase0011()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.opeReportDiv = "0";
            l_request.ordRulReportDiv = "1";
            l_request.positionReportDiv = "0";
            l_request.report_div1 = "1";
            l_request.report_div2 = "0";
            l_request.report_div3 = "1";
            l_request.report_div4 = "0";
            l_request.report_div5 = "1";
            l_request.tradingReportDiv = "0";
            
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
