head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3OpeAcceptSlingshotResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋq���肷�܂�SS�J�ڌ��ʂ�Ԃ����X�|���X�N���X(WEB3OpeAcceptSlingshotResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/21 �e�n(SRA)
 */

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�ڋq���肷�܂�SS�J�ڃ��X�|���X�j
 * �ڋq���肷�܂�SS�J�ڌ��ʂ�Ԃ����X�|���X�N���X<BR>
 * <BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3OpeAcceptSlingshotResponse extends WEB3GenResponse 
{
   public static  String TAGNAME = "response";
   public static  String PTYPE = "web3_ope_accept_slingshot";
   
   /**
    * �i�n�b�V���l�j<BR>
    * �X�����O�V���b�g���Ɏ󂯓n���F�ؗp�̃n�b�V���l<BR>
    */
   public String hashValue;
   
   /**
    * �iCD�t�ڋq�R�[�h�j
    * CD�t�V���̌ڋq�R�[�h�B
    */
   public String acceptCodeCD;
   
   /**
    * @@roseuid 406D2D1B0290
    */
   public WEB3OpeAcceptSlingshotResponse() 
   {
    
   }
   
   /**
    * �R���X�g���N�^�B<BR>
    * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g����Ƀ��X�|���X�I�u�W�F�N�g�𐶐�����B<B
    * R>
    * @@param l_request
    * @@roseuid 406BBC6F0261
    */
   public WEB3OpeAcceptSlingshotResponse(WEB3OpeAcceptSlingshotRequest l_request) 
   {
       super(l_request);    
   }
}
@
