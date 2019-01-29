head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.26.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	TestDBUtilityTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package test.util;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchPK;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.MainAccountImpl;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class TestDBUtilityTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(TestDBUtilityTest.class);

    public TestDBUtilityTest(String arg0)
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
     * Test method for 'test.util.TestDBUtility.insertWithDel(Row)'
     */
    public void testInsertWithDel()
    {       
        final String STR_METHOD_NAME =".testInsertWithDel";
        log.entering(STR_METHOD_NAME);
        try
        {
            BranchRow l_branchRow = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchRow);
            
            BranchRow l_branchRow2 = BranchDao.findRowByPk((BranchPK)l_branchRow.getPrimaryKey());
            assertEquals(l_branchRow.getBranchId(), l_branchRow2.getBranchId());
            
            
            TestDBUtility.insertWithDel(l_branchRow);
            TestDBUtility.insertWithDel(l_branchRow);
            l_branchRow2 = BranchDao.findRowByPk((BranchPK)l_branchRow.getPrimaryKey());
            assertEquals(l_branchRow.getBranchId(), l_branchRow2.getBranchId());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
	
	/**
	 * (テスト内容)<BR>
	 * getxxxParams<BR>
	 * (テスト結果)<BR>
	 * 正常終了すること<BR>
	 */
	public void test_getxxxParams_0001()
	{
        final String STR_METHOD_NAME =".test_getxxxParams_0001";
        log.entering(STR_METHOD_NAME);
        
        Method [] l_mthTestDBUtilityMethods = TestDBUtility.class.getDeclaredMethods();
        
        int l_intAllMethodLength = l_mthTestDBUtilityMethods.length;
		TestDBUtility testDBUtility = new TestDBUtility();
		List l_errorMethods = new ArrayList();
        
        for (int i = 0; i < l_intAllMethodLength; i++) 
        {
        	String l_strMethodName = l_mthTestDBUtilityMethods[i].getName();
        	
        	if (l_strMethodName.startsWith("get") && l_strMethodName.endsWith("Row"))
        	{
                log.debug("メソッド : " + l_strMethodName + TEST_START);
        		try
        		{
					Params l_methodParams = (Params)
					    l_mthTestDBUtilityMethods[i].invoke(testDBUtility, new Object[] {});
					
		            QueryProcessor l_processor = Processors.getDefaultProcessor();
                    TestDBUtility.deleteAll(l_methodParams.getRowType());
                    TestDBUtility.insertWithDel(l_methodParams);
                    int l_intRowCnt = 
                        l_processor.doGetCountQuery(l_methodParams.getRowType());

		            if (l_intRowCnt == 0)
                    {
                        l_errorMethods.add(l_strMethodName);
                    }
				}
        		catch (Exception e)
				{
                    log.error("",e);
                    l_errorMethods.add(l_strMethodName);
				}
                log.debug("メソッド : " + l_strMethodName + TEST_END);
        	}
        }
        if (!l_errorMethods.isEmpty())
        {
            StringBuffer l_strBuf = new StringBuffer();
            for(int i = 0; i < l_errorMethods.size(); i++)
            {
                l_strBuf.append(l_errorMethods.get(i) + "\n");   
            }
            assertEquals("",l_strBuf.toString());
        }
        log.exiting(STR_METHOD_NAME);
	}
    
    public void testIsTableLockedSuccessful_returntrue_C0001()
    {
        final String STR_METHOD_NAME =".testIsTableLockedSuccessful";
        log.entering(STR_METHOD_NAME);
        try
        {
            MainAccountRow l_mainAccountRow = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountRow);
            
            MainAccount l_mainAccount = new MainAccountImpl(l_mainAccountRow);
            
            l_mainAccount.serializeOperationsWithNoWait();
            assertTrue(TestDBUtility.isTableLockedSuccessful(MainAccountRow.TYPE));
            
        }
        catch (Exception e)
        {
            log.error("",e);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsTableLockedSuccessful_returntrue_C0002()
    {
        final String STR_METHOD_NAME =".testIsTableLockedSuccessful";
        log.entering(STR_METHOD_NAME);
        try
        {
            MainAccountRow l_mainAccountRow = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountRow);
            
            MainAccount l_mainAccount = new MainAccountImpl(l_mainAccountRow);
            
            l_mainAccount.serializeOperationsWithWait();
            assertTrue(TestDBUtility.isTableLockedSuccessful(MainAccountRow.TYPE));
            
        }
        catch (Exception e)
        {
            log.error("",e);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsTableLockedSuccessful_returnfalse_C0003()
    {
        final String STR_METHOD_NAME =".testIsTableLockedSuccessful";
        log.entering(STR_METHOD_NAME);
        try
        {
            MainAccountRow l_mainAccountRow = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDelAndCommit(l_mainAccountRow);
            assertFalse(TestDBUtility.isTableLockedSuccessful(MainAccountRow.TYPE));

            TestDBUtility.insertWithDel(l_mainAccountRow);
            assertFalse(TestDBUtility.isTableLockedSuccessful(MainAccountRow.TYPE));
            
        }
        catch (Exception e)
        {
            log.error("",e);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
