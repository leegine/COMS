head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMItemInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ڋ敪��� (WEB3AdminPMItemInfoUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;

/**
 * (���ڋ敪���)<BR>
 * <BR>
 * ���ڋ敪���N���X<BR>
 * <BR>
 * WEB3AdminPMItemInfoUnit<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMItemInfoUnit extends Message
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMItemInfoUnit.class);

   /**
    * �i�區�ڋ敪�j<BR>
    * <BR>
    * �區�ڋ敪<BR>
    * <BR>
    * ����`�l�ɂ��Ă�DB���C�A�E�g<BR>
    * �@@�u�������������ݒ�e�[�u���d�l.xls�v�Q��<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * largeItemDiv<BR>
    * <BR>
    * ��Refer to the DB layout "eqtype_product_condition_table.xls" about defined
    * values<BR>
    * <BR>
    */
   public String largeItemDiv;

   /**
    * �i�����ڋ敪�ꗗ�j<BR>
    * <BR>
    * �區�ڋ敪�ɑ����鏬���ڋ敪�̈ꗗ<BR>
    * ���區�ڋ敪�ɑΉ����鏬���ڋ敪�z��Ƃ��ăZ�b�g�B<BR>
    * <BR>
    * ����`�l�ɂ��Ă�DB���C�A�E�g<BR>
    * �@@�u�������������ݒ�e�[�u���d�l.xls�v�Q��<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * smallItemDivList<BR>
    * <BR>
    * List of smallItemDiv that belongs to largeItemDiv<BR>
    * ��Set as an array of smallItemDiv corresponding to largeItemDiv<BR>
    * <BR>
    * ��Refer to the DB layout "eqtype_product_condition_table.xls" about defined
    * values<BR>
    * <BR>
    */
   public String[] smallItemDivList;

   /**
    * �i���ڋ敪���j<BR>
    * <BR>
    * �����̑區�ڋ敪�A�����ڋ敪�ꗗ�����g�̃v���p�e�B��
    * �Z�b�g����R���X�g���N�^�B<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * Constructor that sets largeItemDiv and smallItemDiv list into its own
    * property<BR>
    * <BR>
    * @@param l_largeItemDiv - �i�區�ڋ敪�j<BR>
    * <BR>
    * �區�ڋ敪<BR>
    * <BR>
    * ����`�l�ɂ��Ă�DB���C�A�E�g<BR>
    * �@@�u�������������ݒ�e�[�u���d�l.xls�v�Q��<BR>
    * <BR>
    * ----<English>---------<BR>
    * <BR>
    * l_largeItemDiv<BR>
    * <BR>
    * ��Refer to the DB layout "eqtype_product_condition_table.xls" about defined
    * values<BR>
    * <BR>
    * @@param l_strSmallItemDivList - �i�����ڋ敪�ꗗ�j<BR>
    * <BR>
    * �����ڋ敪�ꗗ<BR>
    * <BR>
    * ����`�l�ɂ��Ă�DB���C�A�E�g<BR>
    * �@@�u�������������ݒ�e�[�u���d�l.xls�v�Q��<BR>
    * <BR>
    * ----<English>-------<BR>
    * <BR>
    * l_strSmallItemDivList<BR>
    * <BR>
    * ��Refer to the DB layout "eqtype_product_condition_table.xls" about defined
    * values<BR>
    * <BR>
    * @@roseuid 41980A0A019D
    */
   public WEB3AdminPMItemInfoUnit(String l_largeItemDiv, String[] l_strSmallItemDivList)
   {
        this.largeItemDiv = l_largeItemDiv;
        this.smallItemDivList = l_strSmallItemDivList;
   }

   /**
    * (���ڋ敪���)<BR>
    * <BR>
    * �R���X�g���N�^<BR>
    * <BR>
    * Constructor<BR>
    * <BR>
    * @@roseuid 41944EBA00EC
    */
   public WEB3AdminPMItemInfoUnit()
   {

   }
}
@
