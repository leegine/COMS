head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminRuitoTradeStopServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��җݓ������ʔ�����~�T�[�r�XImpl (WEB3AdminRuitoTradeStopServieImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 ��O�� (���u) �V�K�쐬
*/
package webbroker3.xbruito.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductUpdqRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.WEB3RuitoProductManager;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeInfo;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopCompleteRequest;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopCompleteResponse;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopConfirmRequest;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopConfirmResponse;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopInputRequest;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopInputResponse;
import webbroker3.xbruito.service.delegate.WEB3AdminRuitoTradeStopService;

/**
 * (�Ǘ��җݓ������ʔ�����~�T�[�r�XImpl)<BR>
 * �Ǘ��җݓ������ʔ�����~�T�[�r�X�����N���X
 */
public class WEB3AdminRuitoTradeStopServiceImpl implements WEB3AdminRuitoTradeStopService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminRuitoTradeStopServiceImpl.class);
    
    /**
     * �ݓ��Ǘ��ҋ@@�\�����ʔ�����~�T�[�r�X�����{����B<BR>
     *���N�G�X�g�f�[�^�̃N���X�ɂ���āA�ȉ��̂����ꂩ�̃��\�b�h���R�[������B<BR> 
     *�|get���͉��() <BR>
     *�|validate�����ʔ�����~()<BR> 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 406932820270
     */
    public  WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
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

        if (l_request instanceof WEB3AdminRuitoTradeStopInputRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�ݓ������ʔ�����~���͉�ʃ��N�G�X�g�v�̏ꍇ
            log.exiting(STR_METHOD_NAME);
            return this.getInputScreen((WEB3AdminRuitoTradeStopInputRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminRuitoTradeStopConfirmRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�ݓ������ʔ�����~�m�F��ʃ��N�G�X�g�v�̏ꍇ
            log.exiting(STR_METHOD_NAME);
            return this.validateTradeStop((WEB3AdminRuitoTradeStopConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminRuitoTradeStopCompleteRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�ݓ������ʔ�����~������ʃ��N�G�X�g�v�̏ꍇ
            log.exiting(STR_METHOD_NAME);
            return this.submitTradeStop((WEB3AdminRuitoTradeStopCompleteRequest) l_request);
        }
        else
        {
            log.debug(STR_METHOD_NAME + " __Error[���͒l���s���ł�]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }
    }
    
    /**
     * (get���͉��)<BR>
     * �ݓ������ʔ�����~���͉�ʎ擾���������{����B<BR>
     *�V�[�P���X�}�u�i�ݓ��Ǘ��ҁjget���͉�ʁv�Q��<BR>
     * @@param l_request - �ݓ������ʔ�����~���͉�ʃ��N�G�X�g
     * @@return webbroker3.common.message.WEB3AdminRuitoTradeStopInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 406932820270
     */
    private WEB3AdminRuitoTradeStopInputResponse getInputScreen(
        WEB3AdminRuitoTradeStopInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getInputScreen(WEB3AdminRuitoTradeStopInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.2 ���O�C�������Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 �Y���̊Ǘ��҂����̋@@�\���g���邩�����`�F�b�N���s���B 
        //validate����(String, boolean)
        //[����] 
        //�@@�@@�\�J�e�S���[�R�[�h���h�ݓ��i�����Ǘ��j�h 
        //is�X�V�F false 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.ADMIN_RUITO_TRADE_STOP,
            false);
        
        //1.4 �Ǘ��҃I�u�W�F�N�g��菊������،���Ђ��擾����B
        Institution l_institution = l_web3Administrator.getInstitution();
        
        //1.5 �ݓ�������S�Č��o����B
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3RuitoProductManager l_ruitoProductManager =   
            (WEB3RuitoProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getProductManager();
        
        List l_lisRuitoProduct = 
            l_ruitoProductManager.getRuitoProductList(
                    l_institution.getInstitutionCode());
        log.debug("l_lisRuitoProduct.size() = " + l_lisRuitoProduct.size());
        
        //�|�߂�l�̌�����0���̏ꍇ�A�i�f�[�^�s�����j�Ƃ��ė�O���X���[����B
        if (l_lisRuitoProduct.size() == 0)
        {
            log.debug("�f�[�^�s�����G���[");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "�߂�l�̌�����0���̏ꍇ");
        }
        
        //1.6 ���ݓ��t�擾
        //GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
        Timestamp l_tsSystemTime =  GtlUtils.getTradingSystem().getSystemTimestamp();
        
        //1.7  �c�Ɠ��`�F�b�N�擾�������ݓ��t���c�Ɠ����ǂ����𔻒肷��B
		Date l_dateSystemDate = WEB3DateUtility.getDate(
			WEB3DateUtility.formatDate(l_tsSystemTime,"yyyyMMdd"),"yyyyMMdd");
		Timestamp l_tsSystemDate = new Timestamp(l_dateSystemDate.getTime()); 
        boolean l_blnBizDate = this.isBizDate (l_tsSystemDate);
        
        Date l_datCurBizDate = null;
        Date l_datNextBizDate = null;
        
        List l_lisAdminRuitoTradeInfo = new ArrayList();
        
        //1.8 get�ݓ������ꗗ()�̖߂�l�̌������A�J��Ԃ��ėݓ������ʔ������I�u�W�F�N�g�̔z����쐬����B
        for (int i = 0; i < l_lisRuitoProduct.size(); i++)
        {
            RuitoProductRow l_ruitoProductRow = 
                (RuitoProductRow)l_lisRuitoProduct.get(i);
            
            //1.8.1 �ݓ������ʔ������( )(
            WEB3AdminRuitoTradeInfo l_adminRuitoTradeInfo = 
                new WEB3AdminRuitoTradeInfo();
            
            //1.8.2 �������̃v���p�e�B�E�Z�b�g

            //�E�����R�[�h��get�ݓ������ꗗ()�̖߂�l�in���ځj�̖����R�[�h
            l_adminRuitoTradeInfo.ruitoProductCode = l_ruitoProductRow.getProductCode();
            
            //�E��������get�ݓ������ꗗ()�̖߂�l�in���ځj�̖�����
            l_adminRuitoTradeInfo.ruitoProductName = l_ruitoProductRow.getStandardName();
            
            //�E���t�J�n����get�ݓ������ꗗ()�̖߂�l�in���ځj�̔��t�J�n��
            l_adminRuitoTradeInfo.buyStartDate = l_ruitoProductRow.getBuyStartDate();
            
            //�E���t�I������get�ݓ������ꗗ()�̖߂�l�in���ځj�̔��t�I����
            l_adminRuitoTradeInfo.buyEndDate = l_ruitoProductRow.getBuyEndDate();
            
            //�E���J�n����get�ݓ������ꗗ()�̖߂�l�in���ځj�̉��J�n��
            l_adminRuitoTradeInfo.sellStartDate = l_ruitoProductRow.getSellStartDate();
            
            //�E���I������get�ݓ������ꗗ()�̖߂�l�in���ځj�̉��I����
            l_adminRuitoTradeInfo.sellEndDate = l_ruitoProductRow.getSellEndDate();
            
            //1.8.4 �|reset��t���ԋ敪()�̎��{            
            //�Eget�ݓ������ꗗ()�̖߂�l�in���ځj�̗ݓ��^�C�v���hMMF�h�̏ꍇ�A�hMMF�i�ݒ�j�h���Z�b�g�B
            //�Eget�ݓ������ꗗ()�̖߂�l�in���ځj�̗ݓ��^�C�v���h����F�h�̏ꍇ�A�h����F�h���Z�b�g�B
            String l_strTradingTimeType = "";
            if (RuitoTypeEnum.MMF.equals(l_ruitoProductRow.getRuitoType()))
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.MMF_SET;                
            }
            else if (RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoProductRow.getRuitoType()))
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND;
            }
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(l_strTradingTimeType);
            
            //1.8.5 �|setTimestamp()�̎��{
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //1.8.6 ��������I�u�W�F�N�g���擾����B
            //  [get�ݓ���������ɓn������]
            //  �،���Ё��Ǘ��҃I�u�W�F�N�gget�،����()�̖߂�l
            //  �����R�[�h��get�ݓ������ꗗ()�̖߂�l�in���ځj�̖����R�[�h
            RuitoTradedProduct l_ruitoTradedProduct = null;
            try
            {
                log.debug("�����R�[�h�� " + l_ruitoProductRow.getProductCode());
                l_ruitoTradedProduct = 
                    l_ruitoProductManager.getRuitoTradedProduct(
                        l_institution,
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
            
            //1.8.7 is�c�Ɠ�()��true�������ꍇ
            if (l_blnBizDate)
            {
                log.debug("is�c�Ɠ�()��true�������ꍇ");
                //�c�Ɠ��v�Z
                
                //�������������I�u�W�F�N�g�̗L����
                Date l_datValidForBizDate = WEB3DateUtility.getDate(
                        l_ruitoTradedProductRow.getValidForBizDate(), "yyyyMMdd");
                
                //[roll�ɓn������]
                //���Z�^���Z�������P���Z�b�g�B�i���������w��j
                int l_intRoll = 0;
				RuitoTradedProductUpdqRow l_ruitoTradedProductUpdqRow = null;
				try
				{
                    log.debug("�i���������w��j");
                   	l_intRoll = 1;
                    
					log.debug("[roll�ɓn������]=" + l_intRoll);
					Date l_datSalsDate = new WEB3GentradeBizDate(
							new Timestamp(l_datValidForBizDate.getTime())).roll(l_intRoll);
                
					String l_strSalsDate = 
						WEB3DateUtility.formatDate(l_datSalsDate, "yyyyMMdd");
                    
					//�������UPDQ�I�u�W�F�N�g�擾����
					//�|�ȉ��̏����ŁA�u�ݓ���������ꎞ�e�[�u���v����������B
					//[��������]
					//�������ID����������I�u�W�F�N�g�̎������ID and
					//�L������roll()�̖߂�l
					String l_strWhereClause = 
						"traded_product_id = ? and valid_for_biz_date = ?";
                    
					log.debug("�������ID�� " + 
							l_ruitoTradedProduct.getTradedProductId() + "");
					log.debug("�L������ " + 
							l_strSalsDate);
                    
					//DataQueryException,DataNetworkException
					List l_lisRows =
						Processors.getDefaultProcessor().doFindAllQuery(
							RuitoTradedProductUpdqRow.TYPE,
							l_strWhereClause,
							new Object[] { 
								new Long(l_ruitoTradedProduct.getTradedProductId()), 
								l_strSalsDate });
    						//�|�߂�l !=1���̏ꍇ�A�i�f�[�^�s�����j�Ƃ��ė�O���X���[����B
							log.debug("size ="  + l_lisRows.size());
					if (l_lisRows.size() != 1)
					{
						log.debug("�f�[�^�s�����G���[");
						throw new WEB3SystemLayerException(
							WEB3ErrorCatalog.SYSTEM_ERROR_80006,
							getClass().getName() + "." + STR_METHOD_NAME, 
							"�߂�l !=1���̏ꍇ");                       
					}
					l_ruitoTradedProductUpdqRow = 
						(RuitoTradedProductUpdqRow)l_lisRows.get(0);
					log.debug("l_ruitoTradedProductUpdqRow = " + l_ruitoTradedProductUpdqRow);

                }
                catch (DataQueryException l_ex)
                {
                    log.error("__DataQueryException__");
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("__DataNetworkException__");
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                
                //�ݓ������ʔ������I�u�W�F�N�g�ɁA�ȉ��̃v���p�e�B���Z�b�g����B
                //�E���t�\�敪�i�����j���h��������i�������j�h�̔��t��~
                //�E���\�敪�i�����j���h��������i�������j�h�̉���~
                //�E���t�\�敪�i�����j���h��������i�������j�h�̔��t��~
                //�E���\�敪�i�����j���h��������i�������j�h�̉���~
                log.debug("���c�Ɠ����̎�������I�u�W�F�N�g�̃v���p�e�B�E�Z�b�g");
                //1.8.7.4 ��������̃v���p�e�B�E�Z�b�g
                l_adminRuitoTradeInfo.curBuyPosDiv = l_ruitoTradedProductRow.getBuyStop();
                l_adminRuitoTradeInfo.curSellPosDiv = l_ruitoTradedProductRow.getSellStop();
				log.debug("���c�Ɠ�����������I�u�W�F�N�g�̃v���p�e�B�E�Z�b�g");
                l_adminRuitoTradeInfo.nextBuyPosDiv = l_ruitoTradedProductUpdqRow.getBuyStop();
                l_adminRuitoTradeInfo.nextSellPosDiv = l_ruitoTradedProductUpdqRow.getSellStop();
                   
                l_datCurBizDate = WEB3DateUtility.getDate(
                    l_ruitoTradedProductRow.getValidForBizDate(), "yyyyMMdd");
                l_datNextBizDate = WEB3DateUtility.getDate(
                    l_ruitoTradedProductUpdqRow.getValidForBizDate(), "yyyyMMdd");

            }
            else
            {
                log.debug("is�c�Ɠ�()��false�������ꍇ");
                //�ݓ������ʔ������I�u�W�F�N�g�ɁA�ȉ��̃v���p�e�B���Z�b�g����B
                //�|�������A���h��������i�������j�h�Ƃ���B
                //�E���t�\�敪�i�����j��NULL
                //�E���\�敪�i�����j��NULL
                //�E���t�\�敪�i�����j���h��������i�������j�h�̔��t��~
                //�E���\�敪�i�����j���h��������i�������j�h�̉���~
                
                //1.8.8
                l_adminRuitoTradeInfo.curBuyPosDiv = null;
                l_adminRuitoTradeInfo.curSellPosDiv = null;
                l_adminRuitoTradeInfo.nextBuyPosDiv = l_ruitoTradedProductRow.getBuyStop();
                l_adminRuitoTradeInfo.nextSellPosDiv = l_ruitoTradedProductRow.getSellStop();

                l_datCurBizDate = null;
                l_datNextBizDate = WEB3DateUtility.getDate(
                    l_ruitoTradedProductRow.getValidForBizDate(), "yyyyMMdd");              
            }         
            l_lisAdminRuitoTradeInfo.add(l_adminRuitoTradeInfo);
        }
        
        //1.9 create���X�|���X( )       
        WEB3AdminRuitoTradeStopInputResponse l_adminRuitoTradeStopInputResponse =
            (WEB3AdminRuitoTradeStopInputResponse) 
                l_request.createResponse();   
        //�擾�������X�|���X�f�[�^�Ɉȉ��̃v���p�e�B���Z�b�g���A�ԋp����B

        WEB3AdminRuitoTradeInfo[] l_adminRuitoTradeInfo =  
            new WEB3AdminRuitoTradeInfo[l_lisAdminRuitoTradeInfo.size()];
        
        l_lisAdminRuitoTradeInfo.toArray(l_adminRuitoTradeInfo);
       
        //�E�������ꗗ�����J��Ԃ��������ō쐬�����ݓ������ʔ������I�u�W�F�N�g�̔z��
        l_adminRuitoTradeStopInputResponse.productInfoList = 
            l_adminRuitoTradeInfo;
        
        //�E�I�y���[�V�������t���擾�������ݓ��t
        l_adminRuitoTradeStopInputResponse.operationDate = l_tsSystemTime;
        
        //�E���ݓ�����̔���������������i�������j�̗L����(*)
        l_adminRuitoTradeStopInputResponse.curBizDate = l_datCurBizDate;
        
        //�E���ݓ�����̗��c�Ɠ�����������i�������j�̗L����
        l_adminRuitoTradeStopInputResponse.nextBizDate = l_datNextBizDate;
        
        log.exiting(STR_METHOD_NAME);
        return l_adminRuitoTradeStopInputResponse;
    }
    
    /**
     * (validate�����ʔ�����~)<BR>
     * �ݓ������ʔ�����~�m�F���������{����B<BR>
     * �V�[�P���X�}�u�i�ݓ��Ǘ��ҁj�����ʔ�����~�R���v�Q��<BR>
     * @@param l_request - �ݓ������ʔ�����~�m�F���N�G�X�g
     * @@return webbroker3.common.message.WEB3AdminRuitoTradeStopConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 406932820270
     */
    private WEB3AdminRuitoTradeStopConfirmResponse validateTradeStop(
        WEB3AdminRuitoTradeStopConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTradeStop(WEB3AdminRuitoTradeStopConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 ���͓��e�`�F�b�N 
        l_request.validate();
        
        //1.3 ���O�C�������Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4 �Y���̊Ǘ��҂����̋@@�\���g���邩�����`�F�b�N���s���B 
        //validate����(String, boolean)
        //[����] 
        //�@@�@@�\�J�e�S���[�R�[�h���h�ݓ��i�����Ǘ��j�h 
        //is�X�V�F false 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.ADMIN_RUITO_TRADE_STOP,
            true);
                
        //1.5 �I�y���[�V�������t�`�F�b�N
        Timestamp l_tsSystemTime =  GtlUtils.getTradingSystem().getSystemTimestamp();
        if (!(WEB3DateUtility.compareToDay(l_request.operationDate, l_tsSystemTime) == 0))
        {
            log.debug("�I�y���[�V�������t�����ݓ��t�ł͂���܂���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01354,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "���N�G�X�g�f�[�^.�I�y���[�V�������t!= ���ݓ��t�̏ꍇ");                       
        }
        
        //1.6 �Ǘ��҃I�u�W�F�N�g��菊������،���Ђ��擾����B
        Institution l_institution = l_web3Administrator.getInstitution();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3RuitoProductManager l_ruitoProductManager =   
            (WEB3RuitoProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getProductManager();
        
        log.debug("���N�G�X�g�f�[�^.�����X�V���̖߂�l�̌��� = " + 
                l_request.productUpdateInfoList.length);
        
        //1.7 ���N�G�X�g�f�[�^.�����X�V���̖߂�l�̌������A�J��Ԃ�
        for (int i = 0; i < l_request.productUpdateInfoList.length; i++)
        {
            //1.7.1 �ݓ������̎擾 
            //[get�ݓ������ɓn������]
            //�،���Ё��Ǘ��҃I�u�W�F�N�gget�،����()�̖߂�l
            //�����R�[�h�����N�G�X�g�f�[�^.�����X�V���.�����R�[�h
            RuitoProduct l_ruitoProduct = null;
            try
            {
                l_ruitoProduct = 
                    l_ruitoProductManager.getRuitoProduct(
                        l_institution,
                        l_request.productUpdateInfoList[i].ruitoProductCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__");                
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00193,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            RuitoProductRow l_ruitoProductRow = (RuitoProductRow) l_ruitoProduct.getDataSourceObject();
            RuitoProductParams l_ruitoProductParams = 
                new RuitoProductParams(l_ruitoProductRow);
            
            //1.7.2 �ȉ��̂����ꂩ��NULL�̏ꍇ
            if (l_request.productUpdateInfoList[i].buyStartDate == null ||
                l_request.productUpdateInfoList[i].buyEndDate == null ||
                l_request.productUpdateInfoList[i].sellStartDate == null ||
                l_request.productUpdateInfoList[i].sellEndDate == null)
            {
                //(*) ���N�G�X�g�f�[�^.�����X�V���̔��t�J�n����NULL�̏ꍇ�A
                //�擾�����ݓ�����.getDataSourceObject()��get���t�J�n��()�̖߂�l�Ƃ���B
                //�i���t�I�����A���J�n���A���I���������l�Ɏ擾���邱�Ɓj
                
                Date l_datBuyStartDate = 
                    l_request.productUpdateInfoList[i].buyStartDate;
                Date l_datBuyEndDate = 
                    l_request.productUpdateInfoList[i].buyEndDate;
                Date l_datSellStartDate = 
                    l_request.productUpdateInfoList[i].sellStartDate;
                Date l_datSellEndDate = 
                    l_request.productUpdateInfoList[i].sellEndDate;
                
                if (l_datBuyStartDate == null)
                {
                    l_datBuyStartDate = l_ruitoProductRow.getBuyStartDate();
                }
                if (l_datBuyEndDate == null)
                {
                    l_datBuyEndDate = l_ruitoProductRow.getBuyEndDate();
                }
                if (l_datSellStartDate == null)
                {
                    l_datSellStartDate = l_ruitoProductRow.getSellStartDate();
                }
                if (l_datSellEndDate == null)
                {
                    l_datSellEndDate = l_ruitoProductRow.getSellEndDate();
                }                
                
                //1.7.3 ���t�J�n��(*)�����t�I����(*)�̏ꍇ�A��O���X���[����B
                //���J�n��(*)�����I����(*)�̏ꍇ�A��O���X���[����B
                if (WEB3DateUtility.compareToDay(l_datBuyStartDate, l_datBuyEndDate) >= 0 )
                {
                    log.debug("���t�J�n��(*)�����t�I����(*)�̏ꍇ");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01240,
                        getClass().getName() + "." + STR_METHOD_NAME, 
                        "���t�J�n��(*)�����t�I����(*)�̏ꍇ");
                }
                if (WEB3DateUtility.compareToDay(l_datSellStartDate, l_datSellEndDate) >= 0)
                {
                    log.debug("���J�n��(*)�����I����(*)�̏ꍇ");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01241,
                        getClass().getName() + "." + STR_METHOD_NAME, 
                        "���J�n��(*)�����I����(*)�̏ꍇ");
                }
            }
        }
        //1.8 create���X�|���X( )
        WEB3AdminRuitoTradeStopConfirmResponse l_adminRuitoTradeStopConfirmResponse =
            (WEB3AdminRuitoTradeStopConfirmResponse) 
                l_request.createResponse();   
        
        log.exiting(STR_METHOD_NAME);
        return l_adminRuitoTradeStopConfirmResponse;
    }
    
    /**
     * (submit�����ʔ�����~)<BR>
     * �ݓ������ʔ�����~�������������{����B<BR>
     * �V�[�P���X�}�u�i�ݓ��Ǘ��ҁj�����ʔ�����~�X�V�v�Q��<BR>
     * @@param l_request - �ݓ������ʔ�����~�������N�G�X�g
     * @@return webbroker3.common.message.WEB3AdminRuitoTradeStopCompleteResponse<BR>
     * @@throws WEB3BaseException <BR>
     * @@roseuid 406932820270
     */
    private WEB3AdminRuitoTradeStopCompleteResponse submitTradeStop(
            WEB3AdminRuitoTradeStopCompleteRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitTradeStop(WEB3AdminRuitoTradeStopCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 ���͓��e�`�F�b�N 
        l_request.validate();
        
        //1.3 ���O�C�������Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4 �Y���̊Ǘ��҂����̋@@�\���g���邩�����`�F�b�N���s���B 
        //validate����(String, boolean)
        //[����] 
        //�@@�@@�\�J�e�S���[�R�[�h���h�ݓ��i�����Ǘ��j�h 
        //  is�X�V�F false 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.ADMIN_RUITO_TRADE_STOP,
            true);
        
        //1.5 �Ǘ��҂̈Ïؔԍ����`�F�b�N����B 
        l_web3Administrator.validateTradingPassword(l_request.password);
        
        //1.6 �Ǘ��҃I�u�W�F�N�g��菊������،���Ђ��擾����B
        Institution l_institution = l_web3Administrator.getInstitution();
        
        //1.7 �I�y���[�V�������t�`�F�b�N
        //���ݓ��t�擾
        //GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
        Timestamp l_tsSystemTime =  GtlUtils.getTradingSystem().getSystemTimestamp();
        
        //���N�G�X�g�f�[�^.�I�y���[�V�������t != ���ݓ��t�̏ꍇ�A��O���X���[����B
        if (!(WEB3DateUtility.compareToDay(l_request.operationDate, l_tsSystemTime) == 0))
        {
            log.debug("�I�y���[�V�������t�����ݓ��t�ł͂���܂���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01354,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "���N�G�X�g�f�[�^.�I�y���[�V�������t != ���ݓ��t�̏ꍇ");                       
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3RuitoProductManager l_ruitoProductManager =   
            (WEB3RuitoProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getProductManager();
        
        log.debug("���N�G�X�g�f�[�^.�����X�V���̖߂�l�̌��� = " + 
                l_request.productUpdateInfoList.length);
        
        //1.8 ���N�G�X�g�f�[�^.�����X�V���̖߂�l�̌������A�J��Ԃ�
        for (int i = 0; i < l_request.productUpdateInfoList.length; i++)
        {
            //1.8.1 �ݓ������̎擾       
            RuitoProduct l_ruitoProduct = null;
            try
            {
                l_ruitoProduct = 
                    l_ruitoProductManager.getRuitoProduct(
                        l_institution,
                        l_request.productUpdateInfoList[i].ruitoProductCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__");                
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00193,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        
            RuitoProductRow l_ruitoProductRow =  
                (RuitoProductRow)l_ruitoProduct.getDataSourceObject();
            RuitoProductParams l_ruitoProductParams = 
                new RuitoProductParams(l_ruitoProductRow);
            
            //1.8.2 �ȉ��̂����ꂩ��NULL�̏ꍇ
            if (l_request.productUpdateInfoList[i].buyStartDate == null ||
                l_request.productUpdateInfoList[i].buyEndDate == null ||
                l_request.productUpdateInfoList[i].sellStartDate == null ||
                l_request.productUpdateInfoList[i].sellEndDate == null)
            {
                //(*) ���N�G�X�g�f�[�^.�����X�V���̔��t�J�n����NULL�̏ꍇ�A
                //�擾�����ݓ�����.getDataSourceObject()��get���t�J�n��()�̖߂�l�Ƃ���B
                //�i���t�I�����A���J�n���A���I���������l�Ɏ擾���邱�Ɓj
                
                Date l_datBuyStartDate = 
                    l_request.productUpdateInfoList[i].buyStartDate;
                Date l_datBuyEndDate = 
                    l_request.productUpdateInfoList[i].buyEndDate;
                Date l_datSellStartDate = 
                    l_request.productUpdateInfoList[i].sellStartDate;
                Date l_datSellEndDate = 
                    l_request.productUpdateInfoList[i].sellEndDate;
                
                if (l_datBuyStartDate == null)
                {
                    l_datBuyStartDate = l_ruitoProductRow.getBuyStartDate();
                }
                if (l_datBuyEndDate == null)
                {
                    l_datBuyEndDate = l_ruitoProductRow.getBuyEndDate();
                }
                if (l_datSellStartDate == null)
                {
                    l_datSellStartDate = l_ruitoProductRow.getSellStartDate();
                }
                if (l_datSellEndDate == null)
                {
                    l_datSellEndDate = l_ruitoProductRow.getSellEndDate();
                }                
                
                //1.7.3 ���t�J�n��(*)�����t�I����(*)�̏ꍇ�A��O���X���[����B
                //���J�n��(*)�����I����(*)�̏ꍇ�A��O���X���[����B
                if (WEB3DateUtility.compareToDay(l_datBuyStartDate, l_datBuyEndDate) >= 0 )
                {
                    log.debug("���t�J�n��(*)�����t�I����(*)�̏ꍇ");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01240,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "���t�J�n��(*)�����t�I����(*)�̏ꍇ");
                }
                if (WEB3DateUtility.compareToDay(l_datSellStartDate, l_datSellEndDate) >= 0)
                {
                    log.debug("���J�n��(*)�����I����(*)�̏ꍇ");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01241,
                        getClass().getName() + "." + STR_METHOD_NAME, 
                        "���J�n��(*)�����I����(*)�̏ꍇ");
                }
            }
            
            try
            {
                //1.8.3 �������̍X�V
				QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                //(*)
                //�E���N�G�X�g�f�[�^�̒l��NULL�̏ꍇ�A���̍��ڂւ̒l�Z�b�g�͍s��Ȃ��B
                //�E���N�G�X�g�f�[�^�̃Z�b�g����l���S��NULL�������ꍇ�A���̗�ւ̍X�V�����͍s��Ȃ��B
                if (l_request.productUpdateInfoList[i].buyStartDate != null)
                {
                    //�E���t�J�n�������N�G�X�g�f�[�^.�����X�V���.���t�J�n��(*)
                    l_ruitoProductParams.setBuyStartDate(
                            l_request.productUpdateInfoList[i].buyStartDate);
                }
                if (l_request.productUpdateInfoList[i].buyEndDate != null)
                {
                    //�E���t�I���������N�G�X�g�f�[�^.�����X�V���.���t�I����(*)
                    l_ruitoProductParams.setBuyEndDate(
                            l_request.productUpdateInfoList[i].buyEndDate);
                }
                if (l_request.productUpdateInfoList[i].sellStartDate != null)
                {
                    //�E���J�n�������N�G�X�g�f�[�^.�����X�V���.���J�n��(*)
                    l_ruitoProductParams.setSellStartDate(
                            l_request.productUpdateInfoList[i].sellStartDate);
                }
                if (l_request.productUpdateInfoList[i].sellEndDate != null)
                {
                    //�E���I���������N�G�X�g�f�[�^.�����X�V���.���I����(*)
                    l_ruitoProductParams.setSellEndDate(
                            l_request.productUpdateInfoList[i].sellEndDate);
                }
                
                //�E�X�V�҃R�[�h���Ǘ��҃I�u�W�F�N�g���擾�����Ǘ��҃R�[�h            
                l_ruitoProductParams.setLastUpdater(
                        l_web3Administrator.getAdministratorCode());
                
                //�E�X�V���t�i�I�����C���j���擾�������ݓ��t
                l_ruitoProductParams.setOnlineUpdatedTimestamp(l_tsSystemTime);
                
                //�E���N�G�X�g�f�[�^�̃Z�b�g����l���S��NULL�������ꍇ�A���̗�ւ̍X�V�����͍s��Ȃ��B
                if (l_request.productUpdateInfoList[i].buyStartDate != null ||
					l_request.productUpdateInfoList[i].buyEndDate != null ||
					l_request.productUpdateInfoList[i].sellStartDate != null ||
					l_request.productUpdateInfoList[i].sellEndDate != null)
                {
                    log.debug("l_ruitoProductParams = " + l_ruitoProductParams);
                    l_queryProcessor.doUpdateQuery(l_ruitoProductParams);
                }
                
            	//1.8.5 �|reset��t���ԋ敪()�̎��{
            	//[reset��t���ԋ敪�ɓn������]
            	//�Eget�ݓ������ꗗ()�̖߂�l�in���ځj�̗ݓ��^�C�v���hMMF�h�̏ꍇ�A�hMMF�i�ݒ�j�h���Z�b�g
            	//�Eget�ݓ������ꗗ()�̖߂�l�in���ځj�̗ݓ��^�C�v���h����F�h�̏ꍇ�A�h����F�h���Z�b�g�B
            	String l_strTradingTimeType = "";
            	if (RuitoTypeEnum.MMF.equals(l_ruitoProductRow.getRuitoType()))
            	{
                	log.debug("get�ݓ������ꗗ()�̖߂�l�in���ځj�̗ݓ��^�C�v���hMMF�h�̏ꍇ");
                	l_strTradingTimeType = WEB3TradingTimeTypeDef.MMF_SET;                
            	}
            	else if (RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoProductRow.getRuitoType()))
            	{
                	log.debug("get�ݓ������ꗗ()�̖߂�l�in���ځj�̗ݓ��^�C�v���h����F�h�̏ꍇ");
                	l_strTradingTimeType = WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND;
            	}
            	log.debug("reset��t���ԋ敪 = " + l_strTradingTimeType);
            	WEB3GentradeTradingTimeManagement.resetTradingTimeType(l_strTradingTimeType);
                
      	   		//1.8.6 setTimestamp( )
        	    WEB3GentradeTradingTimeManagement.setTimestamp();
				
				//1.8.7 ��������I�u�W�F�N�g�̎擾��������I�u�W�F�N�g�ƂƂ��āA
				//�g���ݓ���������I�u�W�F�N�g���擾����B
				RuitoTradedProduct l_ruitoTradedProduct = null;
				try
				{
					l_ruitoTradedProduct = 
						l_ruitoProductManager.getRuitoTradedProduct(
							l_institution,
							l_request.productUpdateInfoList[i].ruitoProductCode);
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
				
				//1.8.9 ���������擾���� 
				Date l_datBizDate = 
					WEB3GentradeTradingTimeManagement.getOrderBizDate();
				String l_strBizDate = 
					WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
				
				//1.8.10 is�c�Ɠ��i���N�G�X�g.�I�y���[�V�������t�j
				Date l_dateSystemDate = WEB3DateUtility.getDate(
					WEB3DateUtility.formatDate(l_tsSystemTime,"yyyyMMdd"),"yyyyMMdd");
				Timestamp l_tsSystemDate = new Timestamp(l_dateSystemDate.getTime()); 
				boolean l_blnBizDate = this.isBizDate(l_tsSystemDate);

				// �������̎�������iUPDQ�j�I�u�W�F�N�g�̍X�V			
				// ��������I�u�W�F�N�g�擾����
				//�|�ȉ��̏����ŁA�u�ݓ���������e�[�u���v����������B
				//[��������]
				//�����R�[�h����������I�u�W�F�N�g�̎������ID and
				//�L������get������()�̖߂�l
				RuitoTradedProductRow l_ruitoTradedProductRow = null;
				RuitoTradedProductUpdqRow l_ruitoTradedProductUpdqRow = null;

				try
				{
					String l_strWhereClause = 
							"traded_product_id = ? and valid_for_biz_date = ?";
							
					log.debug("�u�ݓ���������e�[�u���v������");
					log.debug("�������ID�� " + 
							l_ruitoTradedProduct.getTradedProductId() + "");
					log.debug("�L������ " + l_strBizDate);
                  
					//DataQueryException,DataNetworkException
					List l_listRutioTradedProductRows =
						Processors.getDefaultProcessor().doFindAllQuery(
							RuitoTradedProductRow.TYPE,
							l_strWhereClause,
							new Object[] { 
								new Long(l_ruitoTradedProduct.getTradedProductId()), 
										l_strBizDate });
										
					//�|�߂�l = 0���̏ꍇ�A�������UPDQ�I�u�W�F�N�g����������B
					log.debug("size ="  + l_listRutioTradedProductRows.size());
					if (l_listRutioTradedProductRows.size() == 0)
					{
						//�������UPDQ�I�u�W�F�N�g�擾����
						//�|�ȉ��̏����ŁA�u�ݓ���������ꎞ�e�[�u���v����������B
						//[��������]
						//�������ID����������I�u�W�F�N�g�̎������ID and
						//�L������get������()�̖߂�l
						log.debug("�u�ݓ���������ꎞ�e�[�u���v������");
						log.debug("�������ID�� " + 
								l_ruitoTradedProduct.getTradedProductId() + "");
						log.debug("�L������ " + l_strBizDate);
                  
						//DataQueryException,DataNetworkException
						List l_listRutioTradedProductUpdqRows =
							Processors.getDefaultProcessor().doFindAllQuery(
								RuitoTradedProductUpdqRow.TYPE,
								l_strWhereClause,
								new Object[] { 
									new Long(l_ruitoTradedProduct.getTradedProductId()), 
											l_strBizDate });
						//�|�߂�l !=1���̏ꍇ�A�i�f�[�^�s�����j�Ƃ��ė�O���X���[����B
						log.debug("size ="  + l_listRutioTradedProductUpdqRows.size());
						if (l_listRutioTradedProductUpdqRows.size() != 1)
						{
							log.debug("�f�[�^�s�����G���[");
							throw new WEB3SystemLayerException(
							WEB3ErrorCatalog.SYSTEM_ERROR_80006,
								getClass().getName() + "." + STR_METHOD_NAME, 
								"�߂�l !=1���̏ꍇ");                       
						}
					
						l_ruitoTradedProductUpdqRow = 
							(RuitoTradedProductUpdqRow)l_listRutioTradedProductUpdqRows.get(0);
						log.debug("l_ruitoTradedProductUpdqRow = " + l_ruitoTradedProductUpdqRow);
						
						//�������Updq�I�u�W�F�N�g�̍X�V
						//�ݓ���������ꎞ�e�[�u��Params�Ɉȉ��̃v���p�e�B���Z�b�g���A
						//���̓��e�ōX�V�������s���B
						RuitoTradedProductUpdqParams l_ruitoTradedProductUpdqParams =
							 new RuitoTradedProductUpdqParams(l_ruitoTradedProductUpdqRow);
							
						// is�c�Ɠ���true�̏ꍇ
						if (l_blnBizDate)
						{
							log.debug("is�c�Ɠ�()��true�������ꍇ");               
							if (l_request.productUpdateInfoList[i].curBuyPosDiv != null)
							{
								//�E���t��~�����N�G�X�g�f�[�^.�����X�V���.���t�\�敪�i�����j
								l_ruitoTradedProductUpdqParams.setBuyStop(
										l_request.productUpdateInfoList[i].curBuyPosDiv);
							}
							if (l_request.productUpdateInfoList[i].curSellPosDiv != null)
							{		
								//�E����~�����N�G�X�g�f�[�^.�����X�V���.���\�敪�i�����j
								l_ruitoTradedProductUpdqParams.setSellStop(
										l_request.productUpdateInfoList[i].curSellPosDiv);
							}
				
							//�E���N�G�X�g�f�[�^�̃Z�b�g����l���S��NULL�������ꍇ�A���̗�ւ̍X�V�����͍s��Ȃ��B
							if (l_request.productUpdateInfoList[i].curBuyPosDiv != null ||
									l_request.productUpdateInfoList[i].curSellPosDiv != null)
							{
								//�E�X�V�҃R�[�h���Ǘ��҃I�u�W�F�N�g���擾�����Ǘ��҃R�[�h
								l_ruitoTradedProductUpdqParams.setLastUpdater(
										l_web3Administrator.getAdministratorCode());
               
								//�E�X�V���t�i�I�����C���j���擾�������ݓ��t
								l_ruitoTradedProductUpdqParams.setOnlineUpdatedTimestamp(
										l_tsSystemTime);
               
								log.debug("l_ruitoTradedProductUpdqParams = " + 
									 l_ruitoTradedProductUpdqParams);
								l_queryProcessor.doUpdateQuery(l_ruitoTradedProductUpdqParams);
							}
						}
						// is�c�Ɠ���false�̏ꍇ
						else if (!l_blnBizDate)
						{
							log.debug("is�c�Ɠ�()��false�������ꍇ");  
							if (l_request.productUpdateInfoList[i].nextBuyPosDiv != null)
							{
								//�E���t��~�����N�G�X�g�f�[�^.�����X�V���.���t�\�敪�i�����j
								l_ruitoTradedProductUpdqParams.setBuyStop(
										l_request.productUpdateInfoList[i].nextBuyPosDiv);
							}
							if (l_request.productUpdateInfoList[i].nextSellPosDiv != null)
							{
								//�E����~�����N�G�X�g�f�[�^.�����X�V���.���\�敪�i�����j
								l_ruitoTradedProductUpdqParams.setSellStop(
										l_request.productUpdateInfoList[i].nextSellPosDiv);
							}
				
							//�E���N�G�X�g�f�[�^�̃Z�b�g����l���S��NULL�������ꍇ�A���̗�ւ̍X�V�����͍s��Ȃ��B
							if (l_request.productUpdateInfoList[i].nextBuyPosDiv != null ||
									l_request.productUpdateInfoList[i].nextSellPosDiv != null)
							{
								//�E�X�V�҃R�[�h���Ǘ��҃I�u�W�F�N�g���擾�����Ǘ��҃R�[�h
								l_ruitoTradedProductUpdqParams.setLastUpdater(
										l_web3Administrator.getAdministratorCode());
               					//�E�X�V���t�i�I�����C���j���擾�������ݓ��t
								l_ruitoTradedProductUpdqParams.setOnlineUpdatedTimestamp(
									l_tsSystemTime);
               					log.debug("l_ruitoTradedProductUpdqParams = " + 
											l_ruitoTradedProductUpdqParams);
								l_queryProcessor.doUpdateQuery(l_ruitoTradedProductUpdqParams);
							}
						}
					}
					
					//�|�߂�l >1���̏ꍇ�A�i�f�[�^�s�����j�Ƃ��ė�O���X���[����B
					else if (l_listRutioTradedProductRows.size() > 1)
					{
						log.debug("�f�[�^�s�����G���[");
						throw new WEB3SystemLayerException(
						WEB3ErrorCatalog.SYSTEM_ERROR_80006,
							getClass().getName() + "." + STR_METHOD_NAME, 
							"�߂�l >1���̏ꍇ");                       
					}
					else
					{
						l_ruitoTradedProductRow = (RuitoTradedProductRow)l_listRutioTradedProductRows.get(0);
						log.debug("l_ruitoTradedProductRow = " + l_ruitoTradedProductRow);
						//��������I�u�W�F�N�g�̍X�V
						//�ݓ���������e�[�u��Params�Ɉȉ��̃v���p�e�B���Z�b�g���A
						//���̓��e�ōX�V�������s���B
						RuitoTradedProductParams l_ruitoTradedProductParams = 
							new RuitoTradedProductParams(l_ruitoTradedProductRow);

						if (l_request.productUpdateInfoList[i].curBuyPosDiv != null)
						{
							//�E���t��~�����N�G�X�g�f�[�^.�����X�V���.���t�\�敪�i�����j
							l_ruitoTradedProductParams.setBuyStop(
									l_request.productUpdateInfoList[i].curBuyPosDiv);
						}
						if (l_request.productUpdateInfoList[i].curSellPosDiv != null)
						{
							//�E����~�����N�G�X�g�f�[�^.�����X�V���.���\�敪�i�����j
							l_ruitoTradedProductParams.setSellStop(
									l_request.productUpdateInfoList[i].curSellPosDiv);
						}
						//�E���N�G�X�g�f�[�^�̃Z�b�g����l���S��NULL�������ꍇ�A���̗�ւ̍X�V�����͍s��Ȃ��B
						if (l_request.productUpdateInfoList[i].curBuyPosDiv != null ||
								l_request.productUpdateInfoList[i].curSellPosDiv != null)
						{
							//�E�X�V�҃R�[�h���Ǘ��҃I�u�W�F�N�g���擾�����Ǘ��҃R�[�h
							l_ruitoTradedProductParams.setLastUpdater(
									l_web3Administrator.getAdministratorCode());
               
							//�E�X�V���t�i�I�����C���j���擾�������ݓ��t
							l_ruitoTradedProductParams.setOnlineUpdatedTimestamp(
									l_tsSystemTime);
              
							log.debug("l_ruitoTradedProductParams = " + 
									l_ruitoTradedProductParams);
							l_queryProcessor.doUpdateQuery(l_ruitoTradedProductParams);
						}
					}
				}
				catch (DataQueryException l_ex)
				{
					log.error("__DataQueryException__");
					throw new WEB3BaseRuntimeException(
						WEB3ErrorCatalog.SYSTEM_ERROR_80003,
						this.getClass().getName() + "." + STR_METHOD_NAME,
						l_ex.getMessage(),
						l_ex);
				}
				catch (DataNetworkException l_ex)
				{
					log.error("__DataNetworkException__");
					throw new WEB3BaseRuntimeException(
						WEB3ErrorCatalog.SYSTEM_ERROR_80003,
						this.getClass().getName() + "." + STR_METHOD_NAME,
						l_ex.getMessage(),
						l_ex);
				}
            
				// is�c�Ɠ���true�̏ꍇ�A���c�Ɠ����̎������UPDQ�I�u�W�F�N�g�̍X�V
				if (l_blnBizDate)
				{					
					//[roll�ɓn������]
					//���Z�^���Z�������P���Z�b�g�B�i���������w��j
					int l_intRoll = 0;
					RuitoTradedProductUpdqRow l_ruitoTradedProductUpdqRowNextDate = null;
					String l_strNextBizDate = null;
					Date l_datNextBizDate = null;
					try
					{
						log.debug("�i���������w��j");
						l_intRoll = 1;
                   
						log.debug("[roll�ɓn������]=" + l_intRoll);
						l_datNextBizDate = new WEB3GentradeBizDate(
								new Timestamp(l_datBizDate.getTime())).roll(l_intRoll);
               
						l_strNextBizDate = 
							WEB3DateUtility.formatDate(l_datNextBizDate, "yyyyMMdd");
                   
						// �������UPDQ�I�u�W�F�N�g�擾����
						//�|�ȉ��̏����ŁA�u�ݓ���������ꎞ�e�[�u���v����������B
						//[��������]
						//�������ID����������I�u�W�F�N�g�̎������ID and
						//�L������roll()�̖߂�l
						String l_strWhereClause = 
								"traded_product_id = ? and valid_for_biz_date = ?";
                   
						log.debug("�������ID�� " + 
								l_ruitoTradedProduct.getTradedProductId() + "");
						log.debug("�L������ " + l_strNextBizDate);
                   
						//DataQueryException,DataNetworkException
						List l_lisRows =
							Processors.getDefaultProcessor().doFindAllQuery(
								RuitoTradedProductUpdqRow.TYPE,
								l_strWhereClause,
								new Object[] { 
									new Long(l_ruitoTradedProduct.getTradedProductId()), 
											l_strNextBizDate });
    					//�|�߂�l !=1���̏ꍇ�A�i�f�[�^�s�����j�Ƃ��ė�O���X���[����B
						log.debug("size ="  + l_lisRows.size());
						if (l_lisRows.size() != 1)
						{
							log.debug("�f�[�^�s�����G���[");
							throw new WEB3SystemLayerException(
							WEB3ErrorCatalog.SYSTEM_ERROR_80006,
								getClass().getName() + "." + STR_METHOD_NAME, 
								"�߂�l !=1���̏ꍇ");                       
						}
						l_ruitoTradedProductUpdqRowNextDate = 
							(RuitoTradedProductUpdqRow)l_lisRows.get(0);
						log.debug("l_ruitoTradedProductUpdqRowNextDate = " 
								+ l_ruitoTradedProductUpdqRowNextDate);
					
						//�������UPDQ�I�u�W�F�N�g�̍X�V
						//�ݓ���������ꎞ�e�[�u��Params�Ɉȉ��̃v���p�e�B���Z�b�g���A
						//���̓��e�ōX�V�������s���B
						RuitoTradedProductUpdqParams l_ruitoTradedProductUpdqParamsNextDate = 
							new RuitoTradedProductUpdqParams(l_ruitoTradedProductUpdqRowNextDate);

						if (l_request.productUpdateInfoList[i].nextBuyPosDiv != null)
						{
							//�E���t��~�����N�G�X�g�f�[�^.�����X�V���.���t�\�敪�i�����j
							l_ruitoTradedProductUpdqParamsNextDate.setBuyStop(
									l_request.productUpdateInfoList[i].nextBuyPosDiv);
						}
						if (l_request.productUpdateInfoList[i].nextSellPosDiv != null)
						{
							//�E����~�����N�G�X�g�f�[�^.�����X�V���.���\�敪�i�����j
							l_ruitoTradedProductUpdqParamsNextDate.setSellStop(
									l_request.productUpdateInfoList[i].nextSellPosDiv);
						}               
              
						//�E���N�G�X�g�f�[�^�̃Z�b�g����l���S��NULL�������ꍇ�A���̗�ւ̍X�V�����͍s��Ȃ��B
						if (l_request.productUpdateInfoList[i].nextBuyPosDiv != null ||
								l_request.productUpdateInfoList[i].nextSellPosDiv != null)
						{
							//�E�X�V�҃R�[�h���Ǘ��҃I�u�W�F�N�g���擾�����Ǘ��҃R�[�h
							l_ruitoTradedProductUpdqParamsNextDate.setLastUpdater(
									l_web3Administrator.getAdministratorCode());
             
							//�E�X�V���t�i�I�����C���j���擾�������ݓ��t
							l_ruitoTradedProductUpdqParamsNextDate.setOnlineUpdatedTimestamp(
									l_tsSystemTime);
							
							log.debug("l_ruitoTradedProductUpdqParamsNextDate = "
							 		 + l_ruitoTradedProductUpdqParamsNextDate);
							l_queryProcessor.doUpdateQuery(l_ruitoTradedProductUpdqParamsNextDate);
						}
					}
					catch (DataQueryException l_ex)
					{
						log.error("__DataQueryException__");
						throw new WEB3BaseRuntimeException(
							WEB3ErrorCatalog.SYSTEM_ERROR_80003,
							this.getClass().getName() + "." + STR_METHOD_NAME,
							l_ex.getMessage(),
							l_ex);
					}
					catch (DataNetworkException l_ex)
					{
						log.error("__DataNetworkException__");
						throw new WEB3BaseRuntimeException(
							WEB3ErrorCatalog.SYSTEM_ERROR_80003,
							this.getClass().getName() + "." + STR_METHOD_NAME,
							l_ex.getMessage(),
							l_ex);
					}
				}
			}            
			catch (DataQueryException l_ex)
			{
				log.error("__DataQueryException__");
				throw new WEB3BaseRuntimeException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					l_ex.getMessage(),
					l_ex);
			}
			catch (DataNetworkException l_ex)
			{
				log.error("__DataNetworkException__");
				throw new WEB3BaseRuntimeException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					l_ex.getMessage(),
					l_ex);
			}
        }
        //1.9 create���X�|���X( )       
        WEB3AdminRuitoTradeStopCompleteResponse l_adminRuitoTradeStopCompleteResponse =
            (WEB3AdminRuitoTradeStopCompleteResponse) 
                l_request.createResponse();   
        
        log.exiting(STR_METHOD_NAME);
        return l_adminRuitoTradeStopCompleteResponse;
    }
    
    /**
     * (is�c�Ɠ�)<BR>
     *����.�Ώۓ��t���x�����ǂ����𔻒肷��B <BR>
     *<BR>
     *�P�j����.�Ώۓ��t���h�y�j���h�܂��́h���j���h�̏ꍇ�Afalse��ԋp����B<BR> 
     *<BR>
     *�Q�j�ȉ��̏����Łu�J�����_�[�e�[�u���v����������B<BR>
     *[��������] <BR>
�@@   *���t������.�Ώۓ��t and <BR>
�@@   *�c�Ɠ��敪���h��c�Ɠ��h <BR>
     *�|�������ʂ̌�����0���̏ꍇ�Afalse��ԋp����B<BR>
     *<BR>
     *�R�j��L�ȊO�̏ꍇ�Atrue��ԋp����B<BR>
     * @@param l_timestap - �Ώۓ��t
     * @@return l_bln
     * @@roseuid 406932820270
     */
    private boolean isBizDate(Timestamp l_tsObjectDate)
    {
        final String STR_METHOD_NAME =
            "submitTradeStop(WEB3AdminRuitoTradeStopCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);   
        
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.setTime(l_tsObjectDate);
        
        int l_intWeekDiv = l_calendar.get(Calendar.DAY_OF_WEEK) - 1;
        
        //�P�j����.�Ώۓ��t���h�y�j���h�܂��́h���j���h�̏ꍇ�Afalse��ԋp����B 
        if (l_intWeekDiv == 6 || l_intWeekDiv == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�Q�j�ȉ��̏����Łu�J�����_�[�e�[�u���v����������B
        try
        {
            String l_strWhereClause = 
                "holiday = ? and biz_date_type = ?";
            //DataQueryException,DataNetworkException
            List l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                        CalendarRow.TYPE,
                    l_strWhereClause,
                    new Object[] { 
                        l_tsObjectDate, 
                        WEB3BizDateTypeDef.NO_BIZ_DATE });

            if (l_lisRows.size() > 0)
            {
                log.exiting(STR_METHOD_NAME);
                return false;                      
            }           
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return true;
    }
}
@
