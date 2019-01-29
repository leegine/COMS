head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.39.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoCampaignIndiviCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ個別顧客指定変更完了ﾘｸｴｽﾄテスト(WEB3AdminAccInfoCampaignIndiviCompleteRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/6  齊珂(中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import test.util.TestSpecialClassUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccInfoCampaignIndiviCompleteRequestTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignIndiviCompleteRequestTest.class);
    
    public WEB3AdminAccInfoCampaignIndiviCompleteRequestTest(String arg0)
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

    public void testValidate01()
    {
        final String STR_METHOD_NAME = "testValidate01()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviCompleteRequest();
        
        try
        {
            l_request.updateFlag = "3";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02710,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate01>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate02()
    {
        final String STR_METHOD_NAME = "testValidate02()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviCompleteRequest();
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        try
        {
            l_request.updateFlag = "1";
            l_request.commissionCampaignInfo.registType = "3";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02711,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate02>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate03()
    {
        final String STR_METHOD_NAME = "testValidate03()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviCompleteRequest();
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        try
        {
            l_request.updateFlag = "0";
            l_request.commissionCampaignInfo.registType = "2";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00833,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate03>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate04()
    {
        final String STR_METHOD_NAME = "testValidate04()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviCompleteRequest();
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        try
        {
            l_request.updateFlag = "0";
            l_request.commissionCampaignInfo.registType = "2";
            l_request.commissionCampaignInfo.branchCode = "1234";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00834,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate04>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate05()
    {
        final String STR_METHOD_NAME = "testValidate05()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviCompleteRequest();
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        try
        {
            l_request.updateFlag = "0";
            l_request.commissionCampaignInfo.registType = "2";
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00835,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate05>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate06()
    {
        final String STR_METHOD_NAME = "testValidate06()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviCompleteRequest();
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        try
        {
            l_request.updateFlag = "0";
            l_request.commissionCampaignInfo.registType = "2";
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.accountCode = "12345";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00836,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate06>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate07()
    {
        final String STR_METHOD_NAME = "testValidate07()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviCompleteRequest();
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        try
        {
            l_request.updateFlag = "0";
            l_request.commissionCampaignInfo.registType = "2";
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.accountCode = "123456";
            l_request.commissionCampaignInfo.campaignName = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
            log.info("l_request.commissionCampaignInfo.campaignName.length == " + l_request.commissionCampaignInfo.campaignName.length());
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02709,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate07>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate08()
    {
        final String STR_METHOD_NAME = "testValidate08()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviCompleteRequest();
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        try
        {
            l_request.updateFlag = "0";
            l_request.commissionCampaignInfo.registType = "1";
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.accountCode = "123456";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02712,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate08>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate09()
    {
        final String STR_METHOD_NAME = "testValidate09()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviCompleteRequest();
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        try
        {
            l_request.updateFlag = "0";
            l_request.commissionCampaignInfo.registType = "2";
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.accountCode = "123456";
            l_request.commissionCampaignInfo.campaignName = "a";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02725,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate09>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate10()
    {
        final String STR_METHOD_NAME = "testValidate10()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviCompleteRequest();
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        try
        {
            l_request.updateFlag = "0";
            l_request.commissionCampaignInfo.registType = "1";
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.accountCode = "123456";
            l_request.commissionCampaignInfo.campaignName = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
            l_request.commissionCampaignInfo.targetPeriodFrom = new Date("2007/02/05");
            l_request.commissionCampaignInfo.targetPeriodTo = new Date("2007/02/04");
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02715,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate10>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate11()
    {
        final String STR_METHOD_NAME = "testValidate11()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviCompleteRequest();
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        try
        {
            l_request.updateFlag = "0";
            l_request.commissionCampaignInfo.registType = "1";
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.accountCode = "123456";
            l_request.commissionCampaignInfo.campaignName = "a";
            l_request.commissionCampaignInfo.targetPeriodFrom = new Date("2007/02/03");
            l_request.commissionCampaignInfo.targetPeriodTo = new Date("2007/02/04");
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02080,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate11>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate12()
    {
        final String STR_METHOD_NAME = "testValidate12()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviCompleteRequest();
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        try
        {
            l_request.updateFlag = "1";
            l_request.commissionCampaignInfo.registType = "1";
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.accountCode = "123456";
            l_request.commissionCampaignInfo.campaignName = "a";
            l_request.commissionCampaignInfo.targetPeriodFrom = new Date("2007/02/03");
            l_request.commissionCampaignInfo.targetPeriodTo = new Date("2007/02/04");
            l_request.commissionCampaignInfo.collectRate = "101";
            
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02082,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate12>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate13()
    {
        final String STR_METHOD_NAME = "testValidate13()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviCompleteRequest();
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        try
        {
            l_request.updateFlag = "1";
            l_request.commissionCampaignInfo.registType = "1";
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.accountCode = "123456";
            l_request.commissionCampaignInfo.campaignName = "a";
            l_request.commissionCampaignInfo.targetPeriodFrom = new Date("2007/02/03");
            l_request.commissionCampaignInfo.targetPeriodTo = new Date("2007/02/04");
            l_request.commissionCampaignInfo.collectRate = "-1";
            
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02082,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate13>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate14()
    {
        final String STR_METHOD_NAME = "testValidate14()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviCompleteRequest();
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        try
        {
            l_request.updateFlag = "0";
            l_request.commissionCampaignInfo.registType = "1";
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.accountCode = "123456";
            l_request.commissionCampaignInfo.campaignName = "a";
            l_request.commissionCampaignInfo.targetPeriodFrom = new Date("2007/02/10");
            l_request.commissionCampaignInfo.targetPeriodTo = new Date("2007/02/04");
            l_request.commissionCampaignInfo.collectRate = "99";
            
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02715,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate14>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate15()
    {
        final String STR_METHOD_NAME = "testValidate15()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviCompleteRequest();
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        try
        {
            l_request.updateFlag = "0";
            l_request.commissionCampaignInfo.registType = "1";
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.accountCode = "123456";
            l_request.commissionCampaignInfo.campaignName = "a";
            l_request.commissionCampaignInfo.targetPeriodFrom = new Date("2007/02/03");
            l_request.commissionCampaignInfo.targetPeriodTo = new Date("2007/03/05");
            l_request.commissionCampaignInfo.collectRate = "99";
            
            
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02715,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate15>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate16()
    {
        final String STR_METHOD_NAME = "testValidate16()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviCompleteRequest();

        try
        {
            TestSpecialClassUtility.testCreateResponse(WEB3AdminAccInfoCampaignIndiviCompleteRequest.class);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
