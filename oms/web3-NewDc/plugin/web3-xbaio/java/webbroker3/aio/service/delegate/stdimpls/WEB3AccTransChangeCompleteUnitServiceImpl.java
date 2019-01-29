head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeCompleteUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替完了UnitServiceImpl(WEB3AccTransChangeCompleteUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 于美麗 (中訊) 新規作成
                   2004/10/22 王蘭芬(中訊) レビュー                                       
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.market.messages.DefaultAioTransferDoneMarketResponseMessage;

import webbroker3.aio.WEB3AioCashTransUpdateInterceptor;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.service.delegate.WEB3AccTransChangeCompleteUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (振替完了UnitServiceImpl)<BR>
 * 振替完了UnitService実装クラス<BR>
 * <BR>
 * Plugin時に自動トランザクション<BR>
 * TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)を指定する。<BR>
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AccTransChangeCompleteUnitServiceImpl
    implements WEB3AccTransChangeCompleteUnitService
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeCompleteUnitServiceImpl.class);

    /**
     * (complete振替)<BR>
     * 振替結果での注文データの更新とトランザクションデータの生成を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（振替請求受付）complete振替」 参照<BR>
     * @@param l_orderUnit - (注文単位オブジェクト)
     * @@throws WEB3BaseException
     * @@roseuid 413C2261033D
     */
    public void completeChange(AioOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "completeChange(AioOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" __parameter_error__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1) 入出金更新インタセプタインスタンスを生成する
        WEB3AioCashTransUpdateInterceptor l_updateInterceptor = 
            new WEB3AioCashTransUpdateInterceptor();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager(); 
                   
        // 2) インタセプタをスレッドにセットする。 
        // [引数] 
        // 入出金更新インタセプタ： 生成したインタセプタ
        l_orderManager.setThreadLocalPersistenceEventInterceptor(
            l_updateInterceptor);
        
        // 3) 入出金実行メッセージインスタンスを生成する。 
        // [引数] 
        // 注文ＩＤ： 引数.注文単位.注文ID 
        // タイムスタンプ： システムタイムスタンプ    
        DefaultAioTransferDoneMarketResponseMessage l_defaultMessage = 
            new DefaultAioTransferDoneMarketResponseMessage(
            l_orderUnit.getOrderId(), GtlUtils.getSystemTimestamp());
            
        // 4) 入出金結果を注文に更新する。 
        // [引数] 
        // 入出金結果： 生成した入出金実行メッセージオブジェクト 
        MarketAdapter l_marketAdapter = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO).getMarketAdapter();
        
        AioMarketResponseReceiverCallbackService l_marketService =
            (AioMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
        
        l_marketService.process(l_defaultMessage);
        log.exiting(STR_METHOD_NAME);
    }
}
@
