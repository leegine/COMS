head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderExecNotifyTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式出来通知一件TransactionCallback(WEB3EquityOrderExecNotifyTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/03 沢村仁士(SRA) 新規作成
                   2005/03/09 劉（FLJ）キューテーブルによる下り処理のトランザクション制御変更
*/

package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3DealedTypeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.equity.service.delegate.WEB3EquityOrderExecNotifyUnitService;
import webbroker3.equity.service.delegate.WEB3MarginOrderExecNotifyUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式出来通知一件TransactionCallback）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 沢村仁士
 * @@version 1.0
 */
public class WEB3EquityOrderExecNotifyTransactionCallback implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderExecNotifyTransactionCallback.class);

    /**
      * 注文単位オブジェクト。<BR>
      */
    private EqTypeOrderUnit orderUnit;

    /**
      * 株式出来通知キューParamsオブジェクト。
      */
    private HostEquityOrderExecNotifyParams orderExecNotifyParams;

    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_orderUnit - (注文単位)
     * @@params l_orderExecNotifyParams - (株式出来通知キューParams)
     */
    public WEB3EquityOrderExecNotifyTransactionCallback(
        EqTypeOrderUnit l_orderUnit,
        HostEquityOrderExecNotifyParams l_orderExecNotifyParams)
    {
        orderUnit = l_orderUnit;
        orderExecNotifyParams = l_orderExecNotifyParams;
    }

    /**
     * トランザクション処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（株式出来通知メインサービス）process」の<BR>
     * 株式出来通知一件TransactionCallback.process()部分参照。<BR>
     * @@return Object
     * @@throws DataQueryException, DataNetworkException, DataCallbackException
     */
    public Object process()
        throws DataQueryException, DataNetworkException, DataCallbackException
    {
        final String STR_METHOD_NAME = "process()";
        log.entering(STR_METHOD_NAME);

        try
        {
	        // 1.3.2. 取得した注文単位＝現物株式の場合
	        if (OrderCategEnum.ASSET.equals(orderUnit.getOrderCateg()))
	        {
	            // 株式出来通知一件サービスの取得
	            WEB3EquityOrderExecNotifyUnitService l_unitService =
	                (WEB3EquityOrderExecNotifyUnitService)Services.getService(
	                    WEB3EquityOrderExecNotifyUnitService.class);
	            // 1.3.2.1. 株式出来通知キューParams.出来通知区分＝（"全部約定"or"一部約定"）の場合
	            if (WEB3DealedTypeDef.EXECUTED.equals(orderExecNotifyParams.getDealedType()) ||
                    WEB3DealedTypeDef.FULLY_EXECUTED.equals(orderExecNotifyParams.getDealedType()) ||
	                WEB3DealedTypeDef.PARTIALLY_EXECUTED.equals(orderExecNotifyParams.getDealedType()))
	            {
	                l_unitService.notifyExecute(orderUnit, orderExecNotifyParams);
	            }
	            // 1.3.2.2. 株式出来通知キューParams.出来通知区分＝（"約定取消"）の場合
	            else if (WEB3DealedTypeDef.CANCEL.equals(orderExecNotifyParams.getDealedType()))
	            {
	                l_unitService.notifyExecuteCancel(orderUnit, orderExecNotifyParams);
	            }
	            // 上記以外の場合
	            else
	            {
	                String l_strMessage =
	                    "株式出来通知キュー.出来通知区分≠（\"全部約定\"or\"一部約定\"or\"約定取消\"）";
	                log.error(l_strMessage);
	                throw new WEB3SystemLayerException(
	                    WEB3ErrorCatalog.SYSTEM_ERROR_80025,
	                    this.getClass().getName() + "." + STR_METHOD_NAME,
	                    l_strMessage);
	            }
	        }
	        // 1.3.3. 取得した注文単位＝信用取引の場合
	        else if (OrderCategEnum.OPEN_MARGIN.equals(orderUnit.getOrderCateg()) ||
	                  OrderCategEnum.CLOSE_MARGIN.equals(orderUnit.getOrderCateg()) ||
	                  OrderCategEnum.SWAP_MARGIN.equals(orderUnit.getOrderCateg()))
	        {
	            // 信用取引出来通知一件サービスの取得
	            WEB3MarginOrderExecNotifyUnitService l_unitService =
	                (WEB3MarginOrderExecNotifyUnitService)Services.getService(
	                    WEB3MarginOrderExecNotifyUnitService.class);
	            // 1.3.3.1. 株式出来通知キューParams.出来通知区分＝（"全部約定"or"一部約定"）の場合
	            if (WEB3DealedTypeDef.EXECUTED.equals(orderExecNotifyParams.getDealedType()) ||
                    WEB3DealedTypeDef.FULLY_EXECUTED.equals(orderExecNotifyParams.getDealedType()) ||
	                WEB3DealedTypeDef.PARTIALLY_EXECUTED.equals(orderExecNotifyParams.getDealedType()))
	            {
	                l_unitService.notifyExecute(orderUnit, orderExecNotifyParams);
	            }
	            // 1.3.3.2. 株式出来通知キューParams.出来通知区分＝（"約定取消"）の場合
	            else if (WEB3DealedTypeDef.CANCEL.equals(orderExecNotifyParams.getDealedType()))
	            {
	                l_unitService.notifyExecuteCancel(orderUnit, orderExecNotifyParams);
	            }
	            // 上記以外の場合
	            else
	            {
	                String l_strMessage =
	                    "株式出来通知キューParams.出来通知区分≠（\"全部約定\"or\"一部約定\"or\"約定取消\"）";
	                log.error(l_strMessage);
	                throw new WEB3SystemLayerException(
	                    WEB3ErrorCatalog.SYSTEM_ERROR_80025,
	                    this.getClass().getName() + "." + STR_METHOD_NAME,
	                    l_strMessage);
	            }
	        }
	        // 上記以外の場合
	        else
	        {
	            String l_strMessage =
	                "株式注文単位.注文カテゴリ≠（\"現物注文\"or\"新規建注文\"or\"返済注文\"or\"現引・現渡注文\"）";
	            log.error(l_strMessage);
	            throw new WEB3SystemLayerException(
	                WEB3ErrorCatalog.SYSTEM_ERROR_80025,
	                this.getClass().getName() + "." + STR_METHOD_NAME,
	                l_strMessage);
	        }
        }
        catch (WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        }
        catch (WEB3BaseRuntimeException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        }
        //キューの更新は株式出来通知一件TransactionCallback内部で行う
        QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
        orderExecNotifyParams.setStatus(WEB3StatusDef.DEALT);
        orderExecNotifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        l_QueryProcessor.doUpdateQuery(orderExecNotifyParams);

        log.exiting(STR_METHOD_NAME);
        return null;
    }
}
@
