head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.30.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenBranch.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���X(WEB3AccOpenBranch.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/15 ���G�� (���u) �V�K�쐬
 */
package webbroker3.accountopen;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���X)<BR>
 * ���X<BR>
 *<BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AccOpenBranch 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenBranch.class);
    
    /**
     * (���X�s)<BR>
     * ���X�s<BR>
     * <BR>
     * �� ���XParams�N���X��DDL��莩����������B<BR>
     */
    private BranchParams branchParams;
    
    /**
     * (���X)<BR>
     * ���X�I�u�W�F�N�g���擾����B <BR> 
     * <BR>
     * �ȉ��̏����ŕ��X�e�[�u������������B<BR> 
     * <BR>
     * �@@[����] <BR>
     * �@@���X.�،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * �@@���X.���X�R�[�h = ����.���X�R�[�h <BR>
     * <BR>
     * �������ʂ̕��X�s�I�u�W�F�N�g���Athis.���X�s�ɃZ�b�g����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public WEB3AccOpenBranch(
        String l_strInstitutionCode, 
        String l_strBranchCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3AccOpenBranch(String, String)";
        log.entering(STR_METHOD_NAME);    
        
        try
        {
            // �@@���X.�،���ЃR�[�h = ����.�،���ЃR�[�h 
            // �@@���X.���X�R�[�h = ����.���X�R�[�h   
            BranchRow l_row = (BranchRow)BranchDao.findRowByInstitutionCodeBranchCode(
                l_strInstitutionCode, 
                l_strBranchCode);
            if (l_row == null)
            {
                log.debug("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
            
            // �������ʂ̕��X�s�I�u�W�F�N�g���Athis.���X�s�ɃZ�b�g����B
            this.branchParams = new BranchParams(l_row);
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
     * (get�ڋq���O�C���^�C�vID)<BR>
     * �ڋq���O�C���^�C�vID���擾����B<BR> 
     * <BR>
     * this.���X�s.�ڋq���O�C���^�C�vID��ԋp����B<BR>
     * @@return String
     */
    public String getAccountTypeId()
    {
        final String STR_METHOD_NAME = "getAccountTypeId()";
        log.entering(STR_METHOD_NAME);        

        //�ڋq���O�C���^�C�vID���擾����
        //this.���X�s.�ڋq���O�C���^�C�vID��ԋp����B
        if (this.branchParams.getAccountTypeIdIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.formatNumber(
                this.branchParams.getAccountTypeId());
        }    
    }
    
    /**
     * (get�ڋq���O�C���O���[�vID)<BR>
     * get�ڋq���O�C���O���[�vID���擾����B<BR> 
     * <BR>
     * this.���X�s.get�ڋq���O�C���O���[�vID��ԋp����B<BR>
     * @@return String
     */
    public String getAccountGroupId()
    {
        final String STR_METHOD_NAME = "getAccountGroupId()";
        log.entering(STR_METHOD_NAME);        

        //get�ڋq���O�C���O���[�vID���擾����B
        //this.���X�s.get�ڋq���O�C���O���[�vID��ԋp����B
        if (this.branchParams.getAccountGroupIdIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.formatNumber(
                this.branchParams.getAccountGroupId());
        }    
    }

    /**
     * (get���XID)<BR>
     * ���XID<BR> 
     * <BR>
     * @@return String
     */
    public String getBranchId()
    {
        final String STR_METHOD_NAME = "getBranchId()";
        log.entering(STR_METHOD_NAME);
        
        //���XID���擾����B
        String l_strBranchId = WEB3StringTypeUtility.formatNumber(
            this.branchParams.getBranchId());
        
        log.exiting(STR_METHOD_NAME);
        return l_strBranchId;
    }
    
    /**
     * @@return Object
     */
    public Object getDataSourceObject() 
    {
        final String STR_METHOD_NAME = " getDataSourceObject()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return this.branchParams;   
    }
}
@
