head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MFSortkeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3MFSortkeyItemDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 王蘭芬(中訊)　@新規作成
                 : 2004/12/01 王蘭芬(中訊)　@変更
                   2006/03/08 鈴木 (SRA) 仕様変更（モデル）：403
*/
package webbroker3.mf.define;

/**
 * (投信管理者銘柄表示順序登録入力画面要求クラス)投信ソートキーキー項目<BR>
 *  定数定義インタフェイス
 *                                                                   
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3MFSortkeyItemDef
{
    /**
     * taxType : 口座
     */
    public static final String TAX_TYPE = "taxType";

    /**
     * marketValue : 評価額
     */
    public static final String MARKET_VALUE = "marketValue";

    /**
     * appraisalProfitLoss : 評価損益
     */
    public static final String APPRAISAL_PROFIT_LOSS = "appraisalProfitLoss";
    
    /**
     * orderCloseTime : 注文受付締切時間
     */
    public static final String ORDER_CLOSE_TIME = "orderCloseTime";

    /**
     * mutualDealingType : 売買　@ 
     */
    public static final String MUTUAL_DEALING_TYPE = "mutualDealingType";

    /**
     * orderDate : 注文日時　@  
     */
    public static final String ORDER_DATE = "orderDate";
    
    /**
     * sellBuyDiv : 請求方法@　@  
     */
    public static final String SELL_BUY_DIV = "sellBuyDiv";    

    /**
     * current_display_order : 現在表示順　@  
     */
    public static final String DISPLAY_ORDER = "displayOrder";

    /**
     * product_code : 銘柄コード　@  
     */
    public static final String PRODUCT_CODE = "mutualProductCode";

    /**
     * mutual_assoc_product_code : 投信協会銘柄コード　@  
     */
    public static final String MUTUAL_ASSOC_PRODUCT_CODE = "mutualAssocProductCode";

    /**
     * mutual_category_code1 : 投信銘柄カテゴリーコード１　@  
     */
    public static final String MUTUAL_CATEGORY_CODE_1 = "categoryCode1";

    /**
     * mutual_category_code2 : 投信銘柄カテゴリーコード２　@  
     */
    public static final String MUTUAL_CATEGORY_CODE_2 = "categoryCode2";

    /**
     * mutual_category_code3 : 投信銘柄カテゴリーコード３
     */
    public static final String MUTUAL_CATEGORY_CODE_3 = "categoryCode3";
    
    /**
     * mutualProductId : 銘柄ID
     */
    public static final String MUTUAL_PRODUCT_ID = "mutualProductId";
    
}
@
