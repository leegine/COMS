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
filename	WEB3GentradeOrderUnitIntroduceDiv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����P�ʏЉ�敪(WEB3GentradeOrderUnitIntroduceDiv)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/25 �Г� (���u) �V�K�쐬
Revesion History : 2006/11/21 ���{ (SRA) �d�l�ύX�i���f���j�F218
**/
package webbroker3.gentrade;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.OrderUnitIntroduceDivDao;
import webbroker3.gentrade.data.OrderUnitIntroduceDivParams;
import webbroker3.gentrade.data.OrderUnitIntroduceDivRow;
import webbroker3.util.WEB3LogUtility;

/**
 * �����P�ʏЉ�敪<BR>
 * <BR>
 */
public class WEB3GentradeOrderUnitIntroduceDiv implements BusinessObject
{

    /**
     * �����P�ʏЉ�敪Row <BR>
     */
    private OrderUnitIntroduceDivParams orderUnitIntroduceDivParams;

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeOrderUnitIntroduceDiv.class);

    /**
	 * (saveNew�����P�ʏЉ�敪)<BR>
	 * this.�����P�ʏЉ�敪�s�̓��e���f�[�^�x�[�X�ɔ��f������B(Insert)<BR>
	 * <BR>
	 * 1) this.�����P�ʏЉ�敪�s�I�u�W�F�N�g�Ɉȉ��̒l���Z�b�g����B<BR>
	 * �@@�쐬���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
	 * �@@�X�V���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
	 * <BR>
	 * 2) this.�����P�ʏЉ�敪�s�I�u�W�F�N�g�̓��e�ŁA<BR>
	 * �@@�����P�ʏЉ�敪�e�[�u�����X�V�iInsert�j����B<BR>
	 * <BR>
	 * @@roseuid 40640F0102AE
     * @@throws WEB3SystemLayerException
	 */
    public void saveNewOrderUnitIntroduceDivRow()
        throws WEB3SystemLayerException
	{
        final String STR_METHOD_NAME = 
            "saveNewOrderUnitIntroduceDivRow()";
        log.entering(STR_METHOD_NAME);
        
        Timestamp l_tsSystemTime = 
            GtlUtils.getTradingSystem().getSystemTimestamp();
        //�쐬���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
        this.orderUnitIntroduceDivParams.setCreatedTimestamp(l_tsSystemTime);
        //�X�V���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
        this.orderUnitIntroduceDivParams.setLastUpdatedTimestamp(l_tsSystemTime);

        // �����P�ʏЉ�敪�e�[�u�����X�V�iInsert�j����B
        try
        {
            QueryProcessor l_processer = Processors.getDefaultProcessor();
            l_processer.doInsertQuery(this.orderUnitIntroduceDivParams); 
        }
        catch (DataException l_de)
        {
            log.error(STR_METHOD_NAME, l_de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        log.exiting(STR_METHOD_NAME);        
	}

    /**
     * (�����P�ʏЉ�敪) <BR>
     * �R���X�g���N�^�B <BR>
     * <BR>
     * �����̏����Ɉ�v���钍���P�ʏЉ�敪�I�u�W�F�N�g��ԋp����B <BR>
     * <BR>
     * �P�j�@@DB���� <BR>
     *�@@�����̒l���L�[�Ƃ��Ē����P�ʏЉ�敪�e�[�u������������B <BR>
     * �@@[����]<BR>
     * �@@�@@�@@�@@�����P��ID = ����.�����P��ID<BR>
     * �@@���@@�����^�C�v = ����.�����^�C�v<BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Z�b�g<BR>
     * �@@�������ʂ̍s�I�u�W�F�N�g�i�����P�ʏЉ�敪Row�j��<BR>
     * this.�����P�ʏЉ�敪�s�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_lngOrderUnitId �����P��ID
     * @@param l_productType �����^�C�v
     * @@throws WEB3SystemLayerException
     * @@roseuid 40640F0102AE
     */
    public WEB3GentradeOrderUnitIntroduceDiv(
        long l_lngOrderUnitId,
        ProductTypeEnum l_productType)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeOrderUnitIntroduceDiv(long, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�P�j�@@DB����
            OrderUnitIntroduceDivRow l_row = 
                OrderUnitIntroduceDivDao.findRowByPk(
                    l_lngOrderUnitId, l_productType);
            //�Q�j�@@�s�I�u�W�F�N�g�Z�b�g 
            this.orderUnitIntroduceDivParams = 
                new OrderUnitIntroduceDivParams(l_row);
        }
        catch (DataFindException l_de)
        {
            log.debug(STR_METHOD_NAME+" not found: " + l_lngOrderUnitId + "," + l_productType);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        catch (DataException l_de)
        {
            log.error(STR_METHOD_NAME, l_de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
	 * (set�����P��ID)<BR>
	 * �����P��ID�̐ݒ���s���B<BR>
	 * <BR>
	 * 1) this.�����P�ʏЉ�敪�s.set�����P��ID( )���R�[������B<BR>
	 * [����]<BR>
	 * �@@�����P��ID=����.�����P��ID<BR>
     * @@param l_lngOrderUnitId �����P��ID
     * @@roseuid 40640F0102AE
	 */
    public void setOrderUnitId(long l_lngOrderUnitId)
	{
        final String STR_METHOD_NAME = "setOrderUnitId(long)";
        log.entering(STR_METHOD_NAME);

        this.orderUnitIntroduceDivParams.setOrderUnitId(l_lngOrderUnitId);
        
        log.exiting(STR_METHOD_NAME);
	}

    /**
     * (get�����P��ID)<BR>
     * �����P��ID��Ԃ��B<BR>
     * <BR>
     * this.�����P�ʏЉ�敪�s.get�����P��ID()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 40640F0102AE
     */
    public long getOrderUnitId()
    {
        return this.orderUnitIntroduceDivParams.getOrderUnitId();
    }

    /**
     * (set����ID)<BR>
     * ����ID�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�����P�ʏЉ�敪�s.set����ID( )���R�[������B<BR>
     * [����]<BR>
     * �@@����ID=����.����ID<BR>
     * @@param l_lngAccountId ����ID
     * @@roseuid 40640F0102AE
     */
    public void setAccountId(long l_lngAccountId)
    {
        final String STR_METHOD_NAME = 
            "setAccountId(long)";
        log.entering(STR_METHOD_NAME);

        this.orderUnitIntroduceDivParams.setAccountId(l_lngAccountId);
            
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get����ID)<BR>
     * �����P��ID��Ԃ��B<BR>
     * <BR>
     * this.�����P�ʏЉ�敪�s.get����ID()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 40640F0102AE
     */
    public long getAccountId()
    {
        return this.orderUnitIntroduceDivParams.getAccountId();
    }

    /**
     * (set�����^�C�v)<BR>
     * �����^�C�v�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�����P�ʏЉ�敪�s.set�����^�C�v( )���R�[������B<BR>
     * [����]<BR>
     * �@@�����^�C�v=����.�����^�C�v<BR>
     * @@param l_productType �����^�C�v
     * @@roseuid 40640F0102AE
     */
    public void setProductType(ProductTypeEnum l_productType)
    {
        final String STR_METHOD_NAME = 
            "setProductType(ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        this.orderUnitIntroduceDivParams.setProductType(l_productType);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�����^�C�v)<BR>
     * �����^�C�v��Ԃ��B<BR>
     * <BR>
     * this.�����P�ʏЉ�敪�s.get�����^�C�v()�̖߂�l��Ԃ��B<BR>
     * @@return ProductTypeEnum
     * @@roseuid 40640F0102AE
     */
    public ProductTypeEnum getProductType()
    {
        return this.orderUnitIntroduceDivParams.getProductType();
    }

    /**
     * (set�Љ�敪)<BR>
     * �Љ�敪�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�����P�ʏЉ�敪�s.set�Љ�敪( )���R�[������B<BR>
     * [����]<BR>
     * �@@�Љ�敪=����.�Љ�敪<BR>
     * @@param l_strProductType �Љ�敪
     * @@roseuid 40640F0102AE
     */
    public void setIntroduceBranchDiv(String l_strProductType)
    {
        final String STR_METHOD_NAME = 
            "setIntroduceBranchDiv(String)";
        log.entering(STR_METHOD_NAME);

        this.orderUnitIntroduceDivParams.setIntroduceBranchDiv(l_strProductType);
            
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�Љ�敪)<BR>
     * �Љ�敪��Ԃ��B<BR>
     * <BR>
     * this.�����P�ʏЉ�敪�s.get�Љ�敪()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40640F0102AE
     */
    public String getIntroduceBranchDiv()
    {
        return this.orderUnitIntroduceDivParams.getIntroduceBranchDiv();
    }

    /**
     * (set�Љ�X�R�[�h)<BR>
     * �Љ�X�R�[�h�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�����P�ʏЉ�敪�s.set�Љ�X�R�[�h( )���R�[������B<BR>
     * [����]<BR>
     * �@@�Љ�X�R�[�h=����.�Љ�X�R�[�h<BR>
     * @@param l_strIntroduceBranchCode �Љ�X�R�[�h
     * @@roseuid 40640F0102AE
     */
    public void setIntroduceBranchCode(String l_strIntroduceBranchCode)
    {
        final String STR_METHOD_NAME = 
            "setIntroduceBranchCode(String)";
        log.entering(STR_METHOD_NAME);

        this.orderUnitIntroduceDivParams.setIntroduceBranchCode(l_strIntroduceBranchCode);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�Љ�X�R�[�h)<BR>
     * �Љ�X�R�[�h��Ԃ��B<BR>
     * <BR>
     * this.�����P�ʏЉ�敪�s.get�Љ�X�R�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40640F0102AE
     */
    public String getIntroduceBranchCode()
    {
        return this.orderUnitIntroduceDivParams.getIntroduceBranchCode();
    }

    /**
     * (set�X�V�҃R�[�h<BR>
     * �X�V�҃R�[�h�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�����P�ʏЉ�敪�s.set�X�V�҃R�[�h( )���R�[������B<BR>
     * [����]<BR>
     * �@@�X�V�҃R�[�h=����.�X�V�҃R�[�h<BR>
     * @@param l_strLastUpdater �X�V�҃R�[�h
     * @@roseuid 40640F0102AE
     */
    public void setLastUpdater(String l_strLastUpdater)
    {
        final String STR_METHOD_NAME = 
            "setLastUpdater(String)";
        log.entering(STR_METHOD_NAME);

        this.orderUnitIntroduceDivParams.setLastUpdater(l_strLastUpdater);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h��Ԃ��B<BR>
     * <BR>
     * this.�����P�ʏЉ�敪�s.get�X�V�҃R�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40640F0102AE
     */
    public String getLastUpdater()
    {
        return this.orderUnitIntroduceDivParams.getLastUpdater();
    }

    /**
     * (�����P�ʏЉ�敪)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40640F0102AE
     */
    public WEB3GentradeOrderUnitIntroduceDiv()
    {
        this.orderUnitIntroduceDivParams = new OrderUnitIntroduceDivParams();
    }

	/* (�� Javadoc)
	 * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject#getDataSourceObject()
	 */
	public Object getDataSourceObject() 
    {
		return this.orderUnitIntroduceDivParams;
	}
}
@
