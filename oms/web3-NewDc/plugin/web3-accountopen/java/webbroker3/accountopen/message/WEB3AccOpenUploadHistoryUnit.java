head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.00.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenUploadHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�݃A�b�v���[�h���𖾍�(WEB3AccOpenUploadHistoryUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/21 ���g (���u) �V�K�쐬 ���f�� No.147
*/

package webbroker3.accountopen.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�����J�݃A�b�v���[�h���𖾍�)<BR>
 * �����J�݃A�b�v���[�h���𖾍�<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AccOpenUploadHistoryUnit extends Message
{

    /**
     * (�A�b�v���[�h������ԋ敪)<BR>
     * �A�b�v���[�h������ԋ敪<BR>
     * <BR>
     * 0�F�@@�A�b�v���[�h�҂�<BR>
     * 1�F�@@�A�b�v���[�h��<BR>
     * 2�F�@@�A�b�v���[�h��<BR>
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
     * (�����J�݃G���[�ԍ�)<BR>
     * �����J�݃G���[�ԍ�<BR>
     * <BR>
     * �� ErrorMessage�e�[�u����PK<BR>
     */
    public String accOpenErrorId;

    /**
     * (�����J�݃A�b�v���[�h���𖾍�)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 47311CBE002E
     */
    public WEB3AccOpenUploadHistoryUnit()
    {

    }
}
@
