head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.00.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoInsiderInfoChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l�������ҏ��ύX���̓��X�|���X(WEB3AdminAccInfoInsiderInfoChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 ���C�g (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l�������ҏ��ύX���̓��X�|���X)<BR>
 * �Ǘ��҂��q�l�������ҏ��ύX���̓��X�|���X<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminAccInfoInsiderInfoChangeInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_insiderInfoChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082129L;
    
    /**
     * (�����ҏ��)<BR>
     * �����ҏ�� <BR>
     */
    public WEB3AccInfoInsiderInfo insiderInfo;
    
    /**
     * @@roseuid 418F3863002E
     */
    public WEB3AdminAccInfoInsiderInfoChangeInputResponse()
    {

    }

    /**
     * (�Ǘ��҂��q�l�������ҏ��ύX���̓��X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeInputResponse
     * @@roseuid 416657DA0191
     */
    public WEB3AdminAccInfoInsiderInfoChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
