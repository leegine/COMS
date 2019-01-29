head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.25.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsManualSubmitConOrderMessageContext.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �������������t�����o�^�R���e�L�X�g���N���X(WEB3RlsForceSubmitConOrdersMessageContext.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2003/09/14 FLJ���@@�V�K�쐬
 */
package webbroker3.rlsgateway;

import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/**
 * �������������t�����o�^�R���e�L�X�g���N���X
 *
 * @@author FLJ��
 * @@version 1.0
 */
public class WEB3RlsManualSubmitConOrderMessageContext
    extends WEB3RlsMessageContext
{

    /**
     *�������i�^�C�v
     */
    private ProductTypeEnum conOrderProductType;

    /**
     * �������iID
     */
    private Long conOrderId;

    /**
     * �e�������i�^�C�v
     */
    private ProductTypeEnum parentOrderProductType;

    /**
     * �e����ID�@@
     */
    private Long parentOrderId;

    /**
     * ������������
     */
    private int serialNoInParent;

    /**
     * �����҃��O�C��ID
     */
    private Long submitterLoginId;

    /**
     * �ʒm�o�H
     */
    private String submitNotifyType;

    /**
     * �������i�^�C�v���擾����
     */
    public ProductTypeEnum getConOrderProductType()
    {
        return conOrderProductType;
    }

    /**
     *�������i�^�C�v��ݒ肷��
     */
    public void setConOrderProductType(ProductTypeEnum l_conOrderProductType)
    {
        conOrderProductType = l_conOrderProductType;
    }

    /**
     * �������iID���擾����
     */
    public Long getConOrderId()
    {
        return conOrderId;
    }

    /**
     * �������iID��ݒ肷��
     */
    public void setConOrderId(Long l_lngConOrderId)
    {
        conOrderId = l_lngConOrderId;
    }

    /**
     * �e����ID���擾����
     */
    public Long getParentOrderId()
    {
        return parentOrderId;
    }

    /**
     * �e����ID��ݒ肷��
     */
    public void setParentOrderId(Long l_lngParentOrderId)
    {
        parentOrderId = l_lngParentOrderId;
    }

    /**
     * �q�������i�^�C�v���擾����
     */
    public ProductTypeEnum getParentOrderProductType()
    {
        return parentOrderProductType;
    }

    /**
     * �����������Ԃ��擾����
     */
    public int getSerialNoInParent()
    {
        return serialNoInParent;
    }

    /**
     * �����҃��O�C��ID���擾����
     */
    public Long getSubmitterLoginId()
    {
        return submitterLoginId;
    }

    /**
     * �ʒm�o�H���擾����
     */
    public String getSubmitNotifyType()
    {
        return submitNotifyType;
    }

    /**
     * �q�������i�^�C�v��ݒ肷��
     */
    public void setParentOrderProductType(ProductTypeEnum l_parentOrderProductType)
    {
        parentOrderProductType = l_parentOrderProductType;
    }

    /**
     * �ʒm�o�H��ݒ肷��
     */
    public void setSerialNoInParent(int l_intSerialNoInParent)
    {
        this.serialNoInParent = l_intSerialNoInParent;
    }

    /**
     * �����҃��O�C��ID��ݒ肷��
     */
    public void setSubmitterLoginId(Long submitterLoginId)
    {
        this.submitterLoginId = submitterLoginId;
    }

    /**
     * �����������Ԃ�ݒ肷��
     */
    public void setSubmitNotifyType(String submitNotifyType)
    {
        this.submitNotifyType = submitNotifyType;
    }

    /**
     * WEB3RlsRegisterConOrdersMessageContext
     */
    public WEB3RlsManualSubmitConOrderMessageContext(
        Long l_lngSubmitterLoginId,
        String l_strSubmitNotifyType,
        SubAccount l_subaccount,
        int l_intOrderType,
        ProductTypeEnum l_conOrderProductType,
        Long l_lngConOrderId,
        ProductTypeEnum
        l_parentOrderProductType,
        Long l_lngParentOrderId,
        int l_intSerialNoInParent)
    {
        super(l_subaccount, l_intOrderType);
        conOrderProductType = l_conOrderProductType;
        conOrderId = l_lngConOrderId;
        parentOrderId = l_lngParentOrderId;
        parentOrderProductType = l_parentOrderProductType;
        serialNoInParent = l_intSerialNoInParent;
        this.submitterLoginId = l_lngSubmitterLoginId;
        this.submitNotifyType = l_strSubmitNotifyType;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("{[WEB3RlsManualSubmitConOrderMessageContext]>>>");
        l_sb.append("subAccount=").append(this.getSubAccount().getAccountId())
            .append(";")
            .append(this.getSubAccount().getSubAccountId());
        l_sb.append(",conOrderType=").append(this.getConOrderType());
        l_sb.append(",conOrderProductType=").append(this.getConOrderProductType());
        l_sb.append(",conOrderId=").append(this.getConOrderId());
        l_sb.append(",parentOrderProductType=").append(this.getParentOrderProductType());
        l_sb.append(",parentOrderId=").append(this.getParentOrderId());
        l_sb.append(",serialNoInParent=").append(this.getSerialNoInParent());
        l_sb.append(",submitterLoginId=").append(this.getSubmitterLoginId());
        l_sb.append(",submitNotifyType=").append(this.getSubmitNotifyType());
        l_sb.append("}");
        return l_sb.toString();
    }

}
@
