head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.01.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l����p�U�����������۰�ޓ���ڽ��ݽ(WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l����p�U�����������۰�ޓ���ڽ��ݽ)<BR>
 * �Ǘ��҂��q�l����p�U�����������۰�ޓ���ڽ��ݽ<BR>
 * @@author �J�N���V
 * @@version 1.0 
 */
public class WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_exclusiveTransferAccountUploadInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082135L;

    /**
     * (�A�b�v���[�h�����ꗗ)<BR>
     * �A�b�v���[�h�����ꗗ<BR>
     */
    public WEB3AccInfoUploadHistoryUnit uploadHistoryList;

    /**
     * @@roseuid 418F3867033C
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse()
    {

    }

    /**
     * (�Ǘ��҂��q�l����p�U�����������۰�ޓ���ڽ��ݽ)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse
     * @@roseuid 415BCACB0014
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
