head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.43.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccMarginOpenChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p������������V�K���������N�G�X�g�N���X(WEB3SuccMarginOpenChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 �A����(���u) �V�K�쐬
                 : 2007/01/10 ���G��(���u) ���f��214
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeCompleteRequest;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���j�M�p������������V�K���������N�G�X�g�N���X)<BR>
 * �i�A���j�M�p������������V�K���������N�G�X�g�N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0 
 */
public class WEB3SuccMarginOpenChangeCompleteRequest extends WEB3MarginOpenMarginChangeCompleteRequest 
{
   
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccMarginOpenChangeCompleteRequest.class);
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_marginOpenChangeComplete";
    
    /**
     * (�m�F���T�Z��n���)<BR>
     * �m�F���T�Z��n���<BR>
     * <BR>
     * �m�F���X�|���X�ő��M�����l�B<BR>
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
     * @@roseuid 4369CC3B00DA
     */
    public WEB3SuccMarginOpenChangeCompleteRequest() 
    {
     
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40602AEA033F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccMarginOpenChangeCompleteResponse(this);
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
     * �@@�@@�E�O�ȉ��̐��� <BR>
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
     * @@throws WEB3BaseException
     * @@roseuid 433CEAD400F3
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@super.validate()���R�[������B
        super.validate();
        
        // �Q�j�@@�m�F���T�Z��n����`�F�b�N
        // �@@this.�m�F���T�Z��n����̒l���ȉ��̂����ꂩ�ɊY������ꍇ�́A
        if ((this.estimatedPrice == null) || (!WEB3StringTypeUtility.isInteger(this.estimatedPrice)) 
            || Integer.parseInt(this.estimatedPrice) <= 0)
        {
            log.debug("�m�F���T�Z��n����̒l���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02292,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�m�F���T�Z��n����̒l���s���ł��B");
        }
        
        // �R�j�@@�A�������P�������l���`�F�b�N
        // �@@�R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
        // �@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������B
        if (this.priceAdjustmentValueInfo != null)
        {
            this.priceAdjustmentValueInfo.validate();
            
            //  �R�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A
            //�@@�@@�@@�@@�@@�@@�����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v��
            // �@@�@@�@@�@@�@@�@@��O��throw����B
            if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv))
            {
                log.debug("�P�������l�ƒ����P���敪�̎w�肪�s�����ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02254,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�P�������l�ƒ����P���敪�̎w�肪�s�����ł��B");
            }
        }
        
        // �S�j�@@�A�������E���������`�F�b�N
        // �@@super.validate�A������()���R�[������B
        super.validateSuccOrder();
        log.exiting(STR_METHOD_NAME);
    }
}
@
