head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.39.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PaymentMethodDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 受渡方法@  定数定義クラス(WEB3PaymentMethodDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/03 周勇 (sinocom) 新規作成
Revesion History : 2004/09/24 彭巍 (中訊) 訂正
*/
package webbroker3.common.define;

/**
 * 受渡方法@  定数を定義する。
 *
 * @@author 周勇 (sinocom)
 * @@version 1.0
 */
public interface WEB3PaymentMethodDef
{

    /**
     * 選択指定
     */
    public final static String SELECT_APP = "0";

    /**
     * 銀行振込み
     */
    public final static String BANK_TRANSFER = "1";

    /**
     * 証券口座入力
     */
    public final static String SECURITIES_ACCOUNT_INPUT = "2";
    
    /**
     *  ６：郵便振込 
     */
    public static final String MAIL_TRANSFER = "6";

    /**
     *  ７：銀行振込（NET） 
     */
    public static final String BANK_TRANSFER_NET = "7";

    /**
     *  ９：銀行振込（普通）
     */
    public static final String BANK_TRANSFER_GENERAL = "9";

}
@
