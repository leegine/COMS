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
filename	WEB3EquityChangeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������������m�F�����@@���X�|���X�f�[�^�N���X(WEB3EquityChangeConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/11 ����� (���u) �V�K�쐬
Revesion History : 2004/12/14 �K�� (SRA) �c�Č��Ή�
Revesion History : 2007/06/13 ���g (���u) ���f��No.1168
*/
package webbroker3.equity.message;

import java.util.Date;

/**
 * �i�����������������m�F���X�|���X�j�B<br>
 * <br>
 * �����������������m�F�����@@���X�|���X�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquityChangeConfirmResponse extends WEB3EquityConfirmCommonResponse
{

    /**
     * (���o������)<BR>
     */
    public String partContQuantity;

    /**
     * (�T�Z�뉿�P��)<BR>
     */
    public String estimatedBookPrice;
    
    /**
     * (�萔�����)<BR>
     * �萔�����<BR>
     */
    public WEB3EquityCommissionInfoUnit commissionInfo;
    
    /**
     * (�m�F���P��)<BR>
     * �m�F���P��<BR>
     */
    public String checkPrice;
    
    /**
     * (�C���T�C�_�[�x���\���t���O)<BR>
     * true�F�x���\���v�@@�@@�@@false�F�x���\���s�v<BR>
     */
    public boolean insiderWarningFlag;

    /**
     * (���ӕ����\���敪)<BR>
     * null�F���ӕ�����\���@@�@@1�F�����s�����ӕ����\���@@�@@3�F�a����s�����ӕ����\��<BR>
     */
    public String attentionObjectionType;
    
    /**
     * (�a����s���z)<BR>
     * �]�͂̕s�����z<BR>
     */
    public String accountBalanceInsufficiency;

    /**
     * (�����L������)<BR>
     * �����L������
     */
    public Date expirationDate;

    /**
     * �|�������t�B�b�N�^�C�v�B<BR>
     */
    public static final String PTYPE = "equity_change_confirm";

    /**
     * �V���A���o�[�W����UID�B<BR>
     */
    public static final long serialVersionUID = 200402241455L;

    /**
     * @@roseuid 409EFF7700DA
     */
    public WEB3EquityChangeConfirmResponse()
    {

    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 405023760250
     */
    public WEB3EquityChangeConfirmResponse(WEB3EquityChangeConfirmRequest l_request)
    {
        super(l_request);
    }
        
}
@
