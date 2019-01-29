head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.25.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsConOrderExecuteMessageContext.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �A���������ʒm�R���e�L�X�g���N���X(WEB3RlsConOrderExecuteMessageContext.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2003/09/14 FLJ���@@�V�K�쐬
 */
package webbroker3.rlsgateway;

import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/**
 * �A���������ʒm�R���e�L�X�g���N���X
 *
 * @@author FLJ��
 * @@version 1.0
 */
public class WEB3RlsConOrderExecuteMessageContext
    extends WEB3RlsMessageContext
{

    /**
     * ���i�^�C�v
     */
    private ProductTypeEnum conOrderProductType;

    /**
     * ����ID
     */
    private Long conOrderId;

    /**
     * ���ʃR�[�h
     */
    private String requestNumber;

    /**
     * ���i�^�C�v���擾����
     */
    public ProductTypeEnum getConOrderProductType()
    {
        return conOrderProductType;
    }

    /**
     * ���i�^�C�v��ݒ肷��
     */
    public void setConOrderProductType(ProductTypeEnum l_conOrderProductType)
    {
        conOrderProductType = l_conOrderProductType;
    }

    /**
     * ����ID���擾����
     */
    public Long getConOrderId()
    {
        return conOrderId;
    }

    /**
     * ����ID��ݒ肷��
     */
    public void setConOrderId(Long l_lngConOrderId)
    {
        conOrderId = l_lngConOrderId;
    }

    /**
     * ���ʃR�[�h���擾����
     */
    public String getRequestNumber()
    {
        return requestNumber;
    }

    /**
     * ���ʃR�[�h��ݒ肷��
     */
    public void setRequestNumber(String l_strRequestNumber)
    {
        requestNumber = l_strRequestNumber;
    }

    /**
     * WEB3RlsConOrderExecuteMessageContext
     */
    public WEB3RlsConOrderExecuteMessageContext(SubAccount l_subaccount,
                                                int l_intOrderType,
                                                ProductTypeEnum l_conOrderProductType,
                                                Long l_lngConOrderId,
                                                String l_strRequestNumber)
    {
        super(l_subaccount, l_intOrderType);
        conOrderProductType = l_conOrderProductType;
        conOrderId = l_lngConOrderId;
        requestNumber = l_strRequestNumber;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("{[WEB3RlsConOrderExecuteMessageContext]>>>");
        l_sb.append("subAccount=").append(this.getSubAccount().getAccountId())
            .append(";")
            .append(this.getSubAccount().getSubAccountId());
        l_sb.append(",conOrderType=").append(this.getConOrderType());
        l_sb.append(",conOrderProductType=").append(this.getConOrderProductType());
        l_sb.append(",conOrderId=").append(this.getConOrderId());
        l_sb.append(",requestNumber=").append(this.getRequestNumber());
        l_sb.append("}");
        return l_sb.toString();
    }

}
@
