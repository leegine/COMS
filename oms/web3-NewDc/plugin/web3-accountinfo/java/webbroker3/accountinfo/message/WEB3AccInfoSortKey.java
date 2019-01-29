head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.06.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l���\�[�g�L�[(WEB3AccInfoSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ���C�g (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (���q�l���\�[�g�L�[)<BR>
 * ���q�l���\�[�g�L�[<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AccInfoSortKey extends Message 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoSortKey.class);
        
    /**
     * (�L�[����)<BR>
     * �L�[����<BR>
     * <BR>
     * ���N���X�𗘗p�������N�G�X�g�ɑ΂��Ẵ��X�|���X�N���X����<BR>
     * �V���{�������L�[���ڂƂ���B <BR>
     * �Ώۍ��ڂɂ��ẮA���N���X�𗘗p�������N�G�X�g��`���̋L�q���Q�ƁB<BR>
     */
    public String keyItem;
    
    /**
     * (�����^�~��)<BR>
     * A�F�����@@D�F�~��<BR>
     */
    public String ascDesc;
    
    /**
     * @@roseuid 418F38610251
     */
    public WEB3AccInfoSortKey() 
    {
     
    }
    
    /**
     * (validate)<BR>
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�L�[���ڂ̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�L�[���ڂ�������l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00085<BR>
     * <BR>
     * �Q�j�@@�����^�~���̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����^�~���������͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00087<BR>
     * �@@�Q�|�Q�j�@@�����^�~�����s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00088<BR>  
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 416B927001B1
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
       /*
        * �P�j�@@�L�[���ڂ̃`�F�b�N<BR>
        * �@@�P�|�P�j�@@�L�[���ڂ�������l�̏ꍇ�A��O���X���[����B<BR>
        *         class: WEB3BusinessLayerException<BR>
        *         tag:   BUSINESS_ERROR_00085<BR>
        * <BR>
        */
        if(keyItem == null || "".equals(keyItem))
        {
            log.debug("[�L�[����] = " + keyItem);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00085, 
                this.getClass().getName(),
                "�L�[���ڂ�������");
        }
        
       /* 
        * �@@�Q�|�P�j�@@�����^�~���������͂̏ꍇ�A��O���X���[����B<BR>
        *         class: WEB3BusinessLayerException<BR>
        *         tag:   BUSINESS_ERROR_00087<BR>
        */
        if(ascDesc == null || "".equals(ascDesc))
        {
            log.debug("[�����^�~��] = " + ascDesc);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00087, 
                this.getClass().getName(),
                "�����^�~����������");
        }
       /* 
        * �@@�Q�|�Q�j�@@�����^�~�����s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B<BR>
        *         class: WEB3BusinessLayerException<BR>
        *         tag:   BUSINESS_ERROR_00088<BR>
        */
        if(!(WEB3AscDescDef.ASC.equals(ascDesc)) && !(WEB3AscDescDef.DESC.equals(ascDesc)))
        {
            log.debug("[�����^�~��] = " + ascDesc);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00088, 
                this.getClass().getName(),
                "�����^�~�����s���ȃR�[�h�l");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
