head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.02.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoManualExpireMainServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifoadmin.service.delegate.stdimpls;


import webbroker3.accountinfo.message.WEB3AccInfoLockRegistReleaseAcceptRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseMainRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseMainResponse;
import webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AdminIfoManualExpireMainServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・先物OP手動失効メインサービスImpl)<BR>
 * 管理者・先物OP手動失効メインサービス実装クラス<BR>
 * （非同期処理を行う為のエントリークラス）<BR>
 * <BR>
 * @@author 稲本潤に(中訊)
 * @@version 1.0
 */
public class WEB3AdminIfoManualExpireMainServiceImplTest extends TestBaseForMock
{

    public WEB3AdminIfoManualExpireMainServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_adminIfoManualExpireMainServiceImpl = new WEB3AdminIfoManualExpireMainServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminIfoManualExpireMainServiceImplTest.class);
    
    /**
     * 管理者・先物OP手動失効メインサービスImpl<BR>
     */
    private WEB3AdminIfoManualExpireMainServiceImpl l_adminIfoManualExpireMainServiceImpl = null;

    /**
     * （非同期）手動失効処理を起動する。<BR>
     * Method-Name: execute<BR>
     * expect: SYSTEM_ERROR_80017<BR>
     * @@param なし
     * @@return なし
     * @@throws なし
     */
   public void testExecute_0001()
    {
        final String STR_METHOD_NAME = " testExecute_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3BackRequest l_request = new WEB3AccInfoLockRegistReleaseAcceptRequest();
            l_adminIfoManualExpireMainServiceImpl.execute(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.error(TEST_END + STR_METHOD_NAME,e);
            assertEquals(WEB3SystemLayerException.class, e.getClass());
            WEB3SystemLayerException xe = (WEB3SystemLayerException)e;
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, xe.getErrorInfo());
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * （非同期）手動失効処理を起動する。<BR>
     * Method-Name: execute<BR>
     * expect: response正常<BR>
     * @@param なし
     * @@return なし
     * @@throws なし
     */
    public void testExecute_0002()
    {
        final String STR_METHOD_NAME = " testExecute_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3BackRequest l_request = new WEB3AdminIfoManualLapseMainRequest();
            l_adminIfoManualExpireMainServiceImpl.execute(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.error(TEST_END + STR_METHOD_NAME,e);
            assertEquals(WEB3BusinessLayerException.class, e.getClass());

        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * （非同期）手動失効処理を起動する。<BR>
     * Method-Name: execute<BR>
     * expect: validate異常<BR>
     * @@param なし
     * @@return なし
     * @@throws なし
     */
    public void testExecute_0003()
    {
        final String STR_METHOD_NAME = " testExecute_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3BackRequest l_request = new WEB3AdminIfoManualLapseMainRequest()
            {
                public void validate() throws WEB3BaseException
                {
                    this.threadNo = Long.valueOf("10");
                }
                /**
                 *（createResponseの実装）<BR>
                 * <BR>
                 * 管理者・株式手動失効メインレスポンオブジェクトを生成して返す。<BR>
                 * @@return WEB3GenResponse
                 * @@roseuid 4158EB6301A2
                 */
                public WEB3BackResponse createResponse() 
                {
                    return new MyWEB3AdminIfoManualLapseMainResponse();
                }
            };

            WEB3BackResponse l_response = l_adminIfoManualExpireMainServiceImpl.execute(l_request);
            if (l_response instanceof MyWEB3AdminIfoManualLapseMainResponse)
            {
                MyWEB3AdminIfoManualLapseMainResponse l_response1 = (MyWEB3AdminIfoManualLapseMainResponse)l_response;
                assertTrue(l_response1.testMethodFlag);
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
            else
            {
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();                
            }
        }
        catch (Exception e)
        {
            log.error(TEST_END + STR_METHOD_NAME,e);
            fail();

        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}

/**
 * (管理者・先物OP手動失効メインレスポンス)<BR>
 * 管理者・先物OP手動失効メインレスポンスクラス<BR>
 * <BR>
 */
class MyWEB3AdminIfoManualLapseMainResponse extends WEB3AdminIfoManualLapseMainResponse
{
    /**
     * @@roseuid 447AB8F40119
     */
    public MyWEB3AdminIfoManualLapseMainResponse() 
    {
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request - リクエストオブジェクト
     */
    protected MyWEB3AdminIfoManualLapseMainResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    } 

    public boolean testMethodFlag = true;
}@
