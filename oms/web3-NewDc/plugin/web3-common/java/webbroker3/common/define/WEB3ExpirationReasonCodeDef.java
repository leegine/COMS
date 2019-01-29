head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.27.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ExpirationReasonCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3ExpirationReasonCodeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
Revesion History : 2006/06/14 凌建平(中訊) インターフェンス申請・No083
*/
package webbroker3.common.define;

/**
 * 失効理由コード　@定数定義インタフェイス
 *                                                                     
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3ExpirationReasonCodeDef
{
    /**
     * 1 : :注文した数量がすべて約定不成立の場合
     */
    public static final String FAILED_EXECUTE = "1";

    /**
     * 2 : 注文した数量の一部が約定成立し残数量が失効した場合
     */
    public static final String PARTIALLY_EXECUTE_CLOSE = "2";

    /**
     * 3 : 引け執行条件付注文がザラバ引け失効した場合
     */
    public static final String LAST_QUOTATION_CLOSE = "3";

    /**
     * 4 : 制限値幅オーバー
     */
    public static final String PRICE_RANGE_EXCESS = "4";

    /**
     * 8 : 母店による失効
     */
    public static final String EXPIRED_BY_AGENT = "8";

    /**
     * 9 : 原注文破棄
     */
    public static final String ORDER_CANCEL = "9";
}
@
