head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.03.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFrontOrderRouteChangeServiceImplTestLmz.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者発注経路切替サービス実装クラステスト(WEB3AdminFrontOrderRouteChangeServiceImplTestLmz.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/03/5  李木子
*/
package webbroker3.dirsec.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.message.WEB3AdminFrontProcessNumberInfoUnit;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeCommonRequest;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeSelectRequest;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecFrontOrderCommonService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.gentrade.data.VirtualServerChangeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.mock.util.WEB3MockObjectRuntimeException;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFrontOrderRouteChangeServiceImplTestLmz extends TestBaseForMock {

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminFrontOrderRouteChangeServiceImplTestLmz.class);

    public WEB3AdminFrontOrderRouteChangeServiceImplTestLmz(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testGetSwitchPointDataCount_0001()
    {
        final String STR_METHOD_NAME = " testGetSwitchPointDataCount_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        VirtualServerChangeParams l_virtualServerChangeRow = new VirtualServerChangeParams();

        l_virtualServerChangeRow.setVirtualServerNumberMarket("12345");
        l_virtualServerChangeRow.setChangeReqResDiv("02");
        l_virtualServerChangeRow.setNoticeType("00");
        l_virtualServerChangeRow.setInstitutionCode("381");
        l_virtualServerChangeRow.setFrontOrderExchangeCode("1");
        l_virtualServerChangeRow.setFrontOrderSystemCode("1");
        l_virtualServerChangeRow.setFrontOrderTradeCode("1");
        l_virtualServerChangeRow.setProductType(ProductTypeEnum.EQUITY);
        l_virtualServerChangeRow.setStatus("1");

        try
        {
            TestDBUtility.insertWithDelAndCommit(l_virtualServerChangeRow);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3AdminFrontOrderRouteChangeServiceImpl l_WEB3AdminFrontOrderRouteChangeServiceImpl =
            new WEB3AdminFrontOrderRouteChangeServiceImpl();

        WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit =
            new WEB3AdminFrontProcessNumberInfoUnit();

        l_processInfoUnit.virtualServerQuantity = "3";

        try
        {
            l_WEB3AdminFrontOrderRouteChangeServiceImpl.getSwitchPointDataCount("381", "1", "1", "1", l_processInfoUnit, "0");

            (l_processInfoUnit.finProcessNumber1).equals("1");
            (l_processInfoUnit.finProcessNumber2).equals("0");
            (l_processInfoUnit.finProcessNumber3).equals("0");
            (l_processInfoUnit.finProcessNumber4).equals("0");
            (l_processInfoUnit.finProcessNumber5).equals("0");
            (l_processInfoUnit.nonProcessNumber1).equals("2");
            (l_processInfoUnit.nonProcessNumber2).equals("3");
            (l_processInfoUnit.nonProcessNumber3).equals("3");
            (l_processInfoUnit.nonProcessNumber4).equals("3");
            (l_processInfoUnit.nonProcessNumber5).equals("3");
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testGetSwitchPointDataCount_0002()
    {
        final String STR_METHOD_NAME = " testGetSwitchPointDataCount_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        VirtualServerChangeParams l_virtualServerChangeRow = new VirtualServerChangeParams();

        l_virtualServerChangeRow.setVirtualServerNumberMarket("12345");
        l_virtualServerChangeRow.setChangeReqResDiv("01");
        l_virtualServerChangeRow.setNoticeType("00");
        l_virtualServerChangeRow.setInstitutionCode("381");
        l_virtualServerChangeRow.setFrontOrderExchangeCode("1");
        l_virtualServerChangeRow.setFrontOrderSystemCode("1");
        l_virtualServerChangeRow.setFrontOrderTradeCode("1");
        l_virtualServerChangeRow.setProductType(ProductTypeEnum.IFO);
        l_virtualServerChangeRow.setStatus("1");

        try
        {
            TestDBUtility.insertWithDelAndCommit(l_virtualServerChangeRow);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3AdminFrontOrderRouteChangeServiceImpl l_WEB3AdminFrontOrderRouteChangeServiceImpl =
            new WEB3AdminFrontOrderRouteChangeServiceImpl();

        WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit =
            new WEB3AdminFrontProcessNumberInfoUnit();

        l_processInfoUnit.virtualServerQuantity = "3";

        try
        {
            l_WEB3AdminFrontOrderRouteChangeServiceImpl.getSwitchPointDataCount("381", "1", "1", "6", l_processInfoUnit, "0");

            (l_processInfoUnit.finProcessNumber1).equals("0");
            (l_processInfoUnit.finProcessNumber2).equals("0");
            (l_processInfoUnit.finProcessNumber3).equals("0");
            (l_processInfoUnit.finProcessNumber4).equals("0");
            (l_processInfoUnit.finProcessNumber5).equals("0");
            (l_processInfoUnit.nonProcessNumber1).equals("1");
            (l_processInfoUnit.nonProcessNumber2).equals("0");
            (l_processInfoUnit.nonProcessNumber3).equals("0");
            (l_processInfoUnit.nonProcessNumber4).equals("0");
            (l_processInfoUnit.nonProcessNumber5).equals("0");
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateSwitchBootDiv_0001()
    {
        final String STR_METHOD_NAME = " testValidateSwitchBootDiv_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminFrontRouteChangeCommonRequestForTest l_request = new WEB3AdminFrontRouteChangeCommonRequestForTest();

        l_request.changeStartDiv = "1";
        l_request.convertMarketCode = "22";
        l_request.marketCode = "38";
        l_request.changeProcessDiv = "1";
        l_request.productType = "1";

        WEB3AdminFrontOrderRouteChangeServiceImpl l_WEB3AdminFrontOrderRouteChangeServiceImpl =
            new WEB3AdminFrontOrderRouteChangeServiceImpl();

        WEB3AdminDirSecFrontOrderCommonService l_WEB3AdminDirSecFrontOrderCommonService =
            new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();

        try
        {
            Method method = WEB3AdminFrontOrderRouteChangeServiceImpl.class.getDeclaredMethod("validateSwitchBootDiv",
                new Class[] {String.class, WEB3AdminFrontRouteChangeCommonRequest.class, WEB3AdminDirSecFrontOrderCommonService.class});
            method.setAccessible(true);
            method.invoke(l_WEB3AdminFrontOrderRouteChangeServiceImpl, new Object[] {"0D", l_request, l_WEB3AdminDirSecFrontOrderCommonService});

            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            WEB3SystemLayerException l_ex1 = (WEB3SystemLayerException)l_ex.getTargetException();

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex1.getErrorInfo());
            assertEquals("getGrayOrder", l_ex1.getErrorMethod());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateSwitchBootDiv_0002()
    {
        final String STR_METHOD_NAME = " testValidateSwitchBootDiv_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminFrontRouteChangeCommonRequestForTest l_request = new WEB3AdminFrontRouteChangeCommonRequestForTest();

        l_request.changeStartDiv = "1";
        l_request.convertMarketCode = "22";
        l_request.marketCode = "38";
        l_request.changeProcessDiv = "1";
        l_request.productType = "6";

        WEB3AdminFrontOrderRouteChangeServiceImpl l_WEB3AdminFrontOrderRouteChangeServiceImpl =
            new WEB3AdminFrontOrderRouteChangeServiceImpl();

        WEB3AdminDirSecFrontOrderCommonService l_WEB3AdminDirSecFrontOrderCommonService =
            new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();

        try
        {
            Method method = WEB3AdminFrontOrderRouteChangeServiceImpl.class.getDeclaredMethod("validateSwitchBootDiv",
                new Class[] {String.class, WEB3AdminFrontRouteChangeCommonRequest.class, WEB3AdminDirSecFrontOrderCommonService.class});
            method.setAccessible(true);
            method.invoke(l_WEB3AdminFrontOrderRouteChangeServiceImpl, new Object[] {"0D", l_request, l_WEB3AdminDirSecFrontOrderCommonService});

            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            WEB3SystemLayerException l_ex1 = (WEB3SystemLayerException)l_ex.getTargetException();

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex1.getErrorInfo());
            assertEquals("getIfoGrayOrder", l_ex1.getErrorMethod());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateSwitchBootDiv_0003()
    {
        final String STR_METHOD_NAME = " testValidateSwitchBootDiv_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminFrontRouteChangeCommonRequestForTest l_request = new WEB3AdminFrontRouteChangeCommonRequestForTest();

        l_request.changeStartDiv = "1";
        l_request.convertMarketCode = "22";
        l_request.marketCode = "381";
        l_request.changeProcessDiv = "1";
        l_request.productType = "1";

        WEB3AdminFrontOrderRouteChangeServiceImpl l_WEB3AdminFrontOrderRouteChangeServiceImpl =
            new WEB3AdminFrontOrderRouteChangeServiceImpl();

        WEB3AdminDirSecFrontOrderCommonService l_WEB3AdminDirSecFrontOrderCommonService =
            new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();

        try
        {
            Method method = WEB3AdminFrontOrderRouteChangeServiceImpl.class.getDeclaredMethod("validateSwitchBootDiv",
                new Class[] {String.class, WEB3AdminFrontRouteChangeCommonRequest.class, WEB3AdminDirSecFrontOrderCommonService.class});
            method.setAccessible(true);
            method.invoke(l_WEB3AdminFrontOrderRouteChangeServiceImpl, new Object[] {"0D", l_request, l_WEB3AdminDirSecFrontOrderCommonService});

            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            WEB3SystemLayerException l_ex1 = (WEB3SystemLayerException)l_ex.getTargetException();

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex1.getErrorInfo());
            assertEquals("getVitualServerCount", l_ex1.getErrorMethod());
            assertEquals("1:0D 2:381 3:2 4:1", l_ex1.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateSwitchBootDiv_0004()
    {
        final String STR_METHOD_NAME = " testValidateSwitchBootDiv_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminFrontRouteChangeCommonRequestForTest l_request = new WEB3AdminFrontRouteChangeCommonRequestForTest();

        l_request.changeStartDiv = "1";
        l_request.convertMarketCode = "22";
        l_request.marketCode = "382";
        l_request.changeProcessDiv = "2";
        l_request.productType = "2";

        WEB3AdminFrontOrderRouteChangeServiceImpl l_WEB3AdminFrontOrderRouteChangeServiceImpl =
            new WEB3AdminFrontOrderRouteChangeServiceImplForTest();

        WEB3AdminDirSecFrontOrderCommonService l_WEB3AdminDirSecFrontOrderCommonService =
            new WEB3AdminDirSecFrontOrderCommonServiceImplForTest();

        try
        {
            Method method = WEB3AdminFrontOrderRouteChangeServiceImpl.class.getDeclaredMethod("validateSwitchBootDiv",
                new Class[] {String.class, WEB3AdminFrontRouteChangeCommonRequest.class, WEB3AdminDirSecFrontOrderCommonService.class});
            method.setAccessible(true);
            method.invoke(l_WEB3AdminFrontOrderRouteChangeServiceImpl, new Object[] {"0D", l_request, l_WEB3AdminDirSecFrontOrderCommonService});

            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            WEB3SystemLayerException l_ex1 = (WEB3SystemLayerException)l_ex.getTargetException();

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex1.getErrorInfo());
            assertEquals("getSwitchPointDataCount", l_ex1.getErrorMethod());
            assertEquals("1:0D 2:382 3:2 4:2", l_ex1.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testGetSelectScreen_0001()
    {
        final String STR_METHOD_NAME = " testGetSelectScreen_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        WEB3AdminFrontRouteChangeSelectRequest l_request = new WEB3AdminFrontRouteChangeSelectRequest();

        l_request.changeProcessDiv = "1";
        l_request.institutionCode = "0D";

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[]{},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[]{},
            new Long(33381330003L));

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImpl",
            "getGrayOrder",
            new Class[]{String.class, String.class, String.class, WEB3AdminFrontProcessNumberInfoUnit.class},
            new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, "getGrayOrder"));

        AdminPermissionParams l_adminPermissionRows = new AdminPermissionParams();

        l_adminPermissionRows.setInstitutionCode("0D");
        l_adminPermissionRows.setPermissionLevel("111");
        l_adminPermissionRows.setTransactionCategory("Z0201");
        l_adminPermissionRows.setUpdatePermissionFlag(BooleanEnum.TRUE);
        l_adminPermissionRows.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_adminPermissionRows.setUpdateTimestamp(Calendar.getInstance().getTime());

        AdministratorTypeParams l_administratorTypeRows = new AdministratorTypeParams();

        l_administratorTypeRows.setInstitutionCode("0D");
        l_administratorTypeRows.setPermissionLevel("111");
        l_administratorTypeRows.setDirAdminFlag(1);
        l_administratorTypeRows.setAllBranchPermissionFlag(BooleanEnum.TRUE);
        l_administratorTypeRows.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_administratorTypeRows.setUpdateTimestamp(Calendar.getInstance().getTime());

        OrderSwitchingParams l_orderSwitchingRow = TestDBUtility.getOrderSwitchingRow();

        l_orderSwitchingRow.setInstitutionCode("0D");
        l_orderSwitchingRow.setSubmitOrderRouteDiv("3");
        l_orderSwitchingRow.setMarketCode("33");
        l_orderSwitchingRow.setProductType(ProductTypeEnum.EQUITY);
        l_orderSwitchingRow.setFrontOrderSystemCode("2");

        try
        {
            TestDBUtility.insertWithDelAndCommit(l_adminPermissionRows);
            TestDBUtility.insertWithDelAndCommit(l_administratorTypeRows);
            TestDBUtility.insertWithDelAndCommit(l_orderSwitchingRow);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            AdministratorParams l_administratorRows = TestDBUtility.getAdministratorRow();

            l_administratorRows.setInstitutionCode("0D");
            l_administratorRows.setPermissionLevel("111");

            WEB3Administrator l_WEB3Administrator = new WEB3Administrator(l_administratorRows);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_WEB3Administrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_WEB3Administrator, "Z0201", true, true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3AdminFrontOrderRouteChangeServiceImpl l_WEB3AdminFrontOrderRouteChangeServiceImpl =
                new WEB3AdminFrontOrderRouteChangeServiceImpl();

            l_WEB3AdminFrontOrderRouteChangeServiceImpl.getSelectScreen(l_request);
            fail();
        }
        catch (WEB3MockObjectRuntimeException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            WEB3SystemLayerException l_ex1 = (WEB3SystemLayerException)l_ex.getCause();
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex1.getErrorInfo());
            assertEquals("getGrayOrder", l_ex1.getErrorMethod());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testGetSelectScreen_0002()
    {
        final String STR_METHOD_NAME = "testGetSelectScreen_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminFrontRouteChangeSelectRequest l_request = new WEB3AdminFrontRouteChangeSelectRequest();

        l_request.changeProcessDiv = "1";
        l_request.institutionCode = "0D";

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[]{},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[]{},
            new Long(33381330003L));

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImpl",
            "getIfoGrayOrder",
            new Class[]{String.class, String.class, String.class, WEB3AdminFrontProcessNumberInfoUnit.class},
            new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, "getIfoGrayOrder"));

        AdminPermissionParams l_adminPermissionRows = new AdminPermissionParams();

        l_adminPermissionRows.setInstitutionCode("0D");
        l_adminPermissionRows.setPermissionLevel("111");
        l_adminPermissionRows.setTransactionCategory("Z0201");
        l_adminPermissionRows.setUpdatePermissionFlag(BooleanEnum.TRUE);
        l_adminPermissionRows.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_adminPermissionRows.setUpdateTimestamp(Calendar.getInstance().getTime());

        AdministratorTypeParams l_administratorTypeRows = new AdministratorTypeParams();

        l_administratorTypeRows.setInstitutionCode("0D");
        l_administratorTypeRows.setPermissionLevel("111");
        l_administratorTypeRows.setDirAdminFlag(1);
        l_administratorTypeRows.setAllBranchPermissionFlag(BooleanEnum.TRUE);
        l_administratorTypeRows.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_administratorTypeRows.setUpdateTimestamp(Calendar.getInstance().getTime());

        OrderSwitchingParams l_orderSwitchingRow = TestDBUtility.getOrderSwitchingRow();

        l_orderSwitchingRow.setInstitutionCode("0D");
        l_orderSwitchingRow.setSubmitOrderRouteDiv("3");
        l_orderSwitchingRow.setMarketCode("33");
        l_orderSwitchingRow.setProductType(ProductTypeEnum.IFO);
        l_orderSwitchingRow.setFrontOrderSystemCode("2");

        try
        {
            TestDBUtility.insertWithDelAndCommit(l_adminPermissionRows);
            TestDBUtility.insertWithDelAndCommit(l_administratorTypeRows);
            TestDBUtility.insertWithDelAndCommit(l_orderSwitchingRow);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            AdministratorParams l_administratorRows = TestDBUtility.getAdministratorRow();

            l_administratorRows.setInstitutionCode("0D");
            l_administratorRows.setPermissionLevel("111");

            WEB3Administrator l_WEB3Administrator = new WEB3Administrator(l_administratorRows);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_WEB3Administrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_WEB3Administrator, "Z0201", true, true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3AdminFrontOrderRouteChangeServiceImpl l_WEB3AdminFrontOrderRouteChangeServiceImpl =
                new WEB3AdminFrontOrderRouteChangeServiceImpl();

            l_WEB3AdminFrontOrderRouteChangeServiceImpl.getSelectScreen(l_request);
            fail();
        }
        catch (WEB3MockObjectRuntimeException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            WEB3SystemLayerException l_ex1 = (WEB3SystemLayerException)l_ex.getCause();
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex1.getErrorInfo());
            assertEquals("getIfoGrayOrder", l_ex1.getErrorMethod());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testGetSelectScreen_0003()
    {
        final String STR_METHOD_NAME = "testGetSelectScreen_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminFrontRouteChangeSelectRequest l_request = new WEB3AdminFrontRouteChangeSelectRequest();

        l_request.changeProcessDiv = "1";
        l_request.institutionCode = "0D";

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[]{},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[]{},
            new Long(33381330003L));

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImpl",
            "getNowOrderRoute",
            new Class[]{String.class, String.class, String.class, String.class, WEB3AdminFrontProcessNumberInfoUnit.class},
            new WEB3BaseException(new ErrorInfo(), "getNowOrderRoute"));

        AdminPermissionParams l_adminPermissionRows = new AdminPermissionParams();

        l_adminPermissionRows.setInstitutionCode("0D");
        l_adminPermissionRows.setPermissionLevel("111");
        l_adminPermissionRows.setTransactionCategory("Z0201");
        l_adminPermissionRows.setUpdatePermissionFlag(BooleanEnum.TRUE);
        l_adminPermissionRows.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_adminPermissionRows.setUpdateTimestamp(Calendar.getInstance().getTime());

        AdministratorTypeParams l_administratorTypeRows = new AdministratorTypeParams();

        l_administratorTypeRows.setInstitutionCode("0D");
        l_administratorTypeRows.setPermissionLevel("111");
        l_administratorTypeRows.setDirAdminFlag(1);
        l_administratorTypeRows.setAllBranchPermissionFlag(BooleanEnum.TRUE);
        l_administratorTypeRows.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_administratorTypeRows.setUpdateTimestamp(Calendar.getInstance().getTime());

        OrderSwitchingParams l_orderSwitchingRow = TestDBUtility.getOrderSwitchingRow();

        l_orderSwitchingRow.setInstitutionCode("0D");
        l_orderSwitchingRow.setSubmitOrderRouteDiv("3");
        l_orderSwitchingRow.setMarketCode("33");
        l_orderSwitchingRow.setProductType(ProductTypeEnum.IFO);
        l_orderSwitchingRow.setFrontOrderSystemCode("2");

        try
        {
            TestDBUtility.insertWithDelAndCommit(l_adminPermissionRows);
            TestDBUtility.insertWithDelAndCommit(l_administratorTypeRows);
            TestDBUtility.insertWithDelAndCommit(l_orderSwitchingRow);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            AdministratorParams l_administratorRows = TestDBUtility.getAdministratorRow();

            l_administratorRows.setInstitutionCode("0D");
            l_administratorRows.setPermissionLevel("111");

            WEB3Administrator l_WEB3Administrator = new WEB3Administrator(l_administratorRows);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_WEB3Administrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_WEB3Administrator, "Z0201", true, true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3AdminFrontOrderRouteChangeServiceImpl l_WEB3AdminFrontOrderRouteChangeServiceImpl =
                new WEB3AdminFrontOrderRouteChangeServiceImpl();

            l_WEB3AdminFrontOrderRouteChangeServiceImpl.getSelectScreen(l_request);
            fail();
        }
        catch (WEB3MockObjectRuntimeException l_ex)
        {
            WEB3BaseException l_ex1 = (WEB3BaseException)l_ex.getCause();

            assertEquals("getNowOrderRoute", l_ex1.getErrorMethod());
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImpl",
                "getNowOrderRoute",
                new Class[] {String.class, String.class, String.class, String.class, WEB3AdminFrontProcessNumberInfoUnit.class});

            assertEquals(String.class,l_paramsValue.getFirstCalled()[0].getClass());
            assertEquals(String.class,l_paramsValue.getFirstCalled()[1].getClass());
            assertEquals(String.class,l_paramsValue.getFirstCalled()[2].getClass());
            assertEquals(String.class,l_paramsValue.getFirstCalled()[3].getClass());
            assertEquals(WEB3AdminFrontProcessNumberInfoUnit.class,l_paramsValue.getFirstCalled()[4].getClass());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testGetSelectScreen_0004()
    {
        final String STR_METHOD_NAME = "testGetSelectScreen_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminFrontRouteChangeSelectRequest l_request = new WEB3AdminFrontRouteChangeSelectRequest();

        l_request.changeProcessDiv = "1";
        l_request.institutionCode = "0D";

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[]{},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[]{},
            new Long(33381330003L));

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImpl",
            "getVitualServerCount",
            new Class[]{String.class, String.class, String.class, String.class, WEB3AdminFrontProcessNumberInfoUnit.class},
            new WEB3BaseException(new ErrorInfo(), "getVitualServerCount"));

        AdminPermissionParams l_adminPermissionRows = new AdminPermissionParams();

        l_adminPermissionRows.setInstitutionCode("0D");
        l_adminPermissionRows.setPermissionLevel("111");
        l_adminPermissionRows.setTransactionCategory("Z0201");
        l_adminPermissionRows.setUpdatePermissionFlag(BooleanEnum.TRUE);
        l_adminPermissionRows.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_adminPermissionRows.setUpdateTimestamp(Calendar.getInstance().getTime());

        AdministratorTypeParams l_administratorTypeRows = new AdministratorTypeParams();

        l_administratorTypeRows.setInstitutionCode("0D");
        l_administratorTypeRows.setPermissionLevel("111");
        l_administratorTypeRows.setDirAdminFlag(1);
        l_administratorTypeRows.setAllBranchPermissionFlag(BooleanEnum.TRUE);
        l_administratorTypeRows.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_administratorTypeRows.setUpdateTimestamp(Calendar.getInstance().getTime());

        OrderSwitchingParams l_orderSwitchingRow = TestDBUtility.getOrderSwitchingRow();

        l_orderSwitchingRow.setInstitutionCode("0D");
        l_orderSwitchingRow.setSubmitOrderRouteDiv("3");
        l_orderSwitchingRow.setMarketCode("33");
        l_orderSwitchingRow.setProductType(ProductTypeEnum.IFO);
        l_orderSwitchingRow.setFrontOrderSystemCode("2");

        try
        {
            TestDBUtility.insertWithDelAndCommit(l_adminPermissionRows);
            TestDBUtility.insertWithDelAndCommit(l_administratorTypeRows);
            TestDBUtility.insertWithDelAndCommit(l_orderSwitchingRow);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            AdministratorParams l_administratorRows = TestDBUtility.getAdministratorRow();

            l_administratorRows.setInstitutionCode("0D");
            l_administratorRows.setPermissionLevel("111");

            WEB3Administrator l_WEB3Administrator = new WEB3Administrator(l_administratorRows);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_WEB3Administrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_WEB3Administrator, "Z0201", true, true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3AdminFrontOrderRouteChangeServiceImpl l_WEB3AdminFrontOrderRouteChangeServiceImpl =
                new WEB3AdminFrontOrderRouteChangeServiceImpl();

            l_WEB3AdminFrontOrderRouteChangeServiceImpl.getSelectScreen(l_request);
            fail();
        }
        catch (WEB3MockObjectRuntimeException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            WEB3BaseException l_ex1 = (WEB3BaseException)l_ex.getCause();

            assertEquals("getVitualServerCount", l_ex1.getErrorMethod());

            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImpl",
                "getVitualServerCount",
                new Class[] {String.class, String.class, String.class, String.class, WEB3AdminFrontProcessNumberInfoUnit.class});

            assertEquals(String.class,l_paramsValue.getFirstCalled()[0].getClass());
            assertEquals(String.class,l_paramsValue.getFirstCalled()[1].getClass());
            assertEquals(String.class,l_paramsValue.getFirstCalled()[2].getClass());
            assertEquals(String.class,l_paramsValue.getFirstCalled()[3].getClass());
            assertEquals(WEB3AdminFrontProcessNumberInfoUnit.class,l_paramsValue.getFirstCalled()[4].getClass());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testGetSelectScreen_0005()
    {
        final String STR_METHOD_NAME = "testGetSelectScreen_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminFrontRouteChangeSelectRequest l_request = new WEB3AdminFrontRouteChangeSelectRequest();

        l_request.changeProcessDiv = "1";
        l_request.institutionCode = "0D";

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[]{},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[]{},
            new Long(33381330003L));

        AdminPermissionParams l_adminPermissionRows = new AdminPermissionParams();

        l_adminPermissionRows.setInstitutionCode("0D");
        l_adminPermissionRows.setPermissionLevel("111");
        l_adminPermissionRows.setTransactionCategory("Z0201");
        l_adminPermissionRows.setUpdatePermissionFlag(BooleanEnum.TRUE);
        l_adminPermissionRows.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_adminPermissionRows.setUpdateTimestamp(Calendar.getInstance().getTime());

        AdministratorTypeParams l_administratorTypeRows = new AdministratorTypeParams();

        l_administratorTypeRows.setInstitutionCode("0D");
        l_administratorTypeRows.setPermissionLevel("111");
        l_administratorTypeRows.setDirAdminFlag(1);
        l_administratorTypeRows.setAllBranchPermissionFlag(BooleanEnum.TRUE);
        l_administratorTypeRows.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_administratorTypeRows.setUpdateTimestamp(Calendar.getInstance().getTime());

        OrderSwitchingParams l_orderSwitchingRow = TestDBUtility.getOrderSwitchingRow();

        l_orderSwitchingRow.setInstitutionCode("0D");
        l_orderSwitchingRow.setSubmitOrderRouteDiv("3");
        l_orderSwitchingRow.setMarketCode("33");
        l_orderSwitchingRow.setProductType(ProductTypeEnum.IFO);
        l_orderSwitchingRow.setFrontOrderSystemCode("2");

        try
        {
            TestDBUtility.insertWithDelAndCommit(l_adminPermissionRows);
            TestDBUtility.insertWithDelAndCommit(l_administratorTypeRows);
            TestDBUtility.insertWithDelAndCommit(l_orderSwitchingRow);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            AdministratorParams l_administratorRows = TestDBUtility.getAdministratorRow();

            l_administratorRows.setInstitutionCode("0D");
            l_administratorRows.setPermissionLevel("111");

            WEB3Administrator l_WEB3Administrator = new WEB3Administrator(l_administratorRows);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_WEB3Administrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_WEB3Administrator, "Z0201", true, true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3AdminFrontOrderRouteChangeServiceImplForTest l_WEB3AdminFrontOrderRouteChangeServiceImpl =
                new WEB3AdminFrontOrderRouteChangeServiceImplForTest();

            l_WEB3AdminFrontOrderRouteChangeServiceImpl.getSelectScreen(l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
            assertEquals("getSwitchPointDataCount", l_ex.getErrorMethod());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public class WEB3AdminFrontOrderRouteChangeServiceImplForTest extends WEB3AdminFrontOrderRouteChangeServiceImpl{

        protected boolean getSwitchPointDataCount(String l_institutionCode, String l_marketCode,
                String l_frontSystemCode, String l_productType, WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit,
                String l_changeProcessDiv) throws WEB3SystemLayerException
        {

            if ("382".equals(l_marketCode) || "33".equals(l_marketCode))
            {
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, "getSwitchPointDataCount",
                    "1:" +l_institutionCode + " 2:" + l_marketCode + " 3:" + l_frontSystemCode + " 4:" + l_productType);
            }
            return false;
        }
    }


    public class WEB3AdminDirSecFrontOrderCommonServiceImplForTest extends WEB3AdminDirSecFrontOrderCommonServiceImpl {

        public void getGrayOrder(String l_institutionCode, String l_marketCode, String l_frontSystemCode,
                WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit) throws WEB3SystemLayerException
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, "getGrayOrder");
        }

        public void getIfoGrayOrder(String l_institutionCode,
                String l_marketCode,
                String l_frontSystemCode,
                WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit) throws WEB3SystemLayerException
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, "getIfoGrayOrder");
        }

        public void getVitualServerCount(String l_institutionCode, String l_marketCode, String l_frontSystemCode,String l_productType, WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit) throws WEB3SystemLayerException
        {
            if ("381".equals(l_marketCode))
            {
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, "getVitualServerCount",
                    "1:" +l_institutionCode + " 2:" + l_marketCode + " 3:" + l_frontSystemCode + " 4:" + l_productType);
            }
        }

        public String getNowOrderRoute(String l_institutionCode, String l_marketCode, String l_frontSystemCode, String l_productType, WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit) throws WEB3SystemLayerException
        {
            if ("33".equals(l_marketCode))
            {
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, "getNowOrderRoute",
                    "1:" +l_institutionCode + " 2:" + l_marketCode + " 3:" + l_frontSystemCode + " 4:" + l_productType);
            }

            return "";
        }
    }

    public class WEB3AdminFrontRouteChangeCommonRequestForTest extends WEB3AdminFrontRouteChangeCommonRequest
    {
        public WEB3AdminFrontRouteChangeCommonRequestForTest()
        {
            super();
        }

        public WEB3GenResponse createResponse() {

            return null;
        }
    }
}
@
