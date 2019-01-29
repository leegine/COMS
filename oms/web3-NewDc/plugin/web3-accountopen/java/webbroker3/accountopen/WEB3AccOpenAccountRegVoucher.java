head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.29.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenAccountRegVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋq�o�^�`�[(WEB3AccOpenAccountRegVoucher.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/12/20 ���o�� �V�K�쐬
		              2006/07/11 ����� (���u) �c�a���C�A�E�g026
		              2006/07/13 ����� (���u) �d�l�ύX�E���f��077
		              2006/07/18 ����� (���u)�d�l�ύX�E���f��081
		              2007/06/14 ���� (SCS) �����̖�� No.003
Revesion History    : 2007/09/28 �юu��(���u) �d�l�ύX DB���C�A�E�g044
Revesion History    : 2008/07/25 �g�C��(���u) DB�X�V�d�l No.034
Revesion History    : 2009/08/21 �И���(���u) DB���C�A�E�g058
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
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.accountopen.data.AccOpenVoucherItemRow;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.data.HostAccRegVoucherParams;
import webbroker3.accountopen.data.HostAccRegVoucherRow;
import webbroker3.accountopen.define.WEB3AccountOpenExpAccountOpenSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenOutputItemSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenVoucherCodeDef;
import webbroker3.accountopen.define.WEB3AdminAccountOpenAccTransferDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccRegAccountDivDef;
import webbroker3.common.define.WEB3AccRegTaxationDivDef;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3AgentDivDef;
import webbroker3.common.define.WEB3CatDelimitterDef;
import webbroker3.common.define.WEB3DividendTransferDivDef;
import webbroker3.common.define.WEB3DocumentDef;
import webbroker3.common.define.WEB3EditWayDivDef;
import webbroker3.common.define.WEB3ForeignerDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3ProamDivDef;
import webbroker3.common.define.WEB3RegDivDef;
import webbroker3.common.define.WEB3ResidentDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TaxationDivDef;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�ڋq�o�^�`�[)<BR>
 * �ڋq�o�^�`�[<BR>
 * 
 * @@author ���o��
 * @@version 1.0 
 */
public class WEB3AccOpenAccountRegVoucher extends WEB3AccOpenVoucher
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AccOpenAccountRegVoucher.class);
    
    /**
     * @@roseuid 41B45E6B035B
     */
    public WEB3AccOpenAccountRegVoucher()
    {

    }

    /**
     * (getInstance)<BR>
     * �����J�݌����q�I�u�W�F�N�g���w�肵�A�ڋq�o�^�`�[�C���X�^���X���쐬����B<BR>
     * <BR>
     * �P�j�@@�C���X�^���X����<BR>
     * �@@�ڋq�o�^�`�[�C���X�^���X�𐶐�����B<BR>
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
     * @@return webbroker3.accountopen.WEB3AccOpenAccountRegVoucher
     * @@roseuid 4191CD1F0033
     */
    public static WEB3AccOpenAccountRegVoucher getInstance(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInstance(WEB3AccOpenExpAccountOpen)";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@�C���X�^���X����
        WEB3AccOpenAccountRegVoucher l_accOpenAccountRegVoucher = new WEB3AccOpenAccountRegVoucher();
        
        //�Q�j�@@�����J�݌����q�v���p�e�B�Z�b�g
        l_accOpenAccountRegVoucher.setAccOpenExpAccountOpen(l_accOpenExpAccountOpen);
        
        //�R�j�@@�����J�ݓ`�[�}�X�^�v���p�e�B�Z�b�g
        l_accOpenAccountRegVoucher.setAccOpenVoucherMaster();
        
        log.exiting(STR_METHOD_NAME);
        return l_accOpenAccountRegVoucher;
    }

    /**
     * (is�I�����C���`�[)<BR>
     * �iis�I�����C���`�[()�̎����j<BR>
     * <BR>
     * true��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 4192EAB00016
     */
    public boolean isOnlineVoucher()
    {
        final String STR_METHOD_NAME = " isOnlineVoucher()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (get�f�[�^�R�[�h)<BR>
     * �iget�f�[�^�R�[�h()�̎����j<BR>
     * <BR>
     * �f�[�^�R�[�h.�ڋq�o�^�iGI821�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 4192EAB00026
     */
    public String getRequestCode()
    {
        final String STR_METHOD_NAME = " getRequestCode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return WEB3HostRequestCodeDef.ACCOPEN_ACCOUNT_REGIST;
    }

    /**
     * (get�`�[�R�[�h)<BR>
     * �iget�`�[�R�[�h()�̎����j<BR>
     * <BR>
     * �hG11�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 419DDE9900A3
     */
    public String getVoucherCode()
    {
        final String STR_METHOD_NAME = " getVoucherCode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return WEB3AccountOpenVoucherCodeDef.ACCOUNT_REG_VOUCHER_CODE;
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
     * �@@�@@�ڋq�o�^�`�[�iG11�j�L���[�e�[�u���̊e���ڂɂ��āA���L�̏��������{����B<BR>
     * <BR>
     * �@@�@@this.get�J�X�^�}�C�Y�Q�ƍ���()���R�[�����A�����J�݌����q�e�[�u����<BR>
     * �������̔z����擾����B<BR>
     * <BR>
     * �@@�@@[get�J�X�^�}�C�Y�Q�ƍ���()�Ɏw�肷�����]<BR>
     * �@@�@@�`�[�ʔԁF�@@�����J�ݓ`�[�}�X�^�s[index].�`�[�ʔ�<BR>
     * �@@�@@�`�[�o�͍��ڕ������F�@@�i��1 �ڋq�o�^�`�[�iG11�j�L���[�e�[�u����<BR>
     * �����Ώۍ��ځj<BR>
     * �@@�@@�`�[�Q�ƍ��ڏ����l�F�@@�i��2 �ڋq�o�^�`�[�iG11�j�L���[�e�[�u����<BR>
     * �����Ώۍ��ڃf�t�H���g�ݒ�l�j<BR>
     * <BR>
     * �@@�@@�i��2�j�@@�ڋq�o�^�`�[�iG11�j�L���[�e�[�u���̏����Ώۍ��ڃf�t�H���g�ݒ�l<BR>
     * �@@�@@DB���C�A�E�g �u�ڋq�o�^�`�[�iG11�j�L���[�e�[�u��.xls#�f�t�H���gDB�ݒ�_���v<BR>
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
     * @@roseuid 4192EAB00055
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
            log.debug("�����J�ݓ`�[�}�X�^�s[]�̊e�v�f���ɁA�Q�|�P�j�`�Q�|�R�j�̏��������{����");
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
                    if (l_strCustomizingRefItem[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItem[j], l_strCustomizingRefItem[j]);
                    }
                }

                //���ʃR�[�h�i�����J�݌����q�j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ACC_OPEN_REQUEST_NUMBER;
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
                
                //�ڋq���i�Łj
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                String[] l_strValues2 = new String[2];
                l_strValues2[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_NAME_ALT1;
                l_strValues2[1] = WEB3AccountOpenExpAccountOpenSymbolNameDef.GIVEN_NAME_ALT1;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME_KANA, l_strValues2);   
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
                
                //�ڋq���i�����j
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues2[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_NAME;
                l_strValues2[1] = WEB3AccountOpenExpAccountOpenSymbolNameDef.GIVEN_NAME;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME, l_strValues2); 
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
                
                //�X�֔ԍ�
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ZIP_CODE;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE, l_strValues1);  
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
                
                //�Z���P
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE1;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1, l_strValues1);  
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
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE1_KANA;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1_KANA, l_strValues1);  
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
                
                //�Z���Q
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE2;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2, l_strValues1);  
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
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE2_KANA;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2_KANA, l_strValues1);  
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
                
                //�Z���R
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE3;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3, l_strValues1);  
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
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE3_KANA;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3_KANA, l_strValues1);  
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
                
                //�d�b�ԍ�
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.TELEPHONE;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE, l_strValues1);  
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
                
                //�A����Z��
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.CONTACT_ADDRESS;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CONTACT_ADDRESS, l_strValues1);  
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
                
                //�A����d�b�ԍ�
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.CONTACT_TELEPHONE;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CONTACT_TELEPHONE, l_strValues1);  
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
                
                //�ڊǑO���X��
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EX_BRANCH_NAME, null);  
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
                
                
                //�ڊǑO�����R�[�h
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.EX_ACCOUNT_CODE, null);  
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
                
                //�E�Ƌ敪
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.OCCUPATION_DIV;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.OCCUPATION_DIV, l_strValues1);  
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
                
                //���N�����N��
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ERA_BORN;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ERA_BORN, l_strValues1);  
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
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.BORN_DATE;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.BORN_DATE, l_strValues1);  
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
                
                //����
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.SEX;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.SEX, l_strValues1);  
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
                
                //����
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.DOCUMENT, null);  
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
                
                //�Z���s��
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.UNKNOWN_ADDRESS, null);  
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
                
                //�񍐏�
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.REPORT, null);  
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
                
                //���Z�^�񋏏Z�敪
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.RESIDENT, null);  
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
                
                //�ېŋ敪
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.TAXATION_DIV, null);  
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
                
                //�ېŋ敪�i�O���j
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.FORIGN_TAXATION_DIV, null);  
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
                
                //�ڋq�敪
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_DIV, null);  
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
                
                //�����J��
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV1, null);  
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
                
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV2, null);  
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
                
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV3, null);  
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
                
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV4, null);  
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
                
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV5, null);  
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
                
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV6, null);  
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
                
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV7, null);  
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
                
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV8, null);  
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
                
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV9, null);  
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
                
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV10, null);  
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
                
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV11, null);  
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
                
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV12, null);  
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
                
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV13, null);  
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
                
                //�\���敪�i�\�������ېŁj
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.TAXATION_APPL_DIV, null);  
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
                
                //���n�ېŋ敪
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.TAXATION_TRAN_DIV, null);  
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
                    WEB3AccountOpenOutputItemSymbolNameDef.SEND_DIV, null);  
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
                
                //�M���o�R�敪
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.TRUST_VIA_DIV, null);  
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
                    WEB3AccountOpenOutputItemSymbolNameDef.CORRECT_DIV1, null);  
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
                
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CORRECT_DIV2, null);  
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
                
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CORRECT_DIV3, null);  
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
                
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CORRECT_DIV4, null);  
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
                
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.CORRECT_DIV5, null);  
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
                
                //�敨OP�����J�݋敪�i���؁j
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.IFO_ACC_OPEN_DIV_TOKYO, null);  
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
                
                //�敨OP�����J�݋敪�i��؁j
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.IFO_ACC_OPEN_DIV_OSAKA, null);  
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
                
                //�敨OP�����J�݋敪�i���؁j
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.IFO_ACC_OPEN_DIV_NAGOYA, null);  
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
                
                //���̑��A����d�b�ԍ�
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.OTHER_CONTACT_TELEPHONE, null);  
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
                
                //����ƈ��҃R�[�h
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.BROKERAGE_TRADER_CODE, null);  
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

                //�v���E�A�}�敪 
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.PROAM_DIV, null);  
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

                //�O���l�敪�i�����@@�j
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.FOREIGNER_DIV_BROADCAST, null);  
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

                //�O���l�敪�i�q��@@�j
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.FOREIGNER_DIV_AVIATION, null);  
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

                //�O���l�敪�iNTT�@@�j
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.FOREIGNER_DIV_NTT, null);  
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

                //�z�����U���w��敪
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.DIVIDEND_TRANSFER_DIV, null);  
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
 
                //�㗝�l�敪�i��C�㗝�l�j
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.AGENT_DIV_PERMANENT, null);  
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

                //�㗝�l�敪�i�@@��㗝�l�j
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.AGENT_DIV_LEGAL, null);  
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
     * �@@�ڋq�o�^�`�[�iG11�j�L���[�e�[�u���s�I�u�W�F�N�g�𐶐����A<BR>
     * �@@�f�t�H���g�ݒ�i��1�j�̒ʂ�Ƀv���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A<BR>
     * �f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�B<BR>
     * <BR>
     * �@@�i��1�j�@@�ڋq�o�^�`�[�iG11�j�L���[�e�[�u���̏����Ώۍ��ڃf�t�H���g�ݒ�<BR>
     * �@@DB���C�A�E�g
     * �u�ڋq�o�^�`�[�iG11�j�L���[�e�[�u��.xls#�f�t�H���gDB�ݒ�_���v<BR>
     * �V�[�g�Q�ƁB<BR>
     * <BR>
     * �Q�j�@@�J�X�^�}�C�Y���ڃZ�b�g <BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^�e�[�u�����ȉ��̏����@@�Ō�������B <BR>
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
     * �@@�@@�� �ȊO�̏ꍇ�A���͍��ڕ������P�`�R�����������J�݌����q��<BR>
     * ���ڒl(��2)���Z�b�g����B<BR>
     * �@@�@@�@@�@@�|���͍��ڕ������P�`�R��null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�|�A�����ڃf���~�b�^���w�肳��Ă���ꍇ�i�A�����ڃf���~�b�^ !=
     * null�j�A<BR>
     * �@@�@@�@@�@@�@@���͍��ڕ������P�C�Q�C�R�̒l���f���~�b�^�ɂĘA������B<BR>
     * <BR>
     * �@@�@@(��2) DB���C�A�E�g�u�����J�ݓ`�[���ڃ}�X�^�v�Q�ƁB<BR>
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
     * �@@�@@�ȉ��̏����ɂČڋq�o�^�`�[�iG11�j�L���[�e�[�u������������B<BR>
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
     * @@roseuid 4192EAB00064
     */
    public void saveVoucherRow(String l_strSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveVoucherRow(String)";
        log.entering(STR_METHOD_NAME);
        //�f�t�H���g�ݒ�s���� 
        HostAccRegVoucherParams l_hostAccRegVoucherParams = new HostAccRegVoucherParams();
        
        //�f�t�H���g�ݒ�i��1�j�̒ʂ�Ƀv���p�e�B���Z�b�g����B
        try
        {             
            String l_strInstitutionCode = this.accOpenExpAccountOpen.getInstitutionCode();
            String l_strBranchCode = this.accOpenExpAccountOpen.getBranchCode();
            String l_strAccountCode = this.accOpenExpAccountOpen.getAccountCode();
            String l_strRequestNumber = this.accOpenExpAccountOpen.getRequestNumber();

            //�f�[�^�R�[�h
            l_hostAccRegVoucherParams.setRequestCode(super.getStringByByteNumber(WEB3HostRequestCodeDef.ACCOPEN_ACCOUNT_REGIST, 5));
            //�،���ЃR�[�h
            l_hostAccRegVoucherParams.setInstitutionCode(super.getStringByByteNumber(l_strInstitutionCode, 3));
            //���X�R�[�h                    
            l_hostAccRegVoucherParams.setBranchCode(super.getStringByByteNumber(l_strBranchCode, 3));
            //�ڋq�R�[�h
            l_hostAccRegVoucherParams.setAccountCode(super.getStringByByteNumber(l_strAccountCode, 7));
            
            //���҃R�[�h
            ExpAccountOpenRow l_expAccountOpenRow = (ExpAccountOpenRow)this.accOpenExpAccountOpen.getDataSourceObject();
            String l_strSonarTraderCode = l_expAccountOpenRow.getSonarTraderCode();
            l_hostAccRegVoucherParams.setTraderCode(super.getStringByByteNumber(l_strSonarTraderCode, 5));
            
            //���ʃR�[�h�i�����J�݌����q�j
            l_hostAccRegVoucherParams.setAccOpenRequestNumber(super.getStringByByteNumber(l_strRequestNumber, 13));
            //�`�[�ʔ�
            l_hostAccRegVoucherParams.setSerialNo(super.getStringByByteNumber(l_strSerialNo, 3));
            //�o�^�敪
            l_hostAccRegVoucherParams.setRegistDiv(super.getStringByByteNumber(WEB3RegDivDef.NEW, 1));
            //�ڋq���i�Łj
            String l_strFamilyNameAlt1 = WEB3StringTypeUtility.to1byteKana(l_expAccountOpenRow.getFamilyNameAlt1());
            String l_strGivenNameAlt1 = WEB3StringTypeUtility.to1byteKana(l_expAccountOpenRow.getGivenNameAlt1());
            String l_strAccountNameKana = l_strFamilyNameAlt1 + " " + l_strGivenNameAlt1;
            
            l_hostAccRegVoucherParams.setAccountNameKana(super.getStringByByteNumber(l_strAccountNameKana, 19));
            
            //�ڋq���i�����j
            String l_strFamilyName = l_expAccountOpenRow.getFamilyName();
            String l_strGivenName = l_expAccountOpenRow.getGivenName();
            String l_strAccountName = l_strFamilyName + "�@@" + l_strGivenName; 
            
//            l_hostAccRegVoucherParams.setAccountName(super.getStringByByteNumber(l_strAccountName, 18));         
            l_hostAccRegVoucherParams.setAccountName(super.getEmStringByByteNumber(l_strAccountName, 18));         
            
            //�X�֔ԍ�
            l_hostAccRegVoucherParams.setZipCode(super.getStringByByteNumber(l_expAccountOpenRow.getZipCode(), 7));
            //�Z���P
            String l_strAddressLine1 = l_expAccountOpenRow.getAddressLine1();    
                   
//            l_hostAccRegVoucherParams.setAddressLine1(super.getStringByByteNumber(l_strAddressLine1, 32));
            l_hostAccRegVoucherParams.setAddressLine1(super.getEmStringByByteNumber(l_strAddressLine1, 32));
            
            //�Z���P�i�J�i�j
            String l_strAddressLine1Kana = WEB3StringTypeUtility.to1byteKana(l_expAccountOpenRow.getAddressLine1Kana());
            l_hostAccRegVoucherParams.setAddressLine1Kana(super.getStringByByteNumber(l_strAddressLine1Kana, 30));
            
            //�Z���Q
            String l_strAddressLine2 = l_expAccountOpenRow.getAddressLine2();
            
//            l_hostAccRegVoucherParams.setAddressLine2(super.getStringByByteNumber(l_strAddressLine2, 32));
            l_hostAccRegVoucherParams.setAddressLine2(super.getEmStringByByteNumber(l_strAddressLine2, 32));
            
            //�Z���Q�i�J�i�j
            String l_strAddressLine2Kana = WEB3StringTypeUtility.to1byteKana(l_expAccountOpenRow.getAddressLine2Kana());
            l_hostAccRegVoucherParams.setAddressLine2Kana(super.getStringByByteNumber(l_strAddressLine2Kana, 30));
            
            //�Z���R
            String l_strAddressLine3 = l_expAccountOpenRow.getAddressLine3();

//            l_hostAccRegVoucherParams.setAddressLine3(super.getStringByByteNumber(l_strAddressLine3, 32));
            l_hostAccRegVoucherParams.setAddressLine3(super.getEmStringByByteNumber(l_strAddressLine3, 32));
            
            //�Z���R�i�J�i�j
            String l_strAddressLine3Kana = WEB3StringTypeUtility.to1byteKana(l_expAccountOpenRow.getAddressLine3Kana());
            //���Z���P�i�J�i�j�A�Z���Q�i�J�i�j�A�Z���R�i�J�i�j�ɃZ�b�g����l�̍��v�T�C�Y��68�����𒴉߂��Ă���ꍇ�́A
            //68�����ȍ~���Z���R�i�J�i�j���؂�̂Ă�B
            String l_strAddressLine = l_hostAccRegVoucherParams.getAddressLine1Kana() + l_hostAccRegVoucherParams.getAddressLine2Kana();
            int l_intAddressLineLength = Math.min(68 - l_strAddressLine.length(), 30);
            l_hostAccRegVoucherParams.setAddressLine3Kana(super.getStringByByteNumber(l_strAddressLine3Kana, l_intAddressLineLength));
            
            //�d�b�ԍ�
            l_hostAccRegVoucherParams.setTelephone(super.getStringByByteNumber(l_expAccountOpenRow.getTelephone(), 16));
            //�A����Z��
            String l_strContactAddress = l_expAccountOpenRow.getContactAddress();
            
//            l_hostAccRegVoucherParams.setContactAddress(super.getStringByByteNumber(l_strContactAddress, 36));
            l_hostAccRegVoucherParams.setContactAddress(super.getEmStringByByteNumber(l_strContactAddress, 36));
            
            //�A����d�b�ԍ�
            l_hostAccRegVoucherParams.setContactTelephone(super.getStringByByteNumber(l_expAccountOpenRow.getContactTelephone(), 16));
            
            //�ڊǑO���X��
            l_hostAccRegVoucherParams.setExBranchName(super.getStringByByteNumber("1", 3));
            //�ڊǑO�����R�[�h
            l_hostAccRegVoucherParams.setExAccountCode(super.getStringByByteNumber("1", 7));
            //�E�Ƌ敪
            l_hostAccRegVoucherParams.setOccupationDiv(super.getStringByByteNumber(l_expAccountOpenRow.getOccupationDiv(), 2));
            //���N�����N��
            l_hostAccRegVoucherParams.setEraBorn(super.getStringByByteNumber(l_expAccountOpenRow.getEraBorn(), 1));
            //���N����
            l_hostAccRegVoucherParams.setBornDate(super.getStringByByteNumber(l_expAccountOpenRow.getBornDate(), 6));
            //����
            l_hostAccRegVoucherParams.setSex(super.getStringByByteNumber(l_expAccountOpenRow.getSex(), 1));
            
            //����
            l_hostAccRegVoucherParams.setDocument(super.getStringByByteNumber(WEB3DocumentDef.NEED, 1));
            //�Z���s��
            l_hostAccRegVoucherParams.setUnknownAddress(null);
            //�񍐏�
            l_hostAccRegVoucherParams.setReport(null);
            //���Z�^�񋏏Z�敪
            l_hostAccRegVoucherParams.setResident(super.getStringByByteNumber(WEB3ResidentDef.RESIDENT, 1));
            //�ېŋ敪
            l_hostAccRegVoucherParams.setTaxationDiv(super.getStringByByteNumber(WEB3AccRegTaxationDivDef.OLD_INTEGRATION, 1));
            //�ېŋ敪�i�O���j
            l_hostAccRegVoucherParams.setForignTaxationDiv(super.getStringByByteNumber(WEB3AccRegTaxationDivDef.OLD_INTEGRATION, 1));
            //�ڋq�敪
            l_hostAccRegVoucherParams.setAccountDiv(super.getStringByByteNumber(WEB3AccRegAccountDivDef.GENERAL, 1));
            //�P�i�ی�a��j
            l_hostAccRegVoucherParams.setAccountOpenDiv1(super.getStringByByteNumber(WEB3AccountOpenDef.OPEN, 1));
            //�Q�i�ϗ������j
            l_hostAccRegVoucherParams.setAccountOpenDiv2(super.getStringByByteNumber(WEB3AccountOpenDef.NOT_OPEN, 1));
            //�R�i�M�p����j
            l_hostAccRegVoucherParams.setAccountOpenDiv3(super.getStringByByteNumber(WEB3AccountOpenDef.NOT_OPEN, 1));
            //�S�i���s������j
            l_hostAccRegVoucherParams.setAccountOpenDiv4(super.getStringByByteNumber(WEB3AccountOpenDef.NOT_OPEN, 1));
            //�T�i�O���،��j
            l_hostAccRegVoucherParams.setAccountOpenDiv5(super.getStringByByteNumber(WEB3AccountOpenDef.OPEN, 1));
            //�U�i���q����j
            l_hostAccRegVoucherParams.setAccountOpenDiv6(super.getStringByByteNumber(WEB3AccountOpenDef.NOT_OPEN, 1));
            //�V�i�D�j
            l_hostAccRegVoucherParams.setAccountOpenDiv7(super.getStringByByteNumber(WEB3AccountOpenDef.NOT_OPEN, 1));
            //�W�i���j
            l_hostAccRegVoucherParams.setAccountOpenDiv8(super.getStringByByteNumber(WEB3AccountOpenDef.NOT_OPEN, 1));
            //�X�i���敨�j
            l_hostAccRegVoucherParams.setAccountOpenDiv9(super.getStringByByteNumber(WEB3AccountOpenDef.NOT_OPEN, 1));
            //�P�O�i�����敨�j
            l_hostAccRegVoucherParams.setAccountOpenDiv10(super.getStringByByteNumber(WEB3AccountOpenDef.NOT_OPEN, 1));
            //�P�P�i����I�v�V�����j
            l_hostAccRegVoucherParams.setAccountOpenDiv11(super.getStringByByteNumber(WEB3AccountOpenDef.NOT_OPEN, 1));
            //�P�Q�iTBOND�I�v�V�����j
            l_hostAccRegVoucherParams.setAccountOpenDiv12(super.getStringByByteNumber(WEB3AccountOpenDef.NOT_OPEN, 1));
            //�P�R�i�����I�v�V�����j
            l_hostAccRegVoucherParams.setAccountOpenDiv13(super.getStringByByteNumber(WEB3AccountOpenDef.NOT_OPEN, 1));
            
            //�\���敪�i�\�������ېŁj
            l_hostAccRegVoucherParams.setTaxationApplDiv(super.getStringByByteNumber(WEB3TaxationDivDef.SEPARATE_SELF_ACCESSMENT, 1));
            //���n�ېŋ敪W
            l_hostAccRegVoucherParams.setTaxationTranDiv(super.getStringByByteNumber(WEB3TaxationDivDef.SEPARATE_SELF_ACCESSMENT, 1));
            //�����敪
            l_hostAccRegVoucherParams.setSendDiv(null);
            //�M���o�R�敪
            l_hostAccRegVoucherParams.setTrustViaDiv(null);
            //�����敪
            l_hostAccRegVoucherParams.setCorrectDiv1(null);
            l_hostAccRegVoucherParams.setCorrectDiv2(null);
            l_hostAccRegVoucherParams.setCorrectDiv3(null);
            l_hostAccRegVoucherParams.setCorrectDiv4(null);
            l_hostAccRegVoucherParams.setCorrectDiv5(null);
            
            //�敨OP�����J�݋敪�i���؁j
            l_hostAccRegVoucherParams.setIfoAccOpenDivTokyo(super.getStringByByteNumber(WEB3AccountOpenDef.NOT_OPEN, 1));
            //�敨OP�����J�݋敪�i��؁j
            l_hostAccRegVoucherParams.setIfoAccOpenDivOsaka(super.getStringByByteNumber(WEB3AccountOpenDef.NOT_OPEN, 1));
            //�敨OP�����J�݋敪�i���؁j
            l_hostAccRegVoucherParams.setIfoAccOpenDivNagoya(super.getStringByByteNumber(WEB3AccountOpenDef.NOT_OPEN, 1));
            //�����敪
            //ThreadLocalSystemAttributesRegistry.getAttribute("web3.adminAccountOpenAccTransfer").booleanValue()��"true"�̏ꍇ
            //3�F��
            //�ȊO�ꍇ0�F������
            Boolean l_isAccOpenTran = (Boolean)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3AdminAccountOpenAccTransferDef.ADMIN_ACCOUNT_OPEN_ACC_TRANSFER);
            if (l_isAccOpenTran != null)
            {
                if (l_isAccOpenTran.booleanValue())
                {
                    l_hostAccRegVoucherParams.setStatus(super.getStringByByteNumber(WEB3StatusDef.TEMPORARY, 1));
                }
            }
            else
            {
                l_hostAccRegVoucherParams.setStatus(super.getStringByByteNumber(WEB3StatusDef.NOT_DEAL, 1));
            }

            Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
            //���M����
            l_hostAccRegVoucherParams.setSendTimestamp(null);
            //�쐬����
            l_hostAccRegVoucherParams.setCreatedTimestamp(l_tsSystemTimestamp);
            //�X�V����
            l_hostAccRegVoucherParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);
            
            //���̑��A����d�b�ԍ�
            l_hostAccRegVoucherParams.setOtherContactTelephone(null);
            //����ƈ��҃R�[�h
            l_hostAccRegVoucherParams.setBrokerageTraderCode(null);

            //�v���E�A�}�敪
            l_hostAccRegVoucherParams.setProamDiv(
                super.getStringByByteNumber(WEB3ProamDivDef.AM, 1));

            //�O���l�敪�i�����@@�j
            l_hostAccRegVoucherParams.setForeignerDivBroadcast(
                super.getStringByByteNumber(WEB3ForeignerDivDef.EXCEPT_FOREIGNER, 1));

            //�O���l�敪�i�q��@@�j
            l_hostAccRegVoucherParams.setForeignerDivAviation(
                super.getStringByByteNumber(WEB3ForeignerDivDef.EXCEPT_FOREIGNER, 1));

            //�O���l�敪�iNTT�@@�j
            l_hostAccRegVoucherParams.setForeignerDivNtt(
                super.getStringByByteNumber(WEB3ForeignerDivDef.EXCEPT_FOREIGNER, 1));

            //�z�����U���w��敪
            l_hostAccRegVoucherParams.setDividendTransferDiv(
                super.getStringByByteNumber(WEB3DividendTransferDivDef.DEFAULT, 1));

            //�㗝�l�敪�i��C�㗝�l�j
            l_hostAccRegVoucherParams.setAgentDivPermanent(
                super.getStringByByteNumber(WEB3AgentDivDef.NOT_ELECTION, 1));

            //�㗝�l�敪�i�@@��㗝�l�j
            l_hostAccRegVoucherParams.setAgentDivLegal(
                super.getStringByByteNumber(WEB3AgentDivDef.NOT_ELECTION, 1));

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
                    l_bindVarsItem);             //DataQueryException, DataNetworkException
                     
            //�Y���s���Ȃ��ꍇ�A�����A�Ō�������B 
            if (l_lisRowItems == null || l_lisRowItems.size() == 0)
            {
                log.debug("�Y���s���Ȃ��ꍇ�A�����A�Ō�������B");
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
                        l_bindVarsItem2);        //DataQueryException, DataNetworkException 
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
                log.debug("�Q�|�P�j�@@�J�X�^�}�C�Y�ҏW");
                for (int i = 0; i < l_intSize; i++)
                {
                    l_accOpenVoucherItemRow = (AccOpenVoucherItemRow)l_lisRowItems.get(i);
                    String l_strValue = null;
                    log.debug("���ڕҏW���@@ == �Œ�l�j�̏ꍇ�A�Œ�Z�b�g�l�̒l���Z�b�g����");
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
                    
                    //�f�[�^�R�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setRequestCode(super.getStringByByteNumber(l_strValue, 5));    
                    }
                    
                    //�،���ЃR�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setInstitutionCode(super.getStringByByteNumber(l_strValue, 3)); 
                    }
                    //���X�R�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.BRANCH_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setBranchCode(super.getStringByByteNumber(l_strValue, 3));  
                    }
                    //�ڋq�R�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setAccountCode(super.getStringByByteNumber(l_strValue, 7)); 
                    }
                    //���҃R�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.TRADER_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setTraderCode(super.getStringByByteNumber(l_strValue, 5));
                    }
                    //���ʃR�[�h�i�����J�݌����q�j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setAccOpenRequestNumber(super.getStringByByteNumber(l_strValue, 13));
                    } 
                        
                    //�`�[�ʔ�
                    if (WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setSerialNo(super.getStringByByteNumber(l_strValue, 3));   
                    }
                        
                    //�o�^�敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setRegistDiv(super.getStringByByteNumber(l_strValue, 1));    
                    }
                    
                    //�ڋq���i�Łj
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME_KANA.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAccRegVoucherParams.setAccountNameKana(super.getStringByByteNumber(l_strValue, 19));
                    }
            
                    //�ڋq���i�����j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
