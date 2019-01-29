head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.59.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBalanceReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�c���Ɖ�X�|���X�N���X(WEB3MutualBalanceReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06 ������ (���u) �V�K�쐬
                   2005/10/20 ��O�� (���u) �t�B�f���e�B�Ή�
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (���M�c���Ɖ�X�|���X)<BR>
 * ���M�c���Ɖ�X�|���X�N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualBalanceReferenceResponse extends WEB3GenResponse 
{   
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_balance_reference";
   
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412281618L;

    /**
    * (�c���Ɖ��)<BR>
    * ���M�c���Ɖ�ׂ̔z��
    */
   public WEB3MutualBalanceReferenceDetailUnit[] balanceReference;
   
   /**
    * (�\���y�[�W�ԍ�)<BR>
    * �\���y�[�W�ԍ�<BR>
    * <BR>
    * ���ۂɕ\������y�[�W�ʒu���w��@@���擪�y�[�W��"1"�Ƃ���
    */
   public String pageIndex;
   
   /**
    * (���y�[�W��)<BR>
    * ���y�[�W��
    */
   public String totalPages;
   
   /**
    * (�����R�[�h��)<BR>
    * �����R�[�h��
    */
   public String totalRecords;
   
   /**
    * (���M�����J�e�S���[�ꗗ)<BR>
    * ���M�����J�e�S���[�ꗗ<BR>
    * (null�̏ꍇ�̓J�e�S���w�薳���Ƃ���)<BR>
    */
   public WEB3MutualProductCategoryUnit[] categoryList;

   /**
    * �R���X�g���N�^�B<BR>
    * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
    * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
    */
   public WEB3MutualBalanceReferenceResponse(WEB3GenRequest l_request)
   {
       super(l_request);
   }    
   
   /**
    * @@roseuid 41D13CC10196
    */
   public WEB3MutualBalanceReferenceResponse() 
   {
    
   }
}
@
