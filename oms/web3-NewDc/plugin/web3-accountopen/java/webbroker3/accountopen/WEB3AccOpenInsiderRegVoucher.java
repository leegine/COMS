head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInsiderRegVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright            : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name            : �����ғo�^�`�[(WEB3AccOpenInsiderRegVoucher.java)
Author Name          : Daiwa Institute of Research
Revesion History     : 2006/07/07 ꎉ� �V�K�쐬
                       2006/07/13 ꎉ� (���u) �d�l�ύX�E���f��077�A�c�a���C�A�E�g026
                       2006/07/18 ꎉ� �d�l�ύX�E���f��081
                       2006/08/23 ���r �d�l�ύX�E���f��097
                       2006/10/12 �ęԍg �c�a���C�A�E�g  No.038
                       2007/06/18 ���� (SCS) �����̖�� No.007
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
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.accountopen.data.AccOpenVoucherItemRow;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.data.HostInsiderRegVoucherParams;
import webbroker3.accountopen.define.WEB3AccountOpenExpAccountOpenSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenOutputItemSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenVoucherCodeDef;

/**
 * (�����ғo�^�`�[)<BR>
 * �����ғo�^�`�[<BR>
 * 
 * @@author ꎉ�
 * @@version 1.0 
 */
public class WEB3AccOpenInsiderRegVoucher extends WEB3AccOpenVoucher
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
     private static WEB3LogUtility log =
             WEB3LogUtility.getInstance(WEB3AccOpenInsiderRegVoucher.class);

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
     *�f�[�^�R�[�h.�����ғo�^�iGI819�j��ԋp����B<BR>
     *@@return String
     *@@roseuid 4192EAB00026
     */
    public String getRequestCode()
    {
        final String STR_METHOD_NAME = " getRequestCode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return WEB3HostRequestCodeDef.ACCOPEN_INSIDER_REG_VOUCHER;
    }


    
    /**
     *(get�`�[�R�[�h)<BR>
     *(get�`�[�R�[�h()�̎���)<BR>
     *<BR>
     *�hG9801�h��ԋp����B<BR>
     *@@return String
     *@@roseuid 419DDE9900A3
     */
    public String getVoucherCode()
    {
        final String STR_METHOD_NAME = " getVoucherCode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return WEB3AccountOpenVoucherCodeDef.INSIDER_REG_VOUCHER_CODE;
    }


    /**
     *(get�m��ύ��ږ�)<BR>
     *(get�m��ύ��ږ�()�̎���)<BR>
     *<BR>
     *���Y�`�[�Ŏg�p���Ă�������J�݌����q�̗񕨗�����z��Ŏ擾����B <BR>
     *<BR>
     *�P�j�@@�`�[�g�p����Table�i�FHashtable�j����<BR>
     *�@@Hashtable�𐶐�����B<BR>
     *<BR>
     *�Q�j�@@�g�p���ڃZ�b�g<BR>
     *<BR>
     *this.�����J�ݓ`�[�}�X�^�s[]�̊e�v�f���ɁA�Q�|�P�j�`�Q�|�R�j�̏�����<BR>
     *���{����B<BR>
     *<BR>
     *�Q�|�P�j�@@�쐬�\����<BR>
     *is�쐬�\�`�[()�ɂāA�쐬�\�ȓ`�[���𔻒肷��B<BR>
     *<BR>
     *[is�쐬�\�`�[()�Ɏw�肷�����]<BR>
     *�`�[�ʔԁF�@@�����J�ݓ`�[�}�X�^�s[index].�`�[�ʔ�<BR>
     *<BR>
     *�쐬�\�łȂ��ꍇ�iis�쐬�\�`�[() == false�j�̂݁A�Q�|�Q�j�����{����B<BR>
     *�쐬�\�ȏꍇ�iis�쐬�\�`�[() == true�j�A���Y�v�f�̏�����<BR>
     *�s�킸���̗v�f����������B�icontinue;�j<BR>
     *<BR>
     *���쐬�\�ȏꍇ�A���ڒl�͕ύX���Ă��ǂ��̂�<BR>
     *�`�[�Q�ƍ��ږ��ɂ͊܂߂Ȃ��B<BR>
     *<BR>
     *�Q�|�Q�j�@@�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾<BR>
     *�����ғo�^�`�[�iG9801�j�L���[�e�[�u���̊e���ڂɂ��āA���L�̏��������{����B<BR>
     *<BR>
     *this.get�J�X�^�}�C�Y�Q�ƍ���()���R�[�����A�����J�݌����q�e�[�u����<BR>
     *�������̔z����擾����B<BR>
     *<BR>
     *[get�J�X�^�}�C�Y�Q�ƍ���()�Ɏw�肷�����]<BR>
     *�`�[�ʔԁF�@@�����J�ݓ`�[�}�X�^�s[index].�`�[�ʔ�<BR>
     *�`�[�o�͍��ڕ������F�@@�i��1 �����ғo�^�`�[�iG9801�j�L���[�e�[�u����<BR>
     *�����Ώۍ��ځj<BR>
     *�`�[�Q�ƍ��ڏ����l�F�@@�i��2 �����ғo�^�`�[�iG9801�j�L���[�e�[�u����<BR>
     *�����Ώۍ��ڃf�t�H���g�ݒ�l�j<BR>
     *<BR>
     *�i��2�j�@@�����ғo�^�`�[�iG9801�j�L���[�e�[�u���̏����Ώۍ��ڃf�t�H���g�ݒ�l<BR>
     *DB���C�A�E�g �u�����ғo�^�`�[�iG9801�j�L���[�e�[�u��.xls#�f�t�H���gDB�ݒ�_���v<BR>
     *�V�[�g���Q�Ƃ��A<BR>
     *�Y�����ڂ̐������ɁA�����J�݌����q�e�[�u������ҏW����<BR>
     *�w�肪����΁A�w�荀�ڂ̗񕨗����z��B<BR>
     *�ȊO�́Anull�B<BR>
     *<BR>
     *�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�<BR>
     *�`�[�g�p����Table�i�FHashtable�j.put()�ɂ�this.get�J�X�^�}�C�Y�Q�ƍ���()<BR>
     *�߂�l����v�f���ǉ�����B<BR>
     *<BR>
     *[put()�Ɏw�肷�����]<BR>
     *key�F�@@this.get�J�X�^�}�C�Y�Q�ƍ���()[n]<BR>
     *value�F�@@this.get�J�X�^�}�C�Y�Q�ƍ���()[n]<BR>
     *<BR>
     *�� key�Cvalue�ɓ����l���Z�b�g����B<BR>
     *<BR>
     *�R�j�@@���ږ��z��ԋp<BR>
     *�`�[�g�p����Table�i�FHashtable�j.values()�@@��ԋp����B<BR>
     *@@return String[]<BR>
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
        }
        for (int i = 0; i < l_intLength; i++)
        {
            log.debug("�����J�ݓ`�[�}�X�^�s[]�̊e�v�f���ɁA�Q�|�P�j�`�Q�|�R�j�̏��������{����B");
            String l_strSerialNo = accOpenVoucherMasterParamses[i].getSerialNo();
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
                    log.debug("****************�،���ЃR�[�h");
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
                    log.debug("**********************�f�[�^�R�[�h");
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
                    log.debug("*****************���X�R�[�h");
                    if (l_strCustomizingRefItems[j] != null)
                    {
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
                    log.debug("*****************�ڋq�R�[�h");
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
                    log.debug("*****************���҃R�[�h");
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
                    log.debug("******************���ʃR�[�h�i�����J�݌����q�j");
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
                    log.debug("***********************�`�[�ʔ�");
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
                    log.debug("**********************�o�^�敪");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //�����R�[�h!!!!!!!!
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_PRODUCT_CODE;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.PRODUCT_CODE, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("********************�����R�[�h");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //�֌W�敪
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_RELATION_DIV;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.RELATION_DIV, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("**********************�֌W�敪");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //������
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_OFFICER_NAME;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.OFFICER_NAME, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("********************������");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //��E���R�[�h
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_POST_CODE;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.POST_CODE, l_strValues);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    log.debug("*********************��E���R�[�h");
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //��E��
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_POST_NAME;
                 l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                     WEB3AccountOpenOutputItemSymbolNameDef.POST_NAME, l_strValues);  
                 //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                 l_int = 0;
                 if (l_strCustomizingRefItems != null)
                 {
                     l_int = l_strCustomizingRefItems.length;
                 }
                 for (int j = 0; j < l_int; j++)
                 {
                     log.debug("**********************��E��");
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
                    log.debug("**********************�����敪");
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
     *�P�j�@@�f�t�H���g�ݒ�s����<BR>
     *�����ғo�^�`�[�iG9801�j�L���[�e�[�u���s�I�u�W�F�N�g�𐶐����A<BR>
     *�f�t�H���g�ݒ�i��1�j�̒ʂ�Ƀv���p�e�B���Z�b�g����B<BR>
     *<BR>
     *��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A<BR>
     *�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�B<BR>
     *<BR>
     *�i��1�j�@@�����ғo�^�`�[�iG9801�j�L���[�e�[�u���̏����Ώۍ��ڃf�t�H���g�ݒ�<BR>
     *DB���C�A�E�g<BR>
     *�u�����ғo�^�`�[�iG9801�j�L���[�e�[�u��.xls#�f�t�H���gDB�ݒ�_���v<BR>
     *�V�[�g�Q�ƁB<BR>
     *<BR>
     *�Q�j�@@�J�X�^�}�C�Y���ڃZ�b�g <BR>
     *�����J�ݓ`�[���ڃ}�X�^�e�[�u�����ȉ��̏����@@�Ō�������B <BR>
     *�Y���s���Ȃ��ꍇ�A�����A�Ō�������B <BR>
     *<BR>
     *[�����@@] <BR>
     *�����J�ݓ`�[���ڃ}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And <BR>
     *�����J�ݓ`�[���ڃ}�X�^.���X�R�[�h = this.get���X�R�[�h() And <BR>
     *�����J�ݓ`�[���ڃ}�X�^.�����敪 = this.get�����敪() And <BR>
     *�����J�ݓ`�[���ڃ}�X�^.�f�[�^�R�[�h = this.get�f�[�^�R�[�h() And <BR>
     *�����J�ݓ`�[���ڃ}�X�^.�`�[�ʔ� = �`�[�ʔ� <BR>
     *<BR>
     *[�����A] <BR>
     *�����J�ݓ`�[���ڃ}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And <BR>
     *�����J�ݓ`�[���ڃ}�X�^.���X�R�[�h = "000" And <BR>
     *�����J�ݓ`�[���ڃ}�X�^.�����敪 = this.get�����敪() And <BR>
     *�����J�ݓ`�[���ڃ}�X�^.�f�[�^�R�[�h = this.get�f�[�^�R�[�h() And <BR>
     *�����J�ݓ`�[���ڃ}�X�^.�`�[�ʔ� = �`�[�ʔ� <BR>
     *<BR>
     *�����@@�C�A�̂ǂ��炩�ŊY���s������ꍇ�́A�Q�|�P�j�̏��������{����B <BR>
     *<BR>
     *�Q�|�P�j�@@�J�X�^�}�C�Y�ҏW<BR>
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
     *�@@�@@���͍��ڕ������P�C�Q�C�R�̒l���f���~�b�^�ɂĘA������B<BR>
     *<BR>
     *�@@(��2) DB���C�A�E�g�u�����J�ݓ`�[���ڃ}�X�^�v�Q�ƁB<BR>
     *<BR>
     *�Q�|�Q�j�@@�`�[�̎��ʃR�[�h�V�K�̔�<BR>
     *�@@�������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h()�ɂĎ��ʃR�[�h���擾���A<BR>
     *�@@�s�I�u�W�F�N�g�̎��ʃR�[�h�iorder_request_number�j�ɃZ�b�g����B<BR>
     *<BR>
     *�@@[get�V�K���ʃR�[�h()�Ɏw�肷�����]<BR>
     *�@@�،���ЃR�[�h�F�@@this.get�،���ЃR�[�h()<BR>
     *�@@���X�R�[�h�F�@@this.get���X�R�[�h()<BR>
     *�@@�����^�C�v�F�@@ProductTypeEnum.���̑�<BR>
     *<BR>
     *�R�j�@@DB�X�V<BR>
     *�R�|�P�j�@@�����s�폜<BR>
     *�@@�ȉ��̏����ɂē����ғo�^�`�[�iG9801�j�L���[�e�[�u������������B<BR>
     *�@@�Y���s�����ɑ��݂���ꍇ�́A�Y���s��delete����B<BR>
     *<BR>
     *�@@[����]<BR>
     *�@@�،���ЃR�[�h =  this.get�،���ЃR�[�h() And<BR>
     *�@@���ʃR�[�h = this.get���ʃR�[�h() And<BR>
     *�@@�`�[�ʔ� = �`�[�ʔ� And<BR>
     *�@@�����敪 = �h�������h<BR>
     *<BR>
     *�R�|�Q�j�@@�`�[�s�}��<BR>
     *�@@�P�j�`�Q�j�ŕҏW�����s�I�u�W�F�N�g��DB�ɍX�V�iDB-insert����j�B<BR>
     *@@param l_strSerialNo - (�`�[�ʔ�)<BR>
     *�`�[�ʔ�<BR>
     *@@throws WEB3BaseException
     */
    public void saveVoucherRow(String l_strSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveVoucherRow(String)";
        log.entering(STR_METHOD_NAME);
        //�f�t�H���g�ݒ�s���� 
        HostInsiderRegVoucherParams l_hostInsiderRegVoucherParams = new HostInsiderRegVoucherParams();
        
        //�f�t�H���g�ݒ�i��1�j�̒ʂ�Ƀv���p�e�B���Z�b�g����B
        try
        {             
            String l_strInstitutionCode = this.accOpenExpAccountOpen.getInstitutionCode();
            String l_strBranchCode = this.accOpenExpAccountOpen.getBranchCode();
            String l_strAccountCode = this.accOpenExpAccountOpen.getAccountCode();
            String l_strRequestNumber = this.accOpenExpAccountOpen.getRequestNumber();

            log.debug("�f�t�H���g�ݒ�i��1�j�̒ʂ�Ƀv���p�e�B���Z�b�g����");
            //�f�[�^�R�[�h
            l_hostInsiderRegVoucherParams.setRequestCode(WEB3HostRequestCodeDef.ACCOPEN_INSIDER_REG_VOUCHER);
            //�،���ЃR�[�h
            l_hostInsiderRegVoucherParams.setInstitutionCode(
            super.getStringByByteNumber(l_strInstitutionCode, 3));
            //���X�R�[�h                    
            l_hostInsiderRegVoucherParams.setBranchCode(
            super.getStringByByteNumber(l_strBranchCode, 3));
            //�ڋq�R�[�h
            l_hostInsiderRegVoucherParams.setAccountCode(
            super.getStringByByteNumber(l_strAccountCode, 7));
            
            //���҃R�[�h
            ExpAccountOpenRow l_expAccountOpenRow = (ExpAccountOpenRow)this.accOpenExpAccountOpen.getDataSourceObject();
            String l_strSonarTraderCode = l_expAccountOpenRow.getSonarTraderCode();
            l_hostInsiderRegVoucherParams.setTraderCode(super.getStringByByteNumber(l_strSonarTraderCode, 5));
            
            //���ʃR�[�h�i�����J�݌����q�j
            l_hostInsiderRegVoucherParams.setAccOpenRequestNumber(
            super.getStringByByteNumber(l_strRequestNumber, 13));
            
            //�`�[�ʔ�
            l_hostInsiderRegVoucherParams.setSerialNo(l_strSerialNo);
            
            //�o�^�敪
            l_hostInsiderRegVoucherParams.setRegistDiv(WEB3RegDivDef.NEW);
            
            //�����R�[�h
            l_hostInsiderRegVoucherParams.setProductCode(
            super.getStringByByteNumber(l_expAccountOpenRow.getInsiderProductCode(),5));
            
            //�֌W�敪
            l_hostInsiderRegVoucherParams.setRelationDiv(
            super.getStringByByteNumber(l_expAccountOpenRow.getInsiderRelationDiv(), 1));
            
            //������
//            l_hostInsiderRegVoucherParams.setOfficerName(
//                    super.getStringByByteNumber(l_expAccountOpenRow.getInsiderOfficerName(), 19));
            l_hostInsiderRegVoucherParams.setOfficerName(
                    super.getEmStringByByteNumber(l_expAccountOpenRow.getInsiderOfficerName(), 19));
            
            //��E���R�[�h
            l_hostInsiderRegVoucherParams.setPostCode(
            super.getStringByByteNumber(l_expAccountOpenRow.getInsiderPostCode(), 2));
            
            //��E��
//            l_hostInsiderRegVoucherParams.setPostName(
//                    super.getStringByByteNumber(l_expAccountOpenRow.getInsiderPostName(), 20));
            l_hostInsiderRegVoucherParams.setPostName(
                    super.getEmStringByByteNumber(l_expAccountOpenRow.getInsiderPostName(), 20));
               
            //�����敪   
            l_hostInsiderRegVoucherParams.setStatus(WEB3StatusDef.NOT_DEAL);

            
            Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
            //���M����
            l_hostInsiderRegVoucherParams.setSendTimestamp(null);
            //�쐬����
            l_hostInsiderRegVoucherParams.setCreatedTimestamp(l_tsSystemTimestamp);
            //�X�V����
            l_hostInsiderRegVoucherParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);
            
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

            String l_strRequestCode = this.getRequestCode();
            
            Object[] l_objBindVarsItem =
                {this.getInstitutionCode(),
                 this.getBranchCode(),
                 this.getAccountDiv(),
                 l_strRequestCode,
                 l_strSerialNo};
                    
            List l_lisRowItems =
                l_queryProcesser.doFindAllQuery(
                    AccOpenVoucherItemRow.TYPE,
                    l_strWhereItem,
                    l_objBindVarsItem);                          //DataQueryException, DataNetworkException
                     
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
                    }
                    else
                    {                      
                        log.debug("���͍��ڕ������P�C�Q�C�R�̒l���f���~�b�^�ɂĘA������B");
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
                    log.debug("********************l_strValue = " + l_strValue);
                    
                    //�f�[�^�R�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.REQUEST_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("&&&&&&&&&&&&&&&&&&&&&&&&�f�[�^�R�[�h");
                        l_hostInsiderRegVoucherParams.setRequestCode(super.getStringByByteNumber(l_strValue, 5));   
                    }
                        
                    //�،���ЃR�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("&&&&&&&&&&&&&&&&&&&&&&&&�،���ЃR�[�h");
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostInsiderRegVoucherParams.setInstitutionCode(super.getStringByByteNumber(l_strValue, 3));    
                    }
                        
                    //���X�R�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.BRANCH_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("&&&&&&&&&&&&&&&&&&&&&&&&���X�R�[�h");
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostInsiderRegVoucherParams.setBranchCode(super.getStringByByteNumber(l_strValue, 3));     
                    }
                        
                    //�ڋq�R�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("&&&&&&&&&&&&&&&&&&&&&&&&�ڋq�R�[�h");
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostInsiderRegVoucherParams.setAccountCode(super.getStringByByteNumber(l_strValue, 7));  
                    }
                        
                    //���҃R�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.TRADER_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("&&&&&&&&&&&&&&&&&&&&&&&&���҃R�[�h");
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostInsiderRegVoucherParams.setTraderCode(super.getStringByByteNumber(l_strValue, 5));     
                    }
                        
                    //���ʃR�[�h�i�����J�݌����q�j
                    if (WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("&&&&&&&&&&&&&&&&&&&&&&&&���ʃR�[�h�i�����J�݌����q�j");
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostInsiderRegVoucherParams.setAccOpenRequestNumber(super.getStringByByteNumber(l_strValue, 13));     
                    }
                        
                    //�`�[�ʔ�
                    if (WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&�`�[�ʔ�");
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostInsiderRegVoucherParams.setSerialNo(super.getStringByByteNumber(l_strValue, 3));    
                    }
                        
                    //�o�^�敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("&&&&&&&&&&&&&&&&&&&&&&�o�^�敪");
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostInsiderRegVoucherParams.setRegistDiv(super.getStringByByteNumber(l_strValue, 1));     
                    }
                        
                    //�����R�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.PRODUCT_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&�����R�[�h");
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostInsiderRegVoucherParams.setProductCode(super.getStringByByteNumber(l_strValue, 5));    
                    }
                        
                    //�֌W�敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.RELATION_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&�֌W�敪");
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostInsiderRegVoucherParams.setRelationDiv(super.getStringByByteNumber(l_strValue, 1));    
                    }
                        
                    //������
                    if (WEB3AccountOpenOutputItemSymbolNameDef.OFFICER_NAME.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&������");
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
//                        l_hostInsiderRegVoucherParams.setOfficerName(super.getStringByByteNumber(l_strValue, 19));     
                        l_hostInsiderRegVoucherParams.setOfficerName(super.getEmStringByByteNumber(l_strValue, 19));     
                    }
                        
                    //��E���R�[�h
                    if (WEB3AccountOpenOutputItemSymbolNameDef.POST_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&��E���R�[�h");
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostInsiderRegVoucherParams.setPostName(super.getStringByByteNumber(l_strValue, 2));    
                    }
                        
                    //��E��
                    if (WEB3AccountOpenOutputItemSymbolNameDef.POST_NAME.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&��E��");
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
//                        l_hostInsiderRegVoucherParams.setPostName(super.getStringByByteNumber(l_strValue, 20));
                        l_hostInsiderRegVoucherParams.setPostName(super.getEmStringByByteNumber(l_strValue, 20));
    
                    }
                        
                    //�����敪
                    if (WEB3AccountOpenOutputItemSymbolNameDef.STATUS.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        log.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&�����敪");
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostInsiderRegVoucherParams.setStatus(super.getStringByByteNumber(l_strValue, 1));
     
                    }    
                }
            }
            
            //�Q�|�Q�j�@@�`�[�̎��ʃR�[�h�V�K�̔�
            log.debug("�Q�|�Q�j�@@�`�[�̎��ʃR�[�h�V�K�̔� "); 
            WEB3HostReqOrderNumberManageService l_reqOrderNumberManageService=
                (WEB3HostReqOrderNumberManageService)Services.getService(
                    WEB3HostReqOrderNumberManageService.class);    
            String l_strNewNumber = l_reqOrderNumberManageService.getNewNumber(
            this.getInstitutionCode(),
            this.getBranchCode(),
                ProductTypeEnum.OTHER);
            l_hostInsiderRegVoucherParams.setOrderRequestNumber(l_strNewNumber);
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
                    
            log.debug("�R�|�P�j�@@�����s�폜");
            l_queryProcesser.doDeleteAllQuery(
            HostInsiderRegVoucherParams.TYPE,
                l_strWhere,
                l_bindVars);                      //DataQueryException, DataNetworkException
            
            //�R�|�Q�j�@@�`�[�s�}��
            log.debug("�R�|�Q�j�@@�`�[�s�}��"); 
            l_queryProcesser.doInsertQuery(l_hostInsiderRegVoucherParams);  
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
     *�����ғo�^�`�[�iG9801�j�L���[�e�[�u���̈ȉ��̏����ɓ��Ă͂܂�s���擾����B<BR>
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
            
            Object l_bindVars[] =
                {l_strInstitutionCode,
                 l_strRequestNumber,
                 l_strSerialNo,
                 WEB3StatusDef.NOT_DEAL};
                    
            List l_lisRows = l_queryProcesser.doFindAllQuery(
            HostInsiderRegVoucherParams.TYPE,
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
                HostInsiderRegVoucherParams.TYPE,
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
     *�����J�݌����q�I�u�W�F�N�g���w�肵�A�����ғo�^�`�[�C���X�^���X���쐬����B<BR>
     *<BR>
     *�P�j�@@�C���X�^���X���� <BR>
     *�����ғo�^�`�[�C���X�^���X�𐶐�����B <BR>
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
     *�����J�݌����q�I�u�W�F�N�g<BR>
     *@@return WEB3AccOpenInsiderRegVoucher<BR>
     *@@roseuid 4191CD1F0033<BR>
     *@@throws WEB3BaseException
     */
    public static WEB3AccOpenInsiderRegVoucher getInstance(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInstance(WEB3AccOpenExpAccountOpen)";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@�C���X�^���X����
        WEB3AccOpenInsiderRegVoucher l_accOpenInsiderRegVoucher = new WEB3AccOpenInsiderRegVoucher();
        
        //�Q�j�@@�����J�݌����q�v���p�e�B�Z�b�g
        l_accOpenInsiderRegVoucher.setAccOpenExpAccountOpen(l_accOpenExpAccountOpen);
        
        //�R�j�@@�����J�ݓ`�[�}�X�^�v���p�e�B�Z�b�g
        l_accOpenInsiderRegVoucher.setAccOpenVoucherMaster();

        log.exiting(STR_METHOD_NAME);
        return l_accOpenInsiderRegVoucher;
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


     /**
      *(get���[�U�f�[�^)<BR>
      *(get���[�U�f�[�^()��override)<BR>
      *<BR>
      * ���[�U�f�[�^�̈�ɃZ�b�g���鍀�ڂ��擾����B<BR>
      * �o�^�敪.1�F�V�K�@@��ԋp����B <BR>
      *@@return String
      */
     public String getUserData() 
     {
         final String STR_METHOD_NAME = " getUserData()";
         log.entering(STR_METHOD_NAME);
         
         log.exiting(STR_METHOD_NAME);
         return WEB3RegDivDef.NEW;
     }


}
@
