head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.58.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressUploadCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�������X�|���X(WEB3AdminAccInfoMailAddressUploadCancelResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/14 ���L�Y (���u) �V�K�쐬                   
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�������X�|���X)<BR>
 * �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�������X�|���X<BR>
 * 
 * @@author ���L�Y(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressUploadCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressUploadComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603141726L; 

    /**
     * @@roseuid 4158E8E201DD
     */
    public WEB3AdminAccInfoMailAddressUploadCompleteResponse() 
    {
     
    }
     
    /**
     * (�Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�������X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     */
    protected WEB3AdminAccInfoMailAddressUploadCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }   
}
@
