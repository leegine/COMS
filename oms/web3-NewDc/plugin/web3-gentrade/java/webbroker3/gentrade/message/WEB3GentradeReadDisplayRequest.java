head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.50.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5cc4d8b0586158f;
filename	WEB3GentradeReadDisplayRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : �d�q���{���\�����N�G�X�g(WEB3GentradeReadDisplayRequest.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/11/10 �����F(���u) �V�K�쐬 �d�l�ύX���f��No.353
*/

package webbroker3.gentrade.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�d�q���{���\�����N�G�X�g)<BR>
 * �d�q���{���\�����N�G�X�g�N���X<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3GentradeReadDisplayRequest extends WEB3GenRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "gentrade_read_display";

    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 201011101417L;

    /**
     * @@roseuid 423698A601D2
     */
    public WEB3GentradeReadDisplayRequest()
    {

    }

    /**
     * ���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��ԋp����B<BR>
     * @@return WEB3GenResponse<BR>
     * @@roseuid 4236708603DE<BR>
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3GentradeReadDisplayResponse(this);
    }
}
@
