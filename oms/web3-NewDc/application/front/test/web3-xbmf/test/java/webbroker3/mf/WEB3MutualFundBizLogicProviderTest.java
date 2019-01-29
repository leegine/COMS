head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.14.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundBizLogicProviderTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : //TODO(WEB3MutualFundBizLogicProviderTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/08 �юu�� (���u) �V�K�쐬
*/
package webbroker3.mf;

import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FutureOpAccountDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.data.FrgnMmfExchangeRateParams;
import webbroker3.gentrade.data.FrgnMmfExchangeRateRow;
import webbroker3.gentrade.data.InstBranchProductParams;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * XXXXXX�N���X//TODO
 * 
 * @@author �юu��(���u)
 * @@version 1.0
 */
public class WEB3MutualFundBizLogicProviderTest extends TestBaseForMock
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3MutualFundBizLogicProviderTest.class);
    
    public WEB3MutualFundBizLogicProviderTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.mf.WEB3MutualFundBizLogicProvider.calcFrgnMmfEstimatedTradeAmount(String, double, String, WEB3MutualFundProduct, WEB3MutualFundEstimatedPrice)'
     */
    public void testCalcFrgnMmfEstimatedTradeAmount_case1()
    {
        final String STR_METHOD_NAME = "testCalcFrgnMmfEstimatedTradeAmount_case1()";
        log.debug(TEST_START + STR_METHOD_NAME);
        MutualFundProductParams l_mfProductParams = getMutualFundProductRow();
        
        WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = new WEB3MutualFundEstimatedPrice();
        
        FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = new FrgnMmfExchangeRateParams();
        l_frgnMmfExchangeRateParams.setInstitutionCode("0D");
        //TTS
        l_frgnMmfExchangeRateParams.setTtSellingRate(1);
        //�⏕�ʉݒP�ʔ�
        l_frgnMmfExchangeRateParams.setSubCurrencyRatio(5);
        //�����S����
        l_frgnMmfExchangeRateParams.setRestrictRate(2);
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(32768L);
        try 
        {
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundProduct",
                    "getFrgnMmfExchangeRate", 
                    new Class[] {},
                    l_frgnMmfExchangeRateParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundProductManager l_productManager = 
                (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                 ProductTypeEnum.MUTUAL_FUND).getProductManager();
            WEB3MutualFundProduct l_mfProduct = (WEB3MutualFundProduct)l_productManager.getProduct(32768L);
            WEB3MutualFundBizLogicProvider l_mfBizLogicProvider= new WEB3MutualFundBizLogicProvider();
            l_mfBizLogicProvider.calcFrgnMmfEstimatedTradeAmount(
                "1",//�P�F���t
                11.125,//��������
                "1",//���ϕ��@@ �P�F�~��
                l_mfProduct, l_mfEstimatedPrice);
            //�T�Z������� = ����.�������� �~ �בփ��[�g �^  �⏕�ʉݒP�ʔ�
            //            =11.125*1/5 = 2.225 ->2.0
            assertEquals("2.0",l_mfEstimatedPrice.getEstimatedTradeAmount() + "");
            
            //�T�Z��������i�O�݁j= 11.125*1/5 = 2.225 ->2.22
            assertEquals("2.22",l_mfEstimatedPrice.getForeignCurrencyEstimatedTradeAmount() + "");
            
            //�T�Z��n���= 11.125*1/5 *(1+ 2) = 6.675 -> 6.0
            assertEquals("6.0",l_mfEstimatedPrice.getEstimatedPrice() + "");
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.debug(TEST_END + STR_METHOD_NAME);
    }
    public void testCalcFrgnMmfEstimatedTradeAmount_case2()
    {
        final String STR_METHOD_NAME = "testCalcFrgnMmfEstimatedTradeAmount_case2()";
        log.debug(TEST_START + STR_METHOD_NAME);
        MutualFundProductParams l_mfProductParams = getMutualFundProductRow();
        
        WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = new WEB3MutualFundEstimatedPrice();
        
        FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = new FrgnMmfExchangeRateParams();
        l_frgnMmfExchangeRateParams.setInstitutionCode("0D");
        //TTB
        l_frgnMmfExchangeRateParams.setTtBuyingRate(1);
        //�⏕�ʉݒP�ʔ�
        l_frgnMmfExchangeRateParams.setSubCurrencyRatio(5);
        //�����S����
        l_frgnMmfExchangeRateParams.setRestrictRate(2);
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(32768L);
        try 
        {
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundProduct",
                    "getFrgnMmfExchangeRate", 
                    null,
                    l_frgnMmfExchangeRateParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundProductManager l_productManager = 
                (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                 ProductTypeEnum.MUTUAL_FUND).getProductManager();
            WEB3MutualFundProduct l_mfProduct = (WEB3MutualFundProduct)l_productManager.getProduct(32768L);
            WEB3MutualFundBizLogicProvider l_mfBizLogicProvider= new WEB3MutualFundBizLogicProvider();
            l_mfBizLogicProvider.calcFrgnMmfEstimatedTradeAmount(
                "2",//�Q�F���
                13,//��������
                "1",//���ϕ��@@ �P�F�~��
                l_mfProduct, l_mfEstimatedPrice);
            //�T�Z��n��� �� ����.�������� �~ �בփ��[�g �^  �⏕�ʉݒP�ʔ�
            //�T�Z��n���= 13*1/5 = 2.6 -> 2.0
            assertEquals("2.0",l_mfEstimatedPrice.getEstimatedPrice() + "");
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.debug(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcFrgnMmfEstimatedTradeAmount_case3()
    {
        final String STR_METHOD_NAME = "testCalcFrgnMmfEstimatedTradeAmount_case3()";
        log.debug(TEST_START + STR_METHOD_NAME);
        MutualFundProductParams l_mfProductParams = getMutualFundProductRow();
        
        WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = new WEB3MutualFundEstimatedPrice();
        
        FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = new FrgnMmfExchangeRateParams();
        l_frgnMmfExchangeRateParams.setInstitutionCode("0D");
        //TTB
        l_frgnMmfExchangeRateParams.setTtSellingRate(1);
        //�⏕�ʉݒP�ʔ�
        l_frgnMmfExchangeRateParams.setSubCurrencyRatio(3);
        //�����S����
        l_frgnMmfExchangeRateParams.setRestrictRate(2);
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(32768L);
        try 
        {
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundProduct",
                    "getFrgnMmfExchangeRate", 
                    null,
                    l_frgnMmfExchangeRateParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundProductManager l_productManager = 
                (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                 ProductTypeEnum.MUTUAL_FUND).getProductManager();
            WEB3MutualFundProduct l_mfProduct = (WEB3MutualFundProduct)l_productManager.getProduct(32768L);
            WEB3MutualFundBizLogicProvider l_mfBizLogicProvider= new WEB3MutualFundBizLogicProvider();
            l_mfBizLogicProvider.calcFrgnMmfEstimatedTradeAmount(
                "1",//�P�F���t
                5,//��������
                "2",//���ϕ��@@ �Q�F�O��
                l_mfProduct, l_mfEstimatedPrice);
            //�T�Z��n��� �� ����.��������  �^  �⏕�ʉݒP�ʔ�
            //�T�Z��n���= 5/3 = 1.6666666666.. -> 1.66
            assertEquals("1.66",l_mfEstimatedPrice.getEstimatedPrice() + "");
            
            //�T�Z��������F �� ����.�������� �~ �בփ��[�g �^  �⏕�ʉݒP�ʔ�
            // �T�Z������� = 5*1/3 = 1.6666666666.. -> 2.0
            assertEquals("2.0",l_mfEstimatedPrice.getEstimatedTradeAmount() + "");
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.debug(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcFrgnMmfEstimatedTradeAmount_case4()
    {
        final String STR_METHOD_NAME = "testCalcFrgnMmfEstimatedTradeAmount_case4()";
        log.debug(TEST_START + STR_METHOD_NAME);
        
        WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = new WEB3MutualFundEstimatedPrice();
        
        try 
        {
            WEB3MutualFundBizLogicProvider l_mfBizLogicProvider= new WEB3MutualFundBizLogicProvider();
            l_mfBizLogicProvider.calcFrgnMmfEstimatedTradeAmount(
                "1",//�P�F���t
                5,//��������
                "2",//���ϕ��@@ �Q�F�O��
                null, l_mfEstimatedPrice);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {   
            log.debug(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.debug(TEST_END + STR_METHOD_NAME);
    }
    public void testCalcFrgnMmfEstimatedTradeAmount_case5()
    {
        final String STR_METHOD_NAME = "testCalcFrgnMmfEstimatedTradeAmount_case5()";
        log.debug(TEST_START + STR_METHOD_NAME);
        
        try 
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundProductManager l_productManager = 
                (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                 ProductTypeEnum.MUTUAL_FUND).getProductManager();
            WEB3MutualFundProduct l_mfProduct = (WEB3MutualFundProduct)l_productManager.getProduct(32768L);
            WEB3MutualFundBizLogicProvider l_mfBizLogicProvider= new WEB3MutualFundBizLogicProvider();
            l_mfBizLogicProvider.calcFrgnMmfEstimatedTradeAmount(
                "1",//�P�F���t
                5,//��������
                "2",//���ϕ��@@ �Q�F�O��
                l_mfProduct, null);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {   
            log.debug(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.debug(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcFrgnMmfEstimatedQty_case1()
    {
        final String STR_METHOD_NAME = "testCalcFrgnMmfEstimatedQty_case1()";
        log.debug(TEST_START + STR_METHOD_NAME);
        
        try 
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundProductManager l_productManager = 
                (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                 ProductTypeEnum.MUTUAL_FUND).getProductManager();
            WEB3MutualFundProduct l_mfProduct = (WEB3MutualFundProduct)l_productManager.getProduct(32768L);
            WEB3MutualFundBizLogicProvider l_mfBizLogicProvider= new WEB3MutualFundBizLogicProvider();
            l_mfBizLogicProvider.calcFrgnMmfEstimatedQty(
                "1",//�P�F���t
                5,//��������
                "2",//���ϕ��@@ �Q�F�O��
                l_mfProduct, null);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {   
            log.debug(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.debug(TEST_END + STR_METHOD_NAME);
    }
    public void testCalcFrgnMmfEstimatedQty_case2()
    {
        final String STR_METHOD_NAME = "testCalcFrgnMmfEstimatedQty_case2()";
        log.debug(TEST_START + STR_METHOD_NAME);
        
        WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = new WEB3MutualFundEstimatedPrice();
        
        try 
        {
            WEB3MutualFundBizLogicProvider l_mfBizLogicProvider= new WEB3MutualFundBizLogicProvider();
            l_mfBizLogicProvider.calcFrgnMmfEstimatedTradeAmount(
                "1",//�P�F���t
                5,//��������
                "2",//���ϕ��@@ �Q�F�O��
                null, l_mfEstimatedPrice);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {   
            log.debug(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.debug(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcFrgnMmfEstimatedQty_case3()
    {
        final String STR_METHOD_NAME = "testCalcFrgnMmfEstimatedQty_case3()";
        log.debug(TEST_START + STR_METHOD_NAME);
        MutualFundProductParams l_mfProductParams = getMutualFundProductRow();
        
        WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = new WEB3MutualFundEstimatedPrice();
        
        FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = new FrgnMmfExchangeRateParams();
        l_frgnMmfExchangeRateParams.setInstitutionCode("0D");
        //TTS
        l_frgnMmfExchangeRateParams.setTtSellingRate(2);
        //�⏕�ʉݒP�ʔ�
        l_frgnMmfExchangeRateParams.setSubCurrencyRatio(1);
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(32768L);
        try 
        {
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundProduct",
                    "getFrgnMmfExchangeRate", 
                    new Class[] {},
                    l_frgnMmfExchangeRateParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundProductManager l_productManager = 
                (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                 ProductTypeEnum.MUTUAL_FUND).getProductManager();
            WEB3MutualFundProduct l_mfProduct = (WEB3MutualFundProduct)l_productManager.getProduct(32768L);
            WEB3MutualFundBizLogicProvider l_mfBizLogicProvider= new WEB3MutualFundBizLogicProvider();
            l_mfBizLogicProvider.calcFrgnMmfEstimatedQty(
                "1",//�P�F���t
                13,//��������
                "1",//���ϕ��@@ �P�F�~��
                l_mfProduct, l_mfEstimatedPrice);
            //�T�Z�������� �� ����.��������  �~  �⏕�ʉݒP�ʔ�  �^  �בփ��[�g
            //            =13*1/2 = 6.5 -> 6.0
            assertEquals("6.0",l_mfEstimatedPrice.getEstimatedQty() + ""); 
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.debug(TEST_END + STR_METHOD_NAME);
    }
    public void testCalcFrgnMmfEstimatedQty_case4()
    {
        final String STR_METHOD_NAME = "testCalcFrgnMmfEstimatedQty_case4()";
        log.debug(TEST_START + STR_METHOD_NAME);
        MutualFundProductParams l_mfProductParams = getMutualFundProductRow();
        
        WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = new WEB3MutualFundEstimatedPrice();
        
        FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = new FrgnMmfExchangeRateParams();
        l_frgnMmfExchangeRateParams.setInstitutionCode("0D");
        //TTS
        l_frgnMmfExchangeRateParams.setTtBuyingRate(2);
        //�⏕�ʉݒP�ʔ�
        l_frgnMmfExchangeRateParams.setSubCurrencyRatio(1);
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(32768L);
        try 
        {
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_mfProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundProduct",
                    "getFrgnMmfExchangeRate", 
                    new Class[] {},
                    l_frgnMmfExchangeRateParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundProductManager l_productManager = 
                (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                 ProductTypeEnum.MUTUAL_FUND).getProductManager();
            WEB3MutualFundProduct l_mfProduct = (WEB3MutualFundProduct)l_productManager.getProduct(32768L);
            WEB3MutualFundBizLogicProvider l_mfBizLogicProvider= new WEB3MutualFundBizLogicProvider();
            l_mfBizLogicProvider.calcFrgnMmfEstimatedQty(
                "2",//�Q�F���
                4.5,//��������
                "2",//���ϕ��@@ �Q�F�O��
                l_mfProduct, l_mfEstimatedPrice);
            //�T�Z�������� �� ����.��������  �~  �⏕�ʉݒP�ʔ�
            //            =4.5*1 = 4.5 -> 4.0
            assertEquals("4.0",l_mfEstimatedPrice.getEstimatedQty() + ""); 
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.debug(TEST_END + STR_METHOD_NAME);
    }
    public static MutualFundProductParams getMutualFundProductRow()
    {
        MutualFundProductParams l_MutualFundProductParams = new MutualFundProductParams();
        l_MutualFundProductParams.setProductId(32768L);
        l_MutualFundProductParams.setInstitutionCode("40");
        l_MutualFundProductParams.setProductCode("0001000");
        l_MutualFundProductParams.setProductIssueCode("0");
        l_MutualFundProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
        l_MutualFundProductParams.setInitPurchaseMinQty(0L);
        l_MutualFundProductParams.setAddtlPurchaseMinQty(0L);
        l_MutualFundProductParams.setLastUpdater("1001");

        l_MutualFundProductParams.setOnlineUpdatedTimestamp(WEB3DateUtility.getDate(
            "20040716","yyyyMMdd"));

        l_MutualFundProductParams.setCreatedTimestamp(WEB3DateUtility.getDate(
            "20040716","yyyyMMdd"));

        l_MutualFundProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate(
            "20040716","yyyyMMdd"));
        return l_MutualFundProductParams;
    }
    
    /**
     * �����敪����W�̏ꍇ���g�����M����.��W�萔���敪���u2�F�O�g�v�ȊO�̏ꍇ�A
     * �����S�����A�����S������1�ɂ���B
     * 
     * �����敪="4" && �g�����M����.��W�萔���敪 = "1"
     * 
     * �����S���� = 1
     * �����S���� = 1
     */
    public void testCalcIncreaseRestraintPriceInRatio_0001()
    {
        final String STR_METHOD_NAME = "testCalcIncreaseRestraintPriceInRatio_0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFundBizLogicProvider l_mfBizLogicProvider = new WEB3MutualFundBizLogicProvider();
        
        try
        {
            this.initData();

            //MutualFundProductRow
            MutualFundProductParams l_MutualFundProductParams = new MutualFundProductParams();
            l_MutualFundProductParams.setProductId(3304148080000L);
            l_MutualFundProductParams.setConstantValueCalcUnit(1000);
            l_MutualFundProductParams.setSellConstantValue(1000);
            l_MutualFundProductParams.setRecruitConstantValue(1000);
            l_MutualFundProductParams.setBuyConstantValue(1000);
            l_MutualFundProductParams.setRecruitCommissionDiv("1");
            l_MutualFundProductParams.setInstitutionCode("0D");
            l_MutualFundProductParams.setCurrencyCode("01");
            l_MutualFundProductParams.setProductCode("0001000");
            l_MutualFundProductParams.setProductIssueCode("0");
            l_MutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_MutualFundProductParams.setInitPurchaseMinQty(0L);
            l_MutualFundProductParams.setAddtlPurchaseMinQty(0L);
            l_MutualFundProductParams.setLastUpdater("1001");
            l_MutualFundProductParams.setOnlineUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_MutualFundProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_MutualFundProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_MutualFundProductParams);
            
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount = l_gentradeManager.getSubAccount(333812512203L, 33381251220301L);

            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct = new WEB3MutualFundProduct(l_MutualFundProductParams);
            }
            catch(Exception l_ex)
            {
                log.error("", l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            
            WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = new WEB3MutualFundEstimatedPrice();
            
            l_mfBizLogicProvider.calcIncreaseRestraintPriceInRatio(
                "4",
                "3",
                123d,
                l_subAccount,
                l_mfEstimatedPrice,
                l_fundTradedProduct);
            
            assertTrue(true);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {   
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH);
            TestDBUtility.insertWithDel(l_mainAccountParams);            
            
            //SubAccountRow
            SubAccountParams l_subAccountParams = new SubAccountParams();
            //�����h�c]
            l_subAccountParams.setAccountId(333812512203L);
            //�⏕�����h�c
            l_subAccountParams.setSubAccountId(33381251220301L);
            //�⏕�����^�C�v
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            //�،���ЃR�[�h
            l_subAccountParams.setInstitutionCode("0D");
            //�،����ID
            l_subAccountParams.setInstitutionId(33);
            //���X�h�c
            l_subAccountParams.setBranchId(33381L);
            //�⏕�����X�e�[�^�X
            l_subAccountParams.setSubAccountStatus(SubAccountStatusEnum.ACTIVE);
            //�����o�^��
            l_subAccountParams.setOpenDate(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
            //��������
            l_subAccountParams.setCloseDate(WEB3DateUtility.getDate("20081211","yyyyMMdd"));
            //�c��(�����j
            l_subAccountParams.setCashBalance(13456.0);
            //�쐬���t
            l_subAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            //�X�V���t
            l_subAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            //ProductRow
            ProductParams l_productParams = new ProductParams();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            l_productParams.setStandardName("�V���Z���e���X");
            l_productParams.setLotSize(0.000000D);
            l_productParams.setCalcSize(1.000000D);
            l_productParams.setEstimationPrice(0.000000D);
            l_productParams.setMarginRatio(0.000000D);
            l_productParams.setSecuritiesEstimationRatio(0.000000D);
            l_productParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_productParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
  
            //FrgnMmfExchangeRateRow
            FrgnMmfExchangeRateParams l_FrgnMmfExchangeRateParams = new FrgnMmfExchangeRateParams();
            l_FrgnMmfExchangeRateParams.setInstitutionCode("0D");
            l_FrgnMmfExchangeRateParams.setCurrencyCode("01");
            l_FrgnMmfExchangeRateParams.setExecTimestamp(Calendar.getInstance().getTime()); 
            l_FrgnMmfExchangeRateParams.setTtSellingRate(1D);
            l_FrgnMmfExchangeRateParams.setTtBuyingRate(50D);
            l_FrgnMmfExchangeRateParams.setSubCurrencyRatio(50);
            l_FrgnMmfExchangeRateParams.setRestrictRate(10D);
            l_FrgnMmfExchangeRateParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_FrgnMmfExchangeRateParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(FrgnMmfExchangeRateRow.TYPE);
            TestDBUtility.insertWithDel(l_FrgnMmfExchangeRateParams);
            
            //InstitutionRow
            InstitutionParams l_institutionParams = new InstitutionParams();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //InstBranchProductRow
            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(33381L);
            l_instBranchProductParams.setCommissionProductCode("20");
            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            
            
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
