head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.13.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformPTSAccountListServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.service.delegate.stdimpls;


import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;


import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteConfRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInqCondition;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInqSortKey;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInquiryRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInquiryResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListResultRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListResultResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminInformPTSAccountListServiceImplTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformPTSAccountListServiceImplTest.class);

    public WEB3AdminInformPTSAccountListServiceImplTest(String arg0)
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

    public void testCreateQueryString_0001()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqCondition condition = new WEB3AdminInformPTSAccountListInqCondition();
        String[] l_strBranchCodes = new String[1];
        l_strBranchCodes[0] = "381";
        condition.branchCode = l_strBranchCodes;
        condition.accountCode = "123456";
        condition.ptsAccOpenDiv = "1";
        condition.applyDateFrom = new Date();
        condition.applyDateTo = new Date();
        
        WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
        try
        {
            Method metod = impl.getClass().getDeclaredMethod(
                "createQueryString",
                new Class[]{WEB3AdminInformPTSAccountListInqCondition.class});
            metod.setAccessible(true);
            String l_result = (String)metod.invoke(impl, new Object[]{condition});
            
            StringBuffer l_sbEx = new StringBuffer();
            l_sbEx.append(" inform_div = ? and institution_code = ? ");
            l_sbEx.append(" and branch_code = ? ");
            l_sbEx.append(" and account_code like ? || '%' ");
            l_sbEx.append(" and ext_div1 = '1' and ext_div2 = ? ");
            l_sbEx.append(" and created_timestamp >= ? ");
            l_sbEx.append(" and created_timestamp <= ? ");
            
            assertEquals(l_sbEx.toString(), l_result);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateQueryString_0002()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqCondition condition = new WEB3AdminInformPTSAccountListInqCondition();
        String[] l_strBranchCodes = new String[2];
        l_strBranchCodes[0] = "381";
        l_strBranchCodes[1] = "382";
        condition.branchCode = l_strBranchCodes;
        condition.accountCode = "123456";
        condition.ptsAccOpenDiv = "1";
        condition.applyDateFrom = new Date();
        condition.applyDateTo = new Date();
        
        WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
        try
        {
            Method metod = impl.getClass().getDeclaredMethod(
                "createQueryString",
                new Class[]{WEB3AdminInformPTSAccountListInqCondition.class});
            metod.setAccessible(true);
            String l_result = (String)metod.invoke(impl, new Object[]{condition});
            
            StringBuffer l_sbEx = new StringBuffer();
            l_sbEx.append(" inform_div = ? and institution_code = ? ");
            l_sbEx.append(" and (branch_code = ?  or branch_code = ? )");
            l_sbEx.append(" and account_code like ? || '%' ");
            l_sbEx.append(" and ext_div1 = '1' and ext_div2 = ? ");
            l_sbEx.append(" and created_timestamp >= ? ");
            l_sbEx.append(" and created_timestamp <= ? ");
            
            assertEquals(l_sbEx.toString(), l_result);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateQueryString_0003()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqCondition condition = new WEB3AdminInformPTSAccountListInqCondition();
        String[] l_strBranchCodes = new String[3];
        l_strBranchCodes[0] = "381";
        l_strBranchCodes[1] = "382";
        l_strBranchCodes[2] = "383";
        condition.branchCode = l_strBranchCodes;
        
        WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
        try
        {
            Method metod = impl.getClass().getDeclaredMethod(
                "createQueryString",
                new Class[]{WEB3AdminInformPTSAccountListInqCondition.class});
            metod.setAccessible(true);
            String l_result = (String)metod.invoke(impl, new Object[]{condition});
            
            StringBuffer l_sbEx = new StringBuffer();
            l_sbEx.append(" inform_div = ? and institution_code = ? ");
            l_sbEx.append(" and (branch_code = ?  or branch_code = ?  or branch_code = ? )");
            
            assertEquals(l_sbEx.toString(), l_result);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryDataContainer_0001()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqCondition condition = new WEB3AdminInformPTSAccountListInqCondition();
        String[] l_strBranchCodes = new String[1];
        l_strBranchCodes[0] = "381";
        condition.branchCode = l_strBranchCodes;
        condition.accountCode = "123456";
        condition.ptsAccOpenDiv = "1";
        condition.informType = "2";
        condition.applyDateFrom = WEB3DateUtility.getDate("20070606", "yyyyMMdd");
        condition.applyDateTo = WEB3DateUtility.getDate("20080606", "yyyyMMdd");
        
        String l_strInstitutionCode = "0D";
        
        WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
        try
        {
            Method metod = impl.getClass().getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, WEB3AdminInformPTSAccountListInqCondition.class});
            metod.setAccessible(true);
            Object[] l_result = (Object[])metod.invoke(impl, new Object[]{l_strInstitutionCode, condition});

            assertEquals("2", l_result[0]);
            assertEquals("0D", l_result[1]);
            assertEquals("381", l_result[2]);
            assertEquals("123456", l_result[3]);
            assertEquals("1", l_result[4]);
            assertEquals(WEB3DateUtility.getDate("20070606", "yyyyMMdd"), l_result[5]);
            assertEquals(WEB3DateUtility.getDate("20080606", "yyyyMMdd"), l_result[6]);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryDataContainer_0002()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqCondition condition = new WEB3AdminInformPTSAccountListInqCondition();
        String[] l_strBranchCodes = new String[2];
        l_strBranchCodes[0] = "381";
        l_strBranchCodes[1] = "382";
        condition.branchCode = l_strBranchCodes;
        condition.accountCode = "123456";
        condition.ptsAccOpenDiv = "1";
        condition.informType = "2";
        condition.applyDateFrom = WEB3DateUtility.getDate("20070606", "yyyyMMdd");
        condition.applyDateTo = WEB3DateUtility.getDate("20080606", "yyyyMMdd");
        
        String l_strInstitutionCode = "0D";
        
        WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
        try
        {
            Method metod = impl.getClass().getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, WEB3AdminInformPTSAccountListInqCondition.class});
            metod.setAccessible(true);
            Object[] l_result = (Object[])metod.invoke(impl, new Object[]{l_strInstitutionCode, condition});

            assertEquals("2", l_result[0]);
            assertEquals("0D", l_result[1]);
            assertEquals("381", l_result[2]);
            assertEquals("382", l_result[3]);
            assertEquals("123456", l_result[4]);
            assertEquals("1", l_result[5]);
            assertEquals(WEB3DateUtility.getDate("20070606", "yyyyMMdd"), l_result[6]);
            assertEquals(WEB3DateUtility.getDate("20080606", "yyyyMMdd"), l_result[7]);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryDataContainer_0003()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqCondition condition = new WEB3AdminInformPTSAccountListInqCondition();
        String[] l_strBranchCodes = new String[3];
        l_strBranchCodes[0] = "381";
        l_strBranchCodes[1] = "382";
        l_strBranchCodes[2] = "383";
        condition.branchCode = l_strBranchCodes;
        condition.informType = "2";
        
        String l_strInstitutionCode = "0D";
        
        WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
        try
        {
            Method metod = impl.getClass().getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, WEB3AdminInformPTSAccountListInqCondition.class});
            metod.setAccessible(true);
            Object[] l_result = (Object[])metod.invoke(impl, new Object[]{l_strInstitutionCode, condition});

            assertEquals("2", l_result[0]);
            assertEquals("0D", l_result[1]);
            assertEquals("381", l_result[2]);
            assertEquals("382", l_result[3]);
            assertEquals("383", l_result[4]);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSortCondString_0001()
    {
        final String STR_METHOD_NAME = "testCreateSortCondString_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqSortKey[] l_sortKeys = new WEB3AdminInformPTSAccountListInqSortKey[1];
        WEB3AdminInformPTSAccountListInqSortKey key = new WEB3AdminInformPTSAccountListInqSortKey();
        key.keyItem = "branchCode";
        key.ascDesc = "A";
        l_sortKeys[0] = key;
        
        WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
        try
        {
            Method metod = impl.getClass().getDeclaredMethod(
                "createSortCondString",
                new Class[]{WEB3AdminInformPTSAccountListInqSortKey[].class});
            metod.setAccessible(true);
            String l_result = (String)metod.invoke(impl, new Object[]{l_sortKeys});

            String l_ep = " branch_code";
            
            assertEquals(l_ep, l_result);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSortCondString_0002()
    {
        final String STR_METHOD_NAME = "testCreateSortCondString_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqSortKey[] l_sortKeys = new WEB3AdminInformPTSAccountListInqSortKey[1];
        WEB3AdminInformPTSAccountListInqSortKey key = new WEB3AdminInformPTSAccountListInqSortKey();
        key.keyItem = "branchCode";
        key.ascDesc = "D";
        l_sortKeys[0] = key;
        
        WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
        try
        {
            Method metod = impl.getClass().getDeclaredMethod(
                "createSortCondString",
                new Class[]{WEB3AdminInformPTSAccountListInqSortKey[].class});
            metod.setAccessible(true);
            String l_result = (String)metod.invoke(impl, new Object[]{l_sortKeys});

            String l_ep = " branch_code desc";
            
            assertEquals(l_ep, l_result);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSortCondString_0003()
    {
        final String STR_METHOD_NAME = "testCreateSortCondString_0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqSortKey[] l_sortKeys = new WEB3AdminInformPTSAccountListInqSortKey[1];
        WEB3AdminInformPTSAccountListInqSortKey key = new WEB3AdminInformPTSAccountListInqSortKey();
        key.keyItem = "accountCode";
        key.ascDesc = "A";
        l_sortKeys[0] = key;
        
        WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
        try
        {
            Method metod = impl.getClass().getDeclaredMethod(
                "createSortCondString",
                new Class[]{WEB3AdminInformPTSAccountListInqSortKey[].class});
            metod.setAccessible(true);
            String l_result = (String)metod.invoke(impl, new Object[]{l_sortKeys});

            String l_ep = " account_code";
            
            assertEquals(l_ep, l_result);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSortCondString_0004()
    {
        final String STR_METHOD_NAME = "testCreateSortCondString_0004()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqSortKey[] l_sortKeys = new WEB3AdminInformPTSAccountListInqSortKey[1];
        WEB3AdminInformPTSAccountListInqSortKey key = new WEB3AdminInformPTSAccountListInqSortKey();
        key.keyItem = "accountCode";
        key.ascDesc = "D";
        l_sortKeys[0] = key;
        
        WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
        try
        {
            Method metod = impl.getClass().getDeclaredMethod(
                "createSortCondString",
                new Class[]{WEB3AdminInformPTSAccountListInqSortKey[].class});
            metod.setAccessible(true);
            String l_result = (String)metod.invoke(impl, new Object[]{l_sortKeys});

            String l_ep = " account_code desc";
            
            assertEquals(l_ep, l_result);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSortCondString_0005()
    {
        final String STR_METHOD_NAME = "testCreateSortCondString_0005()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqSortKey[] l_sortKeys = new WEB3AdminInformPTSAccountListInqSortKey[1];
        WEB3AdminInformPTSAccountListInqSortKey key = new WEB3AdminInformPTSAccountListInqSortKey();
        key.keyItem = "applyDate";
        key.ascDesc = "A";
        l_sortKeys[0] = key;
        
        WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
        try
        {
            Method metod = impl.getClass().getDeclaredMethod(
                "createSortCondString",
                new Class[]{WEB3AdminInformPTSAccountListInqSortKey[].class});
            metod.setAccessible(true);
            String l_result = (String)metod.invoke(impl, new Object[]{l_sortKeys});

            String l_ep = " created_timestamp";
            
            assertEquals(l_ep, l_result);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSortCondString_0006()
    {
        final String STR_METHOD_NAME = "testCreateSortCondString_0006()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqSortKey[] l_sortKeys = new WEB3AdminInformPTSAccountListInqSortKey[2];
        WEB3AdminInformPTSAccountListInqSortKey key = new WEB3AdminInformPTSAccountListInqSortKey();
        key.keyItem = "applyDate";
        key.ascDesc = "D";
        l_sortKeys[1] = key;
        WEB3AdminInformPTSAccountListInqSortKey key2 = new WEB3AdminInformPTSAccountListInqSortKey();
        key2.keyItem = "accountCode";
        key2.ascDesc = "A";
        l_sortKeys[0] = key2;
        
        WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
        try
        {
            Method metod = impl.getClass().getDeclaredMethod(
                "createSortCondString",
                new Class[]{WEB3AdminInformPTSAccountListInqSortKey[].class});
            metod.setAccessible(true);
            String l_result = (String)metod.invoke(impl, new Object[]{l_sortKeys});

            String l_ep = " account_code,  created_timestamp desc";
            
            assertEquals(l_ep, l_result);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testCreateSortCondString_0007()
    {
        final String STR_METHOD_NAME = "testCreateSortCondString_0006()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqSortKey[] l_sortKeys = new WEB3AdminInformPTSAccountListInqSortKey[2];
        WEB3AdminInformPTSAccountListInqSortKey key = new WEB3AdminInformPTSAccountListInqSortKey();
        key.keyItem = "abcd";
        key.ascDesc = "D";
        l_sortKeys[1] = key;
        WEB3AdminInformPTSAccountListInqSortKey key2 = new WEB3AdminInformPTSAccountListInqSortKey();
        key2.keyItem = "abc";
        key2.ascDesc = "A";
        l_sortKeys[0] = key2;
        
        WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
        try
        {
            Method metod = impl.getClass().getDeclaredMethod(
                "createSortCondString",
                new Class[]{WEB3AdminInformPTSAccountListInqSortKey[].class});
            metod.setAccessible(true);
            String l_result = (String)metod.invoke(impl, new Object[]{l_sortKeys});

            String l_ep = "";
            
            assertEquals(l_ep, l_result);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateSortCondString_0008()
    {
        final String STR_METHOD_NAME = "testCreateSortCondString_0007()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqSortKey[] l_sortKeys = new WEB3AdminInformPTSAccountListInqSortKey[0];
        
        WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
        try
        {
            Method metod = impl.getClass().getDeclaredMethod(
                "createSortCondString",
                new Class[]{WEB3AdminInformPTSAccountListInqSortKey[].class});
            metod.setAccessible(true);
            String l_result = (String)metod.invoke(impl, new Object[]{l_sortKeys});

            String l_ep = "";
            
            assertEquals(l_ep, l_result);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSearchScreen_0001()
    {
        final String STR_METHOD_NAME = "testGetSearchScreen_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl
                );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long("111")
                );
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
            impl.getSearchScreen(null);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSearchScreen_0002()
    {
        final String STR_METHOD_NAME = "testGetSearchScreen_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl
                );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long("111")
                );

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, "A0501", false, true);
            
            WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
            WEB3AdminInformPTSAccountListInquiryRequest l_request = new WEB3AdminInformPTSAccountListInquiryRequest();
            WEB3AdminInformPTSAccountListInquiryResponse l_response = impl.getSearchScreen(l_request);

            Timestamp l_tsCurrentDate = GtlUtils.getSystemTimestamp();

            assertEquals(0, WEB3DateUtility.compareToDay(WEB3GentradeUtils.getBizDate(l_tsCurrentDate, -1), l_response.applyDateFrom));
            assertEquals(0, WEB3DateUtility.compareToDay(WEB3DateUtility.addDay(l_tsCurrentDate, -1), l_response.applyDateTo));        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSearchResultScreen_0001()
    {
        final String STR_METHOD_NAME = "testGetSearchResultScreen_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl
                );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long("111")
                );

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, "A0501", false, true);
            
            WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
            WEB3AdminInformPTSAccountListResultRequest l_request = new WEB3AdminInformPTSAccountListResultRequest();
            WEB3AdminInformPTSAccountListResultResponse l_response = impl.getSearchResultScreen(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00945, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSearchResultScreen_0002()
    {
        final String STR_METHOD_NAME = "testGetSearchResultScreen_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl
                );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long("111")
                );

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
//            WEB3AdministratorForMock.mockValidateAuthority(
//                l_expectAdministrator, "A0501", false, true);

            WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
            WEB3AdminInformPTSAccountListResultRequest request = new WEB3AdminInformPTSAccountListResultRequest();
            WEB3AdminInformPTSAccountListInqCondition condition = new WEB3AdminInformPTSAccountListInqCondition();
            condition.informType = "1";
            String[] branchCode = new String[1];
            branchCode[0] = "111";
            condition.branchCode = branchCode;
            condition.accountCode = "123456";
            condition.ptsAccOpenDiv = "1";
            condition.applyDateFrom = WEB3DateUtility.getDate("20070606", "yyyyMMdd");
            condition.applyDateTo = WEB3DateUtility.getDate("20070607", "yyyyMMdd");
            request.searchCondition = condition;
            WEB3AdminInformPTSAccountListInqSortKey[] keys = new WEB3AdminInformPTSAccountListInqSortKey[1];
            WEB3AdminInformPTSAccountListInqSortKey key = new WEB3AdminInformPTSAccountListInqSortKey();
            key.keyItem = "applyDate";
            key.ascDesc = "D";
            keys[0] = key;
            request.sortKeys = keys;
            request.pageIndex = "2";
            request.pageSize = "2";
            WEB3AdminInformPTSAccountListResultResponse l_response = impl.getSearchResultScreen(request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSearchResultScreen_0003()
    {
        final String STR_METHOD_NAME = "testGetSearchResultScreen_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl
                );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long("111")
                );

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, "A0501", false, true);
            
            TestDBUtility.deleteAll(VariousInformRow.TYPE);
//            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
//            l_variousInformParams.setInformDiv("1");
//            l_variousInformParams.setInstitutionCode("0D");
//            l_variousInformParams.setBranchCode("111");
//            TestDBUtility.insertWithDel(l_variousInformParams);

            WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
            WEB3AdminInformPTSAccountListResultRequest request = new WEB3AdminInformPTSAccountListResultRequest();
            WEB3AdminInformPTSAccountListInqCondition condition = new WEB3AdminInformPTSAccountListInqCondition();
            String[] branchCode = new String[1];
            branchCode[0] = "111";
            condition.branchCode = branchCode;
            condition.informType = "1";
            request.searchCondition = condition;
            WEB3AdminInformPTSAccountListInqSortKey[] keys = new WEB3AdminInformPTSAccountListInqSortKey[1];
            WEB3AdminInformPTSAccountListInqSortKey key = new WEB3AdminInformPTSAccountListInqSortKey();
            key.keyItem = "applyDate";
            key.ascDesc = "D";
            keys[0] = key;
            request.sortKeys = keys;
            request.pageIndex = "2";
            request.pageSize = "2";
            WEB3AdminInformPTSAccountListResultResponse l_response = impl.getSearchResultScreen(request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSearchResultScreen_0004()
    {
        final String STR_METHOD_NAME = "testGetSearchResultScreen_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl
                );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long("111")
                );

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, "A0501", false, true);
            
            TestDBUtility.deleteAll(VariousInformRow.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setInformDiv("1");
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setBranchCode("111");
            TestDBUtility.insertWithDel(l_variousInformParams);

            WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
            WEB3AdminInformPTSAccountListResultRequest request = new WEB3AdminInformPTSAccountListResultRequest();
            WEB3AdminInformPTSAccountListInqCondition condition = new WEB3AdminInformPTSAccountListInqCondition();
            String[] branchCode = new String[1];
            branchCode[0] = "111";
            condition.branchCode = branchCode;
            condition.informType = "1";
            request.searchCondition = condition;
            WEB3AdminInformPTSAccountListInqSortKey[] keys = new WEB3AdminInformPTSAccountListInqSortKey[1];
            WEB3AdminInformPTSAccountListInqSortKey key = new WEB3AdminInformPTSAccountListInqSortKey();
            key.keyItem = "applyDate";
            key.ascDesc = "D";
            keys[0] = key;
            request.sortKeys = keys;
            request.pageIndex = "1";
            request.pageSize = "5";
            WEB3AdminInformPTSAccountListResultResponse l_response = impl.getSearchResultScreen(request);
            
            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.totalPages);
            assertEquals("1", l_response.totalRecords);
            assertNull(l_response.ptsAccountInfoList[0].accountCode);
        }
        catch  (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetSearchResultScreen_0005()
    {
        final String STR_METHOD_NAME = "testGetSearchResultScreen_0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl
                );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long("111")
                );

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, "A0501", false, true);
            
            TestDBUtility.deleteAll(VariousInformRow.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setInformDiv("1");
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setBranchCode("111");
            l_variousInformParams.setAccountCode("1234567");
            TestDBUtility.insertWithDel(l_variousInformParams);

            WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
            WEB3AdminInformPTSAccountListResultRequest request = new WEB3AdminInformPTSAccountListResultRequest();
            WEB3AdminInformPTSAccountListInqCondition condition = new WEB3AdminInformPTSAccountListInqCondition();
            String[] branchCode = new String[1];
            branchCode[0] = "111";
            condition.branchCode = branchCode;
            condition.informType = "1";
            request.searchCondition = condition;
            WEB3AdminInformPTSAccountListInqSortKey[] keys = new WEB3AdminInformPTSAccountListInqSortKey[1];
            WEB3AdminInformPTSAccountListInqSortKey key = new WEB3AdminInformPTSAccountListInqSortKey();
            key.keyItem = "applyDate";
            key.ascDesc = "D";
            keys[0] = key;
            request.sortKeys = keys;
            request.pageIndex = "1";
            request.pageSize = "5";
            WEB3AdminInformPTSAccountListResultResponse l_response = impl.getSearchResultScreen(request);
            
            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.totalPages);
            assertEquals("1", l_response.totalRecords);
            assertEquals("123456", l_response.ptsAccountInfoList[0].accountCode);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_0001()
    {
        final String STR_METHOD_NAME = "testExecute_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();

            impl.execute(null);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_0002()
    {
        final String STR_METHOD_NAME = "testExecute_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("123")
                );
            
            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl
                );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long("111")
                );

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
//            WEB3AdministratorForMock.mockValidateAuthority(
//                l_expectAdministrator, "A0501", false, true);

            WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();

            WEB3AdminInformPTSAccountListInquiryRequest request = new WEB3AdminInformPTSAccountListInquiryRequest();
            impl.execute(request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_0003()
    {
        final String STR_METHOD_NAME = "testExecute_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
            WEB3AdminInformPTSAccountListResultRequest request = new WEB3AdminInformPTSAccountListResultRequest();
            impl.execute(request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00945, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_0004()
    {
        final String STR_METHOD_NAME = "testExecute_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminInformPTSAccountListServiceImpl impl = new WEB3AdminInformPTSAccountListServiceImpl();
            WEB3AdminInformAccSwElecDeliDeleteConfRequest request = new WEB3AdminInformAccSwElecDeliDeleteConfRequest();
            impl.execute(request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
