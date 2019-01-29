head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOpenMarginCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����V�K�������������N�G�X�g(WEB3MarginOpenMarginCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ������ (���u) �V�K�쐬
                   2004/12/06 �����a��(SRA) �c�Č��Ή� �m��.�P�U�W
                   2004/12/21 �����a��(SRA) JavaDoc�C��
                   2006/12/25 �����F (���u) ���f�� 1086
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.equity.define.WEB3MarginTradeTypeDef;

import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p����V�K�������������N�G�X�g�j�B<br>
 * <br>
 * �M�p����V�K�������������N�G�X�g�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginOpenMarginCompleteRequest extends WEB3MarginCommonRequest 
{
    /**
     * <p>�i���O�o�̓��[�e�B���e�B�j�B</p>
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginOpenMarginCompleteRequest.class);
    
    /**
     * <p>�iPTYPE�j�B</p>
     */
    public static final String PTYPE = "margin_openMarginComplete";

    /**
     * <p>�iSerialVersionUID�j�B</p>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * <p>�i����ID�j�B</p>
     * <p>����ID</p>
     */
    public String orderId;
    
    /**
     * <p>�i�����R�[�h�j�B</p>
     * <p>�����R�[�h</p>
     */
    public String productCode;
    
    /**
     * <p>�i�s��R�[�h�j�B</p>
     * <p>�s��R�[�h</p>
     */
    public String marketCode;
    
    /**
     * <p>�i�����敪�j�B</p>
     * <p>0�F��ʁ@@1�F����</p>
     */
    public String taxType;
    
    /**
     * <p>�i����敪�j�B</p>
     * <p>����敪<br>
     * <br>
     * 3�F�V�K���������@@4�F�V�K��������<br>
     * �iOrderTypeEnum�ɂĒ�`�j</p>
     */
    public String tradingType;
    
    /**
     * <p>�i�ٍρj�B</p>
     * <p>�ٍ�
     */
    public WEB3MarginRepaymentUnit repayment;
    
    /**
     * <p>�i�m�F���P���j�B</p>
     * <p>�m�F���P��<br>
     * <br>
     * �m�F���X�|���X�ő������l�B</p>
     */
    public String checkPrice;
    
    /**
     * <p>�i�m�F���������j�B</p>
     * <p>�m�F��������<br>
     * <br>
     * �m�F���X�|���X�ő������l�B</p>
     */
    public Date checkDate;
    
    /**
     * <p>�i�Ïؔԍ��j�B</p>
     * <p>�Ïؔԍ�</p>
     */
    public String password;
    
    /**
     * <p>�i�M�p����V�K�������������N�G�X�g�j�B</p>
     * <p>�R���X�g���N�^�B</p>
     */
    public WEB3MarginOpenMarginCompleteRequest() 
    {
     
    }
    
    /**
     * <p>�ivalidate�j�B</p>
     * <p>�P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo���B<br>
     * <br>
     * �Q�j�@@����敪�`�F�b�N<br>
     * �@@�Q�|�P�jthis.����敪��null�ł���΁u����敪��null�v�̗�O���X���[����B<br>
     *   class: WEB3BusinessLayerException<br>
     *   tag:    BUSINESS_ERROR_00601<br>
     * <br>
     * �@@�Q�|�Q�jthis.����敪�����L�ȊO�̒l�̏ꍇ�A<br>
     * �@@�@@�@@�@@�@@�u����敪������`�̒l�v�̗�O���X���[����B<br>
     * �@@�@@�@@�@@�E�h3�F�V�K���������h<br>
     * �@@�@@�@@�@@�E�h4�F�V�K���������h<br>
     *   class: WEB3BusinessLayerException<br>
     *   tag:    BUSINESS_ERROR_00602<br>
     * <br>
     * �R�j�@@�����R�[�h�`�F�b�N<br>
     * �@@�R�|�P�jthis.�����R�[�h��null�ł���΁u�����R�[�h��null�v<br>
     *   �̗�O���X���[����B<br>
     *   class: WEB3BusinessLayerException<br>
     *   tag:    BUSINESS_ERROR_00079<br>
     * �S�j�@@�s��R�[�h�`�F�b�N<br>
     * �@@�S�|�P�jthis.�s��R�[�h��null�ł���΁u�s��R�[�h��null�v<br>
     *   �̗�O���X���[����B<br>
     *   class: WEB3BusinessLayerException<br>
     *   tag:    BUSINESS_ERROR_00443<br>
     * <br>
     * �@@�S�|�Q)this.�s��R�[�h�����L�ȊO�̒l�̏ꍇ�A<br>
     * �@@�@@�@@�@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B<br>
     *          �E�h1�F�����h<br>
     *          �E�h2�F���h <br>
     *          �E�h3�F���É��h <br>
     *          �E�h6�F�����h <br>
     *          �E�h8�F�D�y�h <br>
     *          �E�h9�FNNM�h <br>
     *          �E�h10�FJASDAQ�h<br>
     *          �E�h99�F�D��s��h<BR>
     *   class: WEB3BusinessLayerException<br>
     *   tag:    BUSINESS_ERROR_00608<BR>
     * <br>
     * �T�j�@@�ٍσ`�F�b�N<br>
     * �@@�T�|�P�jthis.�ٍρ�null�ł���΁u�ٍς�null�v�̗�O���X���[����B<br>
     *   class: WEB3BusinessLayerException<br>
     *   tag:    BUSINESS_ERROR_00603<br>
     * <br>
     * �@@�T�|�Q�jthis.�ٍς̐M�p����ٍ�.validate���\�b�h���Ăяo���B<br>
     * <br>
     * �U�j�@@�����敪�`�F�b�N<br>
     * �@@�U�|�P�jthis.�����敪��null�ł���΁u�����敪��null�v�̗�O���X���[����B<br>
     *   class: WEB3BusinessLayerException<br>
     *   tag:    BUSINESS_ERROR_00604<br>
     * <br>
     * �@@�U�|�Q�jthis.�����敪���ȉ��̒l�ȊO�̏ꍇ�A<br>
     * �@@�@@�@@�@@�@@�u�����敪������`�̒l�v�̗�O���X���[����B<br>
     * �@@�@@�@@�@@�E�h0�F��ʁh<br>
     * �@@�@@�@@�@@�E�h1�F����h<br>
     *   class: WEB3BusinessLayerException<br>
     *   tag:    BUSINESS_ERROR_00605<br>
     * <br>
     * �V�j�@@�m�F���P���`�F�b�N�@@�i����ID��null�̏ꍇ�̂ݎ��{����j <br>
     * �@@�V�|�P�jthis.�m�F���P����null�ł���΁u�m�F���P����null�v��<br>
     * �@@�@@�@@�@@��O���X���[����B<br>
     *   class: WEB3BusinessLayerException<br>
     *   tag:    BUSINESS_ERROR_00206<br>
     * <br>
     * �W�j�@@�m�F���������`�F�b�N�@@�i����ID��null�̏ꍇ�̂ݎ��{����j<br>
     * �@@�W�|�P�jthis.�m�F����������null�ł���΁u�m�F����������null�v��<br>
     * �@@�@@�@@�@@��O���X���[����B<br>
     *   class: WEB3BusinessLayerException<br>
     *   tag:    BUSINESS_ERROR_00078<br>
     * <br>
     * �X�j�@@���������`�F�b�N<br>
     * �@@�X�|�P�jthis.����������null�ł���΁A�u�������������w��v�̗�O���X���[����B<br>
     *   class: WEB3BusinessLayerException<br>
     *   tag:    BUSINESS_ERROR_00126</p>
     * 
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "������ WEB3MarginOpenMarginCompleteRequest: validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("�X�[�p�[�N���X��validate���\�b�h���Ăяo��");
        // �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo���B<br>
        super.validate();

        log.debug("����敪�`�F�b�N");
        // �Q�j�@@����敪�`�F�b�N<br>
        // �@@�Q�|�P�jthis.����敪��null�ł���΁u����敪��null�v�̗�O���X���[����B<br>
        //   class: WEB3BusinessLayerException<br>
        //   tag:    BUSINESS_ERROR_00601<br>
        if (this.tradingType == null)
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00601,
            this.getClass().getName() + "validate");
        }

        // �@@�Q�|�Q�jthis.����敪�����L�ȊO�̒l�̏ꍇ�A<br>
        // �@@�@@�@@�@@�@@�u����敪������`�̒l�v�̗�O���X���[����B<br>
        // �@@�@@�@@�@@�E�h3�F�V�K���������h<br>
        // �@@�@@�@@�@@�E�h4�F�V�K���������h<br>
        //   class: WEB3BusinessLayerException<br>
        //   tag:    BUSINESS_ERROR_00602<br>
        if (!WEB3MarginTradeTypeDef.OPEN_LONG_MARGIN.equals(this.tradingType)
                && !WEB3MarginTradeTypeDef.OPEN_SHORT_MARGIN.equals(this.tradingType))
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00602,
            this.getClass().getName() + "validate");
        }

        log.debug("�����R�[�h�`�F�b�N");
        // �R�j�@@�����R�[�h�`�F�b�N<br>
        // �@@�R�|�P�jthis.�����R�[�h��null�ł���΁u�����R�[�h��null�v<br>
        //   �̗�O���X���[����B<br>
        //   class: WEB3BusinessLayerException<br>
        //   tag:    BUSINESS_ERROR_00079<br>
        if (this.productCode == null)
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00079,
            this.getClass().getName() + "validate");
        }
        
        log.debug("�s��R�[�h�`�F�b�N");
        // �S�j�@@�s��R�[�h�`�F�b�N<br>
        // �@@�S�|�P�jthis.�s��R�[�h��null�ł���΁u�s��R�[�h��null�v<br>
        //   �̗�O���X���[����B<br>
        //   class: WEB3BusinessLayerException<br>
        //   tag:    BUSINESS_ERROR_00443<br>
        if (this.marketCode == null)
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00443,
            this.getClass().getName() + "validate");
        }

        // �@@�S�|�Q)this.�s��R�[�h�����L�ȊO�̒l�̏ꍇ�A<br>
        // �@@�@@�@@�@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B<br>
        //          �E�h1�F�����h<br>
        //          �E�h2�F���h <br>
        //          �E�h3�F���É��h <br>
        //          �E�h6�F�����h <br>
        //          �E�h8�F�D�y�h <br>
        //          �E�h9�FNNM�h <br>
        //          �E�h10�FJASDAQ�h<br>
        //          �E�h99�F�D��s��h<BR>
        //   class: WEB3BusinessLayerException<br>
        //   tag:    BUSINESS_ERROR_00608<br>
        if (!WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
                && !WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
                && !WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
                && !WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
                && !WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
                && !WEB3MarketCodeDef.NNM.equals(this.marketCode)
                && !WEB3MarketCodeDef.JASDAQ.equals(this.marketCode)
                && !WEB3MarketCodeDef.PRIORITY_MARKET.equals(this.marketCode))
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00608,
            this.getClass().getName() + "validate");
        }

        log.debug("�ٍσ`�F�b�N");
        // �T�j�@@�ٍσ`�F�b�N<br>
        // �@@�T�|�P�jthis.�ٍρ�null�ł���΁u�ٍς�null�v�̗�O���X���[����B<br>
        //   class: WEB3BusinessLayerException<br>
        //   tag:    BUSINESS_ERROR_00603<br>
        if (this.repayment == null)
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00603,
            this.getClass().getName() + "validate");
        }

        // �@@�T�|�Q�jthis.�ٍς̐M�p����ٍ�.validate���\�b�h���Ăяo���B<br>
        this.repayment.validate();

        log.debug("�����敪�`�F�b�N");
        // �U�j�@@�����敪�`�F�b�N<br>
        // �@@�U�|�P�jthis.�����敪��null�ł���΁u�����敪��null�v�̗�O���X���[����B<br>
        //   class: WEB3BusinessLayerException<br>
        //   tag:    BUSINESS_ERROR_00604<br>
        if (this.taxType == null)
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00604,
            this.getClass().getName() + "validate");
        }
        
        // �@@�U�|�Q�jthis.�����敪���ȉ��̒l�ȊO�̏ꍇ�A<br>
        // �@@�@@�@@�@@�@@�u�����敪������`�̒l�v�̗�O���X���[����B<br>
        // �@@�@@�@@�@@�E�h0�F��ʁh<br>
        // �@@�@@�@@�@@�E�h1�F����h<br>
        //   class: WEB3BusinessLayerException<br>
        //   tag:    BUSINESS_ERROR_00605<br>
        if (!WEB3TaxTypeSpecialDef.NORMAL.equals(this.taxType)
                && !WEB3TaxTypeSpecialDef.SPECIAL.equals(this.taxType))
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00605,
            this.getClass().getName() + "validate");
        }

        if (this.orderId != null)
        {
            log.debug("�m�F���P���`�F�b�N");
            // �V�j�@@�m�F���P���`�F�b�N<br>
            // �@@�V�|�P�jthis.�m�F���P����null�ł���΁u�m�F���P����null�v��<br>
            // �@@�@@�@@�@@��O���X���[����B<br>
            //   class: WEB3BusinessLayerException<br>
            //   tag:    BUSINESS_ERROR_00206<br>
            if (this.checkPrice == null)
            {
                //��O
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00206,
                this.getClass().getName() + "validate");
            }
    
            log.debug("�m�F���������`�F�b�N");
            // �W�j�@@�m�F���������`�F�b�N<br>
            // �@@�W�|�P�jthis.�m�F����������null�ł���΁u�m�F����������null�v��<br>
            // �@@�@@�@@�@@��O���X���[����B<br>
            //   class: WEB3BusinessLayerException<br>
            //   tag:    BUSINESS_ERROR_00078<br>
            if (this.checkDate == null)
            {
                //��O
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "validate");
            }
        }
        
		log.debug("���������`�F�b�N�`�F�b�N");
		// �X�j�@@���������`�F�b�N<br>
		// �@@�X�|�P�jthis.����������null�ł���΁A�u�������������w��v�̗�O���X���[����B<br>
		//   class: WEB3BusinessLayerException<br>
		//   tag:    BUSINESS_ERROR_00126</p>
		if (this.orderQuantity == null)
		{
			//��O
			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00126,
			this.getClass().getName() + "validate");
		}
		
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * <p>�icreate���X�|���X�j�B</p>
     * <p>�M�p����V�K�������������X�|���X�𐶐����ĕԂ��B</p>
     * @@return �M�p����V�K�������������X�|���X
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginOpenMarginCompleteResponse(this);
    }    
}
@
