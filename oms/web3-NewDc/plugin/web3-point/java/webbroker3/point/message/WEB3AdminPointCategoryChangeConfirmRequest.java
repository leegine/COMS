head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.53.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �J�e�S���[�����m�F���N�G�X�g(WEB3AdminPointCategoryChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 ���w��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (�J�e�S���[�����m�F���N�G�X�g)<BR>
 * �J�e�S���[�����m�F���N�G�X�g�N���X<BR>
 *
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminPointCategoryChangeConfirmRequest extends WEB3AdminPointCategoryChangeCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_categoryChangeConfirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412291447L;
    
    /**
     * @@roseuid 41D1254901D4
     */
    public WEB3AdminPointCategoryChangeConfirmRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D1254601D4
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointCategoryChangeConfirmResponse(this);
    }
}
@
