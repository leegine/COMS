head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.57.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoPasswordChangeAccountInquiryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�⍇��ڽ��ݽ(WEB3AdminAccInfoPasswordChangeAccountInquiryResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�⍇��ڽ��ݽ)<BR>
 * �Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�⍇��ڽ��ݽ<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoPasswordChangeAccountInquiryResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_passwordChangeAccountInquiry";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082106L;

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
     * @@roseuid 418F38600280
     */
    public WEB3AdminAccInfoPasswordChangeAccountInquiryResponse()
    {

    }

    /**
     * (�Ǘ��҂��q�l���Ïؔԍ��ύX�ڋq�⍇��ڽ��ݽ)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountInquiryResponse
     * @@roseuid 416B7FFC0318
     */
    public WEB3AdminAccInfoPasswordChangeAccountInquiryResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
