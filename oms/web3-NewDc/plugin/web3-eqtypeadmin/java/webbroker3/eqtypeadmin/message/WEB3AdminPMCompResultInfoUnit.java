head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMCompResultInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��r���ʏ�� (WEB3AdminPMCompResultInfoUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.util.WEB3LogUtility;

/**
 * �i��r���ʏ��j<BR>
 * <BR>
 * ��r���ʏ��N���X<BR>
 * <BR>
 * WEB3AdminPMCompResultInfoUnit<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMCompResultInfoUnit extends WEB3AdminPMProductCondConfigCommon
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMCompResultInfoUnit.class);

   /**
    * �i�S����v�t���O�j<BR>
    * <BR>
    * �S����v�t���O<BR>
    * <BR>
    * false�F�@@�s��v<BR>
    * true�F�@@��v<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * allAgreementFlag<BR>
    * <BR>
    * false: FALSE<BR>
    * true: TRUE<BR>
    * <BR>
    */
   public boolean allAgreementFlag = true;



   /**
    * (�R���X�g���N�^)<BR>
    * <BR>
    * �R���X�g���N�^<BR>
    * <BR>
    * Constructor<BR>
    * <BR>
    * @@roseuid 4191B26F0115
    */
   public WEB3AdminPMCompResultInfoUnit()
   {

   }
}
@
