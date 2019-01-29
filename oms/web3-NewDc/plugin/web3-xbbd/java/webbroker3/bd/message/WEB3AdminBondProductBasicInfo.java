head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.58.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductBasicInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券銘柄基本情報(WEB3AdminBondProductBasicInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (債券銘柄基本情報)<BR>
 * 債券銘柄基本情報クラス
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondProductBasicInfo extends Message
{
    
    /**
     * (銘柄コード(WEB3))<BR>
     * 銘柄コード(WEB3)
     */
    public String productCode;
    
    /**
     * (発行日)<BR>
     * 発行年月日
     */
    public Date issueDate;
    
    /**
     * (発行価格)<BR>
     * 発行価格
     */
    public String issuePrice;
    
    /**
     * (発行額)<BR>
     * 発行額
     */
    public String issueAmount;
    
    /**
     * (単位額面)<BR>
     * 単位額面
     */
    public String parValue;
    
    /**
     * (償還日)<BR>
     * 償還年月日
     */
    public Date maturityDate;
    
    /**
     * (償還価格)<BR>
     * 償還価格
     */
    public String redemptionPrice;
    
    /**
     * (利付タイプ)<BR>
     * 利付タイプ<BR>
     * <BR>
     * 0:固定利付き債,1:割引債,2:変動利付き債
     */
    public String couponType;
    
    /**
     * (利率)<BR>
     * 利率
     */
    public String coupon;
    
    /**
     * (年間利払回数)<BR>
     * 年間利払回数<BR>
     * <BR>
     * 不定時は99999999
     */
    public String yearlyInterestPayments;
    
    /**
     * (利払日1)<BR>
     * 利払日1<BR>
     * <BR>
     * "0000"の時は表示しない
     */
    public String interestPaymentDay1;
    
    /**
     * (利払日2)<BR>
     * 利払日2<BR>
     * <BR>
     * "0000"の時は表示しない
     */
    public String interestPaymentDay2;
    
    /**
     * (HOST銘柄名1)<BR>
     * HOST銘柄名1
     */
    public String hostProductName1;
    
    /**
     * (HOST簡略銘柄名)<BR>
     * HOST簡略銘柄名
     */
    public String hostShortProductName;
    
    /**
     * (HOST銘柄名2)<BR>
     * HOST銘柄名2
     */
    public String hostProductName2;
    
    /**
     * (種別コード)<BR>
     * 種別コード
     */
    public String bondCategCode;
    
    /**
     * (通貨コード)<BR>
     * 通貨コード
     */
    public String currencyCode;
    
    /**
     * (発行市場コード)<BR>
     * 発行市場コード<BR>
     * <BR>
     * 1：国内市場発行・国内発行体<BR>
     * 2：国内市場発行・国外発行体<BR>
     * 3：国外市場発行
     */
    public String issueMarketCode;
    
    /**
     * (発行体コード)<BR>
     * 発行体コード<BR>
     * <BR>
     * 1：国  2：政府機@関   3：地方公共団体 <BR>             
     * 4：公益事業会社　@5：一般事業会社<BR>
     * 6：金融機@関　@7：国際機@関　@8：その他
     */
    public String issueAssociationCode;
    
    /**
     * (経過利子計算タイプ)<BR>
     * 経過利子計算タイプ
     */
    public String accruedInterestCalcType;
    
    /**
     * (経過利子起算日)<BR>
     * 経過利子起算日
     */
    public Date accruedInterestStartDay;
    
    /**
     * (特殊利払区分)<BR>
     * 特殊利払区分<BR>
     * <BR>
     * 0：特殊利払日無し　@1：初期・終期スキップ <BR>
     * 2：初期スキップ　@3：終期スキップ　@4：初期不払 <BR>
     * 5：初期満額払い　@6：ショートインタレスト
     */
    public String specialPaymentDiv;
    
    /**
     * (フローティングレート・金利期間区分)<BR>
     * フローティングレート・金利期間区分
     */
    public String floatingInterestPeriodDiv;
    
    /**
     * (フローティングレート・金利期間)<BR>
     * フローティングレート・金利期間
     */
    public String floatingInterestPeriod;
    
    /**
     * (フローティングレート・金利種類)<BR>
     * フローティングレート・金利種類
     */
    public String floatingInterestType;
    
    /**
     * (フローティングレート・金利スプレッド)<BR>
     * フローティングレート・金利スプレッド
     */
    public String floatingInterestSpread;
    
    /**
     * (フローティングレート・ミニマムクーポン)<BR>
     * フローティングレート・ミニマムクーポン
     */
    public String floatingMinCoupon;
    
    /**
     * (免税区分)<BR>
     * 免税区分<BR>
     * <BR>
     * 0：免税　@1：居住者課税・非居住者免税<BR>
     * 2：居住者免税・非居住者課税　@3：課税
     */
    public String taxFreeDiv;
    
    /**
     * (S&P)<BR>
     * S&P
     */
    public String sAndP;
    
    /**
     * (Moody's)<BR>
     * MOODY'S
     */
    public String moodys;
    
    /**
     * (CUSIP)<BR>
     * CUSIP
     */
    public String cusip;
    
    /**
     * @@roseuid 44E3363A0167
     */
    public WEB3AdminBondProductBasicInfo() 
    {
     
    }
}
@
