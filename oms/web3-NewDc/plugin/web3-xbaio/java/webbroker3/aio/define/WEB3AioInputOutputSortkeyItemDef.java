head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.46.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioInputOutputSortkeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出庫履歴キー項目(WEB3AioInputOutputSortkeyItemDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18  王暁傑 (中訊) 新規作成
*/
package webbroker3.aio.define;

/**
 * 入出庫履歴キー項目
 *                                                                     
 * @@author wang-xiaojie
 * @@version 1.0
 */
public interface WEB3AioInputOutputSortkeyItemDef
{

    /**
     * 受渡日
     */
    public static final String DELIVERY_DATE = "deliveryDate";
    
    /**
     * 商品グループ
     */
    public static final String PRODUCT_GROUP = "productGroup";
    
    /**
     * 銘柄コード
     */
    public static final String PRODUCT_CODE = "productCode";
}
@
