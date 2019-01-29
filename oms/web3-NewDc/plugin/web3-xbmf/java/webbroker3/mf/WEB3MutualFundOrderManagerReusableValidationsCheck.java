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
filename	WEB3MutualFundOrderManagerReusableValidationsCheck.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�������R���ʃ`�F�b�N�N���X(WEB3MutualFundOrderManagerReusableValidationsCheck)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/06 ���E (���u) �V�K�쐬
Revesion History : 2004/08/20 ���� (���u) ���r���[  
Revesion History : 2004/12/06 ������ (���u) �c�Ή� 
Revesion History : 2005/10/20 ��O�� (���u) �t�B�f���e�B�Ή�
Revesion History : 2006/04/05 �ʉ� (SRA) �{�ԏ�QH00120�Ή�
Revesion History : 2006/06/19 ��� (SRA) �{�ԏ�QH00126�Ή�
Revesion History : 2006/09/11 ���� �d�l�ύX�E���f��488�A495�A502�A507
Revesion History : 2006/10/25 ����� �d�l�ύX�E���f��515
Revesion History : 2006/11/07 �R���iSRA�j�����QU02934
Revesion History : 2007/01/26 �Ԑi (���u) �d�l�ύX�E���f��521
Revesion History : 2006/11/07 �R���iSRA�j�����QU02934
Revesion History : 2007/02/05 ������ (���u) �d�l�ύX���f��530
Revesion History : 2008/02/13 ���g (���u) �d�l�ύX���f��590,���f��592
*/
package webbroker3.mf; 

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3ExecStatusDef;
import webbroker3.common.define.WEB3MfRecruitMqSendDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.InstBranchProductDao;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.mf.data.MutualFundDayBalanceRow;
import webbroker3.mf.define.WEB3MFIncludeDivDef;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * �����M�������R���ʃ`�F�b�N�N���X<BR>
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3MutualFundOrderManagerReusableValidationsCheck
    extends MutualFundProductTypeOrderManagerReusableValidations
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualFundOrderManagerReusableValidationsCheck.class);
            
    /**
     * (���M�����R���ʃ`�F�b�N)<BR>
     * �f�t�H���g�R���X�g���N�^�B<BR>
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException
     * @@roseuid 40CD35430203
     */
    public WEB3MutualFundOrderManagerReusableValidationsCheck()
    {
    }
    
    /**
     * (validate�ً}��~)<BR>
     * ���Y�����̎�����ً}��~����Ă��Ȃ����`�F�b�N����B <BR>
     * <BR>
     * �P�j�@@�g�����M����������擾����B <BR>
     * <BR>
     * �@@�|�g�����M�����}�l�[�W�����擾����B<BR>
     * �@@�|�g�����M�����}�l�[�W��.get���M�������()���R�[�����Ċg�����M����������擾����B<BR>
     * �@@�@@�@@�mget���M��������ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@�،���ЁF ����.�g�����M����.getInstitution()<BR>
     * �@@�@@�@@�@@�����R�[�h�F ����.�g�����M����.getProductCode()<BR>
     * �@@�|�擾�ł��Ȃ��ꍇ�́A��O���X���[����B <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00372<BR>
     * <BR>
     * �Q�j�@@�戵�\�`�F�b�N<BR>
     * �@@�|����.�����敪���h1�F���t�h�̏ꍇ�A<BR>
     * �g�����M�������.is���t�\()�̖߂�l��false�̏ꍇ��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00373<BR>
     * �@@�|����.�����敪���h2�F���h�܂��́h4�F����h�̏ꍇ�A<BR>
     * �g�����M�������.is���\()�̖߂�l��false�̏ꍇ��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00374<BR>
     * �@@�|����.�����敪���h3�F�抷�h�̏ꍇ�A<BR>
     * �g�����M�������.is�抷�\()�̖߂�l��false�̏ꍇ��O���X���[����B<BR>
     *           class: WEB3BusinessLayerExceptiosn<BR>
     *           tag:   BUSINESS_ERROR_00375<BR>
     *   �|����.�����敪���h5�F��W�h�̏ꍇ�A<BR>
     * �g�����M�������.is��W�\()�̖߂�l��false�̏ꍇ��O���X���[����B <BR>
     *           class: WEB3BusinessLayerExceptiosn<BR>
     *           tag:   BUSINESS_ERROR_02239<BR>
     * 
     * @@param l_mutualFundProduct - �g�����M����<BR>
     * @@param l_strProcessDiv - (�����敪)<BR>
     * 1�F���t�@@2�F���@@3�F�抷�@@4�F����@@5�F��W<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40B1B47D0280
     */
    public void validateEmergencyStop(
        WEB3MutualFundProduct l_mutualFundProduct,
        String l_strProcessDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateEmergencyStop(WEB3MutualFundProduct l_mutualFundProduct,String l_strProcessDiv)";
        log.entering(STR_METHOD_NAME);
        if (l_mutualFundProduct == null || l_strProcessDiv == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�P�j�@@�g�����M����������擾����B
        //�|�g�����M�����}�l�[�W�����擾����
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
            ProductTypeEnum.MUTUAL_FUND).getProductManager();
        WEB3MutualFundTradedProduct l_mutualFundTradedProduct = null;
        //�g�����M�������
        try
        {
            log.debug("l_mutualFundProduct.getInstitution() = " + l_mutualFundProduct.getInstitution());
            log.debug("l_mutualFundProduct.getInstitution().getInstitutionId() = " + l_mutualFundProduct.getInstitution().getInstitutionId());
            log.debug("l_mutualFundProduct.getInstitution().getInstitutionCode() = " + l_mutualFundProduct.getInstitution().getInstitutionCode());
            log.debug("l_mutualFundProduct.getProductCode() = " + l_mutualFundProduct.getProductCode());
            //�|�g�����M�����}�l�[�W��.get���M�������()���R�[�����Ċg�����M����������擾����
            l_mutualFundTradedProduct =
                (WEB3MutualFundTradedProduct) l_mutualFundProductManager.getMutualFundTradedProduct(
                    l_mutualFundProduct.getInstitution(),
                    l_mutualFundProduct.getProductCode());        
        }
        catch (NotFoundException l_ex)
        {
            log.error("no found MutualFundTradedProduct");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00372,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���");
        }
        if (l_mutualFundTradedProduct == null
            || "".equals(l_mutualFundTradedProduct))
        {
            log.debug(
                "�g�����M�����}�l�[�W��.get���M�������()�ɊY������g�����M�������������܂���B"
                    + "with Institution Code = "
                    + l_mutualFundProduct.getInstitution().getInstitutionCode()
                    + " ProductCode = "
                    + l_mutualFundProduct.getProductCode());
            log.debug("l_mutualFundTradedProduct is null ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���");
        }
        //�Q�j�@@�戵�\�`�F�b�N
        //�|����.�����敪���h1�F���t�h�̏ꍇ�A<BR>
        // �g�����M�������.is���t�\()�̖߂�l��false�̏ꍇ��O���X���[����B
        if (WEB3ProcessDivDef.BUY.equals(l_strProcessDiv))
        {
            if (!l_mutualFundTradedProduct.isAcquiredPossible())
            {
                log.debug("�g�����M�������.is���t�\()�̖߂�l��false");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00373,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�g�����M�������.is���t�\()�̖߂�l��false");
            }
        }
        //�|����.�����敪���h2�F���h�܂��́h4�F����h�̏ꍇ�A<BR>
        //�g�����M�������.is���\()�̖߂�l��false�̏ꍇ��O���X���[����
        else if (WEB3ProcessDivDef.SELL.equals(l_strProcessDiv) 
            || WEB3ProcessDivDef.PURCHASE.equals(l_strProcessDiv))
        {
            if (!l_mutualFundTradedProduct.isSellPossible())
            {
                log.debug("�g�����M�������.is���\()�̖߂�l��false");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00374,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�g�����M�������.is���\()�̖߂�l��false");
            }
        }
        //�|����.�����敪���h3�F�抷�h�̏ꍇ�A<BR>
        //�g�����M�������.is�抷�\()�̖߂�l��false�̏ꍇ��O���X���[����
        else if (WEB3ProcessDivDef.SWITCHING.equals(l_strProcessDiv))
        {
            if (!l_mutualFundTradedProduct.isSwitchingPossible())
            {
                log.debug("�g�����M�������.is�抷�\()�̖߂�l��false");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00375,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�g�����M�������.is�抷�\()�̖߂�l��false");
            }
        }
        //�|����.�����敪���h5�F��W�h�̏ꍇ
        //�g�����M�������.is��W�\()�̖߂�l��false�̏ꍇ��O���X���[����B 
        else if (WEB3ProcessDivDef.RECRUIT.equals(l_strProcessDiv))
        {
            if (!l_mutualFundTradedProduct.isRecruitPossible())
            {
                log.debug("�g�����M�������.is��W�\()�̖߂�l��false");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02239,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�g�����M�������.is��W�\()�̖߂�l��false");
            }
        }
        else
        {
            log.debug("�����敪�s��1�F���t�C2�F���C3�F�抷, 4�F����, 5�F��W");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����敪�s��1�F���t�C2�F���C3�F�抷, 4�F����, 5�F��W");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate����\)<BR>
     * �w�肳�ꂽ����������\���̃`�F�b�N���s���B <BR>
     * <BR>
     * <BR>
     * �P�j�@@���M�����擾<BR>
     * �@@�|�g�����M�����}�l�[�W�����擾����B<BR>
     * <BR>
     * �@@�|�g�����M�����}�l�[�W��.getProduct()���R�[�����A�����I�u�W�F�N�g���擾����B <BR>
     * �@@�@@�mgetProduct�ɓn���p�����^�n <BR>
     * �@@�@@�@@����ID�F ���M�����P��Params.getProductId()�̖߂�l <BR>
     * �@@<BR>
     *   �|�g�����M�����}�l�[�W��.to����()���R�[�����A�g�����M�������擾����B<BR> 
     * �@@�@@�mto�����ɓn���p�����^�n <BR>
     * �@@�@@�@@����Row�F �擾���������I�u�W�F�N�g.getDataSourceObject()�̖߂�l <BR>
     * �@@�@@�擾�ł��Ȃ��ꍇ�͗�O���X���[����B <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00377<BR>
     * <BR>
     * �@@�|�����R�[�h�̎擾<BR>
     * �@@�@@�g�����M����.get�����R�[�h���R�[�����A�����R�[�h���擾����B<BR>
     * <BR>
     * <BR>
     * �Q�j�@@�����敪�i���M�j�̎擾  <BR>
     * �@@�|�g�����M�����}�l�[�W��.get�����敪�i���M�j( )���R�[�����A<BR>
     *      �����敪�i���M�j���擾����B <BR>
     * �@@�@@�mget�����敪�i���M�j�ɓn���p�����^�n <BR>
     * �@@�@@���M�����P�ʁF�����̓��M�����P�� <BR>
     * <BR>
     * <BR>
     * �R�j SONAR������͂��ꂽ�����͎���s�Ƃ���B<BR> 
�@@   *   ���M�����P��Params.get�����o�H�敪()�̖߂�l���uHOST�v�̏ꍇ�͗�O���X���[����B <BR>
     * <BR>
     * <BR>
     * �S�j�@@�ً}��~�`�F�b�N <BR>
     *  �|���M�����R���ʃ`�F�b�N.validate�ً}��~()���R�[�����A�`�F�b�N�G���[�̏ꍇ�́A<BR>
     *  ��O���X���[����B�i�ً}��~�G���[�j�B <BR>
     * �@@�mvalidate�ً}��~�ɓn���p�����^�n<BR>
     * �@@�@@�@@�g�����M�����F �P�j�Ŏ擾�����g�����M���� <BR>
     * �@@�@@�@@�����敪�F �Q�j�Ŏ擾���������敪�i���M�j <BR>
     * <BR>
     * <BR>
     * �T�j�@@�����~���ԃ`�F�b�N<BR>
     * �@@���݂̎��������Y�����̎�t���ԋ敪�^�C�v�̎�����ԂɊY�����邩�A<BR>
     * �@@�܂��ً}��~����Ă��Ȃ����A���邢�̓o�b�`�������łȂ����`�F�b�N����B<BR>
     * <BR>
     * �@@�|���M������ԊǗ�.validate������t�\()���R�[�����A�`�F�b�N�G���[�̏ꍇ�͗�O��<BR>
     *      �X���[����B�i�����~���ԃG���[�j<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00365<BR>
     * <BR>
     * <BR>
     * �U)�@@�������`�F�b�N<BR>
     * �@@���M�����P��.�������ƌ��݂̔����������������`�F�b�N����B <BR>
     * �@@�U�|1)�@@���M�����P��Params.getBizDate()���R�[�����A���M�����P�ʂ�<BR>
     *           ���������擾����B <BR>
     * <BR>
     * �U�|�Q�j�@@�Q�j�Ŏ擾���������敪�i���M�j���抷�̏ꍇ<BR>
     * <BR>
     * �U�|�Q�|�P)�@@���M������ԊǗ�.get�抷������()���R�[�����A���݂̏抷���������擾����B<BR>
     *     �@@�@@�@@�@@�@@[����]<BR>
     *                �抷�������R�[�h�F�@@�P�j�Ŏ擾���������R�[�h<BR>
     *                �抷������R�[�h�F�@@���M�����P��.�����R�[�h�i�抷��j<BR>
     * <BR>
     * �U�|�Q�|�Q)�@@���M�����P�ʂ̔������ƌ��݂̏抷���������قȂ�ꍇ�͗�O���X���[����B<BR>
     * <BR>
     *  �U�|�R�j  �U�|�Q�j�ȊO�̏ꍇ <BR>
     *�@@�@@�@@�@@�@@�@@�@@�u�抷�ȊO�̔������`�F�b�N�v <BR>
     * <BR>              
     * �@@�@@�@@�@@�@@�@@�U�|�R�|�P�j ���X�v���t�@@�����X����u���M��W�����ꊇ���M�敪�v���擾����B<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@[���X�v���t�@@�����X�̎擾����] <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���XID             �� ���M�����P��.get���XID <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�v���t�@@�����X��   �� 'mf.recruit.mq.send.div' <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�v���t�@@�����X�A�� �� 1 <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i*�j���R�[�h���擾�ł��Ȃ������ꍇ�A�u���M��W�����ꊇ���M�敪�v��"�ꊇ���M����"�Ƃ���B<BR> 
     * <BR>
     * �@@�@@�@@�@@�@@�@@�U�|�R�|�Q�j  �@@�`�B�̂����ꂩ�̏ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���M������ԊǗ�.get���M������(�������, �ꊇ�敪)���R�[�����A���������擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@[get���M�������̈���] <BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������  �F �Q�j�Ŏ擾���������敪�i���M�j (OrderTypeEnum�ɕϊ�) <BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ꊇ�敪 �F true <BR> 
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ �u�Q�j�Ŏ擾���������敪�i���M�j="��W"�v ����<BR>�@@
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�u���M��W�����ꊇ���M�敪 = "�ꊇ���M����"�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�A �u�Q�j�Ŏ擾���������敪�i���M�j="���t"�v ����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�u�擾�����g�����M�����Dis������������ = true�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�B �u�Q�j�Ŏ擾���������敪�i���M�j="���"�v ����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�u�擾�����g�����M�����Dis������������ = true�v<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��[is�������������̃p�����^] <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������  �F �Q�j�Ŏ擾���������敪�i���M�j (OrderTypeEnum�ɕϊ�) <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�U�|�R�|�R�j �@@�U�|�R�|�Q�j  �@@�`�B�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���M������ԊǗ�.get���M������(�����Ȃ�)���R�[�����A���������擾����B<BR>                             
     * <BR>
     * �@@�@@�@@�@@�@@�@@�U�|�R�|�S)�@@���M�����P�ʂ̔������Ɣ��������قȂ�ꍇ�͗�O���X���[����B<BR> 
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00378<BR>
     * <BR>
     * <BR>
     * �V)�@@����ԃ`�F�b�N<BR>
     * �@@���M�����P�ʂ̖���Ԃ�"��蒆"�܂���"����"�̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00379<BR>
     * <BR>
     * <BR>
     * �W)�@@������ԃ`�F�b�N<BR>
     * ���U�|�R�|�Q�j  �@@�̏ꍇ�́A�u�W)�@@������ԃ`�F�b�N�v���s��Ȃ��B<BR>
     * <BR>
     *  �g���K���s�\�Ȏ��ԑт̏ꍇ�A�w�肳�ꂽ�����̒�����Ԃ�"�����ρi�V�K����)"��<BR>
     *  �Ȃ��Ă��邩�`�F�b�N����B<BR> 
     *�@@���ݓ��t�������P�ʂ̔������̏ꍇ��"��t�ρi�V�K�����j"�ł��邱�Ƃ��`�F�b�N����B<BR>
     *<BR>
     *�@@�W�|�P)�@@���M������ԊǗ�.is�g���K���s()���R�[�����A�߂�l��true�̏ꍇ�͈ȍ~�̏������s���B <BR>
     *�@@�@@�@@�@@[is�g���K���s�ɓn���p�����^�n <BR>
     *�@@�@@�@@�@@�@@��������: "DEFAULT"<BR> 
     *<BR>
     *�@@�W�|�Q)�@@���ݓ��t�������P�ʂ̔������̏ꍇ�A���M�����P��Params.getOrderStatus()�̖߂�l��<BR>
     *�@@�@@�@@�@@OrderStatusEnum.��t�ρi�V�K����)�łȂ��ꍇ�A��O���X���[����B<BR> 
     *<BR>
     *�@@�W�|�R)�@@����ȊO�̏ꍇ�A���M�����P��Params.getOrderStatus()�̖߂�l��<BR>
     *�@@�@@�@@�@@OrderStatusEnum.�����ρi�V�K����)�łȂ��ꍇ�A��O���X���[����B<BR> 
     * <BR>
     * <BR>
     * �X�j �����L����ԃ`�F�b�N<BR>
     * �@@�����L����Ԃ��A"�N���[�Y"�łȂ����`�F�b�N����B<BR>
     * �@@���M�����P��Params.getOrderOpenStatus()�̖߂�l���A<BR>
     * �@@"�N���[�Y"�iOrderOpenStatusEnum.CLOSED�j�̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00382<BR>
     * �@@���N���[�Y�́A����������s�̏ꍇ�ƁA���ς̏ꍇ������<BR>
     * <BR>
     *  <BR>
     * �P�O�j�@@�ً}��~�`�F�b�N <BR>
     * �@@�Q�j�Ŏ擾���������敪�i���M�j���抷�̏ꍇ�ɁA�抷������ً̋}��~�`�F�b�N�����{<BR>
     *   ����B<BR>
     * <BR>
     * �@@�|�g�����M�����}�l�[�W��.get���M����()���R�[�����A�抷��̖����I�u�W�F�N�g���擾����B <BR>
     * �@@�@@�mget���M�����ɓn���p�����^�n<BR>
     * �@@�@@�@@�،���ЁF�⏕����.getInstitution�i�j�̖߂�l<BR> 
     * �@@�@@�@@�����R�[�h�F ���M�����P��Params.get�����R�[�h�i�抷��j()�̖߂�l <BR>
     * <BR>
     * �@@�|���M�����R���ʃ`�F�b�N.validate�ً}��~()���R�[�����A�`�F�b�N�G���[�̏ꍇ�́A<BR>
     * �@@�@@ ��O���X���[����B�i�ً}��~�G���[�j�B <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00012<BR>
     * �@@�@@�mvalidate�ً}��~�ɓn���p�����^�n <BR>
     * �@@�@@�@@�g�����M�����F �擾�����g�����M���� <BR>
     * �@@�@@�@@�����敪�F ���t<BR>
     *  <BR>
     * <BR>
     * �P�P�j�@@�抷������̎����~���ԃ`�F�b�N<BR>
     * �@@�Q�j�Ŏ擾���������敪�i���M�j���抷�̏ꍇ�ɁA�抷������̎����~���ԃ`�F�b�N��<BR>
     *   ���{����B<BR>
     * <BR>
     * �@@�|���M������ԊǗ�.reset�����R�[�h()���R�[�����A�����R�[�h���抷������̂��̂ɕύX<BR>
     *      ����B <BR>
     * �@@�@@�mreset�����R�[�h�ɓn���p�����^�n <BR>
     * �@@�@@�@@�����R�[�h�F ���M�����P��Params.get�����R�[�h�i�抷��j()�̖߂�l<BR> 
     * <BR>
     * �@@�|��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�@@�@@���M������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * �@@�|���M������ԊǗ�.validete������t�\()���R�[�����A�`�F�b�N�G���[�̏ꍇ�́A��O<BR>
     *      ���X���[����B �i�����~���ԃG���[�j <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00365<BR>
     * <BR>
     * �@@�|���M������ԊǗ�.reset�����R�[�h()���R�[�����A�����R�[�h��<BR>
     *      �P�j�Ŏ擾���������R�[�h�ɕύX����B<BR>
     * �@@�@@�mreset�����R�[�h�ɓn���p�����^�n<BR>
     * �@@�@@�@@�����R�[�h�F �P�j�Ŏ擾���������R�[�h<BR>
     * <BR>
     * �@@�|��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�@@�@@���M������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_subAccount - �⏕����ID<BR>
     * @@param l_mutualFundOrderUnit - ���M�����P�ʃI�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40B572D302E8
     */
    public void validateCancelPossible(
        SubAccount l_subAccount,
        MutualFundOrderUnit l_mutualFundOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateCancelPossible(SubAccount l_subAccount,MutualFundOrderUnit l_mutualFundOrderUnit)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_mutualFundOrderUnit == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�P�j�@@���M�����擾
        //�|�g�����M�����}�l�[�W�����擾����
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
            ProductTypeEnum.MUTUAL_FUND).getProductManager();
        if (l_mutualFundProductManager == null
            || "".equals(l_mutualFundProductManager))
        {
            log.debug("l_mutualFundProductManager is null or empty");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�|�g�����M�����}�l�[�W��.getProduct()���R�[�����A�����I�u�W�F�N�g���擾����
        MutualFundOrderUnitParams l_params =
            (MutualFundOrderUnitParams) l_mutualFundOrderUnit
                .getDataSourceObject();
        if(l_params == null)
        {   
            log.debug("l_params is null");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);     
        }
        //Product l_product = null;
        try
        {
            Product l_product =
                l_mutualFundProductManager.getProduct(l_params.getProductId());
            log.debug("l_product = " + l_product);    
            //�|�g�����M�����}�l�[�W��.to����()���R�[�����A�g�����M�������擾����

            Row l_row = (Row) l_product.getDataSourceObject(); 
            log.debug("l_row.getClass().getName() = " + l_row.getClass().getName());
            if (l_row == null)
            {
                log.debug("l_row == null");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�g�����M�����擾�ł��Ȃ��ꍇ�G���[");
            }
            //WEB3MutualFundProduct l_mutualFundProduct = null; //�g�����M����
            WEB3MutualFundProduct l_mutualFundProduct =
                (WEB3MutualFundProduct) l_mutualFundProductManager.toProduct(
                    l_row);
            //�|�����R�[�h�̎擾
            String l_strProductCode = l_mutualFundProduct.getProductCode();
            //�Q�j�@@�����敪�i���M�j�̎擾
            WEB3MutualFundOrderManager l_mutualFundOrderManager =
                (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getOrderManager();
            
            //�|�g�����M�����}�l�[�W��.get�����敪�i���M�j( )���R�[�����A�����敪�i���M�j���擾����
            String l_strMutualTradeDiv =
                l_mutualFundOrderManager.getMutualTradeDiv(
                    l_mutualFundOrderUnit);
            if(l_strMutualTradeDiv == null || "".equals(l_strMutualTradeDiv ))
            {        
                log.debug("l_strMutualTradeDiv is null or empty");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //�R�j SONAR������͂��ꂽ�����̏ꍇ
            //���M�����P��Params.get�����o�H�敪()�̖߂�l���uHOST�v�̏ꍇ�͗�O���X���[����B
            if (WEB3OrderRootDivDef.HOST.equals(l_params.getOrderRootDiv()))
            {
				log.debug("SONAR������͂��ꂽ�����̏ꍇ�A��O���X���[����");
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00155,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					"SONAR������͂��ꂽ����");
            }   

            //�S�j�@@�ً}��~�`�F�b�N
            //���M�����R���ʃ`�F�b�N.validate�ً}��~()���R�[�����A�`�F�b�N�G���[�̏ꍇ�́A
            //��O���X���[����B�i�ً}��~�G���[�j�B
            WEB3MutualFundOrderManagerReusableValidationsCheck l_mutualFundOrderManagerReusableValidationsCheck =
                new WEB3MutualFundOrderManagerReusableValidationsCheck();
            try
            {
                log.debug("l_mutualFundProduct = " + l_mutualFundProduct);
                log.debug("l_strMutualTradeDiv = " + l_strMutualTradeDiv);
                l_mutualFundOrderManagerReusableValidationsCheck.validateEmergencyStop(
                    l_mutualFundProduct,
                    l_strMutualTradeDiv);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(
                    "�M�����R���ʃ`�F�b�N.validate�ً}��~()���R�[�����A�`�F�b�N�G���[�̏ꍇ�͗�O���X���[����");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //�T�j�@@�����~���ԃ`�F�b�N
            WEB3MutualFundTradingTimeManagement.validateOrderAccept();
            
            //�U)�@@�������`�F�b�N
            //�U�|1)�@@���M�����P��Params.getBizDate()���R�[�����A���M�����P�ʂ�
            //���������擾����B <BR>
            String l_strBizDate = l_params.getBizDate();
            boolean l_blnCond = false;
            
            //�U�|�Q)  �Q�j�Ŏ擾���������敪�i���M�j���抷�̏ꍇ
            if (WEB3ProcessDivDef.SWITCHING.equals(l_strMutualTradeDiv))
            {
                //�U�|�Q�|�P)�@@���M������ԊǗ�.get�抷������()���R�[�����A���݂̏抷���������擾����B
                //�@@          [����]
                //             �抷�������R�[�h�F�@@�P�j�Ŏ擾���������R�[�h
                //             �抷������R�[�h�F�@@���M�����P��.�����R�[�h�i�抷��j
                Date l_SwtOrderBizDate =
                    WEB3MutualFundTradingTimeManagement.getSwtOrderBizDate(l_strProductCode, l_params.getSwtProductCode());
                //�U�|�Q�|�Q)�@@���M�����P�ʂ̔������ƌ��݂̏抷���������قȂ�ꍇ�͗�O���X���[����B
                if (!GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_SwtOrderBizDate).equals(l_strBizDate))
                {
                    log.debug("���M�����P�ʂ̔������ƌ��݂̔��������قȂ�ꍇ�͗�O���X���[����");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00378,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���M�����P�ʂ̔������ƌ��݂̔��������قȂ�");
                }
            }
            // �U�|�R�j  �U�|�Q�j�ȊO�̏ꍇ 
            //�u�抷�ȊO�̔������`�F�b�N�v
            else
            {
                //�U�|�R�|�P�j ���X�v���t�@@�����X����u���M��W�����ꊇ���M�敪�v���擾����B 
                //  [���X�v���t�@@�����X�̎擾����] 
                //  ���XID             �� ���M�����P��.get���XID 
                //  �v���t�@@�����X��   �� 'mf.recruit.mq.send.div' 
                //  �v���t�@@�����X�A�� �� 1 
                //�@@�i*�j���R�[�h���擾�ł��Ȃ������ꍇ�A�u���M��W�����ꊇ���M�敪�v��"�ꊇ���M����"�Ƃ���B
            	BranchPreferencesRow l_branchPreferencesRow = null;

        		try
        		{
                    l_branchPreferencesRow = 
        			    BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(l_params.getBranchId(),
        			    	WEB3BranchPreferencesNameDef.MF_RECRUIT_MQ_SEND_DIV,
                            1);
        		}
        		catch (DataQueryException l_ex)
        		{
        			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
        			log.exiting(STR_METHOD_NAME);
        			throw new WEB3SystemLayerException(
        				WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
        				this.getClass().getName() + STR_METHOD_NAME,
        				l_ex.getMessage(), 
        				l_ex);
        		}
        		catch (DataNetworkException l_ex)
        		{
        			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
        			log.exiting(STR_METHOD_NAME);
        			throw new WEB3SystemLayerException(
        				WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
        				this.getClass().getName() + STR_METHOD_NAME,
        				l_ex.getMessage(), 
        				l_ex);
        		}
        		
        		String l_strValue = "";
        		if (l_branchPreferencesRow != null)
        		{
        			l_strValue = l_branchPreferencesRow.getValue();
        		}
        		else
        		{
        			l_strValue = WEB3MfRecruitMqSendDivDef.DEFAULT;
        		}
                
                //�U�|�R�|�Q�j  �@@�`�B�̂����ꂩ�̏ꍇ�A 
                //���M������ԊǗ�.get���M������(�������, �ꊇ�敪)���R�[�����A���������擾����B
                //�@@[get���M�������̈���]
                //�@@�������  �F �Q�j�Ŏ擾���������敪�i���M�j (OrderTypeEnum�ɕϊ�)
                //�@@�ꊇ�敪 �F true
                //�@@��[is�������������̃p�����^] 
                //  �������  �F �Q�j�Ŏ擾���������敪�i���M�j (OrderTypeEnum�ɕϊ�) 
                Date l_datOrderBizDate = null;
                //�@@�@@ �u�Q�j�Ŏ擾���������敪�i���M�j="��W"�v ���@@�u���M��W�����ꊇ���M�敪 = "�ꊇ���M����"�v
                if (WEB3ProcessDivDef.RECRUIT.equals(l_strMutualTradeDiv) && 
                    WEB3MfRecruitMqSendDivDef.DEFAULT.equals(l_strValue))
                {
                    l_datOrderBizDate = 
                        WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate(OrderTypeEnum.MF_RECRUIT, true);
                    l_blnCond = true;
                }
                //�@@�A �u�Q�j�Ŏ擾���������敪�i���M�j="���t"�v ���@@�u�擾�����g�����M�����Dis������������ = true�v
                else if (WEB3ProcessDivDef.BUY.equals(l_strMutualTradeDiv) && 
                    l_mutualFundProduct.isUnitTypeProduct(OrderTypeEnum.MF_BUY))
                {
                    l_datOrderBizDate =
                        WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate(OrderTypeEnum.MF_BUY, true);
                }
                //�@@�B �u�Q�j�Ŏ擾���������敪�i���M�j="���"�v ���@@�u�擾�����g�����M�����Dis������������ = true�v
                else if (WEB3ProcessDivDef.SELL.equals(l_strMutualTradeDiv) && 
                    l_mutualFundProduct.isUnitTypeProduct(OrderTypeEnum.MF_SELL))
                {
                    l_datOrderBizDate = 
                        WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate(OrderTypeEnum.MF_SELL, true);
                        
                    //�������������̏ꍇ�̔������擾 
                    long l_lngUnitProductId = l_mutualFundProduct.getProductId();
                    if (l_lngUnitProductId == 3303910181800L || l_lngUnitProductId == 3303911181800L)
                    {
                        MutualFundProductRow l_mfProductRow = 
                            (MutualFundProductRow) l_mutualFundProduct.getDataSourceObject();
                        l_datOrderBizDate = l_mfProductRow.getSellSwtEndDate();
                    }
                }

                //�U�|�R�|�R�j �@@�U�|�R�|�Q�j  �@@�`�B�ȊO�̏ꍇ 
                //���M������ԊǗ�.get���M������(�����Ȃ�)���R�[�����A���������擾����B 
                else
                {
                    l_datOrderBizDate =
                        WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();
                }


            	//�U�|�R�|�S)�@@���M�����P�ʂ̔������ƌ��݂̔��������قȂ�ꍇ�͗�O���X���[����B 
        		if (!WEB3DateUtility.getDate(l_strBizDate, "yyyyMMdd").equals(l_datOrderBizDate))
        		{
                    log.debug("���M�����P�ʂ̔������ƌ��݂̔��������قȂ�ꍇ�͗�O���X���[����");
                    log.exiting(STR_METHOD_NAME); 
                    throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00378,
						this.getClass().getName() + "." + STR_METHOD_NAME,
                        	"���M�����P�ʂ̔������ƌ��݂̔��������قȂ�");                        
        		}
        	}
            
            
            //�V)�@@����ԃ`�F�b�N
            if (WEB3ExecStatusDef.EXECUTED_COMPLETE.equals(l_params.exec_status)
                  || WEB3ExecStatusDef.EXECUTED_IN_PROCESS.equals(l_params.exec_status))
            {
                log.debug("���M�����P�ʂ̖���Ԃ���蒆�܂��͖��ς̏ꍇ�A��O���X���[����");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00379,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���M�����P�ʂ̖���Ԃ���蒆�܂��͖���");
            }
			//�W)�@@������ԃ`�F�b�N
            //     ���U�|�R�|�Q�j  �@@�̏ꍇ�́A�u�W)�@@������ԃ`�F�b�N�v���s��Ȃ��B
			//     �g���K���s�\�Ȏ��ԑт̏ꍇ�A�w�肳�ꂽ�����̒�����Ԃ�"�����ρi�V�K����)"��
			//     �Ȃ��Ă��邩�`�F�b�N����B
			//     ���ݓ��t�������P�ʂ̔������̏ꍇ��"��t�ρi�V�K�����j"�ł��邱�Ƃ��`�F�b�N����B
			//�W�|�P)�@@���M������ԊǗ�.is�g���K���s()���R�[�����A�߂�l��true�̏ꍇ�͈ȍ~�̏������s�� 
            if (!l_blnCond)
            {
    			if (WEB3MutualFundTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT))
    			{
    				log.debug("entry::WEB3MutualFundTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT)");
    				//�W�|�Q)�@@���ݓ��t�������P�ʂ̔������̏ꍇ�A���M�����P��Params.getOrderStatus()�̖߂�l��
    				//         OrderStatusEnum.��t�ρi�V�K����)�łȂ��ꍇ�A��O���X���[����B
    				Timestamp l_tsSystemTimestamp = GtlUtils.getSystemTimestamp();
    				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    				String l_stSystemTimestamp = formatter.format(l_tsSystemTimestamp);
                    
    				if(l_stSystemTimestamp.compareTo(l_strBizDate) < 0)
    				{                   
    					if (!OrderStatusEnum.ACCEPTED.equals(l_params.getOrderStatus()))
    					{
    						log.debug(
    							"���M�����P��Params.getOrderStatus()�̖߂�l��OrderStatusEnum.��t�ρi�V�K����)�łȂ��ꍇ�A��O���X���[����B");
    						throw new WEB3BusinessLayerException(
    							WEB3ErrorCatalog.BUSINESS_ERROR_00380,
    							this.getClass().getName() + "." + STR_METHOD_NAME,
    							"���M�����P��Params.getOrderStatus()�̖߂�l��OrderStatusEnum.��t�ρi�V�K����)�łȂ�");
    					}
    				}
    				//�W�|�R)�@@����ȊO�̏ꍇ�A���M�����P��Params.getOrderStatus()�̖߂�l��
    				//         OrderStatusEnum.�����ρi�V�K����)�łȂ��ꍇ�A��O���X���[����B
    				else
    				{
    					if (!OrderStatusEnum.ORDERED.equals(l_params.getOrderStatus()))
    					{
    						log.debug(
    							"���M�����P��Params.getOrderStatus()�̖߂�l��OrderStatusEnum.�����ρi�V�K����)�łȂ��ꍇ�A��O���X���[����B");
    						throw new WEB3BusinessLayerException(
    							WEB3ErrorCatalog.BUSINESS_ERROR_00380,
    							this.getClass().getName() + "." + STR_METHOD_NAME,
    							"���M�����P��Params.getOrderStatus()�̖߂�l��OrderStatusEnum.�����ρi�V�K����)�łȂ�");
    					}
    				}
    			}
            }
            //�X�j �����L����ԃ`�F�b�N
            //�����L����Ԃ��A"�N���[�Y"�łȂ����`�F�b�N����
            if (OrderOpenStatusEnum.CLOSED.equals(l_params.getOrderOpenStatus()))
            {
                log.debug(
                    "���M�����P��Params.getOrderOpenStatus()�̖߂�l��,�N���[�Y�iOrderOpenStatusEnum.CLOSED�j�̏ꍇ�A��O���X���[����B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00382,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "���M�����P��Params.getOrderOpenStatus()�̖߂�l��,�N���[�Y�iOrderOpenStatusEnum.CLOSED�j�ł���");
            }
            //�P�O�j�@@�ً}��~�`�F�b�N
            if (WEB3ProcessDivDef.SWITCHING.equals(l_strMutualTradeDiv))
            {
                //�|�g�����M�����}�l�[�W��.get���M����()���R�[�����A�抷��̖����I�u�W�F�N�g���擾����B
                WEB3MutualFundProduct l_swtProduct = 
                    (WEB3MutualFundProduct)l_mutualFundProductManager.getMutualFundProduct(
                    l_subAccount.getInstitution(),
                    l_params.getSwtProductCode());                    
                //�|���M�����R���ʃ`�F�b�N.validate�ً}��~()���R�[�����A�`�F�b�N�G���[�̏ꍇ�͗�O���X���[����B
                try
                {
                    l_mutualFundOrderManagerReusableValidationsCheck.validateEmergencyStop(
                        l_swtProduct,WEB3ProcessDivDef.BUY);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(
                        "���M�����R���ʃ`�F�b�N.validate�ً}��~()���R�[�����A�`�F�b�N�G���[�̏ꍇ�͗�O���X���[����");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }   
            //�P�P�j�@@�抷������̎����~���ԃ`�F�b�N
            if((WEB3ProcessDivDef.SWITCHING.equals(l_strMutualTradeDiv)))
            {
                //�|���M������ԊǗ�.reset�����R�[�h()���R�[�����A�����R�[�h���抷������̂��̂ɕύX����B
                WEB3MutualFundTradingTimeManagement.resetProductCode(l_params.getSwtProductCode());
                //�|��t�����A���t���[�����Z�b�g����
                //���M������ԊǗ�.setTimestamp()���R�[������B
                WEB3MutualFundTradingTimeManagement.setTimestamp();
                
                //�|���M������ԊǗ�.validete������t�\()���R�[�����A�`�F�b�N�G���[�̏ꍇ�́A��O���X���[����B
                WEB3MutualFundTradingTimeManagement.validateOrderAccept();
                
                //�|���M������ԊǗ�.reset�����R�[�h()���R�[�����A�����R�[�h���P�j�Ŏ擾���������R�[�h�ɕύX����B
                WEB3MutualFundTradingTimeManagement.resetProductCode(l_strProductCode);
                //�|��t�����A���t���[�����Z�b�g����B
                WEB3MutualFundTradingTimeManagement.setTimestamp();
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("no found MutualFundProduct ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);        
    }
    /**
     * ���N���X�̃C���X�^���X��o�^����static���\�b�h�B<BR>
     * <BR>
     * �P�j ���M�����R���ʃ`�F�b�N.setInstance()���R�[������B<BR>
     * �@@�msetInstance�ɓn���p�����^�n<BR>
     * �@@�@@�C���X�^���X�F new ���M�����R���ʃ`�F�b�N()<BR>
     * @@roseuid 40C6A9540350
     */
    public static void register()
    {
        final String STR_METHOD_NAME = "register()";
        log.entering(STR_METHOD_NAME);
        //�P�j ���M�����R���ʃ`�F�b�N.setInstance()���R�[������
        WEB3MutualFundOrderManagerReusableValidationsCheck.setInstance(
            new WEB3MutualFundOrderManagerReusableValidationsCheck());
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (is���搿���\)<BR>
     * ���������w�肳�ꂽ�������ɔ��搿���\�����`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���搿���J�n�����擾����B<BR>
     * �@@�g�����M����.get���搿���J�n��()���R�[�����āA���搿���J�n�����擾����B<BR>
     * <BR>
     * �Q�j�@@���搿���I�������擾����B<BR>
     * �@@�g�����M����.get���搿���I����()���R�[�����āA���搿���I�������擾����B<BR>
     * <BR>
     * �R�j�@@���搿���J�n���Ɣ��搿���I�������ݒ肳��Ă��Ȃ��ꍇ��<BR>
     *  false ��Ԃ��B<BR>
     * <BR>
     * �S�j�@@���搿���J�n���A���搿���I�����A����.��������<BR>
     * YYYYMMDD�`���̕�����ɕϊ�����B<BR>
     * <BR>
     * �T�j�@@�S�j�ŕϊ����������񂪈ȉ��̏����ɍ��v���Ȃ��ꍇ�� false ��Ԃ��B<BR>
     * <BR>
     * �@@�@@���搿���J�n�� �� ������ �� ���搿���I����<BR>
     * <BR>
     * �U�j�@@�擾���ʎc���e�[�u�����������A���������ɊY������f�[�^�����o�����ꍇ�� false�A<BR>
     *      ���o���Ȃ������ꍇ�� true ��ԋp����B <BR>
     * <BR>
     * �@@[��������] <BR>
     * �@@�،���ЃR�[�h���g�����M����.�،���ЃR�[�h <BR>
     * �@@���X�R�[�h���⏕����.���XID�ɑΉ����镔�X�R�[�h�i*1�j <BR>
     * �@@�ڋq�R�[�h���⏕����.����ID�ɑΉ���������R�[�h��6���ڂ܂ł̒l�i*2�j(6���j <BR>
     * �@@�����R�[�h���g�����M����.�����R�[�h <BR>
     * �@@���搿���Z���敪��"0" <BR>
     * <BR>
     * �@@(*1)���X�R�[�h <BR>
     * �@@�@@�A�J�E���g�}�l�[�W��.getBranch�i���XID�j.getBranchCode() <BR>
     * �@@(*2)�ڋq�R�[�h <BR>
     * �@@�@@�⏕����.getMainAccount().getAccountCode()<BR>
     * <BR>
     * @@param l_datOrderBizDate - ������
     * @@param l_subAccount - �⏕����
     * @@param l_mfProduct - �g�����M����
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 40CE8CEE0151
     */
    public boolean isBuyingRequestPossible(
            Date l_datOrderBizDate,
            SubAccount l_subAccount,
            WEB3MutualFundProduct l_mfProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isBuyingRequestPossible(Date l_datOrderBizDate)";
        log.entering(STR_METHOD_NAME);

        if (l_datOrderBizDate == null 
                || l_subAccount == null
                || l_mfProduct == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�j�@@���搿���J�n�����擾����
        Timestamp l_buyClaimStartDate =
            l_mfProduct.getBuyClaimStartDate();

        //�Q�j�@@���搿���I�������擾����
        Timestamp l_buyClaimEndDate =
            l_mfProduct.getBuyClaimEndDate();

        //�R�j�@@���搿���J�n���Ɣ��搿���I�������ݒ肳��Ă��Ȃ��ꍇ
        if (l_buyClaimStartDate == null || l_buyClaimEndDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�S�j�@@���搿���J�n���A���搿���I�����A����.��������YYYYMMDD�`���̕�����ɕϊ�����
        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat();
        String l_strOrderBizDate = l_dateFormat.format(l_datOrderBizDate);
        String l_strClaimStartDate = l_dateFormat.format(l_buyClaimStartDate);
        String l_strClaimEndDate = l_dateFormat.format(l_buyClaimEndDate);

        //�T�j ���搿���J�n�� �� ������ �� ���搿���I���� ���v���Ȃ��ꍇ
        if (!(l_strOrderBizDate.compareTo(l_strClaimStartDate) >= 0
            && l_strClaimEndDate.compareTo(l_strOrderBizDate) >= 0))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        // �U�j�@@�擾���ʎc���e�[�u�����������A���������ɊY������f�[�^�����o�����ꍇ�� false�A
        //      ���o���Ȃ������ꍇ�� true ��ԋp����B 
        // �@@[��������] 
        // �@@�،���ЃR�[�h���g�����M����.�،���ЃR�[�h 
        // �@@���X�R�[�h���⏕����.���XID�ɑΉ����镔�X�R�[�h�i*1�j
        // �@@�ڋq�R�[�h���⏕����.����ID�ɑΉ���������R�[�h��6���ڂ܂ł̒l�i*2�j(6���j
        // �@@�����R�[�h���g�����M����.�����R�[�h 
        // �@@���搿���Z���敪��"0" 
        String l_strWhere = "institution_code = ? and " +
            " branch_code = ? and " +
            " account_code = ? and " +
            " product_code = ? and " +
            " include_div = ?" ;

        String l_strBranchCode = 
            l_subAccount.getMainAccount().getBranch().getBranchCode();

        Object[] l_bindVars = {
            l_mfProduct.getInstitution().getInstitutionCode(), 
            l_strBranchCode, 
            l_subAccount.getMainAccount().getAccountCode().substring(0,6), 
            l_mfProduct.getProductCode(), 
            WEB3MFIncludeDivDef.NOT_INCLUDE};
        
        List l_lisMFDayBalanceRows = null;
        try
        {            
            QueryProcessor l_queryProcessor = 
                Processors.getDefaultProcessor();
            l_lisMFDayBalanceRows = 
                l_queryProcessor.doFindAllQuery(
                    MutualFundDayBalanceRow.TYPE, 
                    l_strWhere, 
                    l_bindVars);
                    
        } 
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when search MutualFundProductCategory");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when search MutualFundProductCategory");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if(l_lisMFDayBalanceRows == null || l_lisMFDayBalanceRows.size() == 0)
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
     * (validate�S�����抷�\)<BR>
     * ���Y�ڋq���S�����E�S���抷�\�����`�F�b�N����B <BR>
     * <BR>
     * �P�j���z�w����抷�����̑��݃`�F�b�N <BR>
     * <BR>
     * �ȉ��̏����Œ����P�ʃe�[�u������������B<BR> 
     * <BR>
     * �@@[��������]<BR>
     * �@@����ID = ����.�⏕����.getAccountId() and<BR>
     * �@@�⏕����ID = ����.�⏕����.getSubAccountId() and<BR>
     * �@@����ID = ����.�g�����M����.getProductId() and <BR>
     * �@@�������ʃ^�C�v = QuantityTypeEnum.���z and <BR>
     * �@@�i������� = OrderStatusEnum.��t�ρi�V�K�����j or<BR>
     * �@@������� = OrderStatusEnum.�����ρi�V�K�����j�j and <BR>
     * �@@�ŋ敪 = ����.�ŋ敪 and<BR>
     * �@@�i�i������� = OrderTypeEnum.�����M�������� and ����� != �h���ρh) or<BR> 
     * �@@�i������� = OrderTypeEnum.�����M���抷���� and <BR>
     * �@@�i����� is null or �i����� = �h��蒆�h and ���� = �抷�������j�j�j<BR> 
     * �@@���������̏����Ō�������B<BR>
     * <BR>
     * ���R�[�h���擾�ł����ꍇ�̓`�F�b�N����=true�A�擾�ł��Ȃ������ꍇ�̓`�F�b�N����=false �Ƃ���B<BR> 
     * <BR>
     * �Q�j�P�j�̃`�F�b�N���� == true�̏ꍇ<BR>  
     * <BR>
     * �@@�@@�Q�|�P�j<BR> 
     * �@@�@@�@@�P�j�Ŏ擾���������P��.������ >= ���M������ԊǗ�.get���M������() �̏ꍇ�A<BR>
     * �@@�@@�@@�h�������z�w�蒍������v���h �̗�O���X���[����B <BR>
     * <BR>
     * �@@�@@�Q�|�Q�j<BR>
     * �@@�@@�@@�P�j�Ŏ擾���������P��.������ �����M������ԊǗ�.get���M������() �̏ꍇ�A<BR>
     * �@@�@@�@@�h����Ĕ����v���h �̗�O���X���[����B<BR>
     * <BR>
     * <BR>
     * ������<BR>
     * ���G���[���b�Z�[�W<BR>
     * <BR>
     * ����Ĕ����v���F<BR>
     *    �u��蒆�̒��������݂��邽�߁A�S����񂪂ł��܂���B����ɍēx�S�������s���Ă��������B�v<BR>
     * �������z�w�蒍������v���F<BR>
     *    �u���z�w�����̑S�����͂ł��܂���B���ɒ����ς݂̋��z�w���񒍕�������A<BR>
     *    �������͎��������w����ւ̕ύX���s������ɁA�ēx�S�������s���Ă��������B�v<BR>
     * <BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_mfProduct - �g�����M����<BR>
     * @@param l_taxType - �ŋ敪<BR>
     * @@param l_orderBizDate - ������<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40B1B47D0280
     */
    public void validateAllSellSwtPoss(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mfProduct, 
        TaxTypeEnum l_taxType,
        Date l_orderBizDate)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateAllSellSwtPoss(SubAccount, WEB3MutualFundProduct, TaxTypeEnum, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_mfProduct == null || l_taxType == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        boolean l_blnAmtCheck = false;
      
        List l_lisOrderUnit1Rows = new Vector();        
        
        MutualFundOrderUnitRow l_mfOrderUnitRow1 = null;        
        
        //�P�j���z�w����抷�����̑��݃`�F�b�N 
        
        //�ȉ��̏����Œ����P�ʃe�[�u������������B 

        //[��������] 
        //����ID = ����.�⏕����.getAccountId() and 
        //�⏕����ID = ����.�⏕����.getSubAccountId() and 
        //����ID = ����.�g�����M����.getProductId() and 
        //�������ʃ^�C�v = QuantityTypeEnum.���z and 
        //�i������� = OrderStatusEnum.��t�ρi�V�K�����j or 
        //  ������� = OrderStatusEnum.�����ρi�V�K�����j�j and 
        //�ŋ敪 = ����.�ŋ敪 and 
        //�i�i������� = OrderTypeEnum.�����M�������� and ����� != �h���ρh) or
        // �i������� = OrderTypeEnum.�����M���抷���� and
        //   �i����� is null or �i����� = �h��蒆�h and ���� = �抷�������j�j�j�j
        //���������̏����Ō�������B

        try
        {            
            String l_strWhereClause = "account_id = ? and sub_account_id = ? " +
                  "and product_id = ? " +
                  "and quantity_type = ? and (order_status = ? or order_status = ?) " +
                  "and tax_type = ? " +
                  "and ((order_type = ? and (exec_status != ? or exec_status is null)) or " +
                  "(order_type = ? and (exec_status is null or (exec_status = ? and exec_date = swt_exec_date)))) ";
            
            Object l_bindVars[] = { 
                new Long(l_subAccount.getAccountId()), 
                new Long(l_subAccount.getSubAccountId()),
                new Long(l_mfProduct.getProductId()),
                QuantityTypeEnum.AMOUNT,
                OrderStatusEnum.ACCEPTED,
                OrderStatusEnum.ORDERED,
                l_taxType,                
                OrderTypeEnum.MF_SELL,
                WEB3ExecStatusDef.EXECUTED_COMPLETE,
                OrderTypeEnum.MF_SWITCHING,
                WEB3ExecStatusDef.EXECUTED_IN_PROCESS
            };
            
            String l_strOrderBy = "biz_date";
            
            l_lisOrderUnit1Rows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    MutualFundOrderUnitRow.TYPE,
                    l_strWhereClause,
                    l_strOrderBy,
                    null,
                    l_bindVars);

            //���R�[�h���擾�ł����ꍇ�̓`�F�b�N����=true�A�擾�ł��Ȃ������ꍇ�̓`�F�b�N����=false �Ƃ���B
            
            if (l_lisOrderUnit1Rows == null || l_lisOrderUnit1Rows.size() == 0)
            {
                log.debug("�P�j�����P�ʃe�[�u�����擾�ł����ꍇ�̓`�F�b�N���� = false");
                l_blnAmtCheck = false;
            }
            else
            {
                log.debug("�P�j�����P�ʃe�[�u�����擾�ł����ꍇ�̓`�F�b�N���� = true");
                l_blnAmtCheck = true;     
                l_mfOrderUnitRow1 = (MutualFundOrderUnitRow)l_lisOrderUnit1Rows.get(0);
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�`�F�b�N���� == true�̏ꍇ
        if (l_blnAmtCheck)
        {
                //�P�j�Ŏ擾���������P��.������ >= ���M������ԊǗ�.get���M������() �̏ꍇ�A
                //�h�������z�w�蒍������v���h �̗�O���X���[����B
                Date l_datBizDate = 
                    WEB3DateUtility.getDate(l_mfOrderUnitRow1.getBizDate(), "yyyyMMdd");

                Date l_datMfBizDate = 
                    WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();

                log.debug("�����P��.������ = " + l_datBizDate);
                log.debug("���M������ԊǗ�.get���M������ = " + l_datMfBizDate);

                if (WEB3DateUtility.compareToDay(l_datBizDate, l_datMfBizDate) >= 0)
                {
                    log.debug("�������z�w�蒍������v���B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02278,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�������z�w�蒍������v���B");
                }

                //�P�j�Ŏ擾���������P��.������ < ���M������ԊǗ�.get���M������() �̏ꍇ�A
                //�h����Ĕ����v���h �̗�O���X���[����B
                else
                {
                    log.debug("����Ĕ����v��");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02279,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "����Ĕ����v���B");
                }
            }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (is���z�w����)<BR>
     * ���Y�ڋq�����݋��z�w��̉����s���Ă��邩���`�F�b�N����B <BR>
     * <BR>
     * �P�j�@@�ȉ��̏����œ��M�����P�ʃe�[�u������������B <BR>
     * �@@�@@�m���������n <BR>
     * �@@�@@�@@����ID = ����.�⏕����.getAccountId() <BR>
     * �@@�@@�@@AND �⏕����ID = ����.�⏕����.getSubAccountId() <BR>
     * �@@�@@�@@AND ����ID = ����.�g�����M����.getProductId() <BR>
     * �@@�@@�@@AND �������ʃ^�C�v = QuantityTypeEnum.���z <BR>
     * �@@�@@�@@AND (������� = OrderStatusEnum.��t�ρi�V�K�����j OR <BR>
     *           ������� = OrderStatusEnum.�����ρi�V�K�����j) <BR>
     * �@@�@@�@@AND �ŋ敪 = ����.�ŋ敪 <BR>
     * �@@�@@�@@AND�i�i������� = OrderTypeEnum.�����M�������� AND ����� != �h���ρh) OR<BR>
     *         �i������� = OrderTypeEnum.�����M���抷���� AND<BR>
     *         �i����� is null OR �i����� = �h��蒆�h AND ���� = �抷�������j�j�j) and<BR>
     * �@@�@@�@@�@@�@@�@@�@@(���M�^�C�v != �h�O��MMF�h or ���ϋ敪 != �h�O�݁h)<BR>
     * <BR>
     * �Q�j�@@�Y�����钍���̃��R�[�h�����݂���ꍇ��true���A<BR>
     * ���݂��Ȃ��ꍇ��false��ԋp����B <BR>
     * <BR>
     * @@param l_subAccunt - �⏕����<BR>
     * @@param l_mfProduct - �g�����M����<BR>
     * @@param l_taxType - �ŋ敪<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40B1B47D0280
     */
    public boolean isPriceDesignateCancelling(
        SubAccount l_subAccunt, 
        WEB3MutualFundProduct l_mfProduct, 
        TaxTypeEnum l_taxType)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isPriceDesignateCancelling(SubAccount, WEB3MutualFundProduct, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccunt == null || l_mfProduct == null || l_taxType == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        List l_lisOrderUnitRows = new Vector();        
        
        //�P�j�@@�ȉ��̏����œ��M�����P�ʃe�[�u������������B 
        //[��������] 
        //����ID = ����.�⏕����.getAccountId() and 
        //�⏕����ID = ����.�⏕����.getSubAccountId() and 
        //����ID = ����.�g�����M����.getProductId() and 
        //�������ʃ^�C�v = QuantityTypeEnum.���z and 
        //�i������� = OrderStatusEnum.��t�ρi�V�K�����j or 
        //  ������� = OrderStatusEnum.�����ρi�V�K�����j�j and 
        //�ŋ敪 = ����.�ŋ敪 and 
        //�i�i������� = OrderTypeEnum.�����M�������� and ����� != �h���ρh) or
        // �i������� = OrderTypeEnum.�����M���抷���� and
        //   �i����� is null or �i����� = �h��蒆�h and ���� = �抷�������j�j�j)
        // and (���M�^�C�v != �h�O��MMF�h or ���ϋ敪 != �h�O�݁h)

        try
        {            
            String l_strWhereClause = "account_id = ? and sub_account_id = ? " +
            "and product_id = ? " +
            "and quantity_type = ? and (order_status = ? or order_status = ?) " +
            "and tax_type = ? " +
            "and ((order_type = ? and (exec_status != ? or exec_status is null)) or " +
            "(order_type = ? and (exec_status is null or (exec_status = ? and exec_date = swt_exec_date)))) "
            + " and (fund_type <> ? or settlement_div <> ?) ";
        
	        Object l_bindVars[] = { 
	            new Long(l_subAccunt.getAccountId()), 
	            new Long(l_subAccunt.getSubAccountId()),
	            new Long(l_mfProduct.getProductId()),
	            QuantityTypeEnum.AMOUNT,
	            OrderStatusEnum.ACCEPTED,
	            OrderStatusEnum.ORDERED,
	            l_taxType,
	            OrderTypeEnum.MF_SELL,
	            WEB3ExecStatusDef.EXECUTED_COMPLETE,
	            OrderTypeEnum.MF_SWITCHING,
	            WEB3ExecStatusDef.EXECUTED_IN_PROCESS,
                MutualFundTypeEnum.FOREIGN_MMF,
                WEB3SettlementDivDef.FOREIGN_CURRENCY
	        };
            
            l_lisOrderUnitRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    MutualFundOrderUnitRow.TYPE,
                    l_strWhereClause,
                    l_bindVars);

            //�Q�j�@@�Y�����钍���̃��R�[�h�����݂���ꍇ��true���A���݂��Ȃ��ꍇ��false��ԋp����B 
            
            if (l_lisOrderUnitRows == null || l_lisOrderUnitRows.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);    
                log.debug("is���z�w���� = false");
                return  false;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("is���z�w���� = true");
                return true;                
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
    }
    
    /**
     * (is�������S��������)<BR>
     * ���z�w����i�抷�j���ɁA�T�Z���������i�ۗL���Y�����f�����j���A <BR>
     * �ۗL���Y�̉������S�����ɑ�����������𒴂��Ă��邩�`�F�b�N����B <BR>
     * ���߂��Ă���ꍇ��true�A���߂��Ă��Ȃ��ꍇ��false��ԋp����B  <BR>
     * �@@ �ۗL���Y�e�[�u�����c�����擾����B  <BR> 
     *    �mgetAsset�ɓn���p�����^�n  <BR>
     *     �A�Z�b�gID�F ����.���YID  <BR>
     * �|getAsset()��NotFoundException���X���[�����ꍇ�́A��O���X���[����B <BR> 
     * �|�擾�����ۗL���Y�I�u�W�F�N�g.getQuantity()���R�[�����Ďc�����擾����B <BR>
     * �A ��Е��X�e�[�u������A�������S�������擾����B <BR>
     *    �mfindRowByPk�ɓn���p�����^�n  <BR>
     * ���XID�F ����.�⏕�����I�u�W�F�N�g<BR>
     * .getMainAccount().getBranch().getBranchId()�̖߂�l <BR>
     * �萔�����i�R�[�h�F �h20�F�����M���h  <BR>
     * �B ���񒍕��̊T�Z�����������Z�o����B <BR>
     *   �mcalc�T�Z��n����ɓn���p�����^�n  <BR>
     *    �⏕�����F ����.�⏕�����I�u�W�F�N�g  <BR>
     *    �����F ����.�g�����M�����I�u�W�F�N�g <BR>
     *    �����i�抷��j�F ����.�����i�抷��j <BR>
     *    �����敪�F ����.�����敪  <BR>
     *    �������ʁF ����.����  <BR>
     *    �w����@@�F ����.�w����@@ <BR>
     *    ���ϕ��@@�F ����.���ϕ��@@  <BR>
     *    �������@@�F ����.�������@@  <BR>
     *    �����敪�F ����.�����敪 <BR>
     *    �����`���l���F �Z�b�V��������擾���������`���l��  <BR>
     *    �������F ����.������  <BR>
     * �C �ۗL���Y �� �擾����InstBranchProductRow.get�������S����() �^ 100 ��  <BR>
     *    �i�ۗL���Y �| ���\�c������ �{�T�Z������ <BR>
     *    ���j�@@�̏ꍇ�Atrue��ԋp����B  <BR>
     * �@@�@@����ȊO�́Afalse��ԋp����B <BR>
     *  <BR>
     * �ۗL���Y�FgetAseet().getQuantity()�̖߂�l <BR>
     * ���\�c�������F����.���\�c������ <BR>
     * �T�Z���������F<BR>
     *    calc�T�Z��n���()�̖߂�l�̊T�Z��n����I�u�W�F�N�g.�T�Z�������� <BR>
     * <BR>
     * @@param l_subAccunt - �⏕����<BR>
     * @@param l_mfProduct - �g�����M����<BR>
     * @@param l_swtProduct - �����i�抷��j<BR>
     * @@param l_strAssetId - ���YID<BR>
     * @@param l_dblSellPossQty - ���\�c������<BR>
     * @@param l_strProcessDiv - �����敪<BR>
     * @@param l_dblQuantity - ��������<BR>
     * @@param l_strSpecifyMethod - �w����@@<BR>
     * @@param l_strSettleMethod - ���ϕ��@@<BR>
     * @@param l_strRequestMethod - �������@@<BR>
     * @@param l_strAccountDiv - �����敪<BR>
     * @@param l_datBizDate - ������<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 40B1B47D0280
     */
    public boolean isSellQtyLimitRateExcess(
        SubAccount l_subAccunt, 
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundProduct l_swtProduct, 
        String l_strAssetId, 
        double l_dblSellPossQty, 
        String l_strProcessDiv, 
        double l_dblQuantity, 
        String l_strSpecifyMethod, 
        String l_strSettleMethod, 
        String l_strRequestMethod, 
        String l_strAccountDiv, 
        Date l_datBizDate)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isSellQtyLimitRateExcess(SubAccount, WEB3MutualFundProduct, " +
            "WEB3MutualFundProduct, String, double, String, double, " +
            "String, String, String, String, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccunt == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);  
        }
                
        //�@@ �ۗL���Y�e�[�u�����c�����擾����B  

        //�@@�mgetAsset�ɓn���p�����^�n  
        //�@@�@@�A�Z�b�gID�F ����.���YID  

        //�|getAsset()��NotFoundException���X���[�����ꍇ�́A��O���X���[����B  
        //�|�擾�����ۗL���Y�I�u�W�F�N�g.getQuantity()���R�[�����Ďc�����擾����B  
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundPositionManager l_mfPositionMgr =
            (WEB3MutualFundPositionManager) l_finApp.getTradingModule
                (ProductTypeEnum.MUTUAL_FUND).getPositionManager();
        
        AssetRow l_assetRow = null;
        try
        {
            Asset l_asset = l_mfPositionMgr.getAsset(Long.parseLong(l_strAssetId));
            l_assetRow = (AssetRow) l_asset.getDataSourceObject();            
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__ ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�ۗL���Y.getQuantity()
        BigDecimal l_bdAssetQuantity =
            new BigDecimal(Double.toString(l_assetRow.getQuantity()));
        log.debug("�ۗL���Y.getQuantity() = " + l_bdAssetQuantity);
        
        //�A ��Е��X�e�[�u������A�������S�������擾����B 

        //�@@�mfindRowByPk�ɓn���p�����^�n 
        //�@@�@@���XID�F ����.�⏕�����I�u�W�F�N�g.getMainAccount().getBranch().getBranchId()�̖߂�l 
        //�@@�@@�萔�����i�R�[�h�F �h20�F�����M���h
        InstBranchProductRow l_instBranchProductRow = null;
        try
        {
            l_instBranchProductRow =
	            InstBranchProductDao.findRowByPk(
	                l_subAccunt.getMainAccount().getBranch().getBranchId(),
	                WEB3CommisionProductCodeDef.MUTUAL_FUND);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
        
        //InstBranchProductRow.get�������S����() 
        BigDecimal l_bdCancelPriceRestraintRate = 
            new BigDecimal (Double.toString(l_instBranchProductRow.getCancelPriceRestraintRate()));
        
        log.debug("InstBranchProductRow.get�������S����() = " + l_bdCancelPriceRestraintRate);
        
        //�B ���񒍕��̊T�Z�����������Z�o����B 

        //�@@�mcalc�T�Z��n����ɓn���p�����^�n 
        //�@@�@@�⏕�����F ����.�⏕�����I�u�W�F�N�g 
        //�@@�@@�����F ����.�g�����M�����I�u�W�F�N�g 
        //   �����i�抷��j�F ����.�����i�抷��j 
        //�@@�@@�����敪�F ����.�����敪 
        //�@@�@@�������ʁF ����.���� 
        //�@@�@@�w����@@�F ����.�w����@@ 
        //�@@�@@���ϕ��@@�F ����.���ϕ��@@ 
        //�@@�@@�������@@�F ����.�������@@ 
        //�@@�@@�����敪�F ����.�����敪 
        //�@@�@@�����`���l���F �Z�b�V��������擾���������`���l�� 
        //�@@�@@�������F ����.������ 
        WEB3MutualFundOrderManager l_mutualFundOrderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getOrderManager();
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);  
        String l_strOrderChanel = 
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);        
        
        WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = 
            l_mutualFundOrderManager.calcEstimateDeliveryAmount(
                l_subAccunt, 
                l_mfProduct,
                l_swtProduct, 
                l_strProcessDiv, 
                l_dblQuantity, 
                l_strSpecifyMethod, 
                l_strSettleMethod, 
                l_strRequestMethod, 
                l_strAccountDiv, 
                l_strOrderChanel, 
                l_datBizDate);
        
        //�T�Z��������
        BigDecimal l_bdEstimatedQty =
            new BigDecimal(Double.toString(l_mfEstimatedPrice.getEstimatedQty()));
        //����.���\�c������
        BigDecimal l_bdSellPossQty =
            new BigDecimal(Double.toString(l_dblSellPossQty));
        log.debug("�T�Z�������� = " + l_bdEstimatedQty);
        log.debug("����.���\�c������ = " + l_bdSellPossQty);

        //�C �ۗL���Y �� �擾����InstBranchProductRow.get�������S����() �^ 100 �� 
        // �i�ۗL���Y �| ���\�c������ �{�T�Z���������j�@@�̏ꍇ�Atrue��ԋp����B 
        //�@@����ȊO�́Afalse��ԋp����B 
        
        //�@@�ۗL���Y�FgetAseet().getQuantity()�̖߂�l         
        //�@@���\�c�������F����.���\�c������         
        //�@@�T�Z���������Fcalc�T�Z��n���()�̖߂�l�̊T�Z��n����I�u�W�F�N�g.�T�Z��������
       
        BigDecimal l_bdNumber = new BigDecimal("100");
        if ((l_bdAssetQuantity.multiply(l_bdCancelPriceRestraintRate).divide(
                l_bdNumber, 10, BigDecimal.ROUND_HALF_UP)).compareTo(
                    l_bdAssetQuantity.subtract(l_bdSellPossQty).add(l_bdEstimatedQty)) < 0)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("is�������S�������� = true");
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("is�������S�������� = false");
            return false;
        }
    }

    /**
     * (validate�O��MMF��d����)<BR>
     * ���Y�ڋq�ŁA���łɊO��MMF�̒��������݂��邩�`�F�b�N����B<BR>
     * <BR>
     * �P�j����.�g�����M����.is�O��MMF() �� false�̏ꍇ�A�����𔲂���B<BR>
     * <BR>
     * �Q�j�ȉ��̏����œ��M�����P�ʃe�[�u���̌������ă��R�[�h���擾�ł����ꍇ�A<BR>
     * �@@"�O��MMF��d�����G���["�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02733<BR>
     * <BR>
     * [��������]<BR>
     * ����ID     �� ����.�⏕����.getAccountId() and<BR>
     * �⏕����ID �� ����.�⏕����.getSubAccountId() and<BR>
     * ����ID     �� ����.�g�����M����.getProductId() and<BR>
     * ������     �� ����.������ and<BR>
     * �������  �� 1�F��t�ρi�V�K�����j<BR>
     * @@param l_subAccunt - �⏕����<BR>
     * �⏕����<BR>
     * @@param l_mfProduct - �g�����M����<BR>
     * �g�����M����<BR>
     * @@param l_datBizDate - ������<BR>
     * ������<BR>
     * @@throws WEB3BaseException 
     */
    public void validateFrgnMmfDoubleOrder(
        SubAccount l_subAccunt,
        WEB3MutualFundProduct l_mfProduct,
        Date l_datBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateFrgnMmfDoubleOrder(SubAccount, WEB3MutualFundProduct, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccunt == null || l_mfProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�P�j����.�g�����M����.is�O��MMF() �� false�̏ꍇ�A�����𔲂���B
       if (!l_mfProduct.isFrgnMmf())
       {
           log.exiting(STR_METHOD_NAME);
           return;
       }

       List l_lisMutualFundOrderUnitRows = new Vector(); 
       //�Q�j�ȉ��̏����œ��M�����P�ʃe�[�u���̌������ă��R�[�h���擾�ł����ꍇ�A
       //�@@"�O��MMF��d�����G���["�̗�O���X���[����B
       //  [��������]
       //    ����ID     �� ����.�⏕����.getAccountId() and
       //    �⏕����ID �� ����.�⏕����.getSubAccountId() and
       //    ����ID     �� ����.�g�����M����.getProductId() and
       //    ������     �� ����.������ and
       //    �������  �� 1�F��t�ρi�V�K�����j
       String l_strWhereClause =
           "account_id = ? and " +
           "sub_account_id = ? and " +
           "product_id = ? and " +
           "biz_date = ? and " +
           "order_status = ?";

       Object l_bindVars[] = {
           new Long(l_subAccunt.getAccountId()),
           new Long(l_subAccunt.getSubAccountId()),
           new Long(l_mfProduct.getProductId()),
           WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd"),
           OrderStatusEnum.ACCEPTED};

       try
       {
           l_lisMutualFundOrderUnitRows =
               Processors.getDefaultProcessor().doFindAllQuery(
                  MutualFundOrderUnitRow.TYPE,
                  l_strWhereClause,
                  l_bindVars);
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

       if (l_lisMutualFundOrderUnitRows != null && l_lisMutualFundOrderUnitRows.size() != 0)
       {
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_02733,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�O��MMF��d�����G���[�B");
       }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�������������S�����\)<BR>
     * ���Y�ڋq���S�����E�S���抷�\�����`�F�b�N����B  <BR>
     * <BR>
     * �P�j���z�w����抷�����̑��݃`�F�b�N  <BR>
     * <BR>
     * �ȉ��̏����Œ����P�ʃe�[�u������������B<BR> 
     * <BR>
     * �@@[��������]<BR>
     * �@@����ID = ����.�⏕����.getAccountId() and<BR>
     * �@@�⏕����ID = ����.�⏕����.getSubAccountId() and<BR>
     * �@@����ID = ����.�g�����M����.getProductId() and <BR>
     * �@@�������ʃ^�C�v = QuantityTypeEnum.���z and <BR>
     * �@@�i������� = OrderStatusEnum.��t�ρi�V�K�����j or<BR>
     * �@@������� = OrderStatusEnum.�����ρi�V�K�����j�j and <BR>
     * �@@�ŋ敪 = ����.�ŋ敪 and<BR>
     * �@@�i�i������� = OrderTypeEnum.�����M�������� and ����� != �h���ρh) or<BR> 
     * �@@�i������� = OrderTypeEnum.�����M���抷���� and <BR>
     * �@@�i����� is null or �i����� = �h��蒆�h and ���� = �抷�������j�j�j<BR> 
     * �@@���������̏����Ō�������B<BR>
     * <BR>
     * ���R�[�h���擾�ł����ꍇ�̓`�F�b�N����=true�A�擾�ł��Ȃ������ꍇ�̓`�F�b�N����=false �Ƃ���B<BR> 
     * <BR>
     * �Q�j�P�j�̃`�F�b�N���� == true�̏ꍇ<BR>  
     * <BR>
     * �@@�@@�Q�|�P�j<BR> 
     * �@@�@@�@@�P�j�Ŏ擾���������P��.������ >= ���M�����}�X�^.���抷�I���� �̏ꍇ�A<BR>
     * �@@�@@�@@�h�������z�w�蒍������v���h �̗�O���X���[����B <BR>
     * <BR>
     * �@@�@@�Q�|�Q�j<BR>
     * �@@�@@�@@�P�j�Ŏ擾���������P��.������ �����M�����}�X�^.���抷�I���� �̏ꍇ�A<BR>
     * �@@�@@�@@�h����Ĕ����v���h �̗�O���X���[����B<BR>
     * <BR>
     * <BR>
     * ������<BR>
     * ���G���[���b�Z�[�W<BR>
     * <BR>
     * ����Ĕ����v���F<BR>
     *    �u��蒆�̒��������݂��邽�߁A�S����񂪂ł��܂���B����ɍēx�S�������s���Ă��������B�v<BR>
     * �������z�w�蒍������v���F<BR>
     *    �u���z�w�����̑S�����͂ł��܂���B���ɒ����ς݂̋��z�w���񒍕�������A<BR>
     *    �������͎��������w����ւ̕ύX���s������ɁA�ēx�S�������s���Ă��������B�v<BR>
     * <BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_mfProduct - �g�����M����<BR>
     * @@param l_taxType - �ŋ敪<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40B1B47D0280
     */
    public void validateUnitTypeProductAllSellPoss(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mfProduct, 
        TaxTypeEnum l_taxType)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateUnitTypeProductAllSellPoss(SubAccount, WEB3MutualFundProduct, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_mfProduct == null || l_taxType == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        boolean l_blnAmtCheck = false;
      
        List l_lisOrderUnit1Rows = new Vector();        
        
        MutualFundOrderUnitRow l_mfOrderUnitRow1 = null;        
        
        //�P�j���z�w����抷�����̑��݃`�F�b�N 
        
        //�ȉ��̏����Œ����P�ʃe�[�u������������B 

        //[��������] 
        //����ID = ����.�⏕����.getAccountId() and 
        //�⏕����ID = ����.�⏕����.getSubAccountId() and 
        //����ID = ����.�g�����M����.getProductId() and 
        //�������ʃ^�C�v = QuantityTypeEnum.���z and 
        //�i������� = OrderStatusEnum.��t�ρi�V�K�����j or 
        //  ������� = OrderStatusEnum.�����ρi�V�K�����j�j and 
        //�ŋ敪 = ����.�ŋ敪 and 
        //�i�i������� = OrderTypeEnum.�����M�������� and ����� != �h���ρh) or
        // �i������� = OrderTypeEnum.�����M���抷���� and
        //   �i����� is null or �i����� = �h��蒆�h and ���� = �抷�������j�j�j�j
        //���������̏����Ō�������B

        try
        {            
            String l_strWhereClause = "account_id = ? and sub_account_id = ? " +
                  "and product_id = ? " +
                  "and quantity_type = ? and (order_status = ? or order_status = ?) " +
                  "and tax_type = ? " +
                  "and ((order_type = ? and (exec_status != ? or exec_status is null)) or " +
                  "(order_type = ? and (exec_status is null or (exec_status = ? and exec_date = swt_exec_date)))) ";
            
            Object l_bindVars[] = { 
                new Long(l_subAccount.getAccountId()), 
                new Long(l_subAccount.getSubAccountId()),
                new Long(l_mfProduct.getProductId()),
                QuantityTypeEnum.AMOUNT,
                OrderStatusEnum.ACCEPTED,
                OrderStatusEnum.ORDERED,
                l_taxType,                
                OrderTypeEnum.MF_SELL,
                WEB3ExecStatusDef.EXECUTED_COMPLETE,
                OrderTypeEnum.MF_SWITCHING,
                WEB3ExecStatusDef.EXECUTED_IN_PROCESS
            };
            
            String l_strOrderBy = "biz_date";
            
            l_lisOrderUnit1Rows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    MutualFundOrderUnitRow.TYPE,
                    l_strWhereClause,
                    l_strOrderBy,
                    null,
                    l_bindVars);

            //���R�[�h���擾�ł����ꍇ�̓`�F�b�N����=true�A�擾�ł��Ȃ������ꍇ�̓`�F�b�N����=false �Ƃ���B
            
            if (l_lisOrderUnit1Rows == null || l_lisOrderUnit1Rows.size() == 0)
            {
                log.debug("�P�j�����P�ʃe�[�u�����擾�ł����ꍇ�̓`�F�b�N���� = false");
                l_blnAmtCheck = false;
            }
            else
            {
                log.debug("�P�j�����P�ʃe�[�u�����擾�ł����ꍇ�̓`�F�b�N���� = true");
                l_blnAmtCheck = true;     
                l_mfOrderUnitRow1 = (MutualFundOrderUnitRow)l_lisOrderUnit1Rows.get(0);
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�`�F�b�N���� == true�̏ꍇ
        if (l_blnAmtCheck)
        {
        	//�P�j�Ŏ擾���������P��.������ >= ���M�����}�X�^.���抷�I���� �̏ꍇ�A
                //�h�������z�w�蒍������v���h �̗�O���X���[����B
                Date l_datBizDate = 
                    WEB3DateUtility.getDate(l_mfOrderUnitRow1.getBizDate(), "yyyyMMdd");
                //���M�����}�X�^�D���抷�I�������擾����B
                MutualFundProductRow l_mfProductRow = 
                    (MutualFundProductRow) l_mfProduct.getDataSourceObject();
                Date l_datSellSwtEndDate = l_mfProductRow.getSellSwtEndDate();
                
                log.debug("�����P��.������ = " + l_datBizDate);
                log.debug("���M�����}�X�^�D���抷�I���� = " + l_datSellSwtEndDate);

                if (WEB3DateUtility.compareToDay(l_datBizDate, l_datSellSwtEndDate) >= 0)
                {
                    log.debug("�������z�w�蒍������v���B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02278,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�������z�w�蒍������v���B");
                }

                //�P�j�Ŏ擾���������P��.������ < ���M�����}�X�^.���抷�I���� �̏ꍇ�A
                //�h����Ĕ����v���h �̗�O���X���[����B
                else
                {
                    log.debug("����Ĕ����v��");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02279,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "����Ĕ����v���B");
                }
            }

        log.exiting(STR_METHOD_NAME);
    }


}
@
