head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.44.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TradeHistoryBondDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券 (WEB3TradeHistoryBondDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/19  張騰宇(中訊) 新規作成
*/
package webbroker3.tradehistory.define;

/**
 * 債券 定数定義インタフェイス
 * 
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3TradeHistoryBondDef
{

    /**
     *30:国内債券
     */
    public final static String DOMESTIC_BOND = "30";
    
    /**
     *60:外国債券
     */
    public final static String FOREIGN_BOND = "60";
    
}@
