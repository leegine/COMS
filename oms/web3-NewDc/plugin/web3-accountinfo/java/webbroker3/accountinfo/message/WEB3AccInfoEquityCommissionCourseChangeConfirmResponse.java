head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.03.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoEquityCommissionCourseChangeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���m�F���X�|���X(WEB3AccInfoEquityCommissionCourseChangeConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���m�F���X�|���X)<BR>
 * ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���m�F���X�|���X<BR>
 * @@author �d��
 * @@version 1.0  
 */
public class WEB3AccInfoEquityCommissionCourseChangeConfirmResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_equityCommissionCourseChangeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082159L;

    /**
     * (���ݓ���)<BR>
     * ���ݓ���<BR>
     */
    public Date currentDate;

    /**
     * @@roseuid 418F39F1029F
     */
    public WEB3AccInfoEquityCommissionCourseChangeConfirmResponse()
    {

    }

    /**
     * (���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���m�F���X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (���N�G�X�g�I�u�W�F�N�g)<BR>     
     * @@roseuid 41368DD00219
     */
    public WEB3AccInfoEquityCommissionCourseChangeConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
