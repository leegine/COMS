head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.41.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPAdvanceDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 立替金明細ユニットクラス(WEB3AdminTPAdvanceDetailUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/02/08 asano(SCS) 新規作成
 */
package webbroker3.tradingpoweradmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (立替金明細ユニット)<BR>
 * 立替金明細ユニット。<BR>
 */
public class WEB3AdminTPAdvanceDetailUnit extends Message
{

    /**
     * (実質客勘残)
     */
    public String realAccountBalance;
    
    /**
     * (預り金)
     */
    public String accountBalance;
    
    /**
     * (現金保証金)
     */
    public String cashDeposit;

    /**
     * (発注充当金)
     */
    public String todayUnexecutedAmount;
    
    /**
     * (日計り拘束金)
     */
    public String dayTradeRestraint;
    
    /**
     * (その他拘束金)
     */    
    public String otherRestraint;
    
    /**
     * (使用可能現金保証金)
     */
    public String marginAccountBalance;
    
    /**
     * (証券評価額)
     */
    public String securityAsset;
    
    /**
     * (未決済建玉評価損益)
     */
    public String contractAssetProfitLoss;
    
    /**
     * (建玉諸経費)
     */
    public String contractTotalCost;
    
    /**
     * (未受渡建玉決済損)
     */
    public String undeliContractLoss;
    
    /**
     * (受入保証金)
     */
    public String receiptMarginDeposit;

    /**
     * (保証金維持必要額)
     */
    public String marginMaintenanceAmount;
        
    /**
     * (現金必要保証金)
     */
    public String cashMarginDeposit;
    
    /**
     * (追証余力)
     */
    public String marginCallPower;
    
    /**
     * (建玉代金)
     */
    public String contractAmount;
    
    /**
     * (保証金預託率)
     */
    public String marginDepositRate;

    /**
     * (立替金)
     */
    public String debitAmount;
    
    /**
     * (特別立替金)
     */
    public String specialDebitAmount;
    
    /**
     * (保証金請求額)
     */
    public String marginClaimedAmount;
    
    /**
     * (顧客勘定残高不足分)
     */
    public String accountBalanceShortfall; 
            
    /**
     * (現金保証金不足分)
     */
    public String cashMarginShortfall;

    /**
     * (証券評価額<代用評価低下率考慮>)
     *
     */
    public String substituteSecurityAssetIncDropRate;
        
    /**
     * (受入保証金<代用評価低下率考慮>)
     */
    public String receiptMarginDepositIncDropRate;
    
    /**
     * (追証余力<代用評価低下率考慮>)
     */
    public String marginCallPowerIncDropRate;

    /**
     * (保証金預託率<代用評価低下率考慮>)
     */
    public String marginDepositRateIncDropRate;

    /**
     * (顧客勘定残高不足分<代用評価低下率考慮>)
     */
    public String accountBalanceShortfallIncDropRate; 

    /**
     * (現金保証金不足分<代用評価低下率考慮>)
     */
    public String cashMarginBalanceShortfallIncDropRate; 

    /**
     * (保証金請求額<代用評価低下率考慮>)
     */
    public String marginClaimedAmountIncDropRate;

    /**
     * (コンストラクタ)
     */
    public WEB3AdminTPAdvanceDetailUnit()
    {
    }
    
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer("WEB3AdminTPAdvanceDetailUnit=");
        l_sb.append("accountBalance=" + this.accountBalance);
        l_sb.append(",accountBalanceShortfall=" + this.accountBalanceShortfall);
        l_sb.append(",accountBalanceShortfallIncDropRate=" + this.accountBalanceShortfallIncDropRate);
        l_sb.append(",cashDeposit=" + this.cashDeposit);
        l_sb.append(",cashMarginBalanceShortfallIncDropRate=" + this.cashMarginBalanceShortfallIncDropRate);
        l_sb.append(",cashMarginDeposit=" + this.cashMarginDeposit);
        l_sb.append(",cashMarginShortfall=" + this.cashMarginShortfall);
        l_sb.append(",contractAmount=" + this.contractAmount);
        l_sb.append(",contractAssetProfitLoss=" + this.contractAssetProfitLoss);
        l_sb.append(",contractTotalCost=" + this.contractTotalCost);
        l_sb.append(",dayTradeRestraint=" + this.dayTradeRestraint);
        l_sb.append(",debitAmount=" + this.debitAmount);
        l_sb.append(",marginAccountBalance=" + this.marginAccountBalance);
        l_sb.append(",marginCallPower=" + this.marginCallPower);
        l_sb.append(",marginCallPowerIncDropRate=" + this.marginCallPowerIncDropRate);
        l_sb.append(",marginClaimedAmount=" + this.marginClaimedAmount);
        l_sb.append(",marginClaimedAmountIncDropRate=" + this.marginClaimedAmountIncDropRate);
        l_sb.append(",marginDepositRate=" + this.marginDepositRate);
        l_sb.append(",marginDepositRateIncDropRate=" + this.marginDepositRateIncDropRate);
        l_sb.append(",marginMaintenanceAmount=" + this.marginMaintenanceAmount);
        l_sb.append(",otherRestraint=" + this.otherRestraint);
        l_sb.append(",realAccountBalance=" + this.realAccountBalance);
        l_sb.append(",receiptMarginDeposit=" + this.receiptMarginDeposit);
        l_sb.append(",receiptMarginDepositIncDropRate=" + this.receiptMarginDepositIncDropRate);
        l_sb.append(",securityAsset=" + this.securityAsset);
        l_sb.append(",substituteSecurityAssetIncDropRate=" + this.substituteSecurityAssetIncDropRate);
        l_sb.append(",specialDebitAmount=" + this.specialDebitAmount);
        l_sb.append(",todayUnexecutedAmount=" + this.todayUnexecutedAmount);
        l_sb.append(",undeliContractLoss=" + this.undeliContractLoss);        
        return l_sb.toString();
    }
    
}
@
