head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.58.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�������X�|���X(WEB3AdminAccInfoMailAddressChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ���]�� (���u) �V�K�쐬
                   2006/05/19 ������ �d�l�ύX�E���f��104
Revesion History : 2010/02/21 ���g (���u) �d�l�ύX�E���f��No.263
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�������X�|���X)<BR>
 * �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�������X�|���X<BR>
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressChangeCompleteResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082118L;

    /**
     * (���[���A�h���X�ύX���)<BR>
     * ���[���A�h���X�ύX���<BR>
     */
    public WEB3AccInfoMailAddressUpdateInfo[] mailAddressUpdateInfo;

    /**
     * @@roseuid 418F385802CE
     */
    public WEB3AdminAccInfoMailAddressChangeCompleteResponse()
    {

    }

    /**
     * (�d���A�h���X���)<BR>
     * �d���A�h���X���<BR>
     */
    public String[] duplicationAddressInfo;

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAccInfoMailAddressChangeCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
