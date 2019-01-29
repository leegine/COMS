head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptAutoLoginResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋq�������O�C�����ʂ�Ԃ����X�|���X�N���X(WEB3AcceptAutoLoginResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/13 �e�n(SRA)
 */

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ServiceImpState;

/**
 * �i�ڋq�������O�C�����X�|���X�j
 * �ڋq�������O�C�����ʂ�Ԃ����X�|���X�N���X<BR>
 * <BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3AcceptAutoLoginResponse extends WEB3GenResponse 
{
   public static  final String TAGNAME = "response";
   public static  final String PTYPE = "web3_accept_autologin";
   
   /**
    * (xTrade�Z�b�V����ID)
    */
   public String xTradeSessionID;
   
   /**
    * �i�ڋq���j
    */
   public WEB3AcceptInfo acceptInfo;
   
   /**
    * �i��Џ��j
    */
   public WEB3InstitutionInfo institutionInfo;
   
   /**
    * �i���X���j
    */
   public WEB3BranchInfo branchInfo;
   
   /**
    * �i�T�[�r�X���{��ԁj
    */
   public WEB3ServiceImpState serviceImpState;
   
   /**
    * @@roseuid 406919F7030C
    */
   public WEB3AcceptAutoLoginResponse() 
   {
    
   }
   
   /**
    * �R���X�g���N�^�B<BR>
    * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g����Ƀ��X�|���X�I�u�W�F�N�g�𐶐�����B<B
    * R>
    * @@param l_request
    * @@roseuid 4062B41401E4
    */
   public WEB3AcceptAutoLoginResponse(WEB3AcceptAutoLoginRequest l_request) 
   {
       super(l_request);    
   }
}
@
