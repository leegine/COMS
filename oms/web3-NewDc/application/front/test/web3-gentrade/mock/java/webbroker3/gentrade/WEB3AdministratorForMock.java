head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.16.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdministratorForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.mock.TestBaseForMock;

public class WEB3AdministratorForMock extends WEB3Administrator
{
    public WEB3AdministratorForMock(AdministratorParams l_administratorParams)
    {
        super(l_administratorParams);
        // TODO Auto-generated constructor stub
    }
    
    /**
     * (getInstanceFromログイン情報)のMockメソッド<BR>
     * <BR>
     * @@param l_expectAdministrator 管理者
     * @@throws WEB3SystemLayerException
     */
    public static void mockGetInstanceFromLoginInfo(WEB3Administrator l_expectAdministrator)
        throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
            
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(l_expectAdministrator.getLoginId()));
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
       
        AdministratorRow l_administratorRow =
            (AdministratorRow)l_expectAdministrator.getDataSourceObject();
        TestDBUtility.insertWithDel(l_administratorRow);
    }
    
    /**
     * 
     */
    public static void mockIsDirAdministrator(WEB3Administrator l_expectAdministrator, boolean l_blnIsDirAdministrator)
    throws WEB3BaseException
    {
        AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
        l_administratorTypeParams.setInstitutionCode(l_expectAdministrator.getInstitutionCode());
        l_administratorTypeParams.setPermissionLevel(l_expectAdministrator.getPermissionLevel());
        l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
        l_administratorTypeParams.setCreatedTimestamp(new Date());
        l_administratorTypeParams.setUpdateTimestamp(new Date());
        if (l_blnIsDirAdministrator)
        {
            l_administratorTypeParams.setDirAdminFlag(1);
        }
        else 
        {
            l_administratorTypeParams.setDirAdminFlag(2);
        }
        
        TestDBUtility.deleteAll(l_administratorTypeParams.getRowType());
        TestDBUtility.insertWithDel(l_administratorTypeParams);
    }
    
    /**
     * (validate権限)のMockメソッド<BR>
     * <BR>
     * @@param l_expectAdministrator 管理者
     * @@param l_strTransactionCategory 機@能カテゴリコード
     * @@param l_isUpdate is更新
     * @@param l_isPass isPass
     *  (1)true: pass,(2)false: throws Exception
     * @@throws WEB3BaseException
     */
    public static void mockValidateAuthority(
        WEB3Administrator l_expectAdministrator,
        String l_strTransactionCategory,
        boolean l_isUpdate,
        boolean l_isPass)
        throws WEB3BaseException
    {
        TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
        if(l_isPass)
        {
            AdminPermissionParams l_adminPermissionParams =
                new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode(l_expectAdministrator.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_expectAdministrator.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory(l_strTransactionCategory);
            if(l_isUpdate)
            {
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            }
            else
            {
                l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            }
            Timestamp l_time = GtlUtils.getSystemTimestamp();
            l_adminPermissionParams.setCreatedTimestamp(l_time);
            l_adminPermissionParams.setUpdateTimestamp(l_time);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
        }
    }
    
    /**
     * 
     * @@param l_strPassword 入力パスワード
     * @@param l_isPass isPass
     *  (1)true: pass,(2)false: throws Exception
     */
    public static void mockValidateTradingPassword(
        String l_strPassword,
        boolean l_isPass)
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
                
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginTypeId",
            new Class[] {},
            new Long(123l));
        
        HashMap l_map = new HashMap();
        l_map.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV,WEB3TradingPwdEnvDef.DEFAULT);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_map);
        
        if(l_isPass)
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);
        }
        else
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "checkPassword",
                new Class[] {String.class},
                Boolean.FALSE);
        }
        
    }

    /**
     * 
     * @@param l_expectAdministrator 管理者
     * @@param l_strBranchCode 部店コード
     * @@param l_isPass isPass
     *  (1)true: pass,(2)false: throws Exception
     */
    public static void mockValidateBranchPermission(
        WEB3Administrator l_expectAdministrator,
        String l_strBranchCode,
        boolean l_isPass)
        throws WEB3BaseException
    {
        String l_str1 = "901";
        String l_str2 = "830";
        String l_strTemp = l_str1;
        if(l_str1.equals(l_strBranchCode))
        {
            l_strTemp = l_str2;
        }
        
        AdministratorRow l_adminstratorRow =
            (AdministratorRow)l_expectAdministrator.getDataSourceObject();
        AdministratorParams l_adminstratorParams = new AdministratorParams(l_adminstratorRow);

        l_adminstratorParams.setBranchCode(l_strTemp);
        try
        {
            Field l_field = WEB3Administrator.class.getDeclaredField("administratorParams");
            l_field.setAccessible(true);
            l_field.set(l_expectAdministrator, l_adminstratorParams);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        AdministratorTypeParams l_administratorTypeParams =
            new AdministratorTypeParams();
        l_administratorTypeParams.setInstitutionCode(l_adminstratorParams.getInstitutionCode());
        l_administratorTypeParams.setPermissionLevel(l_adminstratorParams.getPermissionLevel());
        l_administratorTypeParams.setDirAdminFlag(0);
        Timestamp l_time = GtlUtils.getSystemTimestamp();
        l_administratorTypeParams.setCreatedTimestamp(l_time);
        l_administratorTypeParams.setUpdateTimestamp(l_time);
        if(l_isPass)
        {
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
        }
        else
        {
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
        }
        TestDBUtility.insertWithDel(l_administratorTypeParams);
    }

    /**
     * 
     * @@param l_expectAdministrator 管理者
     * @@param l_strBranchCodes 部店コード[]
     * @@param l_isPass isPass
     *   (1)true: pass,(2)false: throws Exception
     * @@throws WEB3BaseException
     */
    public static void mockValidateBranchPermission(
        WEB3Administrator l_expectAdministrator,
        String[] l_strBranchCodes,
        boolean l_isPass)
        throws WEB3BaseException
    {
        String l_str1 = "901";
        String l_str2 = "456";
        String l_strTemp = l_str1;

        if(l_str1.equals(l_strBranchCodes[0]))
        {
            l_strTemp = l_str2;
        }

        AdministratorRow l_adminstratorRow =
            (AdministratorRow)l_expectAdministrator.getDataSourceObject();
        AdministratorParams l_adminstratorParams = new AdministratorParams(l_adminstratorRow);
        
        l_adminstratorParams.setBranchCode(l_strTemp);
        try
        {
            Field l_field = WEB3Administrator.class.getDeclaredField("administratorParams");
            l_field.setAccessible(true);
            l_field.set(l_expectAdministrator, l_adminstratorParams);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        AdministratorTypeParams l_administratorTypeParams =
            new AdministratorTypeParams();
        l_administratorTypeParams.setInstitutionCode(l_adminstratorParams.getInstitutionCode());
        l_administratorTypeParams.setPermissionLevel(l_adminstratorParams.getPermissionLevel());
        l_administratorTypeParams.setDirAdminFlag(0);
        Timestamp l_time = GtlUtils.getSystemTimestamp();
        l_administratorTypeParams.setCreatedTimestamp(l_time);
        l_administratorTypeParams.setUpdateTimestamp(l_time);
        if(l_isPass)
        {
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
        }
        else
        {
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
        }
        TestDBUtility.insertWithDel(l_administratorTypeParams);
    }
}
@
