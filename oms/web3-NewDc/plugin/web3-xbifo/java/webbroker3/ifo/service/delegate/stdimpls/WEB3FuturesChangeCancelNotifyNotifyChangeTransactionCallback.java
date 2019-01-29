head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesChangeCancelNotifyNotifyChangeTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物訂正取消通知訂正通知一件TransactionCallback(WEB3FuturesChangeCancelNotifyNotifyChangeTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/11 李志強(日本中訊) 新規作成
Revesion History : 2007/04/24 孟亜南(中訊) 仕様変更モデルNo.637
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoChangeCancelNotifyUpdateInterceptor;
import webbroker3.ifo.data.HostFotypeOrderClmdNotifyRow;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeCancelNotifyUnitService;
import webbroker3.util.WEB3LogUtility;


/**
 * （先物訂正取消通知訂正通知一件TransactionCallback）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 李志強
 * @@version 1.0
 */
public class WEB3FuturesChangeCancelNotifyNotifyChangeTransactionCallback implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesChangeCancelNotifyNotifyChangeTransactionCallback.class);

    /**
      * 先物OP訂正取消通知更新インタセプタオブジェクト。<BR>
      */
    private WEB3IfoChangeCancelNotifyUpdateInterceptor ifoChangeCancelNotifyUpdateInterceptor;

    /**
      * 先物OP訂正取消通知キューRowオブジェクト。<BR>
      */
    private HostFotypeOrderClmdNotifyRow hostFotypeOrderClmdNotifyRow;

    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_ifoChangeCancelNotifyUpdateInterceptor - (先物OP訂正取消通知更新インタセプタ)
     * @@params l_hostFotypeOrderClmdNotifyRow - (先物OP訂正取消通知キューRow)
     */
    public WEB3FuturesChangeCancelNotifyNotifyChangeTransactionCallback(
        WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyUpdateInterceptor,
        HostFotypeOrderClmdNotifyRow l_hostFotypeOrderClmdNotifyRow)
    {
        ifoChangeCancelNotifyUpdateInterceptor = l_ifoChangeCancelNotifyUpdateInterceptor;
        hostFotypeOrderClmdNotifyRow = l_hostFotypeOrderClmdNotifyRow;
    }

    /**
     * トランザクション処理を実施する。<BR>
     * @@return Object
     * @@throws DataQueryException, DataNetworkException, DataCallbackException
     */
    public Object process()
        throws DataQueryException, DataNetworkException, DataCallbackException
    {
        final String STR_METHOD_NAME = "process()";
        log.entering(STR_METHOD_NAME);

        WEB3FuturesChangeCancelNotifyUnitService l_futuresChangeCancelNotifyUnitService =
            (WEB3FuturesChangeCancelNotifyUnitService) Services.getService(WEB3FuturesChangeCancelNotifyUnitService.class);
        OrderUnit l_reOrderUnit = null;
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        try
        {
            // lock口座
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
            l_accountManager.lockAccount(
                hostFotypeOrderClmdNotifyRow.getInstitutionCode(),
                hostFotypeOrderClmdNotifyRow.getBranchCode(),
                hostFotypeOrderClmdNotifyRow.getAccountCode());
            
            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl) l_tradingMod.getOrderManager();
            
            //get注文単位
            OrderUnit l_orderUnit = l_orderMgr.getOrderUnit(hostFotypeOrderClmdNotifyRow.getInstitutionCode(),
                hostFotypeOrderClmdNotifyRow.getBranchCode(), 
                ProductTypeEnum.IFO, 
                hostFotypeOrderClmdNotifyRow.getOrderRequestNumber());
            
            // SONAR入力の訂正取消通知：
            // 訂正取消通知キュー.訂正後発注経路区分≠nullの場合、
            // SONAR入力の訂正取消通知であると判定する。
            String l_strModSubmitOrderRouteDiv =
                hostFotypeOrderClmdNotifyRow.getModSubmitOrderRouteDiv();

            // 次のいずれかの条件に該当する場合、処理をスキップする
            // １．注文未済：注文単位.市場確認済み数量==nullの場合
            // ２．訂正取消通知==SONAR入力の取消通知であり、かつ
            // 取得した注文単位がWEBⅢによる訂正取消中であり、かつ
            // 訂正取消通知キュー.訂正後発注経路区分≠注文単位.発注経路区分の場合
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            if (l_orderUnitRow.getConfirmedQuantityIsNull())
            {
                log.debug("注文が受付未済なので、当該注文の訂正取消通知処理をスキップする。");
                Map l_mapChanges = new HashMap();
                l_mapChanges.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(hostFotypeOrderClmdNotifyRow.getPrimaryKey(),l_mapChanges);
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            if (l_orderUnitRow.getConfirmedQuantityIsNull()
                || (l_strModSubmitOrderRouteDiv != null
                    && !l_strModSubmitOrderRouteDiv.equals(l_orderUnitRow.getSubmitOrderRouteDiv())
                    && (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderUnitRow.getOrderStatus())
                        || OrderStatusEnum.CANCELLING.equals(l_orderUnitRow.getOrderStatus())
                        || OrderStatusEnum.MODIFYING.equals(l_orderUnitRow.getOrderStatus())
                        || OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderUnitRow.getOrderStatus()))))
            {
                log.debug(
                    "SONAR入力の訂正取消通知（異なる発注経路からの訂正取消）かつ " +
                    "WEBⅢによる訂正取消中の場合、当該注文の訂正取消通知処理をスキップする。");
                Map l_mapChanges = new HashMap();
                l_mapChanges.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(hostFotypeOrderClmdNotifyRow.getPrimaryKey(),l_mapChanges);
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            //notify訂正
            l_futuresChangeCancelNotifyUnitService.notifyChange(
                l_orderUnit,
                ifoChangeCancelNotifyUpdateInterceptor);       
            l_reOrderUnit = l_orderMgr.getOrderUnit(l_orderUnit.getOrderUnitId());       
         }
        catch (NotFoundException l_exp)
        {
            ErrorInfo l_errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getMessage(),
                l_errorInfo);
        }
        catch (WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        }

        Map l_mapChanges = new HashMap();
        //17:  キューテーブルに処理区分セット
        // 取消通知で出来待ち数量あり(*)の場合、5：処理中。
        // 以外の場合、１：処理済(但し、訂正取消通知区分が”5：エラー”の場合、または処理中に例外が発生した場合、”9：エラー”)
        // (*)取消通知で出来待ち数量ありの場合
        // ・訂正取消通知区分 == ”取消完了” &&
        // ・訂正取消通知区分 ==  ”取消失敗” &&
        // ・訂正後数量 > 注文単位.約定数量
        double l_dblModifiedQuantity = hostFotypeOrderClmdNotifyRow.getModifiedQuantity();
        if (Double.isNaN(l_dblModifiedQuantity))
        {
            l_dblModifiedQuantity = 0;
        }
        double l_dblExecutedQuantity = l_reOrderUnit.getExecutedQuantity();
        if (Double.isNaN(l_dblExecutedQuantity))
        {
            l_dblExecutedQuantity = 0;
        }

        if ((WEB3CanmodReceiptTypeDef.CANCEL.equals(hostFotypeOrderClmdNotifyRow.getCanmodReceiptType())
            || WEB3CanmodReceiptTypeDef.CANCEL_FAILED.equals(hostFotypeOrderClmdNotifyRow.getCanmodReceiptType()))
            && l_dblModifiedQuantity > l_dblExecutedQuantity)
        {
            l_mapChanges.put("status", WEB3StatusDef.DEALING);
            l_mapChanges.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
        }
        else if (WEB3CanmodReceiptTypeDef.ERROR.equals(hostFotypeOrderClmdNotifyRow.getCanmodReceiptType()))
        {
            l_mapChanges.put("status", WEB3StatusDef.DATA_ERROR);
            l_mapChanges.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
        }
        else
        {
            l_mapChanges.put("status", WEB3StatusDef.DEALT);
            l_mapChanges.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
        }

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        l_queryProcessor.doUpdateQuery(hostFotypeOrderClmdNotifyRow.getPrimaryKey(),l_mapChanges);
        log.exiting(STR_METHOD_NAME);

        return null;
    }
}
@
