head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.06.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressInquiryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���Ұٱ��ڽ�S���⍇��ڽ��ݽ(WEB3AdminAccInfoMailAddressInquiryResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18 ���]�� (���u) �V�K�쐬
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l���Ұٱ��ڽ�S���⍇��ڽ��ݽ)<BR>
 * �Ǘ��҂��q�l���Ұٱ��ڽ�S���⍇��ڽ��ݽ<BR>
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressInquiryResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressInquiry";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082115L;

    /**
     * @@roseuid 418F38570138
     */
    public WEB3AdminAccInfoMailAddressInquiryResponse()
    {

    }

    /**
     * (�Ǘ��҂��q�l���Ұٱ��ڽ�S���⍇��ڽ��ݽ)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressInquiryResponse
     * @@roseuid 4136B90D03C8
     */
    public WEB3AdminAccInfoMailAddressInquiryResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@