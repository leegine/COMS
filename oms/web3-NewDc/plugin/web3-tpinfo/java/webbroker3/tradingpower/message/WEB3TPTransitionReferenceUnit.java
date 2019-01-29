head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransitionReferenceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �]�͐��ږ��׃��j�b�g�N���X(WEB3TPAssetUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.message;

import com.fitechlabs.xtrade.kernel.message.Message;
import java.util.Date;

/**
 * (�]�͐��ږ��׃��j�b�g)<BR>
 * �]�͐��ږ��׃��j�b�g�N���X�B<BR>
 * 
 * @@author asano(SCS)
 */

public class WEB3TPTransitionReferenceUnit extends Message
{

    /**
     * ���t
     */
    public Date bizDate;

    /**
     * �a���
     */
    public String accountBalance;

    /**
     * �O����
     */
    public String comparedPreviousDay;

    /**
     * �����[����
     */
    public String unexecutedAmount;

    /**
     * �������ԍό��ϑ��v
     */
    public String orderRepaySettleProfitLoss;

    /**
     * ���񔭒����ԍό��ϑ��v
     */
    public String currOrderRepaySettleProfitLoss;

    /**
     * ���v��S����
     */
    public String dayTradeRestraint;

    /**
     * ���̑��S����
     */
    public String otherRestraint;

    /**
     * (�a����S�ۏo���S����)
     */
    public String cashDepositRestraint;

    /**
     * �����ۏ؋�
     */
    public String cashDeposit;

    /**
     * �،��]���z
     */
    public String securityAsset;

    /**
     * �����ۏ؋�
     */
    public String guarantyDeposit;

    /**
     * ���ʕ]���v
     */
    public String contractAssetProfit;

    /**
     * ���ʕ]����
     */
    public String contractAssetLoss;

    /**
     * ���ʕ]�����v
     */
    public String contractAssetProfitLoss;

    /**
     * ���ʏ��o��
     */
    public String contractTotalCost;

    /**
     * ����n���ʌ��ϑ�
     */
    public String undeliContractLoss;

    /**
     * ����n���ʌ��ωv
     */
    public String undeliContractProfit;

    /**
     * ����ۏ؋�
     */
    public String acceptDeposit;

    /**
     * ���ʑ��
     */
    public String contractAmount;

    /**
     * ���v��ԍρE�������n���ʑ��
     */
    public String dayRepayContractAmount;

    /**
     * ����n���ʑ��
     */
    public String undeliContractAmount;

    /**
     * �K�v�ۏ؋�
     */
    public String marginDeposit;

    /**
     * ���v��ԍρE�������n�K�v�ۏ؋�
     */
    public String dayRepayMarginDeposit;

    /**
     * �����K�v�ۏ؋�
     */
    public String cashMarginDeposit;

    /**
     * ���v��ԍρE�������n�����K�v�ۏ؋�
     */
    public String dayRepayCashMarginDeposit;

    /**
     * �ۏ؋��]��
     */
    public String depositTradingPower;

    /**
     * �ۏ؋����o�S����
     */
    public String depositWithdrawRestraint;

    /**
     * ����n���ʕK�v�ۏ؋�
     */
    public String undeliMarginDeposit;

    /**
     * ����n���ʌ����K�v�ۏ؋�
     */
    public String undeliCashMarginDeposit;

    /**
     * �ۏ؋����o�]��
     */
    public String depositWithdrawTradingPower;

    /**
     * �o���]��
     */
    public String paymentTradingPower;

    /**
     * �ۏ؋��a����
     */
    public String marginCollateralRate;

    /**
     * �ǏؕK�v�ۏ؋�
     */
    public String additionalMarginDeposit;

    /**
     * �Ǐؗ]��
     */
    public String additionalDepositTradingPower;

    /**
     * �ۏ؋��ێ��]�́��ۏ؋�����
     */
    public String marginDepositPower;

    /**
     * �ۏ؋��ێ��]�́��ۏ؋��ێ�����
     */
    public String marginMaintenancePower;

    /**
     * �ۏ؋��ێ��]�́��@@��ۏ؋��ێ�����
     */
    public String legalMarginDepositPower;

    /**
     * �a����s���z            
     */
    public String accountBalanceShortfall;

    /**
     * ���������z
     */
    public String payClaimAmount;

    /**
     * ���������t�]��
     */
    public String equityTradingPower;

    /**
     * ���������t�]��<���v��S�����l��>
     */
    public String equityTradingPowerDayTrade;

    /**
     * �M�p�V�K���]��
     */
    public String marginTradingPower;

    /**
     * �M�p�����]��
     */
    public String swapLongTradingPower;

    /**
     * �M�p�����]��<���v��S�����l��>
     */
    public String swapLongTradingPowerDayTrade;

    /**
     * ���M���t�]��
     */
    public String mutualTradingPower;

    /**
     * ���̑����i���t�]�� 
     */
    public String otherTradingPower;

    /**
     * (�R���X�g���N�^)
     * @@roseuid 41B5632E020B
     */
    public WEB3TPTransitionReferenceUnit()
    {

    }
}@
