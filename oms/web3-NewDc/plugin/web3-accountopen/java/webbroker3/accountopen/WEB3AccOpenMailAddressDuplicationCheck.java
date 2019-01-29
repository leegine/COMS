head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.29.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenMailAddressDuplicationCheck.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�݃��[���A�h���X�d���`�F�b�N(WEB3AccOpenMailAddressDuplicationCheck.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/08 ����(���u) �V�K�쐬
                   2006/06/23 �����iSCS�j�d�l�ύX�E���f��070
*/

package webbroker3.accountopen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

/**
 * (�����J�݃��[���A�h���X�d���`�F�b�N)<BR>
 * �����q�e�[�u���ł̃��[���A�h���X�̏d���`�F�b�N�@@�\���������郆�[�e�B���e�B�E�N���X�B<BR>
 *   
 * @@author ����
 * @@version 1.0
 */
public class WEB3AccOpenMailAddressDuplicationCheck extends WEB3AccOpenDuplicationCheck
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AccOpenMailAddressDuplicationCheck.class);
    
    /**
     * (get�d���ڋq���)<BR>
     * �d���A�h���X�`�F�b�N <BR>
     * <BR>
     * ���͂��ꂽ���[���A�h���X�����ɓo�^����Ă��邩���`�F�b�N����B <BR>
     * �o�^�ς݂̏ꍇ�A���̌ڋq�̕��X�R�[�h�ƌڋq�R�[�h�A<BR>
     * �������ꂽ�e�[�u�����̃Z�b�g�iMap�j��Object�z��Ɋi�[���A�ԋp����B<BR>   
     * �i�d���ڋq�����݂���ꍇ��Object�z��̒���>0�j <BR>
     * <BR>
     * <BR>
     * �P�j �ԋp�l�p��ArrayList�𐶐�����B <BR>
     * <BR>
     * �Q�j �����J�݌����q�e�[�u�����ȉ��̏����ŏd���A�h���X�������s���B<BR> 
     * <BR>
     *�@@�@@[�d���A�h���X��������] <BR>
     * <BR>
     *�@@�@@�@@�����q�t�@@�C��.email�A�h���X = �i�����j���[���A�h���X <BR>
     *�@@�@@�@@AND �����q�t�@@�C��.�����R�[�h IS NOT NULL <BR>
     *�@@�@@�@@AND �����q�t�@@�C��.���ʃR�[�h != super.���ʃR�[�h <BR>
     *�@@�@@�@@AND �����q�t�@@�C��.�،���ЃR�[�h = super.�،���ЃR�[�h <BR>
     * <BR>
     * �R�j �Y���s�����݂���ꍇ�A�ȉ�Map�𐶐����AArrayList�ɒǉ�����B <BR>
     *�@@�@@�@@�@@�@@�L�[���Fsuper.KEY_BRANCH_CODE �l�F�����q�t�@@�C��.���X�R�[�h <BR>
     *�@@�@@�@@�@@�@@�L�[���Fsuper.KEY_ACCOUNT_CODE �l�F�����q�t�@@�C��.�����R�[�h <BR>
     *�@@�@@�@@�@@�@@�L�[���Fsuper.TAB_NAME �l�F�����J�ݏd���`�F�b�N���[�e�B���e�B.MST_NM_EXP_ACC_OPEN <BR>
     * <BR>
     * <BR>
     * �S�j ArrayList��Object�z�񉻂��ԋp����B<BR>
     * @@param l_strMailAddress - (���[���A�h���X)<BR>
     * ���[���A�h���X�B<BR>
     * @@return Object[]
     * @@throws WEB3BaseException 
     */
    public Object[] getDuplicationAccountInfo(String l_strMailAddress) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDuplicationAccountInfo(String)";
        log.entering(STR_METHOD_NAME);

		if (WEB3StringTypeUtility.isEmptyOrBlank(l_strMailAddress)) return null;

        //�P�j �ԋp�l�p��ArrayList�𐶐�����B
        StringBuffer l_sbWhere = new StringBuffer();
        Object[] l_objWhere = null;
        List l_lisRecords = new ArrayList();
        
        //�Q�j �����J�݌����q�e�[�u�����ȉ��̏����ŏd���A�h���X�������s���B
        //[�d���A�h���X��������]
        //�����q�t�@@�C��.email�A�h���X = �i�����j���[���A�h���X 
        //AND �����q�t�@@�C��.�����R�[�h IS NOT NULL 
        //AND �����q�t�@@�C��.���ʃR�[�h != super.���ʃR�[�h 
        //AND �����q�t�@@�C��.�،���ЃR�[�h = super.�،���ЃR�[�h 
		//AND �����q�t�@@�C��.�����o�^�� IS NULL
        l_sbWhere.append("email_address = ?");
        l_sbWhere.append(" and account_code is not null");
        l_sbWhere.append(" and acc_open_request_number <> ?");
        l_sbWhere.append(" and institution_code = ?");
		//�d�l�ύX20060623SCSInomata-->
		l_sbWhere.append(" and account_open_date is null");
		//<--�d�l�ύX20060623SCSInomata        
        l_objWhere = 
            new Object[]{
                l_strMailAddress, super.requestNumber, super.institutionCode};
        
        try
        {
            //�����J�݌����q�e�[�u������������
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                ExpAccountOpenRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        
        int l_intLength = 0;
        if (l_lisRecords != null && !l_lisRecords.isEmpty())
        {
            l_intLength = l_lisRecords.size();
        }
        else
        {
            return null;
        }

        //�R�j �Y���s�����݂���ꍇ�A�ȉ�Map�𐶐����AArrayList�ɒǉ�����B 
        //      �L�[���Fsuper.KEY_BRANCH_CODE �l�F�����q�t�@@�C��.���X�R�[�h 
        //      �L�[���Fsuper.KEY_ACCOUNT_CODE �l�F�����q�t�@@�C��.�����R�[�h 
        //      �L�[���Fsuper.TAB_NAME �l�F�����J�ݏd���`�F�b�N���[�e�B���e�B.MST_NM_EXP_ACC_OPEN 
        ArrayList l_arrayList = new ArrayList();
        for (int i = 0; i < l_intLength; i++)
        {
            Map l_mapRecords = new HashMap();
            ExpAccountOpenRow l_expAccountOpenRow = (ExpAccountOpenRow) l_lisRecords.get(i);
            l_mapRecords.put(KEY_BRANCH_CODE, l_expAccountOpenRow.getBranchCode());
            l_mapRecords.put(KEY_ACCOUNT_CODE, l_expAccountOpenRow.getAccountCode());
            l_mapRecords.put(KEY_TAB_NAME, WEB3AccOpenDuplicationCheckUtility.MST_NM_EXP_ACC_OPEN);
            l_arrayList.add(l_mapRecords);
        }
        
        //�S�j ArrayList��Object�z�񉻂��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_arrayList.toArray();
    }
    
    /**
     * (�����J�݃��[���A�h���X�d���`�F�b�N)<BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * super(<BR> 
     * �i�����j�ڋq�R�[�h,<BR> 
     * �i�����j���ʃR�[�h,<BR>
     * �i�����j�،���� <BR>
     * )<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h) <BR>
     * �����R�[�h�B<BR>
     * @@param l_strRequestNumber - (���ʃR�[�h) <BR>  
     * ���ʃR�[�h�B
     * @@param l_strInstitutionCode - (�،���ЃR�[�h) <BR>
     * �،���ЃR�[�h�B
     */
    public WEB3AccOpenMailAddressDuplicationCheck(
        String l_strAccountCode, String l_strRequestNumber, String l_strInstitutionCode)
    {
        super(l_strAccountCode, l_strRequestNumber, l_strInstitutionCode);
    }

}
@
