head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.52.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositTransitionReferenceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 証拠金推移明細クラス(WEB3IfoDepositTransitionReference)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/02 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.ifodeposit.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (証拠金推移明細)<BR>
 * 証拠金推移明細クラス。<BR>
 * 証拠金の推移参照画面の一列を表現する。<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoDepositTransitionReferenceUnit extends Message
{

    /**
     * (日付)<BR>
     * T+0、または、T+1、または、T+2の日付。<BR>
     */
    public java.util.Date bizDate;

    /**
     * (証拠金残)<BR>
     * 証拠金残高。<BR>
     */
    public String ifoDepositBal;

    /**
     * (本日振替額)<BR>
     * 本日振替額。<BR>
     */
    public String todayCahangeAmt;

    /**
     * (本日先物決済損益)<BR>
     * 本日先物決済損益。<BR>
     * <BR>
     * T+1、T+2のみ設定。<BR>
     */
    public String todayFutSettleProfitLoss;

    /**
     * (本日オプション受渡代金)<BR>
     * 本日オプション受渡代金。<BR>
     * <BR>
     * T+1、T+2のみ設定。<BR>
     */
    public String todayOpNetAmt;

    /**
     * (先物評価損益)<BR>
     * 先物評価損益。<BR>
     */
    public String futEvalProfitLoss;

    /**
     * (買建先物評価損益)<BR>
     * 買建先物評価損益。<BR>
     */
    public String lfEvalProfitLoss;

    /**
     * (売建先物評価損益)<BR>
     * 売建先物評価損益。<BR>
     */
    public String sfEvalProfitLoss;

    /**
     * (受入証拠金残)<BR>
     * 受入証拠金残高。<BR>
     */
    public String acceptanceIfoDepositBal;

    /**
     * (買ポジション建玉)<BR>
     * 買ポジション建玉。<BR>
     */
    public String longPositionContract;

    /**
     * (買ポジション建玉(内注文中))<BR>
     * 注文中買ポジション建玉。<BR>
     */
    public String partLongPositionContract;

    /**
     * (売ポジション建玉)<BR>
     * 売ポジション建玉。<BR>
     */
    public String shortPositionContract;

    /**
     * (売ポジション建玉(内注文中))<BR>
     * 注文中売ポジション建玉。<BR>
     */
    public String partShortPositionContract;

    /**
     * (ポジションバランス)<BR>
     * ポジションバランス。<BR>
     */
    public String positionBalance;

    /**
     * (ポジションバランス区分)<BR>
     * 0：ニュートラル<BR>
     * 1：買超過<BR>
     * 2：売超過<BR>
     */
    public String positionBalanceDiv;

    /**
     * (SPAN証拠金)<BR>
     * SPAN証拠金。<BR>
     * <BR>
     * SPAN採用会社のみ設定。<BR>
     */
    public String spanIfoDeposit;

    /**
     * (ネットオプション価値総額)<BR>
     * ネットオプション価値総額。<BR>
     */
    public String netOptionlValueTotalAmt;

    /**
     * (証拠金所要額)<BR>
     * 証拠金所要額。<BR>
     */
    public String ifoDepositNecessaryAmt;

	/**
	 * (証拠金余力額)<BR>
	 * 証拠金余力額。<BR>
	 * <BR>
	 * T+1、T+2のみ設定。<BR>
	 */
	public String ifoDepositPower;
    
	/**
	 * (証拠金振替余力額)<BR>
	 * 証拠金振替余力額。<BR>
	 */
	public String depositChangePower;

    /**
     * (オプション評価損益)<BR>
     * オプション評価損益。<BR>
     */
    public String opEvalProfitLoss;

    /**
     * (買建オプション評価損益)<BR>
     * 買建オプション評価損益。<BR>
     */
    public String loEvalProfitLoss;

    /**
     * (売建オプション評価損益)<BR>
     * 売建オプション評価損益。<BR>
     */
    public String soEvalProfitLoss;
    
    /**
     * (指数別証拠金推移明細)<BR>
     * 指数別証拠金推移明細の一覧。<BR>
     * <BR>
     * SPAN使用不可の場合設定。<BR>
     */
    public WEB3IfoDepositTranRefPerIndexUnit[] ifoDepositTranRefPerIndexUnit;
    
	/**
	 * (本日入金額)<BR>
	 * 本日入金額。<BR>
	 */
	public String todayCashinAmt;
    
    /**
     * @@roseuid 4186177701C4
     */
    public WEB3IfoDepositTransitionReferenceUnit()
    {

    }
}
@
