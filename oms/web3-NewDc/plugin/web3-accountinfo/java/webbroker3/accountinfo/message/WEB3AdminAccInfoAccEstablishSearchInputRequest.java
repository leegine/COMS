head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.05.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccEstablishSearchInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�������̓��N�G�X�g(WEB3AdminAccInfoAccEstablishSearchInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/09  �����q(���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�������̓��N�G�X�g)<BR>
 * �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�������̓��N�G�X�g<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0 
 */

public class WEB3AdminAccInfoAccEstablishSearchInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_info_acc_establish_search_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200610082163L;

    /**
     * @@roseuid 418F39EF0290
     */
    public WEB3AdminAccInfoAccEstablishSearchInputRequest()
    {

    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoAccEstablishSearchInputResponse(this);
    }
}
@
