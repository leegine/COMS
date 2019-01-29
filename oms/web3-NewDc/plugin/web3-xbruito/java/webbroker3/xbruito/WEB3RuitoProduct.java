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
filename	WEB3RuitoProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���ݓ������N���X(WEB3RuitoProduct)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/8  ��O�� (���u) �V�K�쐬
                   2004/12/02 ��O�� (���u) �c�Ή�
*/
package webbroker3.xbruito;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.stdimpls.RuitoProductImpl;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * �g���ݓ������N���X�B<BR>
 */
public class WEB3RuitoProduct extends RuitoProductImpl
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoProduct.class);
    
    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * super(����Row)���R�[������B<BR>
     * @@param l_productRow - ����Row<BR>
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 406BEF2503A8
     */
    public WEB3RuitoProduct(ProductRow l_productRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_productRow);
    }

    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * super(�ݓ�����Row)���R�[������B<BR>
     * @@param l_ruitoProductRow - �ݓ�����Row<BR>
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 406BEF250399
     */
    public WEB3RuitoProduct(RuitoProductRow l_ruitoProductRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_ruitoProductRow);
    }

    /**
     * this.��������Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get������()��Ԃ��B<BR>
     * @@return String
     * @@roseuid 407636F1035B
     */
    public String getProductName()
    {
        String l_strProductName =
            ((RuitoProductParams) 
            this.getDataSourceObject()).getStandardName().trim();
        return l_strProductName;
    }

    /**
     * this.�R�[�X��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�R�[�X()��Ԃ��B<BR>
     * @@return String
     * @@roseuid 4076370802CE
     */
    public String getCourse()
    {
        String l_strCourse = ((RuitoProductParams) 
        this.getDataSourceObject()).getCourse().trim();
        return l_strCourse;
    }

    /**
     * this.�v������Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�v����()��Ԃ��B<BR>
     * @@return String
     * @@roseuid 407637120280
     */
    public String getPlan()
    {
        String l_strPlan = ((RuitoProductParams) 
        this.getDataSourceObject()).getPlan().trim();
        return l_strPlan;
    }

    /**
     * this.MRF�R�[�h��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().getMRF�R�[�h()��Ԃ��B<BR>
     * @@return String
     * @@roseuid 4076371B031C
     */
    public String getMRFCode()
    {
        String l_strMRFCode =
            ((RuitoProductParams) 
            this.getDataSourceObject()).getMrfFundCode().trim();
        return l_strMRFCode;
    }

    /**
     * this.���t������z��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���t������z()��Ԃ��B<BR>
     * @@return double
     * @@roseuid 407637270195
     */
    public double getBuyMaxPrice()
    {
        double l_dblBuyMaxPrice =
            ((RuitoProductParams) this.getDataSourceObject()).getBuyMaxPrice();
        return l_dblBuyMaxPrice;
    }

    /**
     * this.��������z��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get��������z()��Ԃ��B<BR>
     * @@return double
     * @@roseuid 407637380270
     */
    public double getSellMaxPrice()
    {
        double l_dblSellMaxPrice =
            ((RuitoProductParams) this.getDataSourceObject()).getSellMaxPrice();
        return l_dblSellMaxPrice;
    }

    /**
     * this.���t�������z��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���t�������z()��Ԃ��B<BR>
     * @@return double
     * @@roseuid 40763743033B
     */
    public double getBuyMinPrice()
    {
        double l_dblBuyMinPrice =
            ((RuitoProductParams) this.getDataSourceObject()).getBuyMinPrice();
        return l_dblBuyMinPrice;
    }

    /**
     * this.��񉺌����z��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get��񉺌����z()��Ԃ��B<BR>
     * @@return double
     * @@roseuid 4076374F00DA
     */
    public double getSellMinPrice()
    {
        double l_dblSellMinPrice =
            ((RuitoProductParams) this.getDataSourceObject()).getSellMinPrice();
        return l_dblSellMinPrice;
    }

    /**
     * this.��n���@@��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get��n���@@()��Ԃ��B<BR>
     * <BR>
     * 0�F�I���\<BR>
     * 1�F��s�U��<BR>
     * 2�F�،���������<BR>
     * @@return String
     * @@roseuid 407A53C603D4
     */
    public String getPaymentMethod()
    {
        String l_strPaymentMethod =
            ((RuitoProductParams) this.getDataSourceObject()).getPaymentMethod();
        return l_strPaymentMethod;
    }

    /**
     * this.�w����@@�i���t�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�w����@@�i���t�j()��Ԃ��B<BR>
     * <BR>
     * 0�F�I���\<BR>
     * 3�F���z�w��<BR>
     * 4�F�����w��<BR>
     * @@return String
     * @@roseuid 407A544202BB
     */
    public String getPaymentMethodBuy()
    {
        String l_strPaymentMethodBuy =
            ((RuitoProductParams) 
            this.getDataSourceObject()).getBuyDesignateMethod();
        return l_strPaymentMethodBuy;
    }

    /**
     * this.�w����@@�i���j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�w����@@�i���j()��Ԃ��B<BR>
     * <BR>
     * 0�F�I���\<BR>
     * 2�F�S���w��<BR>
     * 3�F���z�w��<BR>
     * 4�F�����w��<BR>
     * @@return String
     * @@roseuid 407A544202BC
     */
    public String getPaymentMethodSell()
    {
        String l_strPaymentMethodSell =
            ((RuitoProductParams) 
            this.getDataSourceObject()).getSellDesignateMethod();
        return l_strPaymentMethodSell;
    }
    
    /**
     * (is���t�\)
     * ���������w�肳�ꂽ�������ɔ��t�\�����`�F�b�N����B <BR>
     * <BR>
     * �P�j�@@���t�J�n�����擾����B<BR>
     *  this.getDataSourceObject().get���t�J�n��()���R�[�����āA���t�J�n�����擾����B<BR>
     * �Q�j�@@���t�I�������擾����B<BR>
     *  this.getDataSourceObject().get���t�I����()���R�[�����āA���t�I�������擾����B<BR>
     * <BR>
     * �R�j�@@���t�J�n�����邢�͔��t�I�������ݒ肳��Ă��Ȃ��ꍇ�� false ��Ԃ��B<BR>
     * <BR>
     * �S�j�@@���t�J�n���A���t�I�����A����.��������YYYYMMDD�`���̕�����ɕϊ�����B<BR>
     * <BR>
     * �T�j�@@�S�j�ŕϊ����������񂪈ȉ��̏����ɍ��v����ꍇ�� true ���A<BR>
     *      �����łȂ��ꍇ�� false ��Ԃ��B<BR>
     * <BR>
     *      ���t�J�n�� �� ������ �� ���t�I����<BR>
     * @@param l_datBizDate - ������
     * @@return booleans
     * @@roseuid 407A544202BC
     */
    public boolean isBuyPossible(Date l_datBizDate)
    {
        final String STR_METHOD_NAME =
            "isBuyPossible(Date l_datBizDate)";        
        log.entering(STR_METHOD_NAME);
        
        //�P�j���t�J�n�����擾����B
        RuitoProductRow l_ruitoProductRow = (RuitoProductRow)this.getDataSourceObject();
        Date l_datBuyStartDate = l_ruitoProductRow.getBuyStartDate();
        
        //�Q�j���t�I�������擾����B
        Date l_datBuyEndDate = l_ruitoProductRow.getBuyEndDate();
        
        //�R�j���t�J�n�����邢�͔��t�I�������ݒ肳��Ă��Ȃ��ꍇ�� false ��Ԃ��B 
        if (l_datBuyStartDate == null || l_datBuyEndDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
       
        //�S�j���t�J�n���A���t�I�����A����.��������YYYYMMDD�`���̕�����ɕϊ�����B
        Date l_datFormatBizDate =
            WEB3DateUtility.getDate(
            WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd"),
            "yyyyMMdd");
        Date l_datFormatBuyStartDate =
            WEB3DateUtility.getDate(
            WEB3DateUtility.formatDate(l_datBuyStartDate, "yyyyMMdd"),
            "yyyyMMdd");
        Date l_datFormatBuyEndDate =
            WEB3DateUtility.getDate(
            WEB3DateUtility.formatDate(l_datBuyEndDate, "yyyyMMdd"),
            "yyyyMMdd");
        
        log.debug("���t�J�n�� = " + WEB3DateUtility.formatDate(l_datFormatBuyStartDate, "yyyyMMdd"));
        log.debug("������ = " + WEB3DateUtility.formatDate(l_datFormatBizDate, "yyyyMMdd"));
        log.debug("���t�I���� = " + WEB3DateUtility.formatDate(l_datFormatBuyEndDate, "yyyyMMdd"));
        
        //�T�j�@@�S�j�ŕϊ����������񂪈ȉ��̏����ɍ��v����ꍇ�� true ���A
        //      �����łȂ��ꍇ�� false ��Ԃ��B
        //      ���t�J�n�� �� ������ �� ���t�I����
        if (WEB3DateUtility.compareToDay(l_datFormatBizDate, l_datFormatBuyStartDate) >= 0 &&
            WEB3DateUtility.compareToDay(l_datFormatBizDate, l_datFormatBuyEndDate) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }        
    }
    
    /**
     * (is���\)
     * ���������w�肳�ꂽ�������ɉ��\�����`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���J�n�����擾����B<BR>
     *  this.getDataSourceObject().get���J�n��()���R�[�����āA���J�n�����擾����B<BR>
     * �Q�j�@@���I�������擾����B<BR>
     *  this.getDataSourceObject().get���I����()���R�[�����āA���I�������擾����B<BR>
     * <BR>
     * �R�j�@@���J�n�����邢�͉��I�������ݒ肳��Ă��Ȃ��ꍇ�� false ��Ԃ��B <BR>
     * <BR>
     * �S�j�@@���J�n���A���I�����A����.��������YYYYMMDD�`���̕�����ɕϊ�����B<BR>
     * <BR>
     * �T�j�@@�S�j�ŕϊ����������񂪈ȉ��̏����ɍ��v����ꍇ�� true ���A<BR>
     *      �����łȂ��ꍇ�� false ��Ԃ��B
     * <BR>
     *      ���J�n�� �� ������ �� ���I����<BR>
     * @@param l_datBizDate - ������
     * @@return boolean
     * @@roseuid 407A544202BC
     */
    public boolean isSellPossible(Date l_datBizDate)
    {
        final String STR_METHOD_NAME =
            "isSellPossible(Date l_datBizDate)";           
        log.entering(STR_METHOD_NAME);
        
        //�P�j���J�n�����擾����B
        RuitoProductRow l_ruitoProductRow = (RuitoProductRow)this.getDataSourceObject();
        Date l_datSellStartDate = l_ruitoProductRow.getSellStartDate();
        
        //�Q�j���I�������擾����B
        Date l_datSellEndDate = l_ruitoProductRow.getSellEndDate();
        
        //�R�j���J�n�����邢�͉��I�������ݒ肳��Ă��Ȃ��ꍇ�� false ��Ԃ��B 
        if (l_datSellStartDate == null || l_datSellEndDate == null)
        {
            log.debug("���J�n�����邢�͉��I�������ݒ肳��Ă��Ȃ��ꍇ�� false ��Ԃ��B");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
       
        //�S�j���J�n���A���I�����A����.��������YYYYMMDD�`���̕�����ɕϊ�����B
        Date l_datFormatBizDate =
            WEB3DateUtility.getDate(
            WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd"),
            "yyyyMMdd");
        Date l_datFormatSellStartDate =
            WEB3DateUtility.getDate(
            WEB3DateUtility.formatDate(l_datSellStartDate, "yyyyMMdd"),
            "yyyyMMdd");
        Date l_datFormatSellEndDate =
            WEB3DateUtility.getDate(
            WEB3DateUtility.formatDate(l_datSellEndDate, "yyyyMMdd"),
            "yyyyMMdd");        
        
        log.debug("���J�n�� = " + WEB3DateUtility.formatDate(l_datFormatSellStartDate, "yyyyMMdd"));
        log.debug("������ = " + WEB3DateUtility.formatDate(l_datFormatBizDate, "yyyyMMdd"));
        log.debug("���I���� = " + WEB3DateUtility.formatDate(l_datFormatSellEndDate, "yyyyMMdd"));
        
        //�T�j�@@�S�j�ŕϊ����������񂪈ȉ��̏����ɍ��v����ꍇ�� true ���A
        //      �����łȂ��ꍇ�� false ��Ԃ��B
        //      ���J�n�� �� ������ �� ���I����
        if (WEB3DateUtility.compareToDay(l_datFormatBizDate, l_datFormatSellStartDate) >= 0 &&
            WEB3DateUtility.compareToDay(l_datFormatBizDate, l_datFormatSellEndDate) <= 0)
        {
            log.debug("���J�n�� �� ������ �� ���I����");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
}
@
