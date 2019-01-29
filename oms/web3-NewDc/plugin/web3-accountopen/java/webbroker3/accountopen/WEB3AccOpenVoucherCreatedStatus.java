head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.29.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucherCreatedStatus.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݓ`�[�쐬�X�e�[�^�X(WEB3AccOpenVoucherCreatedStatus.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 ���o�� �V�K�쐬
*/

package webbroker3.accountopen;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.accountopen.data.AccOpenVoucherStatusParams;
import webbroker3.accountopen.data.AccOpenVoucherStatusRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����J�ݓ`�[�쐬�X�e�[�^�X)<BR>
 * �����J�ݓ`�[�쐬�X�e�[�^�X<BR>
 * 
 * @@author ���o��
 * @@version 1.0 
 */
public class WEB3AccOpenVoucherCreatedStatus implements BusinessObject
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AccOpenVoucherCreatedStatus.class);
    
    /**
     * (�����J�ݓ`�[�쐬�X�e�[�^�X�s)<BR>
     * �����J�ݓ`�[�쐬�X�e�[�^�X�s�I�u�W�F�N�g<BR>
     * <BR>
     * �� �����J�ݓ`�[�쐬�X�e�[�^�XParams�N���X��DDL��莩����������B<BR>
     */
    private AccOpenVoucherStatusParams accOpenVoucherStatusParams;

    /**
     * (�����J�ݓ`�[�쐬�X�e�[�^�X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w��s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g���A�C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �� �����J�ݓ`�[�쐬�X�e�[�^�XParams�N���X��DDL��莩����������B<BR>
     * @@param l_accOpenVoucherStatusParams - �����J�ݓ`�[�쐬�X�e�[�^�X�s�I�u�W�F�N�g<BR>
     * <BR>
     * �� �����J�ݓ`�[�쐬�X�e�[�^�XParams�N���X��DDL��莩����������B<BR>
     *
     * @@return webbroker3.accountopen.WEB3AccOpenVoucherCreatedStatus
     * @@roseuid 4191977E0091
     */
    public WEB3AccOpenVoucherCreatedStatus(AccOpenVoucherStatusParams l_accOpenVoucherStatusParams)
    {
        final String STR_METHOD_NAME = " WEB3AccOpenVoucherCreatedStatus(AccOpenVoucherStatusParams)";
        log.entering(STR_METHOD_NAME);
        this.accOpenVoucherStatusParams = l_accOpenVoucherStatusParams;
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �igetDataSourceObject�̎����j <BR>
     * <BR>
     * this.�����J�ݓ`�[�쐬�X�e�[�^�X�s��ԋp����B <BR>
     * @@return Object
     * @@roseuid 419196EF0311
     */
    public Object getDataSourceObject()
    {
        final String STR_METHOD_NAME = " getDataSourceObject()";
        log.entering(STR_METHOD_NAME);
         
        log.exiting(STR_METHOD_NAME);
        return this.accOpenVoucherStatusParams;
    }

    /**
     * (get�`�[�쐬�X�e�[�^�X)<BR>
     * this.�����J�ݓ`�[�쐬�X�e�[�^�X�s.�`�[�쐬�X�e�[�^�X��ԋp����B<BR>
     * @@return String
     * @@roseuid 419332890007
     */
    public String getVoucherStatus()
    {
        final String STR_METHOD_NAME = " getVoucherStatus()";
        log.entering(STR_METHOD_NAME);
        
        String l_strVoucherStatus = this.accOpenVoucherStatusParams.getVoucherStatus();
        
        log.exiting(STR_METHOD_NAME);
        return l_strVoucherStatus;
    }

    /**
     * (get�����J�ݓ`�[�쐬�X�e�[�^�X)<BR>
     * �،���ЃR�[�h�C���ʃR�[�h�ɊY����������J�ݓ`�[�쐬�X�e�[�^�X��<BR>
     * ���ׂĎ擾����B<BR>
     * <BR>
     * �ȉ��̏����Ō����J�ݓ`�[�쐬�X�e�[�^�X�e�[�u������������B<BR>
     * �i�Y���s���Ȃ��ꍇ�́Anull��ԋp����j<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�����J�ݓ`�[�쐬�X�e�[�^�X.�،���ЃR�[�h = �،���ЃR�[�h<BR>
     * �@@�����J�ݓ`�[�쐬�X�e�[�^�X.���ʃR�[�h = ���ʃR�[�h<BR>
     * <BR>
     * �������ʂ̊e�����J�ݓ`�[�쐬�X�e�[�^�X�s�I�u�W�F�N�g�ɂ�<BR>
     * �����J�ݓ`�[�쐬�X�e�[�^�X�I�u�W�F�N�g�𐶐����A�z��ɂĕԋp����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@return webbroker3.accountopen.WEB3AccOpenVoucherCreatedStatus[]
     * @@roseuid 41919C3D0052
     */
    public static WEB3AccOpenVoucherCreatedStatus[] getAccOpenVoucherCreatedStatus(String l_strInstitutionCode, String l_strRequestNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAccOpenVoucherCreatedStatus(String, String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException
            String l_strWhere =
                "institution_code = ? and " +        //�����J�ݓ`�[�쐬�X�e�[�^�X.�،���ЃR�[�h = �،���ЃR�[�h 
                "acc_open_request_number = ? ";      //�����J�ݓ`�[�쐬�X�e�[�^�X.���ʃR�[�h = ���ʃR�[�h  

            
            Object l_bindVars[] =
                {l_strInstitutionCode,
                 l_strRequestNumber};
                    
            List l_lisRows = l_queryProcesser.doFindAllQuery(
                AccOpenVoucherStatusRow.TYPE,
                l_strWhere,
                l_bindVars);
                
            int l_intSize = 0;
            if (l_lisRows != null)
            {
                l_intSize = l_lisRows.size();
            }
            
            if (l_intSize == 0)
            {
                log.debug("�Y���s���Ȃ��ꍇ�́Anull��ԋp����");
                log.exiting(STR_METHOD_NAME);
                return null;    
            }
            else
            {
                List l_lis = new ArrayList();
                for (int i = 0; i < l_intSize; i ++)
                {
                    AccOpenVoucherStatusParams l_accOpenVoucherStatusParams = 
                        new AccOpenVoucherStatusParams((AccOpenVoucherStatusRow)l_lisRows.get(i));
                        
                    WEB3AccOpenVoucherCreatedStatus l_accOpenVoucherCreatedStatus = 
                        new WEB3AccOpenVoucherCreatedStatus(l_accOpenVoucherStatusParams);  
                          
                    l_lis.add(l_accOpenVoucherCreatedStatus);
                    log.debug("l_lis.add(l_accOpenVoucherCreatedStatus);");
                }
                WEB3AccOpenVoucherCreatedStatus[] l_accOpenVoucherCreatedStatuses = 
                    new WEB3AccOpenVoucherCreatedStatus[l_intSize];
                l_lis.toArray(l_accOpenVoucherCreatedStatuses);    
                
                log.exiting(STR_METHOD_NAME);
                return l_accOpenVoucherCreatedStatuses;
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccOpenVoucherCreatedStatus.class.getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccOpenVoucherCreatedStatus.class.getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
    }
}
@
