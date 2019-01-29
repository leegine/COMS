head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.12.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�֊m�F���N�G�X�g(WEB3AioSecurityTransferConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 ���z (���u) �V�K�쐬   
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�،��U�֊m�F���N�G�X�g)<BR>
 * �،��U�֊m�F���N�G�X�g�N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferConfirmRequest extends WEB3AioSecurityTransferCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_security_transfer_confirm";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412071344L;     
    
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferConfirmRequest.class);
    
    /**
     * (�U�֐���)<BR>
     * ��ʂɂē��͂��ꂽ�U�֐���
     */
    public String transferQuantity;
    
    /**
     * @@roseuid 41B0255E000F
     */
    public WEB3AioSecurityTransferConfirmRequest() 
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
     *   �̏ꍇ�A��O���X���[����B
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01298<BR> 
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01299<BR> 
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01300<BR> 
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01301<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4153D33A0112
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
        if (WEB3StringTypeUtility.isEmpty(this.transferQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01298,
                this.getClass().getName() + "." + l_strMethodName,
                "�U�֐��� = null");                    
        }
        else if (!WEB3StringTypeUtility.isNumber(this.transferQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01301,
                this.getClass().getName() + "." + l_strMethodName,
                "�U�֐��� != ���p���� and �U�֐��� = " + this.transferQuantity);             
        }
        else if (Double.parseDouble(this.transferQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01299,
                this.getClass().getName() + "." + l_strMethodName,
                "�U�֐��� <= 0 and �U�֐��� = " + this.transferQuantity);             
        }
        else if (this.transferQuantity.length() >= 10)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01300,
                this.getClass().getName() + "." + l_strMethodName,
                "�U�֐���.length() >= 10 and �U�֐���.length() = " + this.transferQuantity.length());             
        }
      
        log.exiting(l_strMethodName);
    }
    
    /**
     *�icreateResponse�̎����j<BR>
     * <BR>
     * �،��U�֊m�F���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioSecurityTransferConfirmResponse(this);
    }
}
@
