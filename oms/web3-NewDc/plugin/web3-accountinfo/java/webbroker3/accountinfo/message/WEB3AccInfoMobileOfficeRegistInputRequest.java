head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.58.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMobileOfficeRegistInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l���g�єԍ��E�Ζ�����ύX�\�����̓��N�G�X�g(WEB3AccInfoMobileOfficeRegistInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���q�l���g�єԍ��E�Ζ�����ύX�\�����̓��N�G�X�g)<BR>
 * ���q�l���g�єԍ��E�Ζ�����ύX�\�����̓��N�G�X�g<BR>
 * @@author �J�N���V
 * @@version 1.0
 */
public class WEB3AccInfoMobileOfficeRegistInputRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_mobileOfficeRegistInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082153L;

    /**
     * @@roseuid 418F39F602DE
     */
    public WEB3AccInfoMobileOfficeRegistInputRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoMobileOfficeRegistInputResponse(this);
    }
}
@
