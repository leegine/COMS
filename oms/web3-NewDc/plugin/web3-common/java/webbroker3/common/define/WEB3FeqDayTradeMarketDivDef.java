head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.52.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FeqDayTradeMarketDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株日計り市場区分定数定義インタフェイス(WEB3FeqDayTradeMarketDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2010/01/12 趙林鵬(中訊) 新規作成 ＤＢレイアウト703
*/

package webbroker3.common.define;

/**
 * 外株日計り市場区分定数定義インタフェイス<BR>
 * (市場用プリファ@レンステーブルのプリファ@レンスの値の參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3FeqDayTradeMarketDivDef
{
    /**
     * 0:外株日計り市場でない
     */
    public final static String DEFAULT = "0";

    /**
     * 1:外株日計り市場である
     */
    public final static String EXECUTE = "1";
}
@
