head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.29.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPTSMarketDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : PTS市場区分 定数定義インタフェイス(WEB3EquityPTSMarketDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/19 孟暁シン(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * PTS市場区分 定数定義インタフェイス
 *
 * @@author 孟暁シン(中訊) 
 * @@version 1.0
 */
public interface WEB3EquityPTSMarketDivDef
{
    /**
     * 0:PTS市場でない
     */
    public static final String PTS_MARKET_NOT= "0";
    
    /**
     * 1:PTS市場である
     */
    public static final String PTS_MARKET_YES = "1";
}@
