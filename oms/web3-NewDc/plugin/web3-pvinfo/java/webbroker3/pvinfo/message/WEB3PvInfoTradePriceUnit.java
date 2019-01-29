head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.07.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoTradePriceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 売買代金情報(WEB3PvInfoTradePriceUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/03/03 王暁傑(中訊) 新規作成
*/
package webbroker3.pvinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (売買代金情報)<BR>
 * 売買代金情報クラス<BR>
 * @@author 王暁傑
 * @@version 1.00
 */
public class WEB3PvInfoTradePriceUnit extends Message 
{
    
    /**
     * (株式売買代金)<BR>
     * 株式売買代金<BR>
     */
    public String equityTradePrice;
    
    /**
     * (信用取引売買代金)<BR>
     * 信用取引売買代金<BR>
     */
    public String marginTradePrice;
    
    /**
     * (先物取引売買代金)<BR>
     * 先物取引売買代金<BR>
     */
    public String futuresTradePrice;
    
    /**
     * (オプション取引売買代金)<BR>
     * オプション取引売買代金<BR>
     */
    public String optionsTradePrice;
    
    /**
     * (売買代金情報)<BR>
     * コンストラクタ<BR>
     */
    public WEB3PvInfoTradePriceUnit()
    {
        
    }
}
@
