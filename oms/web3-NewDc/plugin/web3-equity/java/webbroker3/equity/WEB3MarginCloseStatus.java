head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseStatus.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引決済状態クラス 建株の決済状態を表現する。(WEB3MarginCloseStatus.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/16 李松峰 (中訊) 新規作成
*/
package webbroker3.equity;


/**
 * （信用取引決済状態）。<BR>
 * <BR>
 * 信用取引決済状態クラス
 * @@version 1.0
 */
public class WEB3MarginCloseStatus 
{
    
    /**
     * (決済済フラグ)<BR>
     * 建株が当日決済済の状態を保持しているならばtrue。以外、false。
     */
    public boolean closedMarginFlag;
    
    /**
     * (未決済フラグ)<BR>
     * 建株が未決済の状態を保持しているならばtrue。以外、false。
     */
    public boolean closeMarginFlag;
    
    /**
     * (決済中フラグ)<BR>
     * 建株が決済中の状態を保持しているならばtrue。以外、false。
     */
    public boolean closingMarginFlag;
    
    /**
     * (信用取引決済状態)<BR>
     * コンストラクタ。
     * @@roseuid 40FDCAE50189
     */
    public WEB3MarginCloseStatus() 
    {
     
    }
}
@
