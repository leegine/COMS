head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.06.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\������m�F���X�|���X(WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���q�l��񊔎��ϑ��萔���R�[�X�ύX�\������m�F���X�|���X)<BR>
 * ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\������m�F���X�|���X<BR>
 * @@author �d��
 * @@version 1.0 
 */
public class WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_equityCommissionCourseChangeCancelConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082161L;

    /**
     * @@roseuid 418F39F00128
     */
    public WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse()
    {

    }

    /**
     * (���q�l��񊔎��ϑ��萔���R�[�X�ύX�\������m�F���X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (���N�G�X�g�I�u�W�F�N�g)<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse
     * @@roseuid 41368DEE0371
     */
    public WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@