head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇��ڽ��ݽ(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇��ڽ��ݽ)<BR>
 * �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇��ڽ��ݽ<BR>
 * @@author �J�N���V
 * @@version 1.0 
 */
public class WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mobileOfficeRegistAccountInquiry";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082113L;

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
     * @@roseuid 418F385D00FA
     */
    public WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse()
    {

    }

    /**
     * (�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇��ڽ��ݽ)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse
     * @@roseuid 415A5D980185
     */
    public WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
