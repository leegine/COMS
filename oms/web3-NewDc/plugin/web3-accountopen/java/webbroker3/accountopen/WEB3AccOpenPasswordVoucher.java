head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.28.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenPasswordVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ïؔԍ��`�[(WEB3AccOpenPasswordVoucher.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/12/14 ���o�� �V�K�쐬
                      2006/07/13 ����� �d�l�ύX�E���f��077
                      2006/07/18 ����� (���u)�d�l�ύX�E���f��081
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
import webbroker3.accountopen.data.PasswordVoucherParams;
import webbroker3.accountopen.data.PasswordVoucherRow;
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
import webbroker3.common.define.WEB3PubReasonDivDef;
import webbroker3.common.define.WEB3RegistDeleteDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3VoucherNameDef;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ïؔԍ��`�[)<BR>
 * �Ïؔԍ��`�[<BR>
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3AccOpenPasswordVoucher extends WEB3AccOpenVoucher
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AccOpenPasswordVoucher.class);
    
    /**
     * @@roseuid 41B45E6E003E
     */
    public WEB3AccOpenPasswordVoucher()
    {

    }

    /**
     * (getInstance)<BR>
     * �����J�݌����q�I�u�W�F�N�g���w�肵�A�Ïؔԍ��`�[�C���X�^���X���쐬����B<BR>
     * <BR>
     * �P�j�@@�C���X�^���X����<BR>
     * �@@�Ïؔԍ��`�[�C���X�^���X�𐶐�����B<BR>
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
     * <BR>�@@
     * �S�j�@@���������C���X�^���X��ԋp����B<BR>
     * @@param l_accOpenExpAccountOpen - �����J�݌����q�I�u�W�F�N�g
     *
     * @@return webbroker3.accountopen.WEB3AccOpenPasswordVoucher
     * @@roseuid 4193128F015E
     */
    public static WEB3AccOpenPasswordVoucher getInstance(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInstance(WEB3AccOpenExpAccountOpen)";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@�C���X�^���X����
        WEB3AccOpenPasswordVoucher l_accOpenPasswordVoucher = new WEB3AccOpenPasswordVoucher();
        
        //�Q�j�@@�����J�݌����q�v���p�e�B�Z�b�g
        l_accOpenPasswordVoucher.setAccOpenExpAccountOpen(l_accOpenExpAccountOpen);
        
        //�R�j�@@�����J�ݓ`�[�}�X�^�v���p�e�B�Z�b�g
        l_accOpenPasswordVoucher.setAccOpenVoucherMaster();

        log.exiting(STR_METHOD_NAME);
        return l_accOpenPasswordVoucher;
    }

    /**
     * (is�I�����C���`�[)<BR>
     * �iis�I�����C���`�[()�̎����j<BR>
     * <BR>
     * false��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 4193128F0160
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
     * �f�[�^�R�[�h.�Ïؔԍ��iGI842�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 4193128F0161
     */
    public String getRequestCode()
    {
        final String STR_METHOD_NAME = " getRequestCode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return WEB3HostRequestCodeDef.ACCOPEN_PASSWORD;
    }

    /**
     * (get�`�[�R�[�h)<BR>
     * �iget�`�[�R�[�h()�̎����j<BR>
     * <BR>
     * �hG5511�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 419DDE4A016E
     */
    public String getVoucherCode()
    {
        final String STR_METHOD_NAME = " getVoucherCode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return WEB3AccountOpenVoucherCodeDef.PASSWORD_VOUCHER_CODE;
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
     * �@@�@@�Ïؔԍ��`�[�iG5511�j�e�[�u���̊e���ڂɂ��āA���L�̏��������{����B<BR>
     * <BR>
     * �@@�@@this.get�J�X�^�}�C�Y�Q�ƍ���()���R�[�����A�����J�݌����q�e�[�u����<BR>
     * �������̔z����擾����B<BR>
     * <BR>
     * �@@�@@[get�J�X�^�}�C�Y�Q�ƍ���()�Ɏw�肷�����]<BR>
     * �@@�@@�`�[�ʔԁF�@@�����J�ݓ`�[�}�X�^�s[index].�`�[�ʔ�<BR>
     * �@@�@@�`�[�o�͍��ڕ������F�@@�i��1 �Ïؔԍ��`�[�iG5511�j�e�[�u����<BR>
     * �����Ώۍ��ځj<BR>
     * �@@�@@�`�[�Q�ƍ��ڏ����l�F�@@�i��2 �Ïؔԍ��`�[�iG5511�j�e�[�u����<BR>
     * �����Ώۍ��ڃf�t�H���g�ݒ�l�j<BR>
     * <BR>
     * �@@�@@�i��2�j�@@�Ïؔԍ��`�[�iG5511�j�e�[�u���̏����Ώۍ��ڃf�t�H���g�ݒ�l<BR>
     * �@@�@@DB���C�A�E�g �u�Ïؔԍ��`�[�iG5511�j�e�[�u��.xls#�f�t�H���gDB�ݒ�_���v<BR>
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
     * @@roseuid 4193128F016F
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
        }
        
        log.debug("&&&&&&&&&&&&&&&&&l_intLength = " + l_intLength);
        for (int i = 0; i < l_intLength; i++)
        {
            log.debug("�����J�ݓ`�[�}�X�^�s[]�̊e�v�f���ɁA�Q�|�P�j�`�Q�|�R�j�̏��������{����");
            //�Q�|�P�j�@@�쐬�\����
            String l_strSerialNo = accOpenVoucherMasterParamses[i].getSerialNo();
            //�Q�|�P�j�@@�쐬�\����
            boolean l_blnCreatedPossibleVoucher = this.isCreatedPossibleVoucher(l_strSerialNo);
            
            //�쐬�\�łȂ��ꍇ�iis�쐬�\�`�[() == false�j�̂݁A�Q�|�Q�j�����{����
            if (!l_blnCreatedPossibleVoucher)
            {
                
                log.debug("�쐬�\�łȂ��ꍇ�iis�쐬�\�`�[() == false�j�̂݁A�Q�|�Q�j�����{����");
                String[] l_strValues1 = new String[1];
                //U01000
                //�،���ЃR�[�h
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSTITUTION_CODE;        
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                String[] l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE, l_strValues1);
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
                    log.debug("********************************�،���ЃR�[�h");
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
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.BRANCH_CODE;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.BRANCH_CODE, l_strValues1);   
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("***************************���X�R�[�h");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }               
                
                //�ڋq�R�[�h
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ACCOUNT_CODE;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_CODE, l_strValues1);    
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("****************************�ڋq�R�[�h");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }                
                
                //���҃R�[�h
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.SONAR_TRADER_CODE;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.TRADER_CODE, l_strValues1);
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**************************���҃R�[�h");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }               
                
                //���ʃR�[�h�i�����J�݌����q�j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER, l_strValues1);    
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("************************���ʃR�[�h�i�����J�݌����q�j");
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
                    log.debug("***************************�`�[�ʔ�");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //���s����敪
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DELETE_DIV, null);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("****************************���s����敪");
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
                    log.debug("************************�f�|�^���");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�f�[�^�R�[�h�i�`�[���j
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.VOUCHER_NAME, null);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("*****************************�f�[�^�R�[�h�i�`�[���j");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�\���N����
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DATE, null);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**************************�\���N����");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�Ïؔԍ�
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INITIAL_PASSWORD;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.PASSWORD, l_strValues1);    
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("***********************�Ïؔԍ�");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //���s���R
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.PUB_REASON_DIV, null);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("********************���s���R");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�����ԍ�
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.REF_NUMBER, null);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("***********************�����ԍ�");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //��������
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                String[] l_strValues2 = new String[2];
                l_strValues2[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_NAME_ALT1;
                l_strValues2[1] = WEB3AccountOpenExpAccountOpenSymbolNameDef.GIVEN_NAME_ALT1;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.MODIFIED_NAME, l_strValues2);    
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**************************��������");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�J�[�h�^�C�v
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CARD_TYPE, null);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**************************�J�[�h�^�C�v");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //�I�����C���E�o�b�`�敪
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ONLINE_BATCH_DIV, null);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("************************�I�����C���E�o�b�`�敪");
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }
                
                //SEQ_NO
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.SEQ_NO, null);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItem != null)
                {
                    l_int = l_strCustomizingRefItem.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("************************SEQ_NO");
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
                    log.debug("**********************�����敪");
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
     * �@@�Ïؔԍ��`�[�iG5511�j�e�[�u���s�I�u�W�F�N�g�𐶐����A<BR>
     * �@@�f�t�H���g�ݒ�i��1�j�̒ʂ�Ƀv���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A<BR>
     * �f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�B<BR>
     * <BR>
     * �@@�i��1�j�@@�Ïؔԍ��`�[�iG5511�j�e�[�u���̏����Ώۍ��ڃf�t�H���g�ݒ�<BR>
     * �@@DB���C�A�E�g �u�Ïؔԍ��`�[�iG5511�j�e�[�u��.xls#�f�t�H���gDB�ݒ�_���v<BR>
     * �V�[�g�Q�ƁB<BR>
     * <BR>
     * �Q�j�@@�J�X�^�}�C�Y���ڃZ�b�g <BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^�e�[�u�����ȉ��̏����@@�Ō�������B <BR>
     * �@@�Y���s���Ȃ��ꍇ�A�����A�Ō�������B <BR>
     * <BR>
     * �@@[�����@@] <BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And<BR>
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
     * �@@�@@�ȉ��̏����ɂĈÏؔԍ��`�[�iG5511�j�e�[�u������������B<BR>
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
     * @@roseuid 4193128F0170
     */
    public void saveVoucherRow(String l_strSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveVoucherRow(String)";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@�f�t�H���g�ݒ�s���� 
        PasswordVoucherParams l_passwordVoucherParams = new PasswordVoucherParams();
        
        try
        {
            //�f�t�H���g�ݒ�i��1�j�̒ʂ�Ƀv���p�e�B���Z�b�g����B
            String l_strInstitutionCode = this.accOpenExpAccountOpen.getInstitutionCode();
            String l_strBranchCode = this.accOpenExpAccountOpen.getBranchCode();
            String l_strAccountCode = this.accOpenExpAccountOpen.getAccountCode();
            String l_strRequestNumber = this.accOpenExpAccountOpen.getRequestNumber();
            
            //�f�[�^�R�[�h
            l_passwordVoucherParams.setRequestCode(super.getStringByByteNumber(WEB3HostRequestCodeDef.ACCOPEN_PASSWORD, 5));
            //�،���ЃR�[�h
            l_passwordVoucherParams.setInstitutionCode(super.getStringByByteNumber(l_strInstitutionCode, 3));
            //���X�R�[�h
            l_passwordVoucherParams.setBranchCode(super.getStringByByteNumber(l_strBranchCode, 3));
            //�ڋq�R�[�h
            l_passwordVoucherParams.setAccountCode(super.getStringByByteNumber(l_strAccountCode, 7));
            
            //���҃R�[�h
            ExpAccountOpenRow l_expAccountOpenRow = (ExpAccountOpenRow)this.accOpenExpAccountOpen.getDataSourceObject();
            String l_strSonarTraderCode = l_expAccountOpenRow.getSonarTraderCode();
            l_passwordVoucherParams.setTraderCode(super.getStringByByteNumber(l_strSonarTraderCode, 5));
            
            //���ʃR�[�h�i�����J�݌����q�j
            l_passwordVoucherParams.setAccOpenRequestNumber(super.getStringByByteNumber(l_strRequestNumber, 13));
            //�`�[�ʔ�
            l_passwordVoucherParams.setSerialNo(super.getStringByByteNumber(l_strSerialNo, 3));
            
            //���s����敪
            l_passwordVoucherParams.setRegistDeleteDiv(super.getStringByByteNumber(WEB3RegistDeleteDivDef.REGIST, 1));
            //�f�|�^���
            l_passwordVoucherParams.setDataClass(super.getStringByByteNumber(WEB3DataClassDef.DATA_RECORD, 2));
            //�f�[�^�R�[�h�i�`�[���j
            l_passwordVoucherParams.setVoucherName(super.getStringByByteNumber(WEB3VoucherNameDef.ISSUE_REGIST_VOUCHER, 4));
            
            //�\���N����
            l_passwordVoucherParams.setRegistDate(null);
            String l_strInitialPassword = l_expAccountOpenRow.getInitialPassword();
            //�Ïؔԍ�
            l_passwordVoucherParams.setPassword(super.getStringByByteNumber(l_strInitialPassword, 48));
            //���s���R
            l_passwordVoucherParams.setPubReasonDiv(super.getStringByByteNumber(WEB3PubReasonDivDef.NEW, 1));
            //�����ԍ�
            l_passwordVoucherParams.setRefNumber(super.getStringByByteNumber("00000", 5));
            
            String l_strFamilyNameAlt1 = WEB3StringTypeUtility.to1byteKana(l_expAccountOpenRow.getFamilyNameAlt1());
            String l_strGivenNameAlt1 = WEB3StringTypeUtility.to1byteKana(l_expAccountOpenRow.getGivenNameAlt1());
            String l_strModifiedName = l_strFamilyNameAlt1 + " " + l_strGivenNameAlt1;
            //��������
            l_passwordVoucherParams.setModifiedName(super.getStringByByteNumber(l_strModifiedName, 19));
            
            //�J�[�h�^�C�v
            l_passwordVoucherParams.setCardType(null);
            //�I�����C���E�o�b�`�敪
            l_passwordVoucherParams.setOnlineBatchDiv(null);
            
            //SEQ_NO
            l_passwordVoucherParams.setSeqNo(null);
            //�����敪
            l_passwordVoucherParams.setStatus(super.getStringByByteNumber(WEB3StatusDef.NOT_DEAL, 1));
            //���M����
            l_passwordVoucherParams.setSendTimestamp(null);
            Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
            //�쐬����
            l_passwordVoucherParams.setCreatedTimestamp(l_tsSystemTimestamp);
            //�X�V����
            l_passwordVoucherParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);
            
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
                    l_bindVarsItem);                  //DataQueryException, DataNetworkException
                     
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
                        l_bindVarsItem2);             //DataQueryException, DataNetworkException
            }
            
            int l_intSize = 0;
            if (l_lisRowItems != null)
            {
                l_intSize = l_lisRowItems.size();
            }
             
            AccOpenVoucherItemRow l_accOpenVoucherItemRow = null;
            if (l_intSize > 0)
            {
                log.debug("�Q�|�P�j�@@�J�X�^�}�C�Y�ҏW");
                //�Q�|�P�j�@@�J�X�^�}�C�Y�ҏW
                for (int i = 0; i < l_intSize; i++)
                {
                    log.debug("�Q�|�P�j�@@�J�X�^�}�C�Y�ҏW");
                    l_accOpenVoucherItemRow = (AccOpenVoucherItemRow)l_lisRowItems.get(i);
                    String l_strValue = null;
                    //�i���ڕҏW���@@ == �Œ�l�j�̏ꍇ�A�Œ�Z�b�g�l�̒l���Z�b�g����B
                    if (WEB3EditWayDivDef.FIXED_VALUE.equals(l_accOpenVoucherItemRow.getEditWayDiv()))
                    {
                        l_strValue = l_accOpenVoucherItemRow.getFixedValue();
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
                    
                    log.debug("%%%%%%%%%%%l_strValue = %%%%%%%%%%%%%%" + l_strValue);
                    
                    //�f�[�^�R�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("%%%%%%%%%%%%%%�f�[�^�R�[�h");
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_passwordVoucherParams.setRequestCode(super.getStringByByteNumber(l_strValue, 5));    
                    }
                    
                    //�،���ЃR�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("%%%%%%%%%%%%%%%%%�،���ЃR�[�h");
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_passwordVoucherParams.setInstitutionCode(super.getStringByByteNumber(l_strValue, 3));
                    }
                    //���X�R�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.BRANCH_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("%%%%%%%%%%%%%%%%���X�R�[�h");
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_passwordVoucherParams.setBranchCode(super.getStringByByteNumber(l_strValue, 3));
                    }
                    //�ڋq�R�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("%%%%%%%%%%%%%%%�ڋq�R�[�h");
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_passwordVoucherParams.setAccountCode(super.getStringByByteNumber(l_strValue, 7));
                    }
                    //���҃R�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.TRADER_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("%%%%%%%%%%%%%%%%%%���҃R�[�h");
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_passwordVoucherParams.setTraderCode(super.getStringByByteNumber(l_strValue, 5));
                    }
                    //���ʃR�[�h�i�����J�݌����q�j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("%%%%%%%%%%%%���ʃR�[�h�i�����J�݌����q�j");
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_passwordVoucherParams.setAccOpenRequestNumber(super.getStringByByteNumber(l_strValue, 13));
                    }
                        
                    //�`�[�ʔ�
                    if (WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("%%%%%%%%%%%%%%�`�[�ʔ�");
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_passwordVoucherParams.setSerialNo(super.getStringByByteNumber(l_strValue, 3));    
                    }
                        
                    //���s����敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DELETE_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("%%%%%%%%%%%%%���s����敪");
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_passwordVoucherParams.setRegistDeleteDiv(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //�f�|�^���
                    if (WEB3AccountOpenOutputItemSymbolNameDef.DATA_CLASS.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_passwordVoucherParams.setDataClass(super.getStringByByteNumber(l_strValue, 2));    
                    }
                        
                    //�f�[�^�R�[�h�i�`�[���j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.VOUCHER_NAME.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_passwordVoucherParams.setVoucherName(super.getStringByByteNumber(l_strValue, 4));    
                    }
                        
                    //�\���N����
                    if (WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DATE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_passwordVoucherParams.setRegistDate(super.getStringByByteNumber(l_strValue, 6));
    
                    }
                        
                    //���s���R
                    if (WEB3AccountOpenOutputItemSymbolNameDef.PUB_REASON_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_passwordVoucherParams.setPubReasonDiv(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //�����ԍ�
                    if (WEB3AccountOpenOutputItemSymbolNameDef.REF_NUMBER.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_passwordVoucherParams.setRefNumber(super.getStringByByteNumber(l_strValue, 5));
                    }
                        
                    //�J�[�h�^�C�v
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CARD_TYPE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_passwordVoucherParams.setCardType(super.getStringByByteNumber(l_strValue, 1));   
                    }
                        
                    //�I�����C���E�o�b�`�敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ONLINE_BATCH_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_passwordVoucherParams.setOnlineBatchDiv(super.getStringByByteNumber(l_strValue, 1));     
                    }
                        
                    //SEQ_NO
                    if (WEB3AccountOpenOutputItemSymbolNameDef.SEQ_NO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_passwordVoucherParams.setSeqNo(super.getStringByByteNumber(l_strValue, 4));
                    }
                        
                    //�����敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.STATUS.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_passwordVoucherParams.setStatus(super.getStringByByteNumber(l_strValue, 1));    
                    }
                          
                    //�Ïؔԍ�
                    if (WEB3AccountOpenOutputItemSymbolNameDef.PASSWORD.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_passwordVoucherParams.setPassword(super.getStringByByteNumber(l_strValue, 48));     
                    } 
                    //��������
                    if (WEB3AccountOpenOutputItemSymbolNameDef.MODIFIED_NAME.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_passwordVoucherParams.setModifiedName(super.getStringByByteNumber(l_strValue, 19));
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
            l_passwordVoucherParams.setOrderRequestNumber(l_strNewNumber);
            
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
                PasswordVoucherRow.TYPE,
                l_strWhere,
                l_bindVars);                        //DataQueryException, DataNetworkException
            
            //�R�|�Q�j�@@�`�[�s�}�� 
            l_queryProcesser.doInsertQuery(l_passwordVoucherParams);  
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
     * �Ïؔԍ��`�[�iG5511�j�e�[�u���̈ȉ��̏����ɓ��Ă͂܂�s���擾����B<BR>
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
     * @@roseuid 419C2B7C02C2
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
                PasswordVoucherRow.TYPE,
                l_strWhere,
                l_bindVars);                       //DataQueryException, DataNetworkException
                
            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return false;    
            }
            else
            {
                l_queryProcesser.doDeleteAllQuery(
                    PasswordVoucherRow.TYPE,
                    l_strWhere,
                    l_bindVars);                  //DataQueryException, DataNetworkException
                
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
     * @@roseuid 41B45E6E006D
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
