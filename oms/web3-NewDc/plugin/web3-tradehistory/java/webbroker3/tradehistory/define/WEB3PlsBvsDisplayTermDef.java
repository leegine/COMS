head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.44.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PlsBvsDisplayTermDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 表示期間(WEB3PlsBvsDisplayTermDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/05 賈元春(sinocom) 新規作成
*/
package webbroker3.tradehistory.define;

/**
 * 表示期間 定数定義インタフェイス
 * @@author 賈元春
 * @@version 1.0
 */
public interface WEB3PlsBvsDisplayTermDef
{

    /**
     * 0: 前月月初以降(DEFAULT)
     */
    public final static String DEFAULT = "0";

    /**
     * 1: 1ヶ月分
     */
    public final static String ONE_MONTH = "1";

    /**
     * 2: 1週間分
     */
    public final static String ONE_WEEK = "2";

    /**
     * 3: 前日1日分
     */
    public final static String THE_PREVIOUS_DAY = "3";

}
@
