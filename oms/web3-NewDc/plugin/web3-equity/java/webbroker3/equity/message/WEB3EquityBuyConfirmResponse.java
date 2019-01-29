head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBuyConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������t�����m�F����(WEB3EquityBuyConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 �^�� (���u) �V�K�쐬
Revesion History : 2004/12/06 �����a��(SRA) �c�Č��Ή� �m��.�P�R�T
Revesion History : 2006/12/25 �đo�g(���u) ���f��No.1085
Revesion History : 2007/06/13 ���g (���u) ���f��No.1168
*/

package webbroker3.equity.message;

import java.util.Date;

/**
 * �i�����������t�����m�F���X�|���X�j�B<br>
 * <br>
 * �����������t�����m�F�����@@���X�|���X�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquityBuyConfirmResponse extends WEB3EquityConfirmCommonResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "equity_buy_confirm";

    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200402041600L;

    /**
     * �i�������j<BR>
     * ������<BR>
     */
    public String productName;

    /**
     * �i�萔�����j<BR>
     * �萔�����<BR>
     */
    public WEB3EquityCommissionInfoUnit commissionInfo;

    /**
     * �i�m�F���P���j<BR>
     * ��ʂł͔�\���B�������N�G�X�g�ő��M����l�B<BR>
     */
    public String checkPrice;
    
    /**
     * �i�����h�c�j<BR>
     * ��ʂł͔�\���B�������N�G�X�g�ő��M����l�B<BR>
     */
    public String orderId;
    
    /**
     * �i�C���T�C�_�[�x���\���t���O�j<BR>
     * true�F�x���\���v�@@�@@�@@false�F�x���\���s�v<BR>
     */
    public boolean insiderWarningFlag;

    /**
     * �i���ӕ����\���敪�j<BR>
     * null�F���ӕ�����\���@@�@@1�F�����s�����ӕ����\���@@�@@3�F�a����s�����ӕ����\��<BR>
     */
    public String attentionObjectionType;
    
    /**
     * �i�a����s���z�j<BR>
     * �]�͂̕s�����z<BR>
     */
    public String accountBalanceInsufficiency;

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
     * �R���X�g���N�^�B<BR>
     * �����̖����R���X�g���N�^ <BR>
     * <BR>
     * @@roseuid 405023760250
     */
    public WEB3EquityBuyConfirmResponse()
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
    public WEB3EquityBuyConfirmResponse(WEB3EquityBuyConfirmRequest l_request)
    {
        super(l_request);
    }
}
@
