head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.28.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenDuplicationCheck.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݏd���`�F�b�N(WEB3AccOpenDuplicationCheck.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/08 ����(���u) �V�K�쐬
*/
package webbroker3.accountopen;

import java.util.Map;

import webbroker3.common.WEB3BaseException;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����J�ݏd���`�F�b�N)<BR>
 * �e�e�[�u���ł̌����Ώۂ̏d���`�F�b�N�@@�\���������郆�[�e�B���e�B�E�N���X�B<BR>
 *   
 * @@author ����
 * @@version 1.0
 */
public abstract class WEB3AccOpenDuplicationCheck 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AccOpenDuplicationCheck.class);
    
    /**
     * �d���ڋq���E�ڋq�R�[�h�L�[���B<BR>
     */
    protected final String KEY_ACCOUNT_CODE = "account_code";
    
    /**
     * �d���ڋq���E���X�R�[�h�L�[���B<BR>
     */
    protected final String KEY_BRANCH_CODE = "branch_code";
    
    /**
     * �d���ڋq���E�e�[�u�����L�[��<BR>
     */
    protected final String KEY_TAB_NAME = "tab_name";
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h�B<BR>
     */
    protected String accountCode;
    
    /**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h�B<BR>
     */
    protected String requestNumber;
    
    /**
     * (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     */
    protected String institutionCode;
    
    /**
     * (�����J�ݏd���`�F�b�N)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * <BR>
     * this.�ڋq�R�[�h = �i�����j�ڋq�R�[�h <BR>
     * this.���ʃR�[�h = �i�����j���ʃR�[�h <BR>
     * this.�،���ЃR�[�h = �i�����j�،���ЃR�[�h <BR>
     * ���Z�b�g����B<BR> 
     * @@param l_strAccountCode - (�ڋq�R�[�h) <BR>
     * �����R�[�h�B<BR>
     * @@param l_strRequestNumber - (���ʃR�[�h) <BR>  
     * ���ʃR�[�h�B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h) <BR>
     * �،���ЃR�[�h�B<BR>
     */
    public WEB3AccOpenDuplicationCheck(
        String l_strAccountCode, String l_strRequestNumber, String l_strInstitutionCode)
    {
        this.accountCode = l_strAccountCode;
        this.requestNumber = l_strRequestNumber;
        this.institutionCode = l_strInstitutionCode;
    }
    
    /**
     * (get�ڋq�R�[�h)<BR>
     * �d���ڋq��񂩂�ڋq�R�[�h���擾����B<BR> 
     * <BR>
     * <BR>
     * [�߂�l]<BR> 
     * ((Map)�d���A�h���X���.get(this.KEY_ACCOUNT_CODE)).toString()<BR>
     * @@param l_objDuplicationAddressInfo - (�d���A�h���X���)<BR>
     * �d���A�h���X���B<BR> 
     * get�d���A�h���X()�̖߂�l�z��̗v�f�iMap�j�B<BR> 
     * @@return String
     */
    public String getAccountCode(Object l_objDuplicationAddressInfo)
    {
        final String STR_METHOD_NAME = "getAccountCode(Object)";
        log.entering(STR_METHOD_NAME);
        String l_strAccountCode = null;
        
        Map l_map = (Map)l_objDuplicationAddressInfo;
        if (l_map.get(KEY_ACCOUNT_CODE) != null)
        {
            l_strAccountCode = l_map.get(KEY_ACCOUNT_CODE).toString();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strAccountCode;
    }
    
    /**
     * (get�d���ڋq���)<BR>
     * �����̌����ΏۂƏd������ڋq�����݂��邩�`�F�b�N���s���B<BR> 
     * ���݂���ꍇ�A���̌ڋq�̕��X�R�[�h�ƌڋq�R�[�h�A�������ꂽ�e�[�u�����̃Z�b�g<BR> 
     * �iMap�j��Object�z��Ɋi�[���A�ԋp����B<BR>    
     * �i�d���ڋq�����݂���ꍇ��Object�z��̒���>0<BR> 
     * <BR> 
     * �T�u�N���X�Ŏ����B<BR> 
     * @@param l_strSearchObject - (�����Ώ�)<BR>
     * �����ΏہB<BR>
     * @@return Object[]
     * @@throws WEB3BaseException 
     */
    public abstract Object[] getDuplicationAccountInfo(String l_strSearchObject) throws WEB3BaseException;
    
    /**
     * (get���X�R�[�h)<BR>
     * �d���ڋq��񂩂畔�X�R�[�h���擾����B<BR>  
     * <BR>
     * <BR>
     * [�߂�l] <BR>
     * ((Map)�d���A�h���X���.get(this.KEY_BRANCH_CODE)).toString()<BR> 
     * <BR>
     * @@param l_objDuplicationAddressInfo - (�d���A�h���X���)<BR>
     * �d���A�h���X���B<BR>
     * get�d���A�h���X()�̖߂�l�z��̗v�f�iMap�j�B<BR>
     * @@return String
     */
    public String getBranchCode(Object l_objDuplicationAddressInfo)
    {
        final String STR_METHOD_NAME = "getBranchCode(Object)";
        log.entering(STR_METHOD_NAME);
        
        String l_strBranchCode = null;
        Map l_map = (Map)l_objDuplicationAddressInfo;
        
        if (l_map.get(KEY_BRANCH_CODE) != null)
        {
            l_strBranchCode = l_map.get(KEY_BRANCH_CODE).toString();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strBranchCode;
    }
    
    /**
     * (get�e�[�u����)<BR>
     * �d���ڋq��񂩂�e�[�u�������擾����B <BR>
     * <BR>
     * <BR>
     * [�߂�l] <BR>
     * ((Map)�d���A�h���X���.get(this.KEY_TAB_NAME)).toString()<BR>
     * @@param l_objDuplicationAddressInfo - (�d���A�h���X���)<BR>
     * �d���A�h���X���B<BR>
     * get�d���A�h���X()�̖߂�l�z��̗v�f�iMap�j�B<BR>
     * @@return String
     */
    public String getTableName(Object l_objDuplicationAddressInfo)
    {
        final String STR_METHOD_NAME = "getTableName(Object)";
        log.entering(STR_METHOD_NAME);
        
        String l_strTableName = null;
        Map l_map = (Map)l_objDuplicationAddressInfo;
        if (l_map.get(KEY_TAB_NAME) != null)
        {
            l_strTableName = l_map.get(KEY_TAB_NAME).toString();
        }
        log.exiting(STR_METHOD_NAME);
        return l_strTableName;
    }

}
@
