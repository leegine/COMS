head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.56.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SellDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 解約区分/指定方法@  定数定義クラス(WEB3SellDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/03 周勇 (sinocom) 新規作成
Revesion History : 2004/08/10 孟東 (sinocom) クラス名変更
*/
package webbroker3.common.define;

/**
 * 解約区分/指定方法@ 定数を定義する。
 * 
 *
 * @@author 周勇 (sinocom)
 * @@version 1.0
 */
public interface WEB3SellDivDef
{

    /**
     * 全部指定　@
     */
    public final static String ALL_DESIGNATE = "2";

    /**
     * 金額指定
     */
    public final static String MONEY_DESIGNATE = "3";

    /**
     * 口数指定
     */
    public final static String COUNT_DESIGNATE = "4";

}
@
