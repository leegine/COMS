head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.02.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoUploadHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l���A�b�v���[�h���𖾍�(WEB3AccInfoUploadHistoryUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ���C�g (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (���q�l���A�b�v���[�h���𖾍�)<BR>
 * ���q�l���A�b�v���[�h���𖾍�<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AccInfoUploadHistoryUnit extends Message 
{
    
    /**
     * (�A�b�v���[�h������ԋ敪)<BR>
     * �A�b�v���[�h������ԋ敪 <BR>
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
     * (���q�l���G���[�ԍ�)<BR>
     * ���q�l���G���[�ԍ�<BR>
     * <BR>
     * �� ErrorMessage�e�[�u����PK<BR>
     */
    public String accInfoErrorId;
    
    /**
     * (���q�l���A�b�v���[�h���𖾍�)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccInfoUploadHistoryUnit
     * @@roseuid 415BCF700005
     */
    public WEB3AccInfoUploadHistoryUnit() 
    {
     
    }
}
@
