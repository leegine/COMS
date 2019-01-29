head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.54.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqNettingExchangeServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import test.util.TestDBUtility;

import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqNettingExchangeServiceInterceptorTest extends TestBaseForMock
{

    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3FeqNettingExchangeServiceInterceptorTest.class);

    WEB3FeqNettingExchangeServiceInterceptor l_interceptor=
        new WEB3FeqNettingExchangeServiceInterceptor();

    public WEB3FeqNettingExchangeServiceInterceptorTest(String arg0)
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
     * Test method for 'webbroker3.feq.WEB3FeqNettingExchangeServiceInterceptor.onCall(Method, Object[])'
     */
    public void testOnCallCase1()
    {
        final String STR_METHOD_NAME = "testOnCallCase1()";
        log.entering(STR_METHOD_NAME);
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        //����J�����_�R���e�L�X�g.�،���ЃR�[�h = �Ǘ���.�،���ЃR�[�h
        l_context.setInstitutionCode("0D");

        //����J�����_�R���e�L�X�g.���X�R�[�h = �Ǘ���.���X�R�[�h
        l_context.setBranchCode("381");

        //����J�����_�R���e�L�X�g.�s��R�[�h = null
        l_context.setMarketCode("1");

        //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h10�F�O�������h
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);

        //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

        //����J�����_�R���e�L�X�g.������t���i = �h04�F�O�����h
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FOREIGN_STOCK);

        //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
        
        l_context.setBizDateType("1");
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context); 
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            l_interceptor.onCall(null,null);
            
            WEB3GentradeTradingClendarContext l_context1 =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertEquals("0D",l_context1.getInstitutionCode());
            assertEquals("381",l_context1.getBranchCode());
            assertNull(l_context1.getMarketCode());
            assertEquals("10",l_context1.getTradingTimeType());
            assertEquals("0",l_context1.getProductCode());
            assertEquals("04",l_context1.getOrderAcceptProduct());
            assertNull(l_context1.getOrderAcceptTransaction());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail(); 
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testOnReturnCase1()
    {
        final String STR_METHOD_NAME = "testOnReturnCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                "dfdsf");

            //������ԊǗ�.OFFSET_TAG
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG,
                "sadfwef");

            //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                "sdafwe");
            
            l_interceptor.onReturn(null, null);
            
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));

            //������ԊǗ�.OFFSET_TAG
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG));

            //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH));
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testOnThrowableCase1()
    {
        final String STR_METHOD_NAME = "testOnThrowableCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                "dfdsf");

            //������ԊǗ�.OFFSET_TAG
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG,
                "sadfwef");

            //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                "sdafwe");
            
            l_interceptor.onThrowable(null, null);
            
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));

            //������ԊǗ�.OFFSET_TAG
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG));

            //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH));
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

}
@
