head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.05.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託買付注文確認リクエストクラス(WEB3MutualBuyConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/03 周勇 (中訊) 新規作成
Revesion History : 2004/08/23 于美麗 (中訊) レビュー 
Revesion History : 2006/09/11 周捷 仕様変更・モデル482
Revesion History : 2007/02/03 張騰宇 (中訊) 仕様変更・モデル535
*/
package webbroker3.mf.message;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mf.define.WEB3MFAccountDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 投資信託買付注文確認リクエストクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3MutualBuyConfirmRequest extends WEB3MutualCommonRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_buy_confirm";

    /**
     * 口座区分<BR>
     * <BR>
     * 0:一般　@1:特定　@2:その他<BR>
     */
    public String taxType;
    
    /**
     * 決済方法@<BR>
     * <BR>
     * 1:円貨　@2:外貨<BR>
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
     * (紹介店コード)<BR>
     * 紹介店コード<BR>
     */
    public String introduceStoreCode;
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L;   
       
    /**
     * (投信買付注文確認リクエスト)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 40A8807803E0
     */
    public WEB3MutualBuyConfirmRequest()
    {
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBuyConfirmRequest.class); 
        
           
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 投信買付注文確認レスポンスオブジェクトを生成して返す。
     * @@return WEB3GenResponse
     * @@roseuid 40A8808703E0
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3MutualBuyConfirmResponse(this);
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １)　@銘柄コードチェック<BR>
     * 　@this.銘柄コードがnullの場合、例外をスローする。<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00079 <BR>
     * <BR>
     * ２)　@指定方法@チェック<BR>
     * 　@２－１)　@this.指定方法@がnullの場合、例外をスローする。<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00400 <BR>
     * 　@２－２)　@this.指定方法@が以下のコード以外の場合、例外をスローする。<BR>
     * 　@　@　@　@・”金額”<BR>
     * 　@　@　@　@・”口数”<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00073 <BR>
     * <BR>
     * ３)　@(買付)数量チェック<BR>
     * 　@３－１)　@３－１)　@this.数量==nullの場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00418 <BR>
     * 　@３－２)　@this.数量が数値以外の場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00419 <BR>
     * 　@３－３)　@this.数量≦0の場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00420 <BR>
     *　@ ３－４)　@this.数量の桁数＞11桁の場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00421 <BR>
     * <BR>
     * ４)　@口座区分チェック<BR>
     * 　@４－１)　@this.口座区分がnullの場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00415 <BR>
     * 　@４－２)　@this.口座区分が以下のコード以外の場合、例外をスローする。<BR>
     * 　@　@　@　@・”一般”<BR>
     * 　@　@　@　@・”特定”<BR>
     * 　@　@　@　@・”その他”<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00416 <BR>
     * <BR>
     * ５)　@決済方法@チェック<BR>
     * 　@５－１)　@this.決済方法@がnullの場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00403 <BR>
     * 　@５－２)　@this.決済方法@が以下のコード以外の場合、例外をスローする。<BR>
     * 　@　@　@　@・”円貨”<BR>
     * 　@　@　@　@・”外貨”<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00404 <BR>
     * <BR>
     * ６）　@発注日のチェック<BR>
     *    this.発注日がnullである場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00406 <BR>
     * ７）　@IDチェック<BR> 
     *    this.ID≠nullの場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00XXX <BR>
     * <BR>
     * @@roseuid 40A8974102C6
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);       
         
        //this.銘柄コードがnullの場合、例外をスローする
        if (WEB3StringTypeUtility.isEmpty(this.mutualProductCode))
        {
            log.debug("銘柄コードを入力してください。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄コードを入力してください。");
        }
        
        //２－１)　@this.指定方法@がnullの場合、例外をスローする
        if (WEB3StringTypeUtility.isEmpty(this.specifyDiv))
        {
            log.debug("指定方法@が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00400,
                getClass().getName() + "." + STR_METHOD_NAME,
                "指定方法@が未指定です。");
        }
        
        //２－２)　@this.指定方法@が以下のコード以外の場合、例外をスローする
        if (!(WEB3SellDivDef.COUNT_DESIGNATE.equals(this.specifyDiv)
            || WEB3SellDivDef.MONEY_DESIGNATE.equals(this.specifyDiv)))
        {
            log.debug("指定方法@の値が、「金額」「口数」以外の値である。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00073,
                getClass().getName() + "." + STR_METHOD_NAME,
                "指定方法@の値が、「金額」「口数」以外の値である。");
        }
        
        //３－１)　@this.数量==nullの場合、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.mutualOrderQuantity))
        {
            log.debug("（買付）数量が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00418,
                getClass().getName() + "." + STR_METHOD_NAME,
                "（買付）数量が未指定です。");
        }
        
        //３－２)　@this.数量が数値以外の場合、例外をスローする
        try
        {
            Double.parseDouble(this.mutualOrderQuantity);
        }
        catch (NumberFormatException l_ex)
        {
            log.error("数量が数値以外の場合");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00419,
                getClass().getName() + "." + STR_METHOD_NAME,
                "数量が数値以外の場合");
        }
        
        //３－３)　@this.数量≦0の場合、例外をスローする。
        if (Double.parseDouble(this.mutualOrderQuantity) <= 0)
        {
            log.debug("(買付)数量が0以下の値の場合");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00420,
                getClass().getName() + "." + STR_METHOD_NAME,
                "(買付)数量が0以下の値の場合");
        }
        
        //３－４)　@this.数量の桁数＞10桁の場合、例外をスローする。 
        if (this.mutualOrderQuantity.length() > 10)
        {
            log.debug("(買付)数量が10桁を超える値である");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00421,
                getClass().getName() + "." + STR_METHOD_NAME,
                "(買付)数量が10桁を超える値である");
        }
        
        //４－１)　@this.口座区分がnullの場合、例外をスローする
        if (WEB3StringTypeUtility.isEmpty(this.taxType))
        {
            log.debug("買付口座区分が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00415,
                getClass().getName() + "." + STR_METHOD_NAME,
                "買付口座区分が未指定です。");
        }
        
        //４－２)　@this.口座区分が以下のコード以外の場合、例外をスローする
        if (!(WEB3MFAccountDivDef.NORMAL.equals(this.taxType)
            || WEB3MFAccountDivDef.SPECIAL.equals(this.taxType)
            || WEB3MFAccountDivDef.OTHER.equals(this.taxType)))
        {
            log.debug("買付口座区分が”一般”、”特定”、”その他”以外の場合");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00416,
                getClass().getName() + "." + STR_METHOD_NAME,
                "買付口座区分が”一般”、”特定”、”その他”以外の場合");
        }
        
        //５－１)　@this.決済方法@がnullの場合、例外をスローする
        if (WEB3StringTypeUtility.isEmpty(this.settleDiv))
        {
            log.debug("決済方法@が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00403,
                getClass().getName() + "." + STR_METHOD_NAME,
                "決済方法@が未指定です。");
        }
        
        //５－２)　@this.決済方法@が以下のコード以外の場合、例外をスローする
        if (!(WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(this.settleDiv)
            || WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(this.settleDiv)))
        {
            log.debug("決済方法@が”円貨”又は”外貨”以外の場合");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00404,
                getClass().getName() + "." + STR_METHOD_NAME,
                "決済方法@が以下のコード以外の場合");
        }
        
        //this.発注日がnullである場合、例外をスローする
        if (WEB3StringTypeUtility.isEmpty(WEB3DateUtility.formatDate(this.orderedDate, "yyyyMMdd")))
        {
            log.debug("発注日が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00406,
                getClass().getName() + "." + STR_METHOD_NAME,
                "発注日が未指定です。");
        }
        
        // ７）　@IDチェック
        // 　@this.ID≠nullの場合、例外をスローする。
        if (this.id != null)
        {
            log.debug("投信資産IDが指定不可です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01967,
                getClass().getName() + "." + STR_METHOD_NAME,
                "投信資産ＩＤ = [" + this.id + "]");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
