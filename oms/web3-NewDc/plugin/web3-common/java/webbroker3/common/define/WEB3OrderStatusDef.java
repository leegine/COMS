head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.40.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3OrderStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/24 ma-zt(sinocom)　@新規作成
Revesion History : 2006/06/07 凌建平(中訊) インターフェンス申請・No082
Revesion History : 2006/06/07 栄イ(中訊) 先物OP申請
Revesion History : 2006/11/08 栄イ(中訊) ﾒｯｾｰｼﾞ定義書_株価指数先物(約定照会)
*/
package webbroker3.common.define;

/**
 * 注文状態　@定数定義インタフェイス
 *                                                                     
 * @@author ma-zt
 * @@version 1.0
 */
public interface WEB3OrderStatusDef
{
    /**
     *  0：その他 
     */
    public static final String OTHER = "0";
    /**
     * 1：受付済（新規注文）
     */
    public static final String ACCEPTED_OPENORDER= "1";
    /**
     *  2：発注中（新規注文） 
     */
    public static final String MODIFYING = "2";
    /**
     * 3：発注済（新規注文）
     */
    public static final String MODIFYED = "3";
    /**
    * 6：発注失敗（新規注文）
    */
    public static final String  MODIFY_FAIL_OPENORDER = "6";
    /**
     *  7：受付済（変更注文） 
     */
    public static final String ACCEPTED_CHANGEORDER = "7";
    /**
     * 8：発注中（変更注文） 
     */
    public static final String MODIFYING_CHANGEORDER = "8";
    /**
     * 10：発注済（変更注文）
     */
    public static final String MODIFYED_CHANGEORDER = "10";
    /**
     *  11：発注失敗（変更注文）
     */
    public static final String MODIFY_FAIL_CHANGEORDER = "11";
    /**
     *  12：受付済（取消注文） 
     */
    public static final String  ACCEPTED_CANCELORDER = "12";
    /**
     * 13：発注中（取消注文）
     */
    public static final String MODIFYING_CANCELORDER = "13";
    /**
     *  14：発注済（取消注文） 
     */
    public static final String MODIFYED_CANCELORDER = "14";
    /**
     * 15：発注失敗（取消注文） 
     */
    public static final String MODIFY_FAIL_CANCELORDER = "15";
    /**
     *20：一部失効 
     */
    public static final String PART_INAFFECTED = "20";
    /**
     *21：全部失効 
     */
    public static final String FULL_INAFFECTED = "21";
    /**
     *22：無効
     */
    public static final String CLOSED = "22";

    /**
     * 23：手動失効
     */
    public static final String MANUAL_EXPIRED = "23";

    /**
     * 24：切替注文
     */
    public static final String TRANSFER_ORDER = "24";

    /**
     * 25：切替受付
     */
    public static final String TRANSFER_ACCEPT = "25";

    /**
     * 26：切替完了
     */
    public static final String TRANSFERRED = "26";

    /**
     * 27：切替注文(失敗)
     */
    public static final String TRANSFER_ORDER_FAIL = "27";

    /**
     * 50 : 繰越済
     */
    public static final String TRANSFERED = "50";

    /**
     * 51 : 繰越失敗
     */
    public static final String NOT_TRANSFERED = "51";

}
@
