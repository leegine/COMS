head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.27.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqTradingPowerUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 買付可能額（外貨）(WEB3FeqTradingPowerUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (買付可能額（外貨）)<BR>
 * 買付可能額（外貨）クラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqTradingPowerUnit extends Message 
{
    
    /**
     * (買付可能額)<BR>
     * 買付可能額<BR>
     */
    public String tradingPower;
    
    /**
     * (通貨コード)<BR>
     * 通貨コード<BR>
     */
    public String currencyCode;
    
    /**
     * (買付可能額（外貨）)<BR>
     * コンストラクタ<BR>
     * @@roseuid 4282BFE903B6
     */
    public WEB3FeqTradingPowerUnit() 
    {
     
    }
}
@
