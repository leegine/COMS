head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.55.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointManageDisplayRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�Ǘ���ʃ��N�G�X�g(WEB3AdminPointManageDisplayRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�|�C���g�Ǘ���ʃ��N�G�X�g)<BR>
 * �|�C���g�Ǘ���ʃ��N�G�X�g�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminPointManageDisplayRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_manageDisplay";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290085L;
    
    /**
     * @@roseuid 41D1254C02FD
     */
    public WEB3AdminPointManageDisplayRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D1254C031C
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointManageDisplayResponse(this);
    }
}
@
