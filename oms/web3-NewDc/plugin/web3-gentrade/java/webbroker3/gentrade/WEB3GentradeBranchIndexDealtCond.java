head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeBranchIndexDealtCond.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i���X�w���ʁj�戵����(WEB3GentradeBranchIndexDealtCond.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/20 ����� (���u) �V�K�쐬
Revesion History : 2006/05/11 ������ (���u)�y���ʁz�d�l�ύX�E���f��No.184
Revesion History : 2006/05/17 ������ (���u)�y���ʁz�d�l�ύX�E���f��No.190
Revesion History : 2006/05/17 ������ (���u)�y���ʁz�����̖��E���f��No.012
Revesion History : 2006/08/09  �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.203
*/

package webbroker3.gentrade;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoTradedProductImpl;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.gentrade.data.BranchIndexDealtCondDao;
import webbroker3.gentrade.data.BranchIndexDealtCondPK;
import webbroker3.gentrade.data.BranchIndexDealtCondRow;


/**
 * (�i���X�w���ʁj�戵����)<BR>
 * �،���ЁA���X�A�����Y�����i�w����ʁj���̎戵�\����������\������B<BR>
 * �敨�I�v�V�����Ŏg�p�B<BR>
 * <BR>
 * �iDB���C�A�E�g �u�i���X�w���ʁj�戵�����e�[�u��.xls�v�Q�Ɓj<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3GentradeBranchIndexDealtCond implements BusinessObject
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3GentradeBranchIndexDealtCond.class);

    /**
     * (�i���X�w���ʁj�戵����Row)<BR>
     * �i���X�w���ʁj�戵�����s�I�u�W�F�N�g<BR>
     * �i��������DAO�N���X�j<BR>
     */
    private BranchIndexDealtCondRow branchIndexDealtCondRow;

    /**
     * (�i���X�w���ʁj�戵����)<BR>
     * <BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �{�I�u�W�F�N�g���C���X�^���X�����A<BR>
     * �����̍s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_branchIndexDealtCondRow - �i���X�w���ʁj�戵�����s�I�u�W�F�N�g
     * @@roseuid 40640EA5002E
     */
    public WEB3GentradeBranchIndexDealtCond(BranchIndexDealtCondRow l_branchIndexDealtCondRow)
    {
        final String STR_METHOD_NAME = "WEB3GentradeBranchIndexDealtCond(BranchIndexDealtCondRow)";
        if(l_branchIndexDealtCondRow == null)
        {
            log.error("�i���X�w���ʁj�戵�����s�I�u�W�F�N�g = null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�i���X�w���ʁj�戵�����s�I�u�W�F�N�g = null");
        }
        
        this.branchIndexDealtCondRow = l_branchIndexDealtCondRow;
    }

    /**
     * (�i���X�w���ʁj�戵����)<BR>
     * <BR>
     * �R���X�g���N�^�B<BR>
     * �����̏����Ɉ�v����i���X�w���ʁj�戵�����I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�ȉ��̏����ɂāi���X�w���ʁj�戵�����e�[�u������������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = �،���ЃR�[�h<BR>
     * �@@���X�R�[�h = ���X�R�[�h<BR>
     * �@@�s��R�[�h = �s��R�[�h<BR>
     * �@@�����Y�����R�[�h = �����Y�����R�[�h<BR>
     * �@@�敨�^�I�v�V�����敪 = �敨�^�I�v�V�����敪<BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Z�b�g<BR>
     * �@@�������ʂ̍s�I�u�W�F�N�g�i�i���X�w���ʁj�戵����Row�j��<BR>
     * this.�i���X�w���ʁj�戵����Row�ɃZ�b�g����B<BR>
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strMarketCode - �s��R�[�h
     * @@param l_strFuturesOptionDivision - �敨�^�I�v�V�����敪<BR>
     * �@@ 1�F�敨 2�F�I�v�V����<BR>
     * @@param l_strUnderlyingProductCode - �����Y�����R�[�h<BR>
     *  �@@0005�FTOPIX�@@0018�F���o225�@@0016�F���o300�@@0019�F�~�j���o225<BR>
     * @@return WEB3GentradeBranchIndexDealtCond <BR>
     * @@throws WEB3SystemLayerException <BR>
     * @@roseuid 405FC33C034B
     */
    public WEB3GentradeBranchIndexDealtCond(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMarketCode,
        String l_strFuturesOptionDivision,
        String l_strUnderlyingProductCode) throws WEB3SystemLayerException
    {   
        final String STR_METHOD_NAME =
            "WEB3GentradeBranchIndexDealtCond(String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            BranchIndexDealtCondPK l_condPK = new BranchIndexDealtCondPK();
            
            l_condPK.institution_code    = l_strInstitutionCode;        //�،���ЃR�[�h
            l_condPK.branch_code         = l_strBranchCode;             //���X�R�[�h
            l_condPK.market_code         = l_strMarketCode;             //�s��R�[�h
            l_condPK.future_option_div   = l_strFuturesOptionDivision;  //�敨�^�I�v�V�����敪
            l_condPK.target_product_code = l_strUnderlyingProductCode;  //�����Y�����R�[�h
            
            this.branchIndexDealtCondRow = BranchIndexDealtCondDao.findRowByPk(l_condPK); 
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (�i���X�w���ʁj�戵����)<BR>
     * <BR>
     * �R���X�g���N�^�B<BR>
     * �����̏،���ЁA���X�A�敨OP��������Ɉ�v����<BR>
     * �i���X�w���ʁj�戵�����I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * �P�j�@@�����A�s��擾<BR>
     * �@@�|�敨OP�������.getProduct()�ɂāA<BR>
     * �敨OP��������Ɋ֘A����敨OP�����I�u�W�F�N�g���擾����B<BR>
     * �@@�|�敨OP�������.getMarket()�ɂāA<BR>
     * �敨OP��������Ɋ֘A����s��I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�R���X�g���N�^�R�[��<BR>
     * �@@�I�[�o�[���[�h�̃R���X�g���N�^���R�[������B<BR>
     * <BR>
     * �@@[�w�肷��p�����[�^]<BR>
     * �@@�،���ЃR�[�h = �敨OP����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h = ���X�R�[�h<BR>
     * �@@�s��R�[�h = �s��.�s��R�[�h<BR>
     * �@@�����Y�����R�[�h = �敨OP����.�����Y�����R�[�h<BR>
     * �@@�敨�I�v�V�����敪 = �敨OP����.�敨�^�I�v�V�����敪<BR>
     * 
     * @@param l_strBranchCode - ���X�R�[�h <BR>
     * @@param l_ifoTradedProductImpl - �敨OP��������I�u�W�F�N�g <BR>
     * @@return WEB3GentradeBranchIndexDealtCond <BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 405E77B003E5
     */
    public WEB3GentradeBranchIndexDealtCond(String l_strBranchCode, IfoTradedProductImpl l_tradedProductImpl) 
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "WEB3GentradeBranchIndexDealtCond(String, IfoTradedProductImpl)";
        log.entering(STR_METHOD_NAME);
        
        //�敨OP��������Ɋ֘A����敨OP�����I�u�W�F�N�g���擾����B
        Product l_product = l_tradedProductImpl.getProduct();
        IfoProductRow l_productRow = (IfoProductRow)l_product.getDataSourceObject();
        
        //�敨OP��������Ɋ֘A����s��I�u�W�F�N�g���擾����B
        Market l_market = l_tradedProductImpl.getMarket();

        try
        {
            BranchIndexDealtCondPK l_condPK = new BranchIndexDealtCondPK();
            
            l_condPK.institution_code    = l_productRow.getInstitutionCode();        //�،���ЃR�[�h
            l_condPK.branch_code         = l_strBranchCode;                          //���X�R�[�h
            l_condPK.market_code         = l_market.getMarketCode();                 //�s��R�[�h
            l_condPK.future_option_div   = l_productRow.getFutureOptionDiv();        //�敨�^�I�v�V�����敪
            l_condPK.target_product_code = l_productRow.getUnderlyingProductCode();  //�����Y�����R�[�h
            
            this.branchIndexDealtCondRow = BranchIndexDealtCondDao.findRowByPk(l_condPK); 

        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * this.�敨OP�w������Row��ԋp����B
     * @@return Object
     * @@roseuid 405E77B003E4
     */
    public java.lang.Object getDataSourceObject()
    {
        return this.branchIndexDealtCondRow;
    }

    /**
     * (get�������)<BR>
     * <BR>
     * �@@�{�I�u�W�F�N�g���ێ�����s�I�u�W�F�N�g�̍��ڒl���<BR>
     * ������ʂ��擾����B<BR>
     * <BR>
     * �P�j �e����̋K������<BR>
     * ���@@�V�K�����iis����==true && is�V�K��==true�j�̏ꍇ<BR>
     * �@@�i���X�w���ʁj�戵����Row.����������z��ԋp����B<BR>
     * <BR>
     * ���@@�V�K�����iis����==false && is�V�K��==true�j�̏ꍇ<BR>
     * �@@�i���X�w���ʁj�戵����Row.����������z��ԋp����B<BR>
     * <BR>
     * ���@@�������ԍρiis����==false && is�V�K��==false�j�̏ꍇ<BR>
     * �@@�i���X�w���ʁj�戵����Row.���ԍϏ�����z��ԋp����B<BR>
     * <BR>
     * ���@@�������ԍρiis����==true && is�V�K��==false�j�̏ꍇ<BR>
     * �@@�i���X�w���ʁj�戵����Row.���ԍϏ�����z��ԋp����B<BR>
     * @@param l_blnIsBuyToOpenOrder - is�����iisBuyToOpenOrder�j
     * �������ǂ����̔���B
     * �����̏ꍇtrue�A�����̏ꍇfalse�B
     * @@param l_blnIsOpenContract - is�V�K���iisOpenContract�j<BR>
     * �V�K��������ǂ����̔���B<BR>
     * �V�K���̏ꍇtrue�A�ԍς̏ꍇfalse�B<BR>
     * 
     * @@return double
     * @@roseuid 40611EEC03A6
     */
    public double getMaxQuantity(boolean l_blnIsBuyToOpenOrder, boolean l_blnIsOpenContract)
    {
        final String STR_METHOD_NAME = 
            "getMaxQuantity(boolean l_blnIsBuyToOpenOrder, boolean l_blnIsOpenContract)";
        log.entering(STR_METHOD_NAME);
        
        if (l_blnIsBuyToOpenOrder && l_blnIsOpenContract) //�V�K����        
        {
            return this.branchIndexDealtCondRow.getOpenContLongOrderLimit(); //�����������
        }
        else if (!l_blnIsBuyToOpenOrder && l_blnIsOpenContract) //�V�K����
        {
            return this.branchIndexDealtCondRow.getOpenContShortOrderLimit(); //�����������
        }
        else if (!l_blnIsBuyToOpenOrder && !l_blnIsOpenContract) //�������ԍ�
        {
            return this.branchIndexDealtCondRow.getSettleContLongOrderLimit(); //���ԍϏ������
        }
        else if (l_blnIsBuyToOpenOrder && !l_blnIsOpenContract) //�������ԍ�
        {
            return this.branchIndexDealtCondRow.getSettleContShortOrderLimit(); //���ԍϏ������
        }

        log.exiting(STR_METHOD_NAME);        
        return 0;
    }

    /**
     * (is�戵�\)<BR>
     * �Y���w�����戵�\�ł��邩��ԋp����B<BR>
     * <BR>
     * �ithis.�敨OP�w������Row.�戵�\ == �h�戵�\�h�j�̏ꍇtrue�A<BR>
     * �ȊO��false��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 406400BE0212
     */
    public boolean isHandlingPossible()
    {
        return WEB3DealtDef.CAN_DEALT.equals(this.branchIndexDealtCondRow.getEnableOrder());
    }

    /**
     * (get�i���X�w���ʁj�戵����)<BR>
     * <BR>
     * �istatic���\�b�h�j<BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�ɊY������戵�\��<BR>
     * �i���X�w���ʁj�戵�����I�u�W�F�N�g��S�Ď擾����B<BR>
     * <BR>
     * �P�jDB����<BR>
     * �@@�i���X�w���ʁj�戵�����e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h = �p�����[�^.���X�R�[�h<BR>
     *   �敨�^�I�v�V�����敪 = �p�����[�^.�敨�^�I�v�V�����敪<BR> 
     * �@@�戵�\ = 1(:�戵�\)<BR>
     * <BR>
     * �Q�j�I�u�W�F�N�g����<BR>
     * �@@�������ʂ́i���X�w���ʁj�戵�����s�I�u�W�F�N�g���w�肵�A<BR>
     * �@@�i���X�w���ʁj�戵�����I�u�W�F�N�g�𐶐�����B<BR>
     * �@@���������I�u�W�F�N�g��z��ŕԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h <BR>
     * @@param l_strBranchCode - ���X�R�[�h <BR>
     * @@param l_strFutureOptionDiv - �敨�^�I�v�V�����敪 <BR>
     * @@return WEB3GentradeBranchIndexDealtCond[] <BR>
     * @@throws WEB3SystemLayerException <BR>
     * 
     * @@roseuid 406410E10251
     */
    public static WEB3GentradeBranchIndexDealtCond[] getHandlingCondBranchIndex(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strFutureOptionDiv) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "getHandlingCondBranchIndex(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstRecords = null;
        
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();  //throw DataFindException, DataNetworkException
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");     //�،���ЃR�[�h
            l_sbWhere.append(" and branch_code = ? ");      //���X�R�[�h
            l_sbWhere.append(" and future_option_div = ? ");//�敨�^�I�v�V�����敪
            l_sbWhere.append(" and enable_order = ? ");     //�戵�\
            
            Object[] l_objWhere = { 
                l_strInstitutionCode,   //�،���ЃR�[�h
                l_strBranchCode,        //���X�R�[�h
                l_strFutureOptionDiv,   //�敨�^�I�v�V�����敪
                WEB3DealtDef.CAN_DEALT  //1(:�戵�\)
                };
            
            //���s��R�[�h�����Ń\�[�g���Ď擾����B            
            l_lstRecords = l_processor.doFindAllQuery(
                BranchIndexDealtCondRow.TYPE,
                l_sbWhere.toString(),
                "to_number(market_code)",
                null,
                l_objWhere
                );
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchIndexDealtCond.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchIndexDealtCond.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchIndexDealtCond.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }  
        
            
        WEB3GentradeBranchIndexDealtCond[] l_branchIndexes = 
            new WEB3GentradeBranchIndexDealtCond[l_lstRecords.size()];
        
        for (int i = 0; i < l_lstRecords.size(); i++) 
        {
            l_branchIndexes[i] =
                new WEB3GentradeBranchIndexDealtCond((BranchIndexDealtCondRow)l_lstRecords.get(i));
        }
            
        log.exiting(STR_METHOD_NAME);
            
        return l_branchIndexes;      
    }
    
    /**
     * (get�i���X�w���ʁj�戵����)<BR>
     * �istatic���\�b�h�j<BR>
     * �����̏،���ЃR�[�h�ɊY������戵�\�ȁi���X�w���ʁj�戵����<BR>
     * �I�u�W�F�N�g��S�Ď擾����B<BR>
     *  <BR>
     * �P�jDB����<BR>
     * �i���X�w���ʁj�戵�����e�[�u�����ȉ��̏����Ō�������B<BR>
     * [����]<BR>
     * �،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
     * �戵�\ = 1(:�戵�\)<BR>
     *  <BR>
     * �Q�j�I�u�W�F�N�g����<BR>
     * �������ʂ́i���X�w���ʁj�戵�����s�I�u�W�F�N�g���w�肵�A<BR>
     * �i���X�w���ʁj�戵�����I�u�W�F�N�g�𐶐�����B��������<BR>
     * �I�u�W�F�N�g��z��ŕԋp����B<BR>
     *  <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h <BR>
     * @@return WEB3GentradeBranchIndexDealtCond[] <BR>
     * @@throws WEB3SystemLayerException <BR>
     */
    public static WEB3GentradeBranchIndexDealtCond[] getHandlingCondBranchIndex(String l_strInstitutionCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =  "getHandlingCondBranchIndex(String)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstRecords;
        try
        {
            QueryProcessor L_queryProcessor = Processors.getDefaultProcessor();  
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");     //�،���ЃR�[�h
            l_sbWhere.append(" and enable_order = ? ");     //�戵�\
            
            Object[] l_objWhere = { 
                l_strInstitutionCode,   //�،���ЃR�[�h
                WEB3DealtDef.CAN_DEALT  //1(:�戵�\)
                };
            
            //���s��R�[�h�����Ń\�[�g���Ď擾����B            
            l_lstRecords = L_queryProcessor.doFindAllQuery(
                BranchIndexDealtCondRow.TYPE,
                l_sbWhere.toString(),
                "to_number(market_code)",
                null,
                l_objWhere
                );
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchIndexDealtCond.class.getName() + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        int l_intSize = l_lstRecords.size();
        WEB3GentradeBranchIndexDealtCond[] l_branchIndexes = 
            new WEB3GentradeBranchIndexDealtCond[l_intSize];
        for (int i = 0; i < l_intSize; i++) 
        {
            l_branchIndexes[i] =
                new WEB3GentradeBranchIndexDealtCond((BranchIndexDealtCondRow)l_lstRecords.get(i));
        }
            
        log.exiting(STR_METHOD_NAME);
        return l_branchIndexes;  
        
    }
    
    /**
     * (get�i���X�w���ʁj�戵�����ꗗ)<BR>
     * �istatic���\�b�h�j <BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�ɊY������i���X�w���ʁj�戵�����I�u�W�F�N�g���A<BR>
     * �戵���̉^�s�Ɋւ�炸�S�Ď擾����B <BR>
     *  <BR>
     * �P�jDB���� <BR>
     * �@@�i���X�w���ʁj�戵�����e�[�u�����ȉ��̏����Ō�������B <BR>
     * �@@[����] <BR>
     * �@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h <BR>
     * �@@���X�R�[�h = �p�����[�^.���X�R�[�h <BR>
     * <BR>
     * �Q�j�I�u�W�F�N�g���� <BR>
     * �@@�������ʂ́i���X�w���ʁj�戵�����s�I�u�W�F�N�g���w�肵�A <BR>
     * �@@�i���X�w���ʁj�戵�����I�u�W�F�N�g�𐶐�����B <BR>
     * �@@���������I�u�W�F�N�g��z��ŕԋp����B<BR>
     *  <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h <BR>
     * @@param l_strBranchCode - ���X�R�[�h <BR>
     * @@return WEB3GentradeBranchIndexDealtCond[] <BR>
     * @@throws WEB3SystemLayerException
     */
    public static WEB3GentradeBranchIndexDealtCond[] getHandlingCondBranchIndexList(
        String l_strInstitutionCode, String l_strBranchCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =  "getHandlingCondBranchIndexList(String, String)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstRecords = null;
        try
        {
            //�P�jDB���� 
            //�i���X�w���ʁj�戵�����e�[�u�����ȉ��̏����Ō�������B 
            //[����] 
            //�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h 
            //���X�R�[�h = �p�����[�^.���X�R�[�h 
            QueryProcessor L_queryProcessor = Processors.getDefaultProcessor();  
            StringBuffer l_strQueryString = new StringBuffer();
            l_strQueryString.append(" institution_code = ? ");     //�،���ЃR�[�h
            l_strQueryString.append(" and branch_code = ? ");     //���X�R�[�h
            
            Object[] l_objQueryValue = { 
                l_strInstitutionCode,   //�،���ЃR�[�h
                l_strBranchCode  //���X�R�[�h
                };
            
            l_lstRecords = L_queryProcessor.doFindAllQuery(
                BranchIndexDealtCondRow.TYPE,
                l_strQueryString.toString(),
                l_objQueryValue
                );
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchIndexDealtCond.class.getName() + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //�Q�j�I�u�W�F�N�g���� 
        //�������ʂ́i���X�w���ʁj�戵�����s�I�u�W�F�N�g���w�肵�A 
        //�i���X�w���ʁj�戵�����I�u�W�F�N�g�𐶐�����B 
        WEB3GentradeBranchIndexDealtCond[] l_branchIndexDealtConds = 
            new WEB3GentradeBranchIndexDealtCond[l_lstRecords.size()];
        
        for (int i = 0; i < l_lstRecords.size(); i++) 
        {
            l_branchIndexDealtConds[i] =
                new WEB3GentradeBranchIndexDealtCond(
                    (BranchIndexDealtCondRow)l_lstRecords.get(i));
        }
            
        log.exiting(STR_METHOD_NAME);
        //���������I�u�W�F�N�g��z��ŕԋp����B
        return l_branchIndexDealtConds;
    }
    
    /**
     * (get�i���X�w���ʁj�戵�����ꗗ)<BR>
     * �istatic���\�b�h�j <BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�ɊY������i���X�w���ʁj�戵�����I�u�W�F�N�g���A<BR>
     * �戵���̉^�s�Ɋւ�炸�S�Ď擾����B <BR>
     *  <BR>
     * �P�jDB���� <BR>
     * �@@�i���X�w���ʁj�戵�����e�[�u�����ȉ��̏����Ō�������B <BR>
     * �@@[����] <BR>
     * �@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h <BR>
     * �@@���X�R�[�h = �p�����[�^.���X�R�[�h <BR>
     *   �敨�^�I�v�V�����敪 = �p�����[�^.�敨�^�I�v�V�����敪<BR>
     * <BR>
     * �Q�j�I�u�W�F�N�g���� <BR>
     * �@@�������ʂ́i���X�w���ʁj�戵�����s�I�u�W�F�N�g���w�肵�A <BR>
     * �@@�i���X�w���ʁj�戵�����I�u�W�F�N�g�𐶐�����B <BR>
     * �@@���������I�u�W�F�N�g��z��ŕԋp����B<BR>
     *  <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h <BR>
     * @@param l_strBranchCode - ���X�R�[�h <BR>
     * @@param l_strFutureOptionDiv - �敨�^�I�v�V�����敪 <BR>
     * @@return WEB3GentradeBranchIndexDealtCond[] <BR>
     * @@throws WEB3SystemLayerException
     */
    public static WEB3GentradeBranchIndexDealtCond[] getHandlingCondBranchIndexList(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strFutureOptionDiv)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =  
            "getHandlingCondBranchIndexList(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstRecords = null;
        try
        {
            //�P�jDB���� 
            //�i���X�w���ʁj�戵�����e�[�u�����ȉ��̏����Ō�������B 
            //[����] 
            //�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h 
            //���X�R�[�h = �p�����[�^.���X�R�[�h 
            //�敨�^�I�v�V�����敪 = �p�����[�^.�敨�^�I�v�V�����敪
            QueryProcessor L_queryProcessor = Processors.getDefaultProcessor();  
            StringBuffer l_strQueryString = new StringBuffer();
            l_strQueryString.append(" institution_code = ? ");  //�،���ЃR�[�h
            l_strQueryString.append(" and branch_code = ? ");   //���X�R�[�h
            l_strQueryString.append(" and future_option_div = ? "); //�敨�^�I�v�V�����敪
            
            Object[] l_objQueryValue = { 
                l_strInstitutionCode,   //�،���ЃR�[�h
                l_strBranchCode,  //���X�R�[�h
                l_strFutureOptionDiv  //�敨�^�I�v�V�����敪
                };
            
            l_lstRecords = L_queryProcessor.doFindAllQuery(
                BranchIndexDealtCondRow.TYPE,
                l_strQueryString.toString(),
                l_objQueryValue
                );
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchIndexDealtCond.class.getName() + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //�Q�j�I�u�W�F�N�g���� 
        //�������ʂ́i���X�w���ʁj�戵�����s�I�u�W�F�N�g���w�肵�A 
        //�i���X�w���ʁj�戵�����I�u�W�F�N�g�𐶐�����B 
        WEB3GentradeBranchIndexDealtCond[] l_branchIndexDealtConds = 
            new WEB3GentradeBranchIndexDealtCond[l_lstRecords.size()];
        
        for (int i = 0; i < l_lstRecords.size(); i++) 
        {
            l_branchIndexDealtConds[i] =
                new WEB3GentradeBranchIndexDealtCond(
                    (BranchIndexDealtCondRow)l_lstRecords.get(i));
        }
            
        log.exiting(STR_METHOD_NAME);
        //���������I�u�W�F�N�g��z��ŕԋp����B
        return l_branchIndexDealtConds;
    }

    /**
     * (get�����Y�����R�[�h)<BR>
     * <BR>
     * �{�I�u�W�F�N�g���ێ����錴���Y�����R�[�h���擾����B<BR>
     * <BR>
     * this.�i���X�w���ʁj�戵����Row.�����Y�����R�[�h��ԋp����B<BR>
     * @@roseuid 406425180109
     */
    public String getUnderlyingProductCode()
    {
        return this.branchIndexDealtCondRow.getTargetProductCode();
    }

    /**
     * (get�������������)<BR>
     * <BR>
     * �{�I�u�W�F�N�g���ێ����鑍����������ʂ��擾����B<BR>
     * <BR>
     * �i���X�w���ʁj�戵����Row.������������ʂ�ԋp����B<BR>
     * @@return double
     * @@roseuid 40AAC33A00F2
     */
    public double getTotalOpenSellMaxQuantity()
    {
        return this.branchIndexDealtCondRow.getOpenContLimit();
    }
}
@
