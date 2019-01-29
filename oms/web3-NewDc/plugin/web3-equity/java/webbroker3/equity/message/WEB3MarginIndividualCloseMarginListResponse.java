head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginIndividualCloseMarginListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����ʌ��ψꗗ���X�|���X(WEB3MarginIndividualCloseMarginListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ������ (���u) �V�K�쐬
Revesion History : 2004/12/13 �K�� (SRA) �C��
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�M�p����ʌ��ψꗗ���X�|���X�j�B<br>
 * <br>
 * �M�p����ʌ��ψꗗ���X�|���X�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginIndividualCloseMarginListResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_individualCloseMarginList";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;    
    
    /**
     * (�����R�[�h)<BR>
     */
    public String productCode;
    
    /**
     * (������)
     */
    public String productName;
    
    /**
     * (�s��R�[�h)
     */
    public String marketCode;
    
    /**
     * (�����敪)<BR>
     * 0�F��ʁ@@1�F����
     */
    public String taxType;
    
    /**
     * (���敪)<BR>
     * 1�F�����@@2�F����<BR>
     * �iContractTypeEnum�ɂĒ�`�j<BR>
     */
    public String contractType;
    
    /**
     * (�ٍ�)<BR>
     * �M�p����ٍ�
     */
    public WEB3MarginRepaymentUnit repayment;
    
    /**
     * (�����\�z)
     */
    public String swapLongTradingPower;
    
    /**
     * (���ݒl)<BR>
     *  ���ݒl
     */
    public String currentPrice;
    
    /**
     * (�������׈ꗗ)<BR>
     * �M�p����������ׂ̈ꗗ
     */
    public WEB3MarginContractUnit[] contractUnits;
    
    /**
     * (����I���x��������\������s��R�[�h�̈ꗗ)<BR>
     */
    public String[] messageSuspension;
    
    /**
     * @@roseuid 4140474A00A1
     */
    public WEB3MarginIndividualCloseMarginListResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginIndividualCloseMarginListResponse(WEB3MarginIndividualCloseMarginListRequest l_request)
    {
        super(l_request);
    }
}
@
