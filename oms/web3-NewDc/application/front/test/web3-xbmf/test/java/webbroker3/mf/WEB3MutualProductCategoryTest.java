head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.15.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualProductCategoryTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf;

import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.mf.data.MutualFundProductCategoryParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualProductCategoryTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundProductManagerTest.class);

    public WEB3MutualProductCategoryTest(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testDeleteMutualProductCategoryC0001()
    {
        final String STR_METHOD_NAME = "testDeleteMutualProductCategoryC0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFundProductManager l_mutualFundProductManager =
            new WEB3MutualFundProductManager();
        WEB3MutualProductCategory l_mutualProductCategory =
            new WEB3MutualProductCategory();
        try
        {
            TestDBUtility.deleteAll(MutualFundProductCategoryParams.TYPE);
            MutualFundProductCategoryParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductCategoryRow();
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        List l_lisRows = null;
        try
        {
            l_mutualProductCategory.deleteMutualProductCategory("0D","0");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            l_lisRows = l_mutualFundProductManager.getMutualFundProductCategoryList("0D");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        assertEquals(l_lisRows.size(),0);
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
