head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.13.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXUploadHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�A�b�v���[�h���𖾍�(WEB3FXUploadHistoryUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/18 �A����(���u) �V�K�쐬
*/
package webbroker3.aio.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (FX�A�b�v���[�h���𖾍�)<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3FXUploadHistoryUnit extends Message 
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
     * �A�b�v���[�h����<BR>
     */
    public String uploadNumber;
    
    /**
     * (�A�b�v���[�h�J�n����)<BR>
     * �A�b�v���[�h�J�n����<BR>
     */
    public Date uploadStartDate;
    
    /**
     * (�A�b�v���[�h�I������)<BR>
     * �A�b�v���[�h�I������<BR>
     */
    public Date uploadEndDate;
    
    /**
     * (�A�b�v���[�h�G���[�ԍ�)<BR>
     * �A�b�v���[�h�G���[�ԍ� <BR>
     * <BR>
     * �� ErrorMessage�e�[�u����PK <BR>
     */
    public String fxErrorId;
    
    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 43C61CC10332
     */
    public WEB3FXUploadHistoryUnit() 
    {
     
    }
}
@
