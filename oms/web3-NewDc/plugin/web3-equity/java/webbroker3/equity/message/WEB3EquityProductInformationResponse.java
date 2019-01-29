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
filename	WEB3EquityProductInformationResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������\�����X�|���X(WEB3EquityProductInfomationResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 SRA��� �V�K�쐬
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�����������\�����X�|���X�j�B<BR>
 * <br>
 * �����������\�������@@���X�|���X�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquityProductInformationResponse extends WEB3GenResponse
{

	/**
	 * ������<BR>
	 */
	public String productName;

	/**
	 * �s��R�[�h(�\���p)<BR>
	 */
	public String marketCodePriority;

	/**
	 * (�s��R�[�h�ꗗ)<BR>
	 * �s��R�[�h�̔z��<BR>
	 */
	public String[] marketList;

	/**
	 * �}�[�P�b�g���C�N�����t���O<BR>
	 */
	public Boolean marketMakeFlag;

	/**
	 * �����P��<BR>
	 */
	public String dealingUnit;

	/**
	 * �l������l<BR>
	 */
	public String upperPriceRange;
	
	/**
	 * �l�������l<BR>
	 */
	public String lowerPriceRange;
	
	/**
	 * �M�p����<BR>
	 */
	public String marginAttribute;
	
	/**
	 * ����K��<BR>
	 */
	public String[] tradingRegulation;
	
	/**
	 * ���ʒl�����������t���O<BR>
	 */
	public Boolean specialPriceRangeFlag;

	/**
	 * ����������������t���O<BR>
	 */
	public Boolean sameDayCollectionFlag;

	/**
	 * ��p�|��<BR>
	 */
	public String marginRatio;

    /**
     * ���S�ۋK�������t���O<BR>
     */
    public Boolean additionalCollateralRegulateFlag;

    /**
     * (���S�ۋK��)���ۏ؋���<BR>
     */
    public String buyMarginDepositRate;

    /**
     * (���S�ۋK��)�������ۏ؋���<BR>
     */
    public String buyCashMarginDepositRate;

    /**
     * (���S�ۋK��)���ۏ؋���<BR>
     */
    public String sellMarginDepositRate;

    /**
     * (���S�ۋK��)�������ۏ؋���<BR>
     */
    public String sellCashMarginDepositRate;

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
	public WEB3EquityProductInformationResponse()
	{

	}
	/**
	 * �R���X�g���N�^�B<BR>
	 * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g����Ƀ��X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
	 *<BR>
	 * @@paramWEB3EquityProductInfomationRequest l_request
	 */
	public WEB3EquityProductInformationResponse(WEB3EquityProductInformationRequest l_request)
	{
		super(l_request);
	}
}
@
