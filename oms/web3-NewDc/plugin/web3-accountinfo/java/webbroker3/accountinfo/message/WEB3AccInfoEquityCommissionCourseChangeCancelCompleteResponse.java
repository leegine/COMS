head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.01.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\������������X�|���X(WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���q�l��񊔎��ϑ��萔���R�[�X�ύX�\������������X�|���X)<BR>
 * ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\������������X�|���X<BR>
 * @@author �d��
 * @@version 1.0 
 */
public class WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_equityCommissionCourseChangeCancelComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082162L;

    /**
     * @@roseuid 418F39F0005D
     */
    public WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse()
    {

    }

    /**
     * (���q�l��񊔎��ϑ��萔���R�[�X�ύX�\������������X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (���N�G�X�g�I�u�W�F�N�g)<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse
     * @@roseuid 41368DFD0313
     */
    public WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
