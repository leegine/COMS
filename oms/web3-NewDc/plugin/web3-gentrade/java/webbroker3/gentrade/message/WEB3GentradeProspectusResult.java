head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeProspectusResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ژ_�����{���`�F�b�N���ʃN���X(WEB3GentradeProspectusResult)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 ����(�r�q�`) �V�K�쐬
*/
package webbroker3.gentrade.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �ژ_�����{���`�F�b�N���ʃN���X
 */
public class WEB3GentradeProspectusResult extends Message 
{
   
   /**
    * �`�F�b�N����<br />
    * <br />
    * 0�F �{����<br />
    * 1�F �{������<br />
    * 2�F �{�����ρi��Q���j<br />
    * @@see WEB3GentradeBatoCheckResultDef
    */
   public String checkResult;
   
   /**
    * �`�F�b�N���ʂ��h�{�����ρh�̏ꍇ�A�ژ_�����\���̍ۂɎg�p����URL
    */
   public String url;
   
   /**
    * �n�b�V���l
    */
   public String hashValue;
   
   /**
    * �R���X�g���N�^
    * @@roseuid 4211C29A0122
    */
   public WEB3GentradeProspectusResult() 
   {
   }
}
@
