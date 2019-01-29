head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.19.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionOpenContractOrderHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3OptionOpenContractOrderHandlerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/10 �����i���u�j�V�K�쐬
*/
package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3ComplianceInfoUnit;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsOpenMarginConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractOrderService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionOpenContractOrderHandlerTest extends TestBaseForMock
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionOpenContractOrderHandlerTest.class);
    
    private WEB3OptionOpenContractOrderHandler l_handler = null;
    
    private WEB3OptionsOpenMarginConfirmRequest l_request = null;
    
    private WEB3OptionsOpenMarginCompleteRequest l_requestComplete = null;
    
    private WEB3OptionsOpenMarginConfirmResponse l_responseConfirm = null;
    
    private WEB3OptionsOpenMarginCompleteResponse l_responseComplete = null;
    
    private WEB3OptionOpenContractOrderService l_service = null;

    public WEB3OptionOpenContractOrderHandlerTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_handler = new WEB3OptionOpenContractOrderHandler();
        this.l_request = new WEB3OptionsOpenMarginConfirmRequest();
        this.l_requestComplete = new WEB3OptionsOpenMarginCompleteRequest();
    }
    
    protected void tearDown() throws Exception
    {
        this.l_handler = null;
        this.l_request = null;
        super.tearDown();
    }
    
    /**
     * �����w���I�v�V�����V�K�������T�[�r�X�擾�Ɏ��s���܂����B
     * �e�o�ُ�M���FWEB3ErrorCatalog.SYSTEM_ERROR_80002
     *
     */
    public void testConfirmOrder_C0001()
    {
        String STR_METHOD_NAME = "testConfirmOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            l_service = (WEB3OptionOpenContractOrderService)Services.getService(WEB3OptionOpenContractOrderService.class);
            Services.unregisterService(WEB3OptionOpenContractOrderService.class);
            this.l_responseConfirm = this.l_handler.confirmOrder(this.l_request);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_responseConfirm.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3OptionOpenContractOrderService.class,l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �����w���I�v�V�����V�K�������̔����R���Ɏ��s���܂����B
     * �e�o�ُ�M���FWEB3ErrorCatalog.SYSTEM_ERROR_80017
     *
     */
    public void testConfirmOrder_C0002()
    {
        String STR_METHOD_NAME = "testConfirmOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractOrderServiceImpl", 
                    "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME));
            this.l_responseConfirm = this.l_handler.confirmOrder(this.l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_responseConfirm.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ����ԉ�
     *
     */
    public void testConfirmOrder_C0003()
    {
        String STR_METHOD_NAME = "testConfirmOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            WEB3OptionsOpenMarginConfirmResponse l_expectResponse = new WEB3OptionsOpenMarginConfirmResponse();
            l_expectResponse.checkPrice = "2560";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractOrderServiceImpl", 
                    "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_expectResponse);
            this.l_responseConfirm = this.l_handler.confirmOrder(this.l_request);
            
            assertNotNull(this.l_responseConfirm);
            assertEquals(WEB3OptionsOpenMarginConfirmResponse.class,this.l_responseConfirm.getClass());
            assertEquals("2560",this.l_responseConfirm.checkPrice);
           
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �����w���I�v�V�����V�K�������T�[�r�X�擾�Ɏ��s���܂����B
     * �e�o�ُ�M���FWEB3ErrorCatalog.SYSTEM_ERROR_80002
     *
     */
    public void testCompleteOrder_C0001()
    {
        String STR_METHOD_NAME = "testCompleteOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            l_service = (WEB3OptionOpenContractOrderService)Services.getService(WEB3OptionOpenContractOrderService.class);
            Services.unregisterService(WEB3OptionOpenContractOrderService.class);
            this.l_responseComplete = this.l_handler.completeOrder(this.l_requestComplete);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_responseComplete.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3OptionOpenContractOrderService.class,l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �����w���I�v�V�����V�K�������̓o�^�Ɏ��s���܂����B
     * �e�o�ُ�M���FWEB3ErrorCatalog.SYSTEM_ERROR_80017
     *
     */
    public void testCompleteOrder_C0002()
    {
        String STR_METHOD_NAME = "testCompleteOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractOrderServiceImpl", 
                    "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME));
           
            this.l_responseComplete = this.l_handler.completeOrder(this.l_requestComplete);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_responseComplete.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ����ԉ�
     *
     */
    public void testCompleteOrder_C0003()
    {
        String STR_METHOD_NAME = "testCompleteOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            WEB3OptionsOpenMarginCompleteResponse l_expectResponse = new WEB3OptionsOpenMarginCompleteResponse();
            WEB3ComplianceInfoUnit l_unit = new WEB3ComplianceInfoUnit();
            l_unit.branchCode = "381";
            l_expectResponse.complianceInfo = l_unit;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractOrderServiceImpl", 
                    "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_expectResponse);
            this.l_responseComplete = this.l_handler.completeOrder(this.l_requestComplete);
            
            assertNotNull(this.l_responseComplete);
            assertEquals(WEB3OptionsOpenMarginCompleteResponse.class,this.l_responseComplete.getClass());
            assertEquals("381",this.l_responseComplete.complianceInfo.branchCode);
           
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
