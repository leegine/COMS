head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.08.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�ް�̧���޳�۰��ڽ��ݽ(WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�ް�̧���޳�۰��ڽ��ݽ<BR>
 * �Ǘ��҂��q�l���ʲ�߰�ޯ��<BR>
 * �萔���ύX�ް�̧���޳�۰��ڽ��ݽ<BR>
 * @@author �d��
 * @@version 1.0 
 */
public class WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_hyperBoxCommissionChangeFileDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082133L;

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
     * @@roseuid 418F38540280
     */
    public WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse()
    {

    }

    /**
     * (�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�ް�̧���޳�۰��ڽ��ݽ)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse
     * @@roseuid 4136B9320118
     */
    public WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
