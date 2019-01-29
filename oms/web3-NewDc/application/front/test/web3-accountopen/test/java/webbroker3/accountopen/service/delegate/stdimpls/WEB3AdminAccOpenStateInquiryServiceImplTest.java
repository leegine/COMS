head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.07.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAccOpenStateInquiryServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3AdminAccOpenStateInquiryServiceImplTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/19 武波 (中訊) 新規作成
*/
package webbroker3.accountopen.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;

import test.util.TestDBUtility;

import webbroker3.accountopen.data.AccOpenVoucherStatusParams;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.define.WEB3AccountOpenKeyItemDef;
import webbroker3.accountopen.message.WEB3AccOpenApplyInfo;
import webbroker3.accountopen.message.WEB3AccOpenChangeRequest;
import webbroker3.accountopen.message.WEB3AccOpenSortKey;
import webbroker3.accountopen.message.WEB3AccOpenStateUnit;
import webbroker3.accountopen.message.WEB3AccOpenVoucherInfo;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryDetailRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryListRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryListResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeConfirmRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccOpenStateInquiryServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenStateInquiryServiceImplTest.class);
    public WEB3AdminAccOpenStateInquiryServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        TestDBUtility.deleteAll(AdministratorParams.TYPE);
        TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
        TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
        TestDBUtility.deleteAll(AccOpenVoucherStatusParams.TYPE);
        TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
        TestDBUtility.deleteAll(InstitutionParams.TYPE);
        TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        TestDBUtility.deleteAll(AdministratorParams.TYPE);
        TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
        TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
        TestDBUtility.deleteAll(AccOpenVoucherStatusParams.TYPE);
        TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
        TestDBUtility.deleteAll(InstitutionParams.TYPE);
        TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
        super.tearDown();
    }

    public void testCreateQueryContainer_C0001()
    {
        final String STR_METHOD_NAME = "testCreateQueryContainer_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAccOpenStateInquiryListRequest l_request = new WEB3AdminAccOpenStateInquiryListRequest();
            l_request.foreignerFlag = "1";
            l_request.deleteFlag = "1";
            l_request.printFlag = "1";
            l_request.receiveFlag = "1";
            l_request.taxTypeDiv = "0";
            l_request.insiderDiv="0";
            WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();
            String l_strInstitutionCode = "0D";
            String[] l_strCreateQueryContainers = l_impl.createQueryContainer(l_request, l_strInstitutionCode);
            assertEquals(7, l_strCreateQueryContainers.length);
            assertEquals("0", l_strCreateQueryContainers[6]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryContainer_C0003()
    {
        final String STR_METHOD_NAME = "testCreateQueryContainer_C0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAccOpenStateInquiryListRequest l_request = new WEB3AdminAccOpenStateInquiryListRequest();
            l_request.foreignerFlag = "1";
            l_request.deleteFlag = "1";
            l_request.printFlag = "1";
            l_request.receiveFlag = "1";
            l_request.taxTypeDiv = "0";
            l_request.insiderDiv="1";
            WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();
            String l_strInstitutionCode = "0D";
            String[] l_strCreateQueryContainers = l_impl.createQueryContainer(l_request, l_strInstitutionCode);
            assertEquals(7, l_strCreateQueryContainers.length);
            assertEquals("1", l_strCreateQueryContainers[6]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryContainer_C0004()
    {
        final String STR_METHOD_NAME = "testCreateQueryContainer_C0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAccOpenStateInquiryListRequest l_request = new WEB3AdminAccOpenStateInquiryListRequest();
            l_request.foreignerFlag = "1";
            l_request.deleteFlag = "1";
            l_request.printFlag = "1";
            l_request.receiveFlag = "1";
            l_request.taxTypeDiv = "0";
            WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();
            String l_strInstitutionCode = "0D";
            assertEquals(6, l_impl.createQueryContainer(l_request, l_strInstitutionCode).length);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateQueryContainer_C0002()
    {
        final String STR_METHOD_NAME = "testCreateQueryContainer_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAccOpenStateInquiryListRequest l_request = new WEB3AdminAccOpenStateInquiryListRequest();
            l_request.foreignerFlag = "1";
            l_request.deleteFlag = "1";
            l_request.printFlag = "1";
            l_request.receiveFlag = "1";
            l_request.taxTypeDiv = "1";
            WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();
            String l_strInstitutionCode = "0D";
            assertEquals(7, l_impl.createQueryContainer(l_request, l_strInstitutionCode).length);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateQueryString_C0001()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAccOpenStateInquiryListRequest l_request = new WEB3AdminAccOpenStateInquiryListRequest();
            l_request.foreignerFlag = "1";
            l_request.deleteFlag = "1";
            l_request.printFlag = "1";
            l_request.receiveFlag = "1";
            l_request.taxTypeDiv = "0";
            l_request.insiderDiv="0";
            l_request.accountType="0";
            WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();
            assertEquals(
                " institution_code = ?  and account_div = ?  and foreign_flag = ?  and delete_flag = ?" +
                "  and print_flag = ?  and receipt_flag = ?  and special_acc = ?  and insider_flag = ? ",
                l_impl.createQueryString(l_request));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryString_C0003()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_C0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAccOpenStateInquiryListRequest l_request = new WEB3AdminAccOpenStateInquiryListRequest();
            l_request.foreignerFlag = "1";
            l_request.deleteFlag = "1";
            l_request.printFlag = "1";
            l_request.receiveFlag = "1";
            l_request.taxTypeDiv = "0";
            l_request.insiderDiv="0";
            l_request.accountType="1";
            WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();
            assertEquals(
                " institution_code = ?  and account_div = ?  and foreign_flag = ?  and delete_flag = ?" +
                "  and print_flag = ?  and receipt_flag = ?  and special_acc = ?  and insider_voucher_div = ? ",
                l_impl.createQueryString(l_request));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryString_C0004()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_C0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAccOpenStateInquiryListRequest l_request = new WEB3AdminAccOpenStateInquiryListRequest();
            l_request.foreignerFlag = "1";
            l_request.deleteFlag = "1";
            l_request.printFlag = "1";
            l_request.receiveFlag = "1";
            l_request.taxTypeDiv = "0";
            WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();
            assertEquals(
                " institution_code = ? "
                + " and foreign_flag = ? "
                + " and delete_flag = ? "
                + " and print_flag = ? "
                + " and receipt_flag = ? "
                + " and special_acc = ? ",
                l_impl.createQueryString(l_request));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateQueryString_C0002()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAccOpenStateInquiryListRequest l_request = new WEB3AdminAccOpenStateInquiryListRequest();
            l_request.foreignerFlag = "1";
            l_request.deleteFlag = "1";
            l_request.printFlag = "1";
            l_request.receiveFlag = "1";
            l_request.taxTypeDiv = "1";
            WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();
            assertEquals(
                " institution_code = ? "
                + " and foreign_flag = ? "
                + " and delete_flag = ? "
                + " and print_flag = ? "
                + " and receipt_flag = ? "
                + " and special_acc in (?, ?) ",
                l_impl.createQueryString(l_request));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetAccOpenStatusDetail_C0001()
    {
        final String STR_METHOD_NAME = "testGetAccOpenStatusDetail_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                new LoginInfoImpl());

            TestDBUtility.deleteAllAndCommit(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);

            TestDBUtility.deleteAllAndCommit(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ACC_OPEN);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_adminPermissionParams);

            TestDBUtility.deleteAllAndCommit(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_administratorTypeParams);

            TestDBUtility.deleteAllAndCommit(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccountCode("1");
            l_expAccountOpenParams.setForeignFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setDeleteFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setPrintFlag("1");
            l_expAccountOpenParams.setReceiptFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setSpecialAcc("2");
            l_expAccountOpenParams.setInfomationClaimDatetime(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            l_expAccountOpenParams.setAccountOpenDate(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            l_expAccountOpenParams.setAccOpenRequestNumber("1");
            TestDBUtility.insertWithDelAndCommit(l_expAccountOpenParams);
            
//            acc_open_voucher_status
            TestDBUtility.deleteAllAndCommit(AccOpenVoucherStatusParams.TYPE);
            AccOpenVoucherStatusParams l_AccOpenVoucherStatusParams = TestDBUtility.getAccOpenVoucherStatusRow();
            l_AccOpenVoucherStatusParams.setAccOpenRequestNumber("1");
            l_AccOpenVoucherStatusParams.setSendTimestamp(WEB3DateUtility.getDate("20090819", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_AccOpenVoucherStatusParams);

            WEB3AdminAccOpenStateInquiryDetailRequest l_request = new WEB3AdminAccOpenStateInquiryDetailRequest();
            l_request.branchCode = "381";
            l_request.requestNumber = "1";
            l_request.accountCode = "1";
            WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();

           l_impl.getAccOpenStatusDetail(l_request);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetAccOpenStatusDetail_C0002()
    {
        final String STR_METHOD_NAME = "testGetAccOpenStatusDetail_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                new LoginInfoImpl());

            TestDBUtility.deleteAllAndCommit(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);

            TestDBUtility.deleteAllAndCommit(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ACC_OPEN);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_adminPermissionParams);

            TestDBUtility.deleteAllAndCommit(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_administratorTypeParams);

            TestDBUtility.deleteAllAndCommit(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccountCode("1");
            l_expAccountOpenParams.setForeignFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setDeleteFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setPrintFlag("1");
            l_expAccountOpenParams.setReceiptFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setSpecialAcc("2");
            l_expAccountOpenParams.setInfomationClaimDatetime(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            l_expAccountOpenParams.setAccountOpenDate(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            l_expAccountOpenParams.setAccOpenRequestNumber("1");
            TestDBUtility.insertWithDelAndCommit(l_expAccountOpenParams);
            
//            acc_open_voucher_status
            TestDBUtility.deleteAllAndCommit(AccOpenVoucherStatusParams.TYPE);
            AccOpenVoucherStatusParams l_AccOpenVoucherStatusParams = TestDBUtility.getAccOpenVoucherStatusRow();
            l_AccOpenVoucherStatusParams.setAccOpenRequestNumber("1");
            l_AccOpenVoucherStatusParams.setSendTimestamp(WEB3DateUtility.getDate("20090819", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_AccOpenVoucherStatusParams);

            WEB3AdminAccOpenStateInquiryDetailRequest l_request = new WEB3AdminAccOpenStateInquiryDetailRequest();
            l_request.branchCode = "381";
            l_request.requestNumber = "2";
            l_request.accountCode = "1";
            WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();

           l_impl.getAccOpenStatusDetail(l_request);

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00398);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetAccOpenStatusList_C0001()
    {
        final String STR_METHOD_NAME = "testGetAccOpenStatusList_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                new LoginInfoImpl());

            TestDBUtility.deleteAllAndCommit(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);

            TestDBUtility.deleteAllAndCommit(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ACC_OPEN);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_adminPermissionParams);

            TestDBUtility.deleteAllAndCommit(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_administratorTypeParams);

            TestDBUtility.deleteAllAndCommit(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccountCode("1");
            l_expAccountOpenParams.setForeignFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setDeleteFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setPrintFlag("1");
            l_expAccountOpenParams.setReceiptFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setSpecialAcc("2");
            l_expAccountOpenParams.setInfomationClaimDatetime(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            l_expAccountOpenParams.setAccountOpenDate(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            l_expAccountOpenParams.setAccOpenRequestNumber("1");
            TestDBUtility.insertWithDelAndCommit(l_expAccountOpenParams);
            
//            acc_open_voucher_status
            TestDBUtility.deleteAllAndCommit(AccOpenVoucherStatusParams.TYPE);
            AccOpenVoucherStatusParams l_AccOpenVoucherStatusParams = TestDBUtility.getAccOpenVoucherStatusRow();
            l_AccOpenVoucherStatusParams.setAccOpenRequestNumber("1");
            l_AccOpenVoucherStatusParams.setSendTimestamp(WEB3DateUtility.getDate("20090819", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_AccOpenVoucherStatusParams);

            WEB3AdminAccOpenStateInquiryListRequest l_request = new WEB3AdminAccOpenStateInquiryListRequest();
            l_request.foreignerFlag = "1";
            l_request.deleteFlag = "1";
            l_request.printFlag = "1";
            l_request.receiveFlag = "1";
            l_request.taxTypeDiv = "1";
            l_request.infoClaimDateFrom = WEB3DateUtility.getDate("20090818", "yyyyMMdd");
            l_request.infoClaimDateTo = WEB3DateUtility.getDate("20090819", "yyyyMMdd");
            l_request.sonarSendDateFrom = WEB3DateUtility.getDate("20090818", "yyyyMMdd");
            l_request.sonarSendDateTo = WEB3DateUtility.getDate("20090819", "yyyyMMdd");
            l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20090818", "yyyyMMdd");
            l_request.accountOpenDateTo = WEB3DateUtility.getDate("20090819", "yyyyMMdd");
            l_request.accountCodeFrom = "1";
            l_request.accountCodeTo = "2";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.sortKeys = new WEB3AccOpenSortKey[1];
            l_request.sortKeys[0] = new WEB3AccOpenSortKey();
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.sortKeys[0].keyItem = WEB3AccountOpenKeyItemDef.BRANCH_CODE;
            WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();

            WEB3AdminAccOpenStateInquiryListResponse l_response = l_impl.getAccOpenStatusList(l_request);
            WEB3AccOpenStateUnit[] l_accOpenStateUnits = new WEB3AccOpenStateUnit[l_response.accountOpenStateList.length];
            l_accOpenStateUnits = l_response.accountOpenStateList;
            WEB3AccOpenStateUnit l_accOpenStateUnit =  new WEB3AccOpenStateUnit();
            l_accOpenStateUnit = l_accOpenStateUnits[0]; 
            assertEquals(l_accOpenStateUnit.insiderDiv, "1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetAccOpenStatusList_C0003()
    {
        final String STR_METHOD_NAME = "testGetAccOpenStatusList_C0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                new LoginInfoImpl());

            TestDBUtility.deleteAllAndCommit(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);

            TestDBUtility.deleteAllAndCommit(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ACC_OPEN);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_adminPermissionParams);

            TestDBUtility.deleteAllAndCommit(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_administratorTypeParams);

            TestDBUtility.deleteAllAndCommit(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccountCode("1");
            l_expAccountOpenParams.setForeignFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setDeleteFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setPrintFlag("1");
            l_expAccountOpenParams.setReceiptFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setSpecialAcc("2");
            l_expAccountOpenParams.setInfomationClaimDatetime(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            l_expAccountOpenParams.setAccountOpenDate(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            l_expAccountOpenParams.setAccOpenRequestNumber("1");
            l_expAccountOpenParams.setAccountDiv("1");
            l_expAccountOpenParams.setInsiderFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setInsiderVoucherDiv("2");
            TestDBUtility.insertWithDelAndCommit(l_expAccountOpenParams);
            
//            acc_open_voucher_status
            TestDBUtility.deleteAllAndCommit(AccOpenVoucherStatusParams.TYPE);
            AccOpenVoucherStatusParams l_AccOpenVoucherStatusParams = TestDBUtility.getAccOpenVoucherStatusRow();
            l_AccOpenVoucherStatusParams.setAccOpenRequestNumber("1");
            l_AccOpenVoucherStatusParams.setSendTimestamp(WEB3DateUtility.getDate("20090819", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_AccOpenVoucherStatusParams);

            WEB3AdminAccOpenStateInquiryListRequest l_request = new WEB3AdminAccOpenStateInquiryListRequest();
            l_request.foreignerFlag = "1";
            l_request.deleteFlag = "1";
            l_request.printFlag = "1";
            l_request.receiveFlag = "1";
            l_request.taxTypeDiv = "1";
            l_request.infoClaimDateFrom = WEB3DateUtility.getDate("20090818", "yyyyMMdd");
            l_request.infoClaimDateTo = WEB3DateUtility.getDate("20090819", "yyyyMMdd");
            l_request.sonarSendDateFrom = WEB3DateUtility.getDate("20090818", "yyyyMMdd");
            l_request.sonarSendDateTo = WEB3DateUtility.getDate("20090819", "yyyyMMdd");
            l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20090818", "yyyyMMdd");
            l_request.accountOpenDateTo = WEB3DateUtility.getDate("20090819", "yyyyMMdd");
            l_request.accountCodeFrom = "1";
            l_request.accountCodeTo = "2";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.sortKeys = new WEB3AccOpenSortKey[1];
            l_request.sortKeys[0] = new WEB3AccOpenSortKey();
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.sortKeys[0].keyItem = WEB3AccountOpenKeyItemDef.BRANCH_CODE;
            WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();

            WEB3AdminAccOpenStateInquiryListResponse l_response = l_impl.getAccOpenStatusList(l_request);
            WEB3AccOpenStateUnit[] l_accOpenStateUnits = new WEB3AccOpenStateUnit[l_response.accountOpenStateList.length];
            l_accOpenStateUnits = l_response.accountOpenStateList;
            WEB3AccOpenStateUnit l_accOpenStateUnit =  new WEB3AccOpenStateUnit();
            l_accOpenStateUnit = l_accOpenStateUnits[0]; 
            assertEquals(l_accOpenStateUnit.insiderDiv, "2");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetAccOpenStatusList_C0002()
    {
        final String STR_METHOD_NAME = "testGetAccOpenStatusList_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                new LoginInfoImpl());

            TestDBUtility.deleteAllAndCommit(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);

            TestDBUtility.deleteAllAndCommit(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ACC_OPEN);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_adminPermissionParams);

            TestDBUtility.deleteAllAndCommit(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_administratorTypeParams);

            TestDBUtility.deleteAllAndCommit(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccountCode("1");
            l_expAccountOpenParams.setForeignFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setDeleteFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setPrintFlag("1");
            l_expAccountOpenParams.setReceiptFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setSpecialAcc("1");
            l_expAccountOpenParams.setInfomationClaimDatetime(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            l_expAccountOpenParams.setAccountOpenDate(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            
            TestDBUtility.insertWithDelAndCommit(l_expAccountOpenParams);

            WEB3AdminAccOpenStateInquiryListRequest l_request = new WEB3AdminAccOpenStateInquiryListRequest();
            l_request.foreignerFlag = "1";
            l_request.deleteFlag = "1";
            l_request.printFlag = "1";
            l_request.receiveFlag = "1";
            l_request.taxTypeDiv = "0";
            l_request.infoClaimDateFrom = WEB3DateUtility.getDate("20090818", "yyyyMMdd");
            l_request.infoClaimDateTo = WEB3DateUtility.getDate("20090819", "yyyyMMdd");
            l_request.sonarSendDateFrom = WEB3DateUtility.getDate("20090818", "yyyyMMdd");
            l_request.sonarSendDateTo = WEB3DateUtility.getDate("20090819", "yyyyMMdd");
            l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20090818", "yyyyMMdd");
            l_request.accountOpenDateTo = WEB3DateUtility.getDate("20090819", "yyyyMMdd");
            l_request.accountCodeFrom = "1";
            l_request.accountCodeTo = "2";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.sortKeys = new WEB3AccOpenSortKey[1];
            l_request.sortKeys[0] = new WEB3AccOpenSortKey();
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.sortKeys[0].keyItem = WEB3AccountOpenKeyItemDef.BRANCH_CODE;
            WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();

            l_impl.getAccOpenStatusList(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitRegistUpdated_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitRegistUpdated_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                new LoginInfoImpl());

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
            TestDBUtility.deleteAllAndCommit(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);

            TestDBUtility.deleteAllAndCommit(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ACC_OPEN);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_adminPermissionParams);

            TestDBUtility.deleteAllAndCommit(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_administratorTypeParams);

            TestDBUtility.deleteAllAndCommit(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccountCode("1");
            l_expAccountOpenParams.setForeignFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setDeleteFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setPrintFlag("1");
            l_expAccountOpenParams.setReceiptFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setSpecialAcc("1");
            l_expAccountOpenParams.setInfomationClaimDatetime(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            l_expAccountOpenParams.setAccountOpenDate(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_expAccountOpenParams);

            TestDBUtility.deleteAllAndCommit(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDelAndCommit(l_branchParams);

            WEB3AdminAccOpenApplyUpdateCompleteRequest l_request =
                new WEB3AdminAccOpenApplyUpdateCompleteRequest();
            l_request.accoutOpenApplyInfo = new WEB3AccOpenApplyInfo();
            l_request.accoutOpenApplyInfo.branchCode = "381";
            l_request.accoutOpenApplyInfo.institutionCode = "0D";
            l_request.voucherInfo = new WEB3AccOpenVoucherInfo();
            l_request.accoutOpenApplyInfo.deleteFlag = "1";
            WEB3AdminAccOpenStateInquiryServiceImpl l_impl =
                new WEB3AdminAccOpenStateInquiryServiceImplForTest();

            l_impl.submitRegistUpdated(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03178, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitRegistUpdated_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitRegistUpdated_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                new LoginInfoImpl());

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
            TestDBUtility.deleteAllAndCommit(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);

            TestDBUtility.deleteAllAndCommit(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ACC_OPEN);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_adminPermissionParams);

            TestDBUtility.deleteAllAndCommit(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_administratorTypeParams);

            TestDBUtility.deleteAllAndCommit(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccountCode("1");
            l_expAccountOpenParams.setForeignFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setDeleteFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setPrintFlag("1");
            l_expAccountOpenParams.setReceiptFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setSpecialAcc("1");
            l_expAccountOpenParams.setInfomationClaimDatetime(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            l_expAccountOpenParams.setAccountOpenDate(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_expAccountOpenParams);

            TestDBUtility.deleteAllAndCommit(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDelAndCommit(l_branchParams);

            WEB3AdminAccOpenApplyUpdateCompleteRequest l_request =
                new WEB3AdminAccOpenApplyUpdateCompleteRequest();
            l_request.accoutOpenApplyInfo = new WEB3AccOpenApplyInfo();
            l_request.accoutOpenApplyInfo.branchCode = "381";
            l_request.accoutOpenApplyInfo.institutionCode = "0D";
            l_request.voucherInfo = new WEB3AccOpenVoucherInfo();
            l_request.accoutOpenApplyInfo.deleteFlag = "0";
            WEB3AdminAccOpenStateInquiryServiceImpl l_impl =
                new WEB3AdminAccOpenStateInquiryServiceImplForTest();

            l_impl.submitRegistUpdated(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01309, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitVoucherCreated_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitVoucherCreated_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                new LoginInfoImpl());

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
            TestDBUtility.deleteAllAndCommit(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);

            TestDBUtility.deleteAllAndCommit(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ACC_OPEN);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_adminPermissionParams);

            TestDBUtility.deleteAllAndCommit(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_administratorTypeParams);

            TestDBUtility.deleteAllAndCommit(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccountCode("1");
            l_expAccountOpenParams.setForeignFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setDeleteFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setPrintFlag("1");
            l_expAccountOpenParams.setReceiptFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setSpecialAcc("1");
            l_expAccountOpenParams.setInfomationClaimDatetime(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            l_expAccountOpenParams.setAccountOpenDate(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_expAccountOpenParams);

            TestDBUtility.deleteAllAndCommit(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDelAndCommit(l_branchParams);

            WEB3AdminAccOpenVoucherMakeCompleteRequest l_request =
                new WEB3AdminAccOpenVoucherMakeCompleteRequest();
            l_request.accoutOpenApplyInfo = new WEB3AccOpenApplyInfo();
            l_request.accoutOpenApplyInfo.branchCode = "381";
            l_request.accoutOpenApplyInfo.institutionCode = "0D";
            l_request.voucherInfo = new WEB3AccOpenVoucherInfo();
            l_request.accoutOpenApplyInfo.deleteFlag = "1";
            WEB3AdminAccOpenStateInquiryServiceImpl l_impl =
                new WEB3AdminAccOpenStateInquiryServiceImplForTest();

            l_impl.submitVoucherCreated(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03178, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitVoucherCreated_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitVoucherCreated_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                new LoginInfoImpl());

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
            TestDBUtility.deleteAllAndCommit(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);

            TestDBUtility.deleteAllAndCommit(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ACC_OPEN);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_adminPermissionParams);

            TestDBUtility.deleteAllAndCommit(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_administratorTypeParams);

            TestDBUtility.deleteAllAndCommit(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccountCode("1");
            l_expAccountOpenParams.setForeignFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setDeleteFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setPrintFlag("1");
            l_expAccountOpenParams.setReceiptFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setSpecialAcc("1");
            l_expAccountOpenParams.setInfomationClaimDatetime(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            l_expAccountOpenParams.setAccountOpenDate(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_expAccountOpenParams);

            TestDBUtility.deleteAllAndCommit(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDelAndCommit(l_branchParams);

            WEB3AdminAccOpenVoucherMakeCompleteRequest l_request =
                new WEB3AdminAccOpenVoucherMakeCompleteRequest();
            l_request.accoutOpenApplyInfo = new WEB3AccOpenApplyInfo();
            l_request.accoutOpenApplyInfo.branchCode = "381";
            l_request.accoutOpenApplyInfo.institutionCode = "0D";
            l_request.voucherInfo = new WEB3AccOpenVoucherInfo();
            l_request.accoutOpenApplyInfo.deleteFlag = "0";
            l_request.accoutOpenApplyInfo.accountCodeAutoFlag = "1";
            l_request.accountDiv = "1";
            WEB3AdminAccOpenStateInquiryServiceImpl l_impl =
                new WEB3AdminAccOpenStateInquiryServiceImplForTest();

            l_impl.submitVoucherCreated(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01309, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateRegistUpdated_C0001()
    {
        final String STR_METHOD_NAME = "testValidateRegistUpdated_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                new LoginInfoImpl());

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
            TestDBUtility.deleteAllAndCommit(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);

            TestDBUtility.deleteAllAndCommit(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ACC_OPEN);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_adminPermissionParams);

            TestDBUtility.deleteAllAndCommit(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_administratorTypeParams);

            TestDBUtility.deleteAllAndCommit(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccountCode("1");
            l_expAccountOpenParams.setForeignFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setDeleteFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setPrintFlag("1");
            l_expAccountOpenParams.setReceiptFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setSpecialAcc("1");
            l_expAccountOpenParams.setInfomationClaimDatetime(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            l_expAccountOpenParams.setAccountOpenDate(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_expAccountOpenParams);

            TestDBUtility.deleteAllAndCommit(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDelAndCommit(l_branchParams);

            WEB3AdminAccOpenApplyUpdateConfirmRequest l_request =
                new WEB3AdminAccOpenApplyUpdateConfirmRequest();
            l_request.accoutOpenApplyInfo = new WEB3AccOpenApplyInfo();
            l_request.accoutOpenApplyInfo.branchCode = "381";
            l_request.accoutOpenApplyInfo.institutionCode = "0D";
            l_request.voucherInfo = new WEB3AccOpenVoucherInfo();
            l_request.accoutOpenApplyInfo.deleteFlag = "1";
            WEB3AdminAccOpenStateInquiryServiceImpl l_impl =
                new WEB3AdminAccOpenStateInquiryServiceImplForTest();

            l_impl.validateRegistUpdated(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03178, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateRegistUpdated_C0002()
    {
        final String STR_METHOD_NAME = "testValidateRegistUpdated_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                new LoginInfoImpl());

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
            TestDBUtility.deleteAllAndCommit(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);

            TestDBUtility.deleteAllAndCommit(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ACC_OPEN);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_adminPermissionParams);

            TestDBUtility.deleteAllAndCommit(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_administratorTypeParams);

            TestDBUtility.deleteAllAndCommit(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccountCode("1");
            l_expAccountOpenParams.setForeignFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setDeleteFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setPrintFlag("1");
            l_expAccountOpenParams.setReceiptFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setSpecialAcc("1");
            l_expAccountOpenParams.setInfomationClaimDatetime(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            l_expAccountOpenParams.setAccountOpenDate(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_expAccountOpenParams);

            TestDBUtility.deleteAllAndCommit(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDelAndCommit(l_branchParams);

            WEB3AdminAccOpenApplyUpdateConfirmRequest l_request =
                new WEB3AdminAccOpenApplyUpdateConfirmRequest();
            l_request.accoutOpenApplyInfo = new WEB3AccOpenApplyInfo();
            l_request.accoutOpenApplyInfo.branchCode = "381";
            l_request.accoutOpenApplyInfo.institutionCode = "0D";
            l_request.voucherInfo = new WEB3AccOpenVoucherInfo();
            l_request.accoutOpenApplyInfo.deleteFlag = "0";
            l_request.accoutOpenApplyInfo.accountCodeAutoFlag = "1";
            WEB3AdminAccOpenStateInquiryServiceImpl l_impl =
                new WEB3AdminAccOpenStateInquiryServiceImplForTest();

            l_impl.validateRegistUpdated(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01309, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateVoucherCreated_C0001()
    {
        final String STR_METHOD_NAME = "testValidateVoucherCreated_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                new LoginInfoImpl());

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
            TestDBUtility.deleteAllAndCommit(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);

            TestDBUtility.deleteAllAndCommit(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ACC_OPEN);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_adminPermissionParams);

            TestDBUtility.deleteAllAndCommit(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_administratorTypeParams);

            TestDBUtility.deleteAllAndCommit(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccountCode("1");
            l_expAccountOpenParams.setForeignFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setDeleteFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setPrintFlag("1");
            l_expAccountOpenParams.setReceiptFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setSpecialAcc("1");
            l_expAccountOpenParams.setInfomationClaimDatetime(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            l_expAccountOpenParams.setAccountOpenDate(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_expAccountOpenParams);

            TestDBUtility.deleteAllAndCommit(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDelAndCommit(l_branchParams);

            WEB3AdminAccOpenVoucherMakeConfirmRequest l_request =
                new WEB3AdminAccOpenVoucherMakeConfirmRequest();
            l_request.accoutOpenApplyInfo = new WEB3AccOpenApplyInfo();
            l_request.accoutOpenApplyInfo.branchCode = "381";
            l_request.accoutOpenApplyInfo.institutionCode = "0D";
            l_request.voucherInfo = new WEB3AccOpenVoucherInfo();
            l_request.accoutOpenApplyInfo.deleteFlag = "1";
            WEB3AdminAccOpenStateInquiryServiceImpl l_impl =
                new WEB3AdminAccOpenStateInquiryServiceImplForTest();

            l_impl.validateVoucherCreated(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03178, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateVoucherCreated_C0002()
    {
        final String STR_METHOD_NAME = "testValidateVoucherCreated_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                new LoginInfoImpl());

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
            TestDBUtility.deleteAllAndCommit(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);

            TestDBUtility.deleteAllAndCommit(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.ACC_OPEN);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_adminPermissionParams);

            TestDBUtility.deleteAllAndCommit(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDelAndCommit(l_administratorTypeParams);

            TestDBUtility.deleteAllAndCommit(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccountCode("1");
            l_expAccountOpenParams.setForeignFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setDeleteFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setPrintFlag("1");
            l_expAccountOpenParams.setReceiptFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setSpecialAcc("1");
            l_expAccountOpenParams.setInfomationClaimDatetime(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            l_expAccountOpenParams.setAccountOpenDate(WEB3DateUtility.getDate("20090818", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_expAccountOpenParams);

            TestDBUtility.deleteAllAndCommit(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDelAndCommit(l_branchParams);

            WEB3AdminAccOpenVoucherMakeConfirmRequest l_request =
                new WEB3AdminAccOpenVoucherMakeConfirmRequest();
            l_request.accoutOpenApplyInfo = new WEB3AccOpenApplyInfo();
            l_request.accoutOpenApplyInfo.branchCode = "381";
            l_request.accoutOpenApplyInfo.institutionCode = "0D";
            l_request.voucherInfo = new WEB3AccOpenVoucherInfo();
            l_request.accoutOpenApplyInfo.deleteFlag = "0";
            l_request.accoutOpenApplyInfo.accountCodeAutoFlag = "1";
            WEB3AdminAccOpenStateInquiryServiceImpl l_impl =
                new WEB3AdminAccOpenStateInquiryServiceImplForTest();

            l_impl.validateVoucherCreated(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01309, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    private class WEB3AdminAccOpenStateInquiryServiceImplForTest extends WEB3AdminAccOpenStateInquiryServiceImpl
    {
        protected void validateStatusUpdated(WEB3AccOpenVoucherInfo l_accOpenVoucherInfo, String l_strInstitutionCode, String l_strRequestNumber) throws WEB3BaseException
        {
            
        }
    }
    
    /**
     * リクエストデータの整合性をチェックする。
     *
     */
    public void test_submitChange_0001()
    {
        final String STR_METHOD_NAME = "test_submitChange_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();
        WEB3AccOpenChangeRequest l_request = new WEB3AccOpenChangeRequest();
//        l_request.requestNumber = "ff";
//        l_request.updateItem = "1";
//        l_request.updateStatus = "0";
        
        try
        {
            l_impl.submitChange(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00829, l_ex.getErrorInfo());
        }
        catch (Exception l_ex) 
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *  validate権限(機@能カテゴリコード : String, is更新 : boolean)
     *
     */
    public void test_submitChange_0002()
    {
        final String STR_METHOD_NAME = "test_submitChange_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();
        WEB3AccOpenChangeRequest l_request = new WEB3AccOpenChangeRequest();
        l_request.requestNumber = "ff";
        l_request.updateItem = "1";
        l_request.updateStatus = "0";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(33381330003L));
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorId(123456L);
            TestDBUtility.insertWithDel(l_administratorParams);
            l_impl.submitChange(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex) 
        {
            log.error(l_ex.getLocalizedMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *  口座開設見込客既存データが存在しない場合（nullが返却された場合）、例外をスローする。
     *
     */
    public void test_submitChange_0003()
    {
        final String STR_METHOD_NAME = "test_submitChange_0003()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();
        WEB3AccOpenChangeRequest l_request = new WEB3AccOpenChangeRequest();
        l_request.requestNumber = "ff";
        l_request.updateItem = "1";
        l_request.updateStatus = "0";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(33381330003L));
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorId(123456L);
            l_administratorParams.setPermissionLevel("1");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccOpenRequestNumber("dd");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("1");
            l_adminPermissionParams.setTransactionCategory("A0401");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            l_impl.submitChange(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01318, l_ex.getErrorInfo());
        }
        catch (Exception l_ex) 
        {
            log.error(l_ex.getLocalizedMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate部店権限(部店コード : String[])
     *
     */
    public void test_submitChange_0004()
    {
        final String STR_METHOD_NAME = "test_submitChange_0004()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();
        WEB3AccOpenChangeRequest l_request = new WEB3AccOpenChangeRequest();
        l_request.requestNumber = "ff";
        l_request.updateItem = "1";
        l_request.updateStatus = "0";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(33381330003L));
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorId(123456L);
            l_administratorParams.setPermissionLevel("1");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccOpenRequestNumber("ff");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("1");
            l_adminPermissionParams.setTransactionCategory("A0401");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            l_impl.submitChange(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch (Exception l_ex) 
        {
            log.error(l_ex.getLocalizedMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * リクエスト更新項目が「1：印刷切替」の場合は処理を行う
     * リクエスト.更新後状態と口座開設見込客.印刷フラグが一致する場合（equals() == true）、例外をスローする。
     *
     */
    public void test_submitChange_0005()
    {
        final String STR_METHOD_NAME = "test_submitChange_0005()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();
        WEB3AccOpenChangeRequest l_request = new WEB3AccOpenChangeRequest();
        l_request.requestNumber = "ff";
        l_request.updateItem = "1";
        l_request.updateStatus = "0";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(33381330003L));
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorId(123456L);
            l_administratorParams.setPermissionLevel("1");
            l_administratorParams.setBranchCode("kju");
            l_administratorParams.setPermissionLevel("331");
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);
            
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccOpenRequestNumber("ff");
            l_expAccountOpenParams.setPrintFlag("0");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0401");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            l_impl.submitChange(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03177, l_ex.getErrorInfo());
        }
        catch (Exception l_ex) 
        {
            log.error(l_ex.getLocalizedMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * リクエスト更新項目が「1：印刷切替」の場合は処理を行う
     * save口座開設見込客
     *
     */
    public void test_submitChange_0006()
    {
        final String STR_METHOD_NAME = "test_submitChange_0006()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();
        WEB3AccOpenChangeRequest l_request = new WEB3AccOpenChangeRequest();
        l_request.requestNumber = "ff";
        l_request.updateItem = "1";
        l_request.updateStatus = "0";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(33381330003L));
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorId(123456L);
            l_administratorParams.setPermissionLevel("1");
            l_administratorParams.setBranchCode("kju");
            l_administratorParams.setPermissionLevel("331");
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);
            
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccOpenRequestNumber("ff");
            l_expAccountOpenParams.setPrintFlag("1");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0401");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            l_impl.submitChange(l_request);

            
            String l_strWhere = " institution_code = ? and acc_open_request_number = ?  ";
            Object[] l_objConds =  new Object[]{"0D", "ff"};
            List l_lisRecordexcs = null;
            try
            {
                l_lisRecordexcs = Processors.getDefaultProcessor().doFindAllQuery(
                    ExpAccountOpenParams.TYPE,
                    l_strWhere,
                    l_objConds);
            }
            catch (DataQueryException l_ex)
            {
                fail();
            }
            catch (DataNetworkException l_ex)
            {
                fail();
            }
            ExpAccountOpenParams l_params = (ExpAccountOpenParams)l_lisRecordexcs.get(0);
            assertEquals("0", l_params.getPrintFlag());
        }
        catch (WEB3BaseException l_ex)
        {
            fail();
        }
        catch (Exception l_ex) 
        {
            log.error(l_ex.getLocalizedMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * リクエスト更新項目が「2：受領切替」の場合は処理を行う
     * リクエスト.更新後状態と口座開設見込客.受領フラグが一致する場合（equals() == true）、例外をスローする。
     *
     */
    public void test_submitChange_0007()
    {
        final String STR_METHOD_NAME = "test_submitChange_0007()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();
        WEB3AccOpenChangeRequest l_request = new WEB3AccOpenChangeRequest();
        l_request.requestNumber = "ff";
        l_request.updateItem = "2";
        l_request.updateStatus = "0";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(33381330003L));
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorId(123456L);
            l_administratorParams.setPermissionLevel("1");
            l_administratorParams.setBranchCode("kju");
            l_administratorParams.setPermissionLevel("331");
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);
            
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccOpenRequestNumber("ff");
            l_expAccountOpenParams.setPrintFlag("0");
            l_expAccountOpenParams.setReceiptFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0401");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            l_impl.submitChange(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03177, l_ex.getErrorInfo());
        }
        catch (Exception l_ex) 
        {
            log.error(l_ex.getLocalizedMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * リクエスト更新項目が「2：受領切替」の場合は処理を行う
     * save口座開設見込客
     */
    public void test_submitChange_0008()
    {
        final String STR_METHOD_NAME = "test_submitChange_0008()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();
        WEB3AccOpenChangeRequest l_request = new WEB3AccOpenChangeRequest();
        l_request.requestNumber = "ff";
        l_request.updateItem = "2";
        l_request.updateStatus = "0";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(33381330003L));
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorId(123456L);
            l_administratorParams.setPermissionLevel("1");
            l_administratorParams.setBranchCode("kju");
            l_administratorParams.setPermissionLevel("331");
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);
            
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccOpenRequestNumber("ff");
            l_expAccountOpenParams.setPrintFlag("1");
            l_expAccountOpenParams.setReceiptFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0401");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            l_impl.submitChange(l_request);

            
            String l_strWhere = " institution_code = ? and acc_open_request_number = ?  ";
            Object[] l_objConds =  new Object[]{"0D", "ff"};
            List l_lisRecordexcs = null;
            try
            {
                l_lisRecordexcs = Processors.getDefaultProcessor().doFindAllQuery(
                    ExpAccountOpenParams.TYPE,
                    l_strWhere,
                    l_objConds);
            }
            catch (DataQueryException l_ex)
            {
                fail();
            }
            catch (DataNetworkException l_ex)
            {
                fail();
            }
            ExpAccountOpenParams l_params = (ExpAccountOpenParams)l_lisRecordexcs.get(0);
            assertEquals("0", l_params.getReceiptFlag().intValue() + "");
        }
        catch (WEB3BaseException l_ex)
        {
            fail();
        }
        catch (Exception l_ex) 
        {
            log.error(l_ex.getLocalizedMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * リクエスト更新項目が「3：削除切替」の場合は処理を行う
     * リクエスト.更新後状態と口座開設見込客.削除フラグが一致する場合（equals() == true）、例外をスローする。
     *
     */
    public void test_submitChange_0009()
    {
        final String STR_METHOD_NAME = "test_submitChange_0009()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();
        WEB3AccOpenChangeRequest l_request = new WEB3AccOpenChangeRequest();
        l_request.requestNumber = "ff";
        l_request.updateItem = "3";
        l_request.updateStatus = "0";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(33381330003L));
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorId(123456L);
            l_administratorParams.setPermissionLevel("1");
            l_administratorParams.setBranchCode("kju");
            l_administratorParams.setPermissionLevel("331");
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);
            
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccOpenRequestNumber("ff");
            l_expAccountOpenParams.setPrintFlag("0");
            l_expAccountOpenParams.setReceiptFlag(BooleanEnum.FALSE);
            l_expAccountOpenParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0401");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            l_impl.submitChange(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03177, l_ex.getErrorInfo());
        }
        catch (Exception l_ex) 
        {
            log.error(l_ex.getLocalizedMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * リクエスト更新項目が「3：削除切替」の場合は処理を行う
     * get口座開設状況区分( )
     * 口座開設状況区分が「0：　@DEFAULT（未開設）」以外の場合、例外をスローする
     */
    public void test_submitChange_0010()
    {
        final String STR_METHOD_NAME = "test_submitChange_0010()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();
        WEB3AccOpenChangeRequest l_request = new WEB3AccOpenChangeRequest();
        l_request.requestNumber = "ff";
        l_request.updateItem = "3";
        l_request.updateStatus = "0";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(33381330003L));
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorId(123456L);
            l_administratorParams.setPermissionLevel("1");
            l_administratorParams.setBranchCode("kju");
            l_administratorParams.setPermissionLevel("331");
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);
            
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccOpenRequestNumber("ff");
            l_expAccountOpenParams.setPrintFlag("0");
            l_expAccountOpenParams.setReceiptFlag(BooleanEnum.FALSE);
            l_expAccountOpenParams.setDeleteFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setAccountOpenDate(new Timestamp(123233L));
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0401");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            l_impl.submitChange(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03181, l_ex.getErrorInfo());
        }
        catch (Exception l_ex) 
        {
            log.error(l_ex.getLocalizedMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * リクエスト更新項目が「3：削除切替」の場合は処理を行う
     * save口座開設見込客
     */
    public void test_submitChange_0011()
    {
        final String STR_METHOD_NAME = "test_submitChange_0011()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();
        WEB3AccOpenChangeRequest l_request = new WEB3AccOpenChangeRequest();
        l_request.requestNumber = "ff";
        l_request.updateItem = "3";
        l_request.updateStatus = "0";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(33381330003L));
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorId(123456L);
            l_administratorParams.setPermissionLevel("1");
            l_administratorParams.setBranchCode("kju");
            l_administratorParams.setPermissionLevel("331");
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);
            
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccOpenRequestNumber("ff");
            l_expAccountOpenParams.setPrintFlag("0");
            l_expAccountOpenParams.setReceiptFlag(BooleanEnum.FALSE);
            l_expAccountOpenParams.setDeleteFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0401");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            AccOpenVoucherStatusParams l_accOpenVoucherStatusParams = new AccOpenVoucherStatusParams();
            l_accOpenVoucherStatusParams.setInstitutionCode("0D");
            l_accOpenVoucherStatusParams.setAccOpenRequestNumber("ff");
            l_accOpenVoucherStatusParams.setRequestCode("ss");
            l_accOpenVoucherStatusParams.setSerialNo("1");
            l_accOpenVoucherStatusParams.setVoucherStatus("0");
            l_accOpenVoucherStatusParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_accOpenVoucherStatusParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            l_impl.submitChange(l_request);
            
            String l_strWhere = " institution_code = ? and acc_open_request_number = ?  ";
            Object[] l_objConds =  new Object[]{"0D", "ff"};
            List l_lisRecordexcs = null;
            try
            {
                l_lisRecordexcs = Processors.getDefaultProcessor().doFindAllQuery(
                    ExpAccountOpenParams.TYPE,
                    l_strWhere,
                    l_objConds);
            }
            catch (DataQueryException l_ex)
            {
                fail();
            }
            catch (DataNetworkException l_ex)
            {
                fail();
            }
            ExpAccountOpenParams l_params = (ExpAccountOpenParams)l_lisRecordexcs.get(0);
            assertEquals("0", l_params.getDeleteFlag().intValue() + "");
        }
        catch (WEB3BaseException l_ex)
        {
            fail();
        }
        catch (Exception l_ex) 
        {
            log.error(l_ex.getLocalizedMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate自動採番
     * 引数.口座開設申込情報.顧客コード自動採番フラグ != 1(自動採番を行う)の場合
     * メソッドを正常終了する
     */
    public void test_validateAuto_0001()
    {
        final String STR_METHOD_NAME = "test_validateAuto_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();
        WEB3AccOpenApplyInfo l_accOpenApplyInfo = new WEB3AccOpenApplyInfo();
        l_accOpenApplyInfo.accountCodeAutoFlag = "2";
        try
        {
            Method l_method  = WEB3AdminAccOpenStateInquiryServiceImpl.class.getDeclaredMethod("validateAuto", new Class[]{WEB3AccOpenApplyInfo.class, String.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{l_accOpenApplyInfo, "kkk"});
            assertTrue(true);
        }
        catch (SecurityException e)
        {
            fail();
        }
        catch (NoSuchMethodException e)
        {
            fail();   
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate自動採番
     * 証券会社プリファ@レンステーブルからレコードを取得する 
     * レコードが取得できない場合、以下のチェックを行う 
     * 引数.口座開設申込情報.口座区分 != 0:個人アカウント の場合 
     */
    public void test_validateAuto_0002()
    {
        final String STR_METHOD_NAME = "test_validateAuto_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();
        WEB3AccOpenApplyInfo l_accOpenApplyInfo = new WEB3AccOpenApplyInfo();
        l_accOpenApplyInfo.accountCodeAutoFlag = "1";
        l_accOpenApplyInfo.accountType = "1";

        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
           
            TestDBUtility.insertWithDel(l_institutionParams);
            
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("11");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            
            Method l_method  = WEB3AdminAccOpenStateInquiryServiceImpl.class.getDeclaredMethod("validateAuto", new Class[]{WEB3AccOpenApplyInfo.class, String.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{l_accOpenApplyInfo, "0D"});
        }
        catch (InvocationTargetException e)
        {
            WEB3BaseException l =  (WEB3BaseException)e.getTargetException(); 
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02610, l.getErrorInfo()); 
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate自動採番
     * 証券会社プリファ@レンステーブルからレコードを取得する 
     * レコードが取得できない場合、以下のチェックを行う 
     * 引数.口座開設申込情報.口座区分 != 0:個人アカウント の場合 
     */
    public void test_validateAuto_0003()
    {
        final String STR_METHOD_NAME = "test_validateAuto_0003()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccOpenStateInquiryServiceImpl l_impl = new WEB3AdminAccOpenStateInquiryServiceImpl();
        WEB3AccOpenApplyInfo l_accOpenApplyInfo = new WEB3AccOpenApplyInfo();
        l_accOpenApplyInfo.accountCodeAutoFlag = "1";
        l_accOpenApplyInfo.accountType = "1";
        l_accOpenApplyInfo.residentDiv = "1";
        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
           
            TestDBUtility.insertWithDel(l_institutionParams);
            
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("accountopen.corporate.auto.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            
            Method l_method  = WEB3AdminAccOpenStateInquiryServiceImpl.class.getDeclaredMethod("validateAuto", new Class[]{WEB3AccOpenApplyInfo.class, String.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{l_accOpenApplyInfo, "0D"});
        }
        catch (InvocationTargetException e)
        {
            WEB3BaseException l =  (WEB3BaseException)e.getTargetException(); 
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02610, l.getErrorInfo()); 
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
