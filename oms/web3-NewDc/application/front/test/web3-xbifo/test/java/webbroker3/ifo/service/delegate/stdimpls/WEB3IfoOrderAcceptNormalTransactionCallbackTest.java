head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoOrderAcceptNormalTransactionCallbackTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP������t�ꌏTransactionCallback�e�X�g(WEB3IfoOrderAcceptNormalTransactionCallbackTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/02 ���� (���u) �V�K�쐬 �d�l�ύX ���f��605
*/
package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.ifo.data.HostFotypeOrderAcceptParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�敨OP������t�ꌏTransactionCallback�e�X�g�j<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3IfoOrderAcceptNormalTransactionCallbackTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3IfoOrderAcceptNormalTransactionCallbackTest.class);

    WEB3IfoOrderAcceptUnitServiceImpl l_impl = new WEB3IfoOrderAcceptUnitServiceImplForMock();
    
    public WEB3IfoOrderAcceptNormalTransactionCallbackTest(String arg0)
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

    WEB3IfoOrderAcceptNormalTransactionCallback callback = null;
    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptNormalTransactionCallback.process()'
     */
    public void testProcess_1()
    {
        final String STR_METHOD_NAME = "testProcess_1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImpl",
            "notifyOrderAccept", new Class[] {HostFotypeOrderAcceptParams.class},
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME));
        HostFotypeOrderAcceptParams l_params = null;

        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_params = new HostFotypeOrderAcceptParams();
            l_params.setRequestCode("EI80A");
            l_params.setInstitutionCode("");
            l_params.setBranchCode("");
            l_params.setAccountCode("");
            l_params.setTraderCode("");
            l_params.setOrderRequestNumber("111");
            l_params.setAcceptStatus("1");
            l_params.setStatus("");
            l_params.setSubmitOrderRouteDiv("");
            l_QueryProcessor.doInsertQuery(l_params);
            
            callback = new WEB3IfoOrderAcceptNormalTransactionCallback(l_params);
            callback.process();
            
            TestDBUtility.deleteAll(l_params.getRowType());
        }
        catch(DataCallbackException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getDetails());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            try
            {
                TestDBUtility.deleteAll(l_params.getRowType());
            }
            catch (WEB3BaseException e)
            {
                // TODO Auto-generated catch block
            }
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testProcess_2()
    {
        final String STR_METHOD_NAME = "testProcess_2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImpl",
            "notifyOrderAccept", new Class[] {HostFotypeOrderAcceptParams.class},
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME));
        HostFotypeOrderAcceptParams l_params = null;

        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_params = new HostFotypeOrderAcceptParams();
            l_params.setRequestCode("EI80A");
            l_params.setInstitutionCode("");
            l_params.setBranchCode("");
            l_params.setAccountCode("");
            l_params.setTraderCode("");
            l_params.setOrderRequestNumber("111");
            l_params.setAcceptStatus("2");
            l_params.setStatus("");
            l_params.setSubmitOrderRouteDiv("");
            l_QueryProcessor.doInsertQuery(l_params);
            
            callback = new WEB3IfoOrderAcceptNormalTransactionCallback(l_params);
            callback.process();
            
            TestDBUtility.deleteAll(l_params.getRowType());
        }
        catch(DataCallbackException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getDetails());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            try
            {
                TestDBUtility.deleteAll(l_params.getRowType());
            }
            catch (WEB3BaseException e)
            {
                // TODO Auto-generated catch block
            }
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testProcess_3()
    {
        final String STR_METHOD_NAME = "testProcess_3()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImpl",
            "notifyOrderAcceptOvertime", new Class[] {HostFotypeOrderAcceptParams.class},
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME));
        HostFotypeOrderAcceptParams l_params = null;

        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_params = new HostFotypeOrderAcceptParams();
            l_params.setRequestCode("EI80A");
            l_params.setInstitutionCode("");
            l_params.setBranchCode("");
            l_params.setAccountCode("");
            l_params.setTraderCode("");
            l_params.setOrderRequestNumber("111");
            l_params.setAcceptStatus("3");
            l_params.setStatus("");
            l_params.setSubmitOrderRouteDiv("");
            l_QueryProcessor.doInsertQuery(l_params);
            
            callback = new WEB3IfoOrderAcceptNormalTransactionCallback(l_params);
            callback.process();
            
            TestDBUtility.deleteAll(l_params.getRowType());
        }
        catch(DataCallbackException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getDetails());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            try
            {
                TestDBUtility.deleteAll(l_params.getRowType());
            }
            catch (WEB3BaseException e)
            {
                // TODO Auto-generated catch block
            }
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
