head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.15.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLAccountOpenRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SL�����J�݃��N�G�X�g(WEB3SLAccountOpenRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.754
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (SL�����J�݃��N�G�X�g)<BR>
 * SL�����J�݃��N�G�X�g�N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3SLAccountOpenRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_account_open";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709071037L;

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g<BR>
     * @@roseuid 46BC080901A7
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SLAccountOpenResponse(this);
    }
}
@
