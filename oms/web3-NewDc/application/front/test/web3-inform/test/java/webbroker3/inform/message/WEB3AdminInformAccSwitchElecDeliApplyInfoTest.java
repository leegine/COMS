head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.32.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformAccSwitchElecDeliApplyInfoTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座切替・電子交付申込情報(WEB3AdminInformAccSwitchElecDeliApplyInfoTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/24 孫洪江 (中訊) 新規作成
*/

package webbroker3.inform.message;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminInformAccSwitchElecDeliApplyInfoTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3AdminInformAccSwitchElecDeliApplyInfoTest.class);

    public WEB3AdminInformAccSwitchElecDeliApplyInfoTest(String arg0)
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

    public void testValidate_0001()
    {
        String STR_METHOD_NAME = ".testValidate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//        
//        l_applyInfo.mobileAccoutDiv = null;
//        l_applyInfo.tradingReportDiv = null;
//        l_applyInfo.positionReportDiv = null;
//        l_applyInfo.positionReportCycleDiv = null;
//        l_applyInfo.certificateDepositDiv = null;
//        l_applyInfo.accountStatementDiv = null;
//        l_applyInfo.taxType = null;
//        l_applyInfo.taxTypeNext = null;
//        l_applyInfo.marginTaxType = null;
//        l_applyInfo.marginTaxTypeNext = null;
//        l_applyInfo.capitalGainTaxAccOpenDiv = null;
//
//        try
//        {
//            l_applyInfo.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02688, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
    }
