head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucherCreatedServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݓ`�[�쐬�T�[�r�XImpl(WEB3AccOpenVoucherCreatedServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/20 ���w�� (���u) �V�K�쐬
                 : 2006/06/08 �����(���u) �d�l�ύX�E���f��072
                 : 2006/08/15 �ęԍg(���u) �d�l�ύX�E���f��087
Revesion History : 2009/08/13 ���g(���u) �d�l�ύX�E���f��No.171
*/
package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import webbroker3.accountopen.WEB3AccOpenAccountRegBrokerageFirmVoucher;
import webbroker3.accountopen.WEB3AccOpenAccountRegVoucher;
import webbroker3.accountopen.WEB3AccOpenAgencyInfoRegVoucher;
import webbroker3.accountopen.WEB3AccOpenAgreeTransVoucher;
import webbroker3.accountopen.WEB3AccOpenBankTransVoucher;
import webbroker3.accountopen.WEB3AccOpenChargedInfoVoucher;
import webbroker3.accountopen.WEB3AccOpenContMrgVoucher;
import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.WEB3AccOpenForeignRegVoucher;
import webbroker3.accountopen.WEB3AccOpenGPRegVoucher;
import webbroker3.accountopen.WEB3AccOpenIdConfirmVoucher;
import webbroker3.accountopen.WEB3AccOpenImpConfirmVoucher;
import webbroker3.accountopen.WEB3AccOpenInsiderRegVoucher;
import webbroker3.accountopen.WEB3AccOpenMrfAccountVoucher;
import webbroker3.accountopen.WEB3AccOpenPasswordVoucher;
import webbroker3.accountopen.WEB3AccOpenPostalTransVoucher;
import webbroker3.accountopen.WEB3AccOpenRealNameRegVoucher;
import webbroker3.accountopen.WEB3AccOpenStockHolderRegVoucher;
import webbroker3.accountopen.WEB3AccOpenTradeConditionVoucher;
import webbroker3.accountopen.WEB3AccOpenVoucher;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherCreatedService;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����J�ݓ`�[�쐬�T�[�r�XImpl)<BR>
 * �����J�ݓ`�[�쐬�T�[�r�X�����N���X<BR>
 * @@author ���w��
 * @@version 1.0 
 */
public class WEB3AccOpenVoucherCreatedServiceImpl implements WEB3AccOpenVoucherCreatedService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccOpenVoucherCreatedServiceImpl.class);

    /**
     * @@roseuid 41B45E71001F
     */
    public WEB3AccOpenVoucherCreatedServiceImpl() 
    {
     
    }
    
    /**
     * (create�����J�ݓ`�[)<BR>
     * �w��̌����J�݌����q�̏����A�����J�ݓ`�[���쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����J�ݓ`�[�쐬�jcreate�����J�ݓ`�[�v�Q�ƁB<BR>
     * @@param l_accOpenExpAccountOpen - �����J�݌����q�I�u�W�F�N�g
     * @@return String[]
     * @@roseuid 4191CD88018B
     */
    public String[] createAccOpenVoucher(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createAccOpenVoucher(WEB3AccOpenExpAccountOpen)";
        log.entering(STR_METHOD_NAME);  

        //1.1 ArrayList( )
        ArrayList l_arrayList = new ArrayList();

        //1.2 getInstance(�����J�݌����q)
        WEB3AccOpenAccountRegVoucher l_accountRegVoucher = 
            WEB3AccOpenAccountRegVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.3 add(arg0�i=�ڋq�o�^�`�[�j : Object)
        l_arrayList.add(l_accountRegVoucher);

        //1.4 getInstance(�����J�݌����q)
        WEB3AccOpenContMrgVoucher l_contMrgVoucher = 
            WEB3AccOpenContMrgVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.5 add(arg0�i=�_�񏑒����`�[�j : Object)
        l_arrayList.add(l_contMrgVoucher);

        //1.6 getInstance(�����J�݌����q)
        WEB3AccOpenBankTransVoucher l_bankTransVoucher = 
            WEB3AccOpenBankTransVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.7 add(arg0�i=�U�֐\���i��s�j�`�[�j : Object)
        l_arrayList.add(l_bankTransVoucher);

        //1.8 getInstance(�����J�݌����q)
        WEB3AccOpenPostalTransVoucher l_postalTransVoucher = 
            WEB3AccOpenPostalTransVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.9 add(arg0�i=�U�֐\���i�X���j�`�[�j : Object)
        l_arrayList.add(l_postalTransVoucher);

        //1.10 getInstance(�����J�݌����q)
        WEB3AccOpenChargedInfoVoucher l_chargedInfoVoucher = 
            WEB3AccOpenChargedInfoVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.11 add(arg0�i=�L�����`�[�j : Object)
        l_arrayList.add(l_chargedInfoVoucher);

        //1.12 getInstance(�����J�݌����q)
        WEB3AccOpenAgreeTransVoucher l_agreeTransVoucher = 
            WEB3AccOpenAgreeTransVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.13 add(arg0�i=�ېU���ӓ`�[�j
        l_arrayList.add(l_agreeTransVoucher);

        //1.14 getInstance(�����J�݌����q)
        WEB3AccOpenMrfAccountVoucher l_mrfAccountVoucher = 
            WEB3AccOpenMrfAccountVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.15 add(arg0�i=MRF�����`�[�j : Object)
        l_arrayList.add(l_mrfAccountVoucher);

        //1.16 getInstance(�����J�݌����q)
        WEB3AccOpenImpConfirmVoucher l_impConfirmVoucher = 
            WEB3AccOpenImpConfirmVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.17 add(arg0�i=�d�v�����m�F���j : Object)
        l_arrayList.add(l_impConfirmVoucher);

        //1.18 getInstance(�����J�݌����q)
        WEB3AccOpenIdConfirmVoucher l_confirmVoucher = 
            WEB3AccOpenIdConfirmVoucher.getInstance(l_accOpenExpAccountOpen);        

        //1.19 add(arg0�i=�{�l�m�F�`�[�j : Object)
        l_arrayList.add(l_confirmVoucher);

        //1.20 getInstance(�����J�݌����q)
        WEB3AccOpenPasswordVoucher l_passwordVoucher = 
            WEB3AccOpenPasswordVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.21 add(arg0�i=�Ïؔԍ��`�[�j : Object)
        l_arrayList.add(l_passwordVoucher);

        //1.22 getInstance(�����J�݌����q)
        WEB3AccOpenTradeConditionVoucher l_tradeConditionVoucher = 
            WEB3AccOpenTradeConditionVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.23 add(arg0�i=��c�E�d�q��t�E��������`�[�j : Object)
        l_arrayList.add(l_tradeConditionVoucher);
        
        //1.24 getInstance(�����J�݌����q)
        WEB3AccOpenInsiderRegVoucher l_insiderRegVoucher = 
            WEB3AccOpenInsiderRegVoucher.getInstance(l_accOpenExpAccountOpen);  
                  
        //1.25 add(arg0�i=�����ғo�^�`�[�j : Object)
        l_arrayList.add(l_insiderRegVoucher);
        
        //1.26 getInstance(�����J�݌����q)
        WEB3AccOpenGPRegVoucher l_gpRegVoucher = 
            WEB3AccOpenGPRegVoucher.getInstance(l_accOpenExpAccountOpen);
        
        //1.27 add(arg0�i=GP�����o�^�`�[�j : Object)
        l_arrayList.add(l_gpRegVoucher);
        
        //1.28 getInstance(�����J�݌����q)
        WEB3AccOpenStockHolderRegVoucher l_stockHolderRegVoucher = 
            WEB3AccOpenStockHolderRegVoucher.getInstance(l_accOpenExpAccountOpen);
            
        //1.29 add(arg0�i=���O���E����o�^�`�[�j : Object)
        l_arrayList.add(l_stockHolderRegVoucher);
        
        //1.30 getInstance(�����J�݌����q)
        WEB3AccOpenRealNameRegVoucher l_realNameRegVoucher = 
            WEB3AccOpenRealNameRegVoucher.getInstance(l_accOpenExpAccountOpen); 
            
        //1.31 add(arg0�i=�ڋq�������̓o�^�`�[�j : Object)
        l_arrayList.add(l_realNameRegVoucher);
        
        //1.32 getInstance(�����J�݌����q)
        WEB3AccOpenAccountRegBrokerageFirmVoucher l_accountRegBrokerageFirmVoucher = 
            WEB3AccOpenAccountRegBrokerageFirmVoucher.getInstance(l_accOpenExpAccountOpen);     
            
        //1.33 add(arg0�i=�ڋq�o�^�i����Ɓj�`�[�j : Object)
        l_arrayList.add(l_accountRegBrokerageFirmVoucher);  
        
        //1.34 getInstance(�����J�݌����q)
        WEB3AccOpenForeignRegVoucher l_accOpenforeignSaveRegVoucher = 
            WEB3AccOpenForeignRegVoucher.getInstance(l_accOpenExpAccountOpen);
        
        //1.35 add(arg0�i=�O�ݗa�������o�^�`�[�j : Object)
        l_arrayList.add(l_accOpenforeignSaveRegVoucher);       

        //getInstance(�����J�݌����q : �����J�݌����q)
        WEB3AccOpenAgencyInfoRegVoucher l_accOpenAgencyInfoRegVoucher =
            WEB3AccOpenAgencyInfoRegVoucher.getInstance(l_accOpenExpAccountOpen);

        //add(arg0�i=�@@�\�ʒm���o�^�`�[�j : Object)
        //arg0�i�@@�\�ʒm���o�^�`�[�j�F�@@�@@�\�ʒm���o�^�`�[.getInstance()
        l_arrayList.add(l_accOpenAgencyInfoRegVoucher);

        //1.36 is�g���K���s()
        boolean l_blnIsSubmitMarketTrigger = WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(null);
        log.debug("is�g���K���s:�@@" + l_blnIsSubmitMarketTrigger);
        
        //1.37 toArray( )
        WEB3AccOpenVoucher[] l_openVoucher = new WEB3AccOpenVoucher[l_arrayList.size()];
        l_arrayList.toArray(l_openVoucher);

        //1.38 ArrayList()
        ArrayList l_voucherCodeList = new ArrayList();

        //1.39 �����J�ݓ`�[[]�e�v�f����LOOP����
        boolean l_blnIsVoucher;

        int l_intLength = l_openVoucher.length;
        for (int i = 0; i < l_intLength; i++)
        {
            log.debug("1.27 �����J�ݓ`�[[]�e�v�f����LOOP����");
            //1.39.1
            l_blnIsVoucher = l_openVoucher[i].createVoucher();

            //1.39.2 �`�[���쐬�����ꍇ�i�`�[�쐬() == true�j�A�������{
            if (l_blnIsVoucher)
            {
                log.debug("1.27.2 �`�[���쐬�����ꍇ�B�`�[�R�[�h�F " + l_openVoucher[i].getVoucherCode());
                //1.39.2.1 get�`�[�R�[�h( )
                String l_strVoucherCode = l_openVoucher[i].getVoucherCode();
                
                //1.39.2.2 add(arg0�i=�`�[�R�[�h�j : Object)
                l_voucherCodeList.add(l_strVoucherCode);

                //1.39.2.3 �I�����C�����ԑсiis�g���K���s() == true�j�̏ꍇ�̂ݏ������{
                if (l_blnIsSubmitMarketTrigger)
                {
                    //1.37.2.3.1 �`�[���M( )
                    l_openVoucher[i].sendVoucher();
                }
            }  
        }
 
        //1.40 toArray( )
        log.debug("�`�[�R�[�hList��z��ɕϊ����A�ԋp�B�z�񒷁F " + l_voucherCodeList.size());
        String[] l_strVoucherCodes = new String[l_voucherCodeList.size()];
        l_voucherCodeList.toArray(l_strVoucherCodes);

        log.exiting(STR_METHOD_NAME);
        return l_strVoucherCodes;
    }
    
    /**
     * (delete�����J�ݓ`�[)<BR>
     * �w��̌����J�݌����q�Ɋ֘A����A�����J�ݓ`�[���폜����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����J�ݓ`�[�쐬�jdelete�����J�ݓ`�[�v�Q�ƁB<BR>
     * @@param l_accOpenExpAccountOpen - �����J�݌����q�I�u�W�F�N�g
     * @@return String[]
     * @@roseuid 419C26F200CE
     */
    public String[] deleteAccOpenVoucher(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " deleteAccOpenVoucher(WEB3AccOpenExpAccountOpen)";
        log.entering(STR_METHOD_NAME);

        //1.1 ArrayList( )
        ArrayList l_arrayList = new ArrayList();

        //1.2  getInstance(�����J�݌����q)
        WEB3AccOpenAccountRegVoucher l_accountRegVoucher = 
            WEB3AccOpenAccountRegVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.3 add(arg0�i=�ڋq�o�^�`�[�j : Object)
        l_arrayList.add(l_accountRegVoucher);

        //1.4 getInstance(�����J�݌����q)
        WEB3AccOpenContMrgVoucher l_contMrgVoucher = 
            WEB3AccOpenContMrgVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.5 add(arg0�i=�_�񏑒����`�[�j : Object)
        l_arrayList.add(l_contMrgVoucher);

        //1.6 getInstance(�����J�݌����q)
        WEB3AccOpenBankTransVoucher l_bankTransVoucher = 
            WEB3AccOpenBankTransVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.7 add(arg0�i=�U�֐\���i��s�j�`�[�j : Object)
        l_arrayList.add(l_bankTransVoucher);

        //1.8 getInstance(�����J�݌����q)
        WEB3AccOpenPostalTransVoucher l_postalTransVoucher = 
            WEB3AccOpenPostalTransVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.9 add(arg0�i=�U�֐\���i�X���j�`�[�j : Object)
        l_arrayList.add(l_postalTransVoucher);

        //1.10 getInstance(�����J�݌����q)
        WEB3AccOpenChargedInfoVoucher l_chargedInfoVoucher = 
            WEB3AccOpenChargedInfoVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.11 add(arg0�i=�L�����`�[�j : Object)
        l_arrayList.add(l_chargedInfoVoucher);

        //1.12 getInstance(�����J�݌����q)
        WEB3AccOpenAgreeTransVoucher l_agreeTransVoucher = 
            WEB3AccOpenAgreeTransVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.13 add(arg0�i=�ېU���ӓ`�[�j
        l_arrayList.add(l_agreeTransVoucher);

        //1.14 getInstance(�����J�݌����q)
        WEB3AccOpenMrfAccountVoucher l_mrfAccountVoucher = 
            WEB3AccOpenMrfAccountVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.15 add(arg0�i=MRF�����`�[�j : Object)
        l_arrayList.add(l_mrfAccountVoucher);

        //1.16 getInstance(�����J�݌����q)
        WEB3AccOpenImpConfirmVoucher l_impConfirmVoucher = 
            WEB3AccOpenImpConfirmVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.17 add(arg0�i=�d�v�����m�F���j : Object)
        l_arrayList.add(l_impConfirmVoucher);

        //1.18 getInstance(�����J�݌����q)
        WEB3AccOpenIdConfirmVoucher l_confirmVoucher = 
            WEB3AccOpenIdConfirmVoucher.getInstance(l_accOpenExpAccountOpen);        

        //1.19 add(arg0�i=�{�l�m�F�`�[�j : Object)
        l_arrayList.add(l_confirmVoucher);

        //1.20 getInstance(�����J�݌����q)
        WEB3AccOpenPasswordVoucher l_passwordVoucher = 
            WEB3AccOpenPasswordVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.21 add(arg0�i=�Ïؔԍ��`�[�j : Object)
        l_arrayList.add(l_passwordVoucher);

        //1.22 getInstance(�����J�݌����q)
        WEB3AccOpenTradeConditionVoucher l_tradeConditionVoucher = 
            WEB3AccOpenTradeConditionVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.23 add(arg0�i=��c�E�d�q��t�E��������`�[�j : Object)
        l_arrayList.add(l_tradeConditionVoucher);
        
        //1.24 getInstance(�����J�݌����q)
        WEB3AccOpenInsiderRegVoucher l_insiderRegVoucher = 
            WEB3AccOpenInsiderRegVoucher.getInstance(l_accOpenExpAccountOpen);  
                  
        //1.25 add(arg0�i=�����ғo�^�`�[�j : Object)
        l_arrayList.add(l_insiderRegVoucher);
        
        //1.26 getInstance(�����J�݌����q)
        WEB3AccOpenGPRegVoucher l_gpRegVoucher = 
            WEB3AccOpenGPRegVoucher.getInstance(l_accOpenExpAccountOpen);
        
        //1.27 add(arg0�i=GP�����o�^�`�[�j : Object)
        l_arrayList.add(l_gpRegVoucher);
        
        //1.28 getInstance(�����J�݌����q)
        WEB3AccOpenStockHolderRegVoucher l_stockHolderRegVoucher = 
            WEB3AccOpenStockHolderRegVoucher.getInstance(l_accOpenExpAccountOpen);
            
        //1.29 add(arg0�i=���O���E����o�^�`�[�j : Object)
        l_arrayList.add(l_stockHolderRegVoucher);
        
        //1.30 getInstance(�����J�݌����q)
        WEB3AccOpenRealNameRegVoucher l_realNameRegVoucher = 
            WEB3AccOpenRealNameRegVoucher.getInstance(l_accOpenExpAccountOpen); 
            
        //1.31 add(arg0�i=�ڋq�������̓o�^�`�[�j : Object)
        l_arrayList.add(l_realNameRegVoucher);
        
        //1.32 getInstance(�����J�݌����q)
        WEB3AccOpenAccountRegBrokerageFirmVoucher l_accountRegBrokerageFirmVoucher = 
            WEB3AccOpenAccountRegBrokerageFirmVoucher.getInstance(l_accOpenExpAccountOpen);     
            
        //1.33 add(arg0�i=�ڋq�o�^�i����Ɓj�`�[�j : Object)
        l_arrayList.add(l_accountRegBrokerageFirmVoucher);    
        
        //1.34 getInstance(�����J�݌����q)
        WEB3AccOpenForeignRegVoucher l_accOpenforeignSaveRegVoucher = 
            WEB3AccOpenForeignRegVoucher.getInstance(l_accOpenExpAccountOpen);
        
        //1.35 add(arg0�i=�O�ݗa�������o�^�`�[�j : Object)
        l_arrayList.add(l_accOpenforeignSaveRegVoucher);  

        //getInstance(�����J�݌����q : �����J�݌����q)
        WEB3AccOpenAgencyInfoRegVoucher l_accOpenAgencyInfoRegVoucher =
            WEB3AccOpenAgencyInfoRegVoucher.getInstance(l_accOpenExpAccountOpen);

        //add(arg0�i=�@@�\�ʒm���o�^�`�[�j : Object)
        //arg0�i�@@�\�ʒm���o�^�`�[�j�F�@@�@@�\�ʒm���o�^�`�[.getInstance()
        l_arrayList.add(l_accOpenAgencyInfoRegVoucher);

        //1.36 ArrayList( )
        ArrayList l_voucherCodeList = new ArrayList();
        
        //1.37 toArray( )
        WEB3AccOpenVoucher[] l_openVoucher = new WEB3AccOpenVoucher[l_arrayList.size()];
        l_arrayList.toArray(l_openVoucher);
        
        //1.38 �����J�ݓ`�[[]�e�v�f����LOOP����
        int l_intLength = l_openVoucher.length;
        for (int i = 0; i < l_intLength; i++)
        {
            log.debug("1.26 �����J�ݓ`�[[]�e�v�f����LOOP����");
            //1.38.1  �`�[�폜( )
            boolean l_blnIsDeleteVoucher = l_openVoucher[i].deleteVoucher();

            //1.38.2 �`�[���폜�����ꍇ�i�`�[�폜() == true�j�A�������{
            String l_strVoucherCode = "";
            if (l_blnIsDeleteVoucher)
            {
                log.debug("1.26.2 �`�[���폜�����ꍇ�i�`�[�폜() == true�j�A�������{");
                //1.38.2.1 get�`�[�R�[�h( )
                l_strVoucherCode = l_openVoucher[i].getVoucherCode();
                
                //1.38.2.2  add(arg0�i=�`�[�R�[�h�j : Object)
                l_voucherCodeList.add(l_strVoucherCode);
            }
        }

        //1.39 toArray( )
        String[] l_strVoucherCodes = new String[l_voucherCodeList.size()];
        l_voucherCodeList.toArray(l_strVoucherCodes);

        log.exiting(STR_METHOD_NAME);
        return l_strVoucherCodes;
    }
    
    /**
     * (get�ύX�s���ڈꗗ)<BR>
     * �쐬�ς̓`�[�ɂ��āA�m��ς̍��ڂ�ύX�����Ȃ����߂̍��ږ��ꗗ���擾����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����J�ݓ`�[�쐬�jget�ύX�s���ڈꗗ�v�Q�ƁB<BR>
     * @@param l_accOpenExpAccountOpen - �����J�݌����q�I�u�W�F�N�g
     * @@return String[]
     * @@roseuid 419C4540015A
     */
    public String[] getChangedImpossibleItemList(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getChangedImpossibleItemList(WEB3AccOpenExpAccountOpen)";
        log.entering(STR_METHOD_NAME);  

        //1.1 ArrayList( )
        ArrayList l_arrayList = new ArrayList();

        //1.2  getInstance(�����J�݌����q)
        WEB3AccOpenAccountRegVoucher l_accountRegVoucher = 
            WEB3AccOpenAccountRegVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.3 add(arg0�i=�ڋq�o�^�`�[�j : Object)
        l_arrayList.add(l_accountRegVoucher);

        //1.4 getInstance(�����J�݌����q)
        WEB3AccOpenContMrgVoucher l_contMrgVoucher = 
            WEB3AccOpenContMrgVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.5 add(arg0�i=�_�񏑒����`�[�j : Object)
        l_arrayList.add(l_contMrgVoucher);

        //1.6 getInstance(�����J�݌����q)
        WEB3AccOpenBankTransVoucher l_bankTransVoucher = 
            WEB3AccOpenBankTransVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.7 add(arg0�i=�U�֐\���i��s�j�`�[�j : Object)
        l_arrayList.add(l_bankTransVoucher);

        //1.8 getInstance(�����J�݌����q)
        WEB3AccOpenPostalTransVoucher l_postalTransVoucher = 
            WEB3AccOpenPostalTransVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.9 add(arg0�i=�U�֐\���i�X���j�`�[�j : Object)
        l_arrayList.add(l_postalTransVoucher);

        // 1.10 getInstance(�����J�݌����q)
        WEB3AccOpenChargedInfoVoucher l_chargedInfoVoucher = 
            WEB3AccOpenChargedInfoVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.11 add(arg0�i=�L�����`�[�j : Object)
        l_arrayList.add(l_chargedInfoVoucher);

        //1.12 getInstance(�����J�݌����q)
        WEB3AccOpenAgreeTransVoucher l_agreeTransVoucher = 
            WEB3AccOpenAgreeTransVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.13 add(arg0�i=�ېU���ӓ`�[�j
        l_arrayList.add(l_agreeTransVoucher);

        //1.14 getInstance(�����J�݌����q)
        WEB3AccOpenMrfAccountVoucher l_mrfAccountVoucher = 
            WEB3AccOpenMrfAccountVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.15 add(arg0�i=MRF�����`�[�j : Object)
        l_arrayList.add(l_mrfAccountVoucher);

        //1.16 getInstance(�����J�݌����q)
        WEB3AccOpenImpConfirmVoucher l_impConfirmVoucher = 
            WEB3AccOpenImpConfirmVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.17 add(arg0�i=�d�v�����m�F���j : Object)
        l_arrayList.add(l_impConfirmVoucher);

        //1.18 getInstance(�����J�݌����q)
        WEB3AccOpenIdConfirmVoucher l_confirmVoucher = 
            WEB3AccOpenIdConfirmVoucher.getInstance(l_accOpenExpAccountOpen);        

        //1.19  add(arg0�i=�{�l�m�F�`�[�j : Object)
        l_arrayList.add(l_confirmVoucher);

        //1.20 getInstance(�����J�݌����q)
        WEB3AccOpenPasswordVoucher l_passwordVoucher = 
            WEB3AccOpenPasswordVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.21 add(arg0�i=�Ïؔԍ��`�[�j : Object)
        l_arrayList.add(l_passwordVoucher);

        //1.22 getInstance(�����J�݌����q)
        WEB3AccOpenTradeConditionVoucher l_tradeConditionVoucher = 
            WEB3AccOpenTradeConditionVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.23 add(arg0�i=��c�E�d�q��t�E��������`�[�j : Object)
        l_arrayList.add(l_tradeConditionVoucher);
        
        //1.24 getInstance(�����J�݌����q)
        WEB3AccOpenInsiderRegVoucher l_insiderRegVoucher = 
            WEB3AccOpenInsiderRegVoucher.getInstance(l_accOpenExpAccountOpen);  
                  
        //1.25 add(arg0�i=�����ғo�^�`�[�j : Object)
        l_arrayList.add(l_insiderRegVoucher);
        
        //1.26 getInstance(�����J�݌����q)
        WEB3AccOpenGPRegVoucher l_gpRegVoucher = 
            WEB3AccOpenGPRegVoucher.getInstance(l_accOpenExpAccountOpen);
        
        //1.27 add(arg0�i=GP�����o�^�`�[�j : Object)
        l_arrayList.add(l_gpRegVoucher);
        
        //1.28 getInstance(�����J�݌����q)
        WEB3AccOpenStockHolderRegVoucher l_stockHolderRegVoucher = 
            WEB3AccOpenStockHolderRegVoucher.getInstance(l_accOpenExpAccountOpen);
            
        //1.29 add(arg0�i=���O���E����o�^�`�[�j : Object)
        l_arrayList.add(l_stockHolderRegVoucher);
        
        //1.30 getInstance(�����J�݌����q)
        WEB3AccOpenRealNameRegVoucher l_realNameRegVoucher = 
            WEB3AccOpenRealNameRegVoucher.getInstance(l_accOpenExpAccountOpen); 
            
        //1.31 add(arg0�i=�ڋq�������̓o�^�`�[�j : Object)
        l_arrayList.add(l_realNameRegVoucher);
        
        //1.32 getInstance(�����J�݌����q)
        WEB3AccOpenAccountRegBrokerageFirmVoucher l_accountRegBrokerageFirmVoucher = 
            WEB3AccOpenAccountRegBrokerageFirmVoucher.getInstance(l_accOpenExpAccountOpen);     
            
        //1.33 add(arg0�i=�ڋq�o�^�i����Ɓj�`�[�j : Object)
        l_arrayList.add(l_accountRegBrokerageFirmVoucher);        
        
        //1.34 getInstance(�����J�݌����q)
        WEB3AccOpenForeignRegVoucher l_accOpenforeignSaveRegVoucher = 
            WEB3AccOpenForeignRegVoucher.getInstance(l_accOpenExpAccountOpen);
        
        //1.35 add(arg0�i=�O�ݗa�������o�^�`�[�j : Object)
        l_arrayList.add(l_accOpenforeignSaveRegVoucher);  

        //getInstance(�����J�݌����q : �����J�݌����q)
        WEB3AccOpenAgencyInfoRegVoucher l_accOpenAgencyInfoRegVoucher =
            WEB3AccOpenAgencyInfoRegVoucher.getInstance(l_accOpenExpAccountOpen);

        //add(arg0�i=�@@�\�ʒm���o�^�`�[�j : Object)
        //arg0�i�@@�\�ʒm���o�^�`�[�j�F�@@�@@�\�ʒm���o�^�`�[.getInstance()
        l_arrayList.add(l_accOpenAgencyInfoRegVoucher);

        //1.36 Hashtable( )
        Hashtable l_hashtable = new Hashtable();

        //1.37 toArray( )
        WEB3AccOpenVoucher[] l_openVoucher = new WEB3AccOpenVoucher[l_arrayList.size()];
        l_arrayList.toArray(l_openVoucher);

        //1.38 �����J�ݓ`�[[]�e�v�f����LOOP����
        int l_intLength = l_openVoucher.length;
        for (int i = 0; i < l_intLength; i++)
        {
            if (!l_openVoucher[i].isTargetVoucher())
            {
                //is�Ώۓ`�[()�̖߂�l��false�̏ꍇ�A���̗v�f�ɏ������ڂ��B
                continue;
            }

            log.debug("�����J�ݓ`�[[]�e�v�f����LOOP����");
            //1.38.1 get�m��ύ��ږ�( )
            String[] l_strConfirmedItemNames = l_openVoucher[i].getConfirmedItemName();
            
            //1.38.2 �`�[�o�͍ύ��ڂ̐����iget�m��ύ��ږ�()�̖߂�l�jLOOP����
            int l_intNameLength = l_strConfirmedItemNames.length;
            for (int j = 0; j < l_intNameLength; j++)
            {
                log.debug("�`�[�o�͍ύ��ڂ̐����iget�m��ύ��ږ�()�̖߂�l�jLOOP����");
                //1.38.2.1 put(arg0�i=�`�[�o�͍ύ���[index]�j : Object, arg1�i=�`�[�o�͍ύ���[index]�j : Object)
                l_hashtable.put(l_strConfirmedItemNames[j], l_strConfirmedItemNames[j]);
            }
        }
        
        //1.39 values()
        Collection l_collection = l_hashtable.values();
        
        String[] l_strVoucherCodes = new String[l_collection.size()];
        l_collection.toArray(l_strVoucherCodes);

        log.exiting(STR_METHOD_NAME);
        return l_strVoucherCodes;
    }
}
@
