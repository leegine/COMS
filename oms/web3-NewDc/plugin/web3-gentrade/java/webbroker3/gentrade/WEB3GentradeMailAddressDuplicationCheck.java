head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeMailAddressDuplicationCheck.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[���A�h���X�d���`�F�b�N�N���X(WEB3GentradeMailAddressDuplicationCheck.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/19 ������ (���u) �V�K�쐬
                   2006/06/12 �����iSCS�j�@@�d�l�ύX�E���f��197
                   2006/06/16 �������i���u�j�d�l�ύX�E���f��198
                   2006/06/23 �����iSCS�j�d�l�ύX�E���f��199
Revesion History : 2010/02/23 ��іQ (���u) �d�l�ύX ���f��350
*/

package webbroker3.gentrade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountinfoMultiMailaddressFlagDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.gentrade.data.AccountMailAddressRow;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

/**
 * (���[���A�h���X�d���`�F�b�N�N���X)<BR>
 * ���[���A�h���X�̏d���`�F�b�N�@@�\���������郆�[�e�B���e�B�E�N���X<BR>
 *
 * @@author ������
 * @@version 1.0
 */
public class WEB3GentradeMailAddressDuplicationCheck
{

    /**
     * ���O�o��<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        	WEB3GentradeMailAddressDuplicationCheck.class);

    /**
     * (���X�R�[�h�L�[��)<BR>
     * ���X�R�[�h�L�[��<BR>
     */
    private static String KEY_BRANCH_CODE = "branch_code";

    /**
     * (�ڋq�R�[�h�L�[��)<BR>
     * �ڋq�R�[�h�L�[��<BR>
     */
    private static String KEY_ACCOUNT_CODE = "account_code";

    /**
     * (get�d���A�h���X)<BR>
     * �d���A�h���X�`�F�b�N<BR>
     * �ڋq�}�X�^���͌ڋq���[���A�h���X�e�[�u�����ȉ��̏����ŏd���A�h���X�������s���B<BR>
     * ���啶���A�������̍��ق������Ƃ݂Ȃ�<BR>
     * <BR>
     * �P)���X.get�������[���A�h���X�Ή����{()�ɂăv���t�@@�����X�̒l���擾����B<BR>
     * <BR>
     * [����]<BR>
     * �@@���X�R�[�h�@@=�@@����.���X�R�[�h<BR>
     * �@@�،���ЃR�[�h�@@=�@@����.�،���ЃR�[�h<BR>
     * �@@�v���t�@@�����X���F "accountinfo.multi.mailaddress.flag"<BR>
     * �@@�v���t�@@�����X���̘A�ԁF 1<BR>
     * <BR>
     * �Q�j�ڋq���[���A�h���X�e�[�u�����ȉ��̏����ŏd���A�h���X�������s���B<BR>
     * �@@�P�j�Ŗ߂�l==�h�Q�h�̏ꍇ�ȉ��������s���B<BR>
     * <BR>
     * [�d���A�h���X��������]<BR>
     * email_address=�����F���[���A�h���X AND institution_code=�����F�،���ЃR�[�h<BR>
     * <BR>
     * �@@�Q�|�P�j�ȉ��ɂ����ꂩ�����𖞂����Ă���ꍇ<BR>
     * <BR>
     * �@@�@@�@@���X�R�[�h <>�i�����j���X�R�[�h<BR>
     *   �A�@@�����R�[�h <>�i�����j�ڋq�R�[�h<BR>
     * <BR>
     * �@@[�߂�l]<BR>
     * �@@�d���ڋq�̕��X�R�[�h�ƌڋq�R�[�h�̃Z�b�g�iMap�j��Object�z��Ɋi�[���A�ԋp����B<BR>
     * �@@�i�d���ڋq�����݂���ꍇ��Object�z��̒���>0�j<BR>
     * �@@�����X�g���ŏd���̕��X�R�[�h�ƌڋq�R�[�h�폜����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�Q�|�P�j�ȊO�̏ꍇ�A���̃��R�[�h�͖�������B<BR>
     * <BR>
     * �R�j�ڋq�}�X�^�e�[�u�����ȉ��̏����ŏd���A�h���X�������s���B<BR>
     * �@@�P�j�Ŗ߂�l�I=�h�Q�h�̏ꍇ�ȉ��������s���B<BR>
     * <BR>
     * [�d���A�h���X��������]<BR>
     * <BR>
     * �@@[isRenewal()==FALSE�̏ꍇ]<BR>
     * �@@ email_address=�����F���[���A�h���X AND institution_code=�����F�،���ЃR�[�h<BR>
     * <BR>
     * �A[isRenewal()==TRUE�̏ꍇ]<BR>
     * �@@SUBSTR(account_code,1,6)<>�i�����j�ڋq�R�[�h.substring(0,6)<BR>
     * �@@AND email_address=�i�����j���[���A�h���X<BR>
     * �@@AND institution_code=�i�����j�،���ЃR�[�h<BR>
     * <BR>
     * [�߂�l]<BR>
     * �d���ڋq�̕��X�R�[�h�ƌڋq�R�[�h�̃Z�b�g�iMap�j��Object�z��Ɋi�[���A�ԋp����B<BR>
     * �i�d���ڋq�����݂���ꍇ��Object�z��̒���>0�j<BR>
     * <BR>
     * @@param l_strMailAddress - ���[���A�h���X
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@return Object
     * @@roseuid 406937AB0203
     */
    public static Object[] getDuplicateAddress(
        String l_strMailAddress,
        String l_strAccountCode,
        String l_strBranchCode,
        String l_strInstitutionCode)
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDuplicateAddress(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
		//�d�l�ύX20060623SCSInomata-->
		if (l_strMailAddress == null) return null;
		//<--�d�l�ύX20060623SCSInomata

        //�P)���X.get�������[���A�h���X�Ή����{()�ɂăv���t�@@�����X�̒l���擾����B
        WEB3GentradeInstitution l_institution = null;
        WEB3GentradeBranch l_gentradeBranch = null;
        try
        {
            l_institution = new WEB3GentradeInstitution(l_strInstitutionCode);
            l_gentradeBranch = new WEB3GentradeBranch(l_institution, l_strBranchCode);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3GentradeMailAddressDuplicationCheck.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeMailAddressDuplicationCheck.class.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeMailAddressDuplicationCheck.class.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //[����]
       //���X�R�[�h�@@=�@@����.���X�R�[�h
       //�،���ЃR�[�h�@@=�@@����.�،���ЃR�[�h
       //�v���t�@@�����X���F "accountinfo.multi.mailaddress.flag"
       //�v���t�@@�����X���̘A�ԁF 1
        String l_strMultiMailAddressEnforcement = l_gentradeBranch.getMultiMailAddressEnforcement(
            l_strBranchCode, 
            l_strInstitutionCode,
            WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG,
            1);

        StringBuffer l_sbWhere = new StringBuffer();
        Object[] l_objWhere = null;
        List l_lisRecords = new ArrayList();

        //�Q�j�ڋq���[���A�h���X�e�[�u�����ȉ��̏����ŏd���A�h���X�������s���B
        // �P�j�Ŗ߂�l==�h�Q�h�̏ꍇ�ȉ��������s���B
        if (WEB3AccountinfoMultiMailaddressFlagDef.EXECUTE_T1.equals(l_strMultiMailAddressEnforcement))
        {
            //[�d���A�h���X��������] 
            //email_address=�����F���[���A�h���X AND institution_code=�����F�،���ЃR�[�h
            l_sbWhere.append(" lower(email_address) = ? ");
            l_sbWhere.append(" and institution_code = ? ");

            l_objWhere = new Object[]
            {
                l_strMailAddress.trim().toLowerCase(),
                l_strInstitutionCode.trim()
            };

            try
            {
                //�ڋq���[���A�h���X�e�[�u������������
                QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
                l_lisRecords = l_QueryProcessor.doFindAllQuery(
                    AccountMailAddressRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3GentradeMailAddressDuplicationCheck.class.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3GentradeMailAddressDuplicationCheck.class.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            // [�߂�l] 
            //  �d���ڋq�̕��X�R�[�h�ƌڋq�R�[�h�̃Z�b�g�iMap�j��Object�z��Ɋi�[���A 
            //  �ԋp����B
            //  �i�d���ڋq�������̏ꍇ��Object�z��̒���>0�j
            int l_intLength = 0;
            if (l_lisRecords != null && l_lisRecords.size() != 0)
            {
                l_intLength = l_lisRecords.size();
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            List l_lisContains = new ArrayList();
            
            for (int i = 0; i < l_intLength; i++)
            {
                Map l_mapRecords = new HashMap();
                AccountMailAddressRow l_accountMailAddressRow = (AccountMailAddressRow)l_lisRecords.get(i);

                //�Q�|�P�j�ȉ��ɂ����ꂩ�����𖞂����Ă���ꍇ
                //�@@�@@���X�R�[�h <>�i�����j���X�R�[�h
                //�A�@@�����R�[�h <>�i�����j�ڋq�R�[�h
                String l_strBranchCodeDiff = l_accountMailAddressRow.getBranchCode();
                String l_strAccountCodeDiff = l_accountMailAddressRow.getAccountCode();

                if (l_strBranchCodeDiff.equals(l_strBranchCode)
                    && l_strAccountCodeDiff.equals(l_strAccountCode))
                {
                    continue;
                }

                l_mapRecords.put(KEY_BRANCH_CODE, l_strBranchCodeDiff);
                l_mapRecords.put(KEY_ACCOUNT_CODE, l_strAccountCodeDiff.substring(0,6));

                //�@@�����X�g���ŏd���̕��X�R�[�h�ƌڋq�R�[�h�폜����B
                if (!l_lisContains.contains(l_mapRecords))
                {
                    l_lisContains.add(l_mapRecords);
                }
            }

            Object[] l_objRecords = new Object[l_lisContains.size()];
            l_lisContains.toArray(l_objRecords);

            log.exiting(STR_METHOD_NAME);
            return l_objRecords;
        }

        //�R�j�ڋq�}�X�^�e�[�u�����ȉ��̏����ŏd���A�h���X�������s���B
        //�@@�P�j�Ŗ߂�l�I=�h�Q�h�̏ꍇ�ȉ��������s���B
        boolean l_blnIsRenewal = WEB3GentradeMailAddressDuplicationCheck.isRenewal(
        	l_strAccountCode, 
        	l_strBranchCode, 
        	l_strInstitutionCode);

        //�d���A�h���X��������
        //�ڋq�}�X�^���ȉ��̏����ŏd���A�h���X�������s���B
        if (!l_blnIsRenewal)
        {
        	//�@@[isRenewal()==FALSE�̏ꍇ] 
        	// email_address=�����F���[���A�h���X AND institution_code=�����F�،���ЃR�[�h

            //���[���A�h���X
            l_sbWhere.append(" email_address = ? ");

            //�،���ЃR�[�h
            l_sbWhere.append(" and institution_code = ? ");

            l_objWhere = new Object[]{
            	l_strMailAddress.trim(),
            	l_strInstitutionCode.trim()};
        }
        else
        {
        	//�A[isRenewal()==TRUE�̏ꍇ] 
        	// SUBSTR(account_code1,6)<>�����F�ڋq�R�[�h AND email_address=�����F
        	//���[���A�h���X AND institution_code=�����F�،���ЃR�[�h 
            //�ڋq�R�[�h
            l_sbWhere.append(" SUBSTR(account_code, 1, 6) <> ? ");

            //���[���A�h���X
            l_sbWhere.append(" and email_address = ? ");

            //�،���ЃR�[�h
            l_sbWhere.append(" and institution_code = ? ");
            
//			�d�l�ύX***2006.06.12 SCS Inomata--> 
//			l_objWhere = new Object[]{
//				l_strAccountCode.trim(),
//				l_strMailAddress.trim(), 
//				l_strInstitutionCode.trim()};
			l_objWhere = new Object[]{
				//l_strAccountCode.trim(),
				l_strAccountCode.trim().substring(0,6),
				l_strMailAddress.trim(), 
				l_strInstitutionCode.trim()};
//			<--�d�l�ύX***2006.06.12 SCS Inomata
        }

        try
        {
            //�ڋq�}�X�^�e�[�u������������
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
            	MainAccountRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3GentradeMailAddressDuplicationCheck.class.getName()
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
                WEB3GentradeMailAddressDuplicationCheck.class.getName()
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
                WEB3GentradeMailAddressDuplicationCheck.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        
        // [�߂�l] 
		//  �d���ڋq�̕��X�R�[�h�ƌڋq�R�[�h�̃Z�b�g�iMap�j��Object�z��Ɋi�[���A 
		//  �ԋp����B 
		//  �i�d���ڋq�������̏ꍇ��Object�z��̒���>0�j
        int l_intLength = 0;
        if (l_lisRecords != null && l_lisRecords.size() != 0)
        {
        	l_intLength = l_lisRecords.size();
        }
        else
        {
        	return null;
        }

        Object[] l_objRecords = new Object[l_intLength];
        for (int i = 0; i < l_intLength; i++)
        {
            Map l_mapRecords = new HashMap();
        	MainAccountRow l_mainAccountRow = (MainAccountRow) l_lisRecords.get(i);
            l_mapRecords.put(KEY_BRANCH_CODE, l_mainAccountRow.getBranchCode());
//			�d�l�ύX***2006.06.12 SCS Inomata--> 
			//l_mapRecords.put(KEY_ACCOUNT_CODE, l_mainAccountRow.getAccountCode());
            l_mapRecords.put(KEY_ACCOUNT_CODE, l_mainAccountRow.getAccountCode().substring(0,6));
//			<--�d�l�ύX***2006.06.12 SCS Inomata
            l_objRecords[i] = l_mapRecords;
        }

        return l_objRecords;
    }

    /**
     * (isRenewa)<BR>
     * <BR>
     * �ڋq�R�[�h�����X�R�[�h���،���Ђ������Ƃ��āA<BR>
     * �g���A�J�E���g�}�l�[�W���[����ڋq�I�u�W�F�N�g�𐶐����A<BR>
     * account_id���擾����B<BR>
     * <BR>
     * 1) �i�����j�ڋq�R�[�h == null �̏ꍇ�AFALSE��ԋp����B<BR>
     * <BR>
     * 2�j�i�����j�ڋq�R�[�h != null �̏ꍇ�A�ȉ��������s���B<BR>
     * <BR>
     * account_id�����Ɍڋq�}�X�^�Ƀ��[���A�h���X���o�^����Ă��邩�ǂ���<BR>
     * �iemail_address!=null AND email_address!="" �j�ł��邩�ǂ����𔻒肷��B<BR>
     * <BR>
     * [�߂�l] <BR>
     * ���[���A�h���X�o�^�ς݁�TRUE <BR>
     * ���[���A�h���X���o�^��FALSE <BR>
     * <BR>
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@return boolean
     * @@roseuid 406937AB0203
     */
    private static boolean isRenewal(
        String l_strAccountCode,
        String l_strBranchCode,
        String l_strInstitutionCode)
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isRenewal(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //1) �i�����j�ڋq�R�[�h == null �̏ꍇ�AFALSE��ԋp����B
        if (l_strAccountCode == null)
        {
    		log.exiting(STR_METHOD_NAME);
    		return false;
        }

        //2�j�i�����j�ڋq�R�[�h != null �̏ꍇ�A�ȉ��������s���B
        try
        {
        	//�ڋq�R�[�h�����X�R�[�h���،���Ђ������Ƃ���
        	
//			�d�l�ύX***2006.06.12 SCS Inomata--> 	
//        	MainAccountRow l_mainAccountRow = MainAccountDao.findRowByInstitutionCodeBranchCodeAccountCode(
//        		l_strInstitutionCode,
//        		l_strBranchCode,
//        		l_strAccountCode);
			// get�ڋq(String, String, String)
			FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
			WEB3GentradeAccountManager l_accountManager = 
					 (WEB3GentradeAccountManager)l_finApp.getAccountManager();
			WEB3GentradeMainAccount l_gentradMainAccount = l_accountManager.getMainAccount(
				l_strInstitutionCode.trim(), l_strBranchCode.trim(), l_strAccountCode.trim());
			MainAccountRow l_mainAccountRow = MainAccountDao.findRowByAccountId(l_gentradMainAccount.getAccountId());
//			<--�d�l�ύX***2006.06.12 SCS Inomata
        	
        	//�ڋq�}�X�^�Ƀ��[���A�h���X���o�^����Ă��邩�ǂ��� 
        	//�iemail_address!=null AND email_address!="" �j�ł��邩�ǂ����𔻒肷��B 
        	//���[���A�h���X�o�^�ς݁�TRUE 
        	//���[���A�h���X���o�^��FALSE
        	if (l_mainAccountRow != null
        		&& l_mainAccountRow.getEmailAddress() != null
        		&& !"".equals(l_mainAccountRow.getEmailAddress()))
        	{
        		log.exiting(STR_METHOD_NAME);
        		return true;
        	}
        	else
        	{
        		log.exiting(STR_METHOD_NAME);
        		return false;
        	}
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeMailAddressDuplicationCheck.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeMailAddressDuplicationCheck.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeMailAddressDuplicationCheck.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (get�ڋq�R�[�h)<BR>
     * �d���ڋq��񂩂�ڋq�R�[�h���擾����B<BR>
     * <BR>
     * @@param l_objDuplicationAccInfo - �d���A�h���X���
     * 	get�d���A�h���X()�̖߂�l�z��̗v�f�iMap�j�B
     * @@return String
     * @@roseuid 406937AB0203
     */
    public static String getAccountCode(Object l_objDuplicationAccInfo)
    {
        return ((Map) l_objDuplicationAccInfo).get(KEY_ACCOUNT_CODE).toString();
    }

    /**
     * (get���X�R�[�h )<BR>
     * �d���ڋq��񂩂畔�X�R�[�h���擾����B<BR>
     * <BR>
     * @@param l_objDuplicationAccInfo - �d���A�h���X���
     * 	get�d���A�h���X()�̖߂�l�z��̗v�f�iMap�j�B
     * @@return String
     * @@roseuid 406937AB0203
     */
    public static String getBranchCode(Object l_objDuplicationAccInfo)
    {
        return ((Map) l_objDuplicationAccInfo).get(KEY_BRANCH_CODE).toString();
    }
    
	/**
	 * (remove�d�����)<BR>
	 * �i�����j�d���ڋq��񂩂�i�����j�ڋq�R�[�h�A�i�����j���X�R�[�h�ɊY������<BR>
	 * �Z�b�g�̍폜���s���B<BR>
	 * <BR>
	 * �i�����j�d���ڋq�����Ɂi�����j�ڋq�R�[�h�Ɓi�����j���X�R�[�h�̃Z�b�g��<BR>
	 * ���݂���ꍇ�͍폜���s���A�폜�ς݂̏d���ڋq����ԋp����B<BR>
	 * �ڋq�R�[�h�ƕ��X�R�[�h�̃Z�b�g���폜�������ʁA�d����񂪖����ꍇ�́A<BR>
	 * null��ԋp����
	 * <BR>
	 * @@param l_duploArgs - �d���ڋq���
	 * @@param l_accCode - �i�����j�⍇�����ڋq�R�[�h
	 * @@param l_brCode - �i�����j�⍇�������X�R�[�h
	 * @@return Object[] - �d���ڋq���
     * @@roseuid 406937AB0203
	 */
	public static Object[] removeDuplicationInfo(
		Object[] l_duploArgs, 
		String l_accCode, 
		String l_brCode)
	{
		ArrayList l_duploList = new ArrayList();
		String l_strAccountCode = null;
		String l_strBranchCode = null;
		for (int i = 0; i < l_duploArgs.length; i++)
		{
			if (l_duploArgs[i] != null) 
			{
				l_strAccountCode = 
					WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_duploArgs[i]);
				l_strBranchCode = 
					WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_duploArgs[i]);
					
				if (l_strAccountCode.equals(l_accCode) && l_strBranchCode.equals(l_brCode))
				{
				} else
				{
					l_duploList.add(l_duploArgs[i]); 
				}

			}
		}
		if (l_duploList.size() < 1) return null;
		return l_duploList.toArray();
	}
}
@
