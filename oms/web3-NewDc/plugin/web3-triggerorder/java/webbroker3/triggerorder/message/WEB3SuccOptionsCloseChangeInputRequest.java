head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.45.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccOptionsCloseChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�����w���I�v�V���������ԍϓ��͉�ʃ��N�G�X�g(WEB3SuccOptionsCloseChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 ���V�� (���u) �V�K�쐬 ���f�� No.263,305
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeInputRequest;


/**
 * (�i�A���j�����w���I�v�V���������ԍϓ��͉�ʃ��N�G�X�g)<BR>
 * �i�A���j�����w���I�v�V���������ԍϓ��͉�ʃ��N�G�X�g�N���X<BR>
 * <BR>
 * @@author ���V��
 * @@version 1.0
 */
public class WEB3SuccOptionsCloseChangeInputRequest extends WEB3OptionsCloseMarginChangeInputRequest
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803141445L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_options_close_change_input";

    /**
     * @@roseuid 47D9F2C9028C
     */
    public WEB3SuccOptionsCloseChangeInputRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccOptionsCloseChangeInputResponse(this);
    }
}
@
