head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.59.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������J�݊Ǘ��X�e�[�^�X�X�V�������N�G�X�g(WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ���E(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (�O�������J�݊Ǘ��X�e�[�^�X�X�V�������N�G�X�g)<BR>
 * �O�������J�݊Ǘ��X�e�[�^�X�X�V�������N�G�X�g�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest extends WEB3AdminFEqConAccountMngStateChangeCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_account_open_mng_status_upd_complete";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171808L;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�
     */
    public String password;
    
    /**
     * @@roseuid 423552EB00CB
     */
    public WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest() 
    {
     
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �O�������J�݊Ǘ��X�e�[�^�X�X�V�������X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminFEqConAccountOpenMngStatusUpdCompleteResponse(this);
    }
}
@
