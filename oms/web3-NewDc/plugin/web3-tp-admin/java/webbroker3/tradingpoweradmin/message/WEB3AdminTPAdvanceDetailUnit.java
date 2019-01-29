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
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���֋����׃��j�b�g�N���X(WEB3AdminTPAdvanceDetailUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/02/08 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpoweradmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���֋����׃��j�b�g)<BR>
 * ���֋����׃��j�b�g�B<BR>
 */
public class WEB3AdminTPAdvanceDetailUnit extends Message
{

    /**
     * (�����q���c)
     */
    public String realAccountBalance;
    
    /**
     * (�a���)
     */
    public String accountBalance;
    
    /**
     * (�����ۏ؋�)
     */
    public String cashDeposit;

    /**
     * (�����[����)
     */
    public String todayUnexecutedAmount;
    
    /**
     * (���v��S����)
     */
    public String dayTradeRestraint;
    
    /**
     * (���̑��S����)
     */    
    public String otherRestraint;
    
    /**
     * (�g�p�\�����ۏ؋�)
     */
    public String marginAccountBalance;
    
    /**
     * (�،��]���z)
     */
    public String securityAsset;
    
    /**
     * (�����ό��ʕ]�����v)
     */
    public String contractAssetProfitLoss;
    
    /**
     * (���ʏ��o��)
     */
    public String contractTotalCost;
    
    /**
     * (����n���ʌ��ϑ�)
     */
    public String undeliContractLoss;
    
    /**
     * (����ۏ؋�)
     */
    public String receiptMarginDeposit;

    /**
     * (�ۏ؋��ێ��K�v�z)
     */
    public String marginMaintenanceAmount;
        
    /**
     * (�����K�v�ۏ؋�)
     */
    public String cashMarginDeposit;
    
    /**
     * (�Ǐؗ]��)
     */
    public String marginCallPower;
    
    /**
     * (���ʑ��)
     */
    public String contractAmount;
    
    /**
     * (�ۏ؋��a����)
     */
    public String marginDepositRate;

    /**
     * (���֋�)
     */
    public String debitAmount;
    
    /**
     * (���ʗ��֋�)
     */
    public String specialDebitAmount;
    
    /**
     * (�ۏ؋������z)
     */
    public String marginClaimedAmount;
    
    /**
     * (�ڋq����c���s����)
     */
    public String accountBalanceShortfall; 
            
    /**
     * (�����ۏ؋��s����)
     */
    public String cashMarginShortfall;

    /**
     * (�،��]���z<��p�]���ቺ���l��>)
     *
     */
    public String substituteSecurityAssetIncDropRate;
        
    /**
     * (����ۏ؋�<��p�]���ቺ���l��>)
     */
    public String receiptMarginDepositIncDropRate;
    
    /**
     * (�Ǐؗ]��<��p�]���ቺ���l��>)
     */
    public String marginCallPowerIncDropRate;

    /**
     * (�ۏ؋��a����<��p�]���ቺ���l��>)
     */
    public String marginDepositRateIncDropRate;

    /**
     * (�ڋq����c���s����<��p�]���ቺ���l��>)
     */
    public String accountBalanceShortfallIncDropRate; 

    /**
     * (�����ۏ؋��s����<��p�]���ቺ���l��>)
     */
    public String cashMarginBalanceShortfallIncDropRate; 

    /**
     * (�ۏ؋������z<��p�]���ቺ���l��>)
     */
    public String marginClaimedAmountIncDropRate;

    /**
     * (�R���X�g���N�^)
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
