head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.01.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoFinTransactionManagerImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3IfoFinTransactionManagerImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/08/25 安陽(中訊) 新規作成
*/
package webbroker3.ifo;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoFinTransactionManagerImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3IfoFinTransactionManagerImplTest.class);

    public WEB3IfoFinTransactionManagerImplTest(String arg0)
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
     * Test method for 'webbroker3.ifo.WEB3IfoFinTransactionManagerImpl.getNetAmount(OrderUnit)'
     */
    public void testGetNetAmount_C0001()
    {
        final String STR_METHOD_NAME = "testGetNetAmount_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        
            WEB3IfoFinTransactionManagerImpl l_impl = new WEB3IfoFinTransactionManagerImplForTest();

            double l_dblReturnValue = l_impl.getNetAmount(null);

            assertEquals(6.6, l_dblReturnValue, 0);
        }

        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
        
    class WEB3IfoFinTransactionManagerImplForTest extends WEB3IfoFinTransactionManagerImpl
    {
        public List getTransactions(OrderUnit l_orderUnit) 
        throws WEB3BaseException
        {
            List l_returnList = new ArrayList();
            
            IfoFinTransactionParams l_ifoFinTransactionParams1 = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams1.setNetAmount(2.2d);
            l_returnList.add(l_ifoFinTransactionParams1);
            
            IfoFinTransactionParams l_ifoFinTransactionParams2 = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams2.setNetAmount(4.4d);
            l_returnList.add(l_ifoFinTransactionParams2);

            return l_returnList;
        }
    }

}
@
