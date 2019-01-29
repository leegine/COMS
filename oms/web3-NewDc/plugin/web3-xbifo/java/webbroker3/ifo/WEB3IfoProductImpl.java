head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoProductImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�����N���X(WEB3IfoProductImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ���� �V�K�쐬
              001: 2004/07/22 ���Ō� (���u) WEB3CommisionProductCodeDef��WEB3IfoCommissionProductCodeDef�������ւ���
*/
package webbroker3.ifo;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoProductImpl;

//import webbroker3.ifo.define.WEB3IfoCommissionProductCodeDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
/**
 * �敨OP�����I�u�W�F�N�g<BR>
 * �敨OP�����I�u�W�F�N�g�N���X<BR>
 *�iDB���C�A�E�g �u�敨OP�����e�[�u��.xls�v�Q�Ɓj<BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3IfoProductImpl extends IfoProductImpl
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */         
    private static WEB3LogUtility log =         
        WEB3LogUtility.getInstance(
            WEB3IfoProductImpl.class);
          
    /**
     * �敨OP����Row�s�I�u�W�F�N�g<BR>
     * �i��������DAO�N���X�j<BR>
     */
    private IfoProductRow futuresOptionProductRow; 
 
    
    /**
     * �R���X�g���N�^�B<BR>
     * �����̖����h�c�Ɉ�v����敨OP�����I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�����̒l�ɂĐ敨OP�����e�[�u������������B<BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Z�b�g<BR>
     * �@@�������ʂ̍s�I�u�W�F�N�g�i�敨OP����Row�j��<BR>
     * this.�敨OP����Row�ɃZ�b�g����B<BR>
     * @@param l_lngProductID - �����h�c
     * @@return webbroker3.ifo.WEB3IfoProductImpl
     * @@roseuid 405E7AA603D2
     */
    public WEB3IfoProductImpl(long l_lngProductID) 
        throws DataFindException, DataQueryException, DataNetworkException 
    {   
        super(IfoProductDao.findRowByPk(l_lngProductID)); 
        this.futuresOptionProductRow = 
            IfoProductDao.findRowByPk(l_lngProductID);            
    }
    
    public WEB3IfoProductImpl(ProductRow l_row) 
        throws DataFindException, DataQueryException, DataNetworkException 
    {   
        super(l_row); 
        this.futuresOptionProductRow =  
            IfoProductDao.findRowByPk(l_row.getProductId());   
    }

    public WEB3IfoProductImpl(IfoProductRow l_row) 
        throws DataFindException, DataQueryException, DataNetworkException 
    {   
        super(l_row); 
        this.futuresOptionProductRow = l_row;   
    }
            
    /**
     * (get�萔�����i�R�[�h)<BR>
     * <BR>
     * �����ɊY������萔�����i�R�[�h���擾����B<BR>
     * <BR>
     * �{�I�u�W�F�N�g.�敨�I�v�V�������i.���h�敨�h�̏ꍇ<BR>
     * �@@�|�萔�����i�R�[�h.�h50�F�敨�h��ԋp����B<BR>
     * �{�I�u�W�F�N�g.�敨�I�v�V�������i���h�敨�h�łȂ��ꍇ<BR>
     * �@@�|�萔�����i�R�[�h.�h51�F�����w���n�o�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 40518FE400D6
     */
    public String getCommissionProductCode()     
    {   
        final String METHOD_NAME = "getCommissionProductCode()";                                                                            
        log.entering(METHOD_NAME);  
        
        String l_strFutureOptionDiv =    
            this.futuresOptionProductRow.getFutureOptionDiv();      
        
        if (WEB3FuturesOptionDivDef.FUTURES.equals(l_strFutureOptionDiv))
        {
            log.exiting(METHOD_NAME);
            return  WEB3CommisionProductCodeDef.INDEX_FUTURES;
        }   
        else if (WEB3FuturesOptionDivDef.OPTION.equals(l_strFutureOptionDiv))
        {
            log.exiting(METHOD_NAME);
            return WEB3CommisionProductCodeDef.INDEX_OP;
        }
        
        log.exiting(METHOD_NAME);
        return null;       
    }
    
    /**
     * (get�����R�[�h)<BR>
     * @@return String
     * @@roseuid 4051977101D0
     */
    public String getProductCode()
    {
        return this.futuresOptionProductRow.getProductCode();
    }
    
    /**
     * (get�w���}�X�^)<BR>
     * <BR>
     * �����ɊY������w���}�X�^���擾����B<BR>
     * <BR>
     * �敨OP�w���}�X�^�N���X�̃R���X�g���N�^�ɁA���I�u�W�F�N�g���ێ�����w��ID��������<BR>�w�肵�Đ�������B<BR>
     * @@return webbroker3.ifo.WEB3IfoIndexMaster
     * @@roseuid 405E7A050389
     */
    public WEB3IfoIndexMaster getIndexMaster() 
        throws WEB3BaseException
    {
        final String METHOD_NAME = "getIndexMaster()";
        log.entering(METHOD_NAME);  
        
        try
        {
            long l_lngIndexId = 
                this.futuresOptionProductRow.getIndexId();                  
           
            log.exiting(METHOD_NAME);
            return new WEB3IfoIndexMaster(l_lngIndexId);   
        }
        catch (DataException l_de)
        {                            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + METHOD_NAME,
                l_de.toString(),
                l_de);
        }
    }
    
    /**
     * this.�敨OP�w������Row��ԋp����B
     * @@return Object
     * @@roseuid 405E7AA603D1
     */
    public Object getDataSourceObject() 
    {
        return this.futuresOptionProductRow;
    }
        
    /**
     * (get�D��s��)<BR>
     * �igetPrimaryMarket�̎����j<BR>
     * <BR>
     * this.�敨OP����Row.�Ώێs��h�c�ɂĎs��I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.Market
     * @@roseuid 406916BA031C
     */
    public Market getPrimaryMarket()           
    {  
        long l_lngMarketId = 
            this.futuresOptionProductRow.getTargetMarketId();
        try
        {
            return new WEB3GentradeMarket(l_lngMarketId);       
        }
        catch(DataException l_ex)
        {           
            return null; 
        }
      
    }
    
    /**
     * (get�����Y�����R�[�h )<BR>
     * <BR>
     * this.�敨OP����Row.�����Y�����R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 407F997A0389
     */
    public String getUnderlyingProductCode() 
    {
        return this.futuresOptionProductRow.getUnderlyingProductCode();
    }
    
    /**
     * (is�I�v�V��������)<BR>
     * �I�v�V�����������𔻒肷��B<BR>
     * <BR>
     * this.�敨OP����Row.�敨�^�I�v�V�����敪 == �h�I�v�V�����h�̏ꍇ��true��ԋp����B<BR>
     * �ȊO��false��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 409996320388
     */
    public boolean isOptionProduct() 
    {        
        String l_strFutureOptionDiv =    
               this.futuresOptionProductRow.getFutureOptionDiv();               
       
        if (WEB3FuturesOptionDivDef.OPTION.equals(l_strFutureOptionDiv))
        {
            return true;
        }
      
        return false;
       
       
    }
    
    /**
     * (is�敨����)<BR>
     * �敨�������𔻒肷��B<BR>
     * <BR>
     * this.�敨OP����Row.�敨�^�I�v�V�����敪 == �h�敨�h�̏ꍇ��true��ԋp����B<BR>
     * �ȊO��false��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 409996C8006B
     */
    public boolean isFuturesProduct() 
    {
        String l_strFutureOptionDiv =    
                  this.futuresOptionProductRow.getFutureOptionDiv();
                              
        if (WEB3FuturesOptionDivDef.FUTURES.equals(l_strFutureOptionDiv))
        {
            return true;
        }
        
        return false;
    }
}
@
