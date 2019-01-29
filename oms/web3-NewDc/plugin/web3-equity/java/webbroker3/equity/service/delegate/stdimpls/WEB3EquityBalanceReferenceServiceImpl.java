head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBalanceReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���������c���Ɖ�T�[�r�XImpl(WEB3EquityBalanceReferenceServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/02/07 �򑺁@@�m�m(SRA) �V�K�쐬
                  : 2005/03/31 ��(FLJ)�@@���\�`���[�j���O�C��
                  : 2006/08/29 ���r�@@(���u)�@@�d�l�ύX ���f��No.971
 */

package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityAsset;
import webbroker3.equity.WEB3EquityBalanceReferenceComparator;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.message.WEB3EquityBalanceReferenceDetailUnit;
import webbroker3.equity.message.WEB3EquityBalanceReferenceRequest;
import webbroker3.equity.message.WEB3EquityBalanceReferenceResponse;
import webbroker3.equity.message.WEB3EquityBalanceReferenceTotalRequest;
import webbroker3.equity.message.WEB3EquityBalanceReferenceTotalResponse;
import webbroker3.equity.message.WEB3EquityProductCodeNameUnit;
import webbroker3.equity.message.WEB3EquitySortKey;
import webbroker3.equity.service.delegate.WEB3EquityBalanceReferenceService;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i���������c���Ɖ�T�[�r�XImpl�j�B<BR>
 *<BR>
 * ���������c���Ɖ�T�[�r�X�����N���X<BR>
 */
public class WEB3EquityBalanceReferenceServiceImpl
    extends WEB3EquityClientRequestService
    implements WEB3EquityBalanceReferenceService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityBalanceReferenceServiceImpl.class);

    /**
     * @@roseuid 4206CC4A0163
     */
    public WEB3EquityBalanceReferenceServiceImpl()
    {

    }

    /**
     * ���������c���Ɖ�����s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * �����������c���Ɖ�N�G�X�g�̏ꍇ<BR>
     * �@@this.get�c���Ɖ�()���\�b�h���R�[������B<BR>
     * <BR>
     * �����������c���Ɖ�c�����v���N�G�X�g�̏ꍇ<BR>
     * �@@this.get�c�����v()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 41B5909E001C<BR>
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "WEB3EquityBalanceReferenceServiceImpl.execute()";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.error("�p�����[�^.���N�G�X�g�f�[�^��null�ł��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3EquityBalanceReferenceRequest)
        {
            l_response =
                this.getBalanceReference( (WEB3EquityBalanceReferenceRequest) l_request);
        }
        else if (l_request instanceof WEB3EquityBalanceReferenceTotalRequest)
        {
            l_response =
                this.getBalanceTotal( (WEB3EquityBalanceReferenceTotalRequest) l_request);
        }
        else
        {
            log.error("�p�����[�^.���N�G�X�g�f�[�^�̌^���s���ł��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�c���Ɖ�)<BR>
     *<BR>
     * ���������c���Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(���������c���Ɖ�T�[�r�X)get�c���Ɖ�v<BR>
     * �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^) ���������c���Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3EquityBalanceReferenceResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41B590D9023F<BR>
     */
    protected WEB3EquityBalanceReferenceResponse getBalanceReference(
        WEB3EquityBalanceReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getBalanceReference(WEB3EquityBalanceReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        // ���N�G�X�g�f�[�^.validate()
        l_request.validate();

        // validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        // create�����R�[�h����()
        //���\�`���[�j���O�C�� ��(FLJ)
        //�����R�[�h���̂̈ꗗ�쐬�@@�\�폜
        //delete begin
        //WEB3EquityProductCodeNameUnit[] l_productCodeNameUnits =
        //    this.createProductCodeNameUnit(l_subAccount);
        //delete end

        // get������戵�\�s��()
        //���\�`���[�j���O�C�� ��(FLJ)
        //�s��@@�\�폜
        //delete begin
        //WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        //String[] l_strHandlingMarketCodes =
        //    WEB3GentradeBranchMarketDealtCond.getTradingHandlingPossibleMarket(l_branch, ProductTypeEnum.EQUITY);

        //if (l_strHandlingMarketCodes == null || l_strHandlingMarketCodes.length == 0)
        //{
        //    throw new WEB3BusinessLayerException(
        //        WEB3ErrorCatalog.BUSINESS_ERROR_00643,
        //        this.getClass().getName() + "." + STR_METHOD_NAME);
        //}
        //delete end

        // create��������������()
        String l_strProductCode = l_request.productCode;
        String l_strQueryString = this.createQueryString(l_strProductCode,l_request.taxTypeList);

        // create���������f�[�^�R���e�i()
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution) l_subAccount.
            getInstitution();
        String[] l_strQueryDataContainger = this.createQueryContainer(l_institution,
            l_strProductCode, l_request.taxTypeList);

        // create�c���Ɖ�׈ꗗ()
        //���\�`���[�j���O�C�� ��(FLJ)
        //�s��@@�\�폜
//modify begin
//>>>>>>>>>
//        String l_strMarketCode = l_request.marketCode;
//        WEB3EquityBalanceReferenceDetailUnit[] l_balanceReferenceUnits =
//            this.createBalanceReferenceDetailUnitList(l_subAccount,
//                                                        l_strMarketCode,
//                                                        l_strHandlingMarketCodes,
//                                                        l_strQueryString,
//                                                        l_strQueryDataContainger,
//                                                        null);

        //���\�`���[�j���O�C�� ��(FLJ)
        //�����w�肵�Ȃ������ہAgetBalanceTotal�Ɠ������������AxTrade cache ������
        //���������ʂ��ꊇ�擾
        Hashtable l_lockedAssetDetails=getLockedAssetDetails(l_subAccount);

        WEB3EquityBalanceReferenceDetailUnit[] l_balanceReferenceUnits =
            this.createBalanceReferenceDetailUnitList(l_subAccount,l_lockedAssetDetails,
            l_strQueryString,
            l_strQueryDataContainger,
            " product_id asc ");
//>>>>>>>>>
//modify end


        // sort�c���Ɖ��
        WEB3EquitySortKey[] l_sortKeys = l_request.sortKeys;
        this.sortBalanceReferenceDetailUnit(l_balanceReferenceUnits, l_sortKeys);

        // �\���Ώۍs�̒��o
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_balanceReferenceUnits, l_intPageIndex, l_intPageSize);

        // createResponse()
        WEB3EquityBalanceReferenceResponse l_response =
            (WEB3EquityBalanceReferenceResponse) l_request.createResponse();

        // �v���p�e�B�Z�b�g
        // �����ꗗ
        //���\�`���[�j���O�C�� ��(FLJ)
        //�����R�[�h���̂̈ꗗ�쐬�@@�\�폜 delete begin
        //l_response.productCodeNames = l_productCodeNameUnits;
        //delete end

        // �s��R�[�h�ꗗ
        //���\�`���[�j���O�C�� ��(FLJ)
        //�s��@@�\�폜 delete begin
        //l_response.marketList = l_strHandlingMarketCodes;
        //delete end

        // �c���Ɖ��
        l_response.equityBalanceReferenceDetail =
            (WEB3EquityBalanceReferenceDetailUnit[]) l_pageIndexInfo.getArrayReturned(
            WEB3EquityBalanceReferenceDetailUnit.class);
        // �\���y�[�W�ԍ�
        l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());
        // ���y�[�W��
        l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
        // �����R�[�h��
        l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�c�����v)<BR>
     *<BR>
     * ���������c�����v�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(���������c���Ɖ�T�[�r�X)get�c�����v�v<BR>
     * �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^) ���������c���Ɖ�c�����v���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3EquityBalanceReferenceTotalResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41B590D9025E<BR>
     */
    protected WEB3EquityBalanceReferenceTotalResponse getBalanceTotal(
        WEB3EquityBalanceReferenceTotalRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getBalanceTotal(WEB3EquityBalanceReferenceTotalRequest)";
        log.entering(STR_METHOD_NAME);

        // validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        // get�ۗL���Y�ꗗ()
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // �g���|�W�V�����}�l�[�W��
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

        List l_lstAssets = null;
        l_lstAssets = l_positionManager.getAssets(l_subAccount, ProductTypeEnum.EQUITY, null, null,
                                                  " product_id asc ");

        // ���X�|���X�f�[�^����
        WEB3EquityBalanceReferenceTotalResponse l_response =
            (WEB3EquityBalanceReferenceTotalResponse) l_request.createResponse();

        // �Y���f�[�^�Ȃ��̏ꍇ
        if (l_lstAssets == null || l_lstAssets.size() == 0)
        {
            // ���X�|���X�ɏ����l���Z�b�g���ďI��
            l_response.capitalGainTotalAsset = "0";
            l_response.capitalGainTotalAppraisalProfitLoss = "0";
            l_response.normalAccountTotalAsset = "0";
            l_response.normalAccountTotalAppraisalProfitLoss = "0";
            l_response.stockOptionTotalAsset = "0";
            l_response.stockOptionTotalAppraisalProfitLoss = "0";

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        HashMap l_hmProductQuote = new HashMap();

        WEB3EquityProduct l_eqtypeProduct = null;
        WEB3EquityProductQuote l_productQuote = null;
        double l_dblNormalEstimatedValueTotal = 0.0D;
        double l_dblNormalProfitLossTotal = 0.0D;
        double l_dblSpecialEstimatedValueTotal = 0.0D;
        double l_dblSpecialProfitLossTotal = 0.0D;
        double l_dblStockOptionEstimatedValueTotal = 0.0D;
        double l_dblStockOptionProfitLossTotal = 0.0D;

        // get�ۗL���Y�ꗗ()�̖߂�l�̗v�f����Loop����
        for (int i = 0; i < l_lstAssets.size(); i++)
        {
            WEB3EquityAsset l_asset = (WEB3EquityAsset) l_lstAssets.get(i);
            AssetRow l_assetRow = (AssetRow) l_asset.getDataSourceObject();

            // ���������擾
            l_eqtypeProduct = (WEB3EquityProduct) l_asset.getProduct();

            // �������擾
            l_productQuote = this.getEquityProductQuote(l_subAccount, l_eqtypeProduct,
                l_hmProductQuote);

            // ������񂪎擾�ł��Ȃ������ꍇ�́A���ۗ̕L���Y�֏������ڍs����B
            if (l_productQuote == null)
            {
                continue;
            }

            // ����
            double l_dblCurrentPrice = l_productQuote.getQuote();
            // �c������
            double l_dblBalanceQuantity = l_asset.getQuantity() +
                l_assetRow.getQuantityCannotSell();
            // �]���z���Z�o
            double l_dblEstimatedValue = this.calcEstimatedValue(l_dblCurrentPrice,
                l_dblBalanceQuantity);

            // �T�Z�뉿�P��
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
                
            double l_dblBookValue = l_bizLogicProvider.calcEstimatedBookPrice(
                l_assetRow.getBookValue(), l_assetRow.getQuantityForBookValue(), 0);

            // �]�����v���Z�o(�T�Z�뉿�P�� != 0�̏ꍇ�̂�)
            double l_dblProfitLoss = 0.0D;
            if (l_dblBookValue != 0)
            {
                l_dblProfitLoss = this.calcProfitLoss(l_dblCurrentPrice,
                    l_dblBookValue, l_dblBalanceQuantity);
            }

            // �ۗL���Y.�ŋ敪 == "���"�̏ꍇ
            if (l_asset.getTaxType().equals(TaxTypeEnum.NORMAL))
            {
                // ��ʌ����]���z���v�A��ʌ����]�����v���v�ɉ��Z����
                l_dblNormalEstimatedValueTotal += l_dblEstimatedValue;
                l_dblNormalProfitLossTotal += l_dblProfitLoss;
            }
            // �ۗL���Y.�ŋ敪 == "����" or "������������򒥎�"�̏ꍇ
            else if (l_asset.getTaxType().equals(TaxTypeEnum.SPECIAL)
                     || l_asset.getTaxType().equals(TaxTypeEnum.SPECIAL_WITHHOLD))
            {
                // ��������]���z���v�A��������]�����v���v�ɉ��Z����
                l_dblSpecialEstimatedValueTotal += l_dblEstimatedValue;
                l_dblSpecialProfitLossTotal += l_dblProfitLoss;
            }
            // �ۗL���Y.�ŋ敪 == ("�X�g�b�N�I�v�V����")�̏ꍇ
            else if (l_asset.getTaxType().equals(TaxTypeEnum.STOCK_OPTION))
            {
                // ��������]���z���v�A��������]�����v���v�ɉ��Z����
                l_dblStockOptionEstimatedValueTotal += l_dblEstimatedValue;
                l_dblStockOptionProfitLossTotal += l_dblProfitLoss;
            }
        }

        l_response.capitalGainTotalAsset = WEB3StringTypeUtility.formatNumber(
            l_dblSpecialEstimatedValueTotal);
        l_response.capitalGainTotalAppraisalProfitLoss = WEB3StringTypeUtility.
            formatNumber(l_dblSpecialProfitLossTotal);
        l_response.normalAccountTotalAsset = WEB3StringTypeUtility.formatNumber(
            l_dblNormalEstimatedValueTotal);
        l_response.normalAccountTotalAppraisalProfitLoss = WEB3StringTypeUtility.
            formatNumber(l_dblNormalProfitLossTotal);
        l_response.stockOptionTotalAsset = WEB3StringTypeUtility.formatNumber(
            l_dblStockOptionEstimatedValueTotal);
        l_response.stockOptionTotalAppraisalProfitLoss =  WEB3StringTypeUtility.
        	formatNumber(l_dblStockOptionProfitLossTotal);   
        return l_response;
    }

    /**
     * (create�����R�[�h����)<BR>
     *<BR>
     * �����̕⏕�����ɊY������ۗL���Y�e�[�u���f�[�^��<BR>
     * �����R�[�h���̂̈ꗗ���쐬���A�ԋp����B<BR>
     * <BR>
     * �P�j�ۗL���Y�擾<BR>
     * �@@�����|�W�V�����}�l�[�W��.get�ۗL���Y�ꗗ()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[get�ۗL���Y�ꗗ()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �@@�@@�����^�C�v�F�@@ProductTypeEnum.����<BR>
     * �@@�@@��������������F�@@null<BR>
     * �@@�@@���������f�[�^�R���e�i�F�@@null<BR>
     * �@@�@@�\�[�g�����F�@@"product_id asc"<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �Q�jArrayList�𐶐�����B<BR>
     * <BR>
     * �R�j�P�j�̖߂�l�̗v�f(=�ۗL���Y�I�u�W�F�N�g)�����A<BR>
     * �@@�ȉ��̏������J��Ԃ��B<BR>
     * �@@�R�|�P�j�g�������v���_�N�g�}�l�[�W��.getProduct()�ɂ�<BR>
     * �@@�@@�����������擾����B<BR>
     * <BR>
     * �@@�@@[getProduct()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@arg0�F�@@�ۗL���Y.����ID<BR>
     * <BR>
     * �@@�R�|�Q�j�����R�[�h�d���`�F�b�N<BR>
     * �@@�@@ArrayList�ɁA�擾������������.�����R�[�h�Ɠ���<BR>
     * �@@�@@�����R�[�h��ێ�����v�f�����݂���ꍇ�A<BR>
     * �@@�@@���̗v�f�֏������ڍs����B(continue;)<BR>
     * <BR>
     * �@@�R�|�R�j�������������R�[�h���̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�R�|�S�j���������C���X�^���X�ɁA�ȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�����R�[�h = �擾������������.�����R�[�h<BR>
     * �@@�@@������ = �擾������������.������<BR>
     * <BR>
     * �@@�R�|�T�j��������ArrayList�Ƀv���p�e�B�Z�b�g����<BR>
     * �@@�@@�C���X�^���X��ǉ�����B<BR>
     * <BR>
     * �S�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_subAccount - (�⏕����) �⏕�����I�u�W�F�N�g<BR>
     * @@return WEB3EquityProductCodeNameUnit[]<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41B5A2E1017D<BR>
     */
    protected WEB3EquityProductCodeNameUnit[] createProductCodeNameUnit(
        WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createProductCodeNameUnit(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // �g���|�W�V�����}�l�[�W��
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

        // �ۗL���Y�擾
        List l_lstAssets = l_positionManager.getAssets(l_subAccount,
            ProductTypeEnum.EQUITY,
            null,
            null,
            "product_id asc");
        // �Y���f�[�^�Ȃ��̏ꍇ
        if (l_lstAssets == null || l_lstAssets.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        ArrayList l_lstProductCodeNames = new ArrayList();
        HashMap l_hmProductCodeCheck = new HashMap();
        WEB3EquityAsset l_asset = null;
        WEB3EquityProduct l_eqtypeProduct = null;
        // �������ʂ̗v�f����Loop����
        for (int i = 0; i < l_lstAssets.size(); i++)
        {
            l_asset = (WEB3EquityAsset) l_lstAssets.get(i);
            // ���������擾
            l_eqtypeProduct = (WEB3EquityProduct) l_asset.getProduct();

            // �����R�[�h�d���`�F�b�N
            if (l_hmProductCodeCheck.get(l_eqtypeProduct.getProductCode()) == null)
            {
                // �������������R�[�h���̃C���X�^���X�𐶐�����B
                WEB3EquityProductCodeNameUnit l_productCodeNameUnit = new
                    WEB3EquityProductCodeNameUnit();

                // ���������C���X�^���X�Ƀv���p�e�B�Z�b�g
                // �����R�[�h
                l_productCodeNameUnit.productCode = l_eqtypeProduct.getProductCode();
                // ������
                EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow) l_eqtypeProduct.
                    getDataSourceObject();
                l_productCodeNameUnit.productName = l_eqtypeProductRow.getStandardName();

                // ArrayList�ɒǉ�
                l_lstProductCodeNames.add(l_productCodeNameUnit);
                // �����R�[�h�`�F�b�N�pHashMap�ɒǉ�
                l_hmProductCodeCheck.put(l_productCodeNameUnit.productCode,
                                         l_productCodeNameUnit.productCode);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return (WEB3EquityProductCodeNameUnit[]) l_lstProductCodeNames.toArray(new
            WEB3EquityProductCodeNameUnit[0]);
    }

    /**
     * (create��������������)<BR>
     *<BR>
     * ���������iwhere�ȉ��w��̕�����j���쐬����B<BR>
     * <BR>
     * �P�j�߂�l�ƂȂ镶����̃C���X�^���X�𐶐� <BR>
     * <BR>
     * �Q�j�p�����[�^.�����R�[�h != null�̏ꍇ�A <BR>
     *     �����R�[�h�ɊY���������ID�̌���������������쐬����B<BR> 
     * <BR>
     *   �������������� = " and product_id = ? "<BR> 
     * <BR>
     * �R�j�p�����[�^.�����敪�ꗗ�̗v�f�� != null�@@�̏ꍇ�A<BR> 
     *     �����敪�ꗗ�ɊY����������敪�̌���������������쐬����B<BR> 
     * <BR>
     *    �������������� += "and tax_type in (?, ?,,,) "<BR>  
     * <BR>
     * �R�j�쐬�������������������ԋp����B<BR>
     * @@param l_strProductCode - (�����R�[�h) �����R�[�h<BR>
     * @@param l_strTaxTypeList - (�����敪�ꗗ)�����敪�ꗗ<BR>
     * @@return String<BR>
     * @@roseuid 41B662B7006F<BR>
     */
    protected String createQueryString(String l_strProductCode, String[] l_strTaxTypeList)
    {
        final String STR_METHOD_NAME = "createQueryString(String,String[])";
        log.entering(STR_METHOD_NAME);

        String l_strQueryString = "";
        // �p�����[�^.�����R�[�h == null�̏ꍇ
        if (l_strProductCode != null)
        {
            l_strQueryString = " and product_id = ? ";
        }
       
        //�p�����[�^.�����敪�ꗗ�̗v�f�� != null�@@�̏ꍇ
        if (l_strTaxTypeList != null)
        {
            if (l_strTaxTypeList.length == 1)
            {
                l_strQueryString = l_strQueryString + " and tax_type in (?) ";
            }
            if (l_strTaxTypeList.length > 1)
            {
                l_strQueryString = l_strQueryString + " and tax_type in (?";
                for (int i = 0; i < l_strTaxTypeList.length - 1; i++)
                {
                    l_strQueryString = l_strQueryString + ", ?";
                }
                l_strQueryString = l_strQueryString + ") ";
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     *<BR>
     * ���������iwhere�ȉ��w��̕�����j�̃p�����[�^�̕�����z����쐬����B<BR>
     * <BR>
     * �P�j������z��𐶐� <BR>
     * <BR>
     * �Q�j�p�����[�^.�����R�[�h != null�̏ꍇ�A�p�����[�^.�����R�[�h�ɊY������ <BR>
     *     ����ID(*1)�𐶐���������z��ɒǉ�����B  <BR>
     * <BR>
     * �R�j�p�����[�^.�����敪�ꗗ�̗v�f�� != null�@@�̏ꍇ <BR>
     *     �e�v�f�ɊY�����鉺�L�̒l�𕶎���z��ɒǉ�����B <BR>
     *     �R�|�P�j�����f�[�^�A�_�v�^.getAP�����敪(�p�����[�^.�����敪)���g�p���āA<BR> 
     *             AP�p�̌����敪�ɕϊ�����B <BR>
     *     �R�|�Q�j������z��ɂR�|�P�j�Ŏ擾���������敪���Z�b�g����B<BR> 
     * <BR>
     * �S�j�Q�j�A�R�j�ɂăp�����[�^���Z�b�g����������z���ԋp <BR>
     * (*1)�g���v���_�N�g�}�l�[�W��.get����(�p�����[�^.�،����, <BR>
     * �p�����[�^.�����R�[�h).����ID<BR>
     * @@param l_institution - (�،����) �،���ЃI�u�W�F�N�g<BR>
     * @@param l_strProductCode - (�����R�[�h) �����R�[�h<BR>
     * @@param l_strTaxTypeList - (�����敪�ꗗ) �����敪�ꗗ<BR>
     * @@return String[]<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41B662B70073<BR>
     */
    protected String[] createQueryContainer(WEB3GentradeInstitution l_institution,
                                            String l_strProductCode,
                                            String[] l_strTaxTypeList) throws
        WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createQueryContainer(WEB3GentradeInstitution, String, String[])";
        log.entering(STR_METHOD_NAME);
        
        ArrayList l_strQueryDataList = new ArrayList();

        // �p�����[�^.�����R�[�h != null�̏ꍇ
        if (l_strProductCode != null)
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            // �g���v���_�N�g�}�l�[�W��
            WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager) l_tradingModule.getProductManager();

            EqTypeProduct l_eqtypeProduct = null;
            try
            {
                l_eqtypeProduct =
                    l_productManager.getProduct(l_institution, l_strProductCode);
            }
            catch (NotFoundException l_ex)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����ɊY�����銔���������擾�ł��܂���B");
            }

            // ���������f�[�^�R���e�i�쐬
            l_strQueryDataList.add(
                String.valueOf(l_eqtypeProduct.getProductId()));
            
        }
        //�p�����[�^.�����敪�ꗗ�̗v�f�� != null�@@�̏ꍇ
        if (l_strTaxTypeList != null)
        {
            for (int i = 0; i < l_strTaxTypeList.length; i++)
            {
                l_strQueryDataList.add(
                        WEB3EquityDataAdapter.getApTaxType(l_strTaxTypeList[i]));
            }
        }
        
        String[] l_strQueryDataContainer = new String[l_strQueryDataList.size()];
        log.exiting(STR_METHOD_NAME);
        l_strQueryDataList.toArray(l_strQueryDataContainer);
        return l_strQueryDataContainer;
    }

    /**
     * (create�c���Ɖ�׈ꗗ)<BR>
     *<BR>
     * �ڋq�ɊY������c���Ɖ�ׂ̈ꗗ���쐬���A�ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(���������c���Ɖ�T�[�r�X)create�c���Ɖ�׈ꗗ�v<BR>
     * �Q��<BR>
     * @@param l_subAccount - (�⏕����) �⏕�����I�u�W�F�N�g<BR>
     * @@param l_strSpecifiedMarketCode - (�w��s��R�[�h) �w��s��R�[�h<BR>
     * @@param l_strHandlingMarketCodes - (�戵�\�s��R�[�h�ꗗ) �戵�\�s��R�[�h�̔z��<BR>
     * @@param l_strQueryString - (��������������) ��������������I�u�W�F�N�g<BR>
     * @@param l_strQueryDataContainer - (���������f�[�^�R���e�i) ���������f�[�^�R���e�i�I�u�W�F�N�g<BR>
     * @@param l_strSortCond - (�\�[�g����) �\�[�g����<BR>
     * @@return WEB3EquityBalanceReferenceDetailUnit[]<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41B7B2DF033E<BR>
     */
//    protected WEB3EquityBalanceReferenceDetailUnit[] createBalanceReferenceDetailUnitList(WEB3GentradeSubAccount l_subAccount, String l_strSpecifiedMarketCode, String[] l_strHandlingMarketCodes, String l_strQueryString, String[] l_strQueryDataContainer, String l_strSortCond)
    protected WEB3EquityBalanceReferenceDetailUnit[] createBalanceReferenceDetailUnitList(
        WEB3GentradeSubAccount l_subAccount, Hashtable l_lockedAssetDetails,String l_strQueryString,
        String[] l_strQueryDataContainer, String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createBalanceReferenceDetailUnitList(WEB3GentradeSubAccount, String, String[], String, String[], String)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // �g���|�W�V�����}�l�[�W��
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

        // get�ۗL���Y�ꗗ
        List l_lstAssets = l_positionManager.getAssets(l_subAccount,
            ProductTypeEnum.EQUITY,
            l_strQueryString,
            l_strQueryDataContainer,
            l_strSortCond);

        // �������ʂ̃`�F�b�N
        if (l_lstAssets == null || l_lstAssets.size() == 0)
        {
            return null;
        }

        // �������i�[�pHashMap����
        HashMap l_hmEquityProductQuote = new HashMap();
        // �c���Ɖ�׊i�[�pArrayList����
        ArrayList l_lstBalanceReferenceUnit = new ArrayList();
        // �s��R�[�h�ꗗ�i�[�pArrayList����
        //���\�`���[�j���O�C�� ��(FLJ)
        //�s��@@�\�폜
        //delete begin
        //ArrayList l_lstMarketCodes = new ArrayList();
        //delete end

        // ���^���t�̒�����t�\�`�F�b�N�i�S�s��Ώہj
        //���\�`���[�j���O�C�� ��(FLJ)
        //�s��@@�\�폜
        //begin
        // boolean l_blnBuyOrderOkAllMarket = this.isOrderAcceptPossible(true, null);
        // boolean l_blnSellOrderOkAllMarket = this.isOrderAcceptPossible(false, null);
        //end

        WEB3EquityAsset l_asset = null;
        WEB3EquityProduct l_eqtypeProduct = null;

        // ����\�ڋq�`�F�b�N
        //���\�`���[�j���O�C�� ��(FLJ)
        //LOOP�O�ړ�
        // begin
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.
            getOrderManager();
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator) l_finApp.getCommonOrderValidator();
        OrderValidationResult l_orderValidationResult =
            l_orderValidator.validateSubAccountForTrading(l_subAccount);
        //end

        // get�ۗL���Y�ꗗ()�̖߂�l�̗v�f����Loop����
        for (int i = 0; i < l_lstAssets.size(); i++)
        {
            l_asset = (WEB3EquityAsset) l_lstAssets.get(i);
            AssetRow l_assetRow = (AssetRow) l_asset.getDataSourceObject();
            Double l_dblLockedQuantity = (Double) l_lockedAssetDetails.get(new Long(
                l_asset.
                getAssetId()));
            double l_lockedQuantity = 0.0;
            if (l_dblLockedQuantity != null)
            {
                l_lockedQuantity = l_dblLockedQuantity.doubleValue();
            }

            // �����������擾
            l_eqtypeProduct = (WEB3EquityProduct) l_asset.getProduct();

            // ���������c���Ɖ�׃C���X�^���X�𐶐�
            WEB3EquityBalanceReferenceDetailUnit l_balanceReferenceUnit =
                new WEB3EquityBalanceReferenceDetailUnit();

            // ���t�\���� > ���������ʂ̏ꍇ
            //if (l_asset.getQuantity() > l_asset.getLockedQuantity())
            if (l_asset.getQuantity() > l_lockedQuantity)
            {
                log.debug("���t�\���� > ���������� �Ȃ̂ŁA���t�\�t���O == true");
                // ���t�\�t���O = true���Z�b�g
                l_balanceReferenceUnit.sellPossFlag = true;
            }
            else
            {
                log.debug("���t�\���� <= ���������� �Ȃ̂ŁA���t�\�t���O == false");
                // ���t�\�t���O = false���Z�b�g
                l_balanceReferenceUnit.sellPossFlag = false;
            }

            if (!l_orderValidationResult.equals(OrderValidationResult.
                                                VALIDATION_OK_RESULT))
            {
                log.debug("����s�\�ڋq�Ȃ̂ŁA���t�^���t�\�t���O == false");
                // �`�F�b�NOK�łȂ��ꍇ�́A���t/���t�\�t���O��false���Z�b�g�B
                l_balanceReferenceUnit.buyPossFlag = false;
                l_balanceReferenceUnit.sellPossFlag = false;
            }

            try
            {
                if (l_balanceReferenceUnit.buyPossFlag == true
                    || l_balanceReferenceUnit.sellPossFlag == true)
                {
                    // ��������������~�`�F�b�N
                    l_orderManager.validateProductCode(l_eqtypeProduct.getProductCode(),
                        l_eqtypeProduct.getInstitution().getInstitutionCode());

                    // �C���T�C�_�[�`�F�b�N
                    l_orderManager.validateInsider(l_subAccount, l_eqtypeProduct);
                }
            }
            catch (WEB3BusinessLayerException l_wbex)
            {
                log.debug("�����̌�������������~ �܂��� �C���T�C�_�[�ڋq�Ȃ̂ŁA���t�^���t�\�t���O == false");
                // �`�F�b�NOK�łȂ��ꍇ�́A���t/���t�\�t���O��false���Z�b�g�B
                l_balanceReferenceUnit.buyPossFlag = false;
                l_balanceReferenceUnit.sellPossFlag = false;
            }

            //���\�`���[�j���O�C�� ��(FLJ)
            //�s��@@�\�폜
//begin
//>>>>>
//            // ���t��is������t�\() == false�̏ꍇ
//            if (l_blnBuyOrderOkAllMarket == false)
//            {
//                log.debug("���t�̒�����t�s�\�Ȃ̂ŁA���t�\�t���O == false");
//                // ���t�\�t���O��false���Z�b�g�B
//                l_balanceReferenceUnit.buyPossFlag = false;
//            }
//
//            // ���t��is������t�\() == false�̏ꍇ
//            if (l_blnSellOrderOkAllMarket == false)
//            {
//                log.debug("���t�̒�����t�s�\�Ȃ̂ŁA���t�\�t���O == false");
//                // ���t�\�t���O��false���Z�b�g�B
//                l_balanceReferenceUnit.sellPossFlag = false;
//            }
//>>>>>
//end


            // ���t�܂��́A���t���\�ȏꍇ
            //���\�`���[�j���O�C�� ��(FLJ)
            //�s��@@�\�폜
            //delete begin
//>>>>>>>>>
//            if (l_balanceReferenceUnit.buyPossFlag == true
//                || l_balanceReferenceUnit.sellPossFlag == true)
//            {
//                boolean l_blnBuyOkThisMarket = false;
//                boolean l_blnSellOkThisMarket = false;
//
//                int l_intTradedMarketSize = l_strHandlingMarketCodes.length;
//                // �p�����[�^.����\�s��ꗗ�̗v�f����Loop����
//                for (int j = 0; j < l_intTradedMarketSize; j++)
//                {
//                    String l_strMarketCode = l_strHandlingMarketCodes[j];
//
//                    // ���t������t�\�`�F�b�N
//                    l_blnBuyOkThisMarket = this.isOrderAcceptPossible(true, l_strMarketCode);
//
//                    // ���t������t�\�`�F�b�N
//                    l_blnSellOkThisMarket = this.isOrderAcceptPossible(false, l_strMarketCode);
//
//                    // ���t�E���t�̒�����t�\�������Ƃ�false�̏ꍇ
//                    if (l_blnBuyOkThisMarket == false
//                        && l_blnSellOkThisMarket == false)
//                    {
//                        continue;
//                    }
//
//                    // reset�s��R�[�h
//                    WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
//
//                    // ���t�̌ڋq�����ʎ����~�`�F�b�N
//                    try
//                    {
//                        l_orderManager.validateAccountProductOrderStop(l_subAccount,
//                                                                        l_eqtypeProduct.getProductId(),
//                                                                        OrderTypeEnum.EQUITY_BUY);
//                    } catch (WEB3BusinessLayerException l_wbex_buy) {
//                        try
//                        {
//	                        // ���t�̌ڋq�����ʎ����~�`�F�b�N
//	                        l_orderManager.validateAccountProductOrderStop(l_subAccount,
//	                                                                        l_eqtypeProduct.getProductId(),
//	                                                                        OrderTypeEnum.EQUITY_SELL);
//	                    } catch (WEB3BusinessLayerException l_wbex_sell) {
//                           // ���t�E���t�����Ƃ���O���X���[�����ꍇ�́A���̗v�f�֏������ڍs����B
//	                       continue;
//	                    }
//                    }
//
//                    // �s��I�u�W�F�N�g�擾
//                    WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
//                    WEB3GentradeMarket l_market = null;
//                    try
//                    {
//                        l_market =
//                            (WEB3GentradeMarket)l_finObjectManager.getMarket(l_institution, l_strMarketCode);
//                    } catch (NotFoundException l_ex) {
//                        log.error(STR_METHOD_NAME);
//                        throw new WEB3SystemLayerException(
//                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
//                            this.getClass().getName() + "." + STR_METHOD_NAME,
//                            "�s��I�u�W�F�N�g���擾�ł��܂���B");
//                    }
//
//                    // ��������̔����K���`�F�b�N
//                    WEB3EquityTradedProduct l_tradedProduct = null;
//                    try
//                    {
//                        // ���t�K���`�F�b�N
//                        l_tradedProduct =
//                            (WEB3EquityTradedProduct)l_orderManager.validateTradedProduct(l_subAccount, l_eqtypeProduct, l_market, false);
//                    } catch (WEB3BusinessLayerException l_wbex_buy) {
//                        try {
//	                        // ���t�K���`�F�b�N
//	                        l_tradedProduct =
//	                            (WEB3EquityTradedProduct)l_orderManager.validateTradedProduct(l_subAccount, l_eqtypeProduct, l_market, true);
//                        } catch (WEB3BusinessLayerException l_wbex_sell) {
//                            // ���t�E���t�����Ƃ���O���X���[�����ꍇ�́A���̗v�f�֏������ڍs����B
//                            continue;
//                        }
//                    }
//
//                    // ���X��JASDAQ�����戵�`�F�b�N
//                    try
//                    {
//                        WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_subAccount.getWeb3GenBranch();
//                        l_tradedProduct.validateJASDAQProductHandling(l_branch);
//                    } catch (WEB3BusinessLayerException l_wbex) {
//                        // ��O���X���[�����ꍇ�́A���̗v�f�֏������ڍs����B
//                        continue;
//                    }
//
//                    // �s��R�[�h�ꗗ�i�[���X�g�ɒǉ�
//                    l_lstMarketCodes.add(l_strMarketCode);
//                }
//
//            }
//
//            // ����\�s��R�[�h�̈ꗗ���擾
//            String[] l_strTradingMarketCode = (String[])l_lstMarketCodes.toArray(new String[0]);
//
//            if (l_strTradingMarketCode.length == 0)
//            {
//                log.debug("����\�s��Ȃ��Ȃ̂ŁA���E���t�\�t���O = fales");
//                l_balanceReferenceUnit.buyPossFlag = false;
//                l_balanceReferenceUnit.sellPossFlag = false;
//            }
//
//            // �s��w��(�p�����[�^.�w��s��R�[�h != null)�̏ꍇ
//            if (l_strSpecifiedMarketCode != null)
//            {
//                // �\���Ώۃ`�F�b�N
//                // �@@����\�s��R�[�h�ꗗ�̗v�f����0
//                if (l_strTradingMarketCode.length == 0)
//                {
//                    // ���ۗ̕L���Y��
//                    continue;
//                }
//
//                // �A�p�����[�^.�w��s��R�[�h������\�s��R�[�h�ꗗ�ɑ��݂��Ȃ�
//                boolean l_blnIsContains;
//                l_blnIsContains = l_lstMarketCodes.contains(l_strSpecifiedMarketCode);
//                if (l_blnIsContains == false)
//                {
//                    // �s��R�[�h�ꗗ�i�[���X�g���N���A
//                    l_lstMarketCodes.clear();
//                    // ���ۗ̕L���Y��
//                    continue;
//                }
//            }
//>>>>>>>>>>>>>>
//delete end

            // ���������擾����
            WEB3EquityProduct l_eqtypeProductForCurrentPrice = null;
            // ��W�����̏ꍇ
            if (l_eqtypeProduct.isSubscriptionProduct())
            {
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();
                l_eqtypeProductForCurrentPrice =
                    l_productManager.getProductFromSubscriptionProduct(l_eqtypeProduct);
            }
            else
            {
                l_eqtypeProductForCurrentPrice = l_eqtypeProduct;
            }
            WEB3EquityProductQuote l_productQuote =
                this.getEquityProductQuote(l_subAccount, l_eqtypeProductForCurrentPrice,
                                           l_hmEquityProductQuote);

            // �c���Ɖ�ׂɃv���p�e�B���Z�b�g����B
            // ID
            l_balanceReferenceUnit.id = String.valueOf(l_asset.getAssetId());
            // �����R�[�h
            l_balanceReferenceUnit.productCode = l_eqtypeProduct.getProductCode();
            // ������
            EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow) l_eqtypeProduct.
                getDataSourceObject();
            l_balanceReferenceUnit.productName = l_eqtypeProductRow.getStandardName();
            // �����敪
            l_balanceReferenceUnit.taxType = WEB3EquityDataAdapter.getTaxType(l_asset.getTaxType());
            // ���t�\����
            l_balanceReferenceUnit.sellPossQuantity = WEB3StringTypeUtility.formatNumber(
                //l_asset.getQuantity() - l_asset.getLockedQuantity());
                l_asset.getQuantity() -l_lockedQuantity);
            // ����������
            l_balanceReferenceUnit.orderedQuantity = WEB3StringTypeUtility.formatNumber(
                //l_asset.getLockedQuantity());
                l_lockedQuantity);
            // �s��R�[�h�ꗗ
            //���\�`���[�j���O�C�� ��(FLJ)
            //�s��@@�\�폜
            //delete begin
            //l_balanceReferenceUnit.marketList = l_strTradingMarketCode;
            //delete end

            // �c������
            double l_dblBalanceQuantity;
            l_dblBalanceQuantity = l_asset.getQuantity() +
                l_assetRow.getQuantityCannotSell();
            l_balanceReferenceUnit.balanceQuantity = WEB3StringTypeUtility.formatNumber(
                l_dblBalanceQuantity);
            // ���t�s�\����
            l_balanceReferenceUnit.sellImpossQuantity = WEB3StringTypeUtility.
                formatNumber(l_assetRow.getQuantityCannotSell());
            // �T�Z�뉿�P��
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            double l_dblBookValuePrice = l_bizLogicProvider.calcEstimatedBookPrice(
                l_assetRow.getBookValue(), l_assetRow.getQuantityForBookValue(), 0);
            if (l_dblBookValuePrice == 0)
            {
                l_balanceReferenceUnit.estimatedBookPrice = null;
            }
            else
            {
                l_balanceReferenceUnit.estimatedBookPrice = WEB3StringTypeUtility.formatNumber(
                    l_dblBookValuePrice);
            }
            // �뉿�P�����͍σt���O
            if (l_assetRow.getInputBookValueIsNull() == false)
            {
                l_balanceReferenceUnit.estimatedBookPriceInputFlag = true;
            }
            // �������擾�ł����ꍇ
            if (l_productQuote != null)
            {
                log.debug("***** �����擾OK *****");
                // ����
                double l_dblCurrentPrice = l_productQuote.getQuote();
                l_balanceReferenceUnit.currentPrice = WEB3StringTypeUtility.formatNumber(
                    l_dblCurrentPrice);
                // �O����
                if (l_productQuote.getMarketCode() != null)
                {
	                l_balanceReferenceUnit.comparedPreviousDay = WEB3StringTypeUtility.
	                    formatNumber(l_productQuote.getComparedPreviousDay());
                }
                // �����擾����(HH:MM)
                log.debug("�����擾�敪:[" + l_productQuote.getQuoteFromDiv() + "]");
                Timestamp l_quoteTime = l_productQuote.getQuoteTime();
                if (l_quoteTime != null)
                {
                    l_balanceReferenceUnit.currentPriceTime = WEB3DateUtility.formatDate(
                        l_quoteTime, "HH:mm");
                }
                // �D��s��R�[�h
                l_balanceReferenceUnit.primaryMarketCode = l_productQuote.getMarketCode();
                // �T�Z�]���z�i�c�������j
                l_balanceReferenceUnit.estimatedAssetBalanceQuantity =
                    WEB3StringTypeUtility.formatNumber(this.calcEstimatedValue(
                    l_dblCurrentPrice, l_dblBalanceQuantity));
                // �T�Z�]���z�i���t�\�����j
                l_balanceReferenceUnit.estimatedAssetSellPossQuantity =
                    WEB3StringTypeUtility.formatNumber(this.calcEstimatedValue(
                    //l_dblCurrentPrice, l_asset.getQuantity() - l_asset.getLockedQuantity()));
                    l_dblCurrentPrice, l_asset.getQuantity() - l_lockedQuantity));

                // �T�Z�]���z�i�����������j
                l_balanceReferenceUnit.estimatedAssetOrderedQuantity =
                    WEB3StringTypeUtility.formatNumber(this.calcEstimatedValue(
                    //l_dblCurrentPrice, l_asset.getLockedQuantity()));
                    l_dblCurrentPrice, l_lockedQuantity));

                // �T�Z�]���z�i���t�s�\�����j
                l_balanceReferenceUnit.estimatedAssetSellImpossQuantity =
                    WEB3StringTypeUtility.formatNumber(this.calcEstimatedValue(
                    l_dblCurrentPrice, l_assetRow.getQuantityCannotSell()));

                // �T�Z�뉿�P�� != 0�̏ꍇ�A�T�Z�]�����v���Z�o����
                if (l_dblBookValuePrice != 0)
                {
                    // �T�Z�]�����v�i�c�������j
                    l_balanceReferenceUnit.estimatedAppraisalProfitLossBalanceQuantity =
                        WEB3StringTypeUtility.formatNumber(this.calcProfitLoss(
                        l_dblCurrentPrice, l_dblBookValuePrice,
                        l_dblBalanceQuantity));
                    // �T�Z�]�����v�i���t�\�����j
                    l_balanceReferenceUnit.estimatedAppraisalProfitLossSellPossQuantity =
                        WEB3StringTypeUtility.formatNumber(this.calcProfitLoss(
                        l_dblCurrentPrice, l_dblBookValuePrice,
                        //l_asset.getQuantity() - l_asset.getLockedQuantity()));
                        l_asset.getQuantity() - l_lockedQuantity));

                    // �T�Z�]�����v�i�����������j
                    l_balanceReferenceUnit.estimatedAppraisalProfitLossOrderedQuantity =
                        WEB3StringTypeUtility.formatNumber(this.calcProfitLoss(
                        l_dblCurrentPrice, l_dblBookValuePrice,
                        //l_asset.getLockedQuantity()));
                        l_lockedQuantity));
                    // �T�Z�]�����v�i���t�s�\�����j
                    l_balanceReferenceUnit.estimatedAppraisalProfitLossSellImpossQuantity =
                        WEB3StringTypeUtility.formatNumber(this.calcProfitLoss(
                        l_dblCurrentPrice, l_dblBookValuePrice,
                        l_assetRow.getQuantityCannotSell()));
                }
            }

            // �c���Ɖ�׊i�[���X�g�ɒǉ�
            l_lstBalanceReferenceUnit.add(l_balanceReferenceUnit);
            // �s��R�[�h�ꗗ�i�[���X�g���N���A
            //���\�`���[�j���O�C�� ��(FLJ)
            //�s��@@�\�폜
            //delete begin
            //l_lstMarketCodes.clear();
            //end
        }

        // �c���Ɖ�ׂ̔z��𐶐����ĕԋp
        log.exiting(STR_METHOD_NAME);
        return (WEB3EquityBalanceReferenceDetailUnit[]) l_lstBalanceReferenceUnit.toArray(new
            WEB3EquityBalanceReferenceDetailUnit[0]);
    }

    /**
     * (sort�c���Ɖ��)<BR>
     *<BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���ɂ��ǂ��Ďc���Ɖ�ׂ̃\�[�g���s���B<BR>
     * <BR>
     * �P�j�p�����[�^.�c���Ɖ�� == null�̏ꍇ�A<BR>
     * �@@�������I������B<BR>
     * <BR>
     * �Q�jArrayList�𐶐�����B <BR>
     * <BR>
     * �R�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�R�|�P�j�\�[�g�L�[.�L�[���ڂ̒l�ɑΉ������r���ڂ�Comparator�𐶐����A<BR>
     * �@@�@@�@@ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �@@�@@�@@�@@���������c���Ɖ�Comparator�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@�@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@�@@�@@�@@orderBy�F �\�[�g�L�[.�����^�~��<BR>
     * �@@�@@�@@�@@�@@��r���ځF�@@�\�[�g�L�[.�L�[����<BR>
     * <BR>
     * �@@�@@�@@�AArrayList�ɐ�������Comparator��ǉ�����B<BR>
     * <BR>
     * �S�jWEB3ArraysUtility.sort()���\�b�h���R�[������B <BR>
     * <BR>
     * �@@[sort()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@obj�F�@@�p�����[�^.�c���Ɖ��<BR>
     * �@@�@@com�F�@@��������ArrayList.toArray()�̖߂�l<BR>
     * @@param l_balanceReferenceDetailUnit - (�c���Ɖ��) ���������c���Ɖ�ׂ̔z��<BR>
     * @@param l_sortKey - (�\�[�g�L�[) ���������\�[�g�L�[�̔z��<BR>
     * @@roseuid 41B66ABC0198<BR>
     */
    protected void sortBalanceReferenceDetailUnit(WEB3EquityBalanceReferenceDetailUnit[]
                                                  l_balanceReferenceDetailUnit,
                                                  WEB3EquitySortKey[] l_sortKey)
    {
        final String STR_METHOD_NAME =
            "sortBalanceReferenceDetailUnit(WEB3EquityBalanceReferenceDetailUnit, WEB3EquitySortKey)";
        log.entering(STR_METHOD_NAME);

        // �p�����[�^.�c���Ɖ�� == null�̏ꍇ�A�����I��
        if (l_balanceReferenceDetailUnit == null ||
            l_balanceReferenceDetailUnit.length == 0)
        {
            return;
        }

        ArrayList l_lstComparators = new ArrayList();

        // �p�����[�^.�\�[�g�L�[�̗v�f����Loop����
        WEB3EquityBalanceReferenceComparator l_comparator = null;
        String l_strOrderBy = null;
        String l_strKeyItem = null;
        for (int i = 0; i < l_sortKey.length; i++)
        {
            l_strOrderBy = l_sortKey[i].ascDesc;
            l_strKeyItem = l_sortKey[i].keyItem;
            log.debug("��" + (i + 1) + "�\�[�g�L�[�F" + l_strKeyItem + " " + l_strOrderBy);

            // ���������c���Ɖ�Comparator�𐶐�
            l_comparator = new WEB3EquityBalanceReferenceComparator(l_strOrderBy,
                l_strKeyItem);
            // ArrayList��Comparator��ǉ�
            l_lstComparators.add(l_comparator);
        }

        // �\�[�g
        WEB3ArraysUtility.sort(l_balanceReferenceDetailUnit,
                               (WEB3EquityBalanceReferenceComparator[]) l_lstComparators.
                               toArray(new WEB3EquityBalanceReferenceComparator[0]));
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�������)<BR>
     *<BR>
     * ���������擾����B<BR>
     * <BR>
     * �P�j������񌟍�<BR>
     * �@@�p�����[�^.�������i�[���X�g.get()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[get()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@key�F�@@�p�����[�^.��������.�����R�[�h<BR>
     * �@@�@@�@@�@@�@@+ �p�����[�^.��������.�D��s��<BR>
     * <BR>
     * �Q�j�������ԋp<BR>
     * �@@�P�j�̖߂�l != null�̏ꍇ�A�擾������������ԋp����B<BR>
     * <BR>
     * �R�j�������擾<BR>
     * �@@�P�j�̖߂�l == null�̏ꍇ�A�ȉ��̎菇�Ŏ��������擾����B<BR>
     * �@@�R�|�P�j�p�����[�^.��������.get�D��s�ꎞ�����()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@[get�D��s�ꎞ�����()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * <BR>
     * �@@�R�|�Q�j�p�����[�^.�������i�[���X�g.put()���\�b�h���R�[�����A<BR>
     * �@@�@@�擾�������������i�[����B<BR>
     * <BR>
     * �@@�@@[put()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@key�F�@@�p�����[�^.��������.�����R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@+ �p�����[�^.��������.�D��s��<BR>
     * �@@�@@�@@value�F�@@�擾�����������<BR>
     * <BR>
     * �@@�R�|�R�j�擾������������ԋp����B<BR>
     * @@param l_subAccount - (�⏕����) �⏕�����I�u�W�F�N�g<BR>
     * @@param l_eqtypeProduct - (��������) ���������I�u�W�F�N�g<BR>
     * @@param l_hmProductQuote - (�������i�[���X�g) - <BR>
     * �����R�[�h+�s��R�[�h���L�[�Ƃ��āA���������i�[���郊�X�g<BR>
     * @@return WEB3EquityProductQuote<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41B697730077<BR>
     */
    protected WEB3EquityProductQuote getEquityProductQuote(WEB3GentradeSubAccount
        l_subAccount, WEB3EquityProduct l_eqtypeProduct, HashMap l_hmProductQuote) throws
        WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getEquityProductQuote(WEB3GentradeSubAccount, WEB3EquityProduct, HashMap)";
        log.entering(STR_METHOD_NAME);

        // �D��s��擾
        WEB3GentradeMarket l_market =
            (WEB3GentradeMarket) l_eqtypeProduct.getPrimaryMarket();

        // ������񌟍�
        String l_strKey = "";
        WEB3EquityProductQuote l_productQuote = null;
        if (l_market != null)
        {
            l_strKey = l_eqtypeProduct.getProductCode() + l_market.getMarketCode();
            l_productQuote = (WEB3EquityProductQuote) l_hmProductQuote.get(l_strKey);
        }
        else
        {
            Market[] l_tradedMarkets = l_eqtypeProduct.getTradedMarkets();
            if (l_tradedMarkets == null)
            {
                return null;
            }

            for (int i = 0; i < l_tradedMarkets.length; i++)
            {
                l_strKey = l_eqtypeProduct.getProductCode() +
                    l_tradedMarkets[i].getMarketCode();
                l_productQuote = (WEB3EquityProductQuote) l_hmProductQuote.get(l_strKey);
                if (l_productQuote != null)
                {
                    break;
                }
            }
        }

        // ������񂪎擾�ł����ꍇ�́A�ԋp���ďI��
        if (l_productQuote != null)
        {
            log.debug(STR_METHOD_NAME + " �p�����[�^.�������i�[���X�g���玞�����擾");
            log.exiting(STR_METHOD_NAME);
            return l_productQuote;
        }
        else // ������񂪎擾�ł��Ȃ������ꍇ
        {
            log.debug(STR_METHOD_NAME + " �����T�[�o�[���玞�����擾");
            try
            {
                // �D��s��̎��������擾����B
                l_productQuote = l_eqtypeProduct.getPrimaryMarketProductQuote(
                    l_subAccount);

                // �p�����[�^.�������i�[���X�g�Ɏ擾������������ǉ�����
                // key: �p�����[�^.��������.�����R�[�h + �p�����[�^.��������.�D��s��
                l_hmProductQuote.put(l_strKey, l_productQuote);

            }
            catch (WEB3BusinessLayerException l_wbex)
            {
                l_productQuote = null;
            }
            catch (WEB3SystemLayerException l_wsex)
            {
                throw l_wsex;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_productQuote;
    }

    /**
     * (calc�]���z)<BR>
     *<BR>
     * �]���z���Z�o���A�ԋp����B<BR>
     * ���萔���͊܂܂Ȃ��B<BR>
     * <BR>
     * �p�����[�^.�]���P�� * �p�����[�^.���ʂ̌��ʂ�ԋp����B<BR>
     * @@param l_dblPrice - (�]���P��) �]���P��<BR>
     * @@param l_dblQuantity - (����) ����<BR>
     * @@return double<BR>
     * @@roseuid 41B69CF50077<BR>
     */
    protected double calcEstimatedValue(double l_dblPrice, double l_dblQuantity)
    {
        final String STR_METHOD_NAME = "calcEstimatedValue(double, double)";
        log.entering(STR_METHOD_NAME);

        // �]���z = �p�����[�^.�]���P�� * �p�����[�^.����
        double l_dblResult = l_dblPrice * l_dblQuantity;

        log.exiting(STR_METHOD_NAME);
        return l_dblResult;
    }

    /**
     * (calc�]�����v)<BR>
     *<BR>
     * �]�����v���Z�o���A�ԋp����B<BR>
     * ���萔���͊܂܂Ȃ��B<BR>
     * <BR>
     * (�p�����[�^.�]���P�� - �p�����[�^.�뉿) * �p�����[�^.����<BR>
     * �̌��ʂ�ԋp����B<BR>
     * @@param l_dblPrice - (�]���P��) �]���P��<BR>
     * @@param l_dblBookValue - (�뉿) �뉿<BR>
     * @@param l_dblQuantity - (����) ����<BR>
     * @@return double<BR>
     * @@roseuid 41B69D4A00A5<BR>
     */
    protected double calcProfitLoss(double l_dblPrice, double l_dblBookValue,
                                    double l_dblQuantity)
    {
        final String STR_METHOD_NAME = "calcProfitLoss(double, double, double)";
        log.entering(STR_METHOD_NAME);

        // �]�����v = (�p�����[�^.�]���P�� - �p�����[�^.�뉿) * �p�����[�^.����
        double l_dblResult = (l_dblPrice - l_dblBookValue) * l_dblQuantity;

        log.exiting(STR_METHOD_NAME);
        return l_dblResult;
    }

    /**
     * (is������t�\)<BR>
     *<BR>
     * �����Ɏw�肳�ꂽ����A�s�ꂪ������t�\�ł��邩<BR>
     * ���ʂ���B<BR>
     * ������t�\�ł���ꍇ�́Atrue�B�ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * �P�j������t�g�����U�N�V�����̃��Z�b�g<BR>
     * �@@������ԊǗ�.reset������t�g�����U�N�V����()���R�[������B<BR>
     * <BR>
     * �@@[reset������t�g�����U�N�V����()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@������t�g�����U�N�V�����F<BR>
     * �@@�@@�@@�p�����[�^.is������ == true�̏ꍇ�A"���t"���Z�b�g�B<BR>
     * �@@�@@�@@�ȊO�A"���t"���Z�b�g�B<BR>
     * <BR>
     * �Q�j�s��R�[�h�̃��Z�b�g<BR>
     * �@@�p�����[�^.�s��R�[�h != null�̏ꍇ�A<BR>
     * �@@������ԊǗ�.reset�s��R�[�h()���R�[������B<BR>
     * <BR>
     * �@@[reset�s��R�[�h()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�s��R�[�h�F�@@�p�����[�^.�s��R�[�h<BR>
     * <BR>
     * �R�j������t�\�̃`�F�b�N<BR>
     * �@@������ԊǗ�.validate������t�\()���R�[�����A<BR>
     * �@@��O���X���[���ꂽ�ꍇ�́Afalse��ԋp����B<BR>
     * �@@�ȊO�Atrue��ԋp����B<BR>
     * <BR>
     * �@@��true/false��ԋp����O�ɁA<BR>
     * �@@�@@������ԊǗ�.reset�s��R�[�h(null)���\�b�h��<BR>
     * �@@�@@�R�[�����A�s��R�[�h��null�ŏ��������邱�ƁB<BR>
     * @@param l_blnIsBuyOrder - (is������) ���������ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@���t����<BR>
     * true�F�@@���t����<BR>
     * @@param l_strMarketCode - (�s��R�[�h) �s��R�[�h<BR>
     * <BR>
     * ���S�s��Ώۂ̏ꍇ�́Anull���Z�b�g�B<BR>
     * @@return boolean<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C0F70903E4<BR>
     */
    protected boolean isOrderAcceptPossible(boolean l_blnIsBuyOrder,
                                            String l_strMarketCode) throws
        WEB3BaseException
    {
        final String STR_METHOD_NAME = "isOrderAcceptPossible(boolean, String)";
        log.entering(STR_METHOD_NAME);

        // ������t�g�����U�N�V�����̃��Z�b�g
        if (l_blnIsBuyOrder)
        {
            // ������t�g�����U�N�V���� = "���t"���Z�b�g
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
        }
        else
        {
            // ������t�g�����U�N�V���� = "���t"���Z�b�g
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
        }

        // �p�����[�^.�s��R�[�h != null�̏ꍇ�A�s��R�[�h�����Z�b�g����B
        if (l_strMarketCode != null)
        {
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        }

        // ������t�\�̃`�F�b�N
        try
        {
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch (WEB3BusinessLayerException l_wbex)
        {
            // ��O���X���[���ꂽ�ꍇ��false��ԋp
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        catch (WEB3SystemLayerException l_wsex)
        {
            throw l_wsex;
        }
        finally
        {
            // ����J�����_�R���e�L�X�g.�s��R�[�h��null�ŏ�����
            WEB3GentradeTradingTimeManagement.resetMarketCode(null);
        }

        // ��O���X���[����Ȃ��ꍇ��true��ԋp
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    private Hashtable getLockedAssetDetails(
        WEB3GentradeSubAccount l_subAccount
        ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getLockedAssetDetails(SubAccount)";
        log.entering(STR_METHOD_NAME);
        Hashtable l_ret = new Hashtable(5);

        if (l_subAccount == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                                               STR_METHOD_NAME);
        }
        try
        {
            List list = LockedAssetDetailsDao.findRowsByAccountIdSubAccountId( (
                SubAccountRow)
                l_subAccount.getDataSourceObject());
            for (int i = 0; i < list.size(); i++)
            {
                LockedAssetDetailsRow row = (LockedAssetDetailsRow) list.get(i);
                l_ret.put(new Long(row.getAssetId()), new Double(row.getLockedQuantity()));
            }
        }
        catch (DataQueryException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                               this.getClass().getName() + "." +
                                               STR_METHOD_NAME, e.getMessage(), e);

        }
        catch (DataNetworkException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                               this.getClass().getName() + "." +
                                               STR_METHOD_NAME, e.getMessage(), e);

        }
        log.exiting(STR_METHOD_NAME);
        return l_ret;
    }

}
@
