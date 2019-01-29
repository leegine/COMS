head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.09.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionChangeAccountInquiryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇��ڽ��ݽ(WEB3AdminAccInfoCommissionChangeAccountInquiryResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇��ڽ��ݽ)<BR>
 * �Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇��ڽ��ݽ<BR>
 * @@author �d��
 * @@version 1.0
 */
public class WEB3AdminAccInfoCommissionChangeAccountInquiryResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_commissionChangeAccountInquiry";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082144L;

    /**
     * (�ڋq���i�����j)<BR>
     * �ڋq���i�����j<BR>
     */
    public String accountName;

    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��<BR>
     */
    public String totalPages;

    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     */
    public String totalRecords;

    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;

    /**
     * (�ύX�\�����ꗗ)<BR>
     * �ύX�\�����ꗗ<BR>
     */
    public WEB3AccInfoCommissionCourseChangeInfo[] changeApplyInfoList;

    /**
     * @@roseuid 418F3869033C
     */
    public WEB3AdminAccInfoCommissionChangeAccountInquiryResponse()
    {

    }

    /**
     * (�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇��ڽ��ݽ)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryResponse
     * @@roseuid 4151102400F7
     */
    public WEB3AdminAccInfoCommissionChangeAccountInquiryResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
