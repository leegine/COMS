head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeEquityMarginDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 税区分タイプ定義インタフェイス(WEB3GentradeEquityMarginDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/31 鄒政 (中訊) 新規作成
*/
package webbroker3.gentrade.define;

/**
 * 税区分タイプ定義インタフェイス
 *
 * @@author 鄒政
 * @@version 1.0
 */
public interface WEB3GentradeEquityMarginDivDef
{
    /**
      * 現物株式：１<BR>
      */
    public static final String EQUITY = "1";
    
    /**
      * 信用取引：２<BR>
      */
    public static final String MARGIN = "2";

}
@
