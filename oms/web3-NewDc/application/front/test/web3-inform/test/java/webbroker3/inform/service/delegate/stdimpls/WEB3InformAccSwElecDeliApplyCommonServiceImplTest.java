head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.13.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3InformAccSwElecDeliApplyCommonServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3InformAccSwElecDeliApplyCommonServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/24 �����i���u�j�V�K�쐬
*/
package webbroker3.inform.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.EraParams;
import webbroker3.inform.WEB3Inform;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliAppDtInfo;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliApplyInfo;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3InformAccSwElecDeliApplyCommonServiceImplTest extends TestBaseForMock
{

    private WEB3InformAccSwElecDeliApplyCommonServiceImpl l_serviceImpl = null;

    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformReferenceServiceImplTest.class);

    public WEB3InformAccSwElecDeliApplyCommonServiceImplTest(String l_strName)
    {
        super(l_strName);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.l_serviceImpl = new WEB3InformAccSwElecDeliApplyCommonServiceImpl();
        TestDBUtility.deleteAll(EraParams.TYPE);
        EraParams l_eraParams = this.getEraParams();
        l_eraParams.setJapaneseEraDiv(1);
        TestDBUtility.insertWithDel(l_eraParams);
    }

    protected void tearDown() throws Exception
    {
        this.l_serviceImpl = null;
        super.tearDown();
    }
    
    /**
     * �p�����[�^Null�o���Ȃ��B�e�oSYSTEM_ERROR_80017�ُ�M��
     *
     */
    public void testValidateAccSwitchElecDeliApplyInfo_C0001()
    {
        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_beforeChangedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
                        
            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_beforeChangedInfo,null);
            
            fail();
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(""+l_ex);
            assertEquals(WEB3BusinessLayerException.class,l_ex.getClass());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02688,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateAccSwitchElecDeliApplyInfo_Case1()
    {
        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_Case1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
                        
            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(null,l_changedInfo);
            
            fail();
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(""+l_ex);
            assertEquals(WEB3BusinessLayerException.class,l_ex.getClass());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02688,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateAccSwitchElecDeliApplyInfo_Case2()
    {
        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_Case2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_beforeChangedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            l_beforeChangedInfo.mobileAccoutDiv = "0";
            l_beforeChangedInfo.tradingReportDiv = "0";
            l_beforeChangedInfo.positionReportDiv = "0";
            l_beforeChangedInfo.positionReportCycleDiv = "1";
            l_beforeChangedInfo.certificateDepositDiv = "0";
            l_beforeChangedInfo.accountStatementDiv = "0";
            l_beforeChangedInfo.taxType = "0";
            l_beforeChangedInfo.taxTypeNext = "0";
            l_beforeChangedInfo.marginTaxType = "0";
            l_beforeChangedInfo.marginTaxTypeNext = "0";
            l_beforeChangedInfo.capitalGainTaxAccOpenDiv = "0";
            
            l_changedInfo.mobileAccoutDiv = "0";
            l_changedInfo.tradingReportDiv = "0";
            l_changedInfo.positionReportDiv = "0";
            l_changedInfo.positionReportCycleDiv = "1";
            l_changedInfo.certificateDepositDiv = "0";
            l_changedInfo.accountStatementDiv = "0";
            l_changedInfo.taxType = "0";
            l_changedInfo.taxTypeNext = "0";
            l_changedInfo.marginTaxType = "0";
            l_changedInfo.marginTaxTypeNext = "0";
            l_changedInfo.capitalGainTaxAccOpenDiv = "0";
            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_beforeChangedInfo,l_changedInfo);

            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(""+l_ex);
            assertEquals(WEB3BusinessLayerException.class,l_ex.getClass());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02680,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateAccSwitchElecDeliApplyInfo_Case3()
    {
        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_Case3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_beforeChangedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            l_beforeChangedInfo.mobileAccoutDiv = "0";
            l_beforeChangedInfo.tradingReportDiv = "0";
            l_beforeChangedInfo.positionReportDiv = "0";
            l_beforeChangedInfo.positionReportCycleDiv = "1";
            l_beforeChangedInfo.certificateDepositDiv = "0";
            l_beforeChangedInfo.accountStatementDiv = "0";
            l_beforeChangedInfo.taxType = "0";
            l_beforeChangedInfo.taxTypeNext = "0";
            l_beforeChangedInfo.marginTaxType = "0";
            l_beforeChangedInfo.marginTaxTypeNext = "0";
            l_beforeChangedInfo.capitalGainTaxAccOpenDiv = "0";
            
            l_changedInfo.mobileAccoutDiv = "1";
            l_changedInfo.tradingReportDiv = null;
            l_changedInfo.positionReportDiv = "0";
            l_changedInfo.positionReportCycleDiv = "1";
            l_changedInfo.certificateDepositDiv = "0";
            l_changedInfo.accountStatementDiv = "0";
            l_changedInfo.taxType = "1";
            l_changedInfo.taxTypeNext = "0";
            l_changedInfo.marginTaxType = "0";
            l_changedInfo.marginTaxTypeNext = "0";
            l_changedInfo.capitalGainTaxAccOpenDiv = "0";
            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_beforeChangedInfo,l_changedInfo);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(""+l_ex);
            assertEquals(WEB3BusinessLayerException.class,l_ex.getClass());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02872,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateAccSwitchElecDeliApplyInfo_Case4()
    {
        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_Case4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_beforeChangedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            l_beforeChangedInfo.mobileAccoutDiv = "0";
            l_beforeChangedInfo.tradingReportDiv = "0";
            l_beforeChangedInfo.positionReportDiv = "0";
            l_beforeChangedInfo.positionReportCycleDiv = "1";
            l_beforeChangedInfo.certificateDepositDiv = "0";
            l_beforeChangedInfo.accountStatementDiv = "0";
            l_beforeChangedInfo.taxType = "0";
            l_beforeChangedInfo.taxTypeNext = "0";
            l_beforeChangedInfo.marginTaxType = "0";
            l_beforeChangedInfo.marginTaxTypeNext = "0";
            l_beforeChangedInfo.capitalGainTaxAccOpenDiv = "0";
            
            l_changedInfo.mobileAccoutDiv = "1";
            l_changedInfo.tradingReportDiv = "0";
            l_changedInfo.positionReportDiv = "0";
            l_changedInfo.positionReportCycleDiv = "1";
            l_changedInfo.certificateDepositDiv = "0";
            l_changedInfo.accountStatementDiv = "0";
            l_changedInfo.taxType = null;
            l_changedInfo.taxTypeNext = "0";
            l_changedInfo.marginTaxType = "0";
            l_changedInfo.marginTaxTypeNext = "0";
            l_changedInfo.capitalGainTaxAccOpenDiv = "0";
            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_beforeChangedInfo,l_changedInfo);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(""+l_ex);
            assertEquals(WEB3BusinessLayerException.class,l_ex.getClass());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02873,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateAccSwitchElecDeliApplyInfo_Case5()
    {
        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_Case5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_beforeChangedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            l_beforeChangedInfo.mobileAccoutDiv = "0";
            l_beforeChangedInfo.tradingReportDiv = "0";
            l_beforeChangedInfo.positionReportDiv = "0";
            l_beforeChangedInfo.positionReportCycleDiv = "1";
            l_beforeChangedInfo.certificateDepositDiv = "0";
            l_beforeChangedInfo.accountStatementDiv = "0";
            l_beforeChangedInfo.taxType = "0";
            l_beforeChangedInfo.taxTypeNext = "0";
            l_beforeChangedInfo.marginTaxType = "0";
            l_beforeChangedInfo.marginTaxTypeNext = "0";
            l_beforeChangedInfo.capitalGainTaxAccOpenDiv = "0";
            
            l_changedInfo.mobileAccoutDiv = "1";
            l_changedInfo.tradingReportDiv = "0";
            l_changedInfo.positionReportDiv = "0";
            l_changedInfo.positionReportCycleDiv = "1";
            l_changedInfo.certificateDepositDiv = "0";
            l_changedInfo.accountStatementDiv = "0";
            l_changedInfo.taxType = "0";
            l_changedInfo.taxTypeNext = "0";
            l_changedInfo.marginTaxType = null;
            l_changedInfo.marginTaxTypeNext = "0";
            l_changedInfo.capitalGainTaxAccOpenDiv = "0";
            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_beforeChangedInfo,l_changedInfo);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(""+l_ex);
            assertEquals(WEB3BusinessLayerException.class,l_ex.getClass());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02874,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateAccSwitchElecDeliApplyInfo_Case6()
    {
        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_Case6()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_beforeChangedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            l_beforeChangedInfo.mobileAccoutDiv = "0";
            l_beforeChangedInfo.tradingReportDiv = "0";
            l_beforeChangedInfo.positionReportDiv = "0";
            l_beforeChangedInfo.positionReportCycleDiv = "1";
            l_beforeChangedInfo.certificateDepositDiv = "0";
            l_beforeChangedInfo.accountStatementDiv = "0";
            l_beforeChangedInfo.taxType = "0";
            l_beforeChangedInfo.taxTypeNext = "0";
            l_beforeChangedInfo.marginTaxType = "0";
            l_beforeChangedInfo.marginTaxTypeNext = "0";
            l_beforeChangedInfo.capitalGainTaxAccOpenDiv = "0";
            
            l_changedInfo.mobileAccoutDiv = "1";
            l_changedInfo.tradingReportDiv = "0";
            l_changedInfo.positionReportDiv = "0";
            l_changedInfo.positionReportCycleDiv = "1";
            l_changedInfo.certificateDepositDiv = "0";
            l_changedInfo.accountStatementDiv = "0";
            l_changedInfo.taxType = "2";
            l_changedInfo.taxTypeNext = "0";
            l_changedInfo.marginTaxType = "0";
            l_changedInfo.marginTaxTypeNext = "0";
            l_changedInfo.capitalGainTaxAccOpenDiv = "0";
            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_beforeChangedInfo,l_changedInfo);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(""+l_ex);
            assertEquals(WEB3BusinessLayerException.class,l_ex.getClass());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02875,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateAccSwitchElecDeliApplyInfo_Case7()
    {
        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_Case7()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_beforeChangedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            l_beforeChangedInfo.mobileAccoutDiv = "0";
            l_beforeChangedInfo.tradingReportDiv = "0";
            l_beforeChangedInfo.positionReportDiv = "0";
            l_beforeChangedInfo.positionReportCycleDiv = "1";
            l_beforeChangedInfo.certificateDepositDiv = "0";
            l_beforeChangedInfo.accountStatementDiv = "0";
            l_beforeChangedInfo.taxType = "0";
            l_beforeChangedInfo.taxTypeNext = "0";
            l_beforeChangedInfo.marginTaxType = "0";
            l_beforeChangedInfo.marginTaxTypeNext = "0";
            l_beforeChangedInfo.capitalGainTaxAccOpenDiv = "0";
            
            l_changedInfo.mobileAccoutDiv = "1";
            l_changedInfo.tradingReportDiv = "0";
            l_changedInfo.positionReportDiv = "0";
            l_changedInfo.positionReportCycleDiv = "1";
            l_changedInfo.certificateDepositDiv = "0";
            l_changedInfo.accountStatementDiv = "0";
            l_changedInfo.taxType = "0";
            l_changedInfo.taxTypeNext = "2";
            l_changedInfo.marginTaxType = "0";
            l_changedInfo.marginTaxTypeNext = "0";
            l_changedInfo.capitalGainTaxAccOpenDiv = "0";
            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_beforeChangedInfo,l_changedInfo);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(""+l_ex);
            assertEquals(WEB3BusinessLayerException.class,l_ex.getClass());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02875,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateAccSwitchElecDeliApplyInfo_Case8()
    {
        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_Case8()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_beforeChangedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            l_beforeChangedInfo.mobileAccoutDiv = "0";
            l_beforeChangedInfo.tradingReportDiv = "0";
            l_beforeChangedInfo.positionReportDiv = "0";
            l_beforeChangedInfo.positionReportCycleDiv = "1";
            l_beforeChangedInfo.certificateDepositDiv = "0";
            l_beforeChangedInfo.accountStatementDiv = "0";
            l_beforeChangedInfo.taxType = "0";
            l_beforeChangedInfo.taxTypeNext = "0";
            l_beforeChangedInfo.marginTaxType = "0";
            l_beforeChangedInfo.marginTaxTypeNext = "0";
            l_beforeChangedInfo.capitalGainTaxAccOpenDiv = "0";
            
            l_changedInfo.mobileAccoutDiv = "1";
            l_changedInfo.tradingReportDiv = "0";
            l_changedInfo.positionReportDiv = "0";
            l_changedInfo.positionReportCycleDiv = "1";
            l_changedInfo.certificateDepositDiv = "0";
            l_changedInfo.accountStatementDiv = "0";
            l_changedInfo.taxType = "1";
            l_changedInfo.taxTypeNext = "0";
            l_changedInfo.marginTaxType = "1";
            l_changedInfo.marginTaxTypeNext = "0";
            l_changedInfo.capitalGainTaxAccOpenDiv = "1";
            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_beforeChangedInfo,l_changedInfo);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(""+l_ex);
            assertEquals(WEB3BusinessLayerException.class,l_ex.getClass());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02876,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateAccSwitchElecDeliApplyInfo_Case9()
    {
        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_Case9()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_beforeChangedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            l_beforeChangedInfo.mobileAccoutDiv = "0";
            l_beforeChangedInfo.tradingReportDiv = "0";
            l_beforeChangedInfo.positionReportDiv = "0";
            l_beforeChangedInfo.positionReportCycleDiv = "1";
            l_beforeChangedInfo.certificateDepositDiv = "0";
            l_beforeChangedInfo.accountStatementDiv = "0";
            l_beforeChangedInfo.taxType = "0";
            l_beforeChangedInfo.taxTypeNext = "0";
            l_beforeChangedInfo.marginTaxType = "0";
            l_beforeChangedInfo.marginTaxTypeNext = "0";
            l_beforeChangedInfo.capitalGainTaxAccOpenDiv = "0";
            
            l_changedInfo.mobileAccoutDiv = "1";
            l_changedInfo.tradingReportDiv = "1";
            l_changedInfo.positionReportDiv = "0";
            l_changedInfo.positionReportCycleDiv = "1";
            l_changedInfo.certificateDepositDiv = "0";
            l_changedInfo.accountStatementDiv = "0";
            l_changedInfo.taxType = "1";
            l_changedInfo.taxTypeNext = "0";
            l_changedInfo.marginTaxType = "1";
            l_changedInfo.marginTaxTypeNext = "0";
            l_changedInfo.capitalGainTaxAccOpenDiv = "0";
            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_beforeChangedInfo,l_changedInfo);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(""+l_ex);
            assertEquals(WEB3BusinessLayerException.class,l_ex.getClass());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02877,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateAccSwitchElecDeliApplyInfo_Case10()
    {
        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_Case10()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_beforeChangedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            l_beforeChangedInfo.mobileAccoutDiv = "0";
            l_beforeChangedInfo.tradingReportDiv = "0";
            l_beforeChangedInfo.positionReportDiv = "0";
            l_beforeChangedInfo.positionReportCycleDiv = "1";
            l_beforeChangedInfo.certificateDepositDiv = "0";
            l_beforeChangedInfo.accountStatementDiv = "0";
            l_beforeChangedInfo.taxType = "0";
            l_beforeChangedInfo.taxTypeNext = "0";
            l_beforeChangedInfo.marginTaxType = "0";
            l_beforeChangedInfo.marginTaxTypeNext = "0";
            l_beforeChangedInfo.capitalGainTaxAccOpenDiv = "0";
            
            l_changedInfo.mobileAccoutDiv = "1";
            l_changedInfo.tradingReportDiv = "0";
            l_changedInfo.positionReportDiv = "0";
            l_changedInfo.positionReportCycleDiv = "1";
            l_changedInfo.certificateDepositDiv = "0";
            l_changedInfo.accountStatementDiv = "0";
            l_changedInfo.taxType = "1";
            l_changedInfo.taxTypeNext = "0";
            l_changedInfo.marginTaxType = "1";
            l_changedInfo.marginTaxTypeNext = "0";
            l_changedInfo.capitalGainTaxAccOpenDiv = "0";
            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_beforeChangedInfo,l_changedInfo);
        }
        catch(Exception l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �ύX�O����(����)�ύX����́��`�F�b�N�Ώۍ��ځ����r���A
     * �ꍀ�ڂ����ق��Ȃ��ꍇ�A��O���X���[����B
     * �G���[���b�Z�[�W�yBUSINESS_ERROR_02680�i�ύX���ڂ�����܂���B�j�z��\���B
     *
     */
//    public void testValidateAccSwitchElecDeliApplyInfo_C0002()
//    {
//        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_C0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
//            // ���o�C����p�����J�݋敪
//            l_mainAccountParams.setOnlyMobileOpenDiv("0");
//            l_changedInfo.mobileAccoutDiv = "0";
//            
//            // ����񍐏���t�敪
//            l_mainAccountParams.setTradingReportDiv("1");
//            l_changedInfo.tradingReportDiv = "1";
//            
//            // ����c���񍐏���t�敪
//            l_mainAccountParams.setPositionReportDiv("0");
//            l_changedInfo.positionReportDiv = "0";
//            
//            // ����c���񍐏��쐬�����敪
//            l_mainAccountParams.setPositionReportCycleDiv("3");
//            l_changedInfo.positionReportCycleDiv = "3";
//            
//            // ����c���񍐏��a��؍쐬�t���O
//            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
//            l_changedInfo.certificateDepositDiv = "1";
//            
//            // ����c���񍐏��v�Z���쐬�t���O
//            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);
//            l_changedInfo.accountStatementDiv = "0";
//            
//            // �ŋ敪
//            l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
//            l_changedInfo.taxType = "2";
//            
//            // �ŋ敪�i���N�j
//            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
//            l_changedInfo.taxTypeNext = "3";
//            
//            // �M�p����ŋ敪
//            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxType = "0";
//            
//            // �M�p����ŋ敪�i���N�j
//            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxTypeNext = "0";
//            
//            // ����Ǘ������J�݋敪
//            l_mainAccountParams.setSpMngAccOpenDiv("1");
//            l_changedInfo.capitalGainTaxAccOpenDiv = "1";
//            
//            
//            
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            WEB3GentradeMainAccount  l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
//            
//           
//                        
//            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_changedInfo,l_mainAccount);
//            
//            fail();
//                        
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02680,l_ex.getErrorInfo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    
//    /**
//     * �Y������(����)�ύX����̍��ڂ��A[�S�Ė�����]�̏ꍇ�A��O���X���[����
//     * �e�o�ُ�M���FBUSINESS_ERROR_02688
//     * 
//     */
//    public void testValidateAccSwitchElecDeliApplyInfo_C0003()
//    {
//        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_C0003()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
//            // ���o�C����p�����J�݋敪
//            l_mainAccountParams.setOnlyMobileOpenDiv("0");
//            l_changedInfo.mobileAccoutDiv = null;
//            
//            // ����񍐏���t�敪
//            l_mainAccountParams.setTradingReportDiv("1");
//            l_changedInfo.tradingReportDiv = null;
//            
//            // ����c���񍐏���t�敪
//            l_mainAccountParams.setPositionReportDiv("0");
//            l_changedInfo.positionReportDiv = null;
//            
//            // ����c���񍐏��쐬�����敪
//            l_mainAccountParams.setPositionReportCycleDiv("3");
//            l_changedInfo.positionReportCycleDiv = null;
//            
//            // ����c���񍐏��a��؍쐬�t���O
//            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
//            l_changedInfo.certificateDepositDiv = null;
//            
//            // ����c���񍐏��v�Z���쐬�t���O
//            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);
//            l_changedInfo.accountStatementDiv = null;
//            
//            // �ŋ敪
//            l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
//            l_changedInfo.taxType = null;
//            
//            // �ŋ敪�i���N�j
//            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
//            l_changedInfo.taxTypeNext = null;
//            
//            // �M�p����ŋ敪
//            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxType = null;
//            
//            // �M�p����ŋ敪�i���N�j
//            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxTypeNext = null;
//            
//            // ����Ǘ������J�݋敪
//            l_mainAccountParams.setSpMngAccOpenDiv("1");
//            l_changedInfo.capitalGainTaxAccOpenDiv = null;
//            
//            
//            
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            WEB3GentradeMainAccount  l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
//            
//           
//                        
//            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_changedInfo,l_mainAccount);
//            
//            fail();
//                        
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02688,l_ex.getErrorInfo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    
//    /**
//     * �u���E��c�ύX���ڗL���v�`�F�b�N�A
//     * [�S�Ė����́A���́A�S�ē���]�ȊO�̏ꍇ�A��O���X���[����B
//     * �e�o�ُ�M���FBUSINESS_ERROR_02872
//     * 
//     */
//    public void testValidateAccSwitchElecDeliApplyInfo_C0004()
//    {
//        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_C0004()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
//            // ���o�C����p�����J�݋敪
//            l_mainAccountParams.setOnlyMobileOpenDiv("0");
//            l_changedInfo.mobileAccoutDiv = null;
//            
//            // ����񍐏���t�敪
//            l_mainAccountParams.setTradingReportDiv("1");
//            l_changedInfo.tradingReportDiv = "1";
//            
//            // ����c���񍐏���t�敪
//            l_mainAccountParams.setPositionReportDiv("0");
//            l_changedInfo.positionReportDiv = "0";
//            
//            // ����c���񍐏��쐬�����敪
//            l_mainAccountParams.setPositionReportCycleDiv("3");
//            l_changedInfo.positionReportCycleDiv = "2";
//            
//            // ����c���񍐏��a��؍쐬�t���O
//            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
//            l_changedInfo.certificateDepositDiv = "1";
//            
//            // ����c���񍐏��v�Z���쐬�t���O
//            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);
//            l_changedInfo.accountStatementDiv = null;
//            
//            // �ŋ敪
//            l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
//            l_changedInfo.taxType = null;
//            
//            // �ŋ敪�i���N�j
//            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
//            l_changedInfo.taxTypeNext = null;
//            
//            // �M�p����ŋ敪
//            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxType = null;
//            
//            // �M�p����ŋ敪�i���N�j
//            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxTypeNext = null;
//            
//            // ����Ǘ������J�݋敪
//            l_mainAccountParams.setSpMngAccOpenDiv("1");
//            l_changedInfo.capitalGainTaxAccOpenDiv = null;
//            
//            
//            
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            WEB3GentradeMainAccount  l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
//            
//           
//                        
//            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_changedInfo,l_mainAccount);
//            
//            fail();
//                        
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02872,l_ex.getErrorInfo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    
//    /**
//     * �ŋ敪�ύX���ڗL���`�F�b�N�A
//     * [�S�Ė����́A���́A�S�ē���]�ȊO�̏ꍇ�A��O���X���[����B
//     * �e�o�ُ�M���FBUSINESS_ERROR_02873
//     * 
//     */
//    public void testValidateAccSwitchElecDeliApplyInfo_C0005()
//    {
//        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_C0005()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
//            // ���o�C����p�����J�݋敪
//            l_mainAccountParams.setOnlyMobileOpenDiv("0");
//            l_changedInfo.mobileAccoutDiv = null;
//            
//            // ����񍐏���t�敪
//            l_mainAccountParams.setTradingReportDiv("1");
//            l_changedInfo.tradingReportDiv = "0";
//            
//            // ����c���񍐏���t�敪
//            l_mainAccountParams.setPositionReportDiv("0");
//            l_changedInfo.positionReportDiv = "1";
//            
//            // ����c���񍐏��쐬�����敪
//            l_mainAccountParams.setPositionReportCycleDiv("3");
//            l_changedInfo.positionReportCycleDiv = "3";
//            
//            // ����c���񍐏��a��؍쐬�t���O
//            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
//            l_changedInfo.certificateDepositDiv = "1";
//            
//            // ����c���񍐏��v�Z���쐬�t���O
//            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);
//            l_changedInfo.accountStatementDiv = "0";
//            
//            // �ŋ敪
//            l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
//            l_changedInfo.taxType = "1";
//            
//            // �ŋ敪�i���N�j
//            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
//            l_changedInfo.taxTypeNext = null;
//            
//            // �M�p����ŋ敪
//            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxType = null;
//            
//            // �M�p����ŋ敪�i���N�j
//            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxTypeNext = null;
//            
//            // ����Ǘ������J�݋敪
//            l_mainAccountParams.setSpMngAccOpenDiv("1");
//            l_changedInfo.capitalGainTaxAccOpenDiv = null;
//            
//            
//            
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            WEB3GentradeMainAccount  l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
//            
//           
//                        
//            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_changedInfo,l_mainAccount);
//            
//            fail();
//                        
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02873,l_ex.getErrorInfo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    
//    /**
//     * �u�M�p����ŋ敪�ύX���ڗL���v�`�F�b�N�A
//     * [�S�Ė����́A���́A�S�ē���]�ȊO�̏ꍇ�A��O���X���[����B
//     * �e�o�ُ�M���FBUSINESS_ERROR_02874
//     * 
//     */
//    public void testValidateAccSwitchElecDeliApplyInfo_C0006()
//    {
//        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_C0006()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
//            // ���o�C����p�����J�݋敪
//            l_mainAccountParams.setOnlyMobileOpenDiv("0");
//            l_changedInfo.mobileAccoutDiv = null;
//            
//            // ����񍐏���t�敪
//            l_mainAccountParams.setTradingReportDiv("1");
//            l_changedInfo.tradingReportDiv = null;
//            
//            // ����c���񍐏���t�敪
//            l_mainAccountParams.setPositionReportDiv("0");
//            l_changedInfo.positionReportDiv =  null;
//            
//            // ����c���񍐏��쐬�����敪
//            l_mainAccountParams.setPositionReportCycleDiv("3");
//            l_changedInfo.positionReportCycleDiv =  null;
//            
//            // ����c���񍐏��a��؍쐬�t���O
//            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
//            l_changedInfo.certificateDepositDiv =  null;
//            
//            // ����c���񍐏��v�Z���쐬�t���O
//            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);
//            l_changedInfo.accountStatementDiv =  null;
//            
//            // �ŋ敪
//            l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
//            l_changedInfo.taxType =  null;
//            
//            // �ŋ敪�i���N�j
//            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
//            l_changedInfo.taxTypeNext = null;
//            
//            // �M�p����ŋ敪
//            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxType = "1";
//            
//            // �M�p����ŋ敪�i���N�j
//            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxTypeNext = null;
//            
//            // ����Ǘ������J�݋敪
//            l_mainAccountParams.setSpMngAccOpenDiv("1");
//            l_changedInfo.capitalGainTaxAccOpenDiv = null;
//            
//            
//            
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            WEB3GentradeMainAccount  l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
//            
//           
//                        
//            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_changedInfo,l_mainAccount);
//            
//            fail();
//                        
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02874,l_ex.getErrorInfo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    
//    /**
//     * �u������������v�`�F�b�N�A
//     * �ŋ敪 == 2�F���� �̏ꍇ�A
//     * �M�p����ŋ敪 != (1�F��ʁA���́A2�F����A���́Anull)
//     * �e�o�ُ�M���FBUSINESS_ERROR_02875
//     * 
//     */
//    public void testValidateAccSwitchElecDeliApplyInfo_C0007()
//    {
//        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_C0007()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
//            // ���o�C����p�����J�݋敪
//            l_mainAccountParams.setOnlyMobileOpenDiv("0");
//            l_changedInfo.mobileAccoutDiv = "0";
//            
//            // ����񍐏���t�敪
//            l_mainAccountParams.setTradingReportDiv("1");
//            l_changedInfo.tradingReportDiv = "1";
//            
//            // ����c���񍐏���t�敪
//            l_mainAccountParams.setPositionReportDiv("0");
//            l_changedInfo.positionReportDiv =  "0";
//            
//            // ����c���񍐏��쐬�����敪
//            l_mainAccountParams.setPositionReportCycleDiv("3");
//            l_changedInfo.positionReportCycleDiv =  "3";
//            
//            // ����c���񍐏��a��؍쐬�t���O
//            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
//            l_changedInfo.certificateDepositDiv =  "1";
//            
//            // ����c���񍐏��v�Z���쐬�t���O
//            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);
//            l_changedInfo.accountStatementDiv =  "0";
//            
//            // �ŋ敪
//            l_mainAccountParams.setTaxType(TaxTypeEnum.NORMAL);
//            l_changedInfo.taxType =  "2";
//            
//            // �ŋ敪�i���N�j
//            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
//            l_changedInfo.taxTypeNext = "3";
//            
//            // �M�p����ŋ敪
//            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.NORMAL);
//            l_changedInfo.marginTaxType = "3";
//            
//            // �M�p����ŋ敪�i���N�j
//            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxTypeNext = "1";
//            
//            // ����Ǘ������J�݋敪
//            l_mainAccountParams.setSpMngAccOpenDiv("0");
//            l_changedInfo.capitalGainTaxAccOpenDiv = "1";
//            
//            
//            
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            WEB3GentradeMainAccount  l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
//            
//           
//                        
//            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_changedInfo,l_mainAccount);
//            
//            fail();
//                        
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02875,l_ex.getErrorInfo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    
//    /**
//     * �u������������v�`�F�b�N�A
//     * �ŋ敪 == 3�F������������򒥎� �̏ꍇ�A
//     * �M�p����ŋ敪 != (1�F��ʁA���́A3�F������������򒥎��A���́Anull)
//     * �e�o�ُ�M���FBUSINESS_ERROR_02875
//     * 
//     */
//    public void testValidateAccSwitchElecDeliApplyInfo_C0008()
//    {
//        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_C0008()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
//            // ���o�C����p�����J�݋敪
//            l_mainAccountParams.setOnlyMobileOpenDiv("0");
//            l_changedInfo.mobileAccoutDiv = "0";
//            
//            // ����񍐏���t�敪
//            l_mainAccountParams.setTradingReportDiv("1");
//            l_changedInfo.tradingReportDiv = "1";
//            
//            // ����c���񍐏���t�敪
//            l_mainAccountParams.setPositionReportDiv("0");
//            l_changedInfo.positionReportDiv =  "0";
//            
//            // ����c���񍐏��쐬�����敪
//            l_mainAccountParams.setPositionReportCycleDiv("3");
//            l_changedInfo.positionReportCycleDiv =  "3";
//            
//            // ����c���񍐏��a��؍쐬�t���O
//            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
//            l_changedInfo.certificateDepositDiv =  "1";
//            
//            // ����c���񍐏��v�Z���쐬�t���O
//            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);
//            l_changedInfo.accountStatementDiv =  "0";
//            
//            // �ŋ敪
//            l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
//            l_changedInfo.taxType =  "3";
//            
//            // �ŋ敪�i���N�j
//            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
//            l_changedInfo.taxTypeNext = "3";
//            
//            // �M�p����ŋ敪
//            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxType = "0";
//            
//            // �M�p����ŋ敪�i���N�j
//            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxTypeNext = "1";
//            
//            // ����Ǘ������J�݋敪
//            l_mainAccountParams.setSpMngAccOpenDiv("1");
//            l_changedInfo.capitalGainTaxAccOpenDiv = null;
//            
//            
//            
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            WEB3GentradeMainAccount  l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
//            
//           
//                        
//            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_changedInfo,l_mainAccount);
//            
//            fail();
//                        
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02875,l_ex.getErrorInfo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    
//    /**
//     * �u������������v�`�F�b�N�A
//     * �ŋ敪�i���N�j == 2�F���� �̏ꍇ�A
//     * �M�p����ŋ敪�i���N�j != (1�F��ʁA���́A2�F����A���́Anull)
//     * �e�o�ُ�M���FBUSINESS_ERROR_02875
//     * 
//     */
//    public void testValidateAccSwitchElecDeliApplyInfo_C0009()
//    {
//        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_C0009()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
//            // ���o�C����p�����J�݋敪
//            l_mainAccountParams.setOnlyMobileOpenDiv("0");
//            l_changedInfo.mobileAccoutDiv = "0";
//            
//            // ����񍐏���t�敪
//            l_mainAccountParams.setTradingReportDiv("1");
//            l_changedInfo.tradingReportDiv = "1";
//            
//            // ����c���񍐏���t�敪
//            l_mainAccountParams.setPositionReportDiv("0");
//            l_changedInfo.positionReportDiv =  "0";
//            
//            // ����c���񍐏��쐬�����敪
//            l_mainAccountParams.setPositionReportCycleDiv("3");
//            l_changedInfo.positionReportCycleDiv =  "3";
//            
//            // ����c���񍐏��a��؍쐬�t���O
//            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
//            l_changedInfo.certificateDepositDiv =  "1";
//            
//            // ����c���񍐏��v�Z���쐬�t���O
//            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);
//            l_changedInfo.accountStatementDiv =  "0";
//            
//            // �ŋ敪
//            l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
//            l_changedInfo.taxType =  "1";
//            
//            // �ŋ敪�i���N�j
//            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.taxTypeNext = "2";
//            
//            // �M�p����ŋ敪
//            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxType = "0";
//            
//            // �M�p����ŋ敪�i���N�j
//            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxTypeNext = "0";
//            
//            // ����Ǘ������J�݋敪
//            l_mainAccountParams.setSpMngAccOpenDiv("1");
//            l_changedInfo.capitalGainTaxAccOpenDiv = "1";
//            
//            
//            
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            WEB3GentradeMainAccount  l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
//            
//           
//                        
//            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_changedInfo,l_mainAccount);
//            
//            fail();
//                        
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02875,l_ex.getErrorInfo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /**
//     * �u������������v�`�F�b�N�A
//     * �ŋ敪�i���N�j == 3�F������������򒥎� �̏ꍇ�A
//     * �M�p����ŋ敪�i���N�j != (1�F��ʁA���́A3�F������������򒥎��A���́Anull)
//     * �e�o�ُ�M���FBUSINESS_ERROR_02875
//     * 
//     */
//    public void testValidateAccSwitchElecDeliApplyInfo_C0010()
//    {
//        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_C0010()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
//            // ���o�C����p�����J�݋敪
//            l_mainAccountParams.setOnlyMobileOpenDiv("0");
//            l_changedInfo.mobileAccoutDiv = "0";
//            
//            // ����񍐏���t�敪
//            l_mainAccountParams.setTradingReportDiv("1");
//            l_changedInfo.tradingReportDiv = "1";
//            
//            // ����c���񍐏���t�敪
//            l_mainAccountParams.setPositionReportDiv("0");
//            l_changedInfo.positionReportDiv =  "0";
//            
//            // ����c���񍐏��쐬�����敪
//            l_mainAccountParams.setPositionReportCycleDiv("3");
//            l_changedInfo.positionReportCycleDiv =  "3";
//            
//            // ����c���񍐏��a��؍쐬�t���O
//            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
//            l_changedInfo.certificateDepositDiv =  "1";
//            
//            // ����c���񍐏��v�Z���쐬�t���O
//            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);
//            l_changedInfo.accountStatementDiv =  "0";
//            
//            // �ŋ敪
//            l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
//            l_changedInfo.taxType =  "1";
//            
//            // �ŋ敪�i���N�j
//            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL);
//            l_changedInfo.taxTypeNext = "3";
//            
//            // �M�p����ŋ敪
//            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxType = "0";
//            
//            // �M�p����ŋ敪�i���N�j
//            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxTypeNext = "2";
//            
//            // ����Ǘ������J�݋敪
//            l_mainAccountParams.setSpMngAccOpenDiv("1");
//            l_changedInfo.capitalGainTaxAccOpenDiv = null;
//            
//            
//            
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            WEB3GentradeMainAccount  l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
//            
//           
//                        
//            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_changedInfo,l_mainAccount);
//            
//            fail();
//                        
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02875,l_ex.getErrorInfo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /**
//     * ����Ǘ����������v�`�F�b�N�A
//     * �ŋ敪 == 1�F��ʁA���A�M�p����ŋ敪 == 1�F��� �̏ꍇ
//     * ����Ǘ������J�݋敪 == 1�F�J�݁A
//     * �e�o�ُ�M���FBUSINESS_ERROR_02876
//     * 
//     */
//    public void testValidateAccSwitchElecDeliApplyInfo_C0011()
//    {
//        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_C0011()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
//            // ���o�C����p�����J�݋敪
//            l_mainAccountParams.setOnlyMobileOpenDiv("0");
//            l_changedInfo.mobileAccoutDiv = "0";
//            
//            // ����񍐏���t�敪
//            l_mainAccountParams.setTradingReportDiv("1");
//            l_changedInfo.tradingReportDiv = "1";
//            
//            // ����c���񍐏���t�敪
//            l_mainAccountParams.setPositionReportDiv("0");
//            l_changedInfo.positionReportDiv =  "0";
//            
//            // ����c���񍐏��쐬�����敪
//            l_mainAccountParams.setPositionReportCycleDiv("3");
//            l_changedInfo.positionReportCycleDiv =  "3";
//            
//            // ����c���񍐏��a��؍쐬�t���O
//            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
//            l_changedInfo.certificateDepositDiv =  "1";
//            
//            // ����c���񍐏��v�Z���쐬�t���O
//            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);
//            l_changedInfo.accountStatementDiv =  "0";
//            
//            // �ŋ敪
//            l_mainAccountParams.setTaxType(TaxTypeEnum.NORMAL);
//            l_changedInfo.taxType =  "1";
//            
//            // �ŋ敪�i���N�j
//            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL);
//            l_changedInfo.taxTypeNext = "2";
//            
//            // �M�p����ŋ敪
//            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.NORMAL);
//            l_changedInfo.marginTaxType = "1";
//            
//            // �M�p����ŋ敪�i���N�j
//            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxTypeNext = "0";
//            
//            // ����Ǘ������J�݋敪
//            l_mainAccountParams.setSpMngAccOpenDiv("0");
//            l_changedInfo.capitalGainTaxAccOpenDiv = "1";
//            
//            
//            
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            WEB3GentradeMainAccount  l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
//            
//           
//                        
//            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_changedInfo,l_mainAccount);
//            
//            fail();
//                        
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02876,l_ex.getErrorInfo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /**
//     * ����Ǘ����������v�`�F�b�N�A
//     * ���o�C����p�����J�݋敪 == 1�F�J�� �̏ꍇ
//     * ����񍐏���t�敪 == 1�F�d�q�z�z
//     * �e�o�ُ�M���FBUSINESS_ERROR_02877
//     * 
//     */
//    public void testValidateAccSwitchElecDeliApplyInfo_C0012()
//    {
//        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_C0012()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
//            // ���o�C����p�����J�݋敪
//            l_mainAccountParams.setOnlyMobileOpenDiv("0");
//            l_changedInfo.mobileAccoutDiv = "1";
//            
//            // ����񍐏���t�敪
//            l_mainAccountParams.setTradingReportDiv("1");
//            l_changedInfo.tradingReportDiv = "1";
//            
//            // ����c���񍐏���t�敪
//            l_mainAccountParams.setPositionReportDiv("0");
//            l_changedInfo.positionReportDiv =  "9";
//            
//            // ����c���񍐏��쐬�����敪
//            l_mainAccountParams.setPositionReportCycleDiv("3");
//            l_changedInfo.positionReportCycleDiv =  "3";
//            
//            // ����c���񍐏��a��؍쐬�t���O
//            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
//            l_changedInfo.certificateDepositDiv =  "1";
//            
//            // ����c���񍐏��v�Z���쐬�t���O
//            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);
//            l_changedInfo.accountStatementDiv =  "0";
//            
//            // �ŋ敪
//            l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
//            l_changedInfo.taxType =  "1";
//            
//            // �ŋ敪�i���N�j
//            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL);
//            l_changedInfo.taxTypeNext = "2";
//            
//            // �M�p����ŋ敪
//            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxType = "1";
//            
//            // �M�p����ŋ敪�i���N�j
//            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxTypeNext = "2";
//            
//            // ����Ǘ������J�݋敪
//            l_mainAccountParams.setSpMngAccOpenDiv("1");
//            l_changedInfo.capitalGainTaxAccOpenDiv = "2";
//            
//            
//            
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            WEB3GentradeMainAccount  l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
//            
//           
//                        
//            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_changedInfo,l_mainAccount);
//            
//            fail();
//                        
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02877,l_ex.getErrorInfo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /**
//     * ����Ǘ����������v�`�F�b�N�A
//     * ���o�C����p�����J�݋敪 == 1�F�J�� �̏ꍇ
//     * ����c���񍐏���t�敪 == 9�F�d�q�z�z
//     * �e�o�ُ�M���FBUSINESS_ERROR_02877
//     * 
//     */
//    public void testValidateAccSwitchElecDeliApplyInfo_C0013()
//    {
//        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_C0013()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
//            // ���o�C����p�����J�݋敪
//            l_mainAccountParams.setOnlyMobileOpenDiv("0");
//            l_changedInfo.mobileAccoutDiv = "1";
//            
//            // ����񍐏���t�敪
//            l_mainAccountParams.setTradingReportDiv("1");
//            l_changedInfo.tradingReportDiv = "0";
//            
//            // ����c���񍐏���t�敪
//            l_mainAccountParams.setPositionReportDiv("0");
//            l_changedInfo.positionReportDiv =  "9";
//            
//            // ����c���񍐏��쐬�����敪
//            l_mainAccountParams.setPositionReportCycleDiv("3");
//            l_changedInfo.positionReportCycleDiv =  "3";
//            
//            // ����c���񍐏��a��؍쐬�t���O
//            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
//            l_changedInfo.certificateDepositDiv =  "1";
//            
//            // ����c���񍐏��v�Z���쐬�t���O
//            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);
//            l_changedInfo.accountStatementDiv =  "0";
//            
//            // �ŋ敪
//            l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
//            l_changedInfo.taxType =  "1";
//            
//            // �ŋ敪�i���N�j
//            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL);
//            l_changedInfo.taxTypeNext = "2";
//            
//            // �M�p����ŋ敪
//            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxType = "1";
//            
//            // �M�p����ŋ敪�i���N�j
//            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxTypeNext = "2";
//            
//            // ����Ǘ������J�݋敪
//            l_mainAccountParams.setSpMngAccOpenDiv("1");
//            l_changedInfo.capitalGainTaxAccOpenDiv = "2";
//            
//            
//            
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            WEB3GentradeMainAccount  l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
//            
//           
//                        
//            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_changedInfo,l_mainAccount);
//            fail();
//                        
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02877,l_ex.getErrorInfo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /**
//     * ����ԉ�
//     * 
//     */
//    public void testValidateAccSwitchElecDeliApplyInfo_C0014()
//    {
//        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_C0013()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
//            // ���o�C����p�����J�݋敪
//            l_mainAccountParams.setOnlyMobileOpenDiv("0");
//            l_changedInfo.mobileAccoutDiv = "0";
//            
//            // ����񍐏���t�敪
//            l_mainAccountParams.setTradingReportDiv("1");
//            l_changedInfo.tradingReportDiv = "1";
//            
//            // ����c���񍐏���t�敪
//            l_mainAccountParams.setPositionReportDiv("0");
//            l_changedInfo.positionReportDiv =  "9";
//            
//            // ����c���񍐏��쐬�����敪
//            l_mainAccountParams.setPositionReportCycleDiv("3");
//            l_changedInfo.positionReportCycleDiv =  "3";
//            
//            // ����c���񍐏��a��؍쐬�t���O
//            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
//            l_changedInfo.certificateDepositDiv =  "1";
//            
//            // ����c���񍐏��v�Z���쐬�t���O
//            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);
//            l_changedInfo.accountStatementDiv =  "0";
//            
//            // �ŋ敪
//            l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
//            l_changedInfo.taxType =  "1";
//            
//            // �ŋ敪�i���N�j
//            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL);
//            l_changedInfo.taxTypeNext = "2";
//            
//            // �M�p����ŋ敪
//            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxType = "1";
//            
//            // �M�p����ŋ敪�i���N�j
//            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxTypeNext = "2";
//            
//            // ����Ǘ������J�݋敪
//            l_mainAccountParams.setSpMngAccOpenDiv("1");
//            l_changedInfo.capitalGainTaxAccOpenDiv = "2";
//            
//            
//            
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            WEB3GentradeMainAccount  l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
//            
//           
//                        
//            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_changedInfo,l_mainAccount);
//            
//                        
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        catch(Exception l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /**
//     * ����ԉ�(�M�p����ŋ敪==null)
//     * 
//     */
//    public void testValidateAccSwitchElecDeliApplyInfo_C0015()
//    {
//        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_C0015()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
//            // ���o�C����p�����J�݋敪
//            l_mainAccountParams.setOnlyMobileOpenDiv("0");
//            l_changedInfo.mobileAccoutDiv = "0";
//            
//            // ����񍐏���t�敪
//            l_mainAccountParams.setTradingReportDiv("1");
//            l_changedInfo.tradingReportDiv = "1";
//            
//            // ����c���񍐏���t�敪
//            l_mainAccountParams.setPositionReportDiv("0");
//            l_changedInfo.positionReportDiv =  "9";
//            
//            // ����c���񍐏��쐬�����敪
//            l_mainAccountParams.setPositionReportCycleDiv("3");
//            l_changedInfo.positionReportCycleDiv =  "3";
//            
//            // ����c���񍐏��a��؍쐬�t���O
//            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
//            l_changedInfo.certificateDepositDiv =  "1";
//            
//            // ����c���񍐏��v�Z���쐬�t���O
//            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);
//            l_changedInfo.accountStatementDiv =  "0";
//            
//            // �ŋ敪
//            l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
//            l_changedInfo.taxType =  "1";
//            
//            // �ŋ敪�i���N�j
//            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL);
//            l_changedInfo.taxTypeNext = "2";
//            
//            // �M�p����ŋ敪
//            l_mainAccountParams.setMarginTaxType(null);
//            l_changedInfo.marginTaxType = "1";
//            
//            // �M�p����ŋ敪�i���N�j
//            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxTypeNext = "2";
//            
//            // ����Ǘ������J�݋敪
//            l_mainAccountParams.setSpMngAccOpenDiv("1");
//            l_changedInfo.capitalGainTaxAccOpenDiv = "2";
//            
//            
//            
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            WEB3GentradeMainAccount  l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
//            
//           
//                        
//            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_changedInfo,l_mainAccount);
//            
//                        
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        catch(Exception l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /**
//     * ����ԉ�(�M�p����ŋ敪�i���N�j=null)
//     * 
//     */
//    public void testValidateAccSwitchElecDeliApplyInfo_C0016()
//    {
//        String STR_METHOD_NAME = "testValidateAccSwitchElecDeliApplyInfo_C0016()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
//            // ���o�C����p�����J�݋敪
//            l_mainAccountParams.setOnlyMobileOpenDiv("0");
//            l_changedInfo.mobileAccoutDiv = "0";
//            
//            // ����񍐏���t�敪
//            l_mainAccountParams.setTradingReportDiv("1");
//            l_changedInfo.tradingReportDiv = "1";
//            
//            // ����c���񍐏���t�敪
//            l_mainAccountParams.setPositionReportDiv("0");
//            l_changedInfo.positionReportDiv =  "9";
//            
//            // ����c���񍐏��쐬�����敪
//            l_mainAccountParams.setPositionReportCycleDiv("3");
//            l_changedInfo.positionReportCycleDiv =  "3";
//            
//            // ����c���񍐏��a��؍쐬�t���O
//            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
//            l_changedInfo.certificateDepositDiv =  "1";
//            
//            // ����c���񍐏��v�Z���쐬�t���O
//            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);
//            l_changedInfo.accountStatementDiv =  "0";
//            
//            // �ŋ敪
//            l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
//            l_changedInfo.taxType =  "1";
//            
//            // �ŋ敪�i���N�j
//            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL);
//            l_changedInfo.taxTypeNext = "2";
//            
//            // �M�p����ŋ敪
//            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
//            l_changedInfo.marginTaxType = "1";
//            
//            // �M�p����ŋ敪�i���N�j
//            l_mainAccountParams.setMarginTaxTypeNext(null);
//            l_changedInfo.marginTaxTypeNext = "2";
//            
//            // ����Ǘ������J�݋敪
//            l_mainAccountParams.setSpMngAccOpenDiv("1");
//            l_changedInfo.capitalGainTaxAccOpenDiv = "2";
//            
//            
//            
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            WEB3GentradeMainAccount  l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
//            
//           
//                        
//            this.l_serviceImpl.validateAccSwitchElecDeliApplyInfo(l_changedInfo,l_mainAccount);
//            
//                        
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        catch(Exception l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    

    /**
     * �p�����[�^Null�o���Ȃ��B�e�oSYSTEM_ERROR_80017�ُ�M��
     *
     */
    public void testCreateVariousInform_C0001()
    {
        String STR_METHOD_NAME = "testCreateVariousInform_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {        
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setOnlyMobileOpenDiv("1");
            l_mainAccountParams.setTradingReportDiv("0");
            l_mainAccountParams.setPositionReportDiv("1");
            l_mainAccountParams.setPositionReportCycleDiv("0");
            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);
            l_mainAccountParams.setTaxType(TaxTypeEnum.NORMAL);
            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.NORMAL);
            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams.setSpMngAccOpenDiv("0");
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeMainAccount  l_mainAccount = new WEB3GentradeMainAccount(333812512246L);

            this.l_serviceImpl.createVariousInform(l_mainAccount,null,null,null);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3SystemLayerException.class,l_ex.getClass());
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ���팋��
     * �����Ycase���C����bug�F
     * //�e��A��Params.�،���ЃR�[�h �� �ڋq�s.�،���ЃR�[�h
     * l_variousInformParams.setAccountCode(l_mainAcountRow.getAccountCode());
     * ��Y�C���ׁF
     * 
     *
     */
    public void testCreateVariousInform_C0002()
    {
        String STR_METHOD_NAME = "testCreateVariousInform_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {        
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setOnlyMobileOpenDiv("1");
            l_mainAccountParams.setTradingReportDiv("0");
            l_mainAccountParams.setPositionReportDiv("1");
            l_mainAccountParams.setPositionReportCycleDiv("0");
            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);
            l_mainAccountParams.setTaxType(TaxTypeEnum.NORMAL);
            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.NORMAL);
            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams.setSpMngAccOpenDiv("0");
            l_mainAccountParams.setOnlyMblOpnDivTimestamp(null);
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            
            WEB3GentradeMainAccount  l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
            
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            
            WEB3AdminInformAccSwitchElecDeliAppDtInfo l_dateInfo = new WEB3AdminInformAccSwitchElecDeliAppDtInfo();

            String l_strInformType = "01";
            
            l_changedInfo.mobileAccoutDiv = "1";
            l_changedInfo.tradingReportDiv = "0";
            l_changedInfo.positionReportDiv = "1";
            l_changedInfo.positionReportCycleDiv = "0";
            l_changedInfo.certificateDepositDiv = "1";
            l_changedInfo.accountStatementDiv = "0";
            l_changedInfo.taxType = "3";
            l_changedInfo.taxTypeNext = "2";
            l_changedInfo.marginTaxType = "1";
            l_changedInfo.marginTaxTypeNext = "0";
            l_changedInfo.capitalGainTaxAccOpenDiv = "1";
            
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,6);
            l_calendar.set(Calendar.DAY_OF_MONTH,30);
            l_calendar.set(Calendar.HOUR_OF_DAY,10);
            l_calendar.set(Calendar.MINUTE,10);
            l_calendar.set(Calendar.SECOND,20);

            Timestamp l_tsApplyDate = new Timestamp(l_calendar.getTimeInMillis());

            
            l_dateInfo.applyDate = l_tsApplyDate;
            
            l_dateInfo.startScheduleDate = l_tsApplyDate;
            
            l_dateInfo.taxTypeOpenDate = "20070730";
            
            l_dateInfo.marginTaxTypeOpenDate = "20070730";
            
            
            WEB3Inform l_inform =
                this.l_serviceImpl.createVariousInform(l_mainAccount,l_changedInfo,l_dateInfo,l_strInformType);
            
            VariousInformParams l_variousInformParams = (VariousInformParams)l_inform.getDataSourceObject();
            
            assertEquals("0D",l_variousInformParams.getInstitutionCode());
            assertEquals("01",l_variousInformParams.getInformDiv());
            assertEquals("�����@@�l�Y",l_variousInformParams.getAccountName());
            assertEquals("t4@@dir.co.jp",l_variousInformParams.getEmailAddress());
            assertEquals("1",l_variousInformParams.getExtDiv1());
            assertEquals("0",l_variousInformParams.getExtDiv2());
            assertEquals("1",l_variousInformParams.getExtDiv3());
            assertEquals("0",l_variousInformParams.getExtDiv4());
            assertEquals("1",l_variousInformParams.getExtDiv5());
            assertEquals("0",l_variousInformParams.getExtDiv6());
            assertEquals("3",l_variousInformParams.getExtDiv7());
            assertEquals("2",l_variousInformParams.getExtDiv8());
            assertEquals("1",l_variousInformParams.getExtDiv9());
            assertEquals("0",l_variousInformParams.getExtDiv10());
            assertEquals("1",l_variousInformParams.getExtDiv11());
            assertEquals("1",l_variousInformParams.getExtDiv12());
            assertEquals("20070730101020",l_variousInformParams.getExtText1());
            assertEquals("20070730101020",l_variousInformParams.getExtText2());
            assertEquals("20070730",l_variousInformParams.getExtText3());
            assertEquals("20070730",l_variousInformParams.getExtText4());
            assertNull(l_variousInformParams.getExtText5());
            assertNull(l_variousInformParams.getExtText6());

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateVariousInform_C0003()
    {
        String STR_METHOD_NAME = "testCreateVariousInform_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {        
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setOnlyMobileOpenDiv("1");
            l_mainAccountParams.setTradingReportDiv("0");
            l_mainAccountParams.setPositionReportDiv("1");
            l_mainAccountParams.setPositionReportCycleDiv("0");
            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);
            l_mainAccountParams.setTaxType(TaxTypeEnum.NORMAL);
            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.NORMAL);
            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams.setSpMngAccOpenDiv("0");
            l_mainAccountParams.setOnlyMblOpnDivLastUpdater("1234");
            l_mainAccountParams.setOnlyMblOpnDivTimestamp(WEB3DateUtility.getDate("20070922","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            
            WEB3GentradeMainAccount  l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
            
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            
            WEB3AdminInformAccSwitchElecDeliAppDtInfo l_dateInfo = new WEB3AdminInformAccSwitchElecDeliAppDtInfo();

            String l_strInformType = "01";
            
            l_changedInfo.mobileAccoutDiv = "1";
            l_changedInfo.tradingReportDiv = "0";
            l_changedInfo.positionReportDiv = "1";
            l_changedInfo.positionReportCycleDiv = "0";
            l_changedInfo.certificateDepositDiv = "1";
            l_changedInfo.accountStatementDiv = "0";
            l_changedInfo.taxType = "3";
            l_changedInfo.taxTypeNext = "2";
            l_changedInfo.marginTaxType = "1";
            l_changedInfo.marginTaxTypeNext = "0";
            l_changedInfo.capitalGainTaxAccOpenDiv = "1";
            
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,6);
            l_calendar.set(Calendar.DAY_OF_MONTH,30);
            l_calendar.set(Calendar.HOUR_OF_DAY,10);
            l_calendar.set(Calendar.MINUTE,10);
            l_calendar.set(Calendar.SECOND,20);

            Timestamp l_tsApplyDate = new Timestamp(l_calendar.getTimeInMillis());

            
            l_dateInfo.applyDate = l_tsApplyDate;
            
            l_dateInfo.startScheduleDate = l_tsApplyDate;
            
            l_dateInfo.taxTypeOpenDate = "20070730";
            
            l_dateInfo.marginTaxTypeOpenDate = "20070730";
            
            
            WEB3Inform l_inform =
                this.l_serviceImpl.createVariousInform(l_mainAccount,l_changedInfo,l_dateInfo,l_strInformType);
            
            VariousInformParams l_variousInformParams = (VariousInformParams)l_inform.getDataSourceObject();
            
            assertEquals("0D",l_variousInformParams.getInstitutionCode());
            assertEquals("01",l_variousInformParams.getInformDiv());
            assertEquals("�����@@�l�Y",l_variousInformParams.getAccountName());
            assertEquals("t4@@dir.co.jp",l_variousInformParams.getEmailAddress());
            assertEquals("1",l_variousInformParams.getExtDiv1());
            assertEquals("0",l_variousInformParams.getExtDiv2());
            assertEquals("1",l_variousInformParams.getExtDiv3());
            assertEquals("0",l_variousInformParams.getExtDiv4());
            assertEquals("1",l_variousInformParams.getExtDiv5());
            assertEquals("0",l_variousInformParams.getExtDiv6());
            assertEquals("3",l_variousInformParams.getExtDiv7());
            assertEquals("2",l_variousInformParams.getExtDiv8());
            assertEquals("1",l_variousInformParams.getExtDiv9());
            assertEquals("0",l_variousInformParams.getExtDiv10());
            assertEquals("1",l_variousInformParams.getExtDiv11());
            assertEquals("1",l_variousInformParams.getExtDiv12());
            assertEquals("20070730101020",l_variousInformParams.getExtText1());
            assertEquals("20070730101020",l_variousInformParams.getExtText2());
            assertEquals("20070730",l_variousInformParams.getExtText3());
            assertEquals("20070730",l_variousInformParams.getExtText4());
            assertEquals("1234",l_variousInformParams.getExtText5());
            assertEquals("20070922000000",
                    l_variousInformParams.getExtText6());

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
//    /**
//     * �p�����[�^Null�o���Ȃ��B�e�oSYSTEM_ERROR_80017�ُ�M��
//     *
//     */
//    public void testCreateAccSwitchElecDeliApplyInfo_C0001()
//    {
//        String STR_METHOD_NAME = "testCreateAccSwitchElecDeliApplyInfo_C0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            this.l_serviceImpl.createAccSwitchElecDeliApplyInfo(null);
//            fail();
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            assertEquals(WEB3SystemLayerException.class,l_ex.getClass());
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    
    /**
    * ���팋��
    * 
    *
    */
   public void testCreateAccSwitchElecDeliApplyInfo_C0002()
   {
       String STR_METHOD_NAME = "testCreateAccSwitchElecDeliApplyInfo_C0002()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {        
           TestDBUtility.deleteAll(MainAccountRow.TYPE);
           MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
           l_mainAccountParams.setOnlyMobileOpenDiv("1");
           l_mainAccountParams.setTradingReportDiv("0");
           l_mainAccountParams.setPositionReportDiv("1");
           l_mainAccountParams.setPositionReportCycleDiv("0");
           l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
           l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);
           l_mainAccountParams.setTaxType(TaxTypeEnum.NORMAL);
           l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.NORMAL);
           l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
           l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
           l_mainAccountParams.setSpMngAccOpenDiv("0");
           
           TestDBUtility.insertWithDel(l_mainAccountParams);
           
           
           WEB3GentradeMainAccount  l_mainAccount = new WEB3GentradeMainAccount(333812512246L);

           WEB3AdminInformAccSwitchElecDeliApplyInfo l_info =
               this.l_serviceImpl.createAccSwitchElecDeliApplyInfo(l_mainAccount);
           
           assertEquals("1",l_info.mobileAccoutDiv);
           assertEquals("0",l_info.tradingReportDiv);
           assertEquals("1",l_info.positionReportDiv);
           assertEquals("0",l_info.positionReportCycleDiv);
           assertEquals("1",l_info.certificateDepositDiv);
           assertEquals("0",l_info.accountStatementDiv);
           assertEquals("1",l_info.taxType);
           assertEquals("1",l_info.taxTypeNext);
           assertEquals("0",l_info.marginTaxType);
           assertEquals("0",l_info.marginTaxTypeNext);
           assertEquals("0",l_info.capitalGainTaxAccOpenDiv);

       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   /**
    * ���팋��
    * �ڋq�s.�M�p����ŋ敪 = null
    * �ڋq�s.�M�p����ŋ敪�i���N�j = null
    *
    */
   public void testCreateAccSwitchElecDeliApplyInfo_C0003()
   {
       String STR_METHOD_NAME = "testCreateAccSwitchElecDeliApplyInfo_C0003()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {        
           TestDBUtility.deleteAll(MainAccountRow.TYPE);
           MainAccountParams l_mainAccountParams = new MainAccountParams();

           l_mainAccountParams.setAccountId(333812512246L);
           l_mainAccountParams.setInstitutionCode("0D");
           l_mainAccountParams.setInstitutionId(33L);
           l_mainAccountParams.setAccountCode("2512246");
           l_mainAccountParams.setBranchId(33381L);
           l_mainAccountParams.setBranchCode("381");
           l_mainAccountParams.setSonarTraderCode("11124");
           l_mainAccountParams.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);
           l_mainAccountParams.setFamilyName("�����@@�l�Y");
           l_mainAccountParams.setFamilyNameAlt1("Ųĳ �۳");
           l_mainAccountParams.setGivenNameAlt1("Siro");
           l_mainAccountParams.setZipCode("1001238");
           l_mainAccountParams.setSubZipCode("1001238");
           l_mainAccountParams.setAddressLine1("�����s");
           l_mainAccountParams.setAddressLine2("�]����");
           l_mainAccountParams.setAddressLine3("�[��T");
           l_mainAccountParams.setTelephone("38201115");
           l_mainAccountParams.setMobile("901115");
           l_mainAccountParams.setFax("38202226");
           l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.TRUE);
           l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.TRUE);
           l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.TRUE);
           l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.TRUE);
           l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
           l_mainAccountParams.setResident("0");
           l_mainAccountParams.setNewAccountDiv("0");
           l_mainAccountParams.setViaTrustBankDiv("0");
           l_mainAccountParams.setEmailAddress("t4@@dir.co.jp");
           l_mainAccountParams.setTradingPassword("&:,<#!+=!.,#:##&");
           l_mainAccountParams.setAccountOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
           l_mainAccountParams.setAccountCloseDate(WEB3DateUtility.getDate("99991231","yyyyMMdd"));
           l_mainAccountParams.setPersonIdentify("1");
           l_mainAccountParams.setEraBorn("3");
           l_mainAccountParams.setBornDate("101013");
           l_mainAccountParams.setSex("1");
           l_mainAccountParams.setYellowCustomer("0");
           l_mainAccountParams.setHtSettlementWay("0");
           l_mainAccountParams.setBankAccountRegi("0");
           l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
           l_mainAccountParams.setExaminLockFlag("0");
           l_mainAccountParams.setMngLockFlag("0");
           l_mainAccountParams.setMngLockFlagAdvance(BooleanEnum.FALSE);
           l_mainAccountParams.setMngLockFlagUnpayMargin(BooleanEnum.FALSE);
           l_mainAccountParams.setMngLockFlagShortSecurity(BooleanEnum.FALSE);
           l_mainAccountParams.setMngLockFlagUnsubstitDepo(BooleanEnum.FALSE);
           l_mainAccountParams.setBranchLock("0");
           l_mainAccountParams.setOrderPermission("0");
           l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
           l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
