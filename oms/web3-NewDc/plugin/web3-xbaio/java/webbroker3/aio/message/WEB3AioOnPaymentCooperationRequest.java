head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.08.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOnPaymentCooperationRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���A�g���N�G�X�g(WEB3AioOnPaymentCooperationRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 ���r (���u) �V�K�쐬
*/
package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�o���A�g���N�G�X�g)<BR>
 * �o���A�g���N�G�X�g�N���X
 * @@author ���r
 * @@version 1.0
 */
public class WEB3AioOnPaymentCooperationRequest extends WEB3BackRequest
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOnPaymentCooperationRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_on_payment_cooperation";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511161604L;
    
    /**
     * �،���ЃR�[�h<BR>
     */
    public String institutionCode;
    
    /**
     * �f�t�H���g�R���X�g���N<BR>
     */
	public WEB3AioOnPaymentCooperationRequest() {

	}
    /**
    * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>  
    * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>  
    * <BR>
    * �P�j�،���ЃR�[�h�̃`�F�b�N<BR>
    *  this.�،���ЃR�[�h��null �̏ꍇ�A<BR>  
    * �u�،���ЃR�[�h�̎w��Ȃ��v�̗�O��throw����B<BR>
    * <BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00827<BR> 
    * <BR>
    */    
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        // this.�،���ЃR�[�h��null �̏ꍇ
        // �u�،���ЃR�[�h�̎w��Ȃ��v�̗�O��throw����B
        if(WEB3StringTypeUtility.isEmpty(this.institutionCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�،���ЃR�[�h = null");
        }
        
        log.exiting(STR_METHOD_NAME);
    }   
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB330258
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3AioOnPaymentCooperationResponse(this);
    }    
    
}
@
