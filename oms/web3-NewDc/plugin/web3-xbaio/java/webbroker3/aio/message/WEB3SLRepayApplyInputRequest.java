head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.52.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLRepayApplyInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍϐ\�����̓��N�G�X�g(WEB3SLRepayApplyInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.758
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�،��S�ۃ��[���ԍϐ\�����̓��N�G�X�g)<BR>
 * �S�ۃ��[���ԍϓ��̓��N�G�X�g�N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3SLRepayApplyInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_repay_apply_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709121515L;

    /**
     * @@roseuid 46E8908502DB
     */
    public WEB3SLRepayApplyInputRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g<BR>
     * @@roseuid 46BC080901A7
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SLRepayApplyInputResponse(this);
    }
}
@
