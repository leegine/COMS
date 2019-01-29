head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.30.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenRealNameRegVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋq�������̓o�^�`�[(WEB3AccOpenRealNameRegVoucher.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2006/07/07 ����� �V�K�쐬
                      2006/07/13 ����� ����̍X�E���f��077 �A079
                      2006/07/18 ����� ����̍X�E���f��081
                      2006/10/12 �ęԍg �c�a���C�A�E�g  No.038
                      2007/06/18 ���� (SCS) �����̖�� No.006
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
import webbroker3.accountopen.data.HostRealnameRegVoucherParams;
import webbroker3.accountopen.data.HostRealnameRegVoucherRow;
import webbroker3.accountopen.define.WEB3AccountOpenExpAccountOpenSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenOutputItemSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenVoucherCodeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CatDelimitterDef;
import webbroker3.common.define.WEB3EditWayDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3RegDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�ڋq�������̓o�^�`�[)<BR>
 * �ڋq�������̓o�^�`�[<BR>
 * 
 * @@author �����
 * @@version 1.0 
 */
public class WEB3AccOpenRealNameRegVoucher extends WEB3AccOpenVoucher
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AccOpenRealNameRegVoucher.class);
                
    /**
     * @@roseuid 41B45E6E0148
     */
    public WEB3AccOpenRealNameRegVoucher()
    {

    }    
    
    /**
     *�����J�݌����q�I�u�W�F�N�g���w�肵�A�ڋq�������̓o�^�`�[�C���X�^���X���쐬����B<BR> 
     *<BR>
     *�P�j�C���X�^���X����<BR> �@@
     *�@@�ڋq�������̓o�^�`�[�C���X�^���X�𐶐����� �B<BR>
     *<BR>
     *�Q�j�����J�݌����q�v���p�e�B�Z�b�g<BR> �@@
     *�@@���������C���X�^���X.set�����J�݌����q() �ɂ� �A<BR>
     *�@@�����J�݌����q�v���p�e�B���Z�b�g���� �B<BR>
     *<BR>
     *�@@[set�����J�݌����q()�Ɏw�肷�����]<BR> 
     *�@@�����J�݌����q�F�@@�����J�݌����q<BR> 
     *<BR>
     *�R�j�����J�ݓ`�[�}�X�^�v���p�e�B�Z�b�g 
     *�@@���������C���X�^���X.set�`�[�}�X�^()�ɂāA�`�[�}�X�^�s�v���p�e�B���Z�b�g����B<BR> 
     *<BR>
     *�S�j���������C���X�^���X��ԋp����B<BR>
     *@@param l_accOpenExpAccountOpen - (�����J�݌����q)<BR>
     *�����J�݌����q�I�u�W�F�N�g<BR>
     *@@return WEB3AccOpenRealNameRegistVoucher 
     *@@throws WEB3BaseException
     *@@roseuid 41930C8C0381 
     */
    public static WEB3AccOpenRealNameRegVoucher getInstance(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInstance(WEB3AccOpenExpAccountOpen)";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@�C���X�^���X����
        WEB3AccOpenRealNameRegVoucher l_accOpenRealNameRegVoucher = new WEB3AccOpenRealNameRegVoucher();
        
        //�Q�j�@@�����J�݌����q�v���p�e�B�Z�b�g
        l_accOpenRealNameRegVoucher.setAccOpenExpAccountOpen(l_accOpenExpAccountOpen);
        
        //�R�j�@@�����J�ݓ`�[�}�X�^�v���p�e�B�Z�b�g
        l_accOpenRealNameRegVoucher.setAccOpenVoucherMaster();

        log.exiting(STR_METHOD_NAME);
        return l_accOpenRealNameRegVoucher;
    }
    
    /**
     *(is�I�����C���`�[)<BR>
     *(is�I�����C���`�[()�̎����j<BR>
     *<BR>
     *true��ԋp����B<BR>
     *@@return boolean
     *@@roseuid 41930C8C0383
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
     *(get�f�[�^�R�[�h()�̎����j<BR>
     *<BR>
     *�f�[�^�R�[�h.�ڋq�������̓o�^�`�[�iGI848�j��ԋp����B<BR>
     *@@return String
     *@@roseuid 41930C8C0384
     */
    public String getRequestCode()
    {
        final String STR_METHOD_NAME = " getRequestCode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return WEB3HostRequestCodeDef.ACCOPEN_REALNAME_REG_VOUCHER;
    }
    
    /**
     *(get�`�[�R�[�h)<BR>
     *(get�`�[�R�[�h()�̎����j<BR>
     *<BR>
     *�hG1190�h��ԋp����B<BR>
     *@@return String
     *@@roseuid 419DDF6E021A
     */
    public String getVoucherCode()
    {
        final String STR_METHOD_NAME = " getVoucherCode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return WEB3AccountOpenVoucherCodeDef.REAL_NAME_REG_VOUCHER_CODE;
    }
    
    /**
     *(get�m��ύ��ږ�)<BR>
     *(get�m��ύ��ږ�()�̎����j<BR>
     *<BR>
     *���Y�`�[�Ŏg�p���Ă�������J�݌����q�̗񕨗�����z��Ŏ擾����B<BR> 
     *<BR>
     *�P�j�`�[�g�p����Table�i�FHashtable�j����<BR> 
     *�@@Hashtable�𐶐�����B<BR> 
     *<BR>
     *�Q�j�g�p���ڃZ�b�g<BR> 
     *<BR>
     *�@@this.�����J�ݓ`�[�}�X�^�s[]�̊e�v�f���ɁA�Q�|�P�j�`�Q�|�R�j�̏��������{����B<BR> 
     *<BR>
     *�Q�|�P�j�쐬�\����<BR> 
     *�@@is�쐬�\�`�[()�ɂāA�쐬�\�ȓ`�[���𔻒肷��B<BR> 
     *<BR>
     *�@@[is�쐬�\�`�[()�Ɏw�肷�����]<BR> 
     *�@@�`�[�ʔԁF�@@�����J�ݓ`�[�}�X�^�s[index].�`�[�ʔ�<BR> 
     *<BR>
     *�@@�쐬�\�łȂ��ꍇ�iis�쐬�\�`�[() == false�j�̂݁A�Q�|�Q�j�����{����B<BR> 
     *�@@�쐬�\�ȏꍇ�iis�쐬�\�`�[() == true�j�A���Y�v�f�̏������s�킸����<BR>
     *�@@�v�f����������B�icontinue;�j<BR> 
     *<BR>
     *�@@���쐬�\�ȏꍇ�A���ڒl�͕ύX���Ă��ǂ��̂œ`�[�Q�ƍ��ږ��ɂ͊܂߂Ȃ��B<BR> 
     *<BR>
     *�Q�|�Q�j�J�X�^�}�C�Y�Q�ƍ��ڗ񕨗����擾<BR>
     *�@@�ڋq�������̓o�^�`�[�iG1190�j�L���[�e�[�u���̊e���ڂɂ��āA���L�̏��������{����B<BR> 
     *<BR>
     *�@@this.get�J�X�^�}�C�Y�Q�ƍ���()���R�[�����A�����J�݌����q�e�[�u���񕨗�����<BR>
     *�@@�z����擾����B<BR> 
     *<BR>
     *�@@[get�J�X�^�}�C�Y�Q�ƍ���()�Ɏw�肷�����]<BR> 
     *�@@�`�[�ʔԁF�@@�����J�ݓ`�[�}�X�^�s[index].�`�[�ʔ�<BR>
     *�@@�`�[�o�͍��ڕ������F�@@�i��1 �ڋq�������̓o�^�`�[�iG1190�j�L���[�e�[�u����<BR>
     *�@@�����Ώۍ��ځj<BR> 
     *�@@�`�[�Q�ƍ��ڏ����l�F�@@�i��2 �ڋq�������̓o�^�`�[�iG1190�j�L���[�e�[�u����<BR>
     *�@@�����Ώۍ��ڃf�t�H���g�ݒ�l�j<BR> 
     *<BR>
     *�@@�i��2�j�@@�ڋq�������̓o�^�`�[�iG1190�j�L���[�e�[�u���̏����Ώۍ��ڃf�t�H���g�ݒ�l<BR> 
     *�@@DB���C�A�E�g �u�ڋq�������̓o�^�`�[�iG1190�j�L���[�e�[�u��.xls#<BR>
     *�@@�f�t�H���gDB�ݒ�_���v�V�[�g���Q�Ƃ��A<BR>
     *�@@�Y�����ڂ̐������ɁA�����J�݌����q�e�[�u������ҏW����w�肪����΁A<BR>
     *�@@�w�荀�ڂ̗񕨗����z��B<BR>
     *�@@�ȊO�́Anull�B<BR>
     *<BR>
     *�Q�|�R�j�`�[�g�p����Table�i�FHashtable�j�ɒǉ�<BR>
     *�@@�`�[�g�p����Table�i�FHashtable�j.put()�ɂ�this.get�J�X�^�}�C�Y�Q�ƍ���()�߂�l��<BR>
     *�@@��v�f���ǉ�����B<BR> 
     *<BR>
     *�@@[put()�Ɏw�肷�����]<BR>
     *�@@key�F�@@this.get�J�X�^�}�C�Y�Q�ƍ���()[n]<BR> 
     *�@@value�F�@@this.get�J�X�^�}�C�Y�Q�ƍ���()[n]<BR> 
     *<BR>
     *�@@�� key�Cvalue�ɓ����l���Z�b�g����B<BR> 
     *<BR>
     *�R�j���ږ��z��ԋp<BR> 
     *�@@�`�[�g�p����Table�i�FHashtable�j.values()�@@��ԋp����B<BR> 
     *@@return String[]
     *@@throws WEB3BaseException
     *@@roseuid 41930C8C0392
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

                //�،���ЃR�[�h:�����J�݌����q.�،���ЃR�[�h��ҏW����B
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
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }                              
                
                //�f�[�^�R�[�h:null
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

                //���X�R�[�h:�����J�݌����q.���X�R�[�h��ҏW����B
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
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }

                //�ڋq�R�[�h:�����J�݌����q.�����R�[�h��ҏW����B
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
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }

                //���҃R�[�h:�����J�݌����q.���҃R�[�h�iSONAR�j��ҏW����B
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
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }

                //���ʃR�[�h�i�����J�݌����q�j:�����J�݌����q.���ʃR�[�h��ҏW����B
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
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                
                //�`�[�ʔ�:null
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
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }
                               
                //�o�^�敪:null
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
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }                
              
                //�ڋq����(1):�����J�݌����q.�ڋq�������̂P
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.REAL_NAME1;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.REAL_NAME1, null);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }                 
       
                //�ڋq����(2):�����J�݌����q.�ڋq�������̂Q
                l_strValues[0] = WEB3AccountOpenExpAccountOpenSymbolNameDef.REAL_NAME2;
                l_strCustomizingRefItems = this.getCustomizingRefItem(l_strSerialNo, 
                    WEB3AccountOpenOutputItemSymbolNameDef.REAL_NAME2, null);  
                //�Q�|�R�j�@@�`�[�g�p����Table�i�FHashtable�j�ɒǉ�
                l_int = 0;
                if (l_strCustomizingRefItems != null)
                {
                    l_int = l_strCustomizingRefItems.length;
                }
                for (int j = 0; j < l_int; j++)
                {
                    if (l_strCustomizingRefItems[j] != null)
                    {
                        l_voucherItemList.put(l_strCustomizingRefItems[j], l_strCustomizingRefItems[j]);
                    }
                }       
              
                //�����敪:null
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
     *(save�`�[�s()�̎����j<BR>
     *�@@�����J�ݓ`�[���P���o�^����B<BR> 
     *<BR>
     *�P�j�f�t�H���g�ݒ�s����<BR> 
     *�@@�ڋq�������̓o�^�`�[�iG1190�j�L���[�e�[�u���s�I�u�W�F�N�g�𐶐����A<BR> 
     *�@@�f�t�H���g�ݒ�i��1�j�̒ʂ�Ƀv���p�e�B���Z�b�g����B<BR> 
     *<BR>
     *�@@��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A<BR>
     *�@@�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�B<BR>
     *<BR>
     *�@@�i��1�j�@@�ڋq�������̓o�^�`�[�iG1190�j�L���[�e�[�u���̏����Ώۍ��ڃf�t�H���g�ݒ�<BR> 
     *�@@DB���C�A�E�g �u�ڋq�������̓o�^�`�[�iG1190�j�L���[�e�[�u��.xls#<BR>
     *�@@�f�t�H���gDB�ݒ�_���v�V�[�g�Q�ƁB<BR> 
     *<BR>
     *�Q�j�J�X�^�}�C�Y���ڃZ�b�g<BR> 
     *�@@�����J�ݓ`�[���ڃ}�X�^�e�[�u�����ȉ��̏����@@�Ō�������B<BR> 
     *�@@�Y���s���Ȃ��ꍇ�A�����A�Ō�������B<BR> 
     *<BR>
     *�@@[�����@@]<BR> 
     *�@@�����J�ݓ`�[���ڃ}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And<BR> 
     *�@@�����J�ݓ`�[���ڃ}�X�^.���X�R�[�h = this.get���X�R�[�h() And<BR> 
     *�@@�����J�ݓ`�[���ڃ}�X�^.�����敪 = this.get�����敪() And<BR> 
     *�@@�����J�ݓ`�[���ڃ}�X�^.�f�[�^�R�[�h = this.get�f�[�^�R�[�h() And<BR> 
     *�@@�����J�ݓ`�[���ڃ}�X�^.�`�[�ʔ� = �`�[�ʔ�<BR> 
     *<BR>
     *�@@[�����A]<BR> 
     *�@@�����J�ݓ`�[���ڃ}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And<BR> 
     *�@@�����J�ݓ`�[���ڃ}�X�^.���X�R�[�h = "000" And<BR> 
     *�@@�����J�ݓ`�[���ڃ}�X�^.�����敪 = this.get�����敪() And<BR> 
     *�@@�����J�ݓ`�[���ڃ}�X�^.�f�[�^�R�[�h = this.get�f�[�^�R�[�h() And<BR> 
     *�@@�����J�ݓ`�[���ڃ}�X�^.�`�[�ʔ� = �`�[�ʔ�<BR> 
     *<BR>
     *�@@�����@@�C�A�̂ǂ��炩�ŊY���s������ꍇ�́A�Q�|�P�j�̏��������{����B<BR> 
     *<BR>
     *�Q�|�P�j�J�X�^�}�C�Y�ҏW<BR> 
     *�@@�������ʂ̊e�s���ɁA�o�͍��ڕ������������`�[���ڂ̒l���A�w��̕��@@�ōăZ�b�g����B<BR> 
     *�@@��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A<BR>
     *�@@�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�B<BR> 
     *<BR>
     *�@@�� �i���ڕҏW���@@ == �Œ�l�j�̏ꍇ�A�Œ�Z�b�g�l�̒l���Z�b�g����B<BR> 
     *�@@�� �ȊO�̏ꍇ�A���͍��ڕ������P�`�R�����������J�݌����q�̍��ڒl(��2)���Z�b�g����B<BR> 
     *�@@�|���͍��ڕ������P�`�R��null�̏ꍇ�́A<BR> 
     *�@@�|�A�����ڃf���~�b�^���w�肳��Ă���ꍇ�i�A�����ڃf���~�b�^ != null�j�A<BR> 
     *�@@���͍��ڕ������P�C�Q�C�R�̒l���f���~�b�^�ɂĘA������B<BR> 
     *<BR>
     *�@@(��2) DB���C�A�E�g�u�����J�ݓ`�[���ڃ}�X�^�v�Q�ƁB<BR> 
     *<BR>
     *�Q�|�Q�j�`�[�̎��ʃR�[�h�V�K�̔�<BR>
     *�@@�������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h()�ɂĎ��ʃR�[�h���擾���A<BR> 
     *�@@�s�I�u�W�F�N�g�̎��ʃR�[�h�iorder_request_number�j�ɃZ�b�g����B<BR> 
     * <BR>
     *�@@[get�V�K���ʃR�[�h()�Ɏw�肷�����]<BR> 
     *�@@�،���ЃR�[�h�F�@@this.get�،���ЃR�[�h()<BR> 
     *�@@���X�R�[�h�F�@@this.get���X�R�[�h()<BR> 
     *�@@�����^�C�v�F�@@ProductTypeEnum.���̑�<BR>
     *<BR>
     *�R�jDB�X�V<BR> 
     *�R�|�P�j�����s�폜<BR> 
     *�@@�ȉ��̏����ɂČڋq�������̓o�^�`�[�iG1190�j�L���[�e�[�u������������B<BR> 
     *�@@�Y���s�����ɑ��݂���ꍇ�́A�Y���s��delete����B<BR> 
     *<BR>
     *�@@[����]<BR>
     *�@@�،���ЃR�[�h =  this.get�،���ЃR�[�h() And<BR> 
     *�@@���ʃR�[�h = this.get���ʃR�[�h() And<BR> 
     *�@@�`�[�ʔ� = �`�[�ʔ� And<BR> 
     *�@@�����敪 = �h�������h<BR> 
     *�@@<BR>
     *�R�|�Q�j�`�[�s�}��<BR> 
     *�P�j�`�Q�j�ŕҏW�����s�I�u�W�F�N�g��DB�ɍX�V�iDB-insert����j�B<BR> 
     *@@param l_strSerialNo - (�`�[�ʔ�)<BR>
     *�`�[�ʔ�<BR>
     *@@throws WEB3BaseException
     *@@roseuid 41930C8C0393
     */
    public void saveVoucherRow(String l_strSerialNo) throws WEB3BaseException
    {   
        final String STR_METHOD_NAME = " saveVoucherRow(String)";
        log.entering(STR_METHOD_NAME);
        //�f�t�H���g�ݒ�s���� 
        HostRealnameRegVoucherParams l_hostRealnameRegVoucherParams = new HostRealnameRegVoucherParams();
        
        //�f�t�H���g�ݒ�i��1�j�̒ʂ�Ƀv���p�e�B���Z�b�g����B
        try
        {             
            String l_strInstitutionCode = this.accOpenExpAccountOpen.getInstitutionCode();
            String l_strBranchCode = this.accOpenExpAccountOpen.getBranchCode();
            String l_strAccountCode = this.accOpenExpAccountOpen.getAccountCode();
            String l_strRequestNumber = this.accOpenExpAccountOpen.getRequestNumber();

            //�f�[�^�R�[�h
            l_hostRealnameRegVoucherParams.setRequestCode(WEB3HostRequestCodeDef.ACCOPEN_REALNAME_REG_VOUCHER);
            //�،���ЃR�[�h
            l_hostRealnameRegVoucherParams.setInstitutionCode(
                super.getStringByByteNumber(l_strInstitutionCode, 3));
            //���X�R�[�h                    
            l_hostRealnameRegVoucherParams.setBranchCode(
                super.getStringByByteNumber(l_strBranchCode, 3));
            //�ڋq�R�[�h
            l_hostRealnameRegVoucherParams.setAccountCode(
                super.getStringByByteNumber(l_strAccountCode, 7));
            
            //���҃R�[�h
            ExpAccountOpenRow l_expAccountOpenRow = (ExpAccountOpenRow)this.accOpenExpAccountOpen.getDataSourceObject();
            String l_strSonarTraderCode = l_expAccountOpenRow.getSonarTraderCode();
            l_hostRealnameRegVoucherParams.setTraderCode(
                super.getStringByByteNumber(l_strSonarTraderCode, 5));
            
            //���ʃR�[�h�i�����J�݌����q�j
            l_hostRealnameRegVoucherParams.setAccOpenRequestNumber(
                super.getStringByByteNumber(l_strRequestNumber, 13));
            //�`�[�ʔ�
            l_hostRealnameRegVoucherParams.setSerialNo(l_strSerialNo);        
            //�o�^�敪
            l_hostRealnameRegVoucherParams.setRegistDiv(WEB3RegDivDef.NEW);          
            //�ڋq����(1)
//            l_hostRealnameRegVoucherParams.setRealName1(
//                    super.getStringByByteNumber(l_expAccountOpenRow.getRealName1(), 40));
            l_hostRealnameRegVoucherParams.setRealName1(
                    super.getEmStringByByteNumber(l_expAccountOpenRow.getRealName1(), 40));
            //�ڋq����(2)
//            l_hostRealnameRegVoucherParams.setRealName2(
//                super.getStringByByteNumber(l_expAccountOpenRow.getRealName2(), 40));           
            l_hostRealnameRegVoucherParams.setRealName2(
                    super.getEmStringByByteNumber(l_expAccountOpenRow.getRealName2(), 40));           
            //�����敪
            l_hostRealnameRegVoucherParams.setStatus(WEB3StatusDef.NOT_DEAL);
            Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
            //���M����
            l_hostRealnameRegVoucherParams.setSendTimestamp(null);
            //�쐬����
            l_hostRealnameRegVoucherParams.setCreatedTimestamp(l_tsSystemTimestamp);
            //�X�V����
            l_hostRealnameRegVoucherParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);
            
            //�Q�j�@@�J�X�^�}�C�Y���ڃZ�b�g
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException
            //[�����@@] 
            String l_strWhereItem =
                "institution_code = ? and " +        //�����J�ݓ`�[���ڃ}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() And 
                "branch_code = ? and " +             //�����J�ݓ`�[���ڃ}�X�^.���X�R�[�h = this.get���X�R�[�h() And  
                "account_div = ? and " +             //�����J�ݓ`�[���ڃ}�X�^.�����敪 = this.get�����敪() And  
                "request_code = ? and " +            //�����J�ݓ`�[���ڃ}�X�^.�f�[�^�R�[�h = this.get�f�[�^�R�[�h() And  
                "serial_no = ? ";                    //�����J�ݓ`�[���ڃ}�X�^.�`�[�ʔ� = �`�[�ʔ�

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
                    l_objBindVarsItem);        //DataQueryException, DataNetworkException
                     
            //�Y���s���Ȃ��ꍇ�A�����A�Ō�������B 
            if (l_lisRowItems == null || l_lisRowItems.size() == 0)
            {
                //[�����A]  
                Object[] l_objBindVarsItem2 =
                    {this.getInstitutionCode(),
                     "000",
                     this.getAccountDiv(),
                     this.getRequestCode(),
                     l_strSerialNo};
                    
                l_lisRowItems =
                    l_queryProcesser.doFindAllQuery(
                        AccOpenVoucherItemRow.TYPE,
                        l_strWhereItem,
                        l_objBindVarsItem2);        //DataQueryException, DataNetworkException    
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
                        l_hostRealnameRegVoucherParams.setRequestCode(super.getStringByByteNumber(l_strValue, 5));
                    }
                        
                    //�،���ЃR�[�h
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.INSTITUTION_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostRealnameRegVoucherParams.setInstitutionCode(super.getStringByByteNumber(l_strValue, 3));
                    }
                        
                    //���X�R�[�h
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.BRANCH_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostRealnameRegVoucherParams.setBranchCode(super.getStringByByteNumber(l_strValue, 3));
                    }
                        
                    //�ڋq�R�[�h
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACCOUNT_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostRealnameRegVoucherParams.setAccountCode(super.getStringByByteNumber(l_strValue, 7));
                    }
                        
                    //���҃R�[�h
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.TRADER_CODE.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostRealnameRegVoucherParams.setTraderCode(super.getStringByByteNumber(l_strValue, 5));
                    }
                        
                    //���ʃR�[�h�i�����J�݌����q�j
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.ACC_OPEN_REQUEST_NUMBER.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostRealnameRegVoucherParams.setAccOpenRequestNumber(super.getStringByByteNumber(l_strValue, 13));
                    }
                        
                    //�`�[�ʔ�
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.SERIAL_NO.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostRealnameRegVoucherParams.setSerialNo(super.getStringByByteNumber(l_strValue, 3));
                    }
                    
                    //�o�^�敪
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.REGIST_DIV.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostRealnameRegVoucherParams.setRegistDiv(super.getStringByByteNumber(l_strValue, 1));
                    }

                    //�ڋq����(1)
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.REAL_NAME1.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
//                        l_hostRealnameRegVoucherParams.setRealName1(super.getStringByByteNumber(l_strValue, 40));
                        l_hostRealnameRegVoucherParams.setRealName1(super.getStringByByteNumber(l_strValue, 40));
                    }                                    

                    //�ڋq����(2)
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.REAL_NAME2.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
//                        l_hostRealnameRegVoucherParams.setRealName2(super.getStringByByteNumber(l_strValue, 40));
                        l_hostRealnameRegVoucherParams.setRealName2(super.getEmStringByByteNumber(l_strValue, 40));
                    }
                        
                    //�����敪
                    else if (WEB3AccountOpenOutputItemSymbolNameDef.STATUS.equals(l_accOpenVoucherItemRow.getOutputItemSymbolName()))
                    {
                        //��������Z�b�g����ꍇ�A�o�͍��ڂ̃f�[�^�T�C�Y�𒴂����ꍇ�́A�f�[�^�T�C�Y�ȍ~�̕�����؂�̂Ă�
                        l_hostRealnameRegVoucherParams.setStatus(super.getEmStringByByteNumber(l_strValue, 1));
                    }    
                }
            }
            
            //�Q�|�Q�j�@@�`�[�̎��ʃR�[�h�V�K�̔� 
            WEB3HostReqOrderNumberManageService l_reqOrderNumberManageService=
                (WEB3HostReqOrderNumberManageService)Services.getService(
                    WEB3HostReqOrderNumberManageService.class);    
            String l_strNewNumber = l_reqOrderNumberManageService.getNewNumber(
                this.getInstitutionCode(),
                this.getBranchCode(),
                ProductTypeEnum.OTHER);
            l_hostRealnameRegVoucherParams.setOrderRequestNumber(l_strNewNumber);
            
            //�R�|�P�j�@@�����s�폜
            String l_strWhere =
                "institution_code = ? and " +        //�،���ЃR�[�h = this.get�،���ЃR�[�h() And 
                "acc_open_request_number = ? and " +    //���ʃR�[�h = this.get���ʃR�[�h() And   
                "serial_no = ? and " +               //�`�[�ʔ� = �`�[�ʔ� And   
                "status = ? ";                       //�����敪 = �h�������h  

            Object[] l_objBindVars =
                {this.getInstitutionCode(),
                 this.getRequestNumber(),
                 l_strSerialNo,
                 WEB3StatusDef.NOT_DEAL};
                    
            //�ڋq�������̓o�^�`�[�iG1190�j�L���[�e�[�u��
            l_queryProcesser.doDeleteAllQuery(
                HostRealnameRegVoucherParams.TYPE,
                l_strWhere,
                l_objBindVars);         //DataQueryException, DataNetworkException
            
            //�R�|�Q�j�@@�`�[�s�}�� 
            l_queryProcesser.doInsertQuery(l_hostRealnameRegVoucherParams);  
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
     *(saveDelete�`�[�s()�̎����j<BR>
     *�@@�����J�ݓ`�[���P���폜����B<BR>
     *<BR>
     *�@@�ڋq�������̓o�^�`�[�iG1190�j�L���[�e�[�u���̈ȉ��̏����ɓ��Ă͂܂�s���擾����B<BR> 
     *<BR>
     *�@@[����]<BR> 
     *�@@�،���ЃR�[�h =  this.get�،���ЃR�[�h() And<BR> 
     *�@@���ʃR�[�h = this.get���ʃR�[�h() And<BR> 
     *�@@�`�[�ʔ� = �`�[�ʔ� And<BR> 
     *�@@�����敪 = �h�������h<BR> 
     *<BR>
     *�@@�s���擾�ł��Ȃ������ꍇ�Afalse��ԋp����B<BR> 
     *�@@�s���擾�ł����ꍇ�A�Y���s�̍폜�idelete row�j���s���Atrue��ԋp����B<BR>
     *@@param l_strSerialNo - (�`�[�ʔ�)<BR>
     *�`�[�ʔ�<BR>
     *@@return boolean
     *@@roseuid 419C2C5C012C
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

            Object[] l_obfBindVars =
                {this.getInstitutionCode(),
                 this.getRequestNumber(),
                 l_strSerialNo,
                 WEB3StatusDef.NOT_DEAL};

            //�ڋq�������̓o�^�`�[�iG1190�j�L���[�e�[�u��
            List l_lisRows = l_queryProcesser.doFindAllQuery(
                HostRealnameRegVoucherRow.TYPE,
                l_strWhere,
                l_obfBindVars);     //DataQueryException, DataNetworkException
                
            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return false;    
            }
            else
            {
                l_queryProcesser.doDeleteAllQuery(
                    HostRealnameRegVoucherRow.TYPE,
                    l_strWhere,
                    l_obfBindVars);       //DataQueryException, DataNetworkException   

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
     *@@return Object
     *@@roseuid 41B45E6E0177
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
