head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.09.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoPasswordChangeAccountDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰��ڽ��ݽ(WEB3AdminAccInfoPasswordChangeAccountDownloadResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰��ڽ��ݽ)<BR>
 * �Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰��ڽ��ݽ<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0   
 */
public class WEB3AdminAccInfoPasswordChangeAccountDownloadResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_passwordChangeAccountDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082108L;

    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��<BR>
     */
    public String totalPages;

    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     */
    public String totalRecords;

    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;

    /**
     * (�Ïؔԍ��ύX�ڋq���ꗗ)<BR>
     * �Ïؔԍ��ύX�ڋq���ꗗ<BR>
     */
    public WEB3AccInfoPasswordChangeAccountInfo[] passwordChangeAccountList;

    /**
     * @@roseuid 418F3860036B
     */
    public WEB3AdminAccInfoPasswordChangeAccountDownloadResponse()
    {

    }

    /**
     * (�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�޳�۰��ڽ��ݽ)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountDownloadResponse
     * @@roseuid 416B805B00A7
     */
    public WEB3AdminAccInfoPasswordChangeAccountDownloadResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
