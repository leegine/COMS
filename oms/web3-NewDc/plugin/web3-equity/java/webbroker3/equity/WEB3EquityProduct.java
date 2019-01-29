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
filename	WEB3EquityProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������(WEB3EquityProduct.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/12 �������F(SRA) �V�K�쐬
Revesion History : 2007/06/13 ���g (���u) ���f��No.1177
Revesion History : 2009/09/10 �И��� (���u) ���f��No.1332
Revesion History : 2009/09/21 �Ԑi (���u) ���f�� No.1336
Revesion History : 2009/10/02 �юu�� (���u) ���f�� No.1350
*/
package webbroker3.equity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeProductImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * �i���������j�B<BR>
 * <BR>
 * �����������
 * @@version 1.0
 */
public class WEB3EquityProduct extends EqTypeProductImpl
{
   
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityProduct.class);
        
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h
     */
    private String productCode;
   
    /**
     * (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     */
    private String institutionCode;
   
    /**
     * (�D��s��)<BR>
     * �����̗D��s��I�u�W�F�N�g
     */
    private WEB3GentradeMarket primaryMarket = null;
    
    /**
     * �R���X�g���N�^<BR>
     * @@param productId ����ID
     * @@throws DataQueryException
     * @@throws DataNetworkException
    *  @@roseuid 4042EC8402B6
     */
    public WEB3EquityProduct(long l_lngProductId)
            throws DataQueryException, DataNetworkException
    { 
        super(l_lngProductId);    
    }
    /**
     * �R���X�g���N�^<BR>
     * @@param l_productRow
     * @@throws DataQueryException
     * @@throws DataNetworkException
     * @@roseuid 4042EC8500B2
     */
    public WEB3EquityProduct(ProductRow l_productRow)
            throws DataQueryException, DataNetworkException
    { 
        super(l_productRow);    
    }
    /**
     * �R���X�g���N�^<BR>
     * @@param l_eqtypeProductRow
     * @@throws DataQueryException
     * @@throws DataNetworkException
     * @@roseuid 4042EC850277
     */
    public WEB3EquityProduct(EqtypeProductRow l_eqtypeProductRow)
            throws DataQueryException, DataNetworkException
    { 
        super(l_eqtypeProductRow);
    }
    /**
     * (get��������)<BR>
     * �����̌����������擾����B<BR>
     *  <BR>
     * this.���������s.���Z���̂Q�c�Ɠ��O�̓��t��ԋp����B<BR>
     * @@return Date
     */
    public Date getDevidendRightDate() throws WEB3BaseException
    {
        EqtypeProductParams l_eqtypeProductParams = (EqtypeProductParams)this.getDataSourceObject();
        WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(l_eqtypeProductParams.getYearlyBooksClosingDate());
        return l_gentradeBizDate.roll(-2);
    }
    /**
     * (get�~�j���s��)<BR>
     * �~�j���s����擾����B<BR>                                                                                                            
     * ���Y�������~�j������舵���Ă��Ȃ��ꍇ��null��ԋp����B <BR>
     *  <BR>
     * �P�j�@@��������e�[�u��<BR>
     *       �ieqtype_traded_product�Ceqtype_traded_product_updq�j����<BR>
     * �@@�������i������ԊǗ�.get������()�j�ɊY�����銔����������e�[�u����1���A<BR>
     * �ȉ��̏����ɓ��Ă͂܂銔����������s�I�u�W�F�N�g��2���擾����B<BR>
     *  <BR>
     * �@@��1�@@�������ɊY�����銔����������e�[�u��   <BR>
     * �@@�@@������������}�X�^�e�[�u����������������}�X�^updq�e�[�u���̏��ŁA<BR>
     * �@@�@@�������ɊY������f�[�^����������B<BR>
     *  <BR>
     * �@@�|������������}�X�^�e�[�u��<BR>
     * �@@�@@[��������] <BR>
     * �@@�@@����ID = ��������.getProductId() And <BR>
     * �@@�@@�L���� = �������i������ԊǗ�.get������()�j <BR>
     *  <BR>                                                                                                                                          
     * �@@�|������������}�X�^updq�e�[�u��<BR>
     * �@@�@@[��������] <BR>
     * �@@�@@����ID = ��������.getProductId() And <BR>
     * �@@�@@�L���� = �������i������ԊǗ�.get������()�j <BR>
     *   <BR>                                                                                                                 
     * �@@��2 ������������s�I�u�W�F�N�g<BR>                                                                        
     * �@@EqtypeTradedProductParams�i������������}�X�^�e�[�u�����猟�������ꍇ�j�܂��́A <BR>                                                                  
     * �@@EqtypeTradedProductUpdqParams�i������������}�X�^updq�e�[�u�����猟�������ꍇ�j<BR>                                                                   
     *  <BR>                                                                                                                                                   
     * �Q�j�@@�~�j���s��`�F�b�N  <BR>                                                                                                                          
     * �@@�P�j�Ŏ擾����������������s�I�u�W�F�N�g��2�̂����A<BR>                                                                                               
     * �~�j���戵 == BooleanEnum.TRUE�i1�F�~�j���戵�j<BR>�@@
     *  �ł����������s�I�u�W�F�N�g���擾����B<BR>                                                           
     * �~�j���戵 == BooleanEnum.TRUE�i1�F�~�j���戵�j�̍s�I�u�W�F�N�g���Ȃ��ꍇ�́A<BR>             
     * �~�j������舵���Ă��Ȃ������ł���Ɣ��肵�Anull��ԋp����B<BR>                              
     *  <BR>                                                                                                                         
     * �܂��A�~�j���戵 == BooleanEnum.TRUE�i1�F�~�j���戵�j<BR>
     * �̍s�I�u�W�F�N�g����������ꍇ�́A<BR>                                                             
     * �f�[�^�s�����Ɣ��肵�A��O���X���[����B<BR>  
     * class: WEB3SystemLayerException <BR>
     * tag:   SYSTEM_ERROR_80006 <BR>                                                                                                       
     * <BR>                                                                                                                                      
     * �R�j�@@�s��I�u�W�F�N�g�ԋp                                                                                      
     * �@@�Q�j�Ŏ擾������������s.getMarketId() �ɂĎs��h�c���擾����B<BR>                                               
     * �s��h�c�ɊY������s��I�u�W�F�N�g��ԋp����B<BR> 
     * @@return Market
     */
    public Market getMiniStockMarket() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getMiniStockMarket()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
			FinApp finApp = (FinApp)Services.getService(FinApp.class);
			String l_strWhere = "product_id = ? and (valid_until_biz_date = ?)";
			Object l_objWhere[] = 
			{
				new Long(this.getProductId()),
				WEB3DateUtility.formatDate(WEB3GentradeTradingTimeManagement.getOrderBizDate(),"yyyyMMdd"),
			};
			List l_list = null;
			l_list = Processors.getDefaultProcessor().doFindAllQuery(
				 EqtypeTradedProductRow.TYPE,
				 l_strWhere,
				 l_objWhere);
                
			int l_intLength = l_list.size();
			int l_intCount = 0;
			int l_intFlag = 0;
			for (int i = 0; i < l_intLength; i++)
			{
				EqtypeTradedProductParams l_eqtypeTradedProductParams = 
					new EqtypeTradedProductParams((EqtypeTradedProductRow)l_list.get(i));
				if(BooleanEnum.TRUE.equals(l_eqtypeTradedProductParams.getMiniStockCanDealt()))
				{
					l_intCount++;
					l_intFlag = i;
				}
			}
			if(l_intCount == 1)
			{
				EqtypeTradedProductParams l_eqtypeTradedProductParams = 
					new EqtypeTradedProductParams((EqtypeTradedProductRow)l_list.get(l_intFlag));
				long l_lngMarketId = l_eqtypeTradedProductParams.getMarketId();
				return finApp.getFinObjectManager().getMarket(l_lngMarketId);
			}
			else if (l_intCount == 0)
			{
				l_list = Processors.getDefaultProcessor().doFindAllQuery(
					EqtypeTradedProductUpdqRow.TYPE,
					l_strWhere,
					l_objWhere);
                
				l_intLength = l_list.size();
				l_intCount = 0;
				l_intFlag = 0;
				for (int i = 0; i < l_intLength; i++)
				{
					EqtypeTradedProductUpdqParams l_eqtypeTradedProductParams = 
						new EqtypeTradedProductUpdqParams((EqtypeTradedProductUpdqRow)l_list.get(i));
					if(BooleanEnum.TRUE.equals(l_eqtypeTradedProductParams.getMiniStockCanDealt()))
					{
						l_intCount++;
						l_intFlag = i;
					}
				}
				if (l_intCount == 0)
				{
					return null;
				}
				else if (l_intCount == 1)
				{
					EqtypeTradedProductUpdqParams l_eqtypeTradedProductParams = 
						new EqtypeTradedProductUpdqParams((EqtypeTradedProductUpdqRow)l_list.get(l_intFlag));
					long l_lngMarketId = l_eqtypeTradedProductParams.getMarketId();
					return finApp.getFinObjectManager().getMarket(l_lngMarketId);
				}
				else
				{
					throw new WEB3SystemLayerException(
						WEB3ErrorCatalog.SYSTEM_ERROR_80006,
						STR_METHOD_NAME);
				}
			}
			else
			{
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80006,
					STR_METHOD_NAME);
			}
        }
        catch (DataException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new RuntimeSystemException(
                "System exception while searching product with market id :",
                l_ex);
        }
        catch(NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);  
        }
    }

    /**
     * (get�D��s�ꎞ�����)<BR>
     * <BR>
     * �D��s��̏��Ɏ������i�����擾�敪�A�����A�������\���ԁj���擾����B<BR>
     * �S�Ă̎s��Ŏ��������擾�ł��Ȃ������ꍇ�́Anull��Ԃ��B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���������jget�D��s�ꎞ�����v�Q�ƁB<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@return WEB3EquityProductQuote
     */
    public WEB3EquityProductQuote getPrimaryMarketProductQuote(
        WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPrimaryMarketProductQuote(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        // getPrimaryMarket
        Market l_primaryMarket = this.getPrimaryMarket();
        
        // 1.1. getTradedMarkets
        Market[] l_markets = this.getTradedMarkets();
        // 1.2. getPrimaryMarket() == null(�D��s��ݒ�Ȃ�) ���� getTradedMarket( )==null�i�戵�s��Ȃ��j�̏ꍇ�A�u�戵�s��Ȃ��v�̗�O��throw����B
        if (l_primaryMarket == null && l_markets == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01365,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 1.3. �����擾�Ώێs��ꗗ�����肷��B
        Vector l_vecMarkets = new Vector();
        
        // this.getPrimaryMarket() �� null�̏ꍇ�̂݁A
        // this.getPrimaryMarket().�s��R�[�h���u�����擾�Ώێs��ꗗ�v�̐擪�ɐݒ肷��B
        if (l_primaryMarket != null)
        {
            // �擪�ɐݒ�
            l_vecMarkets.add(l_primaryMarket.getMarketCode());
        }
        
        boolean l_blnNNM = false;
        for (int i = 0;i < l_markets.length;i++)
        {
            if (WEB3MarketCodeDef.TOKYO.equals(l_markets[i].getMarketCode()) ||
                WEB3MarketCodeDef.OSAKA.equals(l_markets[i].getMarketCode()) ||
                WEB3MarketCodeDef.NAGOYA.equals(l_markets[i].getMarketCode()) ||
			    WEB3MarketCodeDef.JASDAQ.equals(l_markets[i].getMarketCode()))
            {
				l_vecMarkets.add(l_markets[i].getMarketCode());
            }
            else if (WEB3MarketCodeDef.NNM.equals(l_markets[i].getMarketCode()))
            {
				l_blnNNM = true;
            }
        }
        if (l_blnNNM == true)
        {
			l_vecMarkets.add(WEB3MarketCodeDef.NNM);
        }
        // 1.4. �i�P�j�`�i�T�j�̑S�s�ꂪ���Y�����̎戵�\�s��Ɋ܂܂�Ă��Ȃ��ꍇ�A
        // �@@�u�D��s��ꗗ�Ɏ戵�s��Ȃ��v�̗�O��throw����B
        int l_intSize = l_vecMarkets.size();
        if (l_intSize < 1)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01366,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 1.5. �����擾�Ώێs��ꗗ�̗v�f�����ALOOP�B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityProductManager l_productManager =
           (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        
        // ����J�����_�R���e�L�X�g�ɃZ�b�g����Ă���s��R�[�h��ޔ�������
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        String l_strOriginalMarketCode = l_context.getMarketCode();
        
        for (int i = 0;i < l_intSize;i++)
        {
            String l_strMarketCode = (String)l_vecMarkets.get(i);
            // reset�s��R�[�h
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
            
            // 1.5.1. get�������(�،���� : �،����, �����R�[�h : String, �s��R�[�h : String)
            WEB3EquityTradedProduct l_tradedProduct = null;
            try
            {
                l_tradedProduct =
                    (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                        this.getInstitution(),
                        this.getProductCode(),
                        l_strMarketCode);
            }
            catch (NotFoundException l_exp)
            {
                // ����J�����_�R���e�L�X�g�̎s��R�[�h�����̐ݒ�ɖ߂�
                WEB3GentradeTradingTimeManagement.resetMarketCode(l_strOriginalMarketCode); 
                continue;
			}
            
            // 1.5.2. get�������(�⏕���� : �⏕����)
            WEB3EquityProductQuote l_productQuote = null;
            try
            {
                l_productQuote = l_tradedProduct.getProductQuote(l_subAccount);
            }
            catch (WEB3BusinessLayerException l_exp)
            {
                if (WEB3ErrorCatalog.BUSINESS_ERROR_01367.equals(l_exp.getErrorInfo()))
                {
                    continue;
                }
                else
                {
                    // ����J�����_�R���e�L�X�g�̎s��R�[�h�����̐ݒ�ɖ߂�
                    WEB3GentradeTradingTimeManagement.resetMarketCode(l_strOriginalMarketCode);
                    throw l_exp;
                }
            }
            
            // 1.5.3. �߂�l��null�i������񂪎擾�ł����ꍇ�j
            if (l_productQuote != null)
            {
                // ����J�����_�R���e�L�X�g�̎s��R�[�h�����̐ݒ�ɖ߂�
                WEB3GentradeTradingTimeManagement.resetMarketCode(l_strOriginalMarketCode);
                
                return l_productQuote;
            }
        }

        // ����J�����_�R���e�L�X�g�̎s��R�[�h�����̐ݒ�ɖ߂�
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_strOriginalMarketCode);
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get�D��s��)<BR>
     * <BR>
     * (getPrimaryMarket()�̃I�[�o�[���C�h)<BR>
     * ����.�D��s��ɊY������s��ID�̎s��I�u�W�F�N�g��ԋp����B<BR>
     * ������.�D��s�� == null�̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * @@return WEB3GentradeMarket<BR>
     */
    public Market getPrimaryMarket()
    {
        final String STR_METHOD_NAME = "getPrimaryMarket()";
        log.entering(STR_METHOD_NAME);
        
        if(primaryMarket == null)
        {
            try
            {
                if (m_productRow.getPrimaryMarketIdIsNull() == false)
                {
                    primaryMarket =
                        (WEB3GentradeMarket)GtlUtils.getFinObjectManager().getMarket(m_productRow.getPrimaryMarketId());
                }
            }
            catch(NotFoundException nfe)
            {
                log.debug("�����e�[�u��.�D��s��:[" + m_productRow.getPrimaryMarketId() +
                    "]�ɊY������s��I�u�W�F�N�g�Ȃ�");
                log.exiting(STR_METHOD_NAME);
                return null;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return primaryMarket;
    }
    
    /**
     * (get����s��ꗗ)<BR>
     * <BR>
     * (getTradedMarkets()�̃I�[�o�[���C�h)<BR>
     * this.����ID�ɊY�������������e�[�u���̃f�[�^���擾���A<BR>
     * �e�s��ID�ɊY������s��I�u�W�F�N�g�̈ꗗ��ԋp����B<BR>
     * <BR>
     * this.����ID�ɊY�����������������݂��Ȃ��ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * @@return WEB3GentradeMarket[]<BR>
     */
    public Market[] getTradedMarkets()
    {
        final String STR_METHOD_NAME = "getTradedMarkets()";
        log.entering(STR_METHOD_NAME);
        
        EqtypeTradedProductRow row = null;
        List rows = null;
        try
        {
            rows = EqtypeTradedProductDao.findRowsByProductId(m_productRow.getProductId());
        }
        catch(DataException dex)
        {
            String msg = "����ID�ɊY�����銔����������ꗗ�̎擾�Ɏ��s���܂��� : " + m_productRow.getProductId();
            log.debug(msg);
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        FinObjectManager finObjMgr = GtlUtils.getFinObjectManager();
        TreeMap l_map = new TreeMap();
        for(int i = 0; i < rows.size(); i++)
        {
            try
            {
                 row = (EqtypeTradedProductRow)rows.get(i);
                WEB3GentradeMarket l_market =
                     (WEB3GentradeMarket)finObjMgr.getMarket(row.getMarketId());
                l_map.put(Integer.valueOf(l_market.getMarketCode()), l_market);
            }
            catch(NotFoundException nfex)
            {
                String msg = "�������.�s��ID�ɊY������s��I�u�W�F�N�g�̎擾�Ɏ��s���܂��� : " + row.getMarketId();
                log.error(msg, nfex);
                throw new RuntimeSystemException(msg, nfex);
            }
        }

        log.exiting(STR_METHOD_NAME);
        WEB3GentradeMarket markets[] = new WEB3GentradeMarket[l_map.size()];
        l_map.values().toArray(markets);
        return markets;
    }
    
    /**
     * (is��W����)<BR>
     * <BR>
     * ��W�������ǂ������ʂ���B <BR>
     * <BR>
     * ��W�����̏ꍇ��true���A�ȊOfalse��ԋp����B <BR>
     * <BR>
     * �P�j��W�������� <BR>
     * �@@this.�����R�[�h��5byte�ڂ�1�̏ꍇ�Atrue��ԋp����B <BR>
     * �@@�ȊO�Afalse��ԋp����B <BR>
     * @@return boolean
     */
    public boolean isSubscriptionProduct()
    {
        final String STR_METHOD_NAME = "isSubscriptionProduct()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        
        // ��W��������
        return this.getProductCode().endsWith("1");
    }

    /**
     * (get�����t���ŏI��)<BR>
     * �����̌����t���ŏI�����擾����B<BR>
     *  <BR>
     * ���������ithis.get��������()�̖߂�l�j�̑O�c�Ɠ��̓��t��ԋp����B<BR>
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getRightCondOrderEndDay() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getRightCondOrderEndDay()";
        log.entering(STR_METHOD_NAME);

        //this.get��������()�̖߂�l
        Date l_datDevidendRightDate = this.getDevidendRightDate();

        Timestamp l_tsDevidendRightDate = new Timestamp(l_datDevidendRightDate.getTime());

        WEB3GentradeBizDate l_gentradeBizDate =
            new WEB3GentradeBizDate(l_tsDevidendRightDate);

        // ���������ithis.get��������()�̖߂�l�j�̑O�c�Ɠ��̓��t��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_gentradeBizDate.roll(-1);
    }
}
@
