head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.14.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������J�݊Ǘ��X�e�[�^�X�X�V�m�F���X�|���X(WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ���E(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�O�������J�݊Ǘ��X�e�[�^�X�X�V�m�F���X�|���X)<BR>
 * �O�������J�݊Ǘ��X�e�[�^�X�X�V�m�F���X�|���X�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_account_open_mng_status_upd_confirm";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171808L;
    
    /**
     * @@roseuid 423552E9031C
     */
    public WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
