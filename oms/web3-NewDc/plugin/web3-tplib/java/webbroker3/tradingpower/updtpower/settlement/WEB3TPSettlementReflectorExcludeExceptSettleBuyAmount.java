head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSettlementReflectorExcludeExceptSettleBuyAmount.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 銘柄ごと取引情報<差金決済相当外買付代金非考慮>(WEB3TPSettlementReflectorExcludeExceptSettleBuyAmount.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/28 孟亞南　@(中訊)　@新規作成　@モデルNo.179
*/
package webbroker3.tradingpower.updtpower.settlement;


/**
 * (銘柄ごと取引情報<差金決済相当外買付代金非考慮>)<BR>
 * 銘柄ごと取引情報<差金決済相当外買付代金非考慮>
 *
 * @@author  孟亞南
 * @@version 1.0
 */
public class WEB3TPSettlementReflectorExcludeExceptSettleBuyAmount extends WEB3TPSettlementReflector
{
    /**
     * コンストラクタ
     */
	public WEB3TPSettlementReflectorExcludeExceptSettleBuyAmount()
	{
	}

	/**
	 * (calc差金決済買付代金)<BR>
	 * 差金決済買付代金を返却する <BR>
	 * <BR>
	 * (*) 計算の詳細は、「基本設計計算式書（余力_差金決済）.doc」参照。<BR>
	 * <BR>
	 * @@return double
	 */
	public double calcSettlementBuyAmount()
	{
		//差金決済買付代金　@=　@差金決済相当買付代金
		//・	差金決済相当買付代金	・・・銘柄ごと取引情報.calc差金決済相当買付代金()
        return this.calcSuitableSettlementBuyAmount();
	}
}
@
