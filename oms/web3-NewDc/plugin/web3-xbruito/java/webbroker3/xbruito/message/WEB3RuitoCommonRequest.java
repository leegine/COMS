head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ������ʃ��N�G�X�g�N���X(WEB3RuitoCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 ���E (���u) �V�K�쐬
*/
package webbroker3.xbruito.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �ݐϓ������ʃ��N�G�X�g<BR>
 */
public class WEB3RuitoCommonRequest extends WEB3GenRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_common";

    /**
     * �����R�[�h<BR>
     */
    public String ruitoProductCode;

    /**
     * ��������<BR>
     */
    public String ruitoOrderQuantity = null;

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40922C33035B
     */
    public WEB3RuitoCommonRequest()
    {

    }

    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40B6F7600222
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
