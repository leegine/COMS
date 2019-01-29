head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminRegistUnitCreateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ғo�^���쐬�T�[�r�XImpl(WEB3AdminMCAdminRegistUnitCreateServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/29 ���z (���u) �V�K�쐬 
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;

import webbroker3.adminmc.message.WEB3AdminMCAdminRegistUnit;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminRegistUnitCreateService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ғo�^���쐬�T�[�r�XImpl)<BR>
 * �Ǘ��ғo�^���쐬�T�[�r�X<BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AdminMCAdminRegistUnitCreateServiceImpl implements WEB3AdminMCAdminRegistUnitCreateService 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminMCAdminRegistUnitCreateServiceImpl.class);
    
    /**
     * (create�Ǘ��ғo�^���)<BR>
     * �Ǘ��҃I�u�W�F�N�g���A�Ǘ��ғo�^��񃁃b�Z�[�W�f�[�^�I�u�W�F�N�g���쐬����B<BR>
     * <BR>
     * �Ǘ��ғo�^���𐶐�����B<BR>
     * <BR>
     * �Ǘ��҃I�u�W�F�N�g.getDataSourceObject()�ɂĎ擾�����Ǘ��ҍs�I�u�W�F�N�g���<BR>
     * ���������I�u�W�F�N�g�ɁA�ȉ��̒ʂ�v���p�e�B�Z�b�g���s���ԋp����B<BR>
     * <BR>
     * �@@���X�R�[�h = �Ǘ��ҍs.���X�R�[�h<BR>
     * �@@�Ǘ��҃R�[�h = �Ǘ��ҍs.�Ǘ��҃R�[�h<BR>
     * �@@�Ǘ��Җ� = �Ǘ��ҍs.�Ǘ��Җ�<BR>
     * �@@���[���A�h���X = �Ǘ��ҍs.���[���A�h���X<BR>
     * �@@�������x���R�[�h = �Ǘ��ҍs.�������x��<BR>
     * �@@�G���[�� = ���O�C���s��.get(���O�C������)<BR>
     * �@@�X�V���� = �Ǘ��ҍs.�X�V����<BR>
     * �@@�X�V�҃R�[�h = �Ǘ��ҍs.�X�V�҃R�[�h<BR>
     * <BR>
     * �@@�� ���O�C���s<BR>
     * �@@�ȉ��̏����Ń��O�C���e�[�u�����������A�擾�����s�I�u�W�F�N�g�B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@���O�C���e�[�u��.���O�C��ID = �Ǘ���.get���O�C��ID()<BR>
     * @@param l_administrator - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminRegistUnit
     * @@roseuid 417DCBD803E4
     */
    public WEB3AdminMCAdminRegistUnit createAdminRegistUnit(WEB3Administrator l_administrator) 
        throws WEB3BaseException
    {
        String l_strMethodName = "createAdminRegistUnit(WEB3Administrator l_administrator)";
        log.entering(l_strMethodName);
        
        if (l_administrator == null)
        {
            log.error("�p�����[�^�l��NULL����I");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        //�Ǘ��ғo�^���𐶐�����B 
        WEB3AdminMCAdminRegistUnit l_web3AdminMCAdminRegistUnit = 
            new WEB3AdminMCAdminRegistUnit();
        
        //�Ǘ��҃I�u�W�F�N�g.getDataSourceObject()�ɂĎ擾�����Ǘ��ҍs�I�u�W�F�N�g���
        //���������I�u�W�F�N�g�ɁA�ȉ��̒ʂ�v���p�e�B�Z�b�g���s���ԋp����B
        
        //a> �Ǘ��ҍs�I�u�W�F�N�g�𐶐�
        AdministratorRow l_administratorRow = 
            (AdministratorRow)l_administrator.getDataSourceObject();
        
        //b> ���X�R�[�h = �Ǘ��ҍs.���X�R�[�h
        l_web3AdminMCAdminRegistUnit.branchCode =
            l_administratorRow.getBranchCode();
        
        //c> �Ǘ��҃R�[�h = �Ǘ��ҍs.�Ǘ��҃R�[�h
        l_web3AdminMCAdminRegistUnit.administratorCode =
            l_administratorRow.getAdministratorCode();
        
        //d> �Ǘ��Җ� = �Ǘ��ҍs.�Ǘ��Җ�
        l_web3AdminMCAdminRegistUnit.administratorName =
            l_administratorRow.getName();
        
        //e> ���[���A�h���X = �Ǘ��ҍs.���[���A�h���X
        l_web3AdminMCAdminRegistUnit.mailAddress =
            l_administratorRow.getEmailAddress();
            
        //f> �������x���R�[�h = �Ǘ��ҍs.�������x��
        l_web3AdminMCAdminRegistUnit.permissionLevel =
            l_administratorRow.getPermissionLevel();
            
        //g> �G���[�� = ���O�C���s��.get(���O�C������)
        //�� ���O�C���s
        //�ȉ��̏����Ń��O�C���e�[�u�����������A�擾�����s�I�u�W�F�N�g�B             
        //[����]
        //���O�C���e�[�u��.���O�C��ID = �Ǘ���.get���O�C��ID()
        long l_lngLoginId = l_administrator.getLoginId();
        
        LoginRow l_loginRow;
        try
        {
            l_loginRow = LoginDao.findRowByPk(l_lngLoginId);
        }
        catch (DataFindException l_ex)
        {
            log.error("__DataFindException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
        }   
        if (l_loginRow.getFailureCountIsNull())
        {
            l_web3AdminMCAdminRegistUnit.errorCount = null;                                   
        }
        else
        {
            l_web3AdminMCAdminRegistUnit.errorCount =
                String.valueOf(l_loginRow.getFailureCount());            
        }
        //h> �X�V���� = �Ǘ��ҍs.�X�V����
        l_web3AdminMCAdminRegistUnit.updateTimeStamp =
            l_administratorRow.getLastUpdatedTimestamp();
            
        //i> �X�V�҃R�[�h = �Ǘ��ҍs.�X�V�҃R�[�h
        l_web3AdminMCAdminRegistUnit.updaterCode =
            l_administratorRow.getLastUpdater();          
            
        log.exiting(l_strMethodName);
        
        return l_web3AdminMCAdminRegistUnit;
    }
    
    /**
     * (create�Ǘ��ғo�^���)<BR>
     * �Ǘ��҃I�u�W�F�N�g�̔z����A�Ǘ��ғo�^��񃁃b�Z�[�W�f�[�^�I�u�W�F�N�g�̔z����쐬����B<BR>
     * <BR>
     * �����̊Ǘ���[]�̊e�v�f���ɁAthis.create�Ǘ��ғo�^���(�Ǘ���)���R�[�����ĊǗ��ғo�^�����쐬����B<BR>
     * �߂�l��z��ɂĕԋp����B<BR>
     * <BR>
     * <BR>
     * @@param l_administrator - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g�̔z��<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminRegistUnit[]
     * @@roseuid 417DCBD803E6
     */
    public WEB3AdminMCAdminRegistUnit[] createAdminRegistUnit(WEB3Administrator[] l_administrator) 
        throws WEB3BaseException
    {
        String l_strMethodName = "createAdminRegistUnit(WEB3Administrator[] l_administrator)";
        log.entering(l_strMethodName);
        
        if (l_administrator == null)
        {
            log.error("�p�����[�^�l��NULL����I");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        //�����̊Ǘ���[]�̊e�v�f���ɁAthis.create�Ǘ��ғo�^���(�Ǘ���)���R�[�����ĊǗ��ғo�^�����쐬����B
        //�߂�l��z��ɂĕԋp����B
        WEB3AdminMCAdminRegistUnit[] l_web3AdminMCAdminRegistUnit =
            new WEB3AdminMCAdminRegistUnit[l_administrator.length];
            
        for (int i = 0; i < l_administrator.length; i++)
        {
            l_web3AdminMCAdminRegistUnit[i] = this.createAdminRegistUnit(l_administrator[i]);           
        }
        
        log.exiting(l_strMethodName); 
               
        return l_web3AdminMCAdminRegistUnit;
    }
}
@
