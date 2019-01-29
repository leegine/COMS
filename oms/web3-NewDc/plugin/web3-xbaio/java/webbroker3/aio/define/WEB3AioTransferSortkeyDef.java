head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.49.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioTransferSortkeyDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioTransferSortkeyDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 王蘭芬 (中訊) 新規作成
*/
package webbroker3.aio.define;

/**
 * 証券振替ソートキーのキー項目　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3AioTransferSortkeyDef {
    
    /**
     * instrumentsType： 商品タイプ  
     */
    public static final String INSTRUMENTS_TYPE = "instrumentsType";
     
    /**
     * productCode： 銘柄コード  
     */
    public static final String PRODUCT_CODE = "productCode";

    /**
     * taxType： 口座区分  
     */
    public static final String TAX_TYPE = "taxType";

    /**
     * changePossQuantity： 数量  
     */
    public static final String CHANGE_POSS_QUANTITY = "changePossQuantity";

    /**
     * marketValue： 評価額  
     */
    public static final String MARKET_VALUE = "marketValue";
 
    /**
     * depositDiv： 預り区分  
     */
    public static final String DEPOSIT_DIV = "depositDiv";
     
}
@
