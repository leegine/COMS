head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.10.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoDepositTransitionReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �؋������ڎQ�Ɖ�ʕ\���T�[�r�XImplTest(WEB3IfoDepositTransitionReferenceServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/08/29 ��іQ(���u) �V�K�쐬
*/

package webbroker3.ifodeposit.service.delegate.stdimpls;

import java.lang.reflect.Field;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.ifodeposit.WEB3IfoCustomerTransfer;
import webbroker3.ifodeposit.WEB3IfoDepositCalc;
import webbroker3.ifodeposit.WEB3IfoDepositCalcCondition;
import webbroker3.ifodeposit.WEB3IfoDepositCalcConditionPerIndex;
import webbroker3.ifodeposit.WEB3IfoSummaryContractPerIndex;
import webbroker3.ifodeposit.message.WEB3IfoDepositCalcResultSaveRequest;
import webbroker3.ifodeposit.message.WEB3IfoDepositPerIndexUnit;
import webbroker3.ifodeposit.message.WEB3IfoDepositTranRefPerIndexUnit;
import webbroker3.ifodeposit.message.WEB3IfoDepositTransitionReferenceRequest;
import webbroker3.ifodeposit.message.WEB3IfoDepositTransitionReferenceResponse;
import webbroker3.ifodeposit.message.WEB3IfoDepositTransitionReferenceUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoDepositTransitionReferenceServiceImplTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3IfoDepositTransitionReferenceServiceImplTest.class);

    private boolean l_bln = false;
    
    public WEB3IfoDepositTransitionReferenceServiceImplTest(String arg0)
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

    /**
     * create�؋������ڎQ�Ɖ��
     * �؋����������J�݂̏ꍇ(this.get�⏕����( )�̖߂�l == null)
     */
    public void testCreateIfoDepositTransitionReferenceResponse0001()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositTransitionReferenceResponse0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(4));
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(4);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType( SubAccountTypeEnum.FX_SPOT_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(4);
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3IfoDepositTransitionReferenceServiceImpl l_impl =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
  
            WEB3IfoDepositTransitionReferenceRequest l_request = new WEB3IfoDepositTransitionReferenceRequest();
            
            WEB3IfoDepositTransitionReferenceResponse l_response =
                l_impl.createIfoDepositTransitionReferenceResponse(l_request);
            
            assertNull(l_response.dayAfterTomorrowClaimAmt);
            assertNull(l_response.ifoDepositUnit);
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
     * ���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
     *SPAN�敪�F�@@�؋����v�Z����.isSPAN�g�p�\( ) == true�̏ꍇ�C
     �i�؋����v�Z����.is���Z�l�����M��()==true�@@or�@@�؋����v�Z����.is�؋����s�����[�����M��()==true�j�̏ꍇ
     */
    public void testCreateIfoDepositTransitionReferenceResponse0002()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositTransitionReferenceResponse0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_bln = true;
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(4));
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("123");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("N1");
            l_clendarContext.setTradingTimeType("13");
            l_clendarContext.setBizDateType("1");
            
            
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20080805", "yyyyMMdd"));
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(4);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType( SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(4);
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3IfoDepositCalc l_ifoDepositCalc = new WEB3IfoDepositCalc();
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            l_ifoDepositCalcCondition.setQuickReportReceivedFlag(true);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field.setAccessible(true);
            field.set(l_ifoDepositCalc,l_ifoDepositCalcCondition);
            
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double l_dblCurrentBizDateTransferAmount = 13;
            l_ifoCustomerTransfer.setBalances(new double[]{10,20,30});
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(l_dblCurrentBizDateTransferAmount);
            
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field1.setAccessible(true);
            field1.set(l_ifoDepositCalc,l_ifoCustomerTransfer);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl",
                    "getIfoDepositCalc",
                    new Class[]{WEB3GentradeSubAccount.class},
                    l_ifoDepositCalc);

            WEB3IfoDepositTransitionReferenceServiceImplForTest l_impl =
                new WEB3IfoDepositTransitionReferenceServiceImplForTest();
  
            WEB3IfoDepositTransitionReferenceRequest l_request = new WEB3IfoDepositTransitionReferenceRequest();
            
            WEB3IfoDepositTransitionReferenceResponse l_response =
                l_impl.createIfoDepositTransitionReferenceResponse(l_request);
            
            assertEquals("0", l_response.nonPayAmt);
            assertEquals("0", l_response.todayClaimAmt);
            assertEquals("0", l_response.tomorrowClaimAmt);
            assertEquals("0", l_response.dayAfterTomorrowClaimAmt);
            assertEquals("0", l_response.tomorrowClaimAmtEve);
            assertEquals("33", l_response.depositChangePower);
            assertNull(l_response.deliveryDate);
            assertEquals("1", l_response.spanDiv);
            assertEquals("1", l_response.fixedIfoDepositFlg);
            assertNull(l_response.ifoDepositPerIndexUnit);
            
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
     * ���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
     * SPAN�敪�F�@@�؋����v�Z����.isSPAN�g�p�\( ) == false�̏ꍇ�C
     �i�؋����v�Z����.is���Z�l�����M��()==false�@@and�@@�؋����v�Z����.is�؋����s�����[�����M��()==false�j�̏ꍇ
     */
    public void testCreateIfoDepositTransitionReferenceResponse0003()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositTransitionReferenceResponse0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_bln = true;
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(4));
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("123");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("N1");
            l_clendarContext.setTradingTimeType("13");
            l_clendarContext.setBizDateType("1");
            
            
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20080805", "yyyyMMdd"));
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(4);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType( SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(4);
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3IfoDepositCalc l_ifoDepositCalc = new WEB3IfoDepositCalc();
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field.setAccessible(true);
            field.set(l_ifoDepositCalc,l_ifoDepositCalcCondition);
            
            
            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double l_dblCurrentBizDateTransferAmount = 13;
            l_ifoCustomerTransfer.setBalances(new double[]{10,20,30});
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(l_dblCurrentBizDateTransferAmount);
            
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field1.setAccessible(true);
            field1.set(l_ifoDepositCalc,l_ifoCustomerTransfer);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl",
                    "getIfoDepositCalc",
                    new Class[]{WEB3GentradeSubAccount.class},
                    l_ifoDepositCalc);

            WEB3IfoDepositTransitionReferenceServiceImplForTest l_impl =
                new WEB3IfoDepositTransitionReferenceServiceImplForTest();
  
            WEB3IfoDepositTransitionReferenceRequest l_request = new WEB3IfoDepositTransitionReferenceRequest();
            
            WEB3IfoDepositTransitionReferenceResponse l_response =
                l_impl.createIfoDepositTransitionReferenceResponse(l_request);
            
            assertEquals("0", l_response.nonPayAmt);
            assertEquals("0", l_response.todayClaimAmt);
            assertEquals("0", l_response.tomorrowClaimAmt);
            assertEquals("0", l_response.dayAfterTomorrowClaimAmt);
            assertEquals("0", l_response.tomorrowClaimAmtEve);
            assertEquals("33", l_response.depositChangePower);
            assertNull(l_response.deliveryDate);
            assertEquals("0", l_response.spanDiv);
            assertEquals("0", l_response.fixedIfoDepositFlg);
            assertNull(l_response.ifoDepositPerIndexUnit);
            
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
     * create�؋������ږ��׈ꗗ
     */
    public void testCreateIfoDepositTransitionReferences0001()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositTransitionReferences0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(4));
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("123");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("N1");
            l_clendarContext.setTradingTimeType("13");
            l_clendarContext.setBizDateType("1");

            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20080805", "yyyyMMdd"));
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(4);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType( SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(4);
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3IfoDepositCalc l_ifoDepositCalc = new WEB3IfoDepositCalc();
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field.setAccessible(true);
            field.set(l_ifoDepositCalc,l_ifoDepositCalcCondition);

            WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();
            double l_dblCurrentBizDateTransferAmount = 13;
            l_ifoCustomerTransfer.setBalances(new double[]{10,20,30});
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(l_dblCurrentBizDateTransferAmount);
            
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoCustomerTransfer");
            field1.setAccessible(true);
            field1.set(l_ifoDepositCalc,l_ifoCustomerTransfer);

            WEB3IfoDepositTransitionReferenceServiceImpl l_impl =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
            
            WEB3IfoDepositTransitionReferenceUnit[] l_unit =
                l_impl.createIfoDepositTransitionReferences(l_ifoDepositCalc);
            
            assertEquals(3, l_unit.length);
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
     * create�w���ʏ؋����ꗗ
       �w���ʏ؋����I�u�W�F�N�g�̔z���ԋp����B
       SPAN�g�p�\(isSPAN�g�p�\() == true
     */
    public void testCreateIfoDepositPerIndexUnitList0001()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositPerIndexUnitList0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {

            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(false);
            

            WEB3IfoDepositTransitionReferenceServiceImpl l_impl =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
            
            WEB3IfoDepositPerIndexUnit[] l_units =
                l_impl.createIfoDepositPerIndexUnitList(l_ifoDepositCalcCondition);
            
            assertNull(l_units);
            
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
     * create�w���ʏ؋����ꗗ
       SPAN�g�p�\(isSPAN�g�p�\() == false
       �w���ʏ؋����v�Z�����v�f == 3
     */
    public void testCreateIfoDepositPerIndexUnitList0002()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositPerIndexUnitList0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {

            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);

            WEB3IfoDepositCalcConditionPerIndex l_perIndex = new WEB3IfoDepositCalcConditionPerIndex();
            l_perIndex.setUnderlyingProductCode("00");
            l_perIndex.setIfoDepositPerUnit(100);
            WEB3IfoDepositCalcConditionPerIndex l_perIndex1 = new WEB3IfoDepositCalcConditionPerIndex();
            l_perIndex1.setUnderlyingProductCode("11");
            l_perIndex1.setIfoDepositPerUnit(200);
            WEB3IfoDepositCalcConditionPerIndex l_perIndex2 = new WEB3IfoDepositCalcConditionPerIndex();
            l_perIndex2.setUnderlyingProductCode("22");
            l_perIndex2.setIfoDepositPerUnit(300);
            WEB3IfoDepositCalcConditionPerIndex[] l_l_perIndexs = new WEB3IfoDepositCalcConditionPerIndex[3];
            l_l_perIndexs[0] = l_perIndex;
            l_l_perIndexs[1] = l_perIndex1;
            l_l_perIndexs[2] = l_perIndex2;
            l_ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_l_perIndexs);

            WEB3IfoDepositTransitionReferenceServiceImpl l_impl =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
            
            WEB3IfoDepositPerIndexUnit[] l_units =
                l_impl.createIfoDepositPerIndexUnitList(l_ifoDepositCalcCondition);
            
            assertEquals(3, l_units.length);
            assertEquals(l_units[0].targetProductCode, "00");
            assertEquals(l_units[1].targetProductCode, "11");
            assertEquals(l_units[2].targetProductCode, "22");
            assertEquals(l_units[0].regIfoDeposit, "100");
            assertEquals(l_units[1].regIfoDeposit, "200");
            assertEquals(l_units[2].regIfoDeposit, "300");
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
     * create�w���ʏ؋����ꗗ
       SPAN�g�p�\(isSPAN�g�p�\() == false
       �w���ʏ؋����v�Z�����v�f == 1
     */
    public void testCreateIfoDepositPerIndexUnitList0003()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositPerIndexUnitList0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {

            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);

            WEB3IfoDepositCalcConditionPerIndex l_perIndex = new WEB3IfoDepositCalcConditionPerIndex();
            l_perIndex.setUnderlyingProductCode("00");
            l_perIndex.setIfoDepositPerUnit(100);
            WEB3IfoDepositCalcConditionPerIndex[] l_l_perIndexs = new WEB3IfoDepositCalcConditionPerIndex[1];
            l_l_perIndexs[0] = l_perIndex;
            l_ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_l_perIndexs);

            WEB3IfoDepositTransitionReferenceServiceImpl l_impl =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
            
            WEB3IfoDepositPerIndexUnit[] l_units =
                l_impl.createIfoDepositPerIndexUnitList(l_ifoDepositCalcCondition);
            
            assertEquals(1, l_units.length);
            assertEquals(l_units[0].targetProductCode, "00");
            assertEquals(l_units[0].regIfoDeposit, "100");
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
     * create�w���ʏ؋����ꗗ
       SPAN�g�p�\(isSPAN�g�p�\() == false
       �w���ʏ؋����v�Z�����v�f == 0
     */
    public void testCreateIfoDepositPerIndexUnitList0004()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositPerIndexUnitList0004";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(true);


            WEB3IfoDepositCalcConditionPerIndex[] l_l_perIndexs = new WEB3IfoDepositCalcConditionPerIndex[0];
            l_ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_l_perIndexs);
            
            WEB3IfoDepositTransitionReferenceServiceImpl l_impl =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
            
            WEB3IfoDepositPerIndexUnit[] l_units =
                l_impl.createIfoDepositPerIndexUnitList(l_ifoDepositCalcCondition);
            
            assertEquals(0, l_units.length);
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
     *is�V�K���]�͉\ 
     *�؋����v�Z.get�؋����v�Z����().is�V�K���]�͉\() == false 
     */
    public void testIsNewOpenTradingPowerAvailable0001()
    {
        final String STR_METHOD_NAME = "testIsNewOpenTradingPowerAvailable0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalc l_ifoDepositCalc = new WEB3IfoDepositCalc();
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(false);
            l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field.setAccessible(true);
            field.set(l_ifoDepositCalc,l_ifoDepositCalcCondition);

            WEB3IfoDepositTransitionReferenceServiceImpl l_impl =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
            
            boolean l_blnIsNewOpenTradingPowerAvailable =
                l_impl.isNewOpenTradingPowerAvailable(l_ifoDepositCalc, 5);

            assertFalse(l_blnIsNewOpenTradingPowerAvailable);
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
     *  is�V�K���]�͉\ 
     *  �؋����v�Z.calc����؋����c��(����.�w���) <  
�@@�@@�@@   �؋����v�Z.get�؋����v�Z����().get�K�v�Œ�؋���() 
     */
    public void testIsNewOpenTradingPowerAvailable0002()
    {
        final String STR_METHOD_NAME = "testIsNewOpenTradingPowerAvailable0002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalc l_ifoDepositCalc = new WEB3IfoDepositCalc();
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
            l_ifoDepositCalcCondition.setMinIfoDeposit(1);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field.setAccessible(true);
            field.set(l_ifoDepositCalc,l_ifoDepositCalcCondition);

            WEB3IfoDepositTransitionReferenceServiceImpl l_impl =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
            
            boolean l_blnIsNewOpenTradingPowerAvailable =
                l_impl.isNewOpenTradingPowerAvailable(l_ifoDepositCalc, 5);

            assertFalse(l_blnIsNewOpenTradingPowerAvailable);
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
     *  is�V�K���]�͉\ 
     *  �E�؋����v�Z.get�؋����v�Z����().is�V�K���]�͉\() == true
