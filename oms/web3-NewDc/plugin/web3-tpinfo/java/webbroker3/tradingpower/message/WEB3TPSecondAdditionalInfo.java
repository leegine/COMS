head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecondAdditionalInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��񐅏��Ǐ؏��(WEB3TPSecondAdditionalInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/20 �����F�i���u�j�V�K�쐬 ���f��No.312
*/
package webbroker3.tradingpower.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (��񐅏��Ǐ؏��) <BR>
 * (��񐅏��Ǐ؏��)<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3TPSecondAdditionalInfo extends Message
{
    /**
     * (����(����2))<BR>
     */
    public Date secondCloseDate2;

    /**
     * (����(����1))<BR>
     */
    public Date secondCloseDate1;

    /**
     * (����(��������))<BR>
     */
    public Date secondCloseDateExpect;

    /**
     * (������(����2))<BR>
     */
    public Date secondDepositOccurredDate2;

    /**
     * (������(����1))<BR>
     */
    public Date secondDepositOccurredDate1;

    /**
     * (������(��������))<BR>
     */
    public Date secondDepositOccurredDateExpect;

    /**
     * (�ۏ؋��ێ���)<BR>
     */
    public String secondDepositRate;

    /**
     * (�ۏ؋��߂��ێ���)<BR>
     */
    public String secondDepositBackRate;

    /**
     * (�ۏ؋���(����2))<BR>
     */
    public String secondMarginDepositRate2;

    /**
     * (�ۏ؋���(����1))<BR>
     */
    public String secondMarginDepositRate1;

    /**
     * (�ۏ؋���(��������))<BR>
     */
    public String secondMarginDepositRateExpect;

    /**
     * (�Ǐ؋��z(������))<BR>
     */
    public String secondDepositNonPay;

    /**
     * (�Ǐ؋��z(����2))<BR>
     */
    public String secondDeposit2;

    /**
     * (�Ǐ؋��z(����1))<BR>
     */
    public String secondDeposit1;

    /**
     * (�Ǐ،��ϕK�v�z(������))<BR>
     */
    public String secondSettlementNonPay;

    /**
     * (�Ǐ،��ϕK�v�z(����2))<BR>
     */
    public String secondSettlement2;

    /**
     * (�Ǐ،��ϕK�v�z(����1))<BR>
     */
    public String secondSettlement1;

    /**
     * (�ۏ؋�����)<BR>
     */
    public String secondMarginDepositInDe;

    /**
     * (�ۏ؋�����(�������z))<BR>
     */
    public String secondMarginDepositInDeExpect;

    /**
     * (���ύό���)<BR>
     */
    public String secondSettledContract;

    /**
     * (���������z(������))<BR>
     */
    public String secondUncancelAmtNonPay;

    /**
     * (���������z(����2))<BR>
     */
    public String secondUncancelAmt2;

    /**
     * (���������z(����1))<BR>
     */
    public String secondUncancelAmt1;

    /**
     * (���������z(��������))<BR>
     */
    public String secondUncancelAmtExpect;

    /**
     * (���������ϕK�v�z(������))<BR>
     */
    public String secondUncancelSettleRequiredAmtNonPay;

    /**
     * (���������ϕK�v�z(����2))<BR>
     */
    public String secondUncancelSettleRequiredAmt2;

    /**
     * (���������ϕK�v�z(����1))<BR>
     */
    public String secondUncancelSettleRequiredAmt1;

    /**
     * (���������ϕK�v�z(��������))<BR>
     */
    public String secondUncancelSettleRequiredAmtExpect;

    /**
     * @@roseuid 48EC703400C2
     */
    public WEB3TPSecondAdditionalInfo()
    {

    }
}
@
