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
filename	WEB3AdminToTradeStopListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・取扱停止一覧リクエスト(WEB3AdminToTradeStopListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04 呉衛安(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.define.WEB3TargetTypeDef;
import webbroker3.admintriggerorder.define.WEB3AdminToTradeStopKeyItemDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (トリガー注文管理者・取扱停止一覧リクエスト)<BR>
 * トリガー注文管理者・取扱停止一覧リクエストクラス<BR>
 * 
 * @@author 呉衛安
 * @@version 1.0 
 */
public class WEB3AdminToTradeStopListRequest extends WEB3AdminToCommonRequest 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToTradeStopListRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_trade_stop_list";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 20060403164900L;
    
    /**
     * (処理区分)<BR>
     * 処理区分<BR>
     * <BR>
     * 1：　@商品<BR>
     * 2：　@市場<BR>
     * 3：　@銘柄<BR>
     */
    public String tradeStopDiv;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode = null;
    
    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号 <BR>
     * <BR>
     * 表示させたいページ位置を指定　@※先頭ページを"1"とする<BR>
     * ※ページングを行う必要がある場合のみセット。<BR>
     */
    public String pageIndex = null;
    
    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数 <BR>
     * <BR>
     * １ページ内に表示させたい行数を指定<BR>
     * ※ページングを行う必要がある場合のみセット。<BR>
     */
    public String pageSize = null;
    
    /**
     * (ソートキー)<BR>
     */
    public WEB3AdminToTradeStopSortKey[] sortKeys;
    
    /**
     * @@roseuid 4430D2C000AB
     */
    public WEB3AdminToTradeStopListRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@super.validate()をコールする。<BR>
     * <BR>
     * ２）　@処理区分チェック<BR>
     * 　@２－１）　@this.処理区分 == nullの場合、<BR>
     * 　@　@「処理区分が未入力」の例外をスローする。<BR>
     *      class : WEB3BusinessLayerException<BR>
     *      tag : BUSINESS_ERROR_01249<BR>
     * <BR>
     * 　@２－２）　@this.処理区分に以下の値以外が設定されていた<BR>
     * 　@　@場合、「処理区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@・"商品"<BR>
     * 　@　@　@・"市場"<BR>
     * 　@　@　@・"銘柄"<BR>
     *      class : WEB3BusinessLayerException<BR>
     *      tag : BUSINESS_ERROR_01250<BR>
     * <BR>
     * ３）　@銘柄コードチェック<BR>
     * 　@this.銘柄コード != nullの場合、以下のチェックを行う。<BR>
     * 　@３－１）　@this.銘柄コードが以下の条件に該当する場合、<BR>
     * 　@　@「銘柄コードエラー」の例外をスローする。<BR>
     * 　@　@・銘柄コード != 数字<BR>
     * 　@　@・銘柄コード.length != 5<BR>
     *      class : WEB3BusinessLayerException<BR>
     *      tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * ４）　@ソートキーチェック<BR>
     * 　@this.ソートキー != nullの場合、以下のチェックを行う。<BR>
     * 　@４－１）　@this.ソートキーの要素数分以下の処理を行う。<BR>
     * 　@　@４－１－１）　@ソートキー.キー項目に以下の値以外が<BR>
     * 　@　@　@設定されていた場合、<BR>
     * 　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・"銘柄コード"<BR>
     * 　@　@　@　@・"有効期限From"<BR>
     *      class : WEB3BusinessLayerException<BR>
     *      tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * 　@　@４－１－２）　@ソートキー.validate()メソッドをコールする。<BR>
     * <BR>
     * ５）　@要求ページ番号チェック <BR>
     * 　@this.要求ページ番号 != nullの場合、以下のチェックを行う。<BR>
     * 　@５－１）　@this.要求ページ番号 != 数字の場合、 <BR>
     * 　@　@「要求ページ番号が数字以外」の例外をスローする。 <BR>
     *      class : WEB3BusinessLayerException<BR>
     *      tag : BUSINESS_ERROR_00090<BR>
     * <BR>
     * 　@５－２）　@this.要求ページ番号 <= 0であった場合、 <BR>
     * 　@　@「要求ページ番号が0以下」の例外をスローする。 <BR>
     *      class : WEB3BusinessLayerException<BR>
     *      tag : BUSINESS_ERROR_00616<BR>
     * <BR>
     * ６）　@ページ内表示行数チェック <BR>
     * 　@this.ページ内表示行数 != nullの場合、以下のチェックを行う。<BR>
     * 　@６－１）　@this.ページ内表示行数 != 数字の場合、 <BR>
     * 　@　@「ページ内表示行数が数字以外」の例外をスローする。 <BR>
     *      class : WEB3BusinessLayerException<BR>
     *      tag : BUSINESS_ERROR_00092<BR>
     * 　@ <BR>
     * 　@６－２）　@this.ページ内表示行数 <= 0であった場合、 <BR>
     * 　@　@「ページ内表示行数が0以下」の例外をスローする。 <BR>
     *      class : WEB3BusinessLayerException<BR>
     *      tag : BUSINESS_ERROR_00617<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4406B75701B6
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）　@super.validate()をコールする。
        super.validate();
        
        // ２）　@処理区分チェック
        //　@２－１）　@this.処理区分 == nullの場合、
        //　@　@「処理区分が未入力」の例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.tradeStopDiv))
        {
            log.debug("処理区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01249,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "処理区分が未指定です。");
        }      

        //　@２－２）　@this.処理区分に以下の値以外が設定されていた
        //　@　@場合、「処理区分が未定義の値」の例外をスローする。
        //　@　@　@・"商品"
        //　@　@　@・"市場"
        //　@　@　@・"銘柄"
        if (!WEB3TargetTypeDef.COMMODITY.equals(this.tradeStopDiv)
            && !WEB3TargetTypeDef.MARKET.equals(this.tradeStopDiv)
            && !WEB3TargetTypeDef.PRODUCT.equals(this.tradeStopDiv))
        {
            log.debug("処理区分の値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01250,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "処理区分の値が存在しないコード値です。");
        }  
        
        // ３）　@銘柄コードチェック
        //　@this.銘柄コード != nullの場合、以下のチェックを行う。
        //　@３－１）　@this.銘柄コードが以下の条件に該当する場合、
        // 　@　@「銘柄コードエラー」の例外をスローする。
        // 　@　@・銘柄コード != 数字
        //　@　@ ・銘柄コード.length != 5
        if (WEB3StringTypeUtility.isNotEmpty(this.productCode)
            && (!WEB3StringTypeUtility.isDigit(this.productCode) 
                || this.productCode.length() != 5))
        {
            log.debug("銘柄コードの入力が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄コードの入力が不正です。");
        } 
        
        // ４）　@ソートキーチェック
        //　@this.ソートキー != nullの場合、以下のチェックを行う。
        if (this.sortKeys != null && this.sortKeys.length > 0)
        {
            //　@４－１）　@this.ソートキーの要素数分以下の処理を行う。
            int l_intSortKeysLen = this.sortKeys.length;
            for (int i = 0; i < l_intSortKeysLen; i++)
            {
                //　@　@４－１－１）　@ソートキー.キー項目に以下の値以外が
                //　@　@　@設定されていた場合、
                //　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。
                //　@　@　@　@・"銘柄コード"
                //　@　@　@　@・"有効期限From"
                if (this.sortKeys[i] != null
                    && !WEB3AdminToTradeStopKeyItemDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem) 
                    && !WEB3AdminToTradeStopKeyItemDef.EXPIRATION_START_DATE.equals(this.sortKeys[i].keyItem))  
                {
                    log.debug("ソートキーのキー項目の値が存在しないコード値です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00086, 
                        this.getClass().getName() + "." + STR_METHOD_NAME, 
                        "ソートキーのキー項目の値が存在しないコード値です。");
                }
                
                //　@　@４－１－２）　@ソートキー.validate()メソッドをコールする。
                this.sortKeys[i].validate();
            }            
        }
        
        // ５）　@要求ページ番号チェック 
        //　@this.要求ページ番号 != nullの場合、以下のチェックを行う。
        if (WEB3StringTypeUtility.isNotEmpty(this.pageIndex))
        {
            //　@５－１）　@this.要求ページ番号 != 数字の場合、 
            //　@　@「要求ページ番号が数字以外」の例外をスローする。
            if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
            {
                log.debug("要求ページ番号が数字以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "要求ページ番号が数字以外の値です。");
            }
    
            //　@５－２）　@this.要求ページ番号 <= 0であった場合、 
            //　@　@「要求ページ番号が0以下」の例外をスローする。 
            if (Integer.parseInt(this.pageIndex) <= 0)
            {
                log.debug("要求ページ番号の値が0以下です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "要求ページ番号の値が0以下です。");
            }            
        }
        
        // ６）　@ページ内表示行数チェック 
        //　@this.ページ内表示行数 != nullの場合、以下のチェックを行う。
        if (WEB3StringTypeUtility.isNotEmpty(this.pageSize))
        {
            //　@６－１）　@this.ページ内表示行数 != 数字の場合、 
            //　@　@「ページ内表示行数が数字以外」の例外をスローする。 
            if (!WEB3StringTypeUtility.isInteger(this.pageSize))
            {
                log.debug("ページ内表示行数が数字以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ページ内表示行数が数字以外の値です。");
            }
            
            //　@６－２）　@this.ページ内表示行数 <= 0であった場合、 
            //　@　@「ページ内表示行数が0以下」の例外をスローする。 
            if (Integer.parseInt(this.pageSize) <= 0)
            {
                log.debug("ページ内表示行数の値が0以下です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ページ内表示行数の値が0以下です。");
            }    
        }
        log.exiting(STR_METHOD_NAME);         
    }
    
    /**
     * (createResponseの実装)<BR>
     * <BR>
     * トリガー注文管理者・取扱停止一覧レスポンスオブジェクトを返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 41E78F6401A5
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminToTradeStopListResponse(this);
    }
}@
