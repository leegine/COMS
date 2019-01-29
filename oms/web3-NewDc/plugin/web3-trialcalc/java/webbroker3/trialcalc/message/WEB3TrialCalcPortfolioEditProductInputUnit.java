head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.24.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioEditProductInputUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービスポートフォリオ編集銘柄入力明細(WEB3TrialCalcPortfolioEditProductInputUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * （計算サービスポートフォリオ編集銘柄入力明細）<BR>
 * <BR>
 * 計算サービスポートフォリオ編集銘柄入力明細。<BR>
 * <BR>
 * WEB3TrialCalcPortfolioEditProductInputUnit<BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3TrialCalcPortfolioEditProductInputUnit extends Message
{
    /**
     * （銘柄コード）<BR>
     * <BR>
     * productCode<BR>
     */
    public String productCode;

    /**
     * （銘柄名）<BR>
     * <BR>
     * productName<BR>
     */
    public String productName;

    /**
     * （株数）<BR>
     * <BR>
     * orderQuantity<BR>
     */
    public String orderQuantity;

    /**
     * （買単価）<BR>
     * <BR>
     * 買単価。<BR>
     * （null指定可）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * buyPrice. (Null can be specified)<BR>
     */
    public String buyPrice;

    /**
     * 市場コード。<BR>
     * （1：東京　@2：大阪　@3：名古屋　@6：福岡　@8：札幌　@9：NNM　@10：JASDAQ）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Market code<BR>
     * (1 : TOKYO  2 : OSAKA  3 : NAGOYA  6 : FUKUOKA  7 : SAPPORO<BR>
     *  9 : NNM    10 : JASDAQ)<BR>
     */
    public String marketCode;

    /**
     * 市場コードの一覧。<BR>
     * （1：東京　@2：大阪　@3：名古屋　@6：福岡　@8：札幌　@9：NNM　@10：JASDAQ）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Market list<BR>
     * (1 : TOKYO  2 : OSAKA  3 : NAGOYA  6 : FUKUOKA  7 : SAPPORO<BR>
     *  9 : NNM    10 : JASDAQ)<BR>
     * <BR>
     */
    public String[] marketCodeList;

    /**
     * @@roseuid 41C81B22031D
     */
    public WEB3TrialCalcPortfolioEditProductInputUnit()
    {

    }
}
@
