head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.07.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoUploadHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��ײ�ްĲ�̫Ұ��݃A�b�v���[�h���𖾍�(WEB3PvInfoUploadHistoryUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
*/
package webbroker3.pvinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (��ײ�ްĲ�̫Ұ��݃A�b�v���[�h���𖾍�)<BR>
 * ��ײ�ްĲ�̫Ұ��݃A�b�v���[�h���𖾍׃N���X<BR>
 * 
 * @@author �����F
 * @@version 1.00
 */
public class WEB3PvInfoUploadHistoryUnit extends Message 
{
    
    /**
     * (�A�b�v���[�h������ԋ敪)<BR>
     * �A�b�v���[�h������ԋ敪<BR>
     * <BR>
     * 0�F�@@�A�b�v���[�h�҂� <BR>
     * 1�F�@@�A�b�v���[�h�� <BR>
     * 2�F�@@�A�b�v���[�h�� <BR>
     */
    public String uploadStateDiv;
    
    /**
     * (�A�b�v���[�h����)<BR>
     */
    public String uploadNumber;
    
    /**
     * (�A�b�v���[�h�J�n����)<BR>
     */
    public Date uploadStartDate;
    
    /**
     * (�A�b�v���[�h�I������)<BR>
     */
    public Date uploadEndDate;
    
    /**
     * (��ײ�ްĲ�̫Ұ��݃G���[�ԍ� )<BR>
     * ��ײ�ްĲ�̫Ұ��݃G���[�ԍ� <BR>
     * <BR>
     * �� ErrorMessage�e�[�u����PK <BR>
     */
    public String pvInfoErrorId;
    
    /**
     * (��ײ�ްĲ�̫Ұ��݃A�b�v���[�h���𖾍�)<BR>
     * �R���X�g���N�^<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoUploadHistoryUnit
     * @@roseuid 4160AF08025D
     */
    public WEB3PvInfoUploadHistoryUnit() 
    {
     
    }
}
@
