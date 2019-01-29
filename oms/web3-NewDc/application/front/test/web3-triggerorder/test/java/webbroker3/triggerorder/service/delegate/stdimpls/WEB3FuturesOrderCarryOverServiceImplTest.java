head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.18.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesOrderCarryOverServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3OptionOrderCarryOverServiceImplESTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/06/29 ���� (���u) ���f��No.671
 */
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractOpenOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.triggerorder.message.WEB3FuturesOrderCarryOverRequest;
import webbroker3.triggerorder.message.WEB3FuturesOrderCarryOverResponse;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesOrderCarryOverServiceImplTest extends TestBaseForMock
{
    private WEB3FuturesOrderCarryOverServiceImpl l_serviceImpl = null;

    private WEB3FuturesOrderCarryOverRequest l_request = null;

    private WEB3FuturesOrderCarryOverResponse l_response = null;

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesOrderCarryOverServiceImplTest.class);

    public WEB3FuturesOrderCarryOverServiceImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_serviceImpl = new WEB3FuturesOrderCarryOverServiceImpl();
        this.l_request = new WEB3FuturesOrderCarryOverRequest();
    }

    protected void tearDown() throws Exception
    {
        this.l_serviceImpl = null;
        this.l_request = null;
        super.tearDown();
    }

    /**
     * 
     * �e�o�ُ�WEB3BaseException�M���FBUSINESS_ERROR_00827
     * 
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_response = this.l_response = (WEB3FuturesOrderCarryOverResponse) this.l_serviceImpl
                    .execute(this.l_request);
            fail();
        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00827, l_web3BaseException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 
     * �e�o�ُ�WEB3BaseRuntimeException�M���FSYSTEM_ERROR_80005
     * 
     */
    public void testCreateNextOrdere_C0001()
    {
        final String STR_METHOD_NAME = "testCreateNextOrdere_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(TestDBUtility.getMainAccountRow());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", new Class[] {},
                    l_branch);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME));
            this.l_serviceImpl.createNextOrder(l_mainAccount,null,null);
            fail();
        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_web3BaseException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * �敨�V�K������"�̏ꍇ
     * 
     * �e�oWEB3BaseException�ُ�M��:BUSINESS_ERROR_00883
     * 
     */
    public void testSubmitNextOrder_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitNextOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverUnitServiceImpl",
                    "createOpenContractNextOrder", new Class[]
                    { OrderUnit.class, List.class}, 
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00883, STR_METHOD_NAME));

            this.l_serviceImpl.submitNextOrder(l_ifoOrderUnit, null);
            fail();
        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00883, l_web3BaseException.getErrorInfo());

            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverUnitServiceImpl",
                    "createOpenContractNextOrder", new Class[]
                    { OrderUnit.class, List.class});
            assertEquals(1001, ((IfoOrderUnit) l_paramsValue1.getFirstCalled()[0]).getOrderUnitId());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * �敨�ԍϒ���"�̏ꍇ
     * 
     * �e�oWEB3BaseException�ُ�M��:BUSINESS_ERROR_00883
     * 
     */
    public void testSubmitNextOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitNextOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverUnitServiceImpl",
                    "createSettleContractNextOrder", new Class[]
                    { OrderUnit.class, List.class}, new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00883, STR_METHOD_NAME));

            this.l_serviceImpl.submitNextOrder(l_ifoOrderUnit, null);
            fail();
        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00883, l_web3BaseException.getErrorInfo());

            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverUnitServiceImpl",
                    "createSettleContractNextOrder", new Class[]
                    { OrderUnit.class, List.class});
            assertEquals(1001, ((IfoOrderUnit) l_paramsValue1.getFirstCalled()[0]).getOrderUnitId());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * ���s���g�敨�ԍϒ���"�̏ꍇ�h�C��s���gOP�V�K������"�̏ꍇ�h
     * 
     * ���팋��
     * 
     */
    public void testSubmitNextOrder_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitNextOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_TRANSFER);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);

            this.l_serviceImpl.submitNextOrder(l_ifoOrderUnit, null);
            assertTrue(true);
        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �p�����[�^�l�s��
     * �e�o�gSYSTEM_ERROR_80017�h�ُ�
     * 
     */
    public void testSubmitNextOrder_C0004()
    {
        final String STR_METHOD_NAME = "testSubmitNextOrder_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_TRANSFER);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            new IfoContractOpenOrderUnitImpl(1001);

            this.l_serviceImpl.submitNextOrder(null, null);
            fail();
        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_web3BaseException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
