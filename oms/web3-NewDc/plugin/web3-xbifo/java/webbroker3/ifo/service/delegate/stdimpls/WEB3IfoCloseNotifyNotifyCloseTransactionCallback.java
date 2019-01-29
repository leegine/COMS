head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoCloseNotifyNotifyCloseTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP失効通知失効処理一件TransactionCallback(WEB3IfoCloseNotifyNotifyCloseTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/11 李志強(日本中訊) 新規作成
*/

package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.data.HostFotypeCloseOrderNotifyParams;
import webbroker3.ifo.service.delegate.WEB3IfoCloseNotifyUnitService;
import webbroker3.util.WEB3LogUtility;


/**
 * （先物OP失効通知失効処理一件TransactionCallback）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 李志強
 * @@version 1.0
 */
public class WEB3IfoCloseNotifyNotifyCloseTransactionCallback implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoCloseNotifyNotifyCloseTransactionCallback.class);

    /**
      * 注文単位オブジェクト。<BR>
      */
    private OrderUnit orderUnit;

    /**
      * 約定数量オブジェクト。<BR>
      */
    private double dblExecutionQuantity;

    /**
      * 失効理由コードオブジェクト。<BR>
      */
    private String strCloseReasonCode;

    /**
      * 失効通知区分オブジェクト。<BR>
      */
    private String strCloseNotifyType;

    /**
      * 失効通知キューParamsオブジェクト。<BR>
      */
    private HostFotypeCloseOrderNotifyParams hostFotypeCloseOrderNotifyParams;

    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_orderUnit - (注文単位)
     * @@params l_dblExecutionQuantity - (約定数量)
     * @@params l_strCloseReasonCode - (失効理由コード)
     * @@params l_strCloseNotifyType - (失効通知区分)
     * @@params l_hostFotypeCloseOrderNotifyParams - (失効通知キューParams)
     */
    public WEB3IfoCloseNotifyNotifyCloseTransactionCallback(
        OrderUnit l_orderUnit,
        double l_dblExecutionQuantity,
        String l_strCloseReasonCode,
        String l_strCloseNotifyType,
        HostFotypeCloseOrderNotifyParams l_hostFotypeCloseOrderNotifyParams)
    {
        orderUnit = l_orderUnit;
        dblExecutionQuantity = l_dblExecutionQuantity;
        strCloseReasonCode = l_strCloseReasonCode;
        strCloseNotifyType = l_strCloseNotifyType;
        hostFotypeCloseOrderNotifyParams = l_hostFotypeCloseOrderNotifyParams;
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

        WEB3IfoCloseNotifyUnitService l_closeNotifyUnitServiceImpl =
            (WEB3IfoCloseNotifyUnitService)Services.getService(WEB3IfoCloseNotifyUnitService.class);
		OrderUnit l_reOrderUnit = null;

        String l_strStatus = null;

        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager = (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            // lock口座
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
            l_accountManager.lockAccount(
                hostFotypeCloseOrderNotifyParams.getInstitutionCode(),
                hostFotypeCloseOrderNotifyParams.getBranchCode(),
                hostFotypeCloseOrderNotifyParams.getAccountCode());

            // 注文状態を確認するために注文単位を再取得する
            orderUnit = l_orderManager.getOrderUnit(
                hostFotypeCloseOrderNotifyParams.getInstitutionCode(),
                hostFotypeCloseOrderNotifyParams.getBranchCode(),
                ProductTypeEnum.IFO,
                hostFotypeCloseOrderNotifyParams.getOrderRequestNumber());

            this.validateOrderStatus((IfoOrderUnit)orderUnit);

            l_strStatus = l_closeNotifyUnitServiceImpl.notifyClose(
                orderUnit,
                dblExecutionQuantity,
                strCloseReasonCode,
                strCloseNotifyType
                );

			l_reOrderUnit = l_orderManager.getOrderUnit(orderUnit.getOrderUnitId());                    
        }
        catch (WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        } 		
        catch (NotFoundException l_exp)
		{
			ErrorInfo l_errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
			l_errorInfo.setErrorClass(l_exp.getClass().getName());
			throw new DataCallbackException(
				l_exp.getMessage(),
				l_errorInfo);
		}

        //8:キューテーブル更新
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        log.debug("ExecutedQuantity:" + l_reOrderUnit.getExecutedQuantity());
        log.debug("HostFortypeQuantity:" + dblExecutionQuantity);

        log.debug("Enter the path that キューテーブル更新 ");
        hostFotypeCloseOrderNotifyParams.setStatus(l_strStatus);
        hostFotypeCloseOrderNotifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        l_queryProcessor.doUpdateQuery(hostFotypeCloseOrderNotifyParams);
        log.debug("Exit the path that キューテーブル更新");

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (validate注文状態)<BR>
     * 対象注文が、失効／失効取消による更新を行って良い状態であるかチェックする。<BR>
     * <BR>
     * （チェック内容）<BR>
     * １）　@引数で渡された注文単位.市場から確認済の数量 == NaNの場合、<BR>
     * 　@　@「該当注文は受付未済／変更の受付済／発注中の状態」の例外をthrowする。<BR>
     * <BR>
     * ２）　@引数で渡された注文単位.注文状態が以下のいずれかに該当する場合、<BR>
     * 　@　@「該当注文は受付未済／変更の受付済／発注中の状態」の例外をthrowする。<BR>
     * 　@　@　@　@ACCEPTED：受付済（新規注文）<BR>
     * 　@　@　@　@ORDERING：発注中（新規注文）<BR>
     * 　@　@　@　@MODIFY_ACCEPTED：受付済（変更注文）<BR>
     * 　@　@　@　@MODIFYING：発注中（変更注文）<BR>
     * 　@　@　@　@CANCEL_ACCEPTED：受付済（取消注文）<BR>
     * 　@　@　@　@CANCELLING：発注中（取消注文）<BR>
     * <BR>
     * 以外、そのままreturnする。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     */
    protected void validateOrderStatus(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".validateOrderStatus(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (l_orderUnitRow.getConfirmedQuantity() == 0.0D)
        {
            log.debug("該当注文は受付未済／変更の受付済／発注中の状態。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01975,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        OrderStatusEnum l_orderStatus = l_orderUnitRow.getOrderStatus();
        if (OrderStatusEnum.ACCEPTED.equals(l_orderStatus) ||
            OrderStatusEnum.ORDERING.equals(l_orderStatus) ||
            OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus) ||
            OrderStatusEnum.MODIFYING.equals(l_orderStatus) ||
            OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus) ||
            OrderStatusEnum.CANCELLING.equals(l_orderStatus))
        {
            log.debug("該当注文は受付未済／変更の受付済／発注中の状態。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01975,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
