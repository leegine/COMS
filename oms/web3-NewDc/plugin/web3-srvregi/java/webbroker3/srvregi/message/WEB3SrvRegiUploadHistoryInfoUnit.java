head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.34.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiUploadHistoryInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
Author Name         : Daiwa Institute of Research
File Name           : �T�[�r�X���p�A�b�v���[�h���𖾍�(WEB3SrvRegiUploadHistoryInfoUnit.java)
Revision History    : 2008/03/10 ���u��(���u) �V�K�쐬 ���f��No.338
*/

package webbroker3.srvregi.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�T�[�r�X���p�A�b�v���[�h���𖾍�)<BR>
 * �T�[�r�X���p�A�b�v���[�h���𖾍�<BR>
 * <BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3SrvRegiUploadHistoryInfoUnit extends Message
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
     * (�T�[�r�X���p�G���[�ԍ�)<BR>
     * ���q�l���G���[�ԍ�<BR>
     * <BR>
     * �� ErrorMessage�e�[�u����PK<BR>
     */
    public String srvRegiErrorId;

    /**
     * (�T�[�r�X���p�A�b�v���[�h���𖾍�)<BR>
     * @@roseuid 47B8D9EC01BA
     */
    public WEB3SrvRegiUploadHistoryInfoUnit()
    {

    }
}
@