//    
//    public void testValidate_0002()
//    {
//        String STR_METHOD_NAME = ".testValidate_0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//        
//        l_applyInfo.mobileAccoutDiv = "1";
//        l_applyInfo.tradingReportDiv = null;
//        l_applyInfo.positionReportDiv = null;
//        l_applyInfo.positionReportCycleDiv = null;
//        l_applyInfo.certificateDepositDiv = null;
//        l_applyInfo.accountStatementDiv = null;
//        l_applyInfo.taxType = null;
//        l_applyInfo.taxTypeNext = null;
//        l_applyInfo.marginTaxType = null;
//        l_applyInfo.marginTaxTypeNext = null;
//        l_applyInfo.capitalGainTaxAccOpenDiv = null;
//
//        try
//        {
//            l_applyInfo.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);            
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }
//    
//    public void testValidate_0003()
//    {
//        String STR_METHOD_NAME = ".testValidate_0003()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//        
//        l_applyInfo.tradingReportDiv = "1";
//        l_applyInfo.positionReportDiv = null;
//        l_applyInfo.positionReportCycleDiv = null;
//        l_applyInfo.certificateDepositDiv = null;
//        l_applyInfo.accountStatementDiv = null;
//
//        try
//        {
//            l_applyInfo.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02872, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }
//    
//    public void testValidate_0004()
//    {
//        String STR_METHOD_NAME = ".testValidate_0004()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//        
//        l_applyInfo.tradingReportDiv = "1";
//        l_applyInfo.positionReportDiv = "1";
//        l_applyInfo.positionReportCycleDiv = "1";
//        l_applyInfo.certificateDepositDiv = "1";
//        l_applyInfo.accountStatementDiv = "1";
//
//        try
//        {
//            l_applyInfo.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);            
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }
//    
//    public void testValidate_0005()
//    {
//        String STR_METHOD_NAME = ".testValidate_0005()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//        
//        l_applyInfo.taxType = "1";
//        l_applyInfo.taxTypeNext = "1";
//            
//        try
//        {
//            l_applyInfo.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);            
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }
//    
//    public void testValidate_0006()
//    {
//        String STR_METHOD_NAME = ".testValidate_0006()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//        
//        l_applyInfo.taxType = "1";
//        l_applyInfo.taxTypeNext = null;
//
//        try
//        {
//            l_applyInfo.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02873, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }
//    
//    public void testValidate_0007()
//    {
//        String STR_METHOD_NAME = ".testValidate_0007()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//        
//        l_applyInfo.marginTaxType = "1";
//        l_applyInfo.marginTaxTypeNext = "1";
//            
//        try
//        {
//            l_applyInfo.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);            
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }
//    
//    public void testValidate_0008()
//    {
//        String STR_METHOD_NAME = ".testValidate_0008()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//        
//        l_applyInfo.marginTaxType = "1";
//        l_applyInfo.marginTaxTypeNext = null;
//
//        try
//        {
//            l_applyInfo.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02874, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }
//
//    public void testValidate_0009()
//    {
//        String STR_METHOD_NAME = ".testValidate_0009()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//        l_applyInfo.taxType = "2";
//        l_applyInfo.marginTaxType = "7";
//
//        l_applyInfo.taxTypeNext = "1";
//        l_applyInfo.marginTaxTypeNext = "1";
//        l_applyInfo.mobileAccoutDiv = "1";
//
//        try
//        {
//            l_applyInfo.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02875, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }
//
//    public void testValidate_0010()
//    {
//        String STR_METHOD_NAME = ".testValidate_0010()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//
//        l_applyInfo.taxType = "2";
//        l_applyInfo.marginTaxType = "1";
//        
//        l_applyInfo.mobileAccoutDiv = "1";
//        l_applyInfo.taxTypeNext = "1";
//        l_applyInfo.marginTaxTypeNext = "1";
//
//        try
//        {
//            l_applyInfo.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }
//
//    public void testValidate_0011()
//    {
//        String STR_METHOD_NAME = ".testValidate_0011()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//
//        l_applyInfo.taxType = "2";
//        l_applyInfo.marginTaxType = "2";
//        
//        l_applyInfo.mobileAccoutDiv = "1";
//        l_applyInfo.taxTypeNext = "1";
//        l_applyInfo.marginTaxTypeNext = "2";
//
//        try
//        {
//            l_applyInfo.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }
//    
//    public void testValidate_0012()
//    {
//        String STR_METHOD_NAME = ".testValidate_0012()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//
//        
//        l_applyInfo.taxType = "2";
//        l_applyInfo.marginTaxType = null;
//        
//        l_applyInfo.mobileAccoutDiv = "1";
//        l_applyInfo.taxTypeNext = "1";
//        l_applyInfo.marginTaxTypeNext = null;
//
//        try
//        {
//            l_applyInfo.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }
//    
//  public void testValidate_0013()
//  {
//      String STR_METHOD_NAME = ".testValidate_0013()";
//      log.entering(TEST_START + STR_METHOD_NAME);
//
//      WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//      l_applyInfo.taxTypeNext = "2";
//      l_applyInfo.marginTaxTypeNext = "7";
//      
//      l_applyInfo.taxType = "2";
//      l_applyInfo.marginTaxType = "1";
//      l_applyInfo.mobileAccoutDiv = "1";
//
//      try
//      {
//          l_applyInfo.validate();
//
//          log.exiting(TEST_END + STR_METHOD_NAME);
//          fail();
//      }
//      catch (WEB3BaseException l_ex)
//      {
//          log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//          log.exiting(TEST_END + STR_METHOD_NAME);
//
//          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02875, l_ex.getErrorInfo());
//      }
//      catch (Exception l_ex)
//      {
//          log.error(TEST_END + STR_METHOD_NAME, l_ex);
//          log.exiting(TEST_END + STR_METHOD_NAME);
//
//          fail();
//      }
//  }
//
//  public void testValidate_0014()
//  {
//      String STR_METHOD_NAME = ".testValidate_0014()";
//      log.entering(TEST_START + STR_METHOD_NAME);
//
//      WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//      l_applyInfo.taxTypeNext = "2";
//      l_applyInfo.marginTaxTypeNext = "1";
//      
//      l_applyInfo.taxType = "2";
//      l_applyInfo.marginTaxType = "1";
//      l_applyInfo.mobileAccoutDiv = "1";
//
//      try
//      {
//          l_applyInfo.validate();
//
//          log.exiting(TEST_END + STR_METHOD_NAME);
//      }
//      catch (WEB3BaseException l_ex)
//      {
//          log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//          log.exiting(TEST_END + STR_METHOD_NAME);
//
//          fail();
//      }
//      catch (Exception l_ex)
//      {
//          log.error(TEST_END + STR_METHOD_NAME, l_ex);
//          log.exiting(TEST_END + STR_METHOD_NAME);
//
//          fail();
//      }
//  }
//
//  public void testValidate_0015()
//  {
//      String STR_METHOD_NAME = ".testValidate_0015()";
//      log.entering(TEST_START + STR_METHOD_NAME);
//
//      WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//      l_applyInfo.taxTypeNext = "2";
//      l_applyInfo.marginTaxTypeNext = "2";
//
//      l_applyInfo.taxType = "2";
//      l_applyInfo.marginTaxType = "1";
//      l_applyInfo.mobileAccoutDiv = "1";
//
//      try
//      {
//          l_applyInfo.validate();
//
//          log.exiting(TEST_END + STR_METHOD_NAME);
//      }
//      catch (WEB3BaseException l_ex)
//      {
//          log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//          log.exiting(TEST_END + STR_METHOD_NAME);
//
//          fail();
//      }
//      catch (Exception l_ex)
//      {
//          log.error(TEST_END + STR_METHOD_NAME, l_ex);
//          log.exiting(TEST_END + STR_METHOD_NAME);
//
//          fail();
//      }
//  }
//  
//  public void testValidate_0016()
//  {
//      String STR_METHOD_NAME = ".testValidate_0016()";
//      log.entering(TEST_START + STR_METHOD_NAME);
//
//      WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//      l_applyInfo.taxTypeNext = "2";
//      l_applyInfo.marginTaxTypeNext = null;
//
//      l_applyInfo.taxType = "2";
//      l_applyInfo.marginTaxType = null;
//      l_applyInfo.mobileAccoutDiv = "1";
//
//      try
//      {
//          l_applyInfo.validate();
//
//          log.exiting(TEST_END + STR_METHOD_NAME);
//      }
//      catch (WEB3BaseException l_ex)
//      {
//          log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//          log.exiting(TEST_END + STR_METHOD_NAME);
//
//          fail();
//      }
//      catch (Exception l_ex)
//      {
//          log.error(TEST_END + STR_METHOD_NAME, l_ex);
//          log.exiting(TEST_END + STR_METHOD_NAME);
//
//          fail();
//      }
//  }
//
//    public void testValidate_0017()
//    {
//        String STR_METHOD_NAME = ".testValidate_0017()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//        l_applyInfo.taxType = "3";
//        l_applyInfo.marginTaxType = "7";
//
//        l_applyInfo.taxTypeNext = "2";
//        l_applyInfo.marginTaxTypeNext = "1";
//        l_applyInfo.mobileAccoutDiv = "1";
//
//        try
//        {
//            l_applyInfo.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02875, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }
//
//    public void testValidate_0018()
//    {
//        String STR_METHOD_NAME = ".testValidate_0018()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//        l_applyInfo.taxType = "3";
//        l_applyInfo.marginTaxType = "1";
//        
//        l_applyInfo.taxTypeNext = "2";
//        l_applyInfo.marginTaxTypeNext = "1";
//        l_applyInfo.mobileAccoutDiv = "1";
//
//        try
//        {
//            l_applyInfo.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }
//
//    public void testValidate_0019()
//    {
//        String STR_METHOD_NAME = ".testValidate_0019()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//        l_applyInfo.taxType = "3";
//        l_applyInfo.marginTaxType = "3";
//
//        l_applyInfo.taxTypeNext = "2";
//        l_applyInfo.marginTaxTypeNext = "1";
//        l_applyInfo.mobileAccoutDiv = "1";
//
//        try
//        {
//            l_applyInfo.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }
//    
//    public void testValidate_0020()
//    {
//        String STR_METHOD_NAME = ".testValidate_0020()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//        l_applyInfo.taxType = "3";
//        l_applyInfo.marginTaxType = null;
//        
//        l_applyInfo.taxTypeNext = "2";
//        l_applyInfo.marginTaxTypeNext = null;
//        l_applyInfo.mobileAccoutDiv = "1";
//
//        try
//        {
//            l_applyInfo.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }
//
//    public void testValidate_0021()
//    {
//        String STR_METHOD_NAME = ".testValidate_0021()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//        l_applyInfo.taxTypeNext = "3";
//        l_applyInfo.marginTaxTypeNext = "7";
//
//        l_applyInfo.taxType = "2";
//        l_applyInfo.marginTaxType = "1";
//        l_applyInfo.mobileAccoutDiv = "1";
//
//        try
//        {
//            l_applyInfo.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02875, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }
//
//    public void testValidate_0022()
//    {
//        String STR_METHOD_NAME = ".testValidate_0022()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//        l_applyInfo.taxTypeNext = "3";
//        l_applyInfo.marginTaxTypeNext = "1";
//        
//        l_applyInfo.taxType = "2";
//        l_applyInfo.marginTaxType = "1";
//        l_applyInfo.mobileAccoutDiv = "1";
//
//        try
//        {
//            l_applyInfo.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }
//
//    public void testValidate_0023()
//    {
//        String STR_METHOD_NAME = ".testValidate_0023()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//        l_applyInfo.taxTypeNext = "3";
//        l_applyInfo.marginTaxTypeNext = "3";
//
//        l_applyInfo.taxType = "2";
//        l_applyInfo.marginTaxType = "1";
//        l_applyInfo.mobileAccoutDiv = "1";
//
//        try
//        {
//            l_applyInfo.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }
//
//    public void testValidate_0024()
//    {
//        String STR_METHOD_NAME = ".testValidate_0024()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//        l_applyInfo.taxTypeNext = "3";
//        l_applyInfo.marginTaxTypeNext = null;
//
//        l_applyInfo.taxType = "2";
//        l_applyInfo.marginTaxType = null;
//        l_applyInfo.mobileAccoutDiv = "1";
//
//        try
//        {
//            l_applyInfo.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }
//
//  public void testValidate_0025()
//  {
//      String STR_METHOD_NAME = ".testValidate_0025()";
//      log.entering(TEST_START + STR_METHOD_NAME);
//
//      WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//      l_applyInfo.taxType = "1";
//      l_applyInfo.marginTaxType = "1";
//      l_applyInfo.capitalGainTaxAccOpenDiv = "1";
//
//      l_applyInfo.taxTypeNext = "2";
//      l_applyInfo.marginTaxTypeNext = "1";
//      l_applyInfo.mobileAccoutDiv = "1";
//
//      try
//      {
//          l_applyInfo.validate();
//
//          log.exiting(TEST_END + STR_METHOD_NAME);
//          fail();
//      }
//      catch (WEB3BaseException l_ex)
//      {
//          log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//          log.exiting(TEST_END + STR_METHOD_NAME);
//
//          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02876, l_ex.getErrorInfo());
//      }
//      catch (Exception l_ex)
//      {
//          log.error(TEST_END + STR_METHOD_NAME, l_ex);
//          log.exiting(TEST_END + STR_METHOD_NAME);
//
//          fail();
//      }
//  }
//
//  public void testValidate_0026()
//  {
//      String STR_METHOD_NAME = ".testValidate_0026()";
//      log.entering(TEST_START + STR_METHOD_NAME);
//
//      WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//      l_applyInfo.taxType = "1";
//      l_applyInfo.marginTaxType = "1";
//      l_applyInfo.capitalGainTaxAccOpenDiv = "2";
//
//      l_applyInfo.taxTypeNext = "2";
//      l_applyInfo.marginTaxTypeNext = "1";
//      l_applyInfo.mobileAccoutDiv = "1";
//
//      try
//      {
//          l_applyInfo.validate();
//
//          log.exiting(TEST_END + STR_METHOD_NAME);
//
//      }
//      catch (WEB3BaseException l_ex)
//      {
//          log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//          log.exiting(TEST_END + STR_METHOD_NAME);
//
//          fail();
//      }
//      catch (Exception l_ex)
//      {
//          log.error(TEST_END + STR_METHOD_NAME, l_ex);
//          log.exiting(TEST_END + STR_METHOD_NAME);
//
//          fail();
//      }
//  }
//
//  public void testValidate_0027()
//  {
//      String STR_METHOD_NAME = ".testValidate_0027()";
//      log.entering(TEST_START + STR_METHOD_NAME);
//
//      WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//      l_applyInfo.mobileAccoutDiv = "1";
//      l_applyInfo.tradingReportDiv = "1";
//      l_applyInfo.positionReportDiv = "0";
//
//      l_applyInfo.mobileAccoutDiv = "1";
//      l_applyInfo.positionReportCycleDiv = "1";
//      l_applyInfo.certificateDepositDiv = "1";
//      l_applyInfo.accountStatementDiv = "1";
//
//      try
//      {
//          l_applyInfo.validate();
//
//          log.exiting(TEST_END + STR_METHOD_NAME);
//          fail();
//      }
//      catch (WEB3BaseException l_ex)
//      {
//          log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//          log.exiting(TEST_END + STR_METHOD_NAME);
//
//          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02877, l_ex.getErrorInfo());
//      }
//      catch (Exception l_ex)
//      {
//          log.error(TEST_END + STR_METHOD_NAME, l_ex);
//          log.exiting(TEST_END + STR_METHOD_NAME);
//
//          fail();
//      }
//  }
//
//  public void testValidate_0028()
//  {
//      String STR_METHOD_NAME = ".testValidate_0028()";
//      log.entering(TEST_START + STR_METHOD_NAME);
//
//      WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//      l_applyInfo.mobileAccoutDiv = "1";
//      l_applyInfo.tradingReportDiv = "0";
//      l_applyInfo.positionReportDiv = "9";
//
//      l_applyInfo.mobileAccoutDiv = "1";
//      l_applyInfo.positionReportCycleDiv = "1";
//      l_applyInfo.certificateDepositDiv = "1";
//      l_applyInfo.accountStatementDiv = "1";
//
//      try
//      {
//          l_applyInfo.validate();
//
//          log.exiting(TEST_END + STR_METHOD_NAME);
//          fail();
//      }
//      catch (WEB3BaseException l_ex)
//      {
//          log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//          log.exiting(TEST_END + STR_METHOD_NAME);
//
//          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02877, l_ex.getErrorInfo());
//      }
//      catch (Exception l_ex)
//      {
//          log.error(TEST_END + STR_METHOD_NAME, l_ex);
//          log.exiting(TEST_END + STR_METHOD_NAME);
//
//          fail();
//      }
//  }
//
//  public void testValidate_0029()
//  {
//      String STR_METHOD_NAME = ".testValidate_0029()";
//      log.entering(TEST_START + STR_METHOD_NAME);
//
//      WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//      l_applyInfo.mobileAccoutDiv = "1";
//      l_applyInfo.tradingReportDiv = "0";
//      l_applyInfo.positionReportDiv = "0";
//
//      l_applyInfo.mobileAccoutDiv = "1";
//      l_applyInfo.positionReportCycleDiv = "1";
//      l_applyInfo.certificateDepositDiv = "1";
//      l_applyInfo.accountStatementDiv = "1";
//
//      try
//      {
//          l_applyInfo.validate();
//
//          log.exiting(TEST_END + STR_METHOD_NAME);
//
//      }
//      catch (WEB3BaseException l_ex)
//      {
//          log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//          log.exiting(TEST_END + STR_METHOD_NAME);
//
//          fail();
//      }
//      catch (Exception l_ex)
//      {
//          log.error(TEST_END + STR_METHOD_NAME, l_ex);
//          log.exiting(TEST_END + STR_METHOD_NAME);
//
//          fail();
//      }
//  }
}
@
