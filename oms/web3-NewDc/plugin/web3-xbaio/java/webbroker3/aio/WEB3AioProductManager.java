head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.24.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioProductManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : AIO�v���_�N�g�}�l�[�W��(WEB3AioProductManager)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/06 ���z (���u) �V�K�쐬
                   2005/03/31 �����(���u) �d�l�ύX�̑Ή�
                   2006/10/25 �Ԑi (���u) ���f��No.662
                   2006/10/26 �Ԑi (���u) ���f��No.663
*/

package webbroker3.aio;

import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProductManager;

import webbroker3.bd.WEB3BondProduct;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (AIO�v���_�N�g�}�l�[�W��)<BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioProductManager implements ProductManager
{
    /**
    * ���O���[�e�B���e�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioProductManager.class);

    /**
     * (get����)<BR>
     * (getProduct�̃I�[�o�[���[�h)<BR>
     * �����^�C�v�A����ID�ɍ��v��������I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �P�j�g���[�f�B���O���W���[���̎擾<BR>
     * <BR>
     * �@@FinApp.getTradingModule(arg0)<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@arg0�F ����.�����^�C�v<BR>
     * <BR>
     * �Q�j�v���_�N�g�}�l�[�W���̎擾<BR>
     * <BR>
     * �@@TradingModule.getProductManager()<BR>
     * <BR>
     * �R�j�����I�u�W�F�N�g�̎擾<BR>
     * <BR>
     * �@@ProductManager.getProduct(arg0)<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@arg0�F ����.����ID<BR>
     * @@param l_productType - �����^�C�v
     * @@param l_lngProductId - ����ID
     * @@return Product
     * @@throws WEB3BaseException
     * @@roseuid 4164A37F02E9
     */
    public Product getProduct(ProductTypeEnum l_productType, long l_lngProductId)
        throws WEB3BaseException
    {
        String l_strMethodName =
            "getProduct(ProductTypeEnum l_productType, long l_lngProductId)";
        log.entering(l_strMethodName);

        // 1�j�g���[�f�B���O���W���[���̎擾
        //FinApp.getTradingModule(arg0)
        //[����]
        //arg0�F ����.�����^�C�v
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(l_productType);

        // �Q�j�v���_�N�g�}�l�[�W���̎擾
        //TradingModule.getProductManager()
        ProductManager l_productManager = l_tradingModule.getProductManager();

        // 3�j�����I�u�W�F�N�g�̎擾
        //ProductManager.getProduct(arg0)
        //[����]
        //arg0�F ����.����ID
        Product l_product = null;
        try
        {
            // test log
            log.debug("l_productId = " + l_lngProductId);
            
            l_product = l_productManager.getProduct(l_lngProductId);

            // test log
            log.debug("l_product = " + l_product.getDataSourceObject());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__error in �����I�u�W�F�N�g�̎擾__", l_ex);
            log.exiting(l_strMethodName);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(l_strMethodName);

        return l_product;
    }

    /**
     * (get����)<BR>
     * (getProduct�̃I�[�o�[���[�h)<BR>
     * �����^�C�v�A�����R�[�h�ɍ��v��������I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �P�j����.�����^�C�v == �h���h �̏ꍇ<BR>
     * <BR>
     * �P�|�P�j����.�����R�[�h����A���̖����R�[�h(SONAR)�Ɖ񍆃R�[�h(SONAR)���擾����B<BR>
     * <BR>
     * �@@�񍆃R�[�h(SONAR) = ����.�����R�[�h�̐擪����5�����̕�����<BR>
     * �@@�����R�[�h(SONAR) = ����.�����R�[�h��6�����ڈȍ~�̕�����<BR>
     * <BR>
     * �P�|�Q�j�ȉ��̏����ŁA�������}�X�^�e�[�u�����烌�R�[�h���擾����B<BR>
     * <BR>
     * �@@[�擾����]<BR>
     * �@@�،���ЃR�[�h = ����.�،����.getInstitutionCode()�̖߂�l<BR>
     * �@@�����R�[�h(SONAR) like '�����R�[�h(SONAR)%'<BR>
     * �@@�񍆃R�[�h(SONAR) = �񍆃R�[�h(SONAR)<BR>
     * <BR>
     * �P�|�R�j�擾�������R�[�h����������}�X�^�I�u�W�F�N�g�𐶐����āA�ԋp����B<BR>
     * <BR>
     * �Q�j����.�����^�C�v != �h���h �̏ꍇ<BR>
     * <BR>
     * �Q�|�P�j�g���[�f�B���O���W���[���̎擾<BR>
     * <BR>
     * �@@FinApp.getTradingModule(arg0)<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@arg0�F ����.�����^�C�v<BR>
     * <BR>
     * �Q�|�Q�j�v���_�N�g�}�l�[�W���̎擾<BR>
     * <BR>
     * �@@TradingModule.getProductManager()<BR> 
     * <BR>
     * �Q�|�R�j�����I�u�W�F�N�g�̎擾<BR>
     * <BR>
     * �Q�|�R�|�P�j����.�����^�C�v=�h�����h �̏ꍇ<BR>
     * <BR>
     * �@@ProductManager.getProduct(arg0, arg1)<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@arg0�F ����.�،����<BR>
     * �@@arg1�F ����.�����R�[�h<BR>
     * <BR>
     * �Q�|�R�|�Q�j����.�����^�C�v=�h���M�h �̏ꍇ<BR>
     * <BR>
     * �@@ProductManager.getMutualFundProduct(arg0, arg1, arg2)<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@arg0�F ����.�،����<BR>
     * �@@arg1�F ����.�����R�[�h<BR>
     * �@@arg2�F �񍆃R�[�h(=0)<BR>
     * @@param l_productType - �����^�C�v
     * @@param l_strProductCode - �����R�[�h
     * @@param l_institusion - �،���ЃI�u�W�F�N�g
     * @@return Product
     * @@throws WEB3BaseException
     * @@roseuid 4164A37F02EC
     */
    public Product getProduct(ProductTypeEnum l_productType, String l_strProductCode, Institution l_institusion)
        throws WEB3BaseException
    {
        String l_strMethodName =
            "getProduct(ProductTypeEnum l_productType, String l_strProductCode, Institution l_institusion)";
        log.entering(l_strMethodName);

        // �P�j����.�����^�C�v == �h���h �̏ꍇ
        if (ProductTypeEnum.BOND.equals(l_productType))
        {
            //�P�|�P�j����.�����R�[�h����A���̖����R�[�h(SONAR)�Ɖ񍆃R�[�h(SONAR)���擾����B
            //�񍆃R�[�h(SONAR) = ����.�����R�[�h�̐擪����5�����̕�����
            //�����R�[�h(SONAR) = ����.�����R�[�h��6�����ڈȍ~�̕�����

            String l_strHostProductIssueCode = l_strProductCode.substring(0, 5);
            String l_strHostProductCode = l_strProductCode.substring(5);

            // test log
            log.debug("l_strHostProductIssueCode = " + l_strHostProductIssueCode);
            log.debug("l_strHostProductCode = " + l_strHostProductCode);

            //�P�|�Q�j�ȉ��̏����ŁA�������}�X�^�e�[�u�����烌�R�[�h���擾����B
            //[�擾����]
            //�،���ЃR�[�h = ����.�،����.getInstitutionCode()�̖߂�l
            //�����R�[�h(SONAR) like '�����R�[�h(SONAR)%'
            //�񍆃R�[�h(SONAR) = �񍆃R�[�h(SONAR)
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                List l_lislist = l_queryProcessor.doFindAllQuery(
                     BondProductRow.TYPE,
                    "institution_code = ? and host_product_code like ? ||'%' and host_product_issue_code = ?",
                    null,
                    new Object[]{l_institusion.getInstitutionCode(), l_strHostProductCode, l_strHostProductIssueCode});
                switch (l_lislist.size())
                {
                    //�P�|�R�j�擾�������R�[�h����������}�X�^�I�u�W�F�N�g�𐶐����āA�ԋp����B
                    case 1: return new WEB3BondProduct((BondProductRow)(l_lislist.get(0)));
                    default:
                        throw new DataFindException(
                        "too many or no results in query WEB3AioProductManager.getProduct("
                        + "ProductTypeEnum l_productType, String l_strProductCode, Institution l_institusion): "
                        + l_lislist.size());
                }
            }
            catch (DataException l_ex)
            {
                log.error("__error in �����I�u�W�F�N�g�i��)�̎擾__", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
            }

        }

        //�Q�j����.�����^�C�v != �h���h �̏ꍇ
        //
        //�Q�|�P�j�g���[�f�B���O���W���[���̎擾
        //
        //FinApp.getTradingModule(arg0)
        //
        //[����]
        //arg0�F ����.�����^�C�v
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(l_productType);

        //�Q�|�Q�j�v���_�N�g�}�l�[�W���̎擾
        //
        //TradingModule.getProductManager()
        ProductManager l_productManager = l_tradingModule.getProductManager();

        //�Q�|�R�j�����I�u�W�F�N�g�̎擾
        //
        Product l_product = null;

        //�Q�|�R�|�P�j����.�����^�C�v=�h�����h �̏ꍇ
        //
        //ProductManager.getProduct(arg0, arg1)
        //
        //[����]
        //arg0�F ����.�،����
        //arg1�F ����.�����R�[�h
        if (ProductTypeEnum.EQUITY.equals(l_productType))
        {

            //Cast of the productManager
            EqTypeProductManager l_eqTypeProductManager = (EqTypeProductManager)l_productManager;
            try
            {
                l_product =
                    l_eqTypeProductManager.getProduct(
                        l_institusion,
                        l_strProductCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("__error in �����I�u�W�F�N�g�i����)�̎擾__", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //�Q�|�R�|�Q�j����.�����^�C�v=�h���M�h �̏ꍇ
        //
        //ProductManager.getMutualFundProduct(arg0, arg1, arg2)
        //
        //[����]
        //arg0�F ����.�،����
        //arg1�F ����.�����R�[�h
        //arg2�F �񍆃R�[�h(=0)
        else if (ProductTypeEnum.MUTUAL_FUND.equals(l_productType))
        {
            //Cast of the productManager
            MutualFundProductManager l_mutualFundProductManager =
                (MutualFundProductManager)l_productManager;
            try
            {
                l_product =
                    l_mutualFundProductManager.getMutualFundProduct(
                        l_institusion,
                        l_strProductCode,
                        "0");
            }
            catch (NotFoundException l_ex)
            {
                log.error("__error in �����I�u�W�F�N�g�i���M)�̎擾__", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        log.exiting(l_strMethodName);
        return l_product;
    }

    /**
     * @@param arg0
     * @@return Product
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 41B0255F0177
     */
    public Product getProduct(long arg0) throws NotFoundException 
    {
     return null;
    }
    
    /**
     * @@param arg0
     * @@param arg1
     * @@return TradedProduct
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 41B0255F0280
     */
    public TradedProduct getTradedProduct(Product arg0, Market arg1) throws NotFoundException 
    {
     return null;
    }
    
    /**
     * @@param arg0
     * @@param arg1
     * @@return TradedProduct
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 41B02560005D
     */
    public TradedProduct getTradedProduct(long arg0, long arg1) throws NotFoundException 
    {
     return null;
    }
    
    /**
     * @@param arg0
     * @@return TradedProduct
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 41B025600213
     */
    public TradedProduct getTradedProduct(long arg0) throws NotFoundException 
    {
     return null;
    }
    
    /**
     * @@param arg0
     * @@return Product
     * @@roseuid 41B02560032C
     */
    public Product toProduct(Row arg0) 
    {
     return null;
    }
    
    /**
     * @@param arg0
     * @@return TradedProduct
     * @@roseuid 41B02561004E
     */
    public TradedProduct toTradedProduct(Row arg0) 
    {
     return null;
    }
    
    /**
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@param arg3
     * @@param arg4
     * @@return java.util.List
     * @@roseuid 41B025610157
     */
    public List searchProducts(Institution arg0, ProductTypeEnum arg1, String arg2, PaginationQueryParamsSpec arg3, SortKeySpec arg4) 
    {
     return null;
    }
    
    /**
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@param arg3
     * @@param arg4
     * @@return java.util.List
     * @@roseuid 41B025620167
     */
    public List searchProductsBeginningWith(Institution arg0, ProductTypeEnum arg1, String arg2, PaginationQueryParamsSpec arg3, SortKeySpec arg4) 
    {
     return null;
    }
    
    /**
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@param arg3
     * @@return java.util.List
     * @@roseuid 41B025630148
     */
    public List searchProducts(ProductTypeEnum arg0, Market arg1, PaginationQueryParamsSpec arg2, SortKeySpec arg3) 
    {
     return null;
    }
}
@
