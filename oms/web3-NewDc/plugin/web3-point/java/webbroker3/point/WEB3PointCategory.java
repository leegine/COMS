head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.01.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointCategory.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�J�e�S���[(WEB3PointCategory.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.point.data.PointCategoryMasterParams;
import webbroker3.point.data.PointCategoryMasterRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�|�C���g�J�e�S���[)<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3PointCategory implements BusinessObject 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3PointCategory.class);
    
    /**
     * (�J�e�S���[�s)<BR>
     * �|�C���g�J�e�S���[�s�I�u�W�F�N�g<BR>
     */
    private PointCategoryMasterParams pointCategoryMasterParams;
    
    /**
     * (�|�C���g�J�e�S���[)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �������Athis.�J�e�S���[�s�ɃZ�b�g����B<BR>
     * @@param l_pointCategoryParams - (�J�e�S���[�s)<BR>
     * �|�C���g�J�e�S���[�s�I�u�W�F�N�g<BR>
     * 
     * @@roseuid 4191C3DF00A5
     */
    public WEB3PointCategory(PointCategoryMasterParams l_pointCategoryParams) 
    {
        final String STR_METHOD_NAME = " WEB3PointCategory(PointCategoryMasterParams)";
        log.entering(STR_METHOD_NAME);
        
        this.pointCategoryMasterParams = l_pointCategoryParams;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (�|�C���g�J�e�S���[)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �P�j��̃|�C���g�J�e�S���[Params�I�u�W�F�N�g�𐶐����A<BR>this.�J�e�S���[�s�ɃZ�b�g����B<BR>
     * <BR>
     * �Q�j�������Athis.�J�e�S���[�s�̊e���ڂɃZ�b�g����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strCategoryName - (�J�e�S���[��)<BR>
     * �J�e�S���[��<BR>
     * 
     * @@param l_strOutline - (�T�v)<BR>
     * �T�v<BR>
     * 
     * @@roseuid 4191A0FB00CC
     */
    public WEB3PointCategory(String l_strInstitutionCode, String l_strCategoryName, String l_strOutline) 
    {
        final String STR_METHOD_NAME = " WEB3PointCategory(String , String , String )";
        log.entering(STR_METHOD_NAME);
        
        //�P�j��̃|�C���g�J�e�S���[Params�I�u�W�F�N�g�𐶐����A<BR>this.�J�e�S���[�s�ɃZ�b�g����B
        PointCategoryMasterParams l_pointCategoryParams = new PointCategoryMasterParams();
        this.pointCategoryMasterParams = l_pointCategoryParams;
        
        //�Q�j�������Athis.�J�e�S���[�s�̊e���ڂɃZ�b�g����B
        this.pointCategoryMasterParams.setInstitutionCode(l_strInstitutionCode);
        this.pointCategoryMasterParams.setCategoryName(l_strCategoryName);
        this.pointCategoryMasterParams.setCategoryOutline(l_strOutline);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (setNew�J�e�S���[�ԍ�)<BR>
     * �V�K�̃J�e�S���[�ԍ����擾���Athis.�J�e�S���[�s.�J�e�S���[�ԍ��ɃZ�b�g����B<BR>
     * <BR>
     * �P�j�ȉ��̏����ŁA�|�C���g�J�e�S���[�}�X�^�[�̌������s���B<BR>
     * <BR>
     * ���������F �،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * order by�F �J�e�S���[�ԍ��̍~��<BR>
     * <BR>
     * �Q�j���X�g��1�Ԗڂ̗v�f���擾����B<BR>
     *    ���J�e�S���[�ԍ����ő�̂��̂��擾����B<BR>
     * <BR>
     * �R�j�擾�����v�f�̃J�e�S���[�ԍ���+1�����l���A<BR>this.�J�e�S���[�s.�J�e�S���[�ԍ��ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@roseuid 4191A02E034D
     */
    public void setNewCategoryNo(String l_strInstitutionCode) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " setNewCategoryNo(String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            // �P�j�ȉ��̏����ŁA�|�C���g�J�e�S���[�}�X�^�[�̌������s���B
            String l_strWhere = "institution_code = ?";
            String l_strOrderBy = "category_no desc";
            Object[] l_objBinds = new Object[]{l_strInstitutionCode};         

            List l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                PointCategoryMasterRow.TYPE,
                l_strWhere,
                l_strOrderBy,
                null,
                l_objBinds);//DataException
        
            // �Q�j���X�g��1�Ԗڂ̗v�f���擾����B
            int l_intNewCategoryNo = 0;
            if (l_lisRecords != null && l_lisRecords.size() > 0)
            {
                log.debug(" �Q�j���X�g��1�Ԗڂ̗v�f���擾����B");
                PointCategoryMasterParams l_param = (PointCategoryMasterParams)l_lisRecords.get(0);
                if (l_param == null)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }

                l_intNewCategoryNo = l_param.getCategoryNo() ;
            }
            
            // �R�j�擾�����v�f�̃J�e�S���[�ԍ���+1�����l���Athis.�J�e�S���[�s.�J�e�S���[�ԍ��ɃZ�b�g����B
            this.pointCategoryMasterParams.setCategoryNo(l_intNewCategoryNo + 1);            
        }
        catch (DataException l_e)
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
     * (set�J�e�S���[��)<BR>
     * �����̃J�e�S���[�����Athis.�J�e�S���[�s.�J�e�S���[���ɃZ�b�g����B<BR>
     * @@param l_strCategoryName - (�J�e�S���[��)<BR>
     * �J�e�S���[��<BR>
     * @@roseuid 4191B4DD00F3
     */
    public void setCategoryName(String l_strCategoryName) 
    {
        final String STR_METHOD_NAME = " setCategoryName(String)";
        log.entering(STR_METHOD_NAME);

        if (pointCategoryMasterParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "�|�C���g�J�e�S���[�s�I�u�W�F�N�g=null.");
        }
        this.pointCategoryMasterParams.setCategoryName(l_strCategoryName);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�J�e�S���[�T�v)<BR>
     * �����̃J�e�S���[�T�v���Athis.�J�e�S���[�s.�J�e�S���[�T�v�ɃZ�b�g����B<BR>
     * @@param l_strOutline - (�T�v)<BR>
     * �J�e�S���[�T�v<BR>
     * @@roseuid 4191B5450170
     */
    public void setCategoryOutline(String l_strOutline) 
    {
        final String STR_METHOD_NAME = " setCategoryOutline(String)";
        log.entering(STR_METHOD_NAME);
        
        if (pointCategoryMasterParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "�|�C���g�J�e�S���[�s�I�u�W�F�N�g=null.");
        }
        this.pointCategoryMasterParams.setCategoryOutline(l_strOutline);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�J�e�S���[�ԍ�)<BR>
     * �J�e�S���[�ԍ����擾����B<BR>
     * <BR>
     * this.�J�e�S���[�s.�J�e�S���[�ԍ���ԋp����B<BR>
     * @@return long
     * @@roseuid 4192D733028D
     */
    public long getCategoryNo() 
    {
        if (pointCategoryMasterParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "�|�C���g�J�e�S���[�s�I�u�W�F�N�g=null.");
        }
        return this.pointCategoryMasterParams.getCategoryNo();
    }
    
    /**
     * (get�J�e�S���[��)<BR>
     * �J�e�S���[�����擾����B<BR>
     * <BR>
     * this.�J�e�S���[�s.�J�e�S���[����ԋp����B<BR>
     * @@return String
     * @@roseuid 4192D76F03A6
     */
    public String getCategoryName() 
    {
        if (pointCategoryMasterParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "�|�C���g�J�e�S���[�s�I�u�W�F�N�g=null.");
        }
        return this.pointCategoryMasterParams.getCategoryName();
    }
    
    /**
     * (get�J�e�S���[�T�v)<BR>
     * �J�e�S���[�T�v���擾����B<BR>
     * <BR>
     * this.�J�e�S���[�s.�J�e�S���[�T�v��ԋp����B<BR>
     * @@return String
     * @@roseuid 4192D7830193
     */
    public String getCategoryOutline() 
    {
        if (pointCategoryMasterParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "�|�C���g�J�e�S���[�s�I�u�W�F�N�g=null.");
        }
        return this.pointCategoryMasterParams.getCategoryOutline();
    }
    
    /**
     * this.�J�e�S���[�s��ԋp����B<BR>
     * @@return Object
     * @@roseuid 4191BAA101BE
     */
    public Object getDataSourceObject() 
    {
        return this.pointCategoryMasterParams;
    }
    
    /**
     * this.�J�e�S���[�s���R�s�[���āA�������e�̕ʃC���X�^���X���쐬����iclone�j�B<BR>
     * �쐬�����R�s�[�����g��this.�J�e�S���[�s�ɃZ�b�g����B<BR>
     * @@roseuid 4191B9400325
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams()";
        log.entering(STR_METHOD_NAME);
        
        PointCategoryMasterParams l_pointCategoryParams = 
            new PointCategoryMasterParams(this.pointCategoryMasterParams);
        this.pointCategoryMasterParams = l_pointCategoryParams;
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
