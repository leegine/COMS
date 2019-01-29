head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityTickValue.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 呼値(WEB3EquityTickValue)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/13 本郷　@千草(SRA) 新規作成
Revesion History : 2004/05/17 孟 東 属性を追加
Revesion History : 2004/01/05 岡村　@和明　@JavaDoc修正
*/
package webbroker3.equity;

import java.util.Date;

/**
 * （呼値）。<BR>
 * <BR>
 * 呼値情報
 * @@version 1.0
 */
public class WEB3EquityTickValue
{

    /**
     * （基準日時）。<BR>
     * <BR>
     * 基準日時
     */
    private Date baseDateTime;
   
    /**
     * （市場コード）。<BR>
     * <BR>
     * 市場コード
     */
    private String marketCode;
   
    /**
     * （呼値）。<BR>
     * <BR>
     * コンストラクタ
     */
    public WEB3EquityTickValue()
    {
    
    }
}
@
