head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.04.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualCategoryRegistConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�Ǘ��҃J�e�S���[�o�^�m�F���X�|���X(WEB3AdminMutualCategoryRegistConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 ���� (���u) �V�K�쐬 
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (���M�Ǘ��҃J�e�S���[�o�^�m�F���X�|���X)<BR>
 * �����M���Ǘ��҃J�e�S���[�o�^�m�F���X�|���X�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AdminMutualCategoryRegistConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_category_regist_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412030955L;
    
    /**
     * (���M�����J�e�S���[�ꗗ)<BR>
     *  ���M�����J�e�S���[�ꗗ<BR>
     *  null�̏ꍇ�̓J�e�S���w�薳���Ƃ���
     */
    public WEB3MutualProductCategoryUnit[] categoryList;
    
    /**
     * (�����M���Ǘ��҃J�e�S���[�o�^�m�F���X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 4153B67901DC
     */
    public WEB3AdminMutualCategoryRegistConfirmResponse()
    {
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminMutualCategoryRegistConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
