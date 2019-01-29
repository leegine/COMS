head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3CCOperatorLoginResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : CC�I�y���[�^���O�C�����ʂ�Ԃ����X�|���X�N���X(WEB3CCOperatorLoginResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/28 �e�n(SRA)
 */

package webbroker3.login.message;


import webbroker3.common.message.WEB3GenResponse;
import webbroker3.login.message.WEB3TraderInfo;
import webbroker3.login.message.WEB3InstitutionInfo;


/**
 * (CC�I�y���[�^���O�C�����X�|���X)<BR>
 * CC�I�y���[�^���O�C�����ʂ�Ԃ����X�|���X�N���X<BR>
 *<BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3CCOperatorLoginResponse extends WEB3GenResponse 
{
   public static  String TAGNAME = "response";
   public static  String PTYPE = "web3_ccoperator_login";
   
   /**
    * (xTrade�Z�b�V����ID)<BR>
    */
   public String xTradeSessionID;
   
   /**
    * (CC�I�y���[�^���)<BR>
    */
   public WEB3TraderInfo ccOperatorInfo;
   
   /**
    * (��Џ��)<BR>
    */
   public WEB3InstitutionInfo institutionInfo;
   
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
    * �R���X�g���N�^�B<BR>
    * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g����Ƀ��X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
    * @@param l_request
    * @@roseuid 4045C6C40347
    */
   public WEB3CCOperatorLoginResponse(WEB3CCOperatorLoginRequest l_request)
   {
       super(l_request);
   }
   
   /**
    * @@roseuid 4045C22803B4
    */
   public WEB3CCOperatorLoginResponse()
   {
    
   }
}
@
