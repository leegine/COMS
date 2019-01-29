head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.26.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAcceptCancelInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式注文受付取消認証入力リクエスト(WEB3AdminFeqOrderAcceptCancelInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 郭英 (中訊) 新規作成
                 : 2005/08/02 王煜 (中訊) レビュー
Revesion History : 2009/08/03 車進(中訊)   モデル　@No.506対応
*/
package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者外国株式注文受付取消認証入力リクエスト)<BR>
 * 管理者外国株式注文受付取消認証入力リクエストクラス
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAcceptCancelInputRequest extends WEB3GenRequest 
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderAcceptCancelInputRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_orderAcceptInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (部店コード)<BR>
     * 部店コード
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     * <BR>
     * ※未指定の場合は、null。
     */
    public String accountCode;
    
    /**
     * (発注日)<BR>
     * 発注日<BR>
     * <BR>
     * ※未指定の場合は、null。
     */
    public Date orderBizDate;
    
    /**
     * (運用コード)<BR>
     * 運用コード<BR>
     * <BR>
     * ※未指定の場合は、null。
     */
    public String managementCode;
    
    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     * <BR>
     * ※未指定の場合は、null。
     */
    public String marketCode;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     * <BR>
     * ※未指定の場合は、null。
     */
    public String productCode;
    
    /**
     * (受付区分)<BR>
     * 受付区分<BR>
     * <BR>
     * 0：注文受付中<BR>
     * 1：訂正取消中<BR>
     * 2：注文受付済<BR>
     * <BR>
     * ※未指定の場合は、null。
     */
    public String[] acceptDiv;
    
    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数
     */
    public String pageSize;
    
    /**
     * (ソートキー)<BR>
     * 外国株式ソートキー
     */
    public WEB3ForeignSortKey[] sortKeys;
    
    /**
     * @@roseuid 42CE39FB033C
     */
    public WEB3AdminFeqOrderAcceptCancelInputRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。 <BR>
     * <BR>
     * １）　@部店コードのチェック <BR>
     * 　@※入力がある場合のみ以下のチェックを行う。 <BR>
     * 　@１－１）　@半角3byteの数字でない場合、例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00834<BR>
     * <BR>
     * ２）　@顧客コードのチェック <BR>
     * 　@※入力がある場合のみ以下のチェックを行う。 <BR>
     * 　@２－１）　@桁数が6でない場合、例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00836<BR>
     * 　@２－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_01043<BR>
     * 　@２－３）　@部店コードに入力がない場合、例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00833<BR>
     * （顧客コード指定<BR>時のみ、部店コード必須）<BR>
     * <BR>
     * ３）　@運用コードのチェック<BR>
     * 　@※入力がある場合のみ以下のチェックを行う。 <BR>
     * 　@３－１）　@5桁の半角数字でない場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_03163<BR>
     * <BR>
     * ４）　@要求ページ番号チェック <BR>
     * 　@４－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
     * 　@４－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00090<BR>
     * 　@４－３）　@0以下の場合、例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * ５）　@ページ内表示行数チェック <BR>
     * 　@５－１）　@未入力の場合、例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00091<BR>
     * 　@５－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00092<BR>
     * 　@５－３）　@0以下の場合、例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     * ６）　@ソートキーのチェック <BR>
     * 　@６－１）　@ソートキーが未入力lの場合、例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00231<BR>
     * 　@６－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00232<BR>
     * 　@６－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。 <BR>
     * 　@　@　@６－３－１）　@ソートキー.validate()をコールする。 <BR>
     * 　@　@　@６－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、<BR>
     *  　@　@例外をスローする。 <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * 　@　@　@　@ 外国株式注文明細.運用コード <BR>
     * 　@　@　@　@ 外国株式注文明細.注文番号<BR>
     * 　@　@　@　@ 外国株式注文明細.部店コード<BR>
     * 　@　@　@　@ 外国株式注文明細.顧客コード<BR>
     * 　@　@　@　@ 外国株式注文明細.特定口座区分<BR>
     * 　@　@　@　@ 外国株式注文明細.注文時間<BR>
     * 　@　@　@　@ 外国株式注文明細.決済区分<BR>
     * 　@　@　@　@ 外国株式注文明細.市場コード<BR>
     * 　@　@　@　@ 外国株式注文明細.銘柄コード<BR>
     * 　@　@　@　@ 外国株式注文明細.売買区分<BR>
     *          外国株式注文明細.発注日
     * @@throws WEB3BaseException
     * @@roseuid 42A5382D0227
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@部店コードのチェック  
        // ※入力がある場合のみ以下のチェックを行う。          
        if (!WEB3StringTypeUtility.isEmpty(this.branchCode))
        {
            //１－１）　@半角3byteの数字でない場合、例外をスローする。
            int l_intCnt = WEB3StringTypeUtility.getByteLength(this.branchCode);
            boolean l_blnFlag= WEB3StringTypeUtility.isDigit(this.branchCode);
            if (l_intCnt != 3 || !l_blnFlag)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "部店コードのサイズが不正です:" + this.branchCode); 
            }
        }
        
        //２）　@顧客コードのチェック  
        //※入力がある場合のみ以下のチェックを行う。
        if (!WEB3StringTypeUtility.isEmpty(this.accountCode))
        {
            //２－１）　@桁数が6でない場合、例外をスローする。
            if (WEB3StringTypeUtility.getByteLength(this.accountCode) != 6)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "顧客コードのサイズが不正です:" + this.accountCode); 
            }
            
            //２－２）　@数字以外の文字が含まれる場合、例外をスローする。
            if (!WEB3StringTypeUtility.isDigit(this.accountCode))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "顧客コードの値が数字以外の値です:" + this.accountCode); 
            }
            
            //２－３）　@部店コードに入力がない場合、例外をスローする。
            //（顧客コード指定時のみ、部店コード必須）
            if (WEB3StringTypeUtility.isEmpty(this.branchCode)) 
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "部店コードが未指定です。" ); 
            }            
        }
        
        //３）　@運用コードのチェック 
        //※入力がある場合のみ以下のチェックを行う。
        if (!WEB3StringTypeUtility.isEmpty(this.managementCode))
        {
            //３－１）　@5桁の半角数字でない場合、例外をスローする。
            int l_intCnt = this.managementCode.length();
            boolean l_blnFlag= WEB3StringTypeUtility.isDigit(this.managementCode);
            if (l_intCnt != 5 || !l_blnFlag)
            {
                log.debug("運用コードが5桁の半角数字ではありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03163,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "運用コードが5桁の半角数字ではありません。" + this.managementCode); 
            }
        }

        //４）　@要求ページ番号チェック  
        //４－１）　@未入力の場合、 要求ページ番号に”１”をセットする。
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            this.pageIndex = "1";
        }

        //４－２）　@数字以外の文字が含まれる場合、例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です:" + this.pageIndex); 
        }

        //４－３）　@0以下の場合、例外をスローする。
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です:" + this.pageIndex); 
        }
        
        //５）　@ページ内表示行数チェック  
        //５－１）　@未入力の場合、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。"); 
        }
        
        //５－２）　@数字以外の文字が含まれる場合、例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です:" + this.pageSize); 
        }
        
        //５－３）　@0以下の場合、例外をスローする。
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です:" + this.pageSize); 
        } 

        //６）　@ソートキーのチェック  
        //６－１）　@ソートキーが未入力lの場合、例外をスローする。
        if (this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーが未指定です。"); 
        }
        
        //６－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。
        if (this.sortKeys.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーの要素数が０です。"); 
        }
        
        //６－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。
        int l_intCnt = this.sortKeys.length;
        for (int i = 0; i < l_intCnt; i ++)
        {
            WEB3ForeignSortKey l_key = sortKeys[i];
            if (l_key != null)
            {
                //６－３－１）　@ソートキー.validate()をコールする。
                l_key.validate();
                
                //６－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、 例外をスローする。
                //      外国株式注文明細.運用コード  
                //      外国株式注文明細.注文番号 
                //      外国株式注文明細.部店コード 
                //      外国株式注文明細.顧客コード 
                //      外国株式注文明細.特定口座区分 
                //      外国株式注文明細.注文時間 
                //      外国株式注文明細.決済区分 
                //      外国株式注文明細.市場コード 
                //      外国株式注文明細.銘柄コード 
                //      外国株式注文明細.売買区分 
                //      外国株式注文明細.発注日
                if (!(WEB3FeqSortKeyItemNameDef.ORDER_EMP_CODE.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.ORDER_NO.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.BRANCH_CODE.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.ACCOUNT_CODE.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.TAX_TYPE_DIV.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.ORDER_TIME.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.SETTLE_DIV.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.MARKET_CODE.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.PRODUCT_CODE.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.BUY_SELL_DIV.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.BIZ_DATE.equals(l_key.keyItem)))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "ソートキーのキー項目の値が存在しないコード値です:" + l_key.keyItem); 
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
        return new WEB3AdminFeqOrderAcceptCancelInputResponse(this);
    }
}
@
