head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3SetAccountResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋq���肷�܂����ʂ�Ԃ����X�|���X�N���X(WEB3SetAccountResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/13 �e�n(SRA)
 */

package webbroker3.login.message;


import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ServiceImpState;


/**
 * (�ڋq���肷�܂����X�|���X)<BR>
 * �ڋq���肷�܂����ʂ�Ԃ����X�|���X�N���X<BR>
 *<BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3SetAccountResponse extends WEB3GenResponse
{
   public static  String TAGNAME = "response";
   public static  String PTYPE = "web3_login_set_account";
   
   /**
    * (xTrade�Z�b�V����ID)
    */
   public String xTradeSessionID;
   
   /**
    * (�ڋq���)
    */
   public WEB3AcceptInfo acceptInfo;
   
   /**
    * �i���X���j<BR>
    * ���肷�܂����ڋq�̕��X���B
    */
   public WEB3BranchInfo branchInfo;
   
   /**
    * (�T�[�r�X���{���)
    */
   public WEB3ServiceImpState serviceImpState;
   
   /**
    * (�擪���ID)
    * ���[�U�w��̐擪��ʂ�\��ID<BR>
    */
   public String topPageID;
   
   /**
    * �R���X�g���N�^�B<BR>
    * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g����Ƀ��X�|���X�I�u�W�F�N�g�𐶐�����B
    * @@param l_request
    * @@roseuid 4045C9490078
    */
   public WEB3SetAccountResponse(WEB3SetAccountRequest l_request)
   {
       super(l_request);
   }
   
   /**
    * @@roseuid 4045C89F02DA
    */
   public WEB3SetAccountResponse()
   {
    
   }
}
@
