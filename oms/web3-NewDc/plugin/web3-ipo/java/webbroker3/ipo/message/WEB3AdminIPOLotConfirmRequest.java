head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.41.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I�����m�F���N�G�X�g(WEB3AdminIPOLotConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/20 �A���� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ���IPO���I�����m�F���N�G�X�g)<BR>
 *  �Ǘ���IPO���I�����m�F���N�G�X�g�N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminIPOLotConfirmRequest extends WEB3IPOLotCommonRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIPOLotConfirmRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_lotConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200512192100L;
    
    /**
     * ���X�R�[�h
     */
    public String[] branchCode;
    
    /**
     * �������g����
     */
    public String allotTotalQuantity;
    
    /**
     * (�����������)<BR>
     * ����������ʁi�P���[�v������j�B
     */
    public String allotLimitQuantity;
    
    /**
     * �⌇�������g����
     */
    public String waitingAllotTotalQuantity;
    
    /**
     * (�⌇�����������)<BR>
     * �⌇����������ʁi�P���[�v������j�B
     */
    public String waitingAllotLimitQuantity;
    
    /**
     * @@roseuid 4112DAD60041
     */
    public WEB3AdminIPOLotConfirmRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate()���R�[������B <BR>
     * <BR>
     * �Q�j�@@�������g���ʃ`�F�b�N<BR>
     * �@@�@@�@@this.�������g���� != null�@@�̏ꍇ�A�ȉ��̃`�F�b�N�������Ȃ��B<BR>
     * <BR>
     * �@@�@@�@@�Q�|�P�j�@@this.�������g���� != ���l �̏ꍇ,��O���X���[<BR>
     *                 �G���[���b�Z�[�W�u�������g���ʂ������ȊO�̒l�ł��B�v
     *                 �̗�O���X���[����B<BR>
     *              class: WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_02314<BR>
     * <BR>
     * �R�j�@@����������ʃ`�F�b�N<BR>
     * �@@�@@�@@this.����������� != null�@@�̏ꍇ�A�ȉ��̃`�F�b�N�������Ȃ��B<BR>
     * <BR>
     * �@@�@@�@@�R�|�P�j�@@this.����������� != ���l �̏ꍇ,��O���X���[<BR>
     *                 �G���[���b�Z�[�W�u����������ʂ������ȊO�̒l�ł��B�v<BR>
     *                 �̗�O���X���[����B<BR>
     *              class: WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_02315<BR>
     * <BR>
     * �S�j�@@�⌇�������g���ʃ`�F�b�N<BR>
     * �@@�@@�@@this.�⌇�������g���� != null�@@�̏ꍇ�A�ȉ��̃`�F�b�N�������Ȃ��B<BR>
     * <BR>
     * �@@�@@�@@�S�|�P�j�@@this.�⌇�������g���� != ���l �̏ꍇ,��O���X���[<BR>
     *                 �G���[���b�Z�[�W�u�⌇�������g���ʂ������ȊO�̒l�ł��B�v<BR>
     *                 �̗�O���X���[����B<BR>
     *              class: WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_02316<BR>
     * <BR>
     * �T�j�@@�⌇����������ʃ`�F�b�N<BR>
     * �@@�@@�@@this.�⌇����������� != null�@@�̏ꍇ�A�ȉ��̃`�F�b�N�������Ȃ��B<BR>
     * <BR>
     * �@@�@@�@@�T�|�P�j�@@this.�⌇����������� != ���l �̏ꍇ,��O���X���[<BR>
     *                 �G���[���b�Z�[�W�u�⌇����������ʂ������ȊO�̒l�ł��B�v<BR>
     *                 �̗�O���X���[����B<BR>
     *              class: WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_02317<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�X�[�p�[�N���X��validate()���R�[������B
        super.validate();
        
        //�Q�|�P�j�@@this.�������g���� != ���l �̏ꍇ,��O���X���[
        //   �G���[���b�Z�[�W�u�������g���ʂ������ȊO�̒l�ł��B�v
        if (this.allotTotalQuantity != null && !WEB3StringTypeUtility.isNumber(this.allotTotalQuantity)) 
        {
            log.debug("�������g���ʂ����l�ȊO�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02314, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "�������g���ʂ����l�ȊO�ł��B");
        }
        
        //�R�|�P�j�@@this.����������� != ���l �̏ꍇ,��O���X���[
        //  �G���[���b�Z�[�W�u����������ʂ������ȊO�̒l�ł��B�v
        if (this.allotLimitQuantity != null && !WEB3StringTypeUtility.isNumber(this.allotLimitQuantity)) 
        {
            log.debug("����������ʂ����l�ȊO�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02315,
                getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ����l�ȊO�ł��B");
        }
        
        //�S�|�P�j this.�⌇�������g���� != ���l �̏ꍇ,��O���X���[
        //       �G���[���b�Z�[�W�u�⌇�������g���ʂ������ȊO�̒l�ł��B�v
        if (this.waitingAllotTotalQuantity != null && !WEB3StringTypeUtility.isNumber(this.waitingAllotTotalQuantity)) 
        {
            log.debug("�⌇�������g���ʂ����l�ȊO�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02316,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�⌇�������g���ʂ����l�ȊO�ł��B");
        }
        
        //�T�|�P�j�@@this.�⌇����������� != ���l �̏ꍇ,��O���X���[
        //        �G���[���b�Z�[�W�u�⌇����������ʂ������ȊO�̒l�ł��B�v
        if (this.waitingAllotLimitQuantity != null && !WEB3StringTypeUtility.isNumber(this.waitingAllotLimitQuantity)) 
        {
            log.debug("�⌇����������ʂ����l�ȊO�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02317,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�⌇����������ʂ����l�ȊO�ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD60055
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOLotConfirmResponse(this);
    }
}
@
