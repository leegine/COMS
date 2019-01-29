head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.45.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccFuturesCloseChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���j�����w���敨�����ԍϓ��͉�ʃ��N�G�X�g(WEB3SuccFuturesCloseChangeInputRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/12 �k�v�u(���u) �V�K�쐬���f��272,303
 */
package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeInputRequest;

/**
 * (�i�A���j�����w���敨�����ԍϓ��͉�ʃ��N�G�X�g)<BR>
 * �i�A���j�����w���敨�����ԍϓ��͉�ʃ��N�G�X�g�N���X<BR>
 * <BR>
 * @@author �k�v�u
 * @@version 1.0
 */
public class WEB3SuccFuturesCloseChangeInputRequest extends WEB3FuturesCloseMarginChangeInputRequest
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803121804L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_futures_close_change_input";

    /**
     * @@roseuid 47D65939007D
     */
    public WEB3SuccFuturesCloseChangeInputRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccFuturesCloseChangeInputResponse(this);
    }
}
@