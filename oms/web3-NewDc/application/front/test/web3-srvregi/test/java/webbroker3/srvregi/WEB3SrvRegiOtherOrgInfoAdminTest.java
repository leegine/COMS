head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.43.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SrvRegiOtherOrgInfoAdminTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外部連携情報管理のテストクラス(WEB3SrvRegiOtherOrgInfoAdminTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/04 周墨洋 新規作成
Revision History : 2008/03/04 周墨洋 仕様変更・モテルNo.330, No.342
*/
package webbroker3.srvregi;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (外部連携情報管理のテストクラス)<BR>
 * 外部連携情報管理のテストクラス<BR>
 * <BR>
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3SrvRegiOtherOrgInfoAdminTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3SrvRegiOtherOrgInfoAdminTest.class);

    /**
     * 外部連携情報管理
     */
    private WEB3SrvRegiOtherOrgInfoAdmin srvRegiOtherOrgInfoAdmin;

    /**
     * @@param l_arg0
     * l_arg0
     */
    public WEB3SrvRegiOtherOrgInfoAdminTest(String l_arg0)
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
     * testValidateAppliRegistCase0001
     */
    public void testSetAndGetMethodCase0001()
    {
        final String STR_METHOD_NAME = "testSetAndGetMethodCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams();
        l_otherOrgInfoAdminParams.setSequenceNumber(123L);
        l_otherOrgInfoAdminParams.setSrvDiv("1234");
        l_otherOrgInfoAdminParams.setId("87654321");
        l_otherOrgInfoAdminParams.setPassword("********");
        l_otherOrgInfoAdminParams.setStatus("0");
        l_otherOrgInfoAdminParams.setInstitutionCode("0D");
        l_otherOrgInfoAdminParams.setBranchCode("381");
        l_otherOrgInfoAdminParams.setAccountCode("2512246");
        l_otherOrgInfoAdminParams.setAppliStartDate(
            WEB3DateUtility.getDate("20080305", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setAppliEndDate(
            WEB3DateUtility.getDate("20080306", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setLastUpdater("aa");
        l_otherOrgInfoAdminParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20080304", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20080304", "yyyyMMdd"));

        this.srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_otherOrgInfoAdminParams);

        srvRegiOtherOrgInfoAdmin.setId("01234567");
        srvRegiOtherOrgInfoAdmin.setPassword("(-_-)b");
        srvRegiOtherOrgInfoAdmin.setStatus("9");
        srvRegiOtherOrgInfoAdmin.setInstitutionCode("D0");
        srvRegiOtherOrgInfoAdmin.setBranchCode("183");
        srvRegiOtherOrgInfoAdmin.setAccountCode("6422152");
        srvRegiOtherOrgInfoAdmin.setAppliStartDate(
            new Timestamp(WEB3DateUtility.getDate("20080310", "yyyyMMdd").getTime()));
        srvRegiOtherOrgInfoAdmin.setAppliEndDate(
            new Timestamp(WEB3DateUtility.getDate("20080311", "yyyyMMdd").getTime()));

        assertEquals(123L, srvRegiOtherOrgInfoAdmin.getSequenceNumber());
        assertEquals("1234", srvRegiOtherOrgInfoAdmin.getSrvDiv());
        assertEquals("01234567", srvRegiOtherOrgInfoAdmin.getId());
        assertEquals("(-_-)b", srvRegiOtherOrgInfoAdmin.getPassword());
        assertEquals("9", srvRegiOtherOrgInfoAdmin.getStatus());
        assertEquals("D0", srvRegiOtherOrgInfoAdmin.getInstitutionCode());
        assertEquals("183", srvRegiOtherOrgInfoAdmin.getBranchCode());
        assertEquals("6422152", srvRegiOtherOrgInfoAdmin.getAccountCode());
        assertEquals(
            new Timestamp(WEB3DateUtility.getDate("20080310", "yyyyMMdd").getTime()),
            srvRegiOtherOrgInfoAdmin.getAppliStartDate());
        assertEquals(
            new Timestamp(WEB3DateUtility.getDate("20080311", "yyyyMMdd").getTime()),
            srvRegiOtherOrgInfoAdmin.getAppliEndDate());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * testCreateNewOtherOrgInfoAdminCase0001
     */
    public void testCreateNewOtherOrgInfoAdminCase0001()
    {
        final String STR_METHOD_NAME = "testCreateNewOtherOrgInfoAdminCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams();
        l_otherOrgInfoAdminParams.setSequenceNumber(123L);
        l_otherOrgInfoAdminParams.setSrvDiv("1234");
        l_otherOrgInfoAdminParams.setId("87654321");
        l_otherOrgInfoAdminParams.setPassword("********");
        l_otherOrgInfoAdminParams.setStatus("0");
        l_otherOrgInfoAdminParams.setInstitutionCode("0D");
        l_otherOrgInfoAdminParams.setBranchCode("381");
        l_otherOrgInfoAdminParams.setAccountCode("2512246");
        l_otherOrgInfoAdminParams.setAppliStartDate(
            WEB3DateUtility.getDate("20080305", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setAppliEndDate(
            WEB3DateUtility.getDate("20080306", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setLastUpdater("aa");
        l_otherOrgInfoAdminParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20080304", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20080304", "yyyyMMdd"));

        this.srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_otherOrgInfoAdminParams);

        long l_lngSequenceNumber = 987L;
        String l_strSrvDiv = "8765";
        WEB3SrvRegiOtherOrgInfoAdmin l_srvRegiOtherOrgInfoAdmin =
            this.srvRegiOtherOrgInfoAdmin.createNewOtherOrgInfoAdmin(
            l_lngSequenceNumber,
            l_strSrvDiv);

        assertEquals(987L, l_srvRegiOtherOrgInfoAdmin.getSequenceNumber());
        assertEquals("8765", l_srvRegiOtherOrgInfoAdmin.getSrvDiv());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * testSaveOtherOrgInfoAdminCase0001
     */
    public void testSaveOtherOrgInfoAdminCase0001()
    {
        final String STR_METHOD_NAME = "testSaveOtherOrgInfoAdminCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OtherOrgInfoAdminParams l_changeOtherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams();
        l_changeOtherOrgInfoAdminParams.setSequenceNumber(123L);
        l_changeOtherOrgInfoAdminParams.setSrvDiv("1234");
        l_changeOtherOrgInfoAdminParams.setId("87654321");
        l_changeOtherOrgInfoAdminParams.setPassword("(T_T)");
        l_changeOtherOrgInfoAdminParams.setStatus("9");
        l_changeOtherOrgInfoAdminParams.setInstitutionCode("D0");
        l_changeOtherOrgInfoAdminParams.setBranchCode("183");
        l_changeOtherOrgInfoAdminParams.setAccountCode("6422152");
        l_changeOtherOrgInfoAdminParams.setAppliStartDate(
            WEB3DateUtility.getDate("20080310", "yyyyMMdd"));
        l_changeOtherOrgInfoAdminParams.setAppliEndDate(
            WEB3DateUtility.getDate("20080311", "yyyyMMdd"));
        l_changeOtherOrgInfoAdminParams.setLastUpdater("aa");

        this.srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_changeOtherOrgInfoAdminParams);

        try
        {
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchParams.TYPE);
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            l_subAccountParams.setAccountId(
                l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(
                l_branchParams.getBranchId());
            l_subAccountParams.setInstitutionId(
                l_mainAccountParams.getInstitutionId());
            l_subAccountParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            l_administratorParams.setBranchId(
                l_branchParams.getBranchId());
            l_administratorParams.setInstitutionId(
                l_mainAccountParams.getInstitutionId());
            l_administratorParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_administratorParams.setTradingPassword("123");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);

            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoForTest());

            this.srvRegiOtherOrgInfoAdmin.saveOtherOrgInfoAdmin();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult =
                l_queryProcessor.doFindAllQuery(OtherOrgInfoAdminParams.TYPE);

            assertEquals(0, l_lisResult.size());
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            fail();
        }
        finally
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }

    /**
     * testSaveOtherOrgInfoAdminCase0002
     */
    public void testSaveOtherOrgInfoAdminCase0002()
    {
        final String STR_METHOD_NAME = "testSaveOtherOrgInfoAdminCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OtherOrgInfoAdminParams l_changeOtherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams();
        l_changeOtherOrgInfoAdminParams.setSequenceNumber(123L);
        l_changeOtherOrgInfoAdminParams.setSrvDiv("1234");
        l_changeOtherOrgInfoAdminParams.setId("87654321");
        l_changeOtherOrgInfoAdminParams.setPassword("(T_T)");
        l_changeOtherOrgInfoAdminParams.setStatus("9");
        l_changeOtherOrgInfoAdminParams.setInstitutionCode("D0");
        l_changeOtherOrgInfoAdminParams.setBranchCode("183");
        l_changeOtherOrgInfoAdminParams.setAccountCode("6422152");
        l_changeOtherOrgInfoAdminParams.setAppliStartDate(
            WEB3DateUtility.getDate("20080310", "yyyyMMdd"));
        l_changeOtherOrgInfoAdminParams.setAppliEndDate(
            WEB3DateUtility.getDate("20080311", "yyyyMMdd"));
        l_changeOtherOrgInfoAdminParams.setLastUpdater("aa");

        this.srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_changeOtherOrgInfoAdminParams);

        try
        {
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchParams.TYPE);
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            l_subAccountParams.setAccountId(
                l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(
                l_branchParams.getBranchId());
            l_subAccountParams.setInstitutionId(
                l_mainAccountParams.getInstitutionId());
            l_subAccountParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            l_administratorParams.setBranchId(
                l_branchParams.getBranchId());
            l_administratorParams.setInstitutionId(
                l_mainAccountParams.getInstitutionId());
            l_administratorParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_administratorParams.setTradingPassword("123");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSequenceNumber(123L);
            l_otherOrgInfoAdminParams.setSrvDiv("1234");
            l_otherOrgInfoAdminParams.setId("01234567");
            l_otherOrgInfoAdminParams.setPassword("********");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("381");
            l_otherOrgInfoAdminParams.setAccountCode("2512246");
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.getDate("20080305", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.getDate("20080306", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setLastUpdater("aa");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(
                WEB3DateUtility.getDate("20080304", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20080304", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            MOCK_MANAGER.setIsMockUsed(true);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            this.srvRegiOtherOrgInfoAdmin.saveOtherOrgInfoAdmin();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult =
                l_queryProcessor.doFindAllQuery(OtherOrgInfoAdminParams.TYPE);

            assertEquals(1, l_lisResult.size());
            OtherOrgInfoAdminParams l_resultOtherOrgInfoAdminParams =
                (OtherOrgInfoAdminParams)l_lisResult.get(0);

            assertEquals(123L, l_resultOtherOrgInfoAdminParams.getSequenceNumber());
            assertEquals("1234", l_resultOtherOrgInfoAdminParams.getSrvDiv());
            assertEquals("87654321", l_resultOtherOrgInfoAdminParams.getId());
            assertEquals("(T_T)", l_resultOtherOrgInfoAdminParams.getPassword());
            assertEquals("9", l_resultOtherOrgInfoAdminParams.getStatus());
            assertEquals("D0", l_resultOtherOrgInfoAdminParams.getInstitutionCode());
            assertEquals("183", l_resultOtherOrgInfoAdminParams.getBranchCode());
            assertEquals("6422152", l_resultOtherOrgInfoAdminParams.getAccountCode());
            assertEquals("330001", l_resultOtherOrgInfoAdminParams.getLastUpdater());
            assertEquals(
                new Timestamp(WEB3DateUtility.getDate("20080310", "yyyyMMdd").getTime()),
                l_resultOtherOrgInfoAdminParams.getAppliStartDate());
            assertEquals(
                new Timestamp(WEB3DateUtility.getDate("20080311", "yyyyMMdd").getTime()),
                l_resultOtherOrgInfoAdminParams.getAppliEndDate());
            assertEquals(
                new Timestamp(WEB3DateUtility.getDate("20080304", "yyyyMMdd").getTime()),
                l_resultOtherOrgInfoAdminParams.getCreatedTimestamp());
            log.debug("======>>>>>>>"+ WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()));
            log.debug("======>>>>>>>"+ l_resultOtherOrgInfoAdminParams.getLastUpdatedTimestamp());
            
            assertEquals(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()),
                WEB3DateUtility.toDay(l_resultOtherOrgInfoAdminParams.getLastUpdatedTimestamp()));
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            fail();
        }
        finally
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }

    /**
     * testSaveOtherOrgInfoAdminCase0003
     */
    public void testSaveOtherOrgInfoAdminCase0003()
    {
        final String STR_METHOD_NAME = "testSaveOtherOrgInfoAdminCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OtherOrgInfoAdminParams l_changeOtherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams();
        l_changeOtherOrgInfoAdminParams.setSequenceNumber(123L);
        l_changeOtherOrgInfoAdminParams.setSrvDiv("1234");
        l_changeOtherOrgInfoAdminParams.setId("87654321");
        l_changeOtherOrgInfoAdminParams.setPassword("(T_T)");
        l_changeOtherOrgInfoAdminParams.setStatus("9");
        l_changeOtherOrgInfoAdminParams.setInstitutionCode("D0");
        l_changeOtherOrgInfoAdminParams.setBranchCode("183");
        l_changeOtherOrgInfoAdminParams.setAccountCode("6422152");
        l_changeOtherOrgInfoAdminParams.setAppliStartDate(
            WEB3DateUtility.getDate("20080310", "yyyyMMdd"));
        l_changeOtherOrgInfoAdminParams.setAppliEndDate(
            WEB3DateUtility.getDate("20080311", "yyyyMMdd"));
        l_changeOtherOrgInfoAdminParams.setLastUpdater("aa");

        this.srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_changeOtherOrgInfoAdminParams);

        try
        {
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchParams.TYPE);
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            l_subAccountParams.setAccountId(
                l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(
                l_branchParams.getBranchId());
            l_subAccountParams.setInstitutionId(
                l_mainAccountParams.getInstitutionId());
            l_subAccountParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSequenceNumber(123L);
            l_otherOrgInfoAdminParams.setSrvDiv("1234");
            l_otherOrgInfoAdminParams.setId("01234567");
            l_otherOrgInfoAdminParams.setPassword("********");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("381");
            l_otherOrgInfoAdminParams.setAccountCode("2512246");
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.getDate("20080305", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.getDate("20080306", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setLastUpdater("aa");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(
                WEB3DateUtility.getDate("20080304", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20080304", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoForTest());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            this.srvRegiOtherOrgInfoAdmin.saveOtherOrgInfoAdmin();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult =
                l_queryProcessor.doFindAllQuery(OtherOrgInfoAdminParams.TYPE);

            assertEquals(1, l_lisResult.size());
            OtherOrgInfoAdminParams l_resultOtherOrgInfoAdminParams =
                (OtherOrgInfoAdminParams)l_lisResult.get(0);

            assertEquals(123L, l_resultOtherOrgInfoAdminParams.getSequenceNumber());
            assertEquals("1234", l_resultOtherOrgInfoAdminParams.getSrvDiv());
            assertEquals("87654321", l_resultOtherOrgInfoAdminParams.getId());
            assertEquals("(T_T)", l_resultOtherOrgInfoAdminParams.getPassword());
            assertEquals("9", l_resultOtherOrgInfoAdminParams.getStatus());
            assertEquals("D0", l_resultOtherOrgInfoAdminParams.getInstitutionCode());
            assertEquals("183", l_resultOtherOrgInfoAdminParams.getBranchCode());
            assertEquals("6422152", l_resultOtherOrgInfoAdminParams.getAccountCode());
            assertEquals("251224", l_resultOtherOrgInfoAdminParams.getLastUpdater());
            assertEquals(
                new Timestamp(WEB3DateUtility.getDate("20080310", "yyyyMMdd").getTime()),
                l_resultOtherOrgInfoAdminParams.getAppliStartDate());
            assertEquals(
                new Timestamp(WEB3DateUtility.getDate("20080311", "yyyyMMdd").getTime()),
                l_resultOtherOrgInfoAdminParams.getAppliEndDate());
            assertEquals(
                new Timestamp(WEB3DateUtility.getDate("20080304", "yyyyMMdd").getTime()),
                l_resultOtherOrgInfoAdminParams.getCreatedTimestamp());
            assertEquals(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()),
                WEB3DateUtility.toDay(l_resultOtherOrgInfoAdminParams.getLastUpdatedTimestamp()));
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            fail();
        }
        finally
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }

    /**
     * testSaveOtherOrgInfoAdminCase0004
     */
    public void testSaveOtherOrgInfoAdminCase0004()
    {
        final String STR_METHOD_NAME = "testSaveOtherOrgInfoAdminCase0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OtherOrgInfoAdminParams l_changeOtherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams();
        l_changeOtherOrgInfoAdminParams.setSequenceNumber(123L);
        l_changeOtherOrgInfoAdminParams.setSrvDiv("1234");
        l_changeOtherOrgInfoAdminParams.setId("87654321");
        l_changeOtherOrgInfoAdminParams.setPassword("(T_T)");
        l_changeOtherOrgInfoAdminParams.setStatus("9");
        l_changeOtherOrgInfoAdminParams.setInstitutionCode("D0");
        l_changeOtherOrgInfoAdminParams.setBranchCode("183");
        l_changeOtherOrgInfoAdminParams.setAccountCode("6422152");
        l_changeOtherOrgInfoAdminParams.setAppliStartDate(
            WEB3DateUtility.getDate("20080310", "yyyyMMdd"));
        l_changeOtherOrgInfoAdminParams.setAppliEndDate(
            WEB3DateUtility.getDate("20080311", "yyyyMMdd"));
        l_changeOtherOrgInfoAdminParams.setLastUpdater("aa");

        this.srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_changeOtherOrgInfoAdminParams);

        try
        {
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchParams.TYPE);
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            l_subAccountParams.setAccountId(
                l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(
                l_branchParams.getBranchId());
            l_subAccountParams.setInstitutionId(
                l_mainAccountParams.getInstitutionId());
            l_subAccountParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSequenceNumber(123L);
            l_otherOrgInfoAdminParams.setSrvDiv("1234");
            l_otherOrgInfoAdminParams.setId("01234567");
            l_otherOrgInfoAdminParams.setPassword("********");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("381");
            l_otherOrgInfoAdminParams.setAccountCode("2512246");
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.getDate("20080305", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.getDate("20080306", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setLastUpdater("aa");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(
                WEB3DateUtility.getDate("20080304", "yyyyMMdd"));
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20080304", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoForTest());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long("333812512246"));

            this.srvRegiOtherOrgInfoAdmin.saveOtherOrgInfoAdmin();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult =
                l_queryProcessor.doFindAllQuery(OtherOrgInfoAdminParams.TYPE);

            assertEquals(1, l_lisResult.size());
            OtherOrgInfoAdminParams l_resultOtherOrgInfoAdminParams =
                (OtherOrgInfoAdminParams)l_lisResult.get(0);

            assertEquals(123L, l_resultOtherOrgInfoAdminParams.getSequenceNumber());
            assertEquals("1234", l_resultOtherOrgInfoAdminParams.getSrvDiv());
            assertEquals("87654321", l_resultOtherOrgInfoAdminParams.getId());
            assertEquals("(T_T)", l_resultOtherOrgInfoAdminParams.getPassword());
            assertEquals("9", l_resultOtherOrgInfoAdminParams.getStatus());
            assertEquals("D0", l_resultOtherOrgInfoAdminParams.getInstitutionCode());
            assertEquals("183", l_resultOtherOrgInfoAdminParams.getBranchCode());
            assertEquals("6422152", l_resultOtherOrgInfoAdminParams.getAccountCode());
            assertEquals("251224", l_resultOtherOrgInfoAdminParams.getLastUpdater());
            assertEquals(
                new Timestamp(WEB3DateUtility.getDate("20080310", "yyyyMMdd").getTime()),
                l_resultOtherOrgInfoAdminParams.getAppliStartDate());
            assertEquals(
                new Timestamp(WEB3DateUtility.getDate("20080311", "yyyyMMdd").getTime()),
                l_resultOtherOrgInfoAdminParams.getAppliEndDate());
            assertEquals(
                new Timestamp(WEB3DateUtility.getDate("20080304", "yyyyMMdd").getTime()),
                l_resultOtherOrgInfoAdminParams.getCreatedTimestamp());
            assertEquals(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()),
                WEB3DateUtility.toDay(l_resultOtherOrgInfoAdminParams.getLastUpdatedTimestamp()));
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            fail();
        }
        finally
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }

    /**
     * testSaveNewOtherOrgInfoAdminCase0001
     */
    public void testSaveNewOtherOrgInfoAdminCase0001()
    {
        final String STR_METHOD_NAME = "testSaveNewOtherOrgInfoAdminCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams();
        l_otherOrgInfoAdminParams.setSequenceNumber(123L);
        l_otherOrgInfoAdminParams.setSrvDiv("1234");
        l_otherOrgInfoAdminParams.setId("87654321");
        l_otherOrgInfoAdminParams.setPassword("(-_-)b");
        l_otherOrgInfoAdminParams.setStatus("0");
        l_otherOrgInfoAdminParams.setInstitutionCode("0D");
        l_otherOrgInfoAdminParams.setBranchCode("381");
        l_otherOrgInfoAdminParams.setAccountCode("2512246");
        l_otherOrgInfoAdminParams.setAppliStartDate(
            WEB3DateUtility.getDate("20080305", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setAppliEndDate(
            WEB3DateUtility.getDate("20080306", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setLastUpdater("aa");
        l_otherOrgInfoAdminParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20080304", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20080304", "yyyyMMdd"));

        this.srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_otherOrgInfoAdminParams);

        try
        {
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchParams.TYPE);
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            l_subAccountParams.setAccountId(
                l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(
                l_branchParams.getBranchId());
            l_subAccountParams.setInstitutionId(
                l_mainAccountParams.getInstitutionId());
            l_subAccountParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            l_administratorParams.setBranchId(
                l_branchParams.getBranchId());
            l_administratorParams.setInstitutionId(
                l_mainAccountParams.getInstitutionId());
            l_administratorParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_administratorParams.setTradingPassword("123");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);

            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            MOCK_MANAGER.setIsMockUsed(true);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            this.srvRegiOtherOrgInfoAdmin.saveNewOtherOrgInfoAdmin();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult =
                l_queryProcessor.doFindAllQuery(OtherOrgInfoAdminParams.TYPE);

            assertEquals(1, l_lisResult.size());
            OtherOrgInfoAdminParams l_resultOtherOrgInfoAdminParams =
                (OtherOrgInfoAdminParams)l_lisResult.get(0);

            assertEquals(123L, l_resultOtherOrgInfoAdminParams.getSequenceNumber());
            assertEquals("1234", l_resultOtherOrgInfoAdminParams.getSrvDiv());
            assertEquals("87654321", l_resultOtherOrgInfoAdminParams.getId());
            assertEquals("(-_-)b", l_resultOtherOrgInfoAdminParams.getPassword());
            assertEquals("0", l_resultOtherOrgInfoAdminParams.getStatus());
            assertEquals("0D", l_resultOtherOrgInfoAdminParams.getInstitutionCode());
            assertEquals("381", l_resultOtherOrgInfoAdminParams.getBranchCode());
            assertEquals("2512246", l_resultOtherOrgInfoAdminParams.getAccountCode());
            assertEquals("330001", l_resultOtherOrgInfoAdminParams.getLastUpdater());
            assertEquals(
                new Timestamp(WEB3DateUtility.getDate("20080305", "yyyyMMdd").getTime()),
                l_resultOtherOrgInfoAdminParams.getAppliStartDate());
            assertEquals(
                new Timestamp(WEB3DateUtility.getDate("20080306", "yyyyMMdd").getTime()),
                l_resultOtherOrgInfoAdminParams.getAppliEndDate());
            assertEquals(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()),
                WEB3DateUtility.toDay(l_resultOtherOrgInfoAdminParams.getCreatedTimestamp()));
            assertEquals(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()),
                WEB3DateUtility.toDay(l_resultOtherOrgInfoAdminParams.getLastUpdatedTimestamp()));
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            fail();
        }
        finally
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }

    /**
     * testSaveNewOtherOrgInfoAdminCase0002
     */
    public void testSaveNewOtherOrgInfoAdminCase0002()
    {
        final String STR_METHOD_NAME = "testSaveNewOtherOrgInfoAdminCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams();
        l_otherOrgInfoAdminParams.setSequenceNumber(123L);
        l_otherOrgInfoAdminParams.setSrvDiv("1234");
        l_otherOrgInfoAdminParams.setId("87654321");
        l_otherOrgInfoAdminParams.setPassword("(-_-)b");
        l_otherOrgInfoAdminParams.setStatus("0");
        l_otherOrgInfoAdminParams.setInstitutionCode("0D");
        l_otherOrgInfoAdminParams.setBranchCode("381");
        l_otherOrgInfoAdminParams.setAccountCode("2512246");
        l_otherOrgInfoAdminParams.setAppliStartDate(
            WEB3DateUtility.getDate("20080305", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setAppliEndDate(
            WEB3DateUtility.getDate("20080306", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setLastUpdater("aa");
        l_otherOrgInfoAdminParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20080304", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20080304", "yyyyMMdd"));

        this.srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_otherOrgInfoAdminParams);

        try
        {
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchParams.TYPE);
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            l_subAccountParams.setAccountId(
                l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(
                l_branchParams.getBranchId());
            l_subAccountParams.setInstitutionId(
                l_mainAccountParams.getInstitutionId());
            l_subAccountParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);

            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoForTest());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            this.srvRegiOtherOrgInfoAdmin.saveNewOtherOrgInfoAdmin();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult =
                l_queryProcessor.doFindAllQuery(OtherOrgInfoAdminParams.TYPE);

            assertEquals(1, l_lisResult.size());
            OtherOrgInfoAdminParams l_resultOtherOrgInfoAdminParams =
                (OtherOrgInfoAdminParams)l_lisResult.get(0);

            assertEquals(123L, l_resultOtherOrgInfoAdminParams.getSequenceNumber());
            assertEquals("1234", l_resultOtherOrgInfoAdminParams.getSrvDiv());
            assertEquals("87654321", l_resultOtherOrgInfoAdminParams.getId());
            assertEquals("(-_-)b", l_resultOtherOrgInfoAdminParams.getPassword());
            assertEquals("0", l_resultOtherOrgInfoAdminParams.getStatus());
            assertEquals("0D", l_resultOtherOrgInfoAdminParams.getInstitutionCode());
            assertEquals("381", l_resultOtherOrgInfoAdminParams.getBranchCode());
            assertEquals("2512246", l_resultOtherOrgInfoAdminParams.getAccountCode());
            assertEquals("251224", l_resultOtherOrgInfoAdminParams.getLastUpdater());
            assertEquals(
                new Timestamp(WEB3DateUtility.getDate("20080305", "yyyyMMdd").getTime()),
                l_resultOtherOrgInfoAdminParams.getAppliStartDate());
            assertEquals(
                new Timestamp(WEB3DateUtility.getDate("20080306", "yyyyMMdd").getTime()),
                l_resultOtherOrgInfoAdminParams.getAppliEndDate());
            assertEquals(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()),
                WEB3DateUtility.toDay(l_resultOtherOrgInfoAdminParams.getCreatedTimestamp()));
            assertEquals(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()),
                WEB3DateUtility.toDay(l_resultOtherOrgInfoAdminParams.getLastUpdatedTimestamp()));
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            fail();
        }
        finally
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }

    /**
     * testSaveNewOtherOrgInfoAdminCase0003
     */
    public void testSaveNewOtherOrgInfoAdminCase0003()
    {
        final String STR_METHOD_NAME = "testSaveNewOtherOrgInfoAdminCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams();
        l_otherOrgInfoAdminParams.setSequenceNumber(123L);
        l_otherOrgInfoAdminParams.setSrvDiv("1234");
        l_otherOrgInfoAdminParams.setId("87654321");
        l_otherOrgInfoAdminParams.setPassword("(-_-)b");
        l_otherOrgInfoAdminParams.setStatus("0");
        l_otherOrgInfoAdminParams.setInstitutionCode("0D");
        l_otherOrgInfoAdminParams.setBranchCode("381");
        l_otherOrgInfoAdminParams.setAccountCode("2512246");
        l_otherOrgInfoAdminParams.setAppliStartDate(
            WEB3DateUtility.getDate("20080305", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setAppliEndDate(
            WEB3DateUtility.getDate("20080306", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setLastUpdater("aa");
        l_otherOrgInfoAdminParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20080304", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20080304", "yyyyMMdd"));

        this.srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_otherOrgInfoAdminParams);

        try
        {
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchParams.TYPE);
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            l_subAccountParams.setAccountId(
                l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(
                l_branchParams.getBranchId());
            l_subAccountParams.setInstitutionId(
                l_mainAccountParams.getInstitutionId());
            l_subAccountParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);

            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoForTest());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            this.srvRegiOtherOrgInfoAdmin.saveNewOtherOrgInfoAdmin();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult =
                l_queryProcessor.doFindAllQuery(OtherOrgInfoAdminParams.TYPE);

            assertEquals(1, l_lisResult.size());
            OtherOrgInfoAdminParams l_resultOtherOrgInfoAdminParams =
                (OtherOrgInfoAdminParams)l_lisResult.get(0);

            assertEquals(123L, l_resultOtherOrgInfoAdminParams.getSequenceNumber());
            assertEquals("1234", l_resultOtherOrgInfoAdminParams.getSrvDiv());
            assertEquals("87654321", l_resultOtherOrgInfoAdminParams.getId());
            assertEquals("(-_-)b", l_resultOtherOrgInfoAdminParams.getPassword());
            assertEquals("0", l_resultOtherOrgInfoAdminParams.getStatus());
            assertEquals("0D", l_resultOtherOrgInfoAdminParams.getInstitutionCode());
            assertEquals("381", l_resultOtherOrgInfoAdminParams.getBranchCode());
            assertEquals("2512246", l_resultOtherOrgInfoAdminParams.getAccountCode());
            assertEquals("251224", l_resultOtherOrgInfoAdminParams.getLastUpdater());
            assertEquals(
                new Timestamp(WEB3DateUtility.getDate("20080305", "yyyyMMdd").getTime()),
                l_resultOtherOrgInfoAdminParams.getAppliStartDate());
            assertEquals(
                new Timestamp(WEB3DateUtility.getDate("20080306", "yyyyMMdd").getTime()),
                l_resultOtherOrgInfoAdminParams.getAppliEndDate());
            assertEquals(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()),
                WEB3DateUtility.toDay(l_resultOtherOrgInfoAdminParams.getCreatedTimestamp()));
            assertEquals(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()),
                WEB3DateUtility.toDay(l_resultOtherOrgInfoAdminParams.getLastUpdatedTimestamp()));
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            fail();
        }
        finally
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }

    /**
     * testSaveNewOtherOrgInfoAdminCase0004
     */
    public void testSaveNewOtherOrgInfoAdminCase0004()
    {
        final String STR_METHOD_NAME = "testSaveNewOtherOrgInfoAdminCase0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams();
        l_otherOrgInfoAdminParams.setSequenceNumber(123L);
        l_otherOrgInfoAdminParams.setSrvDiv("1234");
        l_otherOrgInfoAdminParams.setId("87654321");
        l_otherOrgInfoAdminParams.setPassword("(-_-)b");
        l_otherOrgInfoAdminParams.setStatus("0");
        l_otherOrgInfoAdminParams.setInstitutionCode("0D");
        l_otherOrgInfoAdminParams.setBranchCode("381");
        l_otherOrgInfoAdminParams.setAccountCode("2512246");
        l_otherOrgInfoAdminParams.setAppliStartDate(
            WEB3DateUtility.getDate("20080305", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setAppliEndDate(
            WEB3DateUtility.getDate("20080306", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setLastUpdater("aa");
        l_otherOrgInfoAdminParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20080304", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20080304", "yyyyMMdd"));

        this.srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_otherOrgInfoAdminParams);

        try
        {
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchParams.TYPE);
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            l_institutionParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            l_subAccountParams.setAccountId(
                l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(
                l_branchParams.getBranchId());
            l_subAccountParams.setInstitutionId(
                l_mainAccountParams.getInstitutionId());
            l_subAccountParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoForTest());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            this.srvRegiOtherOrgInfoAdmin.saveNewOtherOrgInfoAdmin();

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_exBE.getErrorInfo());
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            fail();
        }
        finally
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }

    /**
     * testIsStatusChangeableCase0001
     */
    public void testIsStatusChangeableCase0001()
    {
        final String STR_METHOD_NAME = "testIsStatusChangeableCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams();
        this.srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_otherOrgInfoAdminParams);

        try
        {

            Field l_field =
                WEB3SrvRegiOtherOrgInfoAdmin.class.getDeclaredField(
                    "otherOrgInfoAdminParams");
            l_field.setAccessible(true);
            l_field.set(this.srvRegiOtherOrgInfoAdmin, null);

            String l_strStatus = "5";
            boolean l_blnIsStatusChangeable =
                this.srvRegiOtherOrgInfoAdmin.isStatusChangeable(
                    l_strStatus);

            assertEquals(false, l_blnIsStatusChangeable);
        }
        catch (NoSuchFieldException l_exNSFE)
        {
            log.debug(STR_METHOD_NAME, l_exNSFE);
            fail();
        }
        catch (IllegalAccessException l_exIAE)
        {
            log.debug(STR_METHOD_NAME, l_exIAE);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            fail();
        }
        finally
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }

    /**
     * testIsStatusChangeableCase0002
     */
    public void testIsStatusChangeableCase0002()
    {
        final String STR_METHOD_NAME = "testIsStatusChangeableCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams();
        l_otherOrgInfoAdminParams.setSequenceNumber(123L);
        l_otherOrgInfoAdminParams.setSrvDiv("1234");
        l_otherOrgInfoAdminParams.setId("87654321");
        l_otherOrgInfoAdminParams.setPassword("********");
        l_otherOrgInfoAdminParams.setStatus("0");
        l_otherOrgInfoAdminParams.setInstitutionCode("0D");
        l_otherOrgInfoAdminParams.setBranchCode("381");
        l_otherOrgInfoAdminParams.setAccountCode("2512246");
        l_otherOrgInfoAdminParams.setAppliStartDate(
            WEB3DateUtility.getDate("20080305", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setAppliEndDate(
            WEB3DateUtility.getDate("20080306", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setLastUpdater("aa");
        l_otherOrgInfoAdminParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20080304", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20080304", "yyyyMMdd"));

        this.srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_otherOrgInfoAdminParams);

        try
        {
            String l_strStatus = "0";
            boolean l_blnIsStatusChangeable =
                this.srvRegiOtherOrgInfoAdmin.isStatusChangeable(
                    l_strStatus);

            assertEquals(false, l_blnIsStatusChangeable);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            fail();
        }
        finally
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }

    /**
     * testIsStatusChangeableCase0003
     */
    public void testIsStatusChangeableCase0003()
    {
        final String STR_METHOD_NAME = "testIsStatusChangeableCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams();
        l_otherOrgInfoAdminParams.setSequenceNumber(123L);
        l_otherOrgInfoAdminParams.setSrvDiv("1234");
        l_otherOrgInfoAdminParams.setId("87654321");
        l_otherOrgInfoAdminParams.setPassword("********");
        l_otherOrgInfoAdminParams.setStatus("0");
        l_otherOrgInfoAdminParams.setInstitutionCode("0D");
        l_otherOrgInfoAdminParams.setBranchCode("381");
        l_otherOrgInfoAdminParams.setAccountCode("2512246");
        l_otherOrgInfoAdminParams.setAppliStartDate(
            WEB3DateUtility.getDate("20080305", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setAppliEndDate(
            WEB3DateUtility.getDate("20080306", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setLastUpdater("aa");
        l_otherOrgInfoAdminParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20080304", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20080304", "yyyyMMdd"));

        this.srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_otherOrgInfoAdminParams);

        try
        {
            String l_strStatus = "1";
            boolean l_blnIsStatusChangeable =
                this.srvRegiOtherOrgInfoAdmin.isStatusChangeable(
                    l_strStatus);

            assertEquals(true, l_blnIsStatusChangeable);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            fail();
        }
        finally
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }

    /**
     * testIsStatusChangeableCase0004
     */
    public void testIsStatusChangeableCase0004()
    {
        final String STR_METHOD_NAME = "testIsStatusChangeableCase0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams();
        l_otherOrgInfoAdminParams.setSequenceNumber(123L);
        l_otherOrgInfoAdminParams.setSrvDiv("1234");
        l_otherOrgInfoAdminParams.setId("87654321");
        l_otherOrgInfoAdminParams.setPassword("********");
        l_otherOrgInfoAdminParams.setStatus("9");
        l_otherOrgInfoAdminParams.setInstitutionCode("0D");
        l_otherOrgInfoAdminParams.setBranchCode("381");
        l_otherOrgInfoAdminParams.setAccountCode("2512246");
        l_otherOrgInfoAdminParams.setAppliStartDate(
            WEB3DateUtility.getDate("20080305", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setAppliEndDate(
            WEB3DateUtility.getDate("20080306", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setLastUpdater("aa");
        l_otherOrgInfoAdminParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20080304", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20080304", "yyyyMMdd"));

        this.srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_otherOrgInfoAdminParams);

        try
        {
            String l_strStatus = "0";
            boolean l_blnIsStatusChangeable =
                this.srvRegiOtherOrgInfoAdmin.isStatusChangeable(
                    l_strStatus);

            assertEquals(true, l_blnIsStatusChangeable);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            fail();
        }
        finally
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }

    /**
     * testIsStatusChangeableCase0005
     */
    public void testIsStatusChangeableCase0005()
    {
        final String STR_METHOD_NAME = "testIsStatusChangeableCase0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams();
        l_otherOrgInfoAdminParams.setSequenceNumber(123L);
        l_otherOrgInfoAdminParams.setSrvDiv("1234");
        l_otherOrgInfoAdminParams.setId("87654321");
        l_otherOrgInfoAdminParams.setPassword("********");
        l_otherOrgInfoAdminParams.setStatus("3");
        l_otherOrgInfoAdminParams.setInstitutionCode("0D");
        l_otherOrgInfoAdminParams.setBranchCode("381");
        l_otherOrgInfoAdminParams.setAccountCode("2512246");
        l_otherOrgInfoAdminParams.setAppliStartDate(
            WEB3DateUtility.getDate("20080305", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setAppliEndDate(
            WEB3DateUtility.getDate("20080306", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setLastUpdater("aa");
        l_otherOrgInfoAdminParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20080304", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20080304", "yyyyMMdd"));

        this.srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_otherOrgInfoAdminParams);

        try
        {
            String l_strStatus = "0";
            this.srvRegiOtherOrgInfoAdmin.isStatusChangeable(l_strStatus);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            assertEquals(
                WEB3ErrorCatalog.BUSINESS_ERROR_03050,
                l_exBE.getErrorInfo());
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            fail();
        }
        finally
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }

    /**
     * testIsStatusChangeableCase0006
     */
    public void testIsStatusChangeableCase0006()
    {
        final String STR_METHOD_NAME = "testIsStatusChangeableCase0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams();
        l_otherOrgInfoAdminParams.setSequenceNumber(123L);
        l_otherOrgInfoAdminParams.setSrvDiv("1234");
        l_otherOrgInfoAdminParams.setId("87654321");
        l_otherOrgInfoAdminParams.setPassword("********");
        l_otherOrgInfoAdminParams.setStatus("0");
        l_otherOrgInfoAdminParams.setInstitutionCode("0D");
        l_otherOrgInfoAdminParams.setBranchCode("381");
        l_otherOrgInfoAdminParams.setAccountCode("2512246");
        l_otherOrgInfoAdminParams.setAppliStartDate(
            WEB3DateUtility.getDate("20080305", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setAppliEndDate(
            WEB3DateUtility.getDate("20080306", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setLastUpdater("aa");
        l_otherOrgInfoAdminParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20080304", "yyyyMMdd"));
        l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20080304", "yyyyMMdd"));

        this.srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_otherOrgInfoAdminParams);

        try
        {
            String l_strStatus = "3";
            this.srvRegiOtherOrgInfoAdmin.isStatusChangeable(l_strStatus);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            assertEquals(
                WEB3ErrorCatalog.BUSINESS_ERROR_03050,
                l_exBE.getErrorInfo());
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            fail();
        }
        finally
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }

    /**
     *
     */
    public class LoginInfoForTest extends LoginInfoImpl
    {
        /**
         *
         * @@return l_lngLoginId
         */
        public long getLoginId()
        {
            long l_lngLoginId = 33381330003L;
            return l_lngLoginId;
        }
    }

}
@
