head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.29.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenBatchBranch.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�b�`�p���X(WEB3AccOpenBatchBranch.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/15 ���G�� (���u) �V�K�쐬
*/
package webbroker3.accountopen;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.accountopen.data.BatchBranchDao;
import webbroker3.accountopen.data.BatchBranchParams;
import webbroker3.accountopen.data.BatchBranchRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�o�b�`�p���X)<BR>
 * �o�b�`�p���X<BR>
 *<BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AccOpenBatchBranch 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenBatchBranch.class);
    
    /**
     * (�o�b�`�p���X�s)<BR>
     * �o�b�`�p���X�s<BR>
     * <BR>
     * �� �o�b�`�p���XParams�N���X��DDL��莩����������B<BR>
     */
    private BatchBranchParams batchBranchParams;
    
    /**
     * (�o�b�`�p���X)<BR>
     * �o�b�`�p���X�I�u�W�F�N�g���擾����B<BR> 
     * <BR>
     * �ȉ��̏����Ńo�b�`�p���X�e�[�u������������B<BR> 
     * <BR>
     * �@@[����] <BR>
     * �@@�o�b�`�p���X.�،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * �@@�o�b�`�p���X.���X�R�[�h = ����.���X�R�[�h <BR>
     * <BR>
     * �������ʂ̃o�b�`�p���X�s�I�u�W�F�N�g���Athis.�o�b�`�p���X�s�ɃZ�b�g����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public WEB3AccOpenBatchBranch(
        String l_strInstitutionCode, 
        String l_strBranchCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3AccOpenBatchBranch(String, String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {        
            // �@@[����]     
            // �@@�o�b�`�p���X.�،���ЃR�[�h = ����.�،���ЃR�[�h     
            //  �@@�o�b�`�p���X.���X�R�[�h = ����.���X�R�[�h
            BatchBranchRow l_row = 
                (BatchBranchRow)BatchBranchDao.findRowByInstitutionCodeBranchCode(
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
            
            //�������ʂ̃o�b�`�p���X�s�I�u�W�F�N�g���Athis.�o�b�`�p���X�s�ɃZ�b�g����B
            this.batchBranchParams = new BatchBranchParams(l_row);
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
     * (get������胁�[�����M�t���O)<BR>
     * ������胁�[�����M�t���O���擾����B<BR> 
     * <BR>
     * this.�o�b�`�p���X.������胁�[�����M�t���O��ԋp����B<BR>
     * @@return String
     */
    public String getEquityOrderExecMailFlag()
    {
        final String STR_METHOD_NAME = "getEquityOrderExecMailFlag()";
        log.entering(STR_METHOD_NAME);
        
        //������胁�[�����M�t���O���擾����B
        //this.�o�b�`�p���X.������胁�[�����M�t���O��ԋp����B
        String l_strMailFlag = WEB3StringTypeUtility.formatNumber(
            this.batchBranchParams.getEquityOrderExeMailFlag());

        log.exiting(STR_METHOD_NAME);
        return l_strMailFlag;
    }
    
    /**
     * (get��������胁�[�����M�t���O)<BR>
     * ��������胁�[�����M�t���O���擾����B<BR> 
     * <BR>
     * this.�o�b�`�p���X.��������胁�[�����M�t���O��ԋp����B<BR>
     * @@return String
     */
    public String getEquityOrderUnexecMailFlag()
    {
        final String STR_METHOD_NAME = "getEquityOrderUnexecMailFlag()";
        log.entering(STR_METHOD_NAME);
        
        //��������胁�[�����M�t���O���擾����B
        //this.�o�b�`�p���X.��������胁�[�����M�t���O��ԋp����B
        String l_strMailFlag = WEB3StringTypeUtility.formatNumber(
            this.batchBranchParams.getEquityOrderUnexecMailFlag());
        
        log.exiting(STR_METHOD_NAME);
        return l_strMailFlag;
    }
    
    /**
     * (get�敨OP��胁�[�����M�t���O)<BR>
     * �敨OP��胁�[�����M�t���O���擾����B<BR> 
     * <BR>
     * this.�o�b�`�p���X.�敨OP��胁�[�����M�t���O��ԋp����B<BR>
     * @@return String
     */
    public String getIfoOrderExecMailFlag()
    {
        final String STR_METHOD_NAME = "getIfoOrderExecMailFlag()";
        log.entering(STR_METHOD_NAME);
        
        //�敨OP��胁�[�����M�t���O���擾����B
        //this.�o�b�`�p���X.�敨OP��胁�[�����M�t���O��ԋp����B
        String l_strMailFlag = WEB3StringTypeUtility.formatNumber(
            this.batchBranchParams.getIfoOrderExecMailFlag());
        
        log.exiting(STR_METHOD_NAME);
        return l_strMailFlag;
    }
    
    /**
     * (get�敨OP����胁�[�����M�t���O)<BR>
     * �敨OP����胁�[�����M�t���O���擾����B<BR> 
     * <BR>
     * this.�o�b�`�p���X.�敨OP����胁�[�����M�t���O��ԋp����B<BR>
     * @@return String
     */
    public String getIfoOrderUnexecMailFlag()
    {
        final String STR_METHOD_NAME = "getIfoOrderUnexecMailFlag()";
        log.entering(STR_METHOD_NAME);
        
        //�敨OP����胁�[�����M�t���O���擾����B
        //this.�o�b�`�p���X.�敨OP����胁�[�����M�t���O��ԋp����B
        String l_strMailFlag = WEB3StringTypeUtility.formatNumber(
            this.batchBranchParams.getIfoOrderUnexecMailFlag());
        
        log.exiting(STR_METHOD_NAME);
        return l_strMailFlag;
    }
    
    /**
     * (get�ē����[�����M�t���O)<BR>
     * �ē����[�����M�t���O���擾����B<BR> 
     * <BR>
     * this.�o�b�`�p���X.�ē����[�����M�t���O��ԋp����B<BR>
     * @@return String
     */
    public String getInformationMailFlag()
    {
        final String STR_METHOD_NAME = "getInformationMailFlag()";
        log.entering(STR_METHOD_NAME);
        
        //�ē����[�����M�t���O���擾����B
        //this.�o�b�`�p���X.�ē����[�����M�t���O��ԋp����B
        String l_strMailFlag = WEB3StringTypeUtility.formatNumber(
            this.batchBranchParams.getInformationMailFlag());
        
        log.exiting(STR_METHOD_NAME);
        return l_strMailFlag;
    }
    
    /**
     * (get�U�։\��)<BR>
     * �U�։\�񐔂��擾����B<BR> 
     * <BR>
     * this.�o�b�`�p���X.�U�։\�񐔂�ԋp����B<BR>
     * @@return long
     */
    public long getTransferCount()
    {
        final String STR_METHOD_NAME = "getTransferCount()";
        log.entering(STR_METHOD_NAME);
        
        //�U�։\�񐔂��擾����B
        //this.�o�b�`�p���X.�U�։\�񐔂�ԋp����B
        long l_lngTransferCount = this.batchBranchParams.getTransferCount();
        
        log.exiting(STR_METHOD_NAME);
        return l_lngTransferCount;
    }    
    
    /**
     * (ge���擪��ʂh�c)<BR>
     * �擪��ʂh�c���擾����B<BR> 
     * <BR>
     * this.�o�b�`�p���X.�擪��ʂh�c��ԋp����B<BR>
     * @@return String
     */
    public String getTopPageId()
    {
        final String STR_METHOD_NAME = "getTopPageId()";
        log.entering(STR_METHOD_NAME);
        
        //�擪��ʂh�c���擾����B
        //this.�o�b�`�p���X.�擪��ʂh�c��ԋp����B
        String l_strTopPageId = this.batchBranchParams.getTopPageId();
        
        log.exiting(STR_METHOD_NAME);
        return l_strTopPageId;
    }
    
    /**
     * @@return Object
     */
    public Object getDataSourceObject() 
    {
        final String STR_METHOD_NAME = " getDataSourceObject()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return this.batchBranchParams;   
    }
}
@
