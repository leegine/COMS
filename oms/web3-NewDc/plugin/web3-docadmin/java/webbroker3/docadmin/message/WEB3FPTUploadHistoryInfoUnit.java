head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FPTUploadHistoryInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����@@��t�{���A�b�v���[�h���𖾍�(WEB3FPTUploadHistoryInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/07 ���g (���u) �V�K�쐬 ���f�� No.013,No.014
*/
package webbroker3.docadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�����@@��t�{���A�b�v���[�h���𖾍�)<BR>
 * �����@@��t�{���A�b�v���[�h���𖾍�<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3FPTUploadHistoryInfoUnit extends Message
{

    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 0�F �o�^<BR>
     * 1�F �폜<BR>
     */
    public String statusDiv;

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
     * (�����@@��t�{���G���[�ԍ�)<BR>
     * �����@@��t�{���G���[�ԍ�<BR>
     * <BR>
     * �� ErrorMessage�e�[�u����PK<BR>
     */
    public String fptErrorId;

    /**
     * (�����@@��t�{���A�b�v���[�h���𖾍�)<BR>
     * �f�B�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3FPTUploadHistoryInfoUnit()
    {

    }
}
@
