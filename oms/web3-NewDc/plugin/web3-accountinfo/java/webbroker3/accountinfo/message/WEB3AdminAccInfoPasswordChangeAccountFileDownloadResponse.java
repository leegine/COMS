head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.04.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq̧���޳�۰��ڽ��ݽ(WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq̧���޳�۰��ڽ��ݽ)<BR>
 * �Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq̧���޳�۰��ڽ��ݽ<BR>
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_passwordChangeAccountFileDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082107L;

    /**
     * (�_�E�����[�h�t�@@�C��)<BR>
     * �_�E�����[�h�t�@@�C��<BR>
     * <BR>
     * �� CSV�t�@@�C���s�̔z��<BR>
     */
    public String[] downloadFile;

    /**
     * (���ݓ���)<BR>
     * ���ݓ���<BR>
     */
    public Date currentDate;

    /**
     * @@roseuid 418F3861006D
     */
    public WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse()
    {

    }

    /**
     * (�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq̧���޳�۰��ڽ��ݽ)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse
     * @@roseuid 416B8095002A
     */
    public WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
