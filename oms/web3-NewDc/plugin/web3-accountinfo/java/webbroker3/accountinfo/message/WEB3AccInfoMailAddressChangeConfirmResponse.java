head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.02.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailAddressChangeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l��񃁁[���A�h���X�ύX�m�F���X�|���X(WEB3AccInfoMailAddressChangeConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ���]�� (���u) �V�K�쐬
                   2006/05/19 ������ �d�l�ύX�E���f��104
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���q�l��񃁁[���A�h���X�ύX�m�F���X�|���X)<BR>
 * ���q�l��񃁁[���A�h���X�ύX�m�F���X�|���X<BR>
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3AccInfoMailAddressChangeConfirmResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_mailAddressChangeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082157L;

    /**
     * @@roseuid 418F39F4006D
     */
    public WEB3AccInfoMailAddressChangeConfirmResponse()
    {

    }

    /**
     * (�d���A�h���X���)<BR>
     * �d���A�h���X���B<BR>
     */
    public String[] duplicationAddressInfo;

    /**
     * (���q�l��񃁁[���A�h���X�ύX�m�F���X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (���N�G�X�g�I�u�W�F�N�g)<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeConfirmResponse
     * @@roseuid 41368D9800D1
     */
    public WEB3AccInfoMailAddressChangeConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
