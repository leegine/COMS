head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.43.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoProductManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�v���_�N�g�}�l�[�W��(WEB3IpoProductManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ���o�� �V�K�쐬
Revesion History : 2005/01/05 ���(SRA) �c�Ή�>>>050
*/

package webbroker3.ipo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.PaginationQueryParamsSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SortKeySpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3IpoStopDef;
import webbroker3.common.define.WEB3LotStatusDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.ipo.data.IpoProductDao;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * IPO�v���_�N�g�}�l�[�W���N���X�B<BR>
 * 
 * @@author ���o��
 * @@version 1.0
 * 
 * IPO�����ɑ΂��鑀�����������B<BR>
 * <BR>
 * (*) IPO�ł͎�������͎g�p���Ȃ����߁AgetTradedProduct()�A<BR>toTradedProduct()�̓X�^�u�Ƃ���B
 */
public class WEB3IpoProductManagerImpl implements ProductManager 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3IpoProductManagerImpl.class);
    
    /**
     * @@roseuid 4113017900F7
     */
    public WEB3IpoProductManagerImpl() 
    {
     
    }
    
    /**
     * (get����)
     * �igetProduct�̎����j<BR>
     * <BR>
     * �w�肵��IPO�����h�c�ɊY������s��IPO�����e�[�u����茟������B<BR>
     * �������ʂ�IPO�����s�I�u�W�F�N�g�������Ɏw�肵�āAIPO�����I�u�W�F�N�g��<BR>��������B<BR>
     * ���������I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * [�R���X�g���N�^�Ɏw�肷�����]<BR>
     * IPO�����s�I�u�W�F�N�g
     * @@param l_lngProductId - IPO�����h�c
     * @@return Product
     * @@throws NotFoundException
     * @@roseuid 40BDA43E0298
     */
    public Product getProduct(long l_lngProductId) throws NotFoundException
    {
        final String STR_METHOD_NAME = " getProduct(long)";
        log.entering(STR_METHOD_NAME);
                    
        IpoProductRow l_productRow;
        try
        {
            l_productRow = IpoProductDao.findRowByPk(l_lngProductId);
            log.debug("l_productRow =" + l_productRow);

            log.exiting(STR_METHOD_NAME);
            return toProduct(l_productRow);
        }
        
        catch (DataFindException l_ex)
        {
            throw new NotFoundException("�Y������IPO�����s�����݂��Ȃ�");
        }
        catch (DataQueryException l_ex)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME
            );
        }
        catch (DataNetworkException l_ex)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME
            );
        }            
    }
    
    /**
     * (get�L������)<BR>
     * �،���ЁAIPO�o�^�敪�ɊY������L����IPO�����I�u�W�F�N�g�z���<BR>
     * �擾����B<BR>
     * <BR>
     * �P�j�@@IPO�����e�[�u������<BR>
     * �@@IPO�����e�[�u�����������A�ȉ��̏����Ɉ�v����s���擾����B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@IPO�����e�[�u��.�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@IPO�����e�[�u��.IPO�o�^�敪 = ����.IPO�o�^�敪<BR>
     * �@@IPO�����e�[�u��.�폜�t���O = �폜�łȂ��iBooleanEnum.FALSE�j<BR>
     * <BR>
     *   [�擾��]<BR>
     *    ���J���@@�~���i�Fdesc�j�C<BR>
     *  �@@�����R�[�h�@@�����i�Fasc�j<BR> 
     * <BR>
     * �Q�j�@@IPO�����I�u�W�F�N�g�ԋp<BR>
     * �@@�擾�����e�s�I�u�W�F�N�g�ɂ�IPO�����I�u�W�F�N�g�𐶐�(*1)���A<BR>
     * IPO�����I�u�W�F�N�g�̔z���ԋp����B<BR>
     * <BR>
     * �@@(*1)�@@�s�I�u�W�F�N�g�ɂ�IPO�����I�u�W�F�N�g�𐶐�<BR>
     * �@@this.to����()�ɂ�IPO�����I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@[to����()�Ɏw�肷�����]<BR>
     * �@@�擾�����s�I�u�W�F�N�g<BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strRegType - IPO�o�^�敪<BR>
     * <BR>
     * �@@1�F�V�K���<BR>
     * �@@2�F�����<BR>
     * �@@�� DB���C�A�E�g�uIPO�����e�[�u���v�Q�ƁB
     * 
     * @@return webbroker3.ipo.WEB3IpoProductImpl[]
     * @@roseuid 40BEFBF503B8
     */
    public WEB3IpoProductImpl[] getOpenIpoProducts(String l_strInstitutionCode, String l_strRegType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOpenIpoProducts(String,String)";
        log.entering(STR_METHOD_NAME);
        
        QueryProcessor l_queryProcesser = null;
        WEB3IpoProductImpl[] l_ipoProduct = null;
        try
        {
            //IPO�����e�[�u������
            l_queryProcesser = Processors.getDefaultProcessor();
            String l_strWhere =
                    "institution_code = ? and " +
                    "ipo_regist_div = ? and " +
                    "delete_flag = ?";
                    
            String l_strOrderBy = " public_offering_date desc, product_code asc";
            
            Object l_bindVars[] =
                {   new String(l_strInstitutionCode),
                    new String(l_strRegType),
                    new Integer(BooleanEnum.IntValues.FALSE)};
            log.debug("new String(l_strInstitutionCode) = " + new String(l_strInstitutionCode));        
            log.debug("new String(l_strRegType) = " + new String(l_strRegType));
            log.debug("new Integer(BooleanEnum.IntValues.FALSE) = " + new Integer(BooleanEnum.IntValues.FALSE));
                    
            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                IpoProductRow.TYPE,
                l_strWhere,
                l_strOrderBy,
                null,
                l_bindVars);
            
            int l_intSize = l_lisRows.size();
            log.debug("l_lngSize = " + l_intSize);
            
            if (l_intSize == 0)
            {
                return null;
//                log.exiting(STR_METHOD_NAME);
//
//                throw new WEB3SystemLayerException(
//                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
//                    getClass().getName() + STR_METHOD_NAME);
            }
            
            //IPO�����I�u�W�F�N�g�ԋp
            List l_lisProductRows = new ArrayList();
            
            for(int i= 0; i < l_intSize; i++)
            {
                log.debug("�e�s�I�u�W�F�N�g�ɂ�IPO�����I�u�W�F�N�g�𐶐�");

                l_lisProductRows.add(this.toProduct((IpoProductRow)l_lisRows.get(i)));
            }
            
            l_ipoProduct = new WEB3IpoProductImpl[l_intSize];
            
            l_lisProductRows.toArray(l_ipoProduct);
        
            log.exiting(STR_METHOD_NAME);
            
            log.debug("l_ipoProduct.length = " + l_ipoProduct.length);
            for(int i= 0; i < l_intSize; i++)
            {
                log.debug("l_ipoProductid = " + l_ipoProduct[i].getProductId());
            }
            
            return l_ipoProduct;         
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
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
     * (get�戵���L������)<BR>
     * �،���ЁAIPO�o�^�敪�ɊY������L����IPO�����ŁA<BR>
     * �戵���i���J�� > �P�����O�j�̂��̂�z��Ŏ擾����B<BR>
     * <BR>
     * �P�j�@@���J���̂P�����O�擾<BR>
     * ���ݓ���(*1)�̂P�����O��Timestamp���擾����B<BR>
     * <BR>
     * (*1) ���ݓ���<BR>
     * TradingSystem.getSystemTimestamp()�ɂĎ擾����B<BR>
     * <BR>
     * 
     * �Q�j�@@IPO�����e�[�u������<BR>
     * �@@IPO�����e�[�u�����������A�ȉ��̏����Ɉ�v����s���擾����B<BR>
     * <BR>
     * �@@[��������] 
     * �@@IPO�����e�[�u��.�،���ЃR�[�h = ����.�،���ЃR�[�h�@@And 
     * �@@IPO�����e�[�u��.IPO�o�^�敪 = ����.IPO�o�^�敪�@@And 
     * �@@IPO�����e�[�u��.�폜�t���O = �폜�łȂ��iBooleanEnum.FALSE�j�@@And 
     * �@@�iIPO�����e�[�u��.���J�� > �P�j�Ŏ擾����Timestamp�@@Or�@@ IPO�����e�[�u��.���J�� = null�j
     * <BR>
     *   [�擾��]<BR>
     *    ���J���@@�~���i�Fdesc�j�C<BR>
     *  �@@�����R�[�h�@@�����i�Fasc�j<BR> 
     * <BR>
     * �Q�j�@@IPO�����I�u�W�F�N�g�ԋp<BR>
     * �@@�擾�����e�s�I�u�W�F�N�g�ɂ�IPO�����I�u�W�F�N�g�𐶐�(*1)���A<BR>
     * IPO�����I�u�W�F�N�g�̔z���ԋp����B<BR>
     * <BR>
     * �@@(*1)�@@�s�I�u�W�F�N�g�ɂ�IPO�����I�u�W�F�N�g�𐶐�<BR>
     * �@@this.to����()�ɂ�IPO�����I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@[to����()�Ɏw�肷�����]<BR>
     * �@@�擾�����s�I�u�W�F�N�g<BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strRegType - IPO�o�^�敪<BR>
     * <BR>
     * �@@1�F�V�K���<BR>
     * �@@2�F�����<BR>
     * �@@�� DB���C�A�E�g�uIPO�����e�[�u���v�Q�ƁB
     * 
     * @@return webbroker3.ipo.WEB3IpoProductImpl[]
     * @@roseuid 40D2B7520278
     */
    public WEB3IpoProductImpl[] getDealtInProcessOpenIpoProducts(String l_strInstitutionCode, String l_strRegType) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDealtInProcessOpenIpoProducts(String,String)";
        log.entering(STR_METHOD_NAME);
        
        //���J���̂P�����O�擾
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();   //���ݓ���
        
        //IPO�����e�[�u������
        QueryProcessor l_queryProcesser = null;
        WEB3IpoProductImpl[] l_ipoProduct = null;
        try
        {
            l_queryProcesser = Processors.getDefaultProcessor();
            
            String l_strWhere =
                    "institution_code = ? and " +
                    "ipo_regist_div = ? and " +
                    "delete_flag = ? and " + 
                    "(public_offering_date > add_months(?, ?) or " +
                    "public_offering_date is null)";
                    
            String l_strOrderBy = " public_offering_date desc, product_code asc";
            
            Object l_bindVars[] =
                {   new String(l_strInstitutionCode),
                    new String(l_strRegType),
                    new Integer(BooleanEnum.IntValues.FALSE),
                    WEB3DateUtility.formatDate(l_tsCurrentTime,"yyyy/MM/dd"),
                    new Integer(-1)};
                    
            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                IpoProductRow.TYPE,
                l_strWhere,
                l_strOrderBy,
                null,
                l_bindVars);
            
            int l_intSize = l_lisRows.size();
            if (l_intSize == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            
            //IPO�����I�u�W�F�N�g�ԋp
            List l_lisProductRows = new ArrayList();
            
            for(int i= 0; i < l_intSize; i++)
            {
                log.debug("�e�s�I�u�W�F�N�g�ɂ�IPO�����I�u�W�F�N�g�𐶐�");

                l_lisProductRows.add(this.toProduct((IpoProductRow)l_lisRows.get(i)));
            }
            
            l_ipoProduct = new WEB3IpoProductImpl[l_intSize];
            
            l_lisProductRows.toArray(l_ipoProduct);
        
            log.exiting(STR_METHOD_NAME);
            
            log.debug("l_ipoProduct.length = " + l_ipoProduct.length);
            for(int i= 0; i < l_intSize; i++)
            {
                log.debug("l_ipoProductid = " + l_ipoProduct[i].getProductId());
            }
            
            return l_ipoProduct;  
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
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
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@param l_arg0
     * @@param l_arg1
     * @@return TradedProduct
     * @@throws NotFoundException
     * @@roseuid 40BDA43E029A
     */
    public TradedProduct getTradedProduct(Product l_arg0, Market l_arg1) throws NotFoundException 
    {
        final String STR_METHOD_NAME = " getTradedProduct(Product,Market)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return null;
        
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@param l_lngArg0
     * @@param l_lngArg1
     * @@return TradedProduct
     * @@throws NotFoundException
     * @@roseuid 40BDA43E029D
     */
    public TradedProduct getTradedProduct(long l_lngArg0, long l_lngArg1) throws NotFoundException 
    {
        final String STR_METHOD_NAME = " getTradedProduct(long,long)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@param l_lngArg0
     * @@return TradedProduct
     * @@throws NotFoundException
     * @@roseuid 40BDA43E02A0
     */
    public TradedProduct getTradedProduct(long l_lngArg0) throws NotFoundException 
    {
        final String STR_METHOD_NAME = " getTradedProduct(long)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (to����)
     * �itoProduct�̎����j<BR>
     * <BR>
     * �w���ProductRow�I�u�W�F�N�g����Product�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * IPO�����s�I�u�W�F�N�g�������Ɏw�肵�āAIPO�����I�u�W�F�N�g�𐶐�����B<BR>
     * ���������I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * [�R���X�g���N�^�Ɏw�肷�����]<BR>
     * IPO�����s�I�u�W�F�N�g
     * @@param l_ipoProductParams - IPO�����s�I�u�W�F�N�g
     * @@return Product
     * @@roseuid 40BDA43E02A2
     */
    public Product toProduct(Row l_ipoProductParams) 
    {
        final String STR_METHOD_NAME = " toProduct(Row)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return new WEB3IpoProductImpl(l_ipoProductParams);
        
    }
    
    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@param l_arg0
     * @@return TradedProduct
     * @@roseuid 40BDA43E02A4
     */
    public TradedProduct toTradedProduct(Row l_arg0) 
    {
        final String STR_METHOD_NAME = " toTradedProduct(Row)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * �isave�����j<BR>
     * <BR>
     * �P�j IPO�����s�I�u�W�F�N�g�擾<BR>
     * �@@IPO����.getDataSourceObject()�ɂ�IPO�����s���擾����B<BR>
     * <BR>
     * �Q�j �X�V�����Z�b�g����B<BR>
     * �@@IPO�����s�̈ȉ��̍��ڂɁA�l���Z�b�g����B<BR>
     * <BR>
     * �@@IPO�����s.�X�V�҃R�[�h = �Ǘ���.getInstanceFrom���O�C�����().get<BR>�Ǘ��҃R�[�h()<BR>
     * �@@IPO�����s.�X�V���� = TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * �R�j DB�X�V<BR>
     * �@@IPO�����s�I�u�W�F�N�g�̓��e�ŁAIPO�����e�[�u�����X�V�iupdate�j����B<BR>
     * @@param l_ipoProduct - IPO�����I�u�W�F�N�g
     * @@roseuid 40BEBC0B03A8
     */
    public void saveProduct(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveProduct(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);
        
        //IPO�����s�I�u�W�F�N�g�擾
        IpoProductRow l_ipoProductRow = (IpoProductRow)l_ipoProduct.getDataSourceObject();
        IpoProductParams l_ipoProductParams = new IpoProductParams(l_ipoProductRow); 
        
        //�X�V�����Z�b�g����B
        String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        Timestamp l_currentTime = l_finApp.getTradingSystem().getSystemTimestamp();  
        
        try{
            QueryProcessor l_queryProcesser = null;
            l_queryProcesser = Processors.getDefaultProcessor();
            
            l_ipoProductParams.setLastUpdater(l_strAdministratorCode);
            l_ipoProductParams.setLastUpdatedTimestamp(l_currentTime);
            
            l_queryProcesser.doUpdateQuery(l_ipoProductParams);        
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
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
     * �isaveNew�����j<BR>
     * <BR>
     * �w�肳�ꂽIPO�����I�u�W�F�N�g�̓��e�ŁAIPO�����e�[�u�����X�V�iinsert�j����B<BR>
     * <BR>
     * �P�j�@@IPO�����h�c�V�K�̔�<BR>
     * �@@����.IPO����.setNewId()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�擾<BR>
     * �@@����.IPO����.getDataSourceObject()�ɂčs�I�u�W�F�N�g���擾����B<BR>
     *   <BR>
     *   �@@�Q�|�P�j�@@�擾�����s�I�u�W�F�N�g�Ɉȉ��̒ʂ�A���ڂ̏����l���Z�b�g����B<BR> 
�@@   *     �����^�C�v = ProductTypeEnum.IPO <BR>
�@@   *     IPO��~ = 0�FDEFAULT�i�J�Ò��j <BR>
�@@   *     �폜�t���O = BooleanEnum.FALSE <BR>
�@@   *     �X�V�҃R�[�h = �Ǘ���.getInstanceFrom���O�C�����().get�Ǘ��҃R�[�h()<BR> 
�@@   *     �쐬���� = TradingSystem.getSystemTimestamp() <BR>
�@@   *     �X�V���� = TradingSystem.getSystemTimestamp() <BR>
     * <BR>
     * �R�j�@@DB�X�V<BR>
     * �@@�擾�����s�I�u�W�F�N�g�̓��e��IPO�����e�[�u���ɍs��}���iinsert�j����B<BR>
     * @@param l_ipoProduct - IPO�����I�u�W�F�N�g
     * @@roseuid 40BEEC4600F9
     */
    public void saveNewProduct(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewProduct(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);
        
        //IPO�����h�c�V�K�̔�
        l_ipoProduct.setNewId();
        
        //�s�I�u�W�F�N�g�擾
        IpoProductRow l_ipoProductRow = (IpoProductRow)l_ipoProduct.getDataSourceObject();
        log.debug("l_ipoProductRow.getIpoProductId() = " + l_ipoProductRow.getIpoProductId());
        IpoProductParams l_ipoProductParams =  new IpoProductParams(l_ipoProductRow);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        Timestamp l_currentTime = l_finApp.getTradingSystem().getSystemTimestamp();  
        
        //�����^�C�v
        l_ipoProductParams.setProductType(ProductTypeEnum.IPO);
        //���I���
        l_ipoProductParams.setLotStatus(WEB3LotStatusDef.DEFAULT);
        //IPO��~
        l_ipoProductParams.setIpoStop(WEB3IpoStopDef.DEFAULT);
        //�폜�t���O
        l_ipoProductParams.setDeleteFlag(BooleanEnum.FALSE);
        //�쐬����
        l_ipoProductParams.setCreatedTimestamp(l_currentTime);
        //�X�V����
        l_ipoProductParams.setLastUpdatedTimestamp(l_currentTime);
        
        //DB�X�V
        QueryProcessor l_queryProcesser = null;
        try
        {
            l_queryProcesser = Processors.getDefaultProcessor();
            
            l_queryProcesser.doInsertQuery(l_ipoProductParams);
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
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
     * (validate���ԏd���o�^)�@@
     * ��������R�[�h�ŁAIPO���Ԃ��d�Ȃ��Ă�����������ɓo�^���Ă��Ȃ���<BR>
     * �`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@��������<BR>
     * �@@�ȉ��̏�����IPO�����e�[�u������������B<BR>
     * �s�����݂��Ȃ��ꍇ�́A�������I��(return)����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@IPO�����e�[�u��.�،���ЃR�[�h = ����.IPO����.�،���ЃR�[�h<BR>
     * �@@IPO�����e�[�u��.�����R�[�h = ����.IPO����.�����R�[�h<BR>
     * �@@IPO�����e�[�u��.�폜�t���O = �h�폜�łȂ��h�iBooleanEnum.FALSE�j<BR>
     * <BR>
     * �Q�j�@@���Ԕ���<BR>
     * �@@�擾�����eIPO����Params�ɂ��āA�ȉ��̏������s���B<BR>
     * �@@�Q�|�P�j�@@�����O�̍sSkip<BR>
     * �@@�iIPO����Params.IPO�����h�c == ����.IPO�����h�c�j�̍s�́A�ȍ~�̏�����<BR>
     * ���{���Ȃ��B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@IPO�����I�u�W�F�N�g����<BR>
     * �@@IPO����Params�I�u�W�F�N�g�������Ɏw�肵�AIPO�����I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�� new IPO����(IPO����Params)<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�����s�X�P�W���[������<BR>
     * �@@IPO����.is�X�P�W���[������()�ɂāA�����s�̃X�P�W���[�������肵�Ă��邩<BR>
     * ���肷��B<BR>
     * �@@�X�P�W���[�������肵�Ă��Ȃ��ꍇ�iIPO����.is�X�P�W���[������() == false�j�A<BR>
     * ��O���X���[���������I���ireturn�j����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01982<BR>
     * <BR>
     * �@@�Q�|�S�j�@@���ԏd���`�F�b�N<BR>
     * �@@�@@�� ���͌��J��������i���J�����͒l == null�j�ŁA�ȉ��̏����ɓ��Ă�<BR>
     * �܂�ꍇ�A��O���X���[����B<BR>
     * �@@�@@[Error����]<BR>
     * �@@�@@�@@�iIPO����Params.�u�b�N�r���f�B���O�J�n���� <= �u�b�N�r���f�B���O<BR>
     * �J�n�������͒l <= IPO����Params.���J���j<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00506<BR>
     * <BR>
     * �@@�@@�� ���͌��J��������ρi���J�����͒l != null�j�ŁA�ȉ��̂����ꂩ��<BR>
     * �����ɓ��Ă͂܂�ꍇ�A��O���X���[����B<BR>
     * �@@�@@[Error���� 1]<BR>
     * �@@�@@�@@�iIPO����Params.�u�b�N�r���f�B���O�J�n���� <= �u�b�N�r���f�B���O<BR>
     * �J�n�������͒l <= IPO����Params.���J���j<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00507<BR>
     * �@@�@@[Error���� 2]<BR>
     * �@@�@@�@@�iIPO����Params.�u�b�N�r���f�B���O�J�n���� <= ���J�����͒l <= <BR>
     * IPO����Params.���J���j<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00508<BR>
     * �@@�@@[Error���� 3]<BR>
     * �@@�@@�@@�i�u�b�N�r���f�B���O�J�n�������͒l  <= IPO����Params.�u�b�N�r���f�B���O<BR>
     * �J�n�����j&&<BR>
     * �@@�@@�@@�iIPO����Params.���J�� <= IPO����Params.���J�����͒l�j<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00509<BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * 
     * @@param l_strProductCode - �����R�[�h
     * 
     * @@param l_datBookbuildingStartDate - �u�b�N�r���f�B���O�J�n����
     * @@param l_datPublicOfferingDate - ���J��
     * @@param l_lngProductId - IPO�����h�c
     * @@roseuid 40BF071E0127
     */
    public void validateDuplicateTerm(String l_strInstitutionCode, String l_strProductCode, Date l_datBookbuildingStartDate, Date l_datPublicOfferingDate, long l_lngProductId) 
                                          throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateDuplicateTerm(String,String,Date,Date,long)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //��������
            QueryProcessor l_queryProcesser = null;
            l_queryProcesser = Processors.getDefaultProcessor();
            
            String l_strWhere =
                    "institution_code = ? and " +
                    "product_code = ? and " +
                    "delete_flag = ?";
            
            Object l_bindVars[] =
                {   new String(l_strInstitutionCode),
                    new String(l_strProductCode),
                    new Integer(BooleanEnum.IntValues.FALSE)};
                    
            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                IpoProductRow.TYPE,
                l_strWhere,
                l_bindVars);
            
            int l_intSize = l_lisRows.size();
            log.debug("l_lngSize = " + l_intSize);
            if (l_intSize == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return;    
            }
            
            //���Ԕ���                                
            for (int i= 0; i < l_intSize; i++)
            {
                log.debug("���Ԕ���");
                IpoProductRow l_ipoProductRow = (IpoProductRow)l_lisRows.get(i);
                
                if (l_lngProductId != l_ipoProductRow.getIpoProductId())
                {
                    log.debug("l_lngProductId != l_ipoProductRow.getIpoProductId()");
                    log.debug("l_ipoProductRow.getIpoProductId() = " + l_ipoProductRow.getIpoProductId());
                    //IPO�����I�u�W�F�N�g����
                    WEB3IpoProductImpl l_ipoProduct = new WEB3IpoProductImpl(l_ipoProductRow);
                    //�����s�X�P�W���[������
                    if (!l_ipoProduct.isScheduleDecision())
                    {
                        log.debug("�����s�X�P�W���[������");
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01982,
                            getClass().getName() + STR_METHOD_NAME
                        );
                    }
                    //���ԏd���`�F�b�N
                    if (l_datPublicOfferingDate == null && 
                        l_datBookbuildingStartDate.compareTo(l_ipoProductRow.getBookbuildingStartDatetime()) >= 0 &&
                        l_datBookbuildingStartDate.compareTo(l_ipoProductRow.getPublicOfferingDate()) <= 0)
                    {
                        log.debug("���ԏd���`�F�b�N");
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00506,
                            getClass().getName() + STR_METHOD_NAME
                        );    
                    }
                    
                    if (l_datPublicOfferingDate != null)   //���͌��J���������
                    {
                        log.debug("l_datPublicOfferingDate != null");
                        //Error���� 1
                        if (l_datBookbuildingStartDate.compareTo(l_ipoProductRow.getBookbuildingStartDatetime()) >= 0 &&
                           l_datBookbuildingStartDate.compareTo(l_ipoProductRow.getPublicOfferingDate()) <= 0 )
                        {
                            log.debug("Error���� 1");
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00506,
                                getClass().getName() + STR_METHOD_NAME
                            ); 
                        }                                           
                        
                        //Error���� 2
                        if (l_datPublicOfferingDate.compareTo(l_ipoProductRow.getBookbuildingStartDatetime()) >= 0 && 
                           l_datPublicOfferingDate.compareTo(l_ipoProductRow.getPublicOfferingDate()) <= 0)
                        {
                            log.debug("Error���� 2");
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00506,
                                getClass().getName() + STR_METHOD_NAME
                            ); 
                        }
                        
                        //Error���� 3
                        if (l_datBookbuildingStartDate.compareTo(l_ipoProductRow.getBookbuildingStartDatetime()) <= 0 &&
                            l_datPublicOfferingDate.compareTo(l_ipoProductRow.getPublicOfferingDate()) >= 0)
                        {
                            log.debug("Error���� 3");
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00506,
                                getClass().getName() + STR_METHOD_NAME
                            ); 
                        }
                    }
                }
            }
        
            log.exiting(STR_METHOD_NAME);
                   
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
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
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager#searchProducts(com.fitechlabs.xtrade.plugin.tc.gentrade.Institution, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, java.lang.String, com.fitechlabs.xtrade.plugin.tc.gentrade.PaginationQueryParamsSpec, com.fitechlabs.xtrade.plugin.tc.gentrade.SortKeySpec)
     */
    public List searchProducts(Institution arg0, ProductTypeEnum arg1, String arg2, PaginationQueryParamsSpec arg3, SortKeySpec arg4)
    {
        return null;
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager#searchProductsBeginningWith(com.fitechlabs.xtrade.plugin.tc.gentrade.Institution, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, java.lang.String, com.fitechlabs.xtrade.plugin.tc.gentrade.PaginationQueryParamsSpec, com.fitechlabs.xtrade.plugin.tc.gentrade.SortKeySpec)
     */
    public List searchProductsBeginningWith(Institution arg0, ProductTypeEnum arg1, String arg2, PaginationQueryParamsSpec arg3, SortKeySpec arg4)
    {
        return null;
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager#searchProducts(com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, com.fitechlabs.xtrade.plugin.tc.gentrade.Market, com.fitechlabs.xtrade.plugin.tc.gentrade.PaginationQueryParamsSpec, com.fitechlabs.xtrade.plugin.tc.gentrade.SortKeySpec)
     */
    public List searchProducts(ProductTypeEnum arg0, Market arg1, PaginationQueryParamsSpec arg2, SortKeySpec arg3)
    {
        return null;
    }
}
@
