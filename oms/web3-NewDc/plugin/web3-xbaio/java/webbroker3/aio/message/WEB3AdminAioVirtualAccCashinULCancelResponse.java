head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.04.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioVirtualAccCashinULCancelResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�[�`������������UL���~���X�|���X(WEB3AdminAioVirtualAccCashinULCancelResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/09 ������ (���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�o�[�`������������UL���~���X�|���X)<BR>
 * �o�[�`������������UL���~���X�|���X�N���X
 * 
 * @@author ������(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioVirtualAccCashinULCancelResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_aio_virtual_acc_cashin_ul_cancel";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200605091455L;    
    
    /**
     * @@roseuid 4158EB330171
     */
    public WEB3AdminAioVirtualAccCashinULCancelResponse()
    {
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminAioVirtualAccCashinULCancelResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  

}
@
