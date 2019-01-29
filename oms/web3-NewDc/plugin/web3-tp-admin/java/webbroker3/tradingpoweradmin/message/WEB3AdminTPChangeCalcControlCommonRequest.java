head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.40.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeCalcControlCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͐���@@�\�ύX���ʃ��N�G�X�g�N���X(WEB3AdminTPChangeCalcControlCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 �x�� �a��(FLJ) �V�K�쐬
Revision History : 2007/07/26 ��іQ (���u) ���f���FNo.006
Revision History : 2007/09/12 �И��� (���u) ���f���FNo.015
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * �]�͐���@@�\�ύX���ʃ��N�G�X�g�N���X
 */
public class WEB3AdminTPChangeCalcControlCommonRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPChangeCalcControlCommonRequest.class);

    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_change_calccontrol_common";

    /**
    * �ڋq�]�͏���ID
    */
   public String calcConditionId;

   /**
    * �����~�敪
    */
   public String tradingStop;

   /**
    * (�M�p�V�K���]�͋敪)
    */
   public String marginOpenPositionStop;

   /**
    * (�敨OP�V�K���]�͋敪)
    */
   public String ifoOpenPositionStop;

   /**
    * (�o���]�͋敪)
    */
   public String paymentStop;

   /**
    * (���̑����i���t�]�͋敪)
    */
   public String otherTradingStop;

   /**
    * (�Ǐؖ������敪)<BR>
    * 0:�Ǐؖ������Ȃ� 1:�Ǐؖ���������<BR>
    */
   public String additionalDepositStop;

   /**
    * @@roseuid 41DBC928009E
    */
   public WEB3AdminTPChangeCalcControlCommonRequest()
   {

   }

   /**
    * (validate)<BR>
    * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
    * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
    * <BR>
    * �P�j�ڋq�]�͏���ID�̃`�F�b�N<BR>
    * �ȉ��̃`�F�b�N���s���B<BR>
    * �ڋq�]�͏���ID���ȉ��̏����ɊY������ꍇ�A<BR>
    * �u�ڋq�]�͏���ID�G���[�v�̗�O���X���[����B<BR>
    * �E�ڋq�]�͏���ID == null<BR>
    * �E�ڋq�]�͏���ID != ���l<BR>
    *
    * �Q�j�o���]�͋敪�̃`�F�b�N<BR>
    * �ȉ��̃`�F�b�N���s���B<BR>
    * �o���]�͋敪���ȉ��̏����ɊY������ꍇ�A<BR>
    * �u�o���]�͋敪��null�v�̗�O���X���[����B<BR>
    * �E�o���]�͋敪 == null<BR>
    * <BR>
    * �R�j���̑����i���t�]�͋敪�̃`�F�b�N<BR>
    * �ȉ��̃`�F�b�N���s���B<BR>
    * ���̑����i���t�]�͋敪���ȉ��̏����ɊY������ꍇ�A<BR>
    * �u���̑����i���t�]�͋敪��null�v�̗�O���X���[����B<BR>
    * �E���̑����i���t�]�͋敪 == null<BR>
    * <BR>
    * �S�j�����~�敪�̃`�F�b�N<BR>
    * �ȉ��̃`�F�b�N���s���B<BR>
    * �����~�敪���ȉ��̏����ɊY������ꍇ�A<BR>
    * �u�����~�敪��null�v�̗�O���X���[����B<BR>
    * �E�����~�敪 == null<BR>
    * <BR>
    * @@throws WEB3BusinessLayerException
    * @@roseuid 41C6B52B0086
    */
   public void validate() throws WEB3BusinessLayerException
   {
       final String METHOD_NAME = "validate()";

       if(this.calcConditionId == null)
       {
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01893, METHOD_NAME);
       }
       //�ǉ�
       if(this.tradingStop == null)
       {
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01894, METHOD_NAME);
       }
       if(this.paymentStop == null)
       {
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01897, METHOD_NAME);
       }
       if(this.otherTradingStop == null)
       {
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01898, METHOD_NAME);
       }
   }

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DBC92800EC
    */
   public WEB3GenResponse createResponse()
   {
    return null;
   }
}
@
