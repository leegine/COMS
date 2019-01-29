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
filename	WEB3GentradeCurrency.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i���ʁj�ʉ�(WEB3GentradeCurrency.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/22 �h�C (���u) �V�K�쐬
*/
package webbroker3.gentrade;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.GenCurrencyDao;
import webbroker3.gentrade.data.GenCurrencyPK;
import webbroker3.gentrade.data.GenCurrencyRow;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i���ʁj�ʉ�)<BR>
 * �i���ʁj�ʉ�<BR>
 * @@author �h�C (���u)
 * @@version 1.0
 */
public class WEB3GentradeCurrency implements BusinessObject
{
    /** 
     * ���O���[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeCurrency.class);
    
    /**
     * (�i���ʁj�ʉݍs)
     * �i���ʁj�ʉݍs�I�u�W�F�N�g<BR>
     */
    private GenCurrencyParams genCurrencyParams;
    
    /**
     * (get�בփ��[�g)<BR>
     * <BR>
     * �בփ��[�g���擾����B<BR>
     * <BR>
     * �i���͈בփ��[�g != 0�j�̏ꍇ�A���͈בփ��[�g��ԋp����B <BR>
     * �ȊO�A�ȉ��̔�����s���B <BR>
     * <BR>
     * ���@@���t���̏ꍇ�iis���t == true && is���v�Z == true�j <BR>
     * �@@get���t���בփ��[�g()�̖߂�l��ԋp����B <BR>
     * <BR>
     * ���@@���t���̏ꍇ�iis���t == false && is���v�Z == true�j <BR>
     * �@@get���t���בփ��[�g()�̖߂�l��ԋp����B <BR>
     * <BR>
     * ���@@���t�����̏ꍇ�iis���t == true && is���v�Z == false�j <BR>
     * �@@get���t��בփ��[�g()�̖߂�l��ԋp����B <BR>
     * <BR>
     * ���@@���t�����̏ꍇ�iis���t == false && is���v�Z == false�j <BR>
     * �@@get���t��בփ��[�g()�̖߂�l��ԋp����B<BR>
     * <BR>
     * @@param l_blnIsBuy  is���t<BR>
     * ���t���̔��� <BR>
     * <BR>
     * true�F�� <BR>
     * false�F�� <BR>
     * @@param l_blnIsExec  is���v�Z<BR>
     * ���v�Z���̔��� <BR>
     * <BR>
     * true�F���v�Z <BR>
     * false�F�T�Z�v�Z<BR>
     * @@param l_dblInputExchangeRate  ���͈בփ��[�g<BR>
     * ���͈בփ��[�g�i��0�w��j<BR>
     * @@return double 
     */
    public double getExchangeRate(
        boolean l_blnIsBuy, 
        boolean l_blnIsExec, 
        double l_dblInputExchangeRate)
    {
        final String STR_METHOD_NAME = "getExchangeRate(boolean, boolean, double)";
        log.entering(STR_METHOD_NAME);
        
        //�i���͈בփ��[�g != 0�j�̏ꍇ�A���͈בփ��[�g��ԋp����B
        if(l_dblInputExchangeRate != 0)
        {
            log.exiting(STR_METHOD_NAME);
            return l_dblInputExchangeRate;
        }
        
        //�ȊO�A�ȉ��̔�����s���B
        
        //���t���̏ꍇ�iis���t == true && is���v�Z == true�j
        //get���t���בփ��[�g()�̖߂�l��ԋp����B
        if(l_blnIsBuy && l_blnIsExec)
        {
            log.exiting(STR_METHOD_NAME);
            return this.getBuyExecRate();
        }
            
        //���t���̏ꍇ�iis���t == false && is���v�Z == true�j
        //get���t���בփ��[�g()�̖߂�l��ԋp����B
        if(!l_blnIsBuy && l_blnIsExec)
        {
            log.exiting(STR_METHOD_NAME);
            return this.getSellExecRate();
        }
           
        //���t�����̏ꍇ�iis���t == true && is���v�Z == false�j
        //get���t��בփ��[�g()�̖߂�l��ԋp����B
        if(l_blnIsBuy && !l_blnIsExec)
        {
            log.exiting(STR_METHOD_NAME);
            return this.getBuyBaseRate();
        }
            
        //���t�����̏ꍇ�iis���t == false && is���v�Z == false�j
        //get���t��בփ��[�g()�̖߂�l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return this.getSellBaseRate();
    }
    
