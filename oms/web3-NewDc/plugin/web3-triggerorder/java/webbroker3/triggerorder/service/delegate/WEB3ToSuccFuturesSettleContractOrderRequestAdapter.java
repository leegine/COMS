head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.02.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesSettleContractOrderRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :（連続）先物返済注文リクエストアダプタ（WEB3ToSuccFuturesSettleContractOrderRequestAdapter.java）
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/18 孟亞南(中訊) 新規作成モデルNo.259 No.294
 */
package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractOrderRequestAdapter;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseConfirmRequest;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;


/**
 * (（連続）先物返済注文リクエストアダプタ)<BR>
 * （連続）先物返済注文リクエストアダプタクラス<BR>
 *
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3ToSuccFuturesSettleContractOrderRequestAdapter
    extends WEB3FuturesSettleContractOrderRequestAdapter
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesSettleContractOrderRequestAdapter.class);

    /**
     * (親注文の注文単位)<BR>
     * 親注文の注文単位オブジェクト<BR>
     */
    public IfoOrderUnit parentOrderUnit;

    /**
     * (単価)<BR>
     * 単価。<BR>
     * （指値／0（＝成行）／執行単価（±指値））<BR>
     * ※成行注文の場合の概算受渡代金計算に使用した時価は、<BR>
     * ※概算受渡代金計算結果.get計算単価()で取得する。<BR>
     */
    public double price;

    /**
     * @@roseuid 47D6593300FA
     */
    public WEB3ToSuccFuturesSettleContractOrderRequestAdapter()
    {

    }

    /**
     * （連続）先物返済注文リクエストアダプタインスタンスを生成する。<BR>
     * <BR>
     * １）　@本インスタンスを生成する。<BR>
     * ２）　@親注文の注文単位オブジェクトを取得する。<BR>
     * 　@　@連続注文マネージャ.get先物OP親注文の注文単位(<BR>
     * 　@　@リクエスト.連続注文共通情報.（親注文）注文ID)をコールする。<BR>
     * <BR>
     * ３）　@生成したインスタンスに、引数のリクエスト、<BR>
     * 　@　@及び取得した親注文の注文単位オブジェクトをセットする。<BR>
     * 　@　@プロパティの単価には0をセットする。<BR>
     * <BR>
     * ４）　@インスタンスを返却する。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータオブジェクト<BR>
     * @@return WEB3FuturesSettleContractOrderRequestAdapter
     * @@throws WEB3BaseException
     * @@roseuid 47BBD2C403B9
     */
    public static WEB3FuturesSettleContractOrderRequestAdapter create(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "create(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //本インスタンスを生成する。
        WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_settleContractOrderResquestAdapter =
            new WEB3ToSuccFuturesSettleContractOrderRequestAdapter();

        //リクエスト.連続注文共通情報.（親注文）注文ID
        String l_strID = null;
        if (l_request instanceof WEB3SuccFuturesCloseConfirmRequest)
        {
            l_strID = ((WEB3SuccFuturesCloseConfirmRequest)l_request).succCommonInfo.parentOrderId;
        }
        else if (l_request instanceof WEB3SuccFuturesCloseCompleteRequest)
        {
            l_strID = ((WEB3SuccFuturesCloseCompleteRequest)l_request).succCommonInfo.parentOrderId;
        }
        else
        {
            log.error("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                "WEB3ToSuccFuturesSettleContractOrderRequestAdapter." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        //親注文の注文単位オブジェクトを取得する
        //連続注文マネージャを取得する
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //get先物OP親注文の注文単位(long)
        IfoOrderUnit l_ifoOrderUnit =
            l_orderManager.getIfoParentOrderUnit(
                Long.parseLong(l_strID));

        l_settleContractOrderResquestAdapter.request = l_request;
        l_settleContractOrderResquestAdapter.parentOrderUnit = l_ifoOrderUnit;
        l_settleContractOrderResquestAdapter.price = 0;

        //インスタンスを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_settleContractOrderResquestAdapter;
    }

    /**
     * (get建玉)<BR>
     * 建玉オブジェクトを返却する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@this.is反対売買() == trueの場合、<BR>
     * 　@連続注文マネージャImpl.create建玉()をコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@注文単位：　@this.親注文の注文単位<BR>
     * <BR>
     * ２）　@既存残に対する返済（１）以外）の場合、<BR>
     * 　@super.get建玉()をコールし、戻り値を返却する。<BR>
     * @@return WEB3IfoContractImpl
     * @@throws WEB3BaseException
     * @@roseuid 47BBE4AA022C
     */
    public WEB3IfoContractImpl getContract() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContract()";
        log.entering(STR_METHOD_NAME);

        //this.is反対売買() == true
        boolean l_blnReversingOrder = this.isReversingOrder();
        if (l_blnReversingOrder)
        {
            //連続注文マネージャを取得する
            WEB3ToSuccOrderManagerImpl l_orderManager =
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
            WEB3IfoContractImpl l_ifoContractImpl = l_orderManager.createIfoContract(this.parentOrderUnit);

            log.exiting(STR_METHOD_NAME);
            return l_ifoContractImpl;
        }

        //super.get建玉()
        log.exiting(STR_METHOD_NAME);
        return super.getContract();
    }

    /**
     * (is反対売買)<BR>
     * 反対売買かどうか判別する。<BR>
     * <BR>
     * リクエストデータ.連続注文共通情報.連続注文取引区分=="先物返済（前提注文）"の場合は、<BR>
     * trueを返却する。<BR>
     * <BR>
     * 以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 47BCDC2702C4
     */
    public boolean isReversingOrder()
    {
        final String STR_METHOD_NAME = "isReversingOrder()";
        log.entering(STR_METHOD_NAME);

        if (this.request instanceof WEB3SuccFuturesCloseConfirmRequest)
        {
            WEB3SuccFuturesCloseConfirmRequest l_request =
                (WEB3SuccFuturesCloseConfirmRequest)this.request;
            //リクエストデータ.連続注文共通情報.連続注文取引区分=="先物返済（前提注文）"の場合
            if (WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_ASSUMPTION_ORDER.equals(
                l_request.succCommonInfo.succTradingType))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else if (this.request instanceof WEB3SuccFuturesCloseCompleteRequest)
        {
            WEB3SuccFuturesCloseCompleteRequest l_request =
                (WEB3SuccFuturesCloseCompleteRequest)this.request;
            //リクエストデータ.連続注文共通情報.連続注文取引区分=="先物返済（前提注文）"の場合
            if (WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_ASSUMPTION_ORDER.equals(
                l_request.succCommonInfo.succTradingType))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get単価)<BR>
     * 単価をリクエストオブジェクトより取得する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@確認リクエストの場合<BR>
     * <BR>
     * 　@this.単価 == 0の場合は、<BR>
     * 　@連続注文マネージャ.get先物OP予約注文執行単価()をコールし、<BR>
     * 　@戻り値をthis.単価にセットする。<BR>
     * 　@※親注文が成行注文の場合、時価を基準とした執行単価を計算しているため、<BR>
     * 　@※処理中の時価の変動を考慮し、執行単価を記憶しておく。<BR>
     * <BR>
     * 　@[引数設定仕様]<BR>
     * 　@　@親注文の注文単位：　@親注文の注文単位<BR>
     * 　@　@指値：　@super.get単価()の戻り値<BR>
     * 　@　@単価調整値：　@リクエストデータ.単価調整値情報==nullの場合は、null。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@以外、リクエストデータ.単価調整値情報.get単価調整値()。<BR>
     * 　@　@先物OP取引銘柄：　@親注文の注文単位.getTradedProduct()<BR>
     * <BR>
     * <BR>
     * 　@this.単価を返却する。<BR>
     * <BR>
     * ２）　@完了リクエストの場合<BR>
     * <BR>
     * 　@リクエスト.単価調整値情報==null（指値／成行）の場合は、super.get単価()の戻り値を返却する。<BR>
     * 　@リクエスト.単価調整値情報≠null（±指値指定）の場合は、リクエスト.調整後単価を返却する。<BR>
     * 　@※調整後単価がnullの場合は、「執行単価がnull」の例外をthrowする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@  tag: BUSINESS_ERROR_02707<BR>
     * 　@※調整後単価が0以下の場合は、「執行単価が0以下」の例外をthrowする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@  tag: BUSINESS_ERROR_02298<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 47C216510364
     */
    public double getPrice() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPrice()";
        log.entering(STR_METHOD_NAME);

        //確認リクエストの場合
        if (this.request instanceof WEB3SuccFuturesCloseConfirmRequest)
        {
            WEB3SuccFuturesCloseConfirmRequest l_request =
                (WEB3SuccFuturesCloseConfirmRequest)this.request;

            //this.単価 == 0の場合
            if (this.price == 0)
            {
                //連続注文マネージャを取得する
                WEB3ToSuccOrderManagerImpl l_orderManager =
                    (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                Double l_valueInfo = null;
                if (l_request.priceAdjustmentValueInfo != null)
                {
                    l_valueInfo = new Double(l_request.priceAdjustmentValueInfo.getPriceAdjustmentValue());
                }

                //連続注文マネージャ.get先物OP予約注文執行単価
                this.price =
                    l_orderManager.getReserveIfoOrderExecPrice(
                        this.parentOrderUnit,
                        super.getPrice(),
                        l_valueInfo,
                        (WEB3IfoTradedProductImpl)this.parentOrderUnit.getTradedProduct());
            }
            log.exiting(STR_METHOD_NAME);
            return this.price;
        }
        //完了リクエストの場合
        else if (this.request instanceof WEB3SuccFuturesCloseCompleteRequest)
        {
            WEB3SuccFuturesCloseCompleteRequest l_request =
                (WEB3SuccFuturesCloseCompleteRequest)this.request;
            //リクエスト.単価調整値情報==null（指値／成行）の場合は、super.get単価()の戻り値を返却する
            if (l_request.priceAdjustmentValueInfo == null)
            {
                return super.getPrice();
            }
            //リクエスト.単価調整値情報≠null（±指値指定）の場合は、リクエスト.調整後単価を返却する。
            else
            {
                if (l_request.afterAdjustmentPrice == null)
                {
                    log.debug("執行単価がnull。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02707,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "執行単価がnull。");
                }
                else if (Double.parseDouble(l_request.afterAdjustmentPrice) <= 0)
                {
                    log.debug("執行単価が0以下です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02298,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "執行単価が0以下です。");
                }
                log.exiting(STR_METHOD_NAME);
                return Double.parseDouble(l_request.afterAdjustmentPrice);
            }
        }
        else
        {
            log.error("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                 "パラメータタイプ不正。");
        }
    }
}
@
