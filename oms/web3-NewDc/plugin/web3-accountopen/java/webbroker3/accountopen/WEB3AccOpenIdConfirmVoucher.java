head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.29.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenIdConfirmVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �{�l�m�F�`�[(WEB3AccOpenIdConfirmVoucher.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/12/21 ���o�� �V�K�쐬
                      2006/07/13 ����� �d�l�ύX�E���f��077
                      2006/07/18 ����� ����̍X�E���f��081
Revesion History    : 2009/08/13 ���g �c�a���C�A�E�gNo.059,No.060
Revesion History    : 2009/08/28 �����F�@@���f��194
*/

package webbroker3.accountopen;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.accountopen.data.AccOpenVoucherItemRow;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.data.IdConfirmVoucherParams;
import webbroker3.accountopen.data.IdConfirmVoucherRow;
import webbroker3.accountopen.define.WEB3AccountOpenExpAccountOpenSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenOutputItemSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenVoucherCodeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CatDelimitterDef;
import webbroker3.common.define.WEB3DataClassDef;
import webbroker3.common.define.WEB3EditWayDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3RegDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeEra;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�{�l�m�F�`�[)<BR>
 * �{�l�m�F�`�[<BR>
 * 
 * @@author ���o��
 * @@version 1.0 
 */
public class WEB3AccOpenIdConfirmVoucher extends WEB3AccOpenVoucher
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AccOpenIdConfirmVoucher.class);

    /**
     * @@roseuid 41B45E6D00EA
     */
    public WEB3AccOpenIdConfirmVoucher()
    {

    }

    /**
     * (getInstance)<BR>
     * �����J�݌����q�I�u�W�F�N�g���w�肵�A�{�l�m�F�`�[�C���X�^���X���쐬����B<BR>
     * <BR>
     * �P�j�@@�C���X�^���X����<BR>
     * �@@�{�l�m�F�`�[�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�����J�݌����q�v���p�e�B�Z�b�g<BR>
     * �@@���������C���X�^���X.set�����J�݌����q()�ɂāA�����J�݌����q<BR>
     * �v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@[set�����J�݌����q()�Ɏw�肷�����]<BR>
     * �@@�����J�݌����q�F�@@�����J�݌����q<BR>
     * <BR>
     * �R�j�@@�����J�ݓ`�[�}�X�^�v���p�e�B�Z�b�g<BR>
     * �@@���������C���X�^���X.set�`�[�}�X�^()�ɂāA�`�[�}�X�^�s�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �S�j�@@���������C���X�^���X��ԋp����B<BR>
     * @@param l_accOpenExpAccountOpen - �����J�݌����q�I�u�W�F�N�g
     *
     * @@return webbroker3.accountopen.WEB3AccOpenIdConfirmVoucher
     * @@roseuid 41931181018D
     */
    public static WEB3AccOpenIdConfirmVoucher getInstance(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInstance(WEB3AccOpenExpAccountOpen)";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@�C���X�^���X����
        WEB3AccOpenIdConfirmVoucher l_accOpenIdConfirmVoucher = new WEB3AccOpenIdConfirmVoucher();
        
        //�Q�j�@@�����J�݌����q�v���p�e�B�Z�b�g
        l_accOpenIdConfirmVoucher.setAccOpenExpAccountOpen(l_accOpenExpAccountOpen);
        
        //�R�j�@@�����J�ݓ`�[�}�X�^�v���p�e�B�Z�b�g
        l_accOpenIdConfirmVoucher.setAccOpenVoucherMaster();

        log.exiting(STR_METHOD_NAME);
        return l_accOpenIdConfirmVoucher;
    }

    /**
     * (is�I�����C���`�[)<BR>
     * �iis�I�����C���`�[()�̎����j<BR>
     * <BR>
     * false��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 41931181019D
     */
    public boolean isOnlineVoucher()
    {
        final String STR_METHOD_NAME = " isOnlineVoucher()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get�f�[�^�R�[�h)<BR>
     * �iget�f�[�^�R�[�h()�̎����j<BR>
     * <BR>
     * �f�[�^�R�[�h.�{�l�m�F�iGI839�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 41931181019E
     */
    public String getRequestCode()
    {
        final String STR_METHOD_NAME = " getRequestCode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return WEB3HostRequestCodeDef.ACCOPEN_ID_CONFIRM;
    }

    /**
     * (get�`�[�R�[�h)<BR>
     * �iget�`�[�R�[�h()�̎����j<BR>
     * <BR>
     * �hG1175�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 419DDFC101CC
     */
    public String getVoucherCode()
    {
        final String STR_METHOD_NAME = " getVoucherCode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return WEB3AccountOpenVoucherCodeDef.ID_CONFIRM_VOUCHER_CODE;
    }

    /**
     * (get�m��ύ��ږ�)<BR>
     * �iget�m��ύ��ږ�()�̎����j<BR>
     * <BR>
     * ���Y�`�[�Ŏg�p���Ă�������J�݌����q�̗񕨗�����z��Ŏ擾����B<BR>
     * <BR>
     * �P�j�@@�`�[�g�p����Table�i�FHashtable�j����<BR>
     * �@@Hashtable�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�g�p���ڃZ�b�g<BR>
     * <BR>
     * �@@this.�����J�ݓ`�[�}�X�^�s[]�̊e�v�f���ɁA�Q�|�P�j�`�Q�|�R�j�̏�����<BR>
     * ���{����B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�쐬�\����<BR>
     * �@@�@@is�쐬�\�`�[()�ɂāA�쐬�\�ȓ`�[���𔻒肷��B<BR>
     * <BR>
     * �@@�@@[is�쐬�\�`�[()�Ɏw�肷�����]<BR>
     * �@@�@@�`�[�ʔԁF�@@�����J�ݓ`�[�}�X�^�s[index].�`�[�ʔ�<BR>
     * <BR>
     * �@@�@@�쐬�\�łȂ��ꍇ�iis�쐬�\�`�[() == false�j�̂݁A�Q�|�Q�j�����{����B<BR>
     * �@@�@@�쐬�\�ȏꍇ�iis�쐬�\�`�[() == true�j�A���Y�v�f�̏�����<BR>
     * �s�킸���̗v�f����������B�icontinue;�j<BR>
     * <BR>
     * �@@�@@���쐬�\�ȏꍇ�A���ڒl�͕ύX���Ă��ǂ��̂�<BR>
     * �`�[�Q�ƍ��ږ��ɂ͊܂߂Ȃ��B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾<BR>
     * �@@�@@�{�l�m�F�`�[�iG1175�j�e�[�u���̊e���ڂɂ��āA���L�̏��������{����B<BR>
     * <BR>
     * �@@�@@this.get�J�X�^�}�C�Y�Q�ƍ���()���R�[�����A�����J�݌����q�e�[�u����<BR>
     * �������̔z����擾����B<BR>
     * <BR>
     * �@@�@@[get�J�X�^�}�C�Y�Q�ƍ���()�Ɏw�肷�����]<BR>
     * �@@�@@�`�[�ʔԁF�@@�����J�ݓ`�[�}�X�^�s[index].�`�[�ʔ�<BR>
     * �@@�@@�`�[�o�͍��ڕ������F�@@�i��1 �{�l�m�F�`�[�iG1175�j�e�[�u����<BR>
     * �����Ώۍ��ځj<BR>
     * �@@�@@�`�[�Q�ƍ��ڏ����l�F�@@�i��2 �{�l�m�F�`�[�iG1175�j�e�[�u����<BR>
     * �����Ώۍ��ڃf�t�H���g�ݒ�l�j<BR>
     * <BR>
     * �@@�@@�i��2�j�@@�{�l�m�F�`�[�iG1175�j�e�[�u���̏����Ώۍ��ڃf�t�H���g�ݒ�l<BR>
     * �@@�@@DB���C�A�E�g �u�{�l�m�F�`�[�iG1175�j�e�[�u��.xls#�f�t�H���gDB�ݒ�_���v<BR>
     * �V�[�g���Q�Ƃ��A<BR>
     * �@@�@@�Y�����ڂ̐������ɁA�����J�݌����q�e�[�u������ҏW����<BR>
     * �w�肪����΁A�w�荀�ڂ̗񕨗����z��B<BR>
     * �@@�@@�ȊO�́Anull�B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�<BR>
     * �@@�@@�`�[�g�p����Table�i�FHashtable�j.put()�ɂ�this.get�J�X�^�}�C�Y�Q�ƍ���()<BR>
     * �߂�l����v�f���ǉ�����B<BR>
     * <BR>
     * �@@�@@[put()�Ɏw�肷�����]<BR>
     * �@@�@@key�F�@@this.get�J�X�^�}�C�Y�Q�ƍ���()[n]<BR>
     * �@@�@@value�F�@@this.get�J�X�^�}�C�Y�Q�ƍ���()[n]<BR>
     * <BR>
     * �@@�@@�� key�Cvalue�ɓ����l���Z�b�g����B<BR>
     * <BR>
     * �R�j�@@���ږ��z��ԋp<BR>
     * �@@�`�[�g�p����Table�i�FHashtable�j.values()�@@��ԋp����B<BR>
     * @@return String[]
     * @@roseuid 4193118101A0
     */
    public String[] getConfirmedItemName() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getConfirmedItemName()";
        log.entering(STR_METHOD_NAME);
        //�`�[�g�p����Table�i�FHashtable�j���� 
        Hashtable l_voucherItemList = new Hashtable();
        
        //this.�����J�ݓ`�[�}�X�^�s[]�̊e�v�f���ɁA�Q�|�P�j�`�Q�|�R�j�̏��������{����B
        int l_intLength = 0;
        if (accOpenVoucherMasterParamses != null)
        {
            l_intLength = this.accOpenVoucherMasterParamses.length;
        }
        for (int i = 0; i < l_intLength; i++)
        {
            String l_strSerialNo = accOpenVoucherMasterParamses[i].getSerialNo();
            //�Q�|�P�j�@@�쐬�\����
            boolean l_blnCreatedPossibleVoucher = this.isCreatedPossibleVoucher(l_strSerialNo);

            //�쐬�\�łȂ��ꍇ�iis�쐬�\�`�[() == false�j�̂݁A�Q�|�Q�j�����{����
            if (!l_blnCreatedPossibleVoucher)
            {                
                String[] l_strValues = new String[1];
                //U01000
                //�،���ЃR�[�h
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSTITUTION_CODE;
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                String[] l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE, l_strValues);   
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                int l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�f�[�^�R�[�h
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE, null);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************�f�[�^�R�[�h");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //���X�R�[�h
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.BRANCH_CODE;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.BRANCH_CODE, l_strValues);   
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //�ڋq�R�[�h
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ACCOUNT_CODE;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_CODE, l_strValues);   
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //���҃R�[�h
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.SONAR_TRADER_CODE;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.TRADER_CODE, l_strValues);   
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //���ʃR�[�h�i�����J�݌����q�j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ACC_OPEN_REQUEST_NUMBER;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�`�[�ʔ�
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO, null);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�o�^�敪
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DIV, null);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�f�|�^���
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.DATA_CLASS, null);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�{�l�����敪
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ID_ATTRIBUTE_DIV, null);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�Ώێ���敪
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.TRADING_DIV, null);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�m�F���@@�敪
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_WAY_DIV, null);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�m�F���ދ敪
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ID_CONFIRM_DOC_DIV;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_DOC_DIV, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�Z���m�F���ދ敪
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_CONFIRM_DOC, null);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�m�F��
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_DATE, null);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //������@@�敪
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.APPLI_MOTIVAT_DIV, null);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�����ړI�敪
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INVEST_PURPOSE_DIV;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.INVEST_PURPOSE_DIV, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�����o���i���������j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_EQUITY;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_EQUITY, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�����o���i�M�p����j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_MARGIN;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_MARGIN, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�����o���i���j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_BOND;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_BOND, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�����o���i�����M���j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_FUND_SK;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_FUND, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�����o���i�敨�E�I�v�V�����j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_FO;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_FO, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�����o���i�O���،��j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_F_EQUITY;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_F_EQUITY, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�����o���i���̑��j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_ETC;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_ETC, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�o���N���i���j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FROM;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_FROM, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�o���N���i���j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_TO;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_TO, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�����ށi���������j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_EQUITY;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EQUITY_TRADE_DIV, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�����ށi�M�p����j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_MARGIN;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.MARGIN_TRADE_DIIV, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�����ށi���j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_BOND;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.BOND_TRADE_DIV, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�����ށi�����M���j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_FUND;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.FUND_TRADE_DIV, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�����ށi�敨�E�I�v�V�����j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_FO;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.FO_TRADE_DIV, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�����ށi�O���،��j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_F_EQUITY;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.F_EQUITY_TRADE_DIV, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�����ށi���̑��j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_ETC;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ETC_TRADE_DIV, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�N���i���j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ANNUAL_INCOME_FROM;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ANNUAL_INCOME_FROM, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�N���i���j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ANNUAL_INCOME_TO;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ANNUAL_INCOME_TO, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //���Z���Y�i���j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ASSET_VALUE_FROM;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ASSET_VALUE_FROM, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //���Z���Y�i���j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ASSET_VALUE_TO;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ASSET_VALUE_TO, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�����敪
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.STATUS, null);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //�S���҂܂��͑㗝�l����
                //�敪
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.DIV, null);
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //�֌W
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.RELATION, null);
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //�m�F���@@
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_WAY, null);
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //�m�F����
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_DOC, null);
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //�Z���m�F����
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ADD_CONFIRM_DOC, null);
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //�m�F��
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.CHARGE_CONFIRM_DATE, null);
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //�S���ҁi�㗝�l�j��
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.CHARGE_NAME, null);
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //���N����
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.BORN_DATE, null);
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //�A����d�b�ԍ�(�s�O�ǔԁj
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE1, null);
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //�A����d�b�ԍ�(�ǔԁj
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE2, null);
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //�A����d�b�ԍ�(�ԍ��j
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE3, null);
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //�X�֔ԍ��i�e�j
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE1, null);
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //�X�֔ԍ��i�q�j
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE2, null);
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //�Z���P�i�����j
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1, null);
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //�Z���Q�i�����j
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2, null);
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //�Z���R�i�����j
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3, null);
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //�Z���P�i�J�i�j
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1_KANA, null);
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //�Z���Q�i�J�i�j
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2_KANA, null);
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //�Z���R�i�J�i�j
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3_KANA, null);
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
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
     * (save�`�[�s)<BR>
     * �isave�`�[�s()�̎����j<BR>
     * �����J�ݓ`�[���P���o�^����B<BR>
     * <BR>
     * �P�j�@@�f�t�H���g�ݒ�s����<BR>
     * �@@�{�l�m�F�`�[�iG1175�j�e�[�u���s�I�u�W�F�N�g�𐶐����A<BR>
     * �@@�f�t�H���g�ݒ�i��1�j�̒ʂ�Ƀv���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A<BR>
     * �f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�B<BR>
     * <BR>
     * �@@�i��1�j�@@�{�l�m�F�`�[�iG1175�j�e�[�u���̏����Ώۍ��ڃf�t�H���g�ݒ�<BR>
     * �@@DB���C�A�E�g �u�{�l�m�F�`�[�iG1175�j�e�[�u��.xls#�f�t�H���gDB�ݒ�_���v<BR>
     * �V�[�g�Q�ƁB<BR>
     * <BR>
     * �Q�j�@@�J�X�^�}�C�Y���ڃZ�b�g <BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^�e�[�u�����ȉ��̏����@@�Ō�������B<BR>
     * �@@�Y���s���Ȃ��ꍇ�A�����A�Ō�������B <BR>
     * <BR>
     * �@@[�����@@] <BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And <BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.���X�R�[�h = this.get���X�R�[�h() And <BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�����敪 = this.get�����敪() And <BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�f�[�^�R�[�h = this.get�f�[�^�R�[�h() And <BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�`�[�ʔ� = �`�[�ʔ� <BR>
     * <BR>
     * �@@[�����A] <BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And <BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.���X�R�[�h = "000" And <BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�����敪 = this.get�����敪() And <BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�f�[�^�R�[�h = this.get�f�[�^�R�[�h() And <BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�`�[�ʔ� = �`�[�ʔ� <BR>
     * <BR>
     * �@@�����@@�C�A�̂ǂ��炩�ŊY���s������ꍇ�́A�Q�|�P�j�̏��������{����B <BR>
     * <BR>
     * �@@�Q�|�P�j�@@�J�X�^�}�C�Y�ҏW<BR>
     * �@@�@@�������ʂ̊e�s���ɁA�o�͍��ڕ������������`�[���ڂ̒l���A<BR>
     * �w��̕��@@�ōăZ�b�g����B<BR>
     * �@@�@@��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A<BR>
     * �f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�B<BR>
     * <BR>
     * �@@�@@�� �i���ڕҏW���@@ == �Œ�l�j�̏ꍇ�A�Œ�Z�b�g�l�̒l���Z�b�g����B<BR>
     * �@@�@@�� �i���ڕҏW���@@ == �a����t�𐼗���t�ɕϊ��j�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�a����t(���͍��ڕ�����1(�N��)�A���͍��ڕ�����2(�N����)��<BR>
     * ���������J�݌����q�̍��ڒl)�𐼗���t�ɕϊ��iyyyymmdd�j�����l���Z�b�g����B <BR>
     * �@@�@@�@@�@@������ւ̕ϊ��́AWEB3GentradeEra#toDate()���g�p����<BR>
     * �@@�@@�� �ȊO�̏ꍇ�A���͍��ڕ������P�`�R�����������J�݌����q��<BR>
     * ���ڒl(��2)���Z�b�g����B<BR>
     * �@@�@@�@@�@@�|���͍��ڕ������P�`�R��null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�|�A�����ڃf���~�b�^���w�肳��Ă���ꍇ�i�A�����ڃf���~�b�^ !=
     * null�j�A<BR>
     * �@@�@@�@@�@@�@@���͍��ڕ������P�C�Q�C�R�̒l���f���~�b�^�ɂĘA������B<BR>
     * <BR>
     * �@@(��2) DB���C�A�E�g�u�����J�ݓ`�[���ڃ}�X�^�v�Q�ƁB<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�`�[�̎��ʃR�[�h�V�K�̔�<BR>
     * �@@�@@�������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h()�ɂĎ��ʃR�[�h���擾���A<BR>
     * �@@�@@�s�I�u�W�F�N�g�̎��ʃR�[�h�iorder_request_number�j�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@[get�V�K���ʃR�[�h()�Ɏw�肷�����]<BR>
     * �@@�@@�،���ЃR�[�h�F�@@this.get�،���ЃR�[�h()<BR>
     * �@@�@@���X�R�[�h�F�@@this.get���X�R�[�h()<BR>
     * �@@�@@�����^�C�v�F�@@ProductTypeEnum.���̑�<BR>
     * <BR>
     * �R�j�@@DB�X�V<BR>
     * �@@�R�|�P�j�@@�����s�폜<BR>
     * �@@�@@�ȉ��̏����ɂĖ{�l�m�F�`�[�iG1175�j�e�[�u������������B<BR>
     * �@@�@@�Y���s�����ɑ��݂���ꍇ�́A�Y���s��delete����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�،���ЃR�[�h =  this.get�،���ЃR�[�h() And<BR>
     * �@@�@@���ʃR�[�h = this.get���ʃR�[�h() And<BR>
     * �@@�@@�`�[�ʔ� = �`�[�ʔ� And<BR>
     * �@@�@@�����敪 = �h�������h<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�`�[�s�}��<BR>
     * �@@�@@�P�j�`�Q�j�ŕҏW�����s�I�u�W�F�N�g��DB�ɍX�V�iDB-insert����j�B<BR>
     * @@param l_strSerialNo - �`�[�ʔ�
     * @@roseuid 4193118101AC
     */
    public void saveVoucherRow(String l_strSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveVoucherRow(String)";
        log.entering(STR_METHOD_NAME);
        //�f�t�H���g�ݒ�s���� 
        IdConfirmVoucherParams l_idConfirmVoucherParams = new IdConfirmVoucherParams();
        
        //�f�t�H���g�ݒ�i��1�j�̒ʂ�Ƀv���p�e�B���Z�b�g����B
        try
        {             
            String l_strInstitutionCode = this.accOpenExpAccountOpen.getInstitutionCode();
            String l_strBranchCode = this.accOpenExpAccountOpen.getBranchCode();
            String l_strAccountCode = this.accOpenExpAccountOpen.getAccountCode();
            String l_strRequestNumber = this.accOpenExpAccountOpen.getRequestNumber();

            //�f�[�^�R�[�h
            l_idConfirmVoucherParams.setRequestCode(WEB3HostRequestCodeDef.ACCOPEN_ID_CONFIRM);
            //�،���ЃR�[�h
            l_idConfirmVoucherParams.setInstitutionCode(super.getStringByByteNumber(l_strInstitutionCode, 3));            
            //���X�R�[�h                    
            l_idConfirmVoucherParams.setBranchCode(super.getStringByByteNumber(l_strBranchCode, 3));            
            //�ڋq�R�[�h
            l_idConfirmVoucherParams.setAccountCode(super.getStringByByteNumber(l_strAccountCode, 7));
            
            //���҃R�[�h
            ExpAccountOpenRow l_expAccountOpenRow = (ExpAccountOpenRow)this.accOpenExpAccountOpen.getDataSourceObject();
            String l_strSonarTraderCode = l_expAccountOpenRow.getSonarTraderCode();
            l_idConfirmVoucherParams.setTraderCode(super.getStringByByteNumber(l_strSonarTraderCode, 5));
            
            //���ʃR�[�h�i�����J�݌����q�j
            l_idConfirmVoucherParams.setAccOpenRequestNumber(super.getStringByByteNumber(l_strRequestNumber, 13));
            //�`�[�ʔ�
            l_idConfirmVoucherParams.setSerialNo(super.getStringByByteNumber(l_strSerialNo, 3));
            //�o�^�敪
            l_idConfirmVoucherParams.setRegistDiv(WEB3RegDivDef.NEW);
            //�f�|�^���
            l_idConfirmVoucherParams.setDataClass(WEB3DataClassDef.DATA_RECORD);
            //�{�l�����敪
            l_idConfirmVoucherParams.setIdAttributeDiv("1");
            
            //�Ώێ���敪
            l_idConfirmVoucherParams.setTradingDiv("1");
            //�m�F���@@�敪
            l_idConfirmVoucherParams.setConfirmWayDiv("1");
            //�m�F���ދ敪
            l_idConfirmVoucherParams.setConfirmDocDiv(super.getStringByByteNumber(l_expAccountOpenRow.getIdConfirmDocDiv(), 2));
            //�Z���m�F���ދ敪
            l_idConfirmVoucherParams.setAddressConfirmDoc("1");
            //�m�F��
            l_idConfirmVoucherParams.setConfirmDate(null);
            
            //������@@�敪
            l_idConfirmVoucherParams.setAppliMotivatDiv("9");
            //�����ړI�敪
            l_idConfirmVoucherParams.setInvestPurposeDiv(super.getStringByByteNumber(l_expAccountOpenRow.getInvestPurposeDiv(), 1));
            //�����o���i���������j
            l_idConfirmVoucherParams.setExperienceEquity(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getExperienceFlagEquity().intValue()), 1));
            //�����o���i�M�p����j
            l_idConfirmVoucherParams.setExperienceMargin(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getExperienceFlagMargin().intValue()), 1));
            //�����o���i���j
            l_idConfirmVoucherParams.setExperienceBond(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getExperienceFlagBond().intValue()), 1));
            //�����o���i�����M���j
            l_idConfirmVoucherParams.setExperienceFund(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getExperienceFlagFundSk().intValue()), 1));
            //�����o���i�敨�E�I�v�V�����j
            l_idConfirmVoucherParams.setExperienceFo(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getExperienceFlagFo().intValue()), 1));
            //�����o���i�O���،��j
            l_idConfirmVoucherParams.setExperienceFEquity(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getExperienceFlagFEquity().intValue()), 1));
            //�����o���i���̑��j
            l_idConfirmVoucherParams.setExperienceEtc(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getExperienceFlagEtc().intValue()), 1));
            //�o���N���i���j
            l_idConfirmVoucherParams.setExperienceFrom(super.getStringByByteNumber(l_expAccountOpenRow.getExperienceFrom(), 2));
            //�o���N���i���j
            l_idConfirmVoucherParams.setExperienceTo(super.getStringByByteNumber(l_expAccountOpenRow.getExperienceTo(), 2));
            //�����ށi���������j
            l_idConfirmVoucherParams.setEquityTradeDiv(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getInterestFlagEquity().intValue()), 1));
            //�����ށi�M�p����j
            l_idConfirmVoucherParams.setMarginTradeDiv(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getInterestFlagMargin().intValue()), 1));
            //�����ށi���j
            l_idConfirmVoucherParams.setBondTradeDiv(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getInterestFlagBond().intValue()), 1));
            //�����ށi�����M���j
            l_idConfirmVoucherParams.setFundTradeDiv(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getInterestFlagFund().intValue()), 1));
            //�����ށi�敨�E�I�v�V�����j
            l_idConfirmVoucherParams.setFoTradeDiv(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getInterestFlagFo().intValue()), 1));
            //�����ށi�O���،��j
            l_idConfirmVoucherParams.setFEquityTradeDiv(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getInterestFlagFEquity().intValue()), 1));
            //�����ށi���̑��j
            l_idConfirmVoucherParams.setEtcTradeDiv(super.getStringByByteNumber(Integer.toString(l_expAccountOpenRow.getInterestFlagEtc().intValue()), 1));
            //�N���i���j
            l_idConfirmVoucherParams.setAnnualIncomeFrom(super.getStringByByteNumber(l_expAccountOpenRow.getAnnualIncomeFrom(), 6));
            //�N���i���j
            l_idConfirmVoucherParams.setAnnualIncomeTo(super.getStringByByteNumber(l_expAccountOpenRow.getAnnualIncomeTo(), 6));
            //���Z���Y�i���j
            l_idConfirmVoucherParams.setAssetValueFrom(super.getStringByByteNumber(l_expAccountOpenRow.getAssetValueFrom(), 6));
            //���Z���Y�i���j
            l_idConfirmVoucherParams.setAssetValueTo(super.getStringByByteNumber(l_expAccountOpenRow.getAssetValueTo(), 6));
            //�����敪
            l_idConfirmVoucherParams.setStatus(WEB3StatusDef.NOT_DEAL);
            Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
            //���M����
            l_idConfirmVoucherParams.setSendTimestamp(null);
            //�쐬����
            l_idConfirmVoucherParams.setCreatedTimestamp(l_tsSystemTimestamp);
            //�X�V����
            l_idConfirmVoucherParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);

            //�S���҂܂��͑㗝�l����
            l_idConfirmVoucherParams.setDiv(null);
            l_idConfirmVoucherParams.setRelation(null);
            l_idConfirmVoucherParams.setConfirmWay(null);
            l_idConfirmVoucherParams.setConfirmDoc(null);
            l_idConfirmVoucherParams.setAddConfirmDoc(null);
            l_idConfirmVoucherParams.setChargeConfirmDate(null);
            l_idConfirmVoucherParams.setChargeName(null);
            l_idConfirmVoucherParams.setBornDate(null);
            l_idConfirmVoucherParams.setTelephone1(null);
            l_idConfirmVoucherParams.setTelephone2(null);
            l_idConfirmVoucherParams.setTelephone3(null);
            l_idConfirmVoucherParams.setZipCode1(null);
            l_idConfirmVoucherParams.setZipCode2(null);
            l_idConfirmVoucherParams.setAddressLine1(null);
            l_idConfirmVoucherParams.setAddressLine2(null);
            l_idConfirmVoucherParams.setAddressLine3(null);
            l_idConfirmVoucherParams.setAddressLine1Kana(null);
            l_idConfirmVoucherParams.setAddressLine2Kana(null);
            l_idConfirmVoucherParams.setAddressLine3Kana(null);

            //�Q�j�@@�J�X�^�}�C�Y���ڃZ�b�g
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException
            //[�����@@] 
            String l_strWhereItem =
                "institution_code = ? and " +        //�����J�ݓ`�[���ڃ}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And 
                "branch_code = ? and " +             //�����J�ݓ`�[���ڃ}�X�^.���X�R�[�h = this.get���X�R�[�h() And  
                "account_div = ? and " +             //�����J�ݓ`�[���ڃ}�X�^.�����敪 = this.get�����敪() And  
                "request_code = ? and " +            //�����J�ݓ`�[���ڃ}�X�^.�f�[�^�R�[�h = this.get�f�[�^�R�[�h() And  
                "serial_no = ? ";                    //�����J�ݓ`�[���ڃ}�X�^.�`�[�ʔ� = �`�[�ʔ�

            String l_strRequestCode = this.getRequestCode();

            Object l_bindVarsItem[] =
                {l_strInstitutionCode,
                 l_strBranchCode,
                 this.getAccountDiv(),
                 l_strRequestCode,
                 l_strSerialNo};
                    
            List l_lisRowItems = null;
            l_lisRowItems =
                l_queryProcesser.doFindAllQuery(
                    AccOpenVoucherItemRow.TYPE,
                    l_strWhereItem,
                    l_bindVarsItem);        //DataQueryException, DataNetworkException
                     
            //�Y���s���Ȃ��ꍇ�A�����A�Ō�������B 
            if (l_lisRowItems == null || l_lisRowItems.size() == 0)
            {
                //[�����A]  
                Object l_bindVarsItem2[] =
                    {l_strInstitutionCode,
                     "000",
                     this.getAccountDiv(),
                     l_strRequestCode,
                     l_strSerialNo};
                    
                l_lisRowItems = null;
                l_lisRowItems =
                    l_queryProcesser.doFindAllQuery(
                        AccOpenVoucherItemRow.TYPE,
                        l_strWhereItem,
                        l_bindVarsItem2);     //DataQueryException, DataNetworkException
            }
            
            int l_intSize = 0;
            if (l_lisRowItems != null)
            {
                l_intSize = l_lisRowItems.size();
            }
            AccOpenVoucherItemRow l_accOpenVoucherItemRow = null;
            if (l_intSize > 0)
            {
                //�Q�|�P�j�@@�J�X�^�}�C�Y�ҏW
                for (int i = 0; i < l_intSize; i++)
                {
                    l_accOpenVoucherItemRow = (AccOpenVoucherItemRow)l_lisRowItems.get(i);
                    String l_strValue = null;
                    log.debug("�i���ڕҏW���@@ == �Œ�l�j�̏ꍇ�A�Œ�Z�b�g�l�̒l���Z�b�g����B");
                    //�i���ڕҏW���@@ == �Œ�l�j�̏ꍇ�A�Œ�Z�b�g�l�̒l���Z�b�g����B
                    if (WEB3EditWayDivDef.FIXED_VALUE.equals(l_accOpenVoucherItemRow.getEditWayDiv()))
                    {
                        l_strValue = l_accOpenVoucherItemRow.getFixedValue();
                    }
                    else if (WEB3EditWayDivDef.WEST_DATE_CHANGE_TO_JAP_DATE.equals(l_accOpenVoucherItemRow.getEditWayDiv()))
                    {
                        String l_strValue1 = this.nameCompare(l_accOpenVoucherItemRow.getInputItemSymbolName1());
                        String l_strValue2 = this.nameCompare(l_accOpenVoucherItemRow.getInputItemSymbolName2());
                        l_strValue = WEB3DateUtility.formatDate(
                            WEB3GentradeEra.toDate(l_strValue1, l_strValue2), WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                    }
                    else
                    {                      
                        String l_strValue1 = this.nameCompare(l_accOpenVoucherItemRow.getInputItemSymbolName1());
                        String l_strValue2 = this.nameCompare(l_accOpenVoucherItemRow.getInputItemSymbolName2());
                        String l_strValue3 = this.nameCompare(l_accOpenVoucherItemRow.getInputItemSymbolName3());
                        if (WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM_TO_HALFKANA.equals(l_accOpenVoucherItemRow.getEditWayDiv()))
                        {
                            l_strValue1 = WEB3StringTypeUtility.to1byteKana(l_strValue1);
                            l_strValue2 = WEB3StringTypeUtility.to1byteKana(l_strValue2);
                            l_strValue3 = WEB3StringTypeUtility.to1byteKana(l_strValue3);
                        }

                        log.debug("���͍��ڕ������P�C�Q�C�R�̒l���f���~�b�^�ɂĘA������");
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
                    
                    int l_intValueLength = 0;
                    if (l_strValue != null)
                    {
                        l_intValueLength = WEB3StringTypeUtility.getByteLength(l_strValue);
                    }
                    
                    //�f�[�^�R�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {         
                        l_idConfirmVoucherParams.setRequestCode(super.getStringByByteNumber(l_strValue, 5));          
                    }
                        
                    //�،���ЃR�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă� 
                        l_idConfirmVoucherParams.setInstitutionCode(super.getStringByByteNumber(l_strValue, 3));    
                    }
                        
                    //���X�R�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.BRANCH_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setBranchCode(super.getStringByByteNumber(l_strValue, 3));    
                    }
                        
                    //�ڋq�R�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�  
                        l_idConfirmVoucherParams.setAccountCode(super.getStringByByteNumber(l_strValue, 7));   
                    }
                        
                    //���҃R�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.TRADER_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setTraderCode(super.getStringByByteNumber(l_strValue, 5));     
                    }
                        
                    //���ʃR�[�h�i�����J�݌����q�j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setAccOpenRequestNumber(super.getStringByByteNumber(l_strValue, 13));     
                    }
                        
                    //�`�[�ʔ�
                    if (WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setSerialNo(super.getStringByByteNumber(l_strValue, 3));     
                    }
                        
                    //�o�^�敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setRegistDiv(super.getStringByByteNumber(l_strValue, 1));     
                    }
                    
                    //�f�|�^���
                    if (WEB3AccountOpenOutputItemSymbolNameDef.DATA_CLASS.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�    
                        l_idConfirmVoucherParams.setDataClass(super.getStringByByteNumber(l_strValue, 2)); 
                    }

                    //�{�l�����敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ID_ATTRIBUTE_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setIdAttributeDiv(super.getStringByByteNumber(l_strValue, 1));   
                    }
            
                    //�Ώێ���敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.TRADING_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�     
                        l_idConfirmVoucherParams.setTradingDiv(super.getStringByByteNumber(l_strValue, 1));   
                    }

                    //�m�F���@@�敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_WAY_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setConfirmWayDiv(super.getStringByByteNumber(l_strValue, 1));     
                    }

                    //�m�F���ދ敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_DOC_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setConfirmDocDiv(super.getStringByByteNumber(l_strValue, 2));     
                    }

                    //�Z���m�F���ދ敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_CONFIRM_DOC.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă� 
                        l_idConfirmVoucherParams.setAddressConfirmDoc(super.getStringByByteNumber(l_strValue, 1));     
                    }

                    //�m�F��
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_DATE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        if (l_intValueLength >= 8)
                        {
                            l_idConfirmVoucherParams.setConfirmDate(
                                WEB3DateUtility.getDate(l_strValue, WEB3GentradeTimeDef.DATE_FORMAT_YMD));
                        }
                    }

                    //������@@�敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.APPLI_MOTIVAT_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setAppliMotivatDiv(super.getStringByByteNumber(l_strValue, 1));     
                    }

                    //�����ړI�敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.INVEST_PURPOSE_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setInvestPurposeDiv(super.getStringByByteNumber(l_strValue, 1));     
                    }

                    //�����o���i���������j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_EQUITY.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setExperienceEquity(super.getStringByByteNumber(l_strValue, 1));    
                    }
                    
                    //�����o���i�M�p����j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_MARGIN.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setExperienceMargin(super.getStringByByteNumber(l_strValue, 1));    
                    }
                    
                    //�����o���i���j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_BOND.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setExperienceBond(super.getStringByByteNumber(l_strValue, 1));      
                    }
            
                    //�����o���i�����M���j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_FUND.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setExperienceFund(super.getStringByByteNumber(l_strValue, 1));     
                    }
                    
                    //�����o���i�敨�E�I�v�V�����j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_FO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setExperienceFo(super.getStringByByteNumber(l_strValue, 1));     
                    }
                    
                    //�����o���i�O���،��j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_F_EQUITY.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setExperienceFEquity(super.getStringByByteNumber(l_strValue, 1));     
                    }
                    
                    //�����o���i���̑��j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_ETC.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setExperienceEtc(super.getStringByByteNumber(l_strValue, 1));      
                    }
            
                    //�o���N���i���j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_FROM.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setExperienceFrom(super.getStringByByteNumber(l_strValue, 2));      
                    }
                    
                    //�o���N���i���j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EXPERIENCE_TO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setExperienceTo(super.getStringByByteNumber(l_strValue, 2));     
                    }
                    
                    //�����ށi���������j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EQUITY_TRADE_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setEquityTradeDiv(super.getStringByByteNumber(l_strValue, 1));     
                    }
                    
                    //�����ށi�M�p����j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.MARGIN_TRADE_DIIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setMarginTradeDiv(super.getStringByByteNumber(l_strValue, 1));     
                    }
                    
                    //�����ށi���j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.BOND_TRADE_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setBondTradeDiv(super.getStringByByteNumber(l_strValue, 1));     
                    }
            
                    //�����ށi�����M���j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.FUND_TRADE_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setFundTradeDiv(super.getStringByByteNumber(l_strValue, 1));       
                    }
                    
                    //�����ށi�敨�E�I�v�V�����j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.FO_TRADE_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setFoTradeDiv(super.getStringByByteNumber(l_strValue, 1));            
                    }
                    
                    //�����ށi�O���،��j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.F_EQUITY_TRADE_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setFEquityTradeDiv(super.getStringByByteNumber(l_strValue, 1));     
                    }
                    
                    //�����ށi���̑��j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ETC_TRADE_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
     
                        l_idConfirmVoucherParams.setEtcTradeDiv(super.getStringByByteNumber(l_strValue, 1));    
                    }
            
                    //�N���i���j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ANNUAL_INCOME_FROM.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setAnnualIncomeFrom(super.getStringByByteNumber(l_strValue, 6));
                    }
                    
                    //�N���i���j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ANNUAL_INCOME_TO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setAnnualIncomeTo(super.getStringByByteNumber(l_strValue, 6));   
                    }

                    //���Z���Y�i���j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ASSET_VALUE_FROM.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setAssetValueFrom(super.getStringByByteNumber(l_strValue, 6));
    
                    }

                    //���Z���Y�i���j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ASSET_VALUE_TO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setAssetValueTo(super.getStringByByteNumber(l_strValue, 6));     
                    }
                        
                    //�����敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.STATUS.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setStatus(super.getStringByByteNumber(l_strValue, 1));          
                    }    
                    //�S���҂܂��͑㗝�l����
                    //�敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.DIV.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A
                        //�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setDiv(super.getStringByByteNumber(l_strValue, 1));          
                    }
                    //�֌W
                    if (WEB3AccountOpenOutputItemSymbolNameDef.RELATION.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A
                        //�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setRelation(super.getStringByByteNumber(l_strValue, 1));          
                    }
                    //�m�F���@@
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_WAY.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A
                        //�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setConfirmWay(super.getStringByByteNumber(l_strValue, 1));          
                    }
                    //�m�F����
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CONFIRM_DOC.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A
                        //�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setConfirmDoc(super.getStringByByteNumber(l_strValue, 2));          
                    }
                    //�Z���m�F����
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ADD_CONFIRM_DOC.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A
                        //�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setAddConfirmDoc(super.getStringByByteNumber(l_strValue, 1));          
                    }
                    //�m�F��
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CHARGE_CONFIRM_DATE.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A
                        //�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        if (l_intValueLength >= 8)
                        {
                            l_idConfirmVoucherParams.setChargeConfirmDate(
                                WEB3DateUtility.getDate(l_strValue, WEB3GentradeTimeDef.DATE_FORMAT_YMD));
                        }
                    }
                    //�S���ҁi�㗝�l�j��
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CHARGE_NAME.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A
                        //�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setChargeName(super.getStringByByteNumber(l_strValue, 18));
                    }
                    //���N����
                    if (WEB3AccountOpenOutputItemSymbolNameDef.BORN_DATE.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A
                        //�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        if (l_intValueLength >= 8)
                        {
                            l_idConfirmVoucherParams.setBornDate(
                                WEB3DateUtility.getDate(l_strValue, WEB3GentradeTimeDef.DATE_FORMAT_YMD));
                        }
                    }
                    //�A����d�b�ԍ�(�s�O�ǔԁj
                    if (WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE1.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A
                        //�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setTelephone1(super.getStringByByteNumber(l_strValue, 16));
                    }
                    //�A����d�b�ԍ�(�ǔԁj
                    if (WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE2.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A
                        //�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setTelephone2(super.getStringByByteNumber(l_strValue, 4));
                    }
                    //�A����d�b�ԍ�(�ԍ��j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE3.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A
                        //�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setTelephone3(super.getStringByByteNumber(l_strValue, 4));
                    }
                    //�X�֔ԍ��i�e�j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE1.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A
                        //�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setZipCode1(super.getStringByByteNumber(l_strValue, 7));
                    }
                    //�X�֔ԍ��i�q�j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE2.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A
                        //�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setZipCode2(super.getStringByByteNumber(l_strValue, 4));
                    }
                    //�Z���P�i�����j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A
                        //�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setAddressLine1(super.getStringByByteNumber(l_strValue, 32));
                    }
                    //�Z���Q�i�����j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A
                        //�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setAddressLine2(super.getStringByByteNumber(l_strValue, 32));
                    }
                    //�Z���R�i�����j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A
                        //�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setAddressLine3(super.getStringByByteNumber(l_strValue, 32));
                    }
                    //�Z���P�i�J�i�j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1_KANA.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A
                        //�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setAddressLine1Kana(super.getStringByByteNumber(l_strValue, 16));
                    }
                    //�Z���Q�i�J�i�j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2_KANA.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A
                        //�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setAddressLine2Kana(super.getStringByByteNumber(l_strValue, 27));
                    }
                    //�Z���R�i�J�i�j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3_KANA.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A
                        //�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_idConfirmVoucherParams.setAddressLine3Kana(super.getStringByByteNumber(l_strValue, 27));
                    }
                }
            }
            
            //�Q�|�Q�j�@@�`�[�̎��ʃR�[�h�V�K�̔� 
            WEB3HostReqOrderNumberManageService l_reqOrderNumberManageService=
                (WEB3HostReqOrderNumberManageService)Services.getService(
                    WEB3HostReqOrderNumberManageService.class);    
            String l_strNewNumber = l_reqOrderNumberManageService.getNewNumber(
                l_strInstitutionCode,
                l_strBranchCode,
                ProductTypeEnum.OTHER);
            l_idConfirmVoucherParams.setOrderRequestNumber(l_strNewNumber);
            
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
                    
            l_queryProcesser.doDeleteAllQuery(
                IdConfirmVoucherRow.TYPE,
                l_strWhere,
                l_bindVars);       //DataQueryException, DataNetworkException
            
            //�R�|�Q�j�@@�`�[�s�}�� 
            l_queryProcesser.doInsertQuery(l_idConfirmVoucherParams);  
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
     * (saveDelete�`�[�s)<BR>
     * �isaveDelete�`�[�s()�̎����j<BR>
     * �����J�ݓ`�[���P���폜����B<BR>
     * <BR>
     * �{�l�m�F�`�[�iG1175�j�e�[�u���̈ȉ��̏����ɓ��Ă͂܂�s���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h =  this.get�،���ЃR�[�h() And<BR>
     * �@@���ʃR�[�h = this.get���ʃR�[�h() And<BR>
     * �@@�`�[�ʔ� = �`�[�ʔ� And<BR>
     * �@@�����敪 = �h�������h<BR>
     * <BR>
     * �s���擾�ł��Ȃ������ꍇ�Afalse��ԋp����B<BR>
     * �s���擾�ł����ꍇ�A�Y���s�̍폜�idelete row�j���s���Atrue��ԋp����B<BR>
     * @@param l_strSerialNo - �`�[�ʔ�
     *
     * @@return boolean
     * @@roseuid 419C2C8B0293
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
            
            Object l_bindVars[] =
                {l_strInstitutionCode,
                 l_strRequestNumber,
                 l_strSerialNo,
                 WEB3StatusDef.NOT_DEAL};
                    
            List l_lisRows = l_queryProcesser.doFindAllQuery(
                IdConfirmVoucherRow.TYPE,
                l_strWhere,
                l_bindVars);   //DataQueryException, DataNetworkException
                
            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return false;    
            }
            else
            {
                l_queryProcesser.doDeleteAllQuery(
                    IdConfirmVoucherRow.TYPE,
                    l_strWhere,
                    l_bindVars);    //DataQueryException, DataNetworkException
                
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

    /**
     * @@return Object
     * @@roseuid 41B45E6D0119
     */
    public Object getDataSourceObject()
    {
         return null;
    }
    
    /**
     * ���͕������ƌ����J�݌����q�e�[�u���̕������̔�r
     * 
     * @@return Object
     * @@roseuid 41B45E6D033C
     */
    private String nameCompare(String l_str)
    {
        final String STR_METHOD_NAME = " nameCompare(String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_str == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        ExpAccountOpenParams l_expAccountOpenParams = new ExpAccountOpenParams((ExpAccountOpenRow)this.accOpenExpAccountOpen.getDataSourceObject());

        Field[] l_field = l_expAccountOpenParams.getClass().getDeclaredFields();
        
        int l_int = 0;
        if (l_field != null)
        {
            l_int = l_field.length;
        }
        for (int i = 0; i < l_int; i++)
        {
            if (l_str.equals(l_field[i].getName()))
            {
                log.exiting(STR_METHOD_NAME);
                if (l_expAccountOpenParams.getColumn(l_str) != null)
                {
                    String l_strReturn = l_expAccountOpenParams.getColumn(l_str).toString();
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
