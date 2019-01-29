head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoProductManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���ݓ������}�l�[�W���N���X(WEB3RuitoProductManager)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/9  ��O�� (���u) �V�K�쐬
                   2004/12/01 ��O�� (���u) �c�Ή�
*/

package webbroker3.xbruito;

import java.util.Date;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.stdimpls.RuitoProductManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �g���ݓ������}�l�[�W���N���X<BR>
 */
public class WEB3RuitoProductManager extends RuitoProductManagerImpl
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoProductManager.class);

    /**
     * �igetRuitoProduct�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �g���ݓ��������擾����B<BR>
     * <BR>
     * �P�j�@@�ݓ�����Row�I�u�W�F�N�g���擾����B<BR>
     * �@@�|�ݓ�����Dao.findRowByInstitutionCodeProductCodeProductIssueCode()���R�[�����A<BR>
     *     �ݓ�����Row�I�u�W�F�N�g���擾����B<BR>
     * �@@�mfindRowByInstitutionCodeProductCodeProductIssueCode�ɓn���p�����^�n<BR>
     *      �،���ЃR�[�h :  ����.�،���ЃR�[�h <BR>
     * �@@�@@�@@�����R�[�h�F ����.�����R�[�h<BR>
     * �@@�@@�@@�񍆃R�[�h�F ����.�񍆃R�[�h<BR>
     * �@@�|findRowByInstitutionCodeProductCodeProductIssueCode���\�b�h��null��Ԃ����ꍇ��<BR>
     *     NotFoundException�����X���[����B<BR>
     * <BR>
     * �Q�j�@@�g���ݓ������I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * �@@�|�g���ݓ������I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@�m�R���X�g���N�^�ɓn���p�����^�n<BR>
     * �@@�@@�@@�ݓ�����Row�F �擾�����ݓ������I�u�W�F�N�g<BR>
     * �@@�|���������g���ݓ������I�u�W�F�N�g��Ԃ��B<BR>
     * @@param l_institution - �،����<BR>
     * @@param l_strProductCode - �����R�[�h<BR>
     * @@param l_strProductIssueCode - �񍆃R�[�h<BR>
     * @@return RuitoProduct
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 406D08660379
     */
    public RuitoProduct getRuitoProduct(
        Institution l_institution,
        String l_strProductCode,
        String l_strProductIssueCode)
        throws NotFoundException
    {
        String STR_METHOD_NAME =
            "getRuitoProduct(Institution l_institution, String l_strProductCode,"
                + "String l_strProductIssueCode)";
        log.entering(STR_METHOD_NAME);
        if (l_institution == null)
        {
            log.debug(" �p�����[�^Error ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }          
        RuitoProductRow l_ruitoProductRow = null;
        WEB3RuitoProduct l_ruitoProduct = null;
        try
        {
            String l_strInstitutionCode = l_institution.getInstitutionCode();
            
            log.debug("�،���ЃR�[�h = " + l_strInstitutionCode);
            log.debug("�����R�[�h = " + l_strProductCode);
            log.debug("�񍆃R�[�h = " + Long.parseLong(l_strProductIssueCode));
            //1)�ݓ�����Row�I�u�W�F�N�g���擾����B
            l_ruitoProductRow =
                RuitoProductDao.findRowByInstitutionCodeProductCodeProductIssueCode(
                    l_strInstitutionCode,
                    l_strProductCode,
                    l_strProductIssueCode);
            
            if (l_ruitoProductRow == null)
            {
                log.error("__NotFoundException__");
                throw new NotFoundException(
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
            //2)�g���ݓ������I�u�W�F�N�g�𐶐����ĕԂ��B
            l_ruitoProduct = new WEB3RuitoProduct(l_ruitoProductRow);
            //���������g���ݓ������I�u�W�F�N�g��Ԃ��B
            log.debug("===return l_ruitoProduct===");
            log.exiting(STR_METHOD_NAME);
            return l_ruitoProduct;
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

    /**
     * �igetRuitoTradedProduct�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �g���ݓ�����������擾����B<BR>
     * <BR>
     *�P�j�@@�ݓ���������I�u�W�F�N�g���擾����B <BR>                                                                                                                  
     *  super.getRuitoTradedProduct()���R�[�����āA<BR>
     *  �ݓ���������I�u�W�F�N�g���擾����B<BR>                                                                                                                     
     *  [getRuitoTradedProduct�ɓn���p�����^]<BR>                                                                                                                     
     *�@@�@@�،���ЁF ����.�،���� <BR>                                                                                                                     
     *�@@�@@�����R�[�h�F ����.�����R�[�h <BR>                                                                                                                      
     *�@@  �񍆃R�[�h�F ����.�񍆃R�[�h<BR>                                                                                                                       
     *�@@  �s��R�[�h�F ����.�s��R�[�h <BR>                                                                                                                      
     *�Q�j�@@�g���ݓ���������I�u�W�F�N�g���擾����B<BR>                                                                                                                     
     *�@@this.to�������()���R�[�����āA�g���ݓ���������I�u�W�F�N�g���擾����B<BR>                                                                                                                       
     *  [to��������ɓn���p�����^�n<BR>                                                                                                                        
     *�@@�@@Row�I�u�W�F�N�g�F �擾�����ݓ���������I�u�W�F�N�g<BR>
     *    .getDataSourceObject()�̖߂�l<BR>                                                                                                                     
     *�R�j�@@�擾�����g���ݓ���������I�u�W�F�N�g��Ԃ��B<BR>                                                                                                                  

     * @@param l_institution - �،����<BR>
     * @@param l_strProductCode - �����R�[�h<BR>
     * @@param l_strProductIssueCode - �񍆃R�[�h<BR>
     * @@param l_strMarketCode - �s��R�[�h<BR>
     * @@return RuitoTradedProduct
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 406D08660389
     */
    public RuitoTradedProduct getRuitoTradedProduct(
        Institution l_institution,
        String l_strProductCode,
        String l_strProductIssueCode,
        String l_strMarketCode)
        throws NotFoundException
    {
        String STR_METHOD_NAME =
            "getRuitoTradedProduct(Institution l_institution, "
                + "String l_strProductCode, String l_strProductIssueCode, "
                + "String l_strMarketCode)";
        log.entering(STR_METHOD_NAME);
        
        WEB3RuitoTradedProduct l_webRuitoTradedProduct = null;
        RuitoTradedProductRow l_ruitoTradeProductRow = null;       
        
        if (l_institution == null)
        {
            log.debug(" �p�����[�^Error ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }   
        //1)�ݓ��������Row�I�u�W�F�N�g���擾����B
        RuitoTradedProduct l_ruitoTradedProduct = null;
        //NotFoundException
        l_ruitoTradedProduct = super.getRuitoTradedProduct(
                l_institution,
                l_strProductCode,
                l_strProductIssueCode,
                l_strMarketCode);
        
        //�Q�j�g���ݓ���������I�u�W�F�N�g�𐶐����ĕԂ��B
        l_ruitoTradeProductRow = (RuitoTradedProductRow)
                l_ruitoTradedProduct.getDataSourceObject();
        l_webRuitoTradedProduct = (WEB3RuitoTradedProduct)
                this.toTradedProduct(l_ruitoTradeProductRow);

        log.exiting(STR_METHOD_NAME);
        //�R�j�擾�����g���ݓ���������I�u�W�F�N�g��Ԃ��B
        return l_webRuitoTradedProduct;
    }

    /**
     * �itoProduct�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �w��̖���Row�I�u�W�F�N�g����g���ݓ������I�u�W�F�N�g���쐬���ĕԂ��B<BR>
     * <BR>
     * �P�j����.Row�I�u�W�F�N�g��ProductRow�N���X�̃C���X�^���X�łȂ��A<BR>
     *  ����RuitoProductRow�N���X�̃C���X�^���X�łȂ��ꍇ�A<BR>
     *  IllegalArgumentException���X���[����B<BR>
     * <BR>
     * �Q�j�g���ݓ������I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�|����.Row�I�u�W�F�N�g��ProductRow�N���X�̃C���X�^���X�̏ꍇ<BR>
     * �@@�m�R���X�g���N�^�ɓn���p�����^�n<BR>
     *    ����Row�F ����.Row�I�u�W�F�N�g��ProductRow�N���X
     *                  �ɃL���X�g��������<BR>
     *   �|����.Row�I�u�W�F�N�g��RuitoProductRow�N���X�̃C���X�^���X<BR>
     *      �̏ꍇ <BR>
     *   �m�R���X�g���N�^�ɓn���p�����^�n
     *    �ݓ�����Row�F ����.Row�I�u�W�F�N�g��RuitoProductRow�N���X<BR>
     *                      �ɃL���X�g�������� <BR>
     * <BR>
     * �R�j���������g���ݓ������I�u�W�F�N�g��Ԃ��B<BR>
     * @@param l_row - Row�I�u�W�F�N�g<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.Product
     * @@roseuid 406D0889009B
     */
    public Product toProduct(Row l_row)
    {
        String STR_METHOD_NAME = "toProduct(Row l_Row)";
        log.entering(STR_METHOD_NAME);

        Product l_product = null;
        try
        {
            if ((l_row instanceof ProductRow) == false
                && (l_row instanceof RuitoProductRow) == false)
            {
                throw new IllegalArgumentException(
                    this.getClass().getName() + "toProduct(Row l_row)");
            }
            if (l_row instanceof ProductRow)
            {
                ProductParams l_productParams = (ProductParams) l_row;
                //�g���ݓ������I�u�W�F�N�g�𐶐�����
                WEB3RuitoProduct l_ruitoProduct = new WEB3RuitoProduct(l_productParams);
                l_product = (Product) l_ruitoProduct;
            }
            if (l_row instanceof RuitoProductRow)
            {
                RuitoProductParams l_ruitoProductParams = (RuitoProductParams) l_row;
                //�g���ݓ������I�u�W�F�N�g�𐶐�����
                WEB3RuitoProduct l_ruitoProduct = new WEB3RuitoProduct(l_ruitoProductParams);
                l_product = (Product) l_ruitoProduct;
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
        //�R�j���������g���ݓ������I�u�W�F�N�g��Ԃ��B
        return l_product;
    }

    /**
     * �itoTradedProduct�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �w���Row�I�u�W�F�N�g����g���ݓ���������I�u�W�F�N�g���쐬���ĕԂ��B<BR>
     * <BR>
     * �P�j����.Row�I�u�W�F�N�g��TradedProductRow�N���X�̃C���X�^���X�łȂ��A<BR>
     *  ����RuitoTradedProductRow�N���X�̃C���X�^���X�łȂ��ꍇ�A<BR>
     *  IllegalArgumentException���X���[����B<BR>
     * <BR>
     * �Q�j�g���ݓ���������I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�|����.Row�I�u�W�F�N�g��TradedProductRow�N���X�̃C���X�^���X�̏ꍇ<BR>
     * �@@�m�R���X�g���N�^�ɓn���p�����^�n<BR>
     *    �������Row�F ����.Row�I�u�W�F�N�g��TradedProductRow�N���X
     *                  �ɃL���X�g��������<BR>
     *   �|����.Row�I�u�W�F�N�g��RuitoTradedProductRow�N���X�̃C���X�^���X<BR>
     *      �̏ꍇ <BR>
     *   �m�R���X�g���N�^�ɓn���p�����^�n
     *    �ݓ��������Row�F ����.Row�I�u�W�F�N�g��RuitoTradedProductRow�N���X<BR>
     *                      �ɃL���X�g�������� <BR>
     * <BR>
     * �R�j���������g���ݓ������I�u�W�F�N�g��Ԃ��B<BR>
     * @@param l_row - Row�I�u�W�F�N�g<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct
     * @@roseuid 406D088900AB
     */
    public TradedProduct toTradedProduct(Row l_row)
    {
        String STR_METHOD_NAME = "toTradedProduct(Row l_Row)";
        log.entering(STR_METHOD_NAME);

        TradedProduct l_tradedProduct = null;
        WEB3RuitoTradedProduct l_ruitoTradedProduct = null;

        try
        {
            if ((l_row instanceof TradedProductRow) == false 
                && (l_row instanceof RuitoTradedProductRow) == false)
            {
                throw new IllegalArgumentException(
                    "Expected TradedProductRow or RuitoTradedProductRow."
                        + "But the given Row is the type of: "
                        + l_row.getClass());
            }
            if (l_row instanceof TradedProductRow)
            {
                TradedProductRow l_tradeProductRow = (TradedProductRow) l_row;
                //�g���ݓ������I�u�W�F�N�g�𐶐�����
                //DataQueryException,DataNetworkException
                l_ruitoTradedProduct = new WEB3RuitoTradedProduct(l_tradeProductRow);
                l_tradedProduct = (TradedProduct) l_ruitoTradedProduct;
            }
            if (l_row instanceof RuitoTradedProductRow)
            {
                RuitoTradedProductRow l_ruitoTradeProductRow = (RuitoTradedProductRow) l_row;
                //�g���ݓ������I�u�W�F�N�g�𐶐�����
                //DataQueryException,DataNetworkException
                l_ruitoTradedProduct = new WEB3RuitoTradedProduct(l_ruitoTradeProductRow);
                l_tradedProduct = (TradedProduct) l_ruitoTradedProduct;
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
        //�R�j���������g���ݓ������I�u�W�F�N�g��Ԃ��B
        return l_tradedProduct;
    }

    /**
     * ����.MRF�R�[�h�ɑΉ�����g���ݓ������I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * �P�j�@@�ȉ��̏����ŗݓ������e�[�u�����������A�ݓ�����Params���擾����B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�@@�،���ЃR�[�h = ����.�،����.getInstitutionCode()�̖߂�l AND<BR>
     * �@@�@@MRF�R�[�h = ����.MRF�R�[�h<BR>
     * <BR>
     * �@@�Ώۃ��R�[�h���Ȃ��A�܂��͑Ώۃ��R�[�h����������ꍇ��[�f�[�^�s����]��<BR>
     *       ��O���X���[����B<BR>
     *  �@@�@@�G���[�^�O�F   BUSINESS_ERROR_00193<BR>
     *  �@@�@@�X���[���ׂ���O�N���X�FWEB3BusinessLayerException<BR>
     * 
     * <BR>
     * �Q�j�@@�g���ݓ������I�u�W�F�N�g�̐���<BR>
     * <BR>
     * �@@[�g���ݓ������̃R���X�g���N�^�ɓn���p�����^]<BR>
     * �@@�@@�ݓ�����Row�F �P�j�Ŏ擾�����ݓ�����Params<BR>
     * <BR>
     * �R�j�@@�g���ݓ������I�u�W�F�N�g��Ԃ��B<BR>
     * @@param l_institution - �،����<BR>
     * @@param l_strMRFCode - MRF�R�[�h<BR>
     * @@return webbroker3.xbruito.WEB3RuitoExpansionRuitoProductImpl
     * @@roseuid 4076337B0280
     */
    public WEB3RuitoProduct getMRFProduct(
            Institution l_institution, 
            String l_strMRFCode)
    {
        String STR_METHOD_NAME =
            "getMRFProduct(Institution l_institution," + "String l_strMRFCode)";
        log.entering(STR_METHOD_NAME);
        if (l_institution == null)
        {
            log.debug(" �p�����[�^Error ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        
        try
        {    
            String l_strInstitutionCode = l_institution.getInstitutionCode();
            String l_strWhereClause = 
                "institution_code = ? and mrf_fund_code = ?";
            //DataQueryException,DataNetworkException
            List l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    RuitoProductRow.TYPE,
                    l_strWhereClause,
                    new Object[] { l_strInstitutionCode, l_strMRFCode });

            if (l_lisRows == null || l_lisRows.size() > 1)
            {
                log.debug("�g���ݓ��������Ȃ��A�܂��͊g���ݓ��������f�[�^�s�����ł�");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00193,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�g���ݓ��������Ȃ��A�܂��͊g���ݓ��������f�[�^�s�����ł�");               
            }
            RuitoProductRow l_ruitoProductRow = null;
                        
            l_ruitoProductRow = (RuitoProductRow) l_lisRows.get(0);
            
            WEB3RuitoProduct l_ruitoProduct = 
                new WEB3RuitoProduct(l_ruitoProductRow);
                
            log.exiting(STR_METHOD_NAME);
            return l_ruitoProduct;
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

    /**
     * �igetRuitoProduct�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �g���ݓ��������擾����B<BR>
     * <BR>
     * �P�j�@@this.get�g���ݓ�����()���R�[�����A���̖߂�l��Ԃ��B<BR>
     * �@@�mget�g���ݓ������ɓn���p�����^�n<BR>
     * �@@�@@�،���ЁF ����.�،����<BR>
     * �@@�@@�����R�[�h�F ���������R�[�h<BR>
     * �@@�@@�񍆃R�[�h�F �h0�FDEFAULT�h<BR>
     * @@param l_institution - �،����<BR>
     * @@param l_strProductCode - �����R�[�h<BR>
     * @@return RuitoProduct
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 407B719F0095
     */
    public RuitoProduct getRuitoProduct(
            Institution l_institution, String l_strProductCode)
        throws NotFoundException
    {       
        return this.getRuitoProduct(l_institution, l_strProductCode, "0");
    }

    /**
     * �igetRuitoTradedProduct�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �g���ݓ�����������擾����B<BR>
     * <BR>
     * �P�j�@@this.get�g���ݓ��������()���R�[�����A���̖߂�l��Ԃ��B<BR>
     * �@@�mget�g���ݓ���������ɓn���p�����^�n<BR>
     * �@@�@@�،���ЁF ����.�،����<BR>
     * �@@�@@�����R�[�h�F ���������R�[�h<BR>
     * �@@�@@�񍆃R�[�h�F �h0�FDEFAULT�h<BR>
     * �@@�@@�s��R�[�h�F �h0�FDEFAULT�h<BR>
     * @@param l_institution - �،����<BR>
     * @@param l_strProductCode - �����R�[�h<BR>
     * @@return RuitoTradedProduct
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 407B75B202A8
     */
    public RuitoTradedProduct getRuitoTradedProduct(
        Institution l_institution,
        String l_strProductCode)
        throws NotFoundException
    {
        return this.getRuitoTradedProduct(
            l_institution,
            l_strProductCode,
            "0",
            WEB3MarketCodeDef.DEFAULT);
    }

    /**
     * get��n��<BR>
     * <BR>
     * ��n����Ԃ��B<BR>
     * <BR>
     * �P�j�@@�g���ݓ���������I�u�W�F�N�g���擾����B<BR>
     * �@@this.get�ݓ��������()���R�[�����āA<BR>
     *       �g���ݓ���������I�u�W�F�N�g���擾����B<BR>
     * �@@�mget�ݓ���������ɓn���p�����^�n<BR>
     * �@@�@@�،���ЁF ����.�،����<BR>
     * �@@�@@�����R�[�h�F ����.�����R�[�h<BR>
     * <BR>
     * �Q�j�@@��n�����擾����B<BR>
     * �@@�g���ݓ��������.get��n��()���R�[�����āA��n�����擾����B<BR>
     * �@@�mget��n���ɓn���p�����^�n<BR>
     * �@@�@@is���t�F ����.is���t<BR>
     * <BR>
     * �R�j�@@�擾������n����Ԃ��B<BR>
     * @@param l_institution - �،����<BR>
     * @@param l_strProductCode - �����R�[�h<BR>
     * @@param isBuy - ���t�̏ꍇ��true���A���̏ꍇ��false���w�肷��<BR>
     * @@return Date
     * @@roseuid 407BCD620392
     */
    public Date getDeliveryDate(
            Institution l_institution, String l_strProductCode, boolean isBuy)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getDeliveryDate(Institution l_institution, "
                + "String l_strProductCode, boolean isBuy) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null)
        {
            log.debug(" �p�����[�^Error ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }

        //�g���ݓ���������I�u�W�F�N�g���擾����B
        RuitoTradedProduct l_ruitoTradedProduct = null;
        RuitoTradedProductRow l_ruitoTradedProductRow = null;
        Date l_datDeliveryDate = null;
        try
        {
            l_ruitoTradedProduct = this.getRuitoTradedProduct(
                l_institution, l_strProductCode);
            l_ruitoTradedProductRow = (RuitoTradedProductRow) 
                l_ruitoTradedProduct.getDataSourceObject();
            
            WEB3RuitoTradedProduct l_web3ruitoTradedProduct =
                new WEB3RuitoTradedProduct(l_ruitoTradedProductRow);
            //��n�����擾����B
            //NotFoundException,DataQueryException,DataNetworkException
            l_datDeliveryDate = 
                l_web3ruitoTradedProduct.getDailyDeliveryDate(isBuy);

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
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        //�擾������n����Ԃ��B
        return l_datDeliveryDate;

    }

    /**
     * (�g���ݓ����� get�ݓ������iwith�R�[�Xand�v�����j)
     * 
     * �w�肳�ꂽ�R�[�X�A�v�����ɑΉ�����g���ݓ������I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * �P�j�@@�ȉ��̏����ŗݓ������e�[�u�����������A�ݓ�����Params���擾����B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�@@�،���ЃR�[�h = ����.�،����.getInstitutionCode()�̖߂�l AND<BR>
     * �@@�@@�R�[�X = ����.�R�[�X AND<BR>
     * �@@�@@�v���� = ����.�v����<BR>
     * <BR>
     * �@@�Ώۃ��R�[�h���Ȃ��A�܂��͑Ώۃ��R�[�h����������ꍇ��[�f�[�^�s����]��<BR>
     *       ��O���X���[����B<BR>
     *     �@@�G���[�^�O�F   BUSINESS_ERROR_00193<BR>
     *   �@@�@@�X���[���ׂ���O�N���X�FWEB3BusinessLayerException<BR>
     * <BR>
     * �Q�j�@@�g���ݓ������I�u�W�F�N�g�̐���<BR>
     * <BR>
     * �@@[�g���ݓ������̃R���X�g���N�^�ɓn���p�����^]<BR>
     * �@@�@@�ݓ�����Row�F �P�j�Ŏ擾�����ݓ�����Params<BR>
     * <BR>
     * �R�j�@@�g���ݓ������I�u�W�F�N�g��Ԃ��B<BR>
     * @@param l_institution - �،����<BR>
     * @@param l_strCourse - �R�[�X<BR>
     * @@param l_strPlan - �v����<BR>
     * @@return webbroker3.xbruito.WEB3RuitoExpansionRuitoProductImpl
     * @@roseuid 4086524D00AB
     */
    public WEB3RuitoProduct getRuitoProductWithCoursePlan(
        Institution l_institution,
        String l_strCourse,
        String l_strPlan)
    {
        String STR_METHOD_NAME =
            "getRuitoProductWithCoursePlan "
                + "(Institution l_institution, "
                + "String l_strCourse, String l_strPlan)";
        log.entering(STR_METHOD_NAME);
        if (l_institution == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        String l_institutionCode;
        l_institutionCode = l_institution.getInstitutionCode();
        List l_lisRows = null;
        WEB3RuitoProduct l_ruitoProduct = null;

        try
        {
            String l_strWhereClause = "institution_code = ? " + 
                                      "and course = ? " + "and plan = ?";
            log.debug("l_strWhereClause = " + l_strWhereClause);
            //DataQueryException,DataNetworkException
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    RuitoProductRow.TYPE,
                    l_strWhereClause,
                    new Object[] { l_institutionCode, l_strCourse, l_strPlan });

            if (l_lisRows.isEmpty() || l_lisRows.size() > 1)
            {
                log.debug("�g���ݓ��������Ȃ��A�܂��͊g���ݓ��������f�[�^�s�����ł�");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);               
            }            
            RuitoProductRow l_ruitoProductRow = null;
            l_ruitoProductRow = (RuitoProductRow) l_lisRows.get(0);
            
            l_ruitoProduct = new WEB3RuitoProduct(l_ruitoProductRow);

        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
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
        return l_ruitoProduct;
    }

    /**
     * �w�肳�ꂽ����ID�ɑΉ�����g���ݓ������I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * �P�j�@@this.getProduct()���R�[����Product�I�u�W�F�N�g���擾����B<BR>                                                                                                                      
     *�@@�@@�mgetProduct�ɓn���p�����^�n<BR>                                                                                                                     
     *�@@�@@����ID�F ����.����ID <BR>                                                                                                                     
     *                                                                                                                   
     *�Q�j�@@this.to����()���R�[�����A�g���ݓ������I�u�W�F�N�g���擾����B<BR>                                                                                                                       
     *�@@�@@�mto�����ɓn���p�����^�n<BR>                                                                                                                       
     *�@@�@@�@@����Row�F �擾����Product�I�u�W�F�N�g.getDataSourceObject()<BR>
     *      �̖߂�l <BR>                                                                                                                    
     * <BR>                                                                                                                  
     *�R�j�@@�擾�����g���ݓ������I�u�W�F�N�g��Ԃ��B<BR>                                                                                                                      

     * @@param l_lngProductID - ����ID<BR>
     * @@return webbroker3.xbruito.WEB3RuitoExpansionRuitoProductImpl
     * @@roseuid 40886EDD01E3
     */
    public WEB3RuitoProduct getRuitoProduct(long l_lngProductID)
        throws NotFoundException
    {
        String STR_METHOD_NAME = "getRuitoProduct(long l_lngProductID)";
        log.entering(STR_METHOD_NAME);

        WEB3RuitoProduct l_ruitoProduct = null;

        //�P�jthis.getProduct()���R�[����Product�I�u�W�F�N�g���擾����B
        l_ruitoProduct = (WEB3RuitoProduct)this.getProduct(l_lngProductID);
        
        log.debug("l_product.getProductId = " + l_ruitoProduct.getProductId());
        log.debug("l_ruitoProduct.getProductType() = " + l_ruitoProduct.getProductType());
        log.debug("l_ruitoProduct.getProductId() = " + l_ruitoProduct.getProductId());

        log.exiting(STR_METHOD_NAME);
        //�R�j�@@�擾�����g���ݓ������I�u�W�F�N�g��Ԃ��B
        return l_ruitoProduct;
    }
    
    /**
     * (get�ݓ������ꗗ)
     * �ȉ��̏����ŗݓ������e�[�u�����������A�ݓ�����Params��List ���擾����B<BR>
     * <BR>
     * �m���������n <BR>                                                                                                                      
     *�@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h AND <BR>                                                                                                                     
     *�@@�@@(�ݓ��^�C�v�@@= RuitoTypeEnum.�������t�@@���h OR �ݓ��^�C�v = RuitoTypeEnum.MMF) <BR>                                                                                                                     
     * <BR>
     * �morderBy�n<BR>                                                                                                                       
     *�@@�@@�h�R�[�X asc, �v���� desc�h <BR>    
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * @@return webbroker3.xbruito.WEB3RuitoExpansionRuitoProductImpl
     * @@roseuid 40886EDD01E3
     */
    public List getRuitoProductList(String l_strInstitutionCode)
    {
        String STR_METHOD_NAME = "getRuitoProductList(String l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);

        List l_lisProductRows = null;
        
        try
        {
            String l_strWhereClause = 
                    "institution_code = ? and " +
                    "(ruito_type = ? or ruito_type = ?)";
            String l_strOrderBy = "course asc, plan desc";
            
            //�m���������n 
            //�،���ЃR�[�h = ����.�،���ЃR�[�h AND 
            //(�ݓ��^�C�v�@@= RuitoTypeEnum.�������t�@@���h OR �ݓ��^�C�v = RuitoTypeEnum.MMF) 
            //�morderBy�n 
            //�h�R�[�X asc, �v���� desc�h 
            l_lisProductRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    RuitoProductRow.TYPE,
                    l_strWhereClause,
                    l_strOrderBy,
                    null,
                    new Object[] { 
                        l_strInstitutionCode, 
                        RuitoTypeEnum.CHUUKOKU_FUND,
                        RuitoTypeEnum.MMF });
            
            log.debug("l_lisProductRows.size = " + l_lisProductRows.size());
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
        return l_lisProductRows;
    }
}
@
