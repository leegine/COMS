head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyCommonServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �莞��z���t���ʃT�[�r�XImpl(WEB3MutualFixedBuyCommonServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/26 ���G�� (���u) �V�K�쐬
                 : 2006/07/24 �h�C (���u) �d�l�ύX ���f�� No.459
Revesion History : 2008/07/09 ���g (���u) �d�l�ύX ���f�� No.607,612
Revesion History : 2008/07/31 ���g (���u) �d�l�ύX ���f�� No.621
*/
package webbroker3.mf;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesPK;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.FinInstitutionBankRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mf.data.MfFixedBuyingChangeRow;
import webbroker3.mf.data.MfFixedBuyingCondRow;
import webbroker3.mf.data.MfFixedBuyingDrawAccountRow;
import webbroker3.mf.message.WEB3MutualAccountDrawYMComparator;
import webbroker3.mf.message.WEB3MutualDisplayOrderComparator;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionUnit;
import webbroker3.mf.message.WEB3MutualProductCategoryCodeComparator;
import webbroker3.mf.message.WEB3MutualProductCodeComparator;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�莞��z���t���ʃT�[�r�XImpl)<BR>
 * �莞��z���t���ʃT�[�r�XImpl<BR>
 * 
 * @@author ���G��(���u)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyCommonServiceImpl
    implements WEB3MutualFixedBuyCommonService 
{
   /**
    * ���O���[�e�B���e�B<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyCommonServiceImpl.class);
    /**
     * (validate�莞��z���t���z)<BR>
     * �莞��z���t�Œ���z�`�F�b�N�A�P�ʋ��z�`�F�b�N���s�Ȃ��B<BR>
     * <BR>
     * �P�j�莞��z���t�Œ���z���擾����B<BR>
     * �@@�@@(*) ����.�⏕����.getBranch().get���M�莞��z���t�Œ���z()�� <BR>
     * �@@�@@�@@�߂�l���擾����B<BR> 
     * �@@�@@(*) get���M�莞��z���t�Œ���z()�̖߂�l��Double.NaN�̏ꍇ�A<BR>
     * �@@�@@�@@�f�[�^�s�����G���[���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@class: WEB3SystemLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@tag:   SYSTEM_ERROR_80006 <BR>
     * <BR>
     * �@@�P�|�P�j����.���t���z�i���X�j��null and ����.���t���z�i���X�j��0 <BR>
     * �@@�@@�@@�@@�@@�@@�̏ꍇ�͈ȉ����`�F�b�N����B <BR>
     * �@@�@@�@@�@@�@@����.���t���z�i���X�j �� �莞��z���t�Œ���z�� <BR>
     * �@@�@@�@@�@@�@@�@@�ꍇ�͗�O���X���[����B<BR> 
     *�@@�@@�@@�@@�@@�i�莞��z���t�Œ���z�G���[�j <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02481 <BR>
     * <BR>
     * �@@�P�|�Q�j����.���t���z�i�ςݑ����j��null and ����.���t���z�i�ςݑ����j��0 <BR>
     * �@@�@@�@@�@@�@@�@@�̏ꍇ�͈ȉ����`�F�b�N����B<BR>
     * �@@�@@�@@�@@�@@����.���t���z�i�ςݑ����j �� �莞��z���t�Œ���z�� <BR>
     * �@@�@@�@@�@@�@@�@@�ꍇ�͗�O���X���[����B<BR> 
     * �@@�@@�@@�@@�@@�i�莞��z���t�Œ���z�G���[�j<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02481 <BR>
     * <BR>
     * �Q�j�莞��z���t�P�ʋ��z���擾����<BR> 
     * �@@�@@(*) ����.�⏕����.getBranch().get���M�莞��z���t�P�ʋ��z()�� <BR>
     * �@@�@@�@@�߂�l���擾����B<BR> 
     * �@@�@@(*) get���M�莞��z���t�P�ʋ��z()�̖߂�l��Double.NaN�̏ꍇ�A<BR>
     * �@@�@@�@@�f�[�^�s�����G���[���X���[����B <BR>
     * <BR>
     * �@@�Q�|�P�j����.���t���z�i���X�j��null and ����.���t���z�i���X�j��0 <BR>
     * �@@�@@�@@�@@�@@�@@�̏ꍇ�͈ȉ����`�F�b�N����B <BR>
     * �@@�@@�@@�@@�@@����.���t���z�i���X�j���莞��z���t�P�ʋ��z�Ŋ���؂�Ȃ�<BR>
     * �@@�@@�@@�@@�@@�@@�ꍇ�͗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�i�莞��z���t�P�ʋ��z�G���[�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02491 <BR>
     * <BR>
     * �@@�Q�|�Q�j����.���t���z�i�ςݑ����j��null and ����.���t���z�i�ςݑ����j��0 <BR>
     * �@@�@@�@@�@@�@@�@@�̏ꍇ�͈ȉ����`�F�b�N����B <BR>
     * �@@�@@�@@�@@�@@����.���t���z�i�ςݑ����j���莞��z���t�P�ʋ��z�Ŋ���؂�Ȃ�<BR>
     * �@@�@@�@@�@@�@@�@@�ꍇ�͗�O���X���[����B <BR>
     *�@@�@@�@@�@@�@@�i�莞��z���t�P�ʋ��z�G���[�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02491 <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_strMonthlyBuyAmount - (���t���z�i���X�j)<BR>
     * ���t���z�i���X�j<BR>
     * @@param l_strIncreaseBuyAmount - (���t���z�i�ςݑ����j)<BR>
     * ���t���z�i�ςݑ����j<BR>
     * @@throws WEB3BaseException
     */
    public void validateFixedBuyAmount(
        SubAccount l_subAccount, 
        String l_strMonthlyBuyAmount,
        String l_strIncreaseBuyAmount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateFixedBuyAmount(SubAccount l_subAccount, " + 
            " String l_strMonthlyBuyAmount, String l_strIncreaseBuyAmount) ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�莞��z���t�Œ���z���擾����B
        // (*) ����.�⏕����.getBranch().get���M�莞��z���t�Œ���z()�� 
        // �@@�@@�߂�l���擾����B
        
        if (l_subAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
 
        WEB3GentradeBranch l_gentradeBranch = 
            (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch();    
        double l_dblFixedBuyMinAmount = l_gentradeBranch.getMfFixedBuyingMinAmount();
        
        //(*) get���M�莞��z���t�Œ���z()�̖߂�l��Double.NaN�̏ꍇ�A
        //�f�[�^�s�����G���[���X���[����B
        if (Double.isNaN(l_dblFixedBuyMinAmount))
        {
            log.debug("�f�[�^�s�����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
             
        //�P�|�P�j����.���t���z�i���X�j��null and ����.���t���z�i���X�j��0 
        //�@@�@@�@@�@@�@@�̏ꍇ�͈ȉ����`�F�b�N����B
        if (l_strMonthlyBuyAmount != null && !"0".equals(l_strMonthlyBuyAmount))
        {
            //����.���t���z�i���X�j �� �莞��z���t�Œ���z�� 
            //�@@�@@�@@�@@�@@�ꍇ�͗�O���X���[����B
            double l_dblMonthlyBuyAmount = Double.parseDouble(l_strMonthlyBuyAmount);
            if (l_dblMonthlyBuyAmount < l_dblFixedBuyMinAmount)
            {
                log.debug("����.���t���z�i���X�j �� �莞��z���t�Œ���z��" + 
                    "�ꍇ�͗�O���X���[����B");
                log.exiting(STR_METHOD_NAME);  
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02481,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "");
            }
        }
        
        //�P�|�Q�j����.���t���z�i�ςݑ����j��null and ����.���t���z�i�ςݑ����j��0
        // �@@�@@�@@�@@�@@�̏ꍇ�͈ȉ����`�F�b�N����B
        if (l_strIncreaseBuyAmount != null && !"0".equals(l_strIncreaseBuyAmount))
        {
            //����.���t���z�i�ςݑ����j �� �莞��z���t�Œ���z�� 
            // �@@�@@�@@�@@�@@�ꍇ�͗�O���X���[����B
            double l_dblIncreaseBuyAmount = Double.parseDouble(l_strIncreaseBuyAmount);
            if (l_dblIncreaseBuyAmount < l_dblFixedBuyMinAmount)
            {
                log.debug("����.���t���z�i�ςݑ����j �� �莞��z���t�Œ���z��" + 
                    "�ꍇ�͗�O���X���[����B");
                log.exiting(STR_METHOD_NAME);  
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02481,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "");
            }
        }
        
        //�Q�j�莞��z���t�P�ʋ��z���擾����
        //�@@�@@�@@�@@�@@(*) ����.�⏕����.getBranch().get���M�莞��z���t�P�ʋ��z()�� 
        //�@@�@@�@@�@@�@@�߂�l���擾����B
        double l_dblFixedBuyUnitAmount = l_gentradeBranch.getMfFixedBuyingUnitAmount();
               
        //(*) get���M�莞��z���t�P�ʋ��z()�̖߂�l��Double.NaN�̏ꍇ�A<BR>
        // �@@�@@�@@�f�[�^�s�����G���[���X���[����B
        if (Double.isNaN(l_dblFixedBuyUnitAmount))
        {
            log.debug("�f�[�^�s�����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�Q�|�P�j����.���t���z�i���X�j��null and ����.���t���z�i���X�j��0 
        // �@@�@@�@@�@@�@@�̏ꍇ�͈ȉ����`�F�b�N����B
        if (l_strMonthlyBuyAmount != null && !"0".equals(l_strMonthlyBuyAmount))
        {
            //����.���t���z�i���X�j���莞��z���t�P�ʋ��z�Ŋ���؂�Ȃ�
            // �@@�@@�@@�@@�@@�ꍇ�͗�O���X���[����B
            double l_dblMonthlyBuyAmount = Double.parseDouble(l_strMonthlyBuyAmount);
            if (l_dblMonthlyBuyAmount % l_dblFixedBuyUnitAmount != 0)
            {
                log.debug("����.���t���z�i���X�j���莞��z���t�P�ʋ��z" + 
                    "�Ŋ���؂�Ȃ��ꍇ�͗�O���X���[����B");
                log.exiting(STR_METHOD_NAME);  
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02491,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "");
            }
        }
        
        //�Q�|�Q�j����.���t���z�i�ςݑ����j��null and ����.���t���z�i�ςݑ����j��0 
        // �@@�@@�@@�@@�@@�̏ꍇ�͈ȉ����`�F�b�N����B       
        if (l_strIncreaseBuyAmount != null && !"0".equals(l_strIncreaseBuyAmount))
        {
            //����.���t���z�i�ςݑ����j���莞��z���t�P�ʋ��z�Ŋ���؂�Ȃ�
            // �@@�@@�@@�@@�@@�ꍇ�͗�O���X���[����B
            double l_dblIncreaseBuyAmout = Double.parseDouble(l_strIncreaseBuyAmount);
            if (l_dblIncreaseBuyAmout % l_dblFixedBuyUnitAmount != 0)
            {
                log.debug("����.���t���z�i�ςݑ����j���莞��z���t�P�ʋ��z" + 
                    "�Ŋ���؂�Ȃ��ꍇ�͗�O���X���[����B");
                log.exiting(STR_METHOD_NAME);  
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02491,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "");
            }           
        }
        log.exiting(STR_METHOD_NAME);  
    }
    
    /**
     * (validate�O���،������J��)<BR>
     * �O���،������̊J�݂̕K�v�����邩�ǂ������`�F�b�N����B<BR>
     * <BR>
     * �P�j����.�g�����M����.isFWF�i�j���R�[������B<BR>
     * �Q�j����.�g�����M����.is�O�����M�i�j���R�[������B<BR> 
     * �R�jisFWF()�̖߂�l == true �܂��́Ais�O�����M()�̖߂�l == true �̏ꍇ�A<BR>  
     * �@@�@@�ȉ������{����B <BR> 
     * <BR> 
     * �@@�@@�R�|�P�j����.�⏕����.getMainAccount()���R�[�����A�ڋq���擾����B<BR>  
     * <BR> 
     * �@@�@@�R�|�Q�j�擾�����ڋq.is�O���،������J��()���R�[�����A <BR> 
     * �@@�@@�@@�@@�@@�@@is�O���،������J��()�̖߂�l��true�̏ꍇ�Afalse��Ԃ��B<BR>  
     * �@@�@@�@@�@@�@@�@@is�O���،������J��()�̖߂�l��false�̏ꍇ�Atrue��Ԃ��B <BR> 
     * <BR> 
     * �S�j����ȊO�̏ꍇ�Afalse��Ԃ��B<BR> 
     * <BR> 
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_mfProduct - (�g�����M����)<BR>
     * �g�����M����<BR>
     * @@return boolean
     */
    public boolean validateForeignSecAccOpen(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mfProduct) 
    {
        final String STR_METHOD_NAME =
            "validateForeignSecAccOpen(SubAccount l_subAccount, " + 
            "  SubAccount l_subAccount, WEB3MutualFundProduct l_mfProduct) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_mfProduct == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
     
        boolean l_blnRetrunFlag = false;
        
        //�P�j����.�g�����M����.isFWF�i�j���R�[������B
        boolean l_blnIsFWF = l_mfProduct.isFWF();
        
        //�Q�j����.�g�����M����.is�O�����M�i�j���R�[������B
        boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();
        
        //�R�jisFWF()�̖߂�l == true �܂��́Ais�O�����M()�̖߂�l == true �̏ꍇ�A  
        //�@@�@@�ȉ������{����B
        if (l_blnIsFWF || l_blnIsForeignFund)
        {
            //�R�|�P�j����.�⏕����.getMainAccount()���R�[�����A�ڋq���擾����B
            WEB3GentradeMainAccount l_mainAccount = 
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
            
            //�R�|�Q�j�擾�����ڋq.is�O���،������J��()���R�[�����A 
            // �@@�@@�@@�@@�@@�@@is�O���،������J��()�̖߂�l��true�̏ꍇ�Afalse��Ԃ��B  
            //�@@�@@�@@�@@�@@�@@is�O���،������J��()�̖߂�l��false�̏ꍇ�Atrue��Ԃ��B
            boolean l_blnIsForeignAccountOpen = l_mainAccount.isForeignAccountOpen();
            if (l_blnIsForeignAccountOpen)
            {
                l_blnRetrunFlag = false;
            }
            else
            {
                l_blnRetrunFlag = true;
            }
        }
        
        // �S�j����ȊO�̏ꍇ�Afalse��Ԃ��B
        log.exiting(STR_METHOD_NAME);  
        return l_blnRetrunFlag;
    }
    
    /**
     * (get�莞��z���t�������X�g)<BR>
     * �莞��z���t�����̃��X�g��Ԃ��B<BR>
     * <BR>
     * �P�j�莞��z���t�����e�[�u�����������A <BR>
     * �@@�@@�I�u�W�F�N�g��List���擾����B <BR>
     * �@@�@@�m���������n <BR>
     * �@@�@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * �@@�@@�@@���X�R�[�h�@@= ����.���X�R�[�h <BR>
     * �@@�@@�@@�����R�[�h�@@= ����.�����R�[�h <BR>
     * �@@�@@�@@������:��������������null�ȊO�̏ꍇ�A���������� <BR>
     *         and ����:���������������ǉ����A����:���������l���o�C���h����B <BR>
     * <BR>
     * �Q�j�@@�莞��z���t������List�����^�[������B <BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strQueryString - (��������������)<BR>
     * ��������������<BR>
     * @@param l_objQueryValues - (���������l)<BR>
     * ���������l<BR>
     * @@return List<BR>
     * @@throws WEB3BaseException
     */
    public List getFixedBuyConditionList(
        String l_strInstitutionCode,
        String l_strBranchCode, 
        String l_strAccountCode, 
        String l_strQueryString, 
        Object[] l_objQueryValues) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getFixedBuyConditionList(String, String, String, String, Object[])";
        log.entering(STR_METHOD_NAME);
        
        List l_lisReturn = null;
        
        //�m���������n
        //    �،���ЃR�[�h = ����.�،���ЃR�[�h
        //  �@@���X�R�[�h�@@= ����.���X�R�[�h 
        //�@@�@@�����R�[�h�@@= ����.�����R�[�h 
        String l_strWhere = " institution_code = ? and branch_code = ? and account_code = ? ";
        List l_lisValue = new ArrayList();
        l_lisValue.add(l_strInstitutionCode);
        l_lisValue.add(l_strBranchCode);
        l_lisValue.add(l_strAccountCode);
        
        //����:��������������null�ȊO�̏ꍇ�A���������� 
        //and ����:���������������ǉ����A����:���������l���o�C���h����B
        if(l_strQueryString != null && l_objQueryValues != null)
        {
            l_strWhere += "and " + l_strQueryString;
            for(int i = 0; i < l_objQueryValues.length; i++)
            {
                l_lisValue.add(l_objQueryValues[i]);
            }
        }
        Object[] l_objValues = l_lisValue.toArray();
        
        //�莞��z���t�����e�[�u�����������A 
        //�I�u�W�F�N�g��List���擾����B
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturn = l_queryProcessor.doFindAllQuery(
                MfFixedBuyingCondRow.TYPE, 
                l_strWhere, l_objValues);
        }
        catch(DataNetworkException l_dnex)
           {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dnex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnex.getMessage(),
                l_dnex);
           }
        catch(DataQueryException l_dqex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //�莞��z���t������List�����^�[������B
        return l_lisReturn;
    }
    
    /**
     * (get��s�x�X��)<BR>
     * ��s���E�x�X�����擾����B <BR>
     * <BR>
     * ���Z�@@�ցi��s�j�}�X�^���������āA��s���A�x�X�����擾����B <BR>
     * <BR>
     * [��������] <BR>
     * ��s�R�[�h = �����D��s�R�[�h <BR>
     * �x�X�R�[�h = �����D�x�X�R�[�h <BR>
     * <BR>
     * �߂�l�F <BR>
     * �@@String[]��1�Ԗڂ̃C���f�b�N�X�ɂ͋�s���� <BR>
     * �@@2�Ԗڂ̃C���f�b�N�X�ɂ͎x�X�����Z�b�g����B <BR>
     * <BR>
     * @@param l_strFinInstitutionCode - (��s�R�[�h)<BR>
     * ��s�R�[�h<BR>
     * @@param l_strFinBranchCode - (�x�X�R�[�h)<BR>
     * �x�X�R�[�h<BR>
     * @@return String[] <BR>
     * @@throws WEB3BaseException
     */
    public String[] getFinBranchName(
        String l_strFinInstitutionCode,
        String l_strFinBranchCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getFinBranchName(String, String)";
        log.entering(STR_METHOD_NAME);
        
        String[] l_strReturns = new String[2];
        List l_lisRow = new ArrayList();
        
        //[��������]
        //��s�R�[�h = �����D��s�R�[�h 
        //�x�X�R�[�h = �����D�x�X�R�[�h
        String l_strCondition =  
            " fin_institution_code = ? and fin_branch_code = ? ";
        Object[] l_objConditionValues = new Object[] {
            l_strFinInstitutionCode,
            l_strFinBranchCode};
        
        try
        {
            //���Z�@@�ցi��s�j�}�X�^���������āA��s���A�x�X�����擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRow = l_queryProcessor.doFindAllQuery(
                FinInstitutionBankRow.TYPE, 
                l_strCondition, 
                l_objConditionValues);
        }
        catch(DataNetworkException l_dnex)
           {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dnex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnex.getMessage(),
                l_dnex);
           }        
        catch(DataQueryException l_dqex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        
        if(l_lisRow == null || l_lisRow.size() == 0)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���!"); 
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME
            );
        }
        
        //�߂�l�F 
        //String[]��1�Ԗڂ̃C���f�b�N�X�ɂ͋�s����
        //2�Ԗڂ̃C���f�b�N�X�ɂ͎x�X�����Z�b�g����B
        FinInstitutionBankRow l_row = (FinInstitutionBankRow)l_lisRow.get(0);
        l_strReturns[0] = l_row.getFinInstitutionName();
        l_strReturns[1] = l_row.getFinBranchName();
        
        log.exiting(STR_METHOD_NAME);
        return l_strReturns;
    }


    /**
     * (get�莞��z���t�����ύX���X�g)<BR>
     * �����̏����ɊY������莞��z���t�����ύX�̃��X�g���擾����B<BR>
     * <BR>
     * �莞��z���t�����ύX�̃��X�g��Ԃ��B<BR>
     * <BR>
     * �P�j�莞��z���t�����ύX�e�[�u�����������A<BR>
     * �@@�@@�I�u�W�F�N�g��List���擾����B<BR>
     * �@@�@@�m���������n<BR>
     * �@@�@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h�@@= ����.���X�R�[�h<BR>
     * �@@�@@�@@�����R�[�h�@@= ����.�����R�[�h<BR>
     * �@@�@@�@@�폜�t���O�@@= �u0:FALSE�v<BR>
     * <BR>
     * �@@�@@�@@������:��������������null�ȊO�̏ꍇ�A����������<BR>
     * �@@�@@�@@�@@and ����:���������������ǉ����A����:���������l���o�C���h����B<BR>
     * <BR>
     * �Q�j�@@�莞��z���t�����ύX��List�����^�[������B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strQueryString - (��������������)<BR>
     * ��������������<BR>
     * @@param l_objQueryValues - (���������l)<BR>
     * ���������l<BR>
     * @@return List<BR>
     * @@throws WEB3BaseException
     */
    public List getFixedBuyConditionChangeList(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strQueryString,
        Object[] l_objQueryValues) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getFixedBuyConditionChangeList(String, String, String, String, Object[])";
        log.entering(STR_METHOD_NAME);

        //�P�j�莞��z���t�����ύX�e�[�u�����������A�I�u�W�F�N�g��List���擾����B
        //�m���������n
        //�،���ЃR�[�h = ����.�،���ЃR�[�h
        //���X�R�[�h�@@= ����.���X�R�[�h
        //�����R�[�h�@@= ����.�����R�[�h
        //�폜�t���O�@@= �u0:FALSE�v
        //������:��������������null�ȊO�̏ꍇ�A
        //����������and ����:���������������ǉ����A����:���������l���o�C���h����B
        List l_lisMfFixedBuyingChangeRows = null;
        try
        {
            String l_strMfFixedBuyingChangeQuery =
                " institution_code = ? and branch_code = ? "
                + " and account_code = ? and delete_flag = ? ";

            List l_lisMfFixedBuyingChangeQuerys = new ArrayList();
            l_lisMfFixedBuyingChangeQuerys.add(l_strInstitutionCode);
            l_lisMfFixedBuyingChangeQuerys.add(l_strBranchCode);
            l_lisMfFixedBuyingChangeQuerys.add(l_strAccountCode);
            l_lisMfFixedBuyingChangeQuerys.add(BooleanEnum.FALSE);
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            if (!WEB3StringTypeUtility.isEmptyOrBlank(l_strQueryString))
            {
                l_strMfFixedBuyingChangeQuery +=
                     " and " + l_strQueryString;

                for (int i = 0; i < l_objQueryValues.length; i++)
                {
                    l_lisMfFixedBuyingChangeQuerys.add(l_objQueryValues[i]);
                }
            }

            Object[] l_objMfFixedBuyingChangeQuerys =
                new Object[l_lisMfFixedBuyingChangeQuerys.size()];
            l_lisMfFixedBuyingChangeQuerys.toArray(l_objMfFixedBuyingChangeQuerys);
            l_lisMfFixedBuyingChangeRows = l_queryProcessor.doFindAllQuery(
                MfFixedBuyingChangeRow.TYPE,
                l_strMfFixedBuyingChangeQuery,
                l_objMfFixedBuyingChangeQuerys);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisMfFixedBuyingChangeRows;
    }

    /**
     * (calc�K�p�J�n�N���i�Ɩ����t�j)<BR>
     * �Ɩ����t�x�[�X�̓K�p�J�n�N�����擾����B<BR>
     * <BR>
     * 1) GtlUtils.getTradingSystem().getBizDate()���R�[�����A�Ɩ����t���擾����B<BR>
     * <BR>
     * 2) �莞��z���t���ؓ��������v�Z�C���X�^���X�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈���]<BR>
     * �@@�@@�،���ЃR�[�h�F����.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h�F����.���X�R�[�h<BR>
     * <BR>
     * 3) �擾�����莞��z���t���ؓ��������v�Z�I�u�W�F�N�g.calc�ʏ���ؓ��iWEB�j���R�[��<BR>
     * �@@�@@[calc�ʏ���ؓ��iWEB�j�̈���]<BR>
     * �@@�@@�w��N���F�擾�����Ɩ����t<BR>
     * <BR>
     * 4) �擾�����Ɩ����t <= �擾����calc�ʏ���ؓ��iWEB�j�̖߂�l�̏ꍇ<BR>
     * <BR>
     * �@@�@@4-1) �擾�����Ɩ����t�̔N�������^�[������B<BR>
     * �@@�@@�@@�@@�@@�����t��1���Ƃ���B<BR>
     * <BR>
     * 5) ��L�ȊO<BR>
     * <BR>
     * �@@�@@5-1) �擾�����Ɩ����t�̔N��+1���������^�[������B<BR>
     * �@@�@@�@@�@@�@@�����t��1���Ƃ���B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@return Date<BR>
     * @@throws WEB3BaseException
     */
    public Date calcValidStartDateOrderBizdate(
        String l_strInstitutionCode,
        String l_strBranchCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcValidStartDateOrderBizdate(String, String)";
        log.entering(STR_METHOD_NAME);

        //1) GtlUtils.getTradingSystem().getBizDate()���R�[�����A�Ɩ����t���擾����B
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();

        //2) �莞��z���t���ؓ��������v�Z�C���X�^���X�𐶐�
        //�،���ЃR�[�h�F����.�،���ЃR�[�h
        //���X�R�[�h�F����.���X�R�[�h
        WEB3MutualFixedBuyCloseDateDrawDateCalc l_mutualFixedBuyCloseDateDrawDateCalc =
            new WEB3MutualFixedBuyCloseDateDrawDateCalc(l_strInstitutionCode, l_strBranchCode);

        //3) �擾�����莞��z���t���ؓ��������v�Z�I�u�W�F�N�g.calc�ʏ���ؓ��iWEB�j���R�[��
        //�w��N���F�擾�����Ɩ����t
        Date l_datUsuallyCloseDate = l_mutualFixedBuyCloseDateDrawDateCalc.calcUsuallyCloseDate(l_datBizDate);

        Date l_datReturn = null;
        //4) �擾�����Ɩ����t <= �擾����calc�ʏ���ؓ��iWEB�j�̖߂�l�̏ꍇ
        if (WEB3DateUtility.compareToDay(l_datBizDate, l_datUsuallyCloseDate) <= 0)
        {
            //4-1) �擾�����Ɩ����t�̔N�������^�[������B
            //�����t��1���Ƃ���B
            String l_strBizDateYM =
                WEB3DateUtility.formatDate(l_datBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YM);
            l_datReturn = WEB3DateUtility.getDate(l_strBizDateYM, WEB3GentradeTimeDef.DATE_FORMAT_YM);
        }
        else
        {
            //5) ��L�ȊO
            //5-1) �擾�����Ɩ����t�̔N��+1���������^�[������
            //�����t��1���Ƃ���B
            l_datBizDate = WEB3DateUtility.addMonth(l_datBizDate, 1);
            String l_strBizDateYM =
                WEB3DateUtility.formatDate(l_datBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YM);
            l_datReturn = WEB3DateUtility.getDate(l_strBizDateYM, WEB3GentradeTimeDef.DATE_FORMAT_YM);
        }

        log.exiting(STR_METHOD_NAME);
        return l_datReturn;
    }

    /**
     * (calc�K�p�J�n�N���i���ݓ����j)<BR>
     * ���ݓ����x�[�X�̓K�p�J�n�N�����擾����B<BR>
     * <BR>
     * 1) GtlUtils.getTradingSystem().getSystemTimestamp()���R�[�����A���ݓ������擾����B<BR>
     * <BR>
     * 2) �莞��z���t���ؓ��������v�Z�C���X�^���X�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈���]<BR>
     * �@@�@@�،���ЃR�[�h�F����.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h�F����.���X�R�[�h<BR>
     * <BR>
     * 3) �擾�����莞��z���t���ؓ��������v�Z�I�u�W�F�N�g.calc�ʏ���ؓ����iWEB�j���R�[��<BR>
     * �@@�@@[calc�ʏ���ؓ����iWEB�j�̈���]<BR>
     * �@@�@@�w��N���F�擾�������ݓ����̔N����<BR>
     * <BR>
     * 4) �擾�������ݓ��� <= �擾����calc�ʏ���ؓ����iWEB�j�̖߂�l�̏ꍇ<BR>
     * <BR>
     * �@@�@@4-1) �擾�������ݓ����̔N�������^�[������B<BR>
     * �@@�@@�@@�@@�@@�����t��1���Ƃ���B<BR>
     * <BR>
     * 5) ��L�ȊO<BR>
     * <BR>
     * �@@�@@5-1) �擾�������ݓ����̔N��+1���������^�[������B<BR>
     * �@@�@@�@@�@@�@@�����t��1���Ƃ���B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@return Date<BR>
     * @@throws WEB3BaseException
     */
    public Date calcValidStartDateCurrentDate(
        String l_strInstitutionCode,
        String l_strBranchCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcValidStartDateCurrentDate(String, String)";
        log.entering(STR_METHOD_NAME);

        //1) GtlUtils.getTradingSystem().getSystemTimestamp()���R�[�����A���ݓ������擾����B
        Date l_datSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();

        //2) �莞��z���t���ؓ��������v�Z�C���X�^���X�𐶐�
        //�،���ЃR�[�h�F����.�،���ЃR�[�h
        //���X�R�[�h�F����.���X�R�[�h
        WEB3MutualFixedBuyCloseDateDrawDateCalc l_mutualFixedBuyCloseDateDrawDateCalc =
            new WEB3MutualFixedBuyCloseDateDrawDateCalc(l_strInstitutionCode, l_strBranchCode);

        //3) �擾�����莞��z���t���ؓ��������v�Z�I�u�W�F�N�g.calc�ʏ���ؓ����iWEB�j���R�[��
        //�w��N���F�擾�������ݓ����̔N����
        Date l_datUsuallyCloseDateHour =
            l_mutualFixedBuyCloseDateDrawDateCalc.calcUsuallyCloseDateTime(
                WEB3DateUtility.toDay(l_datSystemTimestamp));

        Date l_datReturn = null;
        //4) �擾�������ݓ��� <= �擾����calc�ʏ���ؓ����iWEB�j�̖߂�l�̏ꍇ 
        if (WEB3DateUtility.compareToSecond(l_datSystemTimestamp, l_datUsuallyCloseDateHour) <= 0)
        {
            //4-1) �擾�������ݓ����̔N�������^�[������B
            //�����t��1���Ƃ���B
            String l_strSystemTimestampYM =
                WEB3DateUtility.formatDate(l_datSystemTimestamp, WEB3GentradeTimeDef.DATE_FORMAT_YM);
            l_datReturn =
                WEB3DateUtility.getDate(l_strSystemTimestampYM, WEB3GentradeTimeDef.DATE_FORMAT_YM);
        }
        else
        {
            //5) ��L�ȊO
            // 5-1) �擾�������ݓ����̔N��+1���������^�[������B
            //�����t��1���Ƃ���B
            l_datSystemTimestamp = WEB3DateUtility.addMonth(l_datSystemTimestamp, 1);
            String l_strSystemTimestampYM =
                WEB3DateUtility.formatDate(l_datSystemTimestamp, WEB3GentradeTimeDef.DATE_FORMAT_YM);
            l_datReturn =
                WEB3DateUtility.getDate(l_strSystemTimestampYM, WEB3GentradeTimeDef.DATE_FORMAT_YM);
        }

        log.exiting(STR_METHOD_NAME);
        return l_datReturn;
    }

    /**
     * (validate���������o�^)<BR>
     * �莞��z���t�����������o�^����Ă��邩�`�F�b�N����B<BR>
     * <BR>
     * �P�j�ȉ��̏����ŁA�莞��z���t���������e�[�u������������B<BR>
     * <BR>
     * �@@�@@�m���������n<BR>
     * �@@�@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h�@@= ����.���X�R�[�h<BR>
     * �@@�@@�@@�����R�[�h�@@= ����.�����R�[�h<BR>
     * <BR>
     * �� ���R�[�h�����݂��Ȃ��ꍇ�ɗ�O���X���[����B<BR>
     * �@@�@@�u�莞��z���t�����������o�^�G���[�v<BR>
     * �@@�@@�@@class�@@�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@�@@:�@@BUSINESS_ERROR_03099<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public void validateDrawAccountRegist(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateDrawAccountRegist(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�ȉ��̏����ŁA�莞��z���t���������e�[�u������������B
        try
        {
            String l_strQuery =
                " institution_code = ? and branch_code = ?  and account_code = ? ";
            Object[] l_objQuerys = new Object[]{
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode};
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisMfFixedBuyingDrawAccountRows = l_queryProcessor.doFindAllQuery(
                MfFixedBuyingDrawAccountRow.TYPE,
                l_strQuery,
                l_objQuerys);

            if (l_lisMfFixedBuyingDrawAccountRows.isEmpty())
            {
                //�� ���R�[�h�����݂��Ȃ��ꍇ�ɗ�O���X���[����B
                //�u�莞��z���t�����������o�^�G���[�v
                log.debug("�莞��z���t�����������o�^�G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03099,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�莞��z���t�����������o�^�G���[�B");
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (calc�ܗ^�m��������z)<BR>
     * �ܗ^�m��������z���擾����B<BR>
     * <BR>
     * 1) this.is�ܗ^�����R�[������B<BR>
     * �@@�@@[is�ܗ^���̈���]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F����.�莞��z���t����Row.�،���ЃR�[�h<BR>
     * �@@�@@�@@�w��N���F����.�莞��z���t����Row.���������N��<BR>
     * <BR>
     * 2) this.is�ܗ^����false�̏ꍇ�Anull�����^�[��<BR>
     * <BR>
     * 3) this.is�ܗ^����true�̏ꍇ<BR>
     * <BR>
     * �@@�@@3)-1) �莞��z���t�����ύX�e�[�u������������B<BR>
     * �@@�@@�m���������n<BR>
     * �@@�@@�@@�،���ЃR�[�h = ����.�莞��z���t����Row.�،���ЃR�[�h and <BR>
     * �@@�@@�@@���X�R�[�h�@@= ����.�莞��z���t����Row.���X�R�[�h and<BR>
     * �@@�@@�@@�����R�[�h�@@= ����.�莞��z���t����Row.�����R�[�h and<BR>
     * �@@�@@�@@�����R�[�h�@@= ����.�莞��z���t����Row.�����R�[�h and<BR>
     * �@@�@@�@@�K�p�J�n�N���@@= ����.�莞��z���t����Row.���������N�� and<BR>
     * �@@�@@�@@�폜�t���O = 0:FALSE<BR>
     * <BR>
     * �@@�@@3)-2)���R�[�h���擾�ł����ꍇ�A<BR>
     * �@@�@@�@@�擾�����莞��z���t�����ύXRow.�m��������t���z�i�ςݑ����j�����^�[������B<BR>
     * <BR>
     * �@@�@@3)-3)���R�[�h�����݂��Ȃ��ꍇ�Anull�����^�[������B<BR>
     * <BR>
     * @@param l_mfFixedBuyingCondRow - (�莞��z���t����Row)<BR>
     * �莞��z���t����Row<BR>
     * @@return String<BR>
     * @@throws WEB3BaseException
     */
    public String calcPrizeAndDecisioDrawAmount(
        MfFixedBuyingCondRow l_mfFixedBuyingCondRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcPrizeAndDecisioDrawAmount(MfFixedBuyingCondRow)";
        log.entering(STR_METHOD_NAME);

        if (l_mfFixedBuyingCondRow == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //1) this.is�ܗ^�����R�[������B
        //�،���ЃR�[�h�F����.�莞��z���t����Row.�،���ЃR�[�h
        //�w��N���F����.�莞��z���t����Row.���������N��
        boolean l_blnIsPrizeAndMoon = this.isPrizeAndMonth(
            l_mfFixedBuyingCondRow.getInstitutionCode(),
            l_mfFixedBuyingCondRow.getDrawDate());

        //2) this.is�ܗ^����false�̏ꍇ�Anull�����^�[��
        if (!l_blnIsPrizeAndMoon)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //3) this.is�ܗ^����true�̏ꍇ
        //3)-1) �莞��z���t�����ύX�e�[�u������������
        //�،���ЃR�[�h = ����.�莞��z���t����Row.�،���ЃR�[�h and
        //���X�R�[�h�@@= ����.�莞��z���t����Row.���X�R�[�h and
        //�����R�[�h�@@= ����.�莞��z���t����Row.�����R�[�h and
        //�����R�[�h�@@= ����.�莞��z���t����Row.�����R�[�h and
        //�K�p�J�n�N���@@= ����.�莞��z���t����Row.���������N�� and
        //�폜�t���O = 0:FALSE
        List l_lisMfFixedBuyingChangeRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            String l_strMfFixedBuyingChangeQuery =
                " institution_code = ? "
                + " and branch_code = ? "
                + " and account_code = ? "
                + " and product_code = ? "
                + " and to_char(valid_start_date, 'yyyyMM') = ? "
                + " and delete_flag = ? ";

            List l_lisMfFixedBuyingChangeQuerys = new ArrayList();
            l_lisMfFixedBuyingChangeQuerys.add(l_mfFixedBuyingCondRow.getInstitutionCode());
            l_lisMfFixedBuyingChangeQuerys.add(l_mfFixedBuyingCondRow.getBranchCode());
            l_lisMfFixedBuyingChangeQuerys.add(l_mfFixedBuyingCondRow.getAccountCode());
            l_lisMfFixedBuyingChangeQuerys.add(l_mfFixedBuyingCondRow.getProductCode());
            l_lisMfFixedBuyingChangeQuerys.add(
                WEB3DateUtility.formatDate(
                    l_mfFixedBuyingCondRow.getDrawDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YM));
            l_lisMfFixedBuyingChangeQuerys.add(BooleanEnum.FALSE.intValue() + "");

            Object[] l_objMfFixedBuyingChangeQuerys = new Object[l_lisMfFixedBuyingChangeQuerys.size()];
            l_lisMfFixedBuyingChangeQuerys.toArray(l_objMfFixedBuyingChangeQuerys);
            l_lisMfFixedBuyingChangeRows = l_queryProcessor.doFindAllQuery(
                MfFixedBuyingChangeRow.TYPE,
                l_strMfFixedBuyingChangeQuery,
                l_objMfFixedBuyingChangeQuerys);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_lisMfFixedBuyingChangeRows.isEmpty())
        {
            //3)-3)���R�[�h�����݂��Ȃ��ꍇ�Anull�����^�[������B
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //3)-2)���R�[�h���擾�ł����ꍇ�A�擾�����莞��z���t�����ύXRow.�m��������t���z�i�ςݑ����j�����^�[������B
        MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
            (MfFixedBuyingChangeRow)l_lisMfFixedBuyingChangeRows.get(0);
        if (!l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmountIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.formatNumber(
                l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount());
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (is�ܗ^��)<BR>
     * �w�肵���N�����ܗ^�����ǂ������肷��B<BR>
     * <BR>
     * 1)�@@�V�X�e���v���t�@@�����X�e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@���́i���ϐ����j�FXX_SBS_BONUS_MONTH<BR>
     * �@@�@@�@@��XX�͈���.�،���ЃR�[�h<BR>
     * <BR>
     * 2)�@@�擾�����V�X�e���v���t�@@�����XRow.�l���J���}��؂薈�ɕ������ă��[�v����B<BR>
     * <BR>
     * �@@�@@2)-1)�@@����.�w��N���̌�(MM)�ƃJ���}�ŋ�؂�ꂽ�l����v������A<BR>
     * �@@�@@�@@�@@true�����^�[������B<BR>
     * <BR>
     * 3)����ȊO�̏ꍇ�Afalse�����^�[������B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_datSelectYM - (�w��N��)<BR>
     * �w��N��<BR>
     * @@return boolean<BR>
     * @@throws WEB3BaseException
     */
    public boolean isPrizeAndMonth(
        String l_strInstitutionCode,
        Date l_datSelectYM) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isPrizeAndMonth(String, Date)";
        log.entering(STR_METHOD_NAME);
        //1) �V�X�e���v���t�@@�����X�e�[�u�����ȉ��̏����Ō�������B
        //���́i���ϐ����j�FXX_SBS_BONUS_MONTH
        //��XX�͈���.�،���ЃR�[�h
        SystemPreferencesPK l_systemPreferencesPK =
            new SystemPreferencesPK(
                l_strInstitutionCode + WEB3SystemPreferencesNameDef.SBS_BONUS_MONTH);
        try
        {
            SystemPreferencesRow l_systemPreferencesRow =
                SystemPreferencesDao.findRowByPk(l_systemPreferencesPK);

            //2) �擾�����V�X�e���v���t�@@�����XRow.�l���J���}��؂薈�ɕ������ă��[�v����B
            String l_strValue = l_systemPreferencesRow.getValue();

            //2)-1)  ����.�w��N���̌�(MM)�ƃJ���}�ŋ�؂�ꂽ�l����v������Atrue�����^�[������B
            String l_strSelectYM =
                WEB3DateUtility.formatDate(l_datSelectYM, WEB3GentradeTimeDef.DATE_FORMAT_YM);

            String[] l_strSelectYMs = l_strValue.split(",");
            String l_strSelectMonth = l_strSelectYM.substring(4, 6);

            for (int i = 0; i < l_strSelectYMs.length; i++)
            {
                if (l_strSelectYMs[i] != null
                    && l_strSelectYMs[i].equals(l_strSelectMonth))
                {
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
            }
        }
        catch (DataFindException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //3)����ȊO�̏ꍇ�Afalse�����^�[������B
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (sort�莞��z���t�����ꗗ)<BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���Ɋ�Â��ē��M�莞��z���t�����s�̃\�[�g���s���B<BR>
     * <BR>
     * �P)�@@ArrayList���쐬<BR>
     * <BR>
     * �Q)�@@Comparator�𐶐�<BR>
     * <BR>
     * �@@�E���������N��Comparator�𐶐� (����)<BR>
     * �@@�E�����\������Comparator�𐶐� (����)<BR>
     * �@@�E���M�����J�e�S���[�R�[�hComparator�𐶐� (����)<BR>
     * �@@�E�����R�[�hComparator�𐶐� (����)<BR>
     * <BR>
     * �R)Comparator��ArrayList�ɒǉ�<BR>
     * <BR>
     * �S)�@@ArrayList����Comparator�̔z����쐬<BR>
     * <BR>
     * �T)�@@Comparator�̔z�񏇂̃\�[�g����<BR>
     * (web3-common)WEB3ArraysUtility.sort(����:���M�莞��z���t�����s�AComparator[])<BR>
     * <BR>
     * �U)�@@�\�[�g���ꂽ���M�莞��z���t�����s�̔z���ԋp<BR>
     * ���~���Ɋ�Â��ē��M�莞��z���t�����s�̃\�[�g���s���B<BR>
     * @@param l_mutualFixedBuyConditionUnits - (���M�莞��z���t�����s[] )<BR>
     * ���M�莞��z���t�����s []<BR>
     * @@return WEB3MutualFixedBuyConditionUnit[]<BR>
     * @@throws WEB3BaseException
     */
    public WEB3MutualFixedBuyConditionUnit[] sortFixedBuyConditionList(
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "sortFixedBuyConditionList(WEB3MutualFixedBuyConditionUnit[])";
        log.entering(STR_METHOD_NAME);

        if (l_mutualFixedBuyConditionUnits == null
            || l_mutualFixedBuyConditionUnits.length == 0)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P)ArrayList���쐬
        ArrayList l_lisComparator = new ArrayList();

        // �Q)�@@Comparator�𐶐�
        //   �E���������N��Comparator�𐶐� (����)
        WEB3MutualAccountDrawYMComparator l_accountDrawYMComparator =
            new WEB3MutualAccountDrawYMComparator(WEB3AscDescDef.ASC);

        // �@@�E�����\������Comparator�𐶐� (����)
        WEB3MutualDisplayOrderComparator l_displayOrderComparator =
            new WEB3MutualDisplayOrderComparator(WEB3AscDescDef.ASC);

        // �@@�E���M�����J�e�S���[�R�[�hComparator�𐶐� (����)
        WEB3MutualProductCategoryCodeComparator l_productCategoryCodeComparator =
            new WEB3MutualProductCategoryCodeComparator(WEB3AscDescDef.ASC);

        // �@@�E�����R�[�hComparator�𐶐� (����)
        WEB3MutualProductCodeComparator l_productCodeComparator =
            new WEB3MutualProductCodeComparator(WEB3AscDescDef.ASC);

        // �R)Comparator��ArrayList�ɒǉ�
        l_lisComparator.add(l_accountDrawYMComparator);
        l_lisComparator.add(l_displayOrderComparator);
        l_lisComparator.add(l_productCategoryCodeComparator);
        l_lisComparator.add(l_productCodeComparator);

        // �S)�@@ArrayList����Comparator�̔z����쐬
        Comparator[] l_comparators = new Comparator[l_lisComparator.size()];
        l_lisComparator.toArray(l_comparators);

        // �T)�@@Comparator�̔z�񏇂̃\�[�g����
        // (web3-common)WEB3ArraysUtility.sort(����:���M�莞��z���t�����s�AComparator[])
        WEB3ArraysUtility.sort(l_mutualFixedBuyConditionUnits, l_comparators);

        // �U)�@@�\�[�g���ꂽ���M�莞��z���t�����s�̔z���ԋp
        log.exiting(STR_METHOD_NAME);
        return l_mutualFixedBuyConditionUnits;
    }
}
@
