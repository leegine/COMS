head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.05.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashTransferListInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o���ꗗ���̓��N�G�X�g(WEB3AdminAioCashTransferListInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/03 �����q (���u) �V�K�쐬�@@�d�l�ύX���f�� NO.693
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (���o���ꗗ���̓��N�G�X�g)<BR>
 * ���o���ꗗ���̓��N�G�X�g�N���X<BR>
 *
 * @@author �����q (���u)
 * @@version 1.0
 */
public class WEB3AdminAioCashTransferListInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cash_transfer_list_input";

    /**
     * serialVersionUID<BR>
     */
    private static final long serialVersionUID = 200702051000L;

    /**
     * @@roseuid 45C3F15702AF
     */
    public WEB3AdminAioCashTransferListInputRequest()
    {

    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB660189
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAioCashTransferListInputResponse(this);
    }
}
@
