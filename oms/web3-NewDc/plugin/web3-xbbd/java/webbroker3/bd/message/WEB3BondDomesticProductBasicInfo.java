head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.45.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticProductBasicInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������������{���(WEB3BondDomesticProductBasicInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.200
*/
package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (������������{���)<BR>
 * ������������{���<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public class WEB3BondDomesticProductBasicInfo extends Message
{

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
    * (�������iHOST))<BR>
    * �������iHOST)<BR>
    */
   public String productNameHost;

   /**
    * (��ʃR�[�h)<BR>
    * ��ʃR�[�h<BR>
    */
   public String bondCategCode;

   /**
    * (���s����)<BR>
    * ���s����<BR>
    */
   public String[] issueCouponType;

   /**
    * (���s��)<BR>
    * ���s��<BR>
    */
   public Date issueDate;

   /**
    * (����P��)<BR>
    * ����P��<BR>
    */
   public String applyPrice;

   /**
    * (����)<BR>
    * ����<BR>
    */
   public String coupon;

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
    * (���ғ�)<BR>
    * ���ғ�<BR>
    */
   public Date maturityDate;

   /**
    * (����J�n���iSONAR�j)<BR>
    * ����J�n���iSONAR�j<BR>
    */
   public Date recruitStartDateSONAR;

   /**
    * (����I�����iSONAR�j)<BR>
    * ����I�����iSONAR�j<BR>
    */
   public Date recruitEndDateSONAR;

   /**
    * (������������{���)<BR>
    * �R���X�g���N�^<BR>
    * @@roseuid 466379500138
    */
   public WEB3BondDomesticProductBasicInfo()
   {

   }
}
@
