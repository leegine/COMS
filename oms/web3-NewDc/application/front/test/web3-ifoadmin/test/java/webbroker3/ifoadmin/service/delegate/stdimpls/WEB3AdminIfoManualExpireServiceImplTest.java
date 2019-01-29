head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.03.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoManualExpireServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・先物OP手動失効サービスImpl(WEB3AdminIfoManualExpireServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/06 (中訊) 新規作成
Revision History : 2007/07/11 (中訊) 趙林鵬 仕様変更モデルNo.002
*/

package webbroker3.ifoadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessor;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.db.ServerConfigParams;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.InstitutionImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CarryoverEndTypeDef;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3DaemonTriggerTypeDef;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.BranchIndexDealtCondParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.OnlineRunStatusParams;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifoadmin.message.WEB3AdminIfoLapseTargetOrderCondition;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseConfirmRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseInputRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseInputResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseStatusRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseStatusResponse;
import webbroker3.login.message.WEB3AcceptLoginRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminIfoManualExpireServiceImplTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
    		WEB3AdminIfoManualExpireServiceImplTest.class);

    public WEB3AdminIfoManualExpireServiceImplTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    WEB3AdminIfoManualExpireServiceImpl l_adminIfoManualExpireServiceImpl =
        new WEB3AdminIfoManualExpireServiceImpl();
    String methodId = null;

    public void testExcute_C0001()
    {
        final String STR_METHOD_NAME = " testExcute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_adminIfoManualExpireServiceImpl.execute(null);
            fail();
        } catch (WEB3SystemLayerException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //get入力画面()をコールする。
    public void testExcute_C0002() throws DataException
    {
        final String STR_METHOD_NAME = " testGetinput_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_adminIfoManualExpireServiceImpl =
            new WEB3AdminIfoManualExpireServiceImplForTest();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(0L));

            LoginInfo loginInfo = new LoginInfoImpl();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                loginInfo);

            InstitutionParams  l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("77");
            TestDBUtility.insertWithDel(l_institutionParams);
            InstitutionImpl l_institutionImpl = new InstitutionImpl(l_institutionParams);

            WEB3AdminIfoManualLapseInputRequest l_request = new WEB3AdminIfoManualLapseInputRequestForTest();
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("77");
            l_administratorParams.setPermissionLevel("770");
            l_administratorParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("77");
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory("C0304");
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            BranchIndexDealtCondParams l_branchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams.setInstitutionCode("77");
            l_branchIndexDealtCondParams.setTargetProductCode("66");
            l_branchIndexDealtCondParams.setEnableOrder(WEB3DealtDef.CAN_DEALT);
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);

            BranchIndexDealtCondParams l_branchIndexDealtCondParams1 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams1.setInstitutionCode("77");
            l_branchIndexDealtCondParams1.setTargetProductCode("77");
            l_branchIndexDealtCondParams1.setEnableOrder(WEB3DealtDef.CAN_DEALT);
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams1);

            BranchIndexDealtCondParams l_branchIndexDealtCondParams2 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams2.setInstitutionCode("77");
            l_branchIndexDealtCondParams2.setTargetProductCode("88");
            l_branchIndexDealtCondParams2.setEnableOrder(WEB3DealtDef.CAN_DEALT);
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams2);

            new BranchIndexDealtCondParams(l_branchIndexDealtCondParams);
            WEB3AdminIfoManualLapseInputResponse l_response = (WEB3AdminIfoManualLapseInputResponse) l_adminIfoManualExpireServiceImpl.execute(l_request);

            int flag = 0 ;
            String[] l_strArry = {"66","77","88"};
            for (int i = 0; i < l_strArry.length; i++)
            {
                for(int j = 0; j < l_strArry.length; j++)
                if (l_strArry[i].equals(l_response.targetProductList[j]))
                {
                    flag = flag + 1;
                }

            }
            assertEquals(3,flag);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //validate手動失効()をコールする
    public void testExcute_C0003()
    {
        final String STR_METHOD_NAME = " testExcute_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminIfoManualLapseConfirmRequest l_request = new WEB3AdminIfoManualLapseConfirmRequest();
        l_request.ifoLapseTargetOrderCondition = null;

        try
        {
            WEB3AdminIfoManualExpireServiceImplForTest l_WEB3AdminIfoManualExpireServiceImpl =
                new WEB3AdminIfoManualExpireServiceImplForTest();

            l_WEB3AdminIfoManualExpireServiceImpl.execute(l_request);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02420, l_ex.getErrorInfo());
        }
        catch (Exception e)
        {
            log.debug(e.getMessage(), e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //run手動失効()をコールする。
    public void testExcute_C0004()
    {
        l_adminIfoManualExpireServiceImpl = new WEB3AdminIfoManualExpireServiceImplForTest();

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        methodId = ".testRunManualExpire_C0003()";
        final String STR_METHOD_NAME = ".testExcute_C0004()()";
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
            new Long(0L));
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            l_administratorParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

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
                (WEB3AdminIfoManualLapseRunResponse) l_adminIfoManualExpireServiceImpl.execute(l_adminIfoManualLapseRunRequest);

            //比較
            assertEquals("adminIfo_manualLapseRun", WEB3AdminIfoManualLapseRunResponse.PTYPE);
            assertEquals(200701311315L, WEB3AdminIfoManualLapseRunResponse.serialVersionUID);
            assertEquals(0, WEB3DateUtility.compareToDay(l_adminIfoManualLapseRunResponse.currentTime, l_timestamp));
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    // validate処理ステータス()をコールする。
    // オンライン実行結果レコードが取得できなかった場合l_lisOnlineExecResultList == null
    public void testExcute_C0005()
    {
        final String STR_METHOD_NAME = "testExcute_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("11");
            l_administratorParams.setPermissionLevel("770");
            l_administratorParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =new WEB3Administrator(l_administratorParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(0L));

            LoginInfo loginInfo = new LoginInfoImpl();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                loginInfo);

            AdminPermissionParams l_adminPermissionParams  = TestDBUtility.getAdminPermissionRow();
            // TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            l_adminPermissionParams.setInstitutionCode("11");
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory("C0304");

            TestDBUtility.insertWithDel(l_adminPermissionParams);

            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();

            l_daemonTriggerParams.setThreadNo(123456L);
            l_daemonTriggerParams.setTriggerDate(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_daemonTriggerParams.setTriggerStatus("5");
            l_daemonTriggerParams.setTriggerType(WEB3DaemonTriggerTypeDef.IFO_MANUAL_EXPIRE);
            l_daemonTriggerParams.setRangeFrom(0L);
            l_daemonTriggerParams.setRangeTo(99L);
            l_daemonTriggerParams.setOrderRequestNumber("11");
            TestDBUtility.insertWithDel(l_daemonTriggerParams);

            OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();
            TestDBUtility.deleteAll(l_onlineRunStatusParams.getRowType());
            l_onlineRunStatusParams.setInstitutionCode("66");
            l_onlineRunStatusParams.setProductType(ProductTypeEnum.IFO);
            l_onlineRunStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
            l_onlineRunStatusParams.setOnlineServiceDiv(WEB3OnlineServiceDiv.AUTO_EXECUTE);//l_lisOnlineExecResultList==null
            l_onlineRunStatusParams.setAccountIdFrom(1000);
            l_onlineRunStatusParams.setAccountIdTo(9999);
            l_onlineRunStatusParams.setRunStatusDiv("5");//WEB3RunStatusDivDef.DEALING = "5";
            l_onlineRunStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_onlineRunStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_onlineRunStatusParams);

            WEB3AdminIfoManualLapseStatusRequest l_request =  new WEB3AdminIfoManualLapseStatusRequest();

            WEB3AdminIfoManualLapseStatusResponse l_response = new WEB3AdminIfoManualLapseStatusResponse();
            l_response = (WEB3AdminIfoManualLapseStatusResponse) l_adminIfoManualExpireServiceImpl.execute(l_request);
            assertEquals(l_response.lapseStatus ,WEB3RunStatusDivDef.DEALING);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //WEB3AcceptLoginRequest
    public void testExcute_C0006()
    {
        final String STR_METHOD_NAME = "testExcute_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AcceptLoginRequest l_request = new WEB3AcceptLoginRequest();
            l_adminIfoManualExpireServiceImpl.execute(l_request);
            fail();
        }

        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018 ,l_ex.getErrorInfo());

        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testRunManualExpire_C0001()
    {
        methodId = ".testRunManualExpire_C0001()";
        final String STR_METHOD_NAME = ".testRunManualExpire_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        l_adminIfoManualExpireServiceImpl = new WEB3AdminIfoManualExpireServiceImplForTest();

        String methodId = null;

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
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            //比較
            assertEquals("validate()というメソッドの異常", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testRunManualExpire_C0002()
    {
        l_adminIfoManualExpireServiceImpl = new WEB3AdminIfoManualExpireServiceImplForTest();

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
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
            new Long(0L));
        try
        {
            //データベースへデータをインサート

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            l_administratorParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator =new WEB3Administrator(l_administratorParams);

            //実際メソッドのコール
            WEB3AdminIfoManualLapseRunRequest l_adminIfoManualLapseRunRequest =
                new WEB3AdminIfoManualLapseRunRequestForTest();
            l_adminIfoManualExpireServiceImpl.runManualExpire(l_adminIfoManualLapseRunRequest);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            //比較
            assertEquals("validate手動失効可能()というメソッドの異常", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testRunManualExpire_C0003()
    {
        l_adminIfoManualExpireServiceImpl = new WEB3AdminIfoManualExpireServiceImplForTest();

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
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
            new Long(0L));
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            l_administratorParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator =new WEB3Administrator(l_administratorParams);


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
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
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

        public void validateCarryOvering(WEB3GentradeInstitution l_institution,String[] l_strTradingTypeList)
        {

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

    public void testValidateCarryOvering_C0001()
    {
        final String STR_METHOD_NAME = " testValidateCarryOvering_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("N8080");
            l_context.setBizDateType("1");
            l_context.setMarketCode("1");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            l_tradingTimeParams.setInstitutionCode(l_context.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_context.getBranchCode());
            l_tradingTimeParams.setBizDateType(l_context.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_context.getMarketCode());
            l_tradingTimeParams.setProductCode(l_context.getProductCode());
            l_tradingTimeParams.setTradingTimeType(l_context.getTradingTimeType());
            l_tradingTimeParams.setSessionType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            TestDBUtility.deleteAll(l_orderexecutionEndParams.getRowType());
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
            l_orderexecutionEndParams.setCarryoverEndType(WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP);
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            OrderexecutionEndParams l_orderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams1.setInstitutionCode("0D");
            l_orderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_orderexecutionEndParams1.setCarryoverEndType(WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP);
            l_orderexecutionEndParams1.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams1);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(11L);
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);

            String[] l_strTradingTypeList = null;
            l_adminIfoManualExpireServiceImpl.validateCarryOvering( l_institution,l_strTradingTypeList);

            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02446,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateCarryOvering_C0002()
    {
        final String STR_METHOD_NAME = " testValidateCarryOvering_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("N8080");
            l_context.setBizDateType("1");
            l_context.setMarketCode("1");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            l_tradingTimeParams.setInstitutionCode(l_context.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_context.getBranchCode());
            l_tradingTimeParams.setBizDateType(l_context.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_context.getMarketCode());
            l_tradingTimeParams.setProductCode(l_context.getProductCode());
            l_tradingTimeParams.setTradingTimeType(l_context.getTradingTimeType());
            l_tradingTimeParams.setSessionType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            TestDBUtility.deleteAll(l_orderexecutionEndParams.getRowType());
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
            l_orderexecutionEndParams.setCarryoverEndType(WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP);
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(11L);
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);

            String[] l_strTradingTypeList = {"601","602","603","604"};
            l_adminIfoManualExpireServiceImpl.validateCarryOvering( l_institution,l_strTradingTypeList);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02446,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateCarryOvering_C0003()
    {
        final String STR_METHOD_NAME = " testValidateCarryOvering_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("N8080");
            l_context.setBizDateType("1");
            l_context.setMarketCode("1");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            l_tradingTimeParams.setInstitutionCode(l_context.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_context.getBranchCode());
            l_tradingTimeParams.setBizDateType(l_context.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_context.getMarketCode());
            l_tradingTimeParams.setProductCode(l_context.getProductCode());
            l_tradingTimeParams.setTradingTimeType(l_context.getTradingTimeType());
            l_tradingTimeParams.setSessionType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            OrderexecutionEndParams l_orderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            TestDBUtility.deleteAll(l_orderexecutionEndParams1.getRowType());
            l_orderexecutionEndParams1.setInstitutionCode("0D");
            l_orderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_orderexecutionEndParams1.setCarryoverEndType(WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP);
            l_orderexecutionEndParams1.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams1);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(11L);
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);

            String[] l_strTradingTypeList = {"605","606","607","608"};
            l_adminIfoManualExpireServiceImpl.validateCarryOvering( l_institution,l_strTradingTypeList);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02446,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //パラメータ値不正
    public void testValidateManualExpirePossibility_C0001()
    {
        final String STR_METHOD_NAME = " testValidateManualExpirePossibility_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3Administrator l_administrator =null;
            WEB3GenRequest l_request = null;
            l_adminIfoManualExpireServiceImpl.validateManualExpirePossibility( l_administrator,l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
        }

        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

      //型別例外
    public void testValidateManualExpirePossibility_C0002()
    {
        final String STR_METHOD_NAME = " testValidateManualExpirePossibility_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorId(33381330001L);
            l_administratorParams.setAdministratorCode("330001");
            l_administratorParams.setInstitutionId(33L);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setLoginId(33381330003L);
            l_administratorParams.setPermissionLevel("331");
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =new WEB3Administrator(l_administratorParams);
            WEB3AdminIfoManualLapseInputRequest l_request = null;
            l_adminIfoManualExpireServiceImpl.validateManualExpirePossibility( l_administrator,l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018,l_ex.getErrorInfo());
        }

        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //validate権限?常
    public void testValidateManualExpirePossibility_C0003()
    {
        final String STR_METHOD_NAME = " testValidateManualExpirePossibility_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("77");
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory("D0102");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setLastUpdater("sra501");
            l_adminPermissionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorId(33381330001L);
            l_administratorParams.setAdministratorCode("330001");
            l_administratorParams.setInstitutionId(33L);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setLoginId(33381330003L);
            l_administratorParams.setPermissionLevel("331");
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =new WEB3Administrator(l_administratorParams);

            WEB3AdminIfoManualLapseConfirmRequest l_request = new WEB3AdminIfoManualLapseConfirmRequest();
            WEB3AdminIfoLapseTargetOrderCondition l_ifoLapseTargetOrderCondition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] branchCode = {"123"};
            String[] tradingTypeList = {"004"};
            l_ifoLapseTargetOrderCondition.branchCode = branchCode;
            l_ifoLapseTargetOrderCondition.fuOpDiv = "Option";
            l_ifoLapseTargetOrderCondition.targetProductCode = "12";
            l_ifoLapseTargetOrderCondition.delivaryMonth = "01";
            l_ifoLapseTargetOrderCondition.strikePrice= "10";
            l_ifoLapseTargetOrderCondition.opProductType = "P";
            l_ifoLapseTargetOrderCondition.tradingTypeList = tradingTypeList;
            l_ifoLapseTargetOrderCondition.accountCode = "1234";

            l_request.ifoLapseTargetOrderCondition = l_ifoLapseTargetOrderCondition;
            l_adminIfoManualExpireServiceImpl.validateManualExpirePossibility( l_administrator,l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //validate部店権限?常
    public void testValidateManualExpirePossibility_C0004()
    {
        final String STR_METHOD_NAME = " testValidateManualExpirePossibility_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("77");
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.MANUAL_EXPIRE);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setLastUpdater("sra501");

            l_adminPermissionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
            l_administratorTypeParams.setInstitutionCode("77");
            l_administratorTypeParams.setPermissionLevel("770");
            l_administratorTypeParams.setLastUpdater("my");
            l_administratorTypeParams.setDirAdminFlag(0);
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            l_administratorTypeParams.setPermissionLevelName("1234546");
            l_administratorTypeParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_administratorTypeParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorId(33381330001L);
            l_administratorParams.setAdministratorCode("330001");
            l_administratorParams.setInstitutionId(33L);
            l_administratorParams.setInstitutionCode("77");
            l_administratorParams.setLoginId(33381330003L);
            l_administratorParams.setPermissionLevel("770");
            l_administratorParams.setBranchCode("102");
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =new WEB3Administrator(l_administratorParams);

            WEB3AdminIfoManualLapseConfirmRequest l_request = new WEB3AdminIfoManualLapseConfirmRequest();
            WEB3AdminIfoLapseTargetOrderCondition l_ifoLapseTargetOrderCondition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] branchCode = {"123"};
            String[] tradingTypeList = {"004"};
            l_ifoLapseTargetOrderCondition.branchCode = branchCode;
            l_ifoLapseTargetOrderCondition.fuOpDiv = "Option";
            l_ifoLapseTargetOrderCondition.targetProductCode = "12";
            l_ifoLapseTargetOrderCondition.delivaryMonth = "01";
            l_ifoLapseTargetOrderCondition.strikePrice= "10";
            l_ifoLapseTargetOrderCondition.opProductType = "P";
            l_ifoLapseTargetOrderCondition.tradingTypeList = tradingTypeList;
            l_ifoLapseTargetOrderCondition.accountCode = "1234";

            l_request.ifoLapseTargetOrderCondition = l_ifoLapseTargetOrderCondition;
            l_adminIfoManualExpireServiceImpl.validateManualExpirePossibility( l_administrator,l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //(*)処理起動リクエストの場合  validate取引パスワード(String)?常
    public void testValidateManualExpirePossibility_C0005()
    {
        final String STR_METHOD_NAME = " testValidateManualExpirePossibility_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("77");
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.MANUAL_EXPIRE);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setLastUpdater("sra501");

            l_adminPermissionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
            l_administratorTypeParams.setInstitutionCode("77");
            l_administratorTypeParams.setPermissionLevel("770");
            l_administratorTypeParams.setLastUpdater("my");
            l_administratorTypeParams.setDirAdminFlag(0);
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            l_administratorTypeParams.setPermissionLevelName("1234546");
            l_administratorTypeParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_administratorTypeParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorId(33381330001L);
            l_administratorParams.setAdministratorCode("330001");
            l_administratorParams.setInstitutionId(33L);
            l_administratorParams.setInstitutionCode("77");
            l_administratorParams.setLoginId(33381330003L);
            l_administratorParams.setPermissionLevel("770");
            l_administratorParams.setBranchCode("102");
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =new WEB3AdministratorForTest(l_administratorParams);

            WEB3AdminIfoManualLapseRunRequest l_request = new WEB3AdminIfoManualLapseRunRequest();
            WEB3AdminIfoLapseTargetOrderCondition l_ifoLapseTargetOrderCondition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] branchCode = {"000"};
            String[] tradingTypeList = {"004"};
            l_ifoLapseTargetOrderCondition.branchCode = branchCode;
            l_ifoLapseTargetOrderCondition.fuOpDiv = "Option";
            l_ifoLapseTargetOrderCondition.targetProductCode = "12";
            l_ifoLapseTargetOrderCondition.delivaryMonth = "01";
            l_ifoLapseTargetOrderCondition.strikePrice= "10";
            l_ifoLapseTargetOrderCondition.opProductType = "P";
            l_ifoLapseTargetOrderCondition.tradingTypeList = tradingTypeList;
            l_ifoLapseTargetOrderCondition.accountCode = "1234";
            l_request.password = "mzz";

            l_request.ifoLapseTargetOrderCondition = l_ifoLapseTargetOrderCondition;
            l_adminIfoManualExpireServiceImpl.validateManualExpirePossibility( l_administrator,l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            // assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074,l_ex.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00009,l_ex.getErrorInfo());
        }

        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //validate注文繰越処理中(証券会社,String[])?常
    public void testValidateManualExpirePossibility_C0006()
    {
        final String STR_METHOD_NAME = " testValidateManualExpirePossibility_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("77");
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.MANUAL_EXPIRE);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setLastUpdater("sra501");

            l_adminPermissionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
            l_administratorTypeParams.setInstitutionCode("77");
            l_administratorTypeParams.setPermissionLevel("770");
            l_administratorTypeParams.setLastUpdater("my");
            l_administratorTypeParams.setDirAdminFlag(0);
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            l_administratorTypeParams.setPermissionLevelName("1234546");
            l_administratorTypeParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_administratorTypeParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorId(33381330001L);
            l_administratorParams.setAdministratorCode("330001");
            l_administratorParams.setInstitutionId(33L);
            l_administratorParams.setInstitutionCode("77");
            l_administratorParams.setLoginId(33381330003L);
            l_administratorParams.setPermissionLevel("770");
            l_administratorParams.setBranchCode("102");
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =new WEB3Administrator(l_administratorParams);

            WEB3AdminIfoManualLapseConfirmRequest l_request = new WEB3AdminIfoManualLapseConfirmRequest();
            WEB3AdminIfoLapseTargetOrderCondition l_ifoLapseTargetOrderCondition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] branchCode = {"000"};
            String[] tradingTypeList = null;
            l_ifoLapseTargetOrderCondition.branchCode = branchCode;
            l_ifoLapseTargetOrderCondition.fuOpDiv = "Option";
            l_ifoLapseTargetOrderCondition.targetProductCode = "12";
            l_ifoLapseTargetOrderCondition.delivaryMonth = "01";
            l_ifoLapseTargetOrderCondition.strikePrice= "10";
            l_ifoLapseTargetOrderCondition.opProductType = "P";
            l_ifoLapseTargetOrderCondition.tradingTypeList = tradingTypeList;
            l_ifoLapseTargetOrderCondition.accountCode = "1234";

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("N8080");
            l_context.setBizDateType("1");
            l_context.setMarketCode("1");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            l_tradingTimeParams.setInstitutionCode(l_context.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_context.getBranchCode());
            l_tradingTimeParams.setBizDateType(l_context.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_context.getMarketCode());
            l_tradingTimeParams.setProductCode(l_context.getProductCode());
            l_tradingTimeParams.setTradingTimeType(l_context.getTradingTimeType());
            l_tradingTimeParams.setSessionType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("77");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
            l_orderexecutionEndParams.setCarryoverEndType(WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP);
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            OrderexecutionEndParams l_orderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams1.setInstitutionCode("77");
            l_orderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_orderexecutionEndParams1.setCarryoverEndType(WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP);
            l_orderexecutionEndParams1.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams1);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("77");
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_institutionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);

            l_request.ifoLapseTargetOrderCondition = l_ifoLapseTargetOrderCondition;
            l_adminIfoManualExpireServiceImpl.validateManualExpirePossibility( l_administrator,l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02446,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private class WEB3AdministratorForTest extends WEB3Administrator
    {

        public WEB3AdministratorForTest(AdministratorParams l_administratorParams)
        {
            super(l_administratorParams);
        }

        public void validateTradingPassword(String l_strPassword) throws WEB3BaseException
        {
            throw new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00009, l_strPassword);
        }

    }

    //二重起動チェック異常
    public void testValidateManualExpirePossibility_C0007()
    {
        final String STR_METHOD_NAME = " testValidateManualExpirePossibility_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("77");
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.MANUAL_EXPIRE);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setLastUpdater("sra501");

            l_adminPermissionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
            l_administratorTypeParams.setInstitutionCode("77");
            l_administratorTypeParams.setPermissionLevel("770");
            l_administratorTypeParams.setLastUpdater("my");
            l_administratorTypeParams.setDirAdminFlag(0);
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            l_administratorTypeParams.setPermissionLevelName("1234546");
            l_administratorTypeParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_administratorTypeParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorId(33381330001L);
            l_administratorParams.setAdministratorCode("330001");
            l_administratorParams.setInstitutionId(33L);
            l_administratorParams.setInstitutionCode("77");
            l_administratorParams.setLoginId(33381330003L);
            l_administratorParams.setPermissionLevel("770");
            l_administratorParams.setBranchCode("102");
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =new WEB3Administrator(l_administratorParams);

            WEB3AdminIfoManualLapseConfirmRequest l_request = new WEB3AdminIfoManualLapseConfirmRequest();
            WEB3AdminIfoLapseTargetOrderCondition l_ifoLapseTargetOrderCondition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] branchCode = {"000"};
            String[] tradingTypeList = {"123"};
            l_ifoLapseTargetOrderCondition.branchCode = branchCode;
            l_ifoLapseTargetOrderCondition.fuOpDiv = "Option";
            l_ifoLapseTargetOrderCondition.targetProductCode = "12";
            l_ifoLapseTargetOrderCondition.delivaryMonth = "01";
            l_ifoLapseTargetOrderCondition.strikePrice= "10";
            l_ifoLapseTargetOrderCondition.opProductType = "P";
            l_ifoLapseTargetOrderCondition.tradingTypeList = tradingTypeList;
            l_ifoLapseTargetOrderCondition.accountCode = "1234";

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("N8080");
            l_context.setBizDateType("1");
            l_context.setMarketCode("1");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            l_tradingTimeParams.setInstitutionCode(l_context.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_context.getBranchCode());
            l_tradingTimeParams.setBizDateType(l_context.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_context.getMarketCode());
            l_tradingTimeParams.setProductCode(l_context.getProductCode());
            l_tradingTimeParams.setTradingTimeType(l_context.getTradingTimeType());
            l_tradingTimeParams.setSessionType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("77");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
            l_orderexecutionEndParams.setCarryoverEndType(WEB3CarryoverEndTypeDef.COMPLETE_PROCESS);
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            OrderexecutionEndParams l_orderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams1.setInstitutionCode("77");
            l_orderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_orderexecutionEndParams1.setCarryoverEndType(WEB3CarryoverEndTypeDef.COMPLETE_PROCESS);
            l_orderexecutionEndParams1.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams1);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("77");
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_institutionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);

           //getデーモントリガー一覧getDaemonTriggerList() 測(*)二重起動チェック
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            //オンライン実行結果テーブル(online_run_status)
            OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();

            //l_processor.doDeleteQuery(l_onlineRunStatusParams.getPrimaryKey());
            TestDBUtility.deleteAll(l_onlineRunStatusParams.getRowType());

            l_onlineRunStatusParams.setInstitutionCode("77");
            l_onlineRunStatusParams.setProductType(ProductTypeEnum.IFO);
            l_onlineRunStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
            l_onlineRunStatusParams.setOnlineServiceDiv(WEB3OnlineServiceDiv.MANUAL_EXPIRE);
            l_onlineRunStatusParams.setAccountIdFrom(1000);
            l_onlineRunStatusParams.setAccountIdTo(9999);
            l_onlineRunStatusParams.setRunStatusDiv("5");//WEB3RunStatusDivDef.DEALING
            l_onlineRunStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_onlineRunStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_onlineRunStatusParams);

            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();

            //l_processor.doDeleteQuery(l_daemonTriggerParams.getPrimaryKey());
            TestDBUtility.deleteAll(l_daemonTriggerParams.getRowType());

            l_daemonTriggerParams.setThreadNo(123456L);
            l_daemonTriggerParams.setTriggerDate(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_daemonTriggerParams.setTriggerStatus("0");
            l_daemonTriggerParams.setTriggerType(WEB3DaemonTriggerTypeDef.IFO_MANUAL_EXPIRE);
            l_daemonTriggerParams.setRangeFrom(0L);
            l_daemonTriggerParams.setRangeTo(99L);
            l_daemonTriggerParams.setOrderRequestNumber("11");
            TestDBUtility.insertWithDel(l_daemonTriggerParams);

            l_request.ifoLapseTargetOrderCondition = l_ifoLapseTargetOrderCondition;
            l_adminIfoManualExpireServiceImpl.validateManualExpirePossibility( l_administrator,l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01992,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateManualExpirePossibility_C0008()
    {
        final String STR_METHOD_NAME = " testValidateManualExpirePossibility_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("77");
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.MANUAL_EXPIRE);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setLastUpdater("sra501");

            l_adminPermissionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
            l_administratorTypeParams.setInstitutionCode("77");
            l_administratorTypeParams.setPermissionLevel("770");
            l_administratorTypeParams.setLastUpdater("my");
            l_administratorTypeParams.setDirAdminFlag(0);
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            l_administratorTypeParams.setPermissionLevelName("1234546");
            l_administratorTypeParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_administratorTypeParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorId(33381330001L);
            l_administratorParams.setAdministratorCode("330001");
            l_administratorParams.setInstitutionId(33L);
            l_administratorParams.setInstitutionCode("77");
            l_administratorParams.setLoginId(33381330003L);
            l_administratorParams.setPermissionLevel("770");
            l_administratorParams.setBranchCode("102");
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =new WEB3Administrator(l_administratorParams);

            WEB3AdminIfoManualLapseConfirmRequest l_request = new WEB3AdminIfoManualLapseConfirmRequest();
            WEB3AdminIfoLapseTargetOrderCondition l_ifoLapseTargetOrderCondition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] branchCode = {"000"};
            String[] tradingTypeList = {"123"};
            l_ifoLapseTargetOrderCondition.branchCode = branchCode;
            l_ifoLapseTargetOrderCondition.fuOpDiv = "Option";
            l_ifoLapseTargetOrderCondition.targetProductCode = "12";
            l_ifoLapseTargetOrderCondition.delivaryMonth = "01";
            l_ifoLapseTargetOrderCondition.strikePrice= "10";
            l_ifoLapseTargetOrderCondition.opProductType = "P";
            l_ifoLapseTargetOrderCondition.tradingTypeList = tradingTypeList;
            l_ifoLapseTargetOrderCondition.accountCode = "1234";

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("N8080");
            l_context.setBizDateType("1");
            l_context.setMarketCode("1");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            l_tradingTimeParams.setInstitutionCode(l_context.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_context.getBranchCode());
            l_tradingTimeParams.setBizDateType(l_context.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_context.getMarketCode());
            l_tradingTimeParams.setProductCode(l_context.getProductCode());
            l_tradingTimeParams.setTradingTimeType(l_context.getTradingTimeType());
            l_tradingTimeParams.setSessionType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("77");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
            l_orderexecutionEndParams.setCarryoverEndType(WEB3CarryoverEndTypeDef.COMPLETE_PROCESS);
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            OrderexecutionEndParams l_orderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams1.setInstitutionCode("77");
            l_orderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_orderexecutionEndParams1.setCarryoverEndType(WEB3CarryoverEndTypeDef.COMPLETE_PROCESS);
            l_orderexecutionEndParams1.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams1);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("77");
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_institutionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);

            //getデーモントリガー一覧getDaemonTriggerList() 測(*)二重起動チェック
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            //オンライン実行結果テーブル(online_run_status)
            OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();


            //l_processor.doDeleteQuery(l_onlineRunStatusParams.getPrimaryKey());
            TestDBUtility.deleteAll(l_onlineRunStatusParams.getRowType());

            l_onlineRunStatusParams.setInstitutionCode("77");
            l_onlineRunStatusParams.setProductType(ProductTypeEnum.IFO);
            l_onlineRunStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
            l_onlineRunStatusParams.setOnlineServiceDiv(WEB3OnlineServiceDiv.MANUAL_EXPIRE);
            l_onlineRunStatusParams.setAccountIdFrom(1000);
            l_onlineRunStatusParams.setAccountIdTo(9999);
            l_onlineRunStatusParams.setRunStatusDiv("1");//WEB3RunStatusDivDef.DEALING
            l_onlineRunStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_onlineRunStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_onlineRunStatusParams);

            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();

            //l_processor.doDeleteQuery(l_daemonTriggerParams.getPrimaryKey());
            TestDBUtility.deleteAll(l_daemonTriggerParams.getRowType());

            l_daemonTriggerParams.setThreadNo(123456L);
            l_daemonTriggerParams.setTriggerDate(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_daemonTriggerParams.setTriggerStatus("1");
            l_daemonTriggerParams.setTriggerType(WEB3DaemonTriggerTypeDef.IFO_MANUAL_EXPIRE);
            l_daemonTriggerParams.setRangeFrom(0L);
            l_daemonTriggerParams.setRangeTo(99L);
            l_daemonTriggerParams.setOrderRequestNumber("11");
            TestDBUtility.insertWithDel(l_daemonTriggerParams);

            l_request.ifoLapseTargetOrderCondition = l_ifoLapseTargetOrderCondition;
            l_adminIfoManualExpireServiceImpl.validateManualExpirePossibility( l_administrator,l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01992,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateManualExpirePossibility_C0009()
    {
        final String STR_METHOD_NAME = " testValidateManualExpirePossibility_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("77");
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.MANUAL_EXPIRE);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setLastUpdater("sra501");

            l_adminPermissionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
            l_administratorTypeParams.setInstitutionCode("77");
            l_administratorTypeParams.setPermissionLevel("770");
            l_administratorTypeParams.setLastUpdater("my");
            l_administratorTypeParams.setDirAdminFlag(0);
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            l_administratorTypeParams.setPermissionLevelName("1234546");
            l_administratorTypeParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_administratorTypeParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorId(33381330001L);
            l_administratorParams.setAdministratorCode("330001");
            l_administratorParams.setInstitutionId(33L);
            l_administratorParams.setInstitutionCode("77");
            l_administratorParams.setLoginId(33381330003L);
            l_administratorParams.setPermissionLevel("770");
            l_administratorParams.setBranchCode("102");
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =new WEB3Administrator(l_administratorParams);

            WEB3AdminIfoManualLapseConfirmRequest l_request = new WEB3AdminIfoManualLapseConfirmRequest();
            WEB3AdminIfoLapseTargetOrderCondition l_ifoLapseTargetOrderCondition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] branchCode = {"000"};
            String[] tradingTypeList = {"123"};
            l_ifoLapseTargetOrderCondition.branchCode = branchCode;
            l_ifoLapseTargetOrderCondition.fuOpDiv = "2";
            l_ifoLapseTargetOrderCondition.targetProductCode = null;
            l_ifoLapseTargetOrderCondition.delivaryMonth = "01";
            l_ifoLapseTargetOrderCondition.strikePrice= "10";
            l_ifoLapseTargetOrderCondition.opProductType = "P";
            l_ifoLapseTargetOrderCondition.tradingTypeList = tradingTypeList;
            l_ifoLapseTargetOrderCondition.accountCode = "1234";
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("N8080");
            l_context.setBizDateType("1");
            l_context.setMarketCode("1");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            l_tradingTimeParams.setInstitutionCode(l_context.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_context.getBranchCode());
            l_tradingTimeParams.setBizDateType(l_context.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_context.getMarketCode());
            l_tradingTimeParams.setProductCode(l_context.getProductCode());
            l_tradingTimeParams.setTradingTimeType(l_context.getTradingTimeType());
            l_tradingTimeParams.setSessionType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("77");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
            l_orderexecutionEndParams.setCarryoverEndType(WEB3CarryoverEndTypeDef.COMPLETE_PROCESS);
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            OrderexecutionEndParams l_orderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams1.setInstitutionCode("77");
            l_orderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_orderexecutionEndParams1.setCarryoverEndType(WEB3CarryoverEndTypeDef.COMPLETE_PROCESS);
            l_orderexecutionEndParams1.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams1);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("77");
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_institutionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);

            QueryProcessor l_processor = Processors.getDefaultProcessor();
            //オンライン実行結果テーブル(online_run_status)
            OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();
            //使l_lisOnlineExecResultList = null。
            TestDBUtility.deleteAll(l_onlineRunStatusParams.getRowType());

            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();

            TestDBUtility.deleteAll(l_daemonTriggerParams.getRowType());

            l_daemonTriggerParams.setThreadNo(123456L);
            l_daemonTriggerParams.setTriggerDate(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_daemonTriggerParams.setTriggerStatus("1");
            l_daemonTriggerParams.setTriggerType(WEB3DaemonTriggerTypeDef.IFO_MANUAL_EXPIRE);
            l_daemonTriggerParams.setRangeFrom(0L);
            l_daemonTriggerParams.setRangeTo(99L);
            l_daemonTriggerParams.setOrderRequestNumber("11");
            TestDBUtility.insertWithDel(l_daemonTriggerParams);

            l_request.ifoLapseTargetOrderCondition = l_ifoLapseTargetOrderCondition;
            l_adminIfoManualExpireServiceImpl.validateManualExpirePossibility( l_administrator,l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    private class WEB3AdminIfoLapseTargetOrderConditionForTest extends WEB3AdminIfoLapseTargetOrderCondition
    {

        public  WEB3AdminIfoLapseTargetOrderConditionForTest()
        {
            super();
        }
        public void validate()
        {

        }
    }

    public void testGetinput_C0001()
    {
        final String STR_METHOD_NAME = " testGetinput_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminIfoManualLapseInputRequest l_request = new WEB3AdminIfoManualLapseInputRequest();
            l_adminIfoManualExpireServiceImpl.getInputScreen(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02174,l_ex.getErrorInfo());
        }
        catch (Exception e)
        {
           fail();
       }
    }

    //// validate権限exception
    public void testGetinput_C0002()
    {
        final String STR_METHOD_NAME = " testGetinput_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(0L));

            LoginInfo loginInfo = new LoginInfoImpl();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                loginInfo);

            WEB3AdminIfoManualLapseInputRequest l_request = new WEB3AdminIfoManualLapseInputRequestForTest();
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();

            l_administratorParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            l_adminIfoManualExpireServiceImpl.getInputScreen(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

   // validate注文繰越処理中(証券会社,String[])exception
    public void testGetinput_C0003()
    {
        final String STR_METHOD_NAME = " testGetinput_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(0L));

            LoginInfo loginInfo = new LoginInfoImpl();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                loginInfo);

            TestDBUtility.deleteAll(CalendarRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("N8080");
            l_context.setBizDateType("1");
            l_context.setMarketCode("1");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            l_tradingTimeParams.setInstitutionCode(l_context.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_context.getBranchCode());
            l_tradingTimeParams.setBizDateType(l_context.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_context.getMarketCode());
            l_tradingTimeParams.setProductCode(l_context.getProductCode());
            l_tradingTimeParams.setTradingTimeType(l_context.getTradingTimeType());
            l_tradingTimeParams.setSessionType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            InstitutionParams  l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("77");
            TestDBUtility.insertWithDel(l_institutionParams);
            InstitutionImpl l_institutionImpl = new InstitutionImpl(l_institutionParams);

            WEB3AdminIfoManualLapseInputRequest l_request = new WEB3AdminIfoManualLapseInputRequestForTest();
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("77");
            l_administratorParams.setPermissionLevel("770");
            l_administratorParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("77");
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory("C0304");
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("77");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_orderexecutionEndParams.setCarryoverEndType(WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP);
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            OrderexecutionEndParams l_orderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams1.setInstitutionCode("77");
            l_orderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
            l_orderexecutionEndParams1.setCarryoverEndType(WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP);
            l_orderexecutionEndParams1.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams1);

            WEB3Administrator l_administrator =new WEB3Administrator(l_administratorParams);
            l_adminIfoManualExpireServiceImpl.getInputScreen(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02446,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //一條數據
    public void testGetinput_C0004() throws DataException
    {
        final String STR_METHOD_NAME = " testGetinput_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        methodId = "testGetinput_C0004()";
        l_adminIfoManualExpireServiceImpl =
            new WEB3AdminIfoManualExpireServiceImplForTest();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(0L));

            LoginInfo loginInfo = new LoginInfoImpl();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                loginInfo);

            InstitutionParams  l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            l_institutionParams.setInstitutionCode("77");
            TestDBUtility.insertWithDel(l_institutionParams);
            InstitutionImpl l_institutionImpl = new InstitutionImpl(l_institutionParams);

            WEB3AdminIfoManualLapseInputRequest l_request = new WEB3AdminIfoManualLapseInputRequestForTest();
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            l_administratorParams.setInstitutionCode("77");
            l_administratorParams.setPermissionLevel("770");
            l_administratorParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            l_adminPermissionParams.setInstitutionCode("77");
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory("C0304");
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            BranchIndexDealtCondParams l_branchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
            TestDBUtility.deleteAll(l_branchIndexDealtCondParams.getRowType());
            l_branchIndexDealtCondParams.setMarketCode("33");
            l_branchIndexDealtCondParams.setInstitutionCode("77");
            l_branchIndexDealtCondParams.setTargetProductCode("66");
            l_branchIndexDealtCondParams.setEnableOrder(WEB3DealtDef.CAN_DEALT);
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);

            new BranchIndexDealtCondParams(l_branchIndexDealtCondParams);
            WEB3AdminIfoManualLapseInputResponse l_response = l_adminIfoManualExpireServiceImpl.getInputScreen(l_request);

            assertEquals("66",l_response.targetProductList[0]);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //0條數據
    public void testGetinput_C0005() throws DataException
    {
        final String STR_METHOD_NAME = " testGetinput_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_adminIfoManualExpireServiceImpl =
            new WEB3AdminIfoManualExpireServiceImplForTest();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(0L));

            LoginInfo loginInfo = new LoginInfoImpl();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                loginInfo);

            InstitutionParams  l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            l_institutionParams.setInstitutionCode("66");
            TestDBUtility.insertWithDel(l_institutionParams);
            InstitutionImpl l_institutionImpl = new InstitutionImpl(l_institutionParams);

            WEB3AdminIfoManualLapseInputRequest l_request = new WEB3AdminIfoManualLapseInputRequestForTest();
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            l_administratorParams.setInstitutionCode("66");
            l_administratorParams.setPermissionLevel("770");
            l_administratorParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            l_adminPermissionParams.setInstitutionCode("66");
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory("C0304");
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            BranchIndexDealtCondParams l_branchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
            TestDBUtility.deleteAll(l_branchIndexDealtCondParams.getRowType());
            l_branchIndexDealtCondParams.setInstitutionCode("77");
            l_branchIndexDealtCondParams.setTargetProductCode("66");
            l_branchIndexDealtCondParams.setEnableOrder(WEB3DealtDef.CAN_DEALT);
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);

            new BranchIndexDealtCondParams(l_branchIndexDealtCondParams);
            WEB3AdminIfoManualLapseInputResponse l_response = l_adminIfoManualExpireServiceImpl.getInputScreen(l_request);

            assertEquals(0,l_response.targetProductList.length);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //多條數據
    public void testGetinput_C0006() throws DataException
    {
        final String STR_METHOD_NAME = " testGetinput_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        methodId = "testGetinput_C0006()";
        l_adminIfoManualExpireServiceImpl =
            new WEB3AdminIfoManualExpireServiceImplForTest();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(0L));

            LoginInfo loginInfo = new LoginInfoImpl();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                loginInfo);

            InstitutionParams  l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            l_institutionParams.setInstitutionCode("77");
            TestDBUtility.insertWithDel(l_institutionParams);
            InstitutionImpl l_institutionImpl = new InstitutionImpl(l_institutionParams);

            WEB3AdminIfoManualLapseInputRequest l_request = new WEB3AdminIfoManualLapseInputRequestForTest();
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            l_administratorParams.setInstitutionCode("77");
            l_administratorParams.setPermissionLevel("770");
            l_administratorParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            l_adminPermissionParams.setInstitutionCode("77");
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory("C0304");
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            BranchIndexDealtCondParams l_branchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
            TestDBUtility.deleteAll(l_branchIndexDealtCondParams.getRowType());
            l_branchIndexDealtCondParams.setInstitutionCode("77");
            l_branchIndexDealtCondParams.setTargetProductCode("66");
            l_branchIndexDealtCondParams.setEnableOrder(WEB3DealtDef.CAN_DEALT);

            new BranchIndexDealtCondParams(l_branchIndexDealtCondParams);
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);

            BranchIndexDealtCondParams l_branchIndexDealtCondParams1 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams1.setInstitutionCode("77");
            l_branchIndexDealtCondParams1.setTargetProductCode("77");
            l_branchIndexDealtCondParams1.setEnableOrder(WEB3DealtDef.CAN_DEALT);
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams1);

            BranchIndexDealtCondParams l_branchIndexDealtCondParams2 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams2.setInstitutionCode("77");
            l_branchIndexDealtCondParams2.setTargetProductCode("88");
            l_branchIndexDealtCondParams2.setEnableOrder(WEB3DealtDef.CAN_DEALT);
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams2);

            WEB3AdminIfoManualLapseInputResponse l_response = l_adminIfoManualExpireServiceImpl.getInputScreen(l_request);

            //int l_strLength = l_response.targetProductCodeList.length;
            int flag = 0 ;
            String[] l_strArry = {"66","77","88"};
            for (int i = 0; i < l_strArry.length; i++)
            {
                for(int j = 0; j < l_strArry.length; j++)
                if (l_strArry[i].equals(l_response.targetProductList[j]))
                {
                    flag = flag + 1;
                }
            }
            assertEquals(3,flag);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private class WEB3AdminIfoManualLapseInputRequestForTest extends WEB3AdminIfoManualLapseInputRequest
    {
        public  WEB3AdminIfoManualLapseInputRequestForTest()
        {
            super();
        }
        public void validate() throws WEB3BusinessLayerException
        {

        }
    }

    // validate権限
    public void testValidateStatus_C0001()
    {
        final String STR_METHOD_NAME = " testValidateStatus_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(0L));

            LoginInfo loginInfo = new LoginInfoImpl();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                loginInfo);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("88");
            l_administratorParams.setPermissionLevel("770");
            l_administratorParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =new WEB3Administrator(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams  = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("11");
            l_adminPermissionParams.setPermissionLevel("110");
            l_adminPermissionParams.setTransactionCategory("C0304");

            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3AdminIfoManualLapseStatusRequest l_request =  new WEB3AdminIfoManualLapseStatusRequest();
            l_adminIfoManualExpireServiceImpl.validateStatus(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    // オンライン実行結果レコードが取得できなかった場合l_lisOnlineExecResultList == null
    public void testValidateStatus_C0002()
    {
        final String STR_METHOD_NAME = "testValidateStatus_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("11");
            l_administratorParams.setPermissionLevel("770");
            l_administratorParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =new WEB3Administrator(l_administratorParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(0L));

            LoginInfo loginInfo = new LoginInfoImpl();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                loginInfo);

            AdminPermissionParams l_adminPermissionParams  = TestDBUtility.getAdminPermissionRow();
            // TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            l_adminPermissionParams.setInstitutionCode("11");
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory("C0304");

            TestDBUtility.insertWithDel(l_adminPermissionParams);

            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();

            l_daemonTriggerParams.setThreadNo(123456L);
            l_daemonTriggerParams.setTriggerDate(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_daemonTriggerParams.setTriggerStatus("5");
            l_daemonTriggerParams.setTriggerType(WEB3DaemonTriggerTypeDef.IFO_MANUAL_EXPIRE);
            l_daemonTriggerParams.setRangeFrom(0L);
            l_daemonTriggerParams.setRangeTo(99L);
            l_daemonTriggerParams.setOrderRequestNumber("11");
            TestDBUtility.insertWithDel(l_daemonTriggerParams);

            OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();
            TestDBUtility.deleteAll(l_onlineRunStatusParams.getRowType());
            l_onlineRunStatusParams.setInstitutionCode("66");
            l_onlineRunStatusParams.setProductType(ProductTypeEnum.IFO);
            l_onlineRunStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
            l_onlineRunStatusParams.setOnlineServiceDiv(WEB3OnlineServiceDiv.AUTO_EXECUTE);//l_lisOnlineExecResultList==null
            l_onlineRunStatusParams.setAccountIdFrom(1000);
            l_onlineRunStatusParams.setAccountIdTo(9999);
            l_onlineRunStatusParams.setRunStatusDiv("5");//WEB3RunStatusDivDef.DEALING = "5";
            l_onlineRunStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_onlineRunStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_onlineRunStatusParams);

            WEB3AdminIfoManualLapseStatusRequest l_request =  new WEB3AdminIfoManualLapseStatusRequest();


            WEB3AdminIfoManualLapseStatusResponse l_response = new WEB3AdminIfoManualLapseStatusResponse();
            l_response = l_adminIfoManualExpireServiceImpl.validateStatus(l_request);
            assertEquals(l_response.lapseStatus ,WEB3RunStatusDivDef.DEALING);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    //取得したデーモントリガーレコードの件数と、オンライン実行結果レコードの件数が異なる場合
    public void testValidateStatus_C0003()
    {
        final String STR_METHOD_NAME = "testValidateStatus_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("66");
            l_administratorParams.setPermissionLevel("770");
            l_administratorParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =new WEB3Administrator(l_administratorParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(0L));

            LoginInfo loginInfo = new LoginInfoImpl();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                loginInfo);

            AdminPermissionParams l_adminPermissionParams  = TestDBUtility.getAdminPermissionRow();
            // TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            l_adminPermissionParams.setInstitutionCode("66");//11
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.MANUAL_EXPIRE);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);//BooleanEnum.TRUE
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();

            l_daemonTriggerParams.setThreadNo(123456L);
            l_daemonTriggerParams.setTriggerDate(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_daemonTriggerParams.setTriggerStatus("5");
            l_daemonTriggerParams.setTriggerType(WEB3DaemonTriggerTypeDef.IFO_MANUAL_EXPIRE);
            l_daemonTriggerParams.setRangeFrom(0L);
            l_daemonTriggerParams.setRangeTo(99L);
            l_daemonTriggerParams.setOrderRequestNumber("11");
            TestDBUtility.insertWithDel(l_daemonTriggerParams);

            DaemonTriggerParams l_daemonTriggerParams1 = new DaemonTriggerParams();

            l_daemonTriggerParams1.setThreadNo(1234L);
            l_daemonTriggerParams1.setTriggerDate(WEB3DateUtility.getDate("20040228","yyyyMMdd"));
            l_daemonTriggerParams1.setTriggerStatus("5");
            l_daemonTriggerParams1.setTriggerType(WEB3DaemonTriggerTypeDef.IFO_MANUAL_EXPIRE);
            l_daemonTriggerParams1.setRangeFrom(11L);
            l_daemonTriggerParams1.setRangeTo(99L);
            l_daemonTriggerParams1.setOrderRequestNumber("11");
            TestDBUtility.insertWithDel(l_daemonTriggerParams1);

            OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();
            l_onlineRunStatusParams.setInstitutionCode("66");
            l_onlineRunStatusParams.setProductType(ProductTypeEnum.IFO);
            l_onlineRunStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
            l_onlineRunStatusParams.setOnlineServiceDiv(WEB3OnlineServiceDiv.AUTO_EXECUTE);//l_lisOnlineExecResultList==null
            l_onlineRunStatusParams.setAccountIdFrom(1000);
            l_onlineRunStatusParams.setAccountIdTo(9999);
            l_onlineRunStatusParams.setRunStatusDiv("5");//WEB3RunStatusDivDef.DEALING = "5";
            l_onlineRunStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_onlineRunStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_onlineRunStatusParams);

            WEB3AdminIfoManualLapseStatusRequest l_request =  new WEB3AdminIfoManualLapseStatusRequest();

            WEB3AdminIfoManualLapseStatusResponse l_response = new WEB3AdminIfoManualLapseStatusResponse();
            l_response =l_adminIfoManualExpireServiceImpl.validateStatus(l_request);
            assertEquals(l_response.lapseStatus ,WEB3RunStatusDivDef.DEALING);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    // (*)プロパティセットwhenl_lisOnlineExecResultList != null
    //取得したデーモントリガーレコード.処理状態に"処理中"が1件でも存在する場合
    //WEB3RunStatusDivDef.DEALING.equals(l_onlineRunStatusRow.getRunStatusDiv()
    public void testValidateStatus_C0004()
    {
        final String STR_METHOD_NAME = "testValidateStatus_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);


        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("66");
            l_administratorParams.setPermissionLevel("770");
            l_administratorParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =new WEB3Administrator(l_administratorParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(0L));

            LoginInfo loginInfo = new LoginInfoImpl();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                loginInfo);

            AdminPermissionParams l_adminPermissionParams  = TestDBUtility.getAdminPermissionRow();
            // TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            l_adminPermissionParams.setInstitutionCode("11");//11
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory("C0304");

            TestDBUtility.insertWithDel(l_adminPermissionParams);

            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();

            l_daemonTriggerParams.setThreadNo(123456L);
            l_daemonTriggerParams.setTriggerDate(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_daemonTriggerParams.setTriggerStatus("5");
            l_daemonTriggerParams.setTriggerType(WEB3DaemonTriggerTypeDef.IFO_MANUAL_EXPIRE);
            l_daemonTriggerParams.setRangeFrom(0L);
            l_daemonTriggerParams.setRangeTo(99L);
            l_daemonTriggerParams.setOrderRequestNumber("11");
            TestDBUtility.insertWithDel(l_daemonTriggerParams);

            OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();
            l_onlineRunStatusParams.setInstitutionCode("66");
            l_onlineRunStatusParams.setProductType(ProductTypeEnum.IFO);
            l_onlineRunStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
            l_onlineRunStatusParams.setOnlineServiceDiv(WEB3OnlineServiceDiv.AUTO_EXECUTE);//l_lisOnlineExecResultList==null
            l_onlineRunStatusParams.setAccountIdFrom(1000);
            l_onlineRunStatusParams.setAccountIdTo(9999);
            l_onlineRunStatusParams.setRunStatusDiv("5");//WEB3RunStatusDivDef.DEALING = "5";
            l_onlineRunStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_onlineRunStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_onlineRunStatusParams);

            WEB3AdminIfoManualLapseStatusRequest l_request =  new WEB3AdminIfoManualLapseStatusRequest();
            WEB3AdminIfoManualLapseStatusResponse l_response = new WEB3AdminIfoManualLapseStatusResponse();
            l_response =l_adminIfoManualExpireServiceImpl.validateStatus(l_request);
            assertEquals(l_response.lapseStatus ,WEB3RunStatusDivDef.DEALING);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    // (*)プロパティセットwhenl_lisOnlineExecResultList != null
    //取得したデーモントリガーレコード.処理状態に"処理中"が1件でも存在する場合
    //WEB3DaemonTriggerStatusDef.THREAD_PROCESSING.equals(l_daemonTriggerRow.getTriggerStatus()
    public void testValidateStatus_C0005()
    {
        final String STR_METHOD_NAME = "testValidateStatus_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("66");
            l_administratorParams.setPermissionLevel("770");
            l_administratorParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =new WEB3Administrator(l_administratorParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(0L));

            LoginInfo loginInfo = new LoginInfoImpl();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                loginInfo);

            AdminPermissionParams l_adminPermissionParams  = TestDBUtility.getAdminPermissionRow();
            // TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            l_adminPermissionParams.setInstitutionCode("11");
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory("C0304");

            TestDBUtility.insertWithDel(l_adminPermissionParams);

            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();

            l_daemonTriggerParams.setThreadNo(123456L);
            l_daemonTriggerParams.setTriggerDate(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_daemonTriggerParams.setTriggerStatus("1");
            l_daemonTriggerParams.setTriggerType(WEB3DaemonTriggerTypeDef.IFO_MANUAL_EXPIRE);
            l_daemonTriggerParams.setRangeFrom(0L);
            l_daemonTriggerParams.setRangeTo(99L);
            l_daemonTriggerParams.setOrderRequestNumber("11");
            TestDBUtility.insertWithDel(l_daemonTriggerParams);

            OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();
            l_onlineRunStatusParams.setInstitutionCode("66");
            l_onlineRunStatusParams.setProductType(ProductTypeEnum.IFO);
            l_onlineRunStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
            l_onlineRunStatusParams.setOnlineServiceDiv(WEB3OnlineServiceDiv.AUTO_EXECUTE);//l_lisOnlineExecResultList==null
            l_onlineRunStatusParams.setAccountIdFrom(1000);
            l_onlineRunStatusParams.setAccountIdTo(9999);
            l_onlineRunStatusParams.setRunStatusDiv("3");
            l_onlineRunStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_onlineRunStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_onlineRunStatusParams);

            WEB3AdminIfoManualLapseStatusRequest l_request =  new WEB3AdminIfoManualLapseStatusRequest();

            WEB3AdminIfoManualLapseStatusResponse l_response = new WEB3AdminIfoManualLapseStatusResponse();
            l_response = l_adminIfoManualExpireServiceImpl.validateStatus(l_request);
            assertEquals(l_response.lapseStatus ,WEB3RunStatusDivDef.DEALING);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    // (*)プロパティセットwhenl_lisOnlineExecResultList != null
    //取得したデーモントリガーレコード.処理状態に"処理中"が1件でも存在する場合
    //!WEB3DaemonTriggerStatusDef.THREAD_IDLE.equals(l_daemonTriggerRow.getTriggerStatus()
    public void testValidateStatus_C0006()
    {
        final String STR_METHOD_NAME = "testValidateStatus_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("66");
            l_administratorParams.setPermissionLevel("770");
            l_administratorParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =new WEB3Administrator(l_administratorParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(0L));

            LoginInfo loginInfo = new LoginInfoImpl();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                loginInfo);

            AdminPermissionParams l_adminPermissionParams  = TestDBUtility.getAdminPermissionRow();
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            l_adminPermissionParams.setInstitutionCode("66");
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setTransactionCategory("C0304");
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
            TestDBUtility.deleteAll(l_daemonTriggerParams.getRowType());
            l_daemonTriggerParams.setThreadNo(123456L);
            l_daemonTriggerParams.setTriggerDate(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_daemonTriggerParams.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_API_CALL);
            l_daemonTriggerParams.setTriggerType(WEB3DaemonTriggerTypeDef.IFO_MANUAL_EXPIRE);
            l_daemonTriggerParams.setRangeFrom(0L);
            l_daemonTriggerParams.setRangeTo(99L);
            l_daemonTriggerParams.setOrderRequestNumber("11");
            TestDBUtility.insertWithDel(l_daemonTriggerParams);

            OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();
            TestDBUtility.deleteAll(l_onlineRunStatusParams.getRowType());
            l_onlineRunStatusParams.setInstitutionCode("66");
            l_onlineRunStatusParams.setProductType(ProductTypeEnum.IFO);
            l_onlineRunStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
            l_onlineRunStatusParams.setOnlineServiceDiv(WEB3OnlineServiceDiv.MANUAL_EXPIRE);//l_lisOnlineExecResultList==null
            l_onlineRunStatusParams.setAccountIdFrom(1000);                     //AUTO_EXECUTE??
            l_onlineRunStatusParams.setAccountIdTo(9999);
            l_onlineRunStatusParams.setRunStatusDiv("3");
            l_onlineRunStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_onlineRunStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_onlineRunStatusParams);

            WEB3AdminIfoManualLapseStatusRequest l_request =  new WEB3AdminIfoManualLapseStatusRequest();
            WEB3AdminIfoManualLapseStatusResponse l_response = new WEB3AdminIfoManualLapseStatusResponse();
            l_response = l_adminIfoManualExpireServiceImpl.validateStatus(l_request);
            assertEquals(l_response.lapseStatus ,WEB3RunStatusDivDef.ERROR);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    // (*)プロパティセットwhenl_lisOnlineExecResultList != null
    //取得したデーモントリガーレコード.処理状態に"処理中"が1件でも存在する場合
    //!WEB3RunStatusDivDef.DEALED.equals(l_onlineRunStatusRow.getRunStatusDiv())
    public void testValidateStatus_C0007()
    {
        final String STR_METHOD_NAME = "testValidateStatus_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("66");
            l_administratorParams.setPermissionLevel("770");
            l_administratorParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =new WEB3Administrator(l_administratorParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(0L));

            LoginInfo loginInfo = new LoginInfoImpl();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                loginInfo);

            AdminPermissionParams l_adminPermissionParams  = TestDBUtility.getAdminPermissionRow();
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            l_adminPermissionParams.setInstitutionCode("66");
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory("C0304");

            TestDBUtility.insertWithDel(l_adminPermissionParams);

            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();

            l_daemonTriggerParams.setThreadNo(123456L);
            l_daemonTriggerParams.setTriggerDate(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_daemonTriggerParams.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_IDLE);
            l_daemonTriggerParams.setTriggerType(WEB3DaemonTriggerTypeDef.IFO_MANUAL_EXPIRE);
            l_daemonTriggerParams.setRangeFrom(0L);
            l_daemonTriggerParams.setRangeTo(99L);
            l_daemonTriggerParams.setOrderRequestNumber("11");
            TestDBUtility.insertWithDel(l_daemonTriggerParams);

            OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();
            l_onlineRunStatusParams.setInstitutionCode("66");
            l_onlineRunStatusParams.setProductType(ProductTypeEnum.IFO);
            l_onlineRunStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
            l_onlineRunStatusParams.setOnlineServiceDiv(WEB3OnlineServiceDiv.MANUAL_EXPIRE);//l_lisOnlineExecResultList!=null
            l_onlineRunStatusParams.setAccountIdFrom(1000);
            l_onlineRunStatusParams.setAccountIdTo(9999);
            l_onlineRunStatusParams.setRunStatusDiv(WEB3RunStatusDivDef.NOT_DEAL);
            l_onlineRunStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_onlineRunStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_onlineRunStatusParams);

            WEB3AdminIfoManualLapseStatusRequest l_request =  new WEB3AdminIfoManualLapseStatusRequest();
            WEB3AdminIfoManualLapseStatusResponse l_response = new WEB3AdminIfoManualLapseStatusResponse();
            l_response = l_adminIfoManualExpireServiceImpl.validateStatus(l_request);
            assertEquals(l_response.lapseStatus ,WEB3RunStatusDivDef.ERROR);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //l_intSize==0時(getデーモントリガー一覧( )==null)會抛異常
    // 對異常進行判斷
    public void testValidateStatus_C0008()
    {
        final String STR_METHOD_NAME = "testValidateStatus_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("66");
            l_administratorParams.setPermissionLevel("770");
            l_administratorParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =new WEB3Administrator(l_administratorParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(0L));

            LoginInfo loginInfo = new LoginInfoImpl();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                loginInfo);

            AdminPermissionParams l_adminPermissionParams  = TestDBUtility.getAdminPermissionRow();
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            l_adminPermissionParams.setInstitutionCode("66");//11
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory("C0304");

            TestDBUtility.insertWithDel(l_adminPermissionParams);

            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();

            l_daemonTriggerParams.setThreadNo(123456L);
            l_daemonTriggerParams.setTriggerDate(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_daemonTriggerParams.setTriggerStatus("5");
            l_daemonTriggerParams.setTriggerType(WEB3DaemonTriggerTypeDef.EXECUTE_FUTURE);//l_lisDaemonTriggerList==null
            l_daemonTriggerParams.setRangeFrom(0L);
            l_daemonTriggerParams.setRangeTo(99L);
            l_daemonTriggerParams.setOrderRequestNumber("11");
            TestDBUtility.insertWithDel(l_daemonTriggerParams);

            OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();
            l_onlineRunStatusParams.setInstitutionCode("66");
            l_onlineRunStatusParams.setProductType(ProductTypeEnum.IFO);
            l_onlineRunStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
            l_onlineRunStatusParams.setOnlineServiceDiv(WEB3OnlineServiceDiv.MANUAL_EXPIRE);//l_lisOnlineExecResultList!=null
            l_onlineRunStatusParams.setAccountIdFrom(1000);
            l_onlineRunStatusParams.setAccountIdTo(9999);
            l_onlineRunStatusParams.setRunStatusDiv("5");//WEB3RunStatusDivDef.DEALING = "5";
            l_onlineRunStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_onlineRunStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_onlineRunStatusParams);

            WEB3AdminIfoManualLapseStatusRequest l_request =  new WEB3AdminIfoManualLapseStatusRequest();
            WEB3AdminIfoManualLapseStatusResponse l_response = new WEB3AdminIfoManualLapseStatusResponse();
            l_response = l_adminIfoManualExpireServiceImpl.validateStatus(l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005 ,l_ex.getErrorInfo());
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //多條
    public void testValidateStatus_C0009()
    {
        final String STR_METHOD_NAME = "testValidateStatus_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("66");
            l_administratorParams.setPermissionLevel("770");
            l_administratorParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =new WEB3Administrator(l_administratorParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(0L));

            LoginInfo loginInfo = new LoginInfoImpl();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                loginInfo);

            AdminPermissionParams l_adminPermissionParams  = TestDBUtility.getAdminPermissionRow();
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            l_adminPermissionParams.setInstitutionCode("66");//11
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory("C0304");

            TestDBUtility.insertWithDel(l_adminPermissionParams);

            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();

            l_daemonTriggerParams.setThreadNo(123456L);
            l_daemonTriggerParams.setTriggerDate(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_daemonTriggerParams.setTriggerStatus("1");
            l_daemonTriggerParams.setTriggerType(WEB3DaemonTriggerTypeDef.IFO_MANUAL_EXPIRE);
            l_daemonTriggerParams.setRangeFrom(0L);
            l_daemonTriggerParams.setRangeTo(99L);
            l_daemonTriggerParams.setOrderRequestNumber("11");
            TestDBUtility.insertWithDel(l_daemonTriggerParams);

            DaemonTriggerParams l_daemonTriggerParams1 = new DaemonTriggerParams();

            l_daemonTriggerParams1.setThreadNo(1234L);
            l_daemonTriggerParams1.setTriggerDate(WEB3DateUtility.getDate("20040228","yyyyMMdd"));
            l_daemonTriggerParams1.setTriggerStatus("1");
            l_daemonTriggerParams1.setTriggerType(WEB3DaemonTriggerTypeDef.IFO_MANUAL_EXPIRE);
            l_daemonTriggerParams1.setRangeFrom(11L);
            l_daemonTriggerParams1.setRangeTo(99L);
            l_daemonTriggerParams1.setOrderRequestNumber("11");
            TestDBUtility.insertWithDel(l_daemonTriggerParams1);

            DaemonTriggerParams l_daemonTriggerParams2 = new DaemonTriggerParams();

            l_daemonTriggerParams2.setThreadNo(1234L);
            l_daemonTriggerParams2.setTriggerDate(WEB3DateUtility.getDate("20040228","yyyyMMdd"));
            l_daemonTriggerParams2.setTriggerStatus("1");//WEB3DaemonTriggerStatusDef.THREAD_PROCESSING
            l_daemonTriggerParams2.setTriggerType(WEB3DaemonTriggerTypeDef.IFO_MANUAL_EXPIRE);
            l_daemonTriggerParams2.setRangeFrom(11L);
            l_daemonTriggerParams2.setRangeTo(99L);
            l_daemonTriggerParams2.setOrderRequestNumber("10");
            TestDBUtility.insertWithDel(l_daemonTriggerParams2);

            OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();
            l_onlineRunStatusParams.setInstitutionCode("66");
            l_onlineRunStatusParams.setProductType(ProductTypeEnum.IFO);
            l_onlineRunStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
            l_onlineRunStatusParams.setOnlineServiceDiv(WEB3OnlineServiceDiv.AUTO_EXECUTE);//l_lisOnlineExecResultList==null
            l_onlineRunStatusParams.setAccountIdFrom(1000);
            l_onlineRunStatusParams.setAccountIdTo(9999);
            l_onlineRunStatusParams.setRunStatusDiv("5");//WEB3RunStatusDivDef.DEALING = "5";
            l_onlineRunStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_onlineRunStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_onlineRunStatusParams);

            WEB3AdminIfoManualLapseStatusRequest l_request =  new WEB3AdminIfoManualLapseStatusRequest();

            WEB3AdminIfoManualLapseStatusResponse l_response = new WEB3AdminIfoManualLapseStatusResponse();
            l_response = l_adminIfoManualExpireServiceImpl.validateStatus(l_request);
            assertEquals(l_response.lapseStatus ,WEB3RunStatusDivDef.DEALING);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateStatus_C0010()
    {
        final String STR_METHOD_NAME = "testValidateStatus_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME); 
        
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("66");
            l_administratorParams.setPermissionLevel("770");
            l_administratorParams.setLoginId(0L);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_administrator =new WEB3Administrator(l_administratorParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(0L));

              LoginInfo loginInfo = new LoginInfoImpl();

              TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                loginInfo);
              
            AdminPermissionParams l_adminPermissionParams  = TestDBUtility.getAdminPermissionRow();
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            l_adminPermissionParams.setInstitutionCode("66");//11
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory("C0304");
            
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
            TestDBUtility.deleteAll(l_daemonTriggerParams.getRowType());
            l_daemonTriggerParams.setThreadNo(123456L);
            l_daemonTriggerParams.setTriggerDate(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_daemonTriggerParams.setTriggerStatus("0");//WEB3DaemonTriggerStatusDef.THREAD_IDLE
            l_daemonTriggerParams.setTriggerType(WEB3DaemonTriggerTypeDef.IFO_MANUAL_EXPIRE);
            l_daemonTriggerParams.setRangeFrom(0L);
            l_daemonTriggerParams.setRangeTo(99L);
            l_daemonTriggerParams.setOrderRequestNumber("11");
            TestDBUtility.insertWithDel(l_daemonTriggerParams);
            
            OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();
            TestDBUtility.deleteAll(l_onlineRunStatusParams.getRowType());
            l_onlineRunStatusParams.setInstitutionCode("66");
            l_onlineRunStatusParams.setProductType(ProductTypeEnum.IFO);
            l_onlineRunStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
            l_onlineRunStatusParams.setOnlineServiceDiv(WEB3OnlineServiceDiv.MANUAL_EXPIRE);//l_lisOnlineExecResultList!=null
            l_onlineRunStatusParams.setAccountIdFrom(1000);
            l_onlineRunStatusParams.setAccountIdTo(9999);
            l_onlineRunStatusParams.setRunStatusDiv("1");//WEB3RunStatusDivDef.DEALED  = "1";
            l_onlineRunStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_onlineRunStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_onlineRunStatusParams);
            
             
            WEB3AdminIfoManualLapseStatusRequest l_request =  new WEB3AdminIfoManualLapseStatusRequest();
            
            WEB3AdminIfoManualLapseStatusResponse l_response = new WEB3AdminIfoManualLapseStatusResponse();
            l_response = l_adminIfoManualExpireServiceImpl.validateStatus(l_request);
            assertEquals(l_response.lapseStatus ,WEB3RunStatusDivDef.DEALED);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException e)
        {
            e.printStackTrace();
            fail();
        }
    } 
    /**
     * getデーモントリガー一覧
     * @@author 稲本潤に(中訊)
     */
    public void testGetDaemonTriggerList_0001()
    {
        final String STR_METHOD_NAME = " testGetDaemonTriggerList_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        RowType l_rowType = new RowType("daemon_trigger", "session");

        try
        {
            TestDBUtility.deleteAll(l_rowType);
            //1
            String trigger_type = "W";
            long thread_no = 101010L;
            String order_request_number = "01";
            long range_from = 101010L;
            long range_to = 909090L;
            String trigger_status = "1";
            Timestamp tamp = Timestamp.valueOf("2007-02-06 14:15:16");

            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
            l_daemonTriggerParams.setTriggerType(trigger_type);//W
            l_daemonTriggerParams.setThreadNo(thread_no);
            l_daemonTriggerParams.setOrderRequestNumber(order_request_number);
            l_daemonTriggerParams.setRangeFrom(range_from);
            l_daemonTriggerParams.setRangeTo(range_to);
            l_daemonTriggerParams.setTriggerStatus(trigger_status);
            l_daemonTriggerParams.setTriggerDate(tamp);

            TestDBUtility.insertWithDel(l_daemonTriggerParams);

            //2
            String trigger_type1 = "W";
            long thread_no1 = 212121L;
            String order_request_number1 = "01";
            long range_from1 = 434343;
            long range_to1 = 999999;
            String trigger_status1 = "1";
            Timestamp tamp1 = Timestamp.valueOf("2005-07-03 14:15:18");

            DaemonTriggerParams l_daemonTriggerParams1 = new DaemonTriggerParams();
            l_daemonTriggerParams1.setTriggerType(trigger_type1);//W
            l_daemonTriggerParams1.setThreadNo(thread_no1);
            l_daemonTriggerParams1.setOrderRequestNumber(order_request_number1);
            l_daemonTriggerParams1.setRangeFrom(range_from1);
            l_daemonTriggerParams1.setRangeTo(range_to1);
            l_daemonTriggerParams1.setTriggerStatus(trigger_status1);
            l_daemonTriggerParams1.setTriggerDate(tamp1);

            TestDBUtility.insertWithDel(l_daemonTriggerParams1);

            //3
            String trigger_type2 = "2";
            long thread_no2 = 202020L;
            String order_request_number2 = "02";
            long range_from2 = 202020L;
            long range_to2 = 909090L;
            String trigger_status2 = "2";
            Timestamp tamp2 = Timestamp.valueOf("2005-07-03 14:25:28");

            DaemonTriggerParams l_daemonTriggerParams2 = new DaemonTriggerParams();
            l_daemonTriggerParams2.setTriggerType(trigger_type2);//W
            l_daemonTriggerParams2.setThreadNo(thread_no2);
            l_daemonTriggerParams2.setOrderRequestNumber(order_request_number2);
            l_daemonTriggerParams2.setRangeFrom(range_from2);
            l_daemonTriggerParams2.setRangeTo(range_to2);
            l_daemonTriggerParams2.setTriggerStatus(trigger_status2);
            l_daemonTriggerParams2.setTriggerDate(tamp2);

            TestDBUtility.insertWithDel(l_daemonTriggerParams2);

            List actualList = l_adminIfoManualExpireServiceImpl.getDaemonTriggerList();

            assertEquals(2, actualList.size());

            DaemonTriggerParams l_processors = (DaemonTriggerParams) actualList.get(0);

            assertEquals(trigger_type, l_processors.getTriggerType());
            assertEquals(thread_no, l_processors.getThreadNo());
            assertEquals(order_request_number, l_processors.getOrderRequestNumber());
            assertEquals(range_from, l_processors.getRangeFrom());
            assertEquals(range_to, l_processors.getRangeTo());
            assertEquals(trigger_status, l_processors.getTriggerStatus());
            assertEquals(tamp, l_processors.getTriggerDate());

            DaemonTriggerParams l_processors1 = (DaemonTriggerParams) actualList.get(1);

            assertEquals(trigger_type1, l_processors1.getTriggerType());
            assertEquals(thread_no1, l_processors1.getThreadNo());
            assertEquals(order_request_number1, l_processors1.getOrderRequestNumber());
            assertEquals(range_from1, l_processors1.getRangeFrom());
            assertEquals(range_to1, l_processors1.getRangeTo());
            assertEquals(trigger_status1, l_processors1.getTriggerStatus());
            assertEquals(tamp1, l_processors1.getTriggerDate());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(l_rowType);
            }
            catch (WEB3BaseException e)
            {
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * getデーモントリガー一覧
     * @@author 稲本潤に(中訊)
     */
    public void testGetDaemonTriggerList_0002()
    {
        final String STR_METHOD_NAME = " testGetDaemonTriggerList_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        RowType l_rowType = new RowType("daemon_trigger", "session");
        try
        {
            TestDBUtility.deleteAll(l_rowType);
            l_adminIfoManualExpireServiceImpl.getDaemonTriggerList();
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            assertEquals(WEB3SystemLayerException.class,
                    e.getClass());
            WEB3SystemLayerException xe = (WEB3SystemLayerException)e;
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    xe.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(l_rowType);
            }
            catch (WEB3BaseException e)
            {
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * getデーモントリガー一覧
     * @@author 稲本潤に(中訊)
     */
    public void testGetDaemonTriggerList_0003()
    {
        final String STR_METHOD_NAME = " testGetDaemonTriggerList_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        RowType l_rowType = new RowType("daemon_trigger", "session");
        try
        {
            TestDBUtility.deleteAll(l_rowType);
            //1
            String trigger_type = "1";
            long thread_no = 101010L;
            String order_request_number = "01";
            long range_from = 101010L;
            long range_to = 909090L;
            String trigger_status = "1";
            Timestamp tamp = Timestamp.valueOf("2007-02-06 14:15:16");

            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
            l_daemonTriggerParams.setTriggerType(trigger_type);//W
            l_daemonTriggerParams.setThreadNo(thread_no);
            l_daemonTriggerParams.setOrderRequestNumber(order_request_number);
            l_daemonTriggerParams.setRangeFrom(range_from);
            l_daemonTriggerParams.setRangeTo(range_to);
            l_daemonTriggerParams.setTriggerStatus(trigger_status);
            l_daemonTriggerParams.setTriggerDate(tamp);

            TestDBUtility.insertWithDel(l_daemonTriggerParams);

            l_adminIfoManualExpireServiceImpl.getDaemonTriggerList();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            assertEquals(WEB3SystemLayerException.class,
                    e.getClass());
            WEB3SystemLayerException xe = (WEB3SystemLayerException)e;
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    xe.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(l_rowType);
            }
            catch (WEB3BaseException e)
            {
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * getオンライン実行結果一覧
     * @@author 稲本潤に(中訊)
     */
    public void testGetOnlineRunStatusList_0001()
    {
        final String STR_METHOD_NAME = " testGetOnlineRunStatusList_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        RowType l_rowType = new RowType("online_run_status", "session");

        try
        {
            TestDBUtility.deleteAll(l_rowType);
            //1
            String institution_code = "001";
            ProductTypeEnum product_type = ProductTypeEnum.IFO;
            String future_option_div = "0";
            String online_service_div = "3";
            long account_id_from = 0001L;
            long account_id_to = 9999L;
            String run_status_div = "1";
            Timestamp created_timestamp = Timestamp.valueOf("2007-02-06 14:15:16");
            Timestamp last_updated_timestamp = Timestamp.valueOf("2007-02-06 18:22:20");

            OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();

            l_onlineRunStatusParams.setInstitutionCode(institution_code);
            l_onlineRunStatusParams.setProductType(product_type);
            l_onlineRunStatusParams.setFutureOptionDiv(future_option_div);
            l_onlineRunStatusParams.setOnlineServiceDiv(online_service_div);
            l_onlineRunStatusParams.setAccountIdFrom(account_id_from);
            l_onlineRunStatusParams.setAccountIdTo(account_id_to);
            l_onlineRunStatusParams.setRunStatusDiv(run_status_div);
            l_onlineRunStatusParams.setCreatedTimestamp(created_timestamp);
            l_onlineRunStatusParams.setLastUpdatedTimestamp(last_updated_timestamp);

            TestDBUtility.insertWithDel(l_onlineRunStatusParams);

            //2
            String institution_code1 = "002";
            ProductTypeEnum product_type1 = ProductTypeEnum.EQUITY;
            String future_option_div1 = "1";
            String online_service_div1 = "1";
            long account_id_from1 = 0001L;
            long account_id_to1 = 9999L;
            String run_status_div1 = "1";
            Timestamp created_timestamp1 = Timestamp.valueOf("2007-02-06 14:15:16");
            Timestamp last_updated_timestamp1 = Timestamp.valueOf("2007-02-06 18:22:20");

            OnlineRunStatusParams l_onlineRunStatusParams1 = new OnlineRunStatusParams();

            l_onlineRunStatusParams1.setInstitutionCode(institution_code1);
            l_onlineRunStatusParams1.setProductType(product_type1);
            l_onlineRunStatusParams1.setFutureOptionDiv(future_option_div1);
            l_onlineRunStatusParams1.setOnlineServiceDiv(online_service_div1);
            l_onlineRunStatusParams1.setAccountIdFrom(account_id_from1);
            l_onlineRunStatusParams1.setAccountIdTo(account_id_to1);
            l_onlineRunStatusParams1.setRunStatusDiv(run_status_div1);
            l_onlineRunStatusParams1.setCreatedTimestamp(created_timestamp1);
            l_onlineRunStatusParams1.setLastUpdatedTimestamp(last_updated_timestamp1);

            TestDBUtility.insertWithDel(l_onlineRunStatusParams1);

            List actualList = l_adminIfoManualExpireServiceImpl.getOnlineRunStatusList(institution_code);

            assertEquals(1, actualList.size());
            OnlineRunStatusParams actual = (OnlineRunStatusParams)actualList.get(0);
            assertEquals(institution_code, actual.getInstitutionCode());
            assertEquals(product_type, actual.getProductType());
            assertEquals(future_option_div, actual.getFutureOptionDiv());
            assertEquals(online_service_div, actual.getOnlineServiceDiv());
            assertEquals(account_id_from, actual.getAccountIdFrom());
            assertEquals(account_id_to, actual.getAccountIdTo());
            assertEquals(run_status_div, actual.getRunStatusDiv());
            assertEquals(created_timestamp, actual.getCreatedTimestamp());
            assertEquals(last_updated_timestamp, actual.getLastUpdatedTimestamp());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(l_rowType);
            }
            catch (WEB3BaseException e)
            {
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * getオンライン実行結果一覧
     * @@author 稲本潤に(中訊)
     */
    public void testGetOnlineRunStatusList_0002()
    {
        final String STR_METHOD_NAME = " testGetOnlineRunStatusList_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        RowType l_rowType = new RowType("online_run_status", "session");
        try
        {
            TestDBUtility.deleteAll(l_rowType);

            List list = l_adminIfoManualExpireServiceImpl.getOnlineRunStatusList("001");

            log.exiting(TEST_END + STR_METHOD_NAME);
            assertNull(list);
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(l_rowType);
            }
            catch (WEB3BaseException e)
            {
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * getオンライン実行結果一覧
     * @@author 稲本潤に(中訊)
     */
    public void testGetOnlineRunStatusList_0003()
    {
        final String STR_METHOD_NAME = " testGetOnlineRunStatusList_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        RowType l_rowType = new RowType("online_run_status", "session");
        try
        {
            TestDBUtility.deleteAll(l_rowType);
            //1
            String institution_code = "002";
            ProductTypeEnum product_type = ProductTypeEnum.EQUITY;
            String future_option_div = "1";
            String online_service_div = "1";
            long account_id_from = 0001L;
            long account_id_to = 9999L;
            String run_status_div = "1";
            Timestamp created_timestamp = Timestamp.valueOf("2007-02-06 14:15:16");
            Timestamp last_updated_timestamp = Timestamp.valueOf("2007-02-06 18:22:20");

            OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();

            l_onlineRunStatusParams.setInstitutionCode(institution_code);
            l_onlineRunStatusParams.setProductType(product_type);
            l_onlineRunStatusParams.setFutureOptionDiv(future_option_div);
            l_onlineRunStatusParams.setOnlineServiceDiv(online_service_div);
            l_onlineRunStatusParams.setAccountIdFrom(account_id_from);
            l_onlineRunStatusParams.setAccountIdTo(account_id_to);
            l_onlineRunStatusParams.setRunStatusDiv(run_status_div);
            l_onlineRunStatusParams.setCreatedTimestamp(created_timestamp);
            l_onlineRunStatusParams.setLastUpdatedTimestamp(last_updated_timestamp);

            TestDBUtility.insertWithDel(l_onlineRunStatusParams);

            List list = l_adminIfoManualExpireServiceImpl.getOnlineRunStatusList(institution_code);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertNull(list);
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(l_rowType);
            }
            catch (WEB3BaseException e)
            {
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /**
     * 負荷分散処理を行う為のServerAccessorオブジェクトを返却する。
     * @@author 稲本潤に(中訊)
     */
    public void testGetServerAccessor_0001()
    {
        final String STR_METHOD_NAME = " testGetServerAccessor_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        RowType l_rowType = new RowType("server_config", "config");
        try
        {
            TestDBUtility.deleteAll(l_rowType);

            WEB3AdminIfoManualExpireServiceImpl l_adminIfoManualExpireServiceImpl =
                new WEB3AdminIfoManualExpireServiceImpl();
            l_adminIfoManualExpireServiceImpl.getServerAccessor();
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            assertEquals(WEB3SystemLayerException.class, e.getClass());
            WEB3SystemLayerException xe = (WEB3SystemLayerException)e;
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, xe.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(l_rowType);
            }
            catch (WEB3BaseException e)
            {
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /**
     * 負荷分散処理を行う為のServerAccessorオブジェクトを返却する。
     * @@author 稲本潤に(中訊)
     */
    public void testGetServerAccessor_0002()
    {
        final String STR_METHOD_NAME = " testGetServerAccessor_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        RowType l_rowType = new RowType("server_config", "config");
        try
        {
            TestDBUtility.deleteAll(l_rowType);
            //1
            String config_title = "0";
            String config_categ = "0";
            String config_name = "0";
            String config_value = "0";//sockpool:http//:www.cluster.urls

            ServerConfigParams l_serverConfigParams = new ServerConfigParams();
            l_serverConfigParams.setConfigTitle(config_title);
            l_serverConfigParams.setConfigCateg(config_categ);
            l_serverConfigParams.setConfigName(config_name);
            l_serverConfigParams.setConfigValue(config_value);
            TestDBUtility.insertWithDel(l_serverConfigParams);

            WEB3AdminIfoManualExpireServiceImpl l_adminIfoManualExpireServiceImpl =
                new WEB3AdminIfoManualExpireServiceImpl();
            l_adminIfoManualExpireServiceImpl.getServerAccessor();
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            assertEquals(WEB3SystemLayerException.class, e.getClass());
            WEB3SystemLayerException xe = (WEB3SystemLayerException)e;
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, xe.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(l_rowType);
            }
            catch (WEB3BaseException e)
            {
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /**
     * 負荷分散処理を行う為のServerAccessorオブジェクトを返却する。
     * @@author 稲本潤に(中訊)
     */
    public void testGetServerAccessor_0003()
    {
        final String STR_METHOD_NAME = " testGetServerAccessor_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        RowType l_rowType = new RowType("server_config", "config");
        try
        {
            TestDBUtility.deleteAll(l_rowType);
            //1
            String config_title = "DEVELOPER";
            String config_categ = "cluster.urls";
            String config_name = "count_web3";
            String config_value = "100";//sockpool:http//:www.cluster.urls

            ServerConfigParams l_serverConfigParams = new ServerConfigParams();
            l_serverConfigParams.setConfigTitle(config_title);
            l_serverConfigParams.setConfigCateg(config_categ);
            l_serverConfigParams.setConfigName(config_name);
            l_serverConfigParams.setConfigValue(config_value);
            TestDBUtility.insertWithDel(l_serverConfigParams);

            WEB3AdminIfoManualExpireServiceImpl l_adminIfoManualExpireServiceImpl =
                new WEB3AdminIfoManualExpireServiceImpl();
            l_adminIfoManualExpireServiceImpl.getServerAccessor();
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            assertEquals(WEB3SystemLayerException.class, e.getClass());
            WEB3SystemLayerException xe = (WEB3SystemLayerException)e;
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, xe.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(l_rowType);
            }
            catch (WEB3BaseException e)
            {
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (deleteオンライン実行結果)
     * @@author 稲本潤に(中訊)
     */
    public void testDeleteOnlineRunStatus_0001()
    {
        final String STR_METHOD_NAME = " testDeleteOnlineRunStatus_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        RowType l_rowType = new RowType("online_run_status", "session");

        try
        {
            TestDBUtility.deleteAll(l_rowType);
            l_adminIfoManualExpireServiceImpl.deleteOnlineRunStatus("001");

            List list = l_adminIfoManualExpireServiceImpl.getOnlineRunStatusList("001");
            assertNull(list);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(l_rowType);
            }
            catch (WEB3BaseException e)
            {
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (deleteオンライン実行結果)
     * @@author 稲本潤に(中訊)
     */
    public void testDeleteOnlineRunStatus_0002()
    {
        final String STR_METHOD_NAME = " testDeleteOnlineRunStatus_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        RowType l_rowType = new RowType("online_run_status", "session");

        try
        {
            TestDBUtility.deleteAll(l_rowType);
            //1
            String institution_code = "001";
            ProductTypeEnum product_type = ProductTypeEnum.IFO;
            String future_option_div = "0";
            String online_service_div = "3";
            long account_id_from = 0001L;
            long account_id_to = 9999L;
            String run_status_div = "1";
            Timestamp created_timestamp = Timestamp.valueOf("2007-02-06 14:15:16");
            Timestamp last_updated_timestamp = Timestamp.valueOf("2007-02-06 18:22:20");

            OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();

            l_onlineRunStatusParams.setInstitutionCode(institution_code);
            l_onlineRunStatusParams.setProductType(product_type);
            l_onlineRunStatusParams.setFutureOptionDiv(future_option_div);
            l_onlineRunStatusParams.setOnlineServiceDiv(online_service_div);
            l_onlineRunStatusParams.setAccountIdFrom(account_id_from);
            l_onlineRunStatusParams.setAccountIdTo(account_id_to);
            l_onlineRunStatusParams.setRunStatusDiv(run_status_div);
            l_onlineRunStatusParams.setCreatedTimestamp(created_timestamp);
            l_onlineRunStatusParams.setLastUpdatedTimestamp(last_updated_timestamp);

            TestDBUtility.insertWithDelAndCommit(l_onlineRunStatusParams);
            //2
            String institution_code1 = "002";
            ProductTypeEnum product_type1 = ProductTypeEnum.EQUITY;
            String future_option_div1 = "0";
            String online_service_div1 = "2";
            long account_id_from1 = 0001L;
            long account_id_to1 = 9999L;
            String run_status_div1 = "1";
            Timestamp created_timestamp1 = Timestamp.valueOf("2007-02-06 14:15:16");
            Timestamp last_updated_timestamp1 = Timestamp.valueOf("2007-02-06 18:22:20");

            OnlineRunStatusParams l_onlineRunStatusParams1 = new OnlineRunStatusParams();

            l_onlineRunStatusParams1.setInstitutionCode(institution_code1);
            l_onlineRunStatusParams1.setProductType(product_type1);
            l_onlineRunStatusParams1.setFutureOptionDiv(future_option_div1);
            l_onlineRunStatusParams1.setOnlineServiceDiv(online_service_div1);
            l_onlineRunStatusParams1.setAccountIdFrom(account_id_from1);
            l_onlineRunStatusParams1.setAccountIdTo(account_id_to1);
            l_onlineRunStatusParams1.setRunStatusDiv(run_status_div1);
            l_onlineRunStatusParams1.setCreatedTimestamp(created_timestamp1);
            l_onlineRunStatusParams1.setLastUpdatedTimestamp(last_updated_timestamp1);

            TestDBUtility.insertWithDelAndCommit(l_onlineRunStatusParams1);


            l_adminIfoManualExpireServiceImpl.deleteOnlineRunStatus("001");

            List list = l_adminIfoManualExpireServiceImpl.getOnlineRunStatusList("001");
            assertNull(list);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(l_rowType);
            }
            catch (WEB3BaseException e)
            {
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (deleteオンライン実行結果)
     * @@author 稲本潤に(中訊)
     */
    public void testDeleteOnlineRunStatus_0003()
    {
        final String STR_METHOD_NAME = " testDeleteOnlineRunStatus_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        RowType l_rowType = new RowType("online_run_status", "session");

        try
        {
            TestDBUtility.deleteAll(l_rowType);
            //1
            String institution_code = "001";
            ProductTypeEnum product_type = ProductTypeEnum.IFO;
            String future_option_div = "0";
            String online_service_div = "3";
            long account_id_from = 0001L;
            long account_id_to = 9999L;
            String run_status_div = "1";
            Timestamp created_timestamp = Timestamp.valueOf("2007-02-06 14:15:16");
            Timestamp last_updated_timestamp = Timestamp.valueOf("2007-02-06 18:22:20");

            OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();

            l_onlineRunStatusParams.setInstitutionCode(institution_code);
            l_onlineRunStatusParams.setProductType(product_type);
            l_onlineRunStatusParams.setFutureOptionDiv(future_option_div);
            l_onlineRunStatusParams.setOnlineServiceDiv(online_service_div);
            l_onlineRunStatusParams.setAccountIdFrom(account_id_from);
            l_onlineRunStatusParams.setAccountIdTo(account_id_to);
            l_onlineRunStatusParams.setRunStatusDiv(run_status_div);
            l_onlineRunStatusParams.setCreatedTimestamp(created_timestamp);
            l_onlineRunStatusParams.setLastUpdatedTimestamp(last_updated_timestamp);

            TestDBUtility.insertWithDelAndCommit(l_onlineRunStatusParams);

            //2
            String institution_code2 = "001";
            ProductTypeEnum product_type2 = ProductTypeEnum.IFO;
            String future_option_div2 = "0";
            String online_service_div2 = "3";
            long account_id_from2 = 1111L;
            long account_id_to2 = 9999L;
            String run_status_div2 = "9";
            Timestamp created_timestamp2 = Timestamp.valueOf("2008-04-03 15:14:36");
            Timestamp last_updated_timestamp2 = Timestamp.valueOf("2007-03-01 12:32:50");

            OnlineRunStatusParams l_onlineRunStatusParams2 = new OnlineRunStatusParams();

            l_onlineRunStatusParams2.setInstitutionCode(institution_code2);
            l_onlineRunStatusParams2.setProductType(product_type2);
            l_onlineRunStatusParams2.setFutureOptionDiv(future_option_div2);
            l_onlineRunStatusParams2.setOnlineServiceDiv(online_service_div2);
            l_onlineRunStatusParams2.setAccountIdFrom(account_id_from2);
            l_onlineRunStatusParams2.setAccountIdTo(account_id_to2);
            l_onlineRunStatusParams2.setRunStatusDiv(run_status_div2);
            l_onlineRunStatusParams2.setCreatedTimestamp(created_timestamp2);
            l_onlineRunStatusParams2.setLastUpdatedTimestamp(last_updated_timestamp2);

            TestDBUtility.insertWithDelAndCommit(l_onlineRunStatusParams2);

            //3
            String institution_code3 = "001";
            ProductTypeEnum product_type3 = ProductTypeEnum.IFO;
            String future_option_div3 = "0";
            String online_service_div3 = "3";
            long account_id_from3 = 0211L;
            long account_id_to3 = 9999L;
            String run_status_div3 = "6";
            Timestamp created_timestamp3 = Timestamp.valueOf("2001-03-09 11:12:26");
            Timestamp last_updated_timestamp3 = Timestamp.valueOf("2006-07-02 11:12:10");

            OnlineRunStatusParams l_onlineRunStatusParams3 = new OnlineRunStatusParams();

            l_onlineRunStatusParams3.setInstitutionCode(institution_code3);
            l_onlineRunStatusParams3.setProductType(product_type3);
            l_onlineRunStatusParams3.setFutureOptionDiv(future_option_div3);
            l_onlineRunStatusParams3.setOnlineServiceDiv(online_service_div3);
            l_onlineRunStatusParams3.setAccountIdFrom(account_id_from3);
            l_onlineRunStatusParams3.setAccountIdTo(account_id_to3);
            l_onlineRunStatusParams3.setRunStatusDiv(run_status_div3);
            l_onlineRunStatusParams3.setCreatedTimestamp(created_timestamp3);
            l_onlineRunStatusParams3.setLastUpdatedTimestamp(last_updated_timestamp3);

            TestDBUtility.insertWithDelAndCommit(l_onlineRunStatusParams3);

            //4
            String institution_code1 = "002";
            ProductTypeEnum product_type1 = ProductTypeEnum.EQUITY;
            String future_option_div1 = "0";
            String online_service_div1 = "2";
            long account_id_from1 = 0001L;
            long account_id_to1 = 9999L;
            String run_status_div1 = "1";
            Timestamp created_timestamp1 = Timestamp.valueOf("2007-02-06 14:15:16");
            Timestamp last_updated_timestamp1 = Timestamp.valueOf("2007-02-06 18:22:20");

            OnlineRunStatusParams l_onlineRunStatusParams1 = new OnlineRunStatusParams();

            l_onlineRunStatusParams1.setInstitutionCode(institution_code1);
            l_onlineRunStatusParams1.setProductType(product_type1);
            l_onlineRunStatusParams1.setFutureOptionDiv(future_option_div1);
            l_onlineRunStatusParams1.setOnlineServiceDiv(online_service_div1);
            l_onlineRunStatusParams1.setAccountIdFrom(account_id_from1);
            l_onlineRunStatusParams1.setAccountIdTo(account_id_to1);
            l_onlineRunStatusParams1.setRunStatusDiv(run_status_div1);
            l_onlineRunStatusParams1.setCreatedTimestamp(created_timestamp1);
            l_onlineRunStatusParams1.setLastUpdatedTimestamp(last_updated_timestamp1);

            TestDBUtility.insertWithDelAndCommit(l_onlineRunStatusParams1);


            l_adminIfoManualExpireServiceImpl.deleteOnlineRunStatus("001");

            List list = l_adminIfoManualExpireServiceImpl.getOnlineRunStatusList("001");
            assertNull(list);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(l_rowType);
            }
            catch (WEB3BaseException e)
            {
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (updateAP呼出中)
     * @@author 稲本潤に(中訊)
     */
    public void testUpdateAPCalling_0001()
    {
        final String STR_METHOD_NAME = " testUpdateAPCalling_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        RowType l_rowType = new RowType( "daemon_trigger", "session" );

        try
        {
            TestDBUtility.deleteAll(l_rowType);
            //1
            String trigger_type = "W";
            long thread_no = 101010L;
            String order_request_number = "01";
            long range_from = 101010L;
            long range_to = 909090L;
            String trigger_status = "1";
            Timestamp tamp = Timestamp.valueOf("2007-02-06 14:15:16");

            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
            l_daemonTriggerParams.setTriggerType(trigger_type);//W
            l_daemonTriggerParams.setThreadNo(thread_no);
            l_daemonTriggerParams.setOrderRequestNumber(order_request_number);
            l_daemonTriggerParams.setRangeFrom(range_from);
            l_daemonTriggerParams.setRangeTo(range_to);
            l_daemonTriggerParams.setTriggerStatus(trigger_status);
            l_daemonTriggerParams.setTriggerDate(tamp);

            TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams);

            l_adminIfoManualExpireServiceImpl.updateAPCalling(101010L);
            List actualList = l_adminIfoManualExpireServiceImpl.getDaemonTriggerList();
            assertEquals(1, actualList.size());

            DaemonTriggerParams l_processors = (DaemonTriggerParams) actualList.get(0);

            assertEquals(trigger_type, l_processors.getTriggerType());
            assertEquals(thread_no, l_processors.getThreadNo());
            assertEquals(order_request_number, l_processors.getOrderRequestNumber());
            assertEquals(range_from, l_processors.getRangeFrom());
            assertEquals(range_to, l_processors.getRangeTo());
            assertEquals("2", l_processors.getTriggerStatus());
            assertEquals(tamp, l_processors.getTriggerDate());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(l_rowType);
            }
            catch (WEB3BaseException e)
            {
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (updateAP呼出中)
     * @@author 稲本潤に(中訊)
     */
    public void testUpdateAPCalling_0002()
    {
        final String STR_METHOD_NAME = " testUpdateAPCalling_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        RowType l_rowType = new RowType( "daemon_trigger", "session" );

        try
        {
            TestDBUtility.deleteAll(l_rowType);
            l_adminIfoManualExpireServiceImpl.updateAPCalling(10L);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            assertEquals(WEB3SystemLayerException.class, e.getClass());
            WEB3SystemLayerException xe = (WEB3SystemLayerException)e;
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, xe.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(l_rowType);
            }
            catch (WEB3BaseException e)
            {
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (updateAP呼出中)
     * @@author 稲本潤に(中訊)
     */
    public void testUpdateAPCalling_0003()
    {
        final String STR_METHOD_NAME = " testUpdateAPCalling_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        RowType l_rowType = new RowType( "daemon_trigger", "session" );

        try
        {
            TestDBUtility.deleteAll(l_rowType);
            //1
            String trigger_type = "T";
            long thread_no = 101010L;
            String order_request_number = "01";
            long range_from = 101010L;
            long range_to = 909090L;
            String trigger_status = "1";
            Timestamp tamp = Timestamp.valueOf("2007-02-06 14:15:16");

            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
            l_daemonTriggerParams.setTriggerType(trigger_type);//W
            l_daemonTriggerParams.setThreadNo(thread_no);
            l_daemonTriggerParams.setOrderRequestNumber(order_request_number);
            l_daemonTriggerParams.setRangeFrom(range_from);
            l_daemonTriggerParams.setRangeTo(range_to);
            l_daemonTriggerParams.setTriggerStatus(trigger_status);
            l_daemonTriggerParams.setTriggerDate(tamp);

            TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams);

            l_adminIfoManualExpireServiceImpl.updateAPCalling(10L);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            assertEquals(WEB3SystemLayerException.class, e.getClass());
            WEB3SystemLayerException xe = (WEB3SystemLayerException)e;
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, xe.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(l_rowType);
            }
            catch (WEB3BaseException e)
            {
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateCarryOvering_ES_C0001()
    {
        final String STR_METHOD_NAME = " testValidateCarryOvering_ES_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("N8080");
            l_context.setBizDateType("1");
            l_context.setMarketCode("1");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            l_tradingTimeParams.setInstitutionCode(l_context.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_context.getBranchCode());
            l_tradingTimeParams.setBizDateType(l_context.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_context.getMarketCode());
            l_tradingTimeParams.setProductCode(l_context.getProductCode());
            l_tradingTimeParams.setTradingTimeType(l_context.getTradingTimeType());
            l_tradingTimeParams.setSessionType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            TestDBUtility.deleteAll(l_orderexecutionEndParams.getRowType());
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
            l_orderexecutionEndParams.setCarryoverEndType(WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP);
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            OrderexecutionEndParams l_orderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams1.setInstitutionCode("0D");
            l_orderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_orderexecutionEndParams1.setCarryoverEndType(WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP);
            l_orderexecutionEndParams1.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams1);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(11L);
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);

            String[] l_strTradingTypeList = null;
            l_adminIfoManualExpireServiceImpl.validateCarryOvering( l_institution,l_strTradingTypeList);

            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02446,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateCarryOvering_ES_C0002()
    {
        final String STR_METHOD_NAME = " testValidateCarryOvering_ES_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("N8080");
            l_context.setBizDateType("1");
            l_context.setMarketCode("1");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            l_tradingTimeParams.setInstitutionCode(l_context.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_context.getBranchCode());
            l_tradingTimeParams.setBizDateType(l_context.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_context.getMarketCode());
            l_tradingTimeParams.setProductCode(l_context.getProductCode());
            l_tradingTimeParams.setTradingTimeType(l_context.getTradingTimeType());
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            TestDBUtility.deleteAll(l_orderexecutionEndParams.getRowType());
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
            l_orderexecutionEndParams.setCarryoverEndType(WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP);
            l_orderexecutionEndParams.setOrderexecutionEndType("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            OrderexecutionEndParams l_orderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams1.setInstitutionCode("0D");
            l_orderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_orderexecutionEndParams1.setCarryoverEndType(WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP);
            l_orderexecutionEndParams1.setOrderexecutionEndType("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams1);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(11L);
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);

            String[] l_strTradingTypeList = null;
            l_adminIfoManualExpireServiceImpl.validateCarryOvering( l_institution,l_strTradingTypeList);

            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02446,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
