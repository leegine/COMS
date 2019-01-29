head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.56.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ReserveOrderExistFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 予約注文設定フラグ　@定数定義インタフェイス(WEB3ReserveOrderExistFlagDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/28 中尾寿彦(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 予約注文設定フラグ　@定数定義インタフェイス。
 *
 * @@author SRA中尾
 * @@version 1.0
 */
public interface WEB3ReserveOrderExistFlagDef
{
    /**
     * 0： 設定なし
     */
    public static final String NOT_SET = "0";
    
    /**
     * 1： 設定の可能性あり
     */
    public static final String SET_POSSIBLE = "1";
}
@
