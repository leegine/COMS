head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.04.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�ޖ⍇��ڽ��ݽ(WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�ޖ⍇��ڽ��ݽ)<BR>
 * �Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�ޖ⍇��ڽ��ݽ<BR>
 * @@author �d��
 * @@version 1.0 
 */
public class WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_commissionChangeAccountDownloadInquiry";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082148L;

    /**
     * (�K�p�J�n��)<BR>
     * �K�p�J�n��<BR>
     */
    public Date trialStartDate;

    /**
     * @@roseuid 418F386B02BF
     */
    public WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse()
    {

    }

    /**
     * (�Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�ޖ⍇��ڽ��ݽ)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse
     * @@roseuid 415A5D6D035A
     */
    public WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
