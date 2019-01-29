head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.38.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccTransChangeRequestAcceptNormalTransactionCallbackTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import test.util.TestDBUtility;
import webbroker3.aio.data.HostTransferAcceptParams;
import webbroker3.aio.data.HostTransferAcceptRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccTransChangeRequestAcceptNormalTransactionCallbackTest
        extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccTransChangeRequestAcceptNormalTransactionCallbackTest.class);

    public WEB3AccTransChangeRequestAcceptNormalTransactionCallbackTest(String arg0)
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

    /*
     * 取得した注文単位.length = 0
     * (取得した注文単位異常)
     */
    public void testProcess_C0001()
    {
        final String STR_METHOD_NAME = "testProcess_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(HostTransferAcceptRow.TYPE);
            HostTransferAcceptParams l_hostTransferAcceptParams =
                new HostTransferAcceptParams();
            l_hostTransferAcceptParams.setInstitutionCode("123");
            l_hostTransferAcceptParams.setBranchCode("120");
            l_hostTransferAcceptParams.setAccountCode("100");
            l_hostTransferAcceptParams.setOrderRequestNumber("2");
            l_hostTransferAcceptParams.setRequestCode("01");
            l_hostTransferAcceptParams.setStatus("0");
            l_hostTransferAcceptParams.setAcceptDiv("1");
            l_hostTransferAcceptParams.setErrorMessage("0000");
            TestDBUtility.insertWithDel(l_hostTransferAcceptParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("123");
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("123");
            l_branchParams.setBranchCode("120");
            l_branchParams.setBranchId(120);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("100");
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setBranchId(120);
            l_mainAccountParams.setAccountId(100);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(100);
            l_aioOrderUnitParams.setBranchId(120);
            l_aioOrderUnitParams.setOrderRequestNumber("1");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            WEB3AccTransChangeRequestAcceptNormalTransactionCallback l_callback =
                new WEB3AccTransChangeRequestAcceptNormalTransactionCallback(l_hostTransferAcceptParams);

            l_callback.process();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisQuerys = l_queryProcessor.doFindAllQuery(HostTransferAcceptRow.TYPE);

            assertEquals(1 , l_lisQuerys.size());
            assertEquals("1", ((HostTransferAcceptRow)l_lisQuerys.get(0)).getStatus());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 取得した注文単位.length = 1
     */
    public void testProcess_C0002()
    {
        final String STR_METHOD_NAME = "testProcess_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeAcceptUnitServiceImpl",
                "execute",
                new Class[] {AioOrderUnit.class, String.class, String.class},
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, "execute"));

            TestDBUtility.deleteAll(HostTransferAcceptRow.TYPE);
            HostTransferAcceptParams l_hostTransferAcceptParams =
                new HostTransferAcceptParams();
            l_hostTransferAcceptParams.setInstitutionCode("123");
            l_hostTransferAcceptParams.setBranchCode("120");
            l_hostTransferAcceptParams.setAccountCode("100");
            l_hostTransferAcceptParams.setOrderRequestNumber("1");
            l_hostTransferAcceptParams.setStatus("0");
            l_hostTransferAcceptParams.setAcceptDiv("1");
            l_hostTransferAcceptParams.setRequestCode("01");
            l_hostTransferAcceptParams.setErrorMessage("0000");
            TestDBUtility.insertWithDel(l_hostTransferAcceptParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("123");
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("123");
            l_branchParams.setBranchCode("120");
            l_branchParams.setBranchId(120);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("100");
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setBranchId(120);
            l_mainAccountParams.setAccountId(100);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(100);
            l_aioOrderUnitParams.setBranchId(120);
            l_aioOrderUnitParams.setOrderRequestNumber("1");
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.FX);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            WEB3AccTransChangeRequestAcceptNormalTransactionCallback l_callback =
                new WEB3AccTransChangeRequestAcceptNormalTransactionCallback(l_hostTransferAcceptParams);

            l_callback.process();

            fail();
        }
        catch (DataCallbackException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getDetails());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 取得した注文単位.length = 2
     */
    public void testProcess_C0003()
    {
        final String STR_METHOD_NAME = "testProcess_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeAcceptUnitServiceImpl",
                "execute",
                new Class[] {AioOrderUnit.class, String.class, String.class},
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80004, "execute"));
            
            TestDBUtility.deleteAll(HostTransferAcceptRow.TYPE);
            HostTransferAcceptParams l_hostTransferAcceptParams =
                new HostTransferAcceptParams();
            l_hostTransferAcceptParams.setInstitutionCode("123");
            l_hostTransferAcceptParams.setBranchCode("120");
            l_hostTransferAcceptParams.setAccountCode("100");
            l_hostTransferAcceptParams.setOrderRequestNumber("1");
            l_hostTransferAcceptParams.setStatus("0");
            l_hostTransferAcceptParams.setAcceptDiv("1");
            l_hostTransferAcceptParams.setRequestCode("01");
            l_hostTransferAcceptParams.setErrorMessage("0000");
            TestDBUtility.insertWithDel(l_hostTransferAcceptParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("123");
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("123");
            l_branchParams.setBranchCode("120");
            l_branchParams.setBranchId(120);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("100");
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setBranchId(120);
            l_mainAccountParams.setAccountId(100);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(100);
            l_aioOrderUnitParams.setBranchId(120);
            l_aioOrderUnitParams.setOrderRequestNumber("1");
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.FX);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            WEB3AccTransChangeRequestAcceptNormalTransactionCallback l_callback =
                new WEB3AccTransChangeRequestAcceptNormalTransactionCallback(l_hostTransferAcceptParams);

            l_callback.process();

            fail();
        }
        catch (DataCallbackException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80004, l_ex.getDetails());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
