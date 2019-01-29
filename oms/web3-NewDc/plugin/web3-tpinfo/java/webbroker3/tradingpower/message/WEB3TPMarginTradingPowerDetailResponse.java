head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.33.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPMarginTradingPowerDetailResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �M�p�V�K���]�͏ڍ׉�ʕ\�����X�|���X(WEB3TPMarginTradingPowerDetailResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (�M�p�V�K���]�͏ڍ׉�ʕ\�����X�|���X)<BR>
 * �M�p�V�K���]�͏ڍ׉�ʕ\�����X�|���X�N���X�B<BR>
 * 
 * @@author asano(SCS)
 */

public class WEB3TPMarginTradingPowerDetailResponse extends WEB3GenResponse 
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "tradingpower_margin_tradingpower_detail";

    /**
     * �]�͌v�Z����ID
     */
    public String calcResultId;
   
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
    * ���̑��S����
    */
   public String otherRestraint;
   
   /**
    * �����ۏ؋�
    */
   public String cashDeposit;
   
   /**
    * ��p�،��]���z
    */
   public String substituteSecurityAsset;
   
   /**
    * ��������p�،��]���z
    */
   public String orderSubstituteSecurityAsset;
   
   /**
    * �����ۏ؋�
    */
   public String guarantyDeposit;
   
   /**
    * ���ʕ]����
    */
   public String contractAssetLoss;
   
   /**
    * ���ʕ]���v
    */
   public String contractAssetProfit;
   
   /**
    * ���ʕ]�����v
    */
   public String contractAssetProfitLoss;
   
   /**
    * ���萔��
    */
   public String contractCommission;
   
   /**
    * �����E�t�����E�݊���
    */
   public String interestFeeLoss;
   
   /**
    * �����E�t�����v
    */
   public String interestFeeProfit;
   
   /**
    * ���̑�������p
    */
   public String otherAccruedCost;
   
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
    * �K�v�ۏ؋�
    */
   public String marginDeposit;
   
   /**
    * �����K�v�ۏ؋�
    */
   public String cashMarginDeposit;

   /**
    * ���������ʑ��
    */
   public String orderContractAmount;
   
   /**
    * �������K�v�ۏ؋�
    */
   public String orderMarginDeposit;
   
   /**
    * �����������K�v�ۏ؋�
    */
   public String orderCashMarginDeposit;

   /**
    * ���v��ԍρE�������n���ʑ��
    */
   public String dayRepayContractAmount;
   
   /**
    * ���v��ԍρE�������n�K�v�ۏ؋�
    */
   public String dayRepayMarginDeposit;
   
   /**
    * ���v��ԍρE�������n�����K�v�ۏ؋�
    */
   public String dayRepayCashMarginDeposit;

   /**
    * �K�v�ۏ؋����v
    */
   public String marginDepositTotal;
   
   /**
    * �����K�v�ۏ؋����v
    */
   public String cashMarginDepositTotal;

   /**
    * �ۏ؋��]��
    */
   public String depositTradingPower;
   
   /**
    * �M�p�V�K���]��
    */
   public String marginTradingPower;
   
   /**
    * �ۏ؋��ێ��]��
    */
   public WEB3TPMarginMaintenanceTradingPowerUnit[] marginMaintenanceTradingPowerUnits;
   
   /**
    * �ۏ؋��a����
    */
   public String marginCollateralRate;
   
   /**
    * �ۏ؋���
    */
   public String marginDepositRate;
   
   /**
    * �Œ�K�v�ۏ؋�
    */
   public String minMarginDeposit;
   
   /**
    * ���ʌ��ϑ�<����>
    */
   public String settleContractLossToday;
   
   /**
    * ���ʌ��ϑ�<�w���>
    */
   public String settleContractLossDesignatedDate;
   
   /**
    * ���ʌ��ωv<����>
    */
   public String settleContractProfitToday;
      
   /**
    * ���ʌ��ωv<�w���>
    */
   public String settleContractProfitDesignatedDate;

   /**
    * ���ϑ��v���v<����>
    */
   public String settleContractProfitLossToday;
   
   /**
    * ���ό���<�O�����i�]��>
    */
   public String settleContractPrevDay;
   
   /**
    * ���ϑ��v�݌v<�T�Z>
    */
   public String settleContractProfitLossTotal;
   
   /**
    * (�R���X�g���N�^)
    * @@param l_request
    * @@roseuid 41B6B47A028F
    */
   protected WEB3TPMarginTradingPowerDetailResponse(WEB3GenRequest l_request) 
   {
        super( l_request );
   }
   
   /**
    * (�R���X�g���N�^)
    * @@roseuid 41B5838A0103
    */
   public WEB3TPMarginTradingPowerDetailResponse() 
   {
    
   }
}
@
