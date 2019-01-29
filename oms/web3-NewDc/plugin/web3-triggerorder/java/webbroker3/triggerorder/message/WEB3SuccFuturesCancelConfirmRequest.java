head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.41.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccFuturesCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���j�����w���敨��������m�F���N�G�X�g(WEB3SuccFuturesCancelConfirmRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/12 �k�v�u(���u) �V�K�쐬���f��272,303
 */
package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.message.WEB3FuturesCancelConfirmRequest;

/**
 * (�i�A���j�����w���敨��������m�F���N�G�X�g)<BR>
 * �i�A���j�����w���敨��������m�F���N�G�X�g�N���X<BR>
 * <BR>
 * @@author �k�v�u
 * @@version 1.0
 */
public class WEB3SuccFuturesCancelConfirmRequest extends WEB3FuturesCancelConfirmRequest
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803121737L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_futures_cancel_confirm";

    /**
     * @@roseuid 47D65936030D
     */
    public WEB3SuccFuturesCancelConfirmRequest()
    {

    }
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccFuturesCancelConfirmResponse(this);
    }
}
@
