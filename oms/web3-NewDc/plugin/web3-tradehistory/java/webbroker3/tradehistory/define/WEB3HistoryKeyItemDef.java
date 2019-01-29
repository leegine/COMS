head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.45.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3HistoryKeyItemDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25  範慧琴(sinocom)　@新規作成
*/
package webbroker3.tradehistory.define;

/**
 * ソートキー.キー項目の区分
 *                                                                     
 * @@author 範慧琴
 * @@version 1.0
 */
public interface WEB3HistoryKeyItemDef
{

    /**
     * deliveryDate：受渡日
     */
    public static final String DELIVERY_DATE = "deliveryDate";

    /**
     * execDate：約定日
     */
    public static final String EXEC_DATE = "execDate";
}
@
