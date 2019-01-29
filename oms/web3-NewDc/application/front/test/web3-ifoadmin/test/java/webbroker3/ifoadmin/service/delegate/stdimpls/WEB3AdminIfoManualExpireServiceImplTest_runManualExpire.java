head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.03.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoManualExpireServiceImplTest_runManualExpire.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifoadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessor;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminIfoManualExpireServiceImplTest_runManualExpire extends TestBaseForMock
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminIfoManualExpireServiceImplTest_runManualExpire.class);

    WEB3AdminIfoManualExpireServiceImpl l_adminIfoManualExpireServiceImpl =
        new WEB3AdminIfoManualExpireServiceImplForTest();

    String methodId = null;

    public WEB3AdminIfoManualExpireServiceImplTest_runManualExpire(String arg0)
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

    public void testRunManualExpire_C0001()
    {
        methodId = ".testRunManualExpire_C0001()";
        final String STR_METHOD_NAME = ".testRunManualExpire_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //実際メソッドのコール
            WEB3AdminIfoManualLapseRunRequest l_adminIfoManualLapseRunRequest =
                new WEB3AdminIfoManualLapseRunRequestForTest();
            l_adminIfoManualExpireServiceImpl.runManualExpire(l_adminIfoManualLapseRunRequest);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            //比較
            assertEquals("validate()というメソッドの異常", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
    }

    public void testRunManualExpire_C0002()
    {
        methodId = ".testRunManualExpire_C0002()";
        final String STR_METHOD_NAME = ".testRunManualExpire_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

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
        try
        {
            //データベースへデータをインサート
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_processor.doDeleteAllQuery(l_administratorParams.getRowType());
            l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            //実際メソッドのコール
            WEB3AdminIfoManualLapseRunRequest l_adminIfoManualLapseRunRequest =
                new WEB3AdminIfoManualLapseRunRequestForTest();
            l_adminIfoManualExpireServiceImpl.runManualExpire(l_adminIfoManualLapseRunRequest);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            //比較
            assertEquals("validate手動失効可能()というメソッドの異常", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
    }

    public void testRunManualExpire_C0003()
    {
        methodId = ".testRunManualExpire_C0003()";
        final String STR_METHOD_NAME = ".testRunManualExpire_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

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
        try
        {
            //実際メソッドのコール
            WEB3AdminIfoManualLapseRunRequest l_adminIfoManualLapseRunRequest =
                new WEB3AdminIfoManualLapseRunRequestForTest();
            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 1);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 1);
            Timestamp l_timestamp = new Timestamp(l_canlendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_timestamp);
            WEB3AdminIfoManualLapseRunResponse l_adminIfoManualLapseRunResponse =
                l_adminIfoManualExpireServiceImpl.runManualExpire(l_adminIfoManualLapseRunRequest);

            //比較
            assertEquals("adminIfo_manualLapseRun", WEB3AdminIfoManualLapseRunResponse.PTYPE);
            assertEquals(200701311315L, WEB3AdminIfoManualLapseRunResponse.serialVersionUID);
            assertEquals(0, WEB3DateUtility.compareToDay(l_adminIfoManualLapseRunResponse.currentTime, l_timestamp));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }
    }

    private class WEB3AdminIfoManualLapseRunRequestForTest extends WEB3AdminIfoManualLapseRunRequest
    {
        public void validate() throws WEB3BaseException
        {
            if (".testRunManualExpire_C0001()".equals(methodId))
            {
                throw new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName(),
                    "validate()というメソッドの異常");
            }
            return;
        }
    }

    private class WEB3AdminIfoManualExpireServiceImplForTest extends WEB3AdminIfoManualExpireServiceImpl
    {
        protected void validateManualExpirePossibility(
            WEB3Administrator l_administrator, 
            WEB3GenRequest l_request) throws WEB3BaseException
        {
            if (".testRunManualExpire_C0002()".equals(methodId))
            {
                throw new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName(),
                    "validate手動失効可能()というメソッドの異常");
            }
            return;
        }

        protected void deleteOnlineRunStatus(String l_strInstitutionCode) throws WEB3BaseException
        {
            return;
        }

        protected List getDaemonTriggerList() throws WEB3BaseException
        {
            return new ArrayList();
        }

        protected ServerAccessor getServerAccessor() throws WEB3BaseException
        {
            return null;
        }
    }
}
@
