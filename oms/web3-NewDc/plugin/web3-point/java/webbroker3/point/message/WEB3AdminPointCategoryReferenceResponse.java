head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.55.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �J�e�S���[�ꗗ���X�|���X(WEB3AdminPointCategoryReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 ���w��(���u) �V�K�쐬
*/

package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�J�e�S���[�ꗗ���X�|���X)<BR>
 * �J�e�S���[�ꗗ���X�|���X�N���X<BR>
 *
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminPointCategoryReferenceResponse extends WEB3GenResponse 
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
     * (�J�e�S���[�ꗗ)<BR>
     * �J�e�S���[���ׂ̔z��<BR>
     */
    public webbroker3.point.message.WEB3AdminPointCategoryUnit[] categoryList;
    
    /**
     * @@roseuid 41D1232201B5
     */
    public WEB3AdminPointCategoryReferenceResponse() 
    {
    
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminPointCategoryReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
