head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.42.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPSecondAdditionalInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��񐅏��Ǐ؏��(WEB3AdminTPSecondAdditionalInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 �И��� (���u) �V�K�쐬 ���f��No.027
*/
package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (��񐅏��Ǐ؏��)<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminTPSecondAdditionalInfo extends Message
{

    /**
     * (����(����2))<BR>
     */
    public Date closeDate2 = null;

    /**
     * (����(����1))<BR>
     */
    public Date closeDate1 = null;

    /**
     * (����(��������))<BR>
     */
    public Date closeDateExpect = null;

    /**
     * (������(����2))<BR>
     */
    public Date secondDepositOccurredDate2 = null;

    /**
     * (������(����1))<BR>
     */
    public Date secondDepositOccurredDate1 = null;

    /**
     * (������(��������))<BR>
     */
    public Date secondDepositOccurredDateExpect = null;

    /**
     * (�ۏ؋��ێ���)<BR>
     */
    public String secondDepositRate = "0";

    /**
     * (�ۏ؋��߂��ێ���)<BR>
     */
    public String secondDepositBackRate = "0";

    /**
     * (�ۏ؋���(����2))<BR>
     */
    public String secondMarginDepositRate2 = "0";

    /**
     * (�ۏ؋���(����1))<BR>
     */
    public String secondMarginDepositRate1 = "0";

    /**
     * (�ۏ؋���(��������))<BR>
     */
    public String secondMarginDepositRateExpect = "0";

    /**
     * (�Ǐ؋��z(������))<BR>
     */
    public String secondDepositNonPay = "0";

    /**
     * (�Ǐ؋��z(����2))<BR>
     */
    public String secondDeposit2 = "0";

    /**
     * (�Ǐ؋��z(����1))<BR>
     */
    public String secondDeposit1 = "0";

    /**
     * (�Ǐ،��ϕK�v�z(������))<BR>
     */
    public String secondSettlementNonPay = "0";

    /**
     * (�Ǐ،��ϕK�v�z(����2))<BR>
     */
    public String secondSettlement2 = "0";

    /**
     * (�Ǐ،��ϕK�v�z(����1))<BR>
     */
    public String secondSettlement1 = "0";

    /**
     * (�ۏ؋�����)<BR>
     */
    public String secondMarginDepositInDe = "0";

    /**
     * (�ۏ؋�����(�������z))<BR>
     */
    public String secondMarginDepositInDeExpect = "0";

    /**
     * (���ύό���)<BR>
     */
    public String secondSettledContract = "0";

    /**
     * (���������z(������))<BR>
     */
    public String secondUncancelAmtNonPay = "0";

    /**
     * (���������z(����2))<BR>
     */
    public String secondUncancelAmt2 = "0";

    /**
     * (���������z(����1))<BR>
     */
    public String secondUncancelAmt1 = "0";

    /**
     * (���������z(��������))<BR>
     */
    public String secondUncancelAmtExpect = "0";

    /**
     * (���������ϕK�v�z(������))<BR>
     */
    public String secondUncancelSettleRequiredAmtNonPay = "0";

    /**
     * (���������ϕK�v�z(����2))<BR>
     */
    public String secondUncancelSettleRequiredAmt2 = "0";

    /**
     * (���������ϕK�v�z(����1))<BR>
     */
    public String secondUncancelSettleRequiredAmt1 = "0";

    /**
     * (���������ϕK�v�z(��������))<BR>
     */
    public String secondUncancelSettleRequiredAmtExpect = "0";

    /**
     * @@roseuid 48EC70330383
     */
    public WEB3AdminTPSecondAdditionalInfo()
    {

    }
}
@
