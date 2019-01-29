head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPODemandCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�\�����ʃ��N�G�X�g(WEB3IPODemandCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * IPO�\�����ʃ��N�G�X�g
 *                                                                
 * @@author ����
 * @@version 1.0
 */
public class WEB3IPODemandCommonRequest extends WEB3GenRequest 
{
    /**
    * ���O���[�e�B���e�B<BR>
    */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IPODemandCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_demandCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408112004L;
    
    /**
     * �\������
     */
    public String demandQuantity;
    
    /**
     * �\�����i�敪<BR>
     * �@@0�F���s<BR>
     * �@@1�F�w�l
     */
    public String demandPriceDiv;
    
    /**
     * �\�����i
     */
    public String demandPrice;
    
    /**
     * @@roseuid 4112E79E017C
     */
    public WEB3IPODemandCommonRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�\�����i�̃`�F�b�N<BR>
     * �@@this.�\�����i�����l�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00491<BR>
     * <BR>
     * �Q�j�@@�\�����i�敪�̃`�F�b�N<BR>
     * �@@this.�\�����i�敪 == �h���s�h &&<BR>
     * �@@this.�\�����i�ɓ��͂�����i0�܂���null�ȊO�j�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00492<BR>
     * <BR>
     * �@@this.�\�����i�敪 == �h�w�l�h &&<BR>
     * �@@this.�\�����i�������́i0�܂���null�j�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00493<BR>
     * <BR>
     * �R�j�@@�\�����ʂ̃`�F�b�N<BR>
     * �@@this.�\�����ʂ����l�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00494<BR>
     * @@roseuid 40D7CEA10078
     */
    public void validate() throws WEB3BaseException
    {
        String STR_METHOD_NAME = " validate()";
        
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�\�����i�̃`�F�b�N
        log.debug("�P�j�@@�\�����i�̃`�F�b�N: ENTER");
        if (this.demandPrice != null)
        {
            if(!WEB3StringTypeUtility.isNumber(this.demandPrice))
            {
                //this.�\�����i�����l�łȂ��ꍇ�A��O���X���[����B
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00491,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }
        log.debug("�P�j�@@�\�����i�̃`�F�b�N: END");
        
        //�Q�j�@@�\�����i�敪�̃`�F�b�N
        log.debug("�Q�j-1�@@�\�����i�敪�̃`�F�b�N: ENTER");
        if(WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.demandPriceDiv) && this.demandPrice != null && !"0".equals(this.demandPrice))
        {
            //�\�����i�敪 == �h���s�h && �\�����i�ɓ��͂�����i0�܂���null�ȊO�j�̏ꍇ
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00492,
                getClass().getName() + STR_METHOD_NAME);
        }
        log.debug("�Q�j-1�@@�\�����i�敪�̃`�F�b�N: END");

        log.debug("�Q�j-2�@@�\�����i�敪�̃`�F�b�N: ENTER");
        if(WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.demandPriceDiv) && (this.demandPrice == null || "0".equals(this.demandPrice)))
        {
            //�\�����i�敪 == �h�w�l�h && �\�����i�������́i0�܂���null�j�̏ꍇ
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00493,
                getClass().getName() + STR_METHOD_NAME);         
        }
        log.debug("�Q�j-2�@@�\�����i�敪�̃`�F�b�N: END");
        
        //�R�j�@@�\�����ʂ̃`�F�b�N
        log.debug("�R�j�@@�\�����ʂ̃`�F�b�N: ENTER");
        if(!WEB3StringTypeUtility.isNumber(demandQuantity))
        {
            //�\�����ʂ����l�łȂ��ꍇ
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00494,
                getClass().getName() + STR_METHOD_NAME);   
        }
        log.debug("�R�j�@@�\�����ʂ̃`�F�b�N: END");
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E79E019A
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPODemandCommonResponse(this);
    }
}
@
