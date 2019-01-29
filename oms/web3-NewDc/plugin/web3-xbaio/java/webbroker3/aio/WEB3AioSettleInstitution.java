head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.33.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSettleInstitution.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��g���ϋ@@�փN���X(WEB3AioSettleInstitution)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 ���z (���u) �V�K�쐬
                   2004/10/22 ��O�� (���u) ���r���[ 
*/

package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.aio.data.CooperateBankMasterDao;
import webbroker3.aio.data.CooperateBankMasterParams;
import webbroker3.aio.data.CooperateBankMasterRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;


/**
 * (��g���ϋ@@��)<BR>
 * ��g���ϋ@@�փN���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioSettleInstitution implements BusinessObject 
{    
    /**
    * ���O���[�e�B���e�B
    */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AioSettleInstitution.class);
    
    /**
     * (��g���ϋ@@��Row)<BR>
     * ��g���ϋ@@�֍s�I�u�W�F�N�g
     */
    private CooperateBankMasterParams cooperateBankMasterParams;
    
    /**
     * (��g���ϋ@@��)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �����̌��ϋ@@��ID����A��g���ϋ@@�փe�[�u���̃��R�[�h��<BR>
     * �擾���v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_strPaySchemeId - (���ϋ@@��ID)
     * @@return WEB3AioSettleInstitution
     * @@throws WEB3BaseException
     * @@roseuid 40E28D2202B7
     */
    public WEB3AioSettleInstitution(String l_strPaySchemeId)
        throws WEB3BaseException 
    {
        String l_strMethodName = "WEB3AioSettleInstitution(String l_strPaySchemeId)";
        log.entering(l_strMethodName);
        
        try
        {
            //�����̌��ϋ@@��ID����A��g���ϋ@@�փe�[�u���̃��R�[�h���擾���v���p�e�B�ɃZ�b�g����
            CooperateBankMasterRow l_cooperateBankMasterRow = 
                CooperateBankMasterDao.findRowByPaySchemeId(l_strPaySchemeId);
            if (l_cooperateBankMasterRow == null)
            {
                log.debug("��g���ϋ@@�փe�[�u���̃��R�[�h���擾���Ȃ�");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName);
  
            }    
            cooperateBankMasterParams = new CooperateBankMasterParams(l_cooperateBankMasterRow);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, 
                l_ex.getMessage(), 
                l_ex);
        }
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (get����)<BR>
     * ���̂��擾����B
     * @@return String
     * @@roseuid 40F24D8500BD
     */
    public String getName() 
    {
        return cooperateBankMasterParams.getName();   
    }
    
    /**
     * @@return Object
     * @@roseuid 415A72A50177
     */
    public Object getDataSourceObject() 
    {
     return cooperateBankMasterParams;
    }
}
@
