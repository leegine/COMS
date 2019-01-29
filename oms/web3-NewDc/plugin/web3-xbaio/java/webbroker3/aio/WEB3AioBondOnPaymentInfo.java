head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.25.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioBondOnPaymentInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o�����(WEB3AioBondOnPaymentInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/17 ���G�� (���u) �V�K�쐬
Revesion History : 2007/03/12 �����q (���u)  ���f��No.710
*/
package webbroker3.aio;

import java.util.Date;

import webbroker3.util.WEB3LogUtility;

/**
 * (���o�����)<BR>
 * ���o�����<BR>
 * <BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AioBondOnPaymentInfo
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AioBondOnPaymentInfo.class);

    /**
     * ����ID
     */
    private Long accountId;

    /**
     * ���XID
     */
    private Long branchId;

    /**
     * ��n��
     */
    private Date deliveryDate;

    /**
     * �ʉ݃R�[�h
     */
    private String currencyCode;

    /**
     * ���ϋ敪
     */
    private String settlementDiv;

    /**
     * ��n����i�~�݁j
     */
    private Double estimatedPrice;

    /**
     * ��n����i�O�݁j
     */
    private Double foreignEstimatedPrice;
   
    /**
     * (�������P��ID)<BR>
     * �������P��ID<BR>
     */
    private Long bondOrderUnitId;

    /**
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3AioBondOnPaymentInfo()
    {

    }

    /**
     * (set����ID)<BR>
     * ����ID���Z�b�g����B<BR>
     * <BR>
     * @@param l_accountId - ����ID
     */
    public void setAccountId(Long l_accountId)
    {
        final String STR_METHOD_NAME = "setAccountId(Long l_accountId)";
        log.entering(STR_METHOD_NAME);

        this.accountId = l_accountId;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���XID)<BR>
     * ���XID���Z�b�g����B<BR>
     * <BR>
     * @@param l_branchId - ���XID
     */
    public void setBranchId(Long l_branchId)
    {
        final String STR_METHOD_NAME = "setBranchId(Long l_branchId)";
        log.entering(STR_METHOD_NAME);

        this.branchId = l_branchId;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set��n��)<BR>
     * ��n�����Z�b�g����B<BR>
     * <BR>
     * @@param l_datDeliveryDate - ��n��
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "setDeliveryDate(Date l_datDeliveryDate)";
        log.entering(STR_METHOD_NAME);

        this.deliveryDate = l_datDeliveryDate;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h���Z�b�g����B<BR>
     * <BR>
     * @@param l_strCurrencyCode - �ʉ݃R�[�h
     */
    public void setCurrencyCode(String l_strCurrencyCode)
    {
        final String STR_METHOD_NAME = "setCurrencyCode(String l_strCurrencyCode)";
        log.entering(STR_METHOD_NAME);

        this.currencyCode = l_strCurrencyCode;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���ϋ敪)<BR>
     * ���ϋ敪���Z�b�g����B<BR>
     * <BR>
     * @@param l_strSettlementDiv - ���ϋ敪
     */
    public void setSettlementDiv(String l_strSettlementDiv)
    {
        final String STR_METHOD_NAME = "setSettlementDiv(String l_strSettlementDiv)";
        log.entering(STR_METHOD_NAME);

        this.settlementDiv = l_strSettlementDiv;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set��n����i�~�݁j)<BR>
     * ��n����i�~�݁j���Z�b�g����B<BR>
     * <BR>
     * @@param l_estimatedPrice - ��n����i�~�݁j
     */
    public void setEstimatedPrice(Double l_estimatedPrice)
    {
        final String STR_METHOD_NAME = "setEstimatedPrice(Double l_estimatedPrice)";
        log.entering(STR_METHOD_NAME);

        this.estimatedPrice = l_estimatedPrice;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set��n����i�O�݁j)<BR>
     * ��n����i�O�݁j���Z�b�g����B<BR>
     * <BR>
     * @@param l_foreignEstimatedPrice - ��n����i�O�݁j
     */
    public void setForeignEstimatedPrice(Double l_foreignEstimatedPrice)
    {
        final String STR_METHOD_NAME = "setForeignEstimatedPrice(Double)";
        log.entering(STR_METHOD_NAME);

        this.foreignEstimatedPrice = l_foreignEstimatedPrice;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�������P��ID)<BR>
     * �������P��ID���Z�b�g����B<BR>
     * <BR>
     * @@param l_lngBondOrderUnitIds - (�������P��ID)<BR>
     * �������P��ID
     */
    public void setBondOrderUnitId(Long l_lngBondOrderUnitIds)
    {
        final String STR_METHOD_NAME = "setBondOrderUnitId(Long l_lngBondOrderUnitIds)";
        log.entering(STR_METHOD_NAME);

        this.bondOrderUnitId = l_lngBondOrderUnitIds;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get����ID ())<BR>
     * ����ID��ԋp����B<BR>
     * <BR>
     * @@return Long
     */
    public Long getAccountId()
    {
        return this.accountId;
    }

    /**
     * (get���XID)<BR>
     * ���XID��ԋp����B<BR>
     * <BR>
     * @@return Long
     */
    public Long getBranchId()
    {
        return this.branchId;
    }

    /**
     * (get��n��)<BR>
     * ��n����ԋp����B<BR>
     * <BR>
     * @@return Date
     */
    public Date getDeliveryDate()
    {
        return this.deliveryDate;
    }

    /**
     * (get�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h��ԋp����B<BR>
     * <BR>
     * @@return String
     */
    public String getCurrencyCode()
    {
        return this.currencyCode;
    }

    /**
     * (get���ϋ敪)<BR>
     * ���ϋ敪��ԋp����B<BR>
     * <BR>
     * @@return String
     */
    public String getSettlementDiv()
    {
        return this.settlementDiv;
    }

    /**
     * (get��n����i�~�݁j)<BR>
     * ��n����i�~�݁j��ԋp����B<BR>
     * <BR>
     * @@return Double
     */
    public Double getEstimatedPrice()
    {
        return this.estimatedPrice;
    }

    /**
     * (get��n����i�O�݁j)<BR>
     * ��n����i�O�݁j��ԋp����B<BR>
     * <BR>
     * @@return Double
     */
    public Double getForeignEstimatedPrice()
    {
        return this.foreignEstimatedPrice;
    }

    /**
     * (get�������P��ID)<BR>
     * �������P��ID��ԋp����B<BR>
     * <BR>
     * @@return Long
     */
    public Long getBondOrderUnitId()
    {
        return this.bondOrderUnitId;
    }
}
@
