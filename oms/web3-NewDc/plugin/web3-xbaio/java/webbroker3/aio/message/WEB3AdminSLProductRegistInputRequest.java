head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.58.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLProductRegistInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ����o�^���̓��N�G�X�g(WEB3AdminSLProductRegistInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 �И��� (���u) �V�K�쐬 ���f��No.760
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�S�ۖ����o�^���̓��N�G�X�g)<BR>
 * �S�ۖ����o�^���̓��N�G�X�g�N���X<BR>
 *
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminSLProductRegistInputRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_sl_product_regist_input";

    /**
     * serialVersionUID<BR>
     */
    private static final long serialVersionUID = 200709141313L;

    /**
     * @@roseuid 46E8908401B2
     */
    public WEB3AdminSLProductRegistInputRequest()
    {

    }

    /**
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSLProductRegistInputResponse(this);
    }
}
@
