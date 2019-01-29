head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.28.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenLoginAccountRelation.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���O�C���E�A�J�E���g�E�����[�V����(WEB3AccOpenLoginAccountRelation.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/15 ���G�� (���u) �V�K�쐬
*/
package webbroker3.accountopen;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAccountRelationDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAccountRelationParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAccountRelationRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���O�C���E�A�J�E���g�E�����[�V����)<BR>
 * ���O�C���E�A�J�E���g�E�����[�V����<BR>
 *<BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AccOpenLoginAccountRelation 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenLoginAccountRelation.class);
    
    /**
     * (���O�C���E�A�J�E���g�E�����[�V�����s)<BR>
     * ���O�C���E�A�J�E���g�E�����[�V�����s<BR>
     * <BR>
     * �� ���O�C���E�A�J�E���g�E�����[�V����Params�N���X��DDL��莩����������B<BR>
     */
    private LoginAccountRelationParams loginAccountRelationParams;
    
    /**
     * (���O�C���E�A�J�E���g�E�����[�V����)<BR>
     * ���O�C���E�A�J�E���g�E�����[�V�����I�u�W�F�N�g���擾����B <BR> 
     * <BR>
     * �ȉ��̏����� <BR>
     * ���O�C���E�A�J�E���g�E�����[�V�����e�[�u������������B<BR> 
     * <BR>
     * �@@[����] <BR>
     * �@@���O�C���E�A�J�E���g�E�����[�V����.���O�C���^�C�v�h�c = ����.���O�C���^�C�v�h�c <BR>
     * <BR>
     * �������ʂ̃��O�C���E�A�J�E���g�E�����[�V�����s�I�u�W�F�N�g���A<BR> 
     * this.���O�C���E�A�J�E���g�E�����[�V�����s�ɃZ�b�g����B<BR>
     * @@param l_strLoginTypeId - (���O�C���^�C�v�h�c)<BR>
     * ���O�C���^�C�v�h�c<BR>
     * @@throws WEB3BaseException
     */
    public WEB3AccOpenLoginAccountRelation(String l_strLoginTypeId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3AccOpenLoginAccountRelation(String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            // �@@[����]
            // �@@���O�C���E�A�J�E���g�E�����[�V����.���O�C���^�C�v�h�c = ����.���O�C���^�C�v�h�c 
            if (WEB3StringTypeUtility.isEmpty(l_strLoginTypeId))
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                     this.getClass().getName() + STR_METHOD_NAME);
            }
            
            LoginAccountRelationRow l_row = 
                (LoginAccountRelationRow)LoginAccountRelationDao.findRowByTypeId(
                    Long.parseLong(l_strLoginTypeId));
            
            if (l_row == null)
            {
                log.debug("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
            
            // �������ʂ̃��O�C���E�A�J�E���g�E�����[�V�����s�I�u�W�F�N�g���A
            // this.���O�C���E�A�J�E���g�E�����[�V�����s�ɃZ�b�g����B
            this.loginAccountRelationParams = new LoginAccountRelationParams(l_row);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
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
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�����[�V����ID)<BR>
     * �����[�V����ID���擾����B<BR> 
     * <BR>
     * this.���O�C���E�A�J�E���g�E�����[�V�����s.�����[�V�����h�c��ԋp����B<BR>
     * @@return String
     */
    public String getRelationId()
    {
        final String STR_METHOD_NAME = "getRelationId()";
        log.exiting(STR_METHOD_NAME);
        
        //�����[�V����ID���擾����B
        log.exiting(STR_METHOD_NAME);
        return WEB3StringTypeUtility.formatNumber(
            this.loginAccountRelationParams.getRelationId());
    }
    
    /**
     * @@return Object
     */
    public Object getDataSourceObject() 
    {
        final String STR_METHOD_NAME = " getDataSourceObject()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return this.loginAccountRelationParams;   
    }
}
@
