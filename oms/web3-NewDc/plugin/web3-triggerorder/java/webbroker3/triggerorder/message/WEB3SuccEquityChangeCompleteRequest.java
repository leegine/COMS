head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.47.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccEquityChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�����������������������N�G�X�g(WEB3SuccEquityChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/7  鰊](���u) �V�K�쐬
                 : 2007/01/10 ���G��(���u) ���f��214
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3EquityChangeCompleteRequest;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�i�A���j�����������������������N�G�X�g)<BR>
 * �i�A���j�����������������������N�G�X�g�B<BR>
 * 
 * @@ author 鰊] <BR>
 * @@ version 1.0 <BR>
 */
public class WEB3SuccEquityChangeCompleteRequest extends WEB3EquityChangeCompleteRequest 
{
    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccEquityChangeCompleteRequest.class); 

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_equityChangeComplete";
    
    /**
     * (�m�F���̊T�Z��n���)<BR>
     * �m�F���̊T�Z��n���<BR>
     */
    public String estimatedPrice;
    
    /**
     * (�P�������l���)<BR>
     * �P�������l���B<BR>
     * �}�w�l���w�肳�ꂽ�ꍇ�̂݃Z�b�g�B<BR>
     */
    public WEB3SuccPriceAdjustmentValueInfo priceAdjustmentValueInfo;

    /**
     * (������P��)<BR>
     * ������P���B<BR>
     * �}�w�l���w�肳�ꂽ�ꍇ�̂݃Z�b�g�B<BR>
     */
    public String afterAdjustmentPrice;

    /**
     * @@roseuid 4369CC82002E
     */
    public WEB3SuccEquityChangeCompleteRequest() 
    {
     
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40602AEA033F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccEquityChangeCompleteResponse(this);
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@super.validate()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�m�F���T�Z��n����`�F�b�N<BR>   
     * �@@this.�m�F���T�Z��n����̒l���ȉ��̂����ꂩ�ɊY������ꍇ�́A <BR>
     * �@@��O���X���[����B <BR>
     * <BR>
     * �@@  �Enull <BR>
     * �@@�@@�E�����ȊO <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02292<BR>
     * <BR>
     * �R�j�@@�A�������P�������l���`�F�b�N<BR>
     * �@@�R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v��<BR>
     * �@@�@@�@@�@@�@@�@@��O��throw����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_02254<BR>
     * <BR>
     * �S�j�@@�A�������E���������`�F�b�N<BR>
     * �@@super.validate�A������()���R�[������B<BR>
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4337BF4A00ED
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME ="validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@super.validate()���R�[������B
        super.validate();
                
        // �Q�j�@@�m�F���T�Z��n����`�F�b�N
        if ((this.estimatedPrice == null) || (!WEB3StringTypeUtility.isInteger(this.estimatedPrice)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02292,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �R�j�@@�A�������P�������l���`�F�b�N
            // �R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
        if (this.priceAdjustmentValueInfo != null)
        {
            this.priceAdjustmentValueInfo.validate();
            // �R�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A�����P���敪��"���s"�̏ꍇ
            if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02254,
                this.getClass().getName() + "." + STR_METHOD_NAME);                        
            }
        }
        
        // �S�j�@@�A�������E���������`�F�b�N
        super.validateSuccOrder();
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
