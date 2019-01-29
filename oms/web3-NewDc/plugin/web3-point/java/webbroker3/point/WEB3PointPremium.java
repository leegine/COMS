head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.00.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointPremium.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�i�i(WEB3PointPremium.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.point.data.PointPremiumMasterParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (�|�C���g�i�i)<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3PointPremium implements BusinessObject 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3PointPremium.class);
    
    /**
     * (�i�i�s)<BR>
     * �|�C���g�i�i�s�I�u�W�F�N�g<BR>
     */
    private PointPremiumMasterParams pointPremiumMasterParams;
    
    /**
     * (�|�C���g�i�i)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �������Athis.�i�i�s�ɃZ�b�g����B<BR>
     * @@param l_pointPremiumParams - (�i�i�s)<BR>
     * �|�C���g�i�i�s�I�u�W�F�N�g<BR>
     * 
     * @@roseuid 419331A20202
     */
    public WEB3PointPremium(PointPremiumMasterParams l_pointPremiumParams) 
    {
        final String STR_METHOD_NAME = " WEB3PointPremium(PointPremiumMasterParams)";
        log.entering(STR_METHOD_NAME);
        
        this.pointPremiumMasterParams = l_pointPremiumParams;
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (�|�C���g�i�i)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �P�j��̃|�C���g�i�iParams�I�u�W�F�N�g�𐶐����Athis.�i�i�s�ɃZ�b�g����B<BR>
     * <BR>
     * �Q�j�������Athis.�i�i�s�̊e���ڂɃZ�b�g����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_lngCategoryNo - (�J�e�S���[�ԍ�)<BR>
     * �J�e�S���[�ԍ�<BR>
     * 
     * @@param l_strPremiumNo - (�i�i�ԍ�)<BR>
     * �i�i�ԍ�<BR>
     * 
     * @@param l_strPremiumName - (�i�i��)<BR>
     * �i�i��<BR>
     * 
     * @@param l_lngRequiredPoint - (�K�v�|�C���g)<BR>
     * �K�v�|�C���g<BR>
     * 
     * @@param l_datStartDateTime - (�񋟊J�n����)<BR>
     * �񋟊J�n����<BR>
     * 
     * @@param l_datEndDateTime - (�񋟏I������)<BR>
     * �񋟏I������<BR>
     * 
     * @@roseuid 419331A201E3
     */
    public WEB3PointPremium(
        String l_strInstitutionCode, 
        long l_lngCategoryNo, 
        String l_strPremiumNo, 
        String l_strPremiumName, 
        long l_lngRequiredPoint,
        Date l_datStartDateTime, 
        Date l_datEndDateTime) 
    {
        final String STR_METHOD_NAME = " WEB3PointPremium(String , long , String , String , long , Date , Date )";
        log.entering(STR_METHOD_NAME);
        
        //�P�j��̃|�C���g�i�iParams�I�u�W�F�N�g�𐶐����Athis.�i�i�s�ɃZ�b�g����B
        PointPremiumMasterParams l_pointPremiumParams = new PointPremiumMasterParams();
        this.pointPremiumMasterParams = l_pointPremiumParams;
        
        //�������Athis.�i�i�s�̊e���ڂɃZ�b�g����
        this.pointPremiumMasterParams.setInstitutionCode(l_strInstitutionCode);
        this.pointPremiumMasterParams.setCategoryNo((int)l_lngCategoryNo);
        this.pointPremiumMasterParams.setPremiumNo(l_strPremiumNo);
        this.pointPremiumMasterParams.setPremiumName(l_strPremiumName);
        this.pointPremiumMasterParams.setRequiredPoint((int)l_lngRequiredPoint);
        this.pointPremiumMasterParams.setStartDateTime(l_datStartDateTime);
        this.pointPremiumMasterParams.setEndDateTime(l_datEndDateTime);        
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (get�i�i�ԍ�)<BR>
     * �i�i�ԍ����擾����B<BR>
     * <BR>
     * this.�i�i�s.�i�i�ԍ���ԋp����B<BR>
     * @@return String
     * @@roseuid 4192E597027F
     */
    public String getPremiumNo() 
    {
        if (this.pointPremiumMasterParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "�|�C���g�i�i�s�I�u�W�F�N�g=null.");
        }

        return this.pointPremiumMasterParams.getPremiumNo();
    }
    
    /**
     * (get�i�i��)<BR>
     * �i�i�����擾����B<BR>
     * <BR>
     * this.�i�i�s.�i�i����ԋp����B<BR>
     * @@return String
     * @@roseuid 4192E69300D9
     */
    public String getPremiumName() 
    {
        if (this.pointPremiumMasterParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "�|�C���g�i�i�s�I�u�W�F�N�g=null.");
        }
        return this.pointPremiumMasterParams.getPremiumName();
    }
    
    /**
     * (get�K�v�|�C���g)<BR>
     * �K�v�|�C���g���擾����B<BR>
     * <BR>
     * this.�i�i�s.�K�v�|�C���g��ԋp����B<BR>
     * @@return long
     * @@roseuid 4192E6F3029E
     */
    public long getRequiredPoint() 
    {
        if (this.pointPremiumMasterParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "�|�C���g�i�i�s�I�u�W�F�N�g=null.");
        }
        return this.pointPremiumMasterParams.getRequiredPoint();
    }
    
    /**
     * (get�񋟊J�n����)<BR>
     * �񋟊J�n�������擾����B<BR>
     * <BR>
     * this.�i�i�s.�񋟊J�n������ԋp����B<BR>
     * @@return Date
     * @@roseuid 4192E71C033A
     */
    public Date getStartDateTime() 
    {
        if (this.pointPremiumMasterParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "�|�C���g�i�i�s�I�u�W�F�N�g=null.");
        }
        return this.pointPremiumMasterParams.getStartDateTime();
    }
    
    /**
     * (get�񋟏I������)<BR>
     * �񋟏I���������擾����B<BR>
     * <BR>
     * this.�i�i�s.�񋟏I��������ԋp����B<BR>
     * @@return Date
     * @@roseuid 4192E74902BD
     */
    public Date getEndDateTime() 
    {
        if (this.pointPremiumMasterParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "�|�C���g�i�i�s�I�u�W�F�N�g=null.");
        }
        return this.pointPremiumMasterParams.getEndDateTime();
    }
    
    /**
     * (set�i�i��)<BR>
     * �i�i�����Z�b�g����B<BR>
     * <BR>
     * ����.�i�i�����Athis.�i�i�s.�i�i���ɃZ�b�g����B<BR>
     * @@param l_strPremiumName - (�i�i��)<BR>
     * �i�i��<BR>
     * @@roseuid 4193425A02AE
     */
    public void setPremiumName(String l_strPremiumName) 
    {
        final String STR_METHOD_NAME = " setPremiumName(String)";
        log.entering(STR_METHOD_NAME);
        
        if (this.pointPremiumMasterParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "�|�C���g�i�i�s�I�u�W�F�N�g=null.");
        }
        this.pointPremiumMasterParams.setPremiumName(l_strPremiumName);
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set�K�v�|�C���g)<BR>
     * �K�v�|�C���g���Z�b�g����B<BR>
     * <BR>
     * ����.�K�v�|�C���g���Athis.�i�i�s.�K�v�|�C���g�ɃZ�b�g����B<BR>
     * @@param l_lngRequiredPoint - (�K�v�|�C���g)<BR>
     * �K�v�|�C���g<BR>
     * @@roseuid 4193429E0185
     */
    public void setRequiredPoint(long l_lngRequiredPoint) 
    {
        final String STR_METHOD_NAME = " setRequiredPoint(long)";
        log.entering(STR_METHOD_NAME);
        
        if (this.pointPremiumMasterParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "�|�C���g�i�i�s�I�u�W�F�N�g=null.");
        }

        this.pointPremiumMasterParams.setRequiredPoint((int)l_lngRequiredPoint);
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set�񋟊J�n����)<BR>
     * �񋟊J�n�������Z�b�g����B<BR>
     * <BR>
     * ����.�񋟊J�n�������Athis.�i�i�s.�񋟊J�n�����ɃZ�b�g����B<BR>
     * @@param l_datStartDateTime - (�񋟊J�n����)<BR>
     * �񋟊J�n����<BR>
     * @@roseuid 419342D0006C
     */
    public void setStartDateTime(Date l_datStartDateTime) 
    {
        final String STR_METHOD_NAME = " setStartDateTime(Date)";
        log.entering(STR_METHOD_NAME);
        
        if (this.pointPremiumMasterParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "�|�C���g�i�i�s�I�u�W�F�N�g=null.");
        }
        this.pointPremiumMasterParams.setStartDateTime(l_datStartDateTime);
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set�񋟏I������)<BR>
     * �񋟏I���������Z�b�g����B<BR>
     * <BR>
     * ����.�񋟏I���������Athis.�i�i�s.�񋟏I�������ɃZ�b�g����B<BR>
     * @@param l_datEndDateTime - (�񋟏I������)<BR>
     * �񋟏I������<BR>
     * @@roseuid 419343010240
     */
    public void setEndDateTime(Date l_datEndDateTime) 
    {
        final String STR_METHOD_NAME = " setEndDateTime(Date)";
        log.entering(STR_METHOD_NAME);
        
        if (this.pointPremiumMasterParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "�|�C���g�i�i�s�I�u�W�F�N�g=null.");
        }
        this.pointPremiumMasterParams.setEndDateTime(l_datEndDateTime);
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * this.�i�i�s��ԋp����B<BR>
     * @@return Object
     * @@roseuid 4191BBAE02A9
     */
    public Object getDataSourceObject() 
    {
        return this.pointPremiumMasterParams;
    }
    
    /**
     * this.�i�i�s���R�s�[���āA�������e�̕ʃC���X�^���X���쐬����iclone�j�B<BR>
     * �쐬�����R�s�[�����g��this.�i�i�s�ɃZ�b�g����B<BR>
     * @@roseuid 4191BBAE02A8
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams()";
        log.entering(STR_METHOD_NAME);
        
        PointPremiumMasterParams l_pointPremiumMasterParams = new PointPremiumMasterParams(this.pointPremiumMasterParams);
        this.pointPremiumMasterParams = l_pointPremiumMasterParams;
        
        log.exiting(STR_METHOD_NAME);     
    }
}
@
