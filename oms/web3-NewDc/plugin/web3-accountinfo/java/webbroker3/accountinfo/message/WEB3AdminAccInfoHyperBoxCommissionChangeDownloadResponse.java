head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.07.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�ް��޳�۰��ڽ��ݽ(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�ް��޳�۰��ڽ��ݽ)<BR>
 * �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�ް��޳�۰��ڽ��ݽ<BR>
 * @@author �d��
 * @@version 1.0 
 */
public class WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_hyperBoxCommissionChangeDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082134L;

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
     * (ʲ�߰�ޯ���萔�����ꗗ)<BR>
     * ʲ�߰�ޯ���萔�����ꗗ<BR>
     */
    public WEB3AccInfoCommissionChangeAccountInfo[] hyperBoxCommissionList;

    /**
     * @@roseuid 418F3854009C
     */
    public WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse()
    {

    }

    /**
     * (�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�ް��޳�۰��ڽ��ݽ)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse
     * @@roseuid 4136B94300E9
     */
    public WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
