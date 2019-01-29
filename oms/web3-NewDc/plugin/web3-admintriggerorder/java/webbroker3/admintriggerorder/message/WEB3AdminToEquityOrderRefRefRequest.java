head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToEquityOrderRefRefRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・株式注文照会リクエスト(WEB3AdminToEquityOrderRefRefRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/03　@魏新(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.admintriggerorder.define.WEB3AdminToEquityKeyItemDef;

/**
 * (トリガー注文管理者・株式注文照会リクエスト)<BR>
 * 
 * @@author 魏新<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminToEquityOrderRefRefRequest extends WEB3AdminToOrderRefRefCommonRequest 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToEquityOrderRefRefRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_equity_order_ref_ref";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603031700L;

    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     * <BR>
     */
    public String productCode;
    
    /**
     * コンストラクタ<BR>
     * @@roseuid 43F1B3C80261
     */
    public WEB3AdminToEquityOrderRefRefRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@スーパークラスのvalidate()をコールする。  <BR>
     * <BR>
     * ２）　@銘柄コードチェック  <BR>
     * 　@this.銘柄コード != nullの場合、以下のチェックを行う。 <BR>
     * 　@２－１）this.銘柄コードが以下の条件に該当する場合、 <BR>
     * 　@　@　@　@「銘柄コードエラー」の例外をスローする。 <BR>
     * 　@　@　@　@・銘柄コード != 数字 <BR>
     * 　@　@　@　@・銘柄コード.length != 5  <BR>
     * <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag  : BUSINESS_ERROR_01067<BR>
     * <BR>
     * ３）ソートキーチェック <BR>
     *　@３－１）this.ソートキーの要素数分以下の処理を繰り返す。 <BR>  
     *　@　@３－１－１）ソートキー.キー項目に下記の項目以外が <BR>  
     *　@　@　@設定されていたら、 <BR>
     *　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。<BR>
     *　@　@　@　@・「部店コード」 <BR>
     *　@　@　@　@・「顧客コード」 <BR>
     *　@　@　@　@・「銘柄コード」 <BR>
     *　@　@　@　@・「市場コード」 <BR>
     *　@　@　@　@・「口座区分」 <BR>
     *　@　@　@　@・「取引区分」 <BR>
     *　@　@　@　@・「弁済」 <BR>
     *　@　@　@　@・「値段条件」 <BR>
     *　@　@　@　@・「執行条件」 <BR>
     *　@　@　@　@・「注文有効期限」 <BR>
     *　@　@　@　@・「注文時間」 <BR>
     *　@　@　@　@・「発注日」 <BR>
     *　@　@　@　@・「受渡日」 <BR>
     *<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag  : BUSINESS_ERROR_00086<BR>
     *<BR>       
     * @@throws WEB3BaseException
     * @@roseuid 43DF1E9E0073
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@スーパークラスのvalidate()をコールする。
        super.validate();
        //２）　@銘柄コードチェック
        if(this.productCode != null)
        {
            if(!WEB3StringTypeUtility.isNumber(this.productCode) || this.productCode.length() != 5)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    "銘柄コードの入力が不正です。");  
            }
        }
        // ３）　@ソートキーチェック 
        //　@３－１）this.ソートキーの要素数分以下の処理を繰り返す。 
        //　@　@３－１－１）ソートキー.キー項目に下記の項目以外が
        //　@　@　@設定されていたら、 
        //　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。
        //　@　@　@　@・「部店コード」
        //　@　@　@　@・「顧客コード」
        //　@　@　@　@・「銘柄コード」
        //　@　@　@　@・「市場コード」
        //　@　@　@　@・「口座区分」
        //　@　@　@　@・「取引区分」
        //　@　@　@　@・「弁済」
        //　@　@　@　@・「値段条件」
        //　@　@　@　@・「執行条件」
        //　@　@　@　@・「注文有効期限」
        //　@　@　@　@・「注文時間」
        //　@　@　@　@・「発注日」
        //　@　@　@　@・「受渡日」
        int l_intLen = this.sortKeys.length;
        for (int i = 0; i < l_intLen; i++)
        {
            if (!(WEB3AdminToEquityKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToEquityKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToEquityKeyItemDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToEquityKeyItemDef.MARKET_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToEquityKeyItemDef.TAX_TYPE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToEquityKeyItemDef.TRADING_TYPE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToEquityKeyItemDef.REPAYMENT_TYPE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToEquityKeyItemDef.PRICE_CONDITION_TYPE.equals(this.sortKeys[i].keyItem)                
                || WEB3AdminToEquityKeyItemDef.EXEC_COND_TYPE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToEquityKeyItemDef.EXPIRATION_DATE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToEquityKeyItemDef.ORDER_DATE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToEquityKeyItemDef.ORDER_BIZ_DATE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToEquityKeyItemDef.DELIVERY_DATE.equals(this.sortKeys[i].keyItem)))
            {
                log.debug("ソートキーのキー項目の値が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ソートキーのキー項目の値が存在しないコード値です。");
            }
        }         
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createResponseの実装)<BR>
     * <BR>
     * トリガー注文管理者・株式注文照会レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E78F6401A5
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminToEquityOrderRefRefResponse(this);
    }
}
@
