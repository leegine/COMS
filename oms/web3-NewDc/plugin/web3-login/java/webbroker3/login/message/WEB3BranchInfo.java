head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.27.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3BranchInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : ���X����xTrade�N���C�A���g�ɓn���ׂ̃��b�Z�[�W�N���X(WEB3BranchInfo.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/28 �e�n(SRA)
 */

package webbroker3.login.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���X���)<BR>
 * <BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3BranchInfo extends Message 
{
   
   /**
    * (���XID)
    */
   public long branchID;
   
   /**
    * (���X�R�[�h)
    */
   public String branchCode;
   
   /**
    * (���X��)
    */
   public String branchName;
   
   /**
    * @@roseuid 40762E2F01F6
    */
   public WEB3BranchInfo() 
   {
    
   }
}
@
