head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.52.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiOtherOrgIdDownloadServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽImplのテストクラス(WEB3AdminSrvRegiOtherOrgIdDownloadServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/20 周墨洋 (中訊) 新規作成
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.define.WEB3SrvRegiKeyItemDef;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdDownloadRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdDownloadResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListReferenceRequest;
import webbroker3.srvregi.message.WEB3SrvRegiSortKey;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽImplのテストクラス)<BR>
 * サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽImplのテストクラス<BR>
 * <BR>
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdDownloadServiceImplTest
    extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiOtherOrgIdDownloadServiceImplTest.class);

    /**
     * サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽImpl
     */
    private WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl impl;

    /**
     * @@param l_arg0
     * l_arg0
     */
    public WEB3AdminSrvRegiOtherOrgIdDownloadServiceImplTest(String l_arg0)
    {
        super(l_arg0);
    }

    /**
     *
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /**
     *
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * testGetDownloadFileCase0001
     */
    public void testGetDownloadFileCase0001()
    {
        final String STR_METHOD_NAME = "testGetDownloadFile_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.impl = new WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl();

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(l_branchParams.getBranchId());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(123L);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);

         
//          TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
//          AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
//          l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
//          l_administratorTypeParams.setDirAdminFlag(1);
//          TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSequenceNumber(123L);
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_otherOrgInfoAdminParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_otherOrgInfoAdminParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setId("87654321");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("a");
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setCreatedTimestamp(
                Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
                Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(
                l_administrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_administrator,
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG,
                false,
                true);

            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administrator,
                    new String[]{l_mainAccountParams.getBranchCode()},
                    true);

            WEB3AdminSrvRegiOtherOrgIdDownloadRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdDownloadRequest();
            WEB3SrvRegiSortKey l_srvRegiSortKey = new WEB3SrvRegiSortKey();
            l_srvRegiSortKey.keyItem = WEB3SrvRegiKeyItemDef.SEQUENCE_NUMBER;
            l_srvRegiSortKey.ascDesc = WEB3AscDescDef.ASC;
            l_request.sortKeys = new WEB3SrvRegiSortKey[]{l_srvRegiSortKey};

            l_request.seqNumber = "123";
            l_request.serviceDiv = "12";
            l_request.branchCode =
                new String[]{l_mainAccountParams.getBranchCode()};
            l_request.accountCode =
                l_mainAccountParams.getAccountCode().substring(0, 6);
            l_request.status = "9";
            l_request.id = "87654321";
            l_request.appliStartFrom =
                WEB3DateUtility.getDate("20080327", "yyyyMMdd");
            l_request.appliStartTo =
                WEB3DateUtility.getDate("20080327", "yyyyMMdd");

            WEB3AdminSrvRegiOtherOrgIdDownloadResponse l_response =
                this.impl.getDownloadFile(l_request);

            assertEquals(3, l_response.lines.length);
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyy/MM/dd HH:mm:ss"),
                    l_response.lines[0]);
    
            assertEquals(
                "通番,ID,ステータス,証券会社コード,部店コード,口座コード,適用期間From,適用期間To",
                l_response.lines[1]);
            assertEquals(
                "123,87654321,9,0D,381,251224,20080327,20080327",
                l_response.lines[2]);

            assertEquals(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()),
                WEB3DateUtility.toDay(l_response.currentDate));

            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * testGetDownloadFileCase0002
     */
    public void testGetDownloadFileCase0002()
    {
        final String STR_METHOD_NAME = "testGetDownloadFile_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.impl = new WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl();

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(l_branchParams.getBranchId());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(123L);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);

            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSequenceNumber(123L);
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_otherOrgInfoAdminParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_otherOrgInfoAdminParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setId("87654321");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("a");
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setCreatedTimestamp(
                Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
                Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSequenceNumber(124L);
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_otherOrgInfoAdminParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_otherOrgInfoAdminParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setId("87654321");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("a");
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setCreatedTimestamp(
                Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
                Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSequenceNumber(125L);
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_otherOrgInfoAdminParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_otherOrgInfoAdminParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setId("87654321");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("a");
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setCreatedTimestamp(
                Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
                Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(
                l_administrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_administrator,
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG,
                false,
                true);

            WEB3AdministratorForMock.mockValidateBranchPermission(
                l_administrator,
                new String[]{l_mainAccountParams.getBranchCode()},
                true);
            
            WEB3AdminSrvRegiOtherOrgIdDownloadRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdDownloadRequest();
            WEB3SrvRegiSortKey l_srvRegiSortKey = new WEB3SrvRegiSortKey();
            l_srvRegiSortKey.keyItem = WEB3SrvRegiKeyItemDef.SEQUENCE_NUMBER;
            l_srvRegiSortKey.ascDesc = WEB3AscDescDef.ASC;
            l_request.sortKeys = new WEB3SrvRegiSortKey[]{l_srvRegiSortKey};

            l_request.seqNumber = null;
            l_request.serviceDiv = "12";
            l_request.branchCode =
                new String[]{l_mainAccountParams.getBranchCode()};
            l_request.accountCode =
                l_mainAccountParams.getAccountCode().substring(0, 6);
            l_request.status = "9";
            l_request.id = "87654321";
            l_request.appliStartFrom =
                WEB3DateUtility.getDate("20080327", "yyyyMMdd");
            l_request.appliStartTo =
                WEB3DateUtility.getDate("20080327", "yyyyMMdd");

            WEB3AdminSrvRegiOtherOrgIdDownloadResponse l_response =
                this.impl.getDownloadFile(l_request);

            assertEquals(5, l_response.lines.length);
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyy/MM/dd HH:mm:ss"),
                    l_response.lines[0]);
            assertEquals(
                "通番,ID,ステータス,証券会社コード,部店コード,口座コード,適用期間From,適用期間To",
                l_response.lines[1]);
            assertEquals(
                "123,87654321,9,0D,381,251224,20080327,20080327",
                l_response.lines[2]);
            assertEquals(
                "124,87654321,9,0D,381,251224,20080327,20080327",
                l_response.lines[3]);
            assertEquals(
                "125,87654321,9,0D,381,251224,20080327,20080327",
                l_response.lines[4]);

            assertEquals(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()),
                WEB3DateUtility.toDay(l_response.currentDate));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * testGetDownloadFileCase0003
     */
    public void testGetDownloadFileCase0003()
    {
        final String STR_METHOD_NAME = "testGetDownloadFile_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.impl = new WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl();

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(l_branchParams.getBranchId());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(123L);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(
                l_administrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_administrator,
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG,
                false,
                true);

            WEB3AdministratorForMock.mockValidateBranchPermission(
                l_administrator,
                new String[]{l_mainAccountParams.getBranchCode()},
                true);
            
            WEB3AdminSrvRegiOtherOrgIdDownloadRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdDownloadRequest();
            WEB3SrvRegiSortKey l_srvRegiSortKey = new WEB3SrvRegiSortKey();
            l_srvRegiSortKey.keyItem = WEB3SrvRegiKeyItemDef.SEQUENCE_NUMBER;
            l_srvRegiSortKey.ascDesc = WEB3AscDescDef.ASC;
            l_request.sortKeys = new WEB3SrvRegiSortKey[]{l_srvRegiSortKey};

            l_request.seqNumber = null;
            l_request.serviceDiv = "12";
            l_request.branchCode =
                new String[]{l_mainAccountParams.getBranchCode()};
            l_request.accountCode =
                l_mainAccountParams.getAccountCode().substring(0, 6);
            l_request.status = "9";
            l_request.id = "87654321";
            l_request.appliStartFrom =
                WEB3DateUtility.getDate("20080327", "yyyyMMdd");
            l_request.appliStartTo =
                WEB3DateUtility.getDate("20080327", "yyyyMMdd");

            WEB3AdminSrvRegiOtherOrgIdDownloadResponse l_response =
                this.impl.getDownloadFile(l_request);

            assertEquals(2, l_response.lines.length);
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyy/MM/dd HH:mm:ss"),
                    l_response.lines[0]);
            assertEquals(
                "通番,ID,ステータス,証券会社コード,部店コード,口座コード,適用期間From,適用期間To",
                l_response.lines[1]);

            assertEquals(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()),
                WEB3DateUtility.toDay(l_response.currentDate));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * testExecuteCase0001
     */
    public void testExecuteCase0001()
    {
        final String STR_METHOD_NAME = "testExecuteCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.impl = new WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl();

            WEB3AdminSrvRegiOtherOrgIdDownloadRequest l_request = null;

            this.impl.execute(l_request);

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_exBE.getErrorInfo());
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * testExecuteCase0002
     */
    public void testExecuteCase0002()
    {
        final String STR_METHOD_NAME = "testExecuteCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.impl = new WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl();

            WEB3AdminSrvRegiOtherOrgIdListReferenceRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdListReferenceRequest();

            this.impl.execute(l_request);

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_exBE.getErrorInfo());
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * testExecuteCase0003
     */
    public void testExecuteCase0003()
    {
        final String STR_METHOD_NAME = "testExecuteCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.impl = new WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl();

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(l_branchParams.getBranchId());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(123L);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);

            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSequenceNumber(123L);
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_otherOrgInfoAdminParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_otherOrgInfoAdminParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setId("87654321");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("a");
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.getDate("20080327", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setCreatedTimestamp(
                Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
                Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(
                l_administrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_administrator,
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG,
                false,
                true);

            WEB3AdminSrvRegiOtherOrgIdDownloadRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdDownloadRequest();
            WEB3SrvRegiSortKey l_srvRegiSortKey = new WEB3SrvRegiSortKey();
            l_srvRegiSortKey.keyItem = WEB3SrvRegiKeyItemDef.SEQUENCE_NUMBER;
            l_srvRegiSortKey.ascDesc = WEB3AscDescDef.ASC;
            l_request.sortKeys = new WEB3SrvRegiSortKey[]{l_srvRegiSortKey};

            l_request.seqNumber = "123";
            l_request.serviceDiv = "12";
            l_request.branchCode =
                new String[]{l_mainAccountParams.getBranchCode()};
            l_request.accountCode =
                l_mainAccountParams.getAccountCode().substring(0, 6);
            l_request.status = "9";
            l_request.id = "87654321";
            l_request.appliStartFrom =
                WEB3DateUtility.getDate("20080327", "yyyyMMdd");
            l_request.appliStartTo =
                WEB3DateUtility.getDate("20080327", "yyyyMMdd");

            WEB3AdminSrvRegiOtherOrgIdDownloadResponse l_response =
                (WEB3AdminSrvRegiOtherOrgIdDownloadResponse)this.impl.execute(l_request);

            assertEquals(3, l_response.lines.length);
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyy/MM/dd HH:mm:ss"), l_response.lines[0]);
            assertEquals(
                "通番,ID,ステータス,証券会社コード,部店コード,口座コード,適用期間From,適用期間To",
                l_response.lines[1]);
            assertEquals(
                "123,87654321,9,0D,381,251224,20080327,20080327",
                l_response.lines[2]);

            assertEquals(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()),
                WEB3DateUtility.toDay(l_response.currentDate));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

}
@
