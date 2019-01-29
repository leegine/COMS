head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.54.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondCurrencyInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 通貨情報(WEB3BondCurrencyInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 唐性峰 (中訊) 新規作成
*/

package webbroker3.bd.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (通貨情報)<BR>
 * 通貨情報<BR>
 * <BR>
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3BondCurrencyInfo extends Message
{
    /**
     * (通貨コード)<BR>
     * 通貨コード<BR>
     */
    public String currencyCode;
    
    /**
     * (為替レート)<BR>
     * 為替レート<BR>
     */
    public String fxRate;
    
    /**
     * (通貨情報)<BR>
     * コンストラクタ<BR>
     */
    public WEB3BondCurrencyInfo() 
    {
     
    }
}
@
