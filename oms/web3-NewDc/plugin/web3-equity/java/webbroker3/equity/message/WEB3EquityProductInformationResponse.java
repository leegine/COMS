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
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式銘柄情報表示レスポンス(WEB3EquityProductInfomationResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 SRA坂上 新規作成
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （株式銘柄情報表示レスポンス）。<BR>
 * <br>
 * 株式銘柄情報表示応答　@レスポンスデータクラス
 * @@version 1.0
 */
public class WEB3EquityProductInformationResponse extends WEB3GenResponse
{

	/**
	 * 銘柄名<BR>
	 */
	public String productName;

	/**
	 * 市場コード(表示用)<BR>
	 */
	public String marketCodePriority;

	/**
	 * (市場コード一覧)<BR>
	 * 市場コードの配列<BR>
	 */
	public String[] marketList;

	/**
	 * マーケットメイク銘柄フラグ<BR>
	 */
	public Boolean marketMakeFlag;

	/**
	 * 売買単位<BR>
	 */
	public String dealingUnit;

	/**
	 * 値幅上限値<BR>
	 */
	public String upperPriceRange;
	
	/**
	 * 値幅下限値<BR>
	 */
	public String lowerPriceRange;
	
	/**
	 * 信用属性<BR>
	 */
	public String marginAttribute;
	
	/**
	 * 取引規制<BR>
	 */
	public String[] tradingRegulation;
	
	/**
	 * 特別値幅制限銘柄フラグ<BR>
	 */
	public Boolean specialPriceRangeFlag;

	/**
	 * 代金即日徴収銘柄フラグ<BR>
	 */
	public Boolean sameDayCollectionFlag;

	/**
	 * 代用掛目<BR>
	 */
	public String marginRatio;

    /**
     * 増担保規制銘柄フラグ<BR>
     */
    public Boolean additionalCollateralRegulateFlag;

    /**
     * (増担保規制)買保証金率<BR>
     */
    public String buyMarginDepositRate;

    /**
     * (増担保規制)買現金保証金率<BR>
     */
    public String buyCashMarginDepositRate;

    /**
     * (増担保規制)売保証金率<BR>
     */
    public String sellMarginDepositRate;

    /**
     * (増担保規制)売現金保証金率<BR>
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
	 * コンストラクタ。<BR>
	 * 引数で与えられたリクエストオブジェクトを基にレスポンスオブジェクトを生成する。<BR>
	 *<BR>
	 * @@paramWEB3EquityProductInfomationRequest l_request
	 */
	public WEB3EquityProductInformationResponse(WEB3EquityProductInformationRequest l_request)
	{
		super(l_request);
	}
}
@