//           l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
//           l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
           l_mainAccountParams.setQualifiedInstInvestorDiv("0");
           l_mainAccountParams.setMarginSysAccOpenDiv("0");
           l_mainAccountParams.setMarginGenAccOpenDiv("0");
           l_mainAccountParams.setEquitySpAccOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
           l_mainAccountParams.setForeignSecAccOpenDiv("1");
           l_mainAccountParams.setIfoAccOpenDivTokyo("0");
           l_mainAccountParams.setIfoAccOpenDivOsaka("0");
           l_mainAccountParams.setIfoAccOpenDivNagoya("0");
           l_mainAccountParams.setRuitoAccOpenDiv("0");
           l_mainAccountParams.setMrfAccOpenDiv("0");
           l_mainAccountParams.setFxAccOpenDiv("0");
           l_mainAccountParams.setFeqConAccOpenDiv("0");
           l_mainAccountParams.setTopPageId("0");
           l_mainAccountParams.setQuotoType("0");
           l_mainAccountParams.setTradingReportDiv("1");
           l_mainAccountParams.setPositionReportDiv("9");
           l_mainAccountParams.setPositionReportCycleDiv("1");
           l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
           l_mainAccountParams.setAccountStatementFlag(BooleanEnum.TRUE);
           l_mainAccountParams.setEmailLastUpdater("2512246");
           l_mainAccountParams.setEmailLastUpdatedTimestamp(Calendar.getInstance().getTime());
           l_mainAccountParams.setTradingPasswordUpdater("2512246");
           l_mainAccountParams.setTrPwdLastUpdateTimestamp(Calendar.getInstance().getTime());
           l_mainAccountParams.setMbOffLastUpdater("2512246");
           l_mainAccountParams.setMbOffLastUpdatedTimestamp(Calendar.getInstance().getTime());
           l_mainAccountParams.setEnableOrderLastUpdater("2512246");
           l_mainAccountParams.setEnableOrderUpdatedTimestamp(Calendar.getInstance().getTime());
           l_mainAccountParams.setFxAccOpenDivLastUpdater("2512246");
           l_mainAccountParams.setFxAccOpenUpdatedTimestamp(Calendar.getInstance().getTime());
           l_mainAccountParams.setFeqConAccOpenDivUpdater("2512246");
           l_mainAccountParams.setFeqConAccOpenTimestamp(Calendar.getInstance().getTime());
           l_mainAccountParams.setMrfFundCode("1");
           l_mainAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
           l_mainAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
           l_mainAccountParams.setSpMngAccOpenDiv("0");
           l_mainAccountParams.setOnlyMobileOpenDiv("1");
           l_mainAccountParams.setTradingReportDiv("0");
           l_mainAccountParams.setPositionReportDiv("1");
           l_mainAccountParams.setPositionReportCycleDiv("0");
           l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
           l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);
           l_mainAccountParams.setTaxType(TaxTypeEnum.NORMAL);
           l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.NORMAL);
