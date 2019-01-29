head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.13.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginChangeCloseMarginUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3MarginChangeCloseMarginUpdateInterceptorTest.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2008/07/04 �k�v�u (���u) �V�K�쐬
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeContractSettleChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.InstitutionImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MarginChangeCloseMarginUpdateInterceptorTest extends
		TestBaseForMock {

	/**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3MarginChangeCloseMarginUpdateInterceptorTest.class);

    WEB3MarginChangeCloseMarginUpdateInterceptor l_interceptor = null;
    
	public WEB3MarginChangeCloseMarginUpdateInterceptorTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		MOCK_MANAGER.setIsMockUsed(true);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	//this.�M�p�ԍϒ����������e = null
	public void testMutate_C0001()
	{
		final String STR_METHOD_NAME = "testMutate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	l_interceptor = 
        		new WEB3MarginChangeCloseMarginUpdateInterceptor(null,null,null,null);
        	EqtypeOrderUnitParams l_orderUnitParams = null;
        	assertNull(l_interceptor.mutate(null,null,l_orderUnitParams));
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//�C���^�Z�v�^�̃v���p�e�B.�㗝���͎�==null�̏ꍇ
	//���� = �C���^�Z�v�^�̃v���p�e�B.�㗝���͎�.�����ID
	//��������!=DEFAULT �����������Z�q�A�t�w�l��l=null �iW�w�l�j�����w�l=this.�M�p�ԍϒ����������e.������(W�w�l)�����w�l 
	//�s�ꂩ��m�F�ς݂̐���==Double.NaN�i���s�ꖢ���M �p�����[�^.�����P��Params.������ԁiorderStatus�j�ɁhMODIFIED�h���Z�b�g
	//this.isUnexecuted( )==false�̏ꍇ�́A6�F�ꕔ���������B
	//��������!���i0�FDEFAULT�i�����w��Ȃ��j�A1�F�t�w�l�j�̏ꍇ �iW�w�l�j���s����=�M�p�ԍϒ����������e.get������iW�w�l�j���s����( )
	public void testMutate_C0002()
	{
		final String STR_METHOD_NAME = "testMutate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	long l_lngOrderId=0;
        	EqTypeContractSettleChangeOrderUnitEntry l_changeOrderUnitEntry =null;
        	WEB3MarginChangeSettleContractOrderSpec l_creditCloseMarginChangeUpdateSpec = 
        		new WEB3MarginChangeSettleContractOrderSpec(
        			l_lngOrderId,l_changeOrderUnitEntry); 
            WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice = new WEB3EquityRealizedProfitAndLossPrice();
            
            WEB3GentradeTrader l_trader=null;
            l_interceptor = 
        		new WEB3MarginChangeCloseMarginUpdateInterceptor(
    				l_creditCloseMarginChangeUpdateSpec,l_equityRealizedProfitAndLossPrice,null,l_trader);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setAccountId(0001);
            l_eqtypeOrderUnitParams.setMarketId(001);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                    "getChangeSubmitOrderRouteDiv",
                    new Class[] {EqTypeOrderUnit.class},
                    new String("0001"));
        	assertEquals(0,l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getTraderId());
        	assertEquals(null,l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getOrderCondOperator());
        	assertEquals(0,(long)l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getStopOrderPrice());
        	assertEquals(OrderStatusEnum.MODIFIED,l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getOrderStatus());
        	assertEquals(WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED,l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getModifyCancelType());
        	assertEquals(null,l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getWLimitExecCondType());
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//�C���^�Z�v�^�̃v���p�e�B.�㗝���͎�!=null�̏ꍇ
	public void testMutate_C0003()
	{
		final String STR_METHOD_NAME = "testMutate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	long l_lngTraderId=0,l_lngOrderId=0;
        	EqTypeContractSettleChangeOrderUnitEntry l_changeOrderUnitEntry =null;
        	WEB3MarginChangeSettleContractOrderSpec l_creditCloseMarginChangeUpdateSpec = 
        		new WEB3MarginChangeSettleContractOrderSpec(
        			l_lngOrderId,l_changeOrderUnitEntry); 
            WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice = new WEB3EquityRealizedProfitAndLossPrice();
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(0001); 
            l_traderParams.setTraderCode("001");
            l_traderParams.setBranchCode("001");
            l_traderParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(001); 
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            long l_lnginstId = 001;
            InstitutionImpl l_institution= new InstitutionImpl(l_lnginstId);
            String l_strTraderCode = "001";
            String l_strBranchCode = "001";
            WEB3GentradeTrader l_trader= new WEB3GentradeTrader(l_institution, l_strTraderCode, l_strBranchCode);
            l_interceptor = 
        		new WEB3MarginChangeCloseMarginUpdateInterceptor(
    				l_creditCloseMarginChangeUpdateSpec,l_equityRealizedProfitAndLossPrice,null,l_trader);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setAccountId(0001);
            l_eqtypeOrderUnitParams.setMarketId(001);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                    "getChangeSubmitOrderRouteDiv",
                    new Class[] {EqTypeOrderUnit.class},
                    new String("0001"));
            l_lngTraderId = l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getTraderId();
        	assertEquals(1,l_lngTraderId);
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//��������=default
	//�����������Z�q�A�t�w�l��l
	//�iW�w�l�j�����w�l = null
	//W�w�l�j���s���� = null
	public void testMutate_C0004()
	{
		final String STR_METHOD_NAME = "testMutate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	long l_lngOrderId=0;
        	EqTypeContractSettleChangeOrderUnitEntry l_changeOrderUnitEntry =null;
        	WEB3MarginChangeSettleContractOrderSpec l_creditCloseMarginChangeUpdateSpec = 
        		new WEB3MarginChangeSettleContractOrderSpec(
        			l_lngOrderId,l_changeOrderUnitEntry); 
            WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice = new WEB3EquityRealizedProfitAndLossPrice();
            
            WEB3GentradeTrader l_trader=null;
            l_interceptor = 
        		new WEB3MarginChangeCloseMarginUpdateInterceptor(
    				l_creditCloseMarginChangeUpdateSpec,l_equityRealizedProfitAndLossPrice,null,l_trader);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setAccountId(0001);
            l_eqtypeOrderUnitParams.setMarketId(001);
            l_eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                    "getChangeSubmitOrderRouteDiv",
                    new Class[] {EqTypeOrderUnit.class},
                    new String("0001"));
        	assertNull(l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getOrderCondOperator());
        	assertNull(l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getWLimitExecCondType());
        	assertEquals(0,(long)l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getWLimitPrice());
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//�s�ꂩ��m�F�ς݂̐���==Double.NaN�i���s�ꖢ���M�j 
	//���������E����敪
	//this.isUnexecuted( )==true�̏ꍇ�́A7�F�S�����������B
	public void testMutate_C0005()
	{
		final String STR_METHOD_NAME = "testMutate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	long l_lngOrderId=0;
        	EqTypeContractSettleChangeOrderUnitEntry l_changeOrderUnitEntry =null;
        	WEB3MarginChangeSettleContractOrderSpec l_creditCloseMarginChangeUpdateSpec = 
        		new WEB3MarginChangeSettleContractOrderSpec(
        			l_lngOrderId,l_changeOrderUnitEntry); 
            WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice = new WEB3EquityRealizedProfitAndLossPrice();
            
            WEB3GentradeTrader l_trader=null;
            l_interceptor = 
        		new WEB3MarginChangeCloseMarginUpdateInterceptor(
    				l_creditCloseMarginChangeUpdateSpec,l_equityRealizedProfitAndLossPrice,null,l_trader);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setAccountId(0001);
            l_eqtypeOrderUnitParams.setMarketId(001);
            l_eqtypeOrderUnitParams.setExecutedQuantity(0);
            l_eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                    "getChangeSubmitOrderRouteDiv",
                    new Class[] {EqTypeOrderUnit.class},
                    new String("0001"));
        	assertEquals(WEB3ModifyCancelTypeDef.CHANGED,l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getModifyCancelType());
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//�s�ꂩ��m�F�ς݂̐���!=Double.NaN�i���s�ꑗ�M�ρj
	//�������
    //�p�����[�^.�����P��Params.������ԁiorderStatus�j�ɁhMODIFY_ACCEPTED�h���Z�b�g
	//���������E����敪 = 5:������
	public void testMutate_C0006()
	{
		final String STR_METHOD_NAME = "testMutate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	long l_lngOrderId=0;
        	EqTypeContractSettleChangeOrderUnitEntry l_changeOrderUnitEntry =null;
        	WEB3MarginChangeSettleContractOrderSpec l_creditCloseMarginChangeUpdateSpec = 
        		new WEB3MarginChangeSettleContractOrderSpec(
        			l_lngOrderId,l_changeOrderUnitEntry); 
            WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice = new WEB3EquityRealizedProfitAndLossPrice();
            
            WEB3GentradeTrader l_trader=null;
            l_interceptor = 
        		new WEB3MarginChangeCloseMarginUpdateInterceptor(
    				l_creditCloseMarginChangeUpdateSpec,l_equityRealizedProfitAndLossPrice,null,l_trader);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setAccountId(0001);
            l_eqtypeOrderUnitParams.setBranchId(001);
            l_eqtypeOrderUnitParams.setMarketId(001);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(1111);
            l_eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                    "getChangeSubmitOrderRouteDiv",
                    new Class[] {EqTypeOrderUnit.class},
                    new String("0001"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                    "getChangeOrderRev",
                    new Class[] {EqTypeOrderUnit.class},
                    null);
            
            assertEquals(OrderStatusEnum.MODIFY_ACCEPTED,l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getOrderStatus());
        	assertEquals(WEB3ModifyCancelTypeDef.CHANGING,l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getModifyCancelType());
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//�X�V�l�ݒ�
	//super.mutate()�����{����
	public void testMutate_C0007()
	{
		final String STR_METHOD_NAME = "testMutate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	long l_lngOrderId=0;
        	EqTypeContractSettleChangeOrderUnitEntry l_changeOrderUnitEntry =null;
        	WEB3MarginChangeSettleContractOrderSpec l_creditCloseMarginChangeUpdateSpec = 
        		new WEB3MarginChangeSettleContractOrderSpec(
        			l_lngOrderId,l_changeOrderUnitEntry); 
            WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice = new WEB3EquityRealizedProfitAndLossPrice();
            
            WEB3GentradeTrader l_trader=null;
        	l_interceptor = 
        		new WEB3MarginChangeCloseMarginUpdateInterceptor(
    				l_creditCloseMarginChangeUpdateSpec,l_equityRealizedProfitAndLossPrice,null,l_trader);

        	EqtypeOrderActionParams l_actionParams = null;
            l_interceptor.mutate(null,null,l_actionParams);
            fail();
	    }
        catch(WEB3BaseRuntimeException l_ex)
        {
        	assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//�����C�x���g�^�C�v = 2:�ύX����
	public void testMutate_C0008()
	{
		final String STR_METHOD_NAME = "testMutate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	long l_lngOrderId=0;
        	EqTypeContractSettleChangeOrderUnitEntry l_changeOrderUnitEntry =null;
        	WEB3MarginChangeSettleContractOrderSpec l_creditCloseMarginChangeUpdateSpec = 
        		new WEB3MarginChangeSettleContractOrderSpec(
        			l_lngOrderId,l_changeOrderUnitEntry); 
            WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice = new WEB3EquityRealizedProfitAndLossPrice();
            
            WEB3GentradeTrader l_trader=null;
        	l_interceptor = 
        		new WEB3MarginChangeCloseMarginUpdateInterceptor(
    				l_creditCloseMarginChangeUpdateSpec,l_equityRealizedProfitAndLossPrice,null,l_trader);

        	 TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
             EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
             l_eqtypeOrderUnitParams.setAccountId(0001);
             l_eqtypeOrderUnitParams.setBranchId(001);
             l_eqtypeOrderUnitParams.setOrderUnitId(001);
             l_eqtypeOrderUnitParams.setTraderId(001);
             l_eqtypeOrderUnitParams.setConfirmedQuantity(1111);
             l_eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
             TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
        	
        	TestDBUtility.deleteAll(EqtypeOrderActionRow.TYPE);
        	EqtypeOrderActionParams l_actionParams = TestDBUtility.getEqtypeOrderActionRow();
        	l_actionParams.setAccountId(0001);
        	l_actionParams.setOrderUnitId(001);
            TestDBUtility.insertWithDel(l_actionParams);

        	assertEquals(OrderEventTypeEnum.CHANGE,l_interceptor.mutate(null,null,l_actionParams).getOrderEventType());
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

}
@
