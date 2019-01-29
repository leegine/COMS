head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBuyInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������t�������̓��N�G�X�g(WEB3EquityOrderBuyInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/04/19 �R�w�� (���u) �V�K�쐬
Revesion History : 2004/12/08 �����iSAR�j�c�Č��Ή� �m��.�O�T�V
Revesion History : 2004/12/13 �K�� (SRA) �c�Č��Ή� No.385
Revesion History : 2006/12/25 �đo�g(���u) ���f��No.1086
Revesion History : 2007/12/17 ���n(���u) ���f��No.1210
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.define.WEB3TradingTypeDef;

/**
 * �i�����������t�������̓��N�G�X�g�j�B<BR>
 * <BR>
 * �����������t�������͗v���@@���N�G�X�g�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquityBuyInputRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityBuyInputRequest.class);

    /**
     * �����R�[�h <BR>
     * ���ڎw��̏ꍇ�ɕK�v<BR>
     */
    public String productCode;

    /**
     * �s��R�[�h <BR>
     * 1:�����@@2:���@@3:���É��@@6:�����@@8:�D�y�@@9:NNM�@@10:JASDAQ<BR>
     * ���ڎw��̏ꍇ�ɕK�v<BR>
     */
    public String marketCode;
    
    /**
     * (����敪)<BR>
     * 1�F�����������@@99�F����O����<BR>
     */
    public String tradingType;

    /**
     * �|�������t�B�b�N�^�C�v�B<BR>
     */
    public static final String PTYPE = "equity_order_buy_input";

    /**
     * �V���A���o�[�W����UID <BR>
     */
    public static final long serialVersionUId = 200405081330L;

    /**
     * �R���X�g���N�^ <BR>
     * @@roseuid 406118A90154
     */
    public WEB3EquityBuyInputRequest()
    {

    }

    /**
     * ���X�|���X�f�[�^���쐬����B <BR>
     * @@return webbroker3.equity.message.WEB3EquityOrderBuyInputResponse
     * @@roseuid 40602B2B00BE
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityBuyInputResponse(this);
    }
    
	/**
	 * (validate)<BR>
	 * <BR>
	 * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
	 * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
	 * <BR>
	 * �P�j�@@�s��R�[�h�`�F�b�N<BR>
	 * �@@�@@�@@this.�s��R�[�h��null�A<BR>
	 * �@@�@@�@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A<BR>
	 * �@@�@@�@@�@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B<BR>
	 * �@@�@@�@@�@@�E�P�F����<BR>
	 * �@@�@@�@@�@@�E�Q�F���<BR>
	 * �@@�@@�@@�@@�E�R�F���É�<BR>
	 * �@@�@@�@@�@@�E�U�F����<BR>
	 * �@@�@@�@@�@@�E�W�F�D�y<BR>
	 * �@@�@@�@@�@@�E�X�FNNM<BR>
	 * �@@�@@�@@�@@�E�P�O�FJASDAQ<BR>
     * �@@�@@�@@�@@�E�P�P�FJNX-PTS<BR>
     * �@@�@@�@@�@@�E�X�X�F�D��s��<BR>
	 * �@@�@@�@@class : WEB3BusinessLayerException<BR>
	 * �@@�@@�@@tag   : BUSINESS_ERROR_00608<BR>
	 * 
	 * �Q�j�@@����敪�`�F�b�N<BR>
�@@	 * �Q-�P�jthis.����敪��null�̏ꍇ�A<BR>
     *�@@�@@�@@�u����敪��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00601<BR>
     *<BR>
	 * �Q-�Q�jthis.����敪��null�A<BR>
     *�@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�u����敪������`�̒l�v�̗�O���X���[����B<BR>
     *�@@�@@�E�P�F����������<BR>
     *�@@�@@�E�X�X�F����O����<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00602<BR>
     *<BR>
	 * �R�j�@@����O�����E�����R�[�h�w��`�F�b�N<BR>
     * �R-�P�jthis.����敪���h����O�����h�A<BR>
     *�@@�@@�@@���� this.�����R�[�h��null�̏ꍇ�́A<BR>
     *�@@�@@�@@�u����O�����Ŗ����R�[�h�w��Ȃ��v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01377<BR>
     *<BR>
	 *
	 * �S�j�@@����O�����E�s��R�[�h�w��`�F�b�N<BR>
     * �S-�P�jthis.����敪���h����O�����h�A<BR>
     *�@@�@@�@@���� this.�s��R�[�h��null�̏ꍇ�́A<BR>
     *�@@�@@�@@�u����O�����Ŏs��R�[�h�w��Ȃ��v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01378<BR>
     *<BR>
	 * 
	 * @@throws WEB3BusinessLayerException
	 */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME ="validate()";
        log.entering(STR_METHOD_NAME);
        
        if(this.marketCode != null)
        {
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
        }
		//�Q�j�@@����敪�`�F�b�N<BR>
        //�Q-�P�jthis.����敪��null�̏ꍇ�A<BR>
        //�@@�@@�u����敪��null�v�̗�O���X���[����B<BR>
		//�@@�@@�@@class : WEB3BusinessLayerException<BR>
		// �@@�@@�@@tag   : BUSINESS_ERROR_00601<BR>
		
		log.debug("����敪�`�F�b�N");
        if(this.tradingType == null)
        {
			//��O
			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00601,
			this.getClass().getName() + "validate");
        }

		//�Q-�Q�jthis.����敪��null�A<BR>
		//�@@�@@�����L�̒l�ȊO�̏ꍇ�A<BR>
		// �@@�@@�@@�u����敪������`�̒l�v�̗�O���X���[����B<BR>
		//�@@�@@�E�P�F����������<BR>
		//  �@@�E�X�X�F����O����<BR>
		//�@@�@@�@@class : WEB3BusinessLayerException<BR>
		// �@@�@@�@@tag   : BUSINESS_ERROR_602<BR>
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
		        	        	        	
		
		// �R�j�@@����O�����E�����R�[�h�w��`�F�b�N<BR>
		// �R-�P�jthis.����敪���h����O�����h�A<BR>
		//�@@�@@�@@���� this.�����R�[�h��null�̏ꍇ�́A<BR>
		//�@@�@@�@@�u����O�����Ŗ����R�[�h�w��Ȃ��v�̗�O���X���[����B<BR>
		//�@@�@@�@@class : WEB3BusinessLayerException<BR>
		// �@@�@@�@@tag   : BUSINESS_ERROR_01377<BR>
		
		log.debug("����O�����E�����R�[�h�w��`�F�b�N");
		if (WEB3TradingTypeDef.PRESENCE_ORDER.equals(this.tradingType)
			&& this.productCode == null)
			{
				//
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01377,
				this.getClass().getName() + "validate");
			}
		
		// �S�j�@@����O�����E�s��R�[�h�w��`�F�b�N<BR>
		// �S-�P�jthis.����敪���h����O�����h�A<BR>
		//�@@�@@�@@���� this.�s��R�[�h��null�̏ꍇ�́A<BR>
		//�@@�@@�u����O�����Ŏs��R�[�h�w��Ȃ��v�̗�O���X���[����B<BR>
		//�@@�@@�@@class : WEB3BusinessLayerException<BR>
		// �@@�@@�@@tag   : BUSINESS_ERROR_01378<BR>
		
		log.debug("����O�����E�s��R�[�h�w��`�F�b�N");
		if (WEB3TradingTypeDef.PRESENCE_ORDER.equals(this.tradingType)
			&& this.marketCode == null)
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01378,
				this.getClass().getName() + "validate");				
			}
        
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
