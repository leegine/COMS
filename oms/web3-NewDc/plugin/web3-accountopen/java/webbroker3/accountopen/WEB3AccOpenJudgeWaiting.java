head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.29.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenJudgeWaiting.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݐR���҂�(WEB3AccOpenJudgeWaiting.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/08 �юu�� (���u) �V�K�쐬
*/

package webbroker3.accountopen;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.data.AccOpenWaitingParams;
import webbroker3.accountopen.data.AccOpenWaitingRow;
import webbroker3.accountopen.define.WEB3AccOpenCheckStateDivDef;
import webbroker3.accountopen.define.WEB3AccOpenFromWebSortKeyDef;
import webbroker3.accountopen.define.Web3AccOpenSortKeyDef;
import webbroker3.accountopen.message.WEB3AccOpenSortKey;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3BranchCodeDef;
import webbroker3.common.define.WEB3CheckDivDef;
import webbroker3.common.define.WEB3ReviewCodeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����J�ݐR���҂�)<BR>
 * �����J�ݐR���҂�<BR>
 *                                                              
 * @@author �юu��
 * @@version 1.0
 */

public class WEB3AccOpenJudgeWaiting 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
     private static WEB3LogUtility log = 
         WEB3LogUtility.getInstance(WEB3AccOpenJudgeWaiting.class);

    /**
     * (�����J�ݐR���҂��f�[�^)<BR>
     * �����J�ݐR���҂�Params�̈ꗗ<BR>
     */
    private List accOpenWaitingParamsList;
    
    /**
     * (�����J�ݐR���҂�)<BR>
     * �R���X�g���N�^<BR> 
     * <BR>
     * �P�j�����J�ݐR���҂��f�[�^�̃I�u�W�F�N�g�𐶐�����<BR> 
     */
    public WEB3AccOpenJudgeWaiting()
    {
        final String STR_METHOD_NAME = 
            " WEB3AccOpenJudgeWaiting()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�����J�ݐR���҂��f�[�^�̃I�u�W�F�N�g�𐶐�����
        this.accOpenWaitingParamsList = new ArrayList();

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (add�����J�ݐR���҂��s)<BR>
     * �����J�ݐR���҂��s��ǉ�����B<BR> 
     * <BR>
     * �P�j�����J�ݐR���҂��s == null �̏ꍇ <BR>
     * �@@false ��ԋp���� <BR>
     * <BR>
     * �Q�j�����J�ݐR���҂��f�[�^�ɁA�����J�ݐR���҂��s��ǉ����� <BR>
     * <BR>
     * �R�jtrue ��ԋp���� <BR>
     * @@param l_accOpenWaitingParams - (�����J�ݐR���҂��s) <BR>
     * �����J�ݐR���҂��s <BR>
     * <BR>
     * �� �����J�ݐR���҂�Params�N���X��DDL��莩����������B<BR>
     * @@return boolean
     */
    public boolean addAccOpenWaitingParams(AccOpenWaitingParams l_accOpenWaitingParams)
    {
        final String STR_METHOD_NAME = 
            " addAccOpenWaitingParams(AccOpenWaitingParams)";
        log.entering(STR_METHOD_NAME);

        //�P�j�����J�ݐR���҂��s == null �̏ꍇ 
		//false ��ԋp���� 
        if (l_accOpenWaitingParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�Q�j�����J�ݐR���҂��f�[�^�ɁA�����J�ݐR���҂��s��ǉ�����
        if (this.accOpenWaitingParamsList != null)
        {
            this.accOpenWaitingParamsList.add(l_accOpenWaitingParams);  
        }
        
		//�R�jtrue ��ԋp���� 
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     *(get�����J�ݐR���҂��s��)<BR>
     *�����J�ݐR���҂��f�[�^�̃��R�[�h������Ԃ��B<BR>
     * @@return int
     */
    public int getAccOpenWaitingParamsNumber()
    {
        final String STR_METHOD_NAME = 
            " getAccOpenWaitingParamsNumbers()";
        log.entering(STR_METHOD_NAME);
        
        //�����J�ݐR���҂��f�[�^�̃��R�[�h������Ԃ��B
        int l_intAccOpenWaitingParamsSize = 0;
        if (this.accOpenWaitingParamsList != null)
        {
            l_intAccOpenWaitingParamsSize = 
                this.accOpenWaitingParamsList.size();
        }
        
        log.debug("�����J�ݐR���҂��f�[�^�̃��R�[�h������Ԃ��B" 
            + l_intAccOpenWaitingParamsSize);
        
        log.exiting(STR_METHOD_NAME);
        return l_intAccOpenWaitingParamsSize;
    }
    
    /**
     * (get�����J�ݐR���҂��s)<BR>
     * �w�肵���擾�s�ԍ��̌����J�ݐR���҂��s��ԋp����B<BR>
     * <BR>
     * �P�jthis.get�����J�ݐR���҂��s������S�������擾����B<BR> 
     * <BR>
     * �Q�j�擾�s�ԍ��̃`�F�b�N <BR>
     * �@@�擾�s�ԍ� < 0 �܂��� �擾�s�ԍ� >= �S�����@@�̏ꍇ <BR>
     * �@@null ��ԋp���� <BR>
     * <BR>
     * �R�j�����J�ݐR���҂��f�[�^.get(�擾�s�ԍ�) ��ԋp���� <BR>
     * <BR>
     * �� �����J�ݐR���҂�Params�N���X��DDL��莩����������B<BR>
     * @@param l_intGetLineNo - (�擾�s�ԍ�)<BR>
     * �擾�����������J�ݐR���҂��f�[�^�̍s��<BR>
     * @@return AccOpenWaitingParams
     */
    public AccOpenWaitingParams getAccOpenWaitingParams(int l_intGetLineNo)
    {
        final String STR_METHOD_NAME = 
            " getAccOpenWaitingParams(int l_intGetLineNo)";
        log.entering(STR_METHOD_NAME);

        //�P�jthis.get�����J�ݐR���҂��s������S�������擾����B 
        int l_intAccOpenWaitingParamsSize = 
            this.getAccOpenWaitingParamsNumber();

        //�Q�j�擾�s�ԍ��̃`�F�b�N 
		//�擾�s�ԍ� < 0 �܂��� �擾�s�ԍ� >= �S�����@@�̏ꍇ 
		//null ��ԋp����
        if (l_intGetLineNo < 0 || l_intGetLineNo >= l_intAccOpenWaitingParamsSize)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�R�j�����J�ݐR���҂��f�[�^.get(�擾�s�ԍ�) ��ԋp���� 
        AccOpenWaitingParams l_accOpenWaitingParams = null;
        if (this.accOpenWaitingParamsList != null && 
            !this.accOpenWaitingParamsList.isEmpty())
        {
            AccOpenWaitingRow l_accOpenWaitingRow = 
                (AccOpenWaitingRow) this.accOpenWaitingParamsList.get(
                    l_intGetLineNo);
            
            l_accOpenWaitingParams = 
                new AccOpenWaitingParams(l_accOpenWaitingRow);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_accOpenWaitingParams;
    }
    
    /**
     * (set�����J�ݐR���҂��s )<BR>
     * �����J�ݐR���҂��s���X�V����B<BR> 
     * <BR>
     * �P�j�����̃`�F�b�N<BR> 
     * �@@�P�|�P�j�����J�ݐR���҂��s == null �̏ꍇ<BR> 
     * �@@�@@false ��ԋp���� <BR>
     * <BR>
     * �@@�P�|�Q�j�擾�s�ԍ��̃`�F�b�N<BR> 
     * �@@�@@this.get�����J�ݐR���҂��s������S�������擾����B<BR> 
     * �@@�@@�X�V�s�ԍ� < 0 �܂��� �X�V�s�ԍ� >= �S�����@@�̏ꍇ <BR>
     * �@@�@@false ��ԋp���� <BR>
     * <BR>
     * �Q�j�����J�ݐR���҂��f�[�^�̍X�V�s�ԍ��̃I�u�W�F�N�g��<BR> 
     * �@@�����J�ݐR���҂��s�ŏ㏑������ <BR>
     * <BR>
     * �R�jtrue ��ԋp����<BR> 
     * @@param l_accOpenWaitingParams - (�����J�ݐR���҂��s)<BR>
     * �����J�ݐR���҂��s <BR>
     * <BR>
     * �� �����J�ݐR���҂�Params�N���X��DDL��莩����������B<BR>
     * @@param l_intUpdateLineNo - (�X�V�s�ԍ�)<BR>
     * �X�V�����������J�ݐR���҂��f�[�^�̍s��<BR>
     * @@return boolean
     */
    public boolean setAccOpenWaitingParams(
        AccOpenWaitingParams l_accOpenWaitingParams,
        int l_intUpdateLineNo)
    {
        final String STR_METHOD_NAME = 
            " setAccOpenWaitingParams(AccOpenWaitingParams, int)";
        log.entering(STR_METHOD_NAME);

		//�P�j�����̃`�F�b�N 
		//�P�|�P�j�����J�ݐR���҂��s == null �̏ꍇ 
		//false ��ԋp����
        if (l_accOpenWaitingParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
		//�P�|�Q�j�擾�s�ԍ��̃`�F�b�N 
		//this.get�����J�ݐR���҂��s������S�������擾����B 
		//�X�V�s�ԍ� < 0 �܂��� �X�V�s�ԍ� >= �S�����@@�̏ꍇ 
		//false ��ԋp���� 
        int l_intAccOpenWaitingParamsSize = 
            this.getAccOpenWaitingParamsNumber();
        if (l_intUpdateLineNo < 0 || l_intUpdateLineNo >= l_intAccOpenWaitingParamsSize)
        {
            log.exiting(STR_METHOD_NAME);
            return false;  
        }
        
		//�Q�j�����J�ݐR���҂��f�[�^�̍X�V�s�ԍ��̃I�u�W�F�N�g�� 
		//�����J�ݐR���҂��s�ŏ㏑������
        
        if (this.accOpenWaitingParamsList != null)
        {
            this.accOpenWaitingParamsList.set(l_intUpdateLineNo, l_accOpenWaitingParams);
        }
        
		//�R�jtrue ��ԋp���� 
        log.exiting(STR_METHOD_NAME);
        return true;    
    }
    
    /**
     * (select�R���Ώێ��ʃR�[�h�ꗗ)<BR>
     * �w�肵�Ă�������𐶐����A���ʃR�[�h�̈ꗗ���擾����B<BR> 
     * <BR>
     * �P�j�@@��������������C���X�^���X�i�FString�j�𐶐�<BR>  
     * �@@this.create��������������-�R���Ώێ��ʃR�[�h�ꗗ() ����擾����<BR> 
     * <BR>
     * �@@[����]<BR> 
     * �@@�@@���X�R�[�h�A�ڋq�R�[�h�A���ʃR�[�h�A<BR> 
     * �@@�@@�������i���j�A�������i���j�A�R����ʁA�R���S���҃R�[�h�A�R����<BR> 
     * <BR>
     * �Q�j�@@���������f�[�^�R���e�i�C���X�^���X�i�FString[]�j�𐶐�<BR>  
     * �@@this.create���������f�[�^�R���e�i-�R���Ώێ��ʃR�[�h�ꗗ() ����擾����<BR> 
     * <BR>
     * �@@[����]<BR> 
     * �@@�@@�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�A���ʃR�[�h�A<BR> 
     * �@@�@@�������i���j�A�������i���j�A�R����ʁA�R���S���҃R�[�h<BR> 
     * <BR>
     * �R�j�@@�\�[�g����������C���X�^���X�i�FString�j�𐶐�<BR>  
     * �@@this.create�\�[�g����������-�R���Ώێ��ʃR�[�h�ꗗ() ����擾���� <BR>
     * <BR>
     * �@@[����]<BR> 
     * �@@�@@�\�[�g�L�[<BR> 
     *<BR> 
     * �R�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A<BR>
     * �����J�ݐR���҂�Params��List���擾����B<BR>  
     * �@@�Y���f�[�^���Ȃ��ꍇ�Anull��ԋp���� <BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�Ɏw�肷�����]<BR>  
     * �@@�@@rowType�F�@@�����J�ݐR���҂�Row.TYPE <BR> 
     * �@@�@@where�F�@@��������������C���X�^���X <BR>
     * �@@�@@orderBy�F�@@�\�[�g����������C���X�^���X <BR>
     * �@@�@@conditions�F�@@null  <BR>
     * �@@�@@bindVars�F�@@���������f�[�^�R���e�i�C���X�^���X <BR>
     * <BR>
     * �S�j�@@�ԋp�lCollection�i�FArrayList)����<BR>  
     * �@@�ԋp�lCollection�i�FArrayList)�𐶐�����B<BR>  
     * <BR>
     * �T�j�@@�����J�ݐR���҂�Params��List�̌�����Loop����<BR> 
     * <BR>
     * �@@�T�|�P�j�ԋp�lCollection�ɃZ�b�g�������ɏd���������ʃR�[�h������ꍇ�A<BR>
     * �@@continue ����B<BR> 
     * <BR>
     * �@@�T�|�Q�j�ԋp�lCollection�ɁA<BR>
     * �@@�����J�ݐR���҂�Params��List[index]��ǉ�����<BR> 
     * <BR>
     * �@@�@@[add()�Ɏw�肷�����]<BR>  
     * �@@�@@�����J�ݐR���҂�Params��List[index].���ʃR�[�h<BR> 
     * <BR>
     * �U�j�ԋp�lCollection��ԋp���� <BR>
     * �@@�ԋp�lCollection.toArrays(new String[0])<BR> 
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * @@param l_strRequestNumber - (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     * @@param l_strOccurDateFrom - (�������i���j)<BR>
     * �������i���j<BR>
     * <BR>
     * YYYYMMDD�`��<BR>
     * @@param l_strOccurDateTo - (�������i���j)<BR>
     * �������i���j <BR>
     * <BR>
     * YYYYMMDD�`��<BR>
     * @@param l_strReviewCode - (�R�����)<BR>
     * �R�����<BR>
     * @@param l_strCheckerCode - (�R���S���҃R�[�h)<BR>
     * �R���S���҃R�[�h<BR>
     * @@param l_strCheckState - (�R����)<BR>
     * �R����<BR>
     * @@param l_sortKeys (�\�[�g�L�[)<BR>
     * �����J�݃\�[�g�L�[�̔z��<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     */
    public String[] selectJudgeObjectAccOpenRequestNumberList(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strRequestNumber,
        String l_strOccurDateFrom,
        String l_strOccurDateTo,
        String l_strReviewCode,
        String l_strCheckerCode,
        String l_strCheckState,
        WEB3AccOpenSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " selectJudgeObjectAccOpenRequestNumberList(" +
            "String, String, String, String, String, " +
            "String, String, String, String, WEB3AccOpenSortKey[])"; 
        log.entering(STR_METHOD_NAME);
        
		//�P�j�@@��������������C���X�^���X�i�FString�j�𐶐�  
		//this.create��������������-�R���Ώێ��ʃR�[�h�ꗗ() ����擾���� 
		//[����] 
		//���X�R�[�h�A�ڋq�R�[�h�A���ʃR�[�h�A 
		//�������i���j�A�������i���j�A�R����ʁA�R���S���҃R�[�h�A�R���� 
        String l_strQueryString = 
            this.createQueryStringForJudgeObjectAccOpenRequestNumberList(
                l_strBranchCode,
                l_strAccountCode,
                l_strRequestNumber,
                l_strOccurDateFrom,
                l_strOccurDateTo,
                l_strReviewCode,
                l_strCheckerCode,
                l_strCheckState);

		//�Q�j�@@���������f�[�^�R���e�i�C���X�^���X�i�FString[]�j�𐶐�  
		//this.create���������f�[�^�R���e�i-�R���Ώێ��ʃR�[�h�ꗗ() ����擾���� 
		//[����] 
		//�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�A���ʃR�[�h�A 
		//�������i���j�A�������i���j�A�R����ʁA�R���S���҃R�[�h
        String[] l_strQueryDataContainers = 
            this.createQueryDataContainerForJudgeObjectAccOpenRequestNumberList(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode,
                l_strRequestNumber,
                l_strOccurDateFrom,
                l_strOccurDateTo,
                l_strReviewCode,
                l_strCheckerCode);

        //�R�j�\�[�g����������C���X�^���X�i�FString�j�𐶐�  
		//this.create�\�[�g����������-�R���Ώێ��ʃR�[�h�ꗗ() ����擾���� 
		//[����] 
		//�\�[�g�L�[ 
        String l_strSortKey = 
            this.createSortCondForJudgeObjectAccOpenRequestNumberList(l_sortKeys);
        
        //�R�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A
        //�����J�ݐR���҂�Params��List���擾����B  
		//�Y���f�[�^���Ȃ��ꍇ�Anull��ԋp���� 
        
        List l_lisAccOpenWaitingRecords = null;
		try
        {
			//[doFindAllQuery()�Ɏw�肷�����]  
			//rowType�F�@@�����J�ݐR���҂�Row.TYPE  
			//where�F�@@��������������C���X�^���X 
			//orderBy�F�@@�\�[�g����������C���X�^���X 
			//conditions�F�@@null  
			//bindVars�F�@@���������f�[�^�R���e�i�C���X�^���X  
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisAccOpenWaitingRecords =
                l_queryProcessor.doFindAllQuery(
                    AccOpenWaitingRow.TYPE,
                    l_strQueryString,
                    l_strSortKey,
                    null,
                    l_strQueryDataContainers);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex); 
            log.exiting(STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                + "."
                + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        
        if (l_lisAccOpenWaitingRecords == null || 
            l_lisAccOpenWaitingRecords.isEmpty())
        {
	        log.exiting(STR_METHOD_NAME);
	        return null;
        }
        
		//�S�j�@@�ԋp�lCollection�i�FArrayList)����  
		//�ԋp�lCollection�i�FArrayList)�𐶐�����B
        ArrayList l_lisCollection = new ArrayList();
       
		//�T�j�@@�����J�ݐR���҂�Params��List�̌�����Loop���� 
		//�T�|�P�j�ԋp�lCollection�ɃZ�b�g�������ɏd���������ʃR�[�h������ꍇ�Acontinue ����B 
		//�T�|�Q�j�ԋp�lCollection�ɁA�����J�ݐR���҂�Params��List[index]��ǉ����� 
		//[add()�Ɏw�肷�����]  
		//�����J�ݐR���҂�Params��List[index].���ʃR�[�h 
        Map l_uniqueMap = new Hashtable();
        for (int i = 0; i < l_lisAccOpenWaitingRecords.size(); i++)
        {
            AccOpenWaitingRow l_accOpenWaitingRow = 
                (AccOpenWaitingRow) l_lisAccOpenWaitingRecords.get(i);
            AccOpenWaitingParams l_accOpenWaitingParams = 
                new AccOpenWaitingParams(l_accOpenWaitingRow);
              
            String l_strAccOpenRequestNumber = 
                l_accOpenWaitingParams.getAccOpenRequestNumber();

            if (l_uniqueMap.containsKey(l_strAccOpenRequestNumber))
            {
                continue;
            }
            else
            {
                l_lisCollection.add(l_strAccOpenRequestNumber);
                l_uniqueMap.put(l_strAccOpenRequestNumber, "1");
            }
        }
        
		//�U�j�ԋp�lCollection��ԋp���� 
		//�ԋp�lCollection.toArrays(new String[0]) 
        String[] l_strCollection = new String[l_lisCollection.size()];
        l_lisCollection.toArray(l_strCollection);
        
        log.exiting(STR_METHOD_NAME);
        return l_strCollection;
    }
    
    /**
     * (create��������������-�R���Ώێ��ʃR�[�h�ꗗ)<BR>
     * ���������������ҏW����B<BR>  
     * <BR>
     * �P�j�@@�߂�l����  <BR>
     * �@@�߂�l�̌�������������C���X�^���X�i�FStringBuffer�j�𐶐� <BR> 
     * <BR>
     * �Q�j�@@�،���ЃR�[�h�����ǉ�  <BR>
     * �@@�،���ЃR�[�h������ǉ�����B<BR>  
     * <BR>
     * �@@" institution_code = ? "  <BR>
     * <BR>
     * �R�j�@@���X�R�[�h�����ǉ�  <BR>
     * �@@���X�R�[�h != "000"(�S���X)�̏ꍇ�A���X�R�[�h������ǉ�����B <BR> 
     * <BR>
     * �@@" and branch_code = ? "  <BR>
     * <BR>
     * �S�j�@@�ڋq�R�[�h�����ǉ� <BR>
     * �@@�ڋq�R�[�h != null�̏ꍇ�A�ڋq�R�[�h������ǉ�����B<BR>  
     * <BR>
     * �@@" and account_code like ? "  <BR>
     * <BR>
     * �T�j�@@���ʃR�[�h�����ǉ� <BR>
     * �@@���ʃR�[�h != null�̏ꍇ�A���ʃR�[�h������ǉ�����B<BR>  
     * <BR>
     * �@@" and acc_open_request_number like ? "  <BR>
     * <BR>
     * �U�j�@@�������i���j�����ǉ� <BR>
     * �@@�������i���j != null�̏ꍇ�A��������������ǉ�����B<BR>  
     * <BR>
     * �@@" and to_char(created_timestamp, 'YYYYMMDD') >= ? "  <BR>
     * <BR>
     * �V�j�@@�������i���j�����ǉ� <BR>
     * �@@�������i���j != null�̏ꍇ�A��������������ǉ�����B <BR> 
     * <BR>
     * �@@" and to_char(created_timestamp, 'YYYYMMDD') <= ? "<BR>  
     * <BR>
     * �W�j�@@�R����ʏ����ǉ� <BR>
     * �@@�R����� != "0"(�S��)�̏ꍇ�A�R����ʏ�����ǉ�����B  <BR>
     * <BR>
     * �@@" and review_code = ? "  <BR>
     * <BR>
     * �X�j�@@�R���S���҃R�[�h�����ǉ� <BR>
     * �@@�R���S���҃R�[�h != null�̏ꍇ�A�R���S���҃R�[�h������ǉ�����B<BR>  
     * <BR>
     * �@@" and checker_code like ? "  <BR>
     * <BR>
     * �P�O�j�@@�R���󋵏����ǉ� <BR>
     * �@@�P�O�|�P�j�R���� = "1"(�R���҂�)�̏ꍇ <BR>
     * �@@�@@�R���敪 = "0"(�R���҂�)�@@�̏�����ǉ�����B  <BR>
     * <BR>
     * �@@�@@" and check_div = '0' "  <BR>
     * <BR>
     * �@@�P�O�|�Q�j�R���� = "2"(�R���ς�)�̏ꍇ <BR>
     * �@@�@@�R���敪 = "1"(�F��) or "2"(�۔F)�@@�̏�����ǉ�����B<BR>  
     * <BR>
     * �@@�@@" and check_div in ('1', '2') "  <BR>
     * <BR>
     * �@@�@@�P�O�|�R�j�R���� = "3"(���F��)�̏ꍇ <BR>
     * �@@�@@�@@�R���敪 = "1"(�F��)�@@�̏�����ǉ�����B  <BR>
     * <BR>
     * �@@�@@�@@" and check_div = '1' "  <BR>
     * <BR>
     * �@@�@@�P�O�|�S�j�R���� = "4"(�۔F��)�̏ꍇ <BR>
     * �@@�@@�@@�R���敪 = "2"(�۔F)�@@�̏�����ǉ�����B  <BR>
     * <BR>
     * �@@�@@�@@" and check_div = '2' "  <BR>
     * <BR>
     * �P�P�j��������������C���X�^���X.toString() ��ԋp���� <BR>
     * <BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     * @@param l_strRequestNumber - (���ʃR�[�h)<BR>
     * ���ʃR�[�h
     * @@param l_strOccurDateFrom - (�������i���j)<BR>
     * �������i���j<BR>
     * <BR>
     * YYYYMMDD�`��<BR>
     * @@param l_strOccurDateTo - (�������i���j)<BR>
     * �������i���j <BR>
     * <BR>
     * YYYYMMDD�`��<BR>
     * @@param l_strReviewCode - (�R�����)<BR>
     * �R�����
     * @@param l_strCheckerCode - (�R���S���҃R�[�h)<BR>
     * �R���S���҃R�[�h
     * @@param l_strCheckState - (�R����)<BR>
     * �R����
     * @@return String
     */
    private String createQueryStringForJudgeObjectAccOpenRequestNumberList(
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strRequestNumber,
        String l_strOccurDateFrom,
        String l_strOccurDateTo,
        String l_strReviewCode,
        String l_strCheckerCode,
        String l_strCheckState)
    {
        final String STR_METHOD_NAME = 
            " createQueryStringForJudgeObjectAccOpenRequestNumberList(" +
            "String, String, String, String, " +
            "String, String, String, String)"; 
        log.entering(STR_METHOD_NAME);
        
		//�P�j�@@�߂�l����  
		//�߂�l�̌�������������C���X�^���X�i�FStringBuffer�j�𐶐� 
        StringBuffer l_strQueryString = new StringBuffer();
        
		//�Q�j�@@�،���ЃR�[�h�����ǉ�  
		//�،���ЃR�[�h������ǉ�����B
        //" institution_code = ? "  
        l_strQueryString.append(" institution_code = ? ");

		//�R�j�@@���X�R�[�h�����ǉ�  
		//���X�R�[�h != "000"(�S���X)�̏ꍇ�A���X�R�[�h������ǉ�����B  
		//" and branch_code = ? "  
        if (!WEB3BranchCodeDef.DEFAULT.equals(l_strBranchCode))
        {
            l_strQueryString.append(" and branch_code = ? ");
        }
        
		//�S�j�@@�ڋq�R�[�h�����ǉ� 
		//�ڋq�R�[�h != null�̏ꍇ�A�ڋq�R�[�h������ǉ�����B  
		//" and account_code like ? " 
        if (l_strAccountCode != null)
        {
            l_strQueryString.append(" and account_code like ? ");
        }
        
		//�T�j�@@���ʃR�[�h�����ǉ� 
		//���ʃR�[�h != null�̏ꍇ�A���ʃR�[�h������ǉ�����B  
		//" and acc_open_request_number like ? "  
        if (l_strRequestNumber != null)
        {
            l_strQueryString.append(" and acc_open_request_number like ? ");
        } 
        
		//�U�j�@@�������i���j�����ǉ� 
		//�������i���j != null�̏ꍇ�A��������������ǉ�����B  
		//" and to_char(created_timestamp, 'YYYYMMDD') >= ? "  
        if (l_strOccurDateFrom != null)
        {
            l_strQueryString.append(" and to_char(created_timestamp, 'YYYYMMDD') >= ? ");
        }
        
		//�V�j�@@�������i���j�����ǉ� 
		//�������i���j != null�̏ꍇ�A��������������ǉ�����B  
		//" and to_char(created_timestamp, 'YYYYMMDD') <= ? "  
        if (l_strOccurDateTo != null)
        {
            l_strQueryString.append(" and to_char(created_timestamp, 'YYYYMMDD') <= ? ");
        }
        
		//�W�j�@@�R����ʏ����ǉ� 
		//�R����� != "0"(�S��)�̏ꍇ�A�R����ʏ�����ǉ�����B  
		//" and review_code = ? "  
        if (!WEB3ReviewCodeDef.DEFAULT.equals(l_strReviewCode))
        {
            l_strQueryString.append(" and review_code = ? ");
        }
        
		//�X�j�@@�R���S���҃R�[�h�����ǉ� 
		//�R���S���҃R�[�h != null�̏ꍇ�A�R���S���҃R�[�h������ǉ�����B  
		//" and checker_code like ? "  
        if (l_strCheckerCode != null)
        {
            l_strQueryString.append(" and checker_code like ? ");
        }
        
		//�P�O�j�@@�R���󋵏����ǉ� 
		//�P�O�|�P�j�R���� = "1"(�R���҂�)�̏ꍇ 
		//�R���敪 = "0"(�R���҂�)�@@�̏�����ǉ�����B  
		//" and check_div = '0' "  
        if (WEB3AccOpenCheckStateDivDef.JUDGE_WAITING.equals(l_strCheckState))
        {
            l_strQueryString.append(" and check_div = '0' ");
        }
        
		//�P�O�|�Q�j�R���� = "2"(�R���ς�)�̏ꍇ 
		//�R���敪 = "1"(�F��) or "2"(�۔F)�@@�̏�����ǉ�����B  
		//" and check_div in ('1', '2') "  
        else if (WEB3AccOpenCheckStateDivDef.JUDGE_COMPLETE.equals(l_strCheckState))
        {
            l_strQueryString.append(" and check_div in ('1', '2') ");
        }
        
		//�P�O�|�R�j�R���� = "3"(���F��)�̏ꍇ 
		//�R���敪 = "1"(�F��)�@@�̏�����ǉ�����B  
		//" and check_div = '1' "  
        else if (WEB3AccOpenCheckStateDivDef.APPROVAL_COMPLETE.equals(l_strCheckState))
        {
            l_strQueryString.append(" and check_div = '1' ");
        }
		//�P�O�|�S�j�R���� = "4"(�۔F��)�̏ꍇ 
		//�R���敪 = "2"(�۔F)�@@�̏�����ǉ�����B  
		//" and check_div = '2' "  
        else if (WEB3AccOpenCheckStateDivDef.OPEN_COMPLETE.equals(l_strCheckState))
        {
            l_strQueryString.append(" and check_div = '2' ");
        }
        
		//�P�P�j��������������C���X�^���X.toString() ��ԋp���� 
        log.exiting(STR_METHOD_NAME);
        return l_strQueryString.toString();
    }
    
    /**
     * (create���������f�[�^�R���e�i-�R���Ώێ��ʃR�[�h�ꗗ)<BR>
     * ���������f�[�^�R���e�i��ҏW����B<BR>  
     * <BR>
     * �P�j�@@�߂�l����<BR>  
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�<BR>  
     * <BR>
     * �Q�j�@@�،���ЃR�[�h�����ǉ�<BR>  
     * �@@���������f�[�^�R���e�i�C���X�^���X�ɁA�،���ЃR�[�h��ǉ�����B<BR>  
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>  
     * �@@�،���ЃR�[�h <BR>
     * <BR>
     * �R�j�@@���X�R�[�h�����ǉ�<BR>  
     * �@@���X�R�[�h != "000"(�S���X)�̏ꍇ�A<BR>  
     * �@@���������f�[�^�R���e�i�C���X�^���X�ɁA���X�R�[�h��ǉ�����B<BR>  
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>  
     * �@@���X�R�[�h <BR>
     * <BR>
     * �S�j�@@�ڋq�R�[�h�����ǉ�<BR>  
     * �@@�ڋq�R�[�h != null�̏ꍇ�A<BR> 
     * �@@���������f�[�^�R���e�i�C���X�^���X�ɁA�ڋq�R�[�h��ǉ�����B<BR>  
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>  
     * �@@�ڋq�R�[�h�{'��' <BR>
     * <BR>
     * �T�j�@@���ʃR�[�h�����ǉ�<BR>  
     * �@@���ʃR�[�h != null�̏ꍇ�A<BR> 
     * �@@���������f�[�^�R���e�i�C���X�^���X�ɁA���ʃR�[�h��ǉ�����B<BR>  
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>  
     * �@@���ʃR�[�h�{'��' <BR>
     * <BR>
     * �U�j�@@�������i���j�����ǉ�<BR>  
     * �@@�������i���j != null�̏ꍇ�A<BR> 
     * �@@���������f�[�^�R���e�i�C���X�^���X�ɁA�������i���j��ǉ�����B<BR>  
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>  
     * �@@�������i���j <BR>
     * <BR>
     * �V�j�@@�������i���j�����ǉ�<BR>  
     * �@@�������i���j != null�̏ꍇ�A<BR> 
     * �@@���������f�[�^�R���e�i�C���X�^���X�ɁA�������i���j��ǉ�����B<BR>  
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>  
     * �@@�������i���j<BR> 
     * <BR>
     * �W�j�@@�R����ʏ����ǉ� <BR>
     * �@@�R����� != "0"(�S��)�̏ꍇ�A<BR> 
     * �@@���������f�[�^�R���e�i�C���X�^���X�ɁA�R����ʂ�ǉ�����B<BR>  
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>  
     * �@@�R����� <BR>
     * <BR>
     * �X�j�@@�R���S���҃R�[�h�����ǉ�<BR>  
     * �@@�R���S���҃R�[�h != null�̏ꍇ�A<BR> 
     * �@@���������f�[�^�R���e�i�C���X�^���X�ɁA�R���S���҃R�[�h��ǉ�����B<BR>  
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>  
     * �@@�R���S���҃R�[�h�{'��' <BR>
     * <BR>
     * �P�O�j�@@�z���ԋp<BR>  
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B<BR> 
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     * @@param l_strRequestNumber - (���ʃR�[�h)<BR>
     * ���ʃR�[�h
     * @@param l_strOccurDateFrom - (�������i���j)<BR>
     * �������i���j<BR>
     * <BR>
     * YYYYMMDD�`��<BR>
     * @@param l_strOccurDateTo - (�������i���j)<BR>
     * �������i���j <BR>
     * <BR>
     * YYYYMMDD�`��<BR>
     * @@param l_strReviewCode - (�R�����)<BR>
     * �R�����
     * @@param l_strCheckerCode - (�R���S���҃R�[�h)<BR>
     * �R���S���҃R�[�h
     * @@return String[]
     */
    private String[] createQueryDataContainerForJudgeObjectAccOpenRequestNumberList(
            String l_strInstitutionCode,
            String l_strBranchCode,
            String l_strAccountCode,
            String l_strRequestNumber,
            String l_strOccurDateFrom,
            String l_strOccurDateTo,
            String l_strReviewCode,
            String l_strCheckerCode)
    {
        final String STR_METHOD_NAME = 
            " createQueryDataContainerForJudgeObjectAccOpenRequestNumberList(" +
            "String, String, String, String, String, " +
            "String, String, String)"; 
        log.entering(STR_METHOD_NAME);
        
		//�P�j�@@�߂�l����  
		//�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�  
        ArrayList l_lisQueryContainer = new ArrayList();

		//�Q�j�@@�،���ЃR�[�h�����ǉ�  
		//���������f�[�^�R���e�i�C���X�^���X�ɁA�،���ЃR�[�h��ǉ�����B  
		//[add()�Ɏw�肷�����]  
		//�،���ЃR�[�h 
        l_lisQueryContainer.add(l_strInstitutionCode);
        
		//�R�j�@@���X�R�[�h�����ǉ�  
		//���X�R�[�h != "000"(�S���X)�̏ꍇ�A  
		//���������f�[�^�R���e�i�C���X�^���X�ɁA���X�R�[�h��ǉ�����B  
		//[add()�Ɏw�肷�����]  
		//���X�R�[�h 
        if (!WEB3BranchCodeDef.DEFAULT.equals(l_strBranchCode))
        {
            l_lisQueryContainer.add(l_strBranchCode);
        }

        //�S�j�@@�ڋq�R�[�h�����ǉ�  
		//�ڋq�R�[�h != null�̏ꍇ�A 
		//���������f�[�^�R���e�i�C���X�^���X�ɁA�ڋq�R�[�h��ǉ�����B  
		//[add()�Ɏw�肷�����]  
		//�ڋq�R�[�h�{'��' 
        if (l_strAccountCode != null)
        {
            l_lisQueryContainer.add(l_strAccountCode + '%');
        }  

        //�T�j�@@���ʃR�[�h�����ǉ�  
		//���ʃR�[�h != null�̏ꍇ�A 
		//���������f�[�^�R���e�i�C���X�^���X�ɁA���ʃR�[�h��ǉ�����B  
		//[add()�Ɏw�肷�����]  
		//���ʃR�[�h�{'��' 
        if (l_strRequestNumber != null)
        {
            l_lisQueryContainer.add(l_strRequestNumber + '%');
        } 

        //�U�j�@@�������i���j�����ǉ�  
		//�������i���j != null�̏ꍇ�A 
		//���������f�[�^�R���e�i�C���X�^���X�ɁA�������i���j��ǉ�����B  
		//[add()�Ɏw�肷�����]  
		//�������i���j 
        if (l_strOccurDateFrom != null)
        {
            l_lisQueryContainer.add(l_strOccurDateFrom);
        }

        //�V�j�@@�������i���j�����ǉ�  
		//�������i���j != null�̏ꍇ�A 
		//���������f�[�^�R���e�i�C���X�^���X�ɁA�������i���j��ǉ�����B  
		//[add()�Ɏw�肷�����]  
		//�������i���j 
        if (l_strOccurDateTo != null)
        {
            l_lisQueryContainer.add(l_strOccurDateTo);
        }

        //�W�j�@@�R����ʏ����ǉ� 
		//�R����� != "0"(�S��)�̏ꍇ�A 
		//���������f�[�^�R���e�i�C���X�^���X�ɁA�R����ʂ�ǉ�����B  
		//[add()�Ɏw�肷�����]  
		//�R����� 
        if (!WEB3ReviewCodeDef.DEFAULT.equals(l_strReviewCode))
        {
            l_lisQueryContainer.add(l_strReviewCode);
        }

        //�X�j�@@�R���S���҃R�[�h�����ǉ�  
		//�R���S���҃R�[�h != null�̏ꍇ�A 
		//���������f�[�^�R���e�i�C���X�^���X�ɁA�R���S���҃R�[�h��ǉ�����B  
		//[add()�Ɏw�肷�����]  
		//�R���S���҃R�[�h�{'��' 
        if (l_strCheckerCode != null)
        {
            l_lisQueryContainer.add(l_strCheckerCode + '%');
        } 
        
        //�P�O�j�@@�z���ԋp  
		//�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B
        String[] l_strQueryContainers = new String[l_lisQueryContainer.size()];
        l_lisQueryContainer.toArray(l_strQueryContainers);
        
        log.exiting(STR_METHOD_NAME);
        return l_strQueryContainers;
    }
    
    /**
     * (create�\�[�g����������-�R���Ώێ��ʃR�[�h�ꗗ )<BR>
     * �\�[�g�����������ҏW����B<BR>  
     * <BR>
     * �P�j�����̃\�[�g�L�[�������L�[���ڂ��A�����J�ݐR���҂��񕨗����ɕϊ����� <BR> 
     * ���������ioccurredDate�j�̏ꍇ�A�����J�ݐR���҂�.�쐬����<BR> 
     * ���ʃR�[�h�irequestNumber�j�̏ꍇ�A�����J�ݐR���҂�.���ʃR�[�h <BR>  
     * �Q�j�\�[�g�L�[�̎w��̒ʂ�A�\�[�g����������iorder by��j��ҏW���ԋp����B<BR>  
     * <BR>
     * ���@@�\�[�g�L�[�Ɏw�肳��鍀�ڂ͈ȉ��̒ʂ�B<BR> 
     * �@@�����J�ݐR���҂�.�쐬���� <BR>
     * �@@�����J�ݐR���҂�.���ʃR�[�h <BR>
     * @@param l_sortKeys (�\�[�g�L�[)<BR>
     * �����J�݃\�[�g�L�[�̔z��
     * @@return String
     * @@throws WEB3BaseException
     */
    private String createSortCondForJudgeObjectAccOpenRequestNumberList(
        WEB3AccOpenSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " createSortCondForJudgeObjectAccOpenRequestNumberList(WEB3AccOpenSortKey[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_sortKeys == null || l_sortKeys.length == 0)
        {
            log.debug(" __parameter_error__ ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
		//�P�j�����̃\�[�g�L�[�������L�[���ڂ��A�����J�ݐR���҂��񕨗����ɕϊ����� 
		//�\�[�g�L�[�̎w��̒ʂ�A�\�[�g����������iorder by��j��ҏW���ԋp����B  
		//���@@�\�[�g�L�[�Ɏw�肳��鍀�ڂ͈ȉ��̒ʂ�B        
        String l_strSortCond = "";
        int l_intSortKeyLength = l_sortKeys.length;
        
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            WEB3AccOpenSortKey l_sortKey = l_sortKeys[i];
            if (l_sortKey == null)
            {
                log.debug("WEB3AccOpenSortKey is Null");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                     this.getClass().getName() + STR_METHOD_NAME);
            }

            //�����J�ݐR���҂�.�쐬����
            if (WEB3AccOpenFromWebSortKeyDef.OCCRRRED_DATE.equals(l_sortKey.keyItem))
            {
                l_strSortCond += Web3AccOpenSortKeyDef.CREATED_TIMESTAMP;
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            }
            
            //�����J�ݐR���҂�.���ʃR�[�h
            else if (WEB3AccOpenFromWebSortKeyDef.REQUEST_NUMBER.equals(l_sortKey.keyItem))
            {
                l_strSortCond += Web3AccOpenSortKeyDef.ACC_OPEN_REQUEST_NUMBER;
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            }
        }

        l_strSortCond = 
            l_strSortCond.substring(0, l_strSortCond.length() - 2) + " ";

        log.exiting(STR_METHOD_NAME);
        return l_strSortCond;          
    }
    
    /**
     * (select�R���Ώۈꗗ)<BR>
     * �w�肵�Ă�������𐶐����A�����J�ݐR���҂��ꗗ���擾����B<BR> 
     * <BR>
     * �P�j�@@��������������C���X�^���X�i�FString�j�𐶐�<BR>  
     * �@@this.create��������������-�R���Ώۈꗗ() ����擾����<BR> 
     * <BR>
     * �@@[����]<BR> 
     * �@@�@@���ʃR�[�h[]<BR> 
     * <BR>
     * �Q�j�@@���������f�[�^�R���e�i�C���X�^���X�i�FString[]�j�𐶐�<BR>  
     * �@@this.create���������f�[�^�R���e�i-�R���Ώۈꗗ() ����擾���� <BR>
     * <BR>
     * �@@[����]<BR> 
     * �@@�@@�،���ЃR�[�h <BR>
     * �@@�@@���ʃR�[�h[]<BR> 
     * <BR>
     * �R�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A<BR>
     * �����J�ݐR���҂��I�u�W�F�N�g��List���擾����B<BR>  
     * �@@�Y���f�[�^���Ȃ��ꍇ�A0��ԋp���� <BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�Ɏw�肷�����]<BR>  
     * �@@�@@rowType�F�@@�����J�ݐR���҂�Row.TYPE <BR> 
     * �@@�@@where�F�@@��������������C���X�^���X <BR>
     * �@@�@@bindVars�F�@@���������f�[�^�R���e�i�C���X�^���X<BR> 
     * <BR>
     * �S�j�@@�����J�ݐR���҂��I�u�W�F�N�g��List�������J�ݐR���҂��f�[�^�ɃZ�b�g����<BR> 
     * <BR>
     * �T�j�@@�����J�ݐR���҂��I�u�W�F�N�g��List�̌������擾���A�ԋp����<BR> 
     * @@param l_strRequestNumbers - (���ʃR�[�h)<BR>
     * ���ʃR�[�h�̈ꗗ
     * @@return int
     * @@throws WEB3BaseException
     */
    public int selectJudgeObjectList(
            String l_strInstitutionCode,
            String[] l_strRequestNumbers) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " selectJudgeObjectList(String[])";
        log.entering(STR_METHOD_NAME);
        
		//�P�j�@@��������������C���X�^���X�i�FString�j�𐶐�  
		//this.create��������������-�R���Ώۈꗗ() ����擾���� 
		//[����] 
		//���ʃR�[�h[] 
        String l_strQueryString = 
            this.createQueryStringForJudgeObjectList(l_strRequestNumbers);
        
		//�Q�j�@@���������f�[�^�R���e�i�C���X�^���X�i�FString[]�j�𐶐�  
		//this.create���������f�[�^�R���e�i-�R���Ώۈꗗ() ����擾���� 
		//[����] 
        //�،���ЃR�[�h
		//���ʃR�[�h[]
        String[] l_strQueryDataContainers = 
            this.createQueryDataContainerForJudgeObjectList(l_strInstitutionCode, l_strRequestNumbers);
        
		//�R�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A�����J�ݐR���҂��I�u�W�F�N�g��List���擾����B  
        List l_lisAccOpenWaitingRecords = null;
		try
        {
			//[doFindAllQuery()�Ɏw�肷�����]  
			//rowType�F�@@�����J�ݐR���҂�Row.TYPE  
			//where�F�@@��������������C���X�^���X 
			//bindVars�F�@@���������f�[�^�R���e�i�C���X�^���X
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisAccOpenWaitingRecords =
                l_queryProcessor.doFindAllQuery(
                    AccOpenWaitingRow.TYPE,
                    l_strQueryString,
                    l_strQueryDataContainers);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                + "."
                + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        
		//�Y���f�[�^���Ȃ��ꍇ�A0��ԋp���� 
        if (l_lisAccOpenWaitingRecords == null || 
            l_lisAccOpenWaitingRecords.isEmpty())
        {
	        log.exiting(STR_METHOD_NAME);
	        return 0;
        }
        
		//�S�j�����J�ݐR���҂��I�u�W�F�N�g��List�������J�ݐR���҂��f�[�^�ɃZ�b�g����
        for (int i = 0; i < l_lisAccOpenWaitingRecords.size(); i++)
        {
            AccOpenWaitingRow l_accOpenWaitingRow = 
                (AccOpenWaitingRow) l_lisAccOpenWaitingRecords.get(i);
            AccOpenWaitingParams l_accOpenWaitingParams = 
                new AccOpenWaitingParams(l_accOpenWaitingRow);
            if (this.accOpenWaitingParamsList != null)
            {
                this.accOpenWaitingParamsList.add(l_accOpenWaitingParams);
            }
        }
        
		//�T�j�����J�ݐR���҂��I�u�W�F�N�g��List�̌������擾���A�ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_lisAccOpenWaitingRecords.size();
        
    }
    
    /**
     * (select�R���Ώۈꗗ)<BR>
     * �w�肵�Ă�������𐶐����A�����J�ݐR���҂��ꗗ���擾����B<BR> 
     * <BR>
     * �P�j�@@��������������C���X�^���X�i�FString�j�𐶐�  <BR>
     * �@@this.create��������������-�R���Ώۈꗗ() ����擾���� <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ʃR�[�h[] <BR>
     * 
     * �Q�j�@@���������f�[�^�R���e�i�C���X�^���X�i�FString[]�j�𐶐�<BR>  
     * �@@this.create���������f�[�^�R���e�i-�R���Ώۈꗗ() ����擾����<BR> 
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�،���ЃR�[�h <BR>
     * �@@�@@���ʃR�[�h[] <BR>
     * <BR>
     * �R�j�@@�\�[�g����������C���X�^���X�i�FString�j�𐶐�  <BR>
     * �@@this.create�\�[�g����������-�R���Ώۈꗗ() ����擾���� <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�\�[�g�L�[ <BR>
     * <BR>
     * �S�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A<BR>
     * �����J�ݐR���҂��I�u�W�F�N�g��List���擾����B<BR>  
     * �@@�Y���f�[�^���Ȃ��ꍇ�A0��ԋp���� <BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�Ɏw�肷�����]  <BR>
     * �@@�@@rowType�F�@@�����J�ݐR���҂�Row.TYPE <BR> 
     * �@@�@@where�F�@@��������������C���X�^���X <BR>
     * �@@�@@orderBy�F�@@�\�[�g����������C���X�^���X <BR>
     * �@@�@@conditions�F�@@null  <BR>
     * �@@�@@bindVars�F�@@���������f�[�^�R���e�i�C���X�^���X <BR>
     * <BR>
     * �T�j�@@�����J�ݐR���҂��I�u�W�F�N�g��List�������J�ݐR���҂��f�[�^�ɃZ�b�g����<BR> 
     * <BR>
     * �U�j�@@�����J�ݐR���҂��I�u�W�F�N�g��List�̌������擾���A�ԋp����<BR> 
     * @@param l_strRequestNumbers - (���ʃR�[�h)<BR>
     * ���ʃR�[�h�̈ꗗ
     * @@param l_sortKeys (�\�[�g�L�[)<BR>
     * �����J�݃\�[�g�L�[�̔z��
     * @@return int
     * @@throws WEB3BaseException
     */
    public int selectJudgeObjectList(
	        String l_strInstitutionCode,
	        String[] l_strRequestNumbers, WEB3AccOpenSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " selectJudgeObjectList(String[], WEB3AccOpenSortKey[])";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@��������������C���X�^���X�i�FString�j�𐶐�  
		//this.create��������������-�R���Ώۈꗗ() ����擾����
		//[����] 
		//���ʃR�[�h[] 
        String l_strQueryString = 
            this.createQueryStringForJudgeObjectList(l_strRequestNumbers);
        
		//�Q�j�@@���������f�[�^�R���e�i�C���X�^���X�i�FString[]�j�𐶐�  
		//this.create���������f�[�^�R���e�i-�R���Ώۈꗗ() ����擾���� 
		//[����] 
        //�،���ЃR�[�h
		//���ʃR�[�h[]
        String[] l_strQueryDataContainers = 
            this.createQueryDataContainerForJudgeObjectList(l_strInstitutionCode, l_strRequestNumbers);
        
		//�R�j�@@�\�[�g����������C���X�^���X�i�FString�j�𐶐�  
		//this.create�\�[�g����������-�R���Ώۈꗗ() ����擾���� 
		//[����] 
		//�\�[�g�L�[ 
        String l_sortKey = 
            this.createSortCondStringForJudgeObjectList(l_sortKeys);
        
		//�S�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A�����J�ݐR���҂��I�u�W�F�N�g��List���擾����B  
		//�Y���f�[�^���Ȃ��ꍇ�Anull��ԋp���� 
        List l_lisAccOpenWaitingRecords = null;
		try
        {
			//[doFindAllQuery()�Ɏw�肷�����]  
			//rowType�F�@@�����J�ݐR���҂�Row.TYPE  
			//where�F�@@��������������C���X�^���X 
			//orderBy�F�@@�\�[�g����������C���X�^���X 
			//conditions�F�@@null  
			//bindVars�F�@@���������f�[�^�R���e�i�C���X�^���X�B 
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisAccOpenWaitingRecords =
                l_queryProcessor.doFindAllQuery(
                    AccOpenWaitingRow.TYPE,
                    l_strQueryString,
                    l_sortKey,
                    null,
                    l_strQueryDataContainers);
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
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                + "."
                + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        
		//�Y���f�[�^���Ȃ��ꍇ�A0��ԋp���� 
        if (l_lisAccOpenWaitingRecords == null || 
            l_lisAccOpenWaitingRecords.isEmpty())
        {
	        log.exiting(STR_METHOD_NAME);
	        return 0;
        }
        
		//�T�j�����J�ݐR���҂��I�u�W�F�N�g��List�������J�ݐR���҂��f�[�^�ɃZ�b�g���� 
        for (int i = 0; i < l_lisAccOpenWaitingRecords.size(); i++)
        {
            AccOpenWaitingRow l_accOpenWaitingRow = 
                (AccOpenWaitingRow) l_lisAccOpenWaitingRecords.get(i);
            AccOpenWaitingParams l_accOpenWaitingParams = 
                new AccOpenWaitingParams(l_accOpenWaitingRow);
            if (this.accOpenWaitingParamsList != null)
            {
                this.accOpenWaitingParamsList.add(l_accOpenWaitingParams);
            }
        }
        
		//�U�j�����J�ݐR���҂��I�u�W�F�N�g��List�̌������擾���A�ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_lisAccOpenWaitingRecords.size();
        
    }
    
    /**
     * (create��������������-�R���Ώۈꗗ)<BR>
     * ���������������ҏW����B<BR>  
     * <BR>
     * �P�j�@@�߂�l����  <BR>
     * �@@�߂�l�̌�������������C���X�^���X�i�FStringBuffer�j�𐶐� <BR> 
     * <BR>
     * �Q�j�@@�،���ЃR�[�h�����ǉ�  <BR>
     * �@@�،���ЃR�[�h������ǉ�����B<BR>  
     * <BR>
     * �@@" institution_code = ? "  <BR>
     * <BR>
     * �R�j�@@���ʃR�[�h�����ǉ�  <BR>
     * �@@���ʃR�[�h������ǉ�����B<BR>
     * <BR>
     * �@@" and acc_open_request_number in (?,?,�c,?) " <BR> 
     * <BR>
     * �@@? �͎��ʃR�[�h�̌������ǉ����� <BR>
     * <BR>
     * �S�j��������������C���X�^���X.toString() ��ԋp���� <BR>
     * @@param l_strRequestNumbers - (���ʃR�[�h)<BR>
     * ���ʃR�[�h�̈ꗗ
     * @@return String
     * @@throws WEB3BaseException
     */
    private String createQueryStringForJudgeObjectList(
        String[] l_strRequestNumbers) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " createQueryStringForJudgeObjectList(String[])";
        log.entering(STR_METHOD_NAME);
        
		//�P�j�@@�߂�l����  
		//�߂�l�̌�������������C���X�^���X�i�FStringBuffer�j��
        StringBuffer l_strQueryString = new StringBuffer();
        String l_strQueryStringReturn = "";
        
        int l_intRequestNumbers = 0;
        if (l_strRequestNumbers != null && l_strRequestNumbers.length != 0)
        {
            l_intRequestNumbers = l_strRequestNumbers.length;
        }
        
		//�Q�j�@@�،���ЃR�[�h�����ǉ�  
		//�،���ЃR�[�h������ǉ�����B
        //" institution_code = ? "  
        l_strQueryString.append(" institution_code = ? ");

		//�R�j�@@���ʃR�[�h�����ǉ�  
		//���ʃR�[�h������ǉ�����B  
		//" acc_open_request_number in (?,?,�c,?) "  
		//? �͎��ʃR�[�h�̌������ǉ�����
        
        l_strQueryString.append(" and acc_open_request_number in (");
        
        for (int i = 0; i < l_intRequestNumbers; i++)
        {
            l_strQueryString.append("?, ");  
        }
        
        l_strQueryStringReturn = 
            l_strQueryString.substring(0, l_strQueryString.length() - 2) + ") ";
        
		//�S�j��������������C���X�^���X.toString() ��ԋp���� 
        log.exiting(STR_METHOD_NAME);
        return l_strQueryStringReturn;
    }
    
    /**
     * (create�����f�[�^�R���e�i-�R���Ώۈꗗ)<BR>
     * ���������f�[�^�R���e�i��ҏW����B<BR>  
     * <BR>
     * �P�j�@@�߂�l���� <BR> 
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�<BR>  
     * <BR>
     * �Q�j�@@�،���ЃR�[�h�����ǉ�<BR>  
     * �@@���������f�[�^�R���e�i�C���X�^���X�ɁA�،���ЃR�[�h��ǉ�����B<BR>  
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>  
     * �@@�،���ЃR�[�h <BR>
     * <BR>
     * �R�j�@@���ʃR�[�h�����ǉ�<BR>  
     * �@@���������f�[�^�R���e�i�C���X�^���X�ɁA���ʃR�[�h���������ǉ�����B<BR>  
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>  
     * �@@���ʃR�[�h <BR>
     * <BR>
     * �S�j�@@�z���ԋp<BR>  
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B<BR> 
     * @@param l_strRequestNumbers - (���ʃR�[�h)<BR>
     * ���ʃR�[�h�̈ꗗ
     * @@return String[]
     * @@throws WEB3BaseException
     */
    private String[] createQueryDataContainerForJudgeObjectList(
        String l_strInstitutionCode,
        String[] l_strRequestNumbers) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " createQueryDataContainerForJudgeObjectList(String[])";
        log.entering(STR_METHOD_NAME);
        
        int l_intRequestNumbers = 0;
        if (l_strRequestNumbers != null && l_strRequestNumbers.length != 0)
        {
            l_intRequestNumbers = l_strRequestNumbers.length;
        }
        
		//�P�j�@@�߂�l����  
		//�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐� 
        ArrayList l_lisJudgeObjectList = new ArrayList();

		//�Q�j�@@�،���ЃR�[�h�����ǉ�  
		//���������f�[�^�R���e�i�C���X�^���X�ɁA�،���ЃR�[�h��ǉ�����B  
		//[add()�Ɏw�肷�����]  
		//�،���ЃR�[�h 
        l_lisJudgeObjectList.add(l_strInstitutionCode);
        
		//�R�j�@@���ʃR�[�h�����ǉ�  
		//���������f�[�^�R���e�i�C���X�^���X�ɁA���ʃR�[�h���������ǉ�����B  
		//[add()�Ɏw�肷�����]  
		//���ʃR�[�h 
        for(int i = 0 ; i < l_intRequestNumbers; i++)
        {
            l_lisJudgeObjectList.add(l_strRequestNumbers[i]);
        }
        
		//�S�j�z���ԋp  
		//�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����
        String[] l_strRequestNumber = new String[l_lisJudgeObjectList.size()];
        l_lisJudgeObjectList.toArray(l_strRequestNumber);
        
        log.exiting(STR_METHOD_NAME);
        return l_strRequestNumber;
    }
    
    /**
     * (create�\�[�g����������-�R���Ώۈꗗ)<BR>
     * �\�[�g�����������ҏW����B<BR>  
     * <BR>
     * �P�j�����̃\�[�g�L�[�������L�[���ڂ��A�����J�ݐR���҂��񕨗����ɕϊ�����<BR>  
     * ���������ioccurredDate�j�̏ꍇ�A�����J�ݐR���҂�.�쐬����<BR>  
     * ���ʃR�[�h�irequestNumber�j�̏ꍇ�A�����J�ݐR���҂�.���ʃR�[�h <BR> 
     * �Q�j�\�[�g�L�[�̎w��̒ʂ�A�\�[�g����������iorder by��j��ҏW���ԋp����B<BR>  
     * <BR>
     * ���@@�\�[�g�L�[�Ɏw�肳��鍀�ڂ͈ȉ��̒ʂ�B<BR> 
     * �@@�����J�ݐR���҂�.�쐬���� <BR>
     * �@@�����J�ݐR���҂�.���ʃR�[�h <BR>
     * �R�j�����Ɏ��ʃR�[�h���܂܂�Ă��Ȃ��ꍇ�A<BR>  
     * ���̍��ڂ��\�[�g����������ɒǉ����� <BR>  
     * �@@�L�[���ځF�u���ʃR�[�h�v <BR>  
     * �@@�����^�~���F�uA�F�����v <BR>  
     * �S�j���̍��ڂ��\�[�g����������ɒǉ����� <BR>  
     * �@@�L�[���ځF�u�ʔԁv <BR>  
     * �@@�����^�~���F�uA�F�����v <BR>  
     * <BR>  
     * @@param l_sortKeys (�\�[�g�L�[)<BR>
     * �����J�݃\�[�g�L�[�̔z��
     * @@return String
     * @@throws WEB3BaseException
     */
    private String createSortCondStringForJudgeObjectList(
        WEB3AccOpenSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " createSortCondStringForJudgeObjectList(WEB3AccOpenSortKey[])";
        log.entering(STR_METHOD_NAME);
        
        int l_intSortKeysLength = 0;
        if (l_sortKeys != null && l_sortKeys.length != 0)
        {
            l_intSortKeysLength = l_sortKeys.length;
        }
        
		//�P�j�����̃\�[�g�L�[�������L�[���ڂ��A�����J�ݐR���҂��񕨗����ɕϊ�����A  
		//�\�[�g�L�[�̎w��̒ʂ�A�\�[�g����������iorder by��j��ҏW���ԋp����B  
		//���@@�\�[�g�L�[�Ɏw�肳��鍀�ڂ͈ȉ��̒ʂ�B        
        String l_strSortCond = "";
        String l_strSortKey = "";
        
        boolean l_blnIsAccOpenRequestNumber = false;
        
        for (int i = 0; i < l_intSortKeysLength; i++)
        {
            WEB3AccOpenSortKey l_sortKey = l_sortKeys[i];
            if (l_sortKey == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                     this.getClass().getName() + STR_METHOD_NAME);
            }

            //�R�j�����Ɏ��ʃR�[�h���܂܂�Ă��Ȃ��ꍇ�A���̍��ڂ��\�[�g����������ɒǉ����� 
    		//�L�[���ځF�u���ʃR�[�h�v 
    		//�����^�~���F�uA�F�����v
            
            //�����J�ݐR���҂�.�쐬����
            if(WEB3AccOpenFromWebSortKeyDef.OCCRRRED_DATE.equals(l_sortKey.keyItem))
            {
                l_strSortCond += Web3AccOpenSortKeyDef.CREATED_TIMESTAMP;
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }   
            }
            else if (WEB3AccOpenFromWebSortKeyDef.REQUEST_NUMBER.equals(l_sortKey.keyItem))
            {
                l_blnIsAccOpenRequestNumber = true;
                l_strSortKey = l_sortKey.ascDesc;
                l_strSortCond += Web3AccOpenSortKeyDef.ACC_OPEN_REQUEST_NUMBER;
                if (WEB3AscDescDef.ASC.equals(l_strSortKey))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }

            }
        }
        
        //�����J�ݐR���҂�.���ʃR�[�h
        if (!l_blnIsAccOpenRequestNumber)
        {
            l_strSortCond += Web3AccOpenSortKeyDef.ACC_OPEN_REQUEST_NUMBER; 
            l_strSortCond += " asc, "; 
        }

        //�S�j���̍��ڂ��\�[�g����������ɒǉ����� 
		//�L�[���ځF�u�ʔԁv 
		//�����^�~���F�uA�F�����v
        
        //�����J�ݐR���҂�.�ʔ�
        l_strSortCond += Web3AccOpenSortKeyDef.SERIAL_NO + " asc ";

        log.exiting(STR_METHOD_NAME);
        return l_strSortCond;       
    }
    
    /**
     * (insert�����J�ݐR���҂�)<BR>
     * �����J�ݐR���҂��f�[�^�̓��e��insert���� <BR>
     * <BR>
     * �P�j�����������擾����B<BR> 
     *�@@���������i�o�^�p�j = GtlUtils.getTradingSystem().getSystemTimestamp()<BR>  
     *<BR> 
     *�@@�Q�j�����J�ݐR���҂��f�[�^�̌�����Loop����<BR> 
     * �@@�����J�ݐR���҂��f�[�^[Index].�쐬���� = ���������i�o�^�p�j<BR> 
     * �@@�����J�ݐR���҂��f�[�^[Index].�X�V���� = ���������i�o�^�p�j<BR> 
     * <BR>
     *	�Q�|�Q�jQueryProcessor.doInsertQuery()���\�b�h���R�[������B<BR> 
     * <BR>
     * �@@[doInsertQuery()�ɃZ�b�g����p�����[�^] <BR> 
     * �@@�@@arg0�F�@@�����J�ݐR���҂��f�[�^[Index] <BR>
     * <BR>
     * �� �����J�ݐR���҂��f�[�^�ɂ́A<BR>
     * �����J�ݐR���҂�Param�̃I�u�W�F�N�g���Z�b�g����Ă���<BR> 
     * <BR> 
     * @@throws WEB3BaseException
     */
    public void insertAccOpenWaiting() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " insertAccOpenWaiting()";
        log.entering(STR_METHOD_NAME);
        
		//�P�j�����������擾����B
        //���������i�o�^�p�j = GtlUtils.getTradingSystem().getSystemTimestamp()
        Timestamp l_tsTradingSystem = 
            GtlUtils.getTradingSystem().getSystemTimestamp();

        int l_intAccOpenWaitingParamsSize = 0;
        
        if (this.accOpenWaitingParamsList != null)
        {
            l_intAccOpenWaitingParamsSize = 
                this.accOpenWaitingParamsList.size(); 
        }
        
        //�Q�j�����J�ݐR���҂��f�[�^�̌�����Loop���� 
        
        for (int i = 0; i < l_intAccOpenWaitingParamsSize; i++)
        {
            try
            {
                AccOpenWaitingRow l_accOpenWaitingRow = 
                    (AccOpenWaitingRow) this.accOpenWaitingParamsList.get(i);
                AccOpenWaitingParams l_accOpenWaitingParams = 
                    new AccOpenWaitingParams(l_accOpenWaitingRow);

                //�Q�|�P�j�쐬�����A�X�V�����ɏ��������i�o�^�p�j���Z�b�g����B 

                //�����J�ݐR���҂��f�[�^[Index].�쐬���� = ���������i�o�^�p�j 
                l_accOpenWaitingParams.setCreatedTimestamp(l_tsTradingSystem);
                //�����J�ݐR���҂��f�[�^[Index].�X�V���� = ���������i�o�^�p�j 
                l_accOpenWaitingParams.setLastUpdatedTimestamp(l_tsTradingSystem);

        		//�Q�|�Q�jQueryProcessor.doInsertQuery()���\�b�h���R�[������B  
        		//[doInsertQuery()�ɃZ�b�g����p�����[�^] 
        		//arg0�F�����J�ݐR���҂��f�[�^[Index]
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doInsertQuery(l_accOpenWaitingParams);
            }
            catch (DataFindException l_dfe)
            {
                log.error(STR_METHOD_NAME, l_dfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName()
                        + "."
                        + STR_METHOD_NAME,
                    l_dfe.getMessage(),
                    l_dfe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error(STR_METHOD_NAME, l_dne);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName()
                        + "."
                        + STR_METHOD_NAME,
                    l_dne.getMessage(),
                    l_dne);
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (update�����J�ݐR���҂�)<BR>
     * �����J�ݐR���҂��f�[�^�̓��e��update���� <BR>
     * <BR>
     *�P�j�����������擾����B<BR> 
     * �@@���������i�o�^�p�j = GtlUtils.getTradingSystem().getSystemTimestamp() <BR> 
     *�Q�j�����J�ݐR���҂��f�[�^�̌�����Loop����<BR> 
     * <BR>
     *�Q�|�P�j�X�V�����ɏ��������i�o�^�p�j���Z�b�g����B<BR> 
     * �@@�����J�ݐR���҂��f�[�^[Index].�X�V���� = ���������i�o�^�p�j <BR> 
     *�Q�|�Q�jQueryProcessor.doUpdateQuery()���\�b�h���R�[������B<BR> 
     * �@@[doUpdateQuery()�ɃZ�b�g����p�����[�^]<BR>  
     * �@@�@@arg0�F�@@�����J�ݐR���҂��f�[�^[Index] <BR>
     * <BR>
     * �� �����J�ݐR���҂��f�[�^�ɂ́A<BR>
     * �����J�ݐR���҂�Param�̃I�u�W�F�N�g���Z�b�g����Ă���<BR> 
     * <BR> 
     * @@throws WEB3BaseException
     */
    public void updateAccOpenWaiting() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " updateAccOpenWaiting()";
        log.entering(STR_METHOD_NAME);
        
		//�P�j�����������擾����B
        //���������i�o�^�p�j = GtlUtils.getTradingSystem().getSystemTimestamp()
        Timestamp l_tsTradingSystem = 
            GtlUtils.getTradingSystem().getSystemTimestamp();
        
		//�Q�j�����J�ݐR���҂��f�[�^�̌�����Loop���� 
        int l_intAccOpenWaitingParamsSize = 0;
        
        if (this.accOpenWaitingParamsList != null)
        {
            l_intAccOpenWaitingParamsSize = 
                this.accOpenWaitingParamsList.size(); 
        }

        for (int i = 0; i < l_intAccOpenWaitingParamsSize; i++)
        {
            try
            {
                //�Q�|�P�j�X�V�����ɏ��������i�o�^�p�j���Z�b�g����B 
                AccOpenWaitingRow l_accOpenWaitingRow = 
                    (AccOpenWaitingRow) this.accOpenWaitingParamsList.get(i);
                AccOpenWaitingParams l_accOpenWaitingParams = 
                    new AccOpenWaitingParams(l_accOpenWaitingRow);
                
                //�����J�ݐR���҂��f�[�^[Index].�X�V���� = ���������i�o�^�p�j
                l_accOpenWaitingParams.setLastUpdatedTimestamp(l_tsTradingSystem);
                
        		//�Q�|�Q�jQueryProcessor.doUpdateQuery()���\�b�h���R�[������B   
        		//[doUpdateQuery()�ɃZ�b�g����p�����[�^]  
        		//arg0�F�����J�ݐR���҂��f�[�^[Index]
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(l_accOpenWaitingParams);
            }
            catch (DataFindException l_dfe)
            {
                log.error(STR_METHOD_NAME, l_dfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName()
                        + "."
                        + STR_METHOD_NAME,
                    l_dfe.getMessage(),
                    l_dfe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error(STR_METHOD_NAME, l_dne);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName()
                        + "."
                        + STR_METHOD_NAME,
                    l_dne.getMessage(),
                    l_dne);
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (is�R����)<BR>
     * �����J�ݐR���҂��f�[�^�̓��e���R���ς��`�F�b�N����<BR> 
     * <BR>
     * �P�j�����J�ݐR���҂��f�[�^�̌�����Loop���� <BR>
     * <BR>
     * �Q�j�����J�ݐR���҂��f�[�^[Index].�R���敪 != "0"(�R���҂�)�̏ꍇ<BR> 
     * �@@true ��ԋp���� <BR>
     * <BR>
     * �R�j���ׂĂ̌����J�ݐR���҂��f�[�^�̐R���敪��"0"(�R���҂�)�̏ꍇ <BR>
     * �@@false ��ԋp���� <BR>
     * <BR>
     * �� �����J�ݐR���҂��f�[�^�ɂ́A<BR>
     * �����J�ݐR���҂�Param�̃I�u�W�F�N�g���Z�b�g����Ă���<BR> 
     * @@return boolean
     */
    public boolean isChecked()
    {
        final String STR_METHOD_NAME = " isChecked()";
        log.entering(STR_METHOD_NAME);
        
		//�P�j�����J�ݐR���҂��f�[�^�̌�����Loop����
        int l_intAccOpenWaitingParamsSize = 0;
        
        if (this.accOpenWaitingParamsList != null)
        {
            l_intAccOpenWaitingParamsSize = 
                this.accOpenWaitingParamsList.size(); 
        }
        
        int l_intSize = 0;
        for (int i = 0; i < l_intAccOpenWaitingParamsSize; i++)
        {
            AccOpenWaitingRow l_accOpenWaitingRow = 
                (AccOpenWaitingRow) this.accOpenWaitingParamsList.get(i);
            AccOpenWaitingParams l_accOpenWaitingParams = 
                new AccOpenWaitingParams(l_accOpenWaitingRow);
            
            //�Q�j�����J�ݐR���҂��f�[�^[Index].�R���敪 != "0"(�R���҂�)�̏ꍇ 
    		//true ��ԋp����
            
            if (!WEB3CheckDivDef.CHECK_WAITING.equals(l_accOpenWaitingParams.getCheckDiv()))
            {
                log.exiting(STR_METHOD_NAME);
                return true;  
            }
            else
            {
                l_intSize++;
            }
        }
        
        if (l_intSize == l_intAccOpenWaitingParamsSize)
        {
    		//�R�j���ׂĂ̌����J�ݐR���҂��f�[�^�̐R���敪��"0"(�R���҂�)�̏ꍇ 
    		//false ��ԋp���� 
            log.exiting(STR_METHOD_NAME);
            return false;  
        }
        
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (get�R���敪)<BR>
     * �w�肵���擾�s�ԍ��̌����J�ݐR���҂�.�R���敪��ԋp����B<BR> 
     * <BR>
     * �P�jthis.get�����J�ݐR���҂��s������S�������擾����B<BR> 
     * <BR>
     * �Q�j�擾�s�ԍ��̃`�F�b�N <BR>
     * �@@�擾�s�ԍ� < 0 �܂��� �擾�s�ԍ� >= �S�����@@�̏ꍇ <BR>
     * �@@null ��ԋp���� <BR>
     * <BR>
     * �R�j�����J�ݐR���҂��f�[�^.get(�擾�s�ԍ�)�� <BR>
     * �@@�����J�ݐR���҂�Params�I�u�W�F�N�g���擾���� <BR>
     * <BR>
     * �S�j�����J�ݐR���҂�Params.�R���敪��ԋp����<BR> 
     * <BR>
     * �� �����J�ݐR���҂�Params�N���X��DDL��莩����������B<BR>
     * @@param l_intGetLineNo - (�擾�s�ԍ�) <BR>
     * �擾�����������J�ݐR���҂��f�[�^�̍s��<BR>
     * @@return String
     */
    public String getCheckDiv(int l_intGetLineNo)
    {
        final String STR_METHOD_NAME = 
            " getCheckDiv(int l_intGetLineNo)";
        log.entering(STR_METHOD_NAME);
        
		//�P�jthis.get�����J�ݐR���҂��s������S�������擾����B 
        int l_intAccOpenWaitingParamsSize = 
            this.getAccOpenWaitingParamsNumber();
        
		//�Q�j�擾�s�ԍ��̃`�F�b�N 
		//�@@�擾�s�ԍ� < 0 �܂��� �擾�s�ԍ� >= �S�����@@�̏ꍇ 
		//�@@null ��ԋp���� 
        if (l_intGetLineNo < 0 || l_intGetLineNo >= l_intAccOpenWaitingParamsSize)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
		//�R�j�����J�ݐR���҂��f�[�^.get(�擾�s�ԍ�)�� 
		//�@@�����J�ݐR���҂�Params�I�u�W�F�N�g���擾���� 
        String l_strCheckDiv = null;
        
        if (this.accOpenWaitingParamsList != null && 
            !this.accOpenWaitingParamsList.isEmpty())
        {
            AccOpenWaitingRow l_accOpenWaitingRow = 
                (AccOpenWaitingRow) this.accOpenWaitingParamsList.get(
                    l_intGetLineNo);
            
    		//�S�j�����J�ݐR���҂�Params.�R���敪��ԋp����
            l_strCheckDiv = l_accOpenWaitingRow.getCheckDiv();
            log.debug("�����J�ݐR���҂�Params.�R���敪 = " + l_strCheckDiv);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strCheckDiv;
    }
}
@
