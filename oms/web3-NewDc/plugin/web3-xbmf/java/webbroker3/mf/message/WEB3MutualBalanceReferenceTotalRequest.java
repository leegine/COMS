head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.06.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBalanceReferenceTotalRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�c���Ɖ�c�����v���N�G�X�g�N���X(WEB3MutualBalanceReferenceTotalRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06 ������ (���u) �V�K�쐬
Revesion History : 2007/02/03 �����F (���u) �d�l�ύX�E���f��536
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (���M�c���Ɖ�c�����v���N�G�X�g)<BR>
 * ���M�c���Ɖ�c�����v���N�G�X�g�N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualBalanceReferenceTotalRequest extends WEB3GenRequest 
{    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_balance_reference_total";
   
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412281618L;

    /**
     * (���M�E�O��MMF�\���敪)<BR>
     * ���M��O��MMF�\���敪 <BR>
     * <BR>
     * �\���Ώۂ̖������A���M��O��MMF�Ő؂�ւ��邽�߂̋敪 <BR>
     * <BR>
     * 0:���M�̂� <BR>
     * 1:�O��MMF�̂� <BR>
     * 2:���� <BR>
     * <BR>
     * ��null�̏ꍇ�A�u0:���M�̂݁v�Ƃ���<BR>
     */
    public String mutualFrgnMmfDisplayDiv;

   /**
    * @@roseuid 41D13CC1029F
    */
   public WEB3MutualBalanceReferenceTotalRequest() 
   {
    
   }
   
   /**
    * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
    *<BR>
    * @@return ���X�|���X�I�u�W�F�N�g
    */
   public WEB3GenResponse createResponse() 
   {
       return new WEB3MutualBalanceReferenceTotalResponse(this);
   }
}
@
