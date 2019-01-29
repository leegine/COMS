head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.28.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPAssetTradingPowerServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3TPAssetTradingPowerServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/08 �����i���u�j�V�K�쐬
*/
package webbroker3.tradingpower.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.tradingpower.WEB3ForeignPositionContract;
import webbroker3.tradingpower.WEB3TPAdddepositGenerationInfo;
import webbroker3.tradingpower.WEB3TPAdddepositInfo;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.WEB3TPFirstAdddepositInfo;
import webbroker3.tradingpower.WEB3TPSecondAdddepositInfo;
import webbroker3.tradingpower.WEB3TPShortfallOccurInfo;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcEquity;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcMargin;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailRow;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.data.TpCalcResultMarginRow;
import webbroker3.tradingpower.data.TpCashBalanceFrgnParams;
import webbroker3.tradingpower.message.WEB3TPAdditionalGenerationRequest;
import webbroker3.tradingpower.message.WEB3TPAdditionalGenerationResponse;
import webbroker3.tradingpower.message.WEB3TPAssetRequest;
import webbroker3.tradingpower.message.WEB3TPAssetResponse;
import webbroker3.tradingpower.message.WEB3TPEquityTradingPowerDetailRequest;
import webbroker3.tradingpower.message.WEB3TPEquityTradingPowerDetailResponse;
import webbroker3.tradingpower.message.WEB3TPMarginTradingPowerDetailRequest;
import webbroker3.tradingpower.message.WEB3TPMarginTradingPowerDetailResponse;
import webbroker3.tradingpower.message.WEB3TPShortfallGenerationRequest;
import webbroker3.tradingpower.message.WEB3TPShortfallGenerationResponse;
import webbroker3.tradingpower.message.WEB3TPTransitionReferenceUnit;
import webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPAssetTradingPowerServiceImpl;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPAssetTradingPowerServiceImplTest  extends TestBaseForMock
{

    private WEB3TPAssetTradingPowerServiceImpl l_serviceImpl = null;
    
    private WEB3TPAssetRequest l_request = null;
    
    private TpCashBalanceFrgnParams l_tpCashBalanceFrgnParams = null;
    
    private WEB3ForeignPositionContract l_position = null;
    
    private boolean l_blnIsMarginAccount = false;
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3TPAssetTradingPowerServiceImplTest.class);
    
    public WEB3TPAssetTradingPowerServiceImplTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.l_serviceImpl = new WEB3TPAssetTradingPowerServiceImpl();
        this.l_request = new WEB3TPAssetRequest();
        this.l_position = new WEB3ForeignPositionContract();
    }

    protected void tearDown() throws Exception
    {
//        this.validateMethodParams();
//        super.checkMethodValue();
        this.l_serviceImpl = null;
        this.l_request = null;
        this.l_position = null;
        this.l_blnIsMarginAccount = false;
        super.tearDown();
    }
    
    /**
     * �����ڋq�̏ꍇ
     * get�O�ݎc��<�h��>(T+0)��null�̏ꍇ(��c�p)
     * get�O�ݎc��<���[��>(T+0)��null�̏ꍇ(��c�p)
     * get�O�ݎc��<�h��>(T+5)��null�̏ꍇ(��c�p)
     * get�O�ݎc��<���[��>(T+5)��null�̏ꍇ(��c�p)
     * �O�������]���z<��c>
     */
    public void testCreateAssetResponse_C0001()
    {
        final String STR_METHOD_NAME = " testCreateAssetResponse_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.initData();
            this.setMockMethod();
            WEB3TPAssetResponse l_response = this.l_serviceImpl.createAssetResponse(this.l_request);

            // get�O�ݎc��<�h��>(T+0)��null�̏ꍇ(��c�p)
            assertNull(l_response.assetUnits[0].accountBalanceDollar);
            // get�O�ݎc��<���[��>(T+0)��null�̏ꍇ(��c�p)
            assertNull(l_response.assetUnits[0].accountBalanceEuro);
            // get�O�ݎc��<�h��>(T+5)��null�̏ꍇ(��c�p)
            assertNull(l_response.assetUnits[1].accountBalanceDollar);
            // get�O�ݎc��<���[��>(T+5)��null�̏ꍇ(��c�p)
            assertNull(l_response.assetUnits[1].accountBalanceEuro);
            //
            assertEquals("1000", "" + l_response.assetUnits[0].feqAsset);
            assertEquals("6500", "" + l_response.assetUnits[0].totalAsset);

            assertEquals("2000", "" + l_response.assetUnits[1].feqAsset);
            assertEquals("5000", "" + l_response.assetUnits[1].totalAsset);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �����ڋq�̏ꍇ
     * get�O�ݎc��<�h��>(T+0)��null�ȊO�̏ꍇ(��c�p)
     * get�O�ݎc��<���[��>(T+0)��null�ȊO�̏ꍇ(��c�p)
     * get�O�ݎc��<�h��>(T+5)��null�ȊO�̏ꍇ(��c�p)
     * get�O�ݎc��<���[��>(T+5)��null�ȊO�̏ꍇ(��c�p)
     * 
     * �����Ycase���Cᢌ�bug�F����I��?�F��Y�����g��c�p�h�C���s���g��c�p�h
     * l_assetUnit1.accountBalanceDollar = format(l_accountBalanceDollarT5.doubleValue());��Y��ਁF
     * l_assetUnit2.accountBalanceDollar = format(l_accountBalanceDollarT5.doubleValue());
     * 
     *
     */
    public void testCreateAssetResponse_C0002()
    {
        final String STR_METHOD_NAME = " testCreateAssetResponse_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.initData();
            this.l_tpCashBalanceFrgnParams = new TpCashBalanceFrgnParams();
            this.l_tpCashBalanceFrgnParams.setCashBalanceFrgn0(1250);
            this.l_tpCashBalanceFrgnParams.setCashBalanceFrgn5(1255);
            this.setMockMethod();

            WEB3TPAssetResponse l_response = this.l_serviceImpl.createAssetResponse(this.l_request);
            
            // get�O�ݎc��<�h��>(T+0)��null�ȊO�̏ꍇ(��c�p)
            assertEquals("1250",l_response.assetUnits[0].accountBalanceDollar);
            // get�O�ݎc��<���[��>(T+0)��null�̏ꍇ(��c�p)
            assertEquals("1250",l_response.assetUnits[0].accountBalanceEuro);
            // get�O�ݎc��<�h��>(T+5)��null�̏ꍇ(��c�p)
            assertEquals("1255",l_response.assetUnits[1].accountBalanceDollar);
            // get�O�ݎc��<���[��>(T+5)��null�̏ꍇ(��c�p)
            assertEquals("1255",l_response.assetUnits[1].accountBalanceEuro);
            

            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �M�p�ڋq�̏ꍇ
     * get�O�ݎc��<�h��>(T+0)��null�̏ꍇ(��c�p)
     * get�O�ݎc��<���[��>(T+0)��null�̏ꍇ(��c�p)
     * get�O�ݎc��<�h��>(T+5)��null�̏ꍇ(��c�p)
     * get�O�ݎc��<���[��>(T+5)��null�̏ꍇ(��c�p)
     * ���obug�F�R����
     * 
     *
     */
    public void testCreateAssetResponse_C0003()
    {
        final String STR_METHOD_NAME = " testCreateAssetResponse_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            this.l_blnIsMarginAccount = true;
            this.initData();
            this.setMockMethod();
            WEB3TPAssetResponse l_response = this.l_serviceImpl.createAssetResponse(this.l_request);
            
            // get�O�ݎc��<�h��>(T+0)��null�̏ꍇ(��c�p)
            assertNull(l_response.assetUnits[0].accountBalanceDollar);
            // get�O�ݎc��<���[��>(T+0)��null�̏ꍇ(��c�p)
            assertNull(l_response.assetUnits[0].accountBalanceEuro);
            // get�O�ݎc��<�h��>(T+5)��null�̏ꍇ(��c�p)
            assertNull(l_response.assetUnits[1].accountBalanceDollar);
            // get�O�ݎc��<���[��>(T+5)��null�̏ꍇ(��c�p)
            assertNull(l_response.assetUnits[1].accountBalanceEuro);
            
            assertEquals("2340", "" + l_response.assetUnits[0].feqAsset);
            assertEquals("2340", "" + l_response.assetUnits[0].totalAsset);

            assertEquals("1234", "" + l_response.assetUnits[1].feqAsset);
            assertEquals("1234", "" + l_response.assetUnits[1].totalAsset);
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �M�p�ڋq�̏ꍇ
     * get�O�ݎc��<�h��>(T+0)��null�ȊO�̏ꍇ(��c�p)
     * get�O�ݎc��<���[��>(T+0)��null�ȊOl�̏ꍇ(��c�p)
     * get�O�ݎc��<�h��>(T+5)��null�ȊO�̏ꍇ(��c�p)
     * get�O�ݎc��<���[��>(T+5)��null�ȊO�̏ꍇ(��c�p)
     * 
     *
     */
    public void testCreateAssetResponse_C0004()
    {
        final String STR_METHOD_NAME = " testCreateAssetResponse_C0004";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            this.l_blnIsMarginAccount = true;
            this.initData();
            this.l_tpCashBalanceFrgnParams = new TpCashBalanceFrgnParams();
            this.l_tpCashBalanceFrgnParams.setCashBalanceFrgn0(1250);
            this.l_tpCashBalanceFrgnParams.setCashBalanceFrgn5(1255);
            this.setMockMethod();
            WEB3TPAssetResponse l_response = this.l_serviceImpl.createAssetResponse(this.l_request);
            
            // get�O�ݎc��<�h��>(T+0)��null�ȊO�̏ꍇ(��c�p)
            assertEquals("1250",l_response.assetUnits[0].accountBalanceDollar);
            // get�O�ݎc��<���[��>(T+0)��null�̏ꍇ(��c�p)
            assertEquals("1250",l_response.assetUnits[0].accountBalanceEuro);
            // get�O�ݎc��<�h��>(T+5)��null�̏ꍇ(��c�p)
            assertEquals("1255",l_response.assetUnits[1].accountBalanceDollar);
            // get�O�ݎc��<���[��>(T+5)��null�̏ꍇ(��c�p)
            assertEquals("1255",l_response.assetUnits[1].accountBalanceEuro);
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * ���������ʂ�0���̏ꍇ�A�G���[��ԋp����B
     * WEB3ErrorCatalog.BUSINESS_ERROR_01037
     */
    public void testGetTradingpowerCalcConditionParams_C0001()
    {
        final String STR_METHOD_NAME = " testGetTradingpowerCalcConditionParams_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            Long l_accountId = new Long(55464654654L);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            
            Method l_method = WEB3TPAssetTradingPowerServiceImpl.class.getDeclaredMethod(
                    "getTradingpowerCalcConditionParams",
                    new Class[]{Long.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_accountId};
            
            l_method.invoke(l_serviceImpl, l_obj);
            
            fail();
        }
        catch (InvocationTargetException l_exITE)
        {
            log.error(STR_METHOD_NAME, l_exITE);
            WEB3BusinessLayerException l_targetException =(WEB3BusinessLayerException)l_exITE.getTargetException();
            
            assertEquals(WEB3BusinessLayerException.class,
                    l_exITE.getTargetException().getClass());

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_targetException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testGetTradingpowerCalcConditionParams_C0002()
    {
        final String STR_METHOD_NAME = " testGetTradingpowerCalcConditionParams_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            Long l_accountId = new Long(55464654654L);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(55464654654L);
            l_tradingpowerCalcConditionParams.setBranchId(1234567890);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            Method l_method = WEB3TPAssetTradingPowerServiceImpl.class.getDeclaredMethod(
                    "getTradingpowerCalcConditionParams",
                    new Class[]{Long.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_accountId};
            
            TradingpowerCalcConditionParams l_returnParams =
                (TradingpowerCalcConditionParams)l_method.invoke(l_serviceImpl, l_obj);
            
            assertEquals(1234567890, l_returnParams.getBranchId());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * �����x�������t���O�@@= false
     * (get�ڋq�]�͏���Params()�̖߂�l.�����~�敪 = "0")
     */
    public void testCreateShortfallGenerationScreen_C0001()
    {
        final String STR_METHOD_NAME = " testCreateShortfallGenerationScreen_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(false));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getLackCashOccurSituation", new Class[]
                { MainAccount.class },
                "0");
        
        WEB3TPShortfallOccurInfo l_shortfallOccurInfo = new WEB3TPShortfallOccurInfo();
        //�s�����������.����(T+0)
        l_shortfallOccurInfo.closeDate0 = WEB3DateUtility.getDate("20080801","yyyyMMdd");
        //�s�����������.����(T+1)
        l_shortfallOccurInfo.closeDate1 = WEB3DateUtility.getDate("20080802","yyyyMMdd");
        //�s�����������.����(T+2)
        l_shortfallOccurInfo.closeDate2 = WEB3DateUtility.getDate("20080803","yyyyMMdd");
        //�s�����������.����(T+3)
        l_shortfallOccurInfo.closeDate3 = WEB3DateUtility.getDate("20080804","yyyyMMdd");
        //�s�����������.����(T+4)
        l_shortfallOccurInfo.closeDate4 = WEB3DateUtility.getDate("20080805","yyyyMMdd");
        //�s�����������.����(T+5)
        l_shortfallOccurInfo.closeDate5 = WEB3DateUtility.getDate("20080806","yyyyMMdd");

        //�s�����������.�����K�v�z(T+0)
        l_shortfallOccurInfo.requiredPayAmt0 = 11;
        //�s�����������.�����K�v�z(T+1)
        l_shortfallOccurInfo.requiredPayAmt1 = 12;
        //�s�����������.�����K�v�z(T+2)
        l_shortfallOccurInfo.requiredPayAmt2 = 13;
        //�s�����������.�����K�v�z(T+3)
        l_shortfallOccurInfo.requiredPayAmt3 = 14;
        //�s�����������.�����K�v�z(T+4)
        l_shortfallOccurInfo.requiredPayAmt4 = 15;
        //�s�����������.�����K�v�z(T+5)
        l_shortfallOccurInfo.requiredPayAmt5 = 16;

        //�s�����������.���Z�z(T+0)
        l_shortfallOccurInfo.adjustedAmt0 = 21;
        //�s�����������.���Z�z(T+1)
        l_shortfallOccurInfo.adjustedAmt1 = 22;

        //�s�����������.���v��S����(T+0)
        l_shortfallOccurInfo.dayTradeRestraint0 = 31;
        //�s�����������.���v��S����(T+1)
        l_shortfallOccurInfo.dayTradeRestraint1 = 32;

        //�s�����������.�ۏ؋�����̐U�֊z(T+0)
        l_shortfallOccurInfo.transferFromMarginDeposit0 = 41;
        //�s�����������.�ۏ؋�����̐U�֊z(T+1)
        l_shortfallOccurInfo.transferFromMarginDeposit1 = 42;

        //�s�����������.�ۏ؋������U�֌㔻��t���O
        l_shortfallOccurInfo.depositAutoTransferDivFlag = true;
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getShortfallGenerationInfo", new Class[]
                { MainAccount.class },
                l_shortfallOccurInfo);
        
        try
        {
            WEB3TPShortfallGenerationRequest l_request = new WEB3TPShortfallGenerationRequest();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setTradingStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            WEB3TPShortfallGenerationResponse l_response = l_serviceImpl.createShortfallGenerationScreen(l_request);
            
            assertEquals(WEB3DateUtility.getDate("20080801","yyyyMMdd"), l_response.shortfallGenerationInfo.closeDate0);
            assertEquals(WEB3DateUtility.getDate("20080802","yyyyMMdd"), l_response.shortfallGenerationInfo.closeDate1);
            assertEquals(WEB3DateUtility.getDate("20080803","yyyyMMdd"), l_response.shortfallGenerationInfo.closeDate2);
            assertEquals(WEB3DateUtility.getDate("20080804","yyyyMMdd"), l_response.shortfallGenerationInfo.closeDate3);
            assertEquals(WEB3DateUtility.getDate("20080805","yyyyMMdd"), l_response.shortfallGenerationInfo.closeDate4);
            assertEquals(WEB3DateUtility.getDate("20080806","yyyyMMdd"), l_response.shortfallGenerationInfo.closeDate5);
            
            assertEquals("11", l_response.shortfallGenerationInfo.requiredPayAmt0);
            assertEquals("12", l_response.shortfallGenerationInfo.requiredPayAmt1);
            assertEquals("13", l_response.shortfallGenerationInfo.requiredPayAmt2);
            assertEquals("14", l_response.shortfallGenerationInfo.requiredPayAmt3);
            assertEquals("15", l_response.shortfallGenerationInfo.requiredPayAmt4);
            assertEquals("16", l_response.shortfallGenerationInfo.requiredPayAmt5);
            
            assertEquals("21", l_response.shortfallGenerationInfo.adjustedAmt0);
            assertEquals("22", l_response.shortfallGenerationInfo.adjustedAmt1);
            
            assertEquals("31", l_response.shortfallGenerationInfo.dayTradeRestraint0);
            assertEquals("32", l_response.shortfallGenerationInfo.dayTradeRestraint1);
            
            assertEquals("41", l_response.shortfallGenerationInfo.transferFromMarginDeposit0);
            assertEquals("42", l_response.shortfallGenerationInfo.transferFromMarginDeposit1);
            
            assertTrue(l_response.autoTransferAfterJudgeFlag);
            assertFalse(l_response.payDelayGenerationFlag);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * �����x�������t���O�@@= true
     * (get�ڋq�]�͏���Params()�̖߂�l.�����~�敪 = "1")
     */
    public void testCreateShortfallGenerationScreen_C0002()
    {
        final String STR_METHOD_NAME = " testCreateShortfallGenerationScreen_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(false));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getLackCashOccurSituation", new Class[]
                { MainAccount.class },
                "0");
        
        WEB3TPShortfallOccurInfo l_shortfallOccurInfo = new WEB3TPShortfallOccurInfo();
        //�s�����������.����(T+0)
        l_shortfallOccurInfo.closeDate0 = WEB3DateUtility.getDate("20080801","yyyyMMdd");
        //�s�����������.����(T+1)
        l_shortfallOccurInfo.closeDate1 = WEB3DateUtility.getDate("20080802","yyyyMMdd");
        //�s�����������.����(T+2)
        l_shortfallOccurInfo.closeDate2 = WEB3DateUtility.getDate("20080803","yyyyMMdd");
        //�s�����������.����(T+3)
        l_shortfallOccurInfo.closeDate3 = WEB3DateUtility.getDate("20080804","yyyyMMdd");
        //�s�����������.����(T+4)
        l_shortfallOccurInfo.closeDate4 = WEB3DateUtility.getDate("20080805","yyyyMMdd");
        //�s�����������.����(T+5)
        l_shortfallOccurInfo.closeDate5 = WEB3DateUtility.getDate("20080806","yyyyMMdd");

        //�s�����������.�����K�v�z(T+0)
        l_shortfallOccurInfo.requiredPayAmt0 = 11;
        //�s�����������.�����K�v�z(T+1)
        l_shortfallOccurInfo.requiredPayAmt1 = 12;
        //�s�����������.�����K�v�z(T+2)
        l_shortfallOccurInfo.requiredPayAmt2 = 13;
        //�s�����������.�����K�v�z(T+3)
        l_shortfallOccurInfo.requiredPayAmt3 = 14;
        //�s�����������.�����K�v�z(T+4)
        l_shortfallOccurInfo.requiredPayAmt4 = 15;
        //get�s�����������.�����K�v�z(T+5)
        l_shortfallOccurInfo.requiredPayAmt5 = 16;

        //�s�����������.���Z�z(T+0)
        l_shortfallOccurInfo.adjustedAmt0 = 21;
        //�s�����������.���Z�z(T+1)
        l_shortfallOccurInfo.adjustedAmt1 = 22;

        //�s�����������.���v��S����(T+0)
        l_shortfallOccurInfo.dayTradeRestraint0 = 31;
        //�s�����������.���v��S����(T+1)
        l_shortfallOccurInfo.dayTradeRestraint1 = 32;

        //�s�����������.�ۏ؋�����̐U�֊z(T+0)
        l_shortfallOccurInfo.transferFromMarginDeposit0 = 41;
        //�s�����������.�ۏ؋�����̐U�֊z(T+1)
        l_shortfallOccurInfo.transferFromMarginDeposit1 = 42;

        //�s�����������.�ۏ؋������U�֌㔻��t���O
        l_shortfallOccurInfo.depositAutoTransferDivFlag = false;
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getShortfallGenerationInfo", new Class[]
                { MainAccount.class },
                l_shortfallOccurInfo);
        
        try
        {
            WEB3TPShortfallGenerationRequest l_request = new WEB3TPShortfallGenerationRequest();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setTradingStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            WEB3TPShortfallGenerationResponse l_response = l_serviceImpl.createShortfallGenerationScreen(l_request);
            
            assertEquals(WEB3DateUtility.getDate("20080801","yyyyMMdd"), l_response.shortfallGenerationInfo.closeDate0);
            assertEquals(WEB3DateUtility.getDate("20080802","yyyyMMdd"), l_response.shortfallGenerationInfo.closeDate1);
            assertEquals(WEB3DateUtility.getDate("20080803","yyyyMMdd"), l_response.shortfallGenerationInfo.closeDate2);
            assertEquals(WEB3DateUtility.getDate("20080804","yyyyMMdd"), l_response.shortfallGenerationInfo.closeDate3);
            assertEquals(WEB3DateUtility.getDate("20080805","yyyyMMdd"), l_response.shortfallGenerationInfo.closeDate4);
            assertEquals(WEB3DateUtility.getDate("20080806","yyyyMMdd"), l_response.shortfallGenerationInfo.closeDate5);
            
            assertEquals("11", l_response.shortfallGenerationInfo.requiredPayAmt0);
            assertEquals("12", l_response.shortfallGenerationInfo.requiredPayAmt1);
            assertEquals("13", l_response.shortfallGenerationInfo.requiredPayAmt2);
            assertEquals("14", l_response.shortfallGenerationInfo.requiredPayAmt3);
            assertEquals("15", l_response.shortfallGenerationInfo.requiredPayAmt4);
            assertEquals("16", l_response.shortfallGenerationInfo.requiredPayAmt5);
            
            assertEquals("21", l_response.shortfallGenerationInfo.adjustedAmt0);
            assertEquals("22", l_response.shortfallGenerationInfo.adjustedAmt1);
            
            assertEquals("31", l_response.shortfallGenerationInfo.dayTradeRestraint0);
            assertEquals("32", l_response.shortfallGenerationInfo.dayTradeRestraint1);
            
            assertEquals("41", l_response.shortfallGenerationInfo.transferFromMarginDeposit0);
            assertEquals("42", l_response.shortfallGenerationInfo.transferFromMarginDeposit1);
            
            assertFalse(l_response.autoTransferAfterJudgeFlag);
            assertTrue(l_response.payDelayGenerationFlag);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * �Ǐؖ����������t���O�@@=�@@false
     * (get�ڋq�]�͏���Params().�Ǐؖ������敪 = "0")
     * get�Ǐؔ������().�Ǐ؏��@@==�@@NULL �̏ꍇ
     */
    public void testcreateAdditionalGenerationScreen_C0001()
    {
        final String STR_METHOD_NAME = " testcreateAdditionalGenerationScreen_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(false));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getAdditionalMarginSituation", new Class[]
                { MainAccount.class, boolean.class },
                "0");
        
        WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
        //�Ǐؔ������.�ۏ؋������U�֌㔻��t���O
        l_adddepositGenerationInfo.setDepositAutoTransferDivFlag(true);
        //�Ǐؔ������.�Ǐ؏��
        WEB3TPAdddepositInfo l_adddepositInfo = null;
        l_adddepositGenerationInfo.setAdddepositInfo(l_adddepositInfo);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getAdddepositGenerationInfo", new Class[]
                { MainAccount.class, boolean.class },
                l_adddepositGenerationInfo);
        
        try
        {
            WEB3TPAdditionalGenerationRequest l_request = new WEB3TPAdditionalGenerationRequest();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            WEB3TPAdditionalGenerationResponse l_response = l_serviceImpl.createAdditionalGenerationScreen(l_request);
            
            assertEquals("0", l_response.additionalGenerationStateDiv);
            assertTrue(l_response.autoTransferAfterJudgeFlag);
            assertFalse(l_response.additionalNonPayAmtFlag);
            assertNull(l_response.firstAdditionalInfo);
            assertNull(l_response.secondAdditionalInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * �Ǐؖ����������t���O�@@=�@@true
     * (get�ڋq�]�͏���Params().�Ǐؖ������敪 = "1")
     * get�Ǐؔ������().�Ǐ؏��@@==�@@NULL �̏ꍇ
     */
    public void testcreateAdditionalGenerationScreen_C0002()
    {
        final String STR_METHOD_NAME = " testcreateAdditionalGenerationScreen_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(false));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getAdditionalMarginSituation", new Class[]
                { MainAccount.class, boolean.class },
                "0");
        
        WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
        //�Ǐؔ������.�ۏ؋������U�֌㔻��t���O
        l_adddepositGenerationInfo.setDepositAutoTransferDivFlag(true);
        //�Ǐؔ������.�Ǐ؏��
        WEB3TPAdddepositInfo l_adddepositInfo = null;
        l_adddepositGenerationInfo.setAdddepositInfo(l_adddepositInfo);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getAdddepositGenerationInfo", new Class[]
                { MainAccount.class, boolean.class },
                l_adddepositGenerationInfo);
        
        try
        {
            WEB3TPAdditionalGenerationRequest l_request = new WEB3TPAdditionalGenerationRequest();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            WEB3TPAdditionalGenerationResponse l_response = l_serviceImpl.createAdditionalGenerationScreen(l_request);
            
            assertEquals("0", l_response.additionalGenerationStateDiv);
            assertTrue(l_response.autoTransferAfterJudgeFlag);
            assertTrue(l_response.additionalNonPayAmtFlag);
            assertNull(l_response.firstAdditionalInfo);
            assertNull(l_response.secondAdditionalInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * �Ǐؖ����������t���O�@@=�@@false
     * (get�ڋq�]�͏���Params().�Ǐؖ������敪 = "0")
     * get�Ǐؔ������().�Ǐ؏�� ����ꐅ���Ǐ؏��̃C���X�^���X�ł���ꍇ
     */
    public void testcreateAdditionalGenerationScreen_C0003()
    {
        final String STR_METHOD_NAME = " testcreateAdditionalGenerationScreen_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(false));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getAdditionalMarginSituation", new Class[]
                { MainAccount.class, boolean.class },
                "0");
        
        WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
        //�Ǐؔ������.�ۏ؋������U�֌㔻��t���O
        l_adddepositGenerationInfo.setDepositAutoTransferDivFlag(true);
        //�Ǐؔ������.�Ǐ؏��
        WEB3TPFirstAdddepositInfo l_adddepositInfo = new WEB3TPFirstAdddepositInfo();
        //��ꐅ���Ǐ؏��.�o�ߓ���
        l_adddepositInfo.firstDepositPassDay = 11;
        //��ꐅ���Ǐ؏��.�L���o�ߓ���
        l_adddepositInfo.firstDepositPassDayValid = 12;
        //��ꐅ���Ǐ؏��.������
        l_adddepositInfo.firstDepositOccurredDate = WEB3DateUtility.getDate("20080801","yyyyMMdd");
        //��ꐅ���Ǐ؏��.�ۏ؋���
        l_adddepositInfo.firstMarginDepositRate = 21;
        //��ꐅ���Ǐ؏��.�ۏ؋��ێ���
        l_adddepositInfo.firstDepositRate = 22;
        //��ꐅ���Ǐ؏��.�Ǐ؋��z
        l_adddepositInfo.firstDepositAmount = 23;
        //��ꐅ���Ǐ؏��.�Ǐ،��ϕK�v�z
        l_adddepositInfo.firstSettlement = 24;
        //��ꐅ���Ǐ؏��.�ۏ؋�����
        l_adddepositInfo.firstMarginDepositInDe = 25;
        //��ꐅ���Ǐ؏��.�ۏ؋�����(�������z)
        l_adddepositInfo.firstMarginDepositInDeExpect = 26;
        //��ꐅ���Ǐ؏��.���ύό���
        l_adddepositInfo.firstSettledContract = 27;
        //��ꐅ���Ǐ؏��.���������z
        l_adddepositInfo.firstUncancelAmt = 28;
        //��ꐅ���Ǐ؏��.���������ϕK�v�z
        l_adddepositInfo.firstUncancelSettleRequiredAmt = 29;
        
        l_adddepositGenerationInfo.setAdddepositInfo(l_adddepositInfo);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getAdddepositGenerationInfo", new Class[]
                { MainAccount.class, boolean.class },
                l_adddepositGenerationInfo);
        
        try
        {
            WEB3TPAdditionalGenerationRequest l_request = new WEB3TPAdditionalGenerationRequest();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            WEB3TPAdditionalGenerationResponse l_response = l_serviceImpl.createAdditionalGenerationScreen(l_request);
            
            assertEquals("0", l_response.additionalGenerationStateDiv);
            assertTrue(l_response.autoTransferAfterJudgeFlag);
            assertFalse(l_response.additionalNonPayAmtFlag);
            assertEquals("11", l_response.firstAdditionalInfo.firstDepositPassDay);
            assertEquals("12", l_response.firstAdditionalInfo.firstDepositPassDayValid);
            assertEquals(WEB3DateUtility.getDate("20080801","yyyyMMdd"), l_response.firstAdditionalInfo.firstDepositOccurredDate);
            assertEquals("21", l_response.firstAdditionalInfo.firstMarginDepositRate);
            assertEquals("22", l_response.firstAdditionalInfo.firstDepositRate);
            assertEquals("23", l_response.firstAdditionalInfo.firstDepositAmount);
            assertEquals("24", l_response.firstAdditionalInfo.firstSettlement);
            assertEquals("25", l_response.firstAdditionalInfo.firstMarginDepositInDe);
            assertEquals("26", l_response.firstAdditionalInfo.firstMarginDepositInDeExpect);
            assertEquals("27", l_response.firstAdditionalInfo.firstSettledContract);
            assertEquals("28", l_response.firstAdditionalInfo.firstUncancelAmt);
            assertEquals("29", l_response.firstAdditionalInfo.firstUncancelSettleRequiredAmt);
            assertNull(l_response.secondAdditionalInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * �Ǐؖ����������t���O�@@=�@@true
     * (get�ڋq�]�͏���Params().�Ǐؖ������敪 = "1")
     * get�Ǐؔ������().�Ǐ؏�� ����񐅏��Ǐ؏��̃C���X�^���X�ł���ꍇ
     */
    public void testcreateAdditionalGenerationScreen_C0004()
    {
        final String STR_METHOD_NAME = " testcreateAdditionalGenerationScreen_C0004";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(false));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getAdditionalMarginSituation", new Class[]
                { MainAccount.class, boolean.class },
                "0");
        
        WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
        //�Ǐؔ������.�ۏ؋������U�֌㔻��t���O
        l_adddepositGenerationInfo.setDepositAutoTransferDivFlag(true);
        //�Ǐؔ������.�Ǐ؏��
        WEB3TPSecondAdddepositInfo l_adddepositInfo = new WEB3TPSecondAdddepositInfo();
        //��񐅏��Ǐ؏��.����(����2)
        l_adddepositInfo.secondCloseDate2 = WEB3DateUtility.getDate("20080801","yyyyMMdd");
        //��񐅏��Ǐ؏��.����(����1)
        l_adddepositInfo.secondCloseDate1 = WEB3DateUtility.getDate("20080802","yyyyMMdd");
        //��񐅏��Ǐ؏��.����(��������)
        l_adddepositInfo.secondCloseDateExpect = WEB3DateUtility.getDate("20080803","yyyyMMdd");
        //��񐅏��Ǐ؏��.������(����2)
        l_adddepositInfo.secondDepositOccurredDate2 = WEB3DateUtility.getDate("20080804","yyyyMMdd");
        //��񐅏��Ǐ؏��.������(����1)
        l_adddepositInfo.secondDepositOccurredDate1 = WEB3DateUtility.getDate("20080805","yyyyMMdd");
        //��񐅏��Ǐ؏��.������(��������)
        l_adddepositInfo.secondDepositOccurredDateExpect = WEB3DateUtility.getDate("20080806","yyyyMMdd");
        //��񐅏��Ǐ؏��.�ۏ؋��ێ���
        l_adddepositInfo.secondDepositRate = 11;
        //��񐅏��Ǐ؏��.�ۏ؋��߂��ێ���
        l_adddepositInfo.secondDepositBackRate = 12;
        //��񐅏��Ǐ؏��.�ۏ؋���(����2)
        l_adddepositInfo.secondMarginDepositRate2 = 13;
        //��񐅏��Ǐ؏��.�ۏ؋���(����1)
        l_adddepositInfo.secondMarginDepositRate1 = 14;
        //��񐅏��Ǐ؏��.�ۏ؋���(��������)
        l_adddepositInfo.secondMarginDepositRateExpect = 15;
        //��񐅏��Ǐ؏��.�Ǐ؋��z(������)
        l_adddepositInfo.secondDepositNonPay = 16;
        //��񐅏��Ǐ؏��.�Ǐ؋��z(����2)
        l_adddepositInfo.secondDeposit2 = 17;
        //��񐅏��Ǐ؏��.�Ǐ؋��z(����1)
        l_adddepositInfo.secondDeposit1 = 18;
        //��񐅏��Ǐ؏��.�Ǐ،��ϕK�v�z(������)
        l_adddepositInfo.secondSettlementNonPay = 19;
        //��񐅏��Ǐ؏��.�Ǐ،��ϕK�v�z(����2)
        l_adddepositInfo.secondSettlement2 = 20;
        //��񐅏��Ǐ؏��.�Ǐ،��ϕK�v�z(����1)
        l_adddepositInfo.secondSettlement1 = 21;
        //��񐅏��Ǐ؏��.�ۏ؋�����
        l_adddepositInfo.secondMarginDepositInDe = 22;
        //��񐅏��Ǐ؏��.�ۏ؋�����(�������z)
        l_adddepositInfo.secondMarginDepositInDeExpect = 23;
        //��񐅏��Ǐ؏��.���ύό���
        l_adddepositInfo.secondSettledContract = 24;
        //��񐅏��Ǐ؏��.���������z(������)
        l_adddepositInfo.secondUncancelAmtNonPay = 25;
        //��񐅏��Ǐ؏��.���������z(����2)
        l_adddepositInfo.secondUncancelAmt2 = 26;
        //��񐅏��Ǐ؏��.���������z(����1)
        l_adddepositInfo.secondUncancelAmt1 = 27;
        //��񐅏��Ǐ؏��.���������z(��������)
        l_adddepositInfo.secondUncancelAmtExpect = 28;
        //��񐅏��Ǐ؏��.���������ϕK�v�z(������)
        l_adddepositInfo.secondUncancelSettleRequiredAmtNonPay = 29;
        //��񐅏��Ǐ؏��.���������ϕK�v�z(����2)
        l_adddepositInfo.secondUncancelSettleRequiredAmt2 = 30;
        //��񐅏��Ǐ؏��.���������ϕK�v�z(����1)
        l_adddepositInfo.secondUncancelSettleRequiredAmt1 = 31;
        //��񐅏��Ǐ؏��.���������ϕK�v(��������)
        l_adddepositInfo.secondUncancelSettleRequiredAmtExpect = 32;
        
        l_adddepositGenerationInfo.setAdddepositInfo(l_adddepositInfo);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getAdddepositGenerationInfo", new Class[]
                { MainAccount.class, boolean.class },
                l_adddepositGenerationInfo);
        
        try
        {
            WEB3TPAdditionalGenerationRequest l_request = new WEB3TPAdditionalGenerationRequest();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            WEB3TPAdditionalGenerationResponse l_response = l_serviceImpl.createAdditionalGenerationScreen(l_request);
            
            assertEquals("0", l_response.additionalGenerationStateDiv);
            assertTrue(l_response.autoTransferAfterJudgeFlag);
            assertTrue(l_response.additionalNonPayAmtFlag);
            assertEquals(WEB3DateUtility.getDate("20080801","yyyyMMdd"), l_response.secondAdditionalInfo.secondCloseDate2);
            assertEquals(WEB3DateUtility.getDate("20080802","yyyyMMdd"), l_response.secondAdditionalInfo.secondCloseDate1);
            assertEquals(WEB3DateUtility.getDate("20080803","yyyyMMdd"), l_response.secondAdditionalInfo.secondCloseDateExpect);
            assertEquals(WEB3DateUtility.getDate("20080804","yyyyMMdd"), l_response.secondAdditionalInfo.secondDepositOccurredDate2);
            assertEquals(WEB3DateUtility.getDate("20080805","yyyyMMdd"), l_response.secondAdditionalInfo.secondDepositOccurredDate1);
            assertEquals(WEB3DateUtility.getDate("20080806","yyyyMMdd"), l_response.secondAdditionalInfo.secondDepositOccurredDateExpect);
            assertEquals("11", l_response.secondAdditionalInfo.secondDepositRate);
            assertEquals("12", l_response.secondAdditionalInfo.secondDepositBackRate);
            assertEquals("13", l_response.secondAdditionalInfo.secondMarginDepositRate2);
            assertEquals("14", l_response.secondAdditionalInfo.secondMarginDepositRate1);
            assertEquals("15", l_response.secondAdditionalInfo.secondMarginDepositRateExpect);
            assertEquals("16", l_response.secondAdditionalInfo.secondDepositNonPay);
            assertEquals("17", l_response.secondAdditionalInfo.secondDeposit2);
            assertEquals("18", l_response.secondAdditionalInfo.secondDeposit1);
            assertEquals("19", l_response.secondAdditionalInfo.secondSettlementNonPay);
            assertEquals("20", l_response.secondAdditionalInfo.secondSettlement2);
            assertEquals("21", l_response.secondAdditionalInfo.secondSettlement1);
            assertEquals("22", l_response.secondAdditionalInfo.secondMarginDepositInDe);
            assertEquals("23", l_response.secondAdditionalInfo.secondMarginDepositInDeExpect);
            assertEquals("24", l_response.secondAdditionalInfo.secondSettledContract);
            assertEquals("25", l_response.secondAdditionalInfo.secondUncancelAmtNonPay);
            assertEquals("26", l_response.secondAdditionalInfo.secondUncancelAmt2);
            assertEquals("27", l_response.secondAdditionalInfo.secondUncancelAmt1);
            assertEquals("28", l_response.secondAdditionalInfo.secondUncancelAmtExpect);
            assertEquals("29", l_response.secondAdditionalInfo.secondUncancelSettleRequiredAmtNonPay);
            assertEquals("30", l_response.secondAdditionalInfo.secondUncancelSettleRequiredAmt2);
            assertEquals("31", l_response.secondAdditionalInfo.secondUncancelSettleRequiredAmt1);
            assertEquals("32", l_response.secondAdditionalInfo.secondUncancelSettleRequiredAmtExpect);
            assertNull(l_response.firstAdditionalInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void setMockMethod()
    {
        final String STR_METHOD_NAME = "setMockMethod()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L)); 
            
            
            this.l_position.setTpCashBalanceFrgnParams(this.l_tpCashBalanceFrgnParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                    "getForeignPositionContract", 
                    new Class[]{ WEB3GentradeSubAccount.class, String.class },
                    this.l_position);
            
            WEB3TPTradingPowerCalcEquityForTest l_calcEquity = new WEB3TPTradingPowerCalcEquityForTest();
            if(this.l_blnIsMarginAccount)
            {
                WEB3TPTradingPowerCalcMargin l_calcMargin = new WEB3TPTradingPowerCalcMarginForTest();
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                        "getTradingPowerCalcMargin", 
                        new Class[]{ WEB3GentradeSubAccount.class },
                        l_calcMargin); 
            }
            else
            {
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                        "getTradingPowerCalcEquity", 
                        new Class[]{ WEB3GentradeSubAccount.class },
                        l_calcEquity);
            }
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex); 
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            // �M�p�ڋq
            if(l_blnIsMarginAccount)
            {
                l_mainAccountParams.setMarginGenAccOpenDiv("1");
                l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT); 
            }
            else
            {
                l_mainAccountParams.setMarginGenAccOpenDiv("0");
                l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT); 
            }
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex); 
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void validateMethodParams()
    {
        final String STR_METHOD_NAME = "validateMethodParams()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                    "getForeignPositionContract", 
                    new Class[] { WEB3GentradeSubAccount.class, String.class  });
            assertEquals(WEB3GentradeSubAccount.class,((WEB3GentradeSubAccount)l_paramsValue1.getFirstCalled()[0]).getClass());
            assertEquals(String.class,((String)l_paramsValue1.getFirstCalled()[1]).getClass());
            
            if(l_blnIsMarginAccount)
            {
                WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                        "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                        "getTradingPowerCalcMargin", 
                        new Class[] {WEB3GentradeSubAccount.class});
                
                assertEquals(WEB3GentradeSubAccount.class,((WEB3GentradeSubAccount)l_paramsValue2.getFirstCalled()[0]).getClass());
            }
            else
            {
                WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                    "getTradingPowerCalcEquity", 
                    new Class[] {WEB3GentradeSubAccount.class});
            
            assertEquals(WEB3GentradeSubAccount.class,((WEB3GentradeSubAccount)l_paramsValue2.getFirstCalled()[0]).getClass());
            }
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex); 
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
	/**
     * create�M�p�V�K���]�͏ڍ׉��
     * ���ʏ��o��(�w���)�擾
     */
    public void test_createMarginTradingPowerDetailResponse_0001()  {

    	final String STR_METHOD_NAME = "test_createMarginTradingPowerDetailResponse_0001()";
    	log.exiting(STR_METHOD_NAME);

        WEB3TPMarginTradingPowerDetailRequest l_request = new WEB3TPMarginTradingPowerDetailRequest();

        WEB3TPMarginTradingPowerDetailResponse l_response = new WEB3TPMarginTradingPowerDetailResponse();

        WEB3TPAssetTradingPowerServiceImpl l_impl = new WEB3TPAssetTradingPowerServiceImpl();

        //�]�͌v�Z����ID
        l_request.calcResultId = "1001";

        //���ʏ��o��iT+0�j
        String contractTotalCost = "1";

        TradingSystemImpl tradingSystem = (TradingSystemImpl)GtlUtils.getTradingSystem();

        l_request.bizDate = tradingSystem.getBizDate();
                
        try
        {
			TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

			LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImplForMock);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(33381330003L));            
            
            //BranchParams
			TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
			TestDBUtility.insertWithDel(l_branchParams);
			
            //	InstitutionParams
			TestDBUtility.deleteAll(InstitutionParams.TYPE);
			InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
			TestDBUtility.insertWithDel(l_InstitutionParams);
			
			//BranchPreferencesParams
			TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
			BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
			l_branchPreferencesParams.setBranchId(33381);
			TestDBUtility.insertWithDel(l_branchPreferencesParams);
           
            // SubAccountParams
			TestDBUtility.deleteAll(SubAccountParams.TYPE);
			SubAccountParams l_subAccountParams = TestDBUtility
					.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
			TestDBUtility.insertWithDel(l_subAccountParams);
			
			// MainAccountParams
			TestDBUtility.deleteAll(MainAccountParams.TYPE);
			MainAccountParams l_mainAccountParams = TestDBUtility
					.getMainAccountRow();
			l_mainAccountParams.setMarginGenAccOpenDiv("1");
			TestDBUtility.insertWithDel(l_mainAccountParams);
            
            // Administrator
			TestDBUtility.deleteAll(AdministratorParams.TYPE);
			AdministratorParams l_administratorParams = TestDBUtility
					.getAdministratorRow();
			TestDBUtility.insertWithDel(l_administratorParams);
			
            //	AdminPermissionRow
			TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
			AdminPermissionParams l_adminPermissionParams = TestDBUtility
					.getAdminPermissionRow();
			l_adminPermissionParams.setInstitutionCode("0D");
	        l_adminPermissionParams.setPermissionLevel("331");
	        l_adminPermissionParams.setTransactionCategory("A0201");
			TestDBUtility.insertWithDel(l_adminPermissionParams);

			// TpCalcResultMarginRow
			TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
			TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility
					.getTpCalcResultMarginRow();
			// ���ʏ��o��iT+0�j
			l_tpCalcResultMarginParams.setContractTotalCost(1);
			TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
			
            //TradingpowerCalcConditionParams
			TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);
			TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility
					.getTradingpowerCalcConditionRow();
			l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
			TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
			
			// TpCalcResultMarginDetailRow
			TestDBUtility.deleteAll(TpCalcResultMarginDetailRow.TYPE);
			TpCalcResultMarginDetailRow l_tpCalcResultMarginDetailRow = TestDBUtility
					.getTpCalcResultMarginDetailRow();
			TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailRow);

            l_response = l_impl.createMarginTradingPowerDetailResponse(l_request);
            
            assertEquals(l_response.contractTotalCost,  contractTotalCost);
        }
        catch (WEB3BaseException e)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
        }
    }
    /**
     * create�M�p�V�K���]�͏ڍ׉��
     * ���ʏ��o��(�w���)�擾
     */
    public void test_createMarginTradingPowerDetailResponse_0002()
    {
        final String STR_METHOD_NAME = "test_createMarginTradingPowerDetailResponse_0002()";
        log.exiting(STR_METHOD_NAME);

        WEB3TPMarginTradingPowerDetailRequest l_request = new WEB3TPMarginTradingPowerDetailRequest();

        WEB3TPMarginTradingPowerDetailResponse l_response = new WEB3TPMarginTradingPowerDetailResponse();

        WEB3TPAssetTradingPowerServiceImpl l_impl = new WEB3TPAssetTradingPowerServiceImpl();

        //�]�͌v�Z����ID
        l_request.calcResultId = "1001";

        TradingSystemImpl tradingSystem = (TradingSystemImpl)GtlUtils.getTradingSystem();

        l_request.bizDate = tradingSystem.getBizDate();
                
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImplForMock);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(33381330003L));            
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            //  InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            //BranchPreferencesParams
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
           
            // SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility
                    .getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            // MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            // Administrator
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility
                    .getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            //  AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility
                    .getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0201");
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            // TpCalcResultMarginRow
            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility
                    .getTpCalcResultMarginRow();
            // ���ʏ��o��iT+0�j
            l_tpCalcResultMarginParams.setContractTotalCost(1);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            //TradingpowerCalcConditionParams
            TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility
                    .getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            // TpCalcResultMarginDetailRow
            TestDBUtility.deleteAll(TpCalcResultMarginDetailRow.TYPE);
            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setSetupFee(0.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss(0.2);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit(0.3);
            l_TpCalcResultMarginDetailParams.setContractOtherCost(0.4);
            TestDBUtility.insertWithDel(l_TpCalcResultMarginDetailParams);
            
            l_response = l_impl.createMarginTradingPowerDetailResponse(l_request);
            
            assertEquals(l_response.contractCommission, "0.1");
            assertEquals(l_response.interestFeeLoss, "0.2");
            assertEquals(l_response.interestFeeProfit, "0.3");
            assertEquals(l_response.otherAccruedCost, "0.4");
        }
        catch (WEB3BaseException e)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
        }
    }

	/**
     * create���������]�͏ڍ׉��
     * ���ʏ��o��(�w���)�擾
     */
    public void test_createEquityTradingPowerDetailResponse_0001()  {

    	final String STR_METHOD_NAME = "test_createMarginTradingPowerDetailResponse_0001()";
    	log.exiting(STR_METHOD_NAME);

    	WEB3TPEquityTradingPowerDetailRequest l_request = new WEB3TPEquityTradingPowerDetailRequest();

    	WEB3TPEquityTradingPowerDetailResponse l_response = new WEB3TPEquityTradingPowerDetailResponse();

        WEB3TPAssetTradingPowerServiceImpl l_impl = new WEB3TPAssetTradingPowerServiceImpl();

        //�]�͌v�Z����ID
        l_request.calcResultId = "1001";

        //���ʏ��o��iT+0�j
        String contractTotalCost = "1";

        TradingSystemImpl tradingSystem = (TradingSystemImpl)GtlUtils.getTradingSystem();

        l_request.bizDate = tradingSystem.getBizDate();

        try
        {
			TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

			LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImplForMock);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(33381330003L));
            
            //BranchParams
			TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
			TestDBUtility.insertWithDel(l_branchParams);
			
            //	InstitutionParams
			TestDBUtility.deleteAll(InstitutionParams.TYPE);
			InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
			TestDBUtility.insertWithDel(l_InstitutionParams);
			
			//BranchPreferencesParams
			TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
			BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
			l_branchPreferencesParams.setBranchId(33381);
			TestDBUtility.insertWithDel(l_branchPreferencesParams);
           
            // SubAccountParams
			TestDBUtility.deleteAll(SubAccountParams.TYPE);
			SubAccountParams l_subAccountParams = TestDBUtility
					.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);            
			TestDBUtility.insertWithDel(l_subAccountParams);
			
			// MainAccountParams
			TestDBUtility.deleteAll(MainAccountParams.TYPE);
			MainAccountParams l_mainAccountParams = TestDBUtility
					.getMainAccountRow();
			l_mainAccountParams.setMarginGenAccOpenDiv("1");
			TestDBUtility.insertWithDel(l_mainAccountParams);
            
            // Administrator
			TestDBUtility.deleteAll(AdministratorParams.TYPE);
			AdministratorParams l_administratorParams = TestDBUtility
					.getAdministratorRow();
			TestDBUtility.insertWithDel(l_administratorParams);
			
            //	AdminPermissionRow
			TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
			AdminPermissionParams l_adminPermissionParams = TestDBUtility
					.getAdminPermissionRow();
			l_adminPermissionParams.setInstitutionCode("0D");
	        l_adminPermissionParams.setPermissionLevel("331");
	        l_adminPermissionParams.setTransactionCategory("A0201");
			TestDBUtility.insertWithDel(l_adminPermissionParams);

			// TpCalcResultMarginRow
			TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
			TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility
					.getTpCalcResultMarginRow();
			// ���ʏ��o��iT+0�j
			l_tpCalcResultMarginParams.setContractTotalCost(1);
			TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
			
            //TradingpowerCalcConditionParams
			TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);
			TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility
					.getTradingpowerCalcConditionRow();
			l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
			TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
			
			// TpCalcResultMarginDetailRow
			TestDBUtility.deleteAll(TpCalcResultMarginDetailRow.TYPE);
			TpCalcResultMarginDetailRow l_tpCalcResultMarginDetailRow = TestDBUtility
					.getTpCalcResultMarginDetailRow();
			TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailRow);

            l_response = l_impl.createEquityTradingPowerDetailResponse(l_request);
            
            assertEquals(l_response.contractTotalCost,  contractTotalCost);
        }
        catch (WEB3BaseException e)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
        }
    }
	
	/**
     * create�]�͐��ږ���<�M�p�ڋq>
     * ���ʏ��o��(�w���)�擾
     */
    public void test_createTransitionReferenceUnitsMargin_0001()  {

    	final String STR_METHOD_NAME = "test_createTransitionReferenceUnitsMargin_0001()";
    	log.exiting(STR_METHOD_NAME);

    	WEB3TPTradingPowerCalcMargin l_calcMargin = new WEB3TPTradingPowerCalcMargin();
    	WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
    	l_calcMargin.setCalcCondition(l_calcCondition);

    	WEB3TPTransitionReferenceUnit[] l_units = new WEB3TPTransitionReferenceUnit[6];

    	WEB3TPAssetTradingPowerServiceImpl l_impl = new WEB3TPAssetTradingPowerServiceImpl();

        //���ʏ��o��iT+6�j
    	String contractTotalCost0 = "0";
		String contractTotalCost1 = "1";
		String contractTotalCost2 = "2";
		String contractTotalCost3 = "3";
		String contractTotalCost4 = "4";
		String contractTotalCost5 = "5";

		TradingSystemImpl tradingSystem = (TradingSystemImpl)GtlUtils.getTradingSystem();

		Date[] date = new Date[9];

		date[0] = tradingSystem.getBizDate();
		date[1] = tradingSystem.getBizDate();
		date[2] = tradingSystem.getBizDate();
		date[3] = tradingSystem.getBizDate();
		date[4] = tradingSystem.getBizDate();
		date[5] = tradingSystem.getBizDate();
		date[6] = tradingSystem.getBizDate();
		date[7] = tradingSystem.getBizDate();
		date[8] = tradingSystem.getBizDate();

		l_calcCondition.setBizDate( date);

        try
        {
			// TpCalcResultMarginRow
			TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
			TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility
					.getTpCalcResultMarginRow();
			// ���ʏ��o��iT+0�j
			l_tpCalcResultMarginParams.setContractTotalCost(0);
			l_tpCalcResultMarginParams.setContractTotalCost1(1);
			l_tpCalcResultMarginParams.setContractTotalCost2(2);
			l_tpCalcResultMarginParams.setContractTotalCost3(3);
			l_tpCalcResultMarginParams.setContractTotalCost4(4);
			l_tpCalcResultMarginParams.setContractTotalCost5(5);
			TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

			//TradingpowerCalcConditionParams
			TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);
			TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility
					.getTradingpowerCalcConditionRow();
			l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
			TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

			// TpCalcResultMarginDetailRow 
			TestDBUtility.deleteAll(TpCalcResultMarginDetailRow.TYPE);
			TpCalcResultMarginDetailRow l_tpCalcResultMarginDetailRow = TestDBUtility
					.getTpCalcResultMarginDetailRow();
			TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailRow);

			//TpCalcResultMarginDetailParams
			TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
			TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = TestDBUtility
					.getTpCalcResultMarginDetailRow();
			l_tpCalcResultMarginDetailParams.setContractAssetLoss(1.0);

			TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

			l_calcMargin.setCalcResultMargin(l_tpCalcResultMarginParams);
			l_calcMargin.setCalcResultDetailMargin(l_tpCalcResultMarginDetailParams);

			l_units = l_impl.createTransitionReferenceUnitsMargin(l_calcMargin);
            
            assertEquals(l_units[0].contractTotalCost,  contractTotalCost0);
            assertEquals(l_units[1].contractTotalCost,  contractTotalCost1);
            assertEquals(l_units[2].contractTotalCost,  contractTotalCost2);
            assertEquals(l_units[3].contractTotalCost,  contractTotalCost3);
            assertEquals(l_units[4].contractTotalCost,  contractTotalCost4);
            assertEquals(l_units[5].contractTotalCost,  contractTotalCost5);
        }
        catch (WEB3BaseException e)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
        }

    }
    
    private class WEB3TPTradingPowerCalcEquityForTest extends WEB3TPTradingPowerCalcEquity
    {
        public TpCalcResultEquityDetailParams getCalcResultDetailEquity()
        {
            TpCalcResultEquityDetailParams l_params = new TpCalcResultEquityDetailParams();
            l_params.setCalcResultEquityId(12345);
            l_params.setEquityAssetDelivered(2000);
            l_params.setEquityAssetExecuted(3000);
            l_params.setMiniStockAssetDelivered(3500);
            l_params.setForeignEquityAssetDelivered(1000);
            
            l_params.setForeignEquityAssetExecuted(2000);
            return l_params;
        }
        
        public double getAccountBalance(int l_intSpecifiedPoint)
        {
            return 0;
        }
        
        public double getTodayExecutedAmount(int l_intSpecifiedPoint)
        {
            return 0;
        }
        
        public WEB3TPCalcCondition getCalcCondition()
        {
            return new WEB3TPCalcConditionForTest();
        }
        
    }
    
    private class WEB3TPCalcConditionForTest extends WEB3TPCalcCondition
    {
        public Date getBizDate(int l_intSpecifiedPoint)
        {
            return new Date();
        }
    }
    
    private class WEB3TPTradingPowerCalcMarginForTest extends WEB3TPTradingPowerCalcMargin
    {
        public TpCalcResultMarginDetailParams getCalcResultDetailMargin()
        {
            TpCalcResultMarginDetailParams l_params = new TpCalcResultMarginDetailParams();
            l_params.setForeignEquityAssetDelivered(2340);
            l_params.setForeignEquityAssetExecuted(1234);
            return l_params;
        }
        
        public double getAccountBalance(int l_intSpecifiedPoint)
        {
            return 0;
        }
        
        public double getTodayExecutedAmount(int l_intSpecifiedPoint)
        {
            return 0;
        }
        
        public WEB3TPCalcCondition getCalcCondition()
        {
            return new WEB3TPCalcConditionForTest();
        }
    }
}
@
