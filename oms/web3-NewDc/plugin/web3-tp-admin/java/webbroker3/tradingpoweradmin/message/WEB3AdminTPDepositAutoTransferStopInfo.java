head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.40.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPDepositAutoTransferStopInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@//�\�[�X �t�@@�C��: C:\\web3model-detail\\srcpath\\webbroker3\\tradingpoweradmin\\message\\WEB3AdminTPDepositAutoTransferStopInfo.java

package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �ڋq�ʕۏ؋������U�֒�~�o�^���N���X
 */
public class WEB3AdminTPDepositAutoTransferStopInfo extends Message
{

   /**
    * �a��������U�֒�~ID
    */
   public String autoTransferStopId;

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
    * ��~�J�n��
    */
   public Date transferStopStart;

   /**
    * ��~�I����
    */
   public Date transferStopEnd;

   /**
    * @@roseuid 41DBC97B00EC
    */
   public WEB3AdminTPDepositAutoTransferStopInfo()
   {

   }

   /**
    * ���̃N���X�̕�����\����Ԃ��B
    * @@return String
    */
   public String toString()
   {

       StringBuffer l_sb = new StringBuffer("WEB3AdminTPDepositAutoTransferStopInfo={");
       l_sb.append("autoTransferStopId=").append(this.autoTransferStopId);
       l_sb.append(",branchCode=").append(this.branchCode);
       l_sb.append(",accountCode=").append(this.accountCode);
       l_sb.append(",accountName=").append(this.accountName);
       l_sb.append(",transferStopStart=").append(this.transferStopStart);
       l_sb.append(",transferStopEnd=").append(this.transferStopEnd);
       l_sb.append("}");

       return l_sb.toString();

   }


}
@
