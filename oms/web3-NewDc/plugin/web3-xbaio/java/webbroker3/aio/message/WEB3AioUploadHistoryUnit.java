head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.05.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioUploadHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�[�`������������UL���𖾍�(WEB3AioUploadHistoryUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/09 ������ (���u) �V�K�쐬
*/

package webbroker3.aio.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�o�[�`������������UL���𖾍�)<BR>
 * �o�[�`������������UL���𖾍׃N���X
 * 
 * @@author ������(���u)
 * @@version 1.0 
 */

public class WEB3AioUploadHistoryUnit extends Message
{
    /**
     * @@roseuid 4158EB330171
     */
    public WEB3AioUploadHistoryUnit()
    {
    }
    
    /**
     * (�A�b�v���[�h������ԋ敪)<BR>
     * �A�b�v���[�h������ԋ敪<BR>
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
     * (�o�[�`�������������G���[�ԍ�)<BR>
     * �o�[�`�������������G���[�ԍ�<BR>
     */
    public String aioErrorId;
}
@
