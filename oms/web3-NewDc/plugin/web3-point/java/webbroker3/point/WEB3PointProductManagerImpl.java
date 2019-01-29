head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.01.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointProductManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g���i�}�l�[�W��Impl(WEB3PointProductManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.point.data.PointCategoryMasterDao;
import webbroker3.point.data.PointCategoryMasterParams;
import webbroker3.point.data.PointCategoryMasterRow;
import webbroker3.point.data.PointPremiumMasterDao;
import webbroker3.point.data.PointPremiumMasterParams;
import webbroker3.point.data.PointPremiumMasterRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�|�C���g���i�}�l�[�W��Impl)<BR>
 * �|�C���g���i�}�l�[�W�������N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3PointProductManagerImpl implements WEB3PointProductManager 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3PointProductManagerImpl.class);
    
    /**
     * (get�J�e�S���[)<BR>
     * �،���ЃR�[�h�A�J�e�S���[�ԍ�����A�|�C���g�J�e�S���[�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �|�C���g�J�e�S���[�}�X�^�[DAO�ɂāA�����������Ƃ��郌�R�[�h���擾����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strCategoryNo - (�J�e�S���[�ԍ�)<BR>
     * �J�e�S���[�ԍ�<BR>
     * 
     * @@return webbroker3.point.WEB3PointCategory
     * @@roseuid 418F3D1E0136
     */
    public WEB3PointCategory getCategory(String l_strInstitutionCode, String l_strCategoryNo) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCategory(String , String )";
        log.entering(STR_METHOD_NAME);
        
        PointCategoryMasterParams l_params = null;
        int l_intCategoryNo = 0;
        
        if (!WEB3StringTypeUtility.isInteger(l_strCategoryNo))
        {
            String l_strMessage = "parameter error! categoryNo = " + l_strCategoryNo;
            log.debug(l_strMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        l_intCategoryNo = Integer.parseInt(l_strCategoryNo);
        try
        {
            l_params = new PointCategoryMasterParams(PointCategoryMasterDao.findRowByPk(l_strInstitutionCode, l_intCategoryNo));//DataFindException,DataQueryException,DataNetworkException
        }
        catch (DataFindException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        
        log.exiting(STR_METHOD_NAME);     
        return new WEB3PointCategory(l_params);
    }
    
    /**
     * (get�J�e�S���[)<BR>
     * �،���ЃR�[�h����A���̏،���Ђ������Ă���J�e�S���[�̔z����擾����B<BR>
     * <BR>
     * �P�j�ȉ��̏����Ń|�C���g�J�e�S���[�}�X�^�[�e�[�u������������B<BR>
     * <BR>
     * ���������F �،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * order by�F �J�e�S���[�ԍ�<BR>
     * <BR>
     * �Q�j�擾�������R�[�h��z��ɂ��āA�ԋp����B<BR>
     *    �������̌���0���������ꍇ�́A�v�f0�̔z���ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@return webbroker3.point.WEB3PointCategory[]
     * @@roseuid 418F3D1E0165
     */
    public WEB3PointCategory[] getCategories(String l_strInstitutionCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCategories(String)";
        log.entering(STR_METHOD_NAME);
        
        String l_strWhere = "institution_code = ?";
        Object[] l_bindObjs = new Object[]{l_strInstitutionCode}; 
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                PointCategoryMasterRow.TYPE,
                l_strWhere,
                "category_no",
                null,
                l_bindObjs);//DataFindException,DataQueryException,DataNetworkException 
        }
        catch (DataFindException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        WEB3PointCategory[] l_categories = null;
        if (l_lisRows != null)
        {
            int l_intCategoryCount = l_lisRows.size();
            l_categories = new WEB3PointCategory[l_intCategoryCount];
            for (int i = 0; i < l_intCategoryCount; i++)
            {
                l_categories[i] = new WEB3PointCategory((PointCategoryMasterParams)l_lisRows.get(i)); 
            }
        }
        else
        {
            l_categories = new WEB3PointCategory[0];
        }
        
        log.exiting(STR_METHOD_NAME);     
        return l_categories;
    }
    
    /**
     * (get�i�i)<BR>
     * �،���ЃR�[�h�A�i�i�ԍ�����A�|�C���g�i�i�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �|�C���g�i�i�}�X�^�[DAO�ɂāA�����������Ƃ��郌�R�[�h���擾���ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strPremiumNo - (�i�i�ԍ�)<BR>
     * �i�i�ԍ�<BR>
     * 
     * @@return webbroker3.point.WEB3PointPremium
     * @@roseuid 418F3D1E0194
     */
    public WEB3PointPremium getPremium(String l_strInstitutionCode, String l_strPremiumNo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getPremium(String , String )";
        log.entering(STR_METHOD_NAME);
        
        PointPremiumMasterParams l_params = null;
        try
        {
            l_params = new PointPremiumMasterParams(PointPremiumMasterDao.findRowByPk(l_strInstitutionCode, l_strPremiumNo));//DataFindException,DataQueryException,DataNetworkException
        }
        catch (DataFindException l_e)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        
        log.exiting(STR_METHOD_NAME);     
        return new WEB3PointPremium(l_params);
    }
    
    /**
     * (get�i�i)<BR>
     * �،���ЃR�[�h�A�J�e�S���[�ԍ�����A�|�C���g�i�i�̔z����擾����B<BR>
     * <BR>
     * �P�j�ȉ��̏����Ń|�C���g�i�i�}�X�^�[�e�[�u������������B<BR>
     * <BR>
     * ���������F �،���ЃR�[�h = ����.�،���ЃR�[�h and<BR>
     *               �J�e�S���[�ԍ� = ����.�J�e�S���[�ԍ�<BR>
     * order by�F �i�i�ԍ�<BR>
     * <BR>
     * �Q�j�擾�������R�[�h��z��ɂ��āA�ԋp����B<BR>
     *    �������̌���0���������ꍇ�́A�v�f0�̔z���ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strCategoryNo - (�J�e�S���[�ԍ�)<BR>
     * �J�e�S���[�ԍ�<BR>
     * 
     * @@return webbroker3.point.WEB3PointPremium[]
     * @@roseuid 418F3D1E01C3
     */
    public WEB3PointPremium[] getPremiums(String l_strInstitutionCode, String l_strCategoryNo)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getPremiums(String , String )";
        log.entering(STR_METHOD_NAME);
        
        String l_strWhere = "institution_code = ? AND category_no = ?";
        Object[] l_bindObjs = new Object[]{l_strInstitutionCode, l_strCategoryNo}; 
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                PointPremiumMasterRow.TYPE,
                l_strWhere,
                "premium_no",
                null,
                l_bindObjs);//DataFindException,DataQueryException,DataNetworkException 
        }
        catch (DataFindException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        WEB3PointPremium[] l_premiums = null;
        if (l_lisRows != null)
        {
            int l_intPremiumCount = l_lisRows.size();
            l_premiums = new WEB3PointPremium[l_intPremiumCount];
            for (int i = 0; i < l_intPremiumCount; i++)
            {
                l_premiums[i] = new WEB3PointPremium((PointPremiumMasterParams)l_lisRows.get(i)); 
            }
        }
        else
        {
            l_premiums = new WEB3PointPremium[0];
        }
        
        log.exiting(STR_METHOD_NAME);     
        return l_premiums;
    }
    
    /**
     * (get�戵�\�i�i)<BR>
     * �،���ЃR�[�h�A�J�e�S���[�ԍ�����A���ݎ戵�\�ȃ|�C���g�i�i�̔z����擾����B<BR>
     * <BR>
     * �P�j�ȉ��̏����Ń|�C���g�i�i�}�X�^�[�e�[�u������������B<BR>
     * <BR>
     * ���������F �،���ЃR�[�h = ����.�،���ЃR�[�h and<BR>
     *               �J�e�S���[�ԍ� = ����.�J�e�S���[�ԍ� and<BR>
     *               ���ݓ��� >= �񋟊J�n���� and<BR>
     *               ���ݓ��� < �񋟏I������<BR>
     * order by�F �i�i�ԍ�<BR>
     * <BR>
     * �Q�j�擾�������R�[�h��z��ɂ��āA�ԋp����B<BR>
     *    �������̌���0���������ꍇ�́A�v�f0�̔z���ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strCategoryNo - (�J�e�S���[�ԍ�)<BR>
     * �J�e�S���[�ԍ�<BR>
     * 
     * @@return webbroker3.point.WEB3PointPremium[]
     * @@roseuid 41A43B15015F
     */
    public WEB3PointPremium[] getHandingPossiblePremium(String l_strInstitutionCode, String l_strCategoryNo) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getHandingPossiblePremium(String , String )";
        log.entering(STR_METHOD_NAME);
        
        String l_strWhere = "institution_code = ? AND category_no = ? AND start_date_time <= ? AND end_date_time > ?";
        Object[] l_bindObjs = new Object[]{l_strInstitutionCode, l_strCategoryNo, GtlUtils.getSystemTimestamp(), GtlUtils.getSystemTimestamp()}; 
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                PointPremiumMasterRow.TYPE,
                l_strWhere,
                "premium_no",
                null,
                l_bindObjs);//DataFindException,DataQueryException,DataNetworkException 
        }
        catch (DataFindException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        WEB3PointPremium[] l_premiums = null;
        if (l_lisRows != null)
        {
            int l_intPremiumCount = l_lisRows.size();
            l_premiums = new WEB3PointPremium[l_intPremiumCount];
            for (int i = 0; i < l_intPremiumCount; i++)
            {
                l_premiums[i] = new WEB3PointPremium((PointPremiumMasterParams)l_lisRows.get(i)); 
            }
        }
        else
        {
            l_premiums = new WEB3PointPremium[0];
        }
        
        log.exiting(STR_METHOD_NAME);     
        return l_premiums;
    }
    
    /**
     * (validate�J�e�S���[���e)<BR>
     * �J�e�S���[���e�̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�J�e�S���[���`�F�b�N<BR>
     * <BR>
     *    ����.�J�e�S���[��.length() > 80 or ����.�J�e�S���[�� = null <BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01886<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01705<BR>
     * <BR>
     * �Q�j�T�v�`�F�b�N<BR>
     * <BR>
     *    ����.�T�v.length() > 400<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01706<BR>
     * <BR>
     * @@param l_strCategoryName - (�J�e�S���[��)<BR>
     * �J�e�S���[��<BR>
     * 
     * @@param l_strOutline - (�T�v)<BR>
     * �T�v<BR>
     * @@roseuid 4191859002B7
     */
    public void validateCategorySpec(String l_strCategoryName, String l_strOutline) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateCategorySpec(String , String )";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�J�e�S���[���`�F�b�N
        if (l_strCategoryName == null)
        {
            String l_strMessage = "categoryName length error! categoryName = " + l_strCategoryName;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01886, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        if (WEB3StringTypeUtility.getByteLength(l_strCategoryName) > 80)
        {
            String l_strMessage = "categoryName length error! categoryName = " + l_strCategoryName;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01705, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        // �Q�j�T�v�`�F�b�N
        if (l_strOutline != null && WEB3StringTypeUtility.getByteLength(l_strOutline) > 400)
        {
            String l_strMessage = "outline length error! outline = " + l_strOutline;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01706, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (saveNew�J�e�S���[)<BR>
     * �V�K�̃J�e�S���[��ǉ�(insert)����B<BR>
     * <BR>
     * �P�j�J�e�S���[�ԍ���V�K�ɍ̔Ԃ���B <BR>
     *    ����.�J�e�S���[.setNew�J�e�S���[�ԍ�()���R�[������B <BR>
     * <BR>
     * �Q�j�s�I�u�W�F�N�g�擾 <BR>
     *    ����.�J�e�S���[.getDataSourceObject()�ɂčs�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �R�j�擾�����s�I�u�W�F�N�g�Ɉȉ��̒ʂ�A���ڂ̏����l���Z�b�g����B <BR>
     *    �쐬���� = TradingSystem.getSystemTimestamp() <BR>
     *    �X�V���� = TradingSystem.getSystemTimestamp() <BR>
     *    �X�V�҃R�[�h = ����.�Ǘ��҂̊Ǘ��҃R�[�h<BR>
     * <BR>
     * �S�jDB�X�V <BR>
     *    �s�I�u�W�F�N�g�̓��e�Ń|�C���g�J�e�S���[�}�X�^�[�e�[�u���ɍs��}���iinsert�j����B <BR>
     * <BR>
     * @@param l_category - (�J�e�S���[)<BR>
     * �|�C���g�J�e�S���[�I�u�W�F�N�g<BR>
     * 
     * 
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@roseuid 4191A00C02B0
     */
    public void saveNewCategory(WEB3PointCategory l_category, WEB3Administrator l_admin) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewCategory(WEB3PointCategory , WEB3Administrator )";
        log.entering(STR_METHOD_NAME);
        
        if (l_category == null)
        {
            String l_strMessage = "parameter error! category = null.";
            log.debug(l_strMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);

        }
        if (l_admin == null)
        {
            String l_strMessage = "parameter error! admin = null.";
            log.debug(l_strMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);

        }
        
        // �P�j�J�e�S���[�ԍ���V�K�ɍ̔Ԃ���B
        l_category.setNewCategoryNo(l_admin.getInstitutionCode());
 
        // �Q�j�s�I�u�W�F�N�g�擾 
        PointCategoryMasterParams l_params = (PointCategoryMasterParams)l_category.getDataSourceObject();

        // �R�j�擾�����s�I�u�W�F�N�g�Ɉȉ��̒ʂ�A���ڂ̏����l���Z�b�g����B
        Timestamp l_tsCurrentTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        if (l_params == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        l_params.setCreatedTimestamp(l_tsCurrentTime);
        l_params.setLastUpdatedTimestamp(l_tsCurrentTime);
        l_params.setLastUpdater(l_admin.getAdministratorCode());
        
        // �S�jDB�X�V 
        try
        {
            Processors.getDefaultProcessor().doInsertQuery(l_params);//DataQueryException,DataNetworkException
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate�����J�e�S���[���e)<BR>
     * ���������J�e�S���[���e�̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�J�e�S���[���e�̃`�F�b�N���s���B<BR>
     *    this.validate�J�e�S���[���e()���\�b�h���R�[������B<BR>
     * <BR>
     *    [����]<BR>
     *    �J�e�S���[���F ����.�J�e�S���[��<BR>
     *    �T�v�F ����.�T�v<BR>
     * <BR>
     * �Q�j�����ӏ��̃`�F�b�N���s���B<BR>
     *    �ȉ��̏����ɍ��v����ꍇ�A�܂����������ĂȂ��ꍇ�͗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01707<BR>
     * <BR>
     *    �J�e�S���[.�J�e�S���[�� = ����.�J�e�S���[�� and<BR>
     *    �J�e�S���[.�J�e�S���[�T�v = ����.�T�v<BR>
     * <BR>
     * @@param l_strCategoryName - (�J�e�S���[��)<BR>
     * �J�e�S���[��<BR>
     * 
     * @@param l_strOutline - (�T�v)<BR>
     * �T�v<BR>
     * 
     * @@param l_category - (�J�e�S���[)<BR>
     * �|�C���g�J�e�S���[�I�u�W�F�N�g<BR>
     * @@roseuid 4191B23302A1
     */
    public void validateChangeCategorySpec(String l_strCategoryName, String l_strOutline, WEB3PointCategory l_category)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateChangeCategorySpec(String , String , WEB3PointCategory )";
        log.entering(STR_METHOD_NAME);
        
        if (l_category == null)
        {
            String l_strMessage = "parameter error! category = null.";
            log.debug(l_strMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);

        }
        // �P�j�J�e�S���[���e�̃`�F�b�N���s���B
        this.validateCategorySpec(l_strCategoryName, l_strOutline);
        
        // �Q�j�����ӏ��̃`�F�b�N���s���B
        boolean l_bolCategoryNameEquals = false;
        if (l_strCategoryName == null && l_category.getCategoryName() == null)
        {
            l_bolCategoryNameEquals = true;
        }
        else if (l_strCategoryName == null || l_category.getCategoryName() == null)
        {
            l_bolCategoryNameEquals = false;
        }
        else
        {
            l_bolCategoryNameEquals = l_strCategoryName.equals(l_category.getCategoryName());
        }        
        
        boolean l_bolCategoryOutlineEquals = false;
        if (l_strOutline == null && l_category.getCategoryOutline() == null)
        {
            l_bolCategoryOutlineEquals = true;
        }
        else if (l_strOutline == null || l_category.getCategoryOutline() == null)
        {
            l_bolCategoryOutlineEquals = false;
        }
        else
        {
            l_bolCategoryOutlineEquals = l_strOutline.equals(l_category.getCategoryOutline());
        }        

        if (l_bolCategoryNameEquals && l_bolCategoryOutlineEquals)
        {
            String l_strMessage = "ChangeCategory error! " 
                + l_strCategoryName 
                + ", " + l_strOutline 
                + "; " + l_category.getCategoryName()
                + ", " + l_category.getCategoryOutline();
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01707, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (save�J�e�S���[)<BR>
     * �J�e�S���[���X�V(update)����B<BR>
     * <BR>
     * �P�j�s�I�u�W�F�N�g�擾 <BR>
     *    ����.�J�e�S���[.getDataSourceObject()�ɂčs�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �Q�j�擾�����s�I�u�W�F�N�g�Ɉȉ��̒ʂ�A���ڂ̒l���Z�b�g����B <BR>
     *    �X�V���� = TradingSystem.getSystemTimestamp() <BR>
     *    �X�V�҃R�[�h = ����.�Ǘ��҂̊Ǘ��҃R�[�h<BR>
     * <BR>
     * �R�jDB�X�V <BR>
     *    �s�I�u�W�F�N�g�̓��e�Ń|�C���g�J�e�S���[�}�X�^�[�e�[�u���̍s���X�V�iupdate�j����B <BR>
     * <BR>
     * @@param l_category - (�J�e�S���[)<BR>
     * �|�C���g�J�e�S���[�I�u�W�F�N�g<BR>
     * 
     * 
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@roseuid 4191B6BE0316
     */
    public void saveCategory(WEB3PointCategory l_category, WEB3Administrator l_admin) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveCategory(WEB3PointCategory , WEB3Administrator )";
        log.entering(STR_METHOD_NAME);
        
        if (l_category == null)
        {
            String l_strMessage = "parameter error! category = null.";
            log.debug(l_strMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);

        }
        if (l_admin == null)
        {
            String l_strMessage = "parameter error! admin = null.";
            log.debug(l_strMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);

        }

        // �P�j�s�I�u�W�F�N�g�擾
        PointCategoryMasterParams l_params = (PointCategoryMasterParams)l_category.getDataSourceObject();

        // �Q�j�擾�����s�I�u�W�F�N�g�Ɉȉ��̒ʂ�A���ڂ̒l���Z�b�g����B 
        Timestamp l_tsCurrentTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        if (l_params == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        l_params.setLastUpdatedTimestamp(l_tsCurrentTime);
        l_params.setLastUpdater(l_admin.getAdministratorCode());

        // �R�jDB�X�V 
        try
        {
            Processors.getDefaultProcessor().doUpdateQuery(l_params);//DataQueryException,DataNetworkException
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
                
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate�戵�i�i)<BR>
     * �����̏،���ЃR�[�h�A�J�e�S���[�ԍ��Ɋ֘A����i�i���戵���Ă��邩�ǂ������`�F�b�N����B<BR>
     * <BR>
     * �P�j�ȉ��̏����ŁA�|�C���g�i�i�}�X�^�[�e�[�u������������B<BR>
     * <BR>
     * ���������F �،���ЃR�[�h = ����.�،���ЃR�[�h and<BR>
     *               �J�e�S���[�ԍ� = ����.�J�e�S���[�ԍ�<BR>
     * <BR>
     * �Q�j�擾�ł������� > 0 �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01708<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strCategoryNo - (�J�e�S���[�ԍ�)<BR>
     * �J�e�S���[�ԍ�<BR>
     * @@roseuid 4191C1390306
     */
    public void validateHandingPremium(String l_strInstitutionCode, String l_strCategoryNo)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateHandingPremium(String , String )";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�ȉ��̏����ŁA�|�C���g�i�i�}�X�^�[�e�[�u������������B
        String l_strWhere = "institution_code = ? AND category_no = ?";
        Object[] l_bindObjs = new Object[]{l_strInstitutionCode, l_strCategoryNo}; 
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                PointPremiumMasterRow.TYPE,
                l_strWhere,
                l_bindObjs);//DataFindException,DataQueryException,DataNetworkException 
        }
        catch (DataFindException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        
        // �Q�j�擾�ł������� > 0 �̏ꍇ�A��O���X���[����B
        if (l_lisRows != null && l_lisRows.size() > 0)
        {
            String l_strMessage = "�i�i���� > 0 ! " 
                + "InstitutionCode = " + l_strInstitutionCode 
                + ", CategoryNo = " + l_strCategoryNo;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01708, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate�d���i�i�ԍ�)<BR>
     * �i�i�ԍ����d�����ĂȂ������`�F�b�N����B<BR>
     * <BR>
     * �ȉ��̏����Ń|�C���g�i�i�}�X�^�[�e�[�u�����������A���R�[�h���擾�ł����ꍇ�͗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01709<BR>
     * <BR>
     * ���������F<BR>
     *    �،���ЃR�[�h = ����.�،���ЃR�[�h and<BR>
     *    �i�i�ԍ� = ����.�i�i�ԍ�<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strPremiumNo - (�i�i�ԍ�)<BR>
     * �i�i�ԍ�<BR>
     * @@roseuid 41932C1B0156
     */
    public void validateDupliPremiumNo(String l_strInstitutionCode, String l_strPremiumNo)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateDupliPremiumNo(String , String )";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�ȉ��̏����ŁA�|�C���g�i�i�}�X�^�[�e�[�u������������B
        String l_strWhere = "institution_code = ? AND premium_no = ?";
        Object[] l_bindObjs = new Object[]{l_strInstitutionCode, l_strPremiumNo}; 
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                PointPremiumMasterRow.TYPE,
                l_strWhere,
                l_bindObjs);//DataFindException,DataQueryException,DataNetworkException 
        }
        catch (DataFindException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        
        // �Q�j�擾�ł������� > 0 �̏ꍇ�A��O���X���[����B
        if (l_lisRows != null && l_lisRows.size() > 0)
        {
            String l_strMessage = "�i�i���� > 0 ! " 
                + "InstitutionCode = " + l_strInstitutionCode 
                + ", PremiumNo = " + l_strPremiumNo;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01709, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate�i�i���e)<BR>
     * �i�i�̓��e���`�F�b�N����B<BR>
     * <BR>
     * �P�j�i�i�ԍ��`�F�b�N<BR>
     * <BR>
     * �P�|�P�j<BR>
     * <BR>
     *    ����.�i�i�ԍ�.length() > 5 or ����.�i�i�ԍ�= null <BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01727<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01710<BR>
     * <BR>
     * �P�|�Q�j<BR>
     * <BR>
     *    ����.�i�i�ԍ��Ɂu���p�p�����v�A�u-�i�n�C�t���j�v�ȊO�̒l���܂܂�Ă���ꍇ�A<BR>
     *    ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01711<BR>
     * <BR>
     * �Q�j�i�i���`�F�b�N<BR>
     * <BR>
     *    ����.�i�i��.length() > 80 or ����.�i�i��= null <BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01887<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01712<BR>
     * <BR>
     * <BR>
     * �R�j�K�v�|�C���g�`�F�b�N<BR>
     * <BR>
     *    ����.�K�v�|�C���g.length() > 8 or<BR>
     *    ����.�K�v�|�C���g <= 0 or<BR>
     *    ����.�K�v�|�C���g != ����<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01713<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01714<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01715<BR>
     * <BR>
     * �S�j�񋟊��ԃ`�F�b�N<BR>
     * <BR>
     * �S�|�P�j<BR>
     * <BR>
     *    ����.�񋟊J�n���� = null or<BR>
     *    ����.�񋟏I������ = null<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01716<BR>
     * <BR>
     * �S�|�Q�j<BR>
     * <BR>
     *    ����.�񋟊J�n���� >= ����.�񋟏I������<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01717<BR>
     * <BR>
     * @@param l_strPremiumNo - (�i�i�ԍ�)<BR>
     * �i�i�ԍ�<BR>
     * 
     * @@param l_strPremiumName - (�i�i��)<BR>
     * �i�i��<BR>
     * 
     * @@param l_strRequiredPoint - (�K�v�|�C���g)<BR>
     * �K�v�|�C���g<BR>
     * 
     * @@param l_datStartDateTime - (�񋟊J�n����)<BR>
     * �񋟊J�n����<BR>
     * 
     * @@param l_datEndDateTime - (�񋟏I������)<BR>
     * �񋟏I������<BR>
     * @@roseuid 41932C1B0185
     */
    public void validatePremiumSpec(
        String l_strPremiumNo, 
        String l_strPremiumName, 
        String l_strRequiredPoint, 
        Date l_datStartDateTime, 
        Date l_datEndDateTime) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validatePremiumSpec(String , String , String , Date , Date )";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�i�i�ԍ��`�F�b�N
        // �P�|�P�j ����.�i�i�ԍ�.length() > 5 or        ����.�i�i�ԍ�= null 
        if (l_strPremiumNo == null)
        {
            String l_strMessage = "�i�i�ԍ�error ! �i�i�ԍ� = " + l_strPremiumNo;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01727, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        if (WEB3StringTypeUtility.getByteLength(l_strPremiumNo) > 5)
        {
            String l_strMessage = "�i�i�ԍ�error ! �i�i�ԍ� = " + l_strPremiumNo;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01710, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        // �P�|�Q�j����.�i�i�ԍ��Ɂu���p�p�����v�A�u-�i�n�C�t���j�v�ȊO�̒l���܂܂�Ă���ꍇ
        if (l_strPremiumNo != null)
        {
            if (!WEB3StringTypeUtility.isSingle(l_strPremiumNo))
            {
                String l_strMessage = "�i�i�ԍ�error ! �i�i�ԍ� = " + l_strPremiumNo;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01711, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            else
            {
                int l_intLen = WEB3StringTypeUtility.getByteLength(l_strPremiumNo);
                for (int i = 0; i < l_intLen; i++) 
                {
                    char l_ch = l_strPremiumNo.charAt(i);
                    if (!(WEB3StringTypeUtility.isSingleEng(l_ch)
                        || WEB3StringTypeUtility.isSingleNum(l_ch)
                        || (l_ch == '-')))
                    {
                        String l_strMessage = "�i�i�ԍ�error ! �i�i�ԍ� = " + l_strPremiumNo;
                        log.debug(l_strMessage);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01711, 
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_strMessage);
                    }
                }
            }
        }

        // �Q�j�i�i���`�F�b�N
        if (l_strPremiumName == null)
        {
            String l_strMessage = "�i�i��error ! �i�i�� = " + l_strPremiumName;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01887, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        if (WEB3StringTypeUtility.getByteLength(l_strPremiumName) > 80)
        {
            String l_strMessage = "�i�i��error ! �i�i�� = " + l_strPremiumName;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01712, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        // �R�j�K�v�|�C���g�`�F�b�N
        if (l_strRequiredPoint != null && WEB3StringTypeUtility.getByteLength(l_strRequiredPoint) > 8)
        {
            String l_strMessage = "�K�v�|�C���gerror ! �K�v�|�C���g = " + l_strRequiredPoint;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01713, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        if (!WEB3StringTypeUtility.isNumber(l_strRequiredPoint))
        {
            String l_strMessage = "�K�v�|�C���gerror ! �K�v�|�C���g = " + l_strRequiredPoint;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01715, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        long l_lngRequiredPoint = Long.parseLong(l_strRequiredPoint);
        if (l_lngRequiredPoint <= 0)
        {
            String l_strMessage = "�K�v�|�C���gerror ! �K�v�|�C���g = " + l_strRequiredPoint;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01714, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        // �S�j�񋟊��ԃ`�F�b�N
        // �S�|�P�j����.�񋟊J�n���� = null or ����.�񋟏I������ = null
        if (l_datStartDateTime == null || l_datEndDateTime == null)
        {
            String l_strMessage = "�񋟓���error ! �񋟊J�n����=" + l_datStartDateTime + ",�񋟏I������ ="  + l_datEndDateTime;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01716, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        // �S�|�Q�j����.�񋟊J�n���� >= ����.�񋟏I������
        if (WEB3DateUtility.compareToSecond(l_datStartDateTime, l_datEndDateTime) >= 0)
        {
            String l_strMessage = "�񋟓���error ! �񋟊J�n����=" + l_datStartDateTime + ",�񋟏I������ ="  + l_datEndDateTime;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01717, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (saveNew�i�i)<BR>
     * �V�K�̌i�i��ǉ�(insert)����B<BR>
     * <BR>
     * �P�j�s�I�u�W�F�N�g�擾 <BR>
     *    ����.�i�i.getDataSourceObject()�ɂčs�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �Q�j�擾�����s�I�u�W�F�N�g�Ɉȉ��̒ʂ�A���ڂ̏����l���Z�b�g����B <BR>
     *    �쐬���� = TradingSystem.getSystemTimestamp() <BR>
     *    �X�V���� = TradingSystem.getSystemTimestamp() <BR>
     *    �X�V�҃R�[�h = ����.�Ǘ��҂̊Ǘ��҃R�[�h<BR>
     * <BR>
     * �R�jDB�X�V <BR>
     *    �s�I�u�W�F�N�g�̓��e�Ń|�C���g�i�i�}�X�^�[�e�[�u���ɍs��}���iinsert�j����B <BR>
     * <BR>
     * @@param l_premium - (�i�i)<BR>
     * �|�C���g�i�i�I�u�W�F�N�g<BR>
     * 
     * 
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@roseuid 4193345D003D
     */
    public void saveNewPremium(WEB3PointPremium l_premium, WEB3Administrator l_admin)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " saveNewPremium(WEB3PointPremium , WEB3Administrator )";
        log.entering(STR_METHOD_NAME);
        
        if (l_premium == null)
        {
            String l_strMessage = "parameter error! premium = null.";
            log.debug(l_strMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);

        }
        if (l_admin == null)
        {
            String l_strMessage = "parameter error! admin = null.";
            log.debug(l_strMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);

        }

        // �P�j�s�I�u�W�F�N�g�擾
        PointPremiumMasterParams l_params = (PointPremiumMasterParams)l_premium.getDataSourceObject();
                 
        // �Q�j�擾�����s�I�u�W�F�N�g�Ɉȉ��̒ʂ�A���ڂ̏����l���Z�b�g����B 
        Timestamp l_tsCurrentTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        if (l_params == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        l_params.setCreatedTimestamp(l_tsCurrentTime);
        l_params.setLastUpdatedTimestamp(l_tsCurrentTime);
        l_params.setLastUpdater(l_admin.getAdministratorCode());

        // �R�jDB�X�V 
        try
        {
            Processors.getDefaultProcessor().doInsertQuery(l_params);//DataQueryException,DataNetworkException
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
    
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate�����i�i���e)<BR>
     * ���������i�i�̓��e���`�F�b�N����B<BR>
     * <BR>
     * �P�j�i�i���e�̃`�F�b�N���s���B<BR>
     *    this.validate�i�i���e()���\�b�h���R�[������B<BR>
     * <BR>
     *    [����]<BR>
     *    �i�i�ԍ��F ����.�i�i�ԍ�<BR>
     *    �i�i���F ����.�i�i��<BR>
     *    �K�v�|�C���g�F ����.�K�v�|�C���g<BR>
     *    �񋟊J�n�����F ����.�񋟊J�n����<BR>
     *    �񋟏I�������F ����.�񋟏I������<BR>
     * <BR>
     * �Q�j�����ӏ��̃`�F�b�N���s���B<BR>
     *    �ȉ��̏����ɍ��v����ꍇ�A�܂����������ĂȂ��ꍇ�͗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01718<BR>
     * <BR>
     *    �i�i.�i�i�� = ����.�i�i�� and<BR>
     *    �i�i.�K�v�|�C���g = ����.�K�v�|�C���g and<BR>
     *    �i�i.�񋟊J�n���� = ����.�񋟊J�n���� and<BR>
     *    �i�i.�񋟏I������ = ����.�񋟏I������<BR>
     * <BR>
     * @@param l_strPremiumNo - (�i�i�ԍ�)<BR>
     * �i�i�ԍ�<BR>
     * 
     * @@param l_strPremiumName - (�i�i��)<BR>
     * �i�i��<BR>
     * 
     * @@param l_strRequiredPoint - (�K�v�|�C���g)<BR>
     * �K�v�|�C���g<BR>
     * 
     * @@param l_datStartDateTime - (�񋟊J�n����)<BR>
     * �񋟊J�n����<BR>
     * 
     * @@param l_datEndDateTime - (�񋟏I������)<BR>
     * �񋟏I������<BR>
     * 
     * @@param l_pointPremium - (�i�i)<BR>
     * �|�C���g�i�i�I�u�W�F�N�g<BR>
     * @@roseuid 41932C1B01A4
     */
    public void validateChangePremiumSpec(
        String l_strPremiumNo, 
        String l_strPremiumName, 
        String l_strRequiredPoint, 
        Date l_datStartDateTime, 
        Date l_datEndDateTime, 
        WEB3PointPremium l_pointPremium) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChangePremiumSpec(String , String , String , Date , Date , WEB3PointPremium )";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�i�i���e�̃`�F�b�N���s���B
        this.validatePremiumSpec(
            l_strPremiumNo, 
             l_strPremiumName, 
             l_strRequiredPoint, 
             l_datStartDateTime, 
             l_datEndDateTime);
        
        // �Q�j�����ӏ��̃`�F�b�N���s���B
        if (l_pointPremium == null)
        {
            String l_strMessage = "parameter error! premium = null.";
            log.debug(l_strMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);

        }
        long l_lngRequiredPoint = Long.parseLong(l_strRequiredPoint);
        
        boolean l_bolPremiumNoEquals = false;
        if (l_strPremiumNo == null && l_pointPremium.getPremiumNo() == null)
        {
            l_bolPremiumNoEquals = true;
        }
        else if (l_strPremiumNo == null || l_pointPremium.getPremiumNo() == null)
        {
            l_bolPremiumNoEquals = false;
        }
        else
        {
            l_bolPremiumNoEquals = l_strPremiumNo.equals(l_pointPremium.getPremiumNo());
        }        
        
        boolean l_bolPremiumNameEquals = false;
        if (l_strPremiumName == null && l_pointPremium.getPremiumName() == null)
        {
            l_bolPremiumNameEquals = true;
        }
        else if (l_strPremiumName == null || l_pointPremium.getPremiumName() == null)
        {
            l_bolPremiumNameEquals = false;
        }
        else
        {
            l_bolPremiumNameEquals = l_strPremiumName.equals(l_pointPremium.getPremiumName());
        }        
        if (l_bolPremiumNoEquals
            && l_bolPremiumNameEquals 
            && l_lngRequiredPoint == l_pointPremium.getRequiredPoint()
            && WEB3DateUtility.compareToSecond(l_datStartDateTime, l_pointPremium.getStartDateTime()) == 0
            && WEB3DateUtility.compareToSecond(l_datEndDateTime, l_pointPremium.getEndDateTime()) == 0)
        {
            String l_strMessage = "ChangePremium error! " 
                + l_strPremiumNo 
                + ", " + l_strPremiumName
                + ", " + l_strRequiredPoint 
                + ", " + l_datStartDateTime 
                + ", " + l_datEndDateTime
                + "; " + l_pointPremium.getPremiumNo()
                + ", " + l_pointPremium.getPremiumName()
                + ", " + l_pointPremium.getRequiredPoint()
                + ", " + l_pointPremium.getStartDateTime()
                + ", " + l_pointPremium.getEndDateTime();
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01718, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (save�i�i)<BR>
     * �i�i���X�V(update)����B<BR>
     * <BR>
     * �P�j�s�I�u�W�F�N�g�擾 <BR>
     *    ����.�i�i.getDataSourceObject()�ɂčs�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �Q�j�擾�����s�I�u�W�F�N�g�Ɉȉ��̒ʂ�A���ڂ̒l���Z�b�g����B <BR>
     *    �X�V���� = TradingSystem.getSystemTimestamp() <BR>
     *    �X�V�҃R�[�h = ����.�Ǘ��҂̊Ǘ��҃R�[�h<BR>
     * <BR>
     * �R�jDB�X�V <BR>
     *    �s�I�u�W�F�N�g�̓��e�Ń|�C���g�i�i�}�X�^�[�e�[�u���̍s���X�V�iupdate�j����B <BR>
     * <BR>
     * @@param l_premium - (�i�i)<BR>
     * �|�C���g�i�i�I�u�W�F�N�g<BR>
     * 
     * 
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@roseuid 4193345F002D
     */
    public void savePremium(WEB3PointPremium l_premium, WEB3Administrator l_admin) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " savePremium(WEB3PointPremium , WEB3Administrator )";
        log.entering(STR_METHOD_NAME);
        
        if (l_premium == null)
        {
            String l_strMessage = "parameter error! premium = null.";
            log.debug(l_strMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);

        }
        if (l_admin == null)
        {
            String l_strMessage = "parameter error! admin = null.";
            log.debug(l_strMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);

        }

        // �P�j�s�I�u�W�F�N�g�擾
        PointPremiumMasterParams l_params = (PointPremiumMasterParams)l_premium.getDataSourceObject();

        // �Q�j�擾�����s�I�u�W�F�N�g�Ɉȉ��̒ʂ�A���ڂ̒l���Z�b�g����B 
        Timestamp l_tsCurrentTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        if (l_params == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        l_params.setLastUpdatedTimestamp(l_tsCurrentTime);
        l_params.setLastUpdater(l_admin.getAdministratorCode());

        // �R�jDB�X�V 
        try
        {
            Processors.getDefaultProcessor().doUpdateQuery(l_params);//DataQueryException,DataNetworkException
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
}
@
