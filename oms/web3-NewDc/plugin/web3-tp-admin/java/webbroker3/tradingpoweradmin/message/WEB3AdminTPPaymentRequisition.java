head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.41.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڋq�ʓ��������N���X(WEB3AdminTPPaymentRequition.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 �x�� �a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �ڋq�ʓ��������N���X
 */
public class WEB3AdminTPPaymentRequisition extends Message
{

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
    * ������
    */
   public Date occurredDate;

   /**
    * ���������敪
    */
   public String paymentRequisitionDivision;

   /**
    * ���ы敪
    */
   public String requisitionStatus;

   /**
    * �����������z
    */
   public double paymentRequisitionAmount;

   /**
    * �v�Z���敪
    */
   public String calclationSource;

   /**
    * @@roseuid 41DE22F90332
    */
   public WEB3AdminTPPaymentRequisition()
   {

   }

   /**
    * ���̃N���X�̕�����\����Ԃ��B
    * @@return String
    */
   public String toString()
   {

       StringBuffer l_sb = new StringBuffer("WEB3AdminTPPaymentRequisition={");
       l_sb.append("branchCode=").append(this.branchCode);
       l_sb.append("accountCode=").append(this.accountCode);
       l_sb.append(",accountName=").append(this.accountName);
       l_sb.append(",occurredDate=").append(this.occurredDate);
       l_sb.append(",requisitionStatus=").append(this.requisitionStatus);
       l_sb.append(",paymentRequisitionAmount=").append(this.paymentRequisitionAmount);
       l_sb.append(",calclationSource=").append(this.calclationSource);
       l_sb.append("}");

       return l_sb.toString();

   }

}
@
