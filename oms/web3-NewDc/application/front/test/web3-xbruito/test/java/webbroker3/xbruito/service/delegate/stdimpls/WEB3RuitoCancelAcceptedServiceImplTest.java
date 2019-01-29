head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.30.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3RuitoCancelAcceptedServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.xbruito.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;

import test.util.TestDBUtility;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.WEB3RuitoAcceptedDecisionInterceptor;
import webbroker3.xbruito.data.HostRuitoCancelAcceptParams;
import webbroker3.xbruito.data.HostRuitoCancelAcceptRow;
import webbroker3.xbruito.message.WEB3RuitoCancelAcceptRequest;
import webbroker3.xbruito.service.delegate.WEB3RuitoCancelAcceptedUnitService;

public class WEB3RuitoCancelAcceptedServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoCancelAcceptedServiceImplTest.class);

    private WEB3RuitoCancelAcceptedServiceImpl l_impl =
        new WEB3RuitoCancelAcceptedServiceImpl();

    WEB3RuitoCancelAcceptedUnitService l_cancelAcceptUnitService =
        (WEB3RuitoCancelAcceptedUnitService) Services.getService(
            WEB3RuitoCancelAcceptedUnitService.class);

    public WEB3RuitoCancelAcceptedServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        Services.overrideService(
            WEB3RuitoCancelAcceptedUnitService.class,
            new WEB3RuitoCancelAcceptedUnitServiceImplForMock());
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        Services.overrideService(
            WEB3RuitoCancelAcceptedUnitService.class,
            l_cancelAcceptUnitService);
    }

    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoCancelAcceptedUnitServiceImpl",
                "notifyCancelAcceptedComplete",
                new Class[]{
                    RuitoOrderUnit.class,
                    WEB3RuitoAcceptedDecisionInterceptor.class},
                null);

            TestDBUtility.deleteAllAndCommit(HostRuitoCancelAcceptRow.TYPE);
            HostRuitoCancelAcceptParams l_hostRuitoCancelAcceptParams =
                TestDBUtility.getHostRuitoCancelAcceptRow();
            l_hostRuitoCancelAcceptParams.setInstitutionCode("0D");
            l_hostRuitoCancelAcceptParams.setErrorCode("100");
            l_hostRuitoCancelAcceptParams.setOrderRequestNumber("01");
            l_hostRuitoCancelAcceptParams.setAcceptStatus("1");
            TestDBUtility.insertWithDelAndCommit(l_hostRuitoCancelAcceptParams);
            l_hostRuitoCancelAcceptParams.setInstitutionCode("0D");
            l_hostRuitoCancelAcceptParams.setErrorCode("001");
            l_hostRuitoCancelAcceptParams.setOrderRequestNumber("02");
            TestDBUtility.insertWithDelAndCommit(l_hostRuitoCancelAcceptParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAllAndCommit(RuitoOrderUnitRow.TYPE);
            RuitoOrderUnitParams l_ruitoOrderUnitParams = TestDBUtility.getRuitoOrderUnitRow();
            l_ruitoOrderUnitParams.setOrderRequestNumber("02");
            TestDBUtility.insertWithDelAndCommit(l_ruitoOrderUnitParams);

            WEB3RuitoCancelAcceptRequest l_ruitoCancelAcceptRequest =
                new WEB3RuitoCancelAcceptRequest();

            l_impl.execute(l_ruitoCancelAcceptRequest);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisHostRuitoCancelAcceptRows =
                l_queryProcessor.doFindAllQuery(HostRuitoCancelAcceptRow.TYPE);

            assertEquals(2, l_lisHostRuitoCancelAcceptRows.size());
            HostRuitoCancelAcceptRow l_hostRuitoCancelAcceptRow =
                (HostRuitoCancelAcceptRow)l_lisHostRuitoCancelAcceptRows.get(0);
            assertEquals("1", l_hostRuitoCancelAcceptRow.getStatus());
            l_hostRuitoCancelAcceptRow =
                (HostRuitoCancelAcceptRow)l_lisHostRuitoCancelAcceptRows.get(1);
            assertEquals("1", l_hostRuitoCancelAcceptRow.getStatus());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage());
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoCancelAcceptedUnitServiceImpl",
                "notifyCancelAcceptedComplete",
                new Class[]{
                    RuitoOrderUnit.class,
                    WEB3RuitoAcceptedDecisionInterceptor.class},
                null);

            TestDBUtility.deleteAllAndCommit(HostRuitoCancelAcceptRow.TYPE);
            HostRuitoCancelAcceptParams l_hostRuitoCancelAcceptParams =
                TestDBUtility.getHostRuitoCancelAcceptRow();
            l_hostRuitoCancelAcceptParams.setErrorCode("100");
            l_hostRuitoCancelAcceptParams.setOrderRequestNumber("01");
            l_hostRuitoCancelAcceptParams.setAcceptStatus("1");
            TestDBUtility.insertWithDelAndCommit(l_hostRuitoCancelAcceptParams);
            l_hostRuitoCancelAcceptParams = TestDBUtility.getHostRuitoCancelAcceptRow();
            l_hostRuitoCancelAcceptParams.setErrorCode("001");
            l_hostRuitoCancelAcceptParams.setOrderRequestNumber("02");
            TestDBUtility.insertWithDelAndCommit(l_hostRuitoCancelAcceptParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAllAndCommit(RuitoOrderUnitRow.TYPE);
            RuitoOrderUnitParams l_ruitoOrderUnitParams = TestDBUtility.getRuitoOrderUnitRow();
            l_ruitoOrderUnitParams.setOrderRequestNumber("02");
            TestDBUtility.insertWithDelAndCommit(l_ruitoOrderUnitParams);
            l_ruitoOrderUnitParams = TestDBUtility.getRuitoOrderUnitRow();
            l_ruitoOrderUnitParams.setOrderRequestNumber("01");
            TestDBUtility.insertWithDelAndCommit(l_ruitoOrderUnitParams);

            WEB3RuitoCancelAcceptRequest l_ruitoCancelAcceptRequest =
                new WEB3RuitoCancelAcceptRequest();

            l_impl.execute(l_ruitoCancelAcceptRequest);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisHostRuitoCancelAcceptRows =
                l_queryProcessor.doFindAllQuery(HostRuitoCancelAcceptRow.TYPE);

            assertEquals(2, l_lisHostRuitoCancelAcceptRows.size());
            HostRuitoCancelAcceptRow l_hostRuitoCancelAcceptRow =
                (HostRuitoCancelAcceptRow)l_lisHostRuitoCancelAcceptRows.get(0);
            assertEquals("1", l_hostRuitoCancelAcceptRow.getStatus());
            l_hostRuitoCancelAcceptRow =
                (HostRuitoCancelAcceptRow)l_lisHostRuitoCancelAcceptRows.get(1);
            assertEquals("1", l_hostRuitoCancelAcceptRow.getStatus());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage());
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
