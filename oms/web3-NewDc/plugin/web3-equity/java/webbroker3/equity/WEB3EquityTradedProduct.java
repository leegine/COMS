head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityTradedProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e����� 
File Name        : �������(WEB3EquityTradedProduct.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/20 �������F(SRA) �V�K�쐬
Revesion History : 2007/02/10 ��іQ(���u) ���f�� No.1122
Revesion History : 2007/11/12 �����F(���u) ���f�� No.1203
*/

package webbroker3.equity;

import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeTradedProductImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ListTypeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3MartCanDealtDef;
import webbroker3.common.define.WEB3OpenOtcDivDef;
import webbroker3.common.define.WEB3QuoteFromDivDef;
import webbroker3.common.define.WEB3QuoteTypeDivDef;
import webbroker3.common.define.WEB3RegistDivisionDef;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.common.define.WEB3TradeStopDef;
import webbroker3.equity.data.OrderCarryoverSkipProdRow;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.LastClosingPriceParams;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.quoteadaptor.WEB3EqTypeQuoteData;
import webbroker3.quoteadaptor.RealType;

/**
 * �i��������j�B
 * @@version 1.0
 */
public class WEB3EquityTradedProduct extends EqTypeTradedProductImpl
{

    /**
     * �i�،���ЃR�[�h�j�B
     */
    private String institutionCode;

    /**
     * �i�����R�[�h�j�B
     */
    private String productCode;

    /**
     * �i�s��R�[�h�j�B
     */
    private String marketCode;

    /**
     * �i����j�B
     */
    private Date baseDate;

    /**
     * �iLogger�j�B
     */
    public static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityTradedProduct.class);

    /**
     * @@param l_row
     * @@throws DataQueryException
     * @@throws DataNetworkException
     * @@roseuid 4042EC8C0341
     */
    public WEB3EquityTradedProduct(TradedProductRow l_row)
        throws DataQueryException, DataNetworkException
    {
        super(l_row);

        institutionCode = l_row.getInstitutionCode();
        WEB3EquityProduct l_product =
            new WEB3EquityProduct(l_row.getProductId());
        productCode = l_product.getProductCode();
        WEB3GentradeMarket l_market =
            new WEB3GentradeMarket(l_row.getMarketId());
        marketCode = l_market.getMarketCode();
        baseDate = l_row.getBaseDate();
    }

    /**
     * �iis���������K���j�B<BR>
     * <BR>
     * ��������ɁA���������̔����K�����������Ă��Ȃ������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���������K������Ă��Ȃ������肷��B<BR>
     * �������̏ꍇ�i����.is������==true�j�A<BR>
     * �������.��������~���h��~���h�̏ꍇ�Atrue��ԋp����B<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * <BR> 
     * �Q�j�@@���������K������Ă��Ȃ����`�F�b�N����B<BR>
     * �������̏ꍇ�i����.is������==false�j�A<BR>
     * �������.��������~���h��~���h�̏ꍇ�Atrue��ԋp����B<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * @@param is������ - �������A�������̃t���O�B<BR>
     * �������̏ꍇtrue�A�������̏ꍇfalse���w�肷��B<BR>
     * @@param l_isSellOrder
     * @@return boolean
     * @@roseuid 4042EC8D013F
     */
    public boolean isSpotTradeControl(boolean l_isSellOrder)
    {
        final String STR_METHOD_NAME = "isSpotTradeControl(boolean)";
        log.entering(STR_METHOD_NAME);

        int l_inSpotTradeControl;
        boolean l_boReturn;

        if (l_isSellOrder)
        {
            // �P�j�@@���������K������Ă��Ȃ������肷��B
            l_inSpotTradeControl =
                ((EqtypeTradedProductParams)this.getDataSourceObject())
                    .getSellCashStop();
        }
        else
        {
            // �Q�j�@@���������K������Ă��Ȃ����`�F�b�N����B
            l_inSpotTradeControl =
                ((EqtypeTradedProductParams)this.getDataSourceObject())
                    .getBuyCashStop();
        }

        if (l_inSpotTradeControl != Integer.parseInt(WEB3TradeStopDef.ACTIVE))
        {
            l_boReturn = true;
        }
        else
        {
            l_boReturn = false;
        }

        log.exiting(STR_METHOD_NAME);
        return l_boReturn;
    }

    /**
     * �iis�������s�w��K���j�B<BR>
     * <BR>
     * ��������ɁA���������̐��s�����K�����������Ă��Ȃ������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���������K������Ă��Ȃ������肷��B<BR>
     * �������̏ꍇ�i����.is������==true�j�A<BR>
     * �������.���������s�w���~���h��~���h�̏ꍇ�Atrue��ԋp����B<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * �Q�j�@@���������K������Ă��Ȃ����`�F�b�N����B<BR>
     * �������̏ꍇ�i����.is������==false�j�A<BR>
     * �������.���������s�w���~���h��~���h�̏ꍇ�Atrue��ԋp����B<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@param is������ - �������A�������̃t���O�B<BR>
     * �������̏ꍇtrue�A�������̏ꍇfalse���w�肷��B<BR>
     * @@param l_isSellOrder
     * @@return boolean
     * @@roseuid 4042EC8D014E
     */
    public boolean isSpotMarketOrderDesignateCtrl(boolean l_isSellOrder)
    {
        final String STR_METHOD_NAME =
            "isSpotMarketOrderDesignateCtrl(boolean)";
        log.entering(STR_METHOD_NAME);

        int l_inSpotMarketOrderDesignateCtrl;
        boolean l_boReturn;

        if (l_isSellOrder)
        {
            //�P�j�@@���������K������Ă��Ȃ������肷��B
            l_inSpotMarketOrderDesignateCtrl =
                ((EqtypeTradedProductParams)this.getDataSourceObject())
                    .getSellSpotMarketOrdDesStop();
        }
        else
        {
            //�Q�j�@@���������K������Ă��Ȃ����`�F�b�N����B
            l_inSpotMarketOrderDesignateCtrl =
                ((EqtypeTradedProductParams)this.getDataSourceObject())
                    .getBuySpotMarketOrdDesStop();
        }

        if (l_inSpotMarketOrderDesignateCtrl
            != Integer.parseInt(WEB3TradeStopDef.ACTIVE))
        {
            l_boReturn = true;
        }
        else
        {
            l_boReturn = false;
        }

        log.exiting(STR_METHOD_NAME);
        return l_boReturn;
    }

    /**
     * �ivalidateJASDAQ�����戵�\�j�B<BR>
     * <BR>
     * �����������JASDAQ�����̏ꍇ�A<BR>
     * �w�肳�ꂽ���X�����Y�������������\���ǂ������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@this.�s��ID �ɊY������s��I�u�W�F�N�g.�s��R�[�h��JASDAQ�̏ꍇ�́A<BR>
     * �@@�@@�@@����������return����B<BR>
     * <BR>
     * �Q�j�@@��L�ȊO�̏ꍇ�A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�|�P�j�@@this.�X�����J�敪���}�[�P�b�g���C�N �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�����̕��X.�}�[�P�b�g���C�N�戵���戵�\ �̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���̂܂�return����B<BR>
     * �@@�@@�@@�@@�@@�����̕��X.�}�[�P�b�g���C�N�戵���戵�s�� �̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@��O��throw����B<BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00006<BR>
     * <BR>
     * @@param l_branch - (���X)<BR>
     * ���X�I�u�W�F�N�g�B<BR>
     * @@return WEB3BusinessLayerException <BR>
     * @@roseuid 40A42A450242
     */
    public void validateJASDAQProductHandling(WEB3GentradeBranch l_branch)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateJASDAQProductHandling(WEB3GentradeBranch)";

        log.entering(STR_METHOD_NAME);
        if (l_branch == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        EqtypeTradedProductRow l_tradedProductRow =
            (EqtypeTradedProductRow)this.getDataSourceObject();
        long l_lngMarketId = l_tradedProductRow.getMarketId();

        Market l_market = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        try
        {
            l_market = l_finApp.getFinObjectManager().getMarket(l_lngMarketId);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�s��ID�F[" + l_lngMarketId + "] �ɊY������s��f�[�^��������܂���B",
                l_nfe);
        }
        String l_strMarketCode = l_market.getMarketCode();
        
        // �P�j�@@this.�s��ID �ɊY������s��I�u�W�F�N�g.�s��R�[�h��JASDAQ�̏ꍇ�́A
        // �@@�@@�@@����������return����B
        if (WEB3MarketCodeDef.JASDAQ.equals(l_strMarketCode) == false)
        {
            return;
        }

        // �Q�|�P�j�@@this.�X�����J�敪���}�[�P�b�g���C�N �̏ꍇ�A
        // �@@�@@�@@�@@�@@�����̕��X.�}�[�P�b�g���C�N�戵���戵�\ �̏ꍇ�́A
        // �@@�@@�@@�@@�@@���̂܂�return����B
        // �@@�@@�@@�@@�@@�����̕��X.�}�[�P�b�g���C�N�戵���戵�s�� �̏ꍇ�́A
        // �@@�@@�@@�@@�@@��O��throw����B
        if (WEB3OpenOtcDivDef.MARKET_MAKE_PRODUCT.equals(l_tradedProductRow.getOpenOtcDiv()))
        {
            BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
            if (WEB3MartCanDealtDef.DEAL_DISABLED.equals(String.valueOf(l_branchRow.getHandlingMarketMake())))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }  
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �iis�J�z�X�L�b�v�����j�B<BR>
     * <BR>
     * �����J�z�����̃X�L�b�v�Ώۖ����ł��邩�ǂ����𔻒肷��B<BR>
     * �J�z�X�L�b�v�����ł���ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B<BR>
     * <BR>
     * �P�j�@@�y�����J�z�X�L�b�v�����e�[�u���z���A�ȉ��̏����Ńf�[�^���擾����B<BR>
     * <BR>
     * �@@�@@�،���ЃR�[�h��this.�،���ЃR�[�h<BR>
     * �@@�@@���@@�����R�[�h��(ALL(�S����) or this.�����R�[�h)<BR>
     * �@@�@@���@@�s��R�[�h��(F(�S�s��) or this.�s��R�[�h)<BR>
     * �@@�@@���@@�o�^�敪��1(�����J�z�X�L�b�v)<BR>
     * <BR>
     * �Q�j�@@�Y������f�[�^�����݂���ꍇ��true���A�Y���f�[�^�Ȃ��̏ꍇ��false��Ԃ��B<
     * BR>
     * @@return boolean
     * @@roseuid 40A42ACB0128
     */
    public boolean isTransferSkipProduct() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isTransferSkipProduct()";
        log.entering(STR_METHOD_NAME);

        //�Ԋ҂̒l�̐ݒ�
        boolean l_bReturn = false;
        List l_lisRows = null;
        try
        {
            String l_strWhere =
                " institution_code = ? and (product_code = 'ALL' or product_code = ?)"
                    + " and (market_code = 'F' or market_code = ?) and regist_division = '"
                    + WEB3RegistDivisionDef.ORDER_TRANSFER_STOP
                    + "' ";

            Object l_bindVars[] =
                { this.institutionCode, this.productCode, this.marketCode };
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    OrderCarryoverSkipProdRow.TYPE,
                    l_strWhere,
                    null,
                    null,
                    l_bindVars);
        }
        catch (DataFindException dfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dfe.getMessage(),
                dfe);
        }
        catch (DataNetworkException dne)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dne.getMessage(),
                dne);
        }
        catch (DataQueryException dqe)
        {
            log.error("�e�[�u���̌����Ɏ��s���܂���", dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dqe.getMessage(),
                dqe);
        }

        if (l_lisRows.size() > 0)
        {
            //�Y������f�[�^�����݂���ꍇ��true
            l_bReturn = true;
        }

        log.exiting(STR_METHOD_NAME);
        return l_bReturn;
    }

    /**
     * �iget����j�B
     * @@return ���
     */
    public Date getBaseDate()
    {
        return baseDate;
    }

    /**
     * �iget�،���ЃR�[�h�j�B
     * @@return �،���ЃR�[�h
     */
    public String getInstitutionCode()
    {
        return institutionCode;
    }

    /**
     * �iget�s��R�[�h�j�B
     * @@return �s��R�[�h
     */
    public String getMarketCode()
    {
        return marketCode;
    }

    /**
     * �iget�����R�[�h�j�B
     * @@return �����R�[�h
     */
    public String getProductCode()
    {
        return productCode;
    }

    /**
     * �iset����j�B
     * @@param l_datBaseDate ���
     */
    public void setBaseDate(Date l_datBaseDate)
    {
        this.baseDate = l_datBaseDate;
    }

    /**
     * �iset�،���ЃR�[�h�j�B
     * @@param l_strInstitutionCode �،���ЃR�[�h
     */
    public void setInstitutionCode(String l_strInstitutionCode)
    {
        this.institutionCode = l_strInstitutionCode;
    }

    /**
     * �iset�s��R�[�h�j�B
     * @@param l_strMarketCode �s��R�[�h
     */
    public void setMarketCode(String l_strMarketCode)
    {
        this.marketCode = l_strMarketCode;
    }

    /**
     * �iset�����R�[�h�j�B
     * @@param l_strProductCode �����R�[�h
     */
    public void setProductCode(String l_strProductCode)
    {
        this.productCode = l_strProductCode;
    }

    /**
     * �iis�M�p�����K���j�B<BR>
     * <BR>
     * ��������ɁA�M�p����̔����K�����������Ă��Ȃ�����<BR>
     * �����J�e�S���{�ٍϋ敪�ʂɃ`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@����.�����J�e�S�����ȉ��̂�����ł��Ȃ��ꍇ�́A��O��throw����B<BR>
     * <BR>
     * �@@�@@�@@OrderCategEnum.�h�V�K�������h�iOPEN_MARGIN�j<BR>
     * �@@�@@�@@OrderCategEnum.�h�ԍϒ����h�iCLOSE_MARGIN�j<BR>
     * �@@�@@�@@OrderCategEnum.�h�����E���n�����h�iSWAP_MARGIN�j<BR>
     * <BR>
     * �Q�j�@@�V�K���������K������Ă��Ȃ����𔻒肷��B<BR>
     * <BR>
     * �@@�@@����.�����J�e�S����OrderCategEnum.�h�V�K�������h<BR>
     * �iOPEN_MARGIN�j�̏ꍇ�A<BR>
     * �@@�@@�ȉ��̔�����s���B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�������K������Ă��Ȃ������肷��B<BR>
     * <BR>
     * �@@�@@�E�����i����.is����==false�j�̏ꍇ<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���hDEFAULT�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�����x�M�p��~���h��~���h�A���A<BR>
     * �@@�@@�@@�@@�@@�������.����ʐM�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
     * �ȊO�Afalse��Ԃ��B<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�����x�M�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
     * �ȊO�Afalse��Ԃ��B<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.����ʐM�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
     * �ȊO�Afalse��Ԃ��B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�������K������Ă��Ȃ������肷��B<BR>
     * <BR>
     * �@@�@@�E�����i����.is����==true�j�̏ꍇ<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���hDEFAULT�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�����x�M�p��~���h��~���h�A���A<BR>
     * �@@�@@�@@�@@�@@�������.����ʐM�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
     * �ȊO�Afalse��Ԃ��B<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�����x�M�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
     * �ȊO�Afalse��Ԃ��B<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.����ʐM�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
     * �ȊO�Afalse��Ԃ��B<BR>
     * <BR>
     * �R�j�@@�ԍϒ������K������Ă��Ȃ����𔻒肷��B<BR>
     * <BR>
     * �@@�@@����.�����J�e�S����OrderCategEnum.�h�ԍϒ����h�iCLOSE_MARGIN�j�̏ꍇ�A<BR>
     * �@@�@@�ȉ��̔�����s���B<BR>
     * <BR>
     * �@@�R�|�P�j�@@�������K������Ă��Ȃ������肷��B<BR>
     * <BR>
     * �@@�@@�E�����i����.is����==false�j�̏ꍇ<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���hDEFAULT�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�����ԍρi���ԍρj���x�M�p��~���h��~���h�A���A<BR>
     * �@@�@@�@@�@@�@@�������.�����ԍρi���ԍρj��ʐM�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�ȊO�Afalse��Ԃ��B<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�����ԍρi���ԍρj���x�M�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�ȊO�Afalse��Ԃ��B<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�����ԍρi���ԍρj��ʐM�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�ȊO�Afalse��Ԃ��B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�������K������Ă��Ȃ������肷��B<BR>
     * <BR>
     * �@@�@@�E�����i����.is����==true�j�̏ꍇ<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���hDEFAULT�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�����ԍρi���ԍρj���x�M�p��~���h��~���h�A���A<BR>
     * �@@�@@�@@�@@�@@�������.�����ԍρi���ԍρj��ʐM�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�ȊO�Afalse��Ԃ��B<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�����ԍρi���ԍρj���x�M�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�ȊO�Afalse��Ԃ��B<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�����ԍρi���ԍρj��ʐM�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�ȊO�Afalse��Ԃ��B<BR>
     * <BR>
     * �S�j�@@�������n�������K������Ă��Ȃ����𔻒肷��B<BR>
     * <BR>
     * �@@�@@����.�����J�e�S����OrderCategEnum.�h�����E���n�����h�iSWAP_MARGIN�j�̏ꍇ�A<BR>
     * �@@�@@�ȉ��̔�����s���B<BR>
     * <BR>
     * �@@�S�|�P�j�@@�����i�����j���K������Ă��Ȃ������肷��B<BR>
     * <BR>
     * �@@�@@�E�����i�����j�i����.is����==false�j�̏ꍇ<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���hDEFAULT�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�������x�M�p��~���h��~���h�A���A<BR>
     * �@@�@@�@@�@@�@@�������.������ʐM�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�ȊO�Afalse��Ԃ��B<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�������x�M�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�ȊO�Afalse��Ԃ��B<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.������ʐM�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�ȊO�Afalse��Ԃ��B<BR>
     * <BR>
     * �@@�S�|�Q�j�@@���n�i�����j���K������Ă��Ȃ������肷��B<BR>
     * <BR>
     * �@@�@@�E���n�i�����j�i����.is����==true�j�̏ꍇ<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���hDEFAULT�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.���n���x�M�p��~���h��~���h�A���A<BR>
     * �@@�@@�@@�@@�@@�������.���n��ʐM�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�ȊO�Afalse��Ԃ��B<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.���n���x�M�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�ȊO�Afalse��Ԃ��B<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.���n��ʐM�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�ȊO�Afalse��Ԃ��B<BR>
     * @@param l_strRepaymentType �ٍϋ敪�B<BR>
     * 0�FDEFAULT�i�w��Ȃ��j<BR>
     * 1�F���x�M�p<BR>
     * 2�F��ʐM�p<BR>
     * @@param l_orderCategory �����J�e�S���B�ixTrade��OrderCategEnum�ɂĒ�`�j
     * @@param l_isOpenShortMargin (is����)<BR>
     * �����^�����̃t���O�B<BR>
     * �����������̏ꍇtrue�A�����̏ꍇfalse���w�肷��B<BR>
     * @@return boolean
     * @@roseuid 40AAFC1B002D
     */
    public boolean isMarginTradeControl(
        String l_strRepaymentType,
        OrderCategEnum l_orderCategory,
        boolean l_isOpenShortMargin)
    {
        final String STR_METHOD_NAME = "isTransferSkipProduct()";
        log.entering(STR_METHOD_NAME);
        boolean l_isMarginTradeControl = false;
        //����.�����J�e�S�����ȉ��̂�����ł��Ȃ��ꍇ�́A��O��throw����B<BR>
        //OrderCategEnum.�h�V�K�������h�iOPEN_MARGIN�j<BR>
        //OrderCategEnum.�h�ԍϒ����h�iCLOSE_MARGIN�j<BR>
        //OrderCategEnum.�h�����E���n�����h�iSWAP_MARGIN�j
        EqtypeTradedProductParams l_TradedProductParams =
            (EqtypeTradedProductParams)this.getDataSourceObject();
        if (l_orderCategory.equals(OrderCategEnum.OPEN_MARGIN)
            || l_orderCategory.equals(OrderCategEnum.CLOSE_MARGIN)
            || l_orderCategory.equals(OrderCategEnum.SWAP_MARGIN))
        {
            if (l_orderCategory.equals(OrderCategEnum.OPEN_MARGIN))
            {
                //�����i����.is����==false�j�̏ꍇ
                if (l_isOpenShortMargin == false)
                {
                    //�|����.�ٍϋ敪���hDEFAULT�h�̏ꍇ�A<BR>
                    //�������.�����x�M�p��~���h��~���h�A���A<BR>
                    //�������.����ʐM�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
                    //�ȊO�Afalse��Ԃ��B<BR>
                    if (WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getLongMarginSysStop()))
                            || WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getLongMarginGenStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                    //�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
                    //�������.�����x�M�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
                    //�ȊO�Afalse��Ԃ��B<BR>
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getLongMarginSysStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }

                    //�| ����.�ٍϋ敪 �� �h ��ʐM�p �h �̏ꍇ �A
                    //�������.����ʐM�p��~ �� �h ��~�� �h �ł���� �A true��Ԃ� �B
                    // �ȊO �A false��Ԃ� �B
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getLongMarginGenStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                }
                //�Q�|�Q�j�@@�������K������Ă��Ȃ������肷��B
                else
                {
                    if (WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getShortMarginSysStop()))
                            || WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getShortMarginGenStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }

                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(
                                    l_TradedProductParams.getShortMarginSysStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }

                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getShortMarginGenStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                }
            }
            if (l_orderCategory.equals(OrderCategEnum.CLOSE_MARGIN))
            {
                //�R�|�P�j�@@�������K������Ă��Ȃ������肷��B
                if (l_isOpenShortMargin == false)
                {
                    //�E�����i����.is����==false�j�̏ꍇ
                    //�|����.�ٍϋ敪���hDEFAULT�h�̏ꍇ�A
                    //�������.�����ԍρi���ԍρj���x�M�p��~���h��~���h�A���A
                    //�������.�����ԍρi���ԍρj��ʐM�p��~���h��~���h�ł���΁Atrue��Ԃ��B
                    //�ȊO�Afalse��Ԃ��B
                    if (WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getLongCloseMarginGenStop()))
                            || WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getLongCloseMarginSysStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                    //�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A
                    //�������.�����ԍρi���ԍρj���x�M�p��~���h��~���h�ł���΁Atrue��Ԃ��B
                    //�ȊO�Afalse��Ԃ��B
                    if (WEB3GentradeRepaymentDivDef
                        .REPAYMENT_DIV_MARGIN_SYS
                        .equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getLongCloseMarginSysStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                    //�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
                    //�������.�����ԍρi���ԍρj��ʐM�p��~���h��~���h�ł���΁Atrue��Ԃ��B
                    //�ȊO�Afalse��Ԃ��B
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getLongCloseMarginGenStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                }
                //�R�|�Q�j�@@�������K������Ă��Ȃ������肷��B<BR>
                //�E�����i����.is����==true�j�̏ꍇ<BR>
                else
                {
                    //�|����.�ٍϋ敪���hDEFAULT�h�̏ꍇ�A<BR>
                    //�������.�����ԍρi���ԍρj���x�M�p��~���h��~���h�A���A<BR>
                    //�������.�����ԍρi���ԍρj��ʐM�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
                    //�ȊO�Afalse��Ԃ��B<BR>
                    if (WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getShortCloseMarginGenStop()))
                            || WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getShortCloseMarginSysStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                    //�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
                    //�������.�����ԍρi���ԍρj���x�M�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
                    //�ȊO�Afalse��Ԃ��B<BR>
                    if (WEB3GentradeRepaymentDivDef
                        .REPAYMENT_DIV_MARGIN_SYS
                        .equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getShortCloseMarginSysStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                    //�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
                    //�������.�����ԍρi���ԍρj��ʐM�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
                    //�ȊO�Afalse��Ԃ��B<BR>
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getShortCloseMarginGenStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                }
            }
            if (l_orderCategory.equals(OrderCategEnum.SWAP_MARGIN))
            {
                //�S�|�P�j�@@�����i�����j���K������Ă��Ȃ������肷��B<BR>
                //�E�����i�����j�i����.is����==false�j�̏ꍇ<BR>
                if (l_isOpenShortMargin == false)
                {
                    //�|����.�ٍϋ敪���hDEFAULT�h�̏ꍇ�A<BR>
                    //    �������.�������x�M�p��~���h��~���h�A���A<BR>
                    //    �������.������ʐM�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
                    //    �ȊO�Afalse��Ԃ��B<BR>
                    if (WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getLongSwapMarginGenStop()))
                            || WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getLongSwapMarginSysStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                    //�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
                    //    �������.�������x�M�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
                    //    �ȊO�Afalse��Ԃ��B<BR>
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getLongSwapMarginSysStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                    //�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
                    //    �������.������ʐM�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
                    //    �ȊO�Afalse��Ԃ��B<BR>
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getLongSwapMarginGenStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                }
                //�S�|�Q�j�@@���n�i�����j���K������Ă��Ȃ������肷��B<BR>
                //  �E���n�i�����j�i����.is����==true�j�̏ꍇ<BR>
                else
                {
                    //�|����.�ٍϋ敪���hDEFAULT�h�̏ꍇ�A<BR>
                    //    �������.���n���x�M�p��~���h��~���h�A���A<BR>
                    //    �������.���n��ʐM�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
                    //    �ȊO�Afalse��Ԃ��B<BR>
                    if (WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getShortSwapMarginGenStop()))
                            || WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getShortSwapMarginSysStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                    //    �|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
                    //    �������.���n���x�M�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
                    //    �ȊO�Afalse��Ԃ��B<BR>

                    if (WEB3GentradeRepaymentDivDef
                        .REPAYMENT_DIV_MARGIN_SYS
                        .equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getShortSwapMarginSysStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    } //�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
                    //    �������.���n��ʐM�p��~���h��~���h�ł���΁Atrue��Ԃ��B<BR>
                    //    �ȊO�Afalse��Ԃ��B<BR>
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getShortSwapMarginGenStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                }

            }
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_isMarginTradeControl;
    }

    /**
     * �iis�M�p���s�w��K���j�B<BR>
     * <BR>
     * ��������ɁA�M�p����̐��s�����K�����������Ă��Ȃ������A<BR>
     * �����J�e�S���{�ٍϋ敪�ʂɃ`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@����.�����J�e�S�����ȉ��̂�����ł��Ȃ��ꍇ�́A��O��throw����B<BR>
     * <BR>
     * �@@�@@�@@OrderCategEnum.�h�V�K�������h�iOPEN_MARGIN�j<BR>
     * �@@�@@�@@OrderCategEnum.�h�ԍϒ����h�iCLOSE_MARGIN�j<BR>
     * �@@�@@�@@OrderCategEnum.�h�����E���n�����h�iSWAP_MARGIN�j<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00653<BR>
     * <BR>
     * �Q�j�@@����.�����J�e�S����OrderCategEnum.�h�����E���n�����h�i<BR>SWAP_MARGIN�j
     * �̏ꍇ�A<BR>
     * �@@�@@�@@false��Ԃ��B<BR>
     * <BR>
     * �R�j�@@�V�K�����s�������K������Ă��Ȃ����𔻒肷��B<BR>
     * <BR>
     * �@@�@@����.�����J�e�S����OrderCategEnum.�h�V�K�������h<BR>
     * �iOPEN_MARGIN�j�̏ꍇ�A<BR>
     * �@@�@@�ȉ��̔�����s���B<BR>
     * <BR>
     * �@@�R�|�P�j�@@�������K������Ă��Ȃ������肷��B<BR>
     * <BR>
     * �@@�@@�E�����i����.is����==false�j�̏ꍇ<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�����x�M�p���s�w���~���h��~���h<BR>
     * �ł���΁Atrue��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�ȊO�Afalse��Ԃ��B<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.����ʐM�p���s�w���~���h��~���h�ł���΁A<BR>
     * true��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�ȊO�Afalse��Ԃ��B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�������K������Ă��Ȃ������肷��B<BR>
     * <BR>
     * �@@�@@�E�����i����.is����==true�j�̏ꍇ<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�����x�M�p���s�w���~���h��~���h<BR>
     * �ł���΁Atrue��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�ȊO�Afalse��Ԃ��B<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.����ʐM�p���s�w���~���h��~���h�ł���΁A<BR>
     * true��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�ȊO�Afalse��Ԃ��B<BR>
     * <BR>
     * �S�j�@@�ԍϐ��s�������K������Ă��Ȃ����𔻒肷��B<BR>
     * <BR>
     * �@@�@@����.�����J�e�S����OrderCategEnum.�h�ԍϒ����h<BR>
     * �iCLOSE_MARGIN�j�̏ꍇ�A<BR>
     * �@@�@@�ȉ��̔�����s���B<BR>
     * <BR>
     * �@@�S�|�P�j�@@�������K������Ă��Ȃ������肷��B<BR>
     * <BR>
     * �@@�@@�E�����i����.is����==false�j�̏ꍇ<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�����ԍρi���ԍρj���x�M�p���s�w���~���h��~���h�ł���΁A<BR>
     * �@@�@@�@@�@@�@@true��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�ȊO�Afalse��Ԃ��B<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�����ԍρi���ԍρj��ʐM�p���s�w���~���h��~���h�ł���΁A<BR>
     * �@@�@@�@@�@@�@@true��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�ȊO�Afalse��Ԃ��B<BR>
     * <BR>
     * �@@�S�|�Q�j�@@�������K������Ă��Ȃ������肷��B<BR>
     * <BR>
     * �@@�@@�E�����i����.is����==true�j�̏ꍇ<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�����ԍρi���ԍρj���x�M�p���s�w���~���h��~���h�ł���΁A<BR>
     * �@@�@@�@@�@@�@@true��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�ȊO�Afalse��Ԃ��B<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�����ԍρi���ԍρj��ʐM�p���s�w���~���h��~���h�ł���΁A<BR>
     * �@@�@@�@@�@@�@@true��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�ȊO�Afalse��Ԃ��B<BR>
     * @@param l_strRepaymentType �ٍϋ敪�B<BR>
     * 0�FDEFAULT�i�w��Ȃ��j<BR>
     * 1�F���x�M�p<BR>
     * 2�F��ʐM�p<BR>
     * @@param l_orderCategory �����J�e�S���B�ixTrade��OrderCategEnum�ɂĒ�`�j
     * @@param l_isOpenShortMargin (is����)<BR>
     * �����^�����̃t���O�B<BR>
     * �����������̏ꍇtrue�A�����̏ꍇfalse���w�肷��B<BR>
     * @@return boolean
     * @@roseuid 40AAFC1B002F
     */
    public boolean isMarginMarketOrderDesignateCtrl(
        String l_strRepaymentType,
        OrderCategEnum l_orderCategory,
        boolean l_isOpenShortMargin)
    {
        final String STR_METHOD_NAME = "isMarginMarketOrderDesignateCtrl()";
        log.entering(STR_METHOD_NAME);
        boolean l_isMarginMarketOrderDesignateCtrl = false;
        //����.�����J�e�S�����ȉ��̂�����ł��Ȃ��ꍇ�́A��O��throw����B<BR>
        //OrderCategEnum.�h�V�K�������h�iOPEN_MARGIN�j<BR>
        //OrderCategEnum.�h�ԍϒ����h�iCLOSE_MARGIN�j<BR>
        //OrderCategEnum.�h�����E���n�����h�iSWAP_MARGIN�j
        EqtypeTradedProductParams l_TradedProductParams =
            (EqtypeTradedProductParams)this.getDataSourceObject();
        if (l_orderCategory.equals(OrderCategEnum.OPEN_MARGIN)
            || l_orderCategory.equals(OrderCategEnum.CLOSE_MARGIN)
            || l_orderCategory.equals(OrderCategEnum.SWAP_MARGIN))
        {
            if (l_orderCategory.equals(OrderCategEnum.OPEN_MARGIN))
            {
                //�����i����.is����==false�j�̏ꍇ
                if (l_isOpenShortMargin == false)
                {

                    //�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
                    //�������.�����x�M�p���s�w���~���h��~���h<BR>
                    //�ł���΁Atrue��Ԃ��B<BR>
                    //�ȊO�Afalse��Ԃ��B<BR>
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getLongMsMarketOrdDesStop())))
                        {
                            l_isMarginMarketOrderDesignateCtrl = false;
                        }
                        else
                        {
                            l_isMarginMarketOrderDesignateCtrl = true;
                        }
                    }

                    //�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
                    // �������.����ʐM�p���s�w���~���h��~���h�ł���΁A<BR>
                    // true��Ԃ�.�ȊO�Afalse��Ԃ��B<BR>
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getLongMgMarketOrdDesStop())))
                        {
                            l_isMarginMarketOrderDesignateCtrl = false;
                        }
                        else
                        {
                            l_isMarginMarketOrderDesignateCtrl = true;
                        }
                    }
                }
                //�E�����i����.is����==true�j�̏ꍇ<BR>
                else
                {
                    //�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
                    //�������.�����x�M�p���s�w���~���h��~���h<BR>
                    //�ł���΁Atrue��Ԃ��B<BR>
                    //�ȊO�Afalse��Ԃ��B<BR>                   
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getShortMsMarketOrdDesStop())))
                        {
                            l_isMarginMarketOrderDesignateCtrl = false;
                        }
                        else
                        {
                            l_isMarginMarketOrderDesignateCtrl = true;
                        }
                    }
                    //�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
                    //�������.����ʐM�p���s�w���~���h��~���h�ł���΁A<BR>
                    //true��Ԃ��B�ȊO�Afalse��Ԃ��B<BR>
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getShortMgMarketOrdDesStop())))
                        {
                            l_isMarginMarketOrderDesignateCtrl = false;
                        }
                        else
                        {
                            l_isMarginMarketOrderDesignateCtrl = true;
                        }
                    }
                }
            }

            if (l_orderCategory.equals(OrderCategEnum.CLOSE_MARGIN))
            {
                //�E�����i����.is����==false�j�̏ꍇ<BR>
                if (l_isOpenShortMargin == false)
                {
                    //�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
                    //�������.�����ԍρi���ԍρj���x�M�p���s�w���~���h��~���h�ł���΁A<BR>
                    //true��Ԃ��B<BR>
                    //�ȊO�Afalse��Ԃ��B<BR>                   
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getLongCmsMarketOrdDesStop())))
                        {
                            l_isMarginMarketOrderDesignateCtrl = false;
                        }
                        else
                        {
                            l_isMarginMarketOrderDesignateCtrl = true;
                        }
                    }
                    //�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
                    //�������.�����ԍρi���ԍρj��ʐM�p���s�w���~���h��~���h�ł���΁A<BR>
                    //true��Ԃ��B<BR>
                    //�ȊO�Afalse��Ԃ��B<BR>
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getLongCmgMarketOrdDesStop())))
                        {
                            l_isMarginMarketOrderDesignateCtrl = false;
                        }
                        else
                        {
                            l_isMarginMarketOrderDesignateCtrl = true;
                        }
                    }
                }
                //�������K������Ă��Ȃ������肷��B<BR>
                //�E�����i����.is����==true�j�̏ꍇ<BR>
                else
                {
                    //�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
                    //�������.�����ԍρi���ԍρj���x�M�p���s�w���~���h��~���h�ł���΁A<BR>
                    //true��Ԃ��B<BR>
                    //�ȊO�Afalse��Ԃ��B<BR>               
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getShortCmsMarketOrdDesStop())))
                        {
                            l_isMarginMarketOrderDesignateCtrl = false;
                        }
                        else
                        {
                            l_isMarginMarketOrderDesignateCtrl = true;
                        }
                    }
                    //�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
                    //�������.�����ԍρi���ԍρj��ʐM�p���s�w���~���h��~���h�ł���΁A<BR>
                    //true��Ԃ��B<BR>
                    //�ȊO�Afalse��Ԃ��B<BR>
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getShortCmgMarketOrdDesStop())))
                        {
                            l_isMarginMarketOrderDesignateCtrl = false;
                        }
                        else
                        {
                            l_isMarginMarketOrderDesignateCtrl = true;
                        }
                    }
                }
            }
            if (l_orderCategory.equals(OrderCategEnum.SWAP_MARGIN))
            {
                l_isMarginMarketOrderDesignateCtrl = false;
            }
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00653,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_isMarginMarketOrderDesignateCtrl;
    }

    /**
     * �iget�M�p�����K����~�j�B<BR>
     * <BR>
     * ��������́A�����ٍ̕ϋ敪�A�����J�e�S���A<BR>
     * s�������ɊY�����锄����~���ڂ̒l��Ԃ��B<BR>
     * <BR>
     * �P�j�@@����.�����J�e�S�����ȉ��̂�����ł��Ȃ��ꍇ�́A��O��throw����B<BR>
     * <BR>
     * �@@�@@�@@OrderCategEnum.�h�V�K�������h�iOPEN_MARGIN�j<BR>
     * �@@�@@�@@OrderCategEnum.�h�ԍϒ����h�iCLOSE_MARGIN�j<BR>
     * �@@�@@�@@OrderCategEnum.�h�����E���n�����h�iSWAP_MARGIN�j<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00653<BR>
     * <BR>
     * �Q�j�@@�V�K������<BR>
     * <BR>
     * �@@�@@����.�����J�e�S����OrderCategEnum.�h�V�K�������h<BR>
     * �iOPEN_MARGIN�j�̏ꍇ�́A<BR>
     * �@@�@@�ȉ��̒l��Ԃ��B<BR>
     * <BR>
     * �@@�@@�E�����i����.is����==false�j�̏ꍇ<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�����x�M�p��~ ��Ԃ��B<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.����ʐM�p��~ ��Ԃ��B<BR>
     * <BR>
     * �@@�@@�E�����i����.is����==true�j�̏ꍇ<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�����x�M�p��~ ��Ԃ��B<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.����ʐM�p��~ ��Ԃ��B<BR>
     * <BR>
     * �R�j�@@�ԍϒ���<BR>
     * <BR>
     * �@@�@@����.�����J�e�S����OrderCategEnum.�h�ԍϒ����h<BR>
     * �iCLOSE_MARGIN�j�̏ꍇ�́A<BR>
     * �@@�@@�ȉ��̒l��Ԃ��B<BR>
     * <BR>
     * �@@�@@�E�����i����.is����==false�j�̏ꍇ<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�����ԍρi���ԍρj���x�M�p��~ ��Ԃ��B<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�����ԍρi���ԍρj��ʐM�p��~ ��Ԃ��B<BR>
     * <BR>
     * �@@�@@�E�����i����.is����==true�j�̏ꍇ<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�����ԍρi���ԍρj���x�M�p��~ ��Ԃ��B<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�����ԍρi���ԍρj��ʐM�p��~ ��Ԃ��B<BR>
     * <BR>
     * �S�j�@@�������n����<BR>
     * <BR>
     * �@@�@@����.�����J�e�S����OrderCategEnum.�h�����E���n�����h<BR>
     * �iSWAP_MARGIN�j�̏ꍇ�́A<BR>
     * �@@�@@�ȉ��̒l��Ԃ��B<BR>
     * <BR>
     * �@@�@@�E�����i����.is����==false�j�̏ꍇ<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.�������x�M�p��~ ��Ԃ��B<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.������ʐM�p��~ ��Ԃ��B<BR>
     * <BR>
     * �@@�@@�E�����i����.is����==true�j�̏ꍇ<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.���n���x�M�p��~ ��Ԃ��B<BR>
     * �@@�@@�@@�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�������.���n��ʐM�p��~ ��Ԃ��B<BR>
     * @@param l_strRepaymentType �ٍϋ敪�B<BR>
     * 0�FDEFAULT�i�w��Ȃ��j<BR>
     * 1�F���x�M�p<BR>
     * 2�F��ʐM�p<BR>
     * @@param l_orderCategory �����J�e�S���B�ixTrade��OrderCategEnum�ɂĒ�`�j
     * @@param l_isOpenShortMargin (is����)<BR>
     * �����^�����̃t���O�B<BR>
     * �����������̏ꍇtrue�A�����̏ꍇfalse���w�肷��B<BR>
     * @@return String
     * @@roseuid 40B44ABA014F
     */
    public String getMarginTradeControlStop(
        String l_strRepaymentType,
        OrderCategEnum l_orderCategory,
        boolean l_isOpenShortMargin)
        throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME =
            "getMarginTradeControlStop(String l_strRepaymentType, OrderCategEnum l_orderCategory, boolean l_isOpenShortMargin)";
        log.entering(STR_METHOD_NAME);
        EqtypeTradedProductParams l_TradedProductParams =
            (EqtypeTradedProductParams)this.getDataSourceObject();
        long l_marginTradeControlStop = 0;
        if (OrderCategEnum.OPEN_MARGIN.equals(l_orderCategory)
            || OrderCategEnum.CLOSE_MARGIN.equals(l_orderCategory)
            || OrderCategEnum.SWAP_MARGIN.equals(l_orderCategory))
        {
            if (OrderCategEnum.OPEN_MARGIN.equals(l_orderCategory))
            {
                //�E�����i����.is����==true�j�̏ꍇ<BR>
                if (l_isOpenShortMargin == true)
                {
                    //�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
                    //�������.����ʐM�p��~ ��Ԃ��B                   
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getShortMarginGenStop();
                    }
                    //�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
                    //�������.�����x�M�p��~ ��Ԃ��B<BR>                   
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getShortMarginSysStop();
                    }
                }
                //�E�����i����.is����==false�j�̏ꍇ<BR>
                else
                {
                    //�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
                    //�������.�����x�M�p��~ ��Ԃ��B<BR>
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getLongMarginSysStop();
                    }
                    //�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
                    //�������.����ʐM�p��~ ��Ԃ��B<BR>
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getLongMarginGenStop();
                    }
                }
            }

            if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderCategory))
            {
                //�E�����i����.is����==false�j�̏ꍇ<BR>
                if (l_isOpenShortMargin == false)
                {
                    //�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
                    //�������.�����ԍρi���ԍρj���x�M�p��~ ��Ԃ��B
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getLongCloseMarginSysStop();
                    }
                    //�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
                    //�������.�����ԍρi���ԍρj��ʐM�p��~ ��Ԃ��B<BR>
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getLongCloseMarginGenStop();
                    }

                }
                else
                {
                    //�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
                    //�������.�����ԍρi���ԍρj���x�M�p��~ ��Ԃ��B
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getShortCloseMarginSysStop();
                    }
                    //�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
                    //�������.�����ԍρi���ԍρj��ʐM�p��~ ��Ԃ��B<BR>
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getShortCloseMarginGenStop();
                    }
                }
            }
            if (OrderCategEnum.SWAP_MARGIN.equals(l_orderCategory))
            {
                //�E�����i����.is����==false�j�̏ꍇ<BR>
                if (l_isOpenShortMargin == false)
                {
                    //�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
                    //�������.�������x�M�p��~ ��Ԃ��B<BR>
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getLongSwapMarginSysStop();
                    }
                    //�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
                    //�������.������ʐM�p��~ ��Ԃ��B<BR>
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getLongSwapMarginGenStop();
                    }
                }
                else
                {
                    //�|����.�ٍϋ敪���h���x�M�p�h�̏ꍇ�A<BR>
                    //�������.���n���x�M�p��~ ��Ԃ��B<BR>
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getShortSwapMarginSysStop();
                    }
                    //�|����.�ٍϋ敪���h��ʐM�p�h�̏ꍇ�A<BR>
                    //�������.���n��ʐM�p��~ ��Ԃ��B<BR>
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getShortSwapMarginGenStop();
                    }
                }
            }
        }
        else
        {
            log.error(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00653,
                STR_METHOD_NAME);
        }
        return Long.toString(l_marginTradeControlStop);
    }

    /**
     * �iis�~�j�������K���j�B<BR>
     * <BR>
     * ��������ɁA���������̔����K�����������Ă��Ȃ������`�F�b�N����B<BR>                                                     
     * <BR>                                                                                                                     
     * �P�j�@@���������K������Ă��Ȃ������肷��B <BR>                                  
     * �������̏ꍇ�i����.is������==true�j�A<BR>                                  
     * �ithis.��������s.���~�j����~ == �h��~���h�j�̏ꍇ�Atrue��ԋp����B<BR> 
     * �ȊO�Afalse��ԋp����B<BR>                                                              
     * <BR>                                                                                       
     * �Q�j�@@���������K������Ă��Ȃ����`�F�b�N����B<BR>                                                                                                                             
     * �������̏ꍇ�i����.is������==false�j�A<BR>                                                                                                                                            
     * �ithis.��������s.���~�j����~ == �h��~���h�j�̏ꍇ�Atrue��ԋp����B<BR>                                                                                                           
     * �ȊO�Afalse��ԋp����B<BR> 
     * @@param l_blnIsSellOrder (is������)<BR>
     * @@return boolean
     */
    public boolean isMiniStockTradeControl(boolean l_blnIsSellOrder)
    {

        final String STR_METHOD_NAME ="isMiniStockTradeControl(boolean)";
        log.entering(STR_METHOD_NAME);
                
        EqtypeTradedProductParams l_tradedProductParams = (EqtypeTradedProductParams)this.getDataSourceObject();
        String l_strSellMiniStockStop = "" + l_tradedProductParams.getSellMiniStockStop();
        String l_strBuyMiniStockStop = "" + l_tradedProductParams.getBuyMiniStockStop();
        if((l_blnIsSellOrder && !(WEB3TradeStopDef.ACTIVE.equals(l_strSellMiniStockStop)))
            || (!l_blnIsSellOrder && !(WEB3TradeStopDef.ACTIVE.equals(l_strBuyMiniStockStop))))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    /**
     * �iget�����P�ʁj�B<BR>
     * <BR>
     * ���������P�ʂ��擾����B <BR>
     * this.��������s.�����P�ʂ�ԋp����<BR>
     * @@return double
     */
    public double getLotSize()
    {
        EqtypeTradedProductParams l_tradedProductParams = (EqtypeTradedProductParams)this.getDataSourceObject();
        double l_dblLotSize;
        
        l_dblLotSize = l_tradedProductParams.getLotSize();
        
        return l_dblLotSize;
    }
    /**
     * �iget�~�j�������P�ʁj�B<BR>
     * �~�j�������P�ʂ��擾����B<BR> 
     * �ithis.��������s.�����P�� �� 10�j��ԋp����B<BR>
     * @@return double
     */
    public double getMiniStockLotSize()
    {
        EqtypeTradedProductParams l_tradedProductParams = (EqtypeTradedProductParams)this.getDataSourceObject();
        double l_dblMiniLotSize;
        
        l_dblMiniLotSize = l_tradedProductParams.getLotSize()/10;
                
        return l_dblMiniLotSize;
    }

    /**
     * �iget�������j�B<BR>
     * <BR>
     * �������i�����擾�敪�A�����A�������\���ԁj���擾����B<BR>
     * ���������擾�ł��Ȃ������ꍇ�́Anull��Ԃ��B<BR>
     * <BR>
     * �V�[�P���X�}�u�i��������jget�������v�Q�ƁB<BR>
     * <BR>
     * @@param l_subAccount (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@return WEB3EquityProductQuote
     */
    public WEB3EquityProductQuote getProductQuote(
        WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME ="getProductQuote(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityProductQuote l_productQuote = null;
        
        // 1.1. ���敪��"����"�̏ꍇ�́A�u�w��s��͔���v�̗�O��throw����B
        EqtypeTradedProductRow l_tradedProductRow = (EqtypeTradedProductRow)this.getDataSourceObject();
        if (WEB3ListTypeDef.UNLISTING.equals(l_tradedProductRow.getListType()))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01367,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �g���v���_�N�g�}�l�[�W���擾
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
            
        boolean l_isMarketOpenTimeZone = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
        // ������̏ꍇ
        if (l_isMarketOpenTimeZone == false)
        {
            // ���������擾
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime()));
            
            // �I�l���R�[�h���擾
            LastClosingPriceParams l_lastClosingPriceParams =
                l_productManager.getLastClosingPrice(
                    this.getProduct().getProductId(), l_genBizDate.roll(-1));
	        
	        // 1.3. �I�l���R�[�h���擾�ł����ꍇ
	        if (l_lastClosingPriceParams != null)
	        {
                // �D��s��擾
                Market l_primaryMarket = this.getProduct().getPrimaryMarket();
                
                // �D��s�ꎞ���A�s��R�[�h�Z�b�g
                double l_dblClosingPrice = l_lastClosingPriceParams.getPrimaryClosingPrice();
                String l_strMarketCode = l_primaryMarket.getMarketCode();
                
                if (l_dblClosingPrice <= 0)
                {
                    // ���؏I�l�A�s��R�[�h���Z�b�g
	                l_dblClosingPrice = l_lastClosingPriceParams.getTokyoClosingPrice();
	                l_strMarketCode = WEB3MarketCodeDef.TOKYO;
                }
                if (l_dblClosingPrice <= 0)
                {
                    // ��؏I�l�A�s��R�[�h���Z�b�g
	                l_dblClosingPrice = l_lastClosingPriceParams.getOosakaClosingPrice();
	                l_strMarketCode = WEB3MarketCodeDef.OSAKA;
                }
                if (l_dblClosingPrice <= 0)
                {
                    // ���؏I�l�A�s��R�[�h���Z�b�g
	                l_dblClosingPrice = l_lastClosingPriceParams.getNagoyaClosingPrice();
	                l_strMarketCode = WEB3MarketCodeDef.NAGOYA;
                }
                if (l_dblClosingPrice <= 0)
                {
                    // ���̑��s��̏I�l�A�s��R�[�h���Z�b�g
	                l_dblClosingPrice = l_lastClosingPriceParams.getOtherClosingPrice();
	                l_strMarketCode = null;
                }
                
	            // �����ꂩ�̎s��Œl���t���Ă���ꍇ
                if (l_dblClosingPrice > 0)
	            {
                    //�I�l���擾�����̂��A���̑��s��I�l�ȊO�̏ꍇ
                    EqtypeTradedProductParams l_tradedProduct = null;
                    if (l_strMarketCode != null)
                    {
                        //���������F�@@this.getProduct 
                        //�s��F�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��(
                        //    this.getInstitution(),�����I�l���擾�����s��̎s��R�[�h) 
                        //����F�@@������ԊǗ�.get������( )�Ŏ擾�����������̑O�c�Ɠ�
                        WEB3GentradeFinObjectManager l_finObjectManager =
                            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                        Market l_market = null;
                        try
                        {
                            l_market = l_finObjectManager.getMarket(this.getInstitution(), l_strMarketCode);
                        }
                        catch(NotFoundException l_ex)
                        {
                            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                this.getClass().getName() + "." + STR_METHOD_NAME,
                                l_ex.getMessage(),
                                l_ex);
                        }
                        l_tradedProduct =
                            l_productManager.getTradedProduct(
                                (WEB3EquityProduct)this.getProduct(),
                                l_market,
                                l_genBizDate.roll(-1));
                    }
	                // �߂�l�I�u�W�F�N�g����
			        l_productQuote = new WEB3EquityProductQuote();
		        
                    // ����
		            l_productQuote.setQuote(l_dblClosingPrice);
		            // �O����
		            if (l_strMarketCode == null)
		            {
			            l_productQuote.setComparedPreviousDay(Double.NaN);
		            }
		            else
                    {
                        if (l_tradedProduct.getLastClosingPrice() == 0.0D)
                        {
	                        l_productQuote.setComparedPreviousDay(Double.NaN);
                        }
                        else
                        {
	                        l_productQuote.setComparedPreviousDay(l_dblClosingPrice - l_tradedProduct.getLastClosingPrice());
                        }
                    }
	                // �������\����
		            l_productQuote.setQuoteTime(null);
		            // �����擾�敪
		            l_productQuote.setQuoteFromDiv(WEB3QuoteFromDivDef.CLOSING_PRICE);
		            // �����敪
                    l_productQuote.setQuoteTypeDiv(WEB3QuoteTypeDivDef.CURRENT_PRICE);
                    // �s��R�[�h
		            l_productQuote.setMarketCode(l_strMarketCode);
		            
                    return l_productQuote;
	            }
	        }
        }
        
        WEB3QuoteDataSupplierService l_quoteDataSupplierService =
            (WEB3QuoteDataSupplierService)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getQuoteDataSupplierService();
            
        // 1.4. getEqTypeQuote(tradedProduct : EqTypeTradedProduct , realType : RealType)
        MainAccountRow l_accountRow = (MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject();
        WEB3GentradeMainAccount l_account = new WEB3GentradeMainAccount(l_accountRow);
        RealType l_realType;
        if (l_account.isRealCustomer())
        {
            l_realType = RealType.REAL;
        }
        else
        {
            l_realType = RealType.DELAY;
        }
        WEB3EqTypeQuoteData l_quoteData = null;
        try
        {
            l_quoteData = l_quoteDataSupplierService.getEqTypeQuote(this, l_realType);
        }
        catch (NotFoundException l_exp)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�،���ЃR�[�h�F[" + this.getInstitution().getInstitutionCode()
                + "] �����R�[�h�F[" + this.getProductCode()
                + "] �s��R�[�h�F[" + this.getMarketCode()
                + "�ɑΉ����鎞����񂪌�����܂���B",
                l_exp);
        }
        // 1.5. ���ݒl�A���C�z�l�A���C�z�l�̏����ŉ��i�����擾����
        //     �i�l���t���Ă���i�擾�����l > 0�j�̎��_�ŁA�ȍ~�̉��i���͎擾���Ȃ��j�B
        double l_dblMarketPrice = 0.0D;
        Timestamp l_tsQuoteTime = null;
        String l_strQuoteTypeDiv = null;
        // 1.5.1. getCurrentPrice
        l_dblMarketPrice = l_quoteData.getCurrentPrice();
        l_tsQuoteTime = l_quoteData.getCurrentPriceTime();
        l_strQuoteTypeDiv = WEB3QuoteTypeDivDef.CURRENT_PRICE;
        
        // 1.5.3. getBidPriceTime
        if (l_dblMarketPrice <= 0.0D)
        {
            l_dblMarketPrice = l_quoteData.getBidPrice();
            l_tsQuoteTime = l_quoteData.getBidPriceTime();
	        l_strQuoteTypeDiv = WEB3QuoteTypeDivDef.BID_PRICE;
        }
        // 1.5.5. getAskPrice
        if (l_dblMarketPrice <= 0.0D)
        {
            l_dblMarketPrice = l_quoteData.getAskPrice();
            l_tsQuoteTime = l_quoteData.getAskPriceTime();
	        l_strQuoteTypeDiv = WEB3QuoteTypeDivDef.ASK_PRICE;
        }
        // 1.6. ���ݒl�A���C�z�l�A���C�z�l�̂����ꂩ���擾�ł����ꍇ�i�擾�����l > 0�̏ꍇ�j
        if (l_dblMarketPrice > 0.0D)
        {
            l_productQuote = new WEB3EquityProductQuote();
            l_productQuote.setQuote(l_dblMarketPrice);
            l_productQuote.setComparedPreviousDay(l_quoteData.getChange());
            l_productQuote.setQuoteTime(l_tsQuoteTime);
            l_productQuote.setQuoteFromDiv(WEB3QuoteFromDivDef.MARKET_PRICE);
            l_productQuote.setQuoteTypeDiv(l_strQuoteTypeDiv);
            l_productQuote.setMarketCode(this.getMarketCode());
        }
        else
        {
            // 1.7. getLastClosingPrice
            double l_dblLastClosingPrice = this.getLastClosingPrice();
            // 1.8. �O���I�l���擾�ł����ꍇ�i�擾�����l > 0�̏ꍇ�j
            if (l_dblLastClosingPrice > 0.0D)
            {
                l_productQuote = new WEB3EquityProductQuote();
                l_productQuote.setQuote(l_dblLastClosingPrice);
                l_productQuote.setComparedPreviousDay(Double.NaN);
                l_productQuote.setQuoteTime(null);
                l_productQuote.setQuoteFromDiv(WEB3QuoteFromDivDef.LAST_CLOSING_PRICE);
                l_productQuote.setQuoteTypeDiv(WEB3QuoteTypeDivDef.LAST_CLOSING_PRICE);
                l_productQuote.setMarketCode(this.getMarketCode());
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_productQuote;
    }
}
@