    /**
     * (get�~�݊��Z�ۂߕ���)<BR>
     * <BR>
     * �~�݊��Z�ۂߕ������擾����B <BR>
     * <BR>
     * this.�i���ʁj�ʉݍs.�~�݊��Z�ۂߕ�����ԋp����B <BR>
     * <BR>
     * @@return String 
     */
    public String getChangeJpyRoundDiv()
    {
        final String STR_METHOD_NAME = "getChangeJpyRoundDiv()";
        log.entering(STR_METHOD_NAME);
        
        //this.�i���ʁj�ʉݍs.�~�݊��Z�ۂߕ�����ԋp����B
        log.exiting(STR_METHOD_NAME);
        return this.genCurrencyParams.getChangeJpyRoundDiv();
    }
    
    /**
     * (get�O�݊��Z�ۂߕ���)<BR>
     * <BR>
     * �O�݊��Z�ۂߕ������擾����B <BR>
     * <BR>
     * this.�i���ʁj�ʉݍs.�O�݊��Z�ۂߕ�����ԋp����B <BR>
     * <BR>
     * @@return String 
     */
    public String getChangeFCcyRoundDiv()
    {
        final String STR_METHOD_NAME = "getChangeFCcyRoundDiv()";
        log.entering(STR_METHOD_NAME);
        
        //this.�i���ʁj�ʉݍs.�O�݊��Z�ۂߕ�����ԋp����B
        log.exiting(STR_METHOD_NAME);
        return this.genCurrencyParams.getChangeFCcyRoundDiv();
    }
    
    /**
     * (get����������)<BR>
     * <BR>
     * �������������擾����B <BR>
     * <BR>
     * this.�i���ʁj�ʉݍs.������������ԋp����B <BR>
     * <BR>
     * @@return int 
     */
    public int getScale()
    {
        final String STR_METHOD_NAME = "getScale()";
        log.entering(STR_METHOD_NAME);
        
        //this.�i���ʁj�ʉݍs.������������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return this.genCurrencyParams.getScale();
    }
    
    /**
     * (get�،���ЃR�[�h)<BR>
     * <BR>
     * �،���ЃR�[�h���擾����B <BR>
     * <BR>
     * this.�i���ʁj�ʉݍs.�،���ЃR�[�h��ԋp����B <BR>
     * <BR>
     * @@return String 
     */
    public String getInstitutionCode()
    {
        final String STR_METHOD_NAME = "getInstitutionCode()";
        log.entering(STR_METHOD_NAME);
        
        //this.�i���ʁj�ʉݍs.�،���ЃR�[�h��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return this.genCurrencyParams.getInstitutionCode();
    }
    
    /**
     * (get�ʉݖ�)<BR>
     * <BR>
     * �ʉݖ����擾����B <BR>
     * <BR>
     * this.�i���ʁj�ʉݍs.�ʉݖ���ԋp����B <BR>
     * <BR>
     * @@return String 
     */
    public String getCurrencyName()
    {
        final String STR_METHOD_NAME = "getCurrencyName()";
        log.entering(STR_METHOD_NAME);
        
        //this.�i���ʁj�ʉݍs.�ʉݖ���ԋp����B
        log.exiting(STR_METHOD_NAME);
        return this.genCurrencyParams.getCurrencyName();
    }
    
    /**
     * (get�ʉ݃R�[�h)<BR>
     * <BR>
     * �ʉ݃R�[�h���擾����B <BR>
     * <BR>
     * this.(����)�ʉݍs.�ʉ݃R�[�h��ԋp����B <BR>
     * <BR>
     * @@return String 
     */
    public String getCurrencyCode()
    {
        final String STR_METHOD_NAME = "getCurrencyCode()";
        log.entering(STR_METHOD_NAME);
        
        //this.(����)�ʉݍs.�ʉ݃R�[�h��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return this.genCurrencyParams.getCurrencyCode();
    }
    
