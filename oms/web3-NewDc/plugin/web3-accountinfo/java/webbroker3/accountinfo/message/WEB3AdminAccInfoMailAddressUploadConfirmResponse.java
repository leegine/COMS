head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.08.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressUploadConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�m�F���X�|���X(WEB3AdminAccInfoMailAddressUploadConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/14 ���̖N (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�m�F���X�|���X)<BR>
 * �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�m�F���X�|���X<BR>
 * 
 * @@author ���̖N(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressUploadConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressUploadConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603141715L;

    /**
     * (�A�b�v���[�h����)<BR>
     */
    public String uploadNumber;
    
    /**
     * (�A�b�v���[�hID)<BR>
     */
    public String uploadID;

    /**
     * @@roseuid 418F38570138
     */
    public WEB3AdminAccInfoMailAddressUploadConfirmResponse()
    {

    }

    /**
     * (�Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�m�F���X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@roseuid 4136B90D03C8
     */
    public WEB3AdminAccInfoMailAddressUploadConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
