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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��񐅏��Ǐ؏��(WEB3PvInfoSecondAdditionalInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 �đo�g(���u) �V�K�쐬 ���f��No.109
*/
package webbroker3.pvinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (��񐅏��Ǐؕ\�����)<BR>
 * ��񐅏��Ǐؕ\�����N���X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3PvInfoSecondAdditionalInfo extends Message
{
    /**
     * (�����~�敪)<BR>
     * �����~�敪<BR>
     * <BR>
     * 0�F����\<BR>
     * 1�F�����~��<BR>
     */
    public String secondTradeStopDiv;

    /**
     * (�ۏ؋������U�֌㔻��t���O)<BR>
     * �ۏ؋������U�֌㔻��t���O<BR>
     * <BR>
     * false�F�ۏ؋������U�֑O<BR>
     * true �F�ۏ؋������U�֌�<BR>
     */
    public boolean secondAutoTransferAfterJudgeFlag;

    /**
     * (����(����2))<BR>
     * ����(����2)<BR>
     */
    public Date secondCloseDate2;

    /**
     * (����(����1))<BR>
     * ����(����1)<BR>
     */
    public Date secondCloseDate1;

    /**
     * (����(��������))<BR>
     * ����(��������)<BR>
     */
    public Date secondCloseDateExpect;

    /**
     * (������(����2))<BR>
     * ������(����2)<BR>
     */
    public Date secondDepositOccurredDate2;

    /**
     * (������(����1))<BR>
     * ������(����1)<BR>
     */
    public Date secondDepositOccurredDate1;

    /**
     * (������(��������))<BR>
     * ������(��������)<BR>
     */
    public Date secondDepositOccurredDateExpect;

    /**
     * (�ۏ؋��ێ���)<BR>
     * �ۏ؋��ێ���<BR>
     */
    public String secondDepositRate;

    /**
     * (�ۏ؋��߂��ێ���)<BR>
     * �ۏ؋��߂��ێ���<BR>
     */
    public String secondDepositBackRate;

    /**
     * (�ۏ؋���(����2))<BR>
     * �ۏ؋���(����2)<BR>
     */
    public String secondMarginDepositRate2;

    /**
     * (�ۏ؋���(����1))<BR>
     * �ۏ؋���(����1)<BR>
     */
    public String secondMarginDepositRate1;

    /**
     * (�ۏ؋���(��������))<BR>
     * �ۏ؋���(��������)<BR>
     */
    public String secondMarginDepositRateExpect;

    /**
     * (�Ǐ؋��z(������))<BR>
     * �Ǐ؋��z(������)<BR>
     */
    public String secondDepositNonPay;

    /**
     * (�Ǐ؋��z(����2))<BR>
     * �Ǐ؋��z(����2)<BR>
     */
    public String secondDeposit2;

    /**
     * (�Ǐ؋��z(����1))<BR>
     * �Ǐ؋��z(����1)<BR>
     */
    public String secondDeposit1;

    /**
     * (�Ǐ،��ϕK�v�z(������))<BR>
     * �Ǐ،��ϕK�v�z(������)<BR>
     */
    public String secondSettlementNonPay;

    /**
     * (�Ǐ،��ϕK�v�z(����2))<BR>
     * �Ǐ،��ϕK�v�z(����2)<BR>
     */
    public String secondSettlement2;

    /***
     * (�Ǐ،��ϕK�v�z(����1))<BR>
     * �Ǐ،��ϕK�v�z(����1)<BR>
     */
    public String secondSettlement1;

    /**
     * (�ۏ؋�����)<BR>
     * �ۏ؋�����<BR>
     */
    public String secondMarginDepositInDe;

    /**
     * (�ۏ؋�����(�������z))<BR>
     * �ۏ؋�����(�������z)<BR>
     */
    public String secondMarginDepositInDeExpect;

    /**
     * (���ύό���)<BR>
     * ���ύό���<BR>
     */
    public String secondSettledContract;

    /**
     * (���������z(������))<BR>
     * ���������z(������)<BR>
     */
    public String secondUncancelAmtNonPay;

    /**
     * (���������z(����2))<BR>
     * ���������z(����2)<BR>
     */
    public String secondUncancelAmt2;

    /**
     * (���������z(����1))<BR>
     * ���������z(����1)<BR>
     */
    public String secondUncancelAmt1;

    /**
     * (���������z(��������))<BR>
     * ���������z(��������)<BR>
     */
    public String secondUncancelAmtExpect;

    /**
     * (���������ϕK�v�z(������))<BR>
     * ���������ϕK�v�z(������)<BR>
     */
    public String secondUncancelSettleRequiredAmtNonPay;

    /**
     * (���������ϕK�v�z(����2))<BR>
     * ���������ϕK�v�z(����2)<BR>
     */
    public String secondUncancelSettleRequiredAmt2;

    /**
     * (���������ϕK�v�z(����1))<BR>
     * ���������ϕK�v�z(����1)<BR>
     */
    public String secondUncancelSettleRequiredAmt1;

    /**
     * (���������ϕK�v�z(��������))<BR>
     * ���������ϕK�v�z(��������)<BR>
     */
    public String secondUncancelSettleRequiredAmtExpect;

    /**
     * (��񐅏��Ǐؔ����\�����)<BR>
     * �R���X�g���N�^<BR>
     */
    public WEB3PvInfoSecondAdditionalInfo()
    {

    }
}
@
