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
filename	WEB3GentradeInstitution.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،���ЃN���X(WEB3GentradeInstitution.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 �{���@@�瑐(SRA) �V�K�쐬
Revesion History : 2004/02/10 ����@@���j(SRA) �ҏW
Revesion History : 2004/07/13 羐� (���u) �ύX
Revesion History : 2007/06/27 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.255
Revesion History : 2007/10/09 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.272
Revesion History : 2010/01/12 ��іQ (���u)�y���ʁz�d�l�ύX�E���f��No.348
*/
package webbroker3.gentrade;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.InstitutionImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3FeqDayTradeDivDef;
import webbroker3.common.define.WEB3FeqNettingDivDef;
import webbroker3.common.define.WEB3ForcedsettleorderDivDef;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.common.define.WEB3OrderexecutionEndTypeDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.gentrade.data.InstitutionPreferencesDao;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.gentrade.data.OrderexecutionEndDao;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.util.WEB3LogUtility;

/**
 * �،���Ђ�\������B<BR>
 * xTrade��Institution���g�������N���X�B<BR>
 * <BR>
 * @@author �{���@@�瑐(SRA)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.InstitutionImpl
 */
public class WEB3GentradeInstitution extends InstitutionImpl
{
    /**
     * (�،���ЃR�[�h) <BR>
     */
    private String institutionCode;
    
    /**
     * (�s��J��) <BR>
     *  <BR>
     * �s��J�ǎ��ԑт��ǂ�����ۑ�����t���O�B<BR>
     * �o���I����M�����ɂ�false���ݒ肳���B<BR>
     */
    private boolean isTimeZoneMarket;
    
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeInstitution.class);
    


    /**
     * �R���X�g���N�^�B<BR>
     * <BR> 
     * @@param l_strInstitutionCode �،���ЃR�[�h
     * @@throws com.fitechlabs.xtrade.kernel.data.DataFindException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 403496ED02C8
     */
    public WEB3GentradeInstitution(String l_strInstitutionCode)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        super(l_strInstitutionCode);
    }

    /**
     * �R���X�g���N�^�B<BR>
     * <BR> 
     * @@param l_lngInstId �،����ID
     * @@param l_institutionId
     * @@throws com.fitechlabs.xtrade.kernel.data.DataFindException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 403496ED00E3
     */
    public WEB3GentradeInstitution(long l_institutionId)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        super(l_institutionId);
    }

    /**
     * �R���X�g���N�^�B<BR>
     * <BR> 
     * @@param l_lngInstId �،����ID
     * @@param l_institutionRow
     * @@throws com.fitechlabs.xtrade.kernel.data.DataFindException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 403496EC0315
     */
    public WEB3GentradeInstitution(InstitutionRow l_institutionRow)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        super(l_institutionRow);
    }

    /**
     * (get����������) <BR>
     * �������ɑ΂��钍�����������擾����B <BR>
     * <BR>
     * �P�j�@@�L�������擾 <BR>
     * �@@�Y���،���Ђ̒����L���������擾����B <BR>
     * �@@�@@�i�{�C���X�^���X���ێ�����،����Params���j <BR>
     * <BR>
     * �Q�j�@@�����������Z�o <BR>
     * �@@������(*1)�̒����L��������̓��t���Z�o����B <BR>
     * �@@�Z�o�������t���x���łȂ��ꍇ�A���̓��t��ԋp����B <BR>
     * �@@�Z�o�������t���x���ł������ꍇ�A�Z�o�������t�̑O�c�Ɠ����擾���ԋp����B <BR>
     * <BR>
     * (*1)�@@������ <BR>
     * TradingCalendarImpl.getCurrentBizDate( )�Ŏ擾�������t�B <BR>
     * <BR>
     * @@return java.util.Date
     * @@roseuid 40A8945F031C
     */
    public Date getExpirationDate() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getExpirationDate";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (is�o���I��) <BR>
     * �����Ɏw�肳�ꂽ���i���A�o���I���ς��ǂ����𔻕ʂ���B<BR>
     * [�߂�l] <BR>
     *   true: ������  false: ������ <BR>
     *  <BR>
     * �P�jDB���� <BR>
     *  �ȉ��̏����ŏo���I���e�[�u������������B<BR>
     *  <BR>
     *  [����] <BR>
     * �@@�@@�،���ЃR�[�h�@@�@@�@@�@@ ���@@this.�،���ЃR�[�h <BR>
     * �@@�@@�����^�C�v�@@�@@�@@�@@�@@�@@�@@ ���@@�p�����[�^.�����^�C�v <BR>
     * �@@�@@�敨�^�I�v�V�����敪�@@���@@�p�����[�^.�敨�^�I�v�V�����敪 <BR>
     * �@@�@@�o���I���敪�@@�@@�@@�@@  ���@@"DEFAULT" <BR>
     *  <BR>
     *  �f�[�^���擾�ł����ꍇ��true��ԋp����B<BR>
     *  �擾�ł��Ȃ������ꍇ��false��ԋp����B<BR>
     * @@param l_productType - (�����^�C�v) <BR>
     * @@param l_strFutureOptionDiv - (�敨�^�I�v�V�����敪) <BR>
     * �@@0�FDEFAULT�i�敨�I�v�V�����ȊO�j <BR>
     * �@@1�F�敨 <BR>
     * �@@2�F�I�v�V���� <BR>
     * @@return boolean<BR>
     * @@throws WEB3SystemLayerException
     */
    public boolean isOrderExecEnd(ProductTypeEnum l_productType,String l_strFutureOptionDiv) 
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isOrderExecEnd(ProductTypeEnum,String)";
        //�P�jDB���� 
        //�ȉ��̏����ŏo���I���e�[�u������������B
        //*   �،���ЃR�[�h�@@�@@�@@�@@ ���@@this.�،���ЃR�[�h 
        //*   �����^�C�v�@@�@@�@@�@@�@@�@@�@@ ���@@�p�����[�^.�����^�C�v 
        //*   �敨�^�I�v�V�����敪�@@���@@�p�����[�^.�敨�^�I�v�V�����敪
        OrderexecutionEndRow l_orderexecutionEndRow = null;
        try
        {
            l_orderexecutionEndRow = 
                OrderexecutionEndDao.findRowByInstitutionCodeProductTypeFutureOptionDivOrderexecutionEndType(
                    this.getInstitutionCode(),
                    l_productType,
                    l_strFutureOptionDiv,
                    WEB3OrderexecutionEndTypeDef.DEFAULT);
        }
        catch (DataFindException dfe)
        {

        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //�f�[�^���擾�ł����ꍇ��true��ԋp����B
        //�擾�ł��Ȃ������ꍇ��false��ԋp����B
        if(l_orderexecutionEndRow == null)
        {
            return false;
        }
        else
        {
            return true;
        }
        
    }
    
    /**
     * (is�a��،��]���������������]����) <BR>
     * �،���Ђ�����������a��،��Ƃ��ĕ]�����邩�ǂ����𔻕ʂ���<BR>
     * [�߂�l] <BR>
     *   true: ���{  false: �����{ <BR>
     *  <BR>
     * �P�j�a��،��]�����邩���ʂ���B<BR>
     *  <BR>
     * [this.�a��،��]���� = �h���{�h <BR>
     * ����this.���������]�� = �h���{�h�̏ꍇ]<BR>
     * true��ԋp����B<BR>
     * <BR>
     * [��L�ȊO]<BR>
     * false��ԋp����B<BR> 
     *  <BR>
     * @@return boolean<BR>
     */
    public boolean isInstitutionStockEvaluation()
    {
        InstitutionRow l_institutionRow = (InstitutionRow)this.getDataSourceObject();
        boolean l_isInstitutionStockEvaluation = false;
        if(WEB3EnforcementDef.ENFORCEMENT.equals(l_institutionRow.getAssetEvaluation()) &&
            WEB3EnforcementDef.ENFORCEMENT.equals(l_institutionRow.getStockEvaluation()))
        {
            l_isInstitutionStockEvaluation = true;
        }
        return l_isInstitutionStockEvaluation;

    }
    
    /**
     * (is�a��،��]������GP�]����) <BR>
     * �،���Ђ�GP��a��،��Ƃ��ĕ]�����邩�ǂ����𔻕ʂ���<BR>
     * [�߂�l] <BR>
     *   true: ���{  false: �����{ <BR>
     *  <BR>
     * �P�j�a��،��]�����邩���ʂ���B<BR>
     *  <BR>
     * [this.�a��،��]����= �h���{�h ����this.GP�]��= �h���{�h�̏ꍇ]<BR>
     * true��ԋp����B<BR>
     *  <BR>
     * [��L�ȊO] <BR>
     * false��ԋp����B<BR> 
     *  <BR>
     * @@return boolean<BR>
     */
    public boolean isInstitutionGpEvaluation()
    {
        InstitutionRow l_institutionRow = (InstitutionRow)this.getDataSourceObject();
        boolean l_isInstitutionGpEvaluation = false;
        if(WEB3EnforcementDef.ENFORCEMENT.equals(l_institutionRow.getAssetEvaluation()) &&
            WEB3EnforcementDef.ENFORCEMENT.equals(l_institutionRow.getGpEvaluation()))
        {
            l_isInstitutionGpEvaluation = true;
        }
        return l_isInstitutionGpEvaluation;
    }
    
    /**
     * (is�a��،��]���������M�]����) <BR>
     * �،���Ђ����M��a��،��Ƃ��ĕ]�����邩�ǂ����𔻕ʂ���<BR>
     * [�߂�l] <BR>
     *   true: ���{  false: �����{ <BR>
     *  <BR>
     * �P�j�a��،��]�����邩���ʂ���B<BR> 
     *  <BR> 
     * [this.�a��،��]����= �h���{�h ����<BR> 
     * this.���M�]��= �h���{�h�̏ꍇ] <BR> 
     * true��ԋp����B<BR> 
     * <BR> 
     * [��L�ȊO]<BR> 
     * false��ԋp����B<BR> 
     *  <BR>
     * @@return boolean<BR>
     */
    public boolean isInstitutionFundEvaluation()
    {
        InstitutionRow l_institutionRow = (InstitutionRow)this.getDataSourceObject();
        boolean l_isInstitutionFundEvaluation = false;
        if(WEB3EnforcementDef.ENFORCEMENT.equals(l_institutionRow.getAssetEvaluation()) &&
            WEB3EnforcementDef.ENFORCEMENT.equals(l_institutionRow.getFundEvaluation()))
        {
            l_isInstitutionFundEvaluation = true;
        }
        return l_isInstitutionFundEvaluation;
    }
    
    /**
     * (is�a��،��]���������]����) <BR>
     * �،���Ђ�����a��،��Ƃ��ĕ]�����邩�ǂ����𔻕ʂ���<BR>
     * [�߂�l] <BR>
     *   true: ���{  false: �����{ <BR>
     *  <BR>
     * �P�j�a��،��]�����邩���ʂ���B<BR>
     *  <BR>  
     * [this.�a��،��]����= �h���{�h ���� <BR> 
     * this.���]��= �h���{�h�̏ꍇ] <BR> 
     * true��ԋp����B<BR> 
     *  <BR> 
     * [��L�ȊO] <BR> 
     * false��ԋp����B<BR> 
     *  <BR>
     * @@return boolean<BR>
     */
    public boolean isInstitutionBondEvaluation()
    {
        InstitutionRow l_institutionRow = (InstitutionRow)this.getDataSourceObject();
        boolean l_isInstitutionBondEvaluation = false;
        if(WEB3EnforcementDef.ENFORCEMENT.equals(l_institutionRow.getAssetEvaluation()) &&
            WEB3EnforcementDef.ENFORCEMENT.equals(l_institutionRow.getBondEvaluation()))
        {
            l_isInstitutionBondEvaluation = true;
        }
        return l_isInstitutionBondEvaluation;
    }
    
    /**
     * (get�����J�z�����敪) <BR>
     *  <BR>
     * �����Ɏw�肳�ꂽ���i�̒����J�z�����敪��ԋp����B <BR>
     * <BR>
     * �I�[�o�[���[�h���\�b�h�ɏ������Ϗ��idelegate�j����B <BR>
     * <BR>
     * �@@[�����ݒ�d�l] <BR>
     * �@@�@@�����^�C�v�@@�@@�@@�@@�@@�@@�@@ ���@@�p�����[�^.�����^�C�v <BR>
     * �@@�@@�敨�^�I�v�V�����敪�@@���@@�p�����[�^.�敨�^�I�v�V�����敪 <BR>
     * �@@�@@�o���I���敪�@@�@@�@@�@@  ���@@"DEFAULT" <BR>
     * <BR>
     * @@param l_productType - (�����^�C�v) <BR>
     * (ProductTypeEnum�ɂĒ�`)<BR>
     * @@param l_strFutureOptionDiv - (�敨�^�I�v�V�����敪) <BR>
     * �@@0�FDEFAULT�i�敨�I�v�V�����ȊO�j <BR>
     * �@@1�F�敨 <BR>
     * �@@2�F�I�v�V���� <BR>
     * @@return String <BR>
     * @@throws WEB3SystemLayerException <BR>
     */
    public String getCarryoverEndType(ProductTypeEnum l_productType, String l_strFutureOptionDiv)
        throws WEB3SystemLayerException
    {
        return getCarryoverEndType(
            l_productType,
            l_strFutureOptionDiv,
            WEB3OrderexecutionEndTypeDef.DEFAULT);
    }

    /**
     * (get�����J�z�����敪) <BR>
     *  <BR>
     * �����Ɏw�肳�ꂽ���i�̒����J�z�����敪��ԋp����B <BR>
     * <BR>
     * �P�jDB���� <BR>
     * �@@�ȉ��̏����ŏo���I���e�[�u������������B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�،���ЃR�[�h�@@�@@�@@�@@ ���@@this.�،���ЃR�[�h <BR>
     * �@@�@@�����^�C�v�@@�@@�@@�@@�@@�@@�@@ ���@@�p�����[�^.�����^�C�v <BR>
     * �@@�@@�敨�^�I�v�V�����敪�@@���@@�p�����[�^.�敨�^�I�v�V�����敪 <BR>
     * �@@�@@�o���I���敪�@@�@@�@@�@@  ���@@�p�����[�^.�o���I���敪 <BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����B <BR>
     * <BR>
     * �Q�j�擾�����������ʂ̒����J�z�����敪��ԋp����B <BR>
     * <BR>
     * @@param l_productType - �����^�C�v<BR>
     * (ProductTypeEnum�ɂĒ�`)<BR>
     * @@param l_strFutureOptionDiv - �敨�^�I�v�V�����敪 <BR>
     * 0:DEFAULT(�敨�^�I�v�V�����ȊO) <BR>
     * 1:�敨 <BR>
     * 2:�I�v�V����<BR>
     * @@param l_strOrderExecutionEndType - �o���I���敪<BR>
     * @@return String <BR>
     * @@throws WEB3SystemLayerException <BR>
     */
    public String getCarryoverEndType(
        ProductTypeEnum l_productType,
        String l_strFutureOptionDiv,
        String l_strOrderExecutionEndType)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getCarryoverEndType(ProductTypeEnum,String,String)";

        //�P�jDB����
        //�ȉ��̏����ŏo���I���e�[�u������������B
        //  �،���ЃR�[�h�@@�@@�@@�@@ ���@@this.�،���ЃR�[�h
        //  �����^�C�v�@@�@@�@@�@@�@@�@@�@@ ���@@�p�����[�^.�����^�C�v
        //  �敨�^�I�v�V�����敪�@@���@@�p�����[�^.�敨�^�I�v�V�����敪
        //  �o���I���敪�@@�@@�@@�@@  ���@@�p�����[�^.�o���I���敪
        OrderexecutionEndRow l_orderexecutionEndRow = null;
        try
        {
            l_orderexecutionEndRow =
                OrderexecutionEndDao.findRowByInstitutionCodeProductTypeFutureOptionDivOrderexecutionEndType(
                    this.getInstitutionCode(),
                    l_productType,
                    l_strFutureOptionDiv,
                    l_strOrderExecutionEndType);
        }
        catch (DataFindException l_dfe)
        {
        }
        catch (DataException l_de)
        {
            log.error(l_de.getMessage(), l_de);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        //�Q�j�擾�����������ʂ̒����J�z�����敪��ԋp����B
        //�������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����B
        if (l_orderexecutionEndRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return l_orderexecutionEndRow.getCarryoverEndType();
        }

    }
    
    /**
     * (is�����x������)<BR>
     * <BR>
     * �g���K�[�����̔����x����<BR>
     * ���������Ђ��ǂ������ʂ���B<BR>
     * ��������ꍇ��true���A�ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * �P�j�@@�V�X�e���v���t�@@�����X�e�[�u�����<BR>
     * �@@�����x���������ǂ����̃t���O���擾����B<BR>
     * �@@GtlUtils.getTradingSystem().getPreference()<BR>
     * �@@���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[getPreference()�Ɏw�肷�����]<BR>
     * �@@�@@arg0�F�@@this.�،���ЃR�[�h<BR>
     * �@@�@@�@@+ IS_SUBMIT_DELAY_ORDER(*1)<BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l��"true"�ł���΁Atrue��ԋp����B<BR>
     * �@@�ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * (*1)IS_SUBMIT_DELAY_ORDER<BR>
     * �@@�R�[�h�l�F�@@".rls.is.submit.delay.order"<BR>
     * �@@����L���e�Œ萔��`�t�@@�C�����쐬���A<BR>
     * �@@�@@���̒�`�l���Q�Ƃ��邱�ƁB<BR>
     * @@return boolean
     */
    public boolean isSubmitOrderDelayDisregard()
    {
        final String STR_METHOD_NAME = "isSubmitOrderDelayDisregard()";
        log.entering(STR_METHOD_NAME);
        
        // �V�X�e���v���t�@@�����X�e�[�u����蔭���x���������ǂ����̃t���O���擾����B
        String l_strPreferenceName = this.getInstitutionCode()
            + WEB3SystemPreferencesNameDef.IS_SUBMIT_DELAY_ORDER;
        log.debug("�V�X�e���v���t�@@�����X�e�[�u�������l�F[" + l_strPreferenceName + "]");
        
        String l_strIsSubmitDelayOrder =
            GtlUtils.getTradingSystem().getPreference(l_strPreferenceName);
        
        // �����x�������̉�Ђ��ǂ������ʂ���B
        boolean l_blnIsSubmitOrderDelayDisregard = false;
        if ("true".equals(l_strIsSubmitDelayOrder))
        {
            l_blnIsSubmitOrderDelayDisregard = true;
            log.debug("***** �����x�������̉�� ***�@@�،���ЃR�[�h�F[" + this.getInstitutionCode() + "]");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsSubmitOrderDelayDisregard;
    }

    /**
     * (is�M�p�������ώ��{)<BR>
     * <BR>
     * �M�p�������ώ��{��Ђł��邩�𔻒肷��B <BR>
     * ���{�̏ꍇ��true���A�ȊO�Afalse��ԋp����B <BR>
     * <BR>
     * �P�j�@@Row�I�u�W�F�N�g���M�p�������ώ��{�敪���擾����B <BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l��0�܂���null�̏ꍇ�Afalse���A <BR>
     * �@@�@@�@@�ȊO�Atrue��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isForcedSettleOrder()
    {
        final String STR_METHOD_NAME = "isForcedSettleOrder()";
        log.entering(STR_METHOD_NAME);

        //Row�I�u�W�F�N�g���M�p�������ώ��{�敪���擾����
        //�M�p�������ώ��{�敪��0�܂���null�̏ꍇ�Afalse���A �ȊO�Atrue��ԋp����
        InstitutionRow l_institutionRow = (InstitutionRow)this.getDataSourceObject();
        boolean l_blnIsForcedsettleorder = true;

        if ((l_institutionRow.getForcedsettleorderDiv() == null)
            || (WEB3ForcedsettleorderDivDef.NOT_ENFORCEMENT.equals(
            l_institutionRow.getForcedsettleorderDiv())))
        {
            l_blnIsForcedsettleorder = false;
        }

        log.exiting(STR_METHOD_NAME);
        return l_blnIsForcedsettleorder;
    }

    /**
     * (is���v�����̗p)<BR>
     * ���v������Ђł��邩���肷��B<BR>
     * ���v������Ђ̏ꍇ�Atrue���A���v�����̗p��ЂłȂ��ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �P�j�@@�،���Ѓv���t�@@�����X�e�[�u������ȉ������S�ĂɊY�����郌�R�[�h���擾����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�،���ЃR�[�h ���@@this.�،���ЃR�[�h<BR>
     * �@@�@@�@@�v���t�@@�����X���ږ� = �v���t�@@�����X��.�O�����v�����敪<BR>
     * �@@�@@�@@���ږ��A�� = 1<BR>
     * <BR>
     * �Q�j�@@�擾���R�[�h.�v���t�@@�����X�̒l��"�O�����v�����̗p"�̏ꍇ�A<BR>
     * �@@�@@�@@true��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@��L�ȊO�A�܂��͊Y�����R�[�h�����݂��Ȃ��ꍇ�Afalse��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isDayTradeAdoption() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDayTradeAdoption()";
        log.entering(STR_METHOD_NAME);

        InstitutionPreferencesRow l_institutionPreferencesRow = null;
        //�P�j�@@�،���Ѓv���t�@@�����X�e�[�u������ȉ������S�ĂɊY�����郌�R�[�h���擾����B
        try
        {
            l_institutionPreferencesRow =
                InstitutionPreferencesDao.findRowByInstitutionIdNameNameSerialNo(
                    this.getInstitutionId(),
                    WEB3InstitutionPreferencesNameDef.FEQ_DAY_TRADE_DIV,
                    1);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�@@�擾���R�[�h.�v���t�@@�����X�̒l��"�O�����v�����̗p"�̏ꍇ�A
        //true��ԋp����B
        if (l_institutionPreferencesRow != null
            && WEB3FeqDayTradeDivDef.EXECUTE.equals(l_institutionPreferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //��L�ȊO�A�܂��͊Y�����R�[�h�����݂��Ȃ��ꍇ�Afalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is�l�b�e�B���O�̗p���)<BR>
     * �l�b�e�B���O�̗p��Ђł��邩���肷��B<BR>
     * �l�b�e�B���O�̗p��Ђ̏ꍇ�Atrue���A�l�b�e�B���O�̗p��ЂłȂ��ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �P�j�@@�،���Ѓv���t�@@�����X�e�[�u������ȉ������S�ĂɊY�����郌�R�[�h���擾����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�،���ЃR�[�h ���@@this.�،���ЃR�[�h<BR>
     * �@@�@@�@@�v���t�@@�����X���ږ� = �v���t�@@�����X��.�O���l�b�e�B���O��Ћ敪<BR>
     * �@@�@@�@@���ږ��A�� = 1<BR>
     * <BR>
     * �Q�j�@@�擾���R�[�h.�v���t�@@�����X�̒l��"�O���l�b�e�B���O�̗p"�̏ꍇ�A<BR>
     * �@@�@@�@@true��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@��L�ȊO�A�܂��͊Y�����R�[�h�����݂��Ȃ��ꍇ�Afalse��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isNettingAdoptCompany() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isNettingAdoptCompany()";
        log.entering(STR_METHOD_NAME);

        InstitutionPreferencesRow l_institutionPreferencesRow = null;
        //�P�j�@@�،���Ѓv���t�@@�����X�e�[�u������ȉ������S�ĂɊY�����郌�R�[�h���擾����B
        try
        {
            l_institutionPreferencesRow =
                InstitutionPreferencesDao.findRowByInstitutionIdNameNameSerialNo(
                    this.getInstitutionId(),
                    WEB3InstitutionPreferencesNameDef.FEQ_NETTING_DIV,
                    1);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�@@�擾���R�[�h.�v���t�@@�����X�̒l��"�O���l�b�e�B���O�̗p"�̏ꍇ�A
        //true��ԋp����B
        if (l_institutionPreferencesRow != null
            && WEB3FeqNettingDivDef.EXECUTE.equals(l_institutionPreferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //��L�ȊO�A�܂��͊Y�����R�[�h�����݂��Ȃ��ꍇ�Afalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
