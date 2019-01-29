head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.05.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託解約確認リクエストクラス(WEB3MutualSellConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 黄建 (中訊) 新規作成
Revesion History : 2004/08/25 周勇 (中訊) レビュー
Revesion History : 2005/10/20 韋念瓊 (中訊) フィデリティ対応
Revesion History : 2007/02/03 張騰宇 (中訊) 仕様変更・モデル536
*/
package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ClaimDivDef;
import webbroker3.common.define.WEB3DeliveryMethodDef;
import webbroker3.common.define.WEB3PaymentMethodDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 投資信託解約確認リクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0  
 */

public class WEB3MutualSellConfirmRequest 
    extends WEB3MutualCommonRequest 
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_sell_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408111346L;
  
    /**
     * 請求方法@<BR>
     * <BR>
     * 0:解約請求　@1:買取請求<BR>
     */
    public String sellBuyDiv;
    
    /**
     * 決済方法@<BR>
     * <BR>
     * 1:円貨　@2:外貨<BR>
     */
    public String settleDiv;
    
    /**
     * 受渡方法@<BR>
     * <BR>
     * 1:銀行振込み　@2:証券口座入金<BR>
     * 
     */
    public String deliveryDiv;
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualSellConfirmRequest.class);
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 40A89C6B0036
     */
    public WEB3MutualSellConfirmRequest() 
    {
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 投信解約確認レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A89B3B016F
     */
    
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MutualSellConfirmResponse(this);
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １)　@銘柄コードチェック<BR>
     * 　@this.銘柄コードがnullの場合、例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00079 <BR>
     * <BR>
     * ２)　@指定方法@チェック<BR>
     * 　@２－１)　@this.指定方法@がnullの場合、例外をスローする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00400 <BR>
     * 　@２－２)　@this.指定方法@が以下のコード以外の場合、例外をスローする。<BR>
     * 　@　@　@　@・”全部”<BR>
     * 　@　@　@　@・”金額”<BR>
     * 　@　@　@　@・”口数”<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00073 <BR>
     * <BR>
     * ３）　@(解約)数量チェック <BR>
     * 　@３－１）　@this.指定方法@が“全部”且つ、this.数量!=nullの場合 <BR>
     * 　@　@　@　@　@である場合、 例外をスローする。 <BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00093 <BR>
     * 　@３－２）　@this.指定方法@が“金額”または“口数”であり且つ、 <BR>
     * 　@　@　@　@　@this.数量==nullの場合、例外をスローする。 <BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00094 <BR>
     * 　@３－３）　@this.指定方法@が“金額”または“口数”であり且つ、 <BR>
     * 　@　@　@　@　@this.数量が数値以外である場合、例外をスローする。 <BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00095 <BR>
     * 　@３－４）　@this.指定方法@が“金額”または“口数”であり且つ、 <BR>
     * 　@　@　@　@　@this.数量≦0の場合、例外をスローする。 <BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00096 <BR>
     * 　@３－５）　@this.指定方法@が“金額”または“口数”であり且つ、 <BR>
     * 　@　@　@　@　@this.数量の桁数＞10桁の場合、例外をスローする。<BR> 
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00097 <BR>
     * <BR>
     * ４)　@請求方法@チェック<BR>  
     * 　@４－１)　@this.請求方法@が以下の値のいずれかではない場合、例外をスローする。<BR>  
     * 　@　@　@　@・”解約請求”  <BR>
     * 　@　@　@　@・”買取請求”  <BR>
     * 　@　@　@　@・null<BR>
     *            class: WEB3BusinessLayerException<BR><BR>
     *            tag:   BUSINESS_ERROR_00402 <BR><BR>
     * <BR>
     * ５)　@決済方法@チェック<BR>
     * 　@５－１)　@this.決済方法@がnullの場合、例外をスローする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00403 <BR>
     * 　@５－２)　@this.決済方法@が以下のコード以外の場合、例外をスローする。<BR>
     * 　@　@　@　@・”円貨”<BR>
     * 　@　@　@　@・”外貨”<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00404 <BR>
     * <BR>
     * ６)　@受渡方法@チェック<BR>
     * 　@６－１)　@this.受渡方法@がnullの場合、例外をスローする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00405 <BR>
     * 　@６－２)　@this.受渡方法@が以下のコード以外の場合、例外をスローする。<BR>
     * 　@　@　@　@・”銀行振込み”<BR>
     * 　@　@　@　@・”証券口座入金”<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00099 <BR>
     *   ６－３） this.受渡方法@==”銀行振込み”の場合、 <BR>
     *           this.決済方法@!=”円貨”であれば、例外をスローする。 <BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_02286 <BR>
     * <BR>
     * ７）　@発注日のチェック<BR>
     *    this.発注日がnullである場合、例外をスローする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00406 <BR>
     * <BR>
     * ８）　@IDチェック<BR> 
     * 　@this.ID==nullの場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * @@roseuid 40A89B790017
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate() ";
        log.entering(STR_METHOD_NAME);
   
         //１)　@銘柄コードチェック
       
        //this.銘柄コードがnullの場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.mutualProductCode)) 
        {
            log.debug("銘柄コードを入力してください。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄コードを入力してください。");
        }
       
        //２)　@指定方法@チェック  
       
        //２－１)　@this.指定方法@がnullの場合、例外をスローする。                
        if (WEB3StringTypeUtility.isEmpty(this.specifyDiv)) 
        {
            log.debug("指定方法@が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00400,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "指定方法@が未指定です。");
        }
        
        //２－２)　@this.指定方法@が以下のコード以外の場合、例外をスローする。
         //   ・全部・金額・口数
        if (!(((WEB3SellDivDef.ALL_DESIGNATE).equals(this.specifyDiv)) || 
            ((WEB3SellDivDef.MONEY_DESIGNATE).equals(this.specifyDiv)) || 
            ((WEB3SellDivDef.COUNT_DESIGNATE).equals(this.specifyDiv)))) 
        {
            log.debug("指定方法@の値が存在しないコード値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00073,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "「全部」「金額」 「口数」");    
        }
        
        // ３）　@(解約)数量チェック 
        
        //３－１）this.指定方法@が“全部”且つ、
        //this.数量!=nullの場合、 例外をスローする。
        if ((((WEB3SellDivDef.ALL_DESIGNATE).equals(this.specifyDiv))) && 
            ((this.mutualOrderQuantity != null)))
        {
            log.debug("指定方法@が“全部”の場合は、注文数量指定不可。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00093,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "指定方法@が“全部”の場合は、注文数量指定不可。"); 
        }
        
        //３－２）　@this.指定方法@が“金額”または“口数”であり且つ、
        //this.数量==nullの場合、例外をスローする。
        if ((((WEB3SellDivDef.MONEY_DESIGNATE).equals(this.specifyDiv)) || 
            ((WEB3SellDivDef.COUNT_DESIGNATE).equals(this.specifyDiv))) &&
            (WEB3StringTypeUtility.isEmpty(this.mutualOrderQuantity)))
        {
            log.debug("指定方法@が“金額”または“口数”" +
                "であり且つ、注文数量が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00094,
                getClass().getName() + "." + STR_METHOD_NAME,
                "指定方法@が“金額”または“口数”であり且つ、" +
                "注文数量が未指定です。"); 
        }

        //３－３）　@this.指定方法@が“金額”または“口数”であり且つ、
        //this.数量が数値以外である場合、例外をスローする。
        if ((((WEB3SellDivDef.MONEY_DESIGNATE).equals(this.specifyDiv)) || 
            ((WEB3SellDivDef.COUNT_DESIGNATE).equals(this.specifyDiv))))
        {
            // 文字列の文字種を判断する機@能を実装するユーティリティ
            //・クラス(WEB3StringTypeUtility.java)
            if (WEB3StringTypeUtility.isNumber(this.mutualOrderQuantity) == false)
            {
                log.debug("指定方法@が“金額”または“口数”" +
                    "であり且つ注文数量が数値以外です。"); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00095,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@が“金額”または“口数”であり且つ注文数量が数値以外です。"); 
            }
        }
         
        //３－４）　@this.指定方法@が“金額”または“口数”であり且つ、
        //this.数量≦0の場合、例外をスローする。
        if ((((WEB3SellDivDef.MONEY_DESIGNATE).equals(this.specifyDiv)) ||
            ((WEB3SellDivDef.COUNT_DESIGNATE).equals(this.specifyDiv))))
        {
            if (Double.parseDouble(this.mutualOrderQuantity) <= 0)
            {
                log.debug("指定方法@が“金額”または“口数”であり且つ、" +
                    "注文数量が0以下の値です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00096,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@が“金額”または“口数”であり且つ、注文数量が0以下の値です。"); 
            }       
        }
        
        //３－５）　@this.指定方法@が“金額”または“口数”であり且つ、
        //this.数量の桁数＞10桁の場合、例外をスローする。  
        if ((((WEB3SellDivDef.MONEY_DESIGNATE).equals(this.specifyDiv)) ||
            ((WEB3SellDivDef.COUNT_DESIGNATE).equals(this.specifyDiv))))
        {
            if (this.mutualOrderQuantity.length() > 10)
            {
                log.debug("指定方法@が“金額”または“口数”であり、且つ、" +
                    "注文数量が10桁を超える値です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00097,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@が“金額”または“口数”であり、且つ、" +
                    "注文数量が10桁を超える値です。"); 
            }       
        }  
      
        //４)　@請求方法@チェック        
        //　@this.請求方法@が以下のコード以外の場合、例外をスローする。
         // 0:解約請求 1:買取請求・null 
        if (!(((WEB3ClaimDivDef.SELL).equals(this.sellBuyDiv)) || 
            ((WEB3ClaimDivDef.BUY).equals(this.sellBuyDiv))
            || this.sellBuyDiv == null))
        {
            log.debug("請求方法@が”解約請求””買取請求”null 以外の場合。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00402,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "請求方法@が”解約請求””買取請求”null 以外の場合。"); 
        }
     
        // ５)　@決済方法@チェック 
        
        //５－１)　@this.決済方法@がnullの場合、例外をスローする。<BR>
        if (WEB3StringTypeUtility.isEmpty(this.settleDiv))
        {
            log.debug("決済方法@が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00403,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "決済方法@が未指定です。"); 
        }
        
        //５－２)　@this.決済方法@が以下のコード以外の場合、例外をスローする。<BR>
         //   円貨・外貨
        if (!(WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(this.settleDiv) || 
            WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(this.settleDiv)))
        {
            log.debug("決済方法@が”円貨”又は”外貨”以外の場合");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00404,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "決済方法@が”円貨”又は”外貨”以外の場合"); 
        }
       
        // ６)　@受渡方法@チェック
        
        //６－１)　@this.受渡方法@がnullの場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.deliveryDiv))
        {
            log.debug("受渡方法@が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00405,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "受渡方法@が未指定です。"); 
        }
        
        //　@６－２)　@this.受渡方法@が以下のコード以外の場合、例外をスローする。
        //  銀行振込み・証券口座入金
        if (!(((WEB3DeliveryMethodDef.BANK_TRANSFER).equals(this.deliveryDiv)) || 
            ((WEB3DeliveryMethodDef.SECURITIES_ACCOUNT_INPUT_SELL).equals(this.deliveryDiv))))
        {
            log.debug("受渡方法@が、“銀行振込み”または“証券口座入金”以外です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00099,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "受渡方法@が、“銀行振込み”または“証券口座入金”以外です。"); 
        }
        
        //６－３） this.受渡方法@==”銀行振込み”の場合、 
        //      this.決済方法@!=”円貨”であれば、例外をスローする。 
        if (WEB3PaymentMethodDef.BANK_TRANSFER.equals(this.deliveryDiv))
        {
            if (!WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(this.settleDiv))
            {
                log.debug("受渡方法@が”銀行振込み”の場合、決済方法@は必ず”円貨”を選択してください。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02286,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "受渡方法@が”銀行振込み”の場合、決済方法@は必ず”円貨”を選択してください。"); 
            }
        }
        
        // ７）　@発注日のチェック
                
        //this.発注日がnullである場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(WEB3DateUtility.formatDate(this.orderedDate, "yyyyMMdd")))
        {
            log.debug("発注日が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00406,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注日が未指定です。"); 
        }

        // ８）　@IDチェック
        // 　@this.ID==nullの場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            log.debug("投信資産ＩＤが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                getClass().getName() + "." + STR_METHOD_NAME,
                "投信資産ＩＤが未指定です。");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
