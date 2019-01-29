head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.22.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeBranchTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 部店テスト(WEB3GentradeBranchTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/15 キョウ再平 (中訊) 新規作成        
*/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum.IntValues;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * （部店テスト）<BR>
 *
 * @@author キョウ再平
 * @@version 1.0
 */

public class WEB3GentradeBranchTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3GentradeBranchTest.class);

    public WEB3GentradeBranchTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestDBUtility.deleteAll(BranchRow.TYPE);
        TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        TestDBUtility.deleteAll(BranchRow.TYPE);
        TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
    }

    public void testIsEveningSessionEnforcemented_0001()
    {
        final String STR_METHOD_NAME = " testIsEveningSessionEnforcemented_0001 ";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

        try
        {
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.deleteAll(BranchRow.TYPE);        
            TestDBUtility.insertWithDel(l_branchParams);

            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("evening.session.div");
            l_branchPreferencesParams.setNameSerialNo(2);
            l_branchPreferencesParams.setValue("1");       
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);        
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(l_branchParams.branch_id);

            assertEquals(true, l_branch.isEveningSessionEnforcemented(new ProductTypeEnum(IntValues.IFO, "IFO")));
       }
       catch (Exception l_ex)
       {
           log.error(l_ex.getMessage(), l_ex);
           fail();
       }
       log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
       log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testIsEveningSessionEnforcemented_0002()
    {
        final String STR_METHOD_NAME = " testIsEveningSessionEnforcemented_0002 ";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

        try
        {
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.deleteAll(BranchRow.TYPE);        
            TestDBUtility.insertWithDel(l_branchParams);

            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("evening.session.div");
            l_branchPreferencesParams.setNameSerialNo(2);
            l_branchPreferencesParams.setValue("1");       
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);        
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(l_branchParams.branch_id);

            assertEquals(false, l_branch.isEveningSessionEnforcemented(new ProductTypeEnum(IntValues.AIO, "AIO")));
       }
       catch (Exception l_ex)
       {
           log.error(l_ex.getMessage(), l_ex);
           fail();
       }
       log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
       log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testIsEveningSessionEnforcemented_0003()
    {
        final String STR_METHOD_NAME = " testIsEveningSessionEnforcemented_0003 ";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

        try
        {
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.deleteAll(BranchRow.TYPE);        
            TestDBUtility.insertWithDel(l_branchParams);

            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("evening.session.div");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");       
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);        
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(l_branchParams.branch_id);

            assertEquals(false, l_branch.isEveningSessionEnforcemented(new ProductTypeEnum(IntValues.IFO, "IFO")));
       }
       catch (Exception l_ex)
       {
           log.error(l_ex.getMessage(), l_ex);
           fail();
       }
       log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
       log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testIsEveningSessionEnforcemented_0004()
    {
        final String STR_METHOD_NAME = " testIsEveningSessionEnforcemented_0004 ";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

        try
        {
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.deleteAll(BranchRow.TYPE);        
            TestDBUtility.insertWithDel(l_branchParams);

            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("evening.session.div");
            l_branchPreferencesParams.setNameSerialNo(2);
            l_branchPreferencesParams.setValue("2");       
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);        
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(l_branchParams.branch_id);

            assertEquals(false, l_branch.isEveningSessionEnforcemented(new ProductTypeEnum(IntValues.IFO, "IFO")));
       }
       catch (Exception l_ex)
       {
           log.error(l_ex.getMessage(), l_ex);
           fail();
       }
       log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
       log.exiting(TEST_END + STR_METHOD_NAME);
    }

  public void testIsEqtypeFinalDayWithRight_0001()
  {
      final String STR_METHOD_NAME = " testIsEqtypeFinalDayWithRight_0001 ";
      log.entering(TEST_START + STR_METHOD_NAME);

      TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

      try
      {
          BranchParams l_branchParams = TestDBUtility.getBranchRow();
          l_branchParams.setBranchId(33381);
          TestDBUtility.deleteAll(BranchRow.TYPE);        
          TestDBUtility.insertWithDel(l_branchParams);

          BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
          l_branchPreferencesParams.setBranchId(33381);
          l_branchPreferencesParams.setName("eqtype.final.day.with.right");
          l_branchPreferencesParams.setNameSerialNo(1);
          l_branchPreferencesParams.setValue("1");       
          TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);        
          TestDBUtility.insertWithDel(l_branchPreferencesParams);

          WEB3GentradeBranch l_branch = new WEB3GentradeBranch(l_branchParams.branch_id);

          assertEquals(true, l_branch.isEqtypeFinalDayWithRight());
     }
     catch (Exception l_ex)
     {
         log.error(l_ex.getMessage(), l_ex);
         fail();
     }
     log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
     log.exiting(TEST_END + STR_METHOD_NAME);
  }

  public void testIsEqtypeFinalDayWithRight_0002()
  {
      final String STR_METHOD_NAME = " testIsEqtypeFinalDayWithRight_0002 ";
      log.entering(TEST_START + STR_METHOD_NAME);

      TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

      try
      {
          BranchParams l_branchParams = TestDBUtility.getBranchRow();
          l_branchParams.setBranchId(33381);
          TestDBUtility.deleteAll(BranchRow.TYPE);        
          TestDBUtility.insertWithDel(l_branchParams);

          BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
          l_branchPreferencesParams.setBranchId(33381);
          l_branchPreferencesParams.setName("eqtype.final.day.with.right");
          l_branchPreferencesParams.setNameSerialNo(2);
          l_branchPreferencesParams.setValue("1");       
          TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);        
          TestDBUtility.insertWithDel(l_branchPreferencesParams);

          WEB3GentradeBranch l_branch = new WEB3GentradeBranch(l_branchParams.branch_id);

          assertEquals(false, l_branch.isEqtypeFinalDayWithRight());
     }
     catch (Exception l_ex)
     {
         log.error(l_ex.getMessage(), l_ex);
         fail();
     }
     log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
     log.exiting(TEST_END + STR_METHOD_NAME);
  }

  public void testIsEqtypeFinalDayWithRight_0003()
  {
      final String STR_METHOD_NAME = " testIsEqtypeFinalDayWithRight_0003 ";
      log.entering(TEST_START + STR_METHOD_NAME);

      TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

      try
      {
          BranchParams l_branchParams = TestDBUtility.getBranchRow();
          l_branchParams.setBranchId(33381);
          TestDBUtility.deleteAll(BranchRow.TYPE);        
          TestDBUtility.insertWithDel(l_branchParams);

          BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
          l_branchPreferencesParams.setBranchId(33381);
          l_branchPreferencesParams.setName("eqtype.final.day.with.right");
          l_branchPreferencesParams.setNameSerialNo(2);
          l_branchPreferencesParams.setValue("8");       
          TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);        
          TestDBUtility.insertWithDel(l_branchPreferencesParams);

          WEB3GentradeBranch l_branch = new WEB3GentradeBranch(l_branchParams.branch_id);

          assertEquals(false, l_branch.isEqtypeFinalDayWithRight());
     }
     catch (Exception l_ex)
     {
         log.error(l_ex.getMessage(), l_ex);
         fail();
     }
     log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
     log.exiting(TEST_END + STR_METHOD_NAME);
  }

  public void testIsSuccOrderCarryoverEnforcemented_0001()
  {
      final String STR_METHOD_NAME = " testIsSuccOrderCarryoverEnforcemented_0001 ";
      log.entering(TEST_START + STR_METHOD_NAME);

      TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

      try
      {
          BranchParams l_branchParams = TestDBUtility.getBranchRow();
          l_branchParams.setBranchId(33381);
          TestDBUtility.insertWithDel(l_branchParams);

          BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
          l_branchPreferencesParams.setBranchId(33381);
          l_branchPreferencesParams.setName("triggerorder.sucorder.carryover");
          l_branchPreferencesParams.setNameSerialNo(1);
          l_branchPreferencesParams.setValue("0");
          TestDBUtility.insertWithDel(l_branchPreferencesParams);

          WEB3GentradeBranch l_branch = new WEB3GentradeBranch(l_branchParams.branch_id);

          assertEquals(false, l_branch.isSuccOrderCarryoverEnforcemented(new ProductTypeEnum(1, "EQUITY")));
     }
     catch (Exception l_ex)
     {
         log.error(l_ex.getMessage(), l_ex);
         fail();
     }
     log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
     log.exiting(TEST_END + STR_METHOD_NAME);
  }

  public void testIsSuccOrderCarryoverEnforcemented_0002()
  {
      final String STR_METHOD_NAME = " testIsSuccOrderCarryoverEnforcemented_0002 ";
      log.entering(TEST_START + STR_METHOD_NAME);

      TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

      try
      {
          BranchParams l_branchParams = TestDBUtility.getBranchRow();
          l_branchParams.setBranchId(33381);
          TestDBUtility.insertWithDel(l_branchParams);

          BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
          l_branchPreferencesParams.setBranchId(33381);
          l_branchPreferencesParams.setName("triggerorder.sucorder.carryover");
          l_branchPreferencesParams.setNameSerialNo(2);
          l_branchPreferencesParams.setValue("1");
          TestDBUtility.insertWithDel(l_branchPreferencesParams);

          WEB3GentradeBranch l_branch = new WEB3GentradeBranch(l_branchParams.branch_id);

          assertEquals(true, l_branch.isSuccOrderCarryoverEnforcemented(new ProductTypeEnum(6, "IFO")));
     }
     catch (Exception l_ex)
     {
         log.error(l_ex.getMessage(), l_ex);
         fail();
     }
     log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
     log.exiting(TEST_END + STR_METHOD_NAME);
  }

  public void testIsSuccOrderCarryoverEnforcemented_0003()
  {
      final String STR_METHOD_NAME = " testIsSuccOrderCarryoverEnforcemented_0003 ";
      log.entering(TEST_START + STR_METHOD_NAME);

      TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

      try
      {
          BranchParams l_branchParams = TestDBUtility.getBranchRow();
          l_branchParams.setBranchId(33381);
          TestDBUtility.insertWithDel(l_branchParams);

          BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
          l_branchPreferencesParams.setBranchId(33381);
          l_branchPreferencesParams.setName("triggerorder.sucorder.carryover");
          l_branchPreferencesParams.setNameSerialNo(1);
          l_branchPreferencesParams.setValue("1");
          TestDBUtility.insertWithDel(l_branchPreferencesParams);

          WEB3GentradeBranch l_branch = new WEB3GentradeBranch(l_branchParams.branch_id);

          assertEquals(false, l_branch.isSuccOrderCarryoverEnforcemented(new ProductTypeEnum(6, "IFO")));
     }
     catch (Exception l_ex)
     {
         log.error(l_ex.getMessage(), l_ex);
         fail();
     }
     log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
     log.exiting(TEST_END + STR_METHOD_NAME);
  }
}
@
