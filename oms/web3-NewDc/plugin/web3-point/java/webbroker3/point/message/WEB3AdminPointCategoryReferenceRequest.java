head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �J�e�S���[�ꗗ���N�G�X�g(WEB3AdminPointCategoryReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 ���w��(���u) �V�K�쐬
*/

package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�J�e�S���[�ꗗ���N�G�X�g)<BR>
 * �J�e�S���[�ꗗ���N�G�X�g�N���X<BR>
 *
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminPointCategoryReferenceRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_categoryReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290112L;
    
    /**
     * @@roseuid 41D12322007D
     */
    public WEB3AdminPointCategoryReferenceRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D12322008C
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointCategoryReferenceResponse(this);
    }
}
@
