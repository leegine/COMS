head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.53.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryRegistCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �J�e�S���[�o�^���ʃ��N�G�X�g(WEB3AdminPointCategoryRegistCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 ���w��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�J�e�S���[�o�^���ʃ��N�G�X�g)<BR>
 * �J�e�S���[�o�^���ʃ��N�G�X�g�N���X<BR>
 *
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminPointCategoryRegistCommonRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_categoryRegistCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290111L;
    
    /**
     * (�J�e�S���[��)<BR>
     * �J�e�S���[�̖���<BR>
     */
    public String categoryName;
    
    /**
     * (�J�e�S���[�T�v)<BR>
     * �J�e�S���[�̊T�v<BR>
     */
    public String categoryOutline;
    
    /**
     * @@roseuid 41D1232203B9
     */
    public WEB3AdminPointCategoryRegistCommonRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D1232203C8
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
