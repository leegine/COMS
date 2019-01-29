head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.38.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOpenAtOrderDLServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O��������t����DL�T�[�r�XImpl(WEB3AdminFeqOpenAtOrderDLServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 �Ջ`�g (���u) �V�K�쐬
Revesion History : 2005/08/03 ����(���u) ���r���[  
Revesion History : 2007/01/16 �����(���u) ���f���@@No.333�Ή�
Revesion History : 2007/02/25 ꎉ�(���u) ���f���@@No.344�Ή�
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketPreferencesNameDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadInputResponse;
import webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadRequest;
import webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqOpenAtOrderDLService;
import webbroker3.feq.WEB3FeqOpenAtOrderDLCSV;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O��������t����DL�T�[�r�XImpl)<BR>
 * �O��������t����DL�T�[�r�X�����N���X<BR>
 *   
 * @@author �Ջ`�g
 * @@version 1.0
 */
public class WEB3AdminFeqOpenAtOrderDLServiceImpl 
    implements WEB3AdminFeqOpenAtOrderDLService 
{
    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqOpenAtOrderDLServiceImpl.class);
    
    /**
     * @@roseuid 42CE39F000AB
     */
    public WEB3AdminFeqOpenAtOrderDLServiceImpl() 
    {
     
    }
    
    /**
     * �O��������t����DL�T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B<BR>
     * <BR>
     *    get���͉��()<BR>
     *    get�_�E�����[�h�t�@@�C��()<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 421494340178
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�[�����w��(null)�ł��B");
        }

        WEB3GenResponse l_response;

        //���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B
        //�|get���͉��()
        if (l_request instanceof WEB3AdminFeqOpenAtOrderDownloadInputRequest)
        {
            l_response = getInputScreen((WEB3AdminFeqOpenAtOrderDownloadInputRequest) l_request);
        } 
        //�|get�_�E�����[�h�t�@@�C��()
        else if (l_request instanceof WEB3AdminFeqOpenAtOrderDownloadRequest)
        {
            l_response = getDownloadFile((WEB3AdminFeqOpenAtOrderDownloadRequest) l_request);
        } 
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʎ擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�ǁj��t����DL�jget���͉�ʁv �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4214947202E0
     */
    protected WEB3AdminFeqOpenAtOrderDownloadInputResponse getInputScreen(
        WEB3AdminFeqOpenAtOrderDownloadInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3AdminFeqOpenAtOrderDownloadInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 ���O�C����񂩂�Ǘ��҃C���X�^���X���擾����
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 �،���ЃR�[�h���擾����
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();
        
        //1.3 �����̃`�F�b�N���s���B 
        //[����] 
        //�@@�\�J�e�S���[�R�[�h�F �@@�\�J�e�S���[�R�[�h.�h�O���i�������Ǘ��j�h 
        //is�X�V�F false 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, 
            false);

        //get�戵�\�s��(�،���ЃR�[�h : String, �����^�C�v : ProductTypeEnum)
        //�戵�\�Ȏs��R�[�h�̈ꗗ���擾����B
        //[����]
        //�،���ЁF�@@get�،���ЃR�[�h()�̖߂�l
        //�����^�C�v�F�@@ProductTypeEnum.�O������
        String[] l_strMarkets =
            WEB3GentradeFeqBranchMarketDealtCond.getHandlingPossibleMarket(
                l_strInstitutionCode,
                ProductTypeEnum.FOREIGN_EQUITY);

        int l_intCnt = 0;

        if (l_strMarkets != null && l_strMarkets.length > 0)
        {
            l_intCnt = l_strMarkets.length;
        }
        else if (l_strMarkets.length == 0)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }

        log.debug("HandlingPossibleMarket length ==== " + l_strMarkets.length);

        //��̃��X�g�𐶐�����
        //�i��s�꒼���p�j
        List l_lisMarketCode1 = new ArrayList();

        //��̃��X�g�𐶐�����
        //�i�s�꒼���p�j
        List l_lisMarketCode2 = new ArrayList();

        //get�戵�\�s��()�̖߂�l�̗v�f����Loop����
        for (int i = 0; i < l_intCnt; i++)
        {
            String l_strMarketCode = l_strMarkets[i];
            MarketRow l_marketRow = null;
            try
            {
                l_marketRow =
                    MarketDao.findRowByInstitutionCodeMarketCode(l_strInstitutionCode, l_strMarketCode);

                if (l_marketRow == null)
                {
                    log.debug("market not found with InstitutionCode, MarketCode:"
                        + l_strInstitutionCode + ", " + l_strMarketCode);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "�����ɊY������s�ꂪ�݂���܂���ł����B");
                }
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DB�ւ̃A�N�Z�X�Ɏ��s���܂����B",
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DB�ւ̃A�N�Z�X�Ɏ��s���܂����B",
                    l_ex);
            }

            //�s��p�v���t�@@�����X�e�[�u�����Y�����郌�R�[�h���擾����B
            //�s��ID�F�@@get�戵�\�s��()�̖߂�l�̊Y���v�f�i=�s��R�[�h�j�ɊY������s��.�s��ID
            //�v���t�@@�����X���ږ��F�@@"feq.sle.broker"
            //���ږ��A�ԁF�@@1
            try
            {
                MarketPreferencesDao.findRowByPk(
                    l_marketRow.getMarketId(),
                    WEB3MarketPreferencesNameDef.FEQ_SLE_BROKER,
                    1);

                //����t���[�F�s��p�v���t�@@�����X�e�[�u���ɊY�����R�[�h�����݂���ꍇ
                //���X�g�i�s�꒼���p�j�Ɏs��R�[�h��ǉ�����B
                if (!l_lisMarketCode2.contains(l_strMarketCode))
                {
                    l_lisMarketCode2.add(l_strMarketCode);
                }
                log.debug("�s��p�v���t�@@�����X�e�[�u���ɊY�����R�[�h�����݂���ꍇ�A"
                + "���X�g�i�s�꒼���p�j�Ɏs��R�[�h��ǉ�����");
            }
            catch(DataFindException l_ex)
            {
                //����t���[�F�s��p�v���t�@@�����X�e�[�u���ɊY�����R�[�h�����݂��Ȃ��ꍇ
                //���X�g�i��s�꒼���p�j�Ɏs��R�[�h��ǉ�����B
                if (!l_lisMarketCode1.contains(l_strMarketCode))
                {
                    l_lisMarketCode1.add(l_strMarketCode);
                }

                log.debug("�s��p�v���t�@@�����X�e�[�u���ɊY�����R�[�h�����݂��Ȃ��ꍇ�A"
                    + "���X�g�i��s�꒼���p�j�Ɏs��R�[�h��ǉ�����B");
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DB�ւ̃A�N�Z�X�Ɏ��s���܂����B",
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DB�ւ̃A�N�Z�X�Ɏ��s���܂����B",
                    l_ex);
            }
        }

        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminFeqOpenAtOrderDownloadInputResponse l_response
            = (WEB3AdminFeqOpenAtOrderDownloadInputResponse) l_request.createResponse();

        //�v���p�e�B�Z�b�g
        List l_lisMarketCodes = new ArrayList();
        for (int i = 0; i < l_lisMarketCode1.size(); i++)
        {
            l_lisMarketCodes.add(l_lisMarketCode1.get(i));
        }

        for (int j = 0; j < l_lisMarketCode2.size(); j++)
        {
            l_lisMarketCodes.add(l_lisMarketCode2.get(j));
        }

        String[] l_strMarketCodes = new String[l_lisMarketCodes.size()];
        l_lisMarketCodes.toArray(l_strMarketCodes);

        l_response.marketCodeList = l_strMarketCodes;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�_�E�����[�h�t�@@�C��)<BR>
     * �_�E�����[�h�t�@@�C���擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�ǁj��t����DL�jget�_�E�����[�h�t�@@�C���v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4214949C0253
     */
    protected WEB3AdminFeqOpenAtOrderDownloadResponse getDownloadFile(
        WEB3AdminFeqOpenAtOrderDownloadRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getDownloadFile(WEB3AdminFeqOpenAtOrderDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_request.validate();
        
        //1.2 ���O�C����񂩂�Ǘ��҃C���X�^���X���擾����
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 �����̃`�F�b�N���s���B 
        //[����] 
        //�@@�\�J�e�S���[�R�[�h�F �@@�\�J�e�S���[�R�[�h.�h�O���i�������Ǘ��j�h 
        //is�X�V�F false 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, 
            false);
        
        //1.4 �،���ЃR�[�h���擾����
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();

        //1.5 ���X�R�[�h���擾����
        String l_strBranchCode = l_web3Administrator.getBranchCode();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager
            = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        //1.6 ���X�C���X�^���X���擾����B 
        //[����] 
        //�،���ЃR�[�h�F get�،���ЃR�[�h()�̖߂�l 
        //���X�R�[�h�F get���X�R�[�h()�̖߂�l 
        Branch l_branch = null;
        try 
        {
            l_branch = l_accountManager.getWeb3GenBranch(
                l_strInstitutionCode, 
                l_strBranchCode);
        } 
        catch (NotFoundException l_ex) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386, 
                this.getClass().getName() + STR_METHOD_NAME, 
                "���X�C���X�^���X���擾���Ȃ��B", 
                l_ex);
        }
        if (l_branch == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386, 
                this.getClass().getName() + STR_METHOD_NAME, 
                "���X�C���X�^���X���擾���Ȃ��B");
        }
        
        //1.7 �s��C���X�^���X���擾����B 
        //[����] 
        //�،���ЃR�[�h�F get�،���ЃR�[�h()�̖߂�l 
        //�s��R�[�h�F ���N�G�X�g.�s��R�[�h 
        WEB3GentradeFinObjectManager l_finObjectManager 
            = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
        Market l_market = null;
        try 
        {
            l_market = l_finObjectManager.getMarket(l_strInstitutionCode, l_request.marketCode);
        } 
        catch (NotFoundException l_ex) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�s��C���X�^���X���擾���Ȃ��B", 
                l_ex);
        }
        if (l_market == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�s��C���X�^���X���擾���Ȃ��B");
        }
        
        //1.8 �N�G���v���Z�b�T���擾����
        List l_lisResult = null;
        try 
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //1.9 �����P�ʂ���Y�����钍�����擾����B 
            //[�擾����] 
            //�،���ЃR�[�h = get�،���ЃR�[�h()�̖߂�l 
            //���XID = ���X.getBranchId()�̖߂�l 
            //�s��ID = �s��.getMarketId()�̖߂�l 
            //�����L����� = �h�I�[�v���h
            //������� = �h������(�V�K����)"���́h������(��������)�h
            //�s�ꂩ��m�F�ς̐��� = null
            //������ = ���� 
            //
            //[����] 
            //Row�^�C�v�F �O�������P��Row.TYPE 
            //Where�F "institution_code = ? and branch_id = ? and market_id = ? and order_open_status = ? and 
            //         order_status in (?,?) and confirmed_quantity is null and biz_date = ?" 
            //orderBy�F "order_emp_code" 
            //Condition�F null 
            //���X�g�F �i�ȉ��̗v�f�̔z��j 
            //   get�،���ЃR�[�h()�̖߂�l 
            //   ���X.getBranchId()�̖߂�l 
            //   �s��.getMarketId()�̖߂�l 
            //   OrderOpenStatusEnum.�h�I�[�v���h 
            //   OrderStatusEnum.�h������(�V�K����)"���́h������(��������)�h
            //   null
            //   TradingSystem.getBizdate()�̖߂�l 
            String l_strWhere = " institution_code = ? and branch_id = ? " +
                "and market_id = ? and order_open_status = ? and order_status in (?, ?) and confirmed_quantity is null and biz_date = ? ";
                
            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
            String l_strBizDate = WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd");

            Object[] l_objs = new Object[] {
                l_strInstitutionCode, 
                new Long(l_branch.getBranchId()), 
                new Long(l_market.getMarketId()), 
                OrderOpenStatusEnum.OPEN,
                OrderStatusEnum.ORDERING,
                OrderStatusEnum.MODIFIED,
                l_strBizDate};

            l_lisResult = l_queryProcessor.doFindAllQuery(
                FeqOrderUnitRow.TYPE,
                l_strWhere,
                "order_emp_code",
                null,
                l_objs);
        } 
        catch (DataFindException l_ex) 
        {
            String l_strMessage = "�����P�ʂ���Y�����钍�����擾";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_strMessage, 
                l_ex);
        }
        catch (DataQueryException l_ex) 
        {
            String l_strMessage = "�����P�ʂ���Y�����钍�����擾";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_strMessage, 
                l_ex);
        }
        catch (DataNetworkException l_ex) 
        {
            String l_strMessage = "�����P�ʂ���Y�����钍�����擾";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_strMessage, 
                l_ex);
        }
        if (l_lisResult == null || l_lisResult.isEmpty())
        {
            String l_strMessage = "�����P�ʂ���Y�����钍�����擾";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
            "�Y������f�[�^�����݂��܂���B");
        }

        //1.10 ��t�����_�E�����[�hCSV�C���X�^���X�𐶐�����
        WEB3FeqOpenAtOrderDLCSV l_web3FeqOpenAtOrderDLCSV
            = new WEB3FeqOpenAtOrderDLCSV();

        int l_intCnt = l_lisResult.size();

        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();

        //1.11 �擾���������P�ʖ���Loop����
        for (int i = 0; i < l_intCnt; i++)
        {
            FeqOrderUnitRow l_feqOrderUnitRow = 
                (FeqOrderUnitRow)l_lisResult.get(i);
            WEB3FeqOrderUnit l_feqOrderUnit = 
                (WEB3FeqOrderUnit)l_orderManager.toOrderUnit(l_feqOrderUnitRow);
            //.(WEB3FeqOrderUnit) l_lisResult.get(i);
            
            //1.11.1 ��̖��׍s�𐶐����� add���׍s()
            int l_intLineIndex = l_web3FeqOpenAtOrderDLCSV.addRow();
            
            //1.11.2 �^�p�R�[�h���Z�b�g����B 
            //[����] 
            //�s�ԍ��F add���׍s()�̖߂�l 
            //�^�p�R�[�h�F �����P��.�^�p�R�[�h
            l_web3FeqOpenAtOrderDLCSV.setOrderEmpCode(
                l_intLineIndex, 
                l_feqOrderUnit.getOrderEmpCode());
            
            //1.11.3 ���n�����R�[�h���Z�b�g����B 
            //[����] 
            //�s�ԍ��F add���׍s()�̖߂�l 
            //����ID�F �����P��.����ID 
            l_web3FeqOpenAtOrderDLCSV.setOffshoreProductCode(
                l_intLineIndex, 
                l_feqOrderUnit.getProduct().getProductId());
            
            //1.11.4 ���������Z�b�g����B 
            //[����] 
            //�s�ԍ��F add���׍s()�̖߂�l 
            l_web3FeqOpenAtOrderDLCSV.setProductName(l_intLineIndex);
            
            //1.11.5 �����敪���Z�b�g����B 
            //
            //[����] 
            //�s�ԍ��F add���׍s()�̖߂�l 
            //is���t�F �����P��.is���t()�̖߂�l 
            l_web3FeqOpenAtOrderDLCSV.setOrderType(
                l_intLineIndex, 
                l_feqOrderUnit.isBuy());
            
            //1.11.6 �����������Z�b�g����B 
            //
            //[����] 
            //�s�ԍ��F add���׍s()�̖߂�l 
            //���������F �����P��.�������� 
            l_web3FeqOpenAtOrderDLCSV.setOrderQuantity(
                l_intLineIndex, 
                (long)l_feqOrderUnit.getQuantity());
            
            //1.11.7 �����@@���Z�b�g����B 

            //[����] 
            //�s�ԍ��F add���׍s()�̖߂�l 
            l_web3FeqOpenAtOrderDLCSV.setExecMethod(l_intLineIndex);

            //1.11.8 �����P�����Z�b�g����B 
            //
            //[����] 
            //�s�ԍ��F add���׍s()�̖߂�l 
            //�w�l�F �����P��.�w�l 
            l_web3FeqOpenAtOrderDLCSV.setOrderPrice(
                l_intLineIndex, 
                l_feqOrderUnit.getLimitPrice());

            //1.11.9 �L���������Z�b�g����B 
            //
            //[����] 
            //�s�ԍ��F add���׍s()�̖߂�l 
            l_web3FeqOpenAtOrderDLCSV.setExpirationDate(l_intLineIndex);
        }

        //1.12 CSV�t�@@�C���s���擾����
        String[] l_strCsvFileLines = l_web3FeqOpenAtOrderDLCSV.getCsvFileLines();

        //1.13 ���X�|���X�f�[�^�𐶐�����
        WEB3AdminFeqOpenAtOrderDownloadResponse l_response = 
            (WEB3AdminFeqOpenAtOrderDownloadResponse) l_request.createResponse();

        //1.14 �v���p�e�B�Z�b�g
        l_response.downloadFileList = l_strCsvFileLines;
        l_response.currentDate = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
