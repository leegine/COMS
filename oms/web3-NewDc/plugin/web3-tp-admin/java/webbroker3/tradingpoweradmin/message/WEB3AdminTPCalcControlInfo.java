head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.43.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPCalcControlInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͐�����N���X(WEB3AdminTPCalcControlInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 �x�� �a��(FLJ) �V�K�쐬
Revision History : 2007/07/26 ��іQ (���u) ���f���FNo.006
*/
package webbroker3.tradingpoweradmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �]�͐�����N���X
 */
public class WEB3AdminTPCalcControlInfo extends Message
{
   /**
    * �ڋq�]�͏���ID
    */
   public String calcConditionId;

   /**
    * ���X�R�[�h
    */
   public String branchCode;

   /**
    * �ڋq�R�[�h
    */
   public String accountCode;

   /**
    * �ڋq��
    */
   public String accountName;

   /**
    * �����~�敪
    */
   public String tradingStop;

   /**
    * �M�p�V�K���]�͋敪
    */
   public String marginOpenPositionStop;

   /**
    * �敨OP�V�K���]�͋敪
    */
   public String ifoOpenPositionStop;

   /**
    * �o���]�͋敪
    */
   public String paymentStop;

   /**
    * ���̑����i���t�]�͋敪
    */
   public String otherTradingStop;

   /**
    * (�Ǐؖ������敪)<BR>
    * 0:�Ǐؖ������Ȃ� 1:�Ǐؖ���������<BR>
    */
   public String additionalDepositStop;

   /**
    * @@roseuid 41DBC92901D6
    */
   public WEB3AdminTPCalcControlInfo()
   {

   }

   /**
    * ���̃N���X�̕�����\����Ԃ��B
    * @@return String
    */
   public String toString()
   {

       StringBuffer l_sb = new StringBuffer("WEB3AdminTPCalcControlInfo={");
       l_sb.append(",calcConditionId=").append(this.calcConditionId);
       l_sb.append(",branchCode=").append(this.branchCode);
       l_sb.append("calcConditionId=").append(this.accountCode);
       l_sb.append(",accountName=").append(this.accountName);
       l_sb.append(",tradingStop=").append(this.tradingStop);
       l_sb.append(",marginOpenPositionStop=").append(this.marginOpenPositionStop);
       l_sb.append(",ifoOpenPositionStop=").append(this.ifoOpenPositionStop);
       l_sb.append(",paymentStop=").append(this.paymentStop);
       l_sb.append(",otherTradingStop=").append(this.otherTradingStop);
       l_sb.append("}");

       return l_sb.toString();

   }

}
@
