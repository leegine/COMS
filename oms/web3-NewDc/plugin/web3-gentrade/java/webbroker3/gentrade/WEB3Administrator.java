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
filename	WEB3Administrator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���(WEB3Administrator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/14 羐� (���u) �V�K�쐬
Revesion History : 2006/08/30 �h�C (���u) �d�l�ύX ���f��206��Ή�
Revesion History : 2006/10/24 �h�C (���u) �d�l�ύX ���f��213��Ή�
*/
package webbroker3.gentrade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchCodeDef;
import webbroker3.common.define.WEB3DirAdminFlagDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorDao;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.AdministratorTypeDao;
import webbroker3.gentrade.data.AdministratorTypeRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * �iWEB3Administrator�j<BR>
 * �Ǘ��҃N���X<BR>
 */
public class WEB3Administrator implements BusinessObject
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3Administrator.class);

    /**
     * �Ǘ��ҍs�I�u�W�F�N�g<BR>
     * <BR>
     * �� �Ǘ���Params�N���X��DDL��莩����������B<BR>
     * �� DB���C�A�E�g�u�Ǘ��҃e�[�u���d�l.xls�v�Q�ƁB<BR>
     */
    private AdministratorParams administratorParams;

    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �Ǘ��҃I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_administratorParams - �Ǘ��ҍs�I�u�W�F�N�g<BR>
     * @@return WEB3Administrator
     * @@roseuid 40C3F3020222
     */
    public WEB3Administrator(AdministratorParams l_administratorParams)
    {
        final String STR_METHOD_NAME = "WEB3Administrator(AdministratorParams)";
        if (l_administratorParams == null)
        {
            log.error("�Ǘ��ҍs�I�u�W�F�N�g = null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Ǘ��ҍs�I�u�W�F�N�g = null");
        }
        this.administratorParams = l_administratorParams;
    }

    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �Ǘ��҃I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_lngAdministratorId - �Ǘ��҂h�c<BR>
     * @@return WEB3Administrator
     * @@throws WEB3SystemLayerException
     * @@roseuid 40C3F2680167
     */
    public WEB3Administrator(long l_lngAdministratorId)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "WEB3Administrator(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            AdministratorRow l_administratorRow = 
                AdministratorDao.findRowByPk(l_lngAdministratorId); 
            this.administratorParams = new AdministratorParams(l_administratorRow);
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

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �R���X�g���N�^ <BR>
     *  <BR>
     * �،���ЁC�Ǘ��҃R�[�h���A�Ǘ��҃C���X�^���X�𐶐�����B<BR>
     *  <BR>
     * �ȉ��̏����ŊǗ��҃e�[�u�����������A�擾�����s <BR>
     * �I�u�W�F�N�g�i�Ǘ��ҍs�j���w�肵�A�Ǘ��҃C���X�^���X�𐶐����ԋp����B<BR>
     *  <BR>
     * [����] <BR>
     *  <BR>
     * �،���ЃR�[�h = �،����.getInstitutionCode() <BR>
     * �Ǘ��҃R�[�h = �Ǘ��҃R�[�h<BR>
     *  <BR>
     * @@param l_institution - (�،����)<BR>
     * @@param l_strAdministratorCode - (�Ǘ��҃R�[�h)<BR>
     * @@return WEB3Administrator
     * @@throws WEB3SystemLayerException
     */
    public WEB3Administrator(Institution l_institution, String l_strAdministratorCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "WEB3Administrator(Institution, String)";
        log.entering(STR_METHOD_NAME);
        
        AdministratorRow l_administratorRow = null;
        try
        {
            l_administratorRow =
                AdministratorDao.findRowByInstitutionCodeAdministratorCode(
                    l_institution.getInstitutionCode(),
                    l_strAdministratorCode);
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
        if(l_administratorRow == null)
        {
            WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Ǘ��ҍs�I�u�W�F�N�g = null");
            throw l_sysException;
        }
        
        this.administratorParams = new AdministratorParams(l_administratorRow);
        log.exiting(STR_METHOD_NAME);
            
    }

    /**
     * �igetDataSourceObject�̎����j<BR>
     * <BR>
     * this.�Ǘ���Params��ԋp����B<BR>
     * @@return Object
     * @@roseuid 40C3F2D30157
     */
    public Object getDataSourceObject()
    {
        return this.administratorParams;
    }

    /**
     * (getInstanceFrom���O�C�����)
     * �istatic method�j<BR>
     * <BR>
     * ���O�C�������A���O�C�����Ă���Ǘ��҂̊Ǘ��҃C���X�^���X�� <BR>
     * �擾����B�Ǘ��҃I�u�W�F�N�g�𐶐�����B <BR>
     * <BR>
     * �P�j ���O�C���h�c�擾 <BR>
     * �@@   ���O�C���Z�L�����e�B�T�[�r�X�iOpLoginSecurityService�j��� <BR>
     *      ���O�C���h�c���擾����B <BR>
     * <BR>
     *         long l_loginId; <BR>
     *         OpLoginSecurityService l_opLoginSec = <BR>  
     *             (OpLoginSecurityService)Services.getService( <BR>
     *                 OpLoginSecurityService.class <BR>
     *                 ); <BR>
     *         l_loginId = l_opLoginSec.getLoginInfo().getLoginId(); <BR>
     *  <BR>
     * �Q�j ���O�C���h�c�ɂĊǗ��҃e�[�u�����������A�Ǘ��ҍs�I�u�W�F�N�g�� <BR>
     *      �擾����B���O�C���h�c�ɂĊǗ��҃e�[�u������������ <BR>
     *      �i�� �Ǘ���DAO.findRowByLoginId()�j�B<BR>
     *      �擾�����s�I�u�W�F�N�g�i�Ǘ��ҍs�j���w�肵�A <BR>
     *      �Ǘ��҃C���X�^���X�𐶐�����B <BR>
     *  <BR>
     * �R�j ���������C���X�^���X��ԋp����B <BR>
     * @@return WEB3Administrator
     * @@throws WEB3SystemLayerException
     * @@roseuid 40C3F16100BB
     */
    static public WEB3Administrator getInstanceFromLoginInfo()
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getInstanceFromLoginInfo()";

        //�P�j�@@���O�C���h�c�擾 
        //���O�C���Z�L�����e�B�T�[�r�X�iOpLoginSecurityService�j��胍�O�C���h�c���擾����B
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        long l_lngLoginId = l_opLoginSec.getLoginInfo().getLoginId();

        //�Q�j  ���O�C���h�c�ɂĊǗ��҃e�[�u�����������A�Ǘ��ҍs�I�u�W�F�N�g��
        //�擾����B���O�C���h�c�ɂĊǗ��҃e�[�u������������
        AdministratorRow l_administratorRow;
        try
        {
            l_administratorRow =
                AdministratorDao.findRowByLoginId(l_lngLoginId);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Administrator.class +"." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        if (l_administratorRow == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3Administrator.class +"." + STR_METHOD_NAME,
                "�Ǘ��ҍs�I�u�W�F�N�g = null");
        }
        //�R�j�@@���������C���X�^���X��ԋp����B
        return new WEB3Administrator(new AdministratorParams(l_administratorRow));

    }

    /**
     * (validate����) <BR>
     * �Ǘ��Ҍ����`�F�b�N���s���B <BR>
     * �i�񐄏��̃��\�b�h�j<BR>
     *  <BR> 
     * //TODO ���������ׂ̈�STUB <BR> 
     * //TODO �o�b�N���O�Ή����ɂ��ׂ�validate����(String, boolean)��<BR>
     * �g�p����悤�ɏC������K�v������܂��B <BR>
     * @@roseuid 40C3F5C901A5
     */
    public void validateAuthority()
    {

    }
    
    /**
     * (validate����) <BR>
     * �Ǘ��Ҍ����`�F�b�N���s���B <BR>
     *  <BR>
     * �ȉ��̏����ŊǗ��Ҍ����e�[�u������������B<BR> 
     * �s���擾�ł��Ȃ������ꍇ�́A�Ǘ��҂ɊY���I�y���[�V������ <BR>
     * �������Ȃ��Ɣ��肵��O���X���[����B<BR> 
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_01056<BR>
     *  <BR>
     * [����] <BR>
     *  <BR>
     * ���@@�X�V�����̏ꍇ�iis�X�V == true�j <BR> 
     * �Ǘ��Ҍ����e�[�u��.�،���ЃR�[�h = this.�Ǘ��ҍs.�،���ЃR�[�h <BR>
     * �Ǘ��Ҍ����e�[�u��.�������x�� = this.�Ǘ��ҍs.�������x�� <BR>
     * �Ǘ��Ҍ����e�[�u��.�@@�\�J�e�S���R�[�h = �@@�\�J�e�S���R�[�h <BR>
     * �Ǘ��Ҍ����e�[�u��.�X�V���t���O = BooleanEnum.TRUE <BR>
     *  <BR>
     * ���@@�Ɖ���̏ꍇ�iis�X�V == false�j<BR> 
     * �Ǘ��Ҍ����e�[�u��.�،���ЃR�[�h = this.�Ǘ��ҍs.�،���ЃR�[�h <BR>
     * �Ǘ��Ҍ����e�[�u��.�������x�� = this.�Ǘ��ҍs.�������x�� <BR>
     * �Ǘ��Ҍ����e�[�u��.�@@�\�J�e�S���R�[�h = �@@�\�J�e�S���R�[�h <BR>
     * �Ǘ��Ҍ����e�[�u��.�X�V���t���O in �iBooleanEnum.TRUE�CBooleanEnum.FALSE�j<BR> 
     *  <BR>
     * ���@@�Ǘ��Ҍ���Params�N���X��DDL��莩����������B<BR> 
     *  <BR>
     * @@param l_strTransactionCategory - (�@@�\�J�e�S���R�[�h)<BR>
     *    �� DB���C�A�E�g�u�Ǘ��Ҍ����e�[�u��.xls#�i�⑫�����j<BR>
     *    �@@�\�J�e�S���R�[�h�ꗗ�v�Q�ƁB 
     * @@param l_isUpdate - (is�X�V)<BR>
     *   �X�V�����K�v�ȏ����̏ꍇ��true�A�Q�Ƃ݂̂̏ꍇ��false���w�肷��B<BR> 
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validateAuthority(String l_strTransactionCategory,boolean l_isUpdate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAuthority(String, boolean)";
        log.entering(STR_METHOD_NAME);
        
        try
        {

            if (l_isUpdate)
            {
                //���@@�X�V�����̏ꍇ�iis�X�V == true�j 
                // �Ǘ��Ҍ����e�[�u��.�،���ЃR�[�h = this.�Ǘ��ҍs.�،���ЃR�[�h 
                // �Ǘ��Ҍ����e�[�u��.�������x�� = this.�Ǘ��ҍs.�������x�� 
                // �Ǘ��Ҍ����e�[�u��.�@@�\�J�e�S���R�[�h = �@@�\�J�e�S���R�[�h 
                // �Ǘ��Ҍ����e�[�u��.�X�V���t���O = BooleanEnum.TRUE 
                StringBuffer l_sbWhere = new StringBuffer();
                l_sbWhere.append(" institution_code = ? ");
                l_sbWhere.append(" and permission_level = ? ");
                l_sbWhere.append(" and transaction_category = ? ");
                l_sbWhere.append(" and update_permission_flag = ? ");

                Object[] l_objWhere =
                    {
                        this.administratorParams.getInstitutionCode(),
                        this.administratorParams.getPermissionLevel(),
                        l_strTransactionCategory,
                        BooleanEnum.TRUE };

                QueryProcessor l_queryProcessor =
                    Processors.getDefaultProcessor();
                List l_lstRecords = l_queryProcessor.doFindAllQuery(
                    AdminPermissionRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere);
                
                if(l_lstRecords.size() == 0)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01056,
                        this.getClass().getName() + "." + STR_METHOD_NAME);                 
                }

            }
            else
            {
                //���@@�Ɖ���̏ꍇ�iis�X�V == false�j
                //�Ǘ��Ҍ����e�[�u��.�،���ЃR�[�h = this.�Ǘ��ҍs.�،���ЃR�[�h 
                //�Ǘ��Ҍ����e�[�u��.�������x�� = this.�Ǘ��ҍs.�������x�� 
                //�Ǘ��Ҍ����e�[�u��.�@@�\�J�e�S���R�[�h = �@@�\�J�e�S���R�[�h 
                //�Ǘ��Ҍ����e�[�u��.�X�V���t���O in �iBooleanEnum.TRUE�CBooleanEnum.FALSE�j
                StringBuffer l_sbWhere = new StringBuffer();
                l_sbWhere.append(" institution_code = ? ");
                l_sbWhere.append(" and permission_level = ? ");
                l_sbWhere.append(" and transaction_category = ? ");
                l_sbWhere.append(" and update_permission_flag in( ?,?)");

                Object[] l_objWhere =
                    {
                        this.administratorParams.getInstitutionCode(),
                        this.administratorParams.getPermissionLevel(),
                        l_strTransactionCategory,
                        BooleanEnum.FALSE,
                        BooleanEnum.TRUE};

                QueryProcessor l_queryProcessor =
                    Processors.getDefaultProcessor();
                List l_lstRecords = l_queryProcessor.doFindAllQuery(
                    AdminPermissionRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere);
                
                if(l_lstRecords.size() == 0)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01056,
                        this.getClass().getName() + "." + STR_METHOD_NAME);                 
                }
            }
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
        
        log.exiting(STR_METHOD_NAME);
        return;

    }

    /**
     * (validateTradingPassword)<BR>
     * (validate����p�X���[�h)<BR>
     * ����p�X���[�h�����������̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@OpLoginSecurityService���A���O�C���^�C�v�������擾����B<BR>
     *  <BR>
     * �|���O�C���^�C�v����.������ == ����p�X���[�h�ݒ� <BR>
     *    �i�FTRADING_PWD_ENV�j�̑����l���h0�FDEFAULT�i��� <BR>
     *    �p�X���[�h���ڂ��g�p���Ȃ��j�h�̏ꍇ�A���O�C���p�X���[�h�� <BR>
     *    �ƍ���1����B<BR>
     *  <BR>
     * �|���O�C���^�C�v����.������ == ����p�X���[�h�ݒ� <BR>
     *    �i�FTRADING_PWD_ENV�j�̑����l���h1�F����p�X���[�h�g�p�h <BR>
     *    �̏ꍇ�A����p�X���[�h�̏ƍ���2����B<BR>
     *  <BR>
     * ��1 ���O�C���p�X���[�h�̏ƍ� <BR>
     * �iOpLoginSecurityService.checkPassword() == false�j�̏ꍇ�A<BR>
     * �G���[���ʂ�ԋp����B�ȊO�A����I����ԋp����B<BR>
     *  <BR>
     * ��2 ����p�X���[�h�̏ƍ� <BR>
     * this.�Ǘ��ҍs.����p�X���[�h�𕜍����iWEB3Crypt.decrypt()���g�p�j<BR>
     * ����B�����������p�X���[�h�ƈ����̃p�X���[�h����v���Ȃ��ꍇ�A<BR>
     * �G���[���ʂ�ԋp����B�ȊO�A����I����ԋp����B<BR>
     *  <BR>
     * @@param l_strPassword - ���̓p�X���[�h�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40C3F66701D4
     */
    public void validateTradingPassword(String l_strPassword)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradingPassword(String)";
        log.entering(STR_METHOD_NAME);
        
        //1) OpLoginSecurityService���A���O�C���^�C�v�������擾����
        
        //�T�[�r�X���擾
        OpLoginSecurityService l_securityService = (OpLoginSecurityService)
            Services.getService(OpLoginSecurityService.class);
        OpLoginAdminService l_admService = (OpLoginAdminService) Services.getService(
            OpLoginAdminService.class);
        
        //LoginInfo->LoginType->LoginTypeAttribute 
        LoginInfo l_loginInfo = l_securityService.getLoginInfo();
        Map l_mapAttributes = l_admService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());
        
        //���O�C���^�C�v�������擾����
        String l_strAttribute =
            (String) l_mapAttributes.get(
                WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV);
        
        //2) ���O�C���p�X���[�h�̏ƍ�
        if(WEB3TradingPwdEnvDef.DEFAULT.equals(l_strAttribute))
        {
            //�iOpLoginSecurityService.checkPassword() == false�j�̏ꍇ�A
            //�G���[���ʂ�ԋp����B�ȊO�A����I����ԋp����B
            if(l_securityService.checkPassword(l_strPassword) == false)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00009,
                    this.getClass().getName() + "." + STR_METHOD_NAME);     
            }

        }
        //2) ����p�X���[�h�̏ƍ�
        else if(WEB3TradingPwdEnvDef.USE_TRADING_PWD.equals(l_strAttribute))
        {
            // this.�Ǘ��ҍs.����p�X���[�h�𕜍����iWEB3Crypt.decrypt()���g�p�j
            //����B�����������p�X���[�h�ƈ����̃p�X���[�h����v���Ȃ��ꍇ�A
            //�G���[���ʂ�ԋp����B�ȊO�A����I����ԋp����B
            WEB3Crypt l_crypt = new WEB3Crypt();
            String l_strAdministratorPassword =
                l_crypt.decrypt(this.administratorParams.getTradingPassword());
            if ( ! l_strAdministratorPassword.equals(l_strPassword))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00009,
                    this.getClass().getName() + "." + STR_METHOD_NAME);   
            }

        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����p�X���[�h�ݒ�(���O�C���^�C�v����) = " + l_strAttribute);
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }

    /**
     * (get�،����) <BR>
     * �igetInstitution�j <BR>
     * <BR>
     * this.�Ǘ��ҍs.�،����ID�ɊY������،���ЃI�u�W�F�N�g��ԋp����B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.Institution
     * @@throws WEB3SystemLayerException
     * @@roseuid 40C59C96004C
     */
    public Institution getInstitution() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getInstitution()";

        long l_lngInstitionId = this.administratorParams.getInstitutionId();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        Institution l_institution;
        try
        {
            //get�،���ЃI�u�W�F�N�g
            l_institution =
                l_finApp.getAccountManager().getInstitution(l_lngInstitionId);
        }
        catch (NotFoundException nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Administrator.class +"." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
        }
        
        return l_institution;
    }

    /**
     * (get�،���ЃR�[�h) <BR>
     * �igetInstitutionCode�j <BR>
     * <BR>
     * this.�Ǘ��ҍs.�،���ЃR�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 40C59CCC03C7
     */
    public String getInstitutionCode()
    {
        return this.administratorParams.getInstitutionCode();
    }

    /**
     * (get�Ǘ��҃R�[�h) <BR>
     * �igetAdministratorCode�j <BR>
     *  <BR>
     * this.�Ǘ��ҍs.�Ǘ��҃R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 40C7F5390203
     */
    public String getAdministratorCode()
    {
        return this.administratorParams.getAdministratorCode();
    }
    
    /**
     * (get���X�R�[�h) <BR>
     * �igetBranchCode�j <BR>
     *  <BR>
     * this.�Ǘ��ҍs.���X�R�[�h��ԋp����B<BR>
     * @@return String
     */
    public String getBranchCode()
    {
        return this.administratorParams.getBranchCode();
    }

    /**
     * (isDIR�Ǘ���)<BR>
     * �Ǘ��҂�"DIR�Ǘ���"�ł���ꍇ�Atrue��ԋp����B<BR> 
     * �Ǘ��҂�"�،���ЊǗ���"�ł���ꍇ�Afalse��ԋp����B<BR> 
     *  <BR>
     * �P�j�@@�Ǘ��҃^�C�v�e�[�u������ <BR>
     * �ȉ��̏����ŊǗ��҃^�C�v�e�[�u�����������A�Y������s�� <BR>
     * �s�I�u�W�F�N�g�i�F�Ǘ��҃^�C�vParams�j��ԋp����B <BR>
     *  <BR>
     * [����] <BR>
     * �Ǘ��҃^�C�v�e�[�u��.�،���ЃR�[�h = this.�Ǘ��ҍs.�،���ЃR�[�h <BR>
     * �Ǘ��҃^�C�v�e�[�u��.�������x�� = this.�Ǘ��ҍs.�������x�� <BR>
     *  <BR>
     * ���@@�Ǘ��҃^�C�vParams�N���X��DDL��莩����������B<BR> 
     *  <BR>
     * �Q�j�@@�c�h�q�Ǘ��҃t���O�ԋp <BR>
     * �@@�P�j�Ŏ擾�����Ǘ��҃^�C�v�s.�c�h�q�Ǘ��҃t���O�𔻒肵�A<BR>
     * �Ή�����boolean�l��ԋp����B <BR>
     * <BR>
     * �@@�|�i�Ǘ��҃^�C�v�s.�c�h�q�Ǘ��҃t���O = 1 �j�̏ꍇ�Atrue��ԋp����B<BR> 
     * �@@�|�i�Ǘ��҃^�C�v�s.�c�h�q�Ǘ��҃t���O != 1 �j�̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * @@throws WEB3SystemLayerException
     * @@return boolean
     * @@roseuid 40C7F5390203
     */
    public boolean isDirAdministrator() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isDirAdministrator()";        
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�Ǘ��҃^�C�v�e�[�u������ 
        //[��������] 
        //�Ǘ��҃^�C�v�e�[�u��.�،���ЃR�[�h = this.�Ǘ��ҍs.�،���ЃR�[�h 
        //�Ǘ��҃^�C�v�e�[�u��.�������x�� = this.�Ǘ��ҍs.�������x��
        AdministratorTypeRow l_administratorTypeRow;
        try
        {
            l_administratorTypeRow =
                AdministratorTypeDao.findRowByPk(
                    this.administratorParams.getInstitutionCode(),
                    this.administratorParams.getPermissionLevel());
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() +"." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //�Q�j�@@�c�h�q�Ǘ��҃t���O�ԋp 
        //�P�j�Ŏ擾�����Ǘ��҃^�C�v�s.�c�h�q�Ǘ��҃t���O�𔻒肵�A
        //�Ή�����boolean�l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return WEB3DirAdminFlagDef.DIR_ADMINISTRATOR.equals(
            String.valueOf(l_administratorTypeRow.getDirAdminFlag()));
        
    }
    
    /**
     * (is�S���X����)<BR>
     *  <BR>
     * �Y���Ǘ��҂ɑS���X�f�[�^���������������邩�𔻒肷��B<BR> 
     * �S���X�f�[�^��������ꍇ��true�A���X�f�[�^�݈̂�����ꍇ <BR>
     * ��false��ԋp����B <BR>
     *  <BR>
     * �P�j�@@�Ǘ��҃^�C�v�e�[�u������ <BR>
     * �ȉ��̏����ŊǗ��҃^�C�v�e�[�u�����������A�Y������s�� <BR>
     * �s�I�u�W�F�N�g�i�F�Ǘ��҃^�C�vParams�j��ԋp����B <BR>
     *  <BR>
     * [����] <BR>
     * �Ǘ��҃^�C�v�e�[�u��.�،���ЃR�[�h = this.�Ǘ��ҍs.�،���ЃR�[�h <BR>
     * �Ǘ��҃^�C�v�e�[�u��.�������x�� = this.�Ǘ��ҍs.�������x�� <BR>
     *  <BR>
     * ���@@�Ǘ��҃^�C�vParams�N���X��DDL��莩����������B<BR> 
     *  <BR>
     * �Q�j�@@�S���X���t���O�ԋp <BR>
     * �P�j�Ŏ擾�����Ǘ��҃^�C�v�s.�S���X���t���O�𔻒肵�A<BR>
     * �Ή�����boolean�l��ԋp����B <BR>
     *  <BR>
     * �|�i�Ǘ��҃^�C�v�s.�S���X���t���O == BooleanEnum.TRUE�j�̏ꍇ�A<BR>
     * true��ԋp����B <BR>
     * �|�i�Ǘ��҃^�C�v�s.�S���X���t���O == BooleanEnum.FALSE�j�̏ꍇ�A<BR>
     * false��ԋp����B<BR> 
     *  <BR> 
     * @@throws WEB3SystemLayerException
     * @@return boolean
     */
    public boolean isAllBranchsPermission()
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isAllBranchsPermission()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�Ǘ��҃^�C�v�e�[�u������
        AdministratorTypeRow l_administratorTypeRow;
        try
        {
            l_administratorTypeRow =
                AdministratorTypeDao.findRowByPk(
                    this.administratorParams.getInstitutionCode(),
                    this.administratorParams.getPermissionLevel());

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
        
        //�Q�j�@@�S���X���t���O�ԋp
        log.exiting(STR_METHOD_NAME);
        return BooleanEnum.TRUE.equals(
            l_administratorTypeRow.getAllBranchPermissionFlag());

    }
    
    /**
     * (validate���X����)<BR>
     *  <BR>
     * ���Y�Ǘ��҂��A�w��̕��X����舵���邩���`�F�b�N����B <BR>
     *  <BR>
     * �|�S���X���Ǘ��҂̏ꍇ�ithis.is�S���X����() == true�j<BR> 
     * �������s�킸�I������B <BR>
     *  <BR>
     * �|�����X�̂ݎ戵�̊Ǘ��҂̏ꍇ�ithis.is�S���X���� == false�j<BR> 
     * this.get���X�R�[�h()�C"000"�i�w��Ȃ��j�ȊO�̕��X�R�[�h��������<BR> 
     * �܂܂��΁A���X�������Ȃ��Ɣ��肵��O���X���[����B<BR> 
     * ���@@"000"�F���X�w��Ȃ��B<BR> 
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_01074<BR> 
     * <BR>
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * @@throws WEB3BaseException
     */
    public void validateBranchPermission(String[] l_strBranchCodes)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateBranchPermission(String[])";
        log.entering(STR_METHOD_NAME);
        
        //�S���X���Ǘ��҂̏ꍇ�ithis.is�S���X����() == true�j
        //�������s�킸�I������B
        if(isAllBranchsPermission() == true)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        //�����X�̂ݎ戵�̊Ǘ��҂̏ꍇ�ithis.is�S���X���� == false�j
        //this.get���X�R�[�h()�C"000"�i�w��Ȃ��j�ȊO�̕��X�R�[�h��������
        //�܂܂��΁A���X�������Ȃ��Ɣ��肵��O���X���[����B
        int l_intLength = l_strBranchCodes.length;
        for (int i = 0; i < l_intLength; i++)
        {
            if(( ! getBranchCode().equals(l_strBranchCodes[i])) 
                &&( ! WEB3BranchCodeDef.DEFAULT.equals(l_strBranchCodes[i])))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01074,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

        }       
             
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (validate���X����)<BR>
     *  <BR>
     * ���Y�Ǘ��҂��A�w��̕��X����舵���邩���`�F�b�N����B<BR>  
     *  <BR> 
     * �|�S���X���Ǘ��҂̏ꍇ�ithis.is�S���X����() == true�j<BR>  
     * �������s�킸�I������B<BR>  
     * <BR> 
     * �|�����X�̂ݎ戵�̊Ǘ��҂̏ꍇ�ithis.is�S���X���� == false�j<BR>
     * �ithis.get���X�R�[�h() != ���X�R�[�h�j and �i���X�R�[�h != "000"�j��<BR>
     * �ꍇ�A���X�������Ȃ��Ɣ��肵��O���X���[����B<BR>
     * ���@@"000"�F���X�w��Ȃ��B <BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_01074<BR> 
     * <BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@throws WEB3BaseException
     */
    public void validateBranchPermission(String l_strBranchCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateBranchPermission(String)";
        log.entering(STR_METHOD_NAME);
        
        //�S���X���Ǘ��҂̏ꍇ�ithis.is�S���X����() == true�j
        //�������s�킸�I������B
        if(isAllBranchsPermission() == true)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        //�����X�̂ݎ戵�̊Ǘ��҂̏ꍇ�ithis.is�S���X���� == false�j
        // �ithis.get���X�R�[�h() != ���X�R�[�h�j and �i���X�R�[�h != "000"�j��
        //�ꍇ�A���X�������Ȃ��Ɣ��肵��O���X���[����B
        if( ( ! getBranchCode().equals(l_strBranchCode)) 
            &&( ! WEB3BranchCodeDef.DEFAULT.equals(l_strBranchCode)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01074,
                this.getClass().getName() + "." + STR_METHOD_NAME);   
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (get�������x��)<BR>
     *  <BR>
     * this.�Ǘ��ҍs.�������x����ԋp����B
     */
    public String getPermissionLevel()
    {
        return this.administratorParams.getPermissionLevel();
    }
    
    /**
     * (get�Ǘ��҂h�c)<BR>
     *  <BR>
     * this.�Ǘ��ҍs.�Ǘ���ID��ԋp����B
     */
    public long getAdministratorId()
    {
        return this.administratorParams.getAdministratorId();
    }
    
    /**
     * (get���O�C���h�c)<BR>
     *  <BR>
     * this.�Ǘ��ҍs.�Ǘ��҃��O�C��ID��ԋp����B
     */
    public long getLoginId()
    {
        return this.administratorParams.getLoginId();
    }
    
    /**
     * (get�Ǘ���) <BR>
     * �istatic���\�b�h�j <BR>
     *  <BR>
     * �P�j�@@��������������̐擪�ɁA�،���Џ�����}������B<BR>
     *  <BR>
     * "�،���Ђh�c = " + �Ǘ��ҁi�I�y���[�^�j.get�،���Ђh�c(). �،���Ђh�c<BR> 
     *   + �������������� <BR>
     *  <BR>
     * �Q�j�@@�Ǘ��҃e�[�u������ <BR>
     * QueryProcessor.doFindAllQuery( )�ɂ��A�Ǘ��ҍs���擾����B<BR> 
     *  <BR>
     * [doFindAllQuery()�Ɏw�肷�����] <BR>
     * rowType�F�@@�Ǘ���Row.TYPE <BR> 
     * where�F�@@�P�j�ŕҏW������������������ <BR>
     * orderBy�F�@@�\�[�g���� <BR> 
     * conditions�F�@@null <BR> 
     * bindVars�F�@@���������f�[�^�R���e�i <BR>
     *  <BR>
     * �R�j�@@�������ʂ��A�����Ɉ�v����v�f�� <BR>
     * �Ǘ���List�i�FArrayList�j��ҏW����B<BR>
     *  <BR>
     * �Ǘ���List�i�FArrayList�j�𐶐����A�Q�j�Ŏ擾���� <BR>
     * �e�v�f�ɂ��āA�R�|�P�j�`�R�|�S�j�����{����B<BR>
     *  <BR>
     * �R�|�P�j�@@�Ώۗv�f�̍s�I�u�W�F�N�g���w�肵�A<BR>
     * �Ǘ��҃I�u�W�F�N�g�𐶐�����B<BR>
     *  <BR>
     * �R�|�Q�j�@@���O�C���񐔂̔��� �����O�C���񐔏������w�肳��Ă���ꍇ<BR>
     * �i���O�C���G���[�� != null && ���O�C���G���[�� > 0�j && <BR>
     * �i�Ǘ��҂̃��O�C���G���[�񐔁�1�������̃��O�C���G���[�񐔁j�ł���΁A<BR>
     * �Ώۗv�f�ɂ��āA�ȍ~�̏����͍s��Ȃ��B�icontinue;�j <BR>
     *  <BR>
     * ��1 �Ǘ��҂̃��O�C���G���[�񐔂̎擾 <BR>
     * �Ǘ���.get���O�C���h�c()�ɂĎ擾�������O�C���h�c�Ɉ�v����s�� <BR>
     * ���O�C���e�[�u������擾����B�擾�����s�̃��O�C���G���[�񐔁B<BR>
     *  <BR>
     * �R�|�R�j�@@DIR�Ǘ��҂̔��� ���I�y���[�^���ʏ�Ǘ��҂̏ꍇ�A<BR>
     * DIR�Ǘ��҂̊Ǘ��ҍs�͎戵���s�B<BR>
     * �i�Ǘ��ҁi�I�y���[�^�j.isDIR�Ǘ��ҁi�j == false�j && �i�R�|�P�j�� <BR>
     * ���������Ǘ���.isDIR�Ǘ���() == true�j�ł���΁A�Ώۗv�f�ɂ��āA<BR>
     * �ȍ~�̏����͍s��Ȃ��B�icontinue;�j<BR>
     *  <BR>
     * �R�|�S�j�@@���X�̔��� ���I�y���[�^���S���X���łȂ��ꍇ�A<BR>
     * �����X�̊Ǘ��ҍs�͎戵���s�B<BR>
     * �i�Ǘ��ҁi�I�y���[�^�j.is�S���X���� == false�j &&�i�R�|�P�j�Ő�������<BR>
     * �Ǘ���.get���X�R�[�h() != �Ǘ��ҁi�I�y���[�^�j.get���X�R�[�h()�j�� <BR>
     * ����΁A�Ώۗv�f�ɂ��āA�ȍ~�̏����͍s��Ȃ��B�icontinue;�j <BR>
     *  <BR>
     * �R�|�T�j�@@�Ǘ���List�i�FArrayList�j�ɑΏۗv�f��ǉ��i�Fadd�j����B<BR>
     *  <BR>
     * �S�j�@@�Ǘ��Ҕz��ԋp <BR>
     * �Ǘ���List�i�FArrayList�j��z��ɕϊ��itoArray()�j���A�ԋp����B<BR>
     *  <BR>
     * @@param l_operator - (�Ǘ��ҁi�I�y���[�^�j) <BR>
     * @@param l_strWhere - (��������������) <BR>
     *    �� �w�肵�Ȃ��ꍇnull <BR>
     * @@param l_bindVars - (���������f�[�^�R���e�i) <BR>
     *    �� �w�肵�Ȃ��ꍇnull <BR>
     * @@param l_strOrderBy - (�\�[�g����) <BR>
     *    �� �w�肵�Ȃ��ꍇnull <BR>
     * @@param l_loginErrorTimes - (���O�C���G���[��) <BR>
     *    �� �w�肵�Ȃ��ꍇnull <BR>
     * @@throws WEB3SystemLayerException
     */
    static public WEB3Administrator[] getAdministrators(
        WEB3Administrator l_operator,
        String l_strWhere,
        String[] l_bindVars,
        String l_strOrderBy,
        Integer l_loginErrorTimes)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getAdministrators(WEB3Administrator, String, String[], String, Integer)";
        log.entering(STR_METHOD_NAME);

        //�Ǘ��ҁi�I�y���[�^�j�ɏ،���Ђh�c���擾 
        AdministratorParams l_administratorParams =
            (AdministratorParams) l_operator.getDataSourceObject();
        String l_strInstitutionId =
            String.valueOf(l_administratorParams.getInstitutionId());

        //�P�j�@@��������������̐擪�ɁA�،���Џ���(�،���Ђh�c)��}������B
        String l_strAdminitratorWhere;
        if (l_strWhere == null)
        {
            l_strAdminitratorWhere = " institution_id = ? ";
        }
        else
        {
            l_strAdminitratorWhere = " institution_id = ? " + l_strWhere;
        }
        String[] l_strAdminitratorBindVars;
        if (l_bindVars == null)
        {
            l_strAdminitratorBindVars = new String[] { l_strInstitutionId };
        }
        else
        {
            int l_intLength = l_bindVars.length;
            l_strAdminitratorBindVars = new String[l_intLength + 1];
            l_strAdminitratorBindVars[0] = l_strInstitutionId;
            for (int i = 0; i < l_intLength; i++)
            {
                l_strAdminitratorBindVars[i + 1] = l_bindVars[i];
            }
        }

        try
        {
            //�Q�j�@@�Ǘ��҃e�[�u������ 
            //QueryProcessor.doFindAllQuery( )�ɂ��A�Ǘ��ҍs���擾����B
            //  * rowType�F�@@�Ǘ���Row.TYPE 
            //  * where�F�@@�P�j�ŕҏW������������������ 
            //  * orderBy�F�@@�\�[�g���� 
            //  * conditions�F�@@null 
            //  * bindVars�F�@@���������f�[�^�R���e�i
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lstRecords =
                l_queryProcessor.doFindAllQuery(
                    AdministratorRow.TYPE,
                    l_strAdminitratorWhere,
                    l_strOrderBy,
                    null,
                    l_strAdminitratorBindVars);

            List l_lstAdministrators = new ArrayList();

            //�R�j�@@�������ʂ��A�����Ɉ�v����v�f�� 
            //�Ǘ���List�i�FArrayList�j��ҏW����B
            int l_intSize = l_lstRecords.size();
            for (int i = 0; i < l_intSize; i++)
            {
                //�R�|�P�j�@@�Ώۗv�f�̍s�I�u�W�F�N�g���w�肵�A
                //�Ǘ��҃I�u�W�F�N�g�𐶐�����B
                AdministratorRow l_tmpRow =
                    (AdministratorRow) l_lstRecords.get(i);
                AdministratorParams l_tmpParams = new AdministratorParams(l_tmpRow);
                WEB3Administrator l_tmpAdministrator = new  WEB3Administrator(l_tmpParams);

                //�Ǘ��҂̃��O�C���G���[�񐔂̎擾
                LoginRow l_loginRow = 
                    LoginDao.findRowByPk(l_tmpRow.getLoginId());

                //�R�|�Q�j�@@���O�C���񐔂̔���
                if ((l_loginErrorTimes != null)
                    && (l_loginErrorTimes.intValue() > 0)
                    && (l_loginRow.getFailureCount() < l_loginErrorTimes.intValue()))
                {
                    continue;
                }
                
                //�R�|�R�j�@@DIR�Ǘ��҂̔���
                if ((l_operator.isDirAdministrator() == false)
                    && (l_tmpAdministrator.isDirAdministrator() == true))
                {
                    continue;
                }
                
                //�R�|�S�j�@@���X�̔���
                if ((l_operator.isAllBranchsPermission() == false)
                    && (WEB3Toolkit.isEquals(l_operator.getBranchCode(),l_tmpAdministrator.getBranchCode()) == false))
                {
                    continue;
                }
                
                //�R�|�T�j�@@�Ǘ���List�i�FArrayList�j�ɑΏۗv�f��ǉ��i�Fadd�j����
                l_lstAdministrators.add(l_tmpAdministrator);

            }
            
            //�S�j�@@�Ǘ��Ҕz��ԋp
            l_intSize = l_lstAdministrators.size();
            WEB3Administrator[] l_administrators = new WEB3Administrator[l_intSize];
            for(int i = 0; i < l_intSize; i ++)
            {
                l_administrators[i] = (WEB3Administrator)l_lstAdministrators.get(i);
            }
            
            log.exiting(STR_METHOD_NAME);
            return l_administrators;
            
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Administrator.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

    }

    /**
     * (getDIR�Ǘ��҃t���O)<BR>
     * �Ǘ��҂�DIR�Ǘ��҃t���O��ԋp����B<BR>
     *  <BR>
     * �P�j�@@�Ǘ��҃^�C�v�e�[�u������ <BR>
     * �@@�ȉ��̏����ŊǗ��҃^�C�v�e�[�u�����������A<BR>
     * �@@�Y������s�̍s�I�u�W�F�N�g�i�F�Ǘ��҃^�C�vParams�j��ԋp����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�Ǘ��҃^�C�v�e�[�u��.�،���ЃR�[�h = this.�Ǘ��ҍs.�،���ЃR�[�h <BR>
     * �@@�Ǘ��҃^�C�v�e�[�u��.�������x�� = this.�Ǘ��ҍs.�������x�� <BR>
     * <BR>
     * �@@���@@�Ǘ��҃^�C�vParams�N���X��DDL��莩����������B <BR>
     * <BR>
     * �Q�j�@@�c�h�q�Ǘ��҃t���O�ԋp <BR>
     * �@@�P�j�Ŏ擾�����Ǘ��҃^�C�v�s.�c�h�q�Ǘ��҃t���O��ԋp����B <BR>
     * <BR>
     * @@throws WEB3SystemLayerException
     * @@return String
     */
    public String getDIRAdminFlag() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getDIRAdminFlag()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�Ǘ��҃^�C�v�e�[�u������
        //[��������]
        //�Ǘ��҃^�C�v�e�[�u��.�،���ЃR�[�h = this.�Ǘ��ҍs.�،���ЃR�[�h
        //�Ǘ��҃^�C�v�e�[�u��.�������x�� = this.�Ǘ��ҍs.�������x��
        AdministratorTypeRow l_administratorTypeRow;
        try
        {
            l_administratorTypeRow =
                AdministratorTypeDao.findRowByPk(
                    this.administratorParams.getInstitutionCode(),
                    this.administratorParams.getPermissionLevel());
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        //�Q�j�@@�c�h�q�Ǘ��҃t���O�ԋp
        //�P�j�Ŏ擾�����Ǘ��҃^�C�v�s.�c�h�q�Ǘ��҃t���O��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return String.valueOf(l_administratorTypeRow.getDirAdminFlag());
    }
}
@
