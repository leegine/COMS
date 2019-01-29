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
filename	WEB3MarginOpenMarginConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����V�K�������m�F���X�|���X(WEB3MarginOpenMarginConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ������ (���u) �V�K�쐬
Revesion History : 2004/12/10 �K�� (SRA) �C��
Revesion History : 2006/12/25 �����F (���u) ���f�� 1085
Revesion History : 2007/06/13 ���g (���u) ���f��No.1167
*/

package webbroker3.equity.message;

import java.util.Date;

/**
 * �i�M�p����V�K�������m�F���X�|���X�j�B<br>
 * <br>
 * �M�p����V�K�������m�F���X�|���X�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginOpenMarginConfirmResponse extends WEB3MarginConfirmCommonResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_openMarginConfirm";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (������)
     */
    public String productName;
    
    /**
     * (�萔�����)<BR>
     * �M�p����萔�����
     */
    public WEB3MarginCommissionInfoUnit commissionInfo;
    
    /**
     * (����)<BR>
     * <BR>
     * �M�p����敪���u��ʐM�p�v�̎��ݒ�<BR>
     */
    public String interestRates;
    
    /**
     * (���Z����)
     */
    public String clearUpTerm;
    
    /**
     * (�m�F���P��)<BR>
     * <BR>
     * �������N�G�X�g�ő��M����l�B<BR>
     */
    public String checkPrice;
    
    /**
     * (���̖@@����)<BR>
     * true�F���Ӎ�or���щ����Ή��@@ false�F�񓯈�
     */
    //���ڍ폜
    //public boolean finSalesAgreement;
    
    /**
     * (����ID)
     */
    public String orderId;
      
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
     * (�s��R�[�h)<BR>
     * �s��R�[�h
     */
    public String marketCode;

    /**
     * (�����L������)<BR>
     * �����L������
     */
    public Date expirationDate;

    /**
     * @@roseuid 4140477D03B2
     */
    public WEB3MarginOpenMarginConfirmResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginOpenMarginConfirmResponse(WEB3MarginOpenMarginConfirmRequest l_request)
    {
        super(l_request);
    }
}
@
