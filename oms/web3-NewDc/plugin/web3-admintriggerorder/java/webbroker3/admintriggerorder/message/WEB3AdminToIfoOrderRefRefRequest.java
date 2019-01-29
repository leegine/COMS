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
filename	WEB3AdminToIfoOrderRefRefRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・先物OP注文照会リクエスト(WEB3AdminToIfoOrderRefRefRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/15　@余新敏(中訊) 新規作成
                 : 2006/12/04  唐性峰(中訊)　@モデルNo.069
*/

package webbroker3.admintriggerorder.message;

import webbroker3.admintriggerorder.define.WEB3AdminToIfoKeyItemDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (トリガー注文管理者・先物OP注文照会リクエスト)<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToIfoOrderRefRefRequest extends WEB3AdminToOrderRefRefCommonRequest 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToIfoOrderRefRefRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_ifo_order_ref_ref";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602141850L;
    
    /**
     * (指数種別)<BR>
     * 指数種別<BR>
     * <BR>
     * 0005：　@TOPIX <BR>
     * 0018：　@日経225 <BR>
     * 0016：　@日経300 <BR>
     * 0019：ミニ日経225<BR>
     */
    public String targetProductCode = null;
    
    /**
     * (限月)<BR>
     * 限月<BR>
     * (YYYYMM)<BR>
     */
    public String delivaryMonth = null;
    
    /**
     * (行使価格)<BR>
     * 行使価格<BR>
     */
    public String strikePrice = null;
    
    /**
     * (オプション商品区分)<BR>
     * オプション商品区分<BR>
     * <BR>
     * P：プットオプション <BR>
     * C：コールオプション <BR>
     * <BR>
     * ※先物の場合は、nullをセット。<BR>
     */
    public String opProductType = null;
    
    /**
     * コンストラクタ<BR>
     * @@roseuid 43F1B3C80261
     */
    public WEB3AdminToIfoOrderRefRefRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@スーパークラスのvalidate()をコールする。 <BR>
     * <BR>
     * ２）　@指数種別チェック<BR>
     * 　@this.指数種別≠nullの場合、以下のチェックを行う。<BR>
     * 　@２－１）this.指数種別が数字以外の値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_02441<BR>
     * 　@２－２）this.指数種別の桁数が４桁以外の値であれば例外をスローする。 <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_02442<BR>
     * <BR>
     * ３）　@限月チェック<BR>
     * 　@this.限月≠nullの場合、以下のチェックを行う。<BR>
     * 　@３－１）this.限月≠数字の場合、<BR>
     * 　@「限月が数字以外」の例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02351<BR>
     * <BR>
     * 　@３－２）this.限月≠YYYYMM形式の値であった場合、<BR>
     * 　@「限月日付形式エラー」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00268<BR>
     * <BR>
     * ４）　@行使価格チェック <BR>
     * 　@this.行使価格≠nullの場合、以下のチェックを行う。 <BR>
     * 　@４－１）this.行使価格≠数字の場合、<BR>
     * 　@　@「行使価格が数字以外」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00272<BR>
     * <BR>
     * 　@４－２）this.行使価格 <= 0 の場合、<BR>
     * 　@　@「行使価格が0以下」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00273<BR>
     * <BR>
     * 　@４－３）this.行使価格 > 8桁の値であった場合、<BR>
     * 　@　@「行使価格桁数エラー」の例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00274<BR>
     * <BR>
     * ５）　@オプション商品区分チェック <BR>
     * 　@this.オプション商品区分≠nullの場合、以下のチェックを行う。 <BR>
     * 　@５－１）this.オプション商品区分に下記の項目以外が設定されていたら、 <BR>
     * 　@　@　@　@「オプション商品区分が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@　@・"プット" <BR>
     * 　@　@　@　@・"コール" <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00270<BR>
     * <BR>
     * ６）　@ソートキーチェック <BR>
     * 　@６－１）this.ソートキーの要素数分以下の処理を繰り返す。 <BR>
     * 　@　@６－１－１）ソートキー.キー項目に下記の項目以外が <BR>
     * 　@　@　@設定されていたら、 <BR>
     * 　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。 <BR>
     * <BR>
     * 　@　@　@・「部店コード」<BR>
     * 　@　@　@・「顧客コード」<BR>
     * 　@　@　@・「銘柄コード」<BR>
     * 　@　@　@・「市場コード」<BR>
     * 　@　@　@・「口座区分」<BR>
     * 　@　@　@・「商品区分」<BR>
     * 　@　@　@・「取引区分」<BR>
     * 　@　@　@・「執行条件」<BR>
     * 　@　@　@・「注文有効期限」<BR>
     * 　@　@　@・「注文時間」<BR>
     * 　@　@　@・「発注日」<BR>
     * 　@　@　@・「受渡日」<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00086<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43DF1E9E0073
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@スーパークラスのvalidate()をコールする。 
        super.validate();
        
        //２）　@指数種別チェック
        if (this.targetProductCode != null)
        {
            //　@２－１）this.指数種別が数字以外の値であれば例外をスローする。
            if (!WEB3StringTypeUtility.isNumber(this.targetProductCode))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02441,
                    getClass().getName() + "validate",
                    "指数種別が数字以外の値です。");
            }
            
            //　@２－２）this.指数種別の桁数が４桁以外の値であれば例外をスローする。
            if (this.targetProductCode.length() != 4)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02442,
                    getClass().getName() + "validate",
                    "指数種別のサイズが不正です。");
            }
        }
        
        //３）　@限月チェック
        //　@this.限月≠nullの場合、以下のチェックを行う。
        if (this.delivaryMonth != null)
        {
            //　@３－１）this.限月≠数字の場合、
            //　@「限月が数字以外」の例外をスローする。
            if (!WEB3StringTypeUtility.isInteger(this.delivaryMonth))
            {
                log.debug("限月が数字以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02351,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "限月が数字以外の値です。");
            }
            
            // 　@３－２）this.限月≠YYYYMM形式の値であった場合、
            //　@「限月日付形式エラー」の例外をスローする。 
            if (!WEB3StringTypeUtility.isDateStr(this.delivaryMonth, "yyyyMM"))
            {
                log.debug("限月がＹＹＹＹＭＭ形式で入力してください。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00268,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "限月がＹＹＹＹＭＭ形式で入力してください。");
            }
        }
        
        //４）　@行使価格チェック 
        //　@this.行使価格≠nullの場合、以下のチェックを行う。 
        if (this.strikePrice != null)
        {
            //４－１）this.行使価格≠数字の場合、
            //　@「行使価格が数字以外」の例外をスローする。
            if (!WEB3StringTypeUtility.isInteger(this.strikePrice))
            {
                log.debug("行使価格が数字以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00272,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "行使価格が数字以外の値です。");
            }
            
            //４－２）this.行使価格 <= 0 の場合、
            //　@「行使価格が0以下」の例外をスローする。 
            if (Double.parseDouble(this.strikePrice) <= 0)
            {
                log.debug("行使価格が0以下の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00273,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "行使価格が0以下の値です。");
            }
            
            //４－３）this.行使価格 > 8桁の値であった場合、
            //　@「行使価格桁数エラー」の例外をスローする。
            if (this.strikePrice.length() > 8)
            {
                log.debug("行使価格のサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00274,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "行使価格のサイズが不正です。");
            }
        }
        
        //５）　@オプション商品区分チェック
        //　@this.オプション商品区分≠nullの場合、以下のチェックを行う。
        if (this.opProductType != null)
        {
            //　@５－１）this.オプション商品区分に下記の項目以外が設定されていたら、
            //　@　@　@　@「オプション商品区分が未定義の値」の例外をスローする。
            //　@　@　@　@・"プット" 
            //　@　@　@　@・"コール"
            if (!(WEB3IfoProductTypeDef.PUT_OPTIONS.equals(this.opProductType)
                ||WEB3IfoProductTypeDef.CALL_OPTIONS.equals(this.opProductType)))
            {
                log.debug("オプション商品区分の値が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00270,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "オプション商品区分の値が存在しないコード値です。");
            }
        }

        // ７）　@ソートキーチェック 
        //　@７－１）this.ソートキーの要素数分以下の処理を繰り返す。 
        //　@　@７－１－１）ソートキー.キー項目に下記の項目以外が
        //　@　@　@設定されていたら、 
        //　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。
        //　@　@　@・「部店コード」
        //　@　@　@・「顧客コード」
        //　@　@　@・「銘柄コード」
        //　@　@　@・「市場コード」
        //　@　@　@・「口座区分」
        //　@　@　@・「商品区分」
        //　@　@　@・「取引区分」
        //　@　@　@・「執行条件」
        //　@　@　@・「注文有効期限」
        //　@　@　@・「注文時間」
        //　@　@　@・「発注日」
        //　@　@　@・「受渡日」
        int l_intLen = this.sortKeys.length;
        for (int i = 0; i < l_intLen; i++)
        {
            if (!(WEB3AdminToIfoKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToIfoKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToIfoKeyItemDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToIfoKeyItemDef.MARKET_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToIfoKeyItemDef.TAX_TYPE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToIfoKeyItemDef.PRODUCT_DIV.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToIfoKeyItemDef.TRADING_TYPE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToIfoKeyItemDef.EXEC_COND_TYPE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToIfoKeyItemDef.EXPIRATION_DATE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToIfoKeyItemDef.ORDER_DATE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToIfoKeyItemDef.ORDER_BIZ_DATE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToIfoKeyItemDef.DELIVERY_DATE.equals(this.sortKeys[i].keyItem)))
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
     * トリガー注文管理者・先物OP注文照会レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E78F6401A5
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminToIfoOrderRefRefResponse(this);
    }
}
@
