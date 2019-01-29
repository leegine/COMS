head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.02.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�֊������N�G�X�g(WEB3AioSecurityTransferCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 ���z (���u) �V�K�쐬   
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�،��U�֊������N�G�X�g)<BR>
 * �،��U�֊������N�G�X�g�N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferCompleteRequest extends WEB3AioSecurityTransferCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_security_transfer_complete";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412071521L;     
    
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferCompleteRequest.class);
    
    /**
     * (�U�֐���)<BR>
     * ��ʂɂē��͂��ꂽ�U�֐���
     */
    public String changeQuantity;
    
    /**
     * (�Ïؔԍ�)<BR>
     * ��ʂɂē��͂��ꂽ�Ïؔԍ�
     */
    public String password;
    
    /**
     * (�m�F��������)<BR>
     * �m�F��������
     */
    public Date checkDate;
    
    /**
     * (����ID)<BR>
     * �m�F���Ɏ擾��������ID
     */
    public String orderId;
    
    /**
     * @@roseuid 41B0255E0138
     */
    public WEB3AioSecurityTransferCompleteRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�X�[�p�[�N���X��validate()���\�b�h���R�[������B<BR>
     * <BR>
     * �Q�j�U�֐���<BR>
     * <BR>
     *   ���N�G�X�g�f�[�^.�U�֐��� = null or<BR>
     *   ���N�G�X�g�f�[�^.�U�֐��� <= 0 or<BR>
     *   ���N�G�X�g�f�[�^.�U�֐���.length() >= 10 or<BR>
     *   ���N�G�X�g�f�[�^.�U�֐��� != ���p����<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01298<BR> 
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01299<BR> 
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01300<BR> 
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01301<BR> 
     * <BR>
     * �R�j�m�F��������<BR>
     * <BR>
     *   ���N�G�X�g�f�[�^.�m�F�������� = null <BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00078<BR> 
     * <BR>
     * �S�j����ID<BR>
     * <BR>
     *   ���N�G�X�g�f�[�^.����ID = null <BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00600<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4153D4DA024A
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        //�P�j�X�[�p�[�N���X��validate()���\�b�h���R�[������B
        super.validate();

        //�Q�j�U�֐���
        //  ���N�G�X�g�f�[�^.�U�֐��� = null or
        //  ���N�G�X�g�f�[�^.�U�֐��� <= 0 or
        //  ���N�G�X�g�f�[�^.�U�֐���.length() >= 10 or
        //  ���N�G�X�g�f�[�^.�U�֐��� != ���p����
        //  �̏ꍇ�A��O���X���[����B
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_01298 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_01299 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_01300 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_01301 
        if (WEB3StringTypeUtility.isEmpty(this.changeQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01298,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�U�֐��� = null");                    
        }
        else if (!WEB3StringTypeUtility.isNumber(this.changeQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01301,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�U�֐��� != ���p����, " +
                "���N�G�X�g�f�[�^.�U�֐��� = " + this.changeQuantity);             
        } 
        else if (Double.parseDouble(this.changeQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01299,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�U�֐��� <= 0, " +
                "���N�G�X�g�f�[�^.�U�֐��� = " + this.changeQuantity);             
        }
        else if (this.changeQuantity.length() >= 10)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01300,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�U�֐���.length() >= 10, " +
                "���N�G�X�g�f�[�^.�U�֐���.length() = " + this.changeQuantity.length());             
        }
       

        //�R�j�m�F��������
        //  ���N�G�X�g�f�[�^.�m�F�������� = null 
        //  �̏ꍇ�A��O���X���[����B 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00078 
        if (this.checkDate == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�m�F�������� = null ");             
        }

        //�S�j����ID
        //  ���N�G�X�g�f�[�^.����ID = null 
        //  �̏ꍇ�A��O���X���[����B
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00600     
        if (WEB3StringTypeUtility.isEmpty(this.orderId))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.����ID = null");             
        }
        
        log.exiting(l_strMethodName);
    }
    
    /**
     *�icreateResponse�̎����j<BR>
     * <BR>
     * �،��U�֊������X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioSecurityTransferCompleteResponse(this);
    }
}
@
