head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.35.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiStreamRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p���A�g���N�G�X�g(WEB3SrvRegiStreamRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/19 �g�C�� �V�K�쐬 ���f��370�A���f��375
Revision History : 2008/06/20 ���g (���u) ���f��No.394
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.srvregi.define.WEB3SrvRegiTradingTypeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�T�[�r�X���p���A�g���N�G�X�g)<BR>
 * �T�[�r�X���p���A�g���N�G�X�g<BR>
 *
 * @@author �g�C��
 * @@version 1.0
 */
public class WEB3SrvRegiStreamRequest extends WEB3GenRequest
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiStreamRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "srvregi_stream";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200805191654L;

   /**
    * (�T�[�r�X�敪)<BR>
    * �T�[�r�X�敪<BR>
    */
   public String serviceDiv;

   /**
    * (����敪)<BR>
    * ����敪<BR>
    * 1�F���t����<BR>
    * 2�F���t�����������<BR>
    * 3�F�ژ_�����{���`�F�b�N<BR>
    */
   public String tradingType;

   /**
    * (�����R�[�h)<BR>
    * �����R�[�h<BR>
    */
   public String productCode;

   /**
    * (��ʃR�[�h)<BR>
    * ��ʃR�[�h<BR>
    */
   public String batTypeCode;

   /**
    * (������)<BR>
    * ������<BR>
    */
   public String orderNo;

   /**
    * (��n��)<BR>
    * ��n��(YYYYMMDD)<BR>
    */
   public String deliveryDate;

   /**
    * (������)<BR>
    * ������(YYYYMMDD)<BR>
    */
   public String orderBizDate;

   /**
    * (���z)<BR>
    * ���z<BR>
    */
   public String amount;

   /**
    * (�T�[�r�X���p���A�g���N�G�X�g)
    * �f�t�H���g�R���X�g���N�^<BR>
    * @@roseuid 481554D00210
    */
   public WEB3SrvRegiStreamRequest()
   {

   }

   /**
    * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
    * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
    * <BR>
    * �P�j�K�{�`�F�b�N<BR>
    * �@@�@@�T�[�r�X�敪�̃`�F�b�N<BR>
    * �@@�@@-this.�T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B<BR>
    * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@tag:   BUSINESS_ERROR_00758<BR>
    * �@@�@@-this.�T�[�r�X�敪�̌�����4���𒴂��Ă���ꍇ�A��O���X���[����B<BR>
    * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@tag:   BUSINESS_ERROR_00831<BR>
    * �@@�@@-this.�T�[�r�X�敪�����p�����ȊO�̏ꍇ�A��O���X���[����B<BR>
    * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@tag:   BUSINESS_ERROR_00882<BR>
    * <BR>
    * �@@�A����敪�̃`�F�b�N<BR>
    * �@@�@@-this.����敪==null�̏ꍇ�A��O���X���[����B<BR>
    * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@tag:   BUSINESS_ERROR_00601<BR>
    * �@@�@@-this.����敪���ȉ��̒l�i���p�j�ȊO�̏ꍇ�A��O���X���[����B<BR>
    * �@@�@@�@@�E1�F���t����<BR>
    * �@@�@@�@@�E2�F���t�����������<BR>
    * �@@�@@�@@�E3�F�ژ_�����{���`�F�b�N<BR>
    * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@tag:   BUSINESS_ERROR_00602<BR>
    * <BR>
    * �Q�j����敪���w1�F���t�����x�܂��́A����敪���w3�F�ژ_�����{���`�F�b�N�x�̏ꍇ<BR>
    * <BR>
    * �@@�@@�����R�[�h�̃`�F�b�N<BR>
    * �@@�@@-this.�����R�[�h==null�̏ꍇ�A��O���X���[����B<BR>
    * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@tag:   BUSINESS_ERROR_00079<BR>
    * �@@�@@-this.�����R�[�h�̌�����10���𒴂��Ă���ꍇ�A��O���X���[����B<BR>
    * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@tag:   BUSINESS_ERROR_00439<BR>
    * �@@�@@-this.�����R�[�h�����p�p�����ȊO�̏ꍇ�A��O���X���[����B<BR>
    * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@tag:   BUSINESS_ERROR_01067<BR>
    * <BR>
    * �@@�A��ʃR�[�h�̃`�F�b�N<BR>
    * �@@�@@-this.��ʃR�[�h==null�̏ꍇ�A��O���X���[����B<BR>
    * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@tag:   BUSINESS_ERROR_02202<BR>
    * �@@�@@-this.��ʃR�[�h�̌�����3���𒴂��Ă���ꍇ�A��O���X���[����B<BR>
    * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@tag:   BUSINESS_ERROR_03083<BR>
    * �@@�@@-this.��ʃR�[�h�����p�����ȊO�̏ꍇ�A��O���X���[����B<BR>
    * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@tag:   BUSINESS_ERROR_03084<BR>
    * <BR>
    * <BR>
    * �R�j����敪���w1�F���t�����x�܂��͎���敪���w2�F���t������������x�ꍇ<BR>
    * �@@�@@�������̃`�F�b�N<BR>
    * �@@�@@-this.������==null�̏ꍇ�A��O���X���[����B<BR>
    * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@tag:   BUSINESS_ERROR_03085<BR>
    * �@@�@@-this.�������̌�����10���𒴂��Ă���ꍇ�A��O���X���[����B<BR>
    * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@tag:   BUSINESS_ERROR_03086<BR>
    * �@@�@@-this.�����������p�����ȊO�̏ꍇ�A��O���X���[����B<BR>
    * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@tag:   BUSINESS_ERROR_03087<BR>
    * <BR>
    * <BR>
    * �S�j����敪���w1�F���t�����x�̏ꍇ<BR>
    * �@@�@@��n���̃`�F�b�N<BR>
    * �@@�@@-this.��n��==null�̏ꍇ�A��O���X���[����B<BR>
    * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@tag:   BUSINESS_ERROR_01079<BR>
    * �@@�@@-this.��n�����s���ȓ��t�̏ꍇ�A��O���X���[����B<BR>
    * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@tag:   BUSINESS_ERROR_02865<BR>
    * <BR>
    * �@@�A�������̃`�F�b�N<BR>
    * �@@�@@-this.������==null�̏ꍇ�A��O���X���[����B<BR>
    * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@tag:   BUSINESS_ERROR_00406<BR>
    * �@@�@@-this.���������s���ȓ��t�̏ꍇ�A��O���X���[����B<BR>
    * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@tag:   BUSINESS_ERROR_02160<BR>
    * <BR>
    * �@@�B���z�̃`�F�b�N<BR>
    * �@@�@@-this.���z==null�̏ꍇ�A��O���X���[����B<BR>
    * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@tag:   BUSINESS_ERROR_03088<BR>
    * �@@�@@-this.���z�̌�����12���𒴂��Ă���ꍇ�A��O���X���[����B<BR>
    * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@tag:   BUSINESS_ERROR_03089<BR>
    * �@@�@@-this.���z�����p�����ȊO�̏ꍇ�A��O���X���[����B<BR>
    * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@tag:   BUSINESS_ERROR_03090<BR>
    * @@throws WEB3BaseException
    * @@roseuid 48152DB600F1
    */
   public void validate() throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "validate()";
       log.entering(STR_METHOD_NAME);

       //-this.�T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B
       if (this.serviceDiv == null)
       {
           log.debug("�T�[�r�X�敪�����w��ł��B");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00758,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�T�[�r�X�敪�����w��ł��B");
       }

       // -this.�T�[�r�X�敪�̌�����4���𒴂��Ă���ꍇ�A��O���X���[����B
       if (this.serviceDiv.length() > 4)
       {
           log.debug("�T�[�r�X�敪�̌������s���ł��B�B");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00831,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�T�[�r�X�敪�̌������s���ł��B�B");
       }

       //-this.�T�[�r�X�敪�����p�����ȊO�̏ꍇ�A��O���X���[����B
       if (!WEB3StringTypeUtility.isDigit(this.serviceDiv))
       {
           log.debug("�T�[�r�X�敪�����l�ȊO�̒l�ł��B");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00882,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�T�[�r�X�敪�����l�ȊO�̒l�ł��B");
       }

       // �A����敪�̃`�F�b�N
       //-this.����敪==null�̏ꍇ�A��O���X���[����B
       if (this.tradingType == null)
       {
           log.debug("����敪�����w��ł��B");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00601,
               getClass().getName() + "." + STR_METHOD_NAME,
               "����敪�����w��ł��B");
       }

        //   -this.����敪���ȉ��̒l�i���p�j�ȊO�̏ꍇ�A��O���X���[����B
        //   �E1�F���t����
        //   �E2�F���t�����������
        //   �E3�F�ژ_�����{���`�F�b�N
       if (!WEB3SrvRegiTradingTypeDef.BUY_PROCESS.equals(this.tradingType)
           && !WEB3SrvRegiTradingTypeDef.BUY_ORDER_CANCEL_PROCESS.equals(this.tradingType)
           && !WEB3SrvRegiTradingTypeDef.PROSPECTUS_CHECK.equals(this.tradingType))
       {
           log.debug("����敪�����݂��Ȃ��R�[�h�l�ł��B");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00602,
               getClass().getName() + "." + STR_METHOD_NAME,
               "����敪�����݂��Ȃ��R�[�h�l�ł��B");
       }

        //   �Q�j����敪���w1�F���t�����x�܂��́A����敪���w3�F�ژ_�����{���`�F�b�N�x�̏ꍇ
        //   �@@�����R�[�h�̃`�F�b�N
       if (WEB3SrvRegiTradingTypeDef.BUY_PROCESS.equals(this.tradingType)
           || WEB3SrvRegiTradingTypeDef.PROSPECTUS_CHECK.equals(this.tradingType))
       {
           //-this.�����R�[�h==null�̏ꍇ�A��O���X���[����B
           if (this.productCode == null)
           {
               log.debug("�����R�[�h�����w��ł��B");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "�����R�[�h�����w��ł��B");
           }
           //-this.�����R�[�h�̌�����10���𒴂��Ă���ꍇ�A��O���X���[����B
           if (this.productCode.length() > 10)
           {
               log.debug("�����R�[�h�̃T�C�Y���s���ł��B");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00439,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "�����R�[�h�̃T�C�Y���s���ł��B");
           }
           //-this.�����R�[�h�����p�p�����ȊO�̏ꍇ�A��O���X���[����B
           if (!WEB3StringTypeUtility.isLetterOrDigit(this.productCode))
           {
               log.debug("�����R�[�h�̓��͂��s���ł��B");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "�����R�[�h�̓��͂��s���ł��B");
           }

           //   �A��ʃR�[�h�̃`�F�b�N
           //   -this.��ʃR�[�h==null�̏ꍇ�A��O���X���[����B
           if (this.batTypeCode == null)
           {
               log.debug("��ʃR�[�h�����w��ł��B");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_02202,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "��ʃR�[�h�����w��ł��B");
           }
           //   -this.��ʃR�[�h�̌�����3���𒴂��Ă���ꍇ�A��O���X���[����B
           if (this.batTypeCode.length() > 3)
           {
               log.debug("��ʃR�[�h�̌�����3�����z���Ă��܂��B");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_03083,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "��ʃR�[�h�̌�����3�����z���Ă��܂��B");
           }
           //   -this.��ʃR�[�h�����p�����ȊO�̏ꍇ�A��O���X���[����B
           if (!WEB3StringTypeUtility.isDigit(this.batTypeCode))
           {
               log.debug("��ʃR�[�h�����p�����ȊO�̒l�ł��B");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_03084,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "��ʃR�[�h�����p�����ȊO�̒l�ł��B");
           }
       }
       //�R�j����敪���w1�F���t�����x�܂��͎���敪���w2�F���t������������x�ꍇ
       //�@@�������̃`�F�b�N
       if (WEB3SrvRegiTradingTypeDef.BUY_PROCESS.equals(this.tradingType)
           || WEB3SrvRegiTradingTypeDef.BUY_ORDER_CANCEL_PROCESS.equals(this.tradingType))
       {
           //-this.������==null�̏ꍇ�A��O���X���[����B
           if (this.orderNo == null)
           {
               log.debug("�����������w��ł��B");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_03085,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "�����������w��ł��B");
           }
           // -this.�������̌�����10���𒴂��Ă���ꍇ�A��O���X���[����B
           if (this.orderNo.length() > 10)
           {
               log.debug("�������̌�����10���𒴂��Ă��܂��B");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_03086,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "�������̌�����10���𒴂��Ă��܂��B");
           }
           // -this.�����������p�����ȊO�̏ꍇ�A��O���X���[����B
           if (!WEB3StringTypeUtility.isDigit(this.orderNo))
           {
               log.debug("�����������p�����ȊO�̒l�ł��B");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_03087,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "�����������p�����ȊO�̒l�ł��B");
           }
       }

        //   �S�j����敪���w1�F���t�����x�̏ꍇ
       if (WEB3SrvRegiTradingTypeDef.BUY_PROCESS.equals(this.tradingType))
       {
            //   �@@��n���̃`�F�b�N
            //    -this.��n��==null�̏ꍇ�A��O���X���[����B
           if (this.deliveryDate == null)
           {
               log.debug("��n�������w��ł��B");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_01079,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "��n�������w��ł��B");
           }
            //-this.��n�����s���ȓ��t�̏ꍇ�A��O���X���[����B
           if (this.deliveryDate.length() != 8
               || WEB3DateUtility.getDate(this.deliveryDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD) == null)
           {
               log.debug("��n�����s���ł��B");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_02865,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "��n�����s���ł��B");
           }

           //�A�������̃`�F�b�N
           //    -this.������==null�̏ꍇ�A��O���X���[����B
           if (this.orderBizDate == null)
           {
               log.debug("�����������w��ł��B");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00406,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "�����������w��ł��B");
           }

           //-this.���������s���ȓ��t�̏ꍇ�A��O���X���[����B
           if (this.orderBizDate.length() != 8
               || WEB3DateUtility.getDate(this.orderBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD) == null)
           {
               log.debug("�������G���[�B");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_02160,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "�������G���[�B");
           }

            //�B���z�̃`�F�b�N
            //    -this.���z==null�̏ꍇ�A��O���X���[����B
           if (this.amount == null)
           {
               log.debug("���z�����w��ł��B");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_03088,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "���z�����w��ł��B");
           }

            //    -this.���z�̌�����12���𒴂��Ă���ꍇ�A��O���X���[����B
           if (this.amount.length() > 12)
           {
               log.debug("���z�̌�����12���𒴂��Ă��܂��B");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_03089,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "���z�̌�����12���𒴂��Ă��܂��B");
           }

            //    -this.���z�����p�����ȊO�̏ꍇ�A��O���X���[����B
           if (!WEB3StringTypeUtility.isNumber(this.amount))
           {
               log.debug("���z�����p�����ȊO�̒l�ł��B");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_03090,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "���z�����p�����ȊO�̒l�ł��B");
           }
       }
       log.exiting(STR_METHOD_NAME);
   }

   /**
    * (create���X�|���X)
    * �T�[�r�X���p���A�g���X�|���X�𐶐����ĕԋp����B
    * @@return WEB3GenResponse
    * @@roseuid 4815564302DC
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3SrvRegiStreamResponse(this);
   }
}@
