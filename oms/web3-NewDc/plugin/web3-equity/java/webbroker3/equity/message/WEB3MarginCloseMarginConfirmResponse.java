head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p����ԍϒ����m�F���X�|���X�N���X(WEB3MarginCloseMarginConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/16 ������ (���u) �V�K�쐬
Revesion History : 2004/12/13 �K�� (SRA) �C��
Revesion History : 2007/06/13 ���g (���u) ���f��No.1167
*/
package webbroker3.equity.message;

import java.util.Date;

/**
 * �i�M�p����ԍϒ����m�F���X�|���X�j�B<br>
 * <br>
 * �M�p����ԍϒ����m�F���X�|���X�N���X
 * @@version 1.0
 */
public class WEB3MarginCloseMarginConfirmResponse extends WEB3MarginConfirmCommonResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_closeMarginConfirm";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101645L;        
    /**
     * (�������׈ꗗ)<BR>
     */
    public WEB3MarginContractUnit[] contractUnits;
    
    /**
     * (�M�p����萔�����)<BR>
     */
    public WEB3MarginCommissionInfoUnit commissionInfo;
    
    /**
     * (�m�F���P��)<BR>
     * <BR>
     * �������N�G�X�g�ő��M����l�B<BR>
     */
    public String checkPrice;
    
    /**
     * (����ID)<BR>
     */
    public String orderId;
    
	/**
	 * (�C���T�C�_�[�x���\���t���O)<BR>
	 * true�F�x���\���v�@@�@@�@@false�F�x���\���s�v
	 */
	public boolean insiderWarningFlag; 
    
    /**
     * (�ԍώ����ӕ���)<BR>
     * <BR>
     * �ԍώ����ӕ���<BR>
     */
    public WEB3MarginCloseMarginAttentionUnit closeMarginAttention; 

    /**
     * (�����L������)<BR>
     * �����L������
     */
    public Date expirationDate;

    /**
     * @@roseuid 414047D9002F
     */
    public WEB3MarginCloseMarginConfirmResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginCloseMarginConfirmResponse(WEB3MarginCloseMarginConfirmRequest l_request)
    {
        super(l_request);
    }
}
@
