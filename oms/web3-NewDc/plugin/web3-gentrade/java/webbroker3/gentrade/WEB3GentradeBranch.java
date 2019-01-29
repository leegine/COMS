head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeBranch.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���X(WEB3GentradeBranch.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 �A�C��(���u) �V�K�쐬
Revesion History : 2004/06/23 ����(���u) get�s��x�����\�� �敨�I�v�V������ǉ�
Revesion History : 2004/11/22 羐� (���u) is���̖@@���{()���\�b�h���폜
Revesion History : 2005/07/08 �Г� (���u) get�s��x�����\��()�ɊO���̃��W�b�N��ǉ�
Revesion History : 2005/08/22 �Г� (���u) validate�N����l()��ǉ�
Revesion History : 2005/08/22 �Г� (���u) validate�N�����l()��ǉ�
Revesion History : 2005/09/26 �Г� (���u) is���M�萔���v�Z()��ǉ�
Revesion History : 2005/09/26 �Г� (���u) is���M�抷�攃�t�Œ���z�`�F�b�N���{()��ǉ�
Revesion History : 2005/09/26 �Г� (���u) get���M���򒥎��S����()��ǉ�
Revesion History : 2005/10/17 �Г� (���u) is���M��񎞏o����������()��ǉ�
Revesion History : 2006/01/11 ������ (���u)�y���ʁz�d�l�ύX�Ǘ����f��No.171
Revesion History : 2006/01/17 ������ (���u)�y���ʁz�d�l�ύX�Ǘ����f��No.172
Revesion History : 2006/03/28 ������ (���u)�y���ʁz�d�l�ύX�Ǘ����f��No.182
Revesion History : 2006/06/26 �h�C (���u)�y���ʁz�d�l�ύX�Ǘ����f��No.191
Revesion History : 2006/11/17 �h�C (���u)�y���ʁz�d�l�ύX�Ǘ����f��No.215�A217
Revesion History : 2007/06/08 �L���E�ĕ� (���u)�y���ʁz�d�l�ύX�Ǘ����f��No.242
Revesion History : 2006/06/16 �h�C (���u)�y���ʁz�d�l�ύX�Ǘ����f��No.245
Revesion History : 2006/07/04 �h�C (���u)�y���ʁz�d�l�ύX�Ǘ����f��No.260
Revesion History : 2006/07/05 �h�C (���u)�y���ʁz�d�l�ύX�Ǘ����f��No.262
Revesion History : 2007/11/22 �h�C (���u)�y���ʁz�d�l�ύX�Ǘ����f��No.290�A291
Revesion History : 2008/03/12 �h�C (���u)�y���ʁz�d�l�ύX�Ǘ����f��No.320
Revesion History : 2009/02/13 ��іQ (���u)�y���ʁz�d�l�ύX�Ǘ����f��No.338
Revesion History : 2009/03/18 ��іQ (���u)�y���ʁz�d�l�ύX�Ǘ����f��No.340
Revesion History : 2010/02/23 ��іQ (���u)�y���ʁz�d�l�ύX�Ǘ����f��No.351
*/

package webbroker3.gentrade;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BranchImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BatoFlagDef;
import webbroker3.common.define.WEB3BranchNameSerialNoDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3BuyingMinimumCheckDef;
import webbroker3.common.define.WEB3CommissionCalcDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3EqtypeFinalDayWithRightDef;
import webbroker3.common.define.WEB3EveningSessionDivDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3FxMrfAccountCheckDef;
import webbroker3.common.define.WEB3IfodepositLackchargeNonManagementDef;
import webbroker3.common.define.WEB3ListTypeDef;
import webbroker3.common.define.WEB3MartCanDealtDef;
import webbroker3.common.define.WEB3PaymentOrderCreateDef;
import webbroker3.common.define.WEB3TriggerorderSucorderCarryoverDef;
import webbroker3.gentrade.data.BranchMarketDealtCondDao;
import webbroker3.gentrade.data.BranchMarketDealtCondRow;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���X)<BR>
 * <BR> 
 * �،���Ђ̕����i���X�j��\������B<BR>
 * xTrade�񋟂̍��ڂ̑��ɁA��ЁA���X�ňقȂ���i�戵�\�Ȏs��A�戵�\�Ȏ��s��
 * <BR>
 * ���Ȃǁj���g������B<BR>
 * xTrade��Branch���g�������N���X�B<BR>
 * <BR> 
 * @@author �����@@���F(SRA)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BranchImpl
 */
public class WEB3GentradeBranch
    extends BranchImpl
    implements WEB3EnforcementDef, WEB3MartCanDealtDef, WEB3ListTypeDef
{

    /**
     * �،���ЃR�[�h <BR>
     */
    private String institutionCode;

    /**
     * ���X�R�[�h <BR>
     */
    private String branchCode;

    /**
     * ���O�o�̓I�u�W�F�N�g�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeBranch.class);

    /**
     * �R���X�g���N�^�B<BR>
     * <BR> 
     * @@param l_institution �i�،��j��ЃI�u�W�F�N�g<BR>
     * @@param l_strBranchCode ���X�R�[�h<BR>
     * @@throws com.fitechlabs.xtrade.kernel.data.DataFindException 
     * �Y������f�[�^��������Ȃ��ꍇ<BR>
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException 
     * SQL�̎��s�Ɏ��s�����ꍇ<BR>
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException 
     * DB�T�[�o�Ƃ̒ʐM�Ɏ��s�����ꍇ<BR>
     * @@roseuid 403496E800C4
     */
    public WEB3GentradeBranch(
        Institution l_institution,
        String l_strBranchCode)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        super(l_institution, l_strBranchCode);
    }

    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * @@param l_lngBranchID ���XID<BR>
     * @@throws com.fitechlabs.xtrade.kernel.data.DataFindException 
     * �Y������f�[�^��������Ȃ��ꍇ<BR>
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException 
     * SQL�̎��s�Ɏ��s�����ꍇ<BR>
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException 
     * DB�T�[�o�Ƃ̒ʐM�Ɏ��s�����ꍇ<BR>
     * @@roseuid 403496E702F6
     */
    public WEB3GentradeBranch(long l_lngBranchID)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        super(l_lngBranchID);
    }

    /**
     * ���X�s��戵�����e�[�u�����������A�Y������Row�I�u�W�F�N�g��Ԃ��B<BR>
     * �،���ЃR�[�h�A���X�R�[�h�A�����̎s��R�[�h�Ō�������B<BR>
     * <BR> 
     * @@param l_strMarketCode �s��R�[�h
     * @@return BranchMarketDealtCondRow�I�u�W�F�N�g
     * @@throws WEB3BaseException 
     * ���X�s��戵�����e�[�u���̃A�N�Z�X�Ɏ��s�����ꍇ
     * @@roseuid 403496E80392
     */
    private BranchMarketDealtCondRow getBranchMarketDealtCondRow(String l_strMarketCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBranchMarketDealtCondRow(String)";
        log.entering(STR_METHOD_NAME);

        BranchMarketDealtCondRow l_row = null; //���X�s��戵����Row
        try
        {
            l_row =
                BranchMarketDealtCondDao.findRowByPk(
                    getInstitution().getInstitutionCode(),
                    getBranchCode(),
                    l_strMarketCode);
        }
        catch (DataFindException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        catch (DataQueryException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        catch (DataNetworkException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_row;
    }

    /**
     * (get�������x�P��)<BR>
     *<BR>
     * ���X���ɐݒ肷�锄�����x�P�ʂ��擾����B<BR>
     *<BR> 
     * �P�j�@@���X�s��戵�����e�[�u������<BR>
     * �@@�{�I�u�W�F�N�g�̏،���ЃR�[�h�A���X�R�[�h�A�����̎s��R�[�h�ɂ�<BR>
     * �@@�u���X�s��戵�����e�[�u���v����������B<BR>
     *<BR> 
     * �Q�j�@@���x�P�ʎ擾<BR>
     * �@@�ȉ��̒ʂ蔻�肵�A���x�P�ʒl��ԋp����B<BR>
     * 
     * �@@�|�����̏��敪���ꕔ���̏ꍇ�́h�������x�P�ʁi�ꕔ���j�h��ԋp����B<BR>
     * 
     * �@@�|�����̏��敪���񕔏��̏ꍇ�́h�������x�P�ʁi�񕔏��j�h��ԋp����B<BR>
     * 
     * �@@�|�����̏��敪����L�ȊO�̏ꍇ�́h�������x�P�ʁi�ꕔ���j�h��ԋp����B<BR>
     * <BR>
     * @@param l_strMarketCode - �s��R�[�h <BR>
     * �i�s��R�[�h�͎s���ރC���^�t�F�C�X�ɂăR�[�h��`�j <BR>
     * 
     * @@param l_strListingDivision - (���敪) <BR>
     * ������������}�X�^�ɂĒ�`����Ă�����敪�B <BR>
     * 
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4017687F00D3
     */
    public double getDealingLimitUnit(
        String l_strMarketCode,
        String l_strListingDivision)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDealingLimitUnit(String,String)";
        log.entering(STR_METHOD_NAME);

        BranchMarketDealtCondRow l_row =
            getBranchMarketDealtCondRow(l_strMarketCode);

        if (FIRST_SECTION.equals(l_strListingDivision))
        {
            // ���敪���ꕔ���̏ꍇ
            log.info("���敪���ꕔ���̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            return l_row.getLimitedUnit1stSec();
        }
        else if (SECOND_SECTION.equals(l_strListingDivision))
        {
            // ���敪���񕔏��̏ꍇ
            log.info("���敪���񕔏��̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            return l_row.getLimitedUnit2ndSec();
        }
        else
        {
            // ���敪����L�ȊO�̏ꍇ
            log.info("���敪����L�ȊO�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            return l_row.getLimitedUnit1stSec();
        }
    }
    
    /**
     * (get�������x�P��)<BR>
     *<BR>
     *���X�{�s��{�ٍϖ��ɐݒ肷�锄�����x�P�ʂ��擾����B<BR>
     *��this.get�����P��(�s��R�[�h, ���敪)�́A <BR>
     *   �u�i���X�s��ٍϕʁj�戵�����v�N���X�g�p�ŁB <BR>
     *  <BR>
     * �P�j�@@�i���X�s��ٍϕʁj�戵�I�u�W�F�N�g�擾 <BR>
     *  �i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g�𐶐�����B<BR>
     *  [�R���X�g���N�^�̈���] <BR>
     *  �،���ЃR�[�h�F �{�I�u�W�F�N�g�̏،���ЃR�[�h <BR>
     *  ���X�R�[�h�F �{�I�u�W�F�N�g�̕��X�R�[�h <BR>
     *  �s��R�[�h�F ����.�s��R�[�h <BR>
     *  �ٍϋ敪�F�@@����.�ٍϋ敪 <BR>
     *  �ٍϊ����l�F�@@����.�ٍϊ����l <BR>
     *  <BR>
     * �Q�j�@@���x�P�ʎ擾 <BR>
     *  �i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g.get�������x <BR>
     *   �P��()��ԋp����B<BR>
     *  [get�������x�P��()�̈���] <BR>
     *  ����.���敪 <BR>
     *  ����.is�V�K�� <BR>
     *  ����.is���� <BR>
     *  <BR>
     * @@param l_strMarketCode - �s��R�[�h <BR>
     * @@param l_strListingDivision - ���敪 <BR>
     * @@param l_strRepaymentDiv - �ٍϋ敪 <BR>
     * @@param l_dbRepaymentNum - �ٍϊ����l <BR> 
     * @@param l_blnIsOpenContract - is�V�K�� <BR> 
     * @@param l_blnIsBuyOrder - is���� <BR> 
     * @@return double <BR> 
     * @@throws WEB3SystemLayerException <BR> 
     */
    public double getDealingLimitUnit(
        String l_strMarketCode,
        String l_strListingDivision,
        String l_strRepaymentDiv,
        double l_dbRepaymentNum,
        boolean l_blnIsOpenContract,
        boolean l_blnIsBuyOrder) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getDealingLimitUnit(String,String,String,double,boolean,boolean)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�i���X�s��ٍϕʁj�戵�I�u�W�F�N�g�擾
        WEB3GentradeBranchMarketRepayDealtCond l_branchMarketRepayDealtCond = 
            new WEB3GentradeBranchMarketRepayDealtCond(
                this.getInstitution().getInstitutionCode(),
                this.getBranchCode(),
                l_strMarketCode,
                l_strRepaymentDiv,
                l_dbRepaymentNum
            );
        
        //�Q�j�@@���x�P�ʎ擾
        double l_dblLimitUnit = 
            l_branchMarketRepayDealtCond.getDealingLimitUnit(l_strListingDivision,l_blnIsOpenContract,l_blnIsBuyOrder);
        
        log.exiting(STR_METHOD_NAME);
        return l_dblLimitUnit;
    }
    
    
    /**
     * (is�戵�\�s��) <BR>
     * �w��s�ꂪ���Y���X�̎戵�\�s�ꂩ�𔻒肷��B <BR>
     * <BR>
     * �P�j�@@�i���X�s��ʁj�戵�I�u�W�F�N�g�擾 <BR>
     * �@@�i���X�s��ʁj�戵�����I�u�W�F�N�g�𐶐�����B <BR>
     * <BR>
     * [�R���X�g���N�^�̈���] <BR>
     * �،���ЃR�[�h�F �{�I�u�W�F�N�g�̏،���ЃR�[�h <BR>
     * ���X�R�[�h�F �{�I�u�W�F�N�g�̕��X�R�[�h <BR>
     * �s��F ����.�s��R�[�h <BR>
     * <BR>
     * �Q�j�@@�戵�\���� <BR>
     * �@@�i���X�s��ʁj�戵����.is�戵�\()��ԋp����B <BR>
     * <BR>
     * [is�戵�\ ����] <BR>
     * �����^�C�v�F ����.�����^�C�v <BR>
     * <BR>
     * @@param l_strMarketCode - �s��R�[�h <BR>
     * @@param l_productTypeEnum - �����^�C�v <BR>
     * <BR>
     * 0�F���̑��@@1�F�����@@2�F���@@3�F�����M���@@<BR>
     * 4�F�O�����@@5�F���� 6�F�敨�I�v�V���� <BR>
     * �iProductTypeEnum�ɂĒ�`�j <BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 405E652C012D
     */
    public boolean isHandlingPossibleMarket(
        String l_strMarketCode,
        ProductTypeEnum l_productTypeEnum)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isHandlingPossibleMarket(String,ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //�擾�i���X�s��ʁj�戵�I�u�W�F�N�g
        WEB3GentradeBranchMarketDealtCond l_genBranchMarketDealtCond =
            new WEB3GentradeBranchMarketDealtCond(
                getInstitution().getInstitutionCode(),
                getBranchCode(),
                l_strMarketCode);

        //�戵�\����
        boolean l_isHandingPossibleMarket =
            l_genBranchMarketDealtCond.isHandlingPossible(
                l_productTypeEnum);

        log.exiting(STR_METHOD_NAME);
        return l_isHandingPossibleMarket;
    }
    
    /**
     * (is�M�p�戵���{) <BR>
     *  <BR>
     * �w��̐M�p������A���Y���X�Ŏ戵�\���𔻒肷��B<BR>
     *  <BR>
     * �������ٍ̕ϋ敪���h�w��Ȃ��h�̏ꍇ<BR>
     *   �|this.���x�M�p���{�敪���h���{�h�A�܂��� this.��� <BR>
     *      �M�p���{�敪���h���{�h�̏ꍇ�̂݁Atrue��Ԃ��B<BR>
     *   �|�ȊO�Afalse��Ԃ��B<BR>
     *  <BR>
     * �������ٍ̕ϋ敪���h���x�M�p�h�̏ꍇ <BR>
     *   �|this.���x�M�p���{�敪���h���{�h�̏ꍇ�Atrue��Ԃ��B<BR>
     *   �|�ȊO�Afalse��Ԃ��B<BR>
     *  <BR>
     * �������ٍ̕ϋ敪���h��ʐM�p�h�̏ꍇ <BR>
     *   �|this.��ʐM�p���{�敪���h���{�h�̏ꍇ�Atrue��Ԃ��B <BR>
     *   �|�ȊO�Afalse��Ԃ��B <BR>
     *  <BR>
     * �������ٍ̕ϋ敪����L�ȊO�̏ꍇ <BR>
     *   �|��O��throw����B <BR>
     *       class    : WEB3BaseRuntimeException<BR>
     *       tag      : SYSTEM_ERROR_80017<BR>
     *  <BR>
     * @@param l_strRepaymentDiv - �ٍϋ敪 <BR>
     *   (WEB3GentradeRepaymentDivDef�ɂĒ�`) <BR>
     * @@return boolean
     */
    public boolean isMarginTradeEnforcement(String l_strRepaymentDiv) 
    {
        final String STR_METHOD_NAME =
            "isMarginTradeEnforcement(String)";
        log.entering(STR_METHOD_NAME);
        
        BranchRow l_branchRow = (BranchRow)getDataSourceObject();
        boolean l_isMarginTradeEnforcement = false;
        //�������ٍ̕ϋ敪���w��Ȃ��̏ꍇ
        if(WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentDiv))
        {
            //this.���x�M�p���{�敪���h���{�h�A�܂��� 
            //this.��ʐM�p���{�敪���h���{�h�̏ꍇ�̂݁Atrue��Ԃ�
            if(WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getMarginSysDiv())
               ||WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getMarginGenDiv()))
            {
                l_isMarginTradeEnforcement = true;
            }
            else
            {
                l_isMarginTradeEnforcement = false;
            }
        }
        //�������ٍ̕ϋ敪���h���x�M�p�h�̏ꍇ
        else if(WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentDiv))
        {
            //this.���x�M�p���{�敪���h���{�h�̏ꍇ�Atrue��Ԃ��B
            if(WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getMarginSysDiv()))
            {
                l_isMarginTradeEnforcement = true;
            }
            else
            {
                l_isMarginTradeEnforcement = false;
            }
        }
        //�����ٍ̕ϋ敪���h��ʐM�p�h�̏ꍇ
        else if(WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentDiv))
        {
            //this.��ʐM�p���{�敪���h���{�h�̏ꍇ�Atrue��Ԃ�
            if(WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getMarginGenDiv()))
            {
                l_isMarginTradeEnforcement = true;
            }
            else
            {
                l_isMarginTradeEnforcement = false;
            }
        }
        //�������ٍ̕ϋ敪����L�ȊO�̏ꍇ��O��throw����B 
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ٍ̕ϋ敪 = " + l_strRepaymentDiv);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_isMarginTradeEnforcement;
    }
    
    /**
     * (get�s��x�����\��) <BR>
     * �����̖����^�C�v�A�M�p����敪�A <BR>
     * �敨�^�I�v�V�����敪�ɊY������ <BR>
     * �s��x�����\�����擾����B <BR>
     * <BR>
     * ���@@�����i�����^�C�v == ProductTypeEnum.�����j<BR>
     * �{�I�u�W�F�N�g���ێ�����s�I�u�W�F�N�g��<BR>
     * �u�s��ǌx�����\���i�����E�M�p�j�v��ԋp����B<BR>
     * <BR>
     * ���@@�敨�I�v�V�����i�����^�C�v == ProductTypeEnum.�敨�I�v�V�����j<BR>
     * �|�敨�i�敨�^�I�v�V�����敪 == �h�敨�j�h�̏ꍇ�F<BR>
     *    �{�I�u�W�F�N�g���ێ�����s�I�u�W�F�N�g�� <BR>
     *    �u�s��ǌx�����\���i�敨�j�v��ԋp����B<BR>
     * �|�敨�i�敨�^�I�v�V�����敪 == �h�I�v�V�����j�h�̏ꍇ�F<BR>
     *    �{�I�u�W�F�N�g���ێ�����s�I�u�W�F�N�g�� <BR>
     *    �u�s��ǌx�����\���iOP�j�v��ԋp����B<BR>
     * <BR>
     * ���@@�O�������i�����^�C�v == ProductTypeEnum.�O�������j<BR>
     * �@@�|�{�I�u�W�F�N�g���ێ�����s�I�u�W�F�N�g��<BR>
     * �@@�u�s��ǌx�����\���i�O���j�v��ԋp����B<BR>
     * <BR>
     * @@param l_productTypeEnum �����^�C�v
     * @@param l_strMarginTradeDiv �M�p����敪 <BR>
     * 0�FDEFAULT�i�M�p����ȊO�j�@@1�F���x�M�p�@@2�F��ʐM�p<BR>
     * @@param l_strFutureOptionDiv (�敨�^�I�v�V�����敪) <BR>
     * �@@0�FDEFAULT�i�敨�I�v�V�����ȊO�j 1�F�敨 2�F�I�v�V���� <BR>
     * @@return long
     * @@roseuid 4064134A0157
     */
    public long getMarketMessageSuspension(
        ProductTypeEnum l_productTypeEnum,
        String l_strMarginTradeDiv,
        String l_strFutureOptionDiv)
    {
        final String STR_METHOD_NAME =
            "getMarketMessageSuspension("
                + "ProductTypeEnum ,"
                + "String, "
                + "String)";
        log.entering(STR_METHOD_NAME);

        long l_lngMarketMessageSuspension = 0;
        BranchRow l_branchRow = (BranchRow)getDataSourceObject();
        
        //�����i�����^�C�v == ProductTypeEnum.�����j
        if (l_productTypeEnum.equals(ProductTypeEnum.EQUITY)) 
        {
            //�u�s��ǌx�����\���i�����E�M�p�j�v��ԋp����
            l_lngMarketMessageSuspension = l_branchRow.getCloseWorngEquityMargin();
            
        }
        //�敨�I�v�V�����i�����^�C�v == ProductTypeEnum.�敨�I�v�V�����j
        else if (l_productTypeEnum.equals(ProductTypeEnum.IFO)) 
        {
            //�敨�i�敨�^�I�v�V�����敪 == �h�敨�j�h�̏ꍇ
            if (WEB3FuturesOptionDivDef.FUTURES.equals(l_strFutureOptionDiv))
            {
                //�u�s��ǌx�����\���i�敨�j�v��ԋp����
                l_lngMarketMessageSuspension = l_branchRow.getCloseWorngSysFuture();
            }
            //�敨�i�敨�^�I�v�V�����敪 == �h�I�v�V�����j�h�̏ꍇ
            else if (WEB3FuturesOptionDivDef.OPTION.equals(l_strFutureOptionDiv)) 
            {
                //�u�s��ǌx�����\���iOP�j�v��ԋp����
                l_lngMarketMessageSuspension = l_branchRow.getCloseWorngOption();
            }
            else
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�敨�^�I�v�V�����敪 = " + l_strFutureOptionDiv);
            }
        }
        //�O�������i�����^�C�v == ProductTypeEnum.�O�������j
        else if (ProductTypeEnum.FOREIGN_EQUITY.equals(l_productTypeEnum)) 
        {
            
            //�u�s��ǌx�����\���i�O���j�v��ԋp����B
            l_lngMarketMessageSuspension = l_branchRow.getCloseWorngFeq();
        }

        log.exiting(STR_METHOD_NAME);
        return l_lngMarketMessageSuspension;

    }
    
    /**
     * (is�d�q�����{) <BR>
     *  <BR>
     * ���Y���X���d�q���T�[�r�X�����{���Ă��邩�ǂ����𔻒肷��B<BR>
     * �P�j�u��Е��X���i�e�[�u���v���ȉ��̏����Ō�������B<BR>
     *   ���XID��this.getBranchId( )�̖߂�l�@@and <BR>
     *   �萔�����i�R�[�h������.�萔�����i�R�[�h�@@and <BR>
     *   �d�q�����{FLAG���d�q�����{���� <BR>
     *  <BR>
     * �Q�j�������ʁ�0���̏ꍇ�Afalse��ԋp�B<BR>
     * �������ʁ�0���̏ꍇ�Atrue��ԋp����B<BR>
     *  <BR>
     * @@param l_strCommProductCode - (�萔�����i�R�[�h)
     * @@throws WEB3SystemLayerException
     * @@return boolean
     */
    public boolean isBatoEnforcemented(String l_strCommProductCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isBatoEnforcemented(String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�u��Е��X���i�e�[�u���v����������
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" branch_id = ? ");
        l_sbWhere.append(" and commission_product_code = ? ");
        l_sbWhere.append(" and bato_flag = ? ");
        Object[] l_objWhere =
            {new Long(this.getBranchId()),
             l_strCommProductCode,
             WEB3BatoFlagDef.ENFORCEMENT };
        List l_lstRecords;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                InstBranchProductRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);

        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //�Q�j�������ʁ�0���̏ꍇ�Afalse��ԋp�B
        //�������ʁ�0���̏ꍇ�Atrue��ԋp����
        if(l_lstRecords.size() <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }

    /**
     * (get�v���t�@@�����X�̒l) <BR>
     * <BR>
     * �ȉ��̏����Łu���X�ʃv���t�@@�����X�e�[�u���v���������A<BR>
     *     �h�v���t�@@�����X�̒l�h���擾����B<BR>
     *     [��������]<BR>
     *     ����.�⏕����.get����X().���XID<BR>
     *     ����.�v���t�@@�����X��<BR>
     *     ����.�v���t�@@�����X���̘A��<BR>
     * <BR>
     * @@param l_subAccount �⏕����
     * @@param l_strName �v���t�@@�����X��
     * @@param l_lngNameSerialNo �v���t�@@�����X�̘A��
     * @@throws WEB3BaseException
     */
    protected String getValue(
        WEB3GentradeSubAccount l_subAccount,
        String l_strName,
        long l_lngNameSerialNo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getValue(WEB3GentradeSubAccount, String, long)";
        log.entering(STR_METHOD_NAME);

        //   �ȉ��̏����Łu���X�ʃv���t�@@�����X�e�[�u���v���������A
        //     �h�v���t�@@�����X�̒l�h���擾����B
        // �@@�@@[��������]
        //      ����.�⏕����.get����X().���XID
        //      ����.�v���t�@@�����X��
        // �@@�@@ ����.�v���t�@@�����X���̘A��
        BranchPreferencesRow l_bpRow = null;
        try
        {
            l_bpRow = BranchPreferencesDao.findRowByPk(
                l_subAccount.getWeb3GenBranch().getBranchId(),
                l_strName,
                (int)l_lngNameSerialNo);
        }
        catch (DataFindException e)
        {
            //�擾�ł��Ȃ��i�Y���f�[�^���Ȃ��j�ꍇ�́A����������return����B
            log.debug("���X�ʃv���t�@@�����X�e�[�u����" 
                + "���XID = " + l_subAccount.getWeb3GenBranch().getBranchId()
                + " �v���t�@@�����X�� = " + l_strName
                + " �v���t�@@�����X���̘A�� = " + l_lngNameSerialNo
                + " �̃��R�[�h������");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataQueryException e)
        {
            log.error(e.getMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        catch (DataNetworkException e)
        {
            log.error(e.getMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_bpRow.getValue();
    }
    
    /**
     * (validate�N����l) <BR>
     *  <BR>
     * �ڋq�̔N��Y���@@�\�A��ЁA���X���w�肷��N���������<BR>
     * ���������`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�����^�C�v�̎擾<BR>
     * �@@�i�P�j�����̈����̕⏕�������A�ڋq�I�u�W�F�N�g���擾���A<BR>
     *     �ڋq�I�u�W�F�N�g�̌����^�C�v���擾����B<BR>
     * <BR>
     *   �i�Q�j�i�P�j�Ŏ擾�����ڋq.�����^�C�v=�h�@@�l�A�J�E���g�h�̏ꍇ�́A<BR>
     *     ����������return����B<BR>
     * <BR>
     * �Q�j�N����l�̎擾<BR>
     *   �i�P�j�ȉ��̏����Łu���X�ʃv���t�@@�����X�e�[�u���v���������A<BR>
     *     �h�v���t�@@�����X�̒l�h���擾����B<BR>
     * �@@�@@[��������]<BR>
     *      ����.�⏕����.get����X().���XID<BR>
     *      ����.�v���t�@@�����X��<BR>
     * �@@�@@ ����.�v���t�@@�����X���̘A��<BR>
     * <BR>
     *   �i�Q�j�擾�ł��Ȃ��i�Y���f�[�^���Ȃ��j�ꍇ�́A����������return����B<BR>
     * <BR>
     * �R�j�N��������`�F�b�N<BR>
     *   �i�P�j���N�����N�����擾����B<BR>
     *     �擾�����l���Anull�̏ꍇ�́A��O���X���[����B<BR>
     *     ���N�����N�� = ����.�⏕����.getMainAccount().���N�����N��<BR>
     * <BR>
     * �i�Q�j���N�������擾����i�a��j�B<BR>
     *     �擾�����l���Anull�̏ꍇ�́A��O���X���[����B<BR>
     *     ���N���� = ����.�⏕����.getMainAccount().���N����<BR>
     * <BR>
     *   �i�R�j���N�����N���A�a��N�����𐼗�̓��t�^�ɕϊ�����B<BR>
     *     ����N���� = �N��.toDate(���N�����N��, ���N����)<BR>
     * <BR>
     *   �i�S�j���ݓ��t���擾����B<BR>
     * <BR>
     *   �i�T�j���ݓ��t�Ɛ���N�������ڋq�̔N����v�Z����B<BR>
     * <BR>
     *   �i�U�j�ڋq�̔N��P�j�Ŏ擾�����h�v���t�@@�����X�̒l�h�ȏ�<BR>
     *     �i�ڋq�̔N��@@>= �h�v���t�@@�����X�̒l�h�j�̏ꍇ�Areturn����B<BR>
     *      ��L�̏����łȂ��ꍇ�́A�ǉ����Ƃ��āu�v���t�@@�����X�̒l�v��<BR>
     *      �t�����A��O���X���[����B<BR>
     * <BR>
     *    ���ȉ��̏����ŃG���[�����������ꍇ�́uBUSINESS_ERROR_02200�v��O���X���[����B <BR>
     *       �i�R�j���N�����N���A�a��N�����𐼗�̓��t�^�ɕϊ�����B <BR>
     *       �i�S�j���ݓ��t���擾����B <BR>
     *       �i�T�j���ݓ��t�Ɛ���N�������ڋq�̔N����v�Z����B<BR>
     * <BR>
     * �@@�@@�@@(�������A����.�v���t�@@�����X�� = sl_lowlimit.age.check�@@�̏ꍇ�A<BR>
     * �ȉ��̃G���[���b�Z�[�W���o�͂���B�j <BR>
     * �@@�@@�@@ �N��N����l�ɒB���Ă��܂���B<BR>
     * <BR>
     * @@param l_subAccount �⏕����
     * @@param l_strName �v���t�@@�����X��
     * @@param l_lngNameSerialNo �v���t�@@�����X�̘A��
     * @@throws WEB3BaseException
     */
    public void validateAgeLowLimit(
        WEB3GentradeSubAccount l_subAccount,
        String l_strName,
        long l_lngNameSerialNo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateAgeLowLimit(WEB3GentradeSubAccount, String, long)";
        log.entering(STR_METHOD_NAME);

        //�P�j�����^�C�v�̎擾
        // �@@�i�P�j�����̈����̕⏕�������A�ڋq�I�u�W�F�N�g���擾���A
        //     �ڋq�I�u�W�F�N�g�̌����^�C�v���擾����B
        WEB3GentradeMainAccount l_mainAccount = null;
        l_mainAccount = 
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        MainAccountRow l_mainAccountRow = 
            (MainAccountRow)l_mainAccount.getDataSourceObject();

        MainAccountTypeEnum l_accountType = l_mainAccountRow.getAccountType();

        //  �i�Q�j�i�P�j�Ŏ擾�����ڋq.�����^�C�v=�h�@@�l�A�J�E���g�h�̏ꍇ�́A
        //     ����������return����B
        if (MainAccountTypeEnum.CORPORATE_ACCOUNT == l_accountType)
        {
            log.debug("�ڋq.�����^�C�v=�h�@@�l�A�J�E���g�h");
            log.exiting(STR_METHOD_NAME);
            return ;
        }

        //�Q�j�N����l�̎擾
        String l_strAgeLimit = 
            this.getValue(l_subAccount, l_strName, l_lngNameSerialNo);
        if (l_strAgeLimit == null)
        {
            //�i�Q�j�擾�ł��Ȃ��i�Y���f�[�^���Ȃ��j�ꍇ�́A����������return����B
            log.exiting(STR_METHOD_NAME);
            return ;    
        }

        //�R�j�N��������`�F�b�N
        //�i�P�j���N�����N�����擾����B
        String l_strEraBorn = l_mainAccountRow.getEraBorn();
        if (WEB3StringTypeUtility.isEmpty(l_strEraBorn))
        {
            //�擾�����l���Anull�̏ꍇ�́A��O���X���[����B
            log.debug("���N�����N�������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02198,
                WEB3GentradeTradingTimeManagement.class.getName()
                   + "." + STR_METHOD_NAME);
        }
        //�i�Q�j���N�������擾����i�a��j
        String l_strBornDate = l_mainAccountRow.getBornDate();
        if (WEB3StringTypeUtility.isEmpty(l_strBornDate))
        {
            //�擾�����l���Anull�̏ꍇ�́A��O���X���[����B
            log.debug("���N���������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02199,
                WEB3GentradeTradingTimeManagement.class.getName()
                   + "." + STR_METHOD_NAME);
        }
        long l_lngValue = 0L;
        long l_lngAge = 0L;
        try
        {
            //�i�R�j���N�����N���A�a��N�����𐼗�̓��t�^�ɕϊ�����B
            Date l_datBirthday =
                WEB3GentradeEra.toDate(l_strEraBorn, l_strBornDate);
            //�i�S�j���ݓ��t���擾����B
            Timestamp l_tsSystemTimestamp = GtlUtils.getSystemTimestamp();

            String l_strBirthday =
                WEB3DateUtility.formatDate(l_datBirthday, "yyyyMMdd");
            String l_strSystemDate =
                WEB3DateUtility.formatDate(l_tsSystemTimestamp, "yyyyMMdd");
            long l_lngBirthDay = new Long(l_strBirthday).longValue();
            long l_lngSystemDate = new Long(l_strSystemDate).longValue();
            l_lngValue = new Long(l_strAgeLimit).longValue();
            l_lngAge = (l_lngSystemDate - l_lngBirthDay) / 10000;
        }
        catch (Exception l_ex)
        {
            if (WEB3BranchPreferencesNameDef.SL_LOWLIMIT_AGE_CHECK.equals(l_strName))
            {
                log.error("�N��N����l�ɒB���Ă��܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02955,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME
                );
            }
            log.error("�ڋq�̔N��v���t�@@�����X�̒l��菬�����ł��B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02200,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME
            );
        }

        if (l_lngAge >= l_lngValue)
        {
            log.exiting(STR_METHOD_NAME);
            return ;
        }
        else
        {
            if (WEB3BranchPreferencesNameDef.SL_LOWLIMIT_AGE_CHECK.equals(l_strName))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02955,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME,
                        " �v���t�@@�����X�̒l = " + l_strAgeLimit
                );
            }
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02200,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                    " �v���t�@@�����X�̒l = " + l_strAgeLimit
            );
        }
    }

    /**
     * (validate�N�����l) <BR>
     *  <BR>
     * �ڋq�̔N��Y���@@�\�A��ЁA���X���w�肷��N����������<BR>
     * ���������`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�����^�C�v�̎擾<BR>
     *   �i�P�j�����̈����̕⏕�������A�ڋq�I�u�W�F�N�g���擾���A<BR>
     *     �ڋq�I�u�W�F�N�g�̌����^�C�v���擾����B<BR>
     * <BR>
     *   �i�Q�j�i�P�j�Ŏ擾�����ڋq.�����^�C�v=�h�@@�l�A�J�E���g�h�̏ꍇ�́A<BR>
     *     ����������return����B<BR>
     * <BR>
     * �Q�j�N�����l�̎擾<BR>
     *   �i�P�j�ȉ��̏����Łu���X�ʃv���t�@@�����X�e�[�u���v���������A<BR>
     *    �h�v���t�@@�����X�̒l�h���擾����B<BR>
     *    [��������]<BR>
     *      ����.�⏕����.get����X().���XID<BR>
     *      ����.�v���t�@@�����X��<BR>
     *      ����.�v���t�@@�����X���̘A��<BR>
     * <BR>
     *   �i�Q�j�擾�ł��Ȃ��i�Y���f�[�^���Ȃ��j�ꍇ�́A����������return����B<BR>
     * <BR>
     * �R�j�N���������`�F�b�N<BR>
     *   �i�P�j���N�����N�����擾����B<BR>
     *     �擾�����l���Anull�̏ꍇ�́A��O���X���[����B<BR>
     *     ���N�����N�� = ����.�⏕����.getMainAccount().���N�����N��<BR>
     * <BR>
     *   �i�Q�j���N�������擾����i�a��j�B<BR>
     *     �擾�����l���Anull�̏ꍇ�́A��O���X���[����B<BR>
     *     ���N���� = ����.�⏕����.getMainAccount().���N����<BR>
     *   �i�R�j���N�����N���A�a��N�����𐼗�̓��t�^�ɕϊ�����B<BR>
     *     ����N���� = �N��.toDate(���N�����N��, ���N����)<BR>
     * <BR>
     *   �i�S�j���ݓ��t���擾����B<BR>
     * <BR>
     *   �i�T�j���ݓ��t�Ɛ���N�������ڋq�̔N����v�Z����B<BR>
     * <BR>
     *   �i�U�j�ڋq�̔N��P�j�Ŏ擾�����h�v���t�@@�����X�̒l�h��菬����<BR>
     *     �i �ڋq�̔N��@@< �h�v���t�@@�����X�̒l�h �j�ꍇ�Areturn����B<BR>
     *    ��L�̏����łȂ��ꍇ�́A�ǉ����Ƃ��āu�v���t�@@�����X�̒l�v��<BR>
     *    �t�����A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@(�������A����.�v���t�@@�����X�� = sl_highlimit.age.check�̏ꍇ�A<BR>
     * �ȉ��̃G���[���b�Z�[�W���o�͂���B�j  <BR>
     * �@@�@@�@@ �N��N�����l�𒴂��Ă��܂��B<BR>
     * <BR>
     * @@param l_subAccount �⏕����
     * @@param l_strName �v���t�@@�����X��
     * @@param l_lngNameSerialNo �v���t�@@�����X�̘A��
     * @@throws WEB3BaseException
     */
    public void validateAgeHighLimit(
    WEB3GentradeSubAccount l_subAccount,
        String l_strName,
        long l_lngNameSerialNo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateAgeHighLimit(WEB3GentradeSubAccount, String, long)";
        log.entering(STR_METHOD_NAME);

        //�P�j�����^�C�v�̎擾
        // �@@�i�P�j�����̈����̕⏕�������A�ڋq�I�u�W�F�N�g���擾���A
        //     �ڋq�I�u�W�F�N�g�̌����^�C�v���擾����B
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        MainAccountRow l_mainAccountRow = 
            (MainAccountRow)l_mainAccount.getDataSourceObject();
        MainAccountTypeEnum l_accountType = l_mainAccountRow.getAccountType();

        //  �i�Q�j�i�P�j�Ŏ擾�����ڋq.�����^�C�v=�h�@@�l�A�J�E���g�h�̏ꍇ�́A
        //     ����������return����B
        if (MainAccountTypeEnum.CORPORATE_ACCOUNT == l_accountType)
        {
            log.exiting(STR_METHOD_NAME);
            return ;
        }

        //�Q�j�N�����l�̎擾
        String l_strAgeLimit = 
            this.getValue(l_subAccount, l_strName, l_lngNameSerialNo);
        if (l_strAgeLimit == null)
        {
            //�i�Q�j�擾�ł��Ȃ��i�Y���f�[�^���Ȃ��j�ꍇ�́A����������return����B
            log.exiting(STR_METHOD_NAME);
            return ;    
        }

        //�R�j�N���������`�F�b�N
        //�i�P�j���N�����N�����擾����B
        String l_strEraBorn = l_mainAccountRow.getEraBorn();
        if (WEB3StringTypeUtility.isEmpty(l_strEraBorn))
        {
            //�擾�����l���Anull�̏ꍇ�́A��O���X���[����B
            log.debug("���N�����N�������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02198,
                WEB3GentradeTradingTimeManagement.class.getName()
                   + "." + STR_METHOD_NAME);
        }
        //�i�Q�j���N�������擾����i�a��j
        String l_strBornDate = l_mainAccountRow.getBornDate();
        if (WEB3StringTypeUtility.isEmpty(l_strBornDate))
        {
            //�擾�����l���Anull�̏ꍇ�́A��O���X���[����B
            log.debug("���N���������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02199,
                WEB3GentradeTradingTimeManagement.class.getName()
                   + "." + STR_METHOD_NAME);
        }
        //�i�R�j���N�����N���A�a��N�����𐼗�̓��t�^�ɕϊ�����B
        Date l_datBirthday = 
            WEB3GentradeEra.toDate(l_strEraBorn, l_strBornDate);
        //�i�S�j���ݓ��t���擾����B
        Timestamp l_tsSystemTimestamp = GtlUtils.getSystemTimestamp();

        String l_strBirthday =
            WEB3DateUtility.formatDate(l_datBirthday, "yyyyMMdd");
        String l_strSystemDate =
            WEB3DateUtility.formatDate(l_tsSystemTimestamp, "yyyyMMdd");
        long l_lngBirthDay = new Long(l_strBirthday).longValue();
        long l_lngSystemDate = new Long(l_strSystemDate).longValue();
        long l_lngValue = new Long(l_strAgeLimit).longValue();
        long l_lngAge = (l_lngSystemDate - l_lngBirthDay) / 10000; 
        if ( l_lngAge < l_lngValue)
        {
            log.exiting(STR_METHOD_NAME);
            return ;
        }
        else
        {
            if (WEB3BranchPreferencesNameDef.SL_HIGHLIMIT_AGE_CHECK.equals(l_strName))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02956,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME,
                        " �v���t�@@�����X�̒l = " + l_strAgeLimit
                );
            }
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02201,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                    "�v���t�@@�����X�̒l = " + l_strAgeLimit
            );
        }
    }

    /**
     * (get�v���t�@@�����X�̒l) <BR>
     * <BR>
     * �ȉ��̏����Łu���X�ʃv���t�@@�����X�e�[�u���v���������A<BR>
     *     �h�v���t�@@�����X�̒l�h���擾����B<BR>
     *     [��������]<BR>
     *     ����.���XID<BR>
     *     ����.�v���t�@@�����X��<BR>
     *     ����.�v���t�@@�����X���̘A��<BR>
     * <BR>
     * @@param l_lngBranchId ���XID
     * @@param l_strName �v���t�@@�����X��
     * @@param l_lngNameSerialNo �v���t�@@�����X�̘A��
     * @@throws WEB3BaseException
     */
    protected String getValue(
        long l_lngBranchId,
        String l_strName,
        long l_lngNameSerialNo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getValue(long, String, long)";
        log.entering(STR_METHOD_NAME);

        //   �ȉ��̏����Łu���X�ʃv���t�@@�����X�e�[�u���v���������A
        //     �h�v���t�@@�����X�̒l�h���擾����B
        // �@@�@@[��������]
        //      ����.���XID
        //      ����.�v���t�@@�����X��
        // �@@�@@ ����.�v���t�@@�����X���̘A��
        BranchPreferencesRow l_bpRow = null;
        try
        {
            l_bpRow = BranchPreferencesDao.findRowByPk(
                l_lngBranchId,
                l_strName,
                (int)l_lngNameSerialNo);
        }
        catch (DataFindException e)
        {
            //�擾�ł��Ȃ��i�Y���f�[�^���Ȃ��j�ꍇ�́A����������return����B
            log.debug("���X�ʃv���t�@@�����X�e�[�u����" 
                + "���XID = " + l_lngBranchId
                + " �v���t�@@�����X�� = " + l_strName
                + " �v���t�@@�����X���̘A�� = " + l_lngNameSerialNo
                + " �̃��R�[�h������");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataQueryException e)
        {
            log.error(e.getMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        catch (DataNetworkException e)
        {
            log.error(e.getMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_bpRow.getValue();
    }

    /**
     * (is���M�萔���v�Z) <BR>
     * <BR>
     * ���X�����M�M������ɂĎ萔���v�Z�����邩�ǂ����𔻕ʂ���B<BR>
     * <BR>
     * [�߂�l]<BR>
     * true�F �萔���v�Z�v<BR>
     * false�F �萔���v�Z�s�v<BR>
     * <BR>
     * �ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B<BR>
     * <BR>
     * [����]<BR>
     * ���XID = this.getBranchId()�̖߂�l<BR>
     * �v���t�@@�����X�� = "mf.commission.calc"<BR>
     * �v���t�@@�����X���̘A�� = 1<BR>
     * <BR>
     * �Q�j�擾�������R�[�h.�v���t�@@�����X�̒l == �h�萔���v�Z�v�h �̏ꍇ�A<BR>
     * true ��ԋp����B <BR>
     * <BR>
     * �R�j����ȊO�̏ꍇ�́Afalse��ԋp����B<BR>
     * �����R�[�h���擾�ł��Ȃ������ꍇ���܂ށB<BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isCommissionCalc() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isCommissionCalc()";
        log.entering(STR_METHOD_NAME);
        final int NAME_SERIAL_NO = 1;

        //�v���t�@@�����X�̒l�̎擾
        String l_strValue = 
            this.getValue(
                this.getBranchId(),
                WEB3BranchPreferencesNameDef.COMMISSION_CALC,
                NAME_SERIAL_NO);
        //�Q�j�擾�������R�[�h.�v���t�@@�����X�̒l == �h�萔���v�Z�v�h �̏ꍇ
        if (WEB3CommissionCalcDef.COMMISSION_CALC.equals(l_strValue))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        //�R�j����ȊO�̏ꍇ�́Afalse��ԋp����B
        //�����R�[�h���擾�ł��Ȃ������ꍇ���܂ށB
        return false;
    }

    /**
     * (is���M�抷�攃�t�Œ���z�`�F�b�N���{) <BR>
     * <BR>
     * ���X�����M�M������ɂď抷�攃�t�Œ���z�`�F�b�N�����邩�ǂ����𔻕ʂ���B<BR>
     * <BR>
     * [�߂�l]<BR>
     * true�F ���t�Œ���z�`�F�b�N�v<BR>
     * false�F ���t�Œ���z�`�F�b�N�s�v<BR>
     * <BR>
     * �ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B<BR>
     * <BR>
     * [����]<BR>
     * ���XID = this.getBranchId()�̖߂�l<BR>
     * �v���t�@@�����X�� = "mf.switch.buying.minimum.amount.check"<BR>
     * �v���t�@@�����X���̘A�� = 1<BR>
     * <BR>
     * �Q�j�擾�������R�[�h.�v���t�@@�����X�̒l == �h���t�Œ���z�`�F�b�N�v�h �̏ꍇ�A<BR>
     * true ��ԋp����B<BR>
     * <BR>
     * �R�j����ȊO�̏ꍇ�́Afalse��ԋp����B<BR>
     * �����R�[�h���擾�ł��Ȃ������ꍇ���܂ށB<BR> 
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isBuyingMinimumAmountCheck() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isBuyingMinimumAmountCheck()";
        log.entering(STR_METHOD_NAME);
        final int NAME_SERIAL_NO = 1;

        //�v���t�@@�����X�̒l�̎擾
        String l_strValue = 
            this.getValue(
                this.getBranchId(),
                WEB3BranchPreferencesNameDef.SWITCH_BUYING_MINIMUM_AMOUNT_CHECK,
                NAME_SERIAL_NO);
        //�Q�j�擾�������R�[�h.�v���t�@@�����X�̒l == �h���t�Œ���z�`�F�b�N�v�h �̏ꍇ
        if (WEB3BuyingMinimumCheckDef.AMOUNT_CHECK.equals(l_strValue))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        //�R�j����ȊO�̏ꍇ�́Afalse��ԋp����B
        //�����R�[�h���擾�ł��Ȃ������ꍇ���܂ށB
        return false;
    }

    /**
     * (get���M���򒥎��S����) <BR>
     * <BR>
     * ���M�M���̏抷�����ɂĎg�p���錹�򒥎��S�������擾����B<BR>
     * <BR>
     * �ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B<BR>
     * <BR>
     * [����]<BR>
     * ���XID = this.getBranchId()�̖߂�l<BR>
     * �v���t�@@�����X�� = "mf.switch.withholdingtax.restriction.rate"<BR>
     * �v���t�@@�����X���̘A�� = 1<BR>
     * <BR>
     * �Q�j�擾�������R�[�h.�v���t�@@�����X�̒l��ԋp����B<BR>
     * �����R�[�h���擾�ł��Ȃ������ꍇ�́ADouble.NaN��ԋp����B<BR> 
     * <BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getWithholdingtaxRestrictionRate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWithholdingtaxRestrictionRate()";
        log.entering(STR_METHOD_NAME);
        final int NAME_SERIAL_NO = 1;
        
        //�v���t�@@�����X�̒l�̎擾
        String l_strValue = 
            this.getValue(
                this.getBranchId(),
                WEB3BranchPreferencesNameDef.SWITCH_WITHHOLDINGTAX_RESTRICTION_RATE,
                NAME_SERIAL_NO);
        //�Q�j�擾�������R�[�h.�v���t�@@�����X�̒l��ԋp����
        //�����R�[�h���擾�ł��Ȃ������ꍇ�́ADouble.NaN��ԋp����B
        if (l_strValue == null)
        {
            log.exiting(STR_METHOD_NAME);
            return Double.NaN;
        }

        double l_dblRate = new Double(l_strValue).doubleValue();
         
        log.exiting(STR_METHOD_NAME);
        return l_dblRate;
    }

    /**
     * (is���M��񎞏o����������) <BR>
     * <BR>
     * ���X�����M�M��������ɂďo�������𐶐����邩�ǂ����𔻕ʂ���B<BR>
     * <BR>
     * [�߂�l]<BR>
     * true�F �o�����������v<BR>
     * false�F �o�����������s�v<BR>
     * <BR>
     * �ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B<BR>
     * <BR>
     * [����]<BR>
     * ���XID = this.getBranchId()�̖߂�l<BR>
     * �v���t�@@�����X�� = "mf.dissolution.create.payment.order"<BR>
     * �v���t�@@�����X���̘A�� = 1<BR>
     * <BR>
     * �Q�j�擾�������R�[�h.�v���t�@@�����X�̒l == �h�o�����������v�h �̏ꍇ�A<BR>
     * true ��ԋp����B<BR>
     * <BR>
     * �R�j����ȊO�̏ꍇ�́Afalse��ԋp����B<BR>
     * �����R�[�h���擾�ł��Ȃ������ꍇ���܂ށB<BR> 
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isPaymentOrderCreate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isPaymentOrderCreate()";
        log.entering(STR_METHOD_NAME);
        final int NAME_SERIAL_NO = 1;

        //�v���t�@@�����X�̒l�̎擾
        String l_strValue = 
            this.getValue(
                this.getBranchId(),
                WEB3BranchPreferencesNameDef.DISSOLUTION_CREATE_PAYMENT_ORDER,
                NAME_SERIAL_NO);
        //�Q�j�擾�������R�[�h.�v���t�@@�����X�̒l == �h�o�����������v�h �̏ꍇ
        if (WEB3PaymentOrderCreateDef.CREATE_ORDER.equals(l_strValue))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        //�R�j����ȊO�̏ꍇ�́Afalse��ԋp����B
        //�����R�[�h���擾�ł��Ȃ������ꍇ���܂ށB
        return false;
    }

    /**
     * (isFX_MRF�����J�݃`�F�b�N���{) <BR>
     * <BR>
     * ���X���ב֕ۏ؋�����ɂ�MRF�����J�݃`�F�b�N�����邩�ǂ����𔻕ʂ���B<BR>
     * <BR>
     * [�߂�l]<BR>
     * true�F �`�F�b�N�v<BR>
     * false�F �`�F�b�N�s�v<BR>
     * <BR>
     * �ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���XID = this.getBranchId()�̖߂�l<BR>
     *    �v���t�@@�����X�� = "fx.mrfaccount.check"<BR>
     *    �v���t�@@�����X���̘A�� = 1<BR>
     * <BR>
     * �Q�j�擾�������R�[�h.�v���t�@@�����X�̒l == �h�`�F�b�N�s�v�h �̏ꍇ�Afalse ��ԋp����B<BR>
     * <BR>
     * �R�j����ȊO�̏ꍇ�́Atrue��ԋp����B<BR>
     *    �����R�[�h���擾�ł��Ȃ������ꍇ���܂ށB<BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isFxMrfAccountOpenCheck() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isFxMrfAccountOpenCheck()";
        log.entering(STR_METHOD_NAME);

        final int NAME_SERIAL_NO = 1;

        //�v���t�@@�����X�̒l�̎擾
        String l_strValue = 
            this.getValue(
                this.getBranchId(),
                WEB3BranchPreferencesNameDef.FX_MRFACCOUNT_CHECK,
                NAME_SERIAL_NO);

        //�Q�j�擾�������R�[�h.�v���t�@@�����X�̒l == �h�`�F�b�N�s�v�h �̏ꍇ�Afalse ��ԋp����B
        if (WEB3FxMrfAccountCheckDef.NO_CHECK.equals(l_strValue))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�R�j����ȊO�̏ꍇ�́Atrue��ԋp����B
        //�����R�[�h���擾�ł��Ȃ������ꍇ���܂ށB
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (get���M�莞��z���t�Œ���z) <BR>
     * <BR>
     * �A�C�e���̒�`<BR>
     * ���M�M���̒莞��z���t�ɂĎg�p����莞��z���t�Œ���z���擾����B<BR> 
     * <BR>
     * �ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B<BR> 
     * <BR>
     *    [����] <BR>
     *    ���XID = this.getBranchId()�̖߂�l <BR>
     *    �v���t�@@�����X�� = "mf.fixed.buying.min.amount" <BR>
     *    �v���t�@@�����X���̘A�� = 1 <BR>
     * <BR>
     * �Q�j�擾�������R�[�h.�v���t�@@�����X�̒l��ԋp����B<BR>
     *    �����R�[�h���擾�ł��Ȃ������ꍇ�́ADouble.NaN��ԋp����B<BR>
     * <BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double  getMfFixedBuyingMinAmount() throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getMfFixedBuyingMinAmount()";
        log.entering(STR_METHOD_NAME);
        final int NAME_SERIAL_NO = 1;
        
        //�v���t�@@�����X�̒l�̎擾
        String l_strValue = 
            this.getValue(
                this.getBranchId(),
                WEB3BranchPreferencesNameDef.MF_FIXED_BUYING_MIN_AMOUNT,
                NAME_SERIAL_NO);
        //�Q�j�擾�������R�[�h.�v���t�@@�����X�̒l��ԋp����
        //�����R�[�h���擾�ł��Ȃ������ꍇ�́ADouble.NaN��ԋp����B
        if (l_strValue == null || "".equals(l_strValue.trim()))
        {
            log.exiting(STR_METHOD_NAME);
            return Double.NaN;
        }

        double l_dblMinAmount = new Double(l_strValue.trim()).doubleValue();
         
        log.exiting(STR_METHOD_NAME);
        return l_dblMinAmount;
    }
    
    /**
     * (get���M�莞��z���t�P�ʋ��z) <BR>
     * <BR>
     * �A�C�e���̒�`<BR>
     * ���M�M���̒莞��z���t�ɂĎg�p����莞��z���t�P�ʋ��z���擾����B<BR> 
     * <BR>
     * �ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B<BR> 
     * <BR>
     *    [����] <BR>
     *    ���XID = this.getBranchId()�̖߂�l <BR>
     *    �v���t�@@�����X�� = "mf.fixed.buying.unit.amount" <BR>
     *    �v���t�@@�����X���̘A�� = 1 <BR>
     * <BR>
     * �Q�j�擾�������R�[�h.�v���t�@@�����X�̒l��ԋp����B<BR> 
     *    �����R�[�h���擾�ł��Ȃ������ꍇ�́ADouble.NaN��ԋp����B <BR>
     * <BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double  getMfFixedBuyingUnitAmount() throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getMfFixedBuyingUnitAmount()";
        log.entering(STR_METHOD_NAME);
        final int NAME_SERIAL_NO = 1;
        
        //�v���t�@@�����X�̒l�̎擾
        String l_strValue = 
            this.getValue(
                this.getBranchId(),
                WEB3BranchPreferencesNameDef.MF_FIXED_BUYING_UNIT_AMOUNT,
                NAME_SERIAL_NO);
        //�Q�j�擾�������R�[�h.�v���t�@@�����X�̒l��ԋp����
        //�����R�[�h���擾�ł��Ȃ������ꍇ�́ADouble.NaN��ԋp����B
        if (l_strValue == null || "".equals(l_strValue.trim()))
        {
            log.exiting(STR_METHOD_NAME);
            return Double.NaN;
        }

        double l_dblUnitAmount = new Double(l_strValue.trim()).doubleValue();
         
        log.exiting(STR_METHOD_NAME);
        return l_dblUnitAmount;
    }

    /**
     * (is�����t���ŏI���`�F�b�N) <BR>
     * <BR>
     * �����t���ŏI���`�F�b�N�����{���邩�ǂ����𔻒肵�A <BR>
     * ���{����ꍇ��true���A���Ȃ��ꍇ��false��ԋp����B <BR>
     * <BR>
     * �P�j�@@���X�p�v���t�@@�����X�e�[�u�����A�����t���ŏI���`�F�b�N���擾����B <BR>
     * �@@�@@�@@�@@[��������] <BR>
     * �@@�@@�@@�@@�@@���XID =  this.getBranchId()�̖߂�l <BR>
     * �@@�@@�@@�@@�@@AND�@@�v���t�@@�����X�� = �v���t�@@�����X��.�����t���ŏI���`�F�b�N <BR>
     * �@@�@@�@@�@@�@@AND�@@�v���t�@@�����X���̘A�� = 1 <BR>
     * <BR>
     * �@@�@@�@@�@@����L�����Ń��R�[�h���擾�o���Ȃ��ꍇ�A"false�F���{���Ȃ�"��ԋp����B <BR>
     * <BR>
     * �Q�j�@@�����t���ŏI���`�F�b�N��"�`�F�b�N����"�̏ꍇtrue��ԋp����B <BR>
     * �@@�@@�@@�ȊO�̏ꍇ�Afalse��ԋp����B <BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isEqtypeFinalDayWithRight() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isEqtypeFinalDayWithRight()";
        log.entering(STR_METHOD_NAME);

        //�v���t�@@�����X���̘A�Ԃ��`
        final int NAME_SERIAL_NO = 1;

        //���X�p�v���t�@@�����X�e�[�u�����A�����t���ŏI���`�F�b�N���擾����B
        // ���XID = this.getBranchId()�̖߂�l
        // �v���t�@@�����X�� = �v���t�@@�����X��.�����t���ŏI���`�F�b�N
        // �v���t�@@�����X���̘A�� = 1
        String l_strValue = this.getValue(
            this.getBranchId(),
            WEB3BranchPreferencesNameDef.EQTYPE_FINAL_DAY_WITH_RIGHT,
            NAME_SERIAL_NO);

        //�����t���ŏI���`�F�b�N��"�`�F�b�N����"�̏ꍇtrue��ԋp����B
        if (WEB3EqtypeFinalDayWithRightDef.EXECUTE.equals(l_strValue))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is�[����{) <BR>
     * <BR>
     * �A�C�e���̒�`<BR>
     * �[����{���s���Ă��邩�𔻒肷��B<BR>
     * <BR>
     * �@@�P�j����.�����^�C�v�ɑΉ�����v���t�@@�����X���̘A�Ԃ��擾����B<BR>
     * <BR>
     * �@@�@@�@@�P�|�P�j����.�����^�C�v = "�敨�I�v�V����"�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�v���t�@@�����X���̘A�� = "2�F�敨�I�v�V����"<BR>
     * <BR>
     * �@@�@@�@@�P�|�Q�j��L�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@false�i���{���Ȃ��j��ԋp����B<BR>
     * <BR>
     * �@@�Q�j���X�v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾����B<BR>
     * <BR>
     * �@@�@@�@@[��������]<BR>
     * �@@�@@�@@�@@���XID = this.getBranchId()�̖߂�l<BR>
     * �@@�@@�@@�@@AND�@@�v���t�@@�����X�� = �v���t�@@�����X��.�[����{�敪<BR>
     * �@@�@@�@@�@@AND�@@�v���t�@@�����X���̘A�� = �P�j�Ŏ擾�����v���t�@@�����X���̘A��<BR>
     * <BR>
     * �@@�@@�@@�@@����L�����Ń��R�[�h���擾�o���Ȃ��ꍇ�Afalse�i���{���Ȃ��j��ԋp����B<BR>
     * <BR>
     * �@@�R�j�擾���R�[�h.�v���t�@@�����X�̒l = "���{����"�̏ꍇ�A true��ԋp����B<BR>
     * �@@�@@�@@�ȊO�̏ꍇ�́A false��ԋp����B<BR>
     * <BR>
     * @@param l_productType �����^�C�v
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isEveningSessionEnforcemented(ProductTypeEnum l_productType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isEveningSessionEnforcemented(ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //�P�j����.�����^�C�v�ɑΉ�����v���t�@@�����X���̘A�Ԃ��擾
        String l_strNameSerialNo;
        if (l_productType.equals(ProductTypeEnum.IFO))
        {
            l_strNameSerialNo = WEB3BranchNameSerialNoDef.FUTURE_OPTION;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�Q�j���X�v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾
        String l_strValue = this.getValue(
            this.getBranchId(),
            WEB3BranchPreferencesNameDef.EVENING_SESSION_DIV,
            Integer.parseInt(l_strNameSerialNo));

        //�R�j�擾���R�[�h.�v���t�@@�����X�̒l = "���{����"�̏ꍇ�A true��ԋp����
        if (WEB3EveningSessionDivDef.EXECUTE.equals(l_strValue))
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
     * (is�؋����s���z��Ǘ�) <BR>
     * <BR>
     * �؋����s���z�Ǘ����s���Ă��邩�𔻒肷��B <BR>
     * <BR>
     * <BR>
     * �P�j���X�v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾����B  <BR>
     * <BR>
     * �@@�@@�@@[��������] <BR>
     * �@@�@@�@@�@@���XID = this.getBranchId()�̖߂�l <BR>
     * �@@�@@�@@�@@AND�@@�v���t�@@�����X�� = �v���t�@@�����X��.�؋����s���z��Ǘ� <BR>
     * �@@�@@�@@�@@AND�@@�v���t�@@�����X���̘A�� = 1 <BR>
     * <BR>
     * �@@�@@�@@�@@����L�����Ń��R�[�h���擾�o���Ȃ��ꍇ�Afalse�i�Ǘ�����j��ԋp����B <BR>
     * <BR>
     * �Q�j�擾���R�[�h.�v���t�@@�����X�̒l = "�Ǘ����Ȃ�"�̏ꍇ�A true��ԋp����B <BR>
     * �@@�@@�@@�ȊO�̏ꍇ�́A false��ԋp����B<BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isIfodepositLackchargeNonManagement() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isIfodepositLackchargeNonManagement()";
        log.entering(STR_METHOD_NAME);

        //�v���t�@@�����X���̘A�Ԃ��`
        final int NAME_SERIAL_NO = 1;

        //���X�v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾����B
        //���XID = this.getBranchId()�̖߂�l
        //�v���t�@@�����X�� = �v���t�@@�����X��.�؋����s���z��Ǘ�
        //�v���t�@@�����X���̘A�� = 1
        String l_strValue = this.getValue(
            this.getBranchId(),
            WEB3BranchPreferencesNameDef.IFODEPOSIT_LACKCHARGE_NON_MANAGEMENT,
            NAME_SERIAL_NO);

        //�v���t�@@�����X�̒l = "�Ǘ����Ȃ�"�̏ꍇ�A true��ԋp����B
        if (WEB3IfodepositLackchargeNonManagementDef.NON_MANAGEMENT.equals(l_strValue))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is�A�������J�z���{) <BR>
     * <BR>
     * �A�������̌J�z���s���Ă��邩�𔻒肷��B <BR>
     * <BR>
     * �@@�P�j����.�����^�C�v�ɑΉ�����v���t�@@�����X���̘A�Ԃ��擾����B <BR>
     * <BR>
     * �@@�@@�@@�P�|�P�j����.�����^�C�v = "����"�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�v���t�@@�����X���̘A�� = "1�F�����E�M�p" <BR>
     * <BR>
     * �@@�@@�@@�P�|�Q�j����.�����^�C�v = "�敨�I�v�V����"�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�v���t�@@�����X���̘A�� = "2�F�敨�I�v�V����" <BR>
     * <BR>
     * �@@�Q�j���X�v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾����B  <BR>
     * <BR>
     * �@@�@@�@@[��������] <BR>
     * �@@�@@�@@�@@���XID = this.getBranchId()�̖߂�l <BR>
     * �@@�@@�@@�@@AND�@@�v���t�@@�����X�� = �v���t�@@�����X��.�A�������J�z���{�敪 <BR>
     * �@@�@@�@@�@@AND�@@�v���t�@@�����X���̘A�� = �P�j�Ŏ擾�����v���t�@@�����X���̘A�� <BR>
     * <BR>
     * �@@�@@�@@�@@����L�����Ń��R�[�h���擾�o���Ȃ��ꍇ�Afalse�i���{���Ȃ��j��ԋp����B <BR>
     * <BR>
     * �@@�R�j�擾���R�[�h.�v���t�@@�����X�̒l = "���{����"�̏ꍇ�A true��ԋp����B <BR>
     * �@@�@@�@@�ȊO�̏ꍇ�́A false��ԋp����B<BR>
     * <BR>
     * @@param l_productType �����^�C�v
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isSuccOrderCarryoverEnforcemented(ProductTypeEnum l_productType)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isSuccOrderCarryoverEnforcemented(ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //�P�j����.�����^�C�v�ɑΉ�����v���t�@@�����X���̘A�Ԃ��擾
        String l_strNameSerialNo = null;
        if (l_productType.equals(ProductTypeEnum.EQUITY))
        {
            l_strNameSerialNo = WEB3BranchNameSerialNoDef.EQUITY_MARGIN;
        }
        else if (l_productType.equals(ProductTypeEnum.IFO))
        {
            l_strNameSerialNo = WEB3BranchNameSerialNoDef.FUTURE_OPTION;
        }

        //�Q�j���X�v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾
        String l_strValue = this.getValue(
            this.getBranchId(),
            WEB3BranchPreferencesNameDef.TRIGGERORDER_SUCORDER_CARRYOVER,
            Integer.parseInt(l_strNameSerialNo));

        //��L�����Ń��R�[�h���擾�o���Ȃ��ꍇ�Afalse�i���{���Ȃ��j��ԋp����
        if (l_strValue == null || "".equals(l_strValue.trim()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�R�j�擾���R�[�h.�v���t�@@�����X�̒l = "���{����"�̏ꍇ�A true��ԋp����
        if (WEB3TriggerorderSucorderCarryoverDef.EXECUTE.equals(l_strValue))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get�������[���A�h���X�Ή����{)<BR>
     * ���X�v���t�@@�����X���Q�Ƃ��A�擾�����v���t�@@�����X�̒l��ԋp����B<BR>
     * <BR>
     * �P�j���X�e�[�u�����ȉ��̏����Ō������A���XID���擾����B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@���X�R�[�h = ����.���X�R�[�h<BR>
     * �@@�@@�،��R�[�h = ����.�،��R�[�h<BR>
     * <BR>
     * �Q�j���X�v���t�@@�����X�e�[�u�����ȉ��̏����Ō�������B<BR>
     *  <BR>
     * �@@�@@[����]<BR>
     * �@@�@@���XID = �P�j�Ŏ擾�������XID<BR>
     * �@@�@@�v���t�@@�����X�� = �i�����j�v���t�@@�����X��<BR>
     * �@@�@@�v���t�@@�����X���̘A�� = �i�����j�v���t�@@�����X���̘A��<BR>
     * �@@�@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     *  <BR>
     * �R�j��������.�v���t�@@�����X�̒l��ԋp����B<BR>
     * <BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strName - (�v���t�@@�����X��)<BR>
     * �v���t�@@�����X��<BR>
     * @@param l_intNameSerialNo - (�v���t�@@�����X���̘A��)<BR>
     * �v���t�@@�����X���̘A��<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getMultiMailAddressEnforcement(
        String l_strBranchCode, String l_strInstitutionCode, String l_strName, int l_intNameSerialNo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMultiMailAddressEnforcement(String, String, String, int)";
        log.entering(STR_METHOD_NAME);

        long l_lngBranchId = 0;
        BranchPreferencesRow l_branchPreferencesRow = null;

        try
        {
            //�P�j���X�e�[�u�����ȉ��̏����Ō������A���XID���擾����B
            BranchRow l_branchRow = BranchDao.findRowByInstitutionCodeBranchCode(
                l_strInstitutionCode,
                l_strBranchCode);

            //���XID = �P�j�Ŏ擾�������XID
            if (l_branchRow != null)
            {
                l_lngBranchId = l_branchRow.getBranchId();
            }

            //�Q�j���X�v���t�@@�����X�e�[�u�����ȉ��̏����Ō�������B 
            l_branchPreferencesRow =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_lngBranchId,
                    l_strName,
                    l_intNameSerialNo);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
        if (l_branchPreferencesRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�R�j��������.�v���t�@@�����X�̒l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_branchPreferencesRow.getValue();
    }
}
@
