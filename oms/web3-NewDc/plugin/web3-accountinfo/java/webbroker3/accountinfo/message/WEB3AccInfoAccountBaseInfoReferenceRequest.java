head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.57.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoAccountBaseInfoReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l����{���Ɖ�N�G�X�g(WEB3AccInfoAccountBaseInfoReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ���C�g (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���q�l����{���Ɖ�N�G�X�g)<BR>
 * ���q�l����{���Ɖ�N�G�X�g<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AccInfoAccountBaseInfoReferenceRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_accountBaseInfoReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082155L;

    /**
     * @@roseuid 418F386D033C
     */
    public WEB3AccInfoAccountBaseInfoReferenceRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoAccountBaseInfoReferenceResponse(this);
    }
}
@
