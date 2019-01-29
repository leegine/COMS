head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOrderExecNotifyNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物出来通知正常処理一件TransactionCallback(WEB3FuturesOrderExecNotifyNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 李志強(日本中訊) 新規作成
Revesion History : 2007/04/25 崔遠鵬 (中訊) 仕様変更モデルNo.634
Revesion History : 2008/04/28 安陽 (中訊) 仕様変更モデルNo.881
Revesion History : 2008/08/28 安陽 (中訊) 仕様変更ＤＢレイアウト090
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3CloseNotifyTypeDef;
import webbroker3.common.define.WEB3DealedTypeDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoChangeCancelNotifyUpdateInterceptor;
import webbroker3.ifo.data.HostFotypeCloseOrderNotifyParams;
import webbroker3.ifo.data.HostFotypeCloseOrderNotifyRow;
import webbroker3.ifo.data.HostFotypeOrderClmdNotifyParams;
import webbroker3.ifo.data.HostFotypeOrderClmdNotifyRow;
import webbroker3.ifo.data.HostOptionOrderExecNotifyParams;
import webbroker3.ifo.data.HostOptionOrderExecNotifyRow;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeCancelNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3FuturesOrderExecNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoCloseNotifyUnitService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （株価指数先物出来通知正常処理一件TransactionCallback）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 李志強
 * @@version 1.0
 */
public class WEB3FuturesOrderExecNotifyNormalTransactionCallback implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesOrderExecNotifyNormalTransactionCallback.class);

    /**
      * 先物OP出来通知キューRowオブジェクト。<BR>
      */
    private HostOptionOrderExecNotifyRow hostOptionOrderExecNotifyRow;

    /**
      * 先物OP出来通知キューParamsオブジェクト。<BR>
      */
    private HostOptionOrderExecNotifyParams hostOptionOrderExecNotifyParams;

    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_hostOptionOrderExecNotifyRow - (先物OP出来通知キューRow)
     * @@params l_hostOptionOrderExecNotifyParams - (先物OP出来通知キューParams)
     */
    public WEB3FuturesOrderExecNotifyNormalTransactionCallback(
        HostOptionOrderExecNotifyRow l_hostOptionOrderExecNotifyRow,
        HostOptionOrderExecNotifyParams l_hostOptionOrderExecNotifyParams)
    {
        hostOptionOrderExecNotifyRow = l_hostOptionOrderExecNotifyRow;
        hostOptionOrderExecNotifyParams = l_hostOptionOrderExecNotifyParams;
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


        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderManager = (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
        HostFotypeCloseOrderNotifyParams l_closeParams = null;
        HostFotypeOrderClmdNotifyParams l_cancelParams = null;
        QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();

        HostOptionOrderExecNotifyRow l_notifyRow = hostOptionOrderExecNotifyRow;
        HostOptionOrderExecNotifyParams l_notifyParams = hostOptionOrderExecNotifyParams;

        try
        {
            String l_strInstitutionCode = l_notifyRow.getInstitutionCode();
            String l_strBranchCode = l_notifyRow.getBranchCode();

            //1.2.1 get注文単位(String, String, ProductTypeEnum, String)
            String l_orderRequestNumber =  l_notifyRow.getOrderRequestNumber();
            OrderUnit l_orderUnit = null;
            
            try
            {
                l_orderUnit = l_orderManager.getOrderUnit(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    ProductTypeEnum.IFO,
                    l_orderRequestNumber);
            }
            catch (WEB3BaseException l_ex)
            {
                if (l_ex instanceof WEB3SystemLayerException)
                {
                    WEB3SystemLayerException l_wre = (WEB3SystemLayerException)l_ex;
                    if (l_wre.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80017 &&
                        l_wre.getException() == null)
                    {
                        TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
                        String l_strPreference = l_tradingSystem.getPreference(
                            WEB3SystemPreferencesNameDef.EXEC_NOTIFY_WAIT_SECONDS);
                        if (WEB3StringTypeUtility.isEmpty(l_strPreference))
                        {
                            l_strPreference = "0";
                        }
                      
                        //注文通知を待つか判定する
                        long l_lngPreference = Long.parseLong(l_strPreference);
                        Date l_datAddDate = WEB3DateUtility.addSecond(l_notifyRow.getCreatedTimestamp(),
                            l_lngPreference);
                        Date l_datCurrentDate = GtlUtils.getSystemTimestamp();
                        if (WEB3DateUtility.compareToSecond(l_datCurrentDate, l_datAddDate) > 0)
                        {
                            log.debug("該当するデータが存在しません。");
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                this.getClass().getName() + "." + STR_METHOD_NAME,
                                "該当データなし");
                        }
                        else
                        {
                            //通知キュー.更新日付を更新し、下記処理をスキップする。
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_02752,
                                this.getClass().getName() + "." + STR_METHOD_NAME,
                                "注文単位が取得出来ない。");
                        }
                    }
                }
                throw new WEB3BaseException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage());
            }

            //約定日時：　@出来通知キュー.約定日時
            Timestamp l_tsExecTimestamp = l_notifyRow.getExecTimestamp();

            //約定数量：　@出来通知キュー.約定数量
            double l_dblExecQuantity = l_notifyRow.getExecQuantity();

            //約定単価：　@出来通知キュー.約定単価
            double l_dblExecPrice = l_notifyRow.getExecPrice();

            //出来通知区分：　@出来通知キュー.出来通知区分
            String l_strDealedType = l_notifyRow.getDealedType();

            // 先物出来通知UnitServiceImpl
            WEB3FuturesOrderExecNotifyUnitService l_orderExecNotifyUnitService =
                (WEB3FuturesOrderExecNotifyUnitService) Services.getService(
                    WEB3FuturesOrderExecNotifyUnitService.class);

            // 処理区分
            String l_strStatus = null;

            log.debug("出来通知区分 = " + l_strDealedType);
            //1.2.2 (*1) 約定の場合処理対象出来通知キューレコード.出来通知区分 == ”全部約定”
            //  または”一部約定”の場合処理実施。
            if (WEB3DealedTypeDef.FULLY_EXECUTED.equals(l_strDealedType)
                || WEB3DealedTypeDef.PARTIALLY_EXECUTED.equals(l_strDealedType))
            {
                //1.2.2.1  notify約定(OrderUnit, Timestamp, double, double, String)
                l_orderExecNotifyUnitService.notifyExecute(
                    l_orderUnit,
                    l_tsExecTimestamp,
                    l_dblExecQuantity,
                    l_dblExecPrice,
                    l_strDealedType);


                l_orderUnit = l_orderManager.getOrderUnit(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    ProductTypeEnum.IFO,
                    hostOptionOrderExecNotifyRow.getOrderRequestNumber());


                //1.2.2.2  isPartiallyExecuted()
                l_orderUnit.isPartiallyExecuted();

                //1.3.3(*2)注文単位.失効区分==”失効中”の場合のみ実施
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
                log.debug("注文単位.失効区分 = 4:" + l_orderUnitRow.getExpirationStatus());
                if (OrderExpirationStatusEnum.EXPIRING.equals(l_orderUnitRow.getExpirationStatus()))
                {
                    try
                    {
                        //1.2.2.3.1 (*3)失効通知キューテーブルに”処理中”が存在する場合
                        //(*3) 以下の条件で先物OP失効通知キューテーブルを検索する。
                        //行が取得できた場合、失効通知処理をコールし、処理対象キューテーブルの処理区分を更新する。
                        //[条件]
                        //先物OP失効通知キューテーブル.データコード = "EI817"（株価指数先物失効通知）
                        //先物OP失効通知キューテーブル.証券会社コード = (*A)
                        //先物OP失効通知キューテーブル.部店コード = (*B)
                        //先物OP失効通知キューテーブル.識別コード = 注文単位.識別コード
                        //先物OP失効通知キューテーブル.失効通知区分 = "1"（失効）
                        //先物OP失効通知キューテーブル.処理区分 = ”処理中”
                        //(*A)拡張アカウントマネージャ.getBranch(注文単位.部店ID).getInstitution().getInstitutionCode()
                        //(*B)拡張アカウントマネージャ.getBranch(注文単位.部店ID).getBranchCode()

                        StringBuffer l_sbWhereClose = new StringBuffer();
                        l_sbWhereClose.append(" request_code = ? ");            // "EI817"（株価指数先物失効通知)
                        l_sbWhereClose.append(" and institution_code = ? ");    // (*A)
                        l_sbWhereClose.append(" and branch_code = ? ");         // (*B)
                        l_sbWhereClose.append(" and order_request_number = ? ");// 注文単位.識別コード
                        l_sbWhereClose.append(" and close_notify_type = ? ");   // "1"（失効）
                        l_sbWhereClose.append(" and status = ? ");              // ”処理中”

                        Object[] l_objWhereClose = {
                            WEB3HostRequestCodeDef.FUTURES_CLOSE_NOTICE,
                            l_accountManager.getBranch(l_orderUnitRow.getBranchId()).getInstitution().getInstitutionCode(),
                            l_accountManager.getBranch(l_orderUnitRow.getBranchId()).getBranchCode(),
                            l_orderUnitRow.getOrderRequestNumber(),
                            WEB3CloseNotifyTypeDef.CLOSE,
                            WEB3StatusDef.DEALING};

                        List l_lisSearchResultClose = null;
                        l_lisSearchResultClose = l_QueryProcessor.doFindAllQuery(
                            HostFotypeCloseOrderNotifyRow.TYPE,
                            l_sbWhereClose.toString(),
                            null,
                            null,
                            l_objWhereClose);
                        int l_intNumClose = 0;
                        if (l_lisSearchResultClose != null)
                        {
                            l_intNumClose = l_lisSearchResultClose.size();
                        }
                        log.debug("l_intNumClose = " + l_intNumClose);

                        if (l_intNumClose == 1)
                        {
                              //1.2.3.1.1 notify失効(OrderUnit, double, String, String)
                              //  (先物OP失効通知UnitServiceImpl::notify失効)
                              //  [引数]
                              //  注文単位：　@注文単位
                              //  約定数量：
                              //  失効通知キュー.失効数量 ! = NULL の場合、
                              //  　@注文単位.市場から確認済みの数量-失効通知キュー.失効数量
                              //  失効通知キュー.失効数量 = NULL の場合、
                              //  　@先物OP失効通知キューテーブル.約定数量
                              //  失効理由コード：　@失効通知キューテーブル.失効理由コード
                              WEB3IfoCloseNotifyUnitService l_unitService =
                                  (WEB3IfoCloseNotifyUnitService)Services.getService(
                                      WEB3IfoCloseNotifyUnitService.class);
                              l_closeParams = (HostFotypeCloseOrderNotifyParams)l_lisSearchResultClose.get(0);
                              double l_dblExecutedQuantity = 0.0;
                              if (!l_closeParams.getCloseQuantityIsNull())
                              {
                                  BigDecimal l_bdConfirmedQuantity =
                                      new BigDecimal(String.valueOf(l_orderUnit.getConfirmedQuantity()));
                                  BigDecimal l_bdCloseQuantity =
                                      new BigDecimal(String.valueOf(l_closeParams.getCloseQuantity()));
                                  l_dblExecutedQuantity =
                                      l_bdConfirmedQuantity.subtract(l_bdCloseQuantity).doubleValue();
                              }
                              else
                              {
                                  l_dblExecutedQuantity = l_closeParams.getExecutedQuantity();
                              }
                              l_strStatus = l_unitService.notifyClose(
                                  l_orderUnit,
                                  l_dblExecutedQuantity,
                                  l_closeParams.getReasonCode(),
                                  l_closeParams.getCloseNotifyType());

                              //1.2.3.1.2 処理対象失効通知キューレコード.処理区分を以下の通りセットし更新する。
                              //  [更新内容]
                              //  ”エラー”：上記処理で例外が発生した場合。
                              //  notify失効の戻り値：以外の場合。
                              l_closeParams.setStatus(l_strStatus);
                              l_closeParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                              l_QueryProcessor.doUpdateQuery(l_closeParams);
                        }
                        else
                        {
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                                this.getClass().getName() + STR_METHOD_NAME);
                        }
                    }
                    catch (WEB3BaseException l_bex)
                    {
                        ErrorInfo l_errorInfo = l_bex.getErrorInfo();
                        if (l_errorInfo.getErrorTag().startsWith("BUSINESS_ERROR"))
                        {
                            l_errorInfo=WEB3ErrorCatalog.BUSINESS_ERROR_01961;
                            throw new DataCallbackException(l_errorInfo.getErrorMessage(), l_errorInfo);
                        }
                        else
                        {
                            l_errorInfo=WEB3ErrorCatalog.SYSTEM_ERROR_80077;
                            throw new DataCallbackException(l_errorInfo.getErrorMessage(), l_errorInfo);

                        }
                    }
                    catch (Exception l_e)
                    {
                        log.exiting(STR_METHOD_NAME);
                        log.error("", new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_e.getMessage(),
                            l_e));
                    }

                }

                log.debug("注文単位 注文状態 = " + l_orderUnitRow.getOrderStatus());
                //1.3.4(*5)注文単位.注文状態==”発注中（取消注文）”または”受付済（取消注文）”の場合のみ実施
                if (OrderStatusEnum.CANCELLING.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderUnitRow.getOrderStatus()))
                {
                    log.debug(" 注文単位.注文状態==”発注中（取消注文）”または”受付済（取消注文）”の場合 ");
                    try
                    {
                        //1.2.2.3.2(*5)訂正取消キューテーブルに"処理中"が存在する場合
                        //(*5) 以下の条件で先物OP訂正取消通知キューテーブルを検索する。
                        //行が取得できた場合、取消通知処理をコールし、処理対象キューテーブルの処理区分を更新する。
                        //[条件]
                        //先物OP訂正取消通知キューテーブル.データコード = ”EI816”（株価指数先物訂正取消通知）
                        //先物OP訂正取消通知キューテーブル.証券会社コード = (*A)
                        //先物OP訂正取消通知キューテーブル.部店コード = (*B)
                        //先物OP訂正取消通知キューテーブル.識別コード = 注文単位.識別コード
                        //先物OP訂正取消通知キューテーブル.訂正取消通知区分 = "3"（取消完了）または"4"（取消失敗）
                        //先物OP訂正取消通知キューテーブル.処理区分 = ”処理中”
                        //(*A)拡張アカウントマネージャ.getBranch(注文単位.部店ID).getInstitution().getInstitutionCode()
                        //(*B)拡張アカウントマネージャ.getBranch(注文単位.部店ID).getBranchCode()

                        StringBuffer l_sbWhereCancel = new StringBuffer();
                        l_sbWhereCancel.append(" request_code = ? ");             // "EI816"（株価指数先物訂正取消通知）
                        l_sbWhereCancel.append(" and institution_code = ? ");     // (*A)
                        l_sbWhereCancel.append(" and branch_code = ? ");          // (*B)
                        l_sbWhereCancel.append(" and order_request_number = ? "); // 注文単位.識別コード
                        l_sbWhereCancel.append(" and ( canmod_receipt_type = ? ");// "3"（取消完了）
                        l_sbWhereCancel.append(" or canmod_receipt_type = ? ) "); // または"4"（取消失敗）
                        l_sbWhereCancel.append(" and status = ? ");               // ”処理中”

                        Object[] l_objWhereCancel = {
                            WEB3HostRequestCodeDef.FUTURES_CHANGE_CANCEL_NOTICE,
                            l_accountManager.getBranch(l_orderUnitRow.getBranchId()).getInstitution().getInstitutionCode(),
                            l_accountManager.getBranch(l_orderUnitRow.getBranchId()).getBranchCode(),
                            l_orderUnitRow.getOrderRequestNumber(),
                            WEB3CanmodReceiptTypeDef.CANCEL,
                            WEB3CanmodReceiptTypeDef.CANCEL_FAILED,
                            WEB3StatusDef.DEALING};

                        List l_lisSearchResultCancel = null;
                        l_lisSearchResultCancel = l_QueryProcessor.doFindAllQuery(
                            HostFotypeOrderClmdNotifyRow.TYPE,
                            l_sbWhereCancel.toString(),
                            null,
                            null,
                            l_objWhereCancel);
                        int l_intNumCancel = 0;
                        if (l_lisSearchResultCancel != null)
                        {
                            l_intNumCancel = l_lisSearchResultCancel.size();
                        }
                        log.debug("l_intNumCancel = "+ l_intNumCancel);

                        if (l_intNumCancel == 1)
                        {
                            //1.2.2.3.2.1 先物OP訂正取消通知更新インタセプタ()
                            WEB3IfoChangeCancelNotifyUpdateInterceptor l_interceptor =
                                new WEB3IfoChangeCancelNotifyUpdateInterceptor();

                            l_cancelParams = (HostFotypeOrderClmdNotifyParams) l_lisSearchResultCancel.get(0);

                            //先物OP訂正取消通知更新インタセプタに値を設定。
                            l_interceptor.setChangedQuantity(l_cancelParams.getModifiedQuantity());
                            l_interceptor.setChangedLimitPrice(l_cancelParams.getModifiedLimitPrice());

                            IfoOrderExecutionConditionType l_conditionType = null;

                            if (WEB3ExecutionConditionDef.NO_CONDITION.equals(l_cancelParams.getModifiedExecutionType()))
                            {
                                l_conditionType = IfoOrderExecutionConditionType.NONE;
                            }
                            else if (WEB3ExecutionConditionDef.AT_MARKET_OPEN.equals(l_cancelParams.getModifiedExecutionType()))
                            {
                                l_conditionType = IfoOrderExecutionConditionType.AT_MARKET_OPEN;
                            }
                            else if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE.equals(l_cancelParams.getModifiedExecutionType()))
                            {
                                l_conditionType = IfoOrderExecutionConditionType.AT_MARKET_CLOSE;
                            }
                            else if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_cancelParams.getModifiedExecutionType()))
                            {
                                l_conditionType = IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED;
                            }

                            l_interceptor.setChangedExecCondType(l_conditionType);
                            l_interceptor.setChangeCancelNotifyDivision(l_cancelParams.getCanmodReceiptType());
                            l_interceptor.setChangeCancelResultCode(l_cancelParams.getModifiedResult());

                            //1.2.2.3.2.2  notify取消(OrderUnit, 先物OP訂正取消通知更新インタセプタ)(先物訂正取消通知UnitServiceImpl::notify取消)
                            //[notify取消()に指定する引数]
                            //注文単位    ： 注文単位
                            //インタセプタ：（生成したOP訂正取消通知インタセプタオブジェクト）
                            WEB3FuturesChangeCancelNotifyUnitService l_unitService =
                                (WEB3FuturesChangeCancelNotifyUnitService)Services.getService(
                                    WEB3FuturesChangeCancelNotifyUnitService.class);

                            //1.2.2.3.2.3 処理対象訂正取消通知キューレコード.処理区分を
                            //[更新内容]
                            //”エラー”：上記処理で例外が発生した場合。
                            //notify取消の戻り値：以外の場合。
                            l_strStatus = l_unitService.notifyCancel(
                                l_orderUnit,
                                l_interceptor);

                            l_cancelParams.setStatus(l_strStatus);
                            l_cancelParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                            l_QueryProcessor.doUpdateQuery(l_cancelParams);

                        }
                    }
                    catch (WEB3BaseException l_bex)
                    {
                        ErrorInfo l_errorInfo = l_bex.getErrorInfo();
                        if (l_errorInfo.getErrorTag().startsWith("BUSINESS_ERROR"))
                        {
                            l_errorInfo=WEB3ErrorCatalog.BUSINESS_ERROR_01962;
                                throw new DataCallbackException(
                                l_errorInfo.getErrorMessage(),
                                l_errorInfo);
                        }
                        else
                        {
                            l_errorInfo=WEB3ErrorCatalog.SYSTEM_ERROR_80078;
                                throw new DataCallbackException(
                                l_errorInfo.getErrorMessage(),
                                l_errorInfo);

                        }
                    }
                    catch (Exception l_e)
                    {
                        log.exiting(STR_METHOD_NAME);
                        log.error("", new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_e.getMessage(),
                            l_e));
                    }

                }
            }
            //1.2.3 (*6) 約定取消の場合
            else if (WEB3DealedTypeDef.CANCEL.equals(l_notifyRow.getDealedType()))
            {
                //1.2.3.1 notify約定取消(OrderUnit, Timestamp, double, double)
                //  (先物出来通知UnitServiceImpl::notify約定取消)
                //  [notify約定取消()に指定する引数]
                //  注文単位：　@注文単位
                //  約定日時：　@出来通知キュー.約定日時
                //  約定数量：　@出来通知キュー.約定数量
                //  約定単価：　@出来通知キュー.約定単価
                l_orderExecNotifyUnitService.notifyExecuteCancel(
                    l_orderUnit,
                    l_tsExecTimestamp,
                    l_dblExecQuantity,
                    l_dblExecPrice);
            }

            //1.2.4 処理対象出来通知キューレコード.処理区分を以下の通りセットし更新する。
            //[更新内容]
            // ”エラー”：上記処理（キューテーブル各行ごとの処理）で例外が発生した場合。
            // ”処理済”：以外の場合。
            l_notifyParams.setStatus(WEB3StatusDef.DEALT);
            l_notifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_QueryProcessor.doUpdateQuery(l_notifyParams);
        }
        catch(WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        }

        log.exiting(STR_METHOD_NAME);

        return null;
    }
}
@
