head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.01.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioVirtualAccCashinULInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�[�`������������UL���̓��X�|���X(WEB3AdminAioVirtualAccCashinULInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/5/9 ������ (���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�o�[�`������������UL���̓��X�|���X)<BR>
 * �o�[�`������������UL���̓��X�|���X�N���X<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioVirtualAccCashinULInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_virtual_acc_cashin_ul_input";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200605091615L;
    
    /**
     * (�A�b�v���[�h�����ꗗ)<BR>
     */
    public WEB3AioUploadHistoryUnit uploadHistoryList;
    
    /**
     * @@roseuid 423552AB00FA
     */
    public WEB3AdminAioVirtualAccCashinULInputResponse() 
    {
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAioVirtualAccCashinULInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }   
}
@
