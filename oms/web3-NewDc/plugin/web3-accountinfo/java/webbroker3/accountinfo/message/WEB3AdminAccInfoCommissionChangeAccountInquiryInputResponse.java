head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.01.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇������ڽ��ݽ(WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇������ڽ��ݽ)<BR>
 * �Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇������ڽ��ݽ<BR>
 * @@author �d��
 * @@version 1.0
 */
public class WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_commissionChangeAccountInquiryInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082145L;

    /**
     * @@roseuid 418F38690186
     */
    public WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse()
    {

    }

    /**
     * (�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇������ڽ��ݽ)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse
     * @@roseuid 41665770020E
     */
    public WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
