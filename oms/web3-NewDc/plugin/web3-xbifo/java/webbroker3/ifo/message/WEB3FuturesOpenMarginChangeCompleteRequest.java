head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.22.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenMarginChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�����V�K���������N�G�X�g�N���X(WEB3FuturesOpenMarginChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 ����(���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����w���敨�����V�K���������N�G�X�g)<BR>
 * �����w���敨�����V�K���������N�G�X�g�N���X
 * @@author ����
 * @@version 1.0
 */
public class WEB3FuturesOpenMarginChangeCompleteRequest extends WEB3FuturesCommonRequest 
{
    /**
      * Logger
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesOpenMarginChangeCompleteRequest.class);   
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_openMarginChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407211329L;
    /**
     * (�����h�c)
     */
    public String id;
    
    /**
     * (�Ïؔԍ�)
     */
    public String password;
    
    /**
     * (�m�F���P��)<BR>
     * �m�F���X�|���X�ő��M�����l�B<BR>
     */
    public String checkPrice;
    
    /**
     * (�m�F��������)<BR>
     * �m�F���X�|���X�ő��M�����l�B<BR>
     */
    public Date checkDate;
    
    /**
     * @@roseuid 40F7AE1102AF
     */
    public WEB3FuturesOpenMarginChangeCompleteRequest() 
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
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * �R�j�@@�������ʃ`�F�b�N<BR>
     * �@@�R�|�P�jthis.�������ʂ�null�̒l�ł���Η�O���X���[����B<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00074<BR>
     * �@@�R�|�Q�jthis.�������ʂ������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00075<BR>
     * �@@�R�|�R�jthis.�������ʁ��O �̏ꍇ�A��O���X���[����B<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00076<BR>
     * �@@�R�|�S�jthis.�������ʂ��T���𒴂���l�ł���Η�O���X���[����B<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00077<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A2CEEF005F
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�X�[�p�[�N���X��validate���\�b�h���Ăяo��
        super.validate();

        //�Q�j�h�c�`�F�b�N<BR>
        //this.�h�c��null�̒l�ł���Η�O���X���[����B<BR>
        //class: WEB3BusinessLayerException<BR>
        //tag:   BUSINESS_ERROR_00080<BR>
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "�h�c��null�̒l�ł���B");
        }

        //�R�j�@@�������ʃ`�F�b�N<BR>
        //�@@�R�|�P�jthis.�������ʂ�null�̒l�ł���Η�O���X���[����B<BR>
        //  class: WEB3BusinessLayerException<BR>
        //  tag:   BUSINESS_ERROR_00074<BR>
        if (WEB3StringTypeUtility.isEmpty(this.futOrderQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00074, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "�������ʂ���͂��Ă��������B");
        }

        //�@@�R�|�Q�jthis.�������ʂ������ȊO�̒l�ł���Η�O���X���[����B<BR>
        //  class: WEB3BusinessLayerException<BR>
        //  tag:   BUSINESS_ERROR_00075<BR>
        if (!WEB3StringTypeUtility.isNumber(this.futOrderQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "�������ʂ������ȊO�̒l�ł���B");
        }

        //�@@�R�|�R�jthis.�������ʂ����O�̒l�ł���Η�O���X���[����B<BR>
        //  class: WEB3BusinessLayerException<BR>
        //  tag:   BUSINESS_ERROR_00076<BR>
        if (Long.parseLong(this.futOrderQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "�������ʂ�0�ȉ��̒l�ł���B");
        }

        //�@@�R�|�S�jthis.�������ʂ��T���𒴂���l�ł���Η�O���X���[����B<BR>
        //  class: WEB3BusinessLayerException<BR>
        //  tag:   BUSINESS_ERROR_00077<BR>
        if (WEB3StringTypeUtility.getByteLength(this.futOrderQuantity) > 5)
        {
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
        return new WEB3FuturesOpenMarginChangeCompleteResponse(this);
    }
    
}
@
