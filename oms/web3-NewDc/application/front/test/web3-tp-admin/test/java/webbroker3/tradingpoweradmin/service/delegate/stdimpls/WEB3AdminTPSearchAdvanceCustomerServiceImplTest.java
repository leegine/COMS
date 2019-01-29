head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.38.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTPSearchAdvanceCustomerServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3AdminTPSearchAdvanceCustomerServiceImplTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/10/20 �������i���u�j
 */
package webbroker3.tradingpoweradmin.service.delegate.stdimpls;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailRow;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.data.TpCalcResultMarginRow;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerDetailRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerDetailResponse;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;

public class WEB3AdminTPSearchAdvanceCustomerServiceImplTest extends
		TestBaseForMock {

	public WEB3AdminTPSearchAdvanceCustomerServiceImplTest(String arg0) {
		super(arg0);
	}

	private static WEB3LogUtility log = WEB3LogUtility
			.getInstance(WEB3AdminTPSearchAdvanceCustomerServiceImplTest.class);

	protected void setUp() throws Exception {
		TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
		Services.overrideService(OpLoginSecurityService.class, new OpLoginSecurityServiceImplForMock());
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * get�ۏ؋��ێ�������/���֋������ڋq�ڍ� �ڋq���� =0 �̏ꍇ ���Y�]�͏��<�M�p�ڋq>�擾�AT+0�I�������Z�b
	 */
	public void test_getAdvanceCustomerDetail_0001() {
		final String STR_METHOD_NAME = "test_getAdvanceCustomerDetail_0001()";
		log.entering(STR_METHOD_NAME);

		WEB3AdminTPAdvanceCustomerDetailRequest l_request = new WEB3AdminTPAdvanceCustomerDetailRequest();

		WEB3AdminTPAdvanceCustomerDetailResponse l_response = new WEB3AdminTPAdvanceCustomerDetailResponse();

		WEB3AdminTPSearchAdvanceCustomerServiceImpl l_impl = new WEB3AdminTPSearchAdvanceCustomerServiceImpl();
		
		// ���N�G�X�g�̑����Ó����`�F�b�N
		// �ڋq����
		l_request.customerAttribute = "1";
		// �]�͌v�Z����ID
		l_request.calcResultId = "1001";
		// ��p�]���ቺ��
		l_request.substituteValuationDropRate = "11.30";

		// ���ʏ��o��iT+0�j
		String contractTotalCost = "1";

		try {
			TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
			
			LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImplForMock);
            
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
            //�����h�c
            l_subAccountParams.setAccountId(333812512246L);
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

			l_response = l_impl.getAdvanceCustomerDetail(l_request);

			assertEquals(l_response.advanceCustomerDetailUnits[0].contractTotalCost, contractTotalCost);

		} catch (WEB3BaseException e) {
			fail();
			log.exiting(STR_METHOD_NAME);
		}

	}

	/**
	 * get�ۏ؋��ێ�������/���֋������ڋq�ڍ� �ڋq���� =0 �̏ꍇ ���Y�]�͏��<�M�p�ڋq>�擾�AT+5�I�������Z�b
	 */
	public void test_getAdvanceCustomerDetail_0002() {

		final String STR_METHOD_NAME = "test_getAdvanceCustomerDetail_0002()";
		log.entering(STR_METHOD_NAME);

        WEB3AdminTPAdvanceCustomerDetailRequest l_request = new WEB3AdminTPAdvanceCustomerDetailRequest();

		WEB3AdminTPAdvanceCustomerDetailResponse l_response = new WEB3AdminTPAdvanceCustomerDetailResponse();

		WEB3AdminTPSearchAdvanceCustomerServiceImpl l_impl = new WEB3AdminTPSearchAdvanceCustomerServiceImpl();
		
		// ���N�G�X�g�̑����Ó����`�F�b�N
		// �ڋq����
		l_request.customerAttribute = "1";
		// �]�͌v�Z����ID
		l_request.calcResultId = "1001";
		// ��p�]���ቺ��
		l_request.substituteValuationDropRate = "11.30";

		// ���ʏ��o��iT+0�j
		String contractTotalCost0 = "0";
		String contractTotalCost1 = "1";
		String contractTotalCost2 = "2";
		String contractTotalCost3 = "3";
		String contractTotalCost4 = "4";
		String contractTotalCost5 = "5";

		try {
			TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
			
			LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImplForMock);

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
            //�����h�c
            l_subAccountParams.setAccountId(333812512246L);
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

			l_response = l_impl.getAdvanceCustomerDetail(l_request);
			
			assertEquals(l_response.advanceCustomerDetailUnits[0].contractTotalCost, contractTotalCost0);
			assertEquals(l_response.advanceCustomerDetailUnits[1].contractTotalCost, contractTotalCost1);
			assertEquals(l_response.advanceCustomerDetailUnits[2].contractTotalCost, contractTotalCost2);
			assertEquals(l_response.advanceCustomerDetailUnits[3].contractTotalCost, contractTotalCost3);
			assertEquals(l_response.advanceCustomerDetailUnits[4].contractTotalCost, contractTotalCost4);
			assertEquals(l_response.advanceCustomerDetailUnits[5].contractTotalCost, contractTotalCost5);
			
		} catch (WEB3BaseException e) {
			
			fail();
			log.exiting(STR_METHOD_NAME);
		}

	
	}

}
@
