head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.36.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdministratorForMockTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeDao;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.AdministratorTypeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdministratorForMockTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeTradingTimeManagementForMockTest.class);
    public WEB3AdministratorForMockTest(String arg0)
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
     * Test method for 'webbroker3.gentrade.WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(WEB3Administrator)'
     */
    public void testMockGetInstanceFromLoginInfo()
    {
        final String STR_METHOD_NAME = "testMockGetInstanceFromLoginInfo()";
        log.entering(STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
                   
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3Administrator l_administratorGet =
                WEB3Administrator.getInstanceFromLoginInfo();
            assertEquals(l_administratorSet.getAdministratorId(), l_administratorGet.getAdministratorId());
            log.info(STR_METHOD_NAME + "------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_exc);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * case pass
     */
    public void testMockValidateAuthority_T01()
    {
        final String STR_METHOD_NAME = "testMockValidateAuthority_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "123", true, true);

            l_administratorSet.validateAuthority("123", true);
            log.info(STR_METHOD_NAME + "------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_exc);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * not pass
     */
    public void testMockValidateAuthority_T02()
    {
        final String STR_METHOD_NAME = "testMockValidateAuthority_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "123", true, false);

            l_administratorSet.validateAuthority("123",true);
            fail();
        }
        catch(WEB3BaseException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_exc);
            log.info(STR_METHOD_NAME + "------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_exc);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * case pass
     *
     */
    public void testMockValidateTradingPassword_T01()
    {
        final String STR_METHOD_NAME = "testMockValidateTradingPassword_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockValidateTradingPassword("123",true);
            l_administratorSet.validateTradingPassword("123");
            log.info(STR_METHOD_NAME + "------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_exc);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * case not pass
     *
     */
    public void testMockValidateTradingPassword_T02()
    {
        final String STR_METHOD_NAME = "testMockValidateTradingPassword_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockValidateTradingPassword("123",false);
            l_administratorSet.validateTradingPassword("123");
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_exc);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00009, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_exc);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * case pass
     */
    public void testMockValidateBranchPermission_T01()
    {
        final String STR_METHOD_NAME = "testMockValidateBranchPermission_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockValidateBranchPermission(
                l_administratorSet,
                "624",
                true);
            l_administratorSet.validateBranchPermission("624");
            log.info(STR_METHOD_NAME + "------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_exc);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * case not pass
     */
    public void testMockValidateBranchPermission_T02()
    {
        final String STR_METHOD_NAME = "testMockValidateBranchPermission_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockValidateBranchPermission(
                l_administratorSet,
                "624",
                false);
            l_administratorSet.validateBranchPermission("624");
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_exc);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_exc);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * case pass
     */
    public void testMockValidateBranchPermission_T03()
    {
        final String STR_METHOD_NAME = "testMockValidateBranchPermission_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockValidateBranchPermission(
                l_administratorSet,
                new String[]{"123","456"},
                true);
            l_administratorSet.validateBranchPermission("624");
            log.info(STR_METHOD_NAME + "------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_exc);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * case not pass
     */
    public void testMockValidateBranchPermission_T04()
    {
        final String STR_METHOD_NAME = "testMockValidateBranchPermission_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("123");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockValidateBranchPermission(
                l_administratorSet,
                new String[]{"123","000"},
                false);
            l_administratorSet.validateBranchPermission("123");
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_exc);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_exc);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testMockIsDirAdministrator_T01()
    {
        final String STR_METHOD_NAME = "testMockIsDirAdministrator_T01()";
        log.entering(STR_METHOD_NAME);

        AdministratorParams l_administratorParams = new AdministratorParams();
        l_administratorParams.setInstitutionCode("123");
        l_administratorParams.setPermissionLevel("123");
        WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
        
        try
        {
            WEB3AdministratorForMock.mockIsDirAdministrator(l_expectAdministrator, true);
            
            AdministratorTypeRow l_administratorTypeRow;

            l_administratorTypeRow =
                AdministratorTypeDao.findRowByPk(
                    "123",
                    "123");
            assertEquals(1 , l_administratorTypeRow.getDirAdminFlag());
        } catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
    
    public void testMockIsDirAdministrator_T02()
    {
        final String STR_METHOD_NAME = "testMockIsDirAdministrator_T02()";
        log.entering(STR_METHOD_NAME);

        AdministratorParams l_administratorParams = new AdministratorParams();
        l_administratorParams.setInstitutionCode("123");
        l_administratorParams.setPermissionLevel("123");
        WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
        
        try
        {
            WEB3AdministratorForMock.mockIsDirAdministrator(l_expectAdministrator, false);
            
            AdministratorTypeRow l_administratorTypeRow;

            l_administratorTypeRow =
                AdministratorTypeDao.findRowByPk(
                    "123",
                    "123");
            assertEquals(2 , l_administratorTypeRow.getDirAdminFlag());
        } catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
}
@
