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
filename	WEB3EquitySellCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������t�����������X�|���X(WEB3EquitySellCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 �^�� (���u) �V�K�쐬
Revesion History : 2004/12/14 �K�� (SRA) �c�Č��Ή�
Revesion History : 2006/12/25 �đo�g(���u) ���f��No.1085
Revesion History : 2007/06/13 ���g (���u) ���f��No.1168
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�����������t�����������X�|���X�j�B<BR>
 * <BR>
 * �����������t�������������@@���X�|���X�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquitySellCompleteResponse extends WEB3GenResponse
{

    /**
     * (�X�V����) <BR>
     * ������t���� <BR>
     */
    public Date lastUpdatedTimestamp;

    /**
     * (���ʔԍ�) <BR>
     * ����ID <BR>
     */
    public String orderActionId;
    
    /**
     * (�C���T�C�_�[�x���\���t���O)<BR>
     * true�F�x���\���v�@@�@@�@@false�F�x���\���s�v<BR>
     */
    public boolean insiderWarningFlag;

    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h
     */
    public String marketCode;

    /**
      * PTYPE<BR>
      */
    public static final String PTYPE = "equity_sell_complete";

    /**
     * (�����L������)<BR>
     * �����L������
     */
    public Date expirationDate;

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200402041600L;

    /**
     * @@roseuid 40A094A503C8
     */
    public WEB3EquitySellCompleteResponse()
    {
    }
    
    /**
     * @@roseuid 40A094A503C8
     */
    public WEB3EquitySellCompleteResponse(WEB3EquitySellCompleteRequest l_request)
    {
        super(l_request);
    }

}
@
