head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.27.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3TraderInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : CC�I�y���[�^����xTrade�N���C�A���g�ɓn���ׂ̃��b�Z�[�W�N���X(WEB3TraderInfo.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/28 �e�n(SRA)
Revesion History    : 2007/07/23 ���n�m (���u) �d�l�ύX�E���f��No.040
*/

package webbroker3.login.message;


import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (CC�I�y���[�^���)<BR>
 * CC�I�y���[�^����xTrade�N���C�A���g�ɓn���ׂ̃��b�Z�[�W�N���X<BR>
 *<BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3TraderInfo extends Message 
{
   /**
    * �i�I�y���[�^���X�R�[�h�j<BR>
    * �I�y���[�^�̕��X�R�[�h�B
    */
   public String branchCode;
   
   /**
    * �i����ID�j
    */
   public long traderID;
   
   /**
    * (���҃R�[�h)
    */
   public String traderCode;
   
   /**
    * (xTrade���[�U��)
    */
   public String xTradeUsername;
   
   /**
    * (���Җ��i�����j)
    */
   public String nameKanji;
   
   /**
    * (���Җ��i�J�i�j)
    */
   public String nameKana;
   
   /**
    * �i��s�����ۃt���O�j<BR>
    * ��s�������\�ȃI�y���[�^���ǂ������f����t���O�B
    */
   public String accountOrderFlag;
   
   /**
    * (�ŏI���O�C������)
    */
   public Date lastLoginTime;

   /**
    * �i�����R�[�h�j<BR>
    * �����R�[�h
    */
   public String departmentCode;

   /**
    * @@roseuid 4021A20B00AB
    */
   public WEB3TraderInfo() 
   {
    
   }
}
@
