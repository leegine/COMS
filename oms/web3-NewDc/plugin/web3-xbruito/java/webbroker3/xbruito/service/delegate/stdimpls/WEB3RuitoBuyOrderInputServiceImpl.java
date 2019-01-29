head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoBuyOrderInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ������t�������̓T�[�r�X�����N���X(WEB3RuitoBuyOrderInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/07 �m �X (���u) �V�K�쐬
                   2004/12/08 ��O�� (���u) �c�Ή�
*/
package webbroker3.xbruito.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.CommonOrderValidator;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DesignateMethodDef;
import webbroker3.common.define.WEB3StopDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.xbruito.WEB3RuitoClientRequestService;
import webbroker3.xbruito.WEB3RuitoOrderManagerReusableValidationsCheck;
import webbroker3.xbruito.WEB3RuitoProduct;
import webbroker3.xbruito.WEB3RuitoProductManager;
import webbroker3.xbruito.define.WEB3RuitoBuyPossibleDivDef;
import webbroker3.xbruito.message.WEB3RuitoBuyInputResponse;
import webbroker3.xbruito.message.WEB3RuitoProductCodeNameUnit;
import webbroker3.xbruito.service.delegate.WEB3RuitoBuyOrderInputService;


/**
 * �ݐϓ������t�������̓T�[�r�X�����N���X<BR>
 */
public class WEB3RuitoBuyOrderInputServiceImpl extends WEB3RuitoClientRequestService implements WEB3RuitoBuyOrderInputService 
{
   
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3RuitoBuyOrderInputServiceImpl.class);   
  
   /**
    * �ݐϓ������t�������̓T�[�r�X���������{����B<BR>
    * <BR>
    * �V�[�P���X�}�u�ݓ����t�������́^�i�ݓ��j���t�������́v�Q��<BR>
    * <BR>
    * @@param l_request - ���N�G�X�g�f�[�^
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 406932820270
    */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
       throws WEB3BaseException 
       {     
           final String STR_METHOD_NAME =
               "execute(WEB3GenRequest l_request)";
    
          log.entering(STR_METHOD_NAME);                    
          if (l_request == null)
          { 
              log.debug("�p�����[�^�l��NULL");
              log.exiting(STR_METHOD_NAME);
              throw new WEB3SystemLayerException(
                  WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                  this.getClass().getName() + "." + STR_METHOD_NAME,
                  "�p�����[�^�l��NULL");
          }
    
        //1.1 �@@�⏕�����擾
          SubAccount l_subAccount = this.getSubAccount();
         log.debug("�⏕�����擾" + l_subAccount.getSubAccountId() + "");
          
        //1.2 �����`�F�b�N�I�u�W�F�N�g���擾����
         FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
         CommonOrderValidator l_gentradeOrderValidator = 
             l_finApp.getCommonOrderValidator();
         log.debug("�����`�F�b�N�I�u�W�F�N�g���擾����");
                   
        //1.3�@@�ڋq�ʎ����~�����`�F�b�N
        OrderValidationResult l_orderValidationResult = 
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("__Error[validate����\�ڋq���`�F�b�N]__");
            throw new WEB3SystemLayerException(
                  l_orderValidationResult.getProcessingResult().getErrorInfo(),
                  this.getClass().getName() + "." + STR_METHOD_NAME, 
                  "�ڋq�ʎ����~�����`�F�b�N�G���[�̏ꍇ"); 
        }    
        log.debug("�ڋq�ʎ����~�����`�F�b�N");
        
        
        //1.4 �ݓ������R���ʃ`�F�b�N�̃C���X�^���X���擾����   
        WEB3RuitoOrderManagerReusableValidationsCheck l_ruitoOrderCheckt = 
            (WEB3RuitoOrderManagerReusableValidationsCheck)
                WEB3RuitoOrderManagerReusableValidationsCheck.getInstance(); 
        log.debug("�ݓ������R���ʃ`�F�b�N�̃C���X�^���X���擾����");                     
     
        //1.5�@@�ݐϓ�����������`�F�b�N 
        try
        {
            l_ruitoOrderCheckt.validateRuitoTradedAccountEstablish(l_subAccount);
            log.debug("�ݐϓ�����������`�F�b�N");
        }
        catch(OrderValidationException e)
        {
            log.error("�ݐϓ�����������`�F�b�N�G���[");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00249,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�ݐϓ�����������`�F�b�N�G���[");               
        }
    
        //�@@��t���ԃ`�F�b�N�A�V�X�e�������~�`���b�N 
        boolean l_blnMidiumFundValidFlag = true;
        boolean l_blnMMFValidFlag = true;
        int l_intFundError = 0;
        int l_intMmfError = 0;
        
        //1.6 �|�������t�@@���h�̒�����t�\�`�F�b�N���s���B 
        try
        {
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            log.debug(
                "�������t�@@���h�̒�����t�\�`�F�b�N���s���Bvalidate������t�\"); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug("�������t�@@���h ��t���ԃG���[");
            l_blnMidiumFundValidFlag = false;  
            
            if (WEB3ErrorCatalog.BUSINESS_ERROR_00011.equals(l_ex.getErrorInfo()))
            {
                l_intFundError = 1;
            }
            else if (WEB3ErrorCatalog.BUSINESS_ERROR_00012.equals(l_ex.getErrorInfo()))
            {
                l_intFundError = 2;
            }
            else if (WEB3ErrorCatalog.BUSINESS_ERROR_00013.equals(l_ex.getErrorInfo()))
            {
                l_intFundError = 3;
            }
        }        
        
        //�|������ԊǗ�.reset��t���ԋ敪()���R�[�����A��t���ԋ敪�𒆊����t�@@���h���� 
        //  MMF�ɕύX����B
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                WEB3TradingTimeTypeDef.MMF_SET);
        
        //��t�����A���t���[�����Z�b�g����        
        WEB3GentradeTradingTimeManagement.setTimestamp(); //������ԊǗ�
                      
        //�|MMF�̒�����t�\�`�F�b�N���s���B 
        try
        {
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            log.debug("MMF�̒�����t�\�`�F�b�N���s��");
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug("MMF ��t���ԃG���[");
            l_blnMMFValidFlag = false;  
            
            if (WEB3ErrorCatalog.BUSINESS_ERROR_00011.equals(l_ex.getErrorInfo()))
            {
                l_intMmfError = 1;
            }
            else if (WEB3ErrorCatalog.BUSINESS_ERROR_00012.equals(l_ex.getErrorInfo()))
            {
                l_intMmfError = 2;
            }
            else if (WEB3ErrorCatalog.BUSINESS_ERROR_00013.equals(l_ex.getErrorInfo()))
            {
                l_intMmfError = 3;
            }
        }    
        
        //�ϐ�.�����t�@@���h���t�\�敪
        String l_strChuukokuFundBuyPossdiv = null;
        
        //�ϐ�.MMF���t�\�敪
        String l_strMmfBuyPossdiv = null;
        
        //�|�������t�@@���h��MMF�̗����A�܂��͂����ꂩ�ŗ�O���Ԃ��ꂽ�ꍇ�A�ȉ����s���B 
        if (!l_blnMidiumFundValidFlag || !l_blnMMFValidFlag)
        {
            //(1)�������t�@@���h�̒����̃`�F�b�N�̏ꍇ 
            if (!l_blnMidiumFundValidFlag)                    
            {
                //�E�u�o�b�`�������v�̗�O���Ԃ��ꂽ�ꍇ
                //  �ϐ�.�����t�@@���h���t�\�敪�Ɂh�V�X�e�������~���h���Z�b�g����B
                //�E�u�ً}��~���v�̗�O���Ԃ��ꂽ�ꍇ
                //  �ϐ�.�����t�@@���h���t�\�敪�Ɂh�V�X�e�������~���h���Z�b�g����B
                if (l_intFundError == 1 || l_intFundError == 2)
                {
                    l_strChuukokuFundBuyPossdiv = 
                        WEB3RuitoBuyPossibleDivDef.SYSTEM_TRADING_STOP_ERROR;
                }  
                //�E�u��t�s���ԃG���[�v�̗�O���Ԃ��ꂽ�ꍇ
                //  �ϐ�.�����t�@@���h���t�\�敪�Ɂh��t���ԃG���[�h���Z�b�g����B 
                else if (l_intFundError == 3)
                {
                    l_strChuukokuFundBuyPossdiv = 
                        WEB3RuitoBuyPossibleDivDef.ACCEPTED_TIME_ERROR;
                }
                //�E��O���Ԃ���Ȃ������ꍇ�A�ϐ�.�����t�@@���h���t�\�敪��NULL���Z�b�g����B 
                else
                {
                    l_strChuukokuFundBuyPossdiv = null;
                }
            }
            //(2)MMF�̒����̃`�F�b�N�̏ꍇ 
            if (!l_blnMMFValidFlag)
            {
                //�@@�E��t���ԃG���[�̗�O���Ԃ��ꂽ�ꍇ�A 
                //  �ϐ�.MMF���t�\�敪�Ɂh��t���ԃG���[�h���Z�b�g����B 
                if (l_intMmfError == 1 || l_intMmfError == 2)
                {
                    l_strMmfBuyPossdiv = 
                        WEB3RuitoBuyPossibleDivDef.SYSTEM_TRADING_STOP_ERROR;
                }  
                //�E�V�X�e�������~���̗�O���Ԃ��ꂽ�ꍇ�A 
                //  �ϐ�.MMF���t�\�敪�Ɂh�V�X�e�������~���h���Z�b�g����B 
                else if (l_intMmfError == 3)
                {
                    l_strMmfBuyPossdiv = 
                        WEB3RuitoBuyPossibleDivDef.ACCEPTED_TIME_ERROR;
                }
                //�E��O���Ԃ���Ȃ������ꍇ�A�ϐ�.MMF���t�\�敪��NULL���Z�b�g����B
                else
                {
                    l_strMmfBuyPossdiv = null;
                }
            }            
        } 
        //1.12�@@�����ꗗ�̎擾
        //�|�g���ݓ������}�l�[�W��.get�ݓ������ꗗ( )���R�[������B
        WEB3RuitoProductManager l_ruitoProductManager =   
            (WEB3RuitoProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getProductManager();
        
        List l_lisRuitoProduct = 
            l_ruitoProductManager.getRuitoProductList(
                l_subAccount.getInstitution().getInstitutionCode());
        
        //�|�����R�[�h���̈ꗗ�̍쐬
        WEB3RuitoProductCodeNameUnit[] l_ruitoProductCodeNames = 
            new WEB3RuitoProductCodeNameUnit[l_lisRuitoProduct.size()];
        
        //1.13 get�ݓ������ꗗ( )�̖߂�l�̌������A�J��Ԃ��ݓ������R�[�h���̂̔z����쐬����B
        for (int i = 0; i < l_lisRuitoProduct.size(); i++)
        {
            RuitoProductRow l_ruitoProductRow = 
                (RuitoProductRow)l_lisRuitoProduct.get(i);        
            
            l_ruitoProductCodeNames[i] = new WEB3RuitoProductCodeNameUnit();
    
            l_ruitoProductCodeNames[i].ruitoProductCode = 
                l_ruitoProductRow.getProductCode();
            l_ruitoProductCodeNames[i].ruitoProductName = 
                l_ruitoProductRow.getStandardName();
        
            //�|�g���ݓ������}�l�[�W��.get�ݓ��������( )���R�[�����A�ݓ���������I�u�W�F�N�g���擾����B        
            RuitoTradedProduct l_ruitoTradedProduct = null;
            try
            {
                l_ruitoTradedProduct = 
                    l_ruitoProductManager.getRuitoTradedProduct(
                        l_subAccount.getInstitution(),
                        l_ruitoProductRow.getProductCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�g���ݓ�����������Ȃ�");                
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00250,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            RuitoTradedProductRow l_ruitoTradedProductRow = 
                (RuitoTradedProductRow)l_ruitoTradedProduct.getDataSourceObject();
            String l_strBuyPossDiv = null;
            
            WEB3RuitoProduct l_ruitoProduct = null;
            try
            {
                l_ruitoProduct = (WEB3RuitoProduct) 
                    l_ruitoProductManager.getRuitoProduct(l_ruitoProductRow.getProductId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__");
                    
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }            
           
            Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
                
            //�|�ݓ�����Params.�ݓ��^�C�v=�hMMF�h�̏ꍇ�́hMMF�i�ݒ�j�h���A�ݓ�����Params.�ݓ��^�C�v=�h����F�h�� 
            //�ꍇ�́h����F�h�������ɁAreset������t���i( )���R�[���B 
            if (RuitoTypeEnum.MMF.equals(l_ruitoProductRow.getRuitoType()))
            {
                WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(
                        WEB3TradingTimeTypeDef.MMF_SET);
                //�|������ԊǗ�.setTimestamp( )���R�[���B
                WEB3GentradeTradingTimeManagement.setTimestamp();
                
                if (WEB3StopDef.STOP_INSIDE.equals(l_ruitoTradedProductRow.getBuyStop()))
                {
                    //(1)�擾�����ݓ���������I�u�W�F�N�g.get���t��~( )���h��~���h�̏ꍇ�A 
                    //�h�ً}��~���h���Z�b�g����B 
                    l_strBuyPossDiv = WEB3RuitoBuyPossibleDivDef.SCRAM_STOPING;
                }
                else if (!l_ruitoProduct.isBuyPossible(l_datBizDate))
                {
                    //(2)�g���ݓ�����.is���t�\( )=false�̏ꍇ�A�h�����~���h���Z�b�g����B 
                    l_strBuyPossDiv = WEB3RuitoBuyPossibleDivDef.TRADING_STOPING;
                }
                else if (l_strMmfBuyPossdiv != null)
                {
                    //(3)��t���ԃG���[�A�V�X�e����~���G���[�̃`�F�b�N                 
                    //�|�ݓ�����Params.get�����^�C�v���hMMF�h�̏ꍇ�A�ϐ�.MMF���t�\�敪!=NULL�̏ꍇ�A
                    //�ϐ�.MMF���t�\�敪���Z�b�g����B        
                    l_strBuyPossDiv = l_strMmfBuyPossdiv;
                }
                else
                {
                    //(4)��L(1)(2)(3)�̂�����ɂ��Y�������A���� 
                    //�ϐ�.MMF���t�\�敪��NULL�̏ꍇNULL���Z�b�g����B 
                    l_strBuyPossDiv = null;
                }
            }
            else if (RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoProductRow.getRuitoType()))
            {
                WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(
                        WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND);
                //(1)������ԊǗ�.setTimestamp( )���R�[���B
                WEB3GentradeTradingTimeManagement.setTimestamp();
                
                if (WEB3StopDef.STOP_INSIDE.equals(l_ruitoTradedProductRow.getBuyStop()))
                {
                    //�|�擾�����ݓ���������I�u�W�F�N�g.get���t��~( )���h��~���h�̏ꍇ�A 
                    //�h�ً}��~���h���Z�b�g����B  
                    l_strBuyPossDiv = WEB3RuitoBuyPossibleDivDef.SCRAM_STOPING;
                }                         
                else if (!l_ruitoProduct.isBuyPossible(l_datBizDate))
                {
                    //(2)�g���ݓ�����.is���t�\( )=false�̏ꍇ�A�h�����~���h���Z�b�g����B 
                    l_strBuyPossDiv = WEB3RuitoBuyPossibleDivDef.TRADING_STOPING;
                }                
                //(3)��t���ԃG���[�A�V�X�e����~���G���[�̃`�F�b�N 
                //�|�ݓ�����Params.get�����^�C�v���h�������t�@@���h�h�̏ꍇ�ł���A���� 
                //  �ϐ�.�����t�@@���h���t�\�敪!=NULL�̏ꍇ�A�ϐ�.�����t�@@���h���t�\�敪���Z�b�g����B
                else if (l_strChuukokuFundBuyPossdiv != null)
                {
                    l_strBuyPossDiv = l_strChuukokuFundBuyPossdiv;
                }
                else
                {
                    //(4)��L(1)(2)(3)�̂�����ɂ��Y�������A���� 
                    //�ϐ�.�����t�@@���h���t�\�敪��NULL�̏ꍇNULL���Z�b�g����B 
                    l_strBuyPossDiv = null;
                }
            }            
            //���t�\�敪
            l_ruitoProductCodeNames[i].buyPosDiv = l_strBuyPossDiv;
            
        }
        //�@@���t�\�z�擾 
        double l_dblTradingPower = 0;
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        //(1) ���������擾���� 
        Date l_datBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate();
        //(2)�@@��n�����擾���� 
        Date l_datDeliveryDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
    
        //1.14 ����]�̓T�[�r�X.get���̑����i���t�\�z()���R�[�����A���t�\�z���擾����B 
        l_dblTradingPower = l_tpTradingPowerService.getOtherTradingPower(
            (WEB3GentradeSubAccount)l_subAccount,
            l_datDeliveryDate);
        
        //1.15�@@�w����@@�擾     
        int l_lngOrderInputCount = l_lisRuitoProduct.size();
        List l_listRuitoProduct = new ArrayList(0);
        for(int k=0; k < l_lngOrderInputCount; k++)
        {
            String l_productParams;
             
            RuitoProductParams l_RuitoProductParams = 
                (RuitoProductParams)l_lisRuitoProduct.get(k);
            l_productParams = l_RuitoProductParams.getBuyDesignateMethod();
            
            if(l_productParams != null)
            {
                //���z�w�肪�\
                if (l_productParams.equals(WEB3DesignateMethodDef.AMOUNT))
                {
                    if(l_listRuitoProduct.size() == 0)
                    {
                        l_listRuitoProduct.add(WEB3DesignateMethodDef.AMOUNT);                                                   
                    }
                    if(l_listRuitoProduct.size() == 1 && l_listRuitoProduct.get(0) != 
                        WEB3DesignateMethodDef.AMOUNT)
                    {
                        l_listRuitoProduct.add(WEB3DesignateMethodDef.AMOUNT);
                    }
                }
            
                //�����w�肪�\
                if (l_productParams.equals(WEB3DesignateMethodDef.NUMBER))
                {
                    if(l_listRuitoProduct.size() == 0)
                    {
                        l_listRuitoProduct.add(WEB3DesignateMethodDef.NUMBER);                                                   
                    }
                    if(l_listRuitoProduct.size() == 1 && l_listRuitoProduct.get(0) != 
                        WEB3DesignateMethodDef.NUMBER)
                    {
                        l_listRuitoProduct.add(WEB3DesignateMethodDef.NUMBER);
                    }                                              
                }
            
                //���z�w��ƌ����w��̗������\
                if (l_productParams.equals(WEB3DesignateMethodDef.SELECT))
                {
                    if(l_listRuitoProduct.size() == 0)
                    {
                        l_listRuitoProduct.add(WEB3DesignateMethodDef.AMOUNT);
                        l_listRuitoProduct.add(WEB3DesignateMethodDef.NUMBER);
                    }
                    if(l_listRuitoProduct.size() == 1 && l_listRuitoProduct.get(0) != 
                        WEB3DesignateMethodDef.AMOUNT)
                    {
                        l_listRuitoProduct.add(WEB3DesignateMethodDef.AMOUNT);
                    }
                    if(l_listRuitoProduct.size() == 1 && l_listRuitoProduct.get(0) != 
                        WEB3DesignateMethodDef.NUMBER)
                    {
                        l_listRuitoProduct.add(WEB3DesignateMethodDef.NUMBER);
                    } 
                                                                
                }
            }
        }
                              
        //�ݓ����t�������̓��X�|���X�I�u�W�F�N�g����
        WEB3GenResponse l_response = l_request.createResponse();
        WEB3RuitoBuyInputResponse l_ruitoBuyInputResponse 
                   = (WEB3RuitoBuyInputResponse)l_response;
                   
        log.debug("�ݓ����t�������̓��X�|���X�I�u�W�F�N�g����");
        //�����R�[�h���̈ꗗ
        List l_listtoarray = new ArrayList();
        for(int k = 0; k < l_lngOrderInputCount; k++)
        {
            RuitoProductParams l_RuitoProductParams = 
                 (RuitoProductParams)l_lisRuitoProduct.get(k);
            WEB3RuitoProductCodeNameUnit l_WEB3RuitoProductCodeName = 
                new WEB3RuitoProductCodeNameUnit();
            
            l_WEB3RuitoProductCodeName.ruitoProductCode = 
                l_RuitoProductParams.getProductCode();
            
            log.debug("l_RuitoProductParams.getProductCode() =" + 
                    l_RuitoProductParams.getProductCode());
            
            l_WEB3RuitoProductCodeName.ruitoProductName = 
                l_RuitoProductParams.getStandardName();
            
            log.debug("l_RuitoProductParams.getStandardName() =" + 
                    l_RuitoProductParams.getStandardName());
            
            l_WEB3RuitoProductCodeName.buyPosDiv = l_ruitoProductCodeNames[k].buyPosDiv;

            log.debug("l_ruitoProductCodeNames[" + k + "].buyPosDiv =" + 
                l_ruitoProductCodeNames[k].buyPosDiv);
            
            l_listtoarray.add(l_WEB3RuitoProductCodeName);
                
        }
        if(l_listtoarray != null)
        {
            int l_lsize = l_listtoarray.size();
            WEB3RuitoProductCodeNameUnit[] l_temp = 
                new WEB3RuitoProductCodeNameUnit[l_lsize];
            for(int i = 0; i < l_lsize; i++)
            {
                l_temp[i] = (WEB3RuitoProductCodeNameUnit)l_listtoarray.get(i);
            }       
            l_ruitoBuyInputResponse.ruitoProductCodeNames = l_temp;
        }
        else
        {
            l_ruitoBuyInputResponse.ruitoProductCodeNames = null;
        }
        log.debug("�����R�[�h���̈ꗗ");
        for(int i = 0; i < l_ruitoBuyInputResponse.ruitoProductCodeNames.length; i ++)
        {
            log.debug("�����R�[�h���̈ꗗ" + i + "productCode=" + 
                    l_ruitoBuyInputResponse.ruitoProductCodeNames[i].ruitoProductCode);
            log.debug("�����R�[�h���̈ꗗ" + i + "productName=" + 
                    l_ruitoBuyInputResponse.ruitoProductCodeNames[i].ruitoProductName);
        }
    
        
        //���t�\���z
    
        // ----------------- Start
        // Modify by Alan wang 2004/08/13 according to formating type double to type String
    //    l_ruitoBuyInputResponse.tradingPower = 
    //         new Double(l_tradingPower).toString();
         l_ruitoBuyInputResponse.tradingPower = 
            WEB3StringTypeUtility.formatNumber(l_dblTradingPower);
        // ----------------- End
    
        log.debug("���t�\���z = " + l_ruitoBuyInputResponse.tradingPower);         
             
        //�w����@@�ꗗ
        if(l_listRuitoProduct != null)
        {
            int l_lsize = l_listRuitoProduct.size();
            String[] l_temp = new String[l_lsize];
            for(int i = 0; i < l_lsize; i++)
            {
                l_temp[i] = (String)l_listRuitoProduct.get(i);
            }       
            l_ruitoBuyInputResponse.specifyDivList = l_temp;
    
        }
        else
        {
            l_ruitoBuyInputResponse.specifyDivList = null;     
        }
    
        log.exiting(STR_METHOD_NAME); 
        return l_ruitoBuyInputResponse;
    }
}
@
