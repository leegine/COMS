head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.42.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AioPaymentApplicationDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioPaymentApplicationDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/07 王蘭芬 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 注文単位テーブルの出金申込区分　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3AioPaymentApplicationDivDef 
{
    /**
     * 33：会費  
     */
    public static final String MEMBERSHIP_FEE = "33";

    /**
     * A0：バイキング
     */
	public static final String BUY_KING = "A0";
	
	/**
	 * FT：外国株式連携　@振替出金
	 */
	public static final String FEQ_TRANSFER = "FT";

    /**
     * mf：投信解約
     */
    public static final String MF = "mf";
}
@
