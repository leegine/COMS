head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.42.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPReleaseDepositAutoTransferCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ۏ؋������U�֒�~�����������N�G�X�g�N���X(WEB3AdminTPReleaseDepositAutoTransferCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 �x�� �a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �ۏ؋������U�֒�~�����������N�G�X�g�N���X
 */
public class WEB3AdminTPReleaseDepositAutoTransferCompleteRequest extends WEB3AdminTPReleaseDepositAutoTransferCommonRequest
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_release_depositautotransfer_complete";

   /**
    * �Ǘ��҃p�X���[�h
    */
   public String adminPassword;

   /**
    * @@roseuid 41DBC97A00EC
    */
   public WEB3AdminTPReleaseDepositAutoTransferCompleteRequest()
   {

   }

   /**
    * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
    * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j
    *
    * �P�j�a��������U�֒�~ID�̃`�F�b�N
    * �e�N���X.validate()���ĂԁB
    *
    * �Q�j�Ǘ��҃p�X���[�h�̃`�F�b�N
    * �ȉ��ɊY������ꍇ�u�Ïؔԍ������w��ł��B�v�̗�O���X���[����B
    * �E�Ǘ��҃p�X���[�h == null
    * @@roseuid 41C90BF4000A
    */
   public void validate() throws WEB3BusinessLayerException
   {
       final String METHOD_NAME = "validate()";
       super.validate();
  		if(adminPassword == null)
  		{
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01090, METHOD_NAME);
  		}
   }

   public WEB3GenResponse createResponse()
   {
       return new WEB3AdminTPReleaseDepositAutoTransferCompleteResponse();
   }
}
@
