head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.43.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPReleaseDepositAutoTransferCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ۏ؋������U�֒�~�������ʃ��N�G�X�g�N���X(WEB3AdminTPReleaseDepositAutoTransferCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 �x�� �a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �ۏ؋������U�֒�~�������ʃ��N�G�X�g�N���X
 */
public class WEB3AdminTPReleaseDepositAutoTransferCommonRequest extends WEB3GenRequest
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_release_depositautotransfer_common";

   /**
    * �ۏ؋������U�֒�~ID�ꗗ
    */
   public String[] autoTransferStopIds;

   /**
    * @@roseuid 41DBC97902B1
    */
   public WEB3AdminTPReleaseDepositAutoTransferCommonRequest()
   {

   }

   /**
    * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
    * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j
    *
    * �P�j�ۏ؋������U�֒�~ID�̃`�F�b�N
    * �ȉ��ɊY������ꍇ�u�ۏ؋������U�֒�~ID��null�v�̗�O���X���[����B
    * �E�ۏ؋������U�֒�~ID[] == null
    * �E�ۏ؋������U�֒�~ID[i] == null
    * (i��0����z��̌�����)
    * @@roseuid 41D123D00360
    */
   public void validate() throws WEB3BusinessLayerException
   {
       final String METHOD_NAME = "validate()";

       if(this.autoTransferStopIds == null)
       {
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01903, METHOD_NAME);
       }
       for(int i = 0; i < this.autoTransferStopIds.length; i++)
       {
           if(this.autoTransferStopIds[i] == null)
           {
      			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01903, METHOD_NAME);
           }
       }

   }

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DBC97902FF
    */
   public WEB3GenResponse createResponse()
   {
    return null;
   }
}
@
