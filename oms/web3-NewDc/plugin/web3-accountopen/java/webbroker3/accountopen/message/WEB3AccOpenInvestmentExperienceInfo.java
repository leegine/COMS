head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.59.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInvestmentExperienceInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資経験情報(WEB3AccOpenInvestmentExperienceInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 張学剛 新規作成
*/
package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (投資経験情報)<BR>
 * 投資経験情報<BR>
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AccOpenInvestmentExperienceInfo extends Message 
{
    
    /**
     * (現物株式)<BR>
     * 現物株式<BR>
     * <BR>
     * 投資経験年数を区分にて入力する。<BR>
     * （※ 項目値については各社カスタマイズあり）<BR>
     */
    public String experienceEquityDiv;
    
    /**
     * (信用取引)<BR>
     * 信用取引<BR>
     * <BR>
     * 投資経験年数を区分にて入力する。<BR>
     * （※ 項目値については各社カスタマイズあり）<BR>
     */
    public String experienceMarginDiv;
    
    /**
     * (債券)<BR>
     * 債券<BR>
     * <BR>
     * 投資経験年数を区分にて入力する。<BR>
     * （※ 項目値については各社カスタマイズあり）<BR>
     */
    public String experienceBondDiv;
    
    /**
     * (転換社債)<BR>
     * 転換社債<BR>
     * <BR>
     * 投資経験年数を区分にて入力する。<BR>
     * （※ 項目値については各社カスタマイズあり）<BR>
     */
    public String experienceWtDiv;
    
    /**
     * (投資信託（株式）)<BR>
     * 投資信託（株式）<BR>
     * <BR>
     * 投資経験年数を区分にて入力する。<BR>
     * （※ 項目値については各社カスタマイズあり）<BR>
     */
    public String experienceFundSkDiv;
    
    /**
     * (投資信託（債券）)<BR>
     * 投資信託（債券）<BR>
     * <BR>
     * 投資経験年数を区分にて入力する。<BR>
     * （※ 項目値については各社カスタマイズあり）<BR>
     */
    public String experienceFundBdDiv;
    
    /**
     * (先物・オプション)<BR>
     * 先物・オプション<BR>
     * <BR>
     * 投資経験年数を区分にて入力する。<BR>
     * （※ 項目値については各社カスタマイズあり）<BR>
     */
    public String experienceFoDiv;
    
    /**
     * (外国証券)<BR>
     * 外国証券<BR>
     * <BR>
     * 投資経験年数を区分にて入力する。<BR>
     * （※ 項目値については各社カスタマイズあり）<BR>
     */
    public String experienceFEquityDiv;
    
    /**
     * (その他)<BR>
     * その他<BR>
     * <BR>
     * 投資経験年数を区分にて入力する。<BR>
     * （※ 項目値については各社カスタマイズあり）<BR>
     */
    public String experienceEtcDiv;
    
    /**
     * @@roseuid 41B45E79037A
     */
    public WEB3AccOpenInvestmentExperienceInfo() 
    {
     
    }
}@