    /**
     * (get���t��בփ��[�g)<BR>
     * <BR>
     * ���t��בփ��[�g���擾����B<BR>
     * <BR>
     * this.�i���ʁj�ʉݍs.���񔃕t�בփ��[�g��ԋp���� <BR>
     * <BR>
     * @@return double 
     */
    public double getBuyBaseRate()
    {
        final String STR_METHOD_NAME = "getBuyBaseRate()";
        log.entering(STR_METHOD_NAME);
        
        //this.�i���ʁj�ʉݍs.���񔃕t�בփ��[�g��ԋp����
        log.exiting(STR_METHOD_NAME);
        return this.genCurrencyParams.getCurrentBuyRate();
    }
    
    /**
     * (get���t���בփ��[�g)<BR>
     * <BR>
     * ���t���בփ��[�g���擾����B<BR>
     * <BR>
     * this.�i���ʁj�ʉݍs.���񔃕t���בփ��[�g��ԋp����B<BR>
     * <BR>
     * @@return double 
     */
    public double getBuyExecRate()
    {
        final String STR_METHOD_NAME = "getBuyExecRate()";
        log.entering(STR_METHOD_NAME);
        
        //this.�i���ʁj�ʉݍs.���񔃕t���בփ��[�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return this.genCurrencyParams.getCurrentBuyExecRate();
    }
    
    /**
     * (get���t��בփ��[�g)<BR>
     * <BR>
     * ���t��בփ��[�g���擾����B<BR>
     * <BR>
     * this.�i���ʁj�ʉݍs.���񔄕t�בփ��[�g��ԋp����B<BR>
     * <BR>
     * @@return double 
     */
    public double getSellBaseRate()
    {
        final String STR_METHOD_NAME = "getSellBaseRate()";
        log.entering(STR_METHOD_NAME);
        
        //this.�i���ʁj�ʉݍs.���񔄕t�בփ��[�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return this.genCurrencyParams.getCurrentSellRate();
    }
    
    /**
     * (get���t���בփ��[�g)<BR>
     * <BR>
     * ���t���בփ��[�g���擾����B<BR>
     * <BR>
     * this.�i���ʁj�ʉݍs.���񔄕t���בփ��[�g��ԋp����B<BR>
     * <BR>
     * @@return double 
     */
    public double getSellExecRate()
    {
        final String STR_METHOD_NAME = "getSellExecRate()";
        log.entering(STR_METHOD_NAME);
        
        //this.�i���ʁj�ʉݍs.���񔄕t���בփ��[�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return this.genCurrencyParams.getCurrentSellExecRate();
    }
    
    /**
     * (�i���ʁj�ʉ�)<BR>
     * <BR>
     * �i���ʁj�ʉ݂𐶐�����B <BR>
     * <BR>
     * �P�j�@@�I�u�W�F�N�g���� <BR>
     * �@@�i���ʁj�ʉ݃I�u�W�F�N�g�𐶐�����B <BR>
     * <BR>
     * �Q�j�@@�i���ʁj�ʉݍs�v���p�e�B�̃Z�b�g <BR>
     * �@@�i���ʁj�ʉݍs�𐶐������I�u�W�F�N�g�̃v���p�e�B�ɃZ�b�g���A�ԋp����B <BR>
     * <BR>
     * �� �i���ʁj�ʉ�Params�N���X��DDL��莩����������B<BR>
     * <BR>
     * @@param l_genCurrencyParams  �i���ʁj�ʉݍs<BR>
     * �i���ʁj�ʉݍs�I�u�W�F�N�g <BR>
     * ���i���ʁj�ʉ�Params�N���X��DDL��莩����������<BR>
     * @@throws WEB3BaseException 
     */
    public WEB3GentradeCurrency(GenCurrencyParams l_genCurrencyParams) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3GentradeCurrency(GenCurrencyParams)";
        log.entering(STR_METHOD_NAME);
        
