head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.02.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondBranchConditionTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd;

import test.util.TestDBUtility;

import webbroker3.bd.data.BondBranchConditionParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 債券部店別条件
 * @@author xie-xuan
 * @@version 1.0
 */
public class WEB3BondBranchConditionTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondBranchConditionTest.class);
    
    public WEB3BondBranchConditionTest(String arg0)
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
    
    public void testGetBranchRecruitLimitDiv_0001()
    {
        final String STR_METHOD_NAME = "testGetBranchRecruitLimitDiv_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchRecruitLimitDiv("1");
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);
            
            WEB3BondBranchCondition l_condition = new WEB3BondBranchCondition(33381L);
            assertEquals("1", l_condition.getBranchRecruitLimitDiv());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
