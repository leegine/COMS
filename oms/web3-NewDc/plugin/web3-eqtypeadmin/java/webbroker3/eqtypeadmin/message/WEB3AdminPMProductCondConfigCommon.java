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
filename	WEB3AdminPMProductCondConfigCommon.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ݒ苤�ʏ�� (WEB3AdminPMProductCondConfigCommon.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;
import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;

/**
 * (���������ݒ苤�ʏ��)<BR>
 * <BR>
 * ���������ݒ苤�ʏ��N���X<BR>
 * <BR>
 * WEB3AdminPMProductCondConfigCommon<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMProductCondConfigCommon extends Message
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMProductCondConfCompleteRequest.class);

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
    * �i�����ڋ敪�j<BR>
    * <BR>
    * ����`�l�ɂ��Ă�DB���C�A�E�g<BR>
    * �@@�u�������������ݒ�e�[�u���d�l.xls�v�Q��<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * smallItemDiv<BR>
    * <BR>
    * ��Refer to the DB layout "eqtype_product_condition_table.xls" about defined
    * values<BR>
    * <BR>
    */
   public String smallItemDiv;

   /**
    * @@roseuid 41FA2A060129
    */
   public WEB3AdminPMProductCondConfigCommon()
   {

   }
}
@
