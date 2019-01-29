head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.01.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeHistoryDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������ꗗ�t�@@�C���_�E�����[�h���N�G�X�g(WEB3HistoryTradeHistoryDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/30 ������(���u) �V�K�쐬
*/

package webbroker3.tradehistory.message;

import java.util.Date;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.tradehistory.define.WEB3TradeHistoryProductDivDef;

/**
 * (��������ꗗ�t�@@�C���_�E�����[�h���N�G�X�g)
 * ��������ꗗ�t�@@�C���_�E�����[�h���N�G�X�g�N���X
 * 
 * @@author ������
 * @@version 1.0
 */
public class WEB3HistoryTradeHistoryDownloadRequest extends WEB3GenRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B 
     */
    private  static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(
            WEB3HistoryTradeHistoryDownloadRequest.class); 
  
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "tradeHistory_tradeHistoryDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511301710L;
     
    /**
     * (�\������From)<BR>
     * �\������From<BR>
     * (YYYYMMDD)<BR>
     */
    public String listStartDate;
    
    /**
     * (�\������To)<BR>
     * �\������To<BR>
     * (YYYYMMDD)<BR>
     */
    public String listEndDate;
    
    /**
     * (���i�敪)<BR>
     * A�F�@@�S���i<BR>
     * B�F�@@�����E�M�p<BR>
     * C�F�@@����<BR>
     * D�F�@@�M�p<BR>
     * E�F�@@�敨�E�I�v�V����<BR>
     * F�F�@@�敨<BR>
     * G�F�@@�I�v�V����<BR>
     * H�F�@@���M�E�ݓ�<BR>
     * I�F�@@���o��<BR>
     * J�F�@@���n�v��<BR>
     * K�F�@@�O������<BR>
     * <BR>
     */
    public String commodityType;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * (��������ꗗ�t�@@�C���_�E�����[�h���N�G�X�g)<BR>
     * @@roseuid 41368C40004F
     */
    public WEB3HistoryTradeHistoryDownloadRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�\�����ԃ`�F�b�N<BR>
     * �@@this.�\������From != null ���� this.�\������To != null�̏ꍇ�́A<BR>
     * �@@�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P�|�P�jthis.�\������From��Date�^�ɕϊ����A�G���[�����������ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�\������(��)���t�G���[�v�̗�O���X���[����B<BR>
     *            class:  WEB3BusinessLayerException<BR>
     *            tag  : BUSINESS_ERROR_01065<BR>
     * <BR>
     * �@@�P�|�Q�jthis.�\������To��Date�^�ɕϊ����A�G���[�����������ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�\������(��)���t�G���[�v�̗�O���X���[����B<BR>
     *            class:  WEB3BusinessLayerException<BR>
     *            tag  : BUSINESS_ERROR_01066<BR>
     * <BR>
     * �@@�P�|�R�jthis.�\������From > this.�\������To�ł���ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�\������(��)(��)�������G���[�v�̗�O���X���[����B<BR>
     *            class:  WEB3BusinessLayerException<BR>
     *            tag  : BUSINESS_ERROR_01051<BR>
     * <BR>
     * �Q�j�@@�����R�[�h�`�F�b�N<BR>
     * �@@this.�����R�[�h != null�̏ꍇ�́A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�|�P�jthis.�����R�[�h���ȉ��̏����ɊY������ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�Ethis.�����R�[�h.length() != 4�� ���� 5��<BR>
     *            class:  WEB3BusinessLayerException<BR>
     *            tag  : BUSINESS_ERROR_00439<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.���i�敪���ȉ��ɊY�����Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���i�������G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�E�h�S���i�h<BR>
     * �@@�@@�@@�@@�@@�E�h�����E�M�p�h<BR>
     * �@@�@@�@@�@@�@@�E�h�����h<BR>
     * �@@�@@�@@�@@�@@�E�h�M�p�h<BR>
     * �@@�@@�@@�@@�@@�E�h�O���h<BR>
     *            class:  WEB3BusinessLayerException<BR>
     *            tag  : BUSINESS_ERROR_01068<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41368C40003F
     */
    public void validate() throws  WEB3BaseException
    {
        final  String  STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

         //�P�j�@@�\�����ԃ`�F�b�N
         //�@@this.�\������From != null ���� this.�\������To != null�̏ꍇ�́A
         //�@@�ȉ��̃`�F�b�N���s���B
         if (this.listStartDate != null && this.listEndDate != null)
         {
              //�P�|�P�jthis.�\������From��Date�^�ɕϊ����A�G���[�����������ꍇ�́A
              //   �@@�@@�u�\������(��)���t�G���[�v�̗�O���X���[����B
              if (!(WEB3StringTypeUtility.isDateStr(this.listStartDate, "yyyyMMdd"))) 
              {
                   log.error(" �\������(��)���t�G���[ �B");
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_01065,
                       this.getClass().getName() + STR_METHOD_NAME,
                       "�\�����ԁi���j���t�t�H�[�}�b�g�G���[�B");              
              }

              //�P�|�Q�jthis.�\������To��Date�^�ɕϊ����A�G���[�����������ꍇ�́A
              // �@@�@@�@@�u�\������(��)���t�G���[�v�̗�O���X���[����B
              if (!(WEB3StringTypeUtility.isDateStr(this.listEndDate, "yyyyMMdd"))) 
              {
                   log.error(" �\������(��)���t�G���[ �B");
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_01066,
                       this.getClass().getName() + STR_METHOD_NAME,
                       "�\�����ԁi���j���t�t�H�[�}�b�g�G���[�B");              
              }               
               
              //�P�|�R�jthis.�\������From > this.�\������To�ł���ꍇ�́A
              // �@@�@@�@@�u�\������(��)(��)�������G���[�v�̗�O���X���[����B
              Date l_datListStartDate = WEB3DateUtility.getDate(this.listStartDate, "yyyyMMdd");
              Date l_datListEndDate = WEB3DateUtility.getDate(this.listEndDate, "yyyyMMdd");
              if (WEB3DateUtility.compareToDay(l_datListStartDate,l_datListEndDate) > 0)
              {
                   log.error(" �\������(��)(��)�������G���[ �B");
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_01051,
                       this.getClass().getName() + STR_METHOD_NAME,
                       "�\�����ԁi���j�͕\�����ԁi���j���傫���ł��B");              
              }               
         }

         //�Q�j�@@�����R�[�h�`�F�b�N
         // �@@this.�����R�[�h != null�̏ꍇ�́A�ȉ��̃`�F�b�N���s���B
         if (this.productCode != null)
         {
               //�Q�|�P�jthis.�����R�[�h���ȉ��̏����ɊY������ꍇ�́A
               // �@@�@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B
               // �@@�@@�@@�Ethis.�����R�[�h.length() != 4�� ���� 5��
               if ((WEB3StringTypeUtility.getByteLength(this.productCode) != 4 
                   && WEB3StringTypeUtility.getByteLength(this.productCode) != 5 ))
               {
                   log.error(" �����R�[�h�G���[ �B");
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_00439,
                       this.getClass().getName() + STR_METHOD_NAME,
                       "�����R�[�h�̃T�C�Y���s���ł��B");
               }               
               
               //�Q�|�Q�jthis.���i�敪���ȉ��ɊY�����Ȃ��ꍇ�A
               // �@@�@@�@@�u���i�������G���[�v�̗�O���X���[����B
               // �@@�@@�@@�E�h�S���i�h
               // �@@�@@�@@�E�h�����E�M�p�h
               // �@@�@@�@@�E�h�����h
               // �@@�@@�@@�E�h�M�p�h
			   // �@@�@@�@@�E�h�O�������h
               if (!WEB3TradeHistoryProductDivDef.ALL_PRODUCT.equals(this.commodityType) 
                   && !WEB3TradeHistoryProductDivDef.EQUITY_MARGIN.equals(this.commodityType) 
                   && !WEB3TradeHistoryProductDivDef.EQUITY.equals(this.commodityType) 
                   && !WEB3TradeHistoryProductDivDef.MARGIN.equals(this.commodityType)
			       && !WEB3TradeHistoryProductDivDef.FOREIGN.equals(this.commodityType))
               {
                   log.error(" ���i�������G���[ �B");
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                       this.getClass().getName() + STR_METHOD_NAME,
                       "���i�敪�����݂��Ȃ��R�[�h�l�ł��B");
               }
         }
         
         log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 41789C4B0242
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3HistoryTradeHistoryDownloadResponse(this);
    }
}
@
