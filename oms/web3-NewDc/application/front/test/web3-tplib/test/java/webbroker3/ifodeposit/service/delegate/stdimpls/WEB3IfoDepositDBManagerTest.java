head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.10.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoDepositDBManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3IfoDepositDBManagerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/08/21 張少傑（中訊）新規作成
*/
package webbroker3.ifodeposit.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import test.util.TestDBUtility;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoDepositDBManagerTest extends TestBaseForMock
{
    WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDepositDBManagerTest.class);
    
    public WEB3IfoDepositDBManagerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    //normal insert
    public void testInsertC001()
    {
        String STR_METHOD_NAME = " testInsertC001";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoDepositDBManager l_ifoDepositDBManager = 
            new WEB3IfoDepositDBManager();
        try
        {
            TestDBUtility.deleteAllAndCommit(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams1 = TestDBUtility.getIfoOrderRow();

            IfoOrderParams l_ifoOrderParams2 = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams2.setOrderId(33382L);
            
            List l_IfoOrderLists = new ArrayList();
            l_IfoOrderLists.add(l_ifoOrderParams1);
            l_IfoOrderLists.add(l_ifoOrderParams2);
            
            boolean l_blnReturn = l_ifoDepositDBManager.insert(l_IfoOrderLists);
            
            int orderId = 33381;
            IfoOrderRow l_ifoOrderRow = IfoOrderDao.findRowByPk(orderId);
            
            int orderId1 = 33382;
            IfoOrderRow l_ifoOrderRow1 = IfoOrderDao.findRowByPk(orderId1);
            
            assertEquals(33381, l_ifoOrderRow.getOrderId());
            assertEquals(33382, l_ifoOrderRow1.getOrderId());
            assertEquals(true, l_blnReturn);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //Execption insert
    public void testInsertC002()
    {
        String STR_METHOD_NAME = " testInsertC002";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoDepositDBManager l_ifoDepositDBManager = 
            new WEB3IfoDepositDBManager();
        try
        {
            TestDBUtility.deleteAllAndCommit(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams1 = TestDBUtility.getIfoOrderRow();

            IfoOrderParams l_ifoOrderParams2 = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams2.setOrderId(33381L);
            
            List l_IfoOrderLists = new ArrayList();
            l_IfoOrderLists.add(l_ifoOrderParams1);
            l_IfoOrderLists.add(l_ifoOrderParams2);
            
            boolean l_blnReturn = l_ifoDepositDBManager.insert(l_IfoOrderLists);
            assertEquals(false, l_blnReturn);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //normal delete
    public void testDeleteC001()
    {
        String STR_METHOD_NAME = " testDeleteC001";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoDepositDBManager l_ifoDepositDBManager = 
            new WEB3IfoDepositDBManager();
        
        List l_lstReturnRecord = null;
        try
        {
            TestDBUtility.deleteAllAndCommit(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams1 = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderParams1);
            
            IfoOrderParams l_ifoOrderParams2 = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams2.setOrderId(33382L);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderParams2);
            
            List l_IfoOrderLists = new ArrayList();
            l_IfoOrderLists.add(l_ifoOrderParams1);
            l_IfoOrderLists.add(l_ifoOrderParams2);
            boolean l_blnReturn = l_ifoDepositDBManager.delete(l_IfoOrderLists);
            //find rows
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstReturnRecord = l_queryProcessor.doFindAllQuery(
                IfoOrderRow.TYPE,
                null
                ,null,
                null);
            assertEquals(true, l_blnReturn);
            assertEquals(0, l_lstReturnRecord.size());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //Execption delete
    public void testDeleteC002()
    {
        String STR_METHOD_NAME = " testDeleteC002";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoDepositDBManager l_ifoDepositDBManager = 
            new WEB3IfoDepositDBManager();
        try
        {
            TestDBUtility.deleteAllAndCommit(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams1 = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderParams1);
            l_ifoOrderParams1.setOrderId(40000L);

            IfoOrderParams l_ifoOrderParams2 = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderParams2);
            l_ifoOrderParams2.setOrderId(400001L);
            
            List l_IfoOrderLists = null;
//            l_IfoOrderLists.add(l_ifoOrderParams1);
//            l_IfoOrderLists.add(l_ifoOrderParams2);
            
            boolean l_blnReturn = l_ifoDepositDBManager.delete(l_IfoOrderLists);
            assertEquals(false, l_blnReturn);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
