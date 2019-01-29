head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityCommissionInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式手数料情報(WEB3EquityCommissionInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 桑原(SRA) 新規作成
*/

package webbroker3.equity.message;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * （現物株式手数料情報）。<br>
 * <br>
 * 現物株式手数料情報　@データクラス
 * @@version 1.0
 */

public class WEB3EquityCommissionInfoUnit extends Message
{
	
	/**
	 * (手数料コース）<BR>
	 * 02：定額手数料(スタンダード)　@03：約定代金合計　@04：約定回数<BR>
	 * 05：一日定額制　@12：定額手数料(ハイパーボックス)<BR>
	 */
	public String commissionCourse;

	/**
	 * (手数料)<BR>
	 * 手数料<BR><BR>
	 */
	public String commission;

	/**
	 * (手数料消費税)<BR>
	 * 手数料消費税<BR>
	 */
	public String commissionConsumptionTax;


	/**
	 * @@roseuid 
	 */
	public WEB3EquityCommissionInfoUnit()
	{

	}
	

}
@
