head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.08.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�݃\�[�g�L�[(WEB3AccOpenSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 ���w�� �V�K�쐬
*/
package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (�����J�݃\�[�g�L�[)<BR>
 * �����J�݃\�[�g�L�[<BR>
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AccOpenSortKey extends Message 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccOpenSortKey.class);
    
    /**
     * (�L�[����)<BR>
     * �L�[����<BR>
     * <BR>
     * ���N���X�𗘗p�������N�G�X�g�ɑ΂��Ẵ��X�|���X�N���X���̃V���{������<BR>
     * �L�[���ڂƂ���B <BR>
     * �Ώۍ��ڂɂ��ẮA���N���X�𗘗p�������N�G�X�g��`���̋L�q���Q�ƁB<BR>
     */
    public String keyItem;
    
    /**
     * (�����^�~��)<BR>
     * A�F�����@@D�F�~��<BR>
     */
    public String ascDesc;
    
    /**
     * @@roseuid 41B45E7C003E
     */
    public WEB3AccOpenSortKey() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�L�[���ڂ̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�L�[���ڂ�������l�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00085<BR>
     * <BR>
     * �Q�j�@@�����^�~���̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����^�~���������͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00087<BR>
     * �@@�Q�|�Q�j�@@�����^�~�����s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00088<BR>
     * @@throws WEB3BaseException
     * @@roseuid 418F4ABF0240
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�L�[���ڂ̃`�F�b�N
        //�P�|�P�j�L�[���ڂ�������l�̏ꍇ�A��O���X���[����B
        if (keyItem == null || "".equals(keyItem.trim()))
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                getClass().getName() + STR_METHOD_NAME,
                "�L�[���ڂ�������l�̏ꍇ�A��O���X���[����");
                
            log.debug("�����J��.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;     
        }
        
        //�Q�j�����^�~���̃`�F�b�N
        //�Q�|�P�j�����^�~���������͂̏ꍇ�A��O���X���[����B
        if (ascDesc == null || "".equals(ascDesc.trim()))
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                getClass().getName() + STR_METHOD_NAME,
                "�����^�~���������͂̏ꍇ�A��O���X���[����");
                
            log.debug("�����J��.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;    
        }
        
        //�Q�|�Q�j�����^�~�����s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B
        if (!ascDesc.equals(WEB3AscDescDef.ASC) && !ascDesc.equals(WEB3AscDescDef.DESC))
        {            
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                getClass().getName() + STR_METHOD_NAME,
                "�����^�~�����s���ȃR�[�h�l�̏ꍇ�A��O���X���[����");
                
            log.debug("�����J��.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;    
        }
        
        log.exiting(STR_METHOD_NAME);       
    }
}@
