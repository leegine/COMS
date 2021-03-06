head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.03.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionOpenContractOrderRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）OP新規建注文リクエストアダプタ(WEB3ToSuccOptionOpenContractOrderRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 于瀟(中訊) 新規作成モデル281
*/
package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractOrderRequestAdapter;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenConfirmRequest;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）OP新規建注文リクエストアダプタ)<BR>
 * （連続）オプション新規建注文リクエストアダプタクラス<BR>
 *
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3ToSuccOptionOpenContractOrderRequestAdapter extends WEB3OptionOpenContractOrderRequestAdapter
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionOpenContractOrderRequestAdapter.class);

    /**
     * (親注文の注文単位)<BR>
     * 親注文の注文単位オブジェクト。<BR>
     */
    public IfoOrderUnit parentOrderUnit;

    /**
     * (単価)<BR>
     * 単価。<BR>
     * <BR>
     * （指値／0（＝成行）／執行単価（±指値））<BR>
     */
    public double price;

    /**
     * @@roseuid 47FDBE3F001F
     */
    public WEB3ToSuccOptionOpenContractOrderRequestAdapter()
    {

    }

    /**
     * （連続）オプション新規建注文リクエストアダプタのインスタンスを生成する。<BR>
     * <BR>
     * 　@１）　@本インスタンスを生成する。<BR>
     * <BR>
     * 　@２）　@連続注文マネージャ.get先物OP親注文の注文単位()をコールして、<BR>
     * 　@　@　@　@親注文の注文単位オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@　@[引数設定仕様]<BR>
     * <BR>
     * 　@　@　@　@　@（親注文）注文ID : リクエスト.連続注文共通情報.(親注文）注文ID<BR>
     * <BR>
     * 　@３）　@生成したインスタンスに、以下の項目を設定する。<BR>
     * <BR>
     * 　@　@　@　@リクエスト : 引数.リクエスト<BR>
     * 　@　@　@　@親注文の注文単位 : ２）で取得した親注文の注文単位オブジェクト<BR>
     * 　@　@　@　@単価 : 0（固定）<BR>
     * <BR>
     * 　@４）　@インスタンスを返却する。<BR>
     * <BR>
     * （本メソッドによってインスタンス化するように制限する）<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータ。<BR>
     * @@return WEB3OptionOpenContractOrderRequestAdapter
     * @@throws WEB3BaseException
     * @@roseuid 47BD2FB80391
     */
    public static WEB3OptionOpenContractOrderRequestAdapter create(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "create(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //本インスタンスを生成する
        WEB3ToSuccOptionOpenContractOrderRequestAdapter l_toSuccOptionOpenContractOrderRequestAdapter =
            new WEB3ToSuccOptionOpenContractOrderRequestAdapter();

        //連続注文マネージャ.get先物OP親注文の注文単位()をコールして、
        //親注文の注文単位オブジェクトを取得する。
        //[引数設定仕様]
        //（親注文）注文ID : リクエスト.連続注文共通情報.（親注文）注文ID
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3SuccCommonInfo l_succCommonInfo = null;
        if (l_request instanceof WEB3SuccOptionsOpenConfirmRequest)
        {
            WEB3SuccOptionsOpenConfirmRequest l_succOptionsOpenConfirmRequest =
                (WEB3SuccOptionsOpenConfirmRequest)l_request;
            l_succCommonInfo = l_succOptionsOpenConfirmRequest.succCommonInfo;
        }
        else if (l_request instanceof WEB3SuccOptionsOpenCompleteRequest)
        {
            WEB3SuccOptionsOpenCompleteRequest l_succOptionsOpenCompleteRequest =
                (WEB3SuccOptionsOpenCompleteRequest)l_request;
            l_succCommonInfo = l_succOptionsOpenCompleteRequest.succCommonInfo;
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                "WEB3ToSuccOptionOpenContractOrderRequestAdapter" + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }
        IfoOrderUnit l_ifoOrderUnit =
            l_orderManager.getIfoParentOrderUnit(Long.parseLong(l_succCommonInfo.parentOrderId));

        //生成したインスタンスに、以下の項目を設定する
        //リクエスト : 引数.リクエスト
        //親注文の注文単位 : ２）で取得した親注文の注文単位オブジェクト
        //単価 : 0（固定）
        l_toSuccOptionOpenContractOrderRequestAdapter.request = l_request;
        l_toSuccOptionOpenContractOrderRequestAdapter.parentOrderUnit = l_ifoOrderUnit;
        l_toSuccOptionOpenContractOrderRequestAdapter.price = 0;

        //インスタンスを返却する
        log.exiting(STR_METHOD_NAME);
        return l_toSuccOptionOpenContractOrderRequestAdapter;
    }

    /**
     * (get単価)<BR>
     * 単価を返却する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 　@１）リクエストデータ == 確認リクエストの場合<BR>
     *
     * 　@　@１−１）　@this.単価 == 0の場合は、<BR>
     * 　@　@　@　@　@　@　@連続注文マネージャ.get先物OP予約注文執行単価()をコールし、<BR>
     * 　@　@　@　@　@　@　@戻り値をthis.単価にセットする。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@※親注文が成行注文の場合、時価を基準とした執行単価で計算しているため、<BR>
     * 　@　@　@　@　@　@　@　@処理中の時価変動を考慮し執行単価を記憶しておく。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@[引数設定仕様]<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@親注文の注文単位　@：　@this.親注文の注文単位<BR>
     * 　@　@　@　@　@　@　@　@指値　@：　@super.get単価()の戻り値<BR>
     * 　@　@　@　@　@　@　@　@単価調整値　@：　@リクエストデータ.単価調整値情報 == nullの場合、null<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@以外、リクエストデータ.単価調整値情報.get単価調整値()<BR>
     * 　@　@　@　@　@　@　@　@先物OP取引銘柄　@：　@親注文の注文単位.getTradedProduct()<BR>
     * <BR>
     * 　@　@１−２）　@this.単価を返却する。<BR>
     * <BR>
     * 　@２）　@リクエストデータ == 完了リクエストの場合<BR>
     * <BR>
     * 　@　@　@　@リクエストデータ.単価調整値情報==null（指値／成行）の場合、<BR>
     * 　@　@　@　@　@super.get単価()の戻り値を返却する。<BR>
     * 　@　@　@　@リクエストデータ.単価調整値情報≠null（±指値指定）の場合、<BR>
     * 　@　@　@　@　@リクエストデータ.調整後単価を返却する。<BR>
     * <BR>
     * 　@　@　@ ※調整後単価がnullの場合、「執行単価がnull」の例外をthrowする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_02707<BR>
     * 　@　@　@ ※調整後単価が0以下の場合、「執行単価が0以下」の例外をthrowする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_02298<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 47BD2FCC0100
     */
    public double getPrice() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPrice()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        //リクエストデータ == 確認リクエストの場合
        //this.単価 == 0の場合は、連続注文マネージャ.get先物OP予約注文執行単価()をコールし、
        //戻り値をthis.単価にセットする。
        //※親注文が成行注文の場合、時価を基準とした執行単価で計算しているため、
        //処理中の時価変動を考慮し執行単価を記憶しておく。
        //[引数設定仕様]
        //親注文の注文単位　@：　@this.親注文の注文単位
        //指値　@：　@super.get単価()の戻り値
        //単価調整値　@：　@リクエストデータ.単価調整値情報 == nullの場合、null
        //以外、リクエストデータ.単価調整値情報.get単価調整値()
        //先物OP取引銘柄　@：　@親注文の注文単位.getTradedProduct()
        if (super.request instanceof WEB3SuccOptionsOpenConfirmRequest)
        {
            if (GtlUtils.Double.isZero(this.price))
            {
                WEB3SuccOptionsOpenConfirmRequest l_succOptionsOpenConfirmRequest =
                    (WEB3SuccOptionsOpenConfirmRequest)super.request;

                if (l_succOptionsOpenConfirmRequest.priceAdjustmentValueInfo == null)
                {
                    this.price = l_orderManager.getReserveIfoOrderExecPrice(
                        this.parentOrderUnit,
                        super.getPrice(),
                        null,
                        (WEB3IfoTradedProductImpl)this.parentOrderUnit.getTradedProduct());
                }
                else
                {
                    this.price = l_orderManager.getReserveIfoOrderExecPrice(
                        this.parentOrderUnit,
                        super.getPrice(),
                        new Double(l_succOptionsOpenConfirmRequest.priceAdjustmentValueInfo.getPriceAdjustmentValue()),
                        (WEB3IfoTradedProductImpl)this.parentOrderUnit.getTradedProduct());
                }
            }

            //this.単価を返却する
            log.exiting(STR_METHOD_NAME);
            return this.price;
        }
        //リクエストデータ == 完了リクエストの場合
        else if (this.request instanceof WEB3SuccOptionsOpenCompleteRequest)
        {
            //リクエストデータ.単価調整値情報==null（指値／成行）の場合、super.get単価()の戻り値を返却する
            //リクエストデータ.単価調整値情報≠null（±指値指定）の場合、リクエストデータ.調整後単価を返却する
            //※調整後単価がnullの場合、「執行単価がnull」の例外をthrowする。
            //※調整後単価が0以下の場合、「執行単価が0以下」の例外をthrowする。
            WEB3SuccOptionsOpenCompleteRequest l_succOptionsOpenCompleteRequest =
                (WEB3SuccOptionsOpenCompleteRequest)this.request;

            if (l_succOptionsOpenCompleteRequest.priceAdjustmentValueInfo == null)
            {
                log.exiting(STR_METHOD_NAME);
                return super.getPrice();
            }
            else
            {
                if (l_succOptionsOpenCompleteRequest.afterAdjustmentPrice == null)
                {
                    log.debug("執行単価がnull。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02707,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "執行単価がnull。");
                }

                double l_dblAfterAdjustmentPrice =
                    Double.parseDouble(l_succOptionsOpenCompleteRequest.afterAdjustmentPrice);

                if (GtlUtils.Double.isZero(l_dblAfterAdjustmentPrice) || l_dblAfterAdjustmentPrice < 0)
                {
                    log.debug("執行単価が0以下。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02298,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "執行単価が0以下。 ");
                }

                log.exiting(STR_METHOD_NAME);
                //リクエスト.調整後単価を返却する
                return l_dblAfterAdjustmentPrice;
            }
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }
    }
}
@
