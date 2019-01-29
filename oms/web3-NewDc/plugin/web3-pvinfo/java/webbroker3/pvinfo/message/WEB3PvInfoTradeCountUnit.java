head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.04.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoTradeCountUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引件数情報(WEB3PvInfoTradeCountUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
                   2005/10/07 譚漢江(中訊) 外国株取引件数追加
*/
package webbroker3.pvinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (取引件数情報)<BR>
 * 取引件数情報クラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoTradeCountUnit extends Message 
{
    
    /**
     * (株式取引件数)<BR>
     * 株式取引件数<BR>
     */
    public String equityTradeCount;
    
    /**
     * (信用取引件数)<BR>
     * 信用取引件数<BR>
     */
    public String marginTradeCount;
    
    /**
     * (先物取引件数)<BR>
     * 先物取引件数<BR>
     */
    public String futuresTradeCount;
    
    /**
     * (オプション取引件数)<BR>
     * オプション取引件数<BR>
     */
    public String optionsTradeCount;

    /**
     * (外国株取引件数)<BR>
     * 外国株取引件数<BR>
     */
    public String foreignEquityTradeCount;

    /**
     * (取引件数情報)<BR>
     * コンストラクタ。<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoTradeCountUnit
     * @@roseuid 41455AFE01C3
     */
    public WEB3PvInfoTradeCountUnit() 
    {
     
    }
}
@
