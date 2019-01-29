head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.05.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenInspectDenyCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݐR���۔F�������N�G�X�g(WEB3AdminAccOpenInspectDenyCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/16 ���H�n (���u) �V�K�쐬
*/

package webbroker3.accountopen.message;

import webbroker3.accountopen.define.WEB3AccOpenFromWebSortKeyDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ҍ����J�ݐR���۔F�������N�G�X�g)<BR>
 * �Ǘ��Ҍ����J�ݐR���۔F�������N�G�X�g
 * 
 * @@author ���H�n
 * @@version 1.0
 */
public class WEB3AdminAccOpenInspectDenyCompleteRequest extends WEB3GenRequest
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccOpenInspectDenyCompleteRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_AccOpen_inspectDenyComplete";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200606151150L;

	/**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     * <BR>
     * ������������
     */
    public String[] requestNumber;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�
     */
    public String password;
    
    /**
     * (�����J�݃\�[�g�L�[)<BR>
     */
    public WEB3AccOpenSortKey[] sortKeys;
    
    /**
     * @@roseuid 44912C0E009C
     */
    public WEB3AdminAccOpenInspectDenyCompleteRequest() 
    {
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���ʃR�[�h�̃`�F�b�N <BR>
     * �@@�P�|�P�j�@@�i���ʃR�[�h�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B<BR> 
     * <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02472<BR> 
     * <BR> 
     * �@@�P�|�Q�j�@@���ʃR�[�h�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B  <BR>
     * �@@�@@�@@�P�|�Q�|�P�j�@@�i���ʃR�[�h[index] == null�j�̏ꍇ�A ��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00829<BR> 
     * <BR>
     * �Q�j�@@�\�[�g�L�[�̃`�F�b�N  <BR>
     * �@@�Q�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B  <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00231 <BR> 
     * <BR>
     * �@@�Q�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B  <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00232 <BR> 
     * <BR>
     * �@@�Q�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B  <BR>
     * �@@�@@�@@�Q�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B  <BR>
     * �@@�@@�@@�Q�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ڈȊO�̏ꍇ�A ��O���X���[����B<BR>  
     *�@@�@@�@@�@@�@@���ʃR�[�h�irequestNumber�j<BR> 
     *�@@�@@�@@�@@�@@���������ioccurredDate�j <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00086 <BR> 
     * 
     * @@throws WEB3BaseException 
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���ʃR�[�h�̃`�F�b�N  
        //�P�|�P�j�@@�i���ʃR�[�h�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B  
        if (this.requestNumber == null || this.requestNumber.length == 0) 
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02472,
                getClass().getName() + STR_METHOD_NAME,
                "���ʃR�[�h�̗v�f����0�ł��B");
		}      
        
        //�P�|�Q�j�@@���ʃR�[�h�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B        
        for(int i = 0; i < this.requestNumber.length; i++)
        {
            String l_strRequestNumber = this.requestNumber[i];
            
            //�P�|�Q�|�P�j�@@�i���ʃR�[�h[index] == null�j�̏ꍇ�A ��O���X���[����B
        	if (l_strRequestNumber == null) 
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00829,
                    getClass().getName() + STR_METHOD_NAME,
                    "���ʃR�[�h�����w��ł��B");
    		}
        }
              
        // �Q�j �\�[�g�L�[�̃`�F�b�N
        // �Q�|�P�j �\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }
        
        //�Q�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B
        if (this.sortKeys.length == 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }
        
        //�Q�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B
        int l_intCnt = this.sortKeys.length;
        for (int i = 0; i < l_intCnt; i++)
        {
            WEB3AccOpenSortKey l_accOpenSortKey = this.sortKeys[i];
            
            //�Q�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B
            l_accOpenSortKey.validate();
            
            //�Q�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A ��O���X���[����B
            //���ʃR�[�h�irequestNumber�j 
            //���������ioccurredDate�j
            if (!(WEB3AccOpenFromWebSortKeyDef.REQUEST_NUMBER.equals(l_accOpenSortKey.keyItem) ||
                WEB3AccOpenFromWebSortKeyDef.OCCRRRED_DATE.equals(l_accOpenSortKey.keyItem))) 
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    getClass().getName() + STR_METHOD_NAME,
                    "�L�[���ڂɎ��ʃR�[�h�A���������̍��ږ��ȊO�̒l�����݂��Ă��܂��B");         
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenInspectDenyCompleteResponse(this);
    }

    
}
@
