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
filename	WEB3EquityChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������������������������X�|���X�f�[�^�N���X(WEB3EquityChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/11 ����� (���u) �V�K�쐬
Revesion History : 2004/12/14 �K�� (SRA) �c�Č��Ή�
Revesion History : 2007/06/13 ���g (���u) ���f��No.1168
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�����������������������X�|���X�j�B<br>
 * <br>
 * �������������������������@@���X�|���X�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquityChangeCompleteResponse extends WEB3GenResponse
{

    /**
     * (�X�V����)<BR>
     */
    public Date lastUpdatedTimestamp;

    /**
     * (����ID)<BR>
     */
    public String orderActionId;
    
    /**
     * (�C���T�C�_�[�x���\���t���O)<BR>
     * true�F�x���\���v�@@�@@�@@false�F�x���\���s�v<BR>
     */
	public boolean insiderWarningFlag;

    /**
     * (�A�������ݒ�t���O)<BR>
     * true�F�ݒ肠��@@�@@�@@false�F�ݒ�Ȃ�<BR>
     */
    public boolean succSettingFlag;

    /**
     * �|�������t�B�b�N�^�C�v�B<BR>
     */
    public static final String PTYPE = "equity_change_complete";

    /**
     * (�����L������)<BR>
     * �����L������
     */
    public Date expirationDate;

    /**
     * �V���A���o�[�W����UID�B<BR>
     */
    public static final long serialVersionUID = 200402241355L;

    /**
     * @@roseuid 409EFF810229
     */
    public WEB3EquityChangeCompleteResponse()
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
    public WEB3EquityChangeCompleteResponse(WEB3EquityChangeCompleteRequest l_request)
    {
        super(l_request);
    }
}
@
