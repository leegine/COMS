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
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ®Á¿îñ\¦X|X(WEB3EquityProductInfomationResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 SRAâã VKì¬
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * i®Á¿îñ\¦X|XjB<BR>
 * <br>
 * ®Á¿îñ\¦@@X|Xf[^NX
 * @@version 1.0
 */
public class WEB3EquityProductInformationResponse extends WEB3GenResponse
{

	/**
	 * Á¿¼<BR>
	 */
	public String productName;

	/**
	 * sêR[h(\¦p)<BR>
	 */
	public String marketCodePriority;

	/**
	 * (sêR[hê)<BR>
	 * sêR[hÌzñ<BR>
	 */
	public String[] marketList;

	/**
	 * }[PbgCNÁ¿tO<BR>
	 */
	public Boolean marketMakeFlag;

	/**
	 * PÊ<BR>
	 */
	public String dealingUnit;

	/**
	 * lãÀl<BR>
	 */
	public String upperPriceRange;
	
	/**
	 * lºÀl<BR>
	 */
	public String lowerPriceRange;
	
	/**
	 * Mp®«<BR>
	 */
	public String marginAttribute;
	
	/**
	 * æøK§<BR>
	 */
	public String[] tradingRegulation;
	
	/**
	 * ÁÊl§ÀÁ¿tO<BR>
	 */
	public Boolean specialPriceRangeFlag;

	/**
	 * ãà¦ú¥ûÁ¿tO<BR>
	 */
	public Boolean sameDayCollectionFlag;

	/**
	 * ãp|Ú<BR>
	 */
	public String marginRatio;

    /**
     * SÛK§Á¿tO<BR>
     */
    public Boolean additionalCollateralRegulateFlag;

    /**
     * (SÛK§)ÛØà¦<BR>
     */
    public String buyMarginDepositRate;

    /**
     * (SÛK§)»àÛØà¦<BR>
     */
    public String buyCashMarginDepositRate;

    /**
     * (SÛK§)ÛØà¦<BR>
     */
    public String sellMarginDepositRate;

    /**
     * (SÛK§)»àÛØà¦<BR>
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
	 * RXgN^B<BR>
	 * øÅ^¦çê½NGXgIuWFNgðîÉX|XIuWFNgð¶¬·éB<BR>
	 *<BR>
	 * @@paramWEB3EquityProductInfomationRequest l_request
	 */
	public WEB3EquityProductInformationResponse(WEB3EquityProductInformationRequest l_request)
	{
		super(l_request);
	}
}
@
