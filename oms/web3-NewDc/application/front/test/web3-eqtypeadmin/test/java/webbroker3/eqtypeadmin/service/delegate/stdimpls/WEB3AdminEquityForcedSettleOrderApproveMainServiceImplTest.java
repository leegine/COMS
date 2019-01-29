head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.07.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityForcedSettleOrderApproveMainServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済仮注文承認／非承認メインサービスImpl(WEB3AdminEquityForcedSettleOrderApproveMainServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/29 稲本潤に (中訊) 新規作成
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteServiceImpl;
import webbroker3.system.tune.threadpool.WEB3ThreadPoolPlugin;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・強制決済仮注文承認／非承認メインサービスImpl)<BR>
 * 管理者・強制決済仮注文承認／非承認メインサービス実装クラス<BR>
 * <BR>
 * @@author 稲本潤に
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleOrderApproveMainServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderApproveMainServiceImplTest.class);
    WEB3AdminEquityForcedSettleOrderApproveMainServiceImpl interceptor = null;

    public WEB3AdminEquityForcedSettleOrderApproveMainServiceImplTest(String arg0)
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
    
    /**
     * (（非同期）強制決済仮注文承認／非承認処理を起動する。)<BR>
     * validate error<BR>
     * testExecute_0001<BR>
     */
    public void testExecute_0001()
    {
        final String STR_METHOD_NAME = " testExecute_0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveMainRequest l_request = 
                new WEB3AdminEquityForcedSettleOrderApproveMainRequest();
            /**
             * (証券会社コード)<BR>
             * 証券会社コード<BR>
             */
            l_request.institutionCode = null;

            /**
             * (スレッドNo)<BR>
             * スレッドNo<BR>
             */
            l_request.threadNo = new Long(1234);

            /**
             * (From口座ID)<BR>
             * From口座ID<BR>
             */
            l_request.rangeFrom = new Long(1024);

            /**
             * (To口座ID)<BR>
             * To口座ID<BR>
             */
            l_request.rangeTo = new Long(1196);

            /**
             * (承認区分)<BR>
             * 承認区分<BR>
             * <BR>
             * 0：　@承認<BR>
             * 1：　@非承認<BR>
             */
            l_request.approveType = "0";

            /**
             * (注文ID一覧)<BR>
             * 注文ID一覧<BR>
             */
            l_request.orderIdList = new String[]{"1001", "1002", "1003", "1004", "1005"};
            
            interceptor = new WEB3AdminEquityForcedSettleOrderApproveMainServiceImpl();
            interceptor.execute(l_request);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00827, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * (（非同期）強制決済仮注文承認／非承認処理を起動する。)<BR>
     * （非同期）強制決済仮注文承認／非承認 execute error<BR>
     * testExecute_0002<BR>
     */
    public void testExecute_0002()
    {
        final String STR_METHOD_NAME = " testExecute_0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        final String errorStr = "WEB3AsynExecuteService is failure";
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveMainRequest l_request = 
                new WEB3AdminEquityForcedSettleOrderApproveMainRequest();
            /**
             * (証券会社コード)<BR>
             * 証券会社コード<BR>
             */
            l_request.institutionCode = "0029";

            /**
             * (スレッドNo)<BR>
             * スレッドNo<BR>
             */
            l_request.threadNo = new Long(1234);

            /**
             * (From口座ID)<BR>
             * From口座ID<BR>
             */
            l_request.rangeFrom = new Long(1024);

            /**
             * (To口座ID)<BR>
             * To口座ID<BR>
             */
            l_request.rangeTo = new Long(1196);

            /**
             * (承認区分)<BR>
             * 承認区分<BR>
             * <BR>
             * 0：　@承認<BR>
             * 1：　@非承認<BR>
             */
            l_request.approveType = "0";

            /**
             * (注文ID一覧)<BR>
             * 注文ID一覧<BR>
             */
            l_request.orderIdList = new String[]{"1001", "1002", "1003", "1004", "1005"};
            
            
            interceptor = new WEB3AdminEquityForcedSettleOrderApproveMainServiceImplForTest();
            interceptor.execute(l_request);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (RuntimeException l_ex)
        {
            assertEquals(errorStr, l_ex.getMessage());
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.overrideService(WEB3AsynExecuteService.class , new WEB3AsynExecuteServiceImpl());
        }
    }
    
    /**
     * (（非同期）強制決済仮注文承認／非承認処理を起動する。)<BR>
     * OK<BR>
     * testExecute_0003<BR>
     */
    public void testExecute_0003()
    {
        final String STR_METHOD_NAME = " testExecute_0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveMainRequest l_request = 
                new WEB3AdminEquityForcedSettleOrderApproveMainRequest()
                {
                    /**
                     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
                     * <BR>
                     * @@return WEB3BackResponse
                     */
                    public WEB3BackResponse createResponse()
                    {
                        return new MyWEB3AdminEquityForcedSettleOrderApproveMainResponse(this);
                    }                    
                };
            /**
             * (証券会社コード)<BR>
             * 証券会社コード<BR>
             */
            l_request.institutionCode = "0029";

            /**
             * (スレッドNo)<BR>
             * スレッドNo<BR>
             */
            l_request.threadNo = new Long(1234);

            /**
             * (From口座ID)<BR>
             * From口座ID<BR>
             */
            l_request.rangeFrom = new Long(1024);

            /**
             * (To口座ID)<BR>
             * To口座ID<BR>
             */
            l_request.rangeTo = new Long(1196);

            /**
             * (承認区分)<BR>
             * 承認区分<BR>
             * <BR>
             * 0：　@承認<BR>
             * 1：　@非承認<BR>
             */
            l_request.approveType = "0";

            /**
             * (注文ID一覧)<BR>
             * 注文ID一覧<BR>
             */
            l_request.orderIdList = new String[]{"1001", "1002", "1003", "1004", "1005"};
            
            l_request.administratorId = new Long(254122);
            
//            Services.overrideService(WEB3AsynExecuteService.class , new WEB3AsynExecuteServiceImpl(){});
            
            interceptor = new WEB3AdminEquityForcedSettleOrderApproveMainServiceImpl();
            MyWEB3AdminEquityForcedSettleOrderApproveMainResponse response = 
                (MyWEB3AdminEquityForcedSettleOrderApproveMainResponse)interceptor.execute(l_request);
            assertTrue(response.flag);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
}

/**
 * MyWEB3AdminEquityForcedSettleOrderApproveMainResponse
 * extends 管理者・強制決済仮注文承認／非承認メインレスポンス
 * @@author liu-lifeng
 *
 */
class MyWEB3AdminEquityForcedSettleOrderApproveMainResponse extends WEB3AdminEquityForcedSettleOrderApproveMainResponse
{
    public final boolean flag = true;
    
    /**
     * @@roseuid 462CA42601AA
     */
    public MyWEB3AdminEquityForcedSettleOrderApproveMainResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public MyWEB3AdminEquityForcedSettleOrderApproveMainResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
    
    
}

class WEB3AdminEquityForcedSettleOrderApproveMainServiceImplForTest extends WEB3AdminEquityForcedSettleOrderApproveMainServiceImpl
{
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        throw new RuntimeException("WEB3AsynExecuteService is failure");
    }
}@
