head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAdditionalGenerationRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǐؔ�����ʕ\�����N�G�X�g(WEB3TPAdditionalGenerationRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/10 �����i���u�j�V�K�쐬 ���f��No.312
*/
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǐؔ�����ʕ\�����N�G�X�g) <BR>
 * �Ǐؔ�����ʕ\�����N�G�X�g�N���X�B<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3TPAdditionalGenerationRequest extends WEB3GenRequest
{
    /**
     * (PTYPE) <BR>
     */
    public static final String PTYPE = "additional_generation";

    /**
     * (SerialVersionUID) <BR>
     */
    public static final long serialVersionUID = 200810101032L;

    /**
     * (�R���X�g���N�^) <BR>
     */
    public WEB3TPAdditionalGenerationRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3TPAdditionalGenerationResponse(this);
    }

}
@
