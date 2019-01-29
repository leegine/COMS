head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.07.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAccOpenDataTransferUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3AdminAccOpenDataTransferUnitServiceImplTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/24 武波 (中訊) 新規作成
*/
package webbroker3.accountopen.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenTempDao;
import webbroker3.accountopen.data.ExpAccountOpenTempPK;
import webbroker3.accountopen.data.ExpAccountOpenTempParams;
import webbroker3.accountopen.data.ExpAccountOpenTempRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.FinInstitutionBankParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccOpenDataTransferUnitServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenDataTransferUnitServiceImplTest.class);
    public WEB3AdminAccOpenDataTransferUnitServiceImplTest(String arg0)
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

    public void testValidateBranch_C0001()
    {
        final String STR_METHOD_NAME = "testValidateBranch_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            WEB3AdminAccOpenDataTransferUnitServiceImpl l_impl =
                new WEB3AdminAccOpenDataTransferUnitServiceImpl();
            Method l_method  =
                WEB3AdminAccOpenDataTransferUnitServiceImpl.class.getDeclaredMethod(
                    "validateBranch", new Class[]{String.class, String.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{"381", "0D"});
            fail();
        }
        catch (InvocationTargetException e)
        {
            WEB3BaseException l =  (WEB3BaseException)e.getTargetException(); 
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01386, l.getErrorInfo()); 
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateBranch_C0002()
    {
        final String STR_METHOD_NAME = "testValidateBranch_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3AdminAccOpenDataTransferUnitServiceImpl l_impl =
                new WEB3AdminAccOpenDataTransferUnitServiceImpl();
            Method l_method  =
                WEB3AdminAccOpenDataTransferUnitServiceImpl.class.getDeclaredMethod(
                    "validateBranch", new Class[]{String.class, String.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{"381", "0D"});
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateAccOpenExpAccountOpenTemp_C0002()
    {
        final String STR_METHOD_NAME = "testUpdateAccOpenExpAccountOpenTemp_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ExpAccountOpenTempParams.TYPE);
            WEB3AdminAccOpenDataTransferUnitServiceImpl l_impl =
                new WEB3AdminAccOpenDataTransferUnitServiceImpl();
            Method l_method  =
                WEB3AdminAccOpenDataTransferUnitServiceImpl.class.getDeclaredMethod(
                    "updateAccOpenExpAccountOpenTemp",
                    new Class[]{String.class, String.class, String.class, String.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{"0D", "1", "1", "1"});
            fail();
        }
        catch (InvocationTargetException e)
        {
            WEB3BaseException l =  (WEB3BaseException)e.getTargetException(); 
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l.getErrorInfo()); 
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateAccOpenExpAccountOpenTemp_C0001()
    {
        final String STR_METHOD_NAME = "testUpdateAccOpenExpAccountOpenTemp_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImpl());

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.deleteAll(ExpAccountOpenTempParams.TYPE);
            ExpAccountOpenTempParams l_expAccountOpenTempParams =
                TestDBUtility.getExpAccountOpenTempRow();
            l_expAccountOpenTempParams.setStatus("1");
            l_expAccountOpenTempParams.setInstitutionCode("0D");
            l_expAccountOpenTempParams.setAccOpenRequestNumber("1");
            TestDBUtility.insertWithDel(l_expAccountOpenTempParams);
            WEB3AdminAccOpenDataTransferUnitServiceImpl l_impl =
                new WEB3AdminAccOpenDataTransferUnitServiceImpl();
            Method l_method  =
                WEB3AdminAccOpenDataTransferUnitServiceImpl.class.getDeclaredMethod(
                    "updateAccOpenExpAccountOpenTemp",
                    new Class[]{String.class, String.class, String.class, String.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{"0D", "1", "1", "test"});

            ExpAccountOpenTempRow l_expAccountOpenTempRow = ExpAccountOpenTempDao.findRowByPk(
                new ExpAccountOpenTempPK(
                    "0D",
                    "1"));

            assertEquals("1", l_expAccountOpenTempRow.getStatus());
            assertEquals("test", l_expAccountOpenTempRow.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testProcess_C0006()
    {
        final String STR_METHOD_NAME = "testProcess_C0006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImpl());

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(ExpAccountOpenTempParams.TYPE);
            ExpAccountOpenTempParams l_expAccountOpenTempParams =
                TestDBUtility.getExpAccountOpenTempRow();
            l_expAccountOpenTempParams.setStatus("1");
            l_expAccountOpenTempParams.setInstitutionCode("0D");
            l_expAccountOpenTempParams.setAccOpenRequestNumber("2007092899999");
            TestDBUtility.insertWithDel(l_expAccountOpenTempParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountDiv("4");
            l_expAccountOpenParams.setZipCode("1234567");
//            TestDBUtility.insertWithDel(l_expAccountOpenParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
            WEB3AdminAccOpenDataTransferUnitServiceImpl l_impl =
                new WEB3AdminAccOpenDataTransferUnitServiceImpl();
            l_impl.process(l_accOpenExpAccountOpen);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testProcess_C0001()
    {
        final String STR_METHOD_NAME = "testProcess_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImpl());

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(ExpAccountOpenTempParams.TYPE);
            ExpAccountOpenTempParams l_expAccountOpenTempParams =
                TestDBUtility.getExpAccountOpenTempRow();
            l_expAccountOpenTempParams.setStatus("1");
            l_expAccountOpenTempParams.setInstitutionCode("0D");
            l_expAccountOpenTempParams.setAccOpenRequestNumber("2007092899999");
            TestDBUtility.insertWithDel(l_expAccountOpenTempParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);

            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountDiv("4");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
            WEB3AdminAccOpenDataTransferUnitServiceImpl l_impl =
                new WEB3AdminAccOpenDataTransferUnitServiceImpl();
            l_impl.process(l_accOpenExpAccountOpen);

            ExpAccountOpenTempRow l_expAccountOpenTempRow = ExpAccountOpenTempDao.findRowByPk(
                new ExpAccountOpenTempPK(
                    "0D",
                    "2007092899999"));

            assertEquals("9", l_expAccountOpenTempRow.getStatus());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testProcess_C0002()
    {
        final String STR_METHOD_NAME = "testProcess_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImpl());

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(ExpAccountOpenTempParams.TYPE);
            ExpAccountOpenTempParams l_expAccountOpenTempParams =
                TestDBUtility.getExpAccountOpenTempRow();
            l_expAccountOpenTempParams.setStatus("1");
            l_expAccountOpenTempParams.setInstitutionCode("0D");
            l_expAccountOpenTempParams.setAccOpenRequestNumber("2007092899999");
            TestDBUtility.insertWithDel(l_expAccountOpenTempParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountDiv("4");
            l_expAccountOpenParams.setZipCode("!");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
            WEB3AdminAccOpenDataTransferUnitServiceImpl l_impl =
                new WEB3AdminAccOpenDataTransferUnitServiceImpl();
            l_impl.process(l_accOpenExpAccountOpen);
            ExpAccountOpenTempRow l_expAccountOpenTempRow = ExpAccountOpenTempDao.findRowByPk(
                new ExpAccountOpenTempPK(
                    "0D",
                    "2007092899999"));

            assertEquals("9", l_expAccountOpenTempRow.getStatus());
            assertEquals("項目が有効な値ではありません。", l_expAccountOpenTempRow.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testProcess_C0003()
    {
        final String STR_METHOD_NAME = "testProcess_C0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImpl());

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(ExpAccountOpenTempParams.TYPE);
            ExpAccountOpenTempParams l_expAccountOpenTempParams =
                TestDBUtility.getExpAccountOpenTempRow();
            l_expAccountOpenTempParams.setStatus("1");
            l_expAccountOpenTempParams.setInstitutionCode("0D");
            l_expAccountOpenTempParams.setAccOpenRequestNumber("2007092899999");
            TestDBUtility.insertWithDel(l_expAccountOpenTempParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountDiv("4");
            l_expAccountOpenParams.setZipCode("1234567");
            l_expAccountOpenParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountCode(l_expAccountOpenParams.getAccountCode());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
            WEB3AdminAccOpenDataTransferUnitServiceImpl l_impl =
                new WEB3AdminAccOpenDataTransferUnitServiceImpl();
            l_impl.process(l_accOpenExpAccountOpen);
            ExpAccountOpenTempRow l_expAccountOpenTempRow = ExpAccountOpenTempDao.findRowByPk(
                new ExpAccountOpenTempPK(
                    "0D",
                    "2007092899999"));

            assertEquals("9", l_expAccountOpenTempRow.getStatus());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testProcess_C0004()
    {
        final String STR_METHOD_NAME = "testProcess_C0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImpl());

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(ExpAccountOpenTempParams.TYPE);
            ExpAccountOpenTempParams l_expAccountOpenTempParams =
                TestDBUtility.getExpAccountOpenTempRow();
            l_expAccountOpenTempParams.setStatus("1");
            l_expAccountOpenTempParams.setInstitutionCode("0D");
            l_expAccountOpenTempParams.setAccOpenRequestNumber("2007092899999");
            TestDBUtility.insertWithDel(l_expAccountOpenTempParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountDiv("4");
            l_expAccountOpenParams.setZipCode("1234567");
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setFinInstitutionCode("0D");
            l_expAccountOpenParams.setFinBranchCode("381");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);

            TestDBUtility.deleteAll(FinInstitutionBankParams.TYPE);
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
            WEB3AdminAccOpenDataTransferUnitServiceImpl l_impl =
                new WEB3AdminAccOpenDataTransferUnitServiceImpl();
            l_impl.process(l_accOpenExpAccountOpen);
            ExpAccountOpenTempRow l_expAccountOpenTempRow = ExpAccountOpenTempDao.findRowByPk(
                new ExpAccountOpenTempPK(
                    "0D",
                    "2007092899999"));

            assertEquals("9", l_expAccountOpenTempRow.getStatus());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }


    public void testProcess_C0005()
    {
        final String STR_METHOD_NAME = "testProcess_C0005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImpl());

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(ExpAccountOpenTempParams.TYPE);
            ExpAccountOpenTempParams l_expAccountOpenTempParams =
                TestDBUtility.getExpAccountOpenTempRow();
            l_expAccountOpenTempParams.setStatus("1");
            l_expAccountOpenTempParams.setInstitutionCode("0D");
            l_expAccountOpenTempParams.setAccOpenRequestNumber("2007092899999");
            TestDBUtility.insertWithDel(l_expAccountOpenTempParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountDiv("4");
            l_expAccountOpenParams.setZipCode("1234567");
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setFinInstitutionCode(null);
            l_expAccountOpenParams.setFinBranchCode(null);
            TestDBUtility.insertWithDel(l_expAccountOpenParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);

            TestDBUtility.deleteAll(FinInstitutionBankParams.TYPE);
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
            WEB3AdminAccOpenDataTransferUnitServiceImpl l_impl =
                new WEB3AdminAccOpenDataTransferUnitServiceImpl();
            l_impl.process(l_accOpenExpAccountOpen);
            ExpAccountOpenTempRow l_expAccountOpenTempRow = ExpAccountOpenTempDao.findRowByPk(
                new ExpAccountOpenTempPK(
                    "0D",
                    "2007092899999"));

            assertEquals("9", l_expAccountOpenTempRow.getStatus());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
