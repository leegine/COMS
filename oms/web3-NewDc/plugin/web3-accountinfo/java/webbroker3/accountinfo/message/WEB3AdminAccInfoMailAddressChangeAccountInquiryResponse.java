head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.08.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�ڋq�⍇��ڽ��ݽ(WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ���]�� (���u) �V�K�쐬
*/
package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�ڋq�⍇��ڽ��ݽ)<BR>
 * �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�ڋq�⍇��ڽ��ݽ<BR>
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressChangeAccountInquiry";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082119L;

    /**
     * (�O�c�Ɠ�)<BR>
     * �O�c�Ɠ�<BR>
     */
    public Date previousBizDate;

    /**
     * (�O��)<BR>
     * �O��<BR>
     */
    public Date previousDate;

    /**
     * @@roseuid 418F385C035B
     */
    public WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse()
    {

    }

    /**
     * (�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�ڋq�⍇��ڽ��ݽ)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�ڋq�⍇��ڽ��ݽ)<BR>
     * ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse
     * @@roseuid 415A58A4026F
     */
    public WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
