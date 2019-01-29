head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.22.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenMarginChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�����V�K���m�F���N�G�X�g�N���X(WEB3FuturesOpenMarginChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 ����(���u) �V�K�쐬
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�����w���敨�����V�K���m�F���N�G�X�g)<BR>
 * �����w���敨�����V�K���m�F���N�G�X�g�N���X
 * @@author ����
 * @@version 1.0
 */
public class WEB3FuturesOpenMarginChangeConfirmRequest extends WEB3FuturesCommonRequest 
{
    /**
      * Logger
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesOpenMarginChangeConfirmRequest.class);    
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_openMarginChangeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407211140L;
    /**
     * (�����h�c)
     */
    public String id;
    
    /**
     * @@roseuid 40F7AE11036B
     */
    public WEB3FuturesOpenMarginChangeConfirmRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo��<BR>
     * <BR>
     * �Q�j�@@�h�c�`�F�b�N<BR>
     * �@@this.�h�c��null�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * �R�j�@@�������ʃ`�F�b�N<BR>
     * �@@�R�|�P�jthis.�������ʂ�null�̒l�ł���Η�O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00074<BR>
     * �@@�R�|�Q�jthis.�������ʂ������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00075<BR>
     * �@@�R�|�R�jthis.�������ʁ��O�̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00076<BR>
     * �@@�R�|�S�jthis.�������ʂ��T���𒴂���l�ł���Η�O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00077<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A2CEB0039B
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
                
        log.entering(STR_METHOD_NAME);
        super.validate();
         
        //�h�c�`�F�b�N
        //this.�h�c��null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            log.debug("this.�h�c��null�̒l�ł���Η�O���X���[����"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog. BUSINESS_ERROR_00080,
                STR_METHOD_NAME,
                "�h�c��null�̒l�ł���B"); 
        }
        
        log.debug("this.futOrderQuantity = " + this.futOrderQuantity);
        //�������ʃ`�F�b�N
        //this.�������ʂ�null�̒l�ł���Η�O���X���[����
        if (WEB3StringTypeUtility.isEmpty(this.futOrderQuantity))
        {
            log.debug("�������ʂ�null�̒l�̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00074,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�������ʂ���͂��Ă��������B");
        }

        //�������ʃ`�F�b�N
        //this.�������ʂ������ȊO�̒l�ł���Η�O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.futOrderQuantity))
        {
            log.debug("�������ʂ������ȊO�̒l�̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�������ʂ������ȊO�̒l�ł���B");
        }

        //�������ʃ`�F�b�N
        //this.�������ʂ����O�̒l�ł���Η�O���X���[����B
        if (Long.parseLong(this.futOrderQuantity) <= 0)
        {
            log.debug("�������ʂ����O�̒l�̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�������ʂ�0�ȉ��̒l�ł���B");
        }

        //�������ʃ`�F�b�N
        //this.�������ʂ��T���𒴂���l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.getByteLength(this.futOrderQuantity) > 5)
        {
            log.debug("�������ʂ��T���𒴂���l�̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00077,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�������ʂ��T���𒴂��܂����B");
        }         
          
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesOpenMarginChangeConfirmResponse(this);
    }
}
@
