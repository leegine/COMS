head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.55.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointManageDisplayResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�Ǘ���ʃ��X�|���X(WEB3AdminPointManageDisplayResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�|�C���g�Ǘ���ʃ��X�|���X)<BR>
 * �|�C���g�Ǘ���ʃ��X�|���X�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminPointManageDisplayResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_manageDisplay";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290084L;
    
    /**
     * @@roseuid 41D1254C03B9
     */
    public WEB3AdminPointManageDisplayResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminPointManageDisplayResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
