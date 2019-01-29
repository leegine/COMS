head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.57.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityOrderBuyInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������������t���̓T�[�r�XImplTest(WEB3EquityOrderBuyInputServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/25 ��іQ(���u) �V�K�쐬
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.message.WEB3EquityBuyInputRequest;
import webbroker3.equity.message.WEB3EquityBuyInputResponse;
import webbroker3.equity.message.WEB3EquityProductSelectRequest;
import webbroker3.equity.message.WEB3EquityProductSelectResponse;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchMarketDealtCondParams;
import webbroker3.gentrade.data.BranchMarketDealtCondRow;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondParams;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondRow;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.OrderAcceptStatusParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityOrderBuyInputServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3EquityOrderBuyInputServiceImplTest.class);

    public WEB3EquityOrderBuyInputServiceImplTest(String arg0)
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
    
//    /**
//     * validate������t�\()
//     * ������ԊǗ�.validate������t�\()����I��
//     * isPTS�����J��==false
//     */
//    public void testValidateOrderAcceptCase0001()
//    {
//        final String STR_METHOD_NAME = "testValidateOrderAcceptCase0001()";
//        log.entering(STR_METHOD_NAME);
//
//        boolean l_blnIsPTSAccountEstablished = false;
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        try
//        {
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            
//            WEB3EquityOrderBuyInputServiceImpl l_impl = new WEB3EquityOrderBuyInputServiceImpl();
//            
//            l_impl.validateOrderAccept(l_blnIsPTSAccountEstablished);
//
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /**
//     * ������ԊǗ�.validate������t�\()�̏����ŗ�O���������Ă���
//     * isPTS�����J��==false
//     */
//    public void testValidateOrderAcceptCase0002()
//    {
//        final String STR_METHOD_NAME = "testValidateOrderAcceptCase0002()";
//        log.entering(STR_METHOD_NAME);
//
//        boolean l_blnIsPTSAccountEstablished = false;
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        try
//        {
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false);
//            
//            WEB3EquityOrderBuyInputServiceImpl l_impl = new WEB3EquityOrderBuyInputServiceImpl();
//            
//            l_impl.validateOrderAccept(l_blnIsPTSAccountEstablished);
//            fail();
//
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.debug(STR_METHOD_NAME, l_ex);
//            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80006);
//            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//
//        }
//        catch (Exception l_ex)
//        {
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//            fail();
//        }
//    }
//    
//    /**
//     * ������ԊǗ�.validate������t�\()����I��
//     * isPTS�����J��==true
//     * PTS������ԊǗ�.validate������t�\()����I��
//     */
//    public void testValidateOrderAcceptCase0003()
//    {
//        final String STR_METHOD_NAME = "testValidateOrderAcceptCase0003()";
//        log.entering(STR_METHOD_NAME);
//
//        boolean l_blnIsPTSAccountEstablished = true;
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        try
//        {
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            
//            WEB3EquityOrderBuyInputServiceImpl l_impl = new WEB3EquityOrderBuyInputServiceImpl();
//            
//            l_impl.validateOrderAccept(l_blnIsPTSAccountEstablished);
//
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /**
//     * ������ԊǗ�.validate������t�\()�̏����ŗ�O���������Ă���
//     * isPTS�����J��==true
//     * PTS������ԊǗ�.validate������t�\()�̏����ŗ�O����������
//     */
//    public void testValidateOrderAcceptCase0004()
//    {
//        final String STR_METHOD_NAME = "testValidateOrderAcceptCase0004()";
//        log.entering(STR_METHOD_NAME);
//
//        boolean l_blnIsPTSAccountEstablished = true;
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        try
//        {
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false);
//            
//            WEB3EquityOrderBuyInputServiceImpl l_impl = new WEB3EquityOrderBuyInputServiceImpl();
//            
//            l_impl.validateOrderAccept(l_blnIsPTSAccountEstablished);
//            fail();
//
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.debug(STR_METHOD_NAME, l_ex);
//            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80006);
//            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        }
//        catch (Exception l_ex)
//        {
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//            fail();
//        }
//    }
    
    /**
     * get�s��ǌx���s�� 
     * ������ԊǗ�.get�s��ǌx���s��()���R�[�����A�s��R�[�h�̔z����擾����
     * isPTS�����J��==false
     */
    public void testGetTradeCloseMarketCase0001()
    {
        final String STR_METHOD_NAME = "testGetTradeCloseMarketCase0001()";
        log.entering(STR_METHOD_NAME);

        boolean l_blnIsPTSAccountEstablished = false;

        try
        {            
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                    new Timestamp(WEB3DateUtility.getDate("20071227150000", "yyyyMMddHHmmss").getTime()));
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(1200);
            TestDBUtility.insertWithDel(l_branchParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);
            BranchMarketDealtCondParams l_branchMarketDealtCondParams =
                TestDBUtility.getBranchMarketDealCondRow();
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);
            
            BranchMarketDealtCondParams l_branchMarketDealtCondParams1 =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams1.setMarketCode("6");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams1);

            BranchMarketDealtCondParams l_branchMarketDealtCondParams2 =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams2.setMarketCode("2");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams2);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            WEB3GentradeBranch l_branch =
                (WEB3GentradeBranch)l_finApp.getAccountManager().getBranch(l_branchParams.getBranchId());

            WEB3EquityOrderBuyInputServiceImpl l_impl = new WEB3EquityOrderBuyInputServiceImpl();
            
            String[] l_strTradeCloseMarkets =
                l_impl.getTradeCloseMarket(l_branch, l_blnIsPTSAccountEstablished);
            
            assertEquals(l_strTradeCloseMarkets[0], "1");
            assertEquals(l_strTradeCloseMarkets[1], "2");
            assertEquals(l_strTradeCloseMarkets[2], "6");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get�s��ǌx���s�� 
     * �P�j������ԊǗ�.get�s��ǌx���s��()���R�[�����A�s��R�[�h�̔z����擾����
     * isPTS�����J��==true
     * �Q�jPTS������ԊǗ�.get�s��ǌx���s��()���R�[�����A�s��R�[�h�̔z����擾���� 
     * �P�j�̌��ʂƂQ�j�̌��ʂ��}�[�W���Ďs��R�[�h�����Ń\�[�g����B 
     */
    public void testGetTradeCloseMarketCase0002()
    {
        final String STR_METHOD_NAME = "testGetTradeCloseMarketCase0002()";
        log.entering(STR_METHOD_NAME);

        boolean l_blnIsPTSAccountEstablished = true;

        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                    new Timestamp(WEB3DateUtility.getDate("20071227150000", "yyyyMMddHHmmss").getTime()));
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(1200); 
            TestDBUtility.insertWithDel(l_branchParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);
            BranchMarketDealtCondParams l_branchMarketDealtCondParams =
                TestDBUtility.getBranchMarketDealCondRow();
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);
            
            BranchMarketDealtCondParams l_branchMarketDealtCondParams1 =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams1.setMarketCode("6");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams1);

            BranchMarketDealtCondParams l_branchMarketDealtCondParams2 =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams2.setMarketCode("2");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams2);
            
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams1 =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams1.setMarketCode("5");
            l_branchMarketPtsDealtCondParams1.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams1);
            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams2 =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams2.setMarketCode("6");
            l_branchMarketPtsDealtCondParams2.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams2);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            WEB3GentradeBranch l_branch =
                (WEB3GentradeBranch)l_finApp.getAccountManager().getBranch(l_branchParams.getBranchId());

            WEB3EquityOrderBuyInputServiceImpl l_impl = new WEB3EquityOrderBuyInputServiceImpl();
            
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            String[] l_strTradeCloseMarkets =
                l_impl.getTradeCloseMarket(l_branch, l_blnIsPTSAccountEstablished);
            
            assertEquals(l_strTradeCloseMarkets[0], "1");
            assertEquals(l_strTradeCloseMarkets[1], "2");
            assertEquals(l_strTradeCloseMarkets[2], "5");
            assertEquals(l_strTradeCloseMarkets[3], "6");
            assertEquals(l_strTradeCloseMarkets[4], "11");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get�戵�\�s��
     * (���X�s���)�戵����.get�戵�\�s��()���R�[�����A�s��R�[�h�̔z����擾����B
     * isPTS�����J��==false
     */
    public void testGetHandlingPossibleMarketCase0001()
    {
        final String STR_METHOD_NAME = "testGetHandlingPossibleMarketCase0001()";
        log.entering(STR_METHOD_NAME);

        boolean l_blnIsPTSAccountEstablished = false;

        try
        {
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(1200);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);
            BranchMarketDealtCondParams l_branchMarketDealtCondParams =
                TestDBUtility.getBranchMarketDealCondRow();
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);
            
            BranchMarketDealtCondParams l_branchMarketDealtCondParams1 =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams1.setMarketCode("6");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams1);

            BranchMarketDealtCondParams l_branchMarketDealtCondParams2 =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams2.setMarketCode("2");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams2);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            WEB3GentradeBranch l_branch =
                (WEB3GentradeBranch)l_finApp.getAccountManager().getBranch(l_branchParams.getBranchId());

            WEB3EquityOrderBuyInputServiceImpl l_impl = new WEB3EquityOrderBuyInputServiceImpl();
            
            String[] l_strHandlingPossibleMarkets =
                l_impl.getHandlingPossibleMarket(l_branch, l_blnIsPTSAccountEstablished);
            
            assertEquals(l_strHandlingPossibleMarkets[0], "1");
            assertEquals(l_strHandlingPossibleMarkets[1], "2");
            assertEquals(l_strHandlingPossibleMarkets[2], "6");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get�戵�\�s��
     * �P�j(���X�s���)�戵����.get�戵�\�s��()���R�[�����A�s��R�[�h�̔z����擾����B
     * isPTS�����J��==true
     * �Q�j(���X�s��ʁEPTS)�戵����.get�戵�\�s��()���R�[�����A�s��R�[�h�̔z����擾����B 
     * �P�j�̌��ʂƂQ�j�̌��ʂ��}�[�W���Ďs��R�[�h�����Ń\�[�g����B
     */
    public void testGetHandlingPossibleMarketCase0002()
    {
        final String STR_METHOD_NAME = "testGetHandlingPossibleMarketCase0002()";
        log.entering(STR_METHOD_NAME);

        boolean l_blnIsPTSAccountEstablished = true;

        try
        {
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(1200);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);
            BranchMarketDealtCondParams l_branchMarketDealtCondParams =
                TestDBUtility.getBranchMarketDealCondRow();
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);
            
            BranchMarketDealtCondParams l_branchMarketDealtCondParams1 =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams1.setMarketCode("6");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams1);

            BranchMarketDealtCondParams l_branchMarketDealtCondParams2 =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams2.setMarketCode("2");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams2);
            
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams1 =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams1.setMarketCode("5");
            l_branchMarketPtsDealtCondParams1.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams1);
            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams2 =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams2.setMarketCode("6");
            l_branchMarketPtsDealtCondParams2.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams2);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            WEB3GentradeBranch l_branch =
                (WEB3GentradeBranch)l_finApp.getAccountManager().getBranch(l_branchParams.getBranchId());

            WEB3EquityOrderBuyInputServiceImpl l_impl = new WEB3EquityOrderBuyInputServiceImpl();
            
            String[] l_strHandlingPossibleMarkets =
                l_impl.getHandlingPossibleMarket(l_branch, l_blnIsPTSAccountEstablished);
            
            assertEquals(l_strHandlingPossibleMarkets[0], "1");
            assertEquals(l_strHandlingPossibleMarkets[1], "2");
            assertEquals(l_strHandlingPossibleMarkets[2], "5");
            assertEquals(l_strHandlingPossibleMarkets[3], "6");
            assertEquals(l_strHandlingPossibleMarkets[4], "11");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * get�����I�����
     * ����I������
     */
    public void testGetProductSelectScreenCase0001()
    {
        final String STR_METHOD_NAME = "testGetProductSelectScreenCase0001()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                    new Timestamp(WEB3DateUtility.getDate("20071227150000", "yyyyMMddHHmmss").getTime()));
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    OrderValidationResult.VALIDATION_OK_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateAccountProductOrderStop",
                    new Class[] {SubAccount.class,long.class, OrderTypeEnum.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(1200);
            TestDBUtility.insertWithDel(l_branchParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);
            BranchMarketDealtCondParams l_branchMarketDealtCondParams =
                TestDBUtility.getBranchMarketDealCondRow();
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);
            
            BranchMarketDealtCondParams l_branchMarketDealtCondParams1 =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams1.setMarketCode("6");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams1);

            BranchMarketDealtCondParams l_branchMarketDealtCondParams2 =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams2.setMarketCode("2");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams2);
            
            WEB3EquityOrderBuyInputServiceImpl l_impl = new WEB3EquityOrderBuyInputServiceImpl();
            
            WEB3EquityProductSelectRequest l_request = new WEB3EquityProductSelectRequest();
            WEB3EquityProductSelectResponse l_response = l_impl.getProductSelectScreen(l_request);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class});

            assertEquals(new Long(101001010010L) + "", 
                    ((WEB3GentradeMainAccount)l_paramsValue1.getFirstCalled()[0]).getAccountId() + "");

            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateAccountProductOrderStop",
                    new Class[] {SubAccount.class,long.class, OrderTypeEnum.class});

            assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue2.getFirstCalled()[0]).getAccountId());
            
            assertEquals(new Long(0), l_paramsValue2.getFirstCalled()[1]);

            assertEquals(1, ((OrderTypeEnum)l_paramsValue2.getFirstCalled()[2]).intValue());
        
            assertEquals(l_response.marketList[0], "1");
            assertEquals(l_response.marketList[1], "2");
            assertEquals(l_response.marketList[2], "6");
            assertEquals(l_response.messageSuspension[0], "1");
            assertEquals(l_response.messageSuspension[1], "2");
            assertEquals(l_response.messageSuspension[2], "6");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    /**
     * get�����I�����
     *  validate������t�\(boolean)�e�ُ�
     */
    public void testGetProductSelectScreenCase0002()
    {
        final String STR_METHOD_NAME = "testGetProductSelectScreenCase0002()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false);
            
            WEB3EquityOrderBuyInputServiceImpl l_impl = new WEB3EquityOrderBuyInputServiceImpl();
            
            WEB3EquityProductSelectRequest l_request = new WEB3EquityProductSelectRequest();
            l_impl.getProductSelectScreen(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80006);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * get�����I�����
     *  validate����\�ڋq(�ڋq, boolean, String)�e�ُ�
     */
    public void testGetProductSelectScreenCase0003()
    {
        final String STR_METHOD_NAME = "testGetProductSelectScreenCase0003()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            OrderValidationResult l_error = new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00275));
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    l_error);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(1200);
            TestDBUtility.insertWithDel(l_branchParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);
            BranchMarketDealtCondParams l_branchMarketDealtCondParams =
                TestDBUtility.getBranchMarketDealCondRow();
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);
            
            BranchMarketDealtCondParams l_branchMarketDealtCondParams1 =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams1.setMarketCode("6");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams1);

            BranchMarketDealtCondParams l_branchMarketDealtCondParams2 =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams2.setMarketCode("2");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams2);
            
            WEB3EquityOrderBuyInputServiceImpl l_impl = new WEB3EquityOrderBuyInputServiceImpl();
            
            WEB3EquityProductSelectRequest l_request = new WEB3EquityProductSelectRequest();
            l_impl.getProductSelectScreen(l_request);

            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00275);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class});

            assertEquals(new Long(101001010010L) + "", 
                    ((WEB3GentradeMainAccount)l_paramsValue1.getFirstCalled()[0]).getAccountId() + "");
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * get�����I�����
     *  validate�ڋq�����ʎ����~(�⏕����, long, boolean,String)�e�ُ�
     */
    public void testGetProductSelectScreenCase0004()
    {
        final String STR_METHOD_NAME = "testGetProductSelectScreenCase0004()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    OrderValidationResult.VALIDATION_OK_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateAccountProductOrderStop",
                    new Class[] {SubAccount.class,long.class, OrderTypeEnum.class},
                    new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, ""));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(1200);
            TestDBUtility.insertWithDel(l_branchParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);
            BranchMarketDealtCondParams l_branchMarketDealtCondParams =
                TestDBUtility.getBranchMarketDealCondRow();
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);
            
            BranchMarketDealtCondParams l_branchMarketDealtCondParams1 =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams1.setMarketCode("6");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams1);

            BranchMarketDealtCondParams l_branchMarketDealtCondParams2 =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams2.setMarketCode("2");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams2);
            
            WEB3EquityOrderBuyInputServiceImpl l_impl = new WEB3EquityOrderBuyInputServiceImpl();
            
            WEB3EquityProductSelectRequest l_request = new WEB3EquityProductSelectRequest();
            l_impl.getProductSelectScreen(l_request);
            
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80006);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class});

            assertEquals(new Long(101001010010L) + "", 
                    ((WEB3GentradeMainAccount)l_paramsValue1.getFirstCalled()[0]).getAccountId() + "");

            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateAccountProductOrderStop",
                    new Class[] {SubAccount.class,long.class, OrderTypeEnum.class});

            assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue2.getFirstCalled()[0]).getAccountId());
            
            assertEquals(new Long(0), l_paramsValue2.getFirstCalled()[1]);

            assertEquals(1, ((OrderTypeEnum)l_paramsValue2.getFirstCalled()[2]).intValue());

            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * get�����I�����
     * validate������t�X�e�C�^�X
     */
    public void testGetProductSelectScreenCase0005()
    {
        final String STR_METHOD_NAME = "testGetProductSelectScreenCase0005()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                    new Timestamp(WEB3DateUtility.getDate("20071227150000", "yyyyMMddHHmmss").getTime()));
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    OrderValidationResult.VALIDATION_OK_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateAccountProductOrderStop",
                    new Class[] {SubAccount.class,long.class, OrderTypeEnum.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(1200);
            TestDBUtility.insertWithDel(l_branchParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);
            BranchMarketDealtCondParams l_branchMarketDealtCondParams =
                TestDBUtility.getBranchMarketDealCondRow();
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);
            
            BranchMarketDealtCondParams l_branchMarketDealtCondParams1 =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams1.setMarketCode("6");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams1);

            BranchMarketDealtCondParams l_branchMarketDealtCondParams2 =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams2.setMarketCode("2");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams2);
            
