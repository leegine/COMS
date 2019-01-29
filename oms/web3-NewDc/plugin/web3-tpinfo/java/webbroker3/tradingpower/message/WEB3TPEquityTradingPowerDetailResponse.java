head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPEquityTradingPowerDetailResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���������t�]�͏ڍ׉�ʕ\�����X�|���X(WEB3TPEquityTradingPowerDetailResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (���������t�]�͏ڍ׉�ʕ\�����X�|���X)<BR>
 * ���������t�]�͏ڍ׉�ʕ\�����X�|���X�N���X�B<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPEquityTradingPowerDetailResponse extends WEB3GenResponse 
{

   /**
    * PTYPE
    */
   public static final String PTYPE = "tradingpower_equity_tradingpower_detail";

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
    * ���v��S����
    */
   public String dayTradeRestraint;
   
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
    * �K�v�ۏ؋�
    */
   public String marginDeposit;
   
   /**
    * �����K�v�ۏ؋�
    */
   public String cashMarginDeposit;

   /**
    * �ۏ؋��]��
    */
   public String depositTradingPower;
   
   /**
    * �g�p�\����
    */
   public String actualAccountBalance;

   /**
    * ���������t�[���]��
    */
   public String equityApplyTradingPower;
   
   /**
    * ���������t�]��
    */
   public String equityTradingPower;
   
   
   /**
    * (�R���X�g���N�^)
    * @@param l_request
    * @@roseuid 41B6542000CA
    */
   protected WEB3TPEquityTradingPowerDetailResponse(WEB3GenRequest l_request) 
   {
        super( l_request );
   }
   
   /**
    * (�R���X�g���N�^)
    * @@roseuid 41B5671500E2
    */
   public WEB3TPEquityTradingPowerDetailResponse() 
   {   
   }
   
}
@
