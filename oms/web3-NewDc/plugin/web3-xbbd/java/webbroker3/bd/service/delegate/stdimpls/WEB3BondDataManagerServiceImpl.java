head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDataManagerServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���f�[�^�}�l�[�W���[�T�[�r�XImpl(WEB3BondDataManagerServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 ꎉ�(���u) �V�K�쐬         
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.data.CustodianDao;
import webbroker3.bd.data.CustodianRow;
import webbroker3.bd.service.delegate.WEB3BondDataManagerService;

/**
 * (���f�[�^�}�l�[�W���[�T�[�r�XImpl)<BR>
 * ���f�[�^�}�l�[�W���[�T�[�r�X�N���XImpl�N���X
 * 
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3BondDataManagerServiceImpl implements WEB3BondDataManagerService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3BondDataManagerServiceImpl.class);
    
    /**
     * @@roseuid 44E3362B0251
     */
    public WEB3BondDataManagerServiceImpl() 
    {
     
    }
    
    /**
     * (get�J�X�g�f�B�A���ꗗ)<BR>
     * �J�X�g�f�B�A���ꗗ���擾����B<BR>
     * <BR>
     * �P�j�@@�J�X�g�f�B�A���}�X�^�e�[�u�����������A�������ʂ�List���擾���ĕԂ��B <BR>
     * �@@�@@�m���������n <BR>
     * �@@�@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * �@@�@@�m�\�[�g�����n <BR>
     * �@@�@@�@@ �J�X�g�f�B�A���R�[�h�@@����<BR>
     * <BR>
     * �Q�jList��Ԃ��B
     * @@param l_institution - �،����
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 44B768520236
     */
    public List getCustodianList(Institution l_institution) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getCustodianList(Institution)";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }   
        
        //�،���ЃR�[�h = ����.�،���ЃR�[�h
        String l_strQueryString = " institution_code = ?  ";
        
        //�J�X�g�f�B�A���R�[�h�@@����
        String l_strSortCond = " custodian_code ASC ";
        Object[] l_objBindVars = new Object[1];

        l_objBindVars[0] = l_institution.getInstitutionCode();

        
        //return List
        List l_lisCustodianList = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisCustodianList = l_queryProcessor.doFindAllQuery(
                CustodianRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null, 
                l_objBindVars);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), 
                l_ex);
        }    
        log.exiting(STR_METHOD_NAME);
        return l_lisCustodianList;
    }
    
    /**
     * (get�J�X�g�f�B�A��)<BR>
     * �J�X�g�f�B�A��Row���擾����B<BR>
     * <BR>
     * �P�j�ȉ��̏����ŁA�J�X�g�f�B�A���e�[�u������������B <BR>
     * �@@[��������] <BR>
     * �@@�@@�،���ЃR�[�h=����.�،����.getInstitutionCode()<BR>
     * �@@�@@�J�X�g�f�B�A���R�[�h=����.�J�X�g�f�B�A���R�[�h <BR>
     * �@@�@@��Dao���g���Č�������B<BR>
     * �@@�@@�����R�[�h�����݂��Ȃ��ꍇ�A��O�u�Y���J�X�g�f�B�A�������݂��܂���B�v���X���[����B <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02547<BR>
     * <BR>
     * �Q�j�������ʂ�Row��Ԃ��B�@@
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g
     * @@param l_strCustodianCode - (�J�X�g�f�B�A���R�[�h)<BR>
     * �J�X�g�f�B�A���R�[�h
     * @@return CustodianRow
     * @@throws WEB3BaseException
     * @@roseuid 44D6A42002CE
     */
    public CustodianRow getCustodian(Institution l_institution, String l_strCustodianCode) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getCustodian(Institution, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }   

        CustodianRow l_custodianRow = null;
        try
        {
            l_custodianRow = 
                CustodianDao.findRowByInstitutionCodeCustodianCode(
                    l_institution.getInstitutionCode(), 
                    l_strCustodianCode);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), 
                l_ex);
        }    
        if (l_custodianRow == null)
        {
            log.debug("�Y���J�X�g�f�B�A�������݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02547,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Y���J�X�g�f�B�A�������݂��܂���B");
        }
        return l_custodianRow;
    }
}
@
