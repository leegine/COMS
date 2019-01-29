head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AdministratorInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҏ���xTrade�N���C�A���g�ɓn���ׂ̃��b�Z�[�W�N���X(WEB3AdministratorInfo.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/28 �e�n(SRA)
Revesion History    : 2006/09/1 �h�C (���u)�d�l�ύX�E���f��031
 */

package webbroker3.login.message;


import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * �i�Ǘ��ҏ��j
 * �Ǘ��ҏ���xTrade�N���C�A���g�ɓn���ׂ̃��b�Z�[�W�N���X<BR>
 *<BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3AdministratorInfo extends Message 
{
   
   /**
    * �i�Ǘ���ID�j
    */
   public long administratorID;
   
   /**
    * �i�Ǘ��҃R�[�h�j
    */
   public String administratorCode;
   
   /**
    * �ixTrade���[�U���j
    */
   public String xTradeUsername;
   
   /**
    * �i�Ǘ��Җ��j
    */
   public String administratorName;
   
   /**
    * �i�������x���j
    */
   public String permissionLevel;
   
   /**
    * �i�ŏI���O�C�������j
    */
   public Date lastLoginTime;
   
   /**
    * �i�S���X���t���O�j
    *  �S���X����=true�^�����X�̂݋���=false
    */
   public boolean allBranchPermissionFlag;
   
   /**
    * �iDIR�Ǘ��҃t���O�j
    *  0 �F�@@DIR�Ǘ��� 
    *  1 �F�@@�ʏ�Ǘ��� 
    *  2 �F�@@�ʏ�Ǘ���(�\����) 
    *  3 �F�@@�ʏ�Ǘ���(���F��)
    */
   public String dirAdminFlag;
   
   /**
    * @@roseuid 408F4C1F006A
    */
   public WEB3AdministratorInfo() 
   {
    
   }
}
@
