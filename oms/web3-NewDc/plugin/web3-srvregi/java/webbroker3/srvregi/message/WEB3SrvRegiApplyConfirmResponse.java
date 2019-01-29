head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.35.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiApplyConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�\���m�F���X�|���X(WEB3SrvRegiApplyConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 ���o�� �V�K�쐬
Revesion History : 2007/06/05 ��іQ (���u) ���f��.248
*/

package webbroker3.srvregi.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�T�[�r�X���p�\���m�F���X�|���X)<BR>
 * �T�[�r�X���p�\���m�F���X�|���X�N���X<BR>
 * 
 * @@author ���o��
 * @@version 1.0 
 */
public class WEB3SrvRegiApplyConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "srvregi_applyConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151432L;
    
    /**
     * (�K�p�J�n��)
     */
    public Date trialStartDate;
    
    /**
     * (�K�p�I����)
     */
    public Date trialEndDate;
    
    /**
     * (�m�F��������)
     */
    public Date checkDate;
    
    /**
     * (�ō����D�z)<BR>
     * �ΏۃT�[�r�X�����I�L�E�I�[�N�V�����L�̏ꍇ�̂ݎg�p�B<BR>
     * ����ȊO�̃T�[�r�X�̏ꍇ�ANull��ݒ�B<BR>
     */
    public String taxBidAmt;

    /**
     * (�\�������敪)<BR>
     * 1�F�����Ώہ@@2�F�\���s��<BR>
     */
    public String applyAttributeDiv;

    /**
     * (�����Ώۊ���)<BR>
     * �����Ώۊ���<BR>
     */
    public String freeTargetPeriod;

    /**
     * (���������\���敪)<BR>
     * null or '0' �F�ʏ�\���@@'1'�F���������\��<BR>
     */
    public String freeAttributeApplyDiv;

    /**
     * (�T�[�r�X���p�\���m�F���X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40F1FD5B0120
     */
    public WEB3SrvRegiApplyConfirmResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3SrvRegiApplyConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
