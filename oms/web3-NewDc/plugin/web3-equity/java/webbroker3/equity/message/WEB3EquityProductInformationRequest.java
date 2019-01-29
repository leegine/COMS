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
filename	WEB3EquityProductInformationRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������\�����N�G�X�g(WEB3EquityProductInfomationRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 SRA��� �V�K�쐬
Revesion History : 2007/12/17 ����(���u) ���f�� 1237
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����������\�����N�G�X�g�j�B<BR>
 * <BR>
 * �����������\���v���@@���N�G�X�g�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquityProductInformationRequest extends WEB3GenRequest
{

	/**
	 * ���O�o�̓��[�e�B���e�B�B
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3EquityProductInformationRequest.class);
        
	/**
	 * �����R�[�h
	 */
	public String productCode;

	/**
	 * �s��R�[�h
	 */
	public String marketCode;

	/**
	 * ������t���i
	 */
	public String orderCommodityCode;	

	/**
	 * serialVersionUID<BR>
	 */
	public static final long serialVersionUID = 200412171100L;

	/**
	 * PTYPE<BR>
	 */
	public static final String PTYPE = "equity_product_information";

	/**
	 * @@roseuid XXXXXXXXXXXX
	 */
	public WEB3EquityProductInformationRequest()
	{

	}

	/**
	 * ���X�|���X�f�[�^���쐬����B
	 * @@return WEB3EquityProductInfomationResponse
	 * @@roseuid XXXXXXXXXXXX
	 */
	public WEB3GenResponse createResponse()
	{
		return new WEB3EquityProductInformationResponse(this);
	}

	/**
	 * (validate)<BR>
	 * <BR>
	 * ���N���X�̐������`�F�b�N���s���B<BR>
	 * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
	 * <BR>
	 * �P�j�@@�����R�[�h�`�F�b�N<BR>
	 * �@@this.�\�[�g�L�[��null�̏ꍇ�A<BR>
	 * �@@�������I������B<BR>
	 * <BR>
	 * �Q�j�@@�s��R�[�h�`�F�b�N<BR>
	 * �@@this.�s��R�[�h��null�A<BR>
	 * �@@�����L�̒l�ȊO�̏ꍇ�A<BR>
	 * �@@�������I������B<BR>
	 * �@@�@@�@@�E�P�F����<BR>
	 * �@@�@@�@@�E�Q�F���<BR>
	 * �@@�@@�@@�E�R�F���É�<BR>
	 * �@@�@@�@@�E�U�F����<BR>
	 * �@@�@@�@@�E�W�F�D�y<BR>
	 * �@@�@@�@@�E�X�FNNM<BR>
	 * �@@�@@�@@�E�P�O�FJASDAQ<BR>
     * �@@�@@�@@�E�P�P�FJNX-PTS<BR>
	 * <BR>
 	 * �R�j�@@������t���i�`�F�b�N<BR>
	 * �@@this.������t���i��null�A<BR>
	 * �@@�����L�̒l�ȊO�̏ꍇ�A<BR>
	 * �@@�������I������B<BR>
	 * �@@�@@�@@�E�O�P�F����<BR>
	 * �@@�@@�@@�E�O�Q�F�����~�j����<BR>
	 * �@@�@@�@@�E�O�R�F�M�p���<BR>
	 * <BR>
	 * @@throws WEB3BusinessLayerException
	 * @@roseuid XXXXXXXXXXX
	 */
	public void validate() throws WEB3BusinessLayerException
	{
		final String STR_METHOD_NAME = "validate()";
		log.entering(STR_METHOD_NAME);

		// �Q�j�@@�s��R�[�h�`�F�b�N
		if(this.marketCode != null)
		{
			if(!(WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
				|| WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
				|| WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
				|| WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
				|| WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
				|| WEB3MarketCodeDef.NNM.equals(this.marketCode)
				|| WEB3MarketCodeDef.JASDAQ.equals(this.marketCode)
                || WEB3MarketCodeDef.JNX_PTS.equals(this.marketCode)))
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00608,
					this.getClass().getName() + ".validate()");
			}
		}

		// �R�j�@@������t���i�`�F�b�N
		if(this.orderCommodityCode != null)
		{
			if(!(WEB3OrderAccProductDef.STOCK.equals(this.orderCommodityCode)
				|| WEB3OrderAccProductDef.MINI_STOCK.equals(this.orderCommodityCode)
				|| WEB3OrderAccProductDef.MARGIN.equals(this.orderCommodityCode)))
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01699,
					this.getClass().getName() + ".validate()");
					
					
			}
		}
		

		log.exiting(STR_METHOD_NAME);
	}
}
@
