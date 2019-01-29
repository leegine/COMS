head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.52.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiAccountDataUploadUnitServiceImplTest_insertSrvApplyAttribute.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3AdminSrvRegiAccountDataUploadUnitServiceImplTest_insertSrvApplyAttribute.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/07/13 崔遠鵬(中訊) 新規作成
 */
package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeMainAccountForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiAccountDataUploadUnitServiceImplTest_insertSrvApplyAttribute extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiAccountDataUploadUnitServiceImplTest_insertSrvApplyAttribute.class);

    WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl impl = new WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl();

    public WEB3AdminSrvRegiAccountDataUploadUnitServiceImplTest_insertSrvApplyAttribute(String arg0)
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

    public void testInsertSrvApplyAttribute01()
    {
        final String STR_METHOD_NAME = "testInsertSrvApplyAttribute01()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "lockAccount",
            new Class[]{String.class, String.class, String.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeMainAccount",
            "getAccountCode",
            new Class[]{},
            "2512246");
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
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
 
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[]{String.class, String.class, String.class},
                new WEB3GentradeMainAccountForMock(333812512246L));

            AdministratorParams l_administratorParams = new AdministratorParams();
            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
            l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);

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

            impl.insertSrvApplyAttribute("0D", "381", "", "1", "2", l_tsAppliStartDate, l_tsAppliEndDate);

            List l_lisRows =
                l_processor.doFindAllQuery(
                SrvAppliAttributeRow.TYPE,
                "",
                "",
                null,
                new Object[]{});
            SrvAppliAttributeRow l_srvAppliAttributeRow = (SrvAppliAttributeRow)l_lisRows.get(0);
            assertEquals(l_srvAppliAttributeRow.getInstitutionCode(), "0D");
            assertEquals(l_srvAppliAttributeRow.getBranchCode(), "381");
            assertEquals(l_srvAppliAttributeRow.getAccountCode(), "2512246");
            assertEquals(l_srvAppliAttributeRow.getSrvDiv(), "1");
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
            assertEquals(WEB3DateUtility.compareToDay(l_srvAppliAttributeRow.getCreatedTimestamp(), l_tsAppliyDate), 0);
            assertEquals(WEB3DateUtility.compareToDay(l_srvAppliAttributeRow.getLastUpdatedTimestamp(), l_tsAppliyDate), 0);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
