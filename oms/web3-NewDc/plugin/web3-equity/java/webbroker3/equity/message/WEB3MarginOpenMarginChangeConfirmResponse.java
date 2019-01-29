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
filename	WEB3MarginOpenMarginChangeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p�����������_�V�K���m�F���X�|���X�N���X(WEB3MarginOpenMarginChangeConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 ������ (���u) �V�K�쐬
Revesion History : 2004/12/10 �K�� (SRA) �C��
Revesion History : 2007/06/13 ���g (���u) ���f��No.1167
*/
package webbroker3.equity.message;

import java.util.Date;

/**
 * �i�M�p�����������_�V�K���m�F���X�|���X�j�B<br>
 * <br>
 * �M�p�����������_�V�K���m�F���X�|���X�N���X
 * @@version 1.0
 */
public class WEB3MarginOpenMarginChangeConfirmResponse extends WEB3MarginConfirmCommonResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_openMarginChangeConfirm";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (���o������)<BR>
     */
    public String partContQuantity;
    
    /**
     * (�萔�����)<BR>
     * �M�p����萔�����
     */
    public WEB3MarginCommissionInfoUnit commissionInfo;
    
    /**
     * (�m�F���P��)<BR>
     * <BR>
     * ��ʂł͔�\���B�������N�G�X�g�ő��M����l�B
     */
    public String checkPrice;
    
    /**
     * (�C���T�C�_�[�x���\���t���O)<BR>
     * true�F�x���\���v�@@�@@�@@false�F�x���\���s�v
     */
    public boolean insiderWarningFlag;
    
    /**
     * (���ӕ����\���敪)<BR>
     * ���ӕ����\���敪�B<BR>
     * null�F���ӕ�����\���@@�@@2�F���S�ۋK�����ӕ����\��
     */
    public String attentionObjectionType;

    /**
     * (�����L������)<BR>
     * �����L������
     */
    public Date expirationDate;

    /**
     * @@roseuid 4140453903D6
     */
    public WEB3MarginOpenMarginChangeConfirmResponse() 
    {
     
    }

    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginOpenMarginChangeConfirmResponse(WEB3MarginOpenMarginChangeConfirmRequest l_request)
    {
        super(l_request);
    }
}
@
