head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �莞��z���t����(WEB3MutualFixedBuyCondition.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/24 �h�C (���u) �V�K�쐬 
*/
package webbroker3.mf;

import java.util.ArrayList;
import java.util.Date;
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
import webbroker3.mf.data.MfFixedBuyingCondParams;
import webbroker3.mf.data.MfFixedBuyingCondRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�莞��z���t����)<BR>
 *  �莞��z���t����
 * @@author �h�C(���u)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyCondition implements BusinessObject
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyCondition.class);
    
    /**
     * (�莞��z���t�����s)<BR>
     * �莞��z���t�����s�I�u�W�F�N�g <BR>
     */
    private MfFixedBuyingCondParams mfFixedBuyingCondParams;
    
    /**
     * (�莞��z���t����) <BR> 
     * �R���X�g���N�^<BR>
     * <BR>
     * �P�j�ȉ��̏����ŁA�莞��z���t�����e�[�u������������B  <BR>
     * <BR>
     * �@@�@@�m���������n <BR>
     * �@@�@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * �@@�@@�@@���X�R�[�h�@@= ����.���X�R�[�h <BR>
     * �@@�@@�@@�����R�[�h�@@= ����.�����R�[�h <BR>
     * �@@�@@�@@�����R�[�h�@@= ����.�����R�[�h <BR>
     * <BR>
     * �Q�j�擾���ꂽ���R�[�h��this.�莞��z���t�����s�ɃZ�b�g����B  <BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public WEB3MutualFixedBuyCondition(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountCode, 
        String l_strProductCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "WEB3MutualFixedBuyCondition(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�ȉ��̏����ŁA�莞��z���t�����e�[�u������������B
        //�m���������n 
        //�@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h 
        //�@@�@@���X�R�[�h�@@= ����.���X�R�[�h 
        //�@@�@@�����R�[�h�@@= ����.�����R�[�h 
        //�@@�@@�����R�[�h�@@= ����.�����R�[�h 
        List l_list = new ArrayList();
        String l_strCondition =  
            " institution_code = ? and branch_code = ? and account_code = ? and product_code = ? ";
        Object[] l_objConditionValue = new Object[] {
            l_strInstitutionCode, 
            l_strBranchCode, 
            l_strAccountCode, 
            l_strProductCode};
        
        try
        {
            //�莞��z���t�����e�[�u������������B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_list = l_queryProcessor.doFindAllQuery(
                MfFixedBuyingCondRow.TYPE, 
                l_strCondition, 
                l_objConditionValue);            
        }
        catch(DataNetworkException l_dnex)
           {
            log.error(STR_METHOD_NAME, l_dnex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnex.getMessage(),
                l_dnex);
           }
        catch(DataFindException l_dfex)
        {
            log.error(STR_METHOD_NAME, l_dfex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfex.getMessage(),
                l_dfex);
        }
        catch(DataQueryException l_dqex)
        {
            log.error(STR_METHOD_NAME, l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        
        if(l_list == null || l_list.size() == 0)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���!"); 
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME
            );
        }
        
        //�Q�j�擾���ꂽ���R�[�h��this.�莞��z���t�����s�ɃZ�b�g����B
        MfFixedBuyingCondRow l_row = (MfFixedBuyingCondRow)l_list.get(0);
        this.mfFixedBuyingCondParams = new MfFixedBuyingCondParams(l_row);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get���t���z�i���X�j)<BR>
     * ���t���z�i���X�j��Ԃ��B<BR>
     * this.getDataSourceObject().get���t���z�i���X�j()�̖߂�l��Ԃ��B<BR>
     * @@return String<BR>
     */
    public String getMonthlyBuyAmount()
    {
        double l_dblMonthlyBuyAmount = 
            ((MfFixedBuyingCondParams)this.getDataSourceObject()).getMonthlyBuyAmount();
        return WEB3StringTypeUtility.formatNumber(l_dblMonthlyBuyAmount);
    }
    
    /**
     * (get���t���z�i�ςݑ����j)<BR>
     * ���t���z�i�ςݑ����j��Ԃ��B <BR>
     * this.getDataSourceObject().get���t���z�i�ςݑ����j()�̖߂�l��Ԃ��B<BR>
     * @@return String<BR>
     */
    public String getIncreaseBuyAmount()
    {
        double l_dblIncreaseBuyAmount = 
            ((MfFixedBuyingCondParams)this.getDataSourceObject()).getIncreaseBuyAmount();
        return WEB3StringTypeUtility.formatNumber(l_dblIncreaseBuyAmount);
    }
    
    /**
     * (get�K�p�J�n�N��)<BR>
     * get�K�p�J�n�N��  <BR>
     * this.getDataSourceObject().get�K�p�J�n�N��()�̖߂�l��Ԃ��B<BR>
     * @@return Date <BR>
     */
    public Date getValidStartDate()
    {
        return ((MfFixedBuyingCondParams)this.getDataSourceObject()).getValidStartDate();
    }
    
    /**
     * (get���������N�� )<BR>
     * get���������N��  <BR>
     * this.getDataSourceObject().get���������N��()�̖߂�l��Ԃ��B<BR>
     * @@return Date <BR>
     */
    public Date getDrawDate()
    {
        return ((MfFixedBuyingCondParams)this.getDataSourceObject()).getDrawDate();
    }

    /**
     * BusinessObject.getDataSourceObject()<BR>
     * this.mfFixedBuyingCondParams��Ԃ��B<BR>
     * @@return Object <BR>
     */
    public Object getDataSourceObject() 
    {
        return this.mfFixedBuyingCondParams;
    }
}
@
