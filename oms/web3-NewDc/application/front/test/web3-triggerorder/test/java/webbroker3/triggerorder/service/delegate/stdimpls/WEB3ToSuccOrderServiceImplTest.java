head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.19.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccOrderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3ToSuccOrderServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/06 安陽(中訊) 新規作成
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccOrderServiceImplTest extends TestBaseForMock
{
    
    private WEB3ToSuccOrderServiceImpl l_serviceImpl = null;   
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3ToSuccOrderServiceImplTest.class);
    
    public WEB3ToSuccOrderServiceImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        
        TestDBUtility.deleteAll(InstitutionParams.TYPE);
        InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
        TestDBUtility.insertWithDel(l_InstitutionParams);
        
        
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_serviceImpl = new WEB3ToSuccOrderServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        this.l_serviceImpl = null;
        super.tearDown();
    }

    /*     
     * Test method for 'webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOrderServiceImpl.executeSuccOrder(SubAccount, long, ProductTypeEnum)'
     */
    public void testExecuteSuccOrder_C0001()
    {
        final String STR_METHOD_NAME = "testExecuteSuccOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_BranchParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setParentOrderUnitId(1001);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(20);
            l_ifoOrderUnitParams.setExecutedQuantity(20);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl",
                    "submitFuturesOpenContractOrder", new Class[]
                    {WEB3ToSuccIfoOrderUnitImpl.class}, null);
            
            l_serviceImpl.executeSuccOrder(null, 1001L, ProductTypeEnum.IFO);
            
            WEB3MockObjectParamsValue l_mockObjectParamsValue = MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl",
                    "submitFuturesOpenContractOrder", new Class[]
                    {WEB3ToSuccIfoOrderUnitImpl.class});
            
            Object[] l_objParamsValues = l_mockObjectParamsValue.getFirstCalled();
            WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnitImpl = (WEB3ToSuccIfoOrderUnitImpl)l_objParamsValues[0];
            
            assertEquals(333812512203L, l_ifoOrderUnitImpl.getAccountId());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecuteSuccOrder_C0002()
    {
        final String STR_METHOD_NAME = "testExecuteSuccOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_BranchParams);
            TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setParentOrderUnitId(1001);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(20);
            l_ifoOrderUnitParams.setExecutedQuantity(20);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl",
                    "submitFuturesSettleContractOrder", new Class[]
                    {WEB3ToSuccIfoOrderUnitImpl.class}, null);
            
            l_serviceImpl.executeSuccOrder(null, 1001L, ProductTypeEnum.IFO);
            
            WEB3MockObjectParamsValue l_mockObjectParamsValue = MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl",
                    "submitFuturesSettleContractOrder", new Class[]
                    {WEB3ToSuccIfoOrderUnitImpl.class});
            
            Object[] l_objParamsValues = l_mockObjectParamsValue.getFirstCalled();
            WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnitImpl = (WEB3ToSuccIfoOrderUnitImpl)l_objParamsValues[0];
            
            assertEquals(333812512203L, l_ifoOrderUnitImpl.getAccountId());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testExecuteSuccOrder_C0003()
    {
        final String STR_METHOD_NAME = "testExecuteSuccOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_BranchParams);
            TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setParentOrderUnitId(1001);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(20);
            l_ifoOrderUnitParams.setExecutedQuantity(20);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl",
                    "submitOptionsOpenContractOrder", new Class[]
                    {WEB3ToSuccIfoOrderUnitImpl.class}, null);
            
            l_serviceImpl.executeSuccOrder(null, 1001L, ProductTypeEnum.IFO);
            
            WEB3MockObjectParamsValue l_mockObjectParamsValue = MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl",
                    "submitOptionsOpenContractOrder", new Class[]
                    {WEB3ToSuccIfoOrderUnitImpl.class});
            
            Object[] l_objParamsValues = l_mockObjectParamsValue.getFirstCalled();
            WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnitImpl = (WEB3ToSuccIfoOrderUnitImpl)l_objParamsValues[0];
            
            assertEquals(333812512203L, l_ifoOrderUnitImpl.getAccountId());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecuteSuccOrder_C0004()
    {
        final String STR_METHOD_NAME = "testExecuteSuccOrder_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_BranchParams);
            TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setParentOrderUnitId(1001);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_CLOSE);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(20);
            l_ifoOrderUnitParams.setExecutedQuantity(20);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl",
                    "submitOptionsSettleContractOrder", new Class[]
                    {WEB3ToSuccIfoOrderUnitImpl.class}, null);
            
            l_serviceImpl.executeSuccOrder(null, 1001L, ProductTypeEnum.IFO);
            
            WEB3MockObjectParamsValue l_mockObjectParamsValue = MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl",
                    "submitOptionsSettleContractOrder", new Class[]
                    {WEB3ToSuccIfoOrderUnitImpl.class});
            
            Object[] l_objParamsValues = l_mockObjectParamsValue.getFirstCalled();
            WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnitImpl = (WEB3ToSuccIfoOrderUnitImpl)l_objParamsValues[0];
            
            assertEquals(333812512203L, l_ifoOrderUnitImpl.getAccountId());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
