head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.49.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccOptionsCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�����w���I�v�V������������m�F���N�G�X�g(WEB3SuccOptionsCancelConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 ���V�� (���u) �V�K�쐬 ���f�� No.263,305
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.message.WEB3OptionsCancelConfirmRequest;


/**
 * (�i�A���j�����w���I�v�V������������m�F���N�G�X�g)<BR>
 * �i�A���j�����w���I�v�V������������m�F���N�G�X�g�N���X<BR>
 * <BR>
 * @@author ���V��
 * @@version 1.0
 */
public class WEB3SuccOptionsCancelConfirmRequest extends WEB3OptionsCancelConfirmRequest
{
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803141424L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_options_cancel_confirm";

    /**
     * @@roseuid 47D9F2C90098
     */
    public WEB3SuccOptionsCancelConfirmRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccOptionsCancelConfirmResponse(this);
    }
}
@
