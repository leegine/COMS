head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.07.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�ޖ⍇��ظ���(WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�ޖ⍇��ظ���)<BR>
 * �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�ޖ⍇��ظ���<BR>
 * @@author �d��
 * @@version 1.0
 */
public class WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_hyperBoxCommissionChangeInquiry";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082132L;

    /**
     * @@roseuid 418F385D02EE
     */
    public WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse(this);
    }
}
@
