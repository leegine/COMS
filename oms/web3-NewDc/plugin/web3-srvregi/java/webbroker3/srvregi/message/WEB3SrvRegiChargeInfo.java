head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.33.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiChargeInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用期間料金情報(WEB3SrvRegiChargeInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (サービス利用期間料金情報)<BR>
 * サービス利用期間料金情報データクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3SrvRegiChargeInfo extends Message 
{
    
    /**
     * (利用期間ID)<BR>
     * 通番<BR>
     */
    public String chargeId;
    
    /**
     * (利用期間単位区分)<BR>
     * 1:年　@2:月　@3:日<BR>
     */
    public String chargeDiv;
    
    /**
     * (利用期間)
     */
    public String chargePeriod;
    
    /**
     * (利用料金)
     */
    public String chargeAmt;
    
    /**
     * (無効区分)<BR>
     * true:無効　@false:有効<BR>
     */
    public boolean invalidDiv;
    
    /**
     * (サービス利用期間料金情報)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE322B00C2
     */
    public WEB3SrvRegiChargeInfo() 
    {
     
    }
}
@
