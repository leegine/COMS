head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashTransferCompleteUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金完了UnitService実装クラス(WEB3AioCashTransferCompleteUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 周勇 (中訊) 新規作成  
                   2004/10/26 黄建 (中訊) レビュー    
*/
package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.GenFinTransactionDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.GenFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.GenFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioFinTransactionDao;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.market.messages.DefaultAioTransferDoneMarketResponseMessage;
import webbroker3.aio.WEB3AioCashTransUpdateInterceptor;
import webbroker3.aio.WEB3AioCashoutCancelUpdateInterceptor;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.service.delegate.WEB3AioCashTransferCompleteUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (入出金完了UnitServiceImpl)<BR>
 * 入出金完了UnitService実装クラス<BR>
 * <BR>
 * Plugin時に自動トランザクション<BR>
 * TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)<BR>
 * を指定する。<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashTransferCompleteUnitServiceImpl implements WEB3AioCashTransferCompleteUnitService 
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashTransferCompleteUnitServiceImpl.class);
    
    /**
     * (complete入出金)<BR>
     * 入出金結果での注文データの更新とトランザクションデータの生成を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（入出金受付）complete入出金」 参照<BR>
     * @@param l_orderUnit - (注文単位オブジェクト)
     * @@roseuid 40FE4D7701C3
     */
    public void completeCashTransfer(AioOrderUnit l_orderUnit) 
    {
        final String STR_METHOD_NAME = "completeCashTransfer(AioOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1.1)入出金更新インタセプタインスタンスを生成する。 
        WEB3AioCashTransUpdateInterceptor l_aioCashTransUpdateInterceptor = 
            new WEB3AioCashTransUpdateInterceptor();
        
        // 1.2)インタセプタをスレッドにセットする。 
        //[引数] 
        //  入出金更新インタセプタ： 生成したインタセプタ
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AioOrderManager l_aioOrderManager =
            (AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_aioCashTransUpdateInterceptor);
        
        // 1.3)入出金実行メッセージインスタンスを生成する。
        //[引数] 
        //注文ＩＤ： 引数.注文ID 
        //タイムスタンプ： システムタイムスタンプ
        DefaultAioTransferDoneMarketResponseMessage l_defaultMessage = 
            new DefaultAioTransferDoneMarketResponseMessage(
            l_orderUnit.getOrderId(), 
            GtlUtils.getSystemTimestamp());
         
        // 1.4)入出金結果を注文に更新する。
        //[引数] 
        //入出金結果： 生成した入出金実行メッセージオブジェクト 
        MarketAdapter l_marketAdapter = l_finApp.getTradingModule(ProductTypeEnum.AIO).getMarketAdapter();
        
        AioMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
            (AioMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
           
        l_marketResponseReceiverCallbackService.process(l_defaultMessage);
        
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * (complete入出金取消)<BR>
     * 入出金結果（取消）での注文データとトランザクションデータの更新を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（入出金受付）complete入出金取消」 参照<BR>
     * @@param l_orderUnit - (注文単位オブジェクト)
     * @@roseuid 4105D62B01B5
     */
    public void completeCashTransferCancel(AioOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "completeCashTransferCancel(AioOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1)出金取消更新インタセプタインスタンスを生成する。
        WEB3AioCashoutCancelUpdateInterceptor l_aioCashoutCancelUpdateInterceptor = 
            new WEB3AioCashoutCancelUpdateInterceptor();
        
        // 1.2)インタセプタをスレッドにセットする。
        //[引数] 
        //出金取消更新インタセプタ： 生成したインタセプタ
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(l_aioCashoutCancelUpdateInterceptor);
        
        // 1.3)入出金取消メッセージインスタンスを生成する。
        //[引数] 
        //注文ＩＤ： 引数.注文単位.注文ID
        long l_lngOrderId = l_orderUnit.getOrderId();
        DefaultCancelOrderAcceptedMarketResponseMessage l_responseMessage = 
            new DefaultCancelOrderAcceptedMarketResponseMessage(l_lngOrderId);
        
        // 1.4)取消結果を注文に更新する。
        //[引数] 
        //取消結果： 生成した入出金取消メッセージオブジェクト
        MarketAdapter l_marketAdapter = l_finApp.getTradingModule(ProductTypeEnum.AIO).getMarketAdapter();
        
        AioMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
            (AioMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
        
        l_marketResponseReceiverCallbackService.process(l_responseMessage);
        
        //(*) 注文単位から、トランザクション（取引勘定明細）、トランザクション（顧客勘定明細）
        //を取得して、レコードの更新を行う。
        //更新内容は、
        //「SONAR入出金（取消）_トランザクション（取引勘定明細）.xls」
        //「SONAR入出金（取消）_トランザクション（顧客勘定明細）.xls」を参照
        long l_lngAccountId = l_orderUnit.getAccountId();
        long l_lngAubAccountId = l_orderUnit.getSubAccountId();
        long l_lngProductId = l_orderUnit.getProduct().getProductId();
        long l_lngOrderUnitId = l_orderUnit.getOrderUnitId();
        
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //  update トランザクション（取引勘定明細) 
            AioFinTransactionRow l_aioFinTransactionRow = 
                AioFinTransactionDao.findRowByAccountIdSubAccountIdProductIdOrderUnitId(
                    l_lngAccountId, l_lngAubAccountId, l_lngProductId, l_lngOrderUnitId);
            
            // new params 
            AioFinTransactionParams l_aioFinTransactionParams = 
                new AioFinTransactionParams(l_aioFinTransactionRow);
            
            // set update values
            l_aioFinTransactionParams.setDeleteFlag(BooleanEnum.TRUE);
            l_aioFinTransactionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            // do update
            l_queryProcessor.doUpdateQuery(l_aioFinTransactionParams);
            
            
            //update ﾄﾗﾝｻﾞｸｼｮﾝ（顧客勘定明細）
            long l_lngFinTransactionId = l_aioFinTransactionRow.getFinTransactionId();
            
            GenFinTransactionRow l_genFinTransactionRow = 
                GenFinTransactionDao.findRowByPk(l_lngFinTransactionId);
            
            // new params 
            GenFinTransactionParams l_genFinTransactionParams = 
                new GenFinTransactionParams(l_genFinTransactionRow);
            
            // set update values
            l_genFinTransactionParams.setDeleteFlag(BooleanEnum.TRUE);
            l_genFinTransactionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            // do update
            l_queryProcessor.doUpdateQuery(l_genFinTransactionParams);
            
        }
         catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
}@
