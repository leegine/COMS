head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.50.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistStatusSearchInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҁE�����E���z���o�^�󋵖⍇�����̓��N�G�X�g(WEB3AdminInformProfDistStatusSearchInputRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/04 �Ӑ�(���u) �V�K�쐬 ���f��No.054
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҁE�����E���z���o�^�󋵖⍇�����̓��N�G�X�g)<BR>
 * �Ǘ��ҁE�����E���z���o�^�󋵖⍇�����̓��N�G�X�g�N���X<BR>
 */
public class WEB3AdminInformProfDistStatusSearchInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_prof_dist_status_search_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200706042212L;

    /**
     * @@roseuid 4663A9D80186
     */
    public WEB3AdminInformProfDistStatusSearchInputRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminInformProfDistStatusSearchInputResponse(this);
    }
}
@
