head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OrderStatusDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3OrderStatusDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/20 鈴木美由紀(SRA)　@新規作成
*/
package webbroker3.xbruito.define;

/**
 * 注文状態区分　@定数定義インタフェイス
 *                                                                     
 * @@author 鈴木美由紀
 * @@version 1.0
 */
public interface WEB3OrderStatusDivDef
{
    /**
     * 1 : 受付済（新規注文）
     */
    public static final String ORDERING = "1";

    /**
     * 6 : 発注失敗（新規注文）
     */
    public static final String ORDER_ERROR = "6";

    /**
     * 12 : 受付済（取消注文）
     */
    public static final String ORDER_CANCELING = "12";
    
    /**
     * 14 : 発注済（取消注文）
     */
    public static final String ORDER_CANCELED = "14";

    /**
     * 15 : 発注失敗（取消注文） 
     */
    public static final String ORDER_CANCEL_FAILED = "15";
    
    /**
     * 30 : 受付済（MRF解約あり）
     */
    public static final String ORDERING_MRF_SELL = "30";
    
    /**
     * 31 : 発注済（MRF解約あり）
     */
    public static final String ORDER_MRF_SELL = "31";
    
    /**
     * 32 : 発注失敗（ＭＲＦ解約取消注文）
     */
    public static final String CANCEL_MRF_SELL_FAILED = "32";
    
	/**
	 * 56 : 取消不可    
	 */
	public static final String CANCEL_DISABLE = "56";
}
@