        if(l_genCurrencyParams == null)
        {
            log.error("�i���ʁj�ʉݍs�I�u�W�F�N�g = null");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�i���ʁj�ʉݍs�I�u�W�F�N�g = null");
        }
        
        //�Q�j�@@�i���ʁj�ʉݍs�v���p�e�B�̃Z�b�g
        //�@@�i���ʁj�ʉݍs�𐶐������I�u�W�F�N�g�̃v���p�e�B�ɃZ�b�g���A�ԋp����B
        this.genCurrencyParams = l_genCurrencyParams;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (�i���ʁj�ʉ�)<BR>
     * <BR>
     * (static���\�b�h) <BR>
     * <BR>
     * �i���ʁj�ʉ݂𐶐�����B <BR>
     * <BR>
     * �ȉ��̏����Łi���ʁj�ʉ݃e�[�u������������B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�i���ʁj�ʉ݃e�[�u��.�،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * �@@�i���ʁj�ʉ݃e�[�u��.�ʉ݃R�[�h = ����.�ʉ݃R�[�h <BR>
     * <BR>
     * �������ʂ́i���ʁj�ʉݍs�I�u�W�F�N�g�������Ɏw�肵�āA<BR>
     * �R���X�g���N�^���R�[�����A <BR>
     * �i���ʁj�ʉ݃I�u�W�F�N�g�𐶐�����B <BR>
     * ���������i���ʁj�ʉ݃I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode  �،���ЃR�[�h<BR>
     * @@param l_strCurrencyCode  �ʉ݃R�[�h<BR>
     * @@throws WEB3BaseException 
     */
    public static WEB3GentradeCurrency genCurrency(
        String l_strInstitutionCode, 
        String l_strCurrencyCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "genCurrency(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�ȉ��̏����Łi���ʁj�ʉ݃e�[�u������������B
        //�@@[����] 
        //�@@�i���ʁj�ʉ݃e�[�u��.�،���ЃR�[�h = ����.�،���ЃR�[�h 
        //�@@�i���ʁj�ʉ݃e�[�u��.�ʉ݃R�[�h = ����.�ʉ݃R�[�h
        GenCurrencyRow l_genCurrencyRow = null;
        try
        {
            GenCurrencyPK l_genCurrencyPK = new GenCurrencyPK(
                l_strInstitutionCode,
                l_strCurrencyCode);
            l_genCurrencyRow = GenCurrencyDao.findRowByPk(l_genCurrencyPK);
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeCurrency.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeCurrency.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeCurrency.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�������ʂ́i���ʁj�ʉݍs�I�u�W�F�N�g�������Ɏw�肵�āA
        //�R���X�g���N�^���R�[�����A 
        //�i���ʁj�ʉ݃I�u�W�F�N�g�𐶐�����B 
        //���������i���ʁj�ʉ݃I�u�W�F�N�g��ԋp����B
        GenCurrencyParams l_genCurrencyParams = new GenCurrencyParams(l_genCurrencyRow);
        WEB3GentradeCurrency l_gentradeCurrency = new WEB3GentradeCurrency(l_genCurrencyParams);
        
        log.exiting(STR_METHOD_NAME);
        return l_gentradeCurrency;
    }
    
    /**
     * (get�i���ʁj�ʉ�)<BR>
     * <BR>
     * (static���\�b�h) <BR>
     * <BR>
     * �w�肳�ꂽ�،���Ђ��o�^���Ă���i���ʁj�ʉ݂����ׂĎ擾����B <BR>
     * <BR>
     * �i���ʁj�ʉ݃e�[�u�����ȉ��̏����Ō�������B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�i���ʁj�ʉ݃e�[�u��.�،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * <BR>
     * �Y���s�ɂāi���ʁj�ʉ݃I�u�W�F�N�g�𐶐����A�z��ɂĕԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode  �،���ЃR�[�h<BR>
     * @@return WEB3GentradeCurrency[] <BR>
     * @@throws WEB3BaseException
     */
    public static WEB3GentradeCurrency[] getGentradeCurrency(
        String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getGentradeCurrency(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�i���ʁj�ʉ݃e�[�u�����ȉ��̏����Ō�������B
        // �@@[����] 
        //�@@�i���ʁj�ʉ݃e�[�u��.�،���ЃR�[�h = ����.�،���ЃR�[�h
        List l_lstRecords = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_sbWhere = " institution_code = ? ";     //�،���ЃR�[�h
            Object[] l_objWhere = {l_strInstitutionCode};
            l_lstRecords = l_processor.doFindAllQuery(
                GenCurrencyRow.TYPE,
                l_sbWhere,
                l_objWhere);
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeCurrency.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeCurrency.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeCurrency.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�Y���s�ɂāi���ʁj�ʉ݃I�u�W�F�N�g�𐶐����A�z��ɂĕԋp����B
        WEB3GentradeCurrency[] l_gentradeCurrencys = 
            new WEB3GentradeCurrency[l_lstRecords.size()];
        for (int i = 0; i < l_lstRecords.size(); i++) 
        {
            GenCurrencyParams l_genCurrencyParams = 
                new GenCurrencyParams((GenCurrencyRow)l_lstRecords.get(i));
            l_gentradeCurrencys[i] =
                new WEB3GentradeCurrency(l_genCurrencyParams);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_gentradeCurrencys;
    }
    
    /**
     * (getDataSourceObject)<BR>
     * <BR>
     * getDataSourceObject�̎����B<BR>
     * <BR>
     * this.�i���ʁj�ʉݍs��ԋp����B<BR>
     * <BR>
     * @@return Object 
     */
    public Object getDataSourceObject()
    {
        final String STR_METHOD_NAME = "getDataSourceObject()";
        log.entering(STR_METHOD_NAME);
        
        //this.�i���ʁj�ʉݍs��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return this.genCurrencyParams;
    }
    
    /**
     * (get�ʉ݃R�[�h�ꗗ)<BR>
     * <BR>
     * �istatic���\�b�h�j <BR>
     * <BR>
     * �w�肵���،���Ђ��o�^���Ă���ʉ݂̒ʉ݃R�[�h�����ׂĎ擾����B <BR>
     * <BR>
     * �P�jget�i���ʁj�ʉ݁i�j���Ăяo���A�ʉ݃I�u�W�F�N�g�̔z����擾����B<BR> 
     * <BR>
     * �Q�j�ʉ݃I�u�W�F�N�g�̔z���Loop����B <BR>
     * �@@�Q�|�P�j�ʉ݃I�u�W�F�N�g�̔z�񂩂�ʉ݃R�[�h���擾����B <BR>
     * �@@�Q�|�Q�j�ʉ݃R�[�h�̔z��ɒʉ݃R�[�h��ǉ�����B <BR>
     * <BR>
     * �R�j�ʉ݃R�[�h�̔z���Ԃ��B<BR>
     * <BR>
     * @@param l_strInstitutionCode  �،���ЃR�[�h<BR>
     * @@return String[] <BR>
     * @@throws WEB3BaseException
     */
    public static String[] getCurrencyCodeList(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCurrencyCodeList(String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jget�i���ʁj�ʉ݁i�j���Ăяo���A�ʉ݃I�u�W�F�N�g�̔z����擾����B
        WEB3GentradeCurrency[] l_gentradeCurrencys = 
            WEB3GentradeCurrency.getGentradeCurrency(l_strInstitutionCode);
        
        //�Q�j�ʉ݃I�u�W�F�N�g�̔z���Loop����B 
        //�@@�Q�|�P�j�ʉ݃I�u�W�F�N�g�̔z�񂩂�ʉ݃R�[�h���擾����B
        //�@@�Q�|�Q�j�ʉ݃R�[�h�̔z��ɒʉ݃R�[�h��ǉ�����B
        int l_intLength = l_gentradeCurrencys.length;
        String[] l_strCurrencyCodes = new String[l_intLength];
        for(int i = 0; i < l_intLength; i++)
        {
            l_strCurrencyCodes[i] = l_gentradeCurrencys[i].getCurrencyCode();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strCurrencyCodes;
    }
}
@
