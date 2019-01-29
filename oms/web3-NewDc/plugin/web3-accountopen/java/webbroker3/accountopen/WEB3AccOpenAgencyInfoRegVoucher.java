head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.28.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenAgencyInfoRegVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �@@�\�ʒm���o�^�`�[�iWEB3AccOpenAgencyInfoRegVoucher.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/13 ���g (���u) �V�K�쐬 ���f��No.171
Revision History : 2009/09/04 �����F (���u) �d�l�ύX ���f��210
Revision History : 2009/09/09 �����F (���u) �c�a���C�A�E�g No.064
Revision History : 2009/09/16 �����F (���u) �c�a���C�A�E�g No.067
*/
package webbroker3.accountopen;

import java.lang.reflect.Field;
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
import webbroker3.accountopen.data.HostAgencyNotifyVoucherParams;
import webbroker3.accountopen.data.HostAgencyNotifyVoucherRow;
import webbroker3.accountopen.define.WEB3AccountOpenExpAccountOpenSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenOutputItemSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenVoucherCodeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchCodeDef;
import webbroker3.common.define.WEB3CatDelimitterDef;
import webbroker3.common.define.WEB3EditWayDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3RegDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�@@�\�ʒm���o�^�`�[)<BR>
 * �@@�\�ʒm���o�^�`�[<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AccOpenAgencyInfoRegVoucher extends WEB3AccOpenVoucher
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenAgencyInfoRegVoucher.class);

    /**
     * (�@@�\�ʒm���o�^�`�[)<BR>
     * �@@�\�ʒm���o�^�`�[<BR>
     */
    public WEB3AccOpenAgencyInfoRegVoucher()
    {

    }

    /**
     * �����J�݌����q�I�u�W�F�N�g���w�肵�A�@@�\�ʒm���o�^�`�[�C���X�^���X���쐬����B<BR>
     * <BR>
     * �P�j�@@�C���X�^���X����<BR>
     * �@@�@@�\�ʒm���o�^�`�[�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�����J�݌����q�v���p�e�B�Z�b�g<BR>
     * �@@���������C���X�^���X.set�����J�݌����q()�ɂāA�����J�݌����q�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@[set�����J�݌����q()�Ɏw�肷�����]<BR>
     * �@@�����J�݌����q�F�@@�����J�݌����q<BR>
     * <BR>
     * �R�j�@@�����J�ݓ`�[�}�X�^�v���p�e�B�Z�b�g<BR>
     * �@@���������C���X�^���X.set�`�[�}�X�^()�ɂāA�`�[�}�X�^�s�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �S�j�@@���������C���X�^���X��ԋp����B<BR>
     * @@param l_accOpenExpAccountOpen - (�����J�݌����q)<BR>
     * �����J�݌����q�I�u�W�F�N�g<BR>
     * @@return WEB3AccOpenAgencyInfoRegVoucher
     * @@throws WEB3BaseException
     */
    public static  WEB3AccOpenAgencyInfoRegVoucher getInstance(
        WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInstance(WEB3AccOpenExpAccountOpen)";
        log.entering(STR_METHOD_NAME);

        //�P�j�C���X�^���X����
        WEB3AccOpenAgencyInfoRegVoucher l_accOpenAgencyInfoRegVoucher =
            new WEB3AccOpenAgencyInfoRegVoucher();

        //�Q�j�����J�݌����q�v���p�e�B�Z�b�g
        l_accOpenAgencyInfoRegVoucher.setAccOpenExpAccountOpen(l_accOpenExpAccountOpen);

        //�R�j�����J�ݓ`�[�}�X�^�v���p�e�B�Z�b�g
        l_accOpenAgencyInfoRegVoucher.setAccOpenVoucherMaster();

        //�S�j�@@���������C���X�^���X��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_accOpenAgencyInfoRegVoucher;
    }

    /**
     * (is�I�����C���`�[)<BR>
     * (is�I�����C���`�[()�̎���)<BR>
     * <BR>
     * true��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isOnlineVoucher()
    {
        final String STR_METHOD_NAME = "isOnlineVoucher()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (get�f�[�^�R�[�h)<BR>
     * (get�f�[�^�R�[�h()�̎���)<BR>
     * <BR>
     * �f�[�^�R�[�h.�O�ݗa�������o�^�iGI865�j��ԋp����B<BR>
     * @@return String
     */
    public String getRequestCode()
    {
        final String STR_METHOD_NAME = "getRequestCode()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return WEB3HostRequestCodeDef.ACCOPEN_AGENCY_INFO_REGIST;
    }

    /**
     * (get�`�[�R�[�h)<BR>
     * (get�`�[�R�[�h()�̎���)<BR>
     * <BR>
     * �hGS103�h��ԋp����B<BR>
     * @@return String
     */
    public String getVoucherCode()
    {
        final String STR_METHOD_NAME = "getVoucherCode()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return WEB3AccountOpenVoucherCodeDef.ACCOPEN_AGENCY_INFO_REGIST;
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
     * �@@this.�����J�ݓ`�[�}�X�^�s[]�̊e�v�f���ɁA�Q�|�P�j�`�Q�|�R�j�̏��������{����B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�쐬�\����<BR>
     * �@@�@@is�쐬�\�`�[()�ɂāA�쐬�\�ȓ`�[���𔻒肷��B<BR>
     * <BR>
     * �@@�@@[is�쐬�\�`�[()�Ɏw�肷�����]<BR>
     * �@@�@@�`�[�ʔԁF�@@�����J�ݓ`�[�}�X�^�s[index].�`�[�ʔ�<BR>
     * <BR>
     * �@@�@@�쐬�\�łȂ��ꍇ�iis�쐬�\�`�[() == false�j�̂݁A�Q�|�Q�j�����{����B<BR>
     * �@@�@@�쐬�\�ȏꍇ�iis�쐬�\�`�[() == true�j�A<BR>
     * �@@�@@�@@���Y�v�f�̏������s�킸���̗v�f����������B�icontinue;�j<BR>
     * <BR>
     * �@@�@@���쐬�\�ȏꍇ�A���ڒl�͕ύX���Ă��ǂ��̂œ`�[�Q�ƍ��ږ��ɂ͊܂߂Ȃ��B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾<BR>
     * �@@�@@�@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u���̊e���ڂɂ��āA<BR>
     * �@@�@@�@@���L�̏��������{����B<BR>
     * <BR>
     * �@@�@@this.get�J�X�^�}�C�Y�Q�ƍ���()���R�[�����A<BR>
     * �@@�@@�@@�����J�݌����q�e�[�u���񕨗����̔z����擾����B<BR>
     * <BR>
     * �@@�@@[get�J�X�^�}�C�Y�Q�ƍ���()�Ɏw�肷�����]<BR>
     * �@@�@@�`�[�ʔԁF�@@�����J�ݓ`�[�}�X�^�s[index].�`�[�ʔ�<BR>
     * �@@�@@�`�[�o�͍��ڕ������F<BR>
     * �@@�@@�@@�i��1 �@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u���̏����Ώۍ��ځj<BR>
     * �@@�@@�`�[�Q�ƍ��ڏ����l�F<BR>
     * �@@�@@�@@�i��2 �@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u���̏����Ώۍ��ڃf�t�H���g�ݒ�l�j<BR>
     * <BR>
     * �@@�@@�i��2�j�@@�@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u���̏����Ώۍ��ڃf�t�H���g�ݒ�l<BR>
     * �@@�@@DB���C�A�E�g<BR>
     * �@@�@@�@@�u�@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u��.xls#�f�t�H���gDB�ݒ�_���v�V�[�g���Q�Ƃ��A<BR>
     * �@@�@@�Y�����ڂ̐������ɁA�����J�݌����q�e�[�u������ҏW����w�肪����΁A<BR>
     * �@@�@@�@@�w�荀�ڂ̗񕨗����z��B<BR>
     * �@@�@@�ȊO�́Anull�B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�<BR>
     * �@@�@@�`�[�g�p����Table�i�FHashtable�j.put()<BR>
     * �@@�@@�@@�ɂ�this.get�J�X�^�}�C�Y�Q�ƍ���()�߂�l����v�f���ǉ�����B<BR>
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
     * @@throws WEB3BaseException
     */
    public String[] getConfirmedItemName() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getConfirmedItemName()";
        log.entering(STR_METHOD_NAME);

        //�P�j�`�[�g�p����Table�i�FHashtable�j����
        Hashtable l_voucherItemList = new Hashtable();

        //�Q�j�g�p���ڃZ�b�g
        //this.�����J�ݓ`�[�}�X�^�s[]�̊e�v�f���ɁA�Q�|�P�j�`�Q�|�R�j�̏��������{����B
        int l_intLength = 0;
        if (accOpenVoucherMasterParamses != null)
        {
            l_intLength = this.accOpenVoucherMasterParamses.length;
        }

        for (int i = 0; i < l_intLength; i++)
        {
            //�Q�|�P�j�@@�쐬�\����
            //is�쐬�\�`�[()�ɂāA�쐬�\�ȓ`�[���𔻒肷��B
            //[is�쐬�\�`�[()�Ɏw�肷�����]
            //�`�[�ʔԁF�@@�����J�ݓ`�[�}�X�^�s[index].�`�[�ʔ�
            String l_strSerialNo = accOpenVoucherMasterParamses[i].getSerialNo();
            boolean l_blnCreatedPossibleVoucher = this.isCreatedPossibleVoucher(l_strSerialNo);

            //�쐬�\�łȂ��ꍇ�iis�쐬�\�`�[() == false�j�̂݁A�Q�|�Q�j�����{����B
            if (!l_blnCreatedPossibleVoucher)
            {
                //�@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u���̊e���ڂɂ��āA���L�̏��������{����B
                //this.get�J�X�^�}�C�Y�Q�ƍ���()���R�[�����A�����J�݌����q�e�[�u���񕨗����̔z����擾����B
                //[get�J�X�^�}�C�Y�Q�ƍ���()�Ɏw�肷�����]
                //�`�[�ʔԁF�@@�����J�ݓ`�[�}�X�^�s[index].�`�[�ʔ�
                //�`�[�o�͍��ڕ������F�@@�i��1 �@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u���̏����Ώۍ��ځj
                //�`�[�Q�ƍ��ڏ����l�F�@@�i��2 �@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u���̏����Ώۍ��ڃf�t�H���g�ݒ�l�j
                //�i��2�j�@@�@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u���̏����Ώۍ��ڃf�t�H���g�ݒ�l
                //DB���C�A�E�g �u�@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u��.xls#�f�t�H���gDB�ݒ�_���v�V�[�g���Q�Ƃ��A
                //�Y�����ڂ̐������ɁA�����J�݌����q�e�[�u������ҏW����w�肪����΁A�w�荀�ڂ̗񕨗����z��B
                //�ȊO�́Anull�B

                String[] l_strValues1 = new String[1];

                //�،���ЃR�[�h
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSTITUTION_CODE;
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                String[] l_strCustomizingRefItem = this.getCustomizingRefItem(
                    l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE,
                    l_strValues1);
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

                //�t���K�i
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ACC_NAME_KANA1;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME_KANA1, l_strValues1);
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
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ACC_NAME1;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME1, l_strValues1);
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
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ZIP_CODE;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE1, l_strValues1);
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
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.ZIP_CODE;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE2, l_strValues1);
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

                //�Z��
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ADDRESS_LINE1;
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

                //��\�҂̖�E
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_POST;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.REPRESENT_POST, l_strValues1);
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

                //��\�҂̃t���K�i
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_NAME_KANA1;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.REPRESENT_NAME_KANA1, l_strValues1);
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

                //��\�҂̎���
                //�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾
                l_strValues1[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_NAME1;
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.REPRESENT_NAME1, l_strValues1);
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

                //�t���K�i
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_KANA, null);
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

                //����1
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_NAME1, null);
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

                //���Z�@@�֓��R�[�h
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_INSTITUTION, null);
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

                //�X�܃R�[�h
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_BRANCH, null);
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

                //�a�����
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_TYPE, null);
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

                //�����ԍ�
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_NO, null);
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

                //���`�l�敪
                l_strCustomizingRefItem = this.getCustomizingRefItem(l_strSerialNo,
                    WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV, null);
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
     * �@@�@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u���s�I�u�W�F�N�g�𐶐����A<BR>
     * �@@�f�t�H���g�ݒ�i��1�j�̒ʂ�Ƀv���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A<BR>
     * �@@�@@�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�B<BR>
     * <BR>
     * �@@�i��1�j�@@�@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u���̏����Ώۍ��ڃf�t�H���g�ݒ�<BR>
     * �@@DB���C�A�E�g �u�@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u��.xls#�f�t�H���gDB�ݒ�_���v�V�[�g�Q�ƁB<BR>
     * <BR>
     * �Q�j�@@�J�X�^�}�C�Y���ڃZ�b�g<BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^�e�[�u�����ȉ��̏����@@�Ō�������B<BR>
     * �@@�Y���s���Ȃ��ꍇ�A�����A�Ō�������B<BR>
     * <BR>
     * �@@[�����@@]<BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And<BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.���X�R�[�h = this.get���X�R�[�h() And<BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�����敪 = this.get�����敪() And<BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�f�[�^�R�[�h = this.get�f�[�^�R�[�h() And<BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�`�[�ʔ� = �`�[�ʔ�<BR>
     * <BR>
     * �@@[�����A]<BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And<BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.���X�R�[�h = "000" And<BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�����敪 = this.get�����敪() And<BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�f�[�^�R�[�h = this.get�f�[�^�R�[�h() And<BR>
     * �@@�����J�ݓ`�[���ڃ}�X�^.�`�[�ʔ� = �`�[�ʔ�<BR>
     * <BR>
     * �@@�����@@�C�A�̂ǂ��炩�ŊY���s������ꍇ�́A�Q�|�P�j�̏��������{����B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�J�X�^�}�C�Y�ҏW<BR>
     * �@@�@@�������ʂ̊e�s���ɁA�o�͍��ڕ������������`�[���ڂ̒l���A�w��̕��@@�ōăZ�b�g����B<BR>
     * �@@�@@��������Z�b�g����ꍇ�A<BR>
     * �@@�@@�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�B<BR>
     * <BR>
     * �@@�@@�� �i���ڕҏW���@@ == �Œ�l�j�̏ꍇ�A�Œ�Z�b�g�l�̒l���Z�b�g����B<BR>
     * �@@�@@�� �ȊO�̏ꍇ�A���͍��ڕ������P�`�R�����������J�݌����q�̍��ڒl(��2)���Z�b�g����B<BR>
     * �@@�@@�@@�@@�|���͍��ڕ������P�`�R��null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�|�A�����ڃf���~�b�^���w�肳��Ă���ꍇ�i�A�����ڃf���~�b�^ != null�j�A<BR>
     * �@@�@@�@@�@@�@@���͍��ڕ������P�C�Q�C�R�̒l���f���~�b�^�ɂĘA������B<BR>
     * <BR>
     * �@@�@@�i��2�j DB���C�A�E�g�u�����J�ݓ`�[���ڃ}�X�^�v�Q�ƁB<BR>
     * �@@<BR>
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
     * �@@�@@�ȉ��̏����ɂċ@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u������������B<BR>
     * �@@�@@�Y���s�����ɑ��݂���ꍇ�́A�Y���s��delete����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�،���ЃR�[�h = this.get�،���ЃR�[�h() And<BR>
     * �@@�@@���ʃR�[�h = this.get���ʃR�[�h() And<BR>
     * �@@�@@�`�[�ʔ� = �`�[�ʔ� And<BR>
     * �@@�@@�����敪 = �h�������h<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�`�[�s�}��<BR>
     * �@@�@@�P�j�`�Q�j�ŕҏW�����s�I�u�W�F�N�g��DB�ɍX�V�iDB-insert����j�B<BR>
     * <BR>
     * @@param l_strSerialNo - (�`�[�ʔ�)<BR>
     * �`�[�ʔ�<BR>
     * @@throws WEB3BaseException
     */
    public void saveVoucherRow(String l_strSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveVoucherRow(String)";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@�f�t�H���g�ݒ�s����
        //�@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u���s�I�u�W�F�N�g�𐶐����A
        //�f�t�H���g�ݒ�i��1�j�̒ʂ�Ƀv���p�e�B���Z�b�g����B
        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A
        //�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�B
        //�i��1�j�@@�@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u���̏����Ώۍ��ڃf�t�H���g�ݒ�
        //DB���C�A�E�g
        //�u�@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u��.xls#�f�t�H���gDB�ݒ�_���v�V�[�g�Q�ƁB
        HostAgencyNotifyVoucherParams l_hostAgencyNotifyVoucherParams =
            new HostAgencyNotifyVoucherParams();

        String l_strInstitutionCode = this.accOpenExpAccountOpen.getInstitutionCode();
        String l_strBranchCode = this.accOpenExpAccountOpen.getBranchCode();
        String l_strAccountCode = this.accOpenExpAccountOpen.getAccountCode();
        String l_strRequestNumber = this.accOpenExpAccountOpen.getRequestNumber();

        //�f�[�^�R�[�h:�@@�\�ʒm���o�^�F�hGI865�h
        l_hostAgencyNotifyVoucherParams.setRequestCode(
            super.getStringByByteNumber(WEB3HostRequestCodeDef.ACCOPEN_AGENCY_INFO_REGIST, 5));

        //�،���ЃR�[�h:�����J�݌����q.�،���ЃR�[�h��ҏW����B
        l_hostAgencyNotifyVoucherParams.setInstitutionCode(
            super.getStringByByteNumber(l_strInstitutionCode, 3));

        //���X�R�[�h:�����J�݌����q.���X�R�[�h��ҏW����B
        l_hostAgencyNotifyVoucherParams.setBranchCode(
            super.getStringByByteNumber(l_strBranchCode, 3));

        //�ڋq�R�[�h:�����J�݌����q.�����R�[�h��ҏW����B
        l_hostAgencyNotifyVoucherParams.setAccountCode(
            super.getStringByByteNumber(l_strAccountCode, 7));

        //���҃R�[�h:�����J�݌����q.���҃R�[�h�iSONAR�j��ҏW����B
        ExpAccountOpenRow l_expAccountOpenRow =
            (ExpAccountOpenRow)this.accOpenExpAccountOpen.getDataSourceObject();
        String l_strSonarTraderCode = l_expAccountOpenRow.getSonarTraderCode();
        l_hostAgencyNotifyVoucherParams.setTraderCode(
            super.getStringByByteNumber(l_strSonarTraderCode, 5));

        //���ʃR�[�h�i�����J�݌����q�j:�����J�݌����q.���ʃR�[�h��ҏW����B
        l_hostAgencyNotifyVoucherParams.setAccOpenRequestNumber(
            super.getStringByByteNumber(l_strRequestNumber, 13));

        //�`�[�ʔ�:����.�`�[�ʔ�
        l_hostAgencyNotifyVoucherParams.setSerialNo(
            super.getStringByByteNumber(l_strSerialNo, 3));

        //�o�^�敪:1�F�V�K
        l_hostAgencyNotifyVoucherParams.setRegistDiv(
            super.getStringByByteNumber(WEB3RegDivDef.NEW, 1));

        //�t���K�i:�����J�݌����q.�@@�\�ʒm���.�t���K�i1��ҏW����B
        String l_strAgencyAccNameKana1 =
            WEB3StringTypeUtility.to1byteKana(l_expAccountOpenRow.getAgencyAccNameKana1());
        l_hostAgencyNotifyVoucherParams.setAccountNameKana1(
            super.getStringByByteNumber(l_strAgencyAccNameKana1, 120));

        //����:�����J�݌����q.�@@�\�ʒm���.����1��ҏW����B
        String l_strAgencyAccName1 = l_expAccountOpenRow.getAgencyAccName1();
        l_hostAgencyNotifyVoucherParams.setAccountName1(
            super.getEmStringByByteNumber(l_strAgencyAccName1, 120));

        //�X�֔ԍ��i�e�j:�����J�݌����q.�X�֔ԍ���1�`3���ڂ�ҏW����B
        String l_strZipCode = l_expAccountOpenRow.getZipCode();
        l_hostAgencyNotifyVoucherParams.setZipCode1(
            super.getStringByByteNumber(l_strZipCode, 3));

        //�X�֔ԍ��i�q�j:�����J�݌����q.�X�֔ԍ���4�`7���ڂ�ҏW����B
        String l_strZipCode2 = this.getZipCode2(l_strZipCode);
        l_hostAgencyNotifyVoucherParams.setZipCode2(l_strZipCode2);

        //�Z��:  �����J�݌����q.�@@�\�ʒm���.�Z��1��ҏW����B
        l_hostAgencyNotifyVoucherParams.setAddressLine1(
            super.getEmStringByByteNumber(
                l_expAccountOpenRow.getAgencyAddressLine1(), 96));

        //��\�҂̖�E:�����J�݌����q.�@@�\�ʒm���.��\�҂̖�E��ҏW����B
        l_hostAgencyNotifyVoucherParams.setRepresentPost(
            super.getStringByByteNumber(l_expAccountOpenRow.getAgencyRepPost(), 40));

        //��\�҂̃t���K�i:�����J�݌����q.�@@�\�ʒm���.��\�҂̃t���K�i1��ҏW����B
        String l_strAgencyRepNameKana1 =
            WEB3StringTypeUtility.to1byteKana(l_expAccountOpenRow.getAgencyRepNameKana1());
        l_hostAgencyNotifyVoucherParams.setRepresentNameKana1(
            super.getStringByByteNumber(l_strAgencyRepNameKana1, 120));

        //��\�҂̎���:�����J�݌����q.�@@�\�ʒm���.��\�҂̎���1��ҏW����B
        l_hostAgencyNotifyVoucherParams.setRepresentName1(
            super.getEmStringByByteNumber(l_expAccountOpenRow.getAgencyRepName1(), 120));

        //�t���K�i:null
        l_hostAgencyNotifyVoucherParams.setReceiptKana(null);

        //����1:null
        l_hostAgencyNotifyVoucherParams.setReceiptName1(null);

        //���Z�@@�֓��R�[�h:null
        l_hostAgencyNotifyVoucherParams.setReceiptFinInstitution(null);

        //�X�܃R�[�h:null
        l_hostAgencyNotifyVoucherParams.setReceiptFinBranch(null);

        //�a�����:null
        l_hostAgencyNotifyVoucherParams.setReceiptFinAccType(null);

        //�����ԍ�:null
        l_hostAgencyNotifyVoucherParams.setReceiptFinAccNo(null);

        //���`�l�敪:null
        l_hostAgencyNotifyVoucherParams.setReceiptFinAccDiv(null);

        //�����敪:0�F������
        l_hostAgencyNotifyVoucherParams.setStatus(
            super.getStringByByteNumber(WEB3StatusDef.NOT_DEAL, 1));

        //���M����:null
        l_hostAgencyNotifyVoucherParams.setSendTimestamp(null);

        //�쐬����:��������
        l_hostAgencyNotifyVoucherParams.setCreatedTimestamp(
            GtlUtils.getSystemTimestamp());

        //�X�V����:��������
        l_hostAgencyNotifyVoucherParams.setLastUpdatedTimestamp(
            GtlUtils.getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcesser =
                Processors.getDefaultProcessor();

            //�Q�j�@@�J�X�^�}�C�Y���ڃZ�b�g
            //�����J�ݓ`�[���ڃ}�X�^�e�[�u�����ȉ��̏����@@�Ō�������B
            //�Y���s���Ȃ��ꍇ�A�����A�Ō�������B
            //[�����@@]
            //�����J�ݓ`�[���ڃ}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And
            //�����J�ݓ`�[���ڃ}�X�^.���X�R�[�h = this.get���X�R�[�h() And
            //�����J�ݓ`�[���ڃ}�X�^.�����敪 = this.get�����敪() And
            //�����J�ݓ`�[���ڃ}�X�^.�f�[�^�R�[�h = this.get�f�[�^�R�[�h() And
            //�����J�ݓ`�[���ڃ}�X�^.�`�[�ʔ� = �`�[�ʔ�
            String l_strWhereItem =
                "institution_code = ? and "
                + "branch_code = ? and "
                + "account_div = ? and "
                + "request_code = ? and "
                + "serial_no = ? ";

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
                    l_bindVarsItem);

            //�Y���s���Ȃ��ꍇ�A�����A�Ō�������B
            if (l_lisRowItems == null || l_lisRowItems.size() == 0)
            {
                //[�����A]
                //�����J�ݓ`�[���ڃ}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And
                //�����J�ݓ`�[���ڃ}�X�^.���X�R�[�h = "000" And
                //�����J�ݓ`�[���ڃ}�X�^.�����敪 = this.get�����敪() And
                //�����J�ݓ`�[���ڃ}�X�^.�f�[�^�R�[�h = this.get�f�[�^�R�[�h() And
                //�����J�ݓ`�[���ڃ}�X�^.�`�[�ʔ� = �`�[�ʔ�
                Object l_bindVarsItem2[] =
                    {l_strInstitutionCode,
                    WEB3BranchCodeDef.DEFAULT,
                     this.getAccountDiv(),
                     l_strRequestCode,
                     l_strSerialNo};

                l_lisRowItems =
                    l_queryProcesser.doFindAllQuery(
                        AccOpenVoucherItemRow.TYPE,
                        l_strWhereItem,
                        l_bindVarsItem2);
            }

            //�����@@�C�A�̂ǂ��炩�ŊY���s������ꍇ�́A�Q�|�P�j�̏��������{����B
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
                    //�������ʂ̊e�s���ɁA�o�͍��ڕ������������`�[���ڂ̒l���A
                    //�w��̕��@@�ōăZ�b�g����B
                    l_accOpenVoucherItemRow = (AccOpenVoucherItemRow)l_lisRowItems.get(i);
                    String l_strValue = null;
                    //�� �i���ڕҏW���@@ == �Œ�l�j�̏ꍇ�A�Œ�Z�b�g�l�̒l���Z�b�g����B
                    if (WEB3EditWayDivDef.FIXED_VALUE.equals(l_accOpenVoucherItemRow.getEditWayDiv()))
                    {
                        l_strValue = l_accOpenVoucherItemRow.getFixedValue();
                    }
                    //�� �ȊO�̏ꍇ�A���͍��ڕ������P�`�R�����������J�݌����q�̍��ڒl(��2)���Z�b�g����B
                    //�|���͍��ڕ������P�`�R��null�̏ꍇ��
                    //�|�A�����ڃf���~�b�^���w�肳��Ă���ꍇ�i�A�����ڃf���~�b�^ != null�j
                    //���͍��ڕ������P�C�Q�C�R�̒l���f���~�b�^�ɂĘA������B
                    //�i��2�j DB���C�A�E�g�u�����J�ݓ`�[���ڃ}�X�^�v�Q�ƁB
                    else
                    {
                        String l_strValue1 =
                            this.nameCompare(l_accOpenVoucherItemRow.getInputItemSymbolName1());
                        String l_strValue2 =
                            this.nameCompare(l_accOpenVoucherItemRow.getInputItemSymbolName2());
                        String l_strValue3 =
                            this.nameCompare(l_accOpenVoucherItemRow.getInputItemSymbolName3());
                        if (WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM_TO_HALFKANA.equals(
                            l_accOpenVoucherItemRow.getEditWayDiv()))
                        {
                            l_strValue1 = WEB3StringTypeUtility.to1byteKana(l_strValue1);
                            l_strValue2 = WEB3StringTypeUtility.to1byteKana(l_strValue2);
                            l_strValue3 = WEB3StringTypeUtility.to1byteKana(l_strValue3);
                        }

                        //���͍��ڕ������P�C�Q�C�R�̒l���f���~�b�^�ɂĘA������B

                        if (l_accOpenVoucherItemRow.getCatDelimitter() != null
                            && !WEB3CatDelimitterDef.WITHOUT.equals(
                                l_accOpenVoucherItemRow.getCatDelimitter()))
                        {
                            if (l_strValue1 != null)
                            {
                                l_strValue = l_strValue1;
                                if (l_strValue2 != null)
                                {
                                    if (WEB3CatDelimitterDef.HALF_SPACE.equals(
                                        l_accOpenVoucherItemRow.getCatDelimitter()))
                                    {
                                        l_strValue = l_strValue + " " + l_strValue2;
                                    }
                                    else if (WEB3CatDelimitterDef.FULL_SPACE.equals(
                                        l_accOpenVoucherItemRow.getCatDelimitter()))
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
                                    if (WEB3CatDelimitterDef.HALF_SPACE.equals(
                                        l_accOpenVoucherItemRow.getCatDelimitter()))
                                    {
                                        l_strValue = l_strValue + " " + l_strValue3;
                                    }
                                    else if (WEB3CatDelimitterDef.FULL_SPACE.equals(
                                        l_accOpenVoucherItemRow.getCatDelimitter()))
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

                    //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A
                    //�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�B
                    //�f�[�^�R�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setRequestCode(
                            super.getStringByByteNumber(l_strValue, 5));
                    }

                    //�،���ЃR�[�h
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setInstitutionCode(
                            super.getStringByByteNumber(l_strValue, 3));
                    }
                    //���X�R�[�h
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.BRANCH_CODE.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setBranchCode(
                            super.getStringByByteNumber(l_strValue, 3));
                    }
                    //�ڋq�R�[�h
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_CODE.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setAccountCode(
                            super.getStringByByteNumber(l_strValue, 7));
                    }
                    //���҃R�[�h
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.TRADER_CODE.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setTraderCode(
                            super.getStringByByteNumber(l_strValue, 5));
                    }
                    //���ʃR�[�h�i�����J�݌����q�j
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setAccOpenRequestNumber(
                            super.getStringByByteNumber(l_strValue, 13));
                    }
                    //�`�[�ʔ�
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setSerialNo(
                            super.getStringByByteNumber(l_strValue, 3));
                    }
                    //�o�^�敪
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DIV.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setRegistDiv(
                            super.getStringByByteNumber(l_strValue, 1));
                    }
                    //�t���K�i
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME_KANA1.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setAccountNameKana1(
                            super.getStringByByteNumber(WEB3StringTypeUtility.to1byteKana(l_strValue), 120));
                    }
                    //����
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_NAME1.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setAccountName1(
                            super.getEmStringByByteNumber(l_strValue, 120));
                    }
                    //�X�֔ԍ��i�e�j
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE1.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setZipCode1(
                            super.getStringByByteNumber(l_strValue, 3));
                    }
                    //�X�֔ԍ��i�q�j
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ZIP_CODE2.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setZipCode2(
                            this.getZipCode2(l_strValue));
                    }
                    //�Z��
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ADDRESS_LINE1.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setAddressLine1(
                            super.getEmStringByByteNumber(l_strValue, 96));
                    }
                    //��\�҂̖�E
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.REPRESENT_POST.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setRepresentPost(
                            super.getStringByByteNumber(l_strValue, 40));
                    }
                    //��\�҂̃t���K�i
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.REPRESENT_NAME_KANA1.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setRepresentNameKana1(
                            super.getStringByByteNumber(WEB3StringTypeUtility.to1byteKana(l_strValue), 120));
                    }
                    //��\�҂̎���
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.REPRESENT_NAME1.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setRepresentName1(
                            super.getEmStringByByteNumber(l_strValue, 120));
                    }
                    //�t���K�i
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_KANA.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setReceiptKana(
                            super.getStringByByteNumber(l_strValue, 38));
                    }
                    //����
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_NAME1.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setReceiptName1(
                            super.getStringByByteNumber(l_strValue, 80));
                    }
                    //���Z�@@�֓��R�[�h
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_INSTITUTION.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setReceiptFinInstitution(
                            super.getStringByByteNumber(l_strValue, 4));
                    }
                    //�X�܃R�[�h
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_BRANCH.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setReceiptFinBranch(
                            super.getStringByByteNumber(l_strValue, 3));
                    }
                    //�a�����
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_TYPE.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setReceiptFinAccType(
                            super.getStringByByteNumber(l_strValue, 1));
                    }
                    //�����ԍ�
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_NO.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setReceiptFinAccNo(
                            super.getStringByByteNumber(l_strValue, 7));
                    }
                    //���`�l�敪
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.RECEIPT_FIN_ACC_DIV.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setReceiptFinAccDiv(
                            super.getStringByByteNumber(l_strValue, 1));
                    }
                    //�����敪
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.STATUS.equals(
                        l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        l_hostAgencyNotifyVoucherParams.setStatus(
                            super.getStringByByteNumber(l_strValue, 1));
                    }
                }
            }

            //�Q�|�Q�j�@@�`�[�̎��ʃR�[�h�V�K�̔�
            //�������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h()�ɂĎ��ʃR�[�h���擾���A
            //�s�I�u�W�F�N�g�̎��ʃR�[�h�iorder_request_number�j�ɃZ�b�g����B
            //[get�V�K���ʃR�[�h()�Ɏw�肷�����]
            //�،���ЃR�[�h�F�@@this.get�،���ЃR�[�h()
            //���X�R�[�h�F�@@this.get���X�R�[�h()
            //�����^�C�v�F�@@ProductTypeEnum.���̑�
            WEB3HostReqOrderNumberManageService l_reqOrderNumberManageService=
                (WEB3HostReqOrderNumberManageService)Services.getService(
                    WEB3HostReqOrderNumberManageService.class);
            String l_strNewNumber = l_reqOrderNumberManageService.getNewNumber(
                l_strInstitutionCode,
                l_strBranchCode,
                ProductTypeEnum.OTHER);
            l_hostAgencyNotifyVoucherParams.setOrderRequestNumber(l_strNewNumber);

            //�R�|�P�j�@@�����s�폜
            //�ȉ��̏����ɂċ@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u������������B
            //�Y���s�����ɑ��݂���ꍇ�́A�Y���s��delete����B
            //�،���ЃR�[�h = this.get�،���ЃR�[�h() And
            //���ʃR�[�h = this.get���ʃR�[�h() And
            //�`�[�ʔ� = �`�[�ʔ� And
            //�����敪 = �h�������h
            String l_strWhere =
                "institution_code = ? and "
                + "acc_open_request_number = ? and "
                + "serial_no = ? and "
                + "status = ? ";

            Object l_bindVars[] =
                {l_strInstitutionCode,
                 l_strRequestNumber,
                 l_strSerialNo,
                 WEB3StatusDef.NOT_DEAL};
            l_queryProcesser.doDeleteAllQuery(
                HostAgencyNotifyVoucherParams.TYPE,
                l_strWhere,
                l_bindVars);

            //�R�|�Q�j�@@�`�[�s�}��
            //�P�j�`�Q�j�ŕҏW�����s�I�u�W�F�N�g��DB�ɍX�V�iDB-insert����j�B
            l_queryProcesser.doInsertQuery(l_hostAgencyNotifyVoucherParams);
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
     * (saveDelete�`�[�s)<BR>
     * �isaveDelete�`�[�s()�̎����j<BR>
     * �����J�ݓ`�[���P���폜����B<BR>
     * <BR>
     * �@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u���̈ȉ��̏����ɓ��Ă͂܂�s���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = this.get�،���ЃR�[�h() And<BR>
     * �@@���ʃR�[�h = this.get���ʃR�[�h() And<BR>
     * �@@�`�[�ʔ� = �`�[�ʔ� And<BR>
     * �@@�����敪 = �h�������h<BR>
     * <BR>
     * �s���擾�ł��Ȃ������ꍇ�Afalse��ԋp����B<BR>
     * �s���擾�ł����ꍇ�A�Y���s�̍폜�idelete row�j���s���Atrue��ԋp����B<BR>
     * @@param l_strSerialNo - (�`�[�ʔ�)<BR>
     * �`�[�ʔ�<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean saveDeleteVoucherRow(String l_strSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveDeleteVoucherRow(String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u���̈ȉ��̏����ɓ��Ă͂܂�s���擾����B
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();

            //�،���ЃR�[�h = this.get�،���ЃR�[�h() And
            //���ʃR�[�h = this.get���ʃR�[�h() And
            //�`�[�ʔ� = �`�[�ʔ� And
            //�����敪 = �h�������h
            String l_strWhere =
                " institution_code = ? and "
                + "acc_open_request_number = ? and "
                + "serial_no = ? and "
                + "status = ? ";

            String l_strInstitutionCode = this.getInstitutionCode();
            String l_strRequestNumber = this.getRequestNumber();

            Object l_bindVars[] =
                {l_strInstitutionCode,
                 l_strRequestNumber,
                 l_strSerialNo,
                 WEB3StatusDef.NOT_DEAL};

            List l_lisRows = l_queryProcesser.doFindAllQuery(
                HostAgencyNotifyVoucherRow.TYPE,
                l_strWhere,
                l_bindVars);
            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                //�s���擾�ł��Ȃ������ꍇ�Afalse��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            else
            {
                //�s���擾�ł����ꍇ�A�Y���s�̍폜�idelete row�j���s���Atrue��ԋp����B
                l_queryProcesser.doDeleteAllQuery(
                    HostAgencyNotifyVoucherRow.TYPE,
                    l_strWhere,
                    l_bindVars);

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
     * @@return Object<BR>
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
        final String STR_METHOD_NAME = "nameCompare(String)";
        log.entering(STR_METHOD_NAME);

        if (l_str == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        ExpAccountOpenParams l_expAccountOpenParams =
            new ExpAccountOpenParams((ExpAccountOpenRow)this.accOpenExpAccountOpen.getDataSourceObject());

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

    /**
     * �����J�݌����q.�X�֔ԍ���4�`7���ڂ�ҏW����B
     * @@param l_strZipCode
     * @@return
     */
    private String getZipCode2(String l_strZipCode)
    {
        final String STR_METHOD_NAME = "getZipCode2(String)";
        log.entering(STR_METHOD_NAME);
        if (l_strZipCode == null)
        {
            return null;
        }

        if (l_strZipCode.length() >= 7)
        {
            log.entering(STR_METHOD_NAME);
            return l_strZipCode.substring(3, 7);
        }
        else if (l_strZipCode.length() < 7 && l_strZipCode.length() > 4)
        {
            log.entering(STR_METHOD_NAME);
            return l_strZipCode.substring(l_strZipCode.length()-4, l_strZipCode.length());
        }
        else
        {
            log.entering(STR_METHOD_NAME);
            return l_strZipCode;
        }
    }
}
@
