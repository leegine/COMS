head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.29.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenDuplicationCheckUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݏd���`�F�b�N���[�e�B���e�B(WEB3AccOpenDuplicationCheckUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/08 ����(���u) �V�K�쐬
*/
package webbroker3.accountopen;

import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;

/**
 * (�����J�ݏd���`�F�b�N���[�e�B���e�B)<BR>
 * �����J�݌����q�I�u�W�F�N�g���� <BR>
 * �����J�ݏd���`�F�b�N�I�u�W�F�N�g��<BR> 
 * �e��`�F�b�N���s���ۂɎQ�Ƃ��s�����[�e�B���e�B�N���X�B<BR>
 *   
 * @@author ����
 * @@version 1.0
 */
public class WEB3AccOpenDuplicationCheckUtility 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AccOpenDuplicationCheckUtility.class);
    
    /**
     * �ڋq�}�X�^�i�S���X���j�e�[�u�����B<BR>
     */
    public static final String MST_NM_MAIN_ACC_ALL = "main_account_all";
    
    /**
     * �������}�X�^�e�[�u�����B<BR>
     */
    public static final String MST_NM_ACC_INF_MST = "account_info_mst";
    
    /**
     * �ڋq�}�X�^�e�[�u�����B<BR>
     */
    public static final String MST_NM_MAIN_ACC = "main_account";
    
    /**
     * �����J�݌����q�e�[�u�����B<BR>
     */
    public static final String MST_NM_EXP_ACC_OPEN = "exp_account_open";
    
	/**
	 * �����J�݌����q�e�[�u�������敪�ڋq�B<BR>
	 */
    public static final String EXP_ACC_OPEN_ACCOUNT_DIV_INDV = "0";
	/**
	 * �����J�݌����q�e�[�u�������敪�@@�l�B<BR>
	 */
    public static final String EXP_ACC_OPEN_ACCOUNT_DIV_COP = "1";
    
    /**
     * (get���X�R�[�h���X�g)<BR>
     * ���X�e�[�u���ɑ��݂��镔�X�R�[�h���X�g��Ԃ��B<BR> 
     * <BR>
     * ���X�e�[�u���ɑ��݂���A�i�����j�،���ЃR�[�h�̕��X�R�[�h���擾���A <BR>
     * ","��؂�ŕ��X�R�[�h�̃��X�g���쐬���ԋp����B�B <BR>
     * <BR>
     * ���X�R�[�h�����݂��Ȃ��ꍇ��null��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getBranchCodeList(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBranchCodeList(String)";
        log.entering(STR_METHOD_NAME);  

        List l_lisRecord = null;
        String l_strBranchCode = "";
        try 
        {
            l_lisRecord = 
                Processors.getDefaultProcessor().doFindAllQuery(
                    BranchRow.TYPE,
                    "institution_code = ?",
                    null,
                    new Object[]{l_strInstitutionCode});
        } 
        catch (DataException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                WEB3AccOpenDuplicationCheckUtility.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //���X�R�[�h�����݂��Ȃ��ꍇ��null��ԋp����B
        if (l_lisRecord == null || l_lisRecord.isEmpty())  
        {
            return null;
        }
        
        //���X�e�[�u���ɑ��݂���A�i�����j�،���ЃR�[�h�̕��X�R�[�h���擾���A 
        // ","��؂�ŕ��X�R�[�h�̃��X�g���쐬���ԋp����B�B 
        for (int i = 0; i < l_lisRecord.size(); i++)
        {
            BranchRow l_branchRow = (BranchRow)l_lisRecord.get(i);  
            BranchParams l_branchRowParams = new BranchParams(l_branchRow);
            
            if (i == 0)
            {
                l_strBranchCode = l_strBranchCode + l_branchRowParams.getBranchCode();
            }
            else
            {
                l_strBranchCode = l_strBranchCode + ", " + l_branchRowParams.getBranchCode();
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_strBranchCode;
    }

}
@
