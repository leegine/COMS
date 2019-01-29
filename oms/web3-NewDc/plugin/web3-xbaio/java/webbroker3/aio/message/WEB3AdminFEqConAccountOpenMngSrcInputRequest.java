head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.13.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountOpenMngSrcInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������J�݊Ǘ��������̓��N�G�X�g(WEB3AdminFEqConAccountOpenMngSrcInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ���E(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������J�݊Ǘ��������̓��N�G�X�g)<BR>
 * �O�������J�݊Ǘ��������̓��N�G�X�g�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0 
 */
public class WEB3AdminFEqConAccountOpenMngSrcInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_account_open_mng_src_input";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171808L;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h
     */
    public String[] branchCode;
    
    /**
     * @@roseuid 423552E70261
     */
    public WEB3AdminFEqConAccountOpenMngSrcInputRequest() 
    {
     
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConAccountOpenMngSrcInputRequest.class);
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j���X�R�[�h<BR>
     * <BR>
     *   this.���X�R�[�h == null or<BR>
     *   this.���X�R�[�h.length == 0<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * @@roseuid 41EF56EB0287
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���X�R�[�h
        //this.���X�R�[�h == null or 
        //this.���X�R�[�h.length == 0
        //�̏ꍇ�A��O���X���[����B
        if(this.branchCode == null || this.branchCode.length ==0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h == null or ���X�R�[�h.length == 0�B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �O�������J�݊Ǘ��������̓��X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminFEqConAccountOpenMngSrcInputResponse(this);
    }
}
@