//                        l_hostAccRegVoucherParams.setAccountName(super.getStringByByteNumber(l_strValue, 18));  
                        l_hostAccRegVoucherParams.setAccountName(super.getEmStringByByteNumber(l_strValue, 18));  
                    }
                    
                    //�X�֔ԍ�
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAccRegVoucherParams.setZipCode(super.getStringByByteNumber(l_strValue, 7));
                    }
                    //�Z���P
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
//                        l_hostAccRegVoucherParams.setAddressLine1(super.getStringByByteNumber(l_strValue, 32));
                        l_hostAccRegVoucherParams.setAddressLine1(super.getEmStringByByteNumber(l_strValue, 32));
                    }
           
                    //�Z���P�i�J�i�j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1_KANA.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAccRegVoucherParams.setAddressLine1Kana(super.getStringByByteNumber(l_strValue, 30));
                    }

                    //�Z���Q
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
//                        l_hostAccRegVoucherParams.setAddressLine2(super.getStringByByteNumber(l_strValue, 32));
                        l_hostAccRegVoucherParams.setAddressLine2(super.getEmStringByByteNumber(l_strValue, 32));
                    }

                    //�Z���Q�i�J�i�j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE2_KANA.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAccRegVoucherParams.setAddressLine2Kana(super.getStringByByteNumber(l_strValue, 30));
                    }
            
                    //�Z���R
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
//                        l_hostAccRegVoucherParams.setAddressLine3(super.getStringByByteNumber(l_strValue, 32));
                        l_hostAccRegVoucherParams.setAddressLine3(super.getEmStringByByteNumber(l_strValue, 32));
                    }

                    //�Z���R�i�J�i�j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE3_KANA.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAccRegVoucherParams.setAddressLine3Kana(super.getStringByByteNumber(l_strValue, 30));
                    }
                    
                    //�d�b�ԍ�
                    if (WEB3AccountOpenOutputItemSymbolNameDef.TELEPHONE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAccRegVoucherParams.setTelephone(super.getStringByByteNumber(l_strValue, 16));
                    }
                        
                    //�A����Z��
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CONTACT_ADDRESS.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
//                        l_hostAccRegVoucherParams.setContactAddress(super.getStringByByteNumber(l_strValue, 36));
                        l_hostAccRegVoucherParams.setContactAddress(super.getEmStringByByteNumber(l_strValue, 36));
                    }

                    //�A����d�b�ԍ�
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CONTACT_TELEPHONE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAccRegVoucherParams.setContactTelephone(super.getStringByByteNumber(l_strValue, 16));
                    }
                        
                    //�ڊǑO���X��
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EX_BRANCH_NAME.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setExBranchName(super.getStringByByteNumber(l_strValue, 3));    
                    }
                        
                    //�ڊǑO�����R�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.EX_ACCOUNT_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setExAccountCode(super.getStringByByteNumber(l_strValue, 7));    
                    }
                    
                    //�E�Ƌ敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.OCCUPATION_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setOccupationDiv(super.getStringByByteNumber(l_strValue, 2));
                    }
                        
                    //���N�����N��
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ERA_BORN.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setEraBorn(super.getStringByByteNumber(l_strValue, 1));
                    }
                        
                    //���N����
                    if (WEB3AccountOpenOutputItemSymbolNameDef.BORN_DATE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setBornDate(super.getStringByByteNumber(l_strValue, 6));
                    }
                        
                    //����
                    if (WEB3AccountOpenOutputItemSymbolNameDef.SEX.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setSex(super.getStringByByteNumber(l_strValue, 1));
                    } 
                        
                    //����
                    if (WEB3AccountOpenOutputItemSymbolNameDef.DOCUMENT.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setDocument(super.getStringByByteNumber(l_strValue, 1));   
                    }
                        
                    //�Z���s��
                    if (WEB3AccountOpenOutputItemSymbolNameDef.UNKNOWN_ADDRESS.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setUnknownAddress(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //�񍐏�
                    if (WEB3AccountOpenOutputItemSymbolNameDef.REPORT.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setReport(super.getStringByByteNumber(l_strValue, 1));   
                    }
                        
                    //���Z�^�񋏏Z�敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.RESIDENT.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setResident(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //�ېŋ敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.TAXATION_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setTaxationDiv(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //�ېŋ敪�i�O���j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.FORIGN_TAXATION_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setForignTaxationDiv(super.getStringByByteNumber(l_strValue, 1));   
                    }
                        
                    //�ڋq�敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setAccountDiv(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //�����J��
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV1.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setAccountOpenDiv1(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV2.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setAccountOpenDiv2(super.getStringByByteNumber(l_strValue, 1));   
                    }
                        
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV3.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setAccountOpenDiv3(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV4.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setAccountOpenDiv4(super.getStringByByteNumber(l_strValue, 1));   
                    }
                        
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV5.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setAccountOpenDiv5(super.getStringByByteNumber(l_strValue, 1));     
                    }
                        
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV6.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setAccountOpenDiv6(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV7.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setAccountOpenDiv7(super.getStringByByteNumber(l_strValue, 1));   
                    }
                        
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV8.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setAccountOpenDiv8(super.getStringByByteNumber(l_strValue, 1)); 
                    }
                        
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV9.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setAccountOpenDiv9(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV10.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setAccountOpenDiv10(super.getStringByByteNumber(l_strValue, 1));   
                    }
                        
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV11.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setAccountOpenDiv11(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV12.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setAccountOpenDiv12(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_OPEN_DIV13.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setAccountOpenDiv13(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //�\���敪�i�\�������ېŁj
                    if (WEB3AccountOpenOutputItemSymbolNameDef.TAXATION_APPL_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setTaxationApplDiv(super.getStringByByteNumber(l_strValue, 1));  
                    }
                        
                    //���n�ېŋ敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.TAXATION_TRAN_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setTaxationTranDiv(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //�����敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.SEND_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setSendDiv(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //�M���o�R�敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.TRUST_VIA_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setTrustViaDiv(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //�����敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CORRECT_DIV1.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setCorrectDiv1(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CORRECT_DIV2.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setCorrectDiv2(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CORRECT_DIV3.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setCorrectDiv3(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CORRECT_DIV4.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setCorrectDiv4(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    if (WEB3AccountOpenOutputItemSymbolNameDef.CORRECT_DIV5.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setCorrectDiv5(super.getStringByByteNumber(l_strValue, 1));   
                    }
                        
                    //�敨OP�����J�݋敪�i���؁j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.IFO_ACC_OPEN_DIV_TOKYO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setIfoAccOpenDivTokyo(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //�敨OP�����J�݋敪�i��؁j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.IFO_ACC_OPEN_DIV_OSAKA.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setIfoAccOpenDivOsaka(super.getStringByByteNumber(l_strValue, 1));     
                    }
                        
                    //�敨OP�����J�݋敪�i���؁j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.IFO_ACC_OPEN_DIV_NAGOYA.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setIfoAccOpenDivNagoya(super.getStringByByteNumber(l_strValue, 1));   
                    }
                        
                    //�����敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.STATUS.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setStatus(super.getStringByByteNumber(l_strValue, 1));   
                    }
                    
                    //���̑��A����d�b�ԍ�
                    if (WEB3AccountOpenOutputItemSymbolNameDef.OTHER_CONTACT_TELEPHONE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setOtherContactTelephone(super.getStringByByteNumber(l_strValue, 16));    
                    }
                    
                    //����ƈ��҃R�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.BROKERAGE_TRADER_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setBrokerageTraderCode(super.getStringByByteNumber(l_strValue, 5));    
                    }

                    //�v���E�A�}�敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.PROAM_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setProamDiv(super.getStringByByteNumber(l_strValue, 1));
                    }

                    //�O���l�敪�i�����@@�j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.FOREIGNER_DIV_BROADCAST.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setForeignerDivBroadcast(super.getStringByByteNumber(l_strValue, 1));
                    }

                    //�O���l�敪�i�q��@@�j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.FOREIGNER_DIV_AVIATION.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setForeignerDivAviation(super.getStringByByteNumber(l_strValue, 1));
                    }

                    //�O���l�敪�iNTT�@@�j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.FOREIGNER_DIV_NTT.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setForeignerDivNtt(super.getStringByByteNumber(l_strValue, 1));
                    }

                    //�z�����U���w��敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.DIVIDEND_TRANSFER_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setDividendTransferDiv(super.getStringByByteNumber(l_strValue, 1));
                    }

                    //�㗝�l�敪�i��C�㗝�l�j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.AGENT_DIV_PERMANENT.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setAgentDivPermanent(super.getStringByByteNumber(l_strValue, 1));
                    }

                    //�㗝�l�敪�i�@@��㗝�l�j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.AGENT_DIV_LEGAL.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostAccRegVoucherParams.setAgentDivLegal(super.getStringByByteNumber(l_strValue, 1));
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
            l_hostAccRegVoucherParams.setOrderRequestNumber(l_strNewNumber);
            
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
                HostAccRegVoucherRow.TYPE,
                l_strWhere,
                l_bindVars);                 //DataQueryException, DataNetworkException
            
            //�R�|�Q�j�@@�`�[�s�}�� 
            l_queryProcesser.doInsertQuery(l_hostAccRegVoucherParams);  
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
     * �ڋq�o�^�`�[�iG11�j�L���[�e�[�u���̈ȉ��̏����ɓ��Ă͂܂�s���擾����B<BR>
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
     * @@roseuid 419C2BD702C2
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
                HostAccRegVoucherRow.TYPE,
                l_strWhere,
                l_bindVars);                    //DataQueryException, DataNetworkException
                
            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return false;    
            }
            else
            {
                l_queryProcesser.doDeleteAllQuery(
                    HostAccRegVoucherRow.TYPE,
                    l_strWhere,
                    l_bindVars);               //DataQueryException, DataNetworkException
                
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
     * @@roseuid 41B45E6B038A
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
