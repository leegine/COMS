head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.51.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformDetailHeaderInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �e��A���ڍ׏��N���X(WEB3InformDetailHeaderInfoUnit.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/20 ������(���u) �쐬
*/

package webbroker3.inform.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (�e��A���ڍ׏��)<BR>
 * �e��A���ڍ׏��N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3InformDetailHeaderInfoUnit extends WEB3InformDetailInfoUnit 
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3InformDetailHeaderInfoUnit.class);
   
   /**
    * (���ʃR�[�h)
    */
   public String requestNumber;
   
   /**
    * (��t����)
    */
   public Date receptionDate;
   
   /**
    * @@roseuid 41EF45B901E4
    */
   public WEB3InformDetailHeaderInfoUnit() 
   {
    
   }
   
   /**
    * (�e��A���ڍ׏��)<BR>
    * �R���X�g���N�^<BR>
    * <BR>
    * �e���ڂɁA����.�e��A���s�I�u�W�F�N�g�̓����ڂ̒l���Z�b�g����B<BR>
    * @@param l_variousInformParams - (�e��A���s)<BR>
    * �e��A���s�I�u�W�F�N�g<BR>
    * @@roseuid 41EE0D380173
    */
   public WEB3InformDetailHeaderInfoUnit(VariousInformParams l_variousInformParams) throws WEB3BaseException 
   {
       super(l_variousInformParams);
       
       final String STR_METHOD_NAME = "WEB3InformDetailHeaderInfoUnit()";
       log.entering(STR_METHOD_NAME);

       // ���ʃR�[�h
       this.requestNumber = l_variousInformParams.getRequestNumber();

       // ��t����
       this.receptionDate = l_variousInformParams.getCreatedTimestamp();
       
       log.exiting(STR_METHOD_NAME);
   }
}
@
