head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.07.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualCategoryRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�Ǘ��҃J�e�S���[�o�^�������N�G�X�g(WEB3AdminMutualCategoryRegistCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 ���� (���u) �V�K�쐬 
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (���M�Ǘ��҃J�e�S���[�o�^�������N�G�X�g)<BR>
 * �����M���Ǘ��҃J�e�S���[�o�^�������N�G�X�g�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AdminMutualCategoryRegistCompleteRequest 
    extends WEB3MutualCategoryRegistCommonRequest 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_category_regist_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412031046L;
    
    /**
     * (�Ïؔԍ�)<BR>
     *  �Ïؔԍ�
     */
    public String password;
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���M�Ǘ��҃J�e�S���[�o�^�������X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 4153B8400056
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMutualCategoryRegistCompleteResponse(this);
    }
}
@
