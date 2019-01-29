head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.39.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoDepositClientRequestServiceTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3IfoDepositClientRequestServiceTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/08/20  張少傑 (中訊) 新規作成
*/

package webbroker3.ifodeposit.service.delegate;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.MainAccountImpl;
import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FutureOpAccountDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositTransitionReferenceServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoDepositClientRequestServiceTest extends TestBaseForMock
{
    WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDepositClientRequestServiceTest.class);
    public WEB3IfoDepositClientRequestServiceTest(String arg0)
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
    //先物OP口座未開設の場合、先物OP口座未開設エラー」の例外をthrowする。
    public void testGetSubAccountC001()
    {
        String STR_METHOD_NAME = " testGetSubAccountC001";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(4L));
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(4L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("0");
            l_mainAccountParams.setIfoAccOpenDivOsaka("0");
            l_mainAccountParams.setIfoAccOpenDivNagoya("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(4L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            WEB3IfoDepositClientRequestService l_service =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
            l_service.getSubAccount(); 
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01294, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //顧客.先物OP口座開設区分(東証）　@＝　@先物OP口座開設
    public void testGetSubAccountC002()
    {
        String STR_METHOD_NAME = " testGetSubAccountC002";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(4L));
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(4L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(4L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            WEB3IfoDepositClientRequestService l_service =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
            WEB3GentradeSubAccount l_subAccount = l_service.getSubAccount(); 
            
            assertEquals(l_subAccountParams.getSubAccountId(), l_subAccount.getSubAccountId());
            assertEquals(l_subAccountParams.getAccountId(), l_subAccount.getAccountId());
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            fail();
        }
        catch(Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //顧客.先物OP口座開設区分(大証）　@＝　@先物口座開設
    public void testGetSubAccountC003()
    {
        String STR_METHOD_NAME = " testGetSubAccountC003";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(4L));
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(4L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(4L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            WEB3IfoDepositClientRequestService l_service =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
            WEB3GentradeSubAccount l_subAccount = l_service.getSubAccount(); 
            
            assertEquals(l_subAccountParams.getSubAccountId(), l_subAccount.getSubAccountId());
            assertEquals(l_subAccountParams.getAccountId(), l_subAccount.getAccountId());
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            fail();
        }
        catch(Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //顧客.先物OP口座開設区分(名証）　@＝　@先物OP口座開設
    public void testGetSubAccountC004()
    {
        String STR_METHOD_NAME = " testGetSubAccountC004";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(4L));
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(4L);
            l_mainAccountParams.setIfoAccOpenDivNagoya("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(4L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            WEB3IfoDepositClientRequestService l_service =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
            WEB3GentradeSubAccount l_subAccount = l_service.getSubAccount(); 
            
            assertEquals(l_subAccountParams.getSubAccountId(), l_subAccount.getSubAccountId());
            assertEquals(l_subAccountParams.getAccountId(), l_subAccount.getAccountId());
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            fail();
        }
        catch(Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //証拠金口座未開設の場合
    public void testGetSubAccountC005()
    {
        String STR_METHOD_NAME = " testGetSubAccountC005";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(4L));
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(4L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(4L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            WEB3IfoDepositClientRequestService l_service =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
            WEB3GentradeSubAccount l_subAccount = l_service.getSubAccount(); 
            
            assertEquals(null, l_subAccount);
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            fail();
        }
        catch(Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //先物OP口座開設区分（東証）が先物OP口座開設済の場合,返回true
    public void testIsFuturesOptionAccountOpenC001()
    {
        String STR_METHOD_NAME = " testIsFuturesOptionAccountOpenC001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            MainAccountRow l_mainAccountRow = new MainAccountParams(l_mainAccountParams);
            
            WEB3IfoDepositClientRequestService l_service =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
 
            MainAccount l_mainAccount = new MainAccountImpl(l_mainAccountRow);
            Object[] obj = {l_mainAccount};
            
            Method method = WEB3IfoDepositClientRequestService.class.getDeclaredMethod( ////被測方法@所在的類名
                "isFuturesOptionAccountOpen",  //<------------- 被測方法@名
                new Class[]{MainAccount.class});//< ------------ 方法@參數的類型
            method.setAccessible(true); //<-------------開關
            Boolean l_bl = (Boolean)method.invoke(l_service, obj);  //<---------return
            assertEquals(true,l_bl.booleanValue());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //先物OP口座開設区分（大証）が先物OP口座開設済の場合，返回true
    public void testIsFuturesOptionAccountOpenC002()
    {
        String STR_METHOD_NAME = " testIsFuturesOptionAccountOpenC002";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivOsaka("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            MainAccountRow l_mainAccountRow = new MainAccountParams(l_mainAccountParams);
            
            WEB3IfoDepositClientRequestService l_service =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
 
            MainAccount l_mainAccount = new MainAccountImpl(l_mainAccountRow);
            Object[] obj = {l_mainAccount};
            
            Method method = WEB3IfoDepositClientRequestService.class.getDeclaredMethod( ////被測方法@所在的類名
                "isFuturesOptionAccountOpen",  //<------------- 被測方法@名
                new Class[]{MainAccount.class});//< ------------ 方法@參數的類型
            method.setAccessible(true); //<-------------開關
            Boolean l_bl = (Boolean)method.invoke(l_service, obj);  //<---------return
            assertEquals(true,l_bl.booleanValue());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //先物OP口座開設区分（名証）が先物OP口座開設済の場合，返回true
    public void testIsFuturesOptionAccountOpenC003()
    {
        String STR_METHOD_NAME = " testIsFuturesOptionAccountOpenC003";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivNagoya("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            MainAccountRow l_mainAccountRow = new MainAccountParams(l_mainAccountParams);
            
            WEB3IfoDepositClientRequestService l_service =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
 
            MainAccount l_mainAccount = new MainAccountImpl(l_mainAccountRow);
            Object[] obj = {l_mainAccount};
            
            Method method = WEB3IfoDepositClientRequestService.class.getDeclaredMethod( ////被測方法@所在的類名
                "isFuturesOptionAccountOpen",  //<------------- 被測方法@名
                new Class[]{MainAccount.class});//< ------------ 方法@參數的類型
            method.setAccessible(true); //<-------------開關
            Boolean l_bl = (Boolean)method.invoke(l_service, obj);  //<---------return
            assertEquals(true,l_bl.booleanValue());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //先物OP口座開設区分（名証）が「DEFAULT（口座なし）」の場合，返回false
    public void testIsFuturesOptionAccountOpenC004()
    {
        String STR_METHOD_NAME = " testIsFuturesOptionAccountOpenC004";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivNagoya("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            MainAccountRow l_mainAccountRow = new MainAccountParams(l_mainAccountParams);
            
            WEB3IfoDepositClientRequestService l_service =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
 
            MainAccount l_mainAccount = new MainAccountImpl(l_mainAccountRow);
            Object[] obj = {l_mainAccount};
            
            Method method = WEB3IfoDepositClientRequestService.class.getDeclaredMethod( ////被測方法@所在的類名
                "isFuturesOptionAccountOpen",  //<------------- 被測方法@名
                new Class[]{MainAccount.class});//< ------------ 方法@參數的類型
            method.setAccessible(true); //<-------------開關
            Boolean l_bl = (Boolean)method.invoke(l_service, obj);  //<---------return
            assertEquals(false,l_bl.booleanValue());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //先物OP口座開設区分（名証）が「DEFAULT（口座なし）」の場合，返回false
    public void testIsFuturesOptionAccountOpen_case001()
    {
        String STR_METHOD_NAME = " testIsFuturesOptionAccountOpen_case001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            String l_strIfoAccountOpenDiv = WEB3FutureOpAccountDef.DEFAULT;
            
            WEB3IfoDepositClientRequestService l_service =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
            
            Object[] obj = {l_strIfoAccountOpenDiv};
            
            Method method = WEB3IfoDepositClientRequestService.class.getDeclaredMethod( ////被測方法@所在的類名
                "isFuturesOptionAccountOpen",  //<------------- 被測方法@名
                new Class[]{String.class});//< ------------ 方法@參數的類型
            
            method.setAccessible(true); //<-------------開關
            
            Boolean l_bl = (Boolean)method.invoke(l_service, obj);  //<---------return
            assertEquals(false,l_bl.booleanValue());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    //先物OP口座開設区分が「DEFAULT（口座なし）」以外の場合,返回true
    public void testIsFuturesOptionAccountOpen_case002()
    {
        String STR_METHOD_NAME = " testIsFuturesOptionAccountOpen_case002";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            String l_strIfoAccountOpenDiv = WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH;
            
            WEB3IfoDepositClientRequestService l_service =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
            
            Object[] obj = {l_strIfoAccountOpenDiv};
            
            Method method = WEB3IfoDepositClientRequestService.class.getDeclaredMethod( ////被測方法@所在的類名
                "isFuturesOptionAccountOpen",  //<------------- 被測方法@名
                new Class[]{String.class});//< ------------ 方法@參數的類型
            
            method.setAccessible(true); //<-------------開關
            
            Boolean l_bl = (Boolean)method.invoke(l_service, obj);  //<---------return
            assertEquals(true,l_bl.booleanValue());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //先物OP口座開設区分（東証）が「先物OP口座開設」の場合,返回true
    public void testIsIfoDepositAccountOpenC001()
    {
        String STR_METHOD_NAME = " testIsIfoDepositAccountOpenC001"; 
        log.entering(STR_METHOD_NAME);
        try
        {
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            MainAccountRow l_mainAccountRow = new MainAccountParams(l_mainAccountParams);
            
            WEB3IfoDepositClientRequestService l_service =
                new WEB3IfoDepositTransitionReferenceServiceImpl();

            MainAccount l_mainAccount = new MainAccountImpl(l_mainAccountRow);
            
            Object[] object = {l_mainAccount};
            
            Method method = WEB3IfoDepositClientRequestService.class.getDeclaredMethod(
                    "isIfoDepositAccountOpen",
                    new Class[]{MainAccount.class});
            
            method.setAccessible(true);
            Boolean l_bl = (Boolean)method.invoke(l_service, object);
            assertEquals(true,l_bl.booleanValue());
        }
        catch(Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //先物OP口座開設区分（大証）が「先物OP口座開設」の場合,返回true
    public void testIsIfoDepositAccountOpenC002()
    {
        String STR_METHOD_NAME = " testIsIfoDepositAccountOpenC002"; 
        log.entering(STR_METHOD_NAME);
        try
        {
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivOsaka("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            MainAccountRow l_mainAccountRow = new MainAccountParams(l_mainAccountParams);
            
            WEB3IfoDepositClientRequestService l_service =
                new WEB3IfoDepositTransitionReferenceServiceImpl();

            MainAccount l_mainAccount = new MainAccountImpl(l_mainAccountRow);
            
            Object[] object = {l_mainAccount};
            
            Method method = WEB3IfoDepositClientRequestService.class.getDeclaredMethod(
                    "isIfoDepositAccountOpen",
                    new Class[]{MainAccount.class});
            
            method.setAccessible(true);
            Boolean l_bl = (Boolean)method.invoke(l_service, object);
            assertEquals(true,l_bl.booleanValue());
        }
        catch(Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //先物OP口座開設区分（名証）が「先物口座開設」の場合,返回true
    public void testIsIfoDepositAccountOpenC003()
    {
        String STR_METHOD_NAME = " testIsIfoDepositAccountOpenC003"; 
        log.entering(STR_METHOD_NAME);
        try
        {
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivNagoya("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            MainAccountRow l_mainAccountRow = new MainAccountParams(l_mainAccountParams);
            
            WEB3IfoDepositClientRequestService l_service =
                new WEB3IfoDepositTransitionReferenceServiceImpl();

            MainAccount l_mainAccount = new MainAccountImpl(l_mainAccountRow);
            
            Object[] object = {l_mainAccount};
            
            Method method = WEB3IfoDepositClientRequestService.class.getDeclaredMethod(
                    "isIfoDepositAccountOpen",
                    new Class[]{MainAccount.class});
            
            method.setAccessible(true);
            Boolean l_bl = (Boolean)method.invoke(l_service, object);
            assertEquals(true,l_bl.booleanValue());
        }
        catch(Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    // 先物OP口座開設区分が「OP口座開設」の場合,返回false
    public void testIsIfoDepositAccountOpenC004()
    {
        String STR_METHOD_NAME = " testIsIfoDepositAccountOpenC004"; 
        log.entering(STR_METHOD_NAME);
        try
        {
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            MainAccountRow l_mainAccountRow = new MainAccountParams(l_mainAccountParams);
            
            WEB3IfoDepositClientRequestService l_service =
                new WEB3IfoDepositTransitionReferenceServiceImpl();

            MainAccount l_mainAccount = new MainAccountImpl(l_mainAccountRow);
            
            Object[] object = {l_mainAccount};
            
            Method method = WEB3IfoDepositClientRequestService.class.getDeclaredMethod(
                    "isIfoDepositAccountOpen",
                    new Class[]{MainAccount.class});
            
            method.setAccessible(true);
            Boolean l_bl = (Boolean)method.invoke(l_service, object);
            assertEquals(false,l_bl.booleanValue());
        }
        catch(Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //先物OP口座開設区分が「先物OP口座開設」の場合,返回true
    public void testIsIfoDepositAccountOpencase001()
    {
        String STR_METHOD_NAME = " testIsIfoDepositAccountOpencase001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            String l_strIfoAccountOpenDiv = WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH;
            
            WEB3IfoDepositClientRequestService l_service =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
            
            Object[] obj = {l_strIfoAccountOpenDiv};
            
            Method method = WEB3IfoDepositClientRequestService.class.getDeclaredMethod( ////被測方法@所在的類名
                "isIfoDepositAccountOpen",  //<------------- 被測方法@名
                new Class[]{String.class});//< ------------ 方法@參數的類型
            
            method.setAccessible(true); //<-------------開關
            
            Boolean l_bl = (Boolean)method.invoke(l_service, obj);  //<---------return
            assertEquals(true,l_bl.booleanValue());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //先物OP口座開設区分が「先物口座開設」の場合,返回true
    public void testIsIfoDepositAccountOpencase002()
    {
        String STR_METHOD_NAME = " testIsIfoDepositAccountOpencase002";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            String l_strIfoAccountOpenDiv = WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH;
            
            WEB3IfoDepositClientRequestService l_service =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
            
            Object[] obj = {l_strIfoAccountOpenDiv};
            
            Method method = WEB3IfoDepositClientRequestService.class.getDeclaredMethod( ////被測方法@所在的類名
                "isIfoDepositAccountOpen",  //<------------- 被測方法@名
                new Class[]{String.class});//< ------------ 方法@參數的類型
            
            method.setAccessible(true); //<-------------開關
            
            Boolean l_bl = (Boolean)method.invoke(l_service, obj);  //<---------return
            assertEquals(true,l_bl.booleanValue());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    // 先物OP口座開設区分が「OP口座開設」の場合,返回true
    public void testIsIfoDepositAccountOpencase003()
    {
        String STR_METHOD_NAME = " testIsIfoDepositAccountOpencase001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            String l_strIfoAccountOpenDiv = WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH;
            
            WEB3IfoDepositClientRequestService l_service =
                new WEB3IfoDepositTransitionReferenceServiceImpl();
            
            Object[] obj = {l_strIfoAccountOpenDiv};
            
            Method method = WEB3IfoDepositClientRequestService.class.getDeclaredMethod( ////被測方法@所在的類名
                "isIfoDepositAccountOpen",  //<------------- 被測方法@名
                new Class[]{String.class});//< ------------ 方法@參數的類型
            
            method.setAccessible(true); //<-------------開關
            
            Boolean l_bl = (Boolean)method.invoke(l_service, obj);  //<---------return
            assertEquals(false,l_bl.booleanValue());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
