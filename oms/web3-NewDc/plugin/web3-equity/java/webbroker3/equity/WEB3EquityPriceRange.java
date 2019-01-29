head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPriceRange.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 値幅(WEB3EquityPriceRange)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/13 本郷　@千草(SRA) 新規作成
                   2004/05/17 孟 東 属性を追加
                   2005/01/05 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity;

import java.util.Date;

/**
 * （値幅）。<BR>
 * <BR>
 * 値幅情報
 * @@version 1.0
 */
public class WEB3EquityPriceRange
{
   
    /**
     * (基準日時)<BR>
     */
    private Date baseDateTime;
   
    /**
     * (市場コード)<BR>
     */
    private String marketCode;
   
    /**
     * コンストラクタ
     * @@roseuid 4042EC840073
     */
    public WEB3EquityPriceRange()
    {
    
    }
    
}
@
