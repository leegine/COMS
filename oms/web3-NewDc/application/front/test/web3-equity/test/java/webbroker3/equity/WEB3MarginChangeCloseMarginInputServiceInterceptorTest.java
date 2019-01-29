head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.13.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginChangeCloseMarginInputServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : *�M�p��������ԍϓ��̓T�[�r�X�C���^�Z�v�^�N���XTeat<BR>
                 :(WEB3MarginChangeCloseMarginInputServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/02/25 (���u) �V�K�쐬
*/
package webbroker3.equity;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;

import test.util.TestDBUtility;

import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeInputRequest;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeInputRequest;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3MarginChangeCloseMarginInputServiceInterceptorTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
                 WEB3MarginChangeCloseMarginInputServiceInterceptorTest.class);

    public WEB3MarginChangeCloseMarginInputServiceInterceptorTest(String arg0)
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

    /*
     * Test method for 'webbroker3.equity.WEB3MarginChangeCloseMarginInputServiceInterceptor.onCall(Method, Object[])'
     */
    /**
     * ���N�G�X�g�f�[�^���ȉ��̃C���X�^���X�łȂ��ꍇ�̂݁A�������s���B 
     * �E"�i�A���j�M�p����ԍϒ������̓��N�G�X�g" 
     * �M�p����N���C�A���g���N�G�X�g�T�[�r�X.get�㗝���͎�()�̖߂�l��null
     * �g�����������}�l�[�W��.is�������ϒ���()��false(*2)�̏ꍇ
     *
     */
    public void test_oncall_0001()
    {
        final String STR_METHOD_NAME = "test_onCall_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
             new Class[] {}, new Long(333812512246L));

        try
        {
            WEB3MarginChangeCloseMarginInputServiceInterceptor l_interceptor = new WEB3MarginChangeCloseMarginInputServiceInterceptor();
            // �e�[�u���փf�[�^���C���T�[�g
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long("3338111123"));

            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            EqtypeOrderUnitParams l_orderUnit = TestDBUtility.getEqtypeOrderUnitRow();
            l_orderUnit.setOrderId(1001);
            TestDBUtility.insertWithDel(l_orderUnit);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            WEB3MarginCloseMarginChangeInputRequest l_request = new WEB3MarginCloseMarginChangeInputRequest();
            
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {}, 
                    l_tsOrderAcceptTime);
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "1");
            
            l_request.id = "1001";
            Object[] l_serviceParams = {l_request};
            l_interceptor.onCall(null, l_serviceParams);

            // �\�z���ʂƎ��ی��ʂ̔�r
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            Integer l_offset = (Integer)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG);
            assertEquals("0", l_offset.toString());
            
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertNull(l_context.getMarketCode());
            assertEquals("01", l_context.getTradingTimeType());
            assertEquals("0", l_context.getProductCode());

            //??�X�_��?��
//            BooleanEnum l_bln = (BooleanEnum)ThreadLocalSystemAttributesRegistry.getAttribute(
//                WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP);
//            assertNull(l_bln);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
 
    
    /**
     * 
     */
    public void test_onReturn_0001()
    {
        final String STR_METHOD_NAME = "test_onReturn_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3MarginChangeCloseMarginInputServiceInterceptor l_interceptor = new WEB3MarginChangeCloseMarginInputServiceInterceptor();
        l_interceptor.onReturn(null,null);
        BooleanEnum l_bln = (BooleanEnum)ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP);
        assertNull(l_bln);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     */
    public void test_onThrowable_0001()
    {
        final String STR_METHOD_NAME = "test_onThrowable_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3MarginChangeCloseMarginInputServiceInterceptor l_interceptor = new WEB3MarginChangeCloseMarginInputServiceInterceptor();
        l_interceptor.onThrowable(null,null);
        BooleanEnum l_bln = (BooleanEnum)ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP);
        assertNull(l_bln);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
