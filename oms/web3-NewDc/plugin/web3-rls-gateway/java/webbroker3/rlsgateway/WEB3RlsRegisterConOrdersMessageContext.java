head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.26.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsRegisterConOrdersMessageContext.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �����t���������o�^�R���e�L�X�g���N���X(WEB3RlsRegisterConOrdersMessageContext.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2003/09/14 FLJ���@@�V�K�쐬
 */
package webbroker3.rlsgateway;

import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/**
 * �����t���������o�^�R���e�L�X�g���N���X
 *
 * @@author FLJ��
 * @@version 1.0
 */
public class WEB3RlsRegisterConOrdersMessageContext
    extends WEB3RlsMessageContext
{

    /**
     * �e�������i�^�C�v
     */
    private ProductTypeEnum conOrderProductType;

    /**
     * �e�������iID
     */
    private Long conOrderId;

    /**
     * �q�������i�^�C�v���X�g
     */
    private ProductTypeEnum[] subOrderProductTypes;

    /**
     * �q����ID�v���X�g
     */
    private Long[] subOrderIds;

    /**
     * �e�������i�^�C�v���擾����
     */
    public ProductTypeEnum getConOrderProductType()
    {
        return conOrderProductType;
    }

    /**
     * �e�������i�^�C�v��ݒ肷��
     */
    public void setConOrderProductType(ProductTypeEnum l_conOrderProductType)
    {
        conOrderProductType = l_conOrderProductType;
    }

    /**
     * �e�������iID���擾����
     */
    public Long getConOrderId()
    {
        return conOrderId;
    }

    /**
     * �e�������iID��ݒ肷��
     */
    public void setConOrderId(Long l_lngConOrderId)
    {
        conOrderId = l_lngConOrderId;
    }

    /**
     * �q����ID�v���X�g���擾����
     */
    public Long[] getSubOrderIds()
    {
        return subOrderIds;
    }

    /**
     * �q����ID�v���X�g��ݒ肷��
     */
    public void setSubOrderIds(Long[] l_lngSubOrderIds)
    {
        subOrderIds = l_lngSubOrderIds;
    }

    /**
     * �q�������i�^�C�v���X�g���擾����
     */
    public ProductTypeEnum[] getSubOrderProductTypes()
    {
        return subOrderProductTypes;
    }

    /**
     * �q�������i�^�C�v���X�g��ݒ肷��
     */
    public void setSubOrderProductTypes(ProductTypeEnum[] l_subOrderProductTypes)
    {
        subOrderProductTypes = l_subOrderProductTypes;
    }

    /**
     * WEB3RlsRegisterConOrdersMessageContext
     */
    public WEB3RlsRegisterConOrdersMessageContext(SubAccount l_subaccount,
                                                  int l_intOrderType,
                                                  ProductTypeEnum l_conOrderProductType,
                                                  Long l_lngConOrderId,
                                                  ProductTypeEnum[]
                                                  l_subOrderProductTypes,
                                                  Long[] l_lngSubOrderIds)
    {
        super(l_subaccount, l_intOrderType);
        conOrderProductType = l_conOrderProductType;
        conOrderId = l_lngConOrderId;
        subOrderIds = l_lngSubOrderIds;
        subOrderProductTypes = l_subOrderProductTypes;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("{[WEB3RlsRegisterConOrdersMessageContext]>>>");
        l_sb.append("subAccount=").append(this.getSubAccount().getAccountId())
            .append(";")
            .append(this.getSubAccount().getSubAccountId());
        l_sb.append(",conOrderType=").append(this.getConOrderType());
        l_sb.append(",conOrderProductType=").append(this.getConOrderProductType());
        l_sb.append(",conOrderId=").append(this.getConOrderId());
        l_sb.append(",subOrders=(");
        if (subOrderIds != null)
        {
            for (int i = 0; i < subOrderIds.length; i++)
            {
                l_sb.append(" subOrderId=").append(subOrderIds[i]);
                l_sb.append(" ,subOrderProductType=").append(subOrderProductTypes[i]);
            }
        }
        l_sb.append(")");
        l_sb.append("}");
        return l_sb.toString();
    }

}
@
