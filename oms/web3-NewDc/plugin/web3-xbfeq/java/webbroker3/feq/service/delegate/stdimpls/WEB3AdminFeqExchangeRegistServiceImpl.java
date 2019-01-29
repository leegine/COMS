head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExchangeRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������ב֓o�^�T�[�r�XImpl(WEB3AdminFeqExchangeRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 �Ջ`�g(���u) �V�K�쐬
                   2005/08/03 ����(���u) ���r���[  
Revesion History : 2008/01/23 �đo�g(���u) ���f��No.372
Revesion History : 2009/06/24 �đo�g(���u) ���f��No.499,500,�c�a�X�V�d�lNo.102
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.define.WEB3FeqRateDivDef;
import webbroker3.feq.define.WEB3LastUpdaterDef;
import webbroker3.feq.message.WEB3AdminFeqExchangeRegistCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqExchangeRegistCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqExchangeRegistConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqExchangeRegistConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqExchangeRegistInputRequest;
import webbroker3.feq.message.WEB3AdminFeqExchangeRegistInputResponse;
import webbroker3.feq.message.WEB3FeqExchangeUnit;
import webbroker3.feq.service.delegate.WEB3AdminFeqExchangeRegistService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.data.GenCurrencyDao;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.gentrade.data.GenCurrencyRow;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.tradingpower.define.WEB3TPProcessManagementIdDef;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O�������ב֓o�^�T�[�r�XImpl)<BR>
 * �O�������ב֓o�^�T�[�r�X�����N���X<BR>
 * 
 * @@author �Ջ`�g
 * @@version 1.0
 */
public class WEB3AdminFeqExchangeRegistServiceImpl implements WEB3AdminFeqExchangeRegistService 
{
	  /**
     * <p>
     * �i���O�o�̓��[�e�B���e�B�j�B
     * </p>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqExchangeRegistServiceImpl.class);
    
    /**
     * @@roseuid 42CE39EF037A
     */
    public WEB3AdminFeqExchangeRegistServiceImpl() 
    {
     
    }
    
    /**
     * �O�������ב֓o�^�T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B<BR>
     * <BR>
     *   get���͉��()<BR>
     *   validate�ב֓o�^()<BR>
     *   submit�ב֓o�^()<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4210847703CD
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response;

        //���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B
        //�|get���͉��() 
        if (l_request instanceof WEB3AdminFeqExchangeRegistInputRequest)
        {
            l_response = 
                getInputScreen((WEB3AdminFeqExchangeRegistInputRequest) l_request);
        }
        //�|validate�ב֓o�^() 
        else if (l_request instanceof WEB3AdminFeqExchangeRegistConfirmRequest)
        {
            l_response = 
                validateRateRegist((WEB3AdminFeqExchangeRegistConfirmRequest) l_request);
        }
        //�|submit�ב֓o�^() 
        else if (l_request instanceof WEB3AdminFeqExchangeRegistCompleteRequest)
        {
            l_response = 
                submitRateRegist((WEB3AdminFeqExchangeRegistCompleteRequest) l_request);
        } 
        else
        {
            log.debug("error in get necessory request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʎ擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�ב֓o�^�jget���͉�ʁv �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExchangeRegistInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 421084B6012D
     */
    protected WEB3AdminFeqExchangeRegistInputResponse getInputScreen(
        WEB3AdminFeqExchangeRegistInputRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getInputScreen(WEB3AdminFeqExchangeRegistInputRequest) ";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqProductManager l_productMgr = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();

        //1.1 �Ǘ��҃C���X�^���X���擾����
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�Ǘ��҃��̃O�C����񂪑��݂��Ȃ��B");
        }
        
        //1.2 �����̃`�F�b�N���s���B

        //[����]
        //�@@�\�J�e�S���R�[�h�F �h��ב֓o�^�h
        //is�X�V�F true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_BASE_EXCHANGE_REGIST, true);

        //1.3 �،���ЃR�[�h���擾����
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.4 �ʉ݂̔z����擾����B

        //[get�ʉ�()�Ɏw�肷�����]
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()
        WEB3GentradeCurrency[] l_genCurrencys = l_productMgr.getCurrency(l_strInstitutionCode);
        
        int l_intCnt = 0;
        if (l_genCurrencys != null && l_genCurrencys.length > 0)
        {
            l_intCnt = l_genCurrencys.length;
        }
        
        List l_lisExchangeUnits = new ArrayList();
        //1.5 get�ʉ�()�߂�l�̗v�f����LOOP����
        for (int i = 0; i < l_intCnt; i++) 
        {
            //1.5.1 �O�������ב֏��() �C���X�^���X�𐶐�����
            WEB3FeqExchangeUnit l_baseExchangeUnit =
                new WEB3FeqExchangeUnit();
            
            WEB3GentradeCurrency l_genCurrency = l_genCurrencys[i];
            GenCurrencyRow l_currencyRow = (GenCurrencyRow)l_genCurrency.getDataSourceObject();
            
            //1.5.2 �O�������ב֏��v���p�e�B�ɒl���Z�b�g����
            //�ʉ݃R�[�h�F�@@�ʉ�.�ʉ݃R�[�h
            l_baseExchangeUnit.currencyCode = l_currencyRow.getCurrencyCode();
            //���[�g�敪�F�@@�h��בցh
            l_baseExchangeUnit.rateDiv = WEB3FeqRateDivDef.BASE_EXCHANGE;
            //���t�בփ��[�g�F�@@�ʉ�.get���t��בփ��[�g()
            l_baseExchangeUnit.sellExchangeRate = 
                WEB3StringTypeUtility.formatNumber(l_genCurrency.getSellBaseRate());
                if(l_baseExchangeUnit.sellExchangeRate.equals("0"))
                {
                  l_baseExchangeUnit.sellExchangeRate = null;                
                }

            //���t�בփ��[�g�F�@@�ʉ�.get���t��בփ��[�g()
            l_baseExchangeUnit.buyExchangeRate = 
                WEB3StringTypeUtility.formatNumber(l_genCurrency.getBuyBaseRate());
                if(l_baseExchangeUnit.buyExchangeRate.equals("0"))
                {
                  l_baseExchangeUnit.buyExchangeRate = null;                
                }
 
            //�o�^�����F�@@�ʉ�.��ב֍X�V���t
            l_baseExchangeUnit.registDatetime = l_currencyRow.getRateUpdateTimestamp();

            l_lisExchangeUnits.add(l_baseExchangeUnit);

            //1.5.3 �O�������ב֏��() �C���X�^���X�𐶐�����
            WEB3FeqExchangeUnit l_executedExchangeUnit =
                new WEB3FeqExchangeUnit();
            
            //1.5.4 �O�������ב֏��v���p�e�B�ɒl���Z�b�g����
            //�ʉ݃R�[�h�F�@@�ʉ�.�ʉ݃R�[�h
            l_executedExchangeUnit.currencyCode =l_currencyRow.getCurrencyCode();
            //���[�g�敪�F�@@�h���בցh
            l_executedExchangeUnit.rateDiv = WEB3FeqRateDivDef.EXECUTED_EXCHANGE;

            //���t�בփ��[�g�F�@@�ʉ�.get���t���בփ��[�g()            
            l_executedExchangeUnit.sellExchangeRate = 
                WEB3StringTypeUtility.formatNumber(l_genCurrency.getSellExecRate());            
                if(l_executedExchangeUnit.sellExchangeRate.equals("0"))
                {
                  l_executedExchangeUnit.sellExchangeRate = null;                
                }

            //���t�בփ��[�g�F�@@�ʉ�.get���t���בփ��[�g()
            l_executedExchangeUnit.buyExchangeRate =
                WEB3StringTypeUtility.formatNumber(l_genCurrency.getBuyExecRate());
                if(l_executedExchangeUnit.buyExchangeRate.equals("0"))
                {
                  l_executedExchangeUnit.buyExchangeRate = null;            
                }

            //�o�^�����F�@@�ʉ�.���ב֍X�V
            l_executedExchangeUnit.registDatetime = l_currencyRow.getExecRateUpdateTimestamp();

            l_lisExchangeUnits.add(l_executedExchangeUnit);
        }
        //1.6 ���X�|���X�f�[�^�𐶐�����
        WEB3AdminFeqExchangeRegistInputResponse l_response = 
            (WEB3AdminFeqExchangeRegistInputResponse)l_request.createResponse();

        //1.7 ���X�|���X�f�[�^�v���p�e�B�ɒl���Z�b�g����
        //(*)�@@�ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        //���X�|���X.�ב֏��ꗗ =  ���������O�������ב֏��[]
        if (l_intCnt == 0)
        {
            l_response.exchangeList = null;
        }
        else
        {
            WEB3FeqExchangeUnit[] l_feqExchangeUnits = 
                new WEB3FeqExchangeUnit[l_lisExchangeUnits.size()];
            l_lisExchangeUnits.toArray(l_feqExchangeUnits);
            l_response.exchangeList = l_feqExchangeUnits;             
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�ב֓o�^)<BR>
     * �ב֓o�^�m�F���s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�ב֓o�^�jvalidate�ב֓o�^�v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExchangeRegistConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 421084B6014C
     */
    protected WEB3AdminFeqExchangeRegistConfirmResponse validateRateRegist(
        WEB3AdminFeqExchangeRegistConfirmRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateRateRegist(WEB3AdminFeqExchangeRegistConfirmRequest) ";
        log.entering(STR_METHOD_NAME);

        //1.1 ���N�G�X�g�f�[�^�̃`�F�b�N���s��
        l_request.validate();
        
        //1.2 �Ǘ��҃C���X�^���X���擾����
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�Ǘ��҃��̃O�C����񂪑��݂��Ȃ��B");
        }
        
        //1.3 �����̃`�F�b�N���s���B

        //[����]
        //�@@�\�J�e�S���R�[�h�F �h��ב֓o�^�h
        //is�X�V�F true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_BASE_EXCHANGE_REGIST, true);

        //1.4 ���X�|���X�f�[�^�𐶐�����
        WEB3AdminFeqExchangeRegistConfirmResponse l_response =
            new WEB3AdminFeqExchangeRegistConfirmResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�ב֓o�^)<BR>
     * �ב֓o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�ב֓o�^�jsubmit�ב֓o�^�v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExchangeRegistCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 421084B6016B
     */
    protected WEB3AdminFeqExchangeRegistCompleteResponse submitRateRegist(
        WEB3AdminFeqExchangeRegistCompleteRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " submitRateRegist(WEB3AdminFeqExchangeRegistCompleteRequest) ";
        log.entering(STR_METHOD_NAME);

        //1.1 ���N�G�X�g�f�[�^�̃`�F�b�N���s��
        l_request.validate();
        
        //1.2 �Ǘ��҃C���X�^���X���擾����
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�Ǘ��҃��̃O�C����񂪑��݂��Ȃ��B");
        }

        //1.3 �����̃`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���R�[�h�F �h��ב֓o�^�h
        //is�X�V�F true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_BASE_EXCHANGE_REGIST, true);

        int l_intCnt = 0;
        
        if (l_request.exchangeList != null && l_request.exchangeList.length > 0)
        {
            l_intCnt = l_request.exchangeList.length;
        }

        //get�،����( )
        Institution l_institution = l_admin.getInstitution();

        //1.4 ����p�X���[�h�̃`�F�b�N���s���B
        //[validate����p�X���[�h()�Ɏw�肷�����]
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�
        l_admin.validateTradingPassword(l_request.password);

        
        //1.5 ���N�G�X�g�f�[�^.�ב֏��ꗗ[]�̊e�v�f����LOOP����
        for (int i = 0; i < l_intCnt; i++)
        {
            //1.5.1 �ʉ݃e�[�u���Ɉב֏����X�V����B

            //[persist�ʉ�()�Ɏw�肷�����]
            //�ב֏��F�@@���N�G�X�g�f�[�^.�ב֏��ꗗ[index]
            persistCurrency(l_request.exchangeList[i]);
            
        }

        //save�v���Z�X�Ǘ�(�،����)
        this.saveProcessManagement(l_institution);

        //1.6 ���X�|���X�f�[�^�𐶐�����
        WEB3AdminFeqExchangeRegistCompleteResponse l_response =
            new WEB3AdminFeqExchangeRegistCompleteResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (persist�ʉ�)<BR>
     * �ʉ݃e�[�u���Ɉב֏����X�V����B<BR>
     * <BR>
     * ���@@�בփ��[�g�ɓ��͂��Ȃ��ꍇ�i�ב֏��.���t�בփ��[�g == null && �ב�<BR>
     * ���.���t�בփ��[�g == null�j<BR>
     * �@@�������I������B�ireturn;�j<BR>
     * <BR>
     * ���@@�בփ��[�g�ɓ��͂�����ꍇ<BR>
     * �@@�בփ��[�g��ʉ݃e�[�u���ɍX�V����B<BR>
     * �@@�@@�X�V���e�͈ȉ��̒ʂ�B<BR>
     * <BR>
     * �@@�@@��בցi�ב֏��.���[�g�敪 == �h��בցh�j�̏ꍇ�A<BR>
     * �@@�@@�@@�|�yxTrade�z�⑫����.DB�X�V\\21.�i�ǁj�ב֓o�^<BR>
     * �@@�@@�@@�@@�u�ב֓o�^_�ʉ݃e�[�u���d�l.xls#�ב֓o�^_�ʉ݃e�[�u�� DB�X�V<BR>
     * �@@�@@�@@�@@�i��בցj�v�Q�ƁB<BR>
     * <BR>
     * �@@�@@��בցi�ב֏��.���[�g�敪 == �h���בցh�j�̏ꍇ�A<BR>
     * �@@�@@�@@�|�yxTrade�z�⑫����.DB�X�V\\21.�i�ǁj�ב֓o�^<BR>
     * �@@�@@�@@�@@�u�ב֓o�^_�ʉ݃e�[�u���d�l.xls#�ב֓o�^_�ʉ݃e�[�u�� DB�X�V<BR>
     * �@@�@@�@@�@@�i���בցj�v�Q�ƁB<BR>
     * <BR>
     * �@@�@@�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02159<BR>
     * @@param l_exchangeInfo - (�ב֏��)<BR>
     * �ב֏�񃁃b�Z�[�W�f�[�^�I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42BA603901F3
     */
    private void persistCurrency(WEB3FeqExchangeUnit l_exchangeInfo) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " persistCurrency(WEB3FeqExchangeUnit) ";
        log.entering(STR_METHOD_NAME);
        
        log.debug("�ב֏��.���t�בփ��[�g = " + l_exchangeInfo.sellExchangeRate);
        log.debug("�ב֏��.���t�בփ��[�g = " + l_exchangeInfo.buyExchangeRate);
        
        //�ʉ݃e�[�u���Ɉב֏����X�V����B
        //
        //���@@�בփ��[�g�ɓ��͂��Ȃ��ꍇ
        //�i�ב֏��.���t�בփ��[�g == null && �ב֏��.���t�בփ��[�g == null�j
        //�@@�������I������B�ireturn;�j
        if (WEB3StringTypeUtility.isEmpty(l_exchangeInfo.sellExchangeRate)
            && WEB3StringTypeUtility.isEmpty(l_exchangeInfo.buyExchangeRate))
        {
            log.debug("�בփ��[�g�ɓ��͂��Ȃ��ꍇ");
            return;
        }

        //���@@�בփ��[�g�ɓ��͂�����ꍇ
        //�@@�בփ��[�g��ʉ݃e�[�u���ɍX�V����B
        //�@@�@@�X�V���e�͈ȉ��̒ʂ�B
        //
        //�@@�@@��בցi�ב֏��.���[�g�敪 == �h��בցh�j�̏ꍇ�A
        //�@@�@@�@@�|�yxTrade�z�⑫����.DB�X�V\\21.�i�ǁj�ב֓o�^
        //�@@�@@�@@�@@�u�ב֓o�^_�ʉ݃e�[�u���d�l.xls#�ב֓o�^_�ʉ݃e�[�u�� DB�X�V�i��בցj�v�Q�ƁB
        //
        //�@@�@@��בցi�ב֏��.���[�g�敪 == �h���בցh�j�̏ꍇ�A
        //�@@�@@�@@�|�yxTrade�z�⑫����.DB�X�V\\21.�i�ǁj�ב֓o�^
        //�@@�@@�@@�@@�u�ב֓o�^_�ʉ݃e�[�u���d�l.xls#�ב֓o�^_�ʉ݃e�[�u�� DB�X�V�i���בցj�v�Q�ƁB
        //
        //�@@�@@�ȊO�̏ꍇ�A��O���X���[����B
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        String l_strCurrencyCode = l_exchangeInfo.currencyCode;

        log.debug("l_strInstitutionCode = " + l_strInstitutionCode);
        log.debug("l_strCurrencyCode = " + l_strCurrencyCode);
        
        GenCurrencyParams l_currencyParams  = null;
        try
        {
            GenCurrencyRow l_row = 
                GenCurrencyDao.findRowByPk(l_strInstitutionCode, l_strCurrencyCode);
            l_currencyParams = new GenCurrencyParams(l_row);
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        
        //�ב֏��.���t�בփ��[�g        
        double l_dblSellExchangeRate = 0.0D;
        if (!WEB3StringTypeUtility.isEmpty(l_exchangeInfo.sellExchangeRate))
        {
            l_dblSellExchangeRate = Double.parseDouble(l_exchangeInfo.sellExchangeRate);
        }        
        //�ב֏��.���t�בփ��[�g
        double l_dblBuyExchangeRate = 0.0D;
        if (!WEB3StringTypeUtility.isEmpty(l_exchangeInfo.buyExchangeRate))
        {
            l_dblBuyExchangeRate = Double.parseDouble(l_exchangeInfo.buyExchangeRate);
        }        
        
        log.debug("�ב֏��.���[�g�敪 = " + l_exchangeInfo.rateDiv);

        if (WEB3FeqRateDivDef.BASE_EXCHANGE.equals(l_exchangeInfo.rateDiv))
        {
            //���@@�ב֏��.���[�g�敪 == �h��בցh�̏ꍇ�B                
            log.debug("���@@�ב֏��.���[�g�敪 == �h��בցh�̏ꍇ");
            
            //���񔄕t�בփ��[�g   
            //�i�ב֏��.���t�בփ��[�g == null�j�̏ꍇ�A�i�����l�j�B
            //�i�ב֏��.���t�בփ��[�g != null�j�̏ꍇ�A
            //�@@�|���͒l�i�ב֏��.���t�בփ��[�g�j���Z�b�g�B
            //�O�񔄕t�בփ��[�g   
            //�i�ב֏��.���t�בփ��[�g == null�j�̏ꍇ�A�i�����l�j�B
            //�i�ב֏��.���t�בփ��[�g != null�j�̏ꍇ�A
            //�@@�|���񔄕t�בփ��[�g�i���X�V�O�̒l�j���Z�b�g�B                
            if (!WEB3StringTypeUtility.isEmpty(l_exchangeInfo.sellExchangeRate))
            {
                l_currencyParams.setPrevSellRate(l_currencyParams.current_sell_rate);
                l_currencyParams.setCurrentSellRate(l_dblSellExchangeRate);                    
            }
        
            //���񔃕t�בփ��[�g
            //�i�ב֏��.���t�בփ��[�g == null�j�̏ꍇ�A�i�����l�j�B
            //�i�ב֏��.���t�בփ��[�g != null�j�̏ꍇ�A
            //�@@�|���͒l�i�ב֏��.���t�בփ��[�g�j���Z�b�g�B
            //�O�񔃕t�בփ��[�g   
            //�i�ב֏��.���t�בփ��[�g == null�j�̏ꍇ�A�i�����l�j�B
            //�i�ב֏��.���t�בփ��[�g != null�j�̏ꍇ�A
            //�@@�|���񔃕t�בփ��[�g�i���X�V�O�̒l�j���Z�b�g�B
            if (!WEB3StringTypeUtility.isEmpty(l_exchangeInfo.buyExchangeRate))
            {
                l_currencyParams.setPrevBuyRate(l_currencyParams.current_buy_rate);
                
                l_currencyParams.setCurrentBuyRate(l_dblBuyExchangeRate);
            }    

            //��ב֍X�V�҃R�[�h : �Ǘ��҃R�[�h
            l_currencyParams.setRateLastUpdater(l_admin.getAdministratorCode());
            
            //��ב֍X�V���t:���ݓ���
            l_currencyParams.setRateUpdateTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
                
            //�X�V�҃R�[�h:�Ǘ��҃R�[�h
            l_currencyParams.setLastUpdater(l_admin.getAdministratorCode());
            
            //�X�V���t:���ݓ���
            l_currencyParams.setLastUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());

        }
        else if (WEB3FeqRateDivDef.EXECUTED_EXCHANGE.equals(l_exchangeInfo.rateDiv))
        {
            log.debug("���@@�ב֏��.���[�g�敪 == �h���בցh�̏ꍇ");
            //���@@�ב֏��.���[�g�敪 == �h���בցh
            if (!WEB3StringTypeUtility.isEmpty(l_exchangeInfo.sellExchangeRate))
            {
                //�O�񔄕t���בփ��[�g   
                //�i�ב֏��.���t�בփ��[�g == null�j�̏ꍇ�A�i�����l�j�B
                //�i�ב֏��.���t�בփ��[�g != null�j�̏ꍇ�A
                //�@@�|���񔄕t���בփ��[�g�i���X�V�O�̒l�j���Z�b�g�B
                l_currencyParams.setPrevSellExecRate(l_currencyParams.current_sell_exec_rate);
                
                //���񔄕t���בփ��[�g   
                //�i�ב֏��.���t�בփ��[�g == null�j�̏ꍇ�A�i�����l�j�B
                //�i�ב֏��.���t�בփ��[�g != null�j�̏ꍇ�A
                //�@@�|���͒l�i�ב֏��.���t�בփ��[�g�j���Z�b�g�B
                l_currencyParams.setCurrentSellExecRate(l_dblSellExchangeRate);
            }
            
            if (!WEB3StringTypeUtility.isEmpty(l_exchangeInfo.buyExchangeRate))
            {    
                //�O�񔃕t���בփ��[�g   
                //�i�ב֏��.���t�בփ��[�g == null�j�̏ꍇ�A�i�����l�j�B
                //�i�ב֏��.���t�בփ��[�g != null�j�̏ꍇ�A
                //�@@�|���񔃕t���בփ��[�g�i���X�V�O�̒l�j���Z�b�g�B
                l_currencyParams.setPrevBuyExecRate(l_currencyParams.current_buy_exec_rate);
                
                //���񔃕t���בփ��[�g
                //�i�ב֏��.���t�בփ��[�g == null�j�̏ꍇ�A�i�����l�j�B
                //�i�ב֏��.���t�בփ��[�g != null�j�̏ꍇ�A
                //�@@�|���͒l�i�ב֏��.���t�בփ��[�g�j���Z�b�g�B                    
                l_currencyParams.setCurrentBuyExecRate(l_dblBuyExchangeRate);
            }              

            //���ב֍X�V�҃R�[�h : �Ǘ��҃R�[�h
            l_currencyParams.setExecRateLastUpdater(l_admin.getAdministratorCode());

            //���ב֍X�V���t:���ݓ���
            l_currencyParams.setExecRateUpdateTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
                
            //�X�V�҃R�[�h:�Ǘ��҃R�[�h
            l_currencyParams.setLastUpdater(l_admin.getAdministratorCode());
            
            //�X�V���t:���ݓ���
            l_currencyParams.setLastUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());           
        }
        else
        {
            //�ȊO�̏ꍇ�A��O���X���[����B
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02159, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try 
        {
            WEB3DataAccessUtility.updateRow(l_currencyParams);
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return;
    }

    /**
     * (save�v���Z�X�Ǘ�)<BR>
     * �v���Z�X�Ǘ��e�[�u�����X�V����B<BR>
     * <BR>
     * �P�j �ȉ��̏����Łu�v���Z�X�Ǘ��e�[�u���v���猟������B<BR>
     * �@@[��������]<BR>
     * �@@�@@�v���Z�X�h�c��'0006:�]�͍Čv�Z�����'<BR>
     * �@@�@@�،���ЃR�[�h���،���ЃI�u�W�F�N�g.�،���ЃR�[�h<BR>
     * <BR>
     * �Q�j �������ʂ��擾�ł���ꍇ�A<BR>
     * �@@�擾�����������ʐ���LOOP����<BR>
     * �@@�@@�c�a�X�V���s���B<BR>
     * �@@�@@�@@�u�ב֓o�^_�v���Z�X�Ǘ��e�[�u���d�l.xls<BR>
     * �@@�@@�@@�@@�@@#�ב֓o�^_�v���Z�X�Ǘ� DB�X�V(Update)�v�Q�ƁB<BR>
     * <BR>
     * �R�j �������ʂ��擾�ł��Ȃ��ꍇ�A<BR>
     * �@@�R-�P�j���X�R�[�h�ꗗ���擾<BR>
     * �@@�@@�،���ЃI�u�W�F�N�g.getBranches()<BR>
     * <BR>
     * �@@�R-�Q�j�R-�P�j�Ŏ擾�����������ʐ���LOOP����<BR>
     * �@@�@@�c�a�X�V���s���B<BR>
     * �@@�@@�@@�u�ב֓o�^_�v���Z�X�Ǘ��e�[�u���d�l.xls<BR>
     * �@@�@@�@@�@@�@@#�ב֓o�^_�v���Z�X�Ǘ� DB�X�V(Insert)�v�Q�ƁB<BR>
     * <BR>
     * @@param l_institution - (�،����)<BR>
     * �،����<BR>
     * @@throws WEB3BaseException
     */
    private void saveProcessManagement(Institution l_institution) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveProcessManagement(Institution)";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = null;
        //�ȉ��̏����Łu�v���Z�X�Ǘ��e�[�u���v���猟������
        //[��������]
        // �v���Z�X�h�c��'0006:�]�͍Čv�Z�����'
        // �،���ЃR�[�h���،���ЃI�u�W�F�N�g.�،���ЃR�[�h
        StringBuffer l_sbSql = new StringBuffer();
        l_sbSql.append(" process_id = ? ");
        l_sbSql.append(" and institution_code = ? ");

        Object[] l_sqlValues = new Object[]{
            WEB3TPProcessManagementIdDef.TP_DATUM_TIME,
            l_institution.getInstitutionCode()};

        List l_lisResults = null;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisResults = l_queryProcessor.doFindAllQuery(
                ProcessManagementRow.TYPE,
                l_sbSql.toString(),
                l_sqlValues);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (!l_lisResults.isEmpty())
        {
            //�������ʂ��擾�ł���ꍇ
            //�擾�����������ʐ���LOOP����
            //�c�a�X�V���s��
            // �u�ב֓o�^_�v���Z�X�Ǘ��e�[�u���d�l.xls#�ב֓o�^_�v���Z�X�Ǘ� DB�X�V(Update)�v�Q��
            Map l_mapChanges = new HashMap();
            l_mapChanges.put("last_updater", WEB3LastUpdaterDef.RETIMED_BY_AP);
            l_mapChanges.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

            try
            {
                l_queryProcessor.doUpdateAllQuery(
                    ProcessManagementRow.TYPE,
                    l_sbSql.toString(),
                    l_sqlValues,
                    l_mapChanges);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        else
        {
            //�������ʂ��擾�ł��Ȃ��ꍇ
            //���X�R�[�h�ꗗ���擾
            //�،���ЃI�u�W�F�N�g.getBranches()
            Branch[] l_branchs = l_institution.getBranches();

            //�擾�����������ʐ���LOOP����
            //�c�a�X�V���s��
            //�u�ב֓o�^_�v���Z�X�Ǘ��e�[�u���d�l.xls#�ב֓o�^_�v���Z�X�Ǘ� DB�X�V(Insert)�v�Q��
            ProcessManagementParams l_processManagementParams =
                new ProcessManagementParams();

            //�v���Z�X�h�c:'0006'
            l_processManagementParams.setProcessId(WEB3TPProcessManagementIdDef.TP_DATUM_TIME);

            //�،���ЃR�[�h:�،����.�،���ЃR�[�h
            l_processManagementParams.setInstitutionCode(
                l_institution.getInstitutionCode());

            //�����敪:'1'
            l_processManagementParams.setStatus(WEB3StatusDef.DEALT);

            //�ŏI�X�V��:retimed_by_ap
            l_processManagementParams.setLastUpdater(WEB3LastUpdaterDef.RETIMED_BY_AP);

            for (int i = 0; i < l_branchs.length; i++)
            {
                //���X�R�[�h:�،����.���X�R�[�h[i]
                l_processManagementParams.setBranchCode(l_branchs[i].getBranchCode());

                //�ŏI�X�V����:"���ݓ���(GtlUtils.getSystemTimestamp())"
                l_processManagementParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                try
                {
                    l_queryProcessor.doInsertQuery(l_processManagementParams);
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
        }
    }
}
@
