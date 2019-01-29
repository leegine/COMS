head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.03.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioVirtualAccCashinULConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�[�`������������UL�m�F���N�G�X�g(WEB3AdminAioVirtualAccCashinULConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/5/9 ������ (���u) �V�K�쐬
                 : 2006/06/12 ������ (���u) ���f��No.591
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�o�[�`������������UL�m�F���N�G�X�g)<BR>
 * �o�[�`������������UL�m�F���N�G�X�g�N���X<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioVirtualAccCashinULConfirmRequest extends WEB3GenRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    public static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(
            WEB3AdminAioVirtualAccCashinULConfirmRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_virtual_acc_cashin_ul_confirm";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200605091400L;
    
    /**
     * (�A�b�v���[�h�t�@@�C��)<BR>
     * �A�b�v���[�h�t�@@�C��
     */
    public String[] uploadFile;
    
    /**
     * (��s�R�[�h)<BR>
     * ��s�R�[�h
     */
    public String financialInstitutionCode;
    
    /**
     * @@roseuid 4158EB64017C
     */
    public WEB3AdminAioVirtualAccCashinULConfirmRequest() 
    {
    }
    
    /**
     * (createResponse�̎����j<BR>
     * <BR>
     * �o�[�`������������UL�m�F���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB620327
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioVirtualAccCashinULConfirmResponse(this);
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>  
     * �P�j �A�b�v���[�h�t�@@�C���`�F�b�N<BR> 
     * �P�|�P�j�����͂̏ꍇ�A��O���X���[����B<BR> 
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00976<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 41BD519702AC
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�A�b�v���[�h�t�@@�C���̃`�F�b�N 
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.uploadFile == null || this.uploadFile.length == 0)
        {            
            log.debug("�A�b�v���[�h�t�@@�C���̃`�F�b�N");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00976,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "");
        }
        log.exiting(STR_METHOD_NAME);
    }        
}
@
