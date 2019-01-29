head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.28.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenTelNumberDuplicationCheck.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݓd�b�ԍ��d���`�F�b�N (WEB3AccOpenTelNumberDuplicationCheck.java)
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
import webbroker3.gentrade.data.MainAccountAllRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

/**
 * (�����J�ݓd�b�ԍ��d���`�F�b�N)<BR>
 * �ڋq�}�X�^�A�ڋq�}�X�^�i�S���X�j�A�����J�݌����q�e�[�u���ł́A<BR>
 * �d�b�ԍ��y�ьg�єԍ��̏d���`�F�b�N�@@�\���������郆�[�e�B���e�B�E�N���X�B<BR>
 *   
 * @@author ����
 * @@version 1.0
 */
public class WEB3AccOpenTelNumberDuplicationCheck extends WEB3AccOpenDuplicationCheck
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AccOpenTelNumberDuplicationCheck.class);
            
   /**
    * �����q�d�b�ԍ�������B
    */
    private String[] expOpenAccExqRow;
    
   /**
    * (get�d���ڋq���)<BR>
    * �w�肳�ꂽ�d�b�ԍ����ȉ��̃e�[�u���ɓo�^����Ă��邩�ǂ����`�F�b�N����B<BR> 
    * �o�^�ς݂̏ꍇ�A���̌ڋq�̕��X�R�[�h�ƌڋq�R�[�h�A<BR>
    * �������ꂽ�e�[�u�����̃Z�b�g�iMap�j�� Object�z��Ɋi�[���A�ԋp����B<BR>   
    * �i�d���ڋq�����݂���ꍇ��Object�z��̒���>0�j <BR>
    * <BR>
    * �P�j �ԋp�l�p��ArrayList�𐶐�����B <BR>
    * <BR>
    * �Q�j �ڋq�}�X�^�ɓd�b�ԍ������݂��邩�`�F�b�N���s���B<BR> 
    * <BR>
    *�@@�@@[����] <BR>
    *�@@�@@�@@�ڋq�}�X�^.�،���ЃR�[�h = super.�،���ЃR�[�h <BR>
    *�@@�@@�@@AND (�ڋq�}�X�^.�d�b�ԍ� = <BR>
    *�@@�@@�@@�i�����j�d�b�ԍ� OR �ڋq�}�X�^.�A����d�b�ԍ��i�g�сj = �i�����j�d�b�ԍ�)<BR> 
    * <BR>
    *   �Q�|�P�j �Y���s�����݂���ꍇ�A�ȉ�Map�𐶐����AArrayList�ɒǉ�����B<BR> 
    * �@@�@@�@@�@@�L�[���Fsuper.KEY_BRANCH_CODE �l�F�ڋq�}�X�^.���X�R�[�h <BR>
    * �@@�@@�@@�@@�L�[���Fsuper.KEY_ACCOUNT_CODE �l�F�ڋq�}�X�^.�����R�[�h <BR>
    * �@@�@@�@@�@@�L�[���Fsuper.TAB_NAME �l�F�����J�ݏd���`�F�b�N���[�e�B���e�B.MST_NM_MAIN_ACC<BR> 
    * <BR>
    * <BR>
    * �R�j �ڋq�}�X�^�i�S���X�j�ɓd�b�ԍ������݂��邩�`�F�b�N���s���B <BR>
    * <BR>
    * �@@�R�|�P�j �ڋq�}�X�^�Ƃ̏d���������ׁA<BR>
    *�@@�@@�@@�@@�@@���X�e�[�u���ɑ��݂��镔�X�R�[�h�̃��X�g���擾����B <BR>
    *�@@�@@�@@�@@�@@�����J�ݏd���`�F�b�N���[�e�B���e�B.get���X�R�[�h���X�g(super.�،���ЃR�[�h)<BR>
    *�@@�@@�@@�@@�@@�����s�����X�g���擾����B<BR> 
    * <BR>
    * �@@�R�|�Q�j �ڋq�}�X�^�i�S���X�j��茟�����s���B<BR> 
    * <BR>
    *�@@�@@�R�|�Q�|�P�j �R�|�P�j�̖߂�l��null�łȂ��ꍇ <BR>
    *�@@�@@�@@�@@[����] <BR>
    *�@@�@@�@@�@@�@@�@@�ڋq�}�X�^�i�S���X�j.�،���ЃR�[�h = super.�،���ЃR�[�h <BR>
    *�@@�@@�@@�@@�@@�@@AND �ڋq�}�X�^�i�S���X�j.���X�R�[�h NOT IN ( �R�|�P�j�̖߂�l ) <BR>
    *�@@�@@�@@�@@�@@�@@AND  (�ڋq�}�X�^�i�S���X�j.�d�b�ԍ� = �i�����j�d�b�ԍ� <BR>
    *�@@�@@�@@�@@�@@�@@OR �ڋq�}�X�^�i�S���X�j.�A����E�d�b�ԍ� = �i�����j�d�b�ԍ�) <BR>
    * <BR>
    * <BR>
    *�@@�@@�R�|�Q�|�Q�j  �R�|�P�j�̖߂�l��null�̏ꍇ <BR>
    *�@@�@@�@@�@@[����] <BR>
    *�@@�@@�@@�@@�@@�@@(�ڋq�}�X�^�i�S���X�j.�d�b�ԍ� = �i�����j�d�b�ԍ� <BR>
    *�@@�@@�@@�@@�@@�@@OR �ڋq�}�X�^�i�S���X�j.�A����E�d�b�ԍ� = �i�����j�d�b�ԍ�)<BR> 
    *�@@�@@�@@�@@�@@�@@AND �ڋq�}�X�^�i�S���X�j.�،���ЃR�[�h = super.�،���ЃR�[�h <BR>
    * <BR>
    * <BR>
    * �@@�R�|�R�j �Y���s�����݂���ꍇ�A�ȉ�Map�𐶐����AArrayList�ɒǉ�����B <BR>
    * �@@�@@�@@�@@�L�[���Fsuper.KEY_BRANCH_CODE �l�F�ڋq�}�X�^�i�S���X�j.���X�R�[�h <BR>
    * �@@�@@�@@�@@�L�[���Fsuper.KEY_ACCOUNT_CODE �l�F�ڋq�}�X�^�i�S���X�j.�ڋq�R�[�h <BR>
    * �@@�@@�@@�@@�L�[���Fsuper.TAB_NAME �l�F�����J�ݏd���`�F�b�N���[�e�B���e�B.MST_NM_MAIN_ACC_ALL <BR>
    * <BR>
    * <BR>
    * �S�j �����J�݌����q�e�[�u���ɓd�b�ԍ������݂��邩�`�F�b�N���s���B <BR>
    * <BR>
    *�@@�@@�@@[����] <BR>
    * <BR>
    *�@@�@@�@@�����q�t�@@�C��.�،���ЃR�[�h = super.�،���ЃR�[�h <BR>
    *�@@�@@�@@AND �����q�t�@@�C��.���ʃR�[�h != super.���ʃR�[�h <BR>
    *�@@�@@�@@AND �����q�t�@@�C��.�����R�[�h IS NOT NULL <BR>
    *�@@�@@�@@AND(�����q�t�@@�C��.�d�b�ԍ� = �i�����j�d�b�ԍ� <BR>
    *�@@�@@�@@OR �����q�t�@@�C��.�A����d�b�ԍ��i�g�сj = �i�����j�d�b�ԍ�)) <BR>
    * <BR>
    * <BR>
    *�@@�@@�S�|�P�j �Y���s�����݂���ꍇ�A�ȉ�Map�𐶐����AArrayList�ɒǉ�����B <BR>
    *�@@�@@�@@�@@�@@�@@�L�[���Fsuper.KEY_BRANCH_CODE �l�F�����q�t�@@�C��.���X�R�[�h <BR>
    *�@@�@@�@@�@@�@@�@@�L�[���Fsuper.KEY_ACCOUNT_CODE �l�F�����q�t�@@�C��.�����R�[�h <BR>
    *�@@�@@�@@�@@�@@�@@�L�[���Fsuper.TAB_NAME �l�F�����J�ݏd���`�F�b�N���[�e�B���e�B.MST_NM_EXP_ACC_OPEN <BR>
    * <BR>
    * �T�j ArrayList��Object�z�񉻂��ԋp����B<BR>
    * @@param l_strTelephone - (�d�b�ԍ�)<BR>
    * �d�b�ԍ��B<BR>
    * @@return Object[]
    * @@throws WEB3BaseException 
    */
    public Object[] getDuplicationAccountInfo(String l_strTelephone) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDuplicationAccountInfo(String)";
        log.entering(STR_METHOD_NAME);

		if (WEB3StringTypeUtility.isEmptyOrBlank(l_strTelephone)) return null;

        //�P�j �ԋp�l�p��ArrayList�𐶐�����B
        StringBuffer l_sbWhereOne = new StringBuffer();
        Object[] l_objWhereOne = null;
        ArrayList l_lisReturnList = new ArrayList();
        List l_lisRecordsOne = new ArrayList();
        List l_lisRecordsTwo = new ArrayList();
        List l_lisRecordsThree = new ArrayList();
        
        
        //�Q�j �ڋq�}�X�^�ɓd�b�ԍ������݂��邩�`�F�b�N���s���B 
        //[����] 
        //  �ڋq�}�X�^.�،���ЃR�[�h = super.�،���ЃR�[�h 
        //  AND (�ڋq�}�X�^.�d�b�ԍ� like�i�����j�d�b�ԍ� OR �ڋq�}�X�^.�A����d�b�ԍ��i�g�сjlike�i�����j�d�b�ԍ�) 

        l_sbWhereOne.append("institution_code = ?");
		l_sbWhereOne.append(" and (telephone like ?");
		l_sbWhereOne.append(" or mobile like ?)");
		l_objWhereOne = new Object[]{super.institutionCode, l_strTelephone + "%", l_strTelephone + "%"};

        try
        {
            //�ڋq�}�X�^�e�[�u������������
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecordsOne = l_queryProcessor.doFindAllQuery(
                MainAccountRow.TYPE,
                l_sbWhereOne.toString(),
                l_objWhereOne);
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
        
        if (l_lisRecordsOne != null && !l_lisRecordsOne.isEmpty())
        {
            for (int i = 0; i < l_lisRecordsOne.size(); i++)
            {
                //�Q�|�P�j �Y���s�����݂���ꍇ�A�d�b�ԍ��y�ьg�ѓd�b�ԍ�����
                //�󔒂��������l���i�����j�d�b�ԍ��Ɠ������ꍇ�A
                //�ȉ�Map�𐶐����AArrayList�ɒǉ�����B 
                //       �L�[���Fsuper.KEY_BRANCH_CODE �l�F�ڋq�}�X�^.���X�R�[�h 
                //       �L�[���Fsuper.KEY_ACCOUNT_CODE �l�F�ڋq�}�X�^.�����R�[�h 
                //       �L�[���Fsuper.TAB_NAME �l�F�����J�ݏd���`�F�b�N���[�e�B���e�B.MST_NM_MAIN_ACC
                MainAccountRow l_mainAccountRow = (MainAccountRow) l_lisRecordsOne.get(i);

				//trim(�d�b�ԍ�)����trim(�g�ѓd�b�ԍ�)���i�����j�d�b�ԍ��Ɠ������ꍇ�A
				//�d�������쐬����B
				int l_intInfo = 0;
				String l_strtmpTel = l_mainAccountRow.getTelephone();
				if (l_strtmpTel != null) 
				{
					if (l_strTelephone.equals(l_strtmpTel.trim())) l_intInfo++;				
				}

				String l_strtmpMobile = l_mainAccountRow.getMobile();
				if (l_strtmpMobile != null) 
				{
					if (l_strTelephone.equals(l_strtmpMobile.trim())) l_intInfo++;
				}

				if (l_intInfo > 0)
				{
					Map l_mapRecords = new HashMap();
					l_mapRecords.put(KEY_BRANCH_CODE, l_mainAccountRow.getBranchCode());
					l_mapRecords.put(KEY_ACCOUNT_CODE, l_mainAccountRow.getAccountCode());
					l_mapRecords.put(KEY_TAB_NAME, WEB3AccOpenDuplicationCheckUtility.MST_NM_MAIN_ACC);
					l_lisReturnList.add(l_mapRecords);
				}	
            }
        } 

        //�R�j �ڋq�}�X�^�i�S���X�j�ɓd�b�ԍ������݂��邩�`�F�b�N���s���B
        StringBuffer l_sbWhereTwo = new StringBuffer();
        Object[] l_objWhereTwo = null;
        
        //�R�|�P�j �ڋq�}�X�^�Ƃ̏d���������ׁA���X�e�[�u���ɑ��݂��镔�X�R�[�h�̃��X�g���擾����B 
        //   �����J�ݏd���`�F�b�N���[�e�B���e�B.get���X�R�[�h���X�g(super.�،���ЃR�[�h)�����s�����X�g���擾����B 
        String l_strBranchCode = 
            WEB3AccOpenDuplicationCheckUtility.getBranchCodeList(super.institutionCode);
        
        //�R�|�Q�j �ڋq�}�X�^�i�S���X�j��茟�����s���B 
        if (l_strBranchCode != null)
        {
            //  �R�|�Q�|�P�j �R�|�P�j�̖߂�l��null�łȂ��ꍇ 
            //   [����] 
            //       �ڋq�}�X�^�i�S���X�j.�،���ЃR�[�h = super.�،���ЃR�[�h 
            //       AND �ڋq�}�X�^�i�S���X�j.���X�R�[�h NOT IN ( �R�|�P�j�̖߂�l ) 
            //       AND  (�ڋq�}�X�^�i�S���X�j.�d�b�ԍ� = �i�����j�d�b�ԍ� 
            //       OR �ڋq�}�X�^�i�S���X�j.�A����E�d�b�ԍ� = �i�����j�d�b�ԍ�)
            l_sbWhereTwo.append("comp_code = ?");
            String[] l_strBranchCodes = l_strBranchCode.split(",");
            l_sbWhereTwo.append(" and br_code not in (");
            
            ArrayList l_lisArray = new ArrayList();
            
            l_lisArray.add(super.institutionCode);
            for (int i = 0; i < l_strBranchCodes.length; i++)
            {
                
                if (i == l_strBranchCodes.length - 1)
                {
                    l_sbWhereTwo.append("?)");
                }
                else
                {
                    l_sbWhereTwo.append("?, ");
                }
                l_lisArray.add(l_strBranchCodes[i]);
            }
            
			l_lisArray.add(l_strTelephone + "%");
			l_lisArray.add(l_strTelephone + "%");
			l_sbWhereTwo.append(" and (telephone like ?");
			l_sbWhereTwo.append(" or contact_address_telephone like ?)");
            l_objWhereTwo = new Object[l_lisArray.size()];
            l_lisArray.toArray(l_objWhereTwo);

        }
        else
        {
            //  �R�|�Q�|�Q�j  �R�|�P�j�̖߂�l��null�̏ꍇ 
            //   [����] 
            //    (�ڋq�}�X�^�i�S���X�j.�d�b�ԍ� = �i�����j�d�b�ԍ� OR �ڋq�}�X�^�i�S���X�j.�A����E�d�b�ԍ� = �i�����j�d�b�ԍ�) 
            //    AND �ڋq�}�X�^�i�S���X�j.�،���ЃR�[�h = super.�،���ЃR�[�h 
			l_sbWhereTwo.append("(telephone like ?");
			l_sbWhereTwo.append(" or contact_address_telephone like ?)");
			l_sbWhereTwo.append(" and comp_code = ?");
			l_objWhereTwo = 
				new Object[]{l_strTelephone + "%", l_strTelephone + "%", super.institutionCode};
        }
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecordsTwo = l_queryProcessor.doFindAllQuery(
                MainAccountAllRow.TYPE,
                l_sbWhereTwo.toString(),
                l_objWhereTwo);
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
        
        if (l_lisRecordsTwo != null && !l_lisRecordsTwo.isEmpty())
        {
            for (int i = 0; i < l_lisRecordsTwo.size(); i++)
            {
                //�R�|�R�j �Y���s�����݂���ꍇ�A�d�b�ԍ��y�ьg�ѓd�b�ԍ�����
				//�󔒂��������l���i�����j�d�b�ԍ��Ɠ������ꍇ�A
				//�ȉ�Map�𐶐����AArrayList�ɒǉ�����B 
                //         �L�[���Fsuper.KEY_BRANCH_CODE �l�F�ڋq�}�X�^�i�S���X�j.���X�R�[�h 
                //         �L�[���Fsuper.KEY_ACCOUNT_CODE �l�F�ڋq�}�X�^�i�S���X�j.�ڋq�R�[�h 
                //         �L�[���Fsuper.TAB_NAME �l�F�����J�ݏd���`�F�b�N���[�e�B���e�B.MST_NM_MAIN_ACC_ALL 
                MainAccountAllRow l_mainAccountAllRow = (MainAccountAllRow) l_lisRecordsTwo.get(i);
				//trim(�d�b�ԍ�)����trim(�g�ѓd�b�ԍ�)���i�����j�d�b�ԍ��Ɠ������ꍇ�A
				//�d�������쐬����B
				int l_intInfo = 0;
				String l_strtmpTel = l_mainAccountAllRow.getTelephone();
				if (l_strtmpTel != null) 
				{
					if (l_strTelephone.equals(l_strtmpTel.trim())) l_intInfo++;				
				}

				String l_strtmpMobile = l_mainAccountAllRow.getContactAddressTelephone();
				if (l_strtmpMobile != null) 
				{
					if (l_strTelephone.equals(l_strtmpMobile.trim())) l_intInfo++;
				}

				if (l_intInfo > 0)
				{
					Map l_mapRecords = new HashMap();
	                l_mapRecords.put(KEY_BRANCH_CODE, l_mainAccountAllRow.getBrCode());
	                l_mapRecords.put(KEY_ACCOUNT_CODE, l_mainAccountAllRow.getCustCode());
	                l_mapRecords.put(KEY_TAB_NAME, WEB3AccOpenDuplicationCheckUtility.MST_NM_MAIN_ACC_ALL);
	                l_lisReturnList.add(l_mapRecords);
				}
            }
            
        } 

        //�S�j �����J�݌����q�e�[�u���ɓd�b�ԍ������݂��邩�`�F�b�N���s���B
        //[����] 
        //�����q�t�@@�C��.�،���ЃR�[�h = super.�،���ЃR�[�h 
        //AND �����q�t�@@�C��.���ʃR�[�h != super.���ʃR�[�h 
        //AND �����q�t�@@�C��.�����R�[�h IS NOT NULL 
        //AND �����q�t�@@�C��.�����o�^�� IS NULL
        //AND(�����q�t�@@�C��.�d�b�ԍ� = �i�����j�d�b�ԍ� OR �����q�t�@@�C��.�A����d�b�ԍ��i�g�сj = �i�����j�d�b�ԍ�)) 
        StringBuffer l_sbWhereThree = new StringBuffer();
		ArrayList l_objWhereThree = new ArrayList();
        
        l_sbWhereThree.append("institution_code = ?");
		l_objWhereThree.add(super.institutionCode);
        l_sbWhereThree.append(" and acc_open_request_number <> ?");
		l_objWhereThree.add(super.requestNumber);
        l_sbWhereThree.append(" and account_code is not null");
		l_sbWhereThree.append(" and account_open_date is null");
        l_sbWhereThree.append(" and (telephone = ?");
		l_objWhereThree.add(l_strTelephone);
        
        for(int i = 0; i < expOpenAccExqRow.length; i++){
			l_sbWhereThree.append(" or "+ expOpenAccExqRow[i] +" = ?");
			l_objWhereThree.add(l_strTelephone);
        }
		l_sbWhereThree.append(")");
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecordsThree = l_queryProcessor.doFindAllQuery(
                ExpAccountOpenRow.TYPE,
                l_sbWhereThree.toString(),
                l_objWhereThree.toArray());
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
        
        if (l_lisRecordsThree != null && !l_lisRecordsThree.isEmpty())
        {
            for (int i = 0; i < l_lisRecordsThree.size(); i++)
            {
                Map l_mapRecords = new HashMap();
                //�S�|�P�j �Y���s�����݂���ꍇ�A�ȉ�Map�𐶐����AArrayList�ɒǉ�����B 
                //      �L�[���Fsuper.KEY_BRANCH_CODE �l�F�����q�t�@@�C��.���X�R�[�h 
                //      �L�[���Fsuper.KEY_ACCOUNT_CODE �l�F�����q�t�@@�C��.�����R�[�h 
                //      �L�[���Fsuper.TAB_NAME �l�F�����J�ݏd���`�F�b�N���[�e�B���e�B.MST_NM_EXP_ACC_OPEN 
                ExpAccountOpenRow l_expAccountOpenRow = (ExpAccountOpenRow) l_lisRecordsThree.get(i);
                l_mapRecords.put(KEY_BRANCH_CODE, l_expAccountOpenRow.getBranchCode());
                l_mapRecords.put(KEY_ACCOUNT_CODE, l_expAccountOpenRow.getAccountCode());
                l_mapRecords.put(KEY_TAB_NAME, WEB3AccOpenDuplicationCheckUtility.MST_NM_EXP_ACC_OPEN);
                l_lisReturnList.add(l_mapRecords);
            }

        } 
        
        log.exiting(STR_METHOD_NAME);
        return l_lisReturnList.toArray();
    }
    
    
    /**
	 * @@param exquteRow - �d�b�ԍ�������
	 * �d�b�ԍ�������̃Z�b�g���s���B
	 */
	public void setExpOpenAccExqRow(String[] l_strExquteRow)
	{
		expOpenAccExqRow = l_strExquteRow;
    }
    
    
    /**
     * (�����J�ݓd�b�ԍ��d���`�F�b�N)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     *super( <BR>
     *�i�����j�ڋq�R�[�h, <BR>
     *�i�����j���ʃR�[�h, <BR>
     *�i�����j�،���� <BR>
     *) <BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h) <BR>
     * �����R�[�h�B<BR>
     * @@param l_strRequestNumber - (���ʃR�[�h) <BR>  
     * ���ʃR�[�h�B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h) <BR>
     * �،���ЃR�[�h�B<BR>
     */
    public WEB3AccOpenTelNumberDuplicationCheck(
        String l_strAccountCode, String l_strRequestNumber, String l_strInstitutionCode)
    {
        super(l_strAccountCode, l_strRequestNumber, l_strInstitutionCode);
    }
}
@
