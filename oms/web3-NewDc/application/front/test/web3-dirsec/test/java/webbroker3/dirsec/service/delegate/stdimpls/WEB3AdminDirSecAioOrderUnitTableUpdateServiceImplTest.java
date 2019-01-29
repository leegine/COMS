head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.03.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecAioOrderUnitTableUpdateServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DirAdminFlagDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchResultRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchResultResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminDirSecAioOrderUnitTableUpdateServiceImplTest extends TestBaseForMock
{

    public WEB3AdminDirSecAioOrderUnitTableUpdateServiceImplTest(String arg0)
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

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AdminDirSecAioOrderUnitTableUpdateServiceImplTest.class);

    public void testUpdateMutualFundOrderUnitC0001()
    {
        final String STR_METHOD_NAME = " testUpdateMutualFundOrderUnitC0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        Method method = null;
        try
        {
            TestDBUtility.deleteAll(MutualFundOrderUnitParams.TYPE);

            MutualFundOrderUnitParams l_mutualFundOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
            l_mutualFundOrderUnitParams.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            
            WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl =
                new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();
            
            method = WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.class.getDeclaredMethod(
                "updateMutualFundOrderUnit",
                new Class[]{long.class, String.class, String.class, String.class});
            
            method.setAccessible(true);

            Object[] l_obj = {new Long(1001L), null, "2", "3"};
            
            method.invoke(l_impl, l_obj);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testUpdateMutualFundOrderUnitC0002()
    {
        final String STR_METHOD_NAME = " testUpdateMutualFundOrderUnitC0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        Method method = null;
        try
        {
            TestDBUtility.deleteAll(MutualFundOrderUnitParams.TYPE);

            MutualFundOrderUnitParams l_mutualFundOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
            l_mutualFundOrderUnitParams.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            
            WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl =
                new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();
            
            method = WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.class.getDeclaredMethod(
                "updateMutualFundOrderUnit",
                new Class[]{long.class, String.class, String.class, String.class});
            
            method.setAccessible(true);

            Object[] l_obj = {new Long(1001L), "1", null, null};
            
            method.invoke(l_impl, l_obj);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testGetUpdateCompleteScreenC0001()
    {
        final String STR_METHOD_NAME = " testGetUpdateCompleteScreenC0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorId(1001L);
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setLoginId(1001L);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ORDER_UNIT_UPDATE);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(MutualFundOrderUnitParams.TYPE);

            MutualFundOrderUnitParams l_mutualFundOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
            l_mutualFundOrderUnitParams.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);

            WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest l_request =
                new WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest();
            l_request.orderUnitId = "1001";
            l_request.updateOrderStatus = "1";
            l_request.updateOrderOpenStatus = "1";
            l_request.password = "123";
            l_request.orderUnitTblKbn = "2";
            WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl =
                new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();
            l_impl.getUpdateCompleteScreen(l_request);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testGetUpdateCompleteScreenC0002()
    {
        final String STR_METHOD_NAME = " testGetUpdateCompleteScreenC0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorId(1001L);
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setLoginId(1001L);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ORDER_UNIT_UPDATE);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(MutualFundOrderUnitParams.TYPE);

            MutualFundOrderUnitParams l_mutualFundOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
            l_mutualFundOrderUnitParams.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);

            WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest l_request =
                new WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest();
            l_request.orderUnitId = "1001";
            l_request.updateOrderStatus = null;
            l_request.updateOrderOpenStatus = null;
            l_request.password = "123";
            l_request.orderUnitTblKbn = "2";
            l_request.updateExecStatus = null;
            WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl =
                new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();
            l_impl.getUpdateCompleteScreen(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02519, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    /*
     * リクエストデータ.注文単位テーブル区分 == 3(株式)の場合、
     */
    public void testGetUpdateCompleteScreenC0003()
    {
        final String STR_METHOD_NAME = " testGetUpdateCompleteScreenC0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorId(1001L);
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setLoginId(1001L);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ORDER_UNIT_UPDATE);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(MutualFundOrderUnitParams.TYPE);

            MutualFundOrderUnitParams l_mutualFundOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
            l_mutualFundOrderUnitParams.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(1001L);
            l_eqtypeOrderUnitParams.setBizDate("20070117");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest l_request =
                new WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest();
            l_request.orderUnitId = "1001";
            l_request.updateOrderStatus = "1";
            l_request.updateOrderOpenStatus = "1";
            l_request.password = "123";
            l_request.orderUnitTblKbn = "3";
            l_request.updateOrderBizDate = "20080717";
            WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl =
                new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();
            l_impl.getUpdateCompleteScreen(l_request);

            EqtypeOrderUnitRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "order_unit_id = ? ";
            Object[] l_objWhere = {"1001"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_strWhere,
                    l_objWhere);

            l_row = (EqtypeOrderUnitRow)l_lstRecords.get(0);

            assertEquals(OrderStatusEnum.ACCEPTED,l_row.getOrderStatus());
            assertEquals(OrderOpenStatusEnum.OPEN,l_row.getOrderOpenStatus());
            assertEquals("20080717",l_row.getBizDate());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    /*
     * リクエストデータ.注文単位テーブル区分 == 4(先物OP)の場合、
     */
    public void testGetUpdateCompleteScreenC0004()
    {
        final String STR_METHOD_NAME = " testGetUpdateCompleteScreenC0004";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorId(1001L);
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setLoginId(1001L);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ORDER_UNIT_UPDATE);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(MutualFundOrderUnitParams.TYPE);

            MutualFundOrderUnitParams l_mutualFundOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
            l_mutualFundOrderUnitParams.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1001L);
            l_ifoOrderUnitParams.setBizDate("20070117");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest l_request =
                new WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest();
            l_request.orderUnitId = "1001";
            l_request.updateOrderStatus = "1";
            l_request.updateOrderOpenStatus = "1";
            l_request.password = "123";
            l_request.orderUnitTblKbn = "4";
            l_request.updateOrderBizDate = "20080717";
            WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl =
                new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();
            l_impl.getUpdateCompleteScreen(l_request);

            IfoOrderUnitRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "order_unit_id = ? ";
            Object[] l_objWhere = {"1001"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    IfoOrderUnitRow.TYPE,
                    l_strWhere,
                    l_objWhere);

            l_row = (IfoOrderUnitRow)l_lstRecords.get(0);

            assertEquals(OrderStatusEnum.ACCEPTED,l_row.getOrderStatus());
            assertEquals(OrderOpenStatusEnum.OPEN,l_row.getOrderOpenStatus());
            assertEquals("20080717",l_row.getBizDate());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public class LoginInfoImplTest extends LoginInfoImpl
    {
        public long getLoginId()
        {
            // TODO Auto-generated method stub
            return 1001L;
        }
    }

    public void testGetMutualFundOrderUnitTableRecord_case0001()
    {
        String STR_METHOD_NAME = " testGetMutualFundOrderUnitTableRecord_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl =
            new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();

        MutualFundOrderUnitParams l_mutualFundOrderUnitParams =
            TestDBUtility.getMutualFundOrderUnitRow();

        try
        {
            TestDBUtility.deleteAll(MutualFundOrderUnitParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            long l_lngOrderUnitId = l_mutualFundOrderUnitParams.getOrderUnitId();

            Method method =
                WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.class.getDeclaredMethod(
                    "getMutualFundOrderUnitTableRecord",
                    new Class[]{long.class});
            
            method.setAccessible(true);
            method.invoke(l_impl, new Object[]{new Long(l_lngOrderUnitId)});

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (NoSuchMethodException l_exNSME)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exNSME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (InvocationTargetException l_exITE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exITE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3BusinessLayerException.class,
                l_exITE.getTargetException().getClass());
            WEB3BusinessLayerException l_targetException =
                (WEB3BusinessLayerException)l_exITE.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                l_targetException.getErrorInfo());
        }
        catch (IllegalAccessException l_exIAE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exIAE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MutualFundOrderUnitParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }

    public void testGetMutualFundOrderUnitTableRecord_case0002()
    {
        String STR_METHOD_NAME = " testGetMutualFundOrderUnitTableRecord_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl =
            new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();

        MutualFundOrderUnitParams l_mutualFundOrderUnitParams =
            TestDBUtility.getMutualFundOrderUnitRow();

        try
        {
            TestDBUtility.deleteAll(MutualFundOrderUnitParams.TYPE);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            long l_lngOrderUnitId = l_mutualFundOrderUnitParams.getOrderUnitId();

            Method method =
                WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.class.getDeclaredMethod(
                    "getMutualFundOrderUnitTableRecord",
                    new Class[]{long.class});
            
            method.setAccessible(true);
            method.invoke(l_impl, new Object[]{new Long(l_lngOrderUnitId)});

            List l_lisActualResults = new ArrayList();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisActualResults = l_queryProcessor.doFindAllQuery(MutualFundOrderUnitParams.TYPE);

            assertEquals(1, l_lisActualResults.size());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {

            try
            {
                TestDBUtility.deleteAll(MutualFundOrderUnitParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }

    public void testGetSearchResultScreen_case0001()
    {
        String STR_METHOD_NAME =
            " testGetSearchResultScreen_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl =
            new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();

        AdminPermissionParams l_adminPermissionParams =
            TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode(
            l_administratorParams.getInstitutionCode());
        l_adminPermissionParams.setPermissionLevel(
            l_administratorParams.getPermissionLevel());
        l_adminPermissionParams.setTransactionCategory(
            WEB3TransactionCategoryDef.ORDER_UNIT_UPDATE);
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);

        AdministratorTypeParams l_administratorTypeParams =
            TestDBUtility.getAdministratorTypeRow();
        l_administratorTypeParams.setInstitutionCode(
            l_administratorParams.getInstitutionCode());
        l_administratorTypeParams.setPermissionLevel(
            l_administratorParams.getPermissionLevel());
        l_administratorTypeParams.setDirAdminFlag(
            Integer.parseInt(WEB3DirAdminFlagDef.DIR_ADMINISTRATOR));

        MutualFundOrderUnitParams l_mutualFundOrderUnitParams =
            TestDBUtility.getMutualFundOrderUnitRow();
        
        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.deleteAll(MutualFundOrderUnitParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            WEB3AdminDirSecAioOrderUnitTableSearchResultRequest l_request =
                new WEB3AdminDirSecAioOrderUnitTableSearchResultRequest();
            l_request.orderUnitId = l_mutualFundOrderUnitParams.getOrderUnitId() + "";
            l_request.orderUnitTblKbn = "2";

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            WEB3AdminDirSecAioOrderUnitTableSearchResultResponse l_response =
                l_impl.getSearchResultScreen(l_request);

            assertEquals(l_mutualFundOrderUnitParams.getAccountId() + "",
                l_response.accountId);
            assertEquals(l_mutualFundOrderUnitParams.getBranchId() + "",
                l_response.branchId);
            assertEquals(l_mutualFundOrderUnitParams.getOrderId() + "",
                l_response.orderId);
            assertEquals(
                l_mutualFundOrderUnitParams.getProductType().intValue() + "",
                l_response.productType);
            assertEquals("1",
                l_response.orderStatus);
            assertEquals(
                l_mutualFundOrderUnitParams.getOrderOpenStatus().intValue() + "",
                l_response.orderOpenStatus);
            assertEquals(l_mutualFundOrderUnitParams.getOrderRequestNumber(),
                l_response.requestNumber);
            assertEquals(l_mutualFundOrderUnitParams.getExecStatus(),
                l_response.execStatus);

            log.exiting(TEST_END + STR_METHOD_NAME);

//            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {

            try
            {
                TestDBUtility.deleteAll(MutualFundOrderUnitParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }
    /*
     * リクエストデータ.注文単位テーブル区分 == 3(株式)の場合、
     */
    public void testGetSearchResultScreen_case0002()
    {
        String STR_METHOD_NAME =
            " testGetSearchResultScreen_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl =
            new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();

        AdminPermissionParams l_adminPermissionParams =
            TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode(
            l_administratorParams.getInstitutionCode());
        l_adminPermissionParams.setPermissionLevel(
            l_administratorParams.getPermissionLevel());
        l_adminPermissionParams.setTransactionCategory(
            WEB3TransactionCategoryDef.ORDER_UNIT_UPDATE);
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);

        AdministratorTypeParams l_administratorTypeParams =
            TestDBUtility.getAdministratorTypeRow();
        l_administratorTypeParams.setInstitutionCode(
            l_administratorParams.getInstitutionCode());
        l_administratorTypeParams.setPermissionLevel(
            l_administratorParams.getPermissionLevel());
        l_administratorTypeParams.setDirAdminFlag(
            Integer.parseInt(WEB3DirAdminFlagDef.DIR_ADMINISTRATOR));

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
            TestDBUtility.getEqtypeOrderUnitRow();
        
        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);

            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            WEB3AdminDirSecAioOrderUnitTableSearchResultRequest l_request =
                new WEB3AdminDirSecAioOrderUnitTableSearchResultRequest();
            l_request.orderUnitId = l_eqtypeOrderUnitParams.getOrderUnitId() + "";
            l_request.orderUnitTblKbn = "3";

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            WEB3AdminDirSecAioOrderUnitTableSearchResultResponse l_response =
                l_impl.getSearchResultScreen(l_request);

            assertEquals(l_eqtypeOrderUnitParams.getAccountId() + "",
                l_response.accountId);
            assertEquals(l_eqtypeOrderUnitParams.getBranchId() + "",
                l_response.branchId);
            assertEquals(l_eqtypeOrderUnitParams.getOrderId() + "",
                l_response.orderId);
            assertEquals(
                    l_eqtypeOrderUnitParams.getProductType().intValue() + "",
                l_response.productType);
            assertEquals("1",
                l_response.orderStatus);
            assertEquals(
                    l_eqtypeOrderUnitParams.getOrderOpenStatus().intValue() + "",
                l_response.orderOpenStatus);
            assertEquals(l_eqtypeOrderUnitParams.getOrderRequestNumber(),
                l_response.requestNumber);
            assertNull(l_response.execStatus);

            log.exiting(TEST_END + STR_METHOD_NAME);

        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {

            try
            {
                TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }
    /*
     * リクエストデータ.注文単位テーブル区分 == 4(先物OP)の場合、
     */
    public void testGetSearchResultScreen_case0003()
    {
        String STR_METHOD_NAME =
            " testGetSearchResultScreen_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl =
            new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();

        AdminPermissionParams l_adminPermissionParams =
            TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode(
            l_administratorParams.getInstitutionCode());
        l_adminPermissionParams.setPermissionLevel(
            l_administratorParams.getPermissionLevel());
        l_adminPermissionParams.setTransactionCategory(
            WEB3TransactionCategoryDef.ORDER_UNIT_UPDATE);
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);

        AdministratorTypeParams l_administratorTypeParams =
            TestDBUtility.getAdministratorTypeRow();
        l_administratorTypeParams.setInstitutionCode(
            l_administratorParams.getInstitutionCode());
        l_administratorTypeParams.setPermissionLevel(
            l_administratorParams.getPermissionLevel());
        l_administratorTypeParams.setDirAdminFlag(
            Integer.parseInt(WEB3DirAdminFlagDef.DIR_ADMINISTRATOR));

        IfoOrderUnitParams l_ifoOrderUnitParams =
            TestDBUtility.getIfoOrderUnitRow();
        
        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            WEB3AdminDirSecAioOrderUnitTableSearchResultRequest l_request =
                new WEB3AdminDirSecAioOrderUnitTableSearchResultRequest();
            l_request.orderUnitId = l_ifoOrderUnitParams.getOrderUnitId() + "";
            l_request.orderUnitTblKbn = "4";

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            WEB3AdminDirSecAioOrderUnitTableSearchResultResponse l_response =
                l_impl.getSearchResultScreen(l_request);

            assertEquals(l_ifoOrderUnitParams.getAccountId() + "",
                l_response.accountId);
            assertEquals(l_ifoOrderUnitParams.getBranchId() + "",
                l_response.branchId);
            assertEquals(l_ifoOrderUnitParams.getOrderId() + "",
                l_response.orderId);
            assertEquals(
                    l_ifoOrderUnitParams.getProductType().intValue() + "",
                l_response.productType);
            assertEquals("7",
                l_response.orderStatus);
            assertEquals(
                    l_ifoOrderUnitParams.getOrderOpenStatus().intValue() + "",
                l_response.orderOpenStatus);
            assertEquals(l_ifoOrderUnitParams.getOrderRequestNumber(),
                l_response.requestNumber);
            assertNull(l_response.execStatus);

            log.exiting(TEST_END + STR_METHOD_NAME);

        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {

            try
            {
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }
    /*
     * 株式注文単位Row = null。
     */
    public void testGetEqtypeOrderUnitTableRecord_C0001()
    {
        String STR_METHOD_NAME =
            " testGetEqtypeOrderUnitTableRecord_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl =
            new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            long l_lngOrderUnitId = 1002;

            Method method =
                WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.class.getDeclaredMethod(
                    "getEqtypeOrderUnitTableRecord",
                    new Class[]{long.class});

            method.setAccessible(true);
            method.invoke(l_impl, new Object[]{new Long(l_lngOrderUnitId)});

            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3BusinessLayerException.class, l_ex.getTargetException().getClass());
            WEB3BusinessLayerException l_targetException = (WEB3BusinessLayerException)l_ex.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_targetException.getErrorInfo());
        }
        catch (IllegalAccessException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    /*
     * normal case
     */
    public void testGetEqtypeOrderUnitTableRecord_C0002()
    {
        String STR_METHOD_NAME =
            " testGetEqtypeOrderUnitTableRecord_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl =
            new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            long l_lngOrderUnitId = l_eqtypeOrderUnitParams.getOrderUnitId();

            Method method =
                WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.class.getDeclaredMethod(
                    "getEqtypeOrderUnitTableRecord",
                    new Class[]{long.class});
            
            method.setAccessible(true);
            method.invoke(l_impl, new Object[]{new Long(l_lngOrderUnitId)});
            
            List l_lisActualResults = new ArrayList();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisActualResults = l_queryProcessor.doFindAllQuery(EqtypeOrderUnitParams.TYPE);

            assertEquals(1, l_lisActualResults.size());

        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    /*
     * 更新_注文状態 = nullの場合、
     */
    public void testUpdateEqtypeOrderUnit_C0001()
    {
        final String STR_METHOD_NAME = " testUpdateMutualFundOrderUnit_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        Method method = null;
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(1001L);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl =
                new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();

            method = WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.class.getDeclaredMethod(
                "updateEqtypeOrderUnit",
                new Class[]{long.class, String.class, String.class, String.class});

            method.setAccessible(true);

            Object[] l_obj = {new Long(1001L), null, "2", "20080717"};
            
            method.invoke(l_impl, l_obj);

            EqtypeOrderUnitRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                " order_unit_id = ? ";
            Object[] l_objWhere = {"1001"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_strWhere,
                    l_objWhere);

            l_row = (EqtypeOrderUnitRow)l_lstRecords.get(0);

            assertEquals(OrderOpenStatusEnum.CLOSED,l_row.getOrderOpenStatus());
            assertEquals("20080717",l_row.getBizDate());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    /*
     * 更新_注文有効状態 = nullの場合 && 更新_注文有効状態 == nullの場合、
     */
    public void testUpdateEqtypeOrderUnit_C0002()
    {
        final String STR_METHOD_NAME = " testUpdateEqtypeOrderUnit_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        Method method = null;
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(1001L);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl =
                new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();

            method = WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.class.getDeclaredMethod(
                "updateEqtypeOrderUnit",
                new Class[]{long.class, String.class, String.class, String.class});
            
            method.setAccessible(true);

            Object[] l_obj = {new Long(1001L), "1", null, null};
            
            method.invoke(l_impl, l_obj);
            
            EqtypeOrderUnitRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                " order_unit_id = ? ";
            Object[] l_objWhere = {"1001"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_strWhere,
                    l_objWhere);

            l_row = (EqtypeOrderUnitRow)l_lstRecords.get(0);

            assertEquals(OrderStatusEnum.ACCEPTED,l_row.getOrderStatus());

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    /*
     * DBへのアクセスに失敗しました。
     */
    public void testUpdateEqtypeOrderUnit_C0003()
    {
        final String STR_METHOD_NAME = " testUpdateEqtypeOrderUnit_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        Method method = null;
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(1001L);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl =
                new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();

            method = WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.class.getDeclaredMethod(
                "updateEqtypeOrderUnit",
                new Class[]{long.class, String.class, String.class, String.class});
            
            method.setAccessible(true);

            Object[] l_obj = {new Long(1001L), null, null, null};
            
            method.invoke(l_impl, l_obj);
            
            fail();

        }
        catch(InvocationTargetException l_ex)
        {
            WEB3SystemLayerException l_exception = (WEB3SystemLayerException) l_ex.getTargetException();
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_exception.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    /*
     * normal case
     */
    public void testUpdateEqtypeOrderUnit_C0004()
    {
        final String STR_METHOD_NAME = " testUpdateEqtypeOrderUnit_C0004";
        log.entering(TEST_START + STR_METHOD_NAME);
        Method method = null;
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(1001L);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl =
                new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();

            method = WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.class.getDeclaredMethod(
                "updateEqtypeOrderUnit",
                new Class[]{long.class, String.class, String.class, String.class});
            
            method.setAccessible(true);

            Object[] l_obj = {new Long(1001L), "1", "2", "20080717"};
            
            method.invoke(l_impl, l_obj);

            EqtypeOrderUnitRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                " order_unit_id = ? ";
            Object[] l_objWhere = {"1001"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_strWhere,
                    l_objWhere);

            l_row = (EqtypeOrderUnitRow)l_lstRecords.get(0);

            assertEquals(OrderStatusEnum.ACCEPTED,l_row.getOrderStatus());
            assertEquals(OrderOpenStatusEnum.CLOSED,l_row.getOrderOpenStatus());
            assertEquals("20080717",l_row.getBizDate());

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    //  ※検索結果が0件の場合、エラーを返却する。
    //エラーメッセージ「条件に該当するデータが存在しない。」
    public void testGetIfoOrderUnitTableRecord_C0001()
    {
        final String STR_METHOD_NAME = "testGetIfoOrderUnitTableRecord_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl = new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_orderUnitParams.setOrderUnitId(1111L);
            TestDBUtility.insertWithDel(l_orderUnitParams);
            
            Long l_lngOrderUnitId = new Long(1112);
            Method l_method =
                WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.class.getDeclaredMethod(
                    "getIfoOrderUnitTableRecord", 
                    new Class[]{long.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{l_lngOrderUnitId});
            fail();
        } 
        catch (InvocationTargetException l_ex)
        {
             WEB3BusinessLayerException l_exception = (WEB3BusinessLayerException) l_ex.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, 
                    l_exception.getErrorInfo());
        } catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //correctcase
    public void testGetIfoOrderUnitTableRecord_C0002()
    {
        final String STR_METHOD_NAME = "testGetIfoOrderUnitTableRecord_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl = new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_orderUnitParams.setOrderUnitId(1111);
            TestDBUtility.insertWithDel(l_orderUnitParams);
            
            Long l_lngOrderUnitId = new Long(1111);
            Method l_method =
                WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.class.getDeclaredMethod(
                    "getIfoOrderUnitTableRecord", 
                    new Class[]{long.class});
            l_method.setAccessible(true);
            IfoOrderUnitRow l_ifoOderUnits = (IfoOrderUnitRow)l_method.invoke(l_impl, new Object[]{l_lngOrderUnitId});
            assertEquals(1111, l_ifoOderUnits.getOrderUnitId());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //引数）更新_注文状態 == nullの場合、
    //注文状態：更新不要（既存値）
    public void testUpdateIfoOrderUnit_C0001()
    {
        final String STR_METHOD_NAME = "testUpdateIfoOrderUnit_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Long l_lngOrderUnitId =  new Long(1111);
            String l_strUpdateOrderStatus = null;
            String l_strOrderOpenStatus = "1";
            String l_strUpdateOrderBizDate = "2";
            
            WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl = new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_orderUnitParams.setOrderUnitId(1111);
            l_orderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            TestDBUtility.insertWithDel(l_orderUnitParams);
            
            Method l_method =
                WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.class.getDeclaredMethod(
                    "updateIfoOrderUnit", 
                    new Class[]{long.class, String.class, String.class, String.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{
                l_lngOrderUnitId, l_strUpdateOrderStatus, l_strOrderOpenStatus, l_strUpdateOrderBizDate});
            
            IfoOrderUnitRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "order_unit_id = ?";
            Object[] l_objWhere = {"1111"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    IfoOrderUnitRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            l_row = (IfoOrderUnitRow)l_lstRecords.get(0);
            
            log.debug("沒有更新後Status =========" + l_row.getOrderStatus());
            assertEquals(OrderStatusEnum.MODIFY_ACCEPTED, l_row.getOrderStatus());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //（引数）更新_注文有効状態 == nullの場合、
    //注文有効状態：更新不要（既存値）
    public void testUpdateIfoOrderUnit_C0002()
    {
        final String STR_METHOD_NAME = "testUpdateIfoOrderUnit_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Long l_lngOrderUnitId =  new Long(1111);
            String l_strUpdateOrderStatus = "0";
            String l_strOrderOpenStatus = null;
            String l_strUpdateOrderBizDate = "2";
            
            WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl = new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_orderUnitParams.setOrderUnitId(1111);
            l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(l_orderUnitParams);
            
            Method l_method =
                WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.class.getDeclaredMethod(
                    "updateIfoOrderUnit", 
                    new Class[]{long.class, String.class, String.class, String.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{
                l_lngOrderUnitId, l_strUpdateOrderStatus, l_strOrderOpenStatus, l_strUpdateOrderBizDate});
            
            IfoOrderUnitRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "order_unit_id = ?";
            Object[] l_objWhere = {"1111"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    IfoOrderUnitRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            l_row = (IfoOrderUnitRow)l_lstRecords.get(0);
            
            log.debug("沒有更新後OpenStatus =========" + l_row.getOrderOpenStatus());
            assertEquals(OrderOpenStatusEnum.OPEN, l_row.getOrderOpenStatus());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // （引数）更新_発注日 == nullの場合、
    //発注日：更新不要（既存値）
    public void testUpdateIfoOrderUnit_C0003()
    {
        final String STR_METHOD_NAME = "testUpdateIfoOrderUnit_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Long l_lngOrderUnitId =  new Long(1111);
            String l_strUpdateOrderStatus = "0";
            String l_strOrderOpenStatus = "1";
            String l_strUpdateOrderBizDate = null;
            
            WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl = new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_orderUnitParams.setOrderUnitId(1111);
            l_orderUnitParams.setBizDate("20040101");
            TestDBUtility.insertWithDel(l_orderUnitParams);
            
            Method l_method =
                WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.class.getDeclaredMethod(
                    "updateIfoOrderUnit", 
                    new Class[]{long.class, String.class, String.class, String.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{
                l_lngOrderUnitId, l_strUpdateOrderStatus, l_strOrderOpenStatus, l_strUpdateOrderBizDate});
            
            IfoOrderUnitRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "order_unit_id = ?";
            Object[] l_objWhere = {"1111"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    IfoOrderUnitRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            l_row = (IfoOrderUnitRow)l_lstRecords.get(0);
            
            log.debug("沒有更新後BizDate =========" + l_row.getBizDate());
            assertEquals("20040101", l_row.getBizDate());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //DataQueryException
    public void testUpdateIfoOrderUnit_C0004()
    {
        final String STR_METHOD_NAME = "testUpdateIfoOrderUnit_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Long l_lngOrderUnitId =  new Long(1111);
            String l_strUpdateOrderStatus = null;
            String l_strOrderOpenStatus = null;
            String l_strUpdateOrderBizDate = null;
            
            WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl = new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_orderUnitParams.setOrderUnitId(1111);
            l_orderUnitParams.setBizDate("20040101");
            TestDBUtility.insertWithDel(l_orderUnitParams);
            
            Method l_method =
                WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.class.getDeclaredMethod(
                    "updateIfoOrderUnit", 
                    new Class[]{long.class, String.class, String.class, String.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{
                l_lngOrderUnitId, l_strUpdateOrderStatus, l_strOrderOpenStatus, l_strUpdateOrderBizDate});
            fail();
        }
        catch(InvocationTargetException l_ex)
        {
            WEB3SystemLayerException l_exception = (WEB3SystemLayerException) l_ex.getTargetException();
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    l_exception.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //correctcase
    public void testUpdateIfoOrderUnit_C0005()
    {
        final String STR_METHOD_NAME = "testUpdateIfoOrderUnit_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Long l_lngOrderUnitId =  new Long(1111);
            String l_strUpdateOrderStatus = "0";
            String l_strOrderOpenStatus = "1";
            String l_strUpdateOrderBizDate = "20080717";
            
            WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl = new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_orderUnitParams.setOrderUnitId(1111);
            TestDBUtility.insertWithDel(l_orderUnitParams);
            
            Method l_method =
                WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.class.getDeclaredMethod(
                    "updateIfoOrderUnit", 
                    new Class[]{long.class, String.class, String.class, String.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{
                l_lngOrderUnitId, l_strUpdateOrderStatus, l_strOrderOpenStatus, l_strUpdateOrderBizDate});
            
            IfoOrderUnitRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "order_unit_id = ?";
            Object[] l_objWhere = {"1111"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    IfoOrderUnitRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            l_row = (IfoOrderUnitRow)l_lstRecords.get(0);

            assertEquals(OrderStatusEnum.UNDEFINED,l_row.getOrderStatus());
            assertEquals(OrderOpenStatusEnum.OPEN,l_row.getOrderOpenStatus());
            assertEquals("20080717", l_row.getBizDate());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * WEB3DateUtility.getDate(更新_発注日, "yyyyMMdd") == null の場合、
     */
    public void testValidateUpdateOrderBizDate_C0001()
    {
        final String STR_METHOD_NAME = " testValidateUpdateOrderBizDate_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        Method method = null;
        try
        {

            WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl =
                new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();

            method = WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.class.getDeclaredMethod(
                "validateUpdateOrderBizDate",
                new Class[]{String.class});
            
            method.setAccessible(true);

            Object[] l_obj = {"aaaabbcc"};
            
            method.invoke(l_impl, l_obj);
            
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3BusinessLayerException.class, l_ex.getTargetException().getClass());
            WEB3BusinessLayerException l_targetException = (WEB3BusinessLayerException)l_ex.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03109, l_targetException.getErrorInfo());
        }
        catch (IllegalAccessException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    /*
     * 更新_発注日が営業日以外の日付であった場合、
     */
    public void testValidateUpdateOrderBizDate_C0002()
    {
        final String STR_METHOD_NAME = " testValidateUpdateOrderBizDate_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        Method method = null;
        try
        {

            WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl =
                new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();


            method = WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.class.getDeclaredMethod(
                "validateUpdateOrderBizDate",
                new Class[]{String.class});
            
            method.setAccessible(true);

            Object[] l_obj = {"20080713"};

            method.invoke(l_impl, l_obj);
            
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3BusinessLayerException.class, l_ex.getTargetException().getClass());
            WEB3BusinessLayerException l_targetException = (WEB3BusinessLayerException)l_ex.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02019, l_targetException.getErrorInfo());
        }
        catch (IllegalAccessException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    /*
     * normal case
     */
    public void testValidateUpdateOrderBizDate_C0003()
    {
        final String STR_METHOD_NAME = " testValidateUpdateOrderBizDate_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        Method method = null;
        try
        {

            WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl l_impl =
                new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl();


            method = WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.class.getDeclaredMethod(
                "validateUpdateOrderBizDate",
                new Class[]{String.class});
            
            method.setAccessible(true);

            Object[] l_obj = {"20080717"};

            method.invoke(l_impl, l_obj);

            assertTrue(true);
        }
        catch (IllegalAccessException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
}
@
