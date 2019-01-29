head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.03.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ReturnMethodDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 返還方法@  定数定義クラス(WEB3ReturnMethodDef )
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/03 周勇 (sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 返還方法@  定数を定義する。
 *
 * @@author 周勇 (sinocom)
 * @@version 1.0
 */
public interface WEB3ReturnMethodDef
{

    /**
     * 当日解約　@
     */
    public final static String DAY_SELL = "1";

    /**
     * キャッシング（野村MRFの時）
     */
    public final static String CASHING = "3";


}
@
