head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.55.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPPaymentRequisitionAccountDetailInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ڋq�ڍ׏��(WEB3TPPaymentRequisitionAccountDetailInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/15 �И��� (���u) �V�K�쐬 �d�l�ύX���f��307,315,317,340
*/
package webbroker3.tradingpower;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (���������ڋq�ڍ׏��)<BR>
 * (���������ڋq�ڍ׏��)<BR>
 * <BR>
 * ���������̌ڋq�ڍׂɊւ�������i�[����N���X<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3TPPaymentRequisitionAccountDetailInfo
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPPaymentRequisitionAccountDetailInfo.class);

    /**
     * (�ۏ؋������U�֌㔻��t���O)<BR>
     * (�ۏ؋������U�֌㔻��t���O)<BR>
     * <BR>
     * TRUE�F�@@�ۏ؋������U�֌�<BR>
     * FALSE�F�@@�ۏ؋������U�֑O<BR>
     */
    protected boolean depositAutoTransferDivFlag = false;

    /**
     * (�ڋq����)<BR>
     * (�ڋq����)<BR>
     * <BR>
     * 0�F����(�O����)<BR>
     * 1�F����(�a��،��]����)<BR>
     * 2�F�M�p<BR>
     */
    protected String accountAttribute = null;

    /**
     * (�v�Z��)<BR>
     * (�v�Z��)<BR>
     */
    protected Date calcDate = null;

    /**
     * (�s�����������)<BR>
     * (�s�����������)<BR>
     */
    protected WEB3TPShortfallOccurInfo shortfallGenerationInfo = null;

    /**
     * (�Ǐؔ������)<BR>
     * (�Ǐؔ������)<BR>
     */
    protected WEB3TPAdddepositGenerationInfo adddepositGenerationInfo = null;

    /**
     * @@roseuid 48F5856302DE
     */
    public WEB3TPPaymentRequisitionAccountDetailInfo()
    {

    }

    /**
     * (is�ۏ؋������U�֌㔻��t���O)<BR>
     * (is�ۏ؋������U�֌㔻��t���O)<BR>
     * <BR>
     * this.�ۏ؋������U�֌㔻��t���O��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 48DB61B5033C
     */
    public boolean isDepositAutoTransferDivFlag()
    {
        return this.depositAutoTransferDivFlag;
    }

    /**
     * (set�ۏ؋������U�֌㔻��t���O)<BR>
     * (set�ۏ؋������U�֌㔻��t���O)<BR>
     * <BR>
     * ����.�ۏ؋������U�֌㔻��t���O��this.�ۏ؋������U�֌㔻��t���O�ɃZ�b�g����B<BR>
     * @@param l_blnIsDepositAutoTransferDivFlag - (�ۏ؋������U�֌㔻��t���O)<BR>
     * (�ۏ؋������U�֌㔻��t���O)<BR>
     * @@roseuid 48DB61B5033D
     */
    public void setDepositAutoTransferDivFlag(boolean l_blnIsDepositAutoTransferDivFlag)
    {
        this.depositAutoTransferDivFlag = l_blnIsDepositAutoTransferDivFlag;
    }

    /**
     * (get�ڋq����)<BR>
     * (get�ڋq����)<BR>
     * <BR>
     * this.�ڋq������ԋp����B<BR>
     * @@return String
     * @@roseuid 48C6065000C7
     */
    public String getAccountAttribute()
    {
        return this.accountAttribute;
    }

    /**
     * (set�ڋq����)<BR>
     * (set�ڋq����)<BR>
     * <BR>
     * ����.�ڋq������this.�ڋq�����ɃZ�b�g����B<BR>
     * @@param l_strAccountAttribute - (�ڋq����)<BR>
     * (�ڋq����)<BR>
     * @@roseuid 48C60678025A
     */
    public void setAccountAttribute(String l_strAccountAttribute)
    {
        this.accountAttribute = l_strAccountAttribute;
    }

    /**
     * (get�v�Z��)<BR>
     * (get�v�Z��)<BR>
     * <BR>
     * this.�v�Z����ԋp����B<BR>
     * @@return Date
     * @@roseuid 48C606E20146
     */
    public Date getCalcDate()
    {
        return this.calcDate;
    }

    /**
     * (set�v�Z��)<BR>
     * (set�v�Z��)<BR>
     * <BR>
     * ����.�v�Z����this.�v�Z���ɃZ�b�g����B<BR>
     * @@param l_datCalcDate - (�v�Z��)<BR>
     * (�v�Z��)<BR>
     * @@roseuid 48C606E20147
     */
    public void setCalcDate(Date l_datCalcDate)
    {
        this.calcDate = l_datCalcDate;
    }

    /**
     * (get�s�����������)<BR>
     * (get�s�����������)<BR>
     * <BR>
     * this.�s������������ԋp����B<BR>
     * @@return WEB3TPShortfallGenerationInfo
     * @@roseuid 48C606E303E6
     */
    public WEB3TPShortfallOccurInfo getShortfallGenerationInfo()
    {
        return this.shortfallGenerationInfo;
    }

    /**
     * (set�s�����������)<BR>
     * (set�s�����������)<BR>
     * <BR>
     * ����.�s������������this.�s�����������ɃZ�b�g����B<BR>
     * @@param l_shortfallGenerationInfo - (�s�����������)<BR>
     * (�s�����������)<BR>
     * @@roseuid 48C606E303E7
     */
    public void setShortfallGenerationInfo(WEB3TPShortfallOccurInfo l_shortfallGenerationInfo)
    {
        this.shortfallGenerationInfo = l_shortfallGenerationInfo;
    }

    /**
     * (get�Ǐؔ������)<BR>
     * (get�Ǐؔ������)<BR>
     * <BR>
     * this.�Ǐؔ�������ԋp����B<BR>
     * @@return WEB3TPAdddepositGenerationInfo
     * @@roseuid 48C6091A0035
     */
    public WEB3TPAdddepositGenerationInfo getAdddepositGenerationInfo()
    {
        return this.adddepositGenerationInfo;
    }

    /**
     * (set�Ǐؔ������)<BR>
     * (set�Ǐؔ������)<BR>
     * <BR>
     * ����.�Ǐؔ�������this.�Ǐؔ������ɃZ�b�g����B<BR>
     * @@param l_adddepositGenerationInfo - (�Ǐؔ������)<BR>
     * (�Ǐؔ������)<BR>
     * @@roseuid 48C6091A0036
     */
    public void setAdddepositGenerationInfo(WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo)
    {
        this.adddepositGenerationInfo = l_adddepositGenerationInfo;
    }

    /**
     * (create���������ڋq�ڍ׏��)<BR>
     * @@param l_paymentRequisitionManagement - (���������Ǘ�)<BR>
     * (���������Ǘ�)<BR>
     * @@return WEB3TPPaymentRequisitionAccountDetailInfo
     * @@roseuid 48EC80F402EF
     * @@throws WEB3BaseException
     */
    public static WEB3TPPaymentRequisitionAccountDetailInfo createPaymentRequisitionAccountDetailInfo(
        WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createPaymentRequisitionAccountDetailInfo(WEB3TPPaymentRequisitionManagement)";
        log.entering(STR_METHOD_NAME);

        if (l_paymentRequisitionManagement == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3TPPaymentRequisitionAccountDetailInfo." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //���������ڋq�ڍ׏��𐶐�����B
        WEB3TPPaymentRequisitionAccountDetailInfo l_paymentRequisitionAccountDetailInfo =
            new WEB3TPPaymentRequisitionAccountDetailInfo();

        //(create�s�����������)
        //�s�����������𐶐�����B
        //[����]
        //�E���������Ǘ��F�@@����.���������Ǘ�
        WEB3TPShortfallOccurInfo l_shortfallGenerationInfo =
            WEB3TPShortfallOccurInfo.createShortfallGenerationInfo(l_paymentRequisitionManagement);

        //�ۏ؋������U�֌㔻��t���O = create�s�����������()�̖߂�l.�ۏ؋������U�֌㔻��t���O
        l_paymentRequisitionAccountDetailInfo.depositAutoTransferDivFlag =
            l_shortfallGenerationInfo.depositAutoTransferDivFlag;
        //�s����������� = create�s�����������()�̖߂�l
        l_paymentRequisitionAccountDetailInfo.shortfallGenerationInfo = l_shortfallGenerationInfo;
        //�ڋq���� = get�ڋq����()�̖߂�l
        l_paymentRequisitionAccountDetailInfo.accountAttribute =
            l_paymentRequisitionManagement.getAccountAttribute();
        //�v�Z�� = get�v�Z��()�̖߂�l
        l_paymentRequisitionAccountDetailInfo.calcDate = l_paymentRequisitionManagement.getCalcDate();
        //�Ǐؔ������ = create�Ǐؔ������()�̖߂�l
        l_paymentRequisitionAccountDetailInfo.adddepositGenerationInfo =
            WEB3TPAdddepositGenerationInfo.createAdddepositGenerationInfo(l_paymentRequisitionManagement);

        log.exiting(STR_METHOD_NAME);
        return l_paymentRequisitionAccountDetailInfo;
    }
}
@
