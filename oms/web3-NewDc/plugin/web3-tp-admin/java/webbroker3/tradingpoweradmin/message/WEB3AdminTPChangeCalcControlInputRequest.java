head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.44.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeCalcControlInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͐���@@�\�ύX���͉�ʃ��N�G�X�g�N���X(WEB3AdminTPChangeCalcControlInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 �x�� �a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �]�͐���@@�\�ύX���͉�ʃ��N�G�X�g�N���X
 */
public class WEB3AdminTPChangeCalcControlInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_change_calccontrol_input";

   /**
    * �ڋq�]�͏���ID
    */
   public String calcConditionId;

   /**
    * @@roseuid 41DBC927036C
    */
   public WEB3AdminTPChangeCalcControlInputRequest()
   {

   }

   /**
    * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
    * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j
    *
    * �P�j�ڋq�]�͏���ID�̃`�F�b�N
    * �ȉ��̃`�F�b�N���s���B
    * �ڋq�]�͏���ID���ȉ��̏����ɊY������ꍇ�A
    * �u�ڋq�]�͏���ID��null�v�̗�O���X���[����B
    * �E�ڋq�]�͏���ID == null
    * @@roseuid 41CFE5690372
    */
   public void validate() throws WEB3BusinessLayerException
   {
  		final String METHOD_NAME = "validate()";

   		//�Q�j�a��،��]�����敪�̃`�F�b�N
   		if(this.calcConditionId == null)
   		{
   			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01893, METHOD_NAME);
   		}
   }

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DBC92703AB
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3AdminTPChangeCalcControlInputResponse();
   }


}
@
