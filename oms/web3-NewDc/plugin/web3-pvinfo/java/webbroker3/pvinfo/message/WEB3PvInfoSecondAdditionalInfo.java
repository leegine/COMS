head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.06.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoSecondAdditionalInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : æñÇØîñ(WEB3PvInfoSecondAdditionalInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 Äog(u) VKì¬ fNo.109
*/
package webbroker3.pvinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (æñÇØ\¦îñ)<BR>
 * æñÇØ\¦îñNX<BR>
 * <BR>
 * @@author Äog
 * @@version 1.0
 */
public class WEB3PvInfoSecondAdditionalInfo extends Message
{
    /**
     * (æøâ~æª)<BR>
     * æøâ~æª<BR>
     * <BR>
     * 0FæøÂ\<BR>
     * 1Fæøâ~<BR>
     */
    public String secondTradeStopDiv;

    /**
     * (ÛØà©®UÖã»ètO)<BR>
     * ÛØà©®UÖã»ètO<BR>
     * <BR>
     * falseFÛØà©®UÖO<BR>
     * true FÛØà©®UÖã<BR>
     */
    public boolean secondAutoTransferAfterJudgeFlag;

    /**
     * (úú(¿2))<BR>
     * úú(¿2)<BR>
     */
    public Date secondCloseDate2;

    /**
     * (úú(¿1))<BR>
     * úú(¿1)<BR>
     */
    public Date secondCloseDate1;

    /**
     * (úú(¿©))<BR>
     * úú(¿©)<BR>
     */
    public Date secondCloseDateExpect;

    /**
     * (­¶ú(¿2))<BR>
     * ­¶ú(¿2)<BR>
     */
    public Date secondDepositOccurredDate2;

    /**
     * (­¶ú(¿1))<BR>
     * ­¶ú(¿1)<BR>
     */
    public Date secondDepositOccurredDate1;

    /**
     * (­¶ú(¿©))<BR>
     * ­¶ú(¿©)<BR>
     */
    public Date secondDepositOccurredDateExpect;

    /**
     * (ÛØàÛ¦)<BR>
     * ÛØàÛ¦<BR>
     */
    public String secondDepositRate;

    /**
     * (ÛØàßµÛ¦)<BR>
     * ÛØàßµÛ¦<BR>
     */
    public String secondDepositBackRate;

    /**
     * (ÛØà¦(¿2))<BR>
     * ÛØà¦(¿2)<BR>
     */
    public String secondMarginDepositRate2;

    /**
     * (ÛØà¦(¿1))<BR>
     * ÛØà¦(¿1)<BR>
     */
    public String secondMarginDepositRate1;

    /**
     * (ÛØà¦(¿©))<BR>
     * ÛØà¦(¿©)<BR>
     */
    public String secondMarginDepositRateExpect;

    /**
     * (ÇØàz(¢üà))<BR>
     * ÇØàz(¢üà)<BR>
     */
    public String secondDepositNonPay;

    /**
     * (ÇØàz(¿2))<BR>
     * ÇØàz(¿2)<BR>
     */
    public String secondDeposit2;

    /**
     * (ÇØàz(¿1))<BR>
     * ÇØàz(¿1)<BR>
     */
    public String secondDeposit1;

    /**
     * (ÇØÏKvz(¢üà))<BR>
     * ÇØÏKvz(¢üà)<BR>
     */
    public String secondSettlementNonPay;

    /**
     * (ÇØÏKvz(¿2))<BR>
     * ÇØÏKvz(¿2)<BR>
     */
    public String secondSettlement2;

    /***
     * (ÇØÏKvz(¿1))<BR>
     * ÇØÏKvz(¿1)<BR>
     */
    public String secondSettlement1;

    /**
     * (ÛØà¸)<BR>
     * ÛØà¸<BR>
     */
    public String secondMarginDepositInDe;

    /**
     * (ÛØà¸(©àz))<BR>
     * ÛØà¸(©àz)<BR>
     */
    public String secondMarginDepositInDeExpect;

    /**
     * (ÏÏÊ)<BR>
     * ÏÏÊ<BR>
     */
    public String secondSettledContract;

    /**
     * (¢ðÁàz(¢üà))<BR>
     * ¢ðÁàz(¢üà)<BR>
     */
    public String secondUncancelAmtNonPay;

    /**
     * (¢ðÁàz(¿2))<BR>
     * ¢ðÁàz(¿2)<BR>
     */
    public String secondUncancelAmt2;

    /**
     * (¢ðÁàz(¿1))<BR>
     * ¢ðÁàz(¿1)<BR>
     */
    public String secondUncancelAmt1;

    /**
     * (¢ðÁàz(¿©))<BR>
     * ¢ðÁàz(¿©)<BR>
     */
    public String secondUncancelAmtExpect;

    /**
     * (¢ðÁÏKvz(¢üà))<BR>
     * ¢ðÁÏKvz(¢üà)<BR>
     */
    public String secondUncancelSettleRequiredAmtNonPay;

    /**
     * (¢ðÁÏKvz(¿2))<BR>
     * ¢ðÁÏKvz(¿2)<BR>
     */
    public String secondUncancelSettleRequiredAmt2;

    /**
     * (¢ðÁÏKvz(¿1))<BR>
     * ¢ðÁÏKvz(¿1)<BR>
     */
    public String secondUncancelSettleRequiredAmt1;

    /**
     * (¢ðÁÏKvz(¿©))<BR>
     * ¢ðÁÏKvz(¿©)<BR>
     */
    public String secondUncancelSettleRequiredAmtExpect;

    /**
     * (æñÇØ­¶\¦îñ)<BR>
     * RXgN^<BR>
     */
    public WEB3PvInfoSecondAdditionalInfo()
    {

    }
}
@
