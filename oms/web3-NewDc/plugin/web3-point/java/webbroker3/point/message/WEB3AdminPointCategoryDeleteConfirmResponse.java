head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.57.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryDeleteConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �J�e�S���[�폜�m�F���X�|���X(WEB3AdminPointCategoryDeleteConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 ���w��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�J�e�S���[�폜�m�F���X�|���X)<BR>
 * �J�e�S���[�폜�m�F���X�|���X�N���X<BR>
 *
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminPointCategoryDeleteConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_categoryDeleteConfirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412291447L;
    
    /**
     * (�J�e�S���[��)<BR>
     * �J�e�S���[��<BR>
     */
    public String categoryName;
    
    /**
     * (�J�e�S���[�T�v)<BR>
     * �J�e�S���[�T�v<BR>
     */
    public String categoryOutline;
    
    /**
     * @@roseuid 41D1254602AF
     */
    public WEB3AdminPointCategoryDeleteConfirmResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminPointCategoryDeleteConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
