head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.07.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountStateChangeCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������J�ݏ󋵕ύX���ʃ��N�G�X�g(WEB3AdminFEqConAccountStateChangeCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ���E(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AioAccountDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O�������J�ݏ󋵕ύX���ʃ��N�G�X�g)<BR>
 * �O�������J�ݏ󋵕ύX���ʃ��N�G�X�g�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountStateChangeCommonRequest extends WEB3GenRequest 
{

    /**
     * (�ڋqID)<BR>
     * �O�������ڋqID
     */
    public String accountId;
    
    /**
     * (�X�V������J�ݏ󋵋敪)<BR>
     * �X�V������J�ݏ󋵋敪<BR>
     * <BR>
     * 1�F �J�ݍ�<BR>
     * 9�F ����
     */
    public String updatedAccountOpenStatusDiv;
    
    /**
     * @@roseuid 423554FF0167
     */
    public WEB3AdminFEqConAccountStateChangeCommonRequest() 
    {
     
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConAccountStateChangeCommonRequest.class);
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�ڋqID<BR>
     * <BR>
     *   this.�ڋqID == null�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01952<BR>
     * <BR>
     * �Q�j�X�V������J�ݏ󋵋敪<BR>
     * <BR>
     * �ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *   �h�J�ݍρh<BR>
     *   �h�����h
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01771<BR>
     * <BR>
     * @@roseuid 41E61233000E
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�ڋqID
        //this.�ڋqID == null
        //�̏ꍇ�A��O���X���[����B 
        if(WEB3StringTypeUtility.isEmpty(this.accountId))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01952,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋqID�����w��ł��B");

        }
        
        //�Q�j�X�V������J�ݏ󋵋敪
        //�ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        //�h�J�ݍρh 
        //�h�����h 
        if(!(WEB3AioAccountDivDef.OPEN_COMPLETE.equals(this.updatedAccountOpenStatusDiv)
            || WEB3AioAccountDivDef.DELETE.equals(this.updatedAccountOpenStatusDiv)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01771,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�X�V������J�ݏ󋵋敪[" + this.updatedAccountOpenStatusDiv + "]�B");
            
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 423554FF01B5
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
