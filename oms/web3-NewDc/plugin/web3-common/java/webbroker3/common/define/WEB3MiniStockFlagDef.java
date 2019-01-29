head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.03.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MiniStockFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3MiniStockFlagDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * ミニ株フラグ　@定数定義インタフェイス
 *                                                                     
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3MiniStockFlagDef
{
    /**
     * 0 : DEFAULT
     */
    public static final String DEFAULT = "0";

    /**
     * 1 : 売買停止
     */
    public static final String TRADESTOP = "1";

    /**
     * 2 : 売停止
     */
    public static final String SELLSTOP = "2";

    /**
     * 3 : 買停止
     */
    public static final String BUYSTOP = "3";

}
@
