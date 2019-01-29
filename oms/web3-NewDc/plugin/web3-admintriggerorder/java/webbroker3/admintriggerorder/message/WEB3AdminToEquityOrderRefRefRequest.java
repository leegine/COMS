head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToEquityOrderRefRefRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE���������Ɖ�N�G�X�g(WEB3AdminToEquityOrderRefRefRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/03�@@鰐V(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.admintriggerorder.define.WEB3AdminToEquityKeyItemDef;

/**
 * (�g���K�[�����Ǘ��ҁE���������Ɖ�N�G�X�g)<BR>
 * 
 * @@author 鰐V<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminToEquityOrderRefRefRequest extends WEB3AdminToOrderRefRefCommonRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToEquityOrderRefRefRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_equity_order_ref_ref";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603031700L;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * <BR>
     */
    public String productCode;
    
    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F1B3C80261
     */
    public WEB3AdminToEquityOrderRefRefRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate()���R�[������B  <BR>
     * <BR>
     * �Q�j�@@�����R�[�h�`�F�b�N  <BR>
     * �@@this.�����R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �@@�Q�|�P�jthis.�����R�[�h���ȉ��̏����ɊY������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E�����R�[�h != ���� <BR>
     * �@@�@@�@@�@@�E�����R�[�h.length != 5  <BR>
     * <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag  : BUSINESS_ERROR_01067<BR>
     * <BR>
     * �R�j�\�[�g�L�[�`�F�b�N <BR>
     *�@@�R�|�P�jthis.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B <BR>  
     *�@@�@@�R�|�P�|�P�j�\�[�g�L�[.�L�[���ڂɉ��L�̍��ڈȊO�� <BR>  
     *�@@�@@�@@�ݒ肳��Ă�����A <BR>
     *�@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR>
     *�@@�@@�@@�@@�E�u���X�R�[�h�v <BR>
     *�@@�@@�@@�@@�E�u�ڋq�R�[�h�v <BR>
     *�@@�@@�@@�@@�E�u�����R�[�h�v <BR>
     *�@@�@@�@@�@@�E�u�s��R�[�h�v <BR>
     *�@@�@@�@@�@@�E�u�����敪�v <BR>
     *�@@�@@�@@�@@�E�u����敪�v <BR>
     *�@@�@@�@@�@@�E�u�ٍρv <BR>
     *�@@�@@�@@�@@�E�u�l�i�����v <BR>
     *�@@�@@�@@�@@�E�u���s�����v <BR>
     *�@@�@@�@@�@@�E�u�����L�������v <BR>
     *�@@�@@�@@�@@�E�u�������ԁv <BR>
     *�@@�@@�@@�@@�E�u�������v <BR>
     *�@@�@@�@@�@@�E�u��n���v <BR>
     *<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag  : BUSINESS_ERROR_00086<BR>
     *<BR>       
     * @@throws WEB3BaseException
     * @@roseuid 43DF1E9E0073
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�X�[�p�[�N���X��validate()���R�[������B
        super.validate();
        //�Q�j�@@�����R�[�h�`�F�b�N
        if(this.productCode != null)
        {
            if(!WEB3StringTypeUtility.isNumber(this.productCode) || this.productCode.length() != 5)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    "�����R�[�h�̓��͂��s���ł��B");  
            }
        }
        // �R�j�@@�\�[�g�L�[�`�F�b�N 
        //�@@�R�|�P�jthis.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B 
        //�@@�@@�R�|�P�|�P�j�\�[�g�L�[.�L�[���ڂɉ��L�̍��ڈȊO��
        //�@@�@@�@@�ݒ肳��Ă�����A 
        //�@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B
        //�@@�@@�@@�@@�E�u���X�R�[�h�v
        //�@@�@@�@@�@@�E�u�ڋq�R�[�h�v
        //�@@�@@�@@�@@�E�u�����R�[�h�v
        //�@@�@@�@@�@@�E�u�s��R�[�h�v
        //�@@�@@�@@�@@�E�u�����敪�v
        //�@@�@@�@@�@@�E�u����敪�v
        //�@@�@@�@@�@@�E�u�ٍρv
        //�@@�@@�@@�@@�E�u�l�i�����v
        //�@@�@@�@@�@@�E�u���s�����v
        //�@@�@@�@@�@@�E�u�����L�������v
        //�@@�@@�@@�@@�E�u�������ԁv
        //�@@�@@�@@�@@�E�u�������v
        //�@@�@@�@@�@@�E�u��n���v
        int l_intLen = this.sortKeys.length;
        for (int i = 0; i < l_intLen; i++)
        {
            if (!(WEB3AdminToEquityKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToEquityKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToEquityKeyItemDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToEquityKeyItemDef.MARKET_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToEquityKeyItemDef.TAX_TYPE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToEquityKeyItemDef.TRADING_TYPE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToEquityKeyItemDef.REPAYMENT_TYPE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToEquityKeyItemDef.PRICE_CONDITION_TYPE.equals(this.sortKeys[i].keyItem)                
                || WEB3AdminToEquityKeyItemDef.EXEC_COND_TYPE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToEquityKeyItemDef.EXPIRATION_DATE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToEquityKeyItemDef.ORDER_DATE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToEquityKeyItemDef.ORDER_BIZ_DATE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToEquityKeyItemDef.DELIVERY_DATE.equals(this.sortKeys[i].keyItem)))
            {
                log.debug("�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }         
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createResponse�̎���)<BR>
     * <BR>
     * �g���K�[�����Ǘ��ҁE���������Ɖ�X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 41E78F6401A5
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminToEquityOrderRefRefResponse(this);
    }
}
@
