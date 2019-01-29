head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.06.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInterestDealingInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 興味のある取引情報(WEB3AccOpenInterestDealingInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 鄭海良(中訊) 新規作成
*/

package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (興味のある取引情報)<BR>
 * 興味のある取引情報<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AccOpenInterestDealingInfo extends Message 
{
    
    /**
     * (現物株式フラグ)<BR>
     * 現物株式フラグ<BR>
     * <BR>
     * true：　@興味あり<BR>
     * false：　@興味なし<BR>
     */
    public boolean interestEquityFlag;
    
    /**
     * (株式ミニ投資フラグ)<BR>
     * 株式ミニ投資フラグ<BR>
     * <BR>
     * true：　@興味あり<BR>
     * false：　@興味なし<BR>
     */
    public boolean interestMstkFlag;
    
    /**
     * (信用取引フラグ)<BR>
     * 信用取引フラグ<BR>
     * <BR>
     * true：　@興味あり<BR>
     * false：　@興味なし<BR>
     */
    public boolean interestMarginFlag;
    
    /**
     * (債券フラグ)<BR>
     * 債券フラグ<BR>
     * <BR>
     * true：　@興味あり<BR>
     * false：　@興味なし<BR>
     */
    public boolean interestBondFlag;
    
    /**
     * (投資信託フラグ)<BR>
     * 投資信託フラグ<BR>
     * <BR>
     * true：　@興味あり<BR>
     * false：　@興味なし<BR>
     */
    public boolean interestFundFlag;
    
    /**
     * (先物・オプションフラグ)<BR>
     * 先物・オプションフラグ<BR>
     * <BR>
     * true：　@興味あり<BR>
     * false：　@興味なし<BR>
     */
    public boolean interestFoFlag;
    
    /**
     * (外国証券フラグ)<BR>
     * 外国証券フラグ<BR>
     * <BR>
     * true：　@興味あり<BR>
     * false：　@興味なし<BR>
     */
    public boolean interestFEquityFlag;
    
    /**
     * (その他フラグ)<BR>
     * その他フラグ<BR>
     * <BR>
     * true：　@興味あり<BR>
     * false：　@興味なし<BR>
     */
    public boolean interestEtcFlag;
    
    /**
     * @@roseuid 41B45E7A007D
     */
    public WEB3AccOpenInterestDealingInfo() 
    {
     
    }
}
@
