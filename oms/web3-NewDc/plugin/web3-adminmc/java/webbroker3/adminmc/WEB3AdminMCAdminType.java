head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.31.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminType.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (�Ǘ��҃^�C�v(WEB3AdminMCAdminType)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/29 ���q (���u) �V�K�쐬
                 : 2006/08/24 �s�p (���u) ���f��No.021, 022, DB�X�V�d�lNo006
*/

package webbroker3.adminmc;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DirAdminFlagDef;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.AdministratorTypeRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҃^�C�v)<BR>
 * �Ǘ��҃^�C�v�N���X<BR>
 * @@author ���q
 * @@version 1.0
 * <BR>
 */
public class WEB3AdminMCAdminType implements BusinessObject
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminType.class);

    /**
     * (�Ǘ��҃^�C�v�s)<BR>
     * �Ǘ��҃^�C�v�s�I�u�W�F�N�g <BR>
     * <BR>
     * �� �Ǘ��҃^�C�vParams�N���X��DDL��莩����������B<BR>
     * <BR>
     */
    private AdministratorTypeParams administratorTypeParams;

    /**
     * @@roseuid 419AA3DF0203
     */
    public WEB3AdminMCAdminType()
    {

    }

    /**
     * �igetDataSourceObject�̎����j <BR>
     * <BR>
     * this.�Ǘ��҃^�C�v�s��ԋp����B <BR>
     * <BR>
     * @@return Object
     * @@roseuid 4175F26300AE
     */
    public java.lang.Object getDataSourceObject()
    {
         return this.administratorTypeParams;
    }

    /**
     * (getDIR�Ǘ��҃t���O)<BR>
     * DIR�Ǘ��҃t���O���擾����B<BR>
     * <BR>
     * this.�Ǘ��҃^�C�v�s.DIR�Ǘ��҃t���O��ԋp����B<BR>
     * <BR>
     * @@return String
     */
    public String getDIRAdminFlag()
    {
         return Integer.toString(this.administratorTypeParams.getDirAdminFlag());
    }
    
    /**
     * (get�������x��)<BR>
     * �������x�����擾����B<BR>
     * <BR>
     * this.�Ǘ��҃^�C�v�s.�������x����ԋp����B<BR>
     * <BR>
     * @@return String
     * @@roseuid 4177236C035B
     */
    public String getPermissionLevel()
    {
         return this.administratorTypeParams.getPermissionLevel();
    }

    /**
     * (get�������x������)<BR>
     * �������x�����̂��擾����B<BR>
     * <BR>
     * this.�Ǘ��҃^�C�v�s.�������x�����̂�ԋp����B<BR>
     * <BR>
     * @@return String
     * @@roseuid 417607400050
     */
    public String getPermissionLevelName()
    {
         return this.administratorTypeParams.getPermissionLevelName();
    }

    /**
     * (isDIR�Ǘ���)<BR>
     * DIR�Ǘ��҂��𔻒肷��B<BR>
     * <BR>
     * �@@�|�ithis.�Ǘ��҃^�C�v�s.DIR�Ǘ��҃t���O  = 1 �j�̏ꍇtrue��ԋp����B<BR>
     * �@@�|�ithis.�Ǘ��҃^�C�v�s.DIR�Ǘ��҃t���O != 1 �j�̏ꍇfalse��ԋp����B<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 41760752010C
     */
    public boolean isDIRAdministrator()
    {
        final String STR_METHOD_NAME = "isDIRAdministrator()";
        log.entering(STR_METHOD_NAME);
        
         if (this.administratorTypeParams.getDirAdminFlag() == 1)
         {
             log.exiting(STR_METHOD_NAME);
             return true; 
         }

         log.exiting(STR_METHOD_NAME);
         return false;
    }

    /**
     * (is�S���X����)<BR>
     * �S���X�����𔻒肷��B<BR>
     * <BR>
     * �@@�|�ithis.�Ǘ��҃^�C�v�s.�S���X���t���O == BooleanEnum.TRUE�j�̏ꍇtrue��ԋp����B<BR>
     * �@@�|�ithis.�Ǘ��҃^�C�v�s.�S���X���t���O == BooleanEnum.FALSE�j�̏ꍇfalse��ԋp����B<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 4176076A03BB
     */
    public boolean isAllBranchPermission()
    {
         BooleanEnum l_allbranchpermissionflag = this.administratorTypeParams.getAllBranchPermissionFlag();

         if (BooleanEnum.TRUE.equals(l_allbranchpermissionflag))
         {
             return true;
         }
         else
         {
             return false;
         }
    }

    /**
     * (�Ǘ��҃^�C�v)<BR>
     * �R���X�g���N�^�B<BR>
     * �،���ЃR�[�h�C�������x���ɊY������s���A�Ǘ��҃^�C�v�e�[�u����茟������B <BR>
     * �Y���s���Ȃ��ꍇ�́A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *     tag:          BUSINESS_ERROR_00398 <BR>
     * <BR>
     * �������ʂ̊Ǘ��҃^�C�v�s�I�u�W�F�N�g�������Ɏw�肵�āA�R���X�g���N�^���R�[������B <BR>
     * �R���X�g���N�^�̖߂�l��ԋp����B <BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strPermissionLevel - (�������x��)<BR>
     * �������x��<BR>
     * @@return webbroker3.adminmc.WEB3AdminMCAdminType
     * @@roseuid 4176084A00DD
     */
    public WEB3AdminMCAdminType(String l_strInstitutionCode, String l_strPermissionLevel) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3AdminMCAdminType(String l_strInstitutionCode, String l_strPermissionLevel)";
        log.entering(STR_METHOD_NAME);

        if ((l_strInstitutionCode == null) || (l_strPermissionLevel == null))
        {
            log.error("�p�����[�^Null�o���Ȃ��B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME);
        }

        List l_lisRecords = new ArrayList();

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            String l_sbQueryString = "institution_code = ? and permission_level = ?";

            Object[] l_queryDataContainer = {l_strInstitutionCode, l_strPermissionLevel};

             l_lisRecords = l_queryProcessor.doFindAllQuery(
                AdministratorTypeRow.TYPE,
                l_sbQueryString.toString(),
                l_queryDataContainer);
        }
        catch (DataFindException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intSize = l_lisRecords.size();

        if (l_intSize == 0)
        {
            //�Y���s���Ȃ��ꍇ�́A��O���X���[����B
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                                                                  this.getClass().getName() + STR_METHOD_NAME);
        }

        AdministratorTypeParams  l_admntpprms = null;

        l_admntpprms = (AdministratorTypeParams) l_lisRecords.get(0);

        this.administratorTypeParams = l_admntpprms;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (�Ǘ��҃^�C�v)<BR>
     * �R���X�g���N�^�B<BR>
     * �w��s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g���A�C���X�^���X�𐶐�����B <BR>
     * <BR>
     * ���@@�Ǘ��҃^�C�vParams��DDL��莩����������B <BR>
     * <BR>
     * @@param l_adminTypeLine - (�Ǘ��҃^�C�v�s)<BR>
     * �Ǘ��҃^�C�v�s�I�u�W�F�N�g<BR>
     * <BR>
     * ���@@�Ǘ��҃^�C�vParams��DDL��莩����������B <BR>
     * <BR>
     * @@return webbroker3.adminmc.WEB3AdminMCAdminType
     * @@roseuid 4176099601B8
     */
    public WEB3AdminMCAdminType(AdministratorTypeParams l_administratorTypeParams)
    {
        this.administratorTypeParams = l_administratorTypeParams;
    }

    /**
     * (saveNew�Ǘ��҃^�C�v)<BR>
     * �istatic���\�b�h�j <BR>
     * �����̓��e�ŊǗ��҃^�C�v�e�[�u���ɐV�K�s��}���iInsert�j����B <BR>
     * <BR>
     * �@@[�}������s�̓��e] <BR>
     * �@@�Ǘ��҃^�C�v.�،���ЃR�[�h = �،���ЃR�[�h <BR>
     * �@@�Ǘ��҃^�C�v.�������x�� = �������x�� <BR>
     * �@@�Ǘ��҃^�C�v.�������x������ = �������x������ <BR>
     * �@@�Ǘ��҃^�C�v.DIR�Ǘ��҃t���O = DIR�Ǘ��҃t���O(*1)(*2) <BR>
     * �@@�Ǘ��҃^�C�v.�S���X���t���O = �S���X���t���O(*3) <BR>
     * �@@�Ǘ��҃^�C�v.�X�V�҃R�[�h = �Ǘ��҃R�[�h <BR>
     * �@@�Ǘ��҃^�C�v.�쐬���� = ���������i�� TradingSystem.getSystemTimestamp()�j <BR>
     * �@@�Ǘ��҃^�C�v.�X�V���� = ���������i�� TradingSystem.getSystemTimestamp()�j <BR>
     * <BR>
     * (*1) DIR�Ǘ��҃t���O�̒l <BR>
     * �ʏ�Ǘ��҂̏ꍇ�@@�@@�@@�@@�@@�@@�F "0" <BR>
     * DIR�Ǘ��҂̏ꍇ�@@�@@�@@�@@�@@�@@ �F "1" <BR>
     * �ʏ�Ǘ��ҁi�\���ҁj�̏ꍇ�F "2" <BR>
     * �ʏ�Ǘ��ҁi���F�ҁj�̏ꍇ�F "3" <BR>
     * <BR>
     * (*2) DIR�Ǘ��҃t���O�̃f�[�^�^  <BR>
     * �����񂩂琔�l�^�֕ϊ�����B <BR>
     * <BR>
     * (*3) true�̏ꍇ�ABooleanEnum.TRUE�Cfalse�̏ꍇ�ABooleanEnum.FALSE�B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strPermissionLevel - (�������x��)<BR>
     * �������x��<BR>
     * @@param l_strPermissionLevelName - (�������x������)<BR>
     * �������x������<BR>
     * @@param l_strDIRAdminFlag - (DIR�Ǘ��҃t���O)<BR>
     * DIR�Ǘ��҃t���O <BR>
     * <BR>
     * DIR�Ǘ��҂̏ꍇ�@@�@@�@@�@@�@@�@@ �F "1" <BR>
     * �ʏ�Ǘ��҂̏ꍇ�@@�@@�@@�@@�@@�@@�F "0" <BR>
     * �ʏ�Ǘ��ҁi�\���ҁj�̏ꍇ�F "2" <BR>
     * �ʏ�Ǘ��ҁi���F�ҁj�̏ꍇ�F "3"<BR>
     * @@param l_blnAllBranchPermissionFlag - (�S�X���t���O)<BR>
     * �S���X���t���O<BR>
     * <BR>
     * �S���X���̏ꍇtrue�C�ȊOfalse�B<BR>
     * @@param l_strAdministratorCode - (�Ǘ��҃R�[�h)<BR>
     * �Ǘ��҃R�[�h<BR>
     * @@roseuid 417710510138
     */
    public static void saveNewAdminType(
        String l_strInstitutionCode, 
        String l_strPermissionLevel, 
        String l_strPermissionLevelName, 
        String l_strDIRAdminFlag, 
        boolean l_blnAllBranchPermissionFlag, 
        String l_strAdministratorCode) throws WEB3BaseException
    {
         final String STR_METHOD_NAME = "saveNewAdminType(String l_strInstitutionCode, String l_strPermissionLevel, String l_strPermissionLevelName, String l_strDIRAdminFlag, boolean l_blnAllBranchPermissionFlag, String l_strAdministratorCode))";
         log.entering(STR_METHOD_NAME);

         AdministratorTypeParams l_administratortypeparams = new AdministratorTypeParams();
         //�Ǘ��҃^�C�v.�،���ЃR�[�h = �،���ЃR�[�h
         l_administratortypeparams.setInstitutionCode(l_strInstitutionCode);
         //�Ǘ��҃^�C�v.�������x�� = �������x��
         l_administratortypeparams.setPermissionLevel(l_strPermissionLevel);
         //�Ǘ��҃^�C�v.�������x������ = �������x������
         l_administratortypeparams.setPermissionLevelName(l_strPermissionLevelName);
         //�Ǘ��҃^�C�v.DIR�Ǘ��҃t���O = DIR�Ǘ��҃t���O 
         l_administratortypeparams.setDirAdminFlag( Integer.parseInt(l_strDIRAdminFlag));

         //�Ǘ��҃^�C�v.�S���X���t���O = �S���X���t���O
         if (l_blnAllBranchPermissionFlag)
         {
              l_administratortypeparams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
         }
         else
         {
              l_administratortypeparams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
         }
         //�Ǘ��҃^�C�v.�X�V�҃R�[�h = �Ǘ��҃R�[�h
         l_administratortypeparams.setLastUpdater(l_strAdministratorCode);
         //�Ǘ��҃^�C�v.�쐬���� = ��������
         l_administratortypeparams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
         //�Ǘ��҃^�C�v.�X�V���� = ��������
         l_administratortypeparams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

         //this.�i�Ǘ��ҋ��ʁj�A�b�v���[�h�s �̓��e��DB�ɍs��}���iinsert�j����B
         try
         {
             QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
             l_queryProcessor.doInsertQuery(l_administratortypeparams);
         }
         catch (DataQueryException l_ex)
         {
             log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
 
             log.exiting(STR_METHOD_NAME);
             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                 l_ex.getMessage(),
                 l_ex);
         }
         catch (DataNetworkException l_ex)
         {
             log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
 
             log.exiting(STR_METHOD_NAME);
             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                 l_ex.getMessage(),
                 l_ex);
         }
         log.exiting(STR_METHOD_NAME);
    }

    /**
     * (save�Ǘ��҃^�C�v)<BR>
     * �istatic���\�b�h�j <BR>
     * �����̓��e�ŊǗ��҃^�C�v�e�[�u�����X�V�iUpdate�j����B <BR>
     * <BR>
     * �@@[�X�V���e] <BR>
     * �@@�Ǘ��҃^�C�v.�������x������ = �������x������ <BR>
     * �@@�Ǘ��҃^�C�v.DIR�Ǘ��҃t���O = DIR�Ǘ��҃t���O(*1)(*2) <BR>
     * �@@�Ǘ��҃^�C�v.�S���X���t���O = �S���X���t���O(*3) <BR>
     * �@@�Ǘ��҃^�C�v.�X�V�҃R�[�h = �Ǘ��҃R�[�h <BR>
     * �@@�Ǘ��҃^�C�v.�X�V���� = ���������i�� TradingSystem.getSystemTimestamp()�j <BR>
     * <BR>
     * (*1) DIR�Ǘ��҃t���O�̒l <BR>
     * �ʏ�Ǘ��҂̏ꍇ�@@�@@�@@�@@�@@�@@�F "0" <BR>
     * DIR�Ǘ��҂̏ꍇ�@@�@@�@@�@@�@@�@@ �F "1" <BR>
     * �ʏ�Ǘ��ҁi�\���ҁj�̏ꍇ�F "2" <BR>
     * �ʏ�Ǘ��ҁi���F�ҁj�̏ꍇ�F "3" <BR>
     * <BR>
     * (*2) DIR�Ǘ��҃t���O�̃f�[�^�^  <BR>
     * �����񂩂琔�l�^�֕ϊ�����B <BR>
     * <BR>
     * (*3) true�̏ꍇ�ABooleanEnum.TRUE�Cfalse�̏ꍇ�ABooleanEnum.FALSE�B <BR>
     * <BR>
     * �@@[�X�V�Ώۍs] <BR>
     * �@@�Ǘ��҃^�C�v.�،���ЃR�[�h = �،���ЃR�[�h And <BR>
     * �@@�Ǘ��҃^�C�v.�������x�� = �������x�� <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strPermissionLevel - (�������x��)<BR>
     * �������x��<BR>
     * @@param l_strPermissionLevelName - (�������x������)<BR>
     * �������x������<BR>
     * @@param l_strDIRAdminFlag - (DIR�Ǘ��҃t���O)<BR>
     * DIR�Ǘ��҃t���O <BR>
     * <BR>
     * DIR�Ǘ��҂̏ꍇ�@@�@@�@@�@@�@@�@@ �F "1" <BR>
     * �ʏ�Ǘ��҂̏ꍇ�@@�@@�@@�@@�@@�@@�F "0" <BR>
     * �ʏ�Ǘ��ҁi�\���ҁj�̏ꍇ�F "2" <BR>
     * �ʏ�Ǘ��ҁi���F�ҁj�̏ꍇ�F "3"<BR>
     * @@param l_blnAllBranchPermissionFlag - (�S�X���t���O)<BR>
     * �S���X���t���O<BR>
     * <BR>
     * �S���X���̏ꍇtrue�C�ȊOfalse�B<BR>
     * @@param l_strAdministratorCode - (�Ǘ��҃R�[�h)<BR>
     * �Ǘ��҃R�[�h<BR>
     * @@roseuid 4177125F001F
     */
    public static void saveAdminType(
        String l_strInstitutionCode, 
        String l_strPermissionLevel, 
        String l_strPermissionLevelName, 
        String l_strDIRAdminFlag, 
        boolean l_blnAllBranchPermissionFlag, 
        String l_strAdministratorCode) throws WEB3BaseException
    {
         final String STR_METHOD_NAME = "saveAdminType(String l_strInstitutionCode, String l_strPermissionLevel, String l_strPermissionLevelName, String l_strDIRAdminFlag, boolean l_blnAllBranchPermissionFlag, String l_strAdministratorCode)";
         log.entering(STR_METHOD_NAME);
         try
         {
         	String l_strWhere = " institution_code = ? and permission_level = ?";
         	String l_strOrderBy = "";
         	Object[] l_objWhereValues = {l_strInstitutionCode, l_strPermissionLevel};
             List l_returnList = new ArrayList();

             // �f�[�^���m
             QueryProcessor l_processor = Processors.getDefaultProcessor();
             l_returnList = l_processor.doFindAllQuery(AdministratorTypeRow.TYPE, l_strWhere, l_strOrderBy, null, l_objWhereValues);

             AdministratorTypeParams l_administratortypeparams = null;

             l_administratortypeparams = new AdministratorTypeParams((AdministratorTypeRow) l_returnList.get(0));

             //�Ǘ��҃^�C�v.�������x������ = �������x������
             l_administratortypeparams.setPermissionLevelName(l_strPermissionLevelName);
             //�Ǘ��҃^�C�v.DIR�Ǘ��҃t���O = DIR�Ǘ��҃t���O 
             l_administratortypeparams.setDirAdminFlag( Integer.parseInt(l_strDIRAdminFlag));
             //�Ǘ��҃^�C�v.�S���X���t���O = �S���X���t���O
             if (l_blnAllBranchPermissionFlag)
             {
                  l_administratortypeparams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
             }
             else
             {
                  l_administratortypeparams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
             }
             //�Ǘ��҃^�C�v.�X�V�҃R�[�h = �Ǘ��҃R�[�h
             l_administratortypeparams.setLastUpdater(l_strAdministratorCode);
             //�Ǘ��҃^�C�v.�X�V���� = ��������
             l_administratortypeparams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

             //this.�i�Ǘ��ҋ��ʁj�A�b�v���[�h�s �̓��e��DB�ɍs��}���iinsert�j����B
             l_processor.doUpdateQuery(l_administratortypeparams);
         }
         catch (DataQueryException l_dqe)
         {
             String l_strMessage = "�Ǘ��҃^�C�v������ error";
             log.error(l_strMessage, l_dqe);

             throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                                                WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                                                                l_dqe.getMessage(),
                                                                l_dqe);
         }
         catch (DataNetworkException l_dne)
         {
             String l_strMessage = "�Ǘ��҃^�C�v������  error";
             log.error(l_strMessage, l_dne);

             throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                                                WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                                                                l_dne.getMessage(),
                                                                l_dne);
         }

         log.exiting(STR_METHOD_NAME);
    }

    /**
     * (saveDelete�Ǘ��҃^�C�v)<BR>
     * �istatic���\�b�h�j<BR>
     * �����̓��e�ɊY������Ǘ��҃^�C�v�s�𕨗�DB���폜�iDelete�j����B<BR>
     * <BR>
     * �@@[�X�V�Ώۍs]<BR>
     * �@@�Ǘ��҃^�C�v.�،���ЃR�[�h = �،���ЃR�[�h And<BR>
     * �@@�Ǘ��҃^�C�v.�������x�� = �������x��<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strPermissionLevel - (�������x��)<BR>
     * �������x��<BR>
     * @@roseuid 4177136F00DA
     */
    public static void saveDeleteAdminType(String l_strInstitutionCode, String l_strPermissionLevel) throws WEB3BaseException
    {
         final String STR_METHOD_NAME = "saveDeleteAdminType(String l_strInstitutionCode, String l_strPermissionLevel)";
         log.entering(STR_METHOD_NAME);

         try
         {
         	String l_strWhere = " institution_code = ? and permission_level = ?";
            Object[] l_objWhereValues = {l_strInstitutionCode, l_strPermissionLevel};

         	QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
             l_queryProcessor.doDeleteAllQuery(AdministratorTypeRow.TYPE,
                                                           l_strWhere.toString(),
                                                           l_objWhereValues);
         }
         catch (DataException de)
         {
             log.debug(STR_METHOD_NAME, de);
             throw new WEB3SystemLayerException(
                                   WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                   WEB3AdminMCAdminType.class.getName() + "." + STR_METHOD_NAME,
                                   de.getMessage(),
                                   de);
         }

         log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�Ǘ��҃^�C�v)<BR>
     * �istatic���\�b�h�j<BR>
     * <BR>
     * �ȉ��̏����ɂāA�Ǘ��҃^�C�v�e�[�u������������B<BR>
     * �������ʂ̍s�I�u�W�F�N�g���w�肵�Ǘ��҃^�C�v�I�u�W�F�N�g�𐶐��C�z��ɂĕԋp����B<BR>
     * <BR>
     * �@@[�����@@] ��DIR�Ǘ��҂̏ꍇ�iisDIR�Ǘ��ҁi�I�y���[�^�j == true�j<BR>
     * �@@�Ǘ��҃^�C�v�e�[�u��.�،���ЃR�[�h = �،���ЃR�[�h<BR>
     * <BR>
     * �@@[�����A] ��DIR�Ǘ��҂łȂ��ꍇ�iisDIR�Ǘ��ҁi�I�y���[�^�j == false�j<BR>
     * �@@�Ǘ��҃^�C�v�e�[�u��.�،���ЃR�[�h = �،���ЃR�[�h And<BR>
     * �@@�Ǘ��҃^�C�v�e�[�u��.DIR�Ǘ��҃t���O !=  1 (DIR�Ǘ��҈ȊO)<BR>
     * <BR>
     * �@@[�擾��]<BR>
     * �@@�����̃\�[�g�����̒ʂ�<BR>
     * �@@�� �i�\�[�g���� == null�j�̏ꍇ�A�w�肵�Ȃ��B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * �\�[�g����<BR>
     * @@param l_blnIsDIRAdministrator - (isDIR�Ǘ��ҁi�I�y���[�^�j)<BR>
     * isDIR�Ǘ��ҁi�I�y���[�^�j<BR>
     * <BR>
     * �� ���O�C�����̊Ǘ��҂�DIR�Ǘ��҂ł��邩�̔���B<BR>
     * @@return webbroker3.adminmc.WEB3AdminMCAdminType[]
     * @@roseuid 41760A300215
     */
    public static WEB3AdminMCAdminType[] getAdminType(String l_strInstitutionCode, String l_strSortCond, boolean l_blnIsDIRAdministrator)
    throws WEB3BaseException
    {
         final String STR_METHOD_NAME = "getAdminType(String l_strInstitutionCode, String l_strSortCond, boolean l_blnIsDIRAdministrator)";
         log.entering(STR_METHOD_NAME);
         
         String l_strWhere = "";
         Object[] l_objWhereValues = null;
         List l_returnList = new ArrayList();
        WEB3AdminMCAdminType[] l_adminmcadmintypes = null;
        
         try
         {
             if (l_blnIsDIRAdministrator)
             {
                 l_strWhere = " institution_code = ?";
                 l_objWhereValues = new Object[1];
                 l_objWhereValues[0] = l_strInstitutionCode;
             }
             else
             {
                 l_strWhere = " institution_code = ? and dir_admin_flag <> ?";
                 l_objWhereValues = new Object[2];
                 l_objWhereValues[0] = l_strInstitutionCode;
                 l_objWhereValues[1] = new Integer(1);
             }
             
             QueryProcessor l_processor = Processors.getDefaultProcessor();
             l_returnList = l_processor.doFindAllQuery(AdministratorTypeRow.TYPE, l_strWhere, l_strSortCond, null, l_objWhereValues);

             int l_intSize = l_returnList.size();
             l_adminmcadmintypes = new WEB3AdminMCAdminType[l_intSize];
             
             for (int i = 0; i < l_intSize; i++)
             {
                 l_adminmcadmintypes[i] = new WEB3AdminMCAdminType ((AdministratorTypeParams)l_returnList.get(i));
             }
         }
         catch (DataQueryException l_dqe)
         {
             String l_strMessage = "�Ǘ��҃^�C�v������ error";
             log.error(l_strMessage, l_dqe);

             throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                                                WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                                                                l_dqe.getMessage(),
                                                                l_dqe);
         }
         catch (DataNetworkException l_dne)
         {
             String l_strMessage = "�Ǘ��҃^�C�v������  error";
             log.error(l_strMessage, l_dne);

             throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                                                WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                                                                l_dne.getMessage(),
                                                                l_dne);
         }
         
         
         log.exiting(STR_METHOD_NAME);
         return l_adminmcadmintypes;
    }
    /**
     * (validate�Ǘ��҃^�C�v�폜)<BR>
     * �Ǘ��҃^�C�v�폜�\�`�F�b�N<BR>
     * <BR>
     * �@@�Ǘ��҃e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@�Y���s�����݂���΁A�o�^���̊Ǘ��҂��g�p���Ă���Ǘ��҃^�C�v�ł���Ɣ��肵�A��O���X���[����B<BR>
     *   class :  WEB3BusinessLayerException <BR>
     *     tag :             BUSINESS_ERROR_01282<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�Ǘ���.�،���ЃR�[�h = this.�Ǘ��҃^�C�v�s.�،���ЃR�[�h And<BR>
     * �@@�Ǘ���.�������x�� = this.�Ǘ��҃^�C�v�s.�������x��<BR>
     * <BR>
     * <BR>
     * @@return Void
     * @@roseuid 41A6C0A20164
     */
    public void validateAdminTypeDelete() throws WEB3BaseException
    {
         final String STR_METHOD_NAME = "validateAdminTypeDelete()";
         log.entering(STR_METHOD_NAME);
         
         String l_strWhere = "institution_code = ? and permission_level = ?";
         String l_strOrderBy = "";
         Object[] l_objWhereValues = {this.administratorTypeParams.getInstitutionCode(), this.administratorTypeParams.getPermissionLevel()};
         List l_returnList = new ArrayList();
         
         try
         {
             QueryProcessor l_processor = Processors.getDefaultProcessor();
             l_returnList = l_processor.doFindAllQuery(AdministratorRow.TYPE, l_strWhere, l_strOrderBy, null, l_objWhereValues);

             if (l_returnList.size() > 0)
             {
                 log.error("�u�o�^���̊Ǘ��҂��g�p���Ă���Ǘ��҃^�C�v�ł���v�̗�O���X���[����B");
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                                                       WEB3ErrorCatalog.BUSINESS_ERROR_01282,
                                                       this.getClass().getName() + STR_METHOD_NAME);
             }
         }
         catch (DataQueryException l_dqe)
         {
             String l_strMessage = "�Ǘ��҃^�C�v������ error";
             log.error(l_strMessage, l_dqe);

             throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                                                WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                                                                l_dqe.getMessage(),
                                                                l_dqe);
         }
         catch (DataNetworkException l_dne)
         {
             String l_strMessage = "�Ǘ��҃^�C�v������  error";
             log.error(l_strMessage, l_dne);

             throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                                                WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                                                                l_dne.getMessage(),
                                                                l_dne);
         }
         
         log.exiting(STR_METHOD_NAME);
    }
}
@
