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
filename	WEB3EquityBuyConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������t�����m�F���N�G�X�g(WEB3EquityBuyConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 �^�� (���u) �V�K�쐬
Revesion History : 2004/12/08 �����iSAR�j�c�Č��Ή� �m��.�O�T�V���m��.�P�U�W
Revesion History : 2004/12/13 �K�� (SRA) �c�Č��Ή� No.385
Revesion History : 2006/12/25 �đo�g(���u) ���f��No.1086
Revesion History : 2007/12/17 ���n(���u) ���f��No.1205
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3OrderingConditionDef;



/**
 * �i�����������t�����m�F���N�G�X�g�j�B<BR>
 * <BR>
 * �����������t�����m�F�v���@@���N�G�X�g�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquityBuyConfirmRequest extends WEB3EquityCommonRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityBuyConfirmRequest.class);
        
    /**
     * PTYPE <BR>
     */
    public static final String PTYPE = "equity_buy_confirm";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200402041630L;

    /**
     * �i�����R�[�h�j<BR>
     * �����R�[�h<BR>
     */
    public String productCode;

    /**
     * �i�s��R�[�h�j<BR>
     * 1:�����@@2:���@@3:���É��@@6:�����@@8:�D�y�@@9:NNM�@@10:JASDAQ<BR>
     */
    public String marketCode;

    /**
     * �i�����敪�j<BR>
     * 0�F��ʁ@@�@@1�F����<BR>
     */
    public String taxType;
    
    /**
     * (����敪)<BR>
     * 1�F�����������@@99�F����O����<BR>
     */
    public String tradingType;

    /**
     * (�����������t�����m�F���N�G�X�g) <BR>
     * �R���X�g���N�^ <BR>
     * @@roseuid 406118700183
     */
    public WEB3EquityBuyConfirmRequest()
    {

    }

    /**
     * ���X�|���X�f�[�^���쐬����B
     * @@return WEB3EquityOrder
     * @@roseuid 40602AAF0071
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityBuyConfirmResponse(this);
    }
    
    /**
     * �ivalidate�j<BR>
     * <BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo���B<BR>
     * <BR>
     * �Q�j�@@�����R�[�h�`�F�b�N<BR>
     * �@@this.�����R�[�h��null�̏ꍇ�A<BR>
     * �@@�u�����R�[�h��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00079<BR>
     * <BR>
     * �R�j�@@�s��R�[�h�`�F�b�N<BR>
     * �@@�R�|�P�jthis.�s��R�[�h��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�s��R�[�h��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00443<BR>
     * <BR>
     * �@@�R�|�Q�jthis.�s��R�[�h��null�A<BR>
     * �@@�@@�@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E�P�F����<BR>
     * �@@�@@�@@�@@�E�Q�F���<BR>
     * �@@�@@�@@�@@�E�R�F���É�<BR>
     * �@@�@@�@@�@@�E�U�F����<BR>
     * �@@�@@�@@�@@�E�W�F�D�y<BR>
     * �@@�@@�@@�@@�E�X�FNNM<BR>
     * �@@�@@�@@�@@�E�P�O�FJASDAQ <BR>
     * �@@�@@�@@�@@�E�P�P�FJNX-PTS <BR>
     * �@@�@@�@@�@@�E�X�X�F�D��s��<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00608<BR>
     * <BR>
     * �S�j�@@�����敪�`�F�b�N<BR>
     * �@@�S�|�P�jthis.�����敪��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����敪��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00604<BR>
     * <BR>
     * �@@�S�|�Q�jthis.�����敪��null�A<BR>
     * �@@�@@�@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�u�����敪������`�̒l�v�̗�O���X���[����B<BR> 
     * �@@�@@�@@�@@�E�O�F���<BR>
     * �@@�@@�@@�@@�E�P�F����<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00605<BR>
     * <BR>
     * �T�j�@@����敪�`�F�b�N<BR>
     * �T�|�P�jthis.����敪��null�̏ꍇ�A<BR>
     *�@@�u����敪��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00601<BR>
     *<BR>
     * �T�|�Q�jthis.����敪��null�A<BR>
     *  �����L�̒l�ȊO�̏ꍇ�A<BR>
     * �u����敪������`�̒l�v�̗�O���X���[����B<BR>
     *�@@�E�P�F���������� <BR>
     *�@@�E�X�X�F����O���� <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00602<BR>
     *<BR>
     * �U�j�@@����O�����������e�`�F�b�N <BR>
     * this.����敪��"����O����"�̏ꍇ�̂݁A�ȉ��̃`�F�b�N���s���B<BR>
     *<BR>
     * �U�|�P�jsuper.�����P���敪��"�w�l"�̏ꍇ�A<BR>
     *�u����O�����͎w�l�̂ݎw��i���s�w��s�j�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01372<BR>
     *<BR>
     * �U�|�Q�jsuper.���������敪��"��������"�̏ꍇ�A<BR>
     *�u����O�����͏o����܂Œ����w��s�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01373<BR>
     *<BR>
     * �U�|�R�jsuper.���s������"������"�̏ꍇ�A <BR>
     * �u����O�����͎��s�����w��s�v�̗�O���X���[����B <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01374<BR>
     *<BR>
     * �U�|�S�jsuper.���������敪��"�w��Ȃ�"�̏ꍇ�A<BR>
     * �u����O�����͔��������w��s�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01375<BR>
     *<BR>
     * �U�|�T�jsuper.�l�i������"�w��Ȃ�"�̏ꍇ�A<BR> 
     * �u����O�����͒l�i�����w��s�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01376<BR>
     *<BR>
     * @@throws WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME ="validate()";
        log.entering(STR_METHOD_NAME);
        
        super.validate();
        
        if(this.productCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + ".validate()");
        }
        
        if(this.marketCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + ".validate()");
        }
        
        if(!(WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
            || WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
            || WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
            || WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
            || WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
            || WEB3MarketCodeDef.NNM.equals(this.marketCode)
            || WEB3MarketCodeDef.JASDAQ.equals(this.marketCode)
            || WEB3MarketCodeDef.PRIORITY_MARKET.equals(this.marketCode)
            || WEB3MarketCodeDef.JNX_PTS.equals(this.marketCode)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                this.getClass().getName() + ".validate()");
        }
        
        if(this.taxType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00604,
                this.getClass().getName() + ".validate()");
        }
        
        if(!(WEB3TaxTypeDef.NORMAL.equals(this.taxType)
            || WEB3TaxTypeDef.SPECIAL.equals(this.taxType)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00605,
                this.getClass().getName() + ".validate()");
        }
		//�T�j�@@����敪�`�F�b�N<BR>
		//�T�|�P�jthis.����敪��null�̏ꍇ�A<BR>
		//�@@�u����敪��null�v�̗�O���X���[����B<BR>
		//�@@�@@�@@class : WEB3BusinessLayerException<BR>
		// �@@�@@�@@tag   : BUSINESS_ERROR_00601<BR>
		
		log.debug("����敪�`�F�b�N");
		if(this.tradingType == null)
		{
			//��O
			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00601,
			this.getClass().getName() + "validate");
		}
				
		// �T�|�Q�jthis.����敪��null�A<BR>
		// �����L�̒l�ȊO�̏ꍇ�A<BR>
		//�u����敪������`�̒l�v�̗�O���X���[����B<BR>
		// �E�P�F���������� <BR>
		// �E�X�X�F����O���� <BR>
		//�@@�@@�@@class : WEB3BusinessLayerException<BR>
		// �@@�@@�@@tag   : BUSINESS_ERROR_00601<BR>
		
		if(this.tradingType != null)
		{
			if (!WEB3TradingTypeDef.BUY_ORDER.equals(this.tradingType)
				&& !WEB3TradingTypeDef.PRESENCE_ORDER.equals(this.tradingType))
			{
					//��O
					throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00602,
					this.getClass().getName() + "validate");
			}	
		}
		

		//�U�j�@@����O�����������e�`�F�b�N <BR>
		// this.����敪��"����O����"�̏ꍇ�̂݁A�ȉ��̃`�F�b�N���s���B<BR>
		log.debug("����敪�`�F�b�N");
		if(WEB3TradingTypeDef.PRESENCE_ORDER.equals(this.tradingType))
		{
			// �U�|�P�jsuper.�����P���敪��"�w�l"�̏ꍇ�A<BR>
			//�u����O�����͎w�l�̂ݎw��i���s�w��s�j�v�̗�O���X���[����B<BR>
			//�@@�@@�@@class : WEB3BusinessLayerException<BR>
			// �@@�@@�@@tag   : BUSINESS_ERROR_01372<BR>
			if(!WEB3OrderPriceDivDef.LIMIT_PRICE.equals(super.orderPriceDiv))
			{
				//��O
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01372,
				this.getClass().getName() + "validate");
			}
			
			// �U�|�Q�jsuper.���������敪��"��������"�̏ꍇ�A<BR>
			//�u����O�����͏o����܂Œ����w��s�v�̗�O���X���[����B<BR>
			//�@@�@@�@@class : WEB3BusinessLayerException<BR>
			// �@@�@@�@@tag   : BUSINESS_ERROR_01373<BR>
			if(!WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(super.expirationDateType))
			{
				//��O
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01373,
				this.getClass().getName() + "validate");
			}
			
			//�U�|�R�jsuper.���s������"������"�̏ꍇ�A <BR>
			//�u����O�����͎��s�����w��s�v�̗�O���X���[����B <BR>
			//�@@�@@�@@class : WEB3BusinessLayerException<BR>
			// �@@�@@�@@tag   : BUSINESS_ERROR_01374<BR>
			if(!WEB3ExecutionConditionDef.NO_CONDITION.equals(super.execCondType))
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01374,
				this.getClass().getName() + "validate");
			}

			//�U�|�S�jsuper.���������敪��"�w��Ȃ�"�̏ꍇ�A<BR>
			//�u����O�����͔��������w��s�v�̗�O���X���[����B<BR>
			//�@@�@@�@@class : WEB3BusinessLayerException<BR>
			// �@@�@@�@@tag   : BUSINESS_ERROR_01375<BR>
			if(!WEB3OrderingConditionDef.DEFAULT.equals(super.orderCondType))
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01375,
				this.getClass().getName() + "validate");
			}

			//�U�|�T�jsuper.�l�i������"�w��Ȃ�"�̏ꍇ�A<BR> 
			//�u����O�����͒l�i�����w��s�v�̗�O���X���[����B<BR>
			//�@@�@@�@@class : WEB3BusinessLayerException<BR>
			// �@@�@@�@@tag   : BUSINESS_ERROR_01376<BR>
			
			if(!WEB3OrderingConditionDef.DEFAULT.equals(super.priceCondType))
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01376,
				this.getClass().getName() + "validate");
			}
			
			
		}       

        log.exiting(STR_METHOD_NAME);
    }
}
@
