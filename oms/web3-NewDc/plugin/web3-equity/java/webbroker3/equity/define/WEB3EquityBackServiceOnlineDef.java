head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBackServiceOnlineDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 下り処理時間の扱い　@定数定義インタフェイス (WEB3EquityBackServiceOnlineDef.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/07/02 中尾寿彦(SRA) 作成
*/
package webbroker3.equity.define;

/**
 * 下り処理時間の扱い　@定数定義インタフェイス
 * 
 * @@author 中尾寿彦
 * @@version 1.0
 */
public interface WEB3EquityBackServiceOnlineDef {

    /**
     * 0:下り処理で必ず場中扱い
     */
    public final static String ONLINE = "0";
}
@