�@@�@@�@@  �E�؋����v�Z.calc����؋����c��(����.�w���) =>  
�@@�@@�@@   �؋����v�Z.get�؋����v�Z����().get�K�v�Œ�؋���() 
�@@�@@�@@  �E�؋����v�Z.calc�������z() <= 0 
     */
    public void testIsNewOpenTradingPowerAvailable0003()
    {
        final String STR_METHOD_NAME = "testIsNewOpenTradingPowerAvailable0003";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalc l_ifoDepositCalc = new WEB3IfoDepositCalc();
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
            l_ifoDepositCalcCondition.setMinIfoDeposit(-1);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field.setAccessible(true);
            field.set(l_ifoDepositCalc,l_ifoDepositCalcCondition);

            WEB3IfoDepositTransitionReferenceServiceImpl l_impl =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
            
            boolean l_blnIsNewOpenTradingPowerAvailable =
                l_impl.isNewOpenTradingPowerAvailable(l_ifoDepositCalc, 5);

            assertTrue(l_blnIsNewOpenTradingPowerAvailable);
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
     * create�w���ʏ؋������ږ��׈ꗗ
     * �V�K���]�͉\�ȏꍇ�iis�V�K���]�͉\() == true
     �w���ʐ敨OP���ʏW�v�}�b�v���w�肳�ꂽ�����Y�����R�[�h�̃}�b�s���O��ێ�����ꍇ
     */
    public void testCreateIfoDepositTranRefPerIndexUnitList0001()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositTranRefPerIndexUnitList0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalc l_ifoDepositCalc = new WEB3IfoDepositCalc();
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(true);
            l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
            l_ifoDepositCalcCondition.setMinIfoDeposit(-1);
            
            WEB3IfoDepositCalcConditionPerIndex l_perIndex = new WEB3IfoDepositCalcConditionPerIndex();
            l_perIndex.setUnderlyingProductCode("11111");
            l_perIndex.setIfoDepositPerUnit(100);
            WEB3IfoDepositCalcConditionPerIndex[] l_l_perIndexs = new WEB3IfoDepositCalcConditionPerIndex[1];
            l_l_perIndexs[0] = l_perIndex;
            l_ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_l_perIndexs);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field.setAccessible(true);
            field.set(l_ifoDepositCalc,l_ifoDepositCalcCondition);

            WEB3IfoSummaryContractPerIndex[] l_summaryContractPerIndexs =
                new WEB3IfoSummaryContractPerIndex[1];
            WEB3IfoSummaryContractPerIndex l_summaryContractPerIndex = new WEB3IfoSummaryContractPerIndex();
            l_summaryContractPerIndex.setTargetProductCode("11111");
            l_summaryContractPerIndexs[0] = l_summaryContractPerIndex;
            
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field1.setAccessible(true);
            field1.set(l_ifoDepositCalc,l_summaryContractPerIndexs);
            
            WEB3IfoDepositTransitionReferenceServiceImpl l_impl =
                new WEB3IfoDepositTransitionReferenceServiceImpl();

            WEB3IfoDepositTranRefPerIndexUnit[] l_units = 
                l_impl.createIfoDepositTranRefPerIndexUnitList(l_ifoDepositCalc, 5);
            
            assertEquals(1, l_units.length);
            assertEquals("11111", l_units[0].targetProductCode);
            assertEquals(null, l_units[0].bullQuantity);
            assertEquals(null, l_units[0].bearQuantity);
            assertEquals("0", l_units[0].longPositionContract);
            assertEquals("0", l_units[0].partLongPositionContract);
            assertEquals("0", l_units[0].shortPositionContract);
            assertEquals("0", l_units[0].partShortPositionContract);            
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
     * create�w���ʏ؋������ږ��׈ꗗ
     * �V�K���]�͉\�ȏꍇ�iis�V�K���]�͉\() == fasle
         �w���ʐ敨OP���ʏW�v�}�b�v���w�肳�ꂽ�����Y�����R�[�h�̃}�b�s���O��ێ����Ȃ��̏ꍇ
     */
    public void testCreateIfoDepositTranRefPerIndexUnitList0002()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositTranRefPerIndexUnitList0002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalc l_ifoDepositCalc = new WEB3IfoDepositCalc();
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            l_ifoDepositCalcCondition.setNewOpenTradingPowerAvailableFlag(false);
            l_ifoDepositCalcCondition.setLackChargeNonManagementFlag(true);
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(true);
            l_ifoDepositCalcCondition.setSpanTroubleFlag(false);
            
            WEB3IfoDepositCalcConditionPerIndex l_perIndex = new WEB3IfoDepositCalcConditionPerIndex();
            l_perIndex.setUnderlyingProductCode("00");
            l_perIndex.setIfoDepositPerUnit(100);
            WEB3IfoDepositCalcConditionPerIndex[] l_l_perIndexs = new WEB3IfoDepositCalcConditionPerIndex[1];
            l_l_perIndexs[0] = l_perIndex;
            l_ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_l_perIndexs);
            
            Field field = WEB3IfoDepositCalc.class.getDeclaredField("ifoDepositCalcCondition");
            field.setAccessible(true);
            field.set(l_ifoDepositCalc,l_ifoDepositCalcCondition);

            WEB3IfoSummaryContractPerIndex[] l_summaryContractPerIndexs =
                new WEB3IfoSummaryContractPerIndex[1];
            WEB3IfoSummaryContractPerIndex l_summaryContractPerIndex = new WEB3IfoSummaryContractPerIndex();
            l_summaryContractPerIndex.setTargetProductCode("11111");
            l_summaryContractPerIndexs[0] = l_summaryContractPerIndex;
            
            Field field1 = WEB3IfoDepositCalc.class.getDeclaredField("ifoSummaryContractPerIndexList");
            field1.setAccessible(true);
            field1.set(l_ifoDepositCalc,l_summaryContractPerIndexs);
            
            WEB3IfoDepositTransitionReferenceServiceImpl l_impl =
                new WEB3IfoDepositTransitionReferenceServiceImpl();

            WEB3IfoDepositTranRefPerIndexUnit[] l_units = 
                l_impl.createIfoDepositTranRefPerIndexUnitList(l_ifoDepositCalc, 5);
            
            assertEquals(1, l_units.length);
            assertEquals("00", l_units[0].targetProductCode);
            assertEquals("0", l_units[0].bullQuantity);
            assertEquals("0", l_units[0].bearQuantity);
            assertEquals("0", l_units[0].longPositionContract);
            assertEquals("0", l_units[0].partLongPositionContract);
            assertEquals("0", l_units[0].shortPositionContract);
            assertEquals("0", l_units[0].partShortPositionContract);  

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecute()
    {
        final String STR_METHOD_NAME = "testExecute";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            l_bln = false;
            WEB3IfoDepositTransitionReferenceServiceImplForTest l_impl =
                new WEB3IfoDepositTransitionReferenceServiceImplForTest();
            WEB3GenRequest l_request = new WEB3IfoDepositTransitionReferenceRequest();
            WEB3GenResponse l_genResponse = (WEB3GenResponse)l_impl.execute(l_request);
            assertEquals(null,l_genResponse);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

    }

    public class WEB3IfoDepositTransitionReferenceServiceImplForTest
        extends WEB3IfoDepositTransitionReferenceServiceImpl
    {
        protected WEB3IfoDepositTransitionReferenceUnit[] createIfoDepositTransitionReferences(
            WEB3IfoDepositCalc l_ifoDepositCalc)
        {
            WEB3IfoDepositTransitionReferenceUnit[] l_unit = new WEB3IfoDepositTransitionReferenceUnit[1];
            l_unit[0] = new WEB3IfoDepositTransitionReferenceUnit();
            
            
            return l_unit;
        } 
        
        protected WEB3IfoDepositPerIndexUnit[] createIfoDepositPerIndexUnitList(
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition)
        {
            return null;
        }

        protected WEB3IfoDepositTransitionReferenceResponse createIfoDepositTransitionReferenceResponse(
                WEB3IfoDepositTransitionReferenceRequest l_request) throws WEB3BaseException
        {
            if (l_bln)
            {
                WEB3IfoDepositTransitionReferenceResponse l_response = super.createIfoDepositTransitionReferenceResponse(
                        l_request);
                return l_response;
            }
            else
            {
                return null; 
            }
            
        }
    }  
}
@
