head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l����p�U�����������۰�ފm�Fڽ��ݽ(WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l����p�U�����������۰�ފm�Fڽ��ݽ)<BR>
 * �Ǘ��҂��q�l����p�U�����������۰�ފm�Fڽ��ݽ<BR>
 * @@author �J�N���V
 * @@version 1.0
 */
public class WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_exclusiveTransferAccountUploadConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082136L;

    /**
     * (�A�b�v���[�h����)<BR>
     * �A�b�v���[�h����<BR>
     */
    public String uploadNumber;

    /**
     * (�A�b�v���[�h�h�c)<BR>
     * �A�b�v���[�h�h�c<BR>
     */
    public String uploadID;
    
    /**
     * (�G���[�ڋq�ꗗ)<BR>
     *�G���[�ڋq�ꗗ
     */
    public WEB3AdminAccInfoErrorAccount[] errorAccountList;

    /**
     * @@roseuid 418F3869000F
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse()
    {

    }

    /**
     * (�Ǘ��҂��q�l����p�U�����������۰�ފm�Fڽ��ݽ)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse
     * @@roseuid 415BCA9B0360
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
