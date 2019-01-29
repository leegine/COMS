head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.43.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPStopDepositAutoTransferCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ۏ؋������U�֒�~�o�^�������N�G�X�g�N���X(WEB3AdminTPStopDepositAutoTransferCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 �x�� �a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �ۏ؋������U�֒�~�o�^�������N�G�X�g�N���X
 */
public class WEB3AdminTPStopDepositAutoTransferCompleteRequest extends WEB3AdminTPStopDepositAutoTransferCommonRequest
{

    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_stop_depositautotransfer_complete";

   /**
    * �Ǘ��҃p�X���[�h
    */
   public String adminPassword;

   /**
    * @@roseuid 41DBC97A0040
    */
   public WEB3AdminTPStopDepositAutoTransferCompleteRequest()
   {

   }

   /**
    * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
    * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j
    *
    * �P�j�o�^���e�`�F�b�N
    *  �e�N���X.validate()���ĂԁB
    *
    * �Q�j�Ǘ��҃p�X���[�h�̃`�F�b�N
    * �ȉ��ɊY������ꍇ�u�Ïؔԍ������w��ł��B�v�̗�O���X���[����B
    * �E�Ǘ��҃p�X���[�h == null
    *
    *
    * @@roseuid 41C6B73D0038
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

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DBC97803AB
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3AdminTPStopDepositAutoTransferCompleteResponse();
   }
}
@