//           l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
//           l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
           l_mainAccountParams.setSpMngAccOpenDiv("0");
           
           TestDBUtility.insertWithDel(l_mainAccountParams);
           
           
           WEB3GentradeMainAccount  l_mainAccount = new WEB3GentradeMainAccount(333812512246L);

           WEB3AdminInformAccSwitchElecDeliApplyInfo l_info =
               this.l_serviceImpl.createAccSwitchElecDeliApplyInfo(l_mainAccount);
           
           assertEquals("1",l_info.mobileAccoutDiv);
           assertEquals("0",l_info.tradingReportDiv);
           assertEquals("1",l_info.positionReportDiv);
           assertEquals("0",l_info.positionReportCycleDiv);
           assertEquals("1",l_info.certificateDepositDiv);
           assertEquals("0",l_info.accountStatementDiv);
           assertEquals("1",l_info.taxType);
           assertEquals("1",l_info.taxTypeNext);
           assertNull(l_info.marginTaxType);
           assertNull(l_info.marginTaxTypeNext);
           assertEquals("0",l_info.capitalGainTaxAccOpenDiv);

       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   /**
    * �ŋ敪 = "1"
    * �M�p����ŋ敪 = "1"
    * �����ؑցE�d�q��t�\�����t���.�\������ �� ���ݓ���
    * �����ؑցE�d�q��t�\�����t���.�J�n�\��� �� �c�Ɠ�
    * �u�ŋ敪�J�ݓ��v= null
    * �u�M�p����ŋ敪�J�ݓ��v= null
    *
    */
   public void testCreateAccSwitchElecDeliAppDtInfo_C0001()
   {
       String STR_METHOD_NAME = "testCreateAccSwitchElecDeliAppDtInfo_C0001()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {       
           Calendar l_calendar = Calendar.getInstance();
           l_calendar.set(Calendar.YEAR,2007);
           l_calendar.set(Calendar.MONTH,6);
           l_calendar.set(Calendar.DAY_OF_MONTH,30);
           l_calendar.set(Calendar.HOUR_OF_DAY,17);
           l_calendar.set(Calendar.MINUTE,10);
           l_calendar.set(Calendar.SECOND,20);

           Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

           ThreadLocalSystemAttributesRegistry.setAttribute(
               WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
               "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
               "getSystemTimestamp",
               new Class[] {},
               l_tsBizDate);
           
           Calendar ca1 =  Calendar.getInstance();
           ca1.set(2007,7-1,30);
           
           Date date1 = ca1.getTime();
           WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
           
           WEB3AdminInformAccSwitchElecDeliAppDtInfo l_dtInfo =
               this.l_serviceImpl.createAccSwitchElecDeliAppDtInfo("1","1");
           
           assertEquals(0,
               WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20070730","yyyyMMdd"),l_dtInfo.applyDate));
           
           assertEquals(0,
                   WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20070730","yyyyMMdd"),l_dtInfo.startScheduleDate));
           
           assertNull(l_dtInfo.taxTypeOpenDate);
           assertNull(l_dtInfo.marginTaxTypeOpenDate);
           
       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   /**
    * �ŋ敪 = "2"
    * �M�p����ŋ敪 = "2"
    * �����ؑցE�d�q��t�\�����t���.�\������ �� ���ݓ���
    * �����ؑցE�d�q��t�\�����t���.�J�n�\��� �� �c�Ɠ�
    * �u�ŋ敪�J�ݓ��v= ��ɘa��ϊ��������t
    * �u�M�p����ŋ敪�J�ݓ��v= ��ɘa��ϊ��������t
    * ���Ycase�����obug�F��v�Ґ�Enum�ތ^�I�ɝ����L�z����String�ތ^�I�ɝ��C�A���ڐi�s��r���B
    * �C���O�F
    * if (TaxTypeEnum.SPECIAL.equals(l_strMarginTaxType)
    *        || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_strMarginTaxType))
    * �C���@@�F
    * 
    *   int l_intSpecial = (TaxTypeEnum.SPECIAL).intValue();
    *   int l_intSpecialWithHold = (TaxTypeEnum.SPECIAL_WITHHOLD).intValue();
    *
    *    if (String.valueOf(l_intSpecial).equals(l_strTaxType)
    *        || String.valueOf(l_intSpecialWithHold).equals(l_strTaxType))
    *   
    *
    */
   public void testCreateAccSwitchElecDeliAppDtInfo_C0002()
   {
       String STR_METHOD_NAME = "testCreateAccSwitchElecDeliAppDtInfo_C0002()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {       
           Calendar l_calendar = Calendar.getInstance();
           l_calendar.set(Calendar.YEAR,2008);
           l_calendar.set(Calendar.MONTH,0);
           l_calendar.set(Calendar.DAY_OF_MONTH,07);
           l_calendar.set(Calendar.HOUR_OF_DAY,17);
           l_calendar.set(Calendar.MINUTE,10);
           l_calendar.set(Calendar.SECOND,20);

           Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

           ThreadLocalSystemAttributesRegistry.setAttribute(
               WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
               "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
               "getSystemTimestamp",
               new Class[] {},
               l_tsBizDate);
           
           Calendar ca1 =  Calendar.getInstance();
           ca1.set(2008,1-1,07);
           
           Date date1 = ca1.getTime();
           WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
           
           WEB3AdminInformAccSwitchElecDeliAppDtInfo l_dtInfo =
               this.l_serviceImpl.createAccSwitchElecDeliAppDtInfo("2","2");
           
           assertEquals(0,
               WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20080107","yyyyMMdd"),l_dtInfo.applyDate));
           
           assertEquals(0,
                   WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20080107","yyyyMMdd"),l_dtInfo.startScheduleDate));
           
           assertNotNull(l_dtInfo.taxTypeOpenDate);
           assertNotNull(l_dtInfo.marginTaxTypeOpenDate);
           
           assertEquals("010107",l_dtInfo.taxTypeOpenDate);
           assertEquals("010107",l_dtInfo.marginTaxTypeOpenDate);
           
       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   /**
    * �p�����[�^�l�s���B
    * �e�o�ُ�M���FSYSTEM_ERROR_80017
    *
    */
   public void testCreateAccSwitchElecDeliApplyInfoForVariousInform_C0001()
   {
       String STR_METHOD_NAME = "testCreateAccSwitchElecDeliApplyInfoForVariousInform_C0001()";
       log.entering(TEST_START + STR_METHOD_NAME); 
       try
       {
           VariousInformParams l_variousInformParams = null;
           this.l_serviceImpl.createAccSwitchElecDeliApplyInfo(l_variousInformParams);
           fail();

       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
           log.exiting(TEST_END + STR_METHOD_NAME);
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   /**
    * ����ԉ�
    *
    */
   public void testCreateAccSwitchElecDeliApplyInfoForVariousInform_C0002()
   {
       String STR_METHOD_NAME = "testCreateAccSwitchElecDeliApplyInfoForVariousInform_C0002()";
       log.entering(TEST_START + STR_METHOD_NAME); 
       try
       {
           VariousInformParams l_variousInformParams = new VariousInformParams();
           l_variousInformParams.ext_div1 = "01";
           l_variousInformParams.ext_div2 = "02";
           l_variousInformParams.ext_div3 = "03";
           l_variousInformParams.ext_div4 = "04";
           l_variousInformParams.ext_div5 = "05";
           l_variousInformParams.ext_div6 = "06";
           l_variousInformParams.ext_div7 = "07";
           l_variousInformParams.ext_div8 = "08";
           l_variousInformParams.ext_div9 = "09";
           l_variousInformParams.ext_div10 = "10";
           l_variousInformParams.ext_div11 = "11";
           
           WEB3AdminInformAccSwitchElecDeliApplyInfo l_info =
               this.l_serviceImpl.createAccSwitchElecDeliApplyInfo(l_variousInformParams);
           
           assertEquals("01",l_info.mobileAccoutDiv);
           assertEquals("02",l_info.tradingReportDiv);
           assertEquals("03",l_info.positionReportDiv);
           assertEquals("04",l_info.positionReportCycleDiv);
           assertEquals("05",l_info.certificateDepositDiv);
           assertEquals("06",l_info.accountStatementDiv);
           assertEquals("07",l_info.taxType);
           assertEquals("08",l_info.taxTypeNext);
           assertEquals("09",l_info.marginTaxType);
           assertEquals("10",l_info.marginTaxTypeNext);
           assertEquals("11",l_info.capitalGainTaxAccOpenDiv);
          

       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   /**
    * �p�����[�^�l�s���B
    * �e�o�ُ�M���FSYSTEM_ERROR_80017
    *
    */
   public void testCreateHostConditionRegVoucherParams_C0001()
   {
       String STR_METHOD_NAME = "testCreateHostConditionRegVoucherParams_C0001()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {
           VariousInformParams l_variousInformParams = null;
           WEB3GentradeMainAccount l_mainAccount = null;
           this.l_serviceImpl.createHostConditionRegVoucherParams(l_mainAccount,l_variousInformParams);
           fail();
       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
           log.exiting(TEST_END + STR_METHOD_NAME);
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
      
   /**
    * ����ԉ�
    * ���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@��� = null
    * ���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�d�q��t�i�s�x�j= null
    * ���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�a��� = null
    * ���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�v�Z�� = null
    * ���E��c�d�q��t�E��������o�^Params.�d�q��t�@@����񍐏� = null
    * ���E��c�d�q��t�E��������o�^Params.�i�����j��������@@���� = null
    * ���E��c�d�q��t�E��������o�^Params.�i�����j��������@@���� = null
    * ���E��c�d�q��t�E��������o�^Params.�i�M�p�j��������@@���� = null
    * ���E��c�d�q��t�E��������o�^Params.����Ǘ����� = null
    *
    */
   public void testCreateHostConditionRegVoucherParams_C0002()
   {
       String STR_METHOD_NAME = "testCreateHostConditionRegVoucherParams_C0002()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {
           MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
           // ����c���񍐏��쐬�����敪
           l_mainAccountParams.setPositionReportCycleDiv("1");
           // ����c���񍐏���t�敪
           l_mainAccountParams.setPositionReportDiv("0");
           // ����c���񍐏��a��؍쐬�t���O
           l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
           //  ����c���񍐏��v�Z���쐬�t���O  account_statement_flag
           l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);
           // ����񍐏���t�敪 trading_report_div
           l_mainAccountParams.setTradingReportDiv("1");
           l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
           VariousInformParams l_variousInformParams = new VariousInformParams();
           // ���E��c�d�q��t�E��������o�^Params.�،���ЃR�[�h �� (����)�e��A���s.�،���ЃR�[�h
           l_variousInformParams.institution_code = "0D";
           // ���E��c�d�q��t�E��������o�^Params.���X�R�[�h �� (����)�e��A���s.���X�R�[�h
           l_variousInformParams.branch_code = "381";
           // ���E��c�d�q��t�E��������o�^Params.�ڋq�R�[�h �� (����)�e��A���s.�ڋq�R�[�h
           l_variousInformParams.account_code = "123456";
           l_variousInformParams.ext_text3 ="20070922";
           l_variousInformParams.ext_div4 = "1";
           l_variousInformParams.ext_div3 = "0";
           l_variousInformParams.ext_div5 = "1";
           l_variousInformParams.ext_div6 = "0";
           l_variousInformParams.ext_div2 = "1";
           
           
           WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
           HostConditionRegVoucherParams l_params = 
               this.l_serviceImpl.createHostConditionRegVoucherParams(l_mainAccount,l_variousInformParams);
           
           assertEquals("0D",l_params.getInstitutionCode());
           assertEquals("381",l_params.getBranchCode());
           assertEquals("123456",l_params.getAccountCode());
           assertNull(l_params.getPosReportTermDiv());
           // pos_report_cycle_div
           assertNull(l_params.getPosReportCycleDiv());
           // pos_report_certif_depo_div
           assertNull(l_params.getPosReportCertifDepoDiv());
           // pos_report_acc_state_div
           assertNull(l_params.getPosReportAccStateDiv());
           // trading_e_report_div
           assertNull(l_params.getTradingEReportDiv());

           assertNull(l_params.getEquityTaxDiv());
           
           // setEquityTaxDivNext
           assertNull(l_params.getEquityTaxDivNext());
           
           // ���E��c�d�q��t�E��������o�^Params.�i�����j��������@@�J�ݓ� �� (����)�e��A���s.�e�L�X�g�R
           // equity_sp_acc_open_dat
           assertEquals("20070922",l_params.getEquitySpAccOpenDat());
           
           // setMarginTaxDiv
           assertNull(l_params.getMarginTaxDiv());
           
           // setMarginTaxDivNext
           assertNull(l_params.getMarginTaxDivNext());
           
           // ���E��c�d�q��t�E��������o�^Params.�i�M�p�j��������@@�J�ݓ� �� (����)�e��A���s.�e�L�X�g�S
           // setMarginSpAccOpenDat
           assertNull(l_params.getMarginSpAccOpenDat());
           
           // sp_mng_acc_open_div
           assertNull(l_params.getSpMngAccOpenDiv());
           
           
       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   /**
    * ����ԉ�
    * ���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@��� != null
    * ���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�d�q��t�i�s�x�j!= null
    * ���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�a��� != null
    * ���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�v�Z�� != null
    * ���E��c�d�q��t�E��������o�^Params.�d�q��t�@@����񍐏� != null
    * (����)�e��A���s.�敪�V = 0�F ���̑�
    * ���E��c�d�q��t�E��������o�^Params.�i�����j��������@@���� != null
    * (����)�e��A���s.�敪8 = 0�F ���̑�
    * ���E��c�d�q��t�E��������o�^Params.�i�����j��������@@���� != null
    * (����)�e��A���s.�敪9 = 0�F ���̑�
    * ���E��c�d�q��t�E��������o�^Params.�i�M�p�j��������@@���� != null
    * (����)�e��A���s.�敪10 = 0�F ���̑�
    * ���E��c�d�q��t�E��������o�^Params.����Ǘ����� != null
    *
    */
   public void testCreateHostConditionRegVoucherParams_C0003()
   {
       String STR_METHOD_NAME = "testCreateHostConditionRegVoucherParams_C0003()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {
           MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
           // ����c���񍐏��쐬�����敪
           l_mainAccountParams.setPositionReportCycleDiv("0");
           // ����c���񍐏���t�敪
           l_mainAccountParams.setPositionReportDiv("1");
           // ����c���񍐏��a��؍쐬�t���O
           l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.FALSE);
           //  ����c���񍐏��v�Z���쐬�t���O  account_statement_flag
           l_mainAccountParams.setAccountStatementFlag(BooleanEnum.TRUE);
           // ����񍐏���t�敪 trading_report_div
           l_mainAccountParams.setTradingReportDiv("0");
           l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
           // tax_type_next
           l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.NORMAL);
           // margin_tax_type
           l_mainAccountParams.setMarginTaxType(TaxTypeEnum.NORMAL);
           
           l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.NORMAL);
           
           // sp_mng_acc_open_div
           l_mainAccountParams.setSpMngAccOpenDiv("1");
           VariousInformParams l_variousInformParams = new VariousInformParams();
           // ���E��c�d�q��t�E��������o�^Params.�،���ЃR�[�h �� (����)�e��A���s.�،���ЃR�[�h
           l_variousInformParams.institution_code = "0D";
           // ���E��c�d�q��t�E��������o�^Params.���X�R�[�h �� (����)�e��A���s.���X�R�[�h
           l_variousInformParams.branch_code = "381";
           // ���E��c�d�q��t�E��������o�^Params.�ڋq�R�[�h �� (����)�e��A���s.�ڋq�R�[�h
           l_variousInformParams.account_code = "123456";
           l_variousInformParams.ext_text3 ="20070921";
           l_variousInformParams.ext_text4 ="20070920";
           l_variousInformParams.ext_div4 = "1";
           l_variousInformParams.ext_div3 = "0";
           l_variousInformParams.ext_div5 = "1";
           l_variousInformParams.ext_div6 = "0";
           l_variousInformParams.ext_div2 = "1";
           
           l_variousInformParams.ext_div7 = "0";
           l_variousInformParams.ext_div8 = "0";
           l_variousInformParams.ext_div9 = "0";
           l_variousInformParams.ext_div10 = "0";
           l_variousInformParams.ext_div11 = "0";
           
           
           WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
           HostConditionRegVoucherParams l_params = 
               this.l_serviceImpl.createHostConditionRegVoucherParams(l_mainAccount,l_variousInformParams);
           
           assertEquals("0D",l_params.getInstitutionCode());
           assertEquals("381",l_params.getBranchCode());
           assertEquals("123456",l_params.getAccountCode());

           
           // ����c���񍐏��@@��� pos_report_term_div
           assertEquals("1",l_params.getPosReportTermDiv());
           // ����c���񍐏��@@�d�q��t�i�s�x�jpos_report_cycle_div
           assertEquals("0",l_params.getPosReportCycleDiv());           
           // ����c���񍐏��@@�a��� pos_report_certif_depo_div
           assertEquals("1",l_params.getPosReportCertifDepoDiv()); 
           assertEquals("0",l_params.getPosReportAccStateDiv());
           // trading_e_report_div
           assertEquals("1",l_params.getTradingEReportDiv());

           // �i�����j��������@@����
           assertNull(l_params.getEquityTaxDiv());
           
           // �i�����j��������@@����
           // setEquityTaxDivNext
           assertNull(l_params.getEquityTaxDivNext());
           
           // ���E��c�d�q��t�E��������o�^Params.�i�����j��������@@�J�ݓ� �� (����)�e��A���s.�e�L�X�g�R
           // equity_sp_acc_open_dat �i�����j��������@@�J�ݓ�
           assertEquals("20070921",l_params.getEquitySpAccOpenDat());
           
           // setMarginTaxDiv
           assertNull(l_params.getMarginTaxDiv());
           
           // setMarginTaxDivNext
           assertNull(l_params.getMarginTaxDivNext());
           
           // ���E��c�d�q��t�E��������o�^Params.�i�M�p�j��������@@�J�ݓ� �� (����)�e��A���s.�e�L�X�g�S
           // setMarginSpAccOpenDat �i�M�p�j��������@@�J�ݓ� 
           assertEquals("20070920",l_params.getMarginSpAccOpenDat());
           
           // sp_mng_acc_open_div
           assertEquals("0",l_params.getSpMngAccOpenDiv());
           
           
       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   /**
    * ����ԉ�
    * ���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@��� != null
    * ���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�d�q��t�i�s�x�j!= null
    * ���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�a��� != null
    * ���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�v�Z�� != null
    * ���E��c�d�q��t�E��������o�^Params.�d�q��t�@@����񍐏� != null
    * (����)�e��A���s.�敪�V = 1�F ���
    * ���E��c�d�q��t�E��������o�^Params.�i�����j��������@@���� != null
    * (����)�e��A���s.�敪8 = 1�F ���
    * ���E��c�d�q��t�E��������o�^Params.�i�����j��������@@���� != null
    * (����)�e��A���s.�敪9 = 1�F ���
    * ���E��c�d�q��t�E��������o�^Params.�i�M�p�j��������@@���� != null
    * (����)�e��A���s.�敪10 = 1�F ���
    * ���E��c�d�q��t�E��������o�^Params.����Ǘ����� != null
    *
    */
   public void testCreateHostConditionRegVoucherParams_C0004()
   {
       String STR_METHOD_NAME = "testCreateHostConditionRegVoucherParams_C0004()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {
           MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
           // ����c���񍐏��쐬�����敪
           l_mainAccountParams.setPositionReportCycleDiv("0");
           // ����c���񍐏���t�敪
           l_mainAccountParams.setPositionReportDiv("1");
           // ����c���񍐏��a��؍쐬�t���O
           l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.FALSE);
           //  ����c���񍐏��v�Z���쐬�t���O  account_statement_flag
           l_mainAccountParams.setAccountStatementFlag(BooleanEnum.TRUE);
           // ����񍐏���t�敪 trading_report_div
           l_mainAccountParams.setTradingReportDiv("0");
           l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
           // tax_type_next
           l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL);
           // margin_tax_type
           l_mainAccountParams.setMarginTaxType(TaxTypeEnum.SPECIAL);
           
           l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.SPECIAL);
           
           // sp_mng_acc_open_div
           l_mainAccountParams.setSpMngAccOpenDiv("1");
           VariousInformParams l_variousInformParams = new VariousInformParams();
           // ���E��c�d�q��t�E��������o�^Params.�،���ЃR�[�h �� (����)�e��A���s.�،���ЃR�[�h
           l_variousInformParams.institution_code = "0D";
           // ���E��c�d�q��t�E��������o�^Params.���X�R�[�h �� (����)�e��A���s.���X�R�[�h
           l_variousInformParams.branch_code = "381";
           // ���E��c�d�q��t�E��������o�^Params.�ڋq�R�[�h �� (����)�e��A���s.�ڋq�R�[�h
           l_variousInformParams.account_code = "123456";
           l_variousInformParams.ext_text3 ="20070921";
           l_variousInformParams.ext_text4 ="20070920";
           l_variousInformParams.ext_div4 = "1";
           l_variousInformParams.ext_div3 = "0";
           l_variousInformParams.ext_div5 = "1";
           l_variousInformParams.ext_div6 = "0";
           l_variousInformParams.ext_div2 = "1";
           
           l_variousInformParams.ext_div7 = "1";
           l_variousInformParams.ext_div8 = "1";
           l_variousInformParams.ext_div9 = "1";
           l_variousInformParams.ext_div10 = "1";
           
           l_variousInformParams.ext_div11 = "0";
           
           
           WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
           HostConditionRegVoucherParams l_params = 
               this.l_serviceImpl.createHostConditionRegVoucherParams(l_mainAccount,l_variousInformParams);
           
           assertEquals("0D",l_params.getInstitutionCode());
           assertEquals("381",l_params.getBranchCode());
           assertEquals("123456",l_params.getAccountCode());

           
           // ����c���񍐏��@@��� pos_report_term_div
           assertEquals("1",l_params.getPosReportTermDiv());
           // ����c���񍐏��@@�d�q��t�i�s�x�jpos_report_cycle_div
           assertEquals("0",l_params.getPosReportCycleDiv());           
           // ����c���񍐏��@@�a��� pos_report_certif_depo_div
           assertEquals("1",l_params.getPosReportCertifDepoDiv()); 
           assertEquals("0",l_params.getPosReportAccStateDiv());
           // trading_e_report_div
           assertEquals("1",l_params.getTradingEReportDiv());

           // �i�����j��������@@����
           assertEquals("0",l_params.getEquityTaxDiv());
           
           // �i�����j��������@@����
           // setEquityTaxDivNext
           assertEquals("0",l_params.getEquityTaxDivNext());
           
           // ���E��c�d�q��t�E��������o�^Params.�i�����j��������@@�J�ݓ� �� (����)�e��A���s.�e�L�X�g�R
           // equity_sp_acc_open_dat �i�����j��������@@�J�ݓ�
           assertEquals("20070921",l_params.getEquitySpAccOpenDat());
           
           // setMarginTaxDiv
           assertEquals("0",l_params.getMarginTaxDiv());
           
           // setMarginTaxDivNext
           assertEquals("0",l_params.getMarginTaxDivNext());
           
           // ���E��c�d�q��t�E��������o�^Params.�i�M�p�j��������@@�J�ݓ� �� (����)�e��A���s.�e�L�X�g�S
           // setMarginSpAccOpenDat �i�M�p�j��������@@�J�ݓ� 
           assertEquals("20070920",l_params.getMarginSpAccOpenDat());
           
           // sp_mng_acc_open_div
           assertEquals("0",l_params.getSpMngAccOpenDiv());
           
           
       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   /**
    * ����ԉ�
    * ���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@��� != null
    * ���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�d�q��t�i�s�x�j!= null
    * ���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�a��� != null
    * ���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�v�Z�� != null
    * ���E��c�d�q��t�E��������o�^Params.�d�q��t�@@����񍐏� != null
    * (����)�e��A���s.�敪�V = 2�F ����E����Ȃ�
    * ���E��c�d�q��t�E��������o�^Params.�i�����j��������@@���� != null
    * (����)�e��A���s.�敪8 = 2�F ����E����Ȃ�
    * ���E��c�d�q��t�E��������o�^Params.�i�����j��������@@���� != null
    * (����)�e��A���s.�敪9 = 2�F ����E����Ȃ�
    * ���E��c�d�q��t�E��������o�^Params.�i�M�p�j��������@@���� != null
    * (����)�e��A���s.�敪10 = 2�F ����E����Ȃ�
    * ���E��c�d�q��t�E��������o�^Params.����Ǘ����� != null
    *
    */
   public void testCreateHostConditionRegVoucherParams_C0005()
   {
       String STR_METHOD_NAME = "testCreateHostConditionRegVoucherParams_C0005()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {
           MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
           // ����c���񍐏��쐬�����敪
           l_mainAccountParams.setPositionReportCycleDiv("0");
           // ����c���񍐏���t�敪
           l_mainAccountParams.setPositionReportDiv("1");
           // ����c���񍐏��a��؍쐬�t���O
           l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.FALSE);
           //  ����c���񍐏��v�Z���쐬�t���O  account_statement_flag
           l_mainAccountParams.setAccountStatementFlag(BooleanEnum.TRUE);
           // ����񍐏���t�敪 trading_report_div
           l_mainAccountParams.setTradingReportDiv("0");
           l_mainAccountParams.setTaxType(TaxTypeEnum.NORMAL);
           // tax_type_next
           l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.NORMAL);
           // margin_tax_type
           l_mainAccountParams.setMarginTaxType(TaxTypeEnum.NORMAL);
           
           l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.NORMAL);
           
           // sp_mng_acc_open_div
           l_mainAccountParams.setSpMngAccOpenDiv("1");
           VariousInformParams l_variousInformParams = new VariousInformParams();
           // ���E��c�d�q��t�E��������o�^Params.�،���ЃR�[�h �� (����)�e��A���s.�،���ЃR�[�h
           l_variousInformParams.institution_code = "0D";
           // ���E��c�d�q��t�E��������o�^Params.���X�R�[�h �� (����)�e��A���s.���X�R�[�h
           l_variousInformParams.branch_code = "381";
           // ���E��c�d�q��t�E��������o�^Params.�ڋq�R�[�h �� (����)�e��A���s.�ڋq�R�[�h
           l_variousInformParams.account_code = "123456";
           l_variousInformParams.ext_text3 ="20070921";
           l_variousInformParams.ext_text4 ="20070920";
           l_variousInformParams.ext_div4 = "1";
           l_variousInformParams.ext_div3 = "0";
           l_variousInformParams.ext_div5 = "1";
           l_variousInformParams.ext_div6 = "0";
           l_variousInformParams.ext_div2 = "1";
           
           l_variousInformParams.ext_div7 = "2";
           l_variousInformParams.ext_div8 = "2";
           l_variousInformParams.ext_div9 = "2";
           l_variousInformParams.ext_div10 = "2";
           
           l_variousInformParams.ext_div11 = "0";
           
           
           WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
           HostConditionRegVoucherParams l_params = 
               this.l_serviceImpl.createHostConditionRegVoucherParams(l_mainAccount,l_variousInformParams);
           
           assertEquals("0D",l_params.getInstitutionCode());
           assertEquals("381",l_params.getBranchCode());
           assertEquals("123456",l_params.getAccountCode());

           
           // ����c���񍐏��@@��� pos_report_term_div
           assertEquals("1",l_params.getPosReportTermDiv());
           // ����c���񍐏��@@�d�q��t�i�s�x�jpos_report_cycle_div
           assertEquals("0",l_params.getPosReportCycleDiv());           
           // ����c���񍐏��@@�a��� pos_report_certif_depo_div
           assertEquals("1",l_params.getPosReportCertifDepoDiv()); 
           assertEquals("0",l_params.getPosReportAccStateDiv());
           // trading_e_report_div
           assertEquals("1",l_params.getTradingEReportDiv());

           // �i�����j��������@@����
           assertEquals("1",l_params.getEquityTaxDiv());
           
           // �i�����j��������@@����
           // setEquityTaxDivNext
           assertEquals("1",l_params.getEquityTaxDivNext());
           
           // ���E��c�d�q��t�E��������o�^Params.�i�����j��������@@�J�ݓ� �� (����)�e��A���s.�e�L�X�g�R
           // equity_sp_acc_open_dat �i�����j��������@@�J�ݓ�
           assertEquals("20070921",l_params.getEquitySpAccOpenDat());
           
           // setMarginTaxDiv
           assertEquals("1",l_params.getMarginTaxDiv());
           
           // setMarginTaxDivNext
           assertEquals("1",l_params.getMarginTaxDivNext());
           
           // ���E��c�d�q��t�E��������o�^Params.�i�M�p�j��������@@�J�ݓ� �� (����)�e��A���s.�e�L�X�g�S
           // setMarginSpAccOpenDat �i�M�p�j��������@@�J�ݓ� 
           assertEquals("20070920",l_params.getMarginSpAccOpenDat());
           
           // sp_mng_acc_open_div
           assertEquals("0",l_params.getSpMngAccOpenDiv());
           
           
       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   /**
    * ����ԉ�
    * ���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@��� != null
    * ���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�d�q��t�i�s�x�j!= null
    * ���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�a��� != null
    * ���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�v�Z�� != null
    * ���E��c�d�q��t�E��������o�^Params.�d�q��t�@@����񍐏� != null
    * (����)�e��A���s.�敪�V = 3�F ����E���򂠂� 
    * ���E��c�d�q��t�E��������o�^Params.�i�����j��������@@���� != null
    * (����)�e��A���s.�敪8 = 3�F ����E����Ȃ�
    * ���E��c�d�q��t�E��������o�^Params.�i�����j��������@@���� != null
    * (����)�e��A���s.�敪9 = 3�F ����E����Ȃ�
    * ���E��c�d�q��t�E��������o�^Params.�i�M�p�j��������@@���� != null
    * (����)�e��A���s.�敪10 = 3�F ����E����Ȃ�
    * ���E��c�d�q��t�E��������o�^Params.����Ǘ����� != null
    *
    */
   public void testCreateHostConditionRegVoucherParams_C0006()
   {
       String STR_METHOD_NAME = "testCreateHostConditionRegVoucherParams_C0006()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {
           MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
           // ����c���񍐏��쐬�����敪
           l_mainAccountParams.setPositionReportCycleDiv("0");
           // ����c���񍐏���t�敪
           l_mainAccountParams.setPositionReportDiv("1");
           // ����c���񍐏��a��؍쐬�t���O
           l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.FALSE);
           //  ����c���񍐏��v�Z���쐬�t���O  account_statement_flag
           l_mainAccountParams.setAccountStatementFlag(BooleanEnum.TRUE);
           // ����񍐏���t�敪 trading_report_div
           l_mainAccountParams.setTradingReportDiv("0");
           l_mainAccountParams.setTaxType(TaxTypeEnum.NORMAL);
           // tax_type_next
           l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.NORMAL);
           // margin_tax_type
           l_mainAccountParams.setMarginTaxType(TaxTypeEnum.NORMAL);
           
           l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.NORMAL);
           
           // sp_mng_acc_open_div
           l_mainAccountParams.setSpMngAccOpenDiv("1");
           VariousInformParams l_variousInformParams = new VariousInformParams();
           // ���E��c�d�q��t�E��������o�^Params.�،���ЃR�[�h �� (����)�e��A���s.�،���ЃR�[�h
           l_variousInformParams.institution_code = "0D";
           // ���E��c�d�q��t�E��������o�^Params.���X�R�[�h �� (����)�e��A���s.���X�R�[�h
           l_variousInformParams.branch_code = "381";
           // ���E��c�d�q��t�E��������o�^Params.�ڋq�R�[�h �� (����)�e��A���s.�ڋq�R�[�h
           l_variousInformParams.account_code = "123456";
           l_variousInformParams.ext_text3 ="20070921";
           l_variousInformParams.ext_text4 ="20070920";
           l_variousInformParams.ext_div4 = "1";
           l_variousInformParams.ext_div3 = "0";
           l_variousInformParams.ext_div5 = "1";
           l_variousInformParams.ext_div6 = "0";
           l_variousInformParams.ext_div2 = "1";
           
           l_variousInformParams.ext_div7 = "3";
           l_variousInformParams.ext_div8 = "3";
           l_variousInformParams.ext_div9 = "3";
           l_variousInformParams.ext_div10 = "3";
           
           l_variousInformParams.ext_div11 = "0";
           
           
           WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
           HostConditionRegVoucherParams l_params = 
               this.l_serviceImpl.createHostConditionRegVoucherParams(l_mainAccount,l_variousInformParams);
           
           assertEquals("0D",l_params.getInstitutionCode());
           assertEquals("381",l_params.getBranchCode());
           assertEquals("123456",l_params.getAccountCode());

           
           // ����c���񍐏��@@��� pos_report_term_div
           assertEquals("1",l_params.getPosReportTermDiv());
           // ����c���񍐏��@@�d�q��t�i�s�x�jpos_report_cycle_div
           assertEquals("0",l_params.getPosReportCycleDiv());           
           // ����c���񍐏��@@�a��� pos_report_certif_depo_div
           assertEquals("1",l_params.getPosReportCertifDepoDiv()); 
           assertEquals("0",l_params.getPosReportAccStateDiv());
           // trading_e_report_div
           assertEquals("1",l_params.getTradingEReportDiv());

           // �i�����j��������@@����
           assertEquals("2",l_params.getEquityTaxDiv());
           
           // �i�����j��������@@����
           // setEquityTaxDivNext
           assertEquals("2",l_params.getEquityTaxDivNext());
           
           // ���E��c�d�q��t�E��������o�^Params.�i�����j��������@@�J�ݓ� �� (����)�e��A���s.�e�L�X�g�R
           // equity_sp_acc_open_dat �i�����j��������@@�J�ݓ�
           assertEquals("20070921",l_params.getEquitySpAccOpenDat());
           
           // setMarginTaxDiv
           assertEquals("2",l_params.getMarginTaxDiv());
           
           // setMarginTaxDivNext
           assertEquals("2",l_params.getMarginTaxDivNext());
           
           // ���E��c�d�q��t�E��������o�^Params.�i�M�p�j��������@@�J�ݓ� �� (����)�e��A���s.�e�L�X�g�S
           // setMarginSpAccOpenDat �i�M�p�j��������@@�J�ݓ� 
           assertEquals("20070920",l_params.getMarginSpAccOpenDat());
           
           // sp_mng_acc_open_div
           assertEquals("0",l_params.getSpMngAccOpenDiv());
           
           
       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   //getVariousInformList
   public void testGetVariousInformListCase1()
   {
       String STR_METHOD_NAME = "testGetVariousInformListCase1()";
       log.entering(TEST_START + STR_METHOD_NAME); 
       try
       {
           VariousInformParams l_VariousInformParams = TestDBUtility.getVariousInformRow();
           //�،���ЃR�[�h�F ����.�،���ЃR�[�h
           //�A����ʁF ����.�A�����
           //���X�R�[�h�F ����.���X�R�[�h
           //�ڋq�R�[�h�F ����.�ڋq�R�[�h
           l_VariousInformParams.setInstitutionCode("10");
           l_VariousInformParams.setInformDiv("1");
           l_VariousInformParams.setBranchCode("101");
           l_VariousInformParams.setAccountCode("789");
           
           TestDBUtility.deleteAll(VariousInformRow.TYPE);
           TestDBUtility.insertWithDel(l_VariousInformParams);
           
           List l_lis = this.l_serviceImpl.getVariousInformList("10", "101", "1", "789");
           
           assertEquals(l_lis.size(), 1);
           assertEquals(((VariousInformParams)l_lis.get(0)).getRequestNumber(), "123");
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   public void testGetVariousInformListCase2()
   {
       String STR_METHOD_NAME = "testGetVariousInformListCase2()";
       log.entering(TEST_START + STR_METHOD_NAME); 
       try
       {
           VariousInformParams l_VariousInformParams = TestDBUtility.getVariousInformRow();
           VariousInformParams l_VariousInformParams1 = TestDBUtility.getVariousInformRow();
           VariousInformParams l_VariousInformParams2 = TestDBUtility.getVariousInformRow();
           //�،���ЃR�[�h�F ����.�،���ЃR�[�h
           //�A����ʁF ����.�A�����
           //���X�R�[�h�F ����.���X�R�[�h
           //�ڋq�R�[�h�F ����.�ڋq�R�[�h
           l_VariousInformParams.setInstitutionCode("10");
           l_VariousInformParams.setInformDiv("1");
           l_VariousInformParams.setBranchCode("101");
           l_VariousInformParams.setAccountCode("789");
           l_VariousInformParams.setRequestNumber("123");
           l_VariousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
           
           l_VariousInformParams1.setInstitutionCode("10");
           l_VariousInformParams1.setInformDiv("1");
           l_VariousInformParams1.setBranchCode("101");
           l_VariousInformParams1.setAccountCode("789");
           l_VariousInformParams1.setRequestNumber("1234");
           l_VariousInformParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070609","yyyyMMdd"));
           
           l_VariousInformParams2.setInstitutionCode("10");
           l_VariousInformParams2.setInformDiv("1");
           l_VariousInformParams2.setBranchCode("101");
           l_VariousInformParams2.setAccountCode("789");
           l_VariousInformParams2.setRequestNumber("12345");
           l_VariousInformParams2.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070606","yyyyMMdd"));
           
           TestDBUtility.deleteAll(VariousInformRow.TYPE);
           TestDBUtility.insertWithDel(l_VariousInformParams);
           TestDBUtility.insertWithDel(l_VariousInformParams1);
           TestDBUtility.insertWithDel(l_VariousInformParams2);
           
           List l_lis = this.l_serviceImpl.getVariousInformList("10", "101", "1", "789");
           
           assertEquals(l_lis.size(), 3);
           assertEquals(((VariousInformParams)l_lis.get(0)).getRequestNumber(), "1234");
           assertEquals(((VariousInformParams)l_lis.get(1)).getRequestNumber(), "123");
           assertEquals(((VariousInformParams)l_lis.get(2)).getRequestNumber(), "12345");
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   public void testGetVariousInformListCase3()
   {
       String STR_METHOD_NAME = "testGetVariousInformListCase3()";
       log.entering(TEST_START + STR_METHOD_NAME); 
       try
       {
           VariousInformParams l_VariousInformParams = TestDBUtility.getVariousInformRow();
           //�،���ЃR�[�h�F ����.�،���ЃR�[�h
           //�A����ʁF ����.�A�����
           //���X�R�[�h�F ����.���X�R�[�h
           //�ڋq�R�[�h�F ����.�ڋq�R�[�h
           l_VariousInformParams.setInstitutionCode("10");
           l_VariousInformParams.setInformDiv("1");
           l_VariousInformParams.setBranchCode("102");
           l_VariousInformParams.setAccountCode("789");
           
           TestDBUtility.deleteAll(VariousInformRow.TYPE);
           TestDBUtility.insertWithDel(l_VariousInformParams);
           
           List l_lis = this.l_serviceImpl.getVariousInformList("10", "101", "1", "789");
           
           assertNull(l_lis);
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   //validate�`�[���
   //����.�g���K�[���s�敪 == true
   public void testValidateVoucherCancelCase1()
   {
       String STR_METHOD_NAME = "testValidateVoucherCancelCase1()";
       log.entering(TEST_START + STR_METHOD_NAME); 
       try
       {
           this.l_serviceImpl.validateVoucherCancel("1", true);
           fail();
       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           assertEquals(WEB3BusinessLayerException.class,l_ex.getClass());
           assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02798,l_ex.getErrorInfo());
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   //����.�쐬�� != 1�F�쐬��
   public void testValidateVoucherCancelCase2()
   {
       String STR_METHOD_NAME = "testValidateVoucherCancelCase2()";
       log.entering(TEST_START + STR_METHOD_NAME); 
       try
       {
           this.l_serviceImpl.validateVoucherCancel("0", false);
           fail();
       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           assertEquals(WEB3BusinessLayerException.class,l_ex.getClass());
           assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02798,l_ex.getErrorInfo());
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   public void testValidateVoucherCancelCase3()
   {
       String STR_METHOD_NAME = "testValidateVoucherCancelCase3()";
       log.entering(TEST_START + STR_METHOD_NAME); 
       try
       {
           this.l_serviceImpl.validateVoucherCancel("1", false);
           
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   //validate�`�[�ύX
   //����.�g���K�[���s�敪 == true
   //����.�쐬�󋵂��A0�F���쐬�A4�F��t�G���[ �̂�����ł��Ȃ�
   public void testValidateVoucherChangeCase1()
   {
       String STR_METHOD_NAME = "testValidateVoucherChangeCase1()";
       log.entering(TEST_START + STR_METHOD_NAME); 
       try
       {
           this.l_serviceImpl.validateVoucherChange("1", true);
           fail();
       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           assertEquals(WEB3BusinessLayerException.class,l_ex.getClass());
           assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02786,l_ex.getErrorInfo());
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   //����.�쐬�󋵂��A0�F���쐬
   public void testValidateVoucherChangeCase2()
   {
       String STR_METHOD_NAME = "testValidateVoucherChangeCase2()";
       log.entering(TEST_START + STR_METHOD_NAME); 
       try
       {
           this.l_serviceImpl.validateVoucherChange("0", true);
           
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   //����.�쐬�󋵂�4�F��t�G���[
   public void testValidateVoucherChangeCase3()
   {
       String STR_METHOD_NAME = "testValidateVoucherChangeCase3()";
       log.entering(TEST_START + STR_METHOD_NAME); 
       try
       {
           this.l_serviceImpl.validateVoucherChange("4", true);
           
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   //����.�g���K�[���s�敪 == false ����.�쐬�󋵂��A0�F���쐬�A1�F�쐬�ς݁A4�F��t�G���[ �̂�����ł��Ȃ�
   public void testValidateVoucherChangeCase4()
   {
       String STR_METHOD_NAME = "testValidateVoucherChangeCase4()";
       log.entering(TEST_START + STR_METHOD_NAME); 
       try
       {
           this.l_serviceImpl.validateVoucherChange("2", false);
           fail();
       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           assertEquals(WEB3BusinessLayerException.class,l_ex.getClass());
           assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02787,l_ex.getErrorInfo());
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   //����.�쐬�󋵂��A0�F���쐬
   public void testValidateVoucherChangeCase5()
   {
       String STR_METHOD_NAME = "testValidateVoucherChangeCase5()";
       log.entering(TEST_START + STR_METHOD_NAME); 
       try
       {
           this.l_serviceImpl.validateVoucherChange("0", true);
           
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   //����.�쐬�󋵂�1�F�쐬�ς�
   public void testValidateVoucherChangeCase6()
   {
       String STR_METHOD_NAME = "testValidateVoucherChangeCase6()";
       log.entering(TEST_START + STR_METHOD_NAME); 
       try
       {
           this.l_serviceImpl.validateVoucherChange("1", false);
           
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   //����.�쐬�󋵂�4�F��t�G���[ 
   public void testValidateVoucherChangeCase7()
   {
       String STR_METHOD_NAME = "testValidateVoucherChangeCase7()";
       log.entering(TEST_START + STR_METHOD_NAME); 
       try
       {
           this.l_serviceImpl.validateVoucherChange("4", false);
           
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }

   /**
    * �쐬��=2�F��t��
    * ����.�g���K�[���s�敪 == true �̏ꍇ
    * 
    * �e�o�ُ�M���FBUSINESS_ERROR_02786
    *
    */
   public void testValidateVoucherMake_C0001()
   {
       String STR_METHOD_NAME = "testSubmitMarketTrigger_C0001()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {
           String l_strMakeStatus = "2"; 
           boolean l_blnSubmitMarketTriggerDiv = true;
           this.l_serviceImpl.validateVoucherMake(l_strMakeStatus,l_blnSubmitMarketTriggerDiv);
           fail();
       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02786,l_ex.getErrorInfo());
           log.exiting(TEST_END + STR_METHOD_NAME);
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   /**
    * �쐬��=1�F�쐬��
    * ����.�g���K�[���s�敪 == false �̏ꍇ
    * 
    * �e�o�ُ�M���FBUSINESS_ERROR_02787
    *
    */
   public void testValidateVoucherMake_C0002()
   {
       String STR_METHOD_NAME = "testSubmitMarketTrigger_C0002()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {
           String l_strMakeStatus = "1"; 
           boolean l_blnSubmitMarketTriggerDiv = false;
           this.l_serviceImpl.validateVoucherMake(l_strMakeStatus,l_blnSubmitMarketTriggerDiv);
           fail();
       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02787,l_ex.getErrorInfo());
           log.exiting(TEST_END + STR_METHOD_NAME);
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   /**
    * �쐬��=2�F��t��
    * ����.�g���K�[���s�敪 == false �̏ꍇ
    * 
    * ���팋��
    *
    */
   public void testValidateVoucherMake_C0003()
   {
       String STR_METHOD_NAME = "testSubmitMarketTrigger_C0003()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {
           String l_strMakeStatus = "2"; 
           boolean l_blnSubmitMarketTriggerDiv = false;
           this.l_serviceImpl.validateVoucherMake(l_strMakeStatus,l_blnSubmitMarketTriggerDiv);
           
       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   /**
    * �J�n�\��� == null
    * 
    * �e�o�ُ�M���FSYSTEM_ERROR_80017
    *
    */
   public void testValidateVoucherMake1_C0001()
   {
       String STR_METHOD_NAME = "testValidateVoucherMake1_C0001()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {
           String l_strMakeStatus = "2";
           boolean l_blnSubmitMarketTriggerDiv = true;
           Date l_datStartScheduleDate = null;
           this.l_serviceImpl.validateVoucherMake(l_strMakeStatus,l_blnSubmitMarketTriggerDiv,l_datStartScheduleDate);
           fail();
       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
           log.exiting(TEST_END + STR_METHOD_NAME);
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   /**
    * ����.�g���K�[���s�敪 == true �̏ꍇ
    * �쐬��=1�F�쐬��
    * 
    * �e�o�ُ�M���FBUSINESS_ERROR_02786
    *
    */
   public void testValidateVoucherMake1_C0002()
   {
       String STR_METHOD_NAME = "testValidateVoucherMake1_C0002()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {
           String l_strMakeStatus = "1";
           boolean l_blnSubmitMarketTriggerDiv = true;
           
           Calendar l_calendar = Calendar.getInstance();
           l_calendar.set(2010,6,9);
           Date l_datStartScheduleDate = l_calendar.getTime();
           
           this.l_serviceImpl.validateVoucherMake(l_strMakeStatus,l_blnSubmitMarketTriggerDiv,l_datStartScheduleDate);
           fail();
       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02786,l_ex.getErrorInfo());
           log.exiting(TEST_END + STR_METHOD_NAME);
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   /**
    * ����.�g���K�[���s�敪 == false �̏ꍇ
    * �쐬��=1�F�쐬��
    * 
    * �e�o�ُ�M���FBUSINESS_ERROR_02787
    *
    */
   public void testValidateVoucherMake1_C0003()
   {
       String STR_METHOD_NAME = "testValidateVoucherMake1_C0003()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {
           String l_strMakeStatus = "1";
           boolean l_blnSubmitMarketTriggerDiv = false;
           
           Calendar l_calendar = Calendar.getInstance();
           l_calendar.set(2010,6,9);
           Date l_datStartScheduleDate = l_calendar.getTime();
           
           this.l_serviceImpl.validateVoucherMake(l_strMakeStatus,l_blnSubmitMarketTriggerDiv,l_datStartScheduleDate);
           fail();
       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02787,l_ex.getErrorInfo());
           log.exiting(TEST_END + STR_METHOD_NAME);
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   /**
    * ����.�g���K�[���s�敪 == false �̏ꍇ
    * �쐬��= 2�F��t��
    * 
    * ���팋��
    *
    */
   public void testValidateVoucherMake1_C0004()
   {
       String STR_METHOD_NAME = "testValidateVoucherMake1_C0004()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {
           String l_strMakeStatus = "2";
           boolean l_blnSubmitMarketTriggerDiv = false;
           
           Calendar l_calendar = Calendar.getInstance();
           l_calendar.set(2010,6,9);
           Date l_datStartScheduleDate = l_calendar.getTime();
           
           this.l_serviceImpl.validateVoucherMake(l_strMakeStatus,l_blnSubmitMarketTriggerDiv,l_datStartScheduleDate);
       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   /**
    * 
    * �g���K�[���s�敪 = true
    * �쐬��= 2�F��t�� ������.�J�n�\��� >= ���ݓ��t
    * �e�o�ُ�M���FBUSINESS_ERROR_02786
    *
    */
   public void testValidateVoucherMake1_C0005()
   {
       String STR_METHOD_NAME = "testValidateVoucherMake1_C0005()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {
           String l_strMakeStatus = "2";
           boolean l_blnSubmitMarketTriggerDiv = true;
           
//           Calendar l_calendar = Calendar.getInstance();
//           l_calendar.set(2010,6,9);
//           Date l_datStartScheduleDate = l_calendar.getTime();
           
           Date l_datStartScheduleDate = GtlUtils.getSystemTimestamp();
           
           this.l_serviceImpl.validateVoucherMake(l_strMakeStatus,l_blnSubmitMarketTriggerDiv,l_datStartScheduleDate);
           fail();
       }
       catch(WEB3BaseException l_ex)
       {
           log.error(""+l_ex);
           assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02786,l_ex.getErrorInfo());
           log.exiting(TEST_END + STR_METHOD_NAME);
           
       }
       catch(Exception l_ex)
       {
           log.error(""+l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
   
   public EraParams getEraParams()
   {
       EraParams l_eraParams = new EraParams();
       //�N���敪    japanese_era_div    NUMBER    1    NotNull    1�F�����A2�F�吳�A3�F���a�A4�F�����A9�F�s��
       l_eraParams.setJapaneseEraDiv(1);
       //�N��    japanese_era    VARCHAR2    20    NotNull    1�F�����A2�F�吳�A3�F���a�A4�F�����A9�F�s��
       l_eraParams.setJapaneseEra("1");
       //�J�n�N(�a��)    start_year_jap    VARCHAR2    2    NotNull    �a��(YY)
       l_eraParams.setStartYearJap("01");
       //�J�n�N(����)    start_year    VARCHAR2    4    NotNull    ����(YYYY)
       l_eraParams.setStartYear("2008");
       //�J�n����    start_date    VARCHAR2    4    NotNull    MMDD
       l_eraParams.setStartDate("0002");
       //�I���N(�a��)    end_year_jap    VARCHAR2    2    NotNull    �a��(YY)
       l_eraParams.setEndYearJap("02");
       //�I���N(����)    end_year    VARCHAR2    4    NotNull    ����(YYYY)
       l_eraParams.setEndYear("2009");
       //�I������    end_date    VARCHAR2    4    NotNull    MMDD
       l_eraParams.setEndDate("0102");
       //�쐬����    created_timestamp    DATE        NotNull    DEFAULT sysdate
       l_eraParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
       //�X�V����    last_updated_timestamp    DATE        NotNull    DEFAULT sysdate
       l_eraParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
       return l_eraParams;
   }
}
@
