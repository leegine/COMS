head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.51.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiAccountDataUploadUnitServiceImplTest_updateSrvApplyAttribute.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3AdminSrvRegiAccountDataUploadUnitServiceImplTest_updateSrvApplyAttribute.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/06/13 崔遠鵬(中訊) 新規作成
 */
package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.WEB3AdminSrvRegiAccountDataUploadCsv;
import webbroker3.srvregi.WEB3SrvRegiNewAppliSpec;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.data.SrvAppliAttributeParams;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.define.WEB3SrvRegiAppliLotDivDef;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiOtherOrgServiceImplTest.LoginInfoTest;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiAccountDataUploadUnitServiceImplTest_updateSrvApplyAttribute extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiAccountDataUploadUnitServiceImplTest_updateSrvApplyAttribute.class);

    WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl impl = new WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl();

    public WEB3AdminSrvRegiAccountDataUploadUnitServiceImplTest_updateSrvApplyAttribute(String arg0)
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

    public void testUpdateSrvApplyAttribute01()
    {
        final String STR_METHOD_NAME = "testUpdateSrvApplyAttribute01()";
        log.entering(TEST_START + STR_METHOD_NAME);

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

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("338");
            l_mainAccountParams.setBranchCode("A01");
            l_mainAccountParams.setAccountCode("1234567");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);
            l_srvAppliAttributeParams = TestDBUtility.getSrvAppliAttributeRow();
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);

            AdministratorParams l_administratorParams = new AdministratorParams();
            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
            l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 12);
            Timestamp l_tsAppliStartDate = new Timestamp(l_calendar.getTimeInMillis());
            l_calendar.set(2007, 6-1, 13);
            Timestamp l_tsAppliEndDate = new Timestamp(l_calendar.getTimeInMillis());

            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);

            impl.updateSrvApplyAttribute("338", "A01", "1234567", "1234", "2", l_tsAppliStartDate, l_tsAppliEndDate);

            List l_lisRows =
                l_processor.doFindAllQuery(
                SrvAppliAttributeRow.TYPE,
                "",
                "",
                null,
                new Object[]{});
            SrvAppliAttributeRow l_srvAppliAttributeRow = (SrvAppliAttributeRow)l_lisRows.get(0);
            assertEquals(l_srvAppliAttributeRow.getAppliAttribute(), "2");

            l_calendar.set(2007, 6-1, 12);
            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            assertEquals(WEB3DateUtility.compareToDay(l_srvAppliAttributeRow.getAppliStartDate(), l_tsAppliyDate), 0);

            l_calendar.set(2007, 6-1, 13);
            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            assertEquals(WEB3DateUtility.compareToDay(l_srvAppliAttributeRow.getAppliEndDate(), l_tsAppliyDate), 0);

            assertEquals(l_srvAppliAttributeRow.getLastUpdater(), "330001");

            l_calendar.set(2007, 6-1, 14);
            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            assertEquals(WEB3DateUtility.compareToDay(l_srvAppliAttributeRow.getLastUpdatedTimestamp(), l_tsAppliyDate), 0);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testUpdateAppliRegist01()
    {
        final String STR_METHOD_NAME = "testUpdateAppliRegist01()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                "submitServiceRegist",
                new Class[]
                { WEB3SrvRegiNewAppliSpec.class, Long.class },
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoTest());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(0));
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderCode("1111");
            l_traderParams.setLoginId(1001);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountCode("1111111");
            l_mainAccountParams.setInstitutionId(1001);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(1001);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setSrvDiv("9");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("9");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("381");
            l_otherOrgInfoAdminParams.setAccountCode("1111111");
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            String l_strUploadDiv = WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_NEW_REGIST_LABEL;
            String l_strPaymentDiv = WEB3AdminSrvRegiAccountDataUploadCsv.ACCOUNT_CODE_LABEL;
            String  l_strAppliLotDiv = WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_ELECTION_LABEL;

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl l_impl =
                new WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl();
            l_impl.updateAppliRegist(
                l_subAccount,
                l_strUploadDiv,
                new Long(1001L),
                "0D",
                "9",
                "381",
                "1111111",
                new Timestamp(WEB3DateUtility.getDate("20080228", "yyyyMMdd").getTime()),
                new Timestamp(WEB3DateUtility.getDate("20080228", "yyyyMMdd").getTime()),
                new Timestamp(WEB3DateUtility.getDate("20080228", "yyyyMMdd").getTime()),
                l_strAppliLotDiv,
                l_strPaymentDiv,
                new Double(1.1D),
                new Timestamp(WEB3DateUtility.getDate("20080228", "yyyyMMdd").getTime()),
                "123");
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testUpdateAppliRegist02()
    {
        final String STR_METHOD_NAME = "testUpdateAppliRegist02()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                "submitServiceRegist",
                new Class[]
                { WEB3SrvRegiNewAppliSpec.class, Long.class },
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoTest());

            TestDBUtility.deleteAll(SrvRegiApplicationParams.TYPE);
            SrvRegiApplicationParams l_srvRegiApplicationParams = TestDBUtility.getSrvRegiApplicationParams();
            l_srvRegiApplicationParams.setOrderId(null);
            TestDBUtility.insertWithDel(l_srvRegiApplicationParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                "getServiceRegist",
                new Class[]{String.class, String.class, String.class, String.class, long.class, boolean.class},
                new WEB3GentradeSrvRegiApplication(l_srvRegiApplicationParams));

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderCode("1111");
            l_traderParams.setLoginId(1001);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountCode("1111111");
            l_mainAccountParams.setInstitutionId(1001);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(1001);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setSrvDiv("9");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("9");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("381");
            l_otherOrgInfoAdminParams.setAccountCode("1111111");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            String l_strUploadDiv = WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_LOT_RESULT_UPLOAD_LABEL;
            String l_strPaymentDiv = WEB3AdminSrvRegiAccountDataUploadCsv.ACCOUNT_CODE_LABEL;
            String  l_strAppliLotDiv = WEB3SrvRegiAppliLotDivDef.CANCEL;

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl l_impl =
                new WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl();
            l_impl.updateAppliRegist(
                l_subAccount,
                l_strUploadDiv,
                new Long(1001L),
                "0D",
                "9",
                "381",
                "1111111",
                new Timestamp(WEB3DateUtility.getDate("20080228", "yyyyMMdd").getTime()),
                new Timestamp(WEB3DateUtility.getDate("20080228", "yyyyMMdd").getTime()),
                new Timestamp(WEB3DateUtility.getDate("20080228", "yyyyMMdd").getTime()),
                l_strAppliLotDiv,
                l_strPaymentDiv,
                new Double(1.1D),
                new Timestamp(WEB3DateUtility.getDate("20080228", "yyyyMMdd").getTime()),
                "123");
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public class LoginInfoTest extends LoginInfoImpl
    {
        public long getLoginId()
        {
            // TODO Auto-generated method stub
            return 1001;
        }
    }
}
@
