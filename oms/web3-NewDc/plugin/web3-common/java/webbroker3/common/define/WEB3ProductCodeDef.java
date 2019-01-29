head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.31.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ProductCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3ProductCodeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/18 li-yunfeng(sinocom)　@新規作成
Revesion History : 2007/11/26 栄イ(中訊) 共通仕様変更管理台帳・ＤＢレイアウトNo579
*/
package webbroker3.common.define;

/**
 * 商品コード　@定数定義インタフェイス
 *                                                                     
 * @@author li-yunfeng
 * @@version 1.0
 */
public interface WEB3ProductCodeDef
{
    /**
     * 0 ： DEFAULT
     */
    public static final String DEFAULT = "0";
    
    /**
     * 01 ：為替保証金（口座開設）、外国株式連携（口座開設）
     */
	public static final String ACCOUNT_OPEN = "01";
	
	/**
	 * 02 ：為替保証金（振替（出金))、外国株式連携（振替）
	 */
	public static final String TRANSFER_PAYMENT = "02";
	
	/**
	 * 03 ：為替保証金（振替（入金))
	 */
	public static final String TRANSFER_RECIEPT = "03";

    /**
     * 04 ：口座開設申込
     */
    public static final String ACCOUNT_OPEN_APPLY = "04";

}
@
