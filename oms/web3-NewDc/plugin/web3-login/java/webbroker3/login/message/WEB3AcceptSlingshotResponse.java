head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptSlingshotResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋqSS�J�ڌ��ʂ�Ԃ����X�|���X�N���X(WEB3AcceptSlingshotResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/21 �e�n(SRA)
 */

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�ڋqSS�J�ڃ��X�|���X�j
 * �ڋqSS�J�ڌ��ʂ�Ԃ����X�|���X�N���X<BR>
 * <BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3AcceptSlingshotResponse extends WEB3GenResponse 
{
   public static  final String TAGNAME = "response";
   public static  final String PTYPE = "web3_accept_slingshot";
   
   /**
    * (xTrade�Z�b�V����ID)<BR>
    * �p�X���[�h�ύX���K�v�ȏꍇ�̂ݗL���B<BR>
    * �p�X���[�h�ύX�v���́A���̃Z�b�V�����ɑ΂��Ĕ��s����B<BR>
    */
   public String xTradeSessionID;
   
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
    * (�p�X���[�h�ύX�t���O)<BR>
    * ���O�C�����p�X���[�h�ύX�̗L����\���t���O<BR>
    * �@@"0"�F�ύX�Ȃ�<BR>
    * �@@"1"�F�ύX�K�v<BR>
    */
   public String passwordChangeFlag;
   
   /**
    * (�p�X���[�h�ŏ���)<BR>
    */
   public String passwordMinLength;
   
   /**
    * (�p�X���[�h�ő咷)<BR>
    */
   public String passwordMaxLength;
   
   /**
    * (�p�X���[�h�`�F�b�N����)<BR>
    *  <BR>
    * 1�F�@@�����̂� <BR>
    * 2�F�@@�p���� <BR>
    * 3�F�@@�p�������� <BR>
    * <BR>
    * �� WEB3CodeCheckModeDef�ɂĒ�`��<BR> 
    */
   public String passwordCheckMethod;
   
   /**
    * @@roseuid 406906D20260
    */
   public WEB3AcceptSlingshotResponse() 
   {
    
   }
   
   /**
    * �R���X�g���N�^�B<BR>
    * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g����Ƀ��X�|���X�I�u�W�F�N�g�𐶐�����B<B
    * R>
    * @@param l_request
    * @@roseuid 40627FFB0138
    */
   public WEB3AcceptSlingshotResponse(WEB3AcceptSlingshotRequest l_request) 
   {
       super(l_request);    
   }
}
@