//            TestDBUtility.deleteAll(OrderAcceptStatusParams.TYPE);
//            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
//            l_orderAcceptStatusParams.setBranchCode("123");
//            l_orderAcceptStatusParams.setInstitutionCode("0D");
//            l_orderAcceptStatusParams.setOrderAccProduct(null);
//            l_orderAcceptStatusParams.setOrderAccTransaction(null);
//            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = 
                new WEB3GentradeTradingClendarContext();
            
            l_clendarContext.setInstitutionCode("0D");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, 
                    l_clendarContext);
            
            WEB3EquityOrderBuyInputServiceImpl l_impl = new WEB3EquityOrderBuyInputServiceImpl();
            
            WEB3EquityProductSelectRequest l_request = new WEB3EquityProductSelectRequest();
            WEB3EquityProductSelectResponse l_response = l_impl.getProductSelectScreen(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80006);

            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    /**
     * get���͉��
     * ����I������
     */
    public void testGetBuyInputScreenCase0001()
    {
        final String STR_METHOD_NAME = "testGetBuyInputScreenCase0001()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    OrderValidationResult.VALIDATION_OK_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateAccountProductOrderStop",
                    new Class[] {SubAccount.class,long.class, OrderTypeEnum.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));

            Double l_return = new Double(300);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "getEquityTradingPower",
                    new Class[] { WEB3GentradeSubAccount.class },
                    l_return);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(1200);
            TestDBUtility.insertWithDel(l_branchParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("1");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setBizDateType("0");
            l_tradingTimeParams1.setMarketCode("1");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);
            BranchMarketDealtCondParams l_branchMarketDealtCondParams =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(1006169090018L);
            l_EqtypeProductParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_MarketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesRow.setName("equity.pts.market.div");
            l_MarketPreferencesRow.setValue("1");
            TestDBUtility.insertWithDel(l_MarketPreferencesRow);
            
            WEB3EquityOrderBuyInputServiceImplForTest l_impl = new WEB3EquityOrderBuyInputServiceImplForTest();
            
            WEB3EquityBuyInputRequest l_request = new WEB3EquityBuyInputRequest();
            l_request.productCode = null;
            l_request.marketCode = "1";
            l_request.tradingType = "1";
            WEB3EquityBuyInputResponse l_response = l_impl.getBuyInputScreen(l_request);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class});

            assertEquals(new Long(101001010010L) + "", 
                    ((WEB3GentradeMainAccount)l_paramsValue1.getFirstCalled()[0]).getAccountId() + "");

            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateAccountProductOrderStop",
                    new Class[] {SubAccount.class,long.class, OrderTypeEnum.class});

            assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue2.getFirstCalled()[0]).getAccountId());
            
            assertEquals(new Long(0), l_paramsValue2.getFirstCalled()[1]);

            assertEquals(1, ((OrderTypeEnum)l_paramsValue2.getFirstCalled()[2]).intValue());
        
            assertEquals(l_response.marketList[0], "1");

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    /**
     * get���͉��
     * validate������t�\(boolean)�e�ُ�
     */
    public void testGetBuyInputScreenCase0002()
    {
        final String STR_METHOD_NAME = "testGetBuyInputScreenCase0002()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            WEB3EquityOrderBuyInputServiceImplForTest l_impl = new WEB3EquityOrderBuyInputServiceImplForTest();
            
            WEB3EquityBuyInputRequest l_request = new WEB3EquityBuyInputRequest();
            l_request.productCode = null;
            l_request.marketCode = "1";
            l_request.tradingType = "1";
            l_impl.getBuyInputScreen(l_request);
            fail();

        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80006);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * get���͉��
     * validate����\�ڋq(�ڋq, boolean, String)�e�ُ�
     */
    public void testGetBuyInputScreenCase0003()
    {
        final String STR_METHOD_NAME = "testGetBuyInputScreenCase0003()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));

            OrderValidationResult l_error = new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00275));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    l_error);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setBizDateType("0");
            l_tradingTimeParams1.setMarketCode("1");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            WEB3EquityOrderBuyInputServiceImplForTest l_impl = new WEB3EquityOrderBuyInputServiceImplForTest();
            
            WEB3EquityBuyInputRequest l_request = new WEB3EquityBuyInputRequest();
            l_request.productCode = null;
            l_request.marketCode = "1";
            l_request.tradingType = "1";
            l_impl.getBuyInputScreen(l_request);
            fail();

        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00275);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * get���͉��
     * vvalidate�ڋq�����ʎ����~�e�ُ�
     */
    public void testGetBuyInputScreenCase0004()
    {
        final String STR_METHOD_NAME = "testGetBuyInputScreenCase0004()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    OrderValidationResult.VALIDATION_OK_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateAccountProductOrderStop",
                    new Class[] {SubAccount.class,long.class, OrderTypeEnum.class},
                    new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, ""));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));

            Double l_return = new Double(300);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "getEquityTradingPower",
                    new Class[] { WEB3GentradeSubAccount.class },
                    l_return);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(1200);
            TestDBUtility.insertWithDel(l_branchParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("1");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setBizDateType("0");
            l_tradingTimeParams1.setMarketCode("1");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);
            BranchMarketDealtCondParams l_branchMarketDealtCondParams =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(1006169090018L);
            l_EqtypeProductParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_MarketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesRow.setName("equity.pts.market.div");
            l_MarketPreferencesRow.setValue("1");
            TestDBUtility.insertWithDel(l_MarketPreferencesRow);
            
            WEB3EquityOrderBuyInputServiceImplForTest l_impl = new WEB3EquityOrderBuyInputServiceImplForTest();
            
            WEB3EquityBuyInputRequest l_request = new WEB3EquityBuyInputRequest();
            l_request.productCode = null;
            l_request.marketCode = "1";
            l_request.tradingType = "1";
            l_impl.getBuyInputScreen(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80006);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class});

            assertEquals(new Long(101001010010L) + "", 
                    ((WEB3GentradeMainAccount)l_paramsValue1.getFirstCalled()[0]).getAccountId() + "");

            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateAccountProductOrderStop",
                    new Class[] {SubAccount.class,long.class, OrderTypeEnum.class});

            assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue2.getFirstCalled()[0]).getAccountId());
            
            assertEquals(new Long(0), l_paramsValue2.getFirstCalled()[1]);

            assertEquals(1, ((OrderTypeEnum)l_paramsValue2.getFirstCalled()[2]).intValue());

            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
    get���͉��
   ���N�G�X�g.�s��R�[�h�I��null�̏ꍇ
   ���N�G�X�g.����敪=="����������"�@@�̏ꍇ
   validate�s��R�[�h�e�ُ�
    */
   public void testGetBuyInputScreenCase0005()
   {
       final String STR_METHOD_NAME = "testGetBuyInputScreenCase0005()";
       log.entering(STR_METHOD_NAME);

       MOCK_MANAGER.setIsMockUsed(true);
       try
       {
           WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
                   
           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                   "getAccountId",
                   new Class[] {},
                   new Long(101001010010L));

           //MainAccountParams
           TestDBUtility.deleteAll(MainAccountRow.TYPE);
           MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
           l_mainAccountParams.setAccountId(101001010010L);
           TestDBUtility.insertWithDel(l_mainAccountParams);
           
           TestDBUtility.deleteAll(MarketRow.TYPE);
           MarketParams l_marketRow = TestDBUtility.getMarketRow();
           l_marketRow.setMarketCode("1");
           l_marketRow.setSuspension("1");
           TestDBUtility.insertWithDel(l_marketRow);
           
           //SubAccountParams
           TestDBUtility.deleteAll(SubAccountRow.TYPE);
           SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
           l_subAccountParams.setAccountId(101001010010L);
           l_subAccountParams.setSubAccountId(10100101001007L);
           l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
           TestDBUtility.insertWithDel(l_subAccountParams);

           TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
           l_tradingTimeParams1.setInstitutionCode("0D");
           l_tradingTimeParams1.setBranchCode("123");
           l_tradingTimeParams1.setBizDateType("0");
           l_tradingTimeParams1.setMarketCode("1");
           l_tradingTimeParams1.setProductCode("0");
           l_tradingTimeParams1.setTradingTimeType("01");
           TestDBUtility.insertWithDel(l_tradingTimeParams1);
           
           WEB3EquityOrderBuyInputServiceImplForTest l_impl = new WEB3EquityOrderBuyInputServiceImplForTest();
           
           WEB3EquityBuyInputRequest l_request = new WEB3EquityBuyInputRequest();
           l_request.productCode = null;
           l_request.marketCode = "1";
           l_request.tradingType = "1";
           l_impl.getBuyInputScreen(l_request);
           fail();

       }
       catch(WEB3BaseException l_ex)
       {
           log.debug(STR_METHOD_NAME, l_ex);
           assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01747);
           log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
       }
       catch (Exception l_ex)
       {
           log.error(ERROR + l_ex.getMessage(), l_ex);
           fail();
       }
   }
   
   /**
       get���͉��
       ���N�G�X�g.�s��R�[�h�I��null�̏ꍇ
       ���N�G�X�g.����敪=="����O����"�@@�̏ꍇ
       get�s�ꐳ��擾���validate����\�ڋq�|�e�ُ�
    */
   public void testGetBuyInputScreenCase0006()
   {
       final String STR_METHOD_NAME = "testGetBuyInputScreenCase0006()";
       log.entering(STR_METHOD_NAME);

       MOCK_MANAGER.setIsMockUsed(true);
       try
       {
           WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
                   
           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                   "getAccountId",
                   new Class[] {},
                   new Long(101001010010L));

           OrderValidationResult l_error = new OrderValidationResult(
                   ProcessingResult.newFailedResultInstance(
                       WEB3ErrorCatalog.BUSINESS_ERROR_00275));
           
           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "webbroker3.gentrade.WEB3GentradeOrderValidator",
                   "validateAccountForTrading",
                   new Class[] {WEB3GentradeMainAccount.class},
                   l_error);

           //MainAccountParams
           TestDBUtility.deleteAll(MainAccountRow.TYPE);
           MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
           l_mainAccountParams.setAccountId(101001010010L);
           TestDBUtility.insertWithDel(l_mainAccountParams);
           
           //SubAccountParams
           TestDBUtility.deleteAll(SubAccountRow.TYPE);
           SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
           l_subAccountParams.setAccountId(101001010010L);
           l_subAccountParams.setSubAccountId(10100101001007L);
           l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
           TestDBUtility.insertWithDel(l_subAccountParams);

           TestDBUtility.deleteAll(MarketRow.TYPE);
           MarketParams l_marketRow = TestDBUtility.getMarketRow();
           l_marketRow.setMarketCode("1");
           TestDBUtility.insertWithDel(l_marketRow);
           
           TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
           l_tradingTimeParams1.setInstitutionCode("0D");
           l_tradingTimeParams1.setBranchCode("123");
           l_tradingTimeParams1.setBizDateType("0");
           l_tradingTimeParams1.setMarketCode("1");
           l_tradingTimeParams1.setProductCode("0");
           l_tradingTimeParams1.setTradingTimeType("01");
           TestDBUtility.insertWithDel(l_tradingTimeParams1);
           
           WEB3EquityOrderBuyInputServiceImplForTest l_impl = new WEB3EquityOrderBuyInputServiceImplForTest();
           
           WEB3EquityBuyInputRequest l_request = new WEB3EquityBuyInputRequest();
           l_request.productCode = null;
           l_request.marketCode = "1";
           l_request.tradingType = "99";
           l_request.productCode = "1";
           l_impl.getBuyInputScreen(l_request);
           fail();

       }
       catch(WEB3BaseException l_ex)
       {
           log.debug(STR_METHOD_NAME, l_ex);
           assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00275);
           log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
       }
       catch (Exception l_ex)
       {
           log.error(ERROR + l_ex.getMessage(), l_ex);
           fail();
       }
   }
   
   /**
       get���͉��
   ���N�G�X�g.�s��R�[�h�I��null�̏ꍇ
   ���N�G�X�g.����敪=="����O����"�@@�̏ꍇ
   get�s���s��
*/
public void testGetBuyInputScreenCase0007()
{
   final String STR_METHOD_NAME = "testGetBuyInputScreenCase0007()";
   log.entering(STR_METHOD_NAME);

   MOCK_MANAGER.setIsMockUsed(true);
   try
   {
       WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
               
       TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
               "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
               "getAccountId",
               new Class[] {},
               new Long(101001010010L));

       //MainAccountParams
       TestDBUtility.deleteAll(MainAccountRow.TYPE);
       MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
       l_mainAccountParams.setAccountId(101001010010L);
       TestDBUtility.insertWithDel(l_mainAccountParams);
       
       //SubAccountParams
       TestDBUtility.deleteAll(SubAccountRow.TYPE);
       SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
       l_subAccountParams.setAccountId(101001010010L);
       l_subAccountParams.setSubAccountId(10100101001007L);
       l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
       TestDBUtility.insertWithDel(l_subAccountParams);

       TestDBUtility.deleteAll(MarketRow.TYPE);

       TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
       l_tradingTimeParams1.setInstitutionCode("0D");
       l_tradingTimeParams1.setBranchCode("123");
       l_tradingTimeParams1.setBizDateType("0");
       l_tradingTimeParams1.setMarketCode("1");
       l_tradingTimeParams1.setProductCode("0");
       l_tradingTimeParams1.setTradingTimeType("01");
       TestDBUtility.insertWithDel(l_tradingTimeParams1);
       
       WEB3EquityOrderBuyInputServiceImplForTest l_impl = new WEB3EquityOrderBuyInputServiceImplForTest();
       
       WEB3EquityBuyInputRequest l_request = new WEB3EquityBuyInputRequest();
       l_request.productCode = null;
       l_request.marketCode = "1";
       l_request.tradingType = "99";
       l_request.productCode = "1";
       l_impl.getBuyInputScreen(l_request);
       fail();

   }
   catch(WEB3BaseException l_ex)
   {
       log.debug(STR_METHOD_NAME, l_ex);
       assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00645);
       log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
   }
   catch (Exception l_ex)
   {
       log.error(ERROR + l_ex.getMessage(), l_ex);
       fail();
   }
}

    /**
     * get���͉��
     * validate������t�X�e�C�^�X
     */
    public void testGetBuyInputScreenCase0008()
    {
        final String STR_METHOD_NAME = "testGetBuyInputScreenCase0008()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    OrderValidationResult.VALIDATION_OK_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateAccountProductOrderStop",
                    new Class[] {SubAccount.class,long.class, OrderTypeEnum.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));

            Double l_return = new Double(300);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "getEquityTradingPower",
                    new Class[] { WEB3GentradeSubAccount.class },
                    l_return);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(1200);
            TestDBUtility.insertWithDel(l_branchParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("1");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setBizDateType("0");
            l_tradingTimeParams1.setMarketCode("1");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);
            BranchMarketDealtCondParams l_branchMarketDealtCondParams =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(1006169090018L);
            l_EqtypeProductParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_MarketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesRow.setName("equity.pts.market.div");
            l_MarketPreferencesRow.setValue("1");
            TestDBUtility.insertWithDel(l_MarketPreferencesRow);
            
            WEB3GentradeTradingClendarContext l_clendarContext = 
                new WEB3GentradeTradingClendarContext();
            
            l_clendarContext.setInstitutionCode("0D");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, 
                    l_clendarContext);
            
            WEB3EquityOrderBuyInputServiceImplForTest l_impl = new WEB3EquityOrderBuyInputServiceImplForTest();
            
            WEB3EquityBuyInputRequest l_request = new WEB3EquityBuyInputRequest();
            l_request.productCode = null;
            l_request.marketCode = "1";
            l_request.tradingType = "1";
            WEB3EquityBuyInputResponse l_response = l_impl.getBuyInputScreen(l_request);
            fail();

        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80006);

            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    /**
     * validate����\�ڋq
     �ҝɌڋq == null�C
     */
    public void testValidateAccountForTradingCase0001()
    {
        final String STR_METHOD_NAME = "testValidateAccountForTradingCase0001()";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsPTSAccountEstablished = false;
        try
        {
            WEB3GentradeMainAccount l_mainAccount = null;

            WEB3EquityOrderBuyInputServiceImpl l_impl = new WEB3EquityOrderBuyInputServiceImpl();
            
            OrderValidationResult l_orderValidationResult =
                l_impl.validateAccountForTrading(l_mainAccount, l_blnIsPTSAccountEstablished, null);

            assertEquals(l_orderValidationResult.getProcessingResult().getErrorInfo(),
                WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate����\�ڋq
     * �����`�F�b�N.validate����\�ڋq(�ڋq�A������)���R�[������B 
     */
    public void testValidateAccountForTradingCase0002()
    {
        final String STR_METHOD_NAME = "testValidateAccountForTradingCase0002()";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsPTSAccountEstablished = true;
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    OrderValidationResult.VALIDATION_OK_RESULT);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_MarketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesRow.setName("equity.pts.market.div");
            l_MarketPreferencesRow.setValue("1");
            TestDBUtility.insertWithDel(l_MarketPreferencesRow);
            
            Date l_datsystime = WEB3DateUtility.getDate("20071225", "yyyyMMdd");
                //Calendar.getInstance().getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datsystime);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(l_mainAccountParams.getAccountId());

            WEB3EquityOrderBuyInputServiceImpl l_impl = new WEB3EquityOrderBuyInputServiceImpl();
            
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            OrderValidationResult l_orderValidationResult =
                l_impl.validateAccountForTrading(l_mainAccount, l_blnIsPTSAccountEstablished, "1");

            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class});
            
            assertEquals(new Long(333812512246L) + "", 
                    ((WEB3GentradeMainAccount)l_paramsValue2.getFirstCalled()[0]).getAccountId() + "");
            
            assertEquals(WEB3DateUtility.formatDate(l_datsystime, "yyyyMMdd"), 
                    WEB3DateUtility.formatDate((Date)l_paramsValue2.getFirstCalled()[1], "yyyyMMdd"));
            
            assertEquals(l_orderValidationResult, OrderValidationResult.VALIDATION_OK_RESULT);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate����\�ڋq
     * �����`�F�b�N.validate����\�ڋq(�ڋq)���R�[������B 
     */
    public void testValidateAccountForTradingCase0003()
    {
        final String STR_METHOD_NAME = "testValidateAccountForTradingCase0003()";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsPTSAccountEstablished = true;
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class},
                    OrderValidationResult.VALIDATION_OK_RESULT);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            Date l_datsystime = WEB3DateUtility.getDate("20071225", "yyyyMMdd");
                //Calendar.getInstance().getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datsystime);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(l_mainAccountParams.getAccountId());

            WEB3EquityOrderBuyInputServiceImpl l_impl = new WEB3EquityOrderBuyInputServiceImpl();
            
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            OrderValidationResult l_orderValidationResult =
                l_impl.validateAccountForTrading(l_mainAccount, l_blnIsPTSAccountEstablished, null);

            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class});

            assertEquals(new Long(333812512246L) + "", 
                    ((WEB3GentradeMainAccount)l_paramsValue1.getFirstCalled()[0]).getAccountId() + "");
            
            assertEquals(l_orderValidationResult, OrderValidationResult.VALIDATION_OK_RESULT);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate����\�ڋq
       �����`�F�b�N.validate����\�ڋq(�ڋq�A������)���R�[������B
       PTS������ԊǗ�.get������()  �e�ُ�
     */
    public void testValidateAccountForTradingCase0004()
    {
        final String STR_METHOD_NAME = "testValidateAccountForTradingCase0004()";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsPTSAccountEstablished = true;
        try
        {
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_MarketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesRow.setName("equity.pts.market.div");
            l_MarketPreferencesRow.setValue("1");
            TestDBUtility.insertWithDel(l_MarketPreferencesRow);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(l_mainAccountParams.getAccountId());

            WEB3EquityOrderBuyInputServiceImpl l_impl = new WEB3EquityOrderBuyInputServiceImpl();
            
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            OrderValidationResult l_orderValidationResult =
                l_impl.validateAccountForTrading(l_mainAccount, l_blnIsPTSAccountEstablished, "1");
            
            assertEquals(l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     validate�ڋq�����ʎ����~ 
     �⏕���� == null�C
     */
    public void testValidateAccountProductOrderStopCase0001()
    {
        final String STR_METHOD_NAME = "testValidateAccountProductOrderStopCase0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3EquityOrderBuyInputServiceImpl l_impl = new WEB3EquityOrderBuyInputServiceImpl();
            SubAccount l_subAccount = null;
            long l_lngProductId = 12345678;
            l_impl.validateAccountProductOrderStop(l_subAccount, l_lngProductId, false, null);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     validate�ڋq�����ʎ����~
     PTS�����}�l�[�W��.validate�ڋq�����ʎ����~(PTS)()���R�[������B
     */
    public void testValidateAccountProductOrderStopCase0002()
    {
        final String STR_METHOD_NAME = "testValidateAccountProductOrderStopCase0002()";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsPTSAccountEstablished = true;
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_MarketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesRow.setName("equity.pts.market.div");
            l_MarketPreferencesRow.setValue("1");
            TestDBUtility.insertWithDel(l_MarketPreferencesRow);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                null);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    101001010010L, 10100101001007L);

            WEB3EquityOrderBuyInputServiceImpl l_impl = new WEB3EquityOrderBuyInputServiceImpl();
            
            long l_lngProductId = 12345678;
            l_impl.validateAccountProductOrderStop(
                l_subAccount, l_lngProductId, l_blnIsPTSAccountEstablished, "1");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
        validate�ڋq�����ʎ����~ 
        PTS�����}�l�[�W��.validate�ڋq�����ʎ����~(PTS)()���R�[������e�ُ�
     */
    public void testValidateAccountProductOrderStopCase0003()
    {
        final String STR_METHOD_NAME = "testValidateAccountProductOrderStopCase0003()";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsPTSAccountEstablished = true;
        MOCK_MANAGER.setIsMockUsed(true);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityPTSOrderManager",
            "validatePTSAccountProductOrderStop",
            new Class[]{ SubAccount.class, long.class, OrderTypeEnum.class },
            new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ""));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            l_marketRow.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketRow);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_MarketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesRow.setName("equity.pts.market.div");
            l_MarketPreferencesRow.setValue("1");
            TestDBUtility.insertWithDel(l_MarketPreferencesRow);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    101001010010L, 10100101001007L);

            WEB3EquityOrderBuyInputServiceImpl l_impl = new WEB3EquityOrderBuyInputServiceImpl();
            
            long l_lngProductId = 12345678;
            l_impl.validateAccountProductOrderStop(l_subAccount, l_lngProductId, l_blnIsPTSAccountEstablished, "1");
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "validatePTSAccountProductOrderStop",
                    new Class[]{ SubAccount.class, long.class, OrderTypeEnum.class });

                assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue2.getFirstCalled()[0]).getAccountId());
                
                assertEquals(new Long(12345678), l_paramsValue2.getFirstCalled()[1]);

                assertEquals(1, ((OrderTypeEnum)l_paramsValue2.getFirstCalled()[2]).intValue());
            
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
        validate�ڋq�����ʎ����~ 
        PTS�����}�l�[�W��.�g�����������}�l�[�W��.validate�ڋq�����ʎ����~()���R�[������B 
     */
    public void testValidateAccountProductOrderStopCase0004()
    {
        final String STR_METHOD_NAME = "testValidateAccountProductOrderStopCase0004()";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsPTSAccountEstablished = false;
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateAccountProductOrderStop",
            new Class[] {SubAccount.class,long.class, OrderTypeEnum.class},
            null);        
        try
        {
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    101001010010L, 10100101001007L);

            WEB3EquityOrderBuyInputServiceImpl l_impl = new WEB3EquityOrderBuyInputServiceImpl();
            
            long l_lngProductId = 12345678;
            l_impl.validateAccountProductOrderStop(l_subAccount, l_lngProductId, l_blnIsPTSAccountEstablished, null);
            
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateAccountProductOrderStop",
                new Class[] {SubAccount.class,long.class, OrderTypeEnum.class});

            assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue1.getFirstCalled()[0]).getAccountId());
            
            assertEquals(new Long(12345678), l_paramsValue1.getFirstCalled()[1]);

            assertEquals(1, ((OrderTypeEnum)l_paramsValue1.getFirstCalled()[2]).intValue());

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
        validate�ڋq�����ʎ����~ 
        PTS�����}�l�[�W��.�g�����������}�l�[�W��.validate�ڋq�����ʎ����~()���R�[������e�ُ�
     */
    public void testValidateAccountProductOrderStopCase0005()
    {
        final String STR_METHOD_NAME = "testValidateAccountProductOrderStopCase0005()";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsPTSAccountEstablished = false;
        MOCK_MANAGER.setIsMockUsed(true);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateAccountProductOrderStop",
            new Class[] {SubAccount.class,long.class, OrderTypeEnum.class},
            new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, ""));
        
        try
        {
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    101001010010L, 10100101001007L);

            WEB3EquityOrderBuyInputServiceImpl l_impl = new WEB3EquityOrderBuyInputServiceImpl();
            
            long l_lngProductId = 12345678;
            l_impl.validateAccountProductOrderStop(l_subAccount, l_lngProductId, l_blnIsPTSAccountEstablished, "1");
            fail();

        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80006);

            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateAccountProductOrderStop",
                new Class[] {SubAccount.class,long.class, OrderTypeEnum.class});

            assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue1.getFirstCalled()[0]).getAccountId());
            
            assertEquals(new Long(12345678), l_paramsValue1.getFirstCalled()[1]);

            assertEquals(1, ((OrderTypeEnum)l_paramsValue1.getFirstCalled()[2]).intValue());
            
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    private class WEB3EquityOrderBuyInputServiceImplForTest extends WEB3EquityOrderBuyInputServiceImpl
    {
        protected String getMarketCode(
            String l_strProductCode,
            String l_strMarketCode,
            String l_strInstitutionCode)
            throws WEB3BaseException
        {
            return l_strMarketCode;
        }
    }
}
@
