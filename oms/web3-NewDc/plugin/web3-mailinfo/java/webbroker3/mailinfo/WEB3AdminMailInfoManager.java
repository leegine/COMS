head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.16.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[�����Ǘ�(WEB3AdminMailInfoManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  鰊](���u) �V�K�쐬
Revesion History : 2004/10/19  �����F(���u) �쐬
*/
package webbroker3.mailinfo;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeMailInfo;
import webbroker3.gentrade.data.MailInfoParams;
import webbroker3.gentrade.data.MailInfoRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (���[�����Ǘ�)<BR>
 * ���[�����Ǘ��N���X<BR>
 * 
 * @@author 鰊]
 * @@version 1.0
 */
public class WEB3AdminMailInfoManager 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMailInfoManager.class); 
    
    /**
     * @@roseuid 416CDE9101C5
     */
    public WEB3AdminMailInfoManager() 
    {
     
    }
    
    /**
     * (get���[��)<BR>
     * �w�肳�ꂽ�������烁�[���I�u�W�F�N�g���擾���A�ԋp����B<BR>
     * <BR>
     * 1) ���[���̃R���X�g���N�^���R�[������B<BR>
     * [����]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * �@@���M���[���敪=����.���M���[���敪<BR>
     * �@@����ID=����.����ID<BR>
     * <BR>
     * 2) ���[���̃R���X�g���N�^���牽������̗�O���Ԃ��ꂽ�ꍇ�A<BR>
     * �@@��O�𖳎����Anull��ԋp����B<BR>
     * <BR>
     * 3) �����������[���I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strSendMailDiv - (���M���[���敪)<BR>
     * ���M���[���敪ID<BR>
     * @@param l_strDiscernmentId - (����ID)<BR>
     * ����ID<BR>
     * @@return webbroker3.gentrade.WEB3GentradeMailInfo
     * @@roseuid 413C1CEA005D
     */
    public static WEB3GentradeMailInfo getMail(String l_strInstitutionCode, String l_strSendMailDiv, String l_strDiscernmentId)
    throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getMail(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strInstitutionCode == null || l_strSendMailDiv == null || l_strDiscernmentId == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.exiting(STR_METHOD_NAME);
            return null;
        }   
        
        //���[���I�u�W�F�N�g�̌���     
        WEB3GentradeMailInfo l_returnMail = null;
        try
        {
            //���[���̃R���X�g���N�^���R�[������B
            l_returnMail = new WEB3GentradeMailInfo(l_strInstitutionCode, l_strSendMailDiv, l_strDiscernmentId);
        }
        catch (WEB3BaseException l_ex)
        { 
            log.debug("��O�𖳎����Anull��ԋp����");            
            log.exiting(STR_METHOD_NAME);
            return null;
        }      
        
        log.exiting(STR_METHOD_NAME);
        return l_returnMail;
    }
    
    /**
     * (get���[���ꗗ)<BR>
     * �w�肳�ꂽ�،���ЂɕR�t�����[�������o����B<BR>
     * <BR>
     * 1) �ȉ��̏����Łu���[���e�[�u���v����������B<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * <BR>
     * [���я�]<BR>
     * �@@����.�\�[�g����<BR>
     * <BR>
     * 2) �������ʂ̌������A�ȉ����J��Ԃ��B<BR>
     *  2-1) ���[���̃R���X�g���N�^���R�[�����A���[���I�u�W�F�N�g�𐶐�����B<BR>
     * [����]<BR>
     * �@@���[��Row=�������ʂ̃��[��Params<BR>
     *  2-2) ���[���I�u�W�F�N�g��z��ɒǉ�����B<BR>
     * <BR>
     * 3) 2)�ō쐬�����z���ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * �\�[�g����<BR>
     * @@return webbroker3.gentrade.WEB3GentradeMailInfo[ ]
     * @@roseuid 413C1CF3000F
     */
    public static WEB3GentradeMailInfo[] getMailList(String l_strInstitutionCode, String l_strSortCond) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getMailList(String, String)";
        log.entering(STR_METHOD_NAME);
        
        if(l_strInstitutionCode == null)
        {
            log.error(STR_METHOD_NAME + "�p�����[�^.�،���ЃR�[�hNull�o���Ȃ��B" + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                STR_METHOD_NAME,
                "�p�����[�^.�،���ЃR�[�hNull�o���Ȃ��B");            
        }
        
        //�������ʂ̃��[��Params
        List l_lstReturnRecord = null;
        try
        {
            //[��������]
            //�،���ЃR�[�h=����.�،���ЃR�[�h
            StringBuffer l_sbWhere = new StringBuffer();            
            l_sbWhere.append("institution_code=?");
            
            Object[] l_objWhere = {l_strInstitutionCode};
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //�w�肳�ꂽ�،���ЂɕR�t�����[�������o����B
            l_lstReturnRecord = l_queryProcessor.doFindAllQuery(
                MailInfoRow.TYPE,
                l_sbWhere.toString(),
                l_strSortCond,
                null,
                l_objWhere);
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminMailInfoManager" + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminMailInfoManager" + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminMailInfoManager" + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //���[���I�u�W�F�N�g�z��
        int l_intMailInfoCnt = l_lstReturnRecord.size();        
        WEB3GentradeMailInfo[] l_returnMailInfos = new WEB3GentradeMailInfo[l_intMailInfoCnt];
        for(int i=0; i<l_intMailInfoCnt; i++)
        {            
            l_returnMailInfos[i] = new WEB3GentradeMailInfo((MailInfoParams)l_lstReturnRecord.get(i));
        }                

        log.exiting(STR_METHOD_NAME);
        return l_returnMailInfos;
    }
}
@
