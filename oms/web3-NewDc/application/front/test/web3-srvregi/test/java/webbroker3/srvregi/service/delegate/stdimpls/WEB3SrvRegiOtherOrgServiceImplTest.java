head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.51.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SrvRegiOtherOrgServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.WEB3SrvRegiOtherOrgInfoAdmin;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.define.WEB3SrvRegiKeyItemDef;
import webbroker3.srvregi.message.WEB3SrvRegiSortKey;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3SrvRegiOtherOrgServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3SrvRegiOtherOrgServiceImplTest.class);

    public WEB3SrvRegiOtherOrgServiceImplTest(String arg0)
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

    public void testGetOtherOrgInfoAdminCase0001()
    {
        final String STR_METHOD_NAME = "testGetOtherOrgInfoAdminCase0001()";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiOtherOrgServiceImpl l_impl = new WEB3SrvRegiOtherOrgServiceImpl();

        try
        {
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);

            assertNull(l_impl.getOtherOrgInfo("1",
                "1",
                "1",
                "1",
                false));

            assertNull(l_impl.getOtherOrgInfo("1",
                "1",
                "1",
                "1",
                true));

            assertNull(l_impl.getOtherOrgInfo(1, "1",
                false));

            assertNull(l_impl.getOtherOrgInfo(1, "1",
                true));
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetOtherOrgInfoAdminCase0002()
    {
        final String STR_METHOD_NAME = "testGetOtherOrgInfoAdminCase0002()";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiOtherOrgServiceImpl l_impl = new WEB3SrvRegiOtherOrgServiceImpl();

        try
        {
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("1");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("456");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            l_otherOrgInfoAdminParams.setSrvDiv("1");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("456");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setSequenceNumber(2);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);
            l_impl.getOtherOrgInfo("1",
                "0D",
                "456",
                "111",
                false);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
            try
            {
            l_impl.getOtherOrgInfo("1",
                "0D",
                "456",
                "111",
                true);
            }
            catch (WEB3BaseException ex)
            {
                assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, ex.getErrorInfo());
            }
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetOtherOrgInfoAdminCase0003()
    {
        final String STR_METHOD_NAME = "testGetOtherOrgInfoAdminCase0003()";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiOtherOrgServiceImpl l_impl = new WEB3SrvRegiOtherOrgServiceImpl();

        try
        {
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("1");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("456");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            WEB3SrvRegiOtherOrgInfoAdmin l_srvRegiOtherOrgInfoAdmin = l_impl.getOtherOrgInfo("1",
                "0D",
                "456",
                "111",
                false);

            assertEquals(l_srvRegiOtherOrgInfoAdmin.getSrvDiv(), "1");
            l_srvRegiOtherOrgInfoAdmin = l_impl.getOtherOrgInfo("1",
                "0D",
                "456",
                "111",
                true);
            assertEquals(l_srvRegiOtherOrgInfoAdmin.getSrvDiv(), "1");
            l_srvRegiOtherOrgInfoAdmin = l_impl.getOtherOrgInfo(1, "1",

                false);
            assertEquals(l_srvRegiOtherOrgInfoAdmin.getSrvDiv(), "1");
            l_srvRegiOtherOrgInfoAdmin = l_impl.getOtherOrgInfo(1, "1",
                true);
            assertEquals(l_srvRegiOtherOrgInfoAdmin.getSrvDiv(), "1");
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetUnUsedsSquenceNumberCase0001()
    {
        final String STR_METHOD_NAME = "testGetUnUsedsSquenceNumberCase0001()";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiOtherOrgServiceImpl l_impl = new WEB3SrvRegiOtherOrgServiceImpl();

        try
        {
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);

            l_impl.getUnUseSequenceNumberInfo("1", false);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03019, l_ex.getErrorInfo());
            try
            {
                l_impl.getUnUseSequenceNumberInfo("1", true);
                fail();
            }
            catch (WEB3BaseException ex)
            {
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03019, ex.getErrorInfo());
            }
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetUnUsedsSquenceNumberCase0002()
    {
        final String STR_METHOD_NAME = "testGetUnUsedsSquenceNumberCase0002()";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiOtherOrgServiceImpl l_impl = new WEB3SrvRegiOtherOrgServiceImpl();

        try
        {
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("1");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("456");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            assertEquals(1, l_impl.getUnUseSequenceNumberInfo("1", false).getSequenceNumber());
            assertEquals(1, l_impl.getUnUseSequenceNumberInfo("1", true).getSequenceNumber());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateOtherOrgInfoAdminCase0001()
    {
        final String STR_METHOD_NAME = "testUpdateOtherOrgInfoAdminCase0001()";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiOtherOrgServiceImpl l_impl = new WEB3SrvRegiOtherOrgServiceImpl();

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoTest());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001));
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("1");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("456");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(WEB3DateUtility.getDate("20050701","yyyyMMdd"));
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20050701","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderCode("1111");
            l_traderParams.setLoginId(1001);
            TestDBUtility.insertWithDel(l_traderParams);

            l_impl.submitOtherOrgInfo("0D", "456", "111", "1",
                GtlUtils.getSystemTimestamp(), GtlUtils.getSystemTimestamp(),
                true);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateOtherOrgInfoAdminCase0002()
    {
        final String STR_METHOD_NAME = "testUpdateOtherOrgInfoAdminCase0002()";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiOtherOrgServiceImpl l_impl = new WEB3SrvRegiOtherOrgServiceImpl();

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoTest());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001));
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("1");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("456");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(WEB3DateUtility.getDate("20050701","yyyyMMdd"));
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20050701","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            l_mainAccountParams.setAccountCode("1111111");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            l_impl.submitOtherOrgInfo("0D", "456", "111", "1",
                GtlUtils.getSystemTimestamp(), GtlUtils.getSystemTimestamp(),
                true);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateOtherOrgInfoAdminCase0003()
    {
        final String STR_METHOD_NAME = "testUpdateOtherOrgInfoAdminCase0003()";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiOtherOrgServiceImpl l_impl = new WEB3SrvRegiOtherOrgServiceImpl();

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoTest());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001));
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("1");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("456");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(WEB3DateUtility.getDate("20050701","yyyyMMdd"));
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20050701","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderCode("1111");
            l_traderParams.setLoginId(1001);
            TestDBUtility.insertWithDel(l_traderParams);

            l_impl.submitOtherOrgInfo("0D", "456", "111", "1",
                GtlUtils.getSystemTimestamp(), GtlUtils.getSystemTimestamp(),
                false);
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            l_impl.submitOtherOrgInfo("0D", "456", "111", "1",
                GtlUtils.getSystemTimestamp(), GtlUtils.getSystemTimestamp(),
                false);
            fail();
        }
        catch (WEB3BaseException ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03019, ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateOtherOrgInfoAdminCase0004()
    {
        final String STR_METHOD_NAME = "testUpdateOtherOrgInfoAdminCase0004()";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiOtherOrgServiceImpl l_impl = new WEB3SrvRegiOtherOrgServiceImpl();

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoTest());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001));
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("1");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("456");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(WEB3DateUtility.getDate("20050701","yyyyMMdd"));
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20050701","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            l_mainAccountParams.setAccountCode("1111111");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            l_impl.submitOtherOrgInfo("0D", "456", "111", "1",
                GtlUtils.getSystemTimestamp(), GtlUtils.getSystemTimestamp(),
                false);
            l_impl.submitOtherOrgInfo("0D", "456", "111", "4", "1",
                GtlUtils.getSystemTimestamp(), GtlUtils.getSystemTimestamp(),
                false);
            l_impl.submitOtherOrgInfo("0D", "456", "111", "1", "1",
                GtlUtils.getSystemTimestamp(), GtlUtils.getSystemTimestamp(),
                false);
        }
        catch (WEB3BaseException ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03019, ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetOtherOrgUnUsedCountCase0001()
    {
        final String STR_METHOD_NAME = "testGetOtherOrgUnUsedCountCase0001()";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiOtherOrgServiceImpl l_impl = new WEB3SrvRegiOtherOrgServiceImpl();

        try
        {
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("1");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("456");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            assertEquals(0, l_impl.getOtherOrgUnUsedCount("0").intValue());
            assertEquals(1, l_impl.getOtherOrgUnUsedCount("1").intValue());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetOtherOrgInfoListCase0001()
    {
        final String STR_METHOD_NAME = "testGetOtherOrgInfoListCase0001()";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiOtherOrgServiceImpl l_impl = new WEB3SrvRegiOtherOrgServiceImpl();

        try
        {
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("1");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("456");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()));
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()));
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            long l_lngSequenceNumber = 1;
            String l_strServiceDiv = "1";
            String l_strID = "1001";
            String l_strStatus = "9";
            String l_strInstitutionCode = "0D";
            String[] l_strBranchCodes = new String[]{"456"};
            String l_strAccountCode = "111";
            Timestamp l_tsAppliStartDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Timestamp l_tsAppliEndDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            WEB3SrvRegiSortKey[] l_sortKeys = new WEB3SrvRegiSortKey[9];
            WEB3SrvRegiSortKey l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.ascDesc = WEB3AscDescDef.ASC;
            l_sortKey.keyItem = WEB3SrvRegiKeyItemDef.SEQUENCE_NUMBER;
            l_sortKeys[0] = l_sortKey;
            l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.ascDesc = WEB3AscDescDef.ASC;
            l_sortKey.keyItem = WEB3SrvRegiKeyItemDef.ID;
            l_sortKeys[1] = l_sortKey;
            l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.ascDesc = WEB3AscDescDef.ASC;
            l_sortKey.keyItem = WEB3SrvRegiKeyItemDef.STATUS;
            l_sortKeys[2] = l_sortKey;
            l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.ascDesc = WEB3AscDescDef.ASC;
            l_sortKey.keyItem = WEB3SrvRegiKeyItemDef.BRANCH_CODE;
            l_sortKeys[3] = l_sortKey;
            l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.ascDesc = WEB3AscDescDef.ASC;
            l_sortKey.keyItem = WEB3SrvRegiKeyItemDef.ACCOUNT_CODE;
            l_sortKeys[4] = l_sortKey;
            l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.ascDesc = WEB3AscDescDef.ASC;
            l_sortKey.keyItem = WEB3SrvRegiKeyItemDef.APPLI_START_DATE_FROM;
            l_sortKeys[5] = l_sortKey;
            l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.ascDesc = WEB3AscDescDef.ASC;
            l_sortKey.keyItem = WEB3SrvRegiKeyItemDef.APPLI_END_DATE_TO;
            l_sortKeys[6] = l_sortKey;
            l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.ascDesc = WEB3AscDescDef.ASC;
            l_sortKey.keyItem = WEB3SrvRegiKeyItemDef.LAST_UPDATED_TIMESTAMP;
            l_sortKeys[7] = l_sortKey;
            l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.ascDesc = WEB3AscDescDef.ASC;
            l_sortKey.keyItem = WEB3SrvRegiKeyItemDef.LAST_UPDATER;
            l_sortKeys[8] = l_sortKey;

            l_impl.getOtherOrgInfoList(
                "1",
                l_strServiceDiv,
                l_strID,
                l_strStatus,
                l_strInstitutionCode,
                l_strBranchCodes,
                l_strAccountCode,
                l_tsAppliStartDate,
                l_tsAppliEndDate,
                l_sortKeys);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetOtherOrgInfoListCase0002()
    {
        final String STR_METHOD_NAME = "testGetOtherOrgInfoListCase0002()";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiOtherOrgServiceImpl l_impl = new WEB3SrvRegiOtherOrgServiceImpl();

        try
        {
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("1");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("456");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()));
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()));
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            l_otherOrgInfoAdminParams.setSrvDiv("1");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("123");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setSequenceNumber(2);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()));
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()));
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);
            String l_strServiceDiv = "1";
            String l_strID = "1001";
            String l_strStatus = "9";
            String l_strInstitutionCode = "0D";
            String[] l_strBranchCodes = new String[]{"456", "123"};
            String l_strAccountCode = "111";
            Timestamp l_tsAppliStartDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Timestamp l_tsAppliEndDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            WEB3SrvRegiSortKey[] l_sortKeys = new WEB3SrvRegiSortKey[9];
            WEB3SrvRegiSortKey l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.ascDesc = WEB3AscDescDef.DESC;
            l_sortKey.keyItem = WEB3SrvRegiKeyItemDef.SEQUENCE_NUMBER;
            l_sortKeys[0] = l_sortKey;
            l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.ascDesc = WEB3AscDescDef.DESC;
            l_sortKey.keyItem = WEB3SrvRegiKeyItemDef.ID;
            l_sortKeys[1] = l_sortKey;
            l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.ascDesc = WEB3AscDescDef.DESC;
            l_sortKey.keyItem = WEB3SrvRegiKeyItemDef.STATUS;
            l_sortKeys[2] = l_sortKey;
            l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.ascDesc = WEB3AscDescDef.DESC;
            l_sortKey.keyItem = WEB3SrvRegiKeyItemDef.BRANCH_CODE;
            l_sortKeys[3] = l_sortKey;
            l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.ascDesc = WEB3AscDescDef.DESC;
            l_sortKey.keyItem = WEB3SrvRegiKeyItemDef.ACCOUNT_CODE;
            l_sortKeys[4] = l_sortKey;
            l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.ascDesc = WEB3AscDescDef.DESC;
            l_sortKey.keyItem = WEB3SrvRegiKeyItemDef.APPLI_START_DATE_FROM;
            l_sortKeys[5] = l_sortKey;
            l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.ascDesc = WEB3AscDescDef.DESC;
            l_sortKey.keyItem = WEB3SrvRegiKeyItemDef.APPLI_END_DATE_TO;
            l_sortKeys[6] = l_sortKey;
            l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.ascDesc = WEB3AscDescDef.DESC;
            l_sortKey.keyItem = WEB3SrvRegiKeyItemDef.LAST_UPDATED_TIMESTAMP;
            l_sortKeys[7] = l_sortKey;
            l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.ascDesc = WEB3AscDescDef.DESC;
            l_sortKey.keyItem = WEB3SrvRegiKeyItemDef.LAST_UPDATER;
            l_sortKeys[8] = l_sortKey;

            l_impl.getOtherOrgInfoList(
                null,
                l_strServiceDiv,
                l_strID,
                l_strStatus,
                l_strInstitutionCode,
                l_strBranchCodes,
                l_strAccountCode,
                l_tsAppliStartDate,
                l_tsAppliEndDate,
                l_sortKeys);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetOtherOrgInfoListCase0003()
    {
        final String STR_METHOD_NAME = "testGetOtherOrgInfoListCase0003()";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiOtherOrgServiceImpl l_impl = new WEB3SrvRegiOtherOrgServiceImpl();

        try
        {
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("1");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("456");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()));
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()));
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            l_otherOrgInfoAdminParams.setSrvDiv("1");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("123");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setSequenceNumber(2);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setAppliEndDate(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()));
            l_otherOrgInfoAdminParams.setAppliStartDate(
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp()));
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);
            String l_strServiceDiv = "1";
            String l_strID = "1001";
            String l_strStatus = "9";
//            String l_strAccountCode = "111";
//            Timestamp l_tsAppliStartDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Timestamp l_tsAppliEndDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            WEB3SrvRegiSortKey[] l_sortKeys = null;
            String[] l_strBranchCodes = new String[]{"456", "123"};
            l_impl.getOtherOrgInfoList(
                "1",
                l_strServiceDiv,
                l_strID,
                l_strStatus,
                "0D",
                l_strBranchCodes,
                null,
                null,
                null,
                l_sortKeys);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
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
