head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3OpeAcceptAutoLoginResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋq���肷�܂��������O�C�����ʂ�Ԃ����X�|���X�N���X(WEB3OpeAcceptAutoLoginResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/13 �e�n(SRA)
 */

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ServiceImpState;

/**
 * �i�ڋq���肷�܂��������O�C�����X�|���X�j
 * �ڋq���肷�܂��������O�C�����ʂ�Ԃ����X�|���X�N���X<BR>
 * <BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3OpeAcceptAutoLoginResponse extends WEB3GenResponse 
{
   public static  String TAGNAME = "response";
   public static  String PTYPE = "web3_ope_accept_autologin";
   
   /**
    * �ixTrade�Z�b�V����ID�j
    */
   public String xTradeSessionID;
   
   /**
    * �iCC�I�y���[�^���j
    */
   public WEB3TraderInfo ccOperatorInfo;
   
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
    * @@roseuid 406D318601C5
    */
   public WEB3OpeAcceptAutoLoginResponse() 
   {
    
   }
   
   /**
    * �R���X�g���N�^�B<BR>
    * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g����Ƀ��X�|���X�I�u�W�F�N�g�𐶐�����B<B
    * R>
    * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
    * @@roseuid 406BE17C03B8
    */
   public WEB3OpeAcceptAutoLoginResponse(WEB3OpeAcceptAutoLoginRequest l_request) 
   {
       super(l_request);    
   }
}
@
