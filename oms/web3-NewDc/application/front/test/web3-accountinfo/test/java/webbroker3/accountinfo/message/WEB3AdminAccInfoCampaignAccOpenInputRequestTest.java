head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.40.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoCampaignAccOpenInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ口座開設条件変更入力ﾘｸｴｽﾄテスト(WEB3AdminAccInfoCampaignAccOpenInputRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/6  齊珂(中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import test.util.TestSpecialClassUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccInfoCampaignAccOpenInputRequestTest extends TestBaseForMock
{

    WEB3AdminAccInfoCampaignAccOpenInputRequest l_request = null;
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignAccOpenInputRequestTest.class);
    
    public WEB3AdminAccInfoCampaignAccOpenInputRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        l_request = new WEB3AdminAccInfoCampaignAccOpenInputRequest();
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testValidate41()
    {
        final String STR_METHOD_NAME = "testValidate41()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "2";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02721,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate41>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate42()
    {
        final String STR_METHOD_NAME = "testValidate42()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "1";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02716,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate42>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate43()
    {
        final String STR_METHOD_NAME = "testValidate43()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            l_request.validate();
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate43>>>>>>>>>>>>>>>test pass !!");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02721,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate43>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate44()
    {
        final String STR_METHOD_NAME = "testValidate44()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestSpecialClassUtility.testCreateResponse(WEB3AdminAccInfoCampaignAccOpenInputRequest.class);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

}
@
