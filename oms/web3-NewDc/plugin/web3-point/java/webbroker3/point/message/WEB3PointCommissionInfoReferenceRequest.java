head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.55.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointCommissionInfoReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����萔���������Ɖ�N�G�X�g(WEB3PointCommissionInfoReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/25 ���w��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�����萔���������Ɖ�N�G�X�g)<BR>
 * �����萔���������Ɖ�N�G�X�g�N���X<BR>
 * 
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3PointCommissionInfoReferenceRequest extends WEB3GenRequest
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "point_commissionInfoReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502241605L;    
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D12552000F
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3PointCommissionInfoReferenceResponse(this);
    }
}
@
