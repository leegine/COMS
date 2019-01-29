head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.07.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualApplyConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信募集注文確認リクエストクラス(WEB3MutualApplyConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/26 黄建 (中訊) 新規作成
                   2006/09/11 周捷 仕様変更・モデル482
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 投信募集注文確認リクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0   
 */

public class WEB3MutualApplyConfirmRequest extends WEB3MutualCommonRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_apply_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200509261532L;
    
    /**
     * ログ出力ユーティリティ。
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualApplyConfirmRequest.class);
    
    /**
     * (口座区分)<BR>
     *  口座区分<BR>
     *  0:一般　@1:特定<BR>
     */
    public String taxType;
    
    /**
     * (決済方法@)<BR>
     *  決済方法@<BR>
     *  1:円貨　@2:外貨<BR>
     */
    public String settleDiv;
    
    /**
     * (紹介区分)<BR>
     * 紹介区分<BR>
     * <BR>
     * null:指定無し<BR> 
     * 1:直接取引<BR>
     * 2:単純紹介<BR> 
     * 3:商品紹介<BR> 
     * 4:仲介取引<BR>
     */
    public String introduceStoreDiv;
    
    /**
     * 紹介店コード<BR>
     */
    public String introduceStoreCode;
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>  
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>  
     * <BR>
     * １)　@銘柄コードチェック  <BR>
     * this.銘柄コード==nullの場合、例外をスローする。<BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00079 <BR>
     * <BR>
     * ２)　@指定方法@チェック <BR>
     *   ２－１)　@this.指定方法@==nullの場合、例外をスローする。 <BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00400 <BR>
     *   ２－２)　@this.指定方法@が以下の値のいずれかではない場合、例外をスローする。<BR> 
     *  ・”金額” <BR>
     *  ・”口数” <BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00073 <BR>
     * <BR>
     * ３)　@(募集)数量チェック <BR>
     *  ３－１)　@this.数量==nullの場合、例外をスローする。<BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_02230 <BR>
     *  ３－２)　@this.数量が数値以外の場合、例外をスローする。<BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_02231 <BR>
     *  ３－３)　@this.数量≦0の場合、例外をスローする。<BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_02232 <BR>
     *  ３－４)　@this.数量の桁数＞10桁の場合、例外をスローする。 <BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_02233 <BR>
     * <BR>
     * ４)　@口座区分チェック<BR>
     *  ４－１)　@this.口座区分==nullの場合、例外をスローする。<BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00604 <BR>
     *  ４－２)　@this.口座区分が以下のコード以外の場合、例外をスローする。 <BR>
     *      ・”一般” <BR>
     *      ・”特定” <BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00605 <BR>
     * <BR>
     * ５)　@決済方法@チェック <BR>
     *  ５－１)　@this.決済方法@==nullの場合、例外をスローする。<BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00403 <BR>
     *  ５－２)　@this.決済方法@が以下のコード以外の場合、例外をスローする。<BR>
     *      ・”円貨” <BR>
     *      ・”外貨”<BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00404 <BR>
     * <BR>
     * ６）　@発注日のチェック <BR>
     *  this.発注日==nullの場合、例外をスローする。<BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00406 <BR>
     * <BR>
     * ７）　@IDチェック <BR>
     * this.ID≠nullの場合、例外をスローする。<BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_01967 <BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C656B50132
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１)　@銘柄コードチェック 
        //this.銘柄コード==nullの場合、例外をスローする。
        if (this.mutualProductCode == null)
        {
            log.debug("銘柄コードを入力してください。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄コードを入力してください。");
        } 

        //２)　@指定方法@チェック 
        //２－１)　@this.指定方法@==nullの場合、例外をスローする。
        if (this.specifyDiv == null)
        {
            log.debug("指定方法@が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00400,
                getClass().getName() + "." + STR_METHOD_NAME,
                "指定方法@が未指定です。");
        } 
        
        //２－２)　@this.指定方法@が以下の値のいずれかではない場合、例外をスローする。 
        //・”金額” 
        //・”口数”
        if (!(WEB3SellDivDef.MONEY_DESIGNATE.equals(this.specifyDiv) || 
            WEB3SellDivDef.COUNT_DESIGNATE.equals(this.specifyDiv)))
        {
            log.debug("指定方法@の値が、「金額」「口数」以外の値である。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00073,
                getClass().getName() + "." + STR_METHOD_NAME,
                "指定方法@の値が、「金額」「口数」以外の値である。");
        }

        //３)　@(募集)数量チェック 
        //３－１)　@this.数量==nullの場合、例外をスローする。 
        if (this.mutualOrderQuantity == null)
        {
            log.debug("募集数量が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02230,
                getClass().getName() + "." + STR_METHOD_NAME,
                "募集数量が未指定です。");
        }
        
        //３－２)　@this.数量が数値以外の場合、例外をスローする。 
        if (!WEB3StringTypeUtility.isNumber(this.mutualOrderQuantity))
        {
            log.debug("募集数量が数値以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02231,
                getClass().getName() + "." + STR_METHOD_NAME,
                "募集数量が数値以外の値です。");
        }
        
        //３－３)　@this.数量≦0の場合、例外をスローする。 
        if (Double.parseDouble(this.mutualOrderQuantity) <= 0)
        {
            log.debug("募集数量が0以下の値である。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02232,
                getClass().getName() + "." + STR_METHOD_NAME,
                "募集数量が0以下の値である。");
        }
        
        //３－４)　@this.数量の桁数＞10桁の場合、例外をスローする。  
        if (this.mutualOrderQuantity.length() > 10)
        {
            log.debug("募集数量が10桁を超えました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02233,
                getClass().getName() + "." + STR_METHOD_NAME,
                "募集数量が10桁を超えました。");
        }
        
        //４)　@口座区分チェック 
        //４－１)　@this.口座区分==nullの場合、例外をスローする。
        if (this.taxType == null)
        {
            log.debug("口座区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00604,
                getClass().getName() + "." + STR_METHOD_NAME,
                "口座区分が未指定です。");
        } 
        
        //４－２)　@this.口座区分が以下の値のいずれかではない場合、例外をスローする。 
        //・”一般” 
        //・”特定”
        if (!(WEB3TaxTypeSpecialDef.NORMAL.equals(this.taxType)
            || WEB3TaxTypeSpecialDef.SPECIAL.equals(this.taxType)))
        {
            log.debug("口座区分が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00605,
                getClass().getName() + "." + STR_METHOD_NAME,
                "口座区分が存在しないコード値です。");
        }
        
        //５)決済方法@チェック 
        //５－１)　@this.決済方法@==nullの場合、例外をスローする。
        if (this.settleDiv == null)
        {
            log.debug("決済方法@が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00403,
                getClass().getName() + "." + STR_METHOD_NAME,
                "決済方法@が未指定です。");
        }
        
        //５－２)　@this.決済方法@が以下の値のいずれかではない場合、例外をスローする。 
        //・”円貨” 
        //・”外貨” 
        if (!(WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(this.settleDiv)
            || WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(this.settleDiv)))
        {
            log.debug("決済方法@が”円貨”又は”外貨”以外の場合");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00404,
                getClass().getName() + "." + STR_METHOD_NAME,
                "決済方法@が以下のコード以外の場合");
        }

        //６）発注日のチェック 
        //this.発注日==nullの場合、例外をスローする。 
        if (this.orderedDate == null)
        {
            log.debug("発注日が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00406,
                getClass().getName() + "." + STR_METHOD_NAME,
                "発注日が未指定です。");
        }

        //７）IDチェック 
        //this.ID≠nullの場合、例外をスローする。
        if (this.id != null)
        {
            log.debug("投信資産IDが指定不可です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01967,
                getClass().getName() + "." + STR_METHOD_NAME,
                "投信資産ＩＤ = [" + this.id + "]");
        }   
        log.exiting(STR_METHOD_NAME);    
    }
    
    /**
     *（createResponseの実装）<BR>
     * <BR>
     * 投信募集注文確認レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158E9B5029E
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MutualApplyConfirmResponse(this);
    }   
}
@
