head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.17.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCloseMarginConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物返済注文確認リクエスト(WEB3FuturesCloseMarginConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 鄒鋭 (中訊) 新規作成
Revesion History : 2008/03/11 張騰宇　@仕様変更モデル825
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (株価指数先物返済注文確認リクエスト)<BR>
 * 株価指数先物返済確認リクエストクラス
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3FuturesCloseMarginConfirmRequest extends WEB3FuturesCommonRequest
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesCloseMarginConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_closeMarginConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200407191546L;

    /**
     * (返済建玉)<BR>
     */
    public WEB3FuturesOptionsCloseMarginContractUnit[] closeMarginContractUnits;

    /**
     * (決済順序)<BR>
     * 0：ランダム　@1：単価益順　@2：単価損順　@3：建日順<BR>
     * 一括返済時の場合設定
     */
    public String closingOrder;

    /**
     * @@roseuid 40F7AE180186
     */
    public WEB3FuturesCloseMarginConfirmRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@スーパークラスのvalidateメソッドを呼び出す<BR>
     * <BR>
     * ２）　@返済建玉チェック<BR>
     * 　@this.返済建玉がnullの値または、要素数が０で<BR>
     * 　@あれば例外をスローする。<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00178<BR>
     * <BR>
     * ３）　@決済順序チェック<BR>
     * 　@this.決済順序がnull以外の値でかつ、<BR>
     * 　@this.決済順序が以下の値以外の場合例外をスローする。<BR>
     * 　@　@　@　@・”0：ランダム”<BR>
     * 　@　@　@　@・”1：単価益順”<BR>
     * 　@　@　@　@・”2：単価損順”<BR>
     * 　@　@　@　@・”3：建日順”<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00179<BR>
     * <BR>
     * ４）　@決済パターン・.決済順序チェック<BR>
     * 　@一括返済(this.返済建玉の要素数>1)　@and<BR>
     * 　@this.決済順序==nullの場合、例外「一括返済時、決済順序は指定してください。」をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02304<BR>
     * ５）　@注文数量チェック<BR>
     * 　@５－１）this.決済順序がnullの値または、<BR>
     * 　@　@　@　@”1：単価益順”、”2：単価損順”、”3：建日順”の値でかつ、<BR>
     * 　@　@　@　@this.注文数量がnullの値であれば例外をスローする。<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00245<BR>
     * 　@５－２）this.注文数量がnull以外の値でかつ、<BR>
     * 　@　@　@　@this.注文数量が数字以外の値であれば例外をスローする。<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00075<BR>
     * 　@５－３）this.注文数量がnull以外の値でかつ、<BR>
     * 　@　@　@　@this.注文数量≦０であれば例外をスローする。<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00076<BR>
     * <BR>
     * ６）　@決済順序チェック<BR>
     * 　@６－１）this.決済順序=”0：ランダム” の場合、<BR>
     *           返済建玉の要素数分下記のチェックを繰り返して行う。<BR>
     *           ・返済建玉のvalidate()メソッドを呼び出す。<BR>
     * 　@６－２）this.決済順序=”0：ランダム” and<BR>
     * 　@　@　@　@  要素内のすべての決済順位=null or 0 の場合、<BR>
     *           例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00180<BR>
     * 　@６－３）this.決済順序=”0：ランダム” and<BR>
     * 　@　@　@　@  （返済建玉.決済順位>0 and 返済建玉.数量>0 <BR>
     *           となる組み合わせが存在しない） 場合、<BR>
     *           例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00180<BR>
     * 　@６－４）this.決済順序=（null or ”1：単価益順” or<BR>
     *            ”2：単価損順” or ”3：建日順”） の場合<BR>
     *   　@　@　@　@返済建玉の要素数分下記のチェックを繰り返して行う。<BR>
     *     ６－４－１）返済建玉.決済順位≠null の場合、<BR>
     *                  例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00183<BR>
     *     ６－４－２）返済建玉のvalidate()メソッドを呼び出す。<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A2D7130272
     */
    public void validate() throws WEB3BaseException
    {
        //１）　@スーパークラスのvalidateメソッドを呼び出す
        super.validate();

        //２）　@返済建玉チェック
        //this.返済建玉がnullの値または、要素数が０であれば例外をスローする。
        if (closeMarginContractUnits == null || closeMarginContractUnits.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00178, 
                this.getClass().getName() + "validate",
                "返済建玉がnullの値または、要素数が０である場合のエラー");
        }

        //３）　@決済順序チェック
        //this.決済順序がnull以外の値でかつ、this.決済順序が以下の値以外の場合例外をスローする。
        if (WEB3StringTypeUtility.isNotEmpty(this.closingOrder) 
            && !WEB3ClosingOrderDef.RANDOM.equals(closingOrder) 
            && !WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(closingOrder) 
            && !WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(closingOrder) 
            && !WEB3ClosingOrderDef.OPEN_DATE.equals(closingOrder))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00179, 
                this.getClass().getName() + "validate",
                "決済順序が”0：ランダム”、”1：単価益順”、”2：単価損順”、”3：建日順”いずれかに該当しない場合ｴﾗｰ。");
        }

        // ４）　@決済パターン・.決済順序チェック
        // 　@一括返済(this.返済建玉の要素数>1)　@and
        // 　@this.決済順序==nullの場合、例外「一括返済時、決済順序は指定してください。」をスローする。
        //   class: WEB3BusinessLayerException
        //   tag:   BUSINESS_ERROR_02304
        if (this.closeMarginContractUnits.length > 1
            && this.closingOrder == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02304,
                this.getClass().getName() + "validate",
                "一括返済時、決済順序は指定してください。");
        }

        //５）　@注文数量チェック
        //５－１）this.決済順序がnullの値または、”1：単価益順”、”2：単価損順”、”3：建日順”の値でかつ、this.注文数量がnullの値であれば例外をスローする。
        if ((WEB3StringTypeUtility.isEmpty(closingOrder)
            || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(closingOrder) 
            || WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(closingOrder) 
            || WEB3ClosingOrderDef.OPEN_DATE.equals(closingOrder)) 
            && (WEB3StringTypeUtility.isEmpty(super.futOrderQuantity)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00245, 
                this.getClass().getName() + "validate",
                "決済順序がnullの値または、”1：単価益順”、”2：単価損順”、”3：建日順”の値でかつ、注文数量がnullの値である");
        }

        //５－２）this.注文数量がnull以外の値でかつ、this.注文数量が数字以外の値であれば例外をスローする。
        if ((WEB3StringTypeUtility.isNotEmpty(super.futOrderQuantity)) 
            && !WEB3StringTypeUtility.isNumber(super.futOrderQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075, 
                this.getClass().getName() + "validate",
                "注文数量がnull以外の値でかつ、注文数量が数字以外の値である");

        }

        //５－３）this.注文数量がnull以外の値でかつ、this.注文数量≦０の値であれば例外をスローする。
        if (WEB3StringTypeUtility.isNotEmpty(super.futOrderQuantity) && Long.parseLong(super.futOrderQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076, 
                this.getClass().getName() + "validate",
                "注文数量がnull以外の値でかつ、注文数量≦０である");
        }

        // ６）　@決済順序チェック
        //   ６－１）this.決済順序=”0：ランダム” の場合、
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
            
            //   ６－２）this.決済順序=”0：ランダム” and
            //     要素内のすべての決済順位=null or 0 の場合、例外をスローする。
            if (l_intPriorityCnt == l_intContractUnitsLength)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00180,
                    this.getClass().getName() + "validate",
                    "決済順序が“0：ランダム”の場合、返済建玉.決済順位に0以外の数値を入力してください。");
            }

            //   ６－３）this.決済順序=”0：ランダム” and
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
        
        //　@ ６－４）this.決済順序=(null or"1：単価益順"or"2：単価損順"or"3：建日順")の場合
        //　@　@     返済建玉の要素数分下記のチェックを繰り返して行う。
        if (WEB3StringTypeUtility.isEmpty(this.closingOrder)
            || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.closingOrder)
            || WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.closingOrder)
            || WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
        {
            for (int i = 0; i < l_intContractUnitsLength; i++)
            {
                // ６－４－１）返済建玉.決済順位≠null の場合、例外をスローする。
                if (WEB3StringTypeUtility.isNotEmpty(closeMarginContractUnits[i].settlePriority))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00183,
                        this.getClass().getName() + "validate",
                        "決済順序がランダム指定以外の場合、返済建玉の数量と決済順位は入力出来ません。");
                }
      
                // ６－４－２）返済建玉のvalidate()メソッドを呼び出す。
                closeMarginContractUnits[i].validate();
            }
        }
    }

    /**
     * (validateAT反対取引)<BR>
     * 反対取引指定時の、当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * （連続注文用のメソッド） <BR>
     * １）　@スーパークラスのvalidateメソッドを呼び出す <BR>
     * <BR>
     * ２）　@返済建玉チェック <BR>
     * 　@２－１）this.返済建玉=null の場合、 <BR>
     *           「返済建玉が未指定です。」の例外をスローする。 <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag: BUSINESS_ERROR_00178<BR>
     * <BR>
     * 　@２－２）this.返済建玉の要素数=0 の場合、 <BR>
     * 　@　@　@　@　@「返済建玉が未指定です。」の例外をスローする。 <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag: BUSINESS_ERROR_00178<BR>
     * <BR>
     * ３）　@決済順序チェック <BR>
     * 　@this.決済順序≠null and <BR>
     * 　@this.決済順序≠（以下の値） の場合、 <BR>
     * 「決済順序の値が存在しないコード値です。」の例外をスローする。 <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag: BUSINESS_ERROR_00179<BR>
     * 　@　@　@　@・”0：ランダム” <BR>
     * 　@　@　@　@・”1：単価益順” <BR>
     * 　@　@　@　@・”2：単価損順” <BR>
     * 　@　@　@　@・”3：建日順” <BR>
     * <BR>
     * ４）　@注文数量チェック <BR>
     * 　@４－１）this.決済順序=（null or ”1：単価益順” or ”2：単価損順” or <BR>
     * 　@　@　@　@　@”3：建日順”） and <BR>
     * 　@　@　@　@　@this.注文数量=null の場合、 <BR>
     * 　@　@　@　@　@「決済順序がランダム指定以外の場合、数量は必須入力項目です。」<BR>
     * 　@　@　@　@　@　@の例外をスローする。 <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag: BUSINESS_ERROR_00245<BR>
     * 　@４－２）this.注文数量≠null and this.注文数量≠数字 の場合、 <BR>
     * 　@　@　@　@　@「注文数量が数字以外の値です。」の例外をスローする。 <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag: BUSINESS_ERROR_00075<BR>
     * 　@４－３）this.注文数量≠null and this.注文数量≦0 の場合、 <BR>
     * 　@　@　@　@　@「注文数量が0以下の値です。」の例外をスローする。 <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag: BUSINESS_ERROR_00076<BR>
     * <BR>
     * <BR>
     * ５）　@返済建玉の注文数量チェック <BR>
     * 　@５－１）決済順序＝”0：ランダム”の場合のみ、 <BR>
     * 　@　@　@　@返済建玉の要素数分 <BR>
     * 　@　@　@　@下記のチェックを繰り返して行う。 <BR>
     * 　@　@　@　@※反対取引の場合は、実際には１明細のみが設定されてくる。 <BR>
     * 　@　@　@　@※また、ランダム以外の場合は、リクエスト.注文数量を使用するので、 <BR>
     * 　@　@　@　@※チェック不要。 <BR>
     * 　@　@　@　@・返済建玉.数量 が以下のいずれかの場合は、 <BR>
     * 　@　@　@　@　@「返済建玉の数量指定が不正」の例外をスローする。 <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag: BUSINESS_ERROR_03060<BR>
     * 　@　@　@　@　@　@・null  <BR>
     * 　@　@　@　@　@　@・数字以外  <BR>
     * 　@　@　@　@　@　@・０以下の数字  <BR>
     * 　@　@　@　@　@　@・８桁を超える数字<BR>
     * @@throws WEB3BaseException
     */
    public void validateAtReverseOrder() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateAtReverseOrder()";
        log.entering(STR_METHOD_NAME);

        //１）　@スーパークラスのvalidateメソッドを呼び出す
        super.validate();

        //２）　@返済建玉チェック
        //２－１）this.返済建玉=null の場合、
        //「返済建玉が未指定です。」の例外をスローする。
        if (this.closeMarginContractUnits == null)
        {
            log.debug("返済建玉が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "返済建玉が未指定です。");
        }

        //２－２）this.返済建玉の要素数=0 の場合、
        // 「返済建玉が未指定です。」の例外をスローする。
        if (this.closeMarginContractUnits.length == 0)
        {
            log.debug("返済建玉が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "返済建玉が未指定です。");
        }

        //３）　@決済順序チェック 
        //this.決済順序≠null and this.決済順序≠（以下の値） の場合、
        //「決済順序の値が存在しないコード値です。」の例外をスローする。
        //　@　@　@　@・”0：ランダム”
        //　@　@　@　@・”1：単価益順”
        //　@　@　@　@・”2：単価損順”
        //　@　@　@　@・”3：建日順”
        if (this.closingOrder != null
            && !WEB3ClosingOrderDef.RANDOM.equals(this.closingOrder)
            && !WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.closingOrder)
            && !WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.closingOrder)
            && !WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
        {
            log.debug("決済順序の値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00179,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "決済順序の値が存在しないコード値です。");
        }

        //４）　@注文数量チェック
        //　@４－１）this.決済順序=（null or ”1：単価益順” or ”2：単価損順” or ”3：建日順”） and
        //          this.注文数量=null の場合、
        //          「決済順序がランダム指定以外の場合、数量は必須入力項目です。」の例外をスローする。
        if ((this.closingOrder == null
                || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.closingOrder)
                || WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.closingOrder)
                || WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
            && this.futOrderQuantity == null)
        {
            log.debug("決済順序がランダム指定以外の場合、数量は必須入力項目です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00245,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "決済順序がランダム指定以外の場合、数量は必須入力項目です。");
        }

        //　@４－２）this.注文数量≠null and this.注文数量≠数字 の場合、
        //          「注文数量が数字以外の値です。」の例外をスローする。
        if (this.futOrderQuantity != null && !WEB3StringTypeUtility.isNumber(this.futOrderQuantity))
        {
            log.debug("注文数量が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文数量が数字以外の値です。"); 
        }

        //　@４－３）this.注文数量≠null and this.注文数量≦0 の場合、
        //          「注文数量が0以下の値です。」の例外をスローする。
        if (this.futOrderQuantity != null && Long.parseLong(this.futOrderQuantity) <= 0)
        {
            log.debug("注文数量が0以下の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文数量が0以下の値です。"); 
        }

        //５）　@返済建玉の注文数量チェック
        //　@５－１）決済順序＝”0：ランダム”の場合のみ、
        //　@　@　@　@返済建玉の要素数分
        //　@　@　@　@下記のチェックを繰り返して行う。
        //　@　@　@　@※反対取引の場合は、実際には１明細のみが設定されてくる。
        //　@　@　@　@※また、ランダム以外の場合は、リクエスト.注文数量を使用するので、
        //　@　@　@　@※チェック不要。
        //　@　@　@　@・返済建玉.数量 が以下のいずれかの場合は、
        //　@　@　@　@　@「返済建玉の数量指定が不正」の例外をスローする。
        //　@　@　@　@　@　@・null
        //　@　@　@　@　@　@・数字以外
        //　@　@　@　@　@　@・０以下の数字
        //　@　@　@　@　@　@・８桁を超える数字
        if (WEB3ClosingOrderDef.RANDOM.equals(this.closingOrder))
        {
            int l_intLength = this.closeMarginContractUnits.length;
            for (int i = 0; i < l_intLength; i++)
            {
                WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContract =
                    this.closeMarginContractUnits[i];
                String l_strOrderQuantity = l_closeMarginContract.contractOrderQuantity;
                if (l_strOrderQuantity == null
                    || !WEB3StringTypeUtility.isNumber(l_strOrderQuantity)
                    || Long.parseLong(l_strOrderQuantity) <= 0
                    || l_strOrderQuantity.length() > 8)
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
    * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
    *<BR>
    * @@return レスポンスオブジェクト
    */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesCloseMarginConfirmResponse(this);
    }
}
@
