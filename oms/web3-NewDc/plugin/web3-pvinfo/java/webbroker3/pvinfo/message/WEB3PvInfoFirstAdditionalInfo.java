head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.05.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoFirstAdditionalInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : æêÇØîñ(WEB3PvInfoFirstAdditionalInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 Äog(u) VKì¬ fNo.109
*/
package webbroker3.pvinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (æêÇØ\¦îñ)<BR>
 * æêÇØ\¦îñNX<BR>
 * <BR>
 * @@author Äog
 * @@version 1.0
 */
public class WEB3PvInfoFirstAdditionalInfo extends Message
{
    /**
     * (æøâ~æª)<BR>
     * æøâ~æª<BR>
     * <BR>
     * 0FæøÂ\<BR>
     * 1Fæøâ~<BR>
     */
    public String firstTradeStopDiv;

    /**
     * (ÛØà©®UÖã»ètO)<BR>
     * ÛØà©®UÖã»ètO<BR>
     * <BR>
     * falseFÛØà©®UÖO<BR>
     * true FÛØà©®UÖã<BR>
     */
    public boolean firstAutoTransferAfterJudgeFlag;

    /**
     * (oßú)<BR>
     * oßú<BR>
     */
    public String firstDepositPassDay;

    /**
     * (Løoßú)<BR>
     * Løoßú<BR>
     */
    public String firstDepositPassDayValid;

    /**
     * (­¶ú)<BR>
     * ­¶ú<BR>
     */
    public Date firstDepositOccurredDate;

    /**
     * (ÛØà¦)<BR>
     * ÛØà¦<BR>
     */
    public String firstMarginDepositRate;

    /**
     * (ÛØàÛ¦)<BR>
     * ÛØàÛ¦<BR>
     */
    public String firstDepositRate;

    /**
     * (ÇØàz)<BR>
     * ÇØàz<BR>
     */
    public String firstDepositAmount;

    /**
     * (ÇØÏKvz)<BR>
     * ÇØÏKvz<BR>
     */
    public String firstSettlement;

    /**
     * (ÛØà¸)<BR>
     * ÛØà¸<BR>
     */
    public String firstMarginDepositInDe;

    /**
     * (ÛØà¸(©àz))<BR>
     * ÛØà¸(©àz)<BR>
     */
    public String firstMarginDepositInDeExpect;

    /**
     * (ÏÏÊ)<BR>
     * ÏÏÊ<BR>
     */
    public String firstSettledContract;

    /**
     * (¢ðÁàz)<BR>
     * ¢ðÁàz<BR>
     */
    public String firstUncancelAmt;

    /**
     * (¢ðÁÏKvz)<BR>
     * ¢ðÁÏKvz<BR>
     */
    public String firstUncancelSettleRequiredAmt;

    /**
     * (æêÇØ­¶\¦îñ)<BR>
     * RXgN^<BR>
     */
    public WEB3PvInfoFirstAdditionalInfo()
    {
        
    }
}
@
