head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.57.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticProductRefInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������Ɖ���(WEB3BondDomesticProductRefInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.200
*/
package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�����������Ɖ���)<BR>
 * �����������Ɖ���<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public class WEB3BondDomesticProductRefInfo extends Message
{

   /**
    * (����ID)<BR>
    * ����ID<BR>
    */
   public String productId;

   /**
    * (�����R�[�h)<BR>
    * �����R�[�h<BR>
    */
   public String productCode;

   /**
    * (�񍆃R�[�h)<BR>
    * �񍆃R�[�h<BR>
    */
   public String productIssueCode;

   /**
    * (�������iHOST�j)<BR>
    * �������iHOST�j<BR>
    */
   public String productNameHost;

   /**
    * (�������iWEB3))<BR>
    * �������iWEB3)<BR>
    */
   public String productNameWEB3;

   /**
    * (����P��)<BR>
    * ����P��<BR>
    */
   public String applyPrice;

   /**
    * (�戵�敪)<BR>
    * �戵�敪<BR>
    * <BR>
    * 0�F�s��<BR>
    * 2�F�ڋq<BR>
    */
   public String tradeHandleDiv;

   /**
    * (����)<BR>
    * ����<BR>
    */
   public String coupon;

   /**
    * (���s��)<BR>
    * ���s��<BR>
    */
   public Date issueDate;

   /**
    * (���ғ�)<BR>
    * ���ғ�<BR>
    */
   public Date maturityDate;

   /**
    * (�N�ԗ�����)<BR>
    * �N�ԗ�����<BR>
    */
   public String yearlyInterestPayments;

   /**
    * (������1)<BR>
    * ������1<BR>
    */
   public String couponPaymentDate1;

   /**
    * (������2)<BR>
    * ������2<BR>
    */
   public String couponPaymentDate2;

   /**
    * (�����������Ɖ���)<BR>
    * �R���X�g���N�^<BR>
    * @@roseuid 466379A50138
    */
   public WEB3BondDomesticProductRefInfo()
   {

   }
}
@
