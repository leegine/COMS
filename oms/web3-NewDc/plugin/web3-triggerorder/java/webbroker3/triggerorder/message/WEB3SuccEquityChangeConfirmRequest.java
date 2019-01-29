head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.40.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccEquityChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�����������������m�F���N�G�X�g(WEB3SuccEquityChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/7  鰊](���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3EquityChangeConfirmRequest;
import webbroker3.util.WEB3LogUtility;


/**
 * (�i�A���j�����������������m�F���N�G�X�g)<BR>
 * �i�A���j�����������������m�F���N�G�X�g�B<BR>
 * 
 * @@ author 鰊] <BR>
 * @@ version 1.0 <BR>
 */
public class WEB3SuccEquityChangeConfirmRequest extends WEB3EquityChangeConfirmRequest 
{
    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccEquityChangeConfirmRequest.class); 

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_equityChangeConfirm";
    
    /**
     * (�P�������l���)<BR>
     * �P�������l���B<BR>
     * �}�w�l���w�肳�ꂽ�ꍇ�̂݃Z�b�g�B<BR>
     */
    public WEB3SuccPriceAdjustmentValueInfo priceAdjustmentValueInfo;
    
    /**
     * @@roseuid 4369CC820399
     */
    public WEB3SuccEquityChangeConfirmRequest() 
    {
     
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40602AEA033F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccEquityChangeConfirmResponse(this);
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@super.validate()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�A�������P�������l���`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�A�������P�������l���null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v��<BR>
     * �@@�@@�@@�@@�@@�@@��O��throw����B<BR>
     *              class: WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_02254<BR>
     * <BR>
     * �R�j�@@�A�������E���������`�F�b�N<BR>
     * �@@super.validate�A������()���R�[������B<BR>
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4337BEBE016A
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME ="validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@super.validate()���R�[������B
        super.validate();
        
        // �Q�j�@@�A�������P�������l���`�F�b�N
            // �Q�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
        if (this.priceAdjustmentValueInfo != null)
        {
            this.priceAdjustmentValueInfo.validate();
            // �Q�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A�����P���敪��"���s"�̏ꍇ
            if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02254,
                this.getClass().getName() + "." + STR_METHOD_NAME);                        
            }
        }
        
        // �R�j�@@�A�������E���������`�F�b�N
        super.validateSuccOrder();        
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
