head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquitySellConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������t�����m�F���X�|���X(WEB3EquitySellConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 �^�� (���u) �V�K�쐬
Revesion History : 2004/12/14 �K�� (SRA) �c�Č��Ή�
Revesion History : 2006/12/25 �đo�g(���u) ���f��No.1085
Revesion History : 2007/06/13 ���g (���u) ���f��No.1168
*/
package webbroker3.equity.message;

import java.util.Date;

/**
 * �i�����������t�����m�F���X�|���X�j�B<BR>
 * <BR>
 * �����������t�����m�F�����@@���X�|���X�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquitySellConfirmResponse extends WEB3EquityConfirmCommonResponse
{

    /**
     * �T�Z�뉿�P��<BR>
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
     * (�����h�c)<BR>
     * �����h�c<BR>
     */
    public String orderId;
    
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
     * (�s��R�[�h)<BR>
     * �s��R�[�h
     */
    public String marketCode;

    /**
    * PTYPE<BR>
    */
    public static final String PTYPE = "equity_sell_confirm";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200402041611L;

    /**
     * (�����L������)<BR>
     * �����L������
     */
    public Date expirationDate;

    /**
     * @@roseuid 40A0949B001F
     */
    public WEB3EquitySellConfirmResponse()
    {

    }

    public WEB3EquitySellConfirmResponse(WEB3EquitySellConfirmRequest l_request)
    {
        super(l_request);
    }

}
@
