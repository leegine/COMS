head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.30.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenStockHolderRegVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : ���O���E����o�^�`�[(WEB3AccOpenStockHolderRegVoucher.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2006/07/07 ꎉ� �V�K�쐬
		              2006/07/13 ꎉ� (���u) �d�l�ύX�E���f��077�A079�A�c�a���C�A�E�g026 �A027�A028
		              2006/07/18 ꎉ� �d�l�ύX�E���f��081
                      2006/10/12 �ęԍg �c�a���C�A�E�g  No.038
Revesion History    : 2008/10/08 ���g (���u) �c�a�X�V�d�l�E���f��037
*/
package webbroker3.accountopen;

import java.util.Hashtable;
import java.util.List;
import java.lang.reflect.Field;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.common.define.WEB3CatDelimitterDef;
import webbroker3.common.define.WEB3EditWayDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3RegDivDef;
import webbroker3.common.define.WEB3SelfDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TaxCodeDef;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.accountopen.data.AccOpenVoucherItemRow;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.data.HostStockholderRegVoucherParams;
import webbroker3.accountopen.define.WEB3AccountOpenExpAccountOpenSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenOutputItemSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenVoucherCodeDef;

/**
 * (���O���E����o�^�`�[)<BR>
 * ���O���E����o�^�`�[<BR>
 * 
 * @@author ꎉ�
 * @@version 1.0 
 */
public class WEB3AccOpenStockHolderRegVoucher extends WEB3AccOpenVoucher
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
     private static WEB3LogUtility log =
             WEB3LogUtility.getInstance(WEB3AccOpenStockHolderRegVoucher.class);
    /**
     *(is�I�����C���`�[)<BR>
     *(is�I�����C���`�[()�̎���)<BR>
     *<BR>
     *true��ԋp����B<BR>
     *@@return boolean
     */
    public boolean isOnlineVoucher()
    {
        final String STR_METHOD_NAME = " isOnlineVoucher()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     *(get�f�[�^�R�[�h)<BR>
     *(get�f�[�^�R�[�h()�̎���)<BR>
     *<BR>
     *�f�[�^�R�[�h.���O���E����o�^�`�[�iGI849�j��ԋp����B <BR>
     *@@return String
     */
    public String getRequestCode()
    {
        final String STR_METHOD_NAME = " getRequestCode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return WEB3HostRequestCodeDef.ACCOPEN_STOCKHOLDER_REG_VOUCHER;
    }

    /**
     *(get�`�[�R�[�h)<BR>
     *(get�`�[�R�[�h()�̎���)<BR>
     *<BR>
     *�hG8610�h��ԋp����B <BR>
     *@@return String
     */
    public String getVoucherCode()
    {
        final String STR_METHOD_NAME = " getVoucherCode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return WEB3AccountOpenVoucherCodeDef.STOCK_HOLDER_REG_VOUCHER_CODE;
    }


    /**
       *(get�m��ύ��ږ�)<BR>
       *(get�m��ύ��ږ�()�̎���)<BR>
       *<BR>
       *�@@���Y�`�[�Ŏg�p���Ă�������J�݌����q�̗񕨗�����z��Ŏ擾����B <BR>
       *<BR>
       *�P�j�@@�`�[�g�p����Table�i�FHashtable�j����<BR>
       *�@@Hashtable�𐶐�����B<BR>
       *<BR>
       *�Q�j�@@�g�p���ڃZ�b�g<BR>
       *<BR>
       *�@@this.�����J�ݓ`�[�}�X�^�s[]�̊e�v�f���ɁA�Q�|�P�j�`�Q�|�R�j�̏�����<BR>
       *�@@���{����B<BR>
       *<BR>
       *�@@�Q�|�P�j�@@�쐬�\����<BR>
       *�@@is�쐬�\�`�[()�ɂāA�쐬�\�ȓ`�[���𔻒肷��B<BR>
       *<BR>
       *�@@[is�쐬�\�`�[()�Ɏw�肷�����]<BR>
       *�@@�`�[�ʔԁF�@@�����J�ݓ`�[�}�X�^�s[index].�`�[�ʔ�<BR>
       *<BR>
       *�@@�쐬�\�łȂ��ꍇ�iis�쐬�\�`�[() == false�j�̂݁A�Q�|�Q�j�����{����B<BR>
       *�@@�쐬�\�ȏꍇ�iis�쐬�\�`�[() == true�j�A���Y�v�f�̏�����<BR>
       *�@@�s�킸���̗v�f����������B�icontinue;�j<BR>
       *<BR>
       *�@@���쐬�\�ȏꍇ�A���ڒl�͕ύX���Ă��ǂ��̂�<BR>
       *�@@�`�[�Q�ƍ��ږ��ɂ͊܂߂Ȃ��B<BR>
       *<BR>
       *�@@�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾<BR>
       *�@@���O���E����o�^�`�[�iG8610�j�L���[�e�[�u���̊e���ڂɂ��āA���L�̏��������{����B<BR>
       *<BR>
       *�@@this.get�J�X�^�}�C�Y�Q�ƍ���()���R�[�����A�����J�݌����q�e�[�u����<BR>
       *�@@�������̔z����擾����B<BR>
       *<BR>
       *�@@[get�J�X�^�}�C�Y�Q�ƍ���()�Ɏw�肷�����]<BR>
       *�@@�`�[�ʔԁF�@@�����J�ݓ`�[�}�X�^�s[index].�`�[�ʔ�<BR>
       *�@@�`�[�o�͍��ڕ������F�@@�i��1 ���O���E����o�^�`�[�iG8610�j�L���[�e�[�u����<BR>
       *�@@�����Ώۍ��ځj<BR>
       *�@@�`�[�Q�ƍ��ڏ����l�F�@@�i��2 ���O���E����o�^�`�[�iG8610�j�L���[�e�[�u����<BR>
       *�@@�����Ώۍ��ڃf�t�H���g�ݒ�l�j<BR>
       *<BR>
       *�@@�i��2�j�@@���O���E����o�^�`�[�iG8610�j�L���[�e�[�u���̏����Ώۍ��ڃf�t�H���g�ݒ�l<BR>
       *�@@DB���C�A�E�g �u���O���E����o�^�`�[�iG8610�j�L���[�e�[�u��.xls#�f�t�H���gDB�ݒ�_���v<BR>
       *�@@�V�[�g���Q�Ƃ��A<BR>
       *�@@�Y�����ڂ̐������ɁA�����J�݌����q�e�[�u������ҏW����<BR>
       *�@@�w�肪����΁A�w�荀�ڂ̗񕨗����z��B<BR>
       *�@@�ȊO�́Anull�B<BR>
       *<BR>
       *�@@�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�<BR>
       *�@@�`�[�g�p����Table�i�FHashtable�j.put()�ɂ�this.get�J�X�^�}�C�Y�Q�ƍ���()<BR>
       *�@@�߂�l����v�f���ǉ�����B<BR>
       *<BR>
       *�@@[put()�Ɏw�肷�����]<BR>
       *�@@key�F�@@this.get�J�X�^�}�C�Y�Q�ƍ���()[n]<BR>
       *�@@value�F�@@this.get�J�X�^�}�C�Y�Q�ƍ���()[n]<BR>
       *<BR>   
       *�@@�� key�Cvalue�ɓ����l���Z�b�g����B<BR>
       *<BR>
       *�@@�R�j�@@���ږ��z��ԋp<BR>
       *�@@�`�[�g�p����Table�i�FHashtable�j.values()�@@��ԋp����B<BR>
       *@@return String[]
       *@@throws WEB3BaseException
       */
    
    public String[] getConfirmedItemName() throws WEB3BaseException
      {
          final String STR_METHOD_NAME = " getConfirmedItemName()";
          log.entering(STR_METHOD_NAME);
          //�`�[�g�p����Table�i�FHashtable�j���� 
          Hashtable l_voucherItemList = new Hashtable();
          //this.�����J�ݓ`�[�}�X�^�s[]�̊e�v�f���ɁA�Q�|�P�j�`�Q�|�R�j�̏��������{����B
          int l_intLength = 0;
          if (this.accOpenVoucherMasterParamses != null)
          {
              l_intLength = this.accOpenVoucherMasterParamses.length;
              log.debug("accOpenVoucherMasterParamses.length============"+l_intLength);
          }
          for (int i = 0; i < l_intLength; i++)
          {
              log.debug("�����J�ݓ`�[�}�X�^�s[]�̊e�v�f���ɁA�Q�|�P�j�`�Q�|�R�j�̏��������{����B");
              String l_strSerialNo = accOpenVoucherMasterParamses[i].getSerialNo();
              log.debug("l_strSerialNo@@@@@@@@@@@@@@@@@@@@@@"+l_strSerialNo);
              //�Q�|�P�j�@@�쐬�\����
              boolean l_blnCreatedPossibleVoucher = this.isCreatedPossibleVoucher(l_strSerialNo);

              //�쐬�\�łȂ��ꍇ�iis�쐬�\�`�[() == false�j�̂݁A�Q�|�Q�j�����{����
              if (!l_blnCreatedPossibleVoucher)
              {                
                  log.debug("�쐬�\�łȂ��ꍇ�iis�쐬�\�`�[() == false�j�̂݁A�Q�|�Q�j�����{����");
                  String[] l_strValues = new String[1];
                
                  ////U01000
                  //�،���ЃR�[�h
                  l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSTITUTION_CODE;
                  //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                  String[] l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                      WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE, l_strValues);   
                  //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                  int l_int = 0;
                  if (l_strCustomizingRefItems != null)
                  {
                      l_int = l_strCustomizingRefItems.length;
                  }
                  for (int j = 0; j < l_int; j++)
                  {
                      log.debug("****************1*�،���ЃR�[�h"+l_int);
                      if (l_strCustomizingRefItems[j] != null)
                      {
                          l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                      }
                  }
                
                  //�f�[�^�R�[�h
                  l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                      WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE, null);  
                  //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                  l_int = 0;
                  if (l_strCustomizingRefItems != null)
                  {
                      l_int = l_strCustomizingRefItems.length;
                  }
                  for (int j = 0; j < l_int; j++)
                  {
                      log.debug("*********************2*�f�[�^�R�[�h"+l_int);
                      if (l_strCustomizingRefItems[j] != null)
                      {
                          l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                      }
                  }

                  //���X�R�[�h
                  //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                  l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.BRANCH_CODE;
                  l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                      WEB3AccountOpenOutputItemSymbolNameDef.BRANCH_CODE, l_strValues);   
                  //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                  l_int = 0;
                  if (l_strCustomizingRefItems != null)
                  {
                      l_int = l_strCustomizingRefItems.length;
                  }
                  for (int j = 0; j < l_int; j++)
                  {
                      log.debug("****************3*���X�R�[�h"+l_int);
                      if (l_strCustomizingRefItems[j] != null)
                      {
                          log.debug("l_strCustomizingRefItems[j]---------------"+l_strCustomizingRefItems[j]);
                          l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                      }
                  }

                  //�ڋq�R�[�h
                  //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                  l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ACCOUNT_CODE;
                  l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                      WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_CODE, l_strValues);   
                  //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                  l_int = 0;
                  if (l_strCustomizingRefItems != null)
                  {
                      l_int = l_strCustomizingRefItems.length;
                  }
                  for (int j = 0; j < l_int; j++)
                  {
                      log.debug("****************4*�ڋq�R�[�h"+l_int);
                      if (l_strCustomizingRefItems[j] != null)
                      {
                          l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                      }
                  }

                  //���҃R�[�h
                  //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                  l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.SONAR_TRADER_CODE;
                  l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                      WEB3AccountOpenOutputItemSymbolNameDef.TRADER_CODE, l_strValues);   
                  //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                  l_int = 0;
                  if (l_strCustomizingRefItems != null)
                  {
                      l_int = l_strCustomizingRefItems.length;
                  }
                  for (int j = 0; j < l_int; j++)
                  {
                      log.debug("****************5*���҃R�[�h"+l_int);
                      if (l_strCustomizingRefItems[j] != null)
                      {
                          l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                      }
                  }

                  //���ʃR�[�h�i�����J�݌����q�j
                  //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                  l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ACC_OPEN_REQUEST_NUMBER;
                  l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                      WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER, l_strValues);  
                  //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                  l_int = 0;
                  if (l_strCustomizingRefItems != null)
                  {
                      l_int = l_strCustomizingRefItems.length;
                  }
                  for (int j = 0; j < l_int; j++)
                  {
                      log.debug("*****************6*���ʃR�[�h�i�����J�݌����q�j"+l_int);
                      if (l_strCustomizingRefItems[j] != null)
                      {
                          l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                      }
                  }
                
                  //�`�[�ʔ�
                  l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                      WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO, null);  
                  //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                  l_int = 0;
                  if (l_strCustomizingRefItems != null)
                  {
                      l_int = l_strCustomizingRefItems.length;
                  }
                  for (int j = 0; j < l_int; j++)
                  {
                      log.debug("**********************7*�`�[�ʔ�"+l_int);
                      if (l_strCustomizingRefItems[j] != null)
                      {
                          l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                      }
                  }
                
                  //�o�^�敪
                  l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                      WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DIV, null);  
                  //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                  l_int = 0;
                  if (l_strCustomizingRefItems != null)
                  {
                      l_int = l_strCustomizingRefItems.length;
                  }
                  for (int j = 0; j < l_int; j++)
                  {
                      log.debug("*********************8*�o�^�敪"+l_int);
                      if (l_strCustomizingRefItems[j] != null)
                      {
                          l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                      }
                  }
                
                  //�����J�݌����q.�i���O���j���n��ҏW����B
                  l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_TAXATION_TRAN_DIV;
                  l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                      WEB3AccountOpenOutputItemSymbolNameDef.TAXATION_TRAN_DIV, l_strValues);  
                  //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                  l_int = 0;
                  if (l_strCustomizingRefItems != null)
                  {
                      l_int = l_strCustomizingRefItems.length;
                  }
                  for (int j = 0; j < l_int; j++)
                  {
                      log.debug("****************9*���n"+l_int);
                      if (l_strCustomizingRefItems[j] != null)
                      {
                          l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                      }
                  }
                  
                  //�ڋq���i�Łj
                  String[] l_strValues2 = new String[2];
                   l_strValues2[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_NAME_ALT1;
                   l_strValues2[1] = WEB3AccountOpenExpAccountOpenSymbolNameDef.GIVEN_NAME_ALT1;
                   l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                       WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME_KANA, l_strValues2);     

                  //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                  l_int = 0;
                  if (l_strCustomizingRefItems != null)
                  {
                      l_int = l_strCustomizingRefItems.length;
                  }
                  for (int j = 0; j < l_int; j++)
                  {
                      log.debug("****************10*�ڋq���i�Łj"+l_int);
                      if (l_strCustomizingRefItems[j] != null)
                      {
                          l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                      }
                  }
                
                  //�X�֔ԍ�
                  l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ZIP_CODE;
                  l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                      WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE, l_strValues);  
                  //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                  l_int = 0;
                  if (l_strCustomizingRefItems != null)
                  {
                      l_int = l_strCustomizingRefItems.length;
                  }
                  for (int j = 0; j < l_int; j++)
                  {
                      log.debug("*****************11*�X�֔ԍ�"+l_int);
                      if (l_strCustomizingRefItems[j] != null)
                      {
                          l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                      }
                  }
                
                  //�Z���i�J�i�j
                  l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_ADDRESS_LINE_KANA;
                  l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                      WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1_KANA, l_strValues);  
                  //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                  l_int = 0;
                  if (l_strCustomizingRefItems != null)
                  {
                      l_int = l_strCustomizingRefItems.length;
                  }
                  for (int j = 0; j < l_int; j++)
                  {
                      log.debug("**********************12*�Z���i�J�i�j"+l_int);
                      if (l_strCustomizingRefItems[j] != null)
                      {
                          l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                      }
                  }
                
                  //����:�����J�݌����q.�i���O���j������ҏW����B
                  l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_TRANSFER_DIV;
                   l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                       WEB3AccountOpenOutputItemSymbolNameDef.STK_TRANSFER_DIV, l_strValues);  
                   //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                   l_int = 0;
                   if (l_strCustomizingRefItems != null)
                   {
                       l_int = l_strCustomizingRefItems.length;
                   }
                   for (int j = 0; j < l_int; j++)
                   {
                       log.debug("******************13*����"+l_int);
                       if (l_strCustomizingRefItems[j] != null)
                       {
                           l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                       }
                   }

                  //��s�R�[�h
                  l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_INSTITUTION_CODE;
                  l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                      WEB3AccountOpenOutputItemSymbolNameDef.FIN_INSTITUTION_CODE, l_strValues);  
                  //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                  l_int = 0;
                  if (l_strCustomizingRefItems != null)
                  {
                      l_int = l_strCustomizingRefItems.length;
                  }
                  for (int j = 0; j < l_int; j++)
                  {
                      log.debug("*******************14*��s�R�[�h"+l_int);
                      if (l_strCustomizingRefItems[j] != null)
                      {
                          l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                      }
                  }
                  
                  //�x�X�R�[�h
                  l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_BRANCH_CODE;
                  l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                      WEB3AccountOpenOutputItemSymbolNameDef.FIN_BRANCH_CODE, l_strValues);  
                  //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                  l_int = 0;
                  if (l_strCustomizingRefItems != null)
                  {
                      l_int = l_strCustomizingRefItems.length;
                  }
                  for (int j = 0; j < l_int; j++)
                  {
                      log.debug("*******************15*�x�X�R�[�h"+l_int);
                      if (l_strCustomizingRefItems[j] != null)
                      {
                          l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                      }
                  }
                
                  //�a�����
                  l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_SAVE_DIV;
                   l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                       WEB3AccountOpenOutputItemSymbolNameDef.FIN_SAVE_DIV, l_strValues);  
                   //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                   l_int = 0;
                   if (l_strCustomizingRefItems != null)
                   {
                       l_int = l_strCustomizingRefItems.length;
                   }
                   for (int j = 0; j < l_int; j++)
                   {
                       log.debug("*********************16*�a�����"+l_int);
                       if (l_strCustomizingRefItems[j] != null)
                       {
                           l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                       }
                   }
                 
                 
                  //�����ԍ�
                  l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_ACCOUNT_NO;
                   l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                       WEB3AccountOpenOutputItemSymbolNameDef.FIN_ACCOUNT_NO, l_strValues);  
                   //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                   l_int = 0;
                   if (l_strCustomizingRefItems != null)
                   {
                       l_int = l_strCustomizingRefItems.length;
                   }
                   for (int j = 0; j < l_int; j++)
                   {
                       log.debug("*********************17*�����ԍ�"+l_int);
                       if (l_strCustomizingRefItems[j] != null)
                       {
                           l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                       }
                   }
                   
                  //�������`
                  l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_ACCOUNT_NAME;
                   l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                       WEB3AccountOpenOutputItemSymbolNameDef.FIN_ACCOUNT_NAME, l_strValues);  
                   //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                   l_int = 0;
                   if (l_strCustomizingRefItems != null)
                   {
                       l_int = l_strCustomizingRefItems.length;
                   }
                   for (int j = 0; j < l_int; j++)
                   {
                       log.debug("*********************18*�������`"+l_int);
                       if (l_strCustomizingRefItems[j] != null)
                       {
                           l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                       }
                   }
                   
                   
                  //�����敪
                   l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                       WEB3AccountOpenOutputItemSymbolNameDef.STATUS, null);  
                   //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                   l_int = 0;
                   if (l_strCustomizingRefItems != null)
                   {
                       l_int = l_strCustomizingRefItems.length;
                   }
                   for (int j = 0; j < l_int; j++)
                   {
                       log.debug("********************19*�����敪"+l_int);
                       if (l_strCustomizingRefItems[j] != null)
                       {
                           l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                       }
                   }
                   
                   
                   //���ȋ敪
                    l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                        WEB3AccountOpenOutputItemSymbolNameDef.SELF_DIV, l_strValues);  
                    //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                    l_int = 0;
                    if (l_strCustomizingRefItems != null)
                    {
                        l_int = l_strCustomizingRefItems.length;
                    }
                    for (int j = 0; j < l_int; j++)
                    {
                        log.debug("********************20*���ȋ敪"+l_int);
                        if (l_strCustomizingRefItems[j] != null)
                        {
                            l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                        }
                    }
                    
                    
                    //�ŃR�[�h
                     l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                         WEB3AccountOpenOutputItemSymbolNameDef.TAX_CODE, l_strValues);  
                     //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                     l_int = 0;
                     if (l_strCustomizingRefItems != null)
                     {
                         l_int = l_strCustomizingRefItems.length;
                     }
                     for (int j = 0; j < l_int; j++)
                     {
                         log.debug("********************21*�ŃR�[�h"+l_int);
                         if (l_strCustomizingRefItems[j] != null)
                         {
                             l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                         }
                     }
              }
          }
          String[] l_strValues = new String[l_voucherItemList.size()];
          l_voucherItemList.values().toArray(l_strValues);
          log.exiting(STR_METHOD_NAME);
          return l_strValues;
      }


    /**
     *(save�`�[�s)<BR>
     *(save�`�[�s()�̎���)<BR>
     *�����J�ݓ`�[���P���o�^����B<BR>
     *<BR>
     *�@@�P�j�@@�f�t�H���g�ݒ�s����<BR>
     *�@@���O���E����o�^�`�[�iG8610�j�L���[�e�[�u���s�I�u�W�F�N�g�𐶐����A<BR>
     *�@@�f�t�H���g�ݒ�i��1�j�̒ʂ�Ƀv���p�e�B���Z�b�g����B <BR>
     *<BR>
     *�@@��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A<BR>
     *�@@�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�B<BR>
     *<BR>
     *�@@�i��1�j�@@���O���E����o�^�`�[�iG8610�j�L���[�e�[�u���̏����Ώۍ��ڃf�t�H���g�ݒ�<BR>
     *�@@DB���C�A�E�g<BR>
     *�@@�u���O���E����o�^�`�[�iG8610�j�L���[�e�[�u��.xls#�f�t�H���gDB�ݒ�_���v<BR>
     *�@@�V�[�g�Q�ƁB<BR>
     *<BR>
     *�@@�Q�j�@@�J�X�^�}�C�Y���ڃZ�b�g <BR>
     *�@@�����J�ݓ`�[���ڃ}�X�^�e�[�u�����ȉ��̏����@@�Ō�������B <BR>
     *�@@�Y���s���Ȃ��ꍇ�A�����A�Ō�������B <BR>
     *<BR>
     *�@@[�����@@] <BR>
     *�@@�����J�ݓ`�[���ڃ}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And <BR>
     *�@@�����J�ݓ`�[���ڃ}�X�^.���X�R�[�h = this.get���X�R�[�h() And <BR>
     *�@@�����J�ݓ`�[���ڃ}�X�^.�����敪 = this.get�����敪() And <BR>
     *�@@�����J�ݓ`�[���ڃ}�X�^.�f�[�^�R�[�h = this.get�f�[�^�R�[�h() And <BR>
     *�@@�����J�ݓ`�[���ڃ}�X�^.�`�[�ʔ� = �`�[�ʔ� <BR>
     *<BR>
     *�@@[�����A] <BR>
     *�@@�����J�ݓ`�[���ڃ}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And <BR>
     *�@@�����J�ݓ`�[���ڃ}�X�^.���X�R�[�h = "000" And <BR>
     *�@@�����J�ݓ`�[���ڃ}�X�^.�����敪 = this.get�����敪() And <BR>
     *�@@�����J�ݓ`�[���ڃ}�X�^.�f�[�^�R�[�h = this.get�f�[�^�R�[�h() And <BR>
     *�@@�����J�ݓ`�[���ڃ}�X�^.�`�[�ʔ� = �`�[�ʔ� <BR>
     *<BR>
     *�@@�����@@�C�A�̂ǂ��炩�ŊY���s������ꍇ�́A�Q�|�P�j�̏��������{����B <BR>
     *<BR>
     *�@@�Q�|�P�j�@@�J�X�^�}�C�Y�ҏW<BR>
     *�@@�������ʂ̊e�s���ɁA�o�͍��ڕ������������`�[���ڂ̒l���A<BR>
     *�@@�w��̕��@@�ōăZ�b�g����B<BR>
     *�@@��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A<BR>
     *�@@�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�B<BR>
     *<BR>
     *�@@�� �i���ڕҏW���@@ == �Œ�l�j�̏ꍇ�A�Œ�Z�b�g�l�̒l���Z�b�g����B<BR>
     *�@@�� �ȊO�̏ꍇ�A���͍��ڕ������P�`�R�����������J�݌����q��<BR>
     *�@@���ڒl(��2)���Z�b�g����B<BR>
     *�@@�@@�|���͍��ڕ������P�`�R��null�̏ꍇ�́A<BR>
     *�@@�@@�|�A�����ڃf���~�b�^���w�肳��Ă���ꍇ�i�A�����ڃf���~�b�^ !=null�j�A<BR>
     *�@@���͍��ڕ������P�C�Q�C�R�̒l���f���~�b�^�ɂĘA������B<BR>
     *<BR>
     *�@@(��2) DB���C�A�E�g�u�����J�ݓ`�[���ڃ}�X�^�v�Q�ƁB<BR>
     *<BR>
     *�@@�Q�|�Q�j�@@�`�[�̎��ʃR�[�h�V�K�̔�<BR>
     *�@@�������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h()�ɂĎ��ʃR�[�h���擾���A<BR>
     *�@@�s�I�u�W�F�N�g�̎��ʃR�[�h�iorder_request_number�j�ɃZ�b�g����B<BR>
     *<BR>
     *�@@[get�V�K���ʃR�[�h()�Ɏw�肷�����]<BR>
     *�@@�،���ЃR�[�h�F�@@this.get�،���ЃR�[�h()<BR>
     *�@@���X�R�[�h�F�@@this.get���X�R�[�h()<BR>
     *�@@�����^�C�v�F�@@ProductTypeEnum.���̑�<BR>
     *<BR>
     *�@@�R�j�@@DB�X�V<BR>
     *�@@�R�|�P�j�@@�����s�폜<BR>
     *�@@�ȉ��̏����ɂď��O���E����o�^�`�[�iG8610�j�L���[�e�[�u������������B<BR>
     *�@@�Y���s�����ɑ��݂���ꍇ�́A�Y���s��delete����B<BR>
     *<BR>
     *�@@[����]<BR>
     *�@@�،���ЃR�[�h =  this.get�،���ЃR�[�h() And<BR>
     *�@@���ʃR�[�h = this.get���ʃR�[�h() And<BR>
     *�@@�`�[�ʔ� = �`�[�ʔ� And<BR>
     *�@@�����敪 = �h�������h<BR>
     *<BR>
     *�@@�R�|�Q�j�@@�`�[�s�}��<BR>
     *  �P�j�`�Q�j�ŕҏW�����s�I�u�W�F�N�g��DB�ɍX�V�iDB-insert����j�B<BR>
     *@@param l_strSerialNo - (�`�[�ʔ�)<BR>
     *�`�[�ʔ�<BR>
     *@@throws WEB3BaseException
     */
    public void saveVoucherRow(String l_strSerialNo) throws WEB3BaseException
      {
          final String STR_METHOD_NAME = " saveVoucherRow(String)";
          log.entering(STR_METHOD_NAME);
          log.debug("l_strSerialNo======="+l_strSerialNo);
          //�f�t�H���g�ݒ�s���� 
          HostStockholderRegVoucherParams l_hostStockholderRegVoucherParams = new HostStockholderRegVoucherParams();
        
          //�f�t�H���g�ݒ�i��1�j�̒ʂ�Ƀv���p�e�B���Z�b�g����B
          try
          {             
              String l_strInstitutionCode = this.accOpenExpAccountOpen.getInstitutionCode();
              String l_strBranchCode = this.accOpenExpAccountOpen.getBranchCode();
              String l_strAccountCode = this.accOpenExpAccountOpen.getAccountCode();
              String l_strRequestNumber = this.accOpenExpAccountOpen.getRequestNumber();

              log.debug("�f�t�H���g�ݒ�i��1�j�̒ʂ�Ƀv���p�e�B���Z�b�g����");
              //�f�[�^�R�[�h
              l_hostStockholderRegVoucherParams.setRequestCode(WEB3HostRequestCodeDef.ACCOPEN_STOCKHOLDER_REG_VOUCHER);
              //�،���ЃR�[�h
              l_hostStockholderRegVoucherParams.setInstitutionCode(
              super.getStringByByteNumber(l_strInstitutionCode, 3));
              //���X�R�[�h                    
              l_hostStockholderRegVoucherParams.setBranchCode(
              super.getStringByByteNumber(l_strBranchCode, 3));
              //�ڋq�R�[�h
              l_hostStockholderRegVoucherParams.setAccountCode(
              super.getStringByByteNumber(l_strAccountCode, 7));
            
              //���҃R�[�h
              ExpAccountOpenRow l_expAccountOpenRow = (ExpAccountOpenRow)this.accOpenExpAccountOpen.getDataSourceObject();
              String l_strSonarTraderCode = l_expAccountOpenRow.getSonarTraderCode();
              l_hostStockholderRegVoucherParams.setTraderCode(super.getStringByByteNumber(l_strSonarTraderCode, 5));
            
              //���ʃR�[�h�i�����J�݌����q�j
              l_hostStockholderRegVoucherParams.setAccOpenRequestNumber(
              super.getStringByByteNumber(l_strRequestNumber, 13));
              //�`�[�ʔ�
              l_hostStockholderRegVoucherParams.setSerialNo(l_strSerialNo);
              //�o�^�敪
              l_hostStockholderRegVoucherParams.setRegistDiv(WEB3RegDivDef.NEW);

              //���n
              l_hostStockholderRegVoucherParams.setTaxationTranDiv(
              super.getStringByByteNumber(l_expAccountOpenRow.getStkTaxationTranDiv(), 1));
            
              //�ڋq���i�Łj
              String l_strFamilyNameAlt1 = WEB3StringTypeUtility.to1byteKana(l_expAccountOpenRow.getFamilyNameAlt1());
              String l_strGivenNameAlt1 = WEB3StringTypeUtility.to1byteKana(l_expAccountOpenRow.getGivenNameAlt1());
              String l_strAccountNameKana = l_strFamilyNameAlt1 + " " + l_strGivenNameAlt1;
              l_hostStockholderRegVoucherParams.setAccountNameKana(super.getStringByByteNumber(l_strAccountNameKana, 19));         

              //�X�֔ԍ�
               l_hostStockholderRegVoucherParams.setZipCode(
              super.getStringByByteNumber(l_expAccountOpenRow.getZipCode(), 7));
              
              //�Z���i�J�i�j
              l_hostStockholderRegVoucherParams.setAddressLineKana(
              super.getStringByByteNumber(l_expAccountOpenRow.getAddressLine1Kana(), 70));
               
               //����
              l_hostStockholderRegVoucherParams.setTransferDiv(
              super.getStringByByteNumber(l_expAccountOpenRow.getStkTransferDiv(), 1));
              //��s�R�[�h
               l_hostStockholderRegVoucherParams.setFinInstitutionCode(
               super.getStringByByteNumber(l_expAccountOpenRow.getStkFinInstitutionCode(), 4));
              //�x�X�R�[�h
               l_hostStockholderRegVoucherParams.setFinBranchCode(
               super.getStringByByteNumber(l_expAccountOpenRow.getStkFinBranchCode(), 3));
              //�a�����
               l_hostStockholderRegVoucherParams.setFinSaveDiv(
               super.getStringByByteNumber(l_expAccountOpenRow.getStkFinSaveDiv(), 1));
              //�����ԍ�
               l_hostStockholderRegVoucherParams.setFinAccountNo(
               super.getStringByByteNumber(l_expAccountOpenRow.getStkFinAccountNo(), 7));
              //�������`
               // --------------�������`�l�𔼊p�J�i�ɕϊ�����悤�ɏC���@@SCS sato---------------
               String l_strEditFinAccountNmae = WEB3StringTypeUtility.to1byteKana(l_expAccountOpenRow.getFinAccountName());
               
               l_hostStockholderRegVoucherParams.setFinAccountName(
               super.getStringByByteNumber(l_strEditFinAccountNmae, 19));
               // ----------------- end ------------------------------------------------------
              //�����敪   
              l_hostStockholderRegVoucherParams.setStatus(WEB3StatusDef.NOT_DEAL);
                    
              Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
              //���M����
              l_hostStockholderRegVoucherParams.setSendTimestamp(null);
              //�쐬����
              l_hostStockholderRegVoucherParams.setCreatedTimestamp(l_tsSystemTimestamp);
              //�X�V����
              l_hostStockholderRegVoucherParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);

              //���ȋ敪
              l_hostStockholderRegVoucherParams.setSelfDiv(WEB3SelfDivDef.CUSTOMER);

              //�ŃR�[�h
              l_hostStockholderRegVoucherParams.setTaxCode(WEB3TaxCodeDef.TAXATION);

              //�Q�j�@@�J�X�^�}�C�Y���ڃZ�b�g
              QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException
              //[�����@@] 
              log.debug("�J�X�^�}�C�Y���ڃZ�b�g");
              String l_strWhereItem =
                  "institution_code = ? and " +        //�����J�ݓ`�[���ڃ}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And 
                  "branch_code = ? and " +             //�����J�ݓ`�[���ڃ}�X�^.���X�R�[�h = this.get���X�R�[�h() And  
                  "account_div = ? and " +             //�����J�ݓ`�[���ڃ}�X�^.�����敪 = this.get�����敪() And  
                  "request_code = ? and " +            //�����J�ݓ`�[���ڃ}�X�^.�f�[�^�R�[�h = this.get�f�[�^�R�[�h() And  
                  "serial_no = ? ";                    //�����J�ݓ`�[���ڃ}�X�^.�`�[�ʔ� = �`�[�ʔ�

              //String l_strRequestCode = this.getRequestCode();
               log.debug("testinggggggggggggggggg"+this.getBranchCode());      
              Object[] l_objBindVarsItem =
                  {this.getInstitutionCode(),
                   this.getBranchCode(),
                   this.getAccountDiv(),
                   this.getRequestCode(),
                   l_strSerialNo};
                    
              List l_lisRowItems =
                  l_queryProcesser.doFindAllQuery(
                      AccOpenVoucherItemRow.TYPE,
                      l_strWhereItem,
                      l_objBindVarsItem);                        //DataQueryException, DataNetworkException
                     
              //�Y���s���Ȃ��ꍇ�A�����A�Ō�������B 
              if (l_lisRowItems == null || l_lisRowItems.size() == 0)
              {
                  log.debug("���s���Ȃ��ꍇ�A�����A�Ō�������B");
                  //[�����A]  
                  Object[] l_objBindVarsItem2 =
                      { this.getInstitutionCode(),
                       "000",
                       this.getAccountDiv(),
                       this.getRequestCode(),
                       l_strSerialNo};
                    
                  l_lisRowItems =
                      l_queryProcesser.doFindAllQuery(
                          AccOpenVoucherItemRow.TYPE,
                          l_strWhereItem,
                          l_objBindVarsItem2);                  //DataQueryException, DataNetworkException
              }
            
              int l_intSize = 0;
              if (l_lisRowItems != null)
              {
                  l_intSize = l_lisRowItems.size();
                  log.debug("l_lisRowItems.size()==================="+l_lisRowItems.size());
              }
              AccOpenVoucherItemRow l_accOpenVoucherItemRow = null;
              if (l_intSize > 0)
              {
                  log.debug("�Q�|�P�j�@@�J�X�^�}�C�Y�ҏW");
                  //�Q�|�P�j�@@�J�X�^�}�C�Y�ҏW
                  for (int i = 0; i < l_intSize; i++)
                  {
                      l_accOpenVoucherItemRow = (AccOpenVoucherItemRow)l_lisRowItems.get(i);
                      String l_strValue = null;
                      //�i���ڕҏW���@@ == �Œ�l�j�̏ꍇ�A�Œ�Z�b�g�l�̒l���Z�b�g����B
                      if (WEB3EditWayDivDef.FIXED_VALUE.equals(l_accOpenVoucherItemRow.getEditWayDiv()))
                      {
                          log.debug("�i���ڕҏW���@@ == �Œ�l�j�̏ꍇ�A�Œ�Z�b�g�l�̒l���Z�b�g����B");
                          l_strValue = l_accOpenVoucherItemRow.getFixedValue();
                          log.debug("getFixedValue------------"+l_strValue);
                      }
                      else
                      {                      
                          log.debug("���͍��ڕ������P�C�Q�C�R�̒l���f���~�b�^�ɂĘA������B");
                          log.debug("getInputItemSymbolName11111111111"+l_accOpenVoucherItemRow.getInputItemSymbolName1());
                          String l_strValue1 = this.nameCompare(l_accOpenVoucherItemRow.getInputItemSymbolName1());
                          log.debug("l_strValue1=========="+l_strValue1);
                          String l_strValue2 = this.nameCompare(l_accOpenVoucherItemRow.getInputItemSymbolName2());
                          log.debug("l_strValue2=========="+l_strValue2);
                          String l_strValue3 = this.nameCompare(l_accOpenVoucherItemRow.getInputItemSymbolName3());
                          log.debug("l_strValue3=========="+l_strValue3);
                          if (WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM_TO_HALFKANA.equals(l_accOpenVoucherItemRow.getEditWayDiv()))
                          {
                              l_strValue1 = WEB3StringTypeUtility.to1byteKana(l_strValue1);
                              l_strValue2 = WEB3StringTypeUtility.to1byteKana(l_strValue2);
                              l_strValue3 = WEB3StringTypeUtility.to1byteKana(l_strValue3);
                          }

                          //���͍��ڕ������P�C�Q�C�R�̒l���f���~�b�^�ɂĘA������B 
                        
                          if (l_accOpenVoucherItemRow.getCatDelimitter() != null 
                              && !WEB3CatDelimitterDef.WITHOUT.equals(l_accOpenVoucherItemRow.getCatDelimitter()))
                          {
                              if (l_strValue1 != null)
                              {
                                  l_strValue = l_strValue1;
                                  if (l_strValue2 != null)
                                  {
                                      if (WEB3CatDelimitterDef.HALF_SPACE.equals(l_accOpenVoucherItemRow.getCatDelimitter()))
                                      {
                                          l_strValue = l_strValue + " " + l_strValue2;
                                      }
                                      else if (WEB3CatDelimitterDef.FULL_SPACE.equals(l_accOpenVoucherItemRow.getCatDelimitter()))
                                      {
                                          l_strValue = l_strValue + "�@@" + l_strValue2;
                                      }
                                      else
                                      {
                                          l_strValue = l_strValue + "-" + l_strValue2;
                                      }
                                  }
                                  if (l_strValue3 != null)
                                  {
                                      if (WEB3CatDelimitterDef.HALF_SPACE.equals(l_accOpenVoucherItemRow.getCatDelimitter()))
                                      {
                                          l_strValue = l_strValue + " " + l_strValue3;
                                      }
                                      else if (WEB3CatDelimitterDef.FULL_SPACE.equals(l_accOpenVoucherItemRow.getCatDelimitter()))
                                      {
                                          l_strValue = l_strValue + "�@@" + l_strValue3;
                                      }
                                      else
                                      {
                                          l_strValue = l_strValue + "-" + l_strValue3;
                                      }
                                  }
                              }
                          }
                          else
                          {
                              if (l_strValue1 != null)
                              {
                                  l_strValue = l_strValue1;
                                  if (l_strValue2 != null)
                                  {
                                      l_strValue = l_strValue + l_strValue2;
                                  }
                                  if (l_strValue3 != null)
                                  {
                                      l_strValue = l_strValue + l_strValue3;
                                  }
                              }    
                          }
                      }
                      log.debug("********************l_strValue = " + l_strValue);
                      log.debug("********************getOutputItemSymbolName = " +    l_accOpenVoucherItemRow.getOutputItemSymbolName());

                    
                      //�f�[�^�R�[�h
                      if (WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                      {
                          log.debug("&&&&&&&&&&&&&&&&&&&&&&&&�f�[�^�R�[�h");
                          l_hostStockholderRegVoucherParams.setRequestCode(super.getStringByByteNumber(l_strValue, 5));   
                      }
                        
                      //�،���ЃR�[�h
                      if (WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                      {
                          log.debug("&&&&&&&&&&&&&&&&&&&&&&&&�،���ЃR�[�h");
                          //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                          l_hostStockholderRegVoucherParams.setInstitutionCode(super.getStringByByteNumber(l_strValue, 3));    
                      }
                        
                      //���X�R�[�h
                      if (WEB3AccountOpenOutputItemSymbolNameDef.BRANCH_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                      {
                          log.debug("&&&&&&&&&&&&&&&&&&&&&&&&���X�R�[�h");
                          //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                          l_hostStockholderRegVoucherParams.setBranchCode(super.getStringByByteNumber(l_strValue, 3));     
                      }
                        
                      //�ڋq�R�[�h
                      if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                      {
                          log.debug("&&&&&&&&&&&&&&&&&&&&&&&&�ڋq�R�[�h");
                          //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                          l_hostStockholderRegVoucherParams.setAccountCode(super.getStringByByteNumber(l_strValue, 7));  
                      }
                        
                      //���҃R�[�h
                      if (WEB3AccountOpenOutputItemSymbolNameDef.TRADER_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                      {
                          log.debug("&&&&&&&&&&&&&&&&&&&&&&&&���҃R�[�h");
                          //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                          l_hostStockholderRegVoucherParams.setTraderCode(super.getStringByByteNumber(l_strValue, 5));     
                      }
                        
                      //���ʃR�[�h�i�����J�݌����q�j
                      if (WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                      {
                          log.debug("&&&&&&&&&&&&&&&&&&&&&&&&���ʃR�[�h�i�����J�݌����q�j");
                          //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                          l_hostStockholderRegVoucherParams.setAccOpenRequestNumber(super.getStringByByteNumber(l_strValue, 13));     
                      }
                        
                      //�`�[�ʔ�
                      if (WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                      {
                          log.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&�`�[�ʔ�");
                          //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                          l_hostStockholderRegVoucherParams.setSerialNo(super.getStringByByteNumber(l_strValue, 3));    
                      }
                        
                      //�o�^�敪
                      if (WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                      {
                          log.debug("&&&&&&&&&&&&&&&&&&&&&&�o�^�敪");
                          //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                          l_hostStockholderRegVoucherParams.setRegistDiv(super.getStringByByteNumber(l_strValue, 1));     
                      }
                        
                        
                      //���n
                      if (WEB3AccountOpenOutputItemSymbolNameDef.TAXATION_TRAN_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                      {
                          log.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&���n");
                          //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                          l_hostStockholderRegVoucherParams.setTaxationTranDiv(super.getStringByByteNumber(l_strValue, 1));    
                      }
                      //�ڋq���i�Łj
                      if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME_KANA.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                      {
                          log.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&�ڋq��");
                          //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                          l_hostStockholderRegVoucherParams.setAccountNameKana(super.getStringByByteNumber(l_strValue,19));    
                      }
                        
                      //�X�֔ԍ�
                      if (WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                      {
                          log.debug("&&&&&&&&&&&&&&&&&&&&&&&&&�X�֔ԍ�");
                          //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                          l_hostStockholderRegVoucherParams.setZipCode(super.getStringByByteNumber(l_strValue, 7));     
                      }
                        
                      //�Z���i�J�i�j
                      if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE_KANA.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                      {
                          log.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&�Z���i�J�i�j");
                          //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                          l_hostStockholderRegVoucherParams.setAddressLineKana(super.getStringByByteNumber(l_strValue, 70));    
                      }
                        
                      //����
                      if (WEB3AccountOpenOutputItemSymbolNameDef.STK_TRANSFER_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                      {
                          log.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&����");
                          //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                          l_hostStockholderRegVoucherParams.setTransferDiv(super.getStringByByteNumber(l_strValue, 1));
    
                      }
                        
                      //��s�R�[�h
                      if (WEB3AccountOpenOutputItemSymbolNameDef.FIN_INSTITUTION_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                      {
                          log.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&��s�R�[�h");
                          //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                          l_hostStockholderRegVoucherParams.setFinInstitutionCode(super.getStringByByteNumber(l_strValue, 4));
     
                      }    
                      //�x�X�R�[�h
                      if (WEB3AccountOpenOutputItemSymbolNameDef.FIN_BRANCH_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                      {
                          log.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&�x�X�R�[�h");
                          //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                          l_hostStockholderRegVoucherParams.setFinBranchCode(super.getStringByByteNumber(l_strValue, 3));
     
                      }   
                      //�a�����
                      if (WEB3AccountOpenOutputItemSymbolNameDef.FIN_SAVE_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                      {
                          log.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&�a�����");
                          //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                          l_hostStockholderRegVoucherParams.setFinSaveDiv(super.getStringByByteNumber(l_strValue, 1));
     
                      }   
                      //�����ԍ�
                      if (WEB3AccountOpenOutputItemSymbolNameDef.FIN_ACCOUNT_NO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                      {
                          log.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&�����ԍ�");
                          //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                          l_hostStockholderRegVoucherParams.setFinAccountNo(super.getStringByByteNumber(l_strValue, 7));
     
                      }   
                      //�������`
                      if (WEB3AccountOpenOutputItemSymbolNameDef.FIN_ACCOUNT_NAME.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                      {
                          log.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&�������`");
                          //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                          l_hostStockholderRegVoucherParams.setFinAccountName(super.getStringByByteNumber(l_strValue, 19));
     
                      }   
                      //�����敪
                      if (WEB3AccountOpenOutputItemSymbolNameDef.STATUS.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                      {
                          log.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&�����敪");
                          //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                          l_hostStockholderRegVoucherParams.setStatus(super.getStringByByteNumber(l_strValue, 1));
     
                      }   
                      //���ȋ敪
                      if (WEB3AccountOpenOutputItemSymbolNameDef.SELF_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                      {
                          log.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&���ȋ敪");
                          //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                          l_hostStockholderRegVoucherParams.setSelfDiv(super.getStringByByteNumber(l_strValue, 1));
     
                      }   
                      //�ŃR�[�h
                      if (WEB3AccountOpenOutputItemSymbolNameDef.TAX_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                      {
                          log.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&�ŃR�[�h");
                          //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                          l_hostStockholderRegVoucherParams.setTaxCode(super.getStringByByteNumber(l_strValue, 1));
     
                      }   

                  }
              }
            log.debug("l_hostStockholderRegVoucherParams.requestcode==wowow======"+l_hostStockholderRegVoucherParams.getRequestCode());
              //�Q�|�Q�j�@@�`�[�̎��ʃR�[�h�V�K�̔�
              log.debug("�Q�|�Q�j�@@�`�[�̎��ʃR�[�h�V�K�̔� "); 
              WEB3HostReqOrderNumberManageService l_reqOrderNumberManageService=
                  (WEB3HostReqOrderNumberManageService)Services.getService(
                      WEB3HostReqOrderNumberManageService.class);    
              String l_strNewNumber = l_reqOrderNumberManageService.getNewNumber(
                  this.getInstitutionCode(),
                  this.getBranchCode(),
                  ProductTypeEnum.OTHER);
              l_hostStockholderRegVoucherParams.setOrderRequestNumber(l_strNewNumber);
              log.debug("l_strNewNumber = " + l_strNewNumber);
            
              //�R�|�P�j�@@�����s�폜
              String l_strWhere =
                  "institution_code = ? and " +        //�،���ЃR�[�h = this.get�،���ЃR�[�h() And 
                  "acc_open_request_number = ? and " +    //���ʃR�[�h = this.get���ʃR�[�h() And   
                  "serial_no = ? and " +               //�`�[�ʔ� = �`�[�ʔ� And   
                  "status = ? ";                       //�����敪 = �h�������h  

              Object l_bindVars[] =
                  {l_strInstitutionCode,
                   l_strRequestNumber,
                   l_strSerialNo,
                   WEB3StatusDef.NOT_DEAL};
              log.debug("l_strInstitutionCode:::"+l_strInstitutionCode);
              log.debug("l_strRequestNumber::"+l_strRequestNumber);
              log.debug("l_strSerialNo::"+l_strSerialNo);
              log.debug("NOT_DEAL::"+ WEB3StatusDef.NOT_DEAL);
              log.debug("�R�|�P�j�@@�����s�폜");
              l_queryProcesser.doDeleteAllQuery(
              HostStockholderRegVoucherParams.TYPE,
                  l_strWhere,
                  l_bindVars);                      //DataQueryException, DataNetworkException
            
              //�R�|�Q�j�@@�`�[�s�}��
              log.debug("�R�|�Q�j�@@�`�[�s�}��"); 
              l_queryProcesser.doInsertQuery(l_hostStockholderRegVoucherParams);  
          }    
          catch (DataNetworkException l_ex)
          {
              log.error(l_ex.getMessage(), l_ex);            
              log.exiting(STR_METHOD_NAME);
              throw new WEB3SystemLayerException(
                  WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                  getClass().getName() + STR_METHOD_NAME,
                  l_ex.toString(),
                  l_ex);
          }
          catch (DataQueryException l_ex)
          {
              log.error(l_ex.getMessage(), l_ex);            
              log.exiting(STR_METHOD_NAME);
              throw new WEB3SystemLayerException(
                  WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                  getClass().getName() + STR_METHOD_NAME,
                  l_ex.toString(),
                  l_ex);
          }

          log.exiting(STR_METHOD_NAME);
      }

    /**
     *(saveDelete�`�[�s)<BR>
     *(saveDelete�`�[�s()�̎���)<BR>
     *�����J�ݓ`�[���P���폜����B<BR>
     *<BR>
     *���O���E����o�^�`�[�iG8610�j�L���[�e�[�u���̈ȉ��̏����ɓ��Ă͂܂�s���擾����B <BR>
     *<BR>
     *[����]<BR>
     *�،���ЃR�[�h =  this.get�،���ЃR�[�h() And<BR>
     *���ʃR�[�h = this.get���ʃR�[�h() And<BR>
     *�`�[�ʔ� = �`�[�ʔ� And<BR>
     *�����敪 = �h�������h<BR>
     *<BR>
     *�s���擾�ł��Ȃ������ꍇ�Afalse��ԋp����B<BR>
     *�s���擾�ł����ꍇ�A�Y���s�̍폜�idelete row�j���s���Atrue��ԋp����B<BR>
     *@@param l_strSerialNo - (�`�[�ʔ�)<BR>
     *�`�[�ʔ�<BR>
     *@@return boolean 
     *@@throws WEB3BaseException
     */
    public boolean saveDeleteVoucherRow(String l_strSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveDeleteVoucherRow(String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException
            String l_strWhere =
                "institution_code = ? and " +        //�،���ЃR�[�h = this.get�،���ЃR�[�h() And 
                "acc_open_request_number = ? and " +    //���ʃR�[�h = this.get���ʃR�[�h() And   
                "serial_no = ? and " +               //�`�[�ʔ� = �`�[�ʔ� And   
                "status = ? ";                       //�����敪 = �h�������h  

            String l_strInstitutionCode = this.getInstitutionCode();
            String l_strRequestNumber = this.getRequestNumber();
            
           log.debug("l_strInstitutionCode"+l_strInstitutionCode);
           log.debug("l_strRequestNumber"+l_strRequestNumber);
           log.debug("l_strSerialNo"+l_strSerialNo);
           log.debug("WEB3StatusDef.NOT_DEAL"+WEB3StatusDef.NOT_DEAL);
            Object l_bindVars[] =
                {l_strInstitutionCode,
                 l_strRequestNumber,
                 l_strSerialNo,
                 WEB3StatusDef.NOT_DEAL};
                    
            List l_lisRows = l_queryProcesser.doFindAllQuery(
            HostStockholderRegVoucherParams.TYPE,
                l_strWhere,
                l_bindVars);
                
            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                log.debug("�s���擾�ł��Ȃ������ꍇ�Afalse��ԋp����");
                log.exiting(STR_METHOD_NAME);
                return false;    
            }
            else
            {
                l_queryProcesser.doDeleteAllQuery(
                HostStockholderRegVoucherParams.TYPE,
                    l_strWhere,
                    l_bindVars);                       //DataQueryException, DataNetworkException
                
                log.debug("�s���擾�ł����ꍇ�A�Y���s�̍폜�idelete row�j���s���Atrue��ԋp����B");
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }

    }


    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject#getDataSourceObject()
     */
    public Object getDataSourceObject()
    {
        // TODO Auto-generated method stub
        return null;
    }
    /**
     *�����J�݌����q�I�u�W�F�N�g���w�肵�A���O���E����o�^�`�[�C���X�^���X���쐬����B <BR>
     *<BR>
     *�P�j�@@�C���X�^���X���� <BR>
     *���O���E����o�^�`�[�C���X�^���X�𐶐�����B  <BR>
     *<BR>
     *�Q�j�@@�����J�݌����q�v���p�e�B�Z�b�g <BR>
     *���������C���X�^���X.set�����J�݌����q()�ɂāA�����J�݌����q�v���p�e�B���Z�b�g����B <BR>
     *<BR>
     *[set�����J�݌����q()�Ɏw�肷�����] <BR>
     *�����J�݌����q�F�@@�����J�݌����q <BR>
     *<BR>
     *�R�j�@@�����J�ݓ`�[�}�X�^�v���p�e�B�Z�b�g <BR>
     *���������C���X�^���X.set�`�[�}�X�^()�ɂāA�`�[�}�X�^�s�v���p�e�B���Z�b�g����B <BR>
     *<BR>
     *�S�j�@@���������C���X�^���X��ԋp����B <BR>
     *@@param l_accOpenExpAccountOpen - (�����J�݌����q) <BR>
     *�����J�݌����q�I�u�W�F�N�g <BR>
     *@@return WEB3AccOpenStockHolderRegVoucher<BR>
     *@@throws WEB3BaseException
     */
    public static WEB3AccOpenStockHolderRegVoucher getInstance(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInstance(WEB3AccOpenExpAccountOpen)";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@�C���X�^���X����
        WEB3AccOpenStockHolderRegVoucher l_accOpenStockHolderRegVoucher = new WEB3AccOpenStockHolderRegVoucher();
        
        //�Q�j�@@�����J�݌����q�v���p�e�B�Z�b�g
        l_accOpenStockHolderRegVoucher.setAccOpenExpAccountOpen(l_accOpenExpAccountOpen);
        
        //�R�j�@@�����J�ݓ`�[�}�X�^�v���p�e�B�Z�b�g
        l_accOpenStockHolderRegVoucher.setAccOpenVoucherMaster();

        log.exiting(STR_METHOD_NAME);
        return l_accOpenStockHolderRegVoucher;
    }
    /**
         *���͕������ƌ����J�݌����q�e�[�u���̕������̔�r
         *@@return Object
         *@@roseuid 41B45E6D033C
         */
        private String nameCompare(String l_str)
        {
            final String STR_METHOD_NAME = " nameCompare(String)";
            log.entering(STR_METHOD_NAME);
        
            if (l_str == null)
            {
                log.debug("str ===============nulllllllllllllll");
                log.exiting(STR_METHOD_NAME);
                return null;
            }
        
            ExpAccountOpenParams l_expAccountOpenParams = new ExpAccountOpenParams((ExpAccountOpenRow)this.accOpenExpAccountOpen.getDataSourceObject());

            Field[] l_field = l_expAccountOpenParams.getClass().getDeclaredFields();
        
            int l_int = 0;
            if (l_field != null)
            {
                l_int = l_field.length;
                log.debug("in????????");
            }
        
            for (int i = 0; i < l_int; i++)
            {
                if (l_str.equals(l_field[i].getName()))
                {
                    log.exiting(STR_METHOD_NAME);
                    if (l_expAccountOpenParams.getColumn(l_str) != null)
                    {    
                        String l_strReturn = l_expAccountOpenParams.getColumn(l_str).toString();
                        log.debug("l_strReturn======-----========="+l_strReturn);
                        return l_strReturn;  

                    }
                    else
                    {
                        return null;  
                    }
                }
            }
        
            log.exiting(STR_METHOD_NAME);
            return null;   
        }





}
@
