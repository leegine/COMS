head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.03.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBalanceReferenceDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�c���Ɖ�׃N���X(WEB3MutualBalanceReferenceDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06 ������ (���u) �V�K�쐬
                   2005/10/20 ��O�� (���u) �t�B�f���e�B�Ή�
*/

package webbroker3.mf.message;

/**
 * (���M�c���Ɖ��)<BR>
 * ���M�c���Ɖ�׃N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualBalanceReferenceDetailUnit extends WEB3MutualSellSwitchingProductGroup 
{    
   /**
    * (���t�\�敪)<BR>
    * ���t�\�t���O<BR>
    * <BR>
    * null:����\<BR>
    * 1:�S�����<BR>
    * 2:�戵�s��<BR>
    * 3:����s��<BR>
    * 4:�ً}��~��<BR>
    * 5:������ԊO������~��<BR>
    * 6:��W���Ԓ� <BR>
    */
   public String buyPosDiv;
   
   /**
    * (���M�����J�e�S���[�R�[�h)<BR>
    * ���M�����J�e�S���[�R�[�h<BR>
    */
   public String categoryCode;
   
   /**
    * (���M�c���Ɖ��)<BR>
    * �R���X�g���N�^
    * @@return webbroker3.mf.message.WEB3MutualBalanceReferenceDetailUnit
    * @@roseuid 41AD90EF004F
    */
   public WEB3MutualBalanceReferenceDetailUnit() 
   {
    
   }
}
@
