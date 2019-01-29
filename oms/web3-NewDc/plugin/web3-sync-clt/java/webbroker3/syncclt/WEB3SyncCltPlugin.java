head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.26.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d7db4ef1a89;
filename	WEB3SyncCltPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3SyncCltPluginプラグインのプラグインクラス(WEB3SyncCltPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2003/09/14 FLJ劉　@新規作成
 					2007/12/21 FLJ孫　@更新
 Revesion History : 2009/01/07 張騰宇 (中訊) 東証次世代対応
 */
package webbroker3.syncclt;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.interceptor.*;
import webbroker3.aio.service.delegate.*;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoNotifyService;
import webbroker3.equity.service.delegate.*;
import webbroker3.ifo.service.delegate.*;
import webbroker3.mf.service.delegate.*;
import webbroker3.syncclt.data.*;
import webbroker3.util.*;
import webbroker3.xbruito.service.delegate.*;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherRegAcceptService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoLockRegistReleaseAcceptService;

/**
 * <p>
 * WEB3SyncCltPluginプラグインのプラグインクラス。<br>
 * <br>
 * </p>
 *
 * @@author FLJ劉
 * @@version 1.0
 */
public class WEB3SyncCltPlugin
    extends Plugin
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SyncCltPlugin.class);

    private static boolean isPlugged = false;

    /**
     * デフォルトコンストラクタ。
     */
    public WEB3SyncCltPlugin()
    {
    }

    public static void plug() throws Exception
    {
        plug(WEB3SyncCltPlugin.class);
    }

    /**
     * このプラグインクラスのメインメソッド
     */
    public static void onPlug() throws Exception
    {

        KernelPlugin.plug();
        WEB3SyncCltSessionDatabaseExtensions.plug();

        //株式注文入力通知
        Services.addInterceptor(
            WEB3EquityOrderNotifyService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3EquityOrderNotifyService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //株式注文入力通知
        Services.addInterceptor(
            WEB3MarginOrderNotifyService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3MarginOrderNotifyService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //現引現渡注文通知
        Services.addInterceptor(
            WEB3MarginSwapOrderNotifyService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3MarginSwapOrderNotifyService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //累積投資注文受付
        Services.addInterceptor(
            WEB3RuitoTradeOrderNotifyService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3RuitoTradeOrderNotifyService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //SONAR入出金
        Services.addInterceptor(
            WEB3AioSonarCashTransService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3AioSonarCashTransService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //振替請求通知
        Services.addInterceptor(
            WEB3AccTransChangeRequestNotifyService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3AccTransChangeRequestNotifyService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //証券振替強制
        Services.addInterceptor(
            WEB3AioSecurityTransferForceService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3AioSecurityTransferForceService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //特定口座振替
        Services.addInterceptor(
            WEB3AioSpsecTransferForceService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3AioSpsecTransferForceService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //振替請求、出庫請求
        Services.addInterceptor(
            WEB3AioOutputNotifyService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3AioOutputNotifyService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //投信売買通知
        Services.addInterceptor(
            WEB3MutualTradeOrderNotifyService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3MutualTradeOrderNotifyService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //OP注文通知
        Services.addInterceptor(
            WEB3OptionsOrderNotifyService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3OptionsOrderNotifyService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //先物注文通知
        Services.addInterceptor(
            WEB3FuturesOrderNotifyService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3FuturesOrderNotifyService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //20061227追加
        //投信注文受付サービス
        //WEB3MutualOrderAcceptService
        Services.addInterceptor(
            WEB3MutualOrderAcceptService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3MutualOrderAcceptService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //投資信託取消受付サービス
        //WEB3MutualCancelAcceptService
        Services.addInterceptor(
            WEB3MutualCancelAcceptService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3MutualCancelAcceptService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //20070601追加
        //外貨MMF注文受付サービス
		//WEB3MutualFrgnMmfOrderAcceptService
        Services.addInterceptor(
            WEB3MutualFrgnMmfOrderAcceptService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3MutualFrgnMmfOrderAcceptService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //↓　@20071221追加------------------------
        //p_type = aio_cashin_accept /* 入出金受付(GI80C) */
        //入金受付サービス
		//WEB3AioCashinAcceptService
        Services.addInterceptor(
                WEB3AioCashinAcceptService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
                WEB3AioCashinAcceptService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));        
        
        //p_type = acc_trans_change_request_accept /* 振替請求受付(GI80F) */
        //振替請求受付サービス
		//WEB3AccTransChangeRequestAcceptService
        Services.addInterceptor(
                WEB3AccTransChangeRequestAcceptService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
                WEB3AccTransChangeRequestAcceptService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //p_type = aio_security_transfer_notify /* 証券振替通知(GI80G) */
        //証券振替通知サービス
		//WEB3AioSecurityTransferNotifyService
        Services.addInterceptor(
                WEB3AioSecurityTransferNotifyService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
                WEB3AioSecurityTransferNotifyService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //p_type = accOpen_voucherRegAccept /* 口座開設受付(GI82A) */
        //口座開設伝票登録受付サービス
		//WEB3AccOpenVoucherRegAcceptService
        Services.addInterceptor(
                WEB3AccOpenVoucherRegAcceptService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
                WEB3AccOpenVoucherRegAcceptService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));        
        
        //p_type = accInfo_lockRegistReleaseAccept /* ロック客登録解除(GI84F),特殊客登録(GI84G) */
        //ロック登録解除受付サービス
		//WEB3AccInfoLockRegistReleaseAcceptService
        Services.addInterceptor(
                WEB3AccInfoLockRegistReleaseAcceptService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
                WEB3AccInfoLockRegistReleaseAcceptService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING)); 
        
        //↑　@20071221追加------------------------
        //↓　@20080402追加------------------------
        // p_type = aio_cashout_accept /* 出金請求受付(GI80A)*/
        //出金受付サービス
        //WEB3AioCashoutAcceptService
        Services.addInterceptor(
                WEB3AioCashoutAcceptService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
                WEB3AioCashoutAcceptService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING)); 
        //↑　@20080402追加------------------------       

        //注意情報通知
        Services.addInterceptor(
            WEB3AdminEquityAttentionInfoNotifyService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3AdminEquityAttentionInfoNotifyService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        isPlugged = true;

        log.info("WEB3SyncCltPlugin was plugged.");
    }

    public static boolean isPlugged()
    {
        return isPlugged;
    }
}
@
