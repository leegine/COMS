head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.18.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション返済完了リクエストクラス(WEB3OptionsCloseMarginCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 呉艶飛 新規作成
              001: 2004/07/22 王暁傑 (中訊) WEB3ClosingOrderDefでWEB3IfoMessageTypeDefを差し替える
              002: 2004/07/30 王暁傑 (中訊) 対応バッグ WEB3_IFO_UT-000088
Revesion History : 2008/03/11 金傑(中訊) モデル 826                              
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (株価指数オプション返済完了リクエスト)<BR>
 * 株価指数オプション返済完了リクエストクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginCompleteRequest extends WEB3OptionsCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "options_closeMarginComplete";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406111915L;
        
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3OptionsCloseMarginCompleteRequest.class);

    /**
     * 返済建玉
     */
    public WEB3FuturesOptionsCloseMarginContractUnit[] closeMarginContractUnits;
   
    /**
     * (決済順序)<BR>
     * 0：ランダム　@1：単価益順　@2：単価損順　@3：建日順<BR>
     * 一括返済時の場合設定<BR>
     */
    public String closingOrder;
   
    /**
     * 暗証番号
     */
    public String password;
   
    /**
     * (確認時単価)<BR>
     * 確認レスポンスで送信した値。<BR>
     */
    public String checkPrice;
   
    /**
     * (確認時発注日)<BR>
     * 確認レスポンスで送信した値。<BR>
     */
    public Date checkDate;

    /**
     * (注文ID)<BR>
     * 確認レスポンスで送った値。<BR>
     */
    public String orderId;
   
    /**
     * @@roseuid 40C0A8F0002E
     */
    public WEB3OptionsCloseMarginCompleteRequest() 
    {
    
    }
   
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@スーパークラスのvalidateメソッドを呼び出す<BR>
     * <BR>
     * ２）　@返済建玉チェック<BR>
     *   ２−１）this.返済建玉=null の場合、<BR>
     *         　@例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00178<BR>
     *   ２−２）this.返済建玉の要素数=0 の場合、<BR>
     *         　@例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00178<BR>
     * <BR>
     * ３）　@決済順序チェック<BR>
     * 　@this.決済順序がnull以外の値でかつ、<BR>
     *   this.決済順序が以下の値以外の場合例外をスローする。<BR>
     * 　@　@　@　@・”0：ランダム”<BR>
     * 　@　@　@　@・”1：単価益順”<BR>
     * 　@　@　@　@・”2：単価損順”<BR>
     * 　@　@　@　@・”3：建日順”<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00179<BR>
     * <BR>
     * ４）　@決済パターン・.決済順序チェック <BR>
     * 　@一括返済(this.返済建玉の要素数>1)　@and　@ <BR>
     * 　@this.決済順序==nullの場合、例外「一括返済時、決済順序は指定してください。」をスローする。<BR> 
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02304<BR>
     * <BR>
     * ５）　@注文数量チェック<BR>
     * 　@５−１）this.決済順序がnullの値または、<BR>
     * 　@　@　@　@”1：単価益順”、”2：単価損順”、”3：建日順”の値でかつ、<BR>
     * 　@　@　@　@this.注文数量がnullの値であれば例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00074<BR>
     * 　@５−２）this.注文数量がnull以外の値でかつ、<BR>
     * 　@　@　@　@this.注文数量が数字以外の値であれば例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00075<BR>
     * 　@５−３）this.注文数量がnull以外の値でかつ、<BR>
     * 　@　@　@　@this.注文数量≦０の値であれば例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00076<BR>
     * <BR>
     * ６）　@決済順序チェック<BR>
     *   ６−１）this.決済順序=”0：ランダム” の場合、<BR>
     *           返済建玉の要素数分下記のチェックを繰り返して行う。<BR>
     *           返済建玉のvalidate()メソッドを呼び出す。<BR>
     *   ６−２）this.決済順序=”0：ランダム” の場合、以下のチェックを行う。<BR>
     * 　@　@６−２−１）返済建玉の全要素について、<BR>
     *                返済建玉.決済順位=null or 0 の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00180<BR>
     * 　@　@６−２−２）返済建玉の全要素について、<BR>
     *                返済建玉.決済順位>0 and 返済建玉.数量>0 となる<BR>
     *                組み合わせが存在しない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00180<BR>
     * 　@６−３）this.決済順序=（null or ”1：単価益順” or ”2：単価損順” or ”3：建日順”）の場合、<BR>
     *           返済建玉の要素数分下記のチェックを繰り返して行う。<BR>
     * 　@　@６−３−１）返済建玉.決済順位≠null の場合、<BR>
     *                  例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00183<BR>
     * 　@　@６−３−２）返済建玉のvalidate()メソッドを呼び出す。<BR>
     * <BR>
     * ７）　@確認時単価チェック(this.注文ID==nullの場合、チェックを行わない）<BR>
     * 　@this.確認時単価が初期値（未入力）の値であれば例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00206<BR>
     * <BR>
     * ８）　@確認時発注日チェック(this.注文ID==nullの場合、チェックを行わない）<BR>
     * 　@this.確認時発注日が初期値（未入力）の値であれば例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00078<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4069798F0374
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@スーパークラスのvalidateメソッドを呼び出す
        super.validate();
        
        
        //２）返済建玉チェック
        if (closeMarginContractUnits == null || closeMarginContractUnits.length == 0)
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00178,
                this.getClass().getName() + "validate",
                "返済建玉がnullの値または、要素数が０である場合のエラー");
        }

        //３）決済順序チェック
        log.info(this.closingOrder);
        if (this.closingOrder != null
            && !WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(closingOrder)
            && !WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(closingOrder)
            && !WEB3ClosingOrderDef.OPEN_DATE.equals(closingOrder)
            && !WEB3ClosingOrderDef.RANDOM.equals(closingOrder))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00179,
                this.getClass().getName() + "validate",
                "決済順序が”0：ランダム”、”1：単価益順”、”2：単価損順”、”3：建日順”いずれかに該当しない場合ｴﾗｰ。");
        }

        //４）　@決済パターン・.決済順序チェック 
        //一括返済(this.返済建玉の要素数>1)　@and　@ 
        // 　@this.決済順序==nullの場合、例外「一括返済時、決済順序は指定してください。」をスローする。
        if (this.closeMarginContractUnits.length > 1 && this.closingOrder == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02304,
                this.getClass().getName() + "validate",
                "一括返済時、決済順序は指定してください。");
        }
        
        //５）　@注文数量チェック
        //５−１）this.決済順序がnullの値または、”1：単価益順”、”2：単価損順”、”3：建日順”の値でかつ、this.注文数量がnullの値であれば例外をスローする。
        log.info(this.closingOrder);
        if ((WEB3StringTypeUtility.isEmpty(this.closingOrder)
            || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(closingOrder)
            || WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(closingOrder)
            || WEB3ClosingOrderDef.OPEN_DATE.equals(closingOrder))
            && super.opOrderQuantity == null)
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00245,
                this.getClass().getName() + "validate",
                "決済順序がnullの値または、”1：単価益順”、”2：単価損順”、”3：建日順”の値でかつ、注文数量がnullの値である。");
        }

        //５−２）this.注文数量がnull以外の値でかつ、this.注文数量が数字以外の値であれば例外をスローする。
        if (WEB3StringTypeUtility.isNotEmpty(super.opOrderQuantity) 
            && !WEB3StringTypeUtility.isNumber(super.opOrderQuantity))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                this.getClass().getName() + "validate",
                "注文数量が数字以外の値である。");
        }

        //５−３）this.注文数量がnull以外の値でかつ、this.注文数量≦０の値であれば例外をスローする。 
        if (WEB3StringTypeUtility.isNotEmpty(super.opOrderQuantity)
            && Long.parseLong(super.opOrderQuantity) <= 0)
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                this.getClass().getName() + "validate",
                "注文数量が0以下の値である。");
        }

        // ６）　@決済順序チェック
        //   ６−１）this.決済順序=”0：ランダム” の場合、
        //      返済建玉の要素数分下記のチェックを繰り返して行う。
        //         ・返済建玉のvalidate()メソッドを呼び出す。
        int l_intContractUnitsLength = closeMarginContractUnits.length;
        int l_intPriorityCnt = 0;
        int l_intPriorityQuantityCnt = 0;
        if (WEB3ClosingOrderDef.RANDOM.equals(this.closingOrder))
        {
            for (int i = 0; i < l_intContractUnitsLength; i++)
            {
                // 返済建玉のvalidate()メソッドを呼び出す。
                closeMarginContractUnits[i].validate();
            
                // 要素内のすべての決済順位=null or 0 の場合。
                if (WEB3StringTypeUtility.isEmpty(closeMarginContractUnits[i].settlePriority)
                    || Double.parseDouble(closeMarginContractUnits[i].settlePriority) == 0)
                {
                    l_intPriorityCnt += 1;
                }
            
                // 返済建玉.決済順位>0 and 返済建玉.数量>0、
                //     となる組み合わせが存在しない場合。
                if (!WEB3StringTypeUtility.isEmpty(closeMarginContractUnits[i].settlePriority) 
                    && (Double.parseDouble(closeMarginContractUnits[i].settlePriority) > 0
                    && Double.parseDouble(closeMarginContractUnits[i].contractOrderQuantity) > 0))
                {
                    l_intPriorityQuantityCnt += 1;
                }
            }
            
            //   ６−２−１）this.決済順序=”0：ランダム” and
            //     要素内のすべての決済順位=null or 0 の場合、例外をスローする。
            if (l_intPriorityCnt == l_intContractUnitsLength)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00180,
                    this.getClass().getName() + "validate",
                    "決済順序が“0：ランダム”の場合、返済建玉.決済順位に0以外の数値を入力してください。");
            }

            //   ６−２−２）this.決済順序=”0：ランダム” and
            //    （返済建玉.決済順位>0 and 返済建玉.数量>0 となる組み合わせが存在しない） 場合、
            //     例外をスローする。
            if (l_intPriorityQuantityCnt == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00180,
                    this.getClass().getName() + "validate",
                    "決済順序が“0：ランダム”の場合は、（返済建玉.決済順位>0 and 返済建玉.数量>0) となる組み合わせが存 在しない場合はエラー。");
            }
        }
    
        //　@ ６−３）this.決済順序=(null or"1：単価益順"or"2：単価損順"or"3：建日順")の場合
        //　@　@     返済建玉の要素数分下記のチェックを繰り返して行う。
        if (WEB3StringTypeUtility.isEmpty(this.closingOrder)
            || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.closingOrder)
            || WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.closingOrder)
            || WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
        {
            for (int i = 0; i < l_intContractUnitsLength; i++)
            {
                // ６−３−１）返済建玉.決済順位≠null の場合、例外をスローする。
                if (WEB3StringTypeUtility.isNotEmpty(closeMarginContractUnits[i].settlePriority))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00183,
                        this.getClass().getName() + "validate",
                        "決済順序がランダム指定以外の場合、返済建玉の数量と決済順位は入力出来ません。");
                }
  
                // ６−３−２）返済建玉のvalidate()メソッドを呼び出す。
                closeMarginContractUnits[i].validate();
            }
        }
        
        // ７）　@確認時単価チェック(this.注文ID==nullの場合、チェックを行わない）
        //  this.確認時単価が初期値（未入力）の値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isEmpty(this.orderId) 
            && WEB3StringTypeUtility.isEmpty(this.checkPrice))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00206, 
                this.getClass().getName() + "validate",
                "確認時単価がnullの値である");
        }

        // ８）　@確認時発注日チェック(this.注文ID==nullの場合、チェックを行わない）
        // this.確認時発注日が初期値（未入力）の値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isEmpty(this.orderId) 
            && this.checkDate == null)
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078, 
                this.getClass().getName() + "validate",
                "確認時発注日がnullの値である。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateAT反対取引) <BR>
     * 反対取引指定時の、当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * （連続注文用のメソッド） <BR>
     * １）　@スーパークラスのvalidateメソッドを呼び出す<BR>
     *<BR>
     * ２）　@注文IDチェック <BR>
     * 　@２−１）this.注文ID＝nullの場合、<BR>
     * 　@　@　@　@「注文IDがnull」の例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_00600<BR>
     * <BR>
     * ３）　@返済建玉チェック<BR>
     * 　@３−１）this.返済建玉=null の場合、<BR>
     * 　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_00178<BR>
     * 　@３−２）this.返済建玉の要素数=0 の場合、<BR>
     * 　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_00178<BR>
     * <BR>
     * ４）　@決済順序チェック<BR>
     * 　@this.決済順序≠null and <BR>
     * 　@this.決済順序≠（以下の値） の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_00179<BR>
     * 　@　@　@　@・”0：ランダム”<BR>
     * 　@　@　@　@・”1：単価益順” <BR>
     * 　@　@　@　@・”2：単価損順” <BR>
     * 　@　@　@　@・”3：建日順” <BR>
     * <BR>
     * ５）　@注文数量チェック<BR>
     * 　@５−１）this.決済順序=（null or ”1：単価益順” or ”2：単価損順” or <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@”3：建日順”） and <BR>
     * 　@　@　@　@this.注文数量=null の場合、<BR>
     * 　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_00245<BR>
     * 　@５−２）this.注文数量≠null and this.注文数量≠数字 の場合、<BR>
     * 　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_00075<BR>
     * 　@５−３）this.注文数量≠null and this.注文数量≦0 の場合、<BR>
     * 　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_00076<BR>
     * <BR>
     * <BR>
     * ６）　@返済建玉の注文数量チェック<BR>
     * 　@６−１）決済順序＝”0：ランダム”の場合のみ、<BR>
     * 　@　@　@　@返済建玉の要素数分<BR>
     * 　@　@　@　@下記のチェックを繰り返して行う。<BR>
     * 　@　@　@　@※反対取引の場合は、実際には１明細のみが設定されてくる。<BR>
     * 　@　@　@　@※また、ランダム以外の場合は、リクエスト.注文数量を使用するので、<BR>
     * 　@　@　@　@※チェック不要。<BR>
     * 　@　@　@　@・返済建玉.注文数量 が以下のいずれかの場合は、<BR>
     * 　@　@　@　@「返済建玉の注文数量指定が不正」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_03060<BR>
     * 　@　@　@　@　@・null<BR>
     * 　@　@　@　@　@・数字以外<BR>
     * 　@　@　@　@　@・０以下の数字<BR>
     * 　@　@　@　@　@・８桁を超える数字<BR>
     * <BR>
     * ７）　@確認時単価チェック<BR>
     * 　@this.確認時単価＝nullであった場合、「確認時単価がnull」の <BR>
     * 　@例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_00206<BR>
     * <BR>
     * ８）　@確認時発注日チェック<BR>
     * 　@this.確認時発注日＝nullであった場合、「確認時発注日がnull」の<BR>
     * 　@例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_00078<BR>
     *
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validateATReserveOrder()throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateATReserveOrder()";
        log.entering(STR_METHOD_NAME);
        
        // １）　@スーパークラスのvalidateメソッドを呼び出す
        super.validate();
        
        // ２）　@注文IDチェック
        // ２−１）this.注文ID＝nullの場合、
        if (this.orderId == null)
        {
            // 「注文IDがnull」の例外をスローする。
            log.debug("注文IDが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文IDが未指定です。");
        }

        // ３）　@返済建玉チェック
        // ３−１）this.返済建玉=null の場合
        if (this.closeMarginContractUnits == null)
        {
            // 例外をスローする。
            log.debug("返済建玉が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "返済建玉が未指定です。");
        }
        // ３−２）this.返済建玉の要素数=0 の場合
        if (this.closeMarginContractUnits.length == 0)
        {
            // 例外をスローする。
            log.debug("返済建玉が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "返済建玉が未指定です。");
        }
        
        // ４）　@決済順序チェック
        // this.決済順序≠null and  this.決済順序≠（以下の値） の場合、例外をスローする。
            //・”0：ランダム” 
            //・”1：単価益順” 
            //・”2：単価損順” 
            //・”3：建日順” 
        if (WEB3StringTypeUtility.isNotEmpty(this.closingOrder) &&
            !WEB3ClosingOrderDef.RANDOM.equals(this.closingOrder) &&
            !WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.closingOrder) &&
            !WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.closingOrder) &&
            !WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
        {
            // 例外をスローする。
            log.debug("決済順序の値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00179,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "決済順序の値が存在しないコード値です。");
        }
        
        // ５）　@注文数量チェック
        // ５−１）this.決済順序=（null or ”1：単価益順” or ”2：単価損順” or ”3：建日順”） and 
        //                        this.注文数量=null の場合、
        if ((this.closingOrder == null ||
            WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.closingOrder) ||
            WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.closingOrder) ||
            WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder)) &&
            this.opOrderQuantity == null)
        {
            // 例外をスローする。
            log.debug("決済順序がランダム指定以外の場合、数量は必須入力項目です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00245,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "決済順序がランダム指定以外の場合、数量は必須入力項目です。");
        }
        // ５−２）this.注文数量≠null and this.注文数量≠数字 の場合、例外をスローする。
        if (this.opOrderQuantity != null &&
            !WEB3StringTypeUtility.isNumber(this.opOrderQuantity))
        {
            // 例外をスローする。
            log.debug("注文数量が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文数量が数字以外の値です。");
        }
        // ５−３）this.注文数量≠null and this.注文数量≦0 の場合、例外をスローする。
        if (this.opOrderQuantity != null)
        {
            double l_dblOrderQuantity = Double.parseDouble(this.opOrderQuantity);
            if (l_dblOrderQuantity <= 0)
            {
                // 例外をスローする。
                log.debug("注文数量が0以下の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注文数量が0以下の値です。"); 
            }
        }
        // ６）　@返済建玉の注文数量チェック
        // ６−１）決済順序＝”0：ランダム”の場合のみ、
        if (WEB3ClosingOrderDef.RANDOM.equals(this.closingOrder))
        {
            // 返済建玉の要素数分
            // 下記のチェックを繰り返して行う。
            int l_intCloseMarginContractCnt = this.closeMarginContractUnits.length;
            for (int i = 0; i < l_intCloseMarginContractCnt; i++)
            {
                // 返済建玉.注文数量 が以下のいずれかの場合は、「返済建玉の注文数量指定が不正」の例外をスローする。
                // ・null
                // ・数字以外
                // ・０以下の数字
                // ・８桁を超える数字
                String l_strContractOrderQuantity = this.closeMarginContractUnits[i].contractOrderQuantity;
                if(l_strContractOrderQuantity==null ||
                    !WEB3StringTypeUtility.isNumber(l_strContractOrderQuantity) ||
                    Long.parseLong(l_strContractOrderQuantity) <= 0 ||
                    l_strContractOrderQuantity.length() > 8)
                {
                    log.debug("返済建玉の注文数量指定が不正。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03060,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "返済建玉の注文数量指定が不正。"); 
                }
            }
        }
        
        // ７）　@確認時単価チェック
        // this.確認時単価＝nullであった場合、「確認時単価がnull」の例外をスローする。
        if (this.checkPrice == null)
        {
            // 「確認時単価がnull」の例外をスローする。
            log.debug("確認時単価が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00206,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "確認時単価が未指定です。");
        }
        
        // ８）　@確認時発注日チェック 
        // this.確認時発注日＝nullであった場合、「確認時発注日がnull」の例外をスローする。
        if (this.checkDate == null)
        {
            // 「確認時単価がnull」の例外をスローする。
            log.debug("確認時発注日が入力されていません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "確認時発注日が入力されていません。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
   
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3OptionsCloseMarginCompleteResponse(this);
    }
}
@
