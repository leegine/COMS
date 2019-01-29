head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.10.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityAssetInquiryServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3EquityAssetInquiryServiceInterceptorTest.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2008/07/04 �k�v�u (���u) �V�K�쐬
*/
package webbroker3.equity;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.equity.message.WEB3EquitySellListRequest;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityAssetInquiryServiceInterceptorTest extends
		TestBaseForMock {

	/**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3EquityAssetInquiryServiceInterceptorTest.class);

    private WEB3EquityAssetInquiryServiceInterceptor assetInquiryServiceInterceptor = null;

	public WEB3EquityAssetInquiryServiceInterceptorTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		MOCK_MANAGER.setIsMockUsed(true);
	    this.assetInquiryServiceInterceptor = new WEB3EquityAssetInquiryServiceInterceptor();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	//l_request is not instanceof WEB3EquitySellListRequest
	public void testOnCall_C0001()
	{

        final String STR_METHOD_NAME = "testOnCall_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	Object[] l_serviceParam = new Object[]{new WEB3EquityAssetInquiryServiceInterceptor()};
        	assertNull("onCall(null,l_serviceParam) is null?", assetInquiryServiceInterceptor.onCall(null,l_serviceParam));
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
     }
	// ����J�����_�R���e�L�X�g.�s��R�[�h = ���N�G�X�g�f�[�^���ҏW
	public void testOnCall_C0002()
	{

        final String STR_METHOD_NAME = "testOnCall_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	WEB3EquitySellListRequest listRequest = new WEB3EquitySellListRequest();
        	listRequest.marketCode = "001"; 
        	Object[] l_serviceParam = new Object[]{listRequest};
        	
        	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(123));
            
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                    "getSystemTimestamp",
                    new Class[]
                    {}, l_tsOrderAcceptTime);
            
            // �����\���I�ɝ�,?�����\������\
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            // �ݒu���l��\���I�ɝ�
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            // ����`�ɝ��ݒu
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(111);
            l_mainAccountParams.setInstitutionCode("0A");
            // ��DB�������ɝ�
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(111);
            l_institutionParams.setInstitutionCode("0A");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(111);
            l_branchParams.setInstitutionCode("0A");
            TestDBUtility.insertWithDel(l_branchParams);

            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "2");
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0A");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("2");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context = (WEB3GentradeTradingClendarContext) assetInquiryServiceInterceptor.onCall(null, l_serviceParam);
            assertEquals("001", l_context.getMarketCode());
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
     }
	
	//����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
	public void testOnCall_C0003()
	{

        final String STR_METHOD_NAME = "testOnCall_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	WEB3EquitySellListRequest listRequest = new WEB3EquitySellListRequest();
        	listRequest.marketCode = "001"; 
        	Object[] l_serviceParam = new Object[]{listRequest};
        	
        	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(123));
            
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                    "getSystemTimestamp",
                    new Class[]
                    {}, l_tsOrderAcceptTime);
            
            // �����\���I�ɝ�,?�����\������\
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            // �ݒu���l��\���I�ɝ�
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            // ����`�ɝ��ݒu
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(111);
            l_mainAccountParams.setInstitutionCode("0A");
            // ��DB�������ɝ�
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(111);
            l_institutionParams.setInstitutionCode("0A");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(111);
            l_branchParams.setInstitutionCode("0A");
            TestDBUtility.insertWithDel(l_branchParams);

            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "2");
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0A");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("2");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context = (WEB3GentradeTradingClendarContext) assetInquiryServiceInterceptor.onCall(null, l_serviceParam);
            assertEquals("0A", l_context.getInstitutionCode());
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
     }
	
	// ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
	public void testOnCall_C0004()
	{

        final String STR_METHOD_NAME = "testOnCall_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	WEB3EquitySellListRequest listRequest = new WEB3EquitySellListRequest();
        	listRequest.marketCode = "001"; 
        	Object[] l_serviceParam = new Object[]{listRequest};
        	
        	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(123));
            
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                    "getSystemTimestamp",
                    new Class[]
                    {}, l_tsOrderAcceptTime);
            
            // �����\���I�ɝ�,?�����\������\
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            // �ݒu���l��\���I�ɝ�
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            // ����`�ɝ��ݒu
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(111);
            l_mainAccountParams.setInstitutionCode("0A");
            // ��DB�������ɝ�
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(111);
            l_institutionParams.setInstitutionCode("0A");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(111);
            l_branchParams.setInstitutionCode("0A");
            TestDBUtility.insertWithDel(l_branchParams);

            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "2");
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0A");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("2");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context = (WEB3GentradeTradingClendarContext) assetInquiryServiceInterceptor.onCall(null, l_serviceParam);
            assertEquals("381", l_context.getBranchCode());
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