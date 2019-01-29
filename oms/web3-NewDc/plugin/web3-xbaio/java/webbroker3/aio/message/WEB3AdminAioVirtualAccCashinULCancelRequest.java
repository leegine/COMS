head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.55.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioVirtualAccCashinULCancelRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�[�`������������UL���~���N�G�X�g(WEB3AdminAioVirtualAccCashinULCancelRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/09 ������ (���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�o�[�`������������UL���~���N�G�X�g)<BR>
 * �o�[�`������������UL���~���N�G�X�g�N���X
 * 
 * @@author ������(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioVirtualAccCashinULCancelRequest extends WEB3GenRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminAioVirtualAccCashinULCancelRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_aio_virtual_acc_cashin_ul_cancel";    
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200605091050L;

    /**
     * (�A�b�v���[�hID)<BR>
     * �A�b�v���[�hID<BR>
     */
    public String uploadID;
    
    /**
     * @@roseuid 43F49A6E0186
     */
    public WEB3AdminAioVirtualAccCashinULCancelRequest()
    {
    }        
    
    /**
     * (createResponse�̎����j<BR>
     * <BR>
     * �o�[�`������������UL���~���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB620327
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioVirtualAccCashinULCancelResponse(this);                
    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>  
     * �P�j�@@�A�b�v���[�hID�̃`�F�b�N<BR> 
     * �P�|�P�j�����͂̏ꍇ�A��O���X���[����B<BR> 
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00973<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 41BD519702AC
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�A�b�v���[�hID�̃`�F�b�N 
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.uploadID))
        {
            log.debug("�A�b�v���[�hID�̃`�F�b�N");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00973,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�A�b�v���[�hID�����w��ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }   
}
@
